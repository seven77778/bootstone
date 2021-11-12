package work.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * Created by lsh on 2018/11/6.
 *
 * @author lsh
 * @date 2018/11/06
 *
 * 手动创建线程池，不允许使用Executors来创建
 * 1.newFixedThreadPool/newSingleThreadExecutor 都是使用new LinkedBlockingQueue<Runnable>()
 * 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM
 * 2.newCachedThreadPool/newScheduledThreadPool 其maximumPoolSize都是Integer.MAX_VALUE
 * 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM
 *
 *keepAliveTime：超过coresize的非运行线程
 *LinkedBlockingQueue：使用链表实现的先进先出队列，默认大小为Integer.MAX_VALUE
 *
 */
public class MyBasicThreadFactory {
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE = 10000;
    private static final long KEEPALIVE_TIME = 200L;


    public static ExecutorService getExecutorService(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();

        return new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEPALIVE_TIME, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<Runnable>(1024),threadFactory,new AbortPolicy());
    }

    public static ExecutorService getTestExecutorService(){
        return new ThreadPoolExecutor(1000,99999,5000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(1024));
    }

    public static void main(String[] args) {
        //coresize=0,配合无界队列，会怎么样
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        ThreadPoolExecutor ss = new ThreadPoolExecutor(0, MAXIMUM_POOL_SIZE, KEEPALIVE_TIME, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(), threadFactory, new AbortPolicy());
    }
}
