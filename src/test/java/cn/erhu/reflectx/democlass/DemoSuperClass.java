package cn.erhu.reflectx.democlass;

/**
 * DemoSuperClass
 *
 * @author hujunjie
 * @version 1.0
 * @since 02/11/2016 4:20 PM
 */
public class DemoSuperClass {

    public String superPublicStr = "superPublicStr";
    public String superPrivateField = "superPrivateField";
    public String superPrivateStaticField = "superPrivateStaticField";

    public String superPublicMethod() {
        return "superPublicMethod";
    }

    private String superPrivateMethod() {
        return "superPrivateMethod";
    }

    private String superStaticPrivateMethod() {
        return "superStaticPrivateMethod";
    }
}
