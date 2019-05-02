package com.lsh.demo.bootstone.study.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lsh on 2019-04-25.
 *
 * Condition的作用是对锁进行更精确的控制。
 *
 * Condition中的await()方法相当于Object的wait()方法，
 * Condition中的signal()方法相当于Object的notify()方法，
 * Condition中的signalAll()相当于Object的notifyAll()方法。
 * 不同的是，Object中的wait(),notify(),notifyAll()方法是和"同步锁"(synchronized关键字)捆绑使用的；
 * 而Condition是需要与"互斥锁"/"共享锁"捆绑使用的。
 */
public class ConditionTest {

    public static void main(String[] args)throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        //同一把锁，可以创建多个 condition
        Condition condition2 = reentrantLock.newCondition();
        condition.await();
        //唤醒一个等待的线程
        condition.signal();
    }

}
