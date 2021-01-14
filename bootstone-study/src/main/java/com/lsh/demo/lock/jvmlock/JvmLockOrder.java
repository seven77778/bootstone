package com.lsh.demo.lock.jvmlock;


import com.lsh.demo.basic.thread.threadpool.MyBasicThreadFactory;
import com.lsh.demo.lock.lockutil.OrderLockServer;
import com.lsh.demo.lock.lockutil.OrderService;
import com.lsh.demo.lock.task.OrderTask;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by LSH on 2018/11/6.
 *
 * @author LSH
 * @date 2018/11/6
 */
public class JvmLockOrder {

    public static void main(String[] args) {

        //手动创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //ExecutorService executorService = MyBasicThreadFactory.getExecutorService();

        final CountDownLatch latch  = new CountDownLatch(1);
        //todo 这个写法感觉不对啊
//        OrderService orderService = new OrderLockServer();
        for(int i=0;i<10;i++) {
            OrderService orderService = new OrderLockServer();
            executorService.submit(new OrderTask(latch, orderService));
        }
        latch.countDown();
        executorService.shutdown();
    }

    /**
     * 手动创建线程池
     * coresize为5时，多个线程复用，为10时，名字都不同
     * 类锁和对象锁
     */
    @Test
    public void test() throws Exception {
        final ExecutorService executorService = MyBasicThreadFactory.getExecutorService();
        final CountDownLatch latch = new CountDownLatch(10);
        //final OrderService orderService = new OrderLockServer();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            final OrderService orderService = new OrderLockServer();
            executorService.execute(() -> System.out
                .printf("线程名称 %s 订单号： %s \r\n", Thread.currentThread().getName(), orderService.getOrderNo()));
        }
        latch.await(500, TimeUnit.MILLISECONDS);
        latch.countDown();
        executorService.shutdown();
        System.out.println("耗时 " + (System.currentTimeMillis() - time));
    }
}
