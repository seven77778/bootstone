package com.lsh.demo.basic.juc.longadder;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by lsh on 2018/11/24 13:59
 *
 * LongAdder 比 AtomicInteger 性能更好
 *
 * AtomicInteger的基本实现机制应该比较了解,它们是在一个死循环内,不断尝试修改目标值,直到修改成功,
 * 如果竞争不激烈,那么修改成功的概率就很高,
 * 否则,修改失败的概率就很高,在大量修改失败时,这些原子操作就会进行多次循环尝试,因此性能就会受到影响
 *
 * 总结：
 * LongAdder 类与AtomicLong类的区别在于
 * 高并发时  LongAdder 将对单一变量的CAS操作分散为对数组cells中多个元素的CAS操作，取值时进行求和；
 * 而在并发较低时仅对base变量进行CAS操作，与AtomicLong类原理相同。不得不说这种分布式的设计还是很巧妙的
 *
 * fixme 1.longAdder怎么区分是不是高并发的
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
