package com.lsh.demo.bootstone.study.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lsh on 2019-04-25.
 *
 * 在Java中通常实现锁有两种方式，一种是synchronized关键字，另一种是Lock
 * synchronized是基于JVM层面实现的，而Lock是基于JDK层面实现的
 *
 * 对于使用者的直观体验上Lock是比较复杂的，需要lock和unlock
 * 在JDK1.5之后synchronized引入了偏向锁，轻量级锁和重量级锁，
 * 从而大大的提高了synchronized的性能，同时对于synchronized的优化也在继续进行
 *
 * ReentrantLock实现Lock接口，在ReentrantLock中引用了AbstractQueuedSynchronizer的子类，
 * 所有的同步操作都是依靠 AbstractQueuedSynchronizer（队列同步器）实现
 */
public class ReentrantLockLearn {

    public static void main(String[] args) {
        //默认是非公平锁
        ReentrantLock nonFairLock = new ReentrantLock();
        ReentrantLock fairLock = new ReentrantLock(true);
        //非公平  sync = new ReentrantLock.NonfairSync();
        nonFairLock.lock();


    }
}
