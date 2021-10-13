package com.lsh.demo.bootstone.web.test;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class HashedWheelTimerTest {

    private CountDownLatch countDownLatch = new CountDownLatch(2);


    @Test
    public void test() throws Exception{
        //定义一个HashedWheelTimer，有16个格的轮子，每一秒走一个一个格子
        HashedWheelTimer timer = new HashedWheelTimer(1, TimeUnit.SECONDS, 16);
        //把任务加到HashedWheelTimer里，到了延迟的时间就会自动执行

        timer.newTimeout((timeout) -> {
            System.out.println("task1 execute");
            countDownLatch.countDown();
        }, 500, TimeUnit.MILLISECONDS);

        timer.newTimeout((timeout) -> {
            System.out.println("task2 execute");
            countDownLatch.countDown();
        }, 2, TimeUnit.SECONDS);

        countDownLatch.await();

        timer.stop();

    }
}
