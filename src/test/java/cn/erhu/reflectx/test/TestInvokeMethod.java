package cn.erhu.reflectx.test;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import cn.erhu.reflectx.ReflectX;
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

    /**
     * 方法调用
     */
    @Test
    public void testInvokeMethod() {
        Object obj = ReflectX
                .on(String.class)
                .create("Hello")
                .call("charAt", 1).get();
        assertEquals('e', obj);
    }

    /**
     * 私用方法调用
     */
    @Test
    public void testInvokePrivateMethod() {
        Object obj = ReflectX
                .on(ReflectX.class)
                .create()
                .call("wrapper", int.class).get();
        assertEquals(obj, Integer.class);
    }

    /**
     * 静态方法调用
     */
    @Test
    public void testInvokeStaticMethod() {
        List obj = (List) (ReflectX
                .on(Collections.class)
                .call("emptyList").get());
        assertEquals(obj.size(), 0);
    }

    /**
     * 测试超类方法调用
     */
    @Test
    public void testInvokePublicMethodFromSuperClass() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .call("superPublicMethod").get();
        assertEquals("superPublicMethod", obj);
    }

    /**
     * 测试超类私有方法调用
     */
    @Test
    public void testInvokePrivateMethodFromSuperClass() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .call("superPrivateMethod").get();
        assertEquals("superPrivateMethod", obj);
    }

    /**
     * 测试超类私有静态方法调用
     */
    @Test
    public void testInvokePrivateStaticMethodFromSuperClass() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .call("superStaticPrivateMethod").get();
        assertEquals("superStaticPrivateMethod", obj);
    }
}
