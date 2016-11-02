package cn.erhu.reflectx;

import static org.junit.Assert.assertEquals;

import cn.erhu.reflectx.democlass.DemoClass;
import org.junit.Test;

/**
 * TestInvokeMethod
 *
 * @author hujunjie
 * @version 1.0
 * @since 02/11/2016 3:17 PM
 */
public class TestInvokeMethod {

    @Test
    public void testInvokeMethod() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .call("publicMethod", 1).get();
        assertEquals("public 1", obj);
    }

    @Test
    public void testInvokePrivateMethod() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .call("privateMethod", 1).get();
        assertEquals("private 1", obj);
    }

    @Test
    public void testInvokeStaticMethod() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .call("publicStaticMethod", 1).get();
        assertEquals("public static 1", obj);
    }

    @Test
    public void testInvokePublicMethodFromSuperClass() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .call("superPublicMethod").get();
        assertEquals("superPublicMethod", obj);
    }

    @Test
    public void testInvokePrivateMethodFromSuperClass() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .call("superPrivateMethod").get();
        assertEquals("superPrivateMethod", obj);
    }
}
