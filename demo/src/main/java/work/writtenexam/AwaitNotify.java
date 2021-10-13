package work.writtenexam;

import lombok.Data;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

/**
 * 20210924 面试题
 */
public class AwaitNotify {

    @Test
    public void waitAndNofity() throws InterruptedException {
        /**
         * wait notify 必须在synchronize中使用，主要用于线程之间的通知
         */
        LongAdder longAdder = new LongAdder();
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (this) {
                    longAdder.increment();
                    System.out.println("wait--" + longAdder.intValue());
                    if (longAdder.intValue() > 10) {
                        try {
                            this.wait();
                            System.out.println("~~~~~~~~~");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (this) {
                    if (longAdder.intValue() == 11) {
                        this.notify();
                        System.out.println("notify");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }

    /**
     * 循环打印12A34B，数字1-52，字母A-z
     */

    Thread t1, t2;

    @Test
    public void testA12B34() throws InterruptedException {

        t1 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                System.out.print(2*i -1);
                System.out.print(2*i);
                LockSupport.unpark(t2);
                LockSupport.park(t1);
            }
        });

        t2 = new Thread(() -> {
            char c;
            for (c = 'A'; c <= 'Z'; ++c) {
                LockSupport.park(t2);
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
//        t2.join(); // 不要join也是可以的
        t2.start();
        Thread.sleep(1000);
    }
    /**
     * 三个线程，顺序执行
     */
    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(()->{
            Thread.yield();
            System.out.println("t1");
        });

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });

        Thread t3 = new Thread(()->{
            System.out.println("t3");
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        Thread.sleep(100);
    }

    /**
     * 实现一个类似微博热搜前100名的，使用java数据结构
     * 最大堆
     */

    @Test
    public void testResou100() {
        PriorityQueue<Resou> pq = new PriorityQueue<Resou>(new Comparator<Resou>() {
            @Override
            public int compare(Resou o1, Resou o2) {
                return o2.getCount()-o1.getCount();
            }
        }
        );
        pq.offer(new Resou(111,"101"));
        pq.offer(new Resou(12,"102"));
        pq.offer(new Resou(666,"103"));
        pq.offer(new Resou(7896,"104"));
        System.out
                .println(pq);
    }

    @Data
    class Resou{
        private int count;
        private String id;
        private String text;

        public Resou(int count, String id) {
            this.count = count;
            this.id = id;
        }
    }

    /**
     * 快速排序几种方法，写出时间复杂度
     */


    /**
     * 设计并实现一个简易的缓存框架，要求支持并发的读写和LRU缓存淘汰算法，并考虑性能
     */


    /**
     * 编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，3个线程交替打印1-100的整数，要求输出结果有序,
     *   样例Sample:
     * Thread1: 1
     * Thread2: 2
     * Thread3: 3
     * Thread1: 4
     * Thread2: 5
     * Thread3: 6
     * ....
     * Thread3: 99
     * Thread1: 100
     */


    /**
     * 遍历一个二叉树，打印出该路径中每个节点数字的和与给定目标值一致的有效路径。
     * 有效路径：从根节点到叶节点的路径。
     * 给定一个二叉树 :
     *      1
     *     / \
     *    2   4
     *   / \
     *  2   3
     * 目标值= 5时返回结果：
     * 1 2 2
     * 1 4
     */

    /**
     * 使用两个栈来实现队列的一些操作。
     * 队列应支持push(element)，pop() 和 top()，其中pop是弹出队列中的第一个(最前面的)元素。
     * pop和top方法都应该返回第一个元素的值。
     * 样例：比如push(1), pop(), push(2), push(3), top(), pop()，你应该返回1，2和2
     * 要求：仅使用两个栈来实现它，不使用任何其他数据结构，push，pop 和 top的复杂度都应该是均摊O(1)的
     *
     */


    /**
     * 比较一个源字符串和一个目标字符串，
     * 如果在源字符串中包含目标字符串全部字符，输出所包含的第一个最小子串；
     * 如果不存在，输出空。
     * ：样例
     * source = "BPDAUNZHGAHSIWBADNC"，target = "BDN" 满足要求的解  "BADN"
     * 要求：时间复杂度为O(n^2)
     */
}
