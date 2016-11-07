package cn.erhu.reflectx.test;

import static org.junit.Assert.assertEquals;

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
public class TestGetField {

    @Test
    public void testGetPublicField() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .field("publicStr").get();
        assertEquals(obj, "publicStr");
    }

    @Test
    public void testGetPrivateField() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .field("privateStr").get();
        assertEquals(obj, "privateStr");
    }

    @Test
    public void testGetPrivateStaticField() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .field("privateStaticStr").get();
        assertEquals("privateStaticStr", obj);

        // -----------
        /*
        Field field = DemoClass.class.getDeclaredField("privateStaticStr");
        field.setAccessible(true);
        // TODO 为什么不加 下面这行代码, 测试会失败?
        // DemoClass m = new DemoClass();
        Object obj2 = field.get(null);
        assertEquals("privateStaticStr", obj2);*/
    }

    @Test
    public void testGetPublicFieldFromSuperClass() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .field("superPublicStr").get();
        assertEquals("superPublicStr", obj);
    }

    @Test
    public void testGetPrivateFieldFromSuperClass() {
        Object obj = ReflectX
                .on(DemoClass.class)
                .create()
                .field("superPrivateField").get();
        assertEquals("superPrivateField", obj);
    }
}
