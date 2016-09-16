package cn.erhu.reflectx;

/**
 * ReflectXTest
 *
 * @author hujunjie
 * @version 1.0
 * @since 9/16/16 11:01 AM
 */
public class ReflectXTest {
    public static void main(String[] args) {
        //invokeMethod();
        //invokePrivateMethod();
        //invokeStaticMethod();
    }

    private static void invokeMethod() {
        Object obj = new ReflectX().on(DemoClass.class)
                .instance()
                .method("publicMethod", int.class)
                .invoke(10);

        System.out.println(obj);
    }

    private static void invokePrivateMethod() {
        Object obj = new ReflectX().on("cn.erhu.reflectx.DemoClass")
                .instance()
                .method("privateMethod", int.class)
                .invoke(1);

        System.out.println(obj);
    }

    private static void invokeStaticMethod() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .method("publicStaticMethod", int.class)
                .invoke(10);

        System.out.println(obj);
    }
}
