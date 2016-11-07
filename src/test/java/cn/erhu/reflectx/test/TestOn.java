package cn.erhu.reflectx.test;

import java.util.ArrayList;

import cn.erhu.reflectx.ReflectX;
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
    public void onObject() {
        Object size = ReflectX.on(new ArrayList()).field("size").get();
        Assert.assertEquals(size, 0);
    }

    @Test
    public void onClassName() {
        Object obj = ReflectX.on("java.lang.String").create("Hello").get();
        Assert.assertEquals(obj, "Hello");
    }

    @Test
    public void onDotClass() {
        Object hello = ReflectX.on(Integer.class).create("3").get();
        Assert.assertEquals(hello, 3);
    }

    @Test
    public void onClassLoader() {
        // todo test ClassLoader
    }
}
