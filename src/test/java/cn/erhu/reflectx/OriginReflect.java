package cn.erhu.reflectx;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import cn.erhu.reflectx.democlass.DemoClass;

/**
 * OriginReflect
 *
 * @author hujunjie
 * @version 1.0
 * @since 9/16/16 9:54 AM
 */
public class OriginReflect {

    public static void main(String[] args) {
        DemoClass obj = new DemoClass();
        Class cls = obj.getClass();

        printClass(cls);
        printMethods(cls);
        println("----------");
        printDeclaredMethods(cls);
        printMetaData(cls);
        printAnnotationMethod(cls);
        newInstanceFromClass(cls);
        invokeMethod(cls);
        invokeStaticMethod(cls);
    }

    public static void printMethods(Class cls) {
        for (Method method : cls.getMethods()) {
            println(method);
        }
    }

    public static void printDeclaredMethods(Class cls) {
        for (Method method : cls.getDeclaredMethods()) {
            println(method);
        }
    }

    public static void invokeMethod(Class<?> cls) {
        try {
            Object obj = cls.newInstance();
            if (obj != null) {
                Method method = cls.getMethod("publicMethod", int.class);
                println(method.invoke(obj, 3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invokeStaticMethod(Class<?> cls) {
        try {
            Method method = cls.getMethod("publicStaticMethod", int.class);
            println(method.invoke(null, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newInstanceFromClass(Class cls) {
        try {
            Object obj = cls.newInstance();
            println(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void printMetaData(Class cls) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            println(method);
        }
    }

    private static void printAnnotationMethod(Class cls) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            for (Annotation annotation : method.getAnnotations()) {
                println(annotation);
            }
        }
    }

    private static void printClass(Class cls) {
        println(cls);
        try {
            Class cls2 = Class.forName("cn.erhu.reflectx.DemoClass");
            println(cls2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        println(String.class);
        println(Integer.class);
        println(int.class);
        println(char[].class);
        println(float[].class);
        println(double[].class);
        println(long[].class);
        println(int[].class);
    }

    private static void println(Object o) {
        if (o != null) {
            System.out.println(o.toString());
        }
    }
}
