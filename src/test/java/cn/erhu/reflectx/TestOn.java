package cn.erhu.reflectx;

import cn.erhu.reflectx.democlass.DemoClass;
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

    /**
     * on object
     */
    @Test
    public void on1() {
        Object obj = ReflectX
                .on(new DemoClass("hi"))
                .field("privateStr").get();
        Assert.assertEquals(obj, "hi");
    }

    /**
     * on className
     */
    @Test
    public void on2() {
        Object obj = ReflectX
                .on("cn.erhu.reflectx.democlass.DemoClass")
                .create("hi")
                .field("privateStr").get();
        Assert.assertEquals(obj, "hi");
    }

    @Test
    public void on3() {
        // on .class
        Object obj = ReflectX
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
