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
 * 3.threadLocal一定要在finally中清理掉，否则可能会影响其他线程
 * @see
 * com.lsh.demo.bootstone.web.controller.ThreadPoolController#testThreadLocal(java.lang.String)
 * 很奇怪，这里get到的数据是错乱的，没用规律，随机性 fixme
 */
public class ThreadLocalLearn {

    private ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> stringThreadLoca2 = new ThreadLocal<>();
    private ThreadLocal<String> stringThreadLoca3 = new ThreadLocal<>();
    private ThreadLocal<String> stringThreadLoca4 = new ThreadLocal<>();


    Random random = new Random(123);

    @Test
    public void testRandom(){
        System.out.println(random.nextInt());
        stringThreadLoca2.set("123");
        stringThreadLocal.set("12233232");
        stringThreadLoca3.set("12233232");

        System.out.println(123);
        stringThreadLocal.get();
        stringThreadLoca2.get();

    }



    @Test
    public void test(){
        FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();
        fastThreadLocal.set("");
        fastThreadLocal.get();
    }


}
