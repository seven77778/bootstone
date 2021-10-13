package com.lsh.demo.lock.reentrantlock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lsh on 2018/11/7 18:50.
 *
 * @author lsh
 * @date 2018/11/07
 */
public class ReenLockTest {

    /**
     * reentrantlock basic usage
     */
    ReentrantLock lock = new ReentrantLock(false);

    @Test
    public void testReadWrite(){
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    }

    @Test
    public void test() {

        new Thread(() -> {
                lock.lock();
            System.out.println(lock);

        }).start();

        new Thread(()->{
            lock.lock();
            System.out.println(lock);
        }).start();


        System.out.println(1111);
    }

    //lock的state是什么？
    @Test
    public void test2() {
        System.out.println(new Random(16));

        new Thread(()->{
            lock.lock();
            System.out.println(11);
        }).start();

    }



    /**
     * 问题如下：
     * <p>
     * A: lock 是非公平锁 -- 是非公平锁，性能开销较小
     * B: finally 代码块不会抛出异常 --
     * C: tryLock 获取锁失败则直接往下执行 --
     */
//    private final static Lock lock1 = new ReentrantLock();
//
//    //输出结果 yichang -- finally
//    public static void main(String[] args) {
//        try {
//            lock1.tryLock();
//            throw new Exception("");//add -> 进入异常，并且进入finally
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("yichang");
//        } finally {
//            lock1.unlock();
//            System.out.println("finally");
//        }
//    }

}
