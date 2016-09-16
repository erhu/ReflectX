package cn.erhu.reflectx;

/**
 * DemoClass
 *
 * @author hujunjie
 * @version 1.0
 * @since 9/16/16 9:58 AM
 */
public class DemoClass {

    public static String publicStaticStr;
    public String publicStr;

    private static String privateStaticStr;
    private String privateStr;

    public String publicMethod(int i) {
        return String.format("public %d", i);
    }

    private String privateMethod(int i) {
        return String.format("private %d", i);
    }

    public static String publicStaticMethod(int i) {
        return String.format("public static %d", i);
    }

    @Deprecated
    public void deprecatedMethod() {
        // nothing
    }

    public class PublicInnerClass {

    }

    private class PrivateInnerClass {

    }
}
