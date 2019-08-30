package basic.eighteen;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lsh on 2019-08-29.
 *
 * 多线程
 *
 * 模拟12306卖票系统，多个线程代表多个卖票窗口，但是票总数是固定的
 *
 * @see
 */
public class Tickets implements Runnable {

    //java 原子计数器 看到不认识也没关系 --
    private AtomicInteger ticketsCont = new AtomicInteger(5); // 一共有五张火车票

    @Override
    public void run() {

        while (ticketsCont.get()>0){
            ticketsCont.decrementAndGet();//如果有票，就减去1，也就是卖掉一张
            System.out.println(Thread.currentThread().getName() + " 卖掉一张票 ，还有 " + ticketsCont);
        }
    }

    @Test
    public  void test() {
        //new 一个对象，也就是 一个公用的卖票系统，大家的票源
        Tickets tickets = new Tickets();

        //三个卖票窗口
        Thread t1 = new Thread(tickets);
        Thread t2 = new Thread(tickets);
        Thread t3 = new Thread(tickets);

        //卖票窗口一起卖票
        t1.start();
        t2.start();
        t3.start();
    }
}
