package cn.erhu.reflectx;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * ReflectX
 *
 * @author hujunjie
 * @version 1.0
 * @since 9/16/16 10:40 AM
 */
public class ReflectX {

    private Class<?> clazz;
    private Object instance;

    /*------------------*/
    /*------- on -------*/
    /*------------------*/
    public static ReflectX on(Class clsName) {
        ReflectX x = new ReflectX();
        x.clazz = clsName;
        return x;
    }

    public static ReflectX on(String clsName) {
        ReflectX x = new ReflectX();
        try {
            x.clazz = Class.forName(clsName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static ReflectX on(String clsName, ClassLoader loader) {
        ReflectX x = new ReflectX();
        try {
            x.clazz = Class.forName(clsName, true, loader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static ReflectX on(Object object) {
        ReflectX x = new ReflectX();
        if (object != null) {
            x.instance = object;
            x.clazz = object.getClass();
        }
        return x;
    }

    /*------------------*/
    /*----- create -----*/
    /*------------------*/
    public ReflectX create() {
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ReflectX create(Object... p) {
        try {
            instance = clazz.getConstructor(types(p)).newInstance(p);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return this;
    }


    /*------------------*/
    /*------ field -----*/
    /*------------------*/
    public ReflectX field(String name) throws ReflectXException {
        Field field = null;
        Class<?> tmpClass = clazz;

        // 沿着类的继承结构查找 Field, 如果当前类没找到, 会从递归从父类从查找。
        // (主要用于在父类中查找 protected 和 private field)
        while (tmpClass != null && field == null) {
            field = findField(tmpClass, name);
            tmpClass = tmpClass.getSuperclass();
        }

        if (field != null) {

            // 调用非静态属性时, instance 不能为空
            if ((field.getModifiers() & Modifier.STATIC) == 0) {
                if (instance == null) {
                    throw new NullPointerException("Instance is null when call non-static field");
                }
            }

            try {
                return on(field.get(instance));
            } catch (Exception e) {
                throw new ReflectXException(e);
            }

        } else {
            throw new ReflectXException(new Exception(String.format("%s not found", name)));
        }
    }

    /**
     * 根据类名和属性名称, 查找属性
     *
     * @param clazz     要查找的类
     * @param fieldName 要查找的属性的名称
     * @return 属性
     */
    private Field findField(Class<?> clazz, String fieldName) {
        Field field = null;
        if (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                try {
                    field = clazz.getField(fieldName);
                } catch (NoSuchFieldException e1) {
                    throw new ReflectXException(e1);
                }
            }
        }
        if (field != null) {
            field.setAccessible(true);
        }
        return field;
    }



    /*------------------*/
    /*---- set field ---*/
    /*------------------*/
    /**
     * 给指定属性设置值
     *
     * @param fieldName 属性名称
     * @param val       值
     * @throws ReflectXException
     */
    public ReflectX set(String fieldName, Object val) throws ReflectXException {
        Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                field.set(instance, val);
            } catch (IllegalAccessException e) {
                throw new ReflectXException(e);
            }
        }
        return this;
    }

    /*------------------*/
    /*------ call ------*/
    /*------------------*/
    public ReflectX call(String name, Object... objs) {
        Method method = null;
        Class<?> tmpClass = clazz;
        Class<?>[] pTypes = types(objs);

        // 沿着类的继承结构查找 Method, 如果当前类没找到, 会从递归从父类从查找。
        // (主要用于在父类中查找 protected 和 private 方法)
        while (tmpClass != null && method == null) {
            method = findMethod(tmpClass, name, pTypes);
            tmpClass = tmpClass.getSuperclass();
        }

        if (method != null) {
            method.setAccessible(true);
            return on(invoke(instance, method, objs));
        }

        throw new ReflectXException(
                new NoSuchMethodException(String.format("%s(%s) not found", name, objs)));
    }

    /**
     * 根据类名和方法参数列表来查找方法
     *
     * @param clazz      要查找的类
     * @param methodName 要查找的目标方法名称
     * @param pTypes     目标方法参数列表
     * @return 方法
     */
    private Method findMethod(Class<?> clazz, String methodName, Class<?>[] pTypes) {
        Method method = null;
        if (clazz != null) {
            try {
                method = clazz.getDeclaredMethod(methodName, pTypes);
            } catch (NoSuchMethodException e) {
                // 如果在 declaredMethods 中未找到方法, 将参数中的数字类型放宽要求进行匹配(视包装数字类型和原始数字类型等价)
                method = findMethod(clazz.getDeclaredMethods(), methodName, pTypes);
                // 尝试获取 class 所有的 public 方法(包含父类和接口的 public 方法)
                if (method == null) {
                    try {
                        method = clazz.getMethod(methodName, pTypes);
                    } catch (NoSuchMethodException e1) {
                        method = findMethod(clazz.getMethods(), methodName, pTypes);
                    }
                }
            }
        }
        return method;
    }

    /**
     * 从 methods 中匹配 method, 放宽数字类型要求
     */
    private Method findMethod(Method[] methods, String methodName, Class<?>[] pTypes) {
        if (methods != null) {
            for (Method m : methods) {
                if (m.getName().equals(methodName) && match(m.getParameterTypes(), pTypes)) {
                    return m;
                }
            }
        }
        return null;
    }

    /*------------------*/
    /*----- get --------*/
    /*------------------*/
    public Object get() {
        return instance;
    }

    /*------------------*/
    /*---- private -----*/
    /*------------------*/
    private Object invoke(Object instance, Method method, Object... parameters) {
        // not static call
        if ((method.getModifiers() & Modifier.STATIC) == 0) {
            if (instance == null) {
                throw new NullPointerException("Instance is null when call non-static method");
            }
        }

        try {
            return method.invoke(instance, parameters);
        } catch (IllegalAccessException e) {
            throw new ReflectXException(e);
        } catch (InvocationTargetException e) {
            throw new ReflectXException(e);
        }
    }

    private Class<?>[] types(Object... obj) {
        Class<?>[] types = new Class[obj.length];
        for (int i = 0; i < obj.length; i++) {
            types[i] = obj[i].getClass();
        }
        return types;
    }

    private boolean match(Class<?>[] p1, Class<?>[] p2) {
        if (p1.length != p2.length) {
            return false;
        }

        int i = 0;
        for (Class<?> c1 : p1) {
            if (!wrapper(c1).isAssignableFrom(wrapper(p2[i]))) {
                return false;
            }
        }
        return true;
    }

    private Class<?> wrapper(Class<?> c) {
        if (c.isPrimitive()) {
            if (c == int.class) {
                return Integer.class;
            } else if (c == short.class) {
                return Short.class;
            } else if (c == byte.class) {
                return Byte.class;
            } else if (c == long.class) {
                return Long.class;
            } else if (c == float.class) {
                return Float.class;
            } else if (c == double.class) {
                return Double.class;
            } else if (c == char.class) {
                return Character.class;
            } else if (c == boolean.class) {
                return Boolean.class;
            } else if (c == Void.class) {
                return Void.class;
            }
        }
        return c;
    }
}
