package com.lsh.demo.basic.thread.threadlocal;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lsh on 2019-05-13.
 */
public class MyThreadLocalDemo1<T> {

    private ConcurrentHashMap<Long,T> hashMap = new ConcurrentHashMap<>();

    void set(T value) {
        hashMap.put(Thread.currentThread().getId(),value);
    }

    T get() {
        return hashMap.get(Thread.currentThread().getId());
    }

}
