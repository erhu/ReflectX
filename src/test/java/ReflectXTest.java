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
}
