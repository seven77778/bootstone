package work.writtenexam;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

/**
 * 请实现两个线程, 使之交替打印 +1+-+100, 如, 两个线程分别为: Printer1+和Printer2, 最后输出结果为
 * +++Printer1+-+1
 * +++Printer2+-+2
 * +++Printer1+-+3
 * +++Printer2+-+4
 */
public class Print20211027 {
    Thread t1,t2;

    @Test
    public void test(){
        LongAdder longAdder = new LongAdder();
        CyclicBarrier cyc = new CyclicBarrier(100);
        t1 = new Thread(()->{
            for(int i =0;i<50;i++){
                longAdder.increment();
                System.out.println(("+++" +Thread.currentThread().getName()+"-+" + longAdder.intValue()));
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"Printer1+");

        t2 = new Thread(()->{
            for(int i =0;i<50;i++){
                LockSupport.park();
                longAdder.increment();
                System.out.println("+++" +Thread.currentThread().getName()+"+-+" + longAdder.intValue());
                LockSupport.unpark(t1);
            }
        },"Printer2");
        t1.start();
        t2.start();

        try{
            cyc.await(1, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}