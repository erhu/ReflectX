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
public class TestSetField {

    /**
     * 测试 设置对象的私有属性
     */
    @Test
    public void testSetPrivateField() {
        DemoClass obj = (DemoClass) ReflectX
                .on(DemoClass.class)
                .create()
                .set("privateStr", "privateStrNew").get();

        assertEquals(ReflectX.on(obj).field("privateStr").get(), "privateStrNew");
    }

    /**
     * 测试 设置对象的私有静态属性
     */
    @Test
    public void testSetPrivateStaticField() {
        DemoClass obj = (DemoClass) ReflectX
                .on(DemoClass.class)
                .create()
                .set("privateStaticStr", "privateStaticStrNew").get();

        assertEquals(ReflectX.on(obj).field("privateStaticStr").get(), "privateStaticStrNew");
    }

    /**
     * 测试 设置对象父类的私有属性
     */
    @Test
    public void testSetSuperPrivateField() {
        DemoClass obj = (DemoClass) ReflectX
                .on(DemoClass.class)
                .create()
                .set("superPrivateField", "superPrivateFieldNew").get();

        assertEquals(ReflectX.on(obj).field("superPrivateField").get(), "superPrivateFieldNew");
    }

    /**
     * 测试 设置对象父类的静态私有属性
     */
    @Test
    public void testSetSuperPrivateStaticField() {
        DemoClass obj = (DemoClass) ReflectX
                .on(DemoClass.class)
                .create()
                .set("superPrivateStaticField", "superPrivateStaticFieldNew").get();

        assertEquals(ReflectX.on(obj).field("superPrivateStaticField").get(), "superPrivateStaticFieldNew");
    }

}
