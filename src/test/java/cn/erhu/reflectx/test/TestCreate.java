package cn.erhu.reflectx.test;

import cn.erhu.reflectx.ReflectX;
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
        Object obj = ReflectX
                .on(String.class)
                .create()
                .get();
        Assert.assertEquals(obj, "");
    }

    @Test
    public void create2() {
        Object obj = ReflectX
                .on(String.class)
                .create("4")
                .get();
        Assert.assertEquals(obj, "4");
    }
}
