package com.lsh.demo.bootstone.study.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created by lsh on 2019-04-23.
 * <p>
 * write a myself thread pool todo
 * <p>
 * LinkedBlockingQueue和LinkedBlockingDeque
 * <p>
 * 优点：1.降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗
 * 2.提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行
 * 3.管理线程
 * <p>
 * <p>
 * <p>
 * <p>
 * 【强制】线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。
 * 说明：使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资
 * 源不足的问题。如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者
 * “过度切换”的问题。
 * <p>
 * <p>
 * <p>
 * 【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样
 * 的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
 * 说明： Executors 返回的线程池对象的弊端如下：
 * 1） FixedThreadPool 和 SingleThreadPool :
 * 允许的请求队列长度为 Integer.MAX_VALUE ，可能会堆积大量的请求，从而导致 OOM 。
 * 2） CachedThreadPool 和 ScheduledThreadPool :
 * 允许的创建线程数量为 Integer.MAX_VALUE ，可能会创建大量的线程，从而导致 OOM 。
 */
public class ThreadPoolLearn {

    public static void main(String[] args) {
        /*
         * 其实都是 ThreadPoolExecutor
         * fixed  是 Integer.MAX_VALUE，无边界队列，可能会导致内存问题
         */
        Executor executor = Executors.newFixedThreadPool(1024);

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(10, 20, 1000,
                        TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(20),
                        new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build());

        ThreadPoolExecutor threadPoolExecutor1 =
                new ThreadPoolExecutor(1, 2, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1024),
                        new ThreadFactoryBuilder().setNameFormat("demo2-pool-%d").build());



        /*
         throw RejectedExecutionException
         执行了40个
         */
        for(int i=0;i<100;i++){
            threadPoolExecutor.submit(() ->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();


        /**
         * params
         *
         * 工作策略
         * corePooSize -> 队列 -> maximumPoolSize -> 拒绝策略
         *
         *1 corePoolSize
         * 核心线程数
         *
         *
         *2 maximumPoolSize
         * 线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务
         *
         *3 keepAliveTime
         * 线程存活时间,对于超过核心线程数的线程，当线程处理空闲状态下，且维持时间达到keepAliveTime时，线程将被销毁
         *
         *4 workQueue
         * 工作队列，用于存在待执行的线程任务
         *
         *5 threadFactory
         * 创建线程的工厂，通过setName区分不同线程池所创建出来的线程
         *
         *6 handler
         * 当到达线程数上限或工作队列已满时的拒绝处理逻辑
         *
         *7 unit
         * keepAliveTime的时间单位
         *
         */

        /**
         * 任务仓库队列 -- BlockingQueue<Runnable> workQueue
         *
         * LinkedBlockingQueue
         * 最常用，使用链表实现的FIFO队列，构造时可以指定大小，默认大小为Integer.MAX_VALUE
         *
         * ArrayBlockingQueue
         *
         * SynchronousQueue
         *
         * PriorityBlockingQueue
         *
         */




        /* note

        1. new LinkedBlockingQueue<Runnable>()，这是一个无边界队列，如果不断的往里加任务时，最终会导致内存问题

        2. Runnable和Callable

            可以向线程池提交的任务有两种：Runnable和Callable，二者的区别如下：

            方法签名不同:
            void Runnable.run()
            V Callable.call() throws Exception

            是否允许有返回值，Callable允许有返回值
            是否允许抛出异常，Callable允许抛出异常。
            Callable是JDK1.5时加入的接口，作为Runnable的一种补充，允许有返回值，允许抛出异常
            Runnable jdk1.0 即存在

        3. submit 提交有返回值
           execute 无返回值


         */





        /* question todo
        1. corePoolSize会自动销毁吗

        不会，当达到corePoolSize数目之后，就会维持至少corePoolSize数目的Thread在pool中，哪怕他们都处于空闲状态（idle）
        超过core的max，在到达live time会销毁

        2.线程数量设置多少合适

        3.LinkedBlockingQueue  和  LinkedBlockingDeque 区别


         */
    }
}
