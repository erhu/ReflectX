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
        // on object
        Object obj = new ReflectX()
                .on(new DemoClass("hi"))
                .field("privateStr").get();
        Assert.assertEquals(obj, "hi");
    }

    @Test
    public void on2() {
        // on className
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .create("hi")
                .field("privateStr").get();
        Assert.assertEquals(obj, "hi");
    }

    @Test
    public void on3() {
        // on .class
        Object obj = new ReflectX()
                .on(DemoClass.class)
                .create("hi")
                .field("privateStr").get();
        Assert.assertEquals(obj, "hi");
    }

    @Test
    public void on4() {
        // todo test ClassLoader
    }
}
