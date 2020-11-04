package com.lsh.demo.basic.thread.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * Created by lsh on 2019-05-13.
 *
 * @see WeakReference 防止内存泄漏
 *
 * @see ThreadLocal
 *
 * 1. set 方法
 * ThreadLocalMap
 */
public class ThreadLocalLearn {

    private ThreadLocal<String> stringThreadLocal;


    @Test
    public void test(){

        FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();
        fastThreadLocal.set("");
    }


}
