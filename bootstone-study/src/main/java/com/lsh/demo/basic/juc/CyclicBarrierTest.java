package com.lsh.demo.basic.juc;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by lsh on 2019/2/25.
 *
 * 一种同步辅助工具，允许一组线程相互等待*对方到达共同的屏障点。
 * 在包含固定大小的线程组的程序中(这些线程组偶尔必须相互等待)，cyclicBarrier 是非常有用的。
 * 这个屏障被称为* <em> cycle </em>，因为它可以在等待的线程*释放后被重用。
 */
public class CyclicBarrierTest {

    @Test
    public void test () throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(8 );
            cyclicBarrier.await();
    }


}
