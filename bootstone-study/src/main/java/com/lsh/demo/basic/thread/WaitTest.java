package com.lsh.demo.basic.thread;

/**
 * Created by LSH on 2019/4/21 - 11:23.
 * <p>
 * declaration : wait 方法必须包含在对应的synchronized语句中？
 *
 * 不加同步关键字 直接抛出 IllegalMonitorStateException
 */
public class WaitTest implements Runnable{


    private static Object object  = new Object();



    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {
            synchronized (object){
                object.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Wait end");
    }


    public static class NotifyTest implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            synchronized (object){

                object.notify();
                System.out.println("notify end");
            }

        }
    }

    public static void main(String[] args) {
        new Thread(new WaitTest()).start();
        new Thread(new NotifyTest()).start();
    }
}
