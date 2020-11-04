package com.lsh.demo.lock.atomic;

import com.lsh.demo.basic.thread.threadpool.MyBasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by lsh on 2018/11/7 12:43.
 *
 * @author lsh
 * @date 2018/11/07
 */
public class AtomicTest {
    int num = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    private void add() {
        num++;
        atomicInteger.incrementAndGet();
        atomicInteger.getAndAdd(1);
    }

    /**
     * 测试incrementAndGet()
     *
     * res = 9989
     * atomic = 10000
     */
    @Test
    public void test() throws Exception {
        ExecutorService service = MyBasicThreadFactory.getExecutorService();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            service.submit(() -> {
                add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        System.out.println("res = " + num);
        System.out.println("atomic = " + atomicInteger.get());
    }

    /**
     * 测试compareAndSet(int expect, int update) -- 如果传入的expect等于atomicInteger现有的值
     * 就将atomicInteger改为update
     */
    @Test
    public void test2() {
        boolean b = atomicInteger.compareAndSet(0, 1);
        System.out.println(b);
        System.out.println(atomicInteger.get());

        boolean b1 = atomicInteger.compareAndSet(1, 998);
        System.out.println(b1);
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement();
    }

    /**
     * AtomicStampedReference 解决ABA问题
     * 1.初始值设为new String("abc"),后面compareAndSet用"abc"，失败，可见比较的是地址 ==
     *
     */
    @Test
    public void test3() {
        String str = "abc";
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(str,1);
        Object res = atomicStampedReference.get(new int[] {1,2});
        System.out.println(res);

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
        boolean res2 = atomicStampedReference.compareAndSet(str, "abcd", 1, 2);
        System.out.println("res2 "+res2);
        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }


    /**
     * 测试++i 和 i++ return的结果
     * */
    @Test
    public void testadd(){
        int i=0;
        int i2=0;
        int b = i++;
        int a = ++i2;
        System.out.println(b);//0
        System.out.println(a);//0
        int x =0;
        x++;
        System.out.println(x);
    }

    /**
     * test getAndUpdate 自旋锁？
     * */
    @Test
    public void test4(){
        AtomicInteger atomicInteger = new AtomicInteger();
    }

    /**
     * test
     * do {
     var5 = this.getIntVolatile(var1, var2);
     } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
     *每次都从内存中取出最新的值，然后加1，不行就循环
     * */
    @Test
    public void test5(){
        int num =1;
        do {
            num++;
            System.out.println("dodod");
        } while(num <5);
    }


}
