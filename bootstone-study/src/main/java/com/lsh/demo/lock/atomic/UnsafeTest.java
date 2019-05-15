package com.lsh.demo.lock.atomic;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by lsh on 2018/11/7 16:17.
 *
 * @author lsh
 * @date 2018/11/07
 *
 * 1.final 的含义是只能被赋值一次。。。！！！
 */
public class UnsafeTest {

    private  long valueOffset ;
    private volatile int value;
    private volatile long  mylong;


    //private static final Unsafe myUnsafe = Unsafe.getUnsafe();

    /**
     * 测试unsafe中compareAndSwapInt 用法
     * int valueOffset等于12
     * long valueOffset等于24 ？？
     * */
    @Test
    public  void test() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        valueOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("value"));
        System.out.println(unsafe.getAndAddInt(this, valueOffset, 1));
        System.out.println(unsafe.getAndAddInt(this, valueOffset, 1));
    }

    /**
     * AtomicInteger 中直接使用
     * private static final Unsafe unsafe = Unsafe.getUnsafe();
     * getDeclaredField --包括public、private和proteced
     * getFields()--获得公共字段
     *
     * out:异常
     * java.lang.ExceptionInInitializerError
     Caused by: java.lang.SecurityException: Unsafe
     * */
    @Test
    public void test2() throws Exception {
        final Unsafe myUnsafe = Unsafe.getUnsafe();
        long valueOffset = myUnsafe.objectFieldOffset(UnsafeTest.class.getField(""));
        myUnsafe.getAndAddInt(this, valueOffset, 2);
        int res = myUnsafe.getAndAddInt(this, valueOffset, 2);
        System.out.println(res);
    }

}
