package com.lsh.demo.lock.longadder;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by lsh on 2018/11/24 13:59
 *
 * LongAdder 比 AtomicInteger 性能更好
 */
public class LongAdderTest {


    @Test
    public void test(){
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.intValue());

        class B{

        }
    }

    protected class A{

    }
}
