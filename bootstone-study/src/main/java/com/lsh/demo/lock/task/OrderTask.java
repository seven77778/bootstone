package com.lsh.demo.lock.task;


import com.lsh.demo.lock.lockutil.OrderService;

import java.util.concurrent.CountDownLatch;

/**
 * Created by LSH on 2018/11/6.
 *
 * @author LSH
 * @date 2018/11/6
 */
public class OrderTask implements Runnable {

    private CountDownLatch latch;
    private OrderService orderService;

    public OrderTask(CountDownLatch latch, OrderService orderService) {
        this.latch = latch;
        this.orderService = orderService;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("线程名称 %s 订单号： %s \r\n", Thread.currentThread().getName(),orderService.getOrderNo());
    }
}
