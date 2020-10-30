package com.lsh.demo.lock.task;

import com.lsh.demo.lock.MyBasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池submit 返回值
 * new Callable有返回值
 *
 * submit有Future返回值
 * excute方法会抛出异常。
 * sumbit方法不会抛出异常。除非你调用Future.get()
 *
 */
public class SubmitTest {

    @Test
    public void test1() throws Exception {
        ExecutorService pool = MyBasicThreadFactory.getExecutorService();
        Future<String> f = pool.submit(
                this::get
        );
        System.out.println(f.get());
    }


    public String get() throws Exception{
        Thread.sleep(5000);
        return "1";
    }

    //keepAliveTime为0
    private ExecutorService executor = Executors.newFixedThreadPool(1);
    //keepAliveTime为60
    private ExecutorService executor2 = Executors.newCachedThreadPool();


    /**
     * 1）newFixedThreadPool和newSingleThreadExecutor:
     * 允许的请求队列长度为 Integer.MAX_VALUE
     *   主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
     * LinkedBlockingQueue：使用链表实现的先进先出队列，默认大小为Integer.MAX_VALUE；
     *
     *
     * 2）newCachedThreadPool和newScheduledThreadPool:
     * 允许的创建线程数量为 Integer.MAX_VALUE
     *   主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
     *
     * SynchronousQueue：不保存提交的任务, 数据也不会缓存到队列中, 用于生产者和消费者互等对方, 一起离开.
     */

}
