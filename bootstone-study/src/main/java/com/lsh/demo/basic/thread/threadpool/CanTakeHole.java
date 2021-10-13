package com.lsh.demo.basic.thread.threadpool;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CanTakeHole {
    /**
     * submit , execute 带来的坑
     */
    /**
     * 统计任务已经执行的数量
     * key:任务名称
     * value:数量
     */
    private static Map<String, AtomicInteger> countTasks = new ConcurrentHashMap<>();

    public static void  main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(1024)){
            /**
             * 任务执行完后统计任务执行的数量
             * @param r
             * @param t
             */
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                countTasks.compute(r.toString(), (s, atomicInteger) ->
                        new AtomicInteger(atomicInteger == null ? 0 : atomicInteger.incrementAndGet()));
            }
        };
        /**
         * 源源不断的任务添加进线程池被执行
         */
        for (int i =0; i < 10; i++) {
            threadPoolExecutor.submit(new SimpleRunnable());
        }
        CountDownLatch cd = new CountDownLatch(1);
        cd.await(10, TimeUnit.SECONDS);
        System.out.println(JSON.toJSONString(countTasks));
        threadPoolExecutor.shutdownNow();
    }
    static class SimpleRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("simple task");
        }

        @Override
        public String toString(){
            return this.getClass().getSimpleName();
        }
    }
}
