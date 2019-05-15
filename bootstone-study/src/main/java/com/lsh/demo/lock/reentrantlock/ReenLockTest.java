package com.lsh.demo.lock.reentrantlock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lsh on 2018/11/7 18:50.
 *
 * @author lsh
 * @date 2018/11/07
 */
public class ReenLockTest {

    /**
     * reentrantlock basic usage
     * */
    @Test
    public void test(){
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
    }

}
