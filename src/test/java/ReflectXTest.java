import cn.erhu.reflectx.ReflectX;
import org.junit.Assert;
import org.junit.Test;


/**
 * ReflectXTest
 *
 * @author hujunjie
 * @version 1.0
 * @since 9/16/16 11:38 AM
 */
public class ReflectXTest {

    @Test
    public void testInvokeMethod() {
        Object obj = new ReflectX().on("cn.erhu.reflectx.DemoClass")
                .instance()
                .method("publicMethod", int.class)
                .invoke(1);
        Assert.assertEquals("public 1", obj);
    }

    @Test
    public void testInvokePrivateMethod() {
        Object obj = new ReflectX().on("cn.erhu.reflectx.DemoClass")
                .instance()
                .method("privateMethod", int.class)
                .invoke(1);
        Assert.assertEquals("private 1", obj);
    }

    @Test
    public void testInvokeStaticMethod() {
        Object obj = new ReflectX().on("cn.erhu.reflectx.DemoClass")
                .method("publicStaticMethod", int.class)
                .invoke(1);
        Assert.assertEquals("public static 1", obj);
    }


}
