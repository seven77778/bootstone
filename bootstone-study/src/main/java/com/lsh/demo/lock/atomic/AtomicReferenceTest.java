package com.lsh.demo.lock.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lsh
 * @date 2018/11/08
 */
public class AtomicReferenceTest {

    private AtomicReference<Integer> reference = new AtomicReference<>();

    /**
     * test AtomicReference
     * */
    @Test
    public void test(){
        reference.compareAndSet(1,2);
        System.out.println(reference.compareAndSet(null,1));
        System.out.println(reference.compareAndSet(1,2));
        System.out.println(reference.get());
    }

    public void lock(){
        while (!reference.compareAndSet(null,1)){
            //donothing
            System.out.println("waitting");
        }
    }
    /**
     * test lock()
     */
    @Test
    public void test2(){
        reference.compareAndSet(null,1);
        lock();
        System.out.println("locked");
    }

}
