package cn.erhu.reflectx;

import static org.junit.Assert.assertEquals;

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
    public void testCreate() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.democlass.DemoClass")
                .create("hello")
                .field("privateStr").get();
        assertEquals(obj, "hello");
    }

    @Test
    public void testGetPrivateField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.democlass.DemoClass")
                .create()
                .field("privateStr").get();
        assertEquals(obj, "privateStr");
    }

    @Test
    public void testGetPublicField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.democlass.DemoClass")
                .create()
                .field("publicStr").get();
        assertEquals(obj, "publicStr");
    }

    @Test
    public void testGetPrivateStaticField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.democlass.DemoClass")
                .field("privateStaticStr").get();
        assertEquals(obj, "privateStaticStr");
    }

    @Test
    public void testGetPublicStaticField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.democlass.DemoClass")
                .field("publicStaticStr").get();
        assertEquals(obj, "publicStaticStr");
    }

}
