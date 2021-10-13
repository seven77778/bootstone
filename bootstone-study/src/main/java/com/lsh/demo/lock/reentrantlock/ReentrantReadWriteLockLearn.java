package com.lsh.demo.lock.reentrantlock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockLearn {

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Test
    public void test(){
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();
        readLock.tryLock();
        writeLock.tryLock();
        writeLock.lock();
    }
}
