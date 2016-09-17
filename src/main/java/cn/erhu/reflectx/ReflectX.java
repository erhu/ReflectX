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
    private Method method;
    private Object instance;

    public ReflectX on(String claName) {
        try {
            clazz = Class.forName(claName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ReflectX on(Class clsName) {
        clazz = clsName;
        return this;
    }

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
            Class<?>[] classes = new Class[p.length];
            int i = 0;
            for (Object o : p) {
                classes[i++] = o.getClass();
            }
            instance = clazz.getConstructor(classes).newInstance(p);
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
                if (instance == null) {
                    throw new NullPointerException("create is null when call non-static field");
                }
            }

            return field.get(instance);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    public ReflectX method(String name, Class<?>... parameterTypes) {
        try {
            method = clazz.getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Object invoke(Object... parameters) {
        // not static method
        if ((method.getModifiers() & Modifier.STATIC) == 0) {
            if (instance == null) {
                throw new NullPointerException("create is null when call non-static method");
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
}
