package cn.erhu.reflectx;

import cn.erhu.reflectx.democlass.DemoClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * TestCreate
 *
 * @author hujunjie
 * @version 1.0
 * @since 26/09/2016 11:13 PM
 */
public class TestCreate {

    @Test
    public void create1() {
        Object obj = new ReflectX()
                .on(DemoClass.class)
                .create()
                .get();
        Assert.assertEquals(obj.getClass(), DemoClass.class);
    }

    @Test
    public void create2() {
        Object obj = new ReflectX()
                .on(DemoClass.class)
                .create("hi")
                .field("privateStr")
                .get();
        Assert.assertEquals(obj, "hi");
    }
}
