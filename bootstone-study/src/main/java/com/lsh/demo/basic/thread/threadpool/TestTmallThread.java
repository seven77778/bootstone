package com.lsh.demo.basic.thread.threadpool;

import com.lsh.demo.lock.MyBasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.ExecutorService;

/**
 * Created by lsh on 2019-05-16.
 */
public class TestTmallThread {

    /**
     * learn Tamll
     * 1. 控制台直接打印出4853个线程名字，然后卡住不动
     * 2. 所以名字都是pool-1开头，但是tmall的 有 pool-83312-thread-1  pool-83030-thread-1，线程池无限多个？ todo
     * pool-1-thread-54
     * pool-1-thread-50
     * pool-1-thread-46
     * pool-1-thread-42
     * pool-1-thread-30
     * pool-1-thread-26
     * pool-1-thread-10
     * pool-1-thread-14
     * 3. core max 数量不同，创建到 pool-7 才停止
     * @param args
     */

    public static void main(String[] args) {
        ExecutorService pool = MyBasicThreadFactory.getExecutorService();

        for(int i=0;i<10000;i++){
            pool.submit(() ->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }


    /**
     * learn threadgroup 逻辑
     */
    @Test
    public void test(){
        SecurityManager s = System.getSecurityManager();
        System.out.println(s);
    }
}
