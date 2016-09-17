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
    private Object object;

    public ReflectX on(Class clsName) {
        clazz = clsName;
        return this;
    }

    public ReflectX on(String claName) {
        try {
            clazz = Class.forName(claName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ReflectX on(String claName, ClassLoader loader) {
        try {
            clazz = Class.forName(claName, true, loader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ReflectX on(Object object) {
        this.object = object;
        this.clazz = object.getClass();
        return this;
    }

    public ReflectX create() {
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ReflectX create(Object... p) {
        try {
            object = clazz.getConstructor(types(p)).newInstance(p);
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

    public Object field(String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);

            // not static field
            if ((field.getModifiers() & Modifier.STATIC) == 0) {
                if (object == null) {
                    throw new NullPointerException("create is null when call non-static field");
                }
            }

            return field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object call(String name, Object... objs) {
        Method method = null;
        Class<?>[] pTypes = types(objs);

        try {
            method = clazz.getDeclaredMethod(name, pTypes);
        } catch (NoSuchMethodException e) {
            boolean found = false;
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().equals(name)
                        && match(m.getParameterTypes(), pTypes)) {
                    method = m;
                    found = true;
                    break;
                }
            }
            if (!found) {
                e.printStackTrace();
            }
        }

        if (method != null) {
            method.setAccessible(true);
            return invoke(object, method, objs);
        }

        return null;
    }

    private Object invoke(Object instance, Method method, Object... parameters) {
        // not static call
        if ((method.getModifiers() & Modifier.STATIC) == 0) {
            if (instance == null) {
                throw new NullPointerException("create is null when call non-static call");
            }
        }

        try {
            return method.invoke(instance, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?>[] types(Object... obj) {
        Class<?>[] types = new Class[obj.length];
        for (int i = 0; i < obj.length; i++) {
            types[i] = obj[i].getClass();
        }
        return types;
    }

    private boolean match(Class<?>[] p1, Class<?>[] p2) {
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
