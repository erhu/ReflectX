package cn.erhu.reflectx;

import org.junit.Assert;
import org.junit.Test;

/**
 * TestOn
 *
 * @author hujunjie
 * @version 1.0
 * @since 9/17/16 12:47 PM
 */
public class TestOn {

    @Test
    public void on1() {
        Object obj = new ReflectX()
                .on(new DemoClass("hi"))
                .field("privateStr");
        Assert.assertEquals(obj, "hi");
    }

    @Test
    public void on2() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .create("hi")
                .field("privateStr");
        Assert.assertEquals(obj, "hi");
    }

    @Test
    public void on3() {
        Object obj = new ReflectX()
                .on(DemoClass.class)
                .create("hi")
                .field("privateStr");
        Assert.assertEquals(obj, "hi");
    }

    @Test
    public void on4() {
        // todo test ClassLoader
    }
}
