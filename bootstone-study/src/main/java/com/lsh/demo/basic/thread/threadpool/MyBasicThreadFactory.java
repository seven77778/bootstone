package com.lsh.demo.basic.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * Created by lsh on 2018/11/6.
 *
 * @author lsh
 * @date 2018/11/06
 * <p>
 * 手动创建线程池，不允许使用Executors来创建
 * 1.newFixedThreadPool/newSingleThreadExecutor 都是使用new LinkedBlockingQueue<Runnable>()
 * 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM
 * 2.newCachedThreadPool/newScheduledThreadPool 其maximumPoolSize都是Integer.MAX_VALUE
 * 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM
 * <p>
 * keepAliveTime：超过coresize的非运行线程
 * 官方文档：这是多余空闲线程在终止之前等待新任务的最大时间。
 * <p>
 * <p>
 * LinkedBlockingQueue：使用链表实现的先进先出队列，默认大小为Integer.MAX_VALUE
 * <p>
 * submit有Future返回值
 * excute方法会抛出异常。
 * sumbit方法不会抛出异常。除非你调用Future.get()
 * <p>
 * excute入参Runnable
 * submit入参可以为Callable，也可以为Runnable
 */
public class MyBasicThreadFactory {

    private static final int CORE_POOL_SIZE = 100;
    private static final int MAXIMUM_POOL_SIZE = 10000;
    private static final long KEEPALIVE_TIME = 200L;



    public static ExecutorService getExecutorService() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEPALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(1024), threadFactory, new AbortPolicy());
    }
    public static ExecutorService getExecutorService(int num) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        return new ThreadPoolExecutor(num, num, KEEPALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(1024), threadFactory, new AbortPolicy());
    }


    //神奇，灵狐插件不报错。。。
    private ExecutorService executor = Executors.newFixedThreadPool(1);
    private ExecutorService executor2 = Executors.newScheduledThreadPool(11);

}
