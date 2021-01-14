package com.lsh.demo.lock.test;

/**
 * Created by lsh on 2018/11/6 16:18.
 *
 * @author lsh
 * @date 2018/11/06
 *
 * 一道面试题
 *
 * synchronized其实是在1.6之后做了很多优化的，其中就有一个自旋锁，就能保证不需要让出CPU，
 * 有可能刚好这部分时间和主线程输出重合
 * 并且在他之前就有可能发生，b先等于1000，这个时候主线程输出其实就会有两种情况。2000 或者 1000
 *
 * 什么是线程安全：
 * 当多个线程访问一个对象时，如果不用考虑这些线程在运行时环境下的调度和交替执行，也不需要进行额外的同步
 * 或者在调用方进行任何其它的协调操作，调用这个对象的行为都可以获得正确的结果，那这个对象就是线程安全的
 */
public class SyncTest2 implements Runnable {

    int num =100;

    synchronized void add(){
        System.out.println("add");
        num = 1000;
        sleep(500);
        System.out.println("num : "+num);
    }

    synchronized void delete(){
        sleep(250);
        System.out.println("delete");
        num = 2000;
    }

    /**
     * result 可能是0，可能是100
     * 1.delete
     add
     result 1000
     num : 1000

     2.delete
     add
     result 2000
     num : 1000

     3.delete
     add
     num : 1000
     result 2000

     num一定等于1000，result可能1000，可能2000，当result等于1000，num一定先执行
     * */
    public static void main(String[] args) {
        SyncTest2 syncTest2 = new SyncTest2();
        Thread thread = new Thread(syncTest2);
        thread.start();
        syncTest2.delete();
        System.out.println("result " + syncTest2.num);
    }


    @Override
    public void run() {
        add();
    }

    /**
     * confuse
     * */
    public void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
