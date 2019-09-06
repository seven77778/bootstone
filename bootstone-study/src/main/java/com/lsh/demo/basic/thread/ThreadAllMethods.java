package com.lsh.demo.basic.thread;

/**
 * Created by lsh on 2019-09-06.
 *
 * wait: 释放当前锁，阻塞直到被notify或notifyAll唤醒，或者超时，或者线程被中断(InterruptedException)
 * notify: 任意选择一个（无法控制选哪个）正在这个对象上等待的线程把它唤醒，其它线程依然在等待被唤醒
 * notifyAll: 唤醒所有线程，让它们去竞争，不过也只有一个能抢到锁
 * sleep: 不是Object中的方法，而是Thread类的静态方法，让当前线程持有锁阻塞指定时间
 *
 */
public class ThreadAllMethods {



}
