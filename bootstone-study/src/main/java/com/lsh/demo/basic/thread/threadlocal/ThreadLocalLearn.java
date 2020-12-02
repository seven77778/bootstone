package com.lsh.demo.basic.thread.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * Created by lsh on 2019-05-13.
 *
 * @see WeakReference 防止内存泄漏
 *
 * @see ThreadLocal
 *
 * 1. set 方法
 * ThreadLocalMap
 *
 * 2.threadLocal 提升性能
 *
 */
public class ThreadLocalLearn {

    private ThreadLocal<String> stringThreadLocal;


    Random random = new Random(123);

    @Test
    public void testRandom(){
        System.out.println(random.nextInt());

    }



    @Test
    public void test(){
        FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();
        fastThreadLocal.set("");
    }


}
