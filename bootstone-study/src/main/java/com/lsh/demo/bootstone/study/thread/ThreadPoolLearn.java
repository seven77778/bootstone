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
 * 【强制】线程池不允许使用 Executors 去创建，而是通过 new ThreadPoolExecutor 的方式，这样
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
         * fixed 是new LinkedBlockingQueue<Runnable>()，长度默认为 Integer.MAX_VALUE，无边界队列，可能会导致内存问题
         */
        Executor executor = Executors.newFixedThreadPool(1024);
        /*
          maximumPoolSize Integer.MAX_VALUE
         */
        Executor executor2 = Executors.newCachedThreadPool();
        Executor executor3 = Executors.newFixedThreadPool(1024);
        Executor executor4 = Executors.newFixedThreadPool(1024);

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(10, 20, 1000,TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(),
                        new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build());

        ThreadPoolExecutor threadPoolExecutor1 =
                new ThreadPoolExecutor(1, 2, 100, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(1024),
                        new ThreadFactoryBuilder().setNameFormat("demo2-pool-%d").build());

        /*
             LinkedBlockingQueue 和 ArrayBlockingQueue

             相同点：

             1.LinkedBlockingQueue和ArrayBlockingQueue都是可阻塞的队列，
             内部都是使用ReentrantLock和Condition来保证生产和消费的同步
             2.LinkedBlockingQueue和ArrayBlockingQueue都实现了BlockingQueue接口


             区别：

             1. 底层实现不同
             LinkedBlockingQueue内部维护的是一个链表结构
             在生产和消费的时候，需要创建Node对象进行插入或移除，大批量数据的系统中，其对于GC的压力会比较大

             而ArrayBlockingQueue内部维护了一个数组

             在生产和消费的时候，是直接将枚举对象插入或移除的，不会产生或销毁任何额外的对象实例

             2.锁具体实现不同
             LinkedBlockingQueue中的锁是分离的，生产者的锁PutLock，消费者的锁takeLock
             而ArrayBlockingQueue生产者和消费者使用的是同一把锁

             3.构造区别
             LinkedBlockingDeque 可以不指定大小，无界队列
             ArrayBlockingQueue 必须指定大小





         */


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
         *  ArrayBlockingQueue是一个边界缓冲对流，底层是array，他是FIFO先进先出的。
         * 新插入的数据会被插入到队列的末尾，而且总是从队头取数据
         *
         * SynchronousQueue
         *
         * PriorityBlockingQueue
         *
         */

        /* 队列的基本操作 poll + peek +offer 不抛异常

        1. remove() 和 poll()

        方法都是从队列中删除第一个元素
        remove() 的行为与 Collection 接口的版本相似，
        但是新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null。
        因此新的方法更适合容易出现异常条件的情况。



        2. element() 和 peek() 用于在队列的头部查询元素

        与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null


        3. offer 和 add区别：

        offer: 将指定元素插入此队列中（如果立即可行且不会违反容量限制），
        成功时返回 true，如果当前没有可用的空间，则返回 false，不会抛异常

        一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。
        这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，
        而只是得到由 offer() 返回的 false。

        4. take: 获取并移除此队列的头部，在元素变得可用之前一直等待 。queue的长度 == 0 的时候，一直阻塞

        5. put 将指定元素插入此队列中，将等待可用的空间.通俗点说就是>maxSize 时候，阻塞，直到能够有空间插入元素

         */



        /* 队列大全

        Java并发包中的阻塞队列一共7个，当然他们都是线程安全的。

        　　ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。

        　　LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。

        　　PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。

        　　DealyQueue：一个使用优先级队列实现的无界阻塞队列。

        　　SynchronousQueue：一个不存储元素的阻塞队列。

        　　LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。

        　　LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。

               （摘自《Java并发编程的艺术》）

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

         - 什么是IO密集型 -- 适合多线程
         有阻塞的状态,就是不一直会运行CPU(中间就一个等待状态,就告诉CPU 等待状态,这个就叫IO密集型),例如:sleep 状态等

         在服务器上进行网络通讯、网络传输、磁盘读写等均为IO操作，多为网络请求压力大、磁盘读写频繁的操作
            io密集型的可能需要对磁盘进行升级、提高磁盘的相应速度和传输效率或通过负载技术将文件读写分散到多台服务器中；
            如果网络请求负载较高，可通过负载均衡技术、水平扩展提高负载

         - 什么是CPU密集型 -- 不要太多线程
            cpu经常占用到100% ，没有任何等待
            多用来做计算、逻辑判断等cpu操作。可考虑通过消息队列或其他降维算法，将计算分散到不同的计算节点

        3.LinkedBlockingQueue  和  LinkedBlockingDeque 区别


         */
    }
}
