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
                .on("cn.erhu.reflectx.DemoClass")
                .create("hello")
                .field("privateStr");
        assertEquals(obj, "hello");
    }

    @Test
    public void testInvokeMethod() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .create()
                .method("publicMethod", int.class)
                .invoke(1);
        assertEquals("public 1", obj);
    }

    @Test
    public void testInvokePrivateMethod() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .create()
                .method("privateMethod", int.class)
                .invoke(1);
        assertEquals("private 1", obj);
    }

    @Test
    public void testInvokeStaticMethod() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .method("publicStaticMethod", int.class)
                .invoke(1);
        assertEquals("public static 1", obj);
    }


    @Test
    public void testGetPrivateField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .create()
                .field("privateStr");
        assertEquals(obj, "privateStr");
    }

    @Test
    public void testGetPublicField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .create()
                .field("publicStr");
        assertEquals(obj, "publicStr");
    }

    @Test
    public void testGetPrivateStaticField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .field("privateStaticStr");
        assertEquals(obj, "privateStaticStr");
    }

    @Test
    public void testGetPublicStaticField() {
        Object obj = new ReflectX()
                .on("cn.erhu.reflectx.DemoClass")
                .field("publicStaticStr");
        assertEquals(obj, "publicStaticStr");
    }

}
