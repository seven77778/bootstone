package com.lsh.demo.bootstone.study.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created by lsh on 2019-04-23.
 *
 * write a myself thread pool todo
 *
 * LinkedBlockingQueue和LinkedBlockingDeque
 *
 */
public class MyThreadPool {

    public static void main(String[] args) {
        /**
         * 其实都是 ThreadPoolExecutor
         */
        Executor executor = Executors.newFixedThreadPool(1024);

        /**
         *
         */
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1,20,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(1024),
                        new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build());



    }
}
