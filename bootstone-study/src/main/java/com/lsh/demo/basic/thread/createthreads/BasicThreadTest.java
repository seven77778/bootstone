package com.lsh.demo.basic.thread.createthreads;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/6 14:11.
 *
 * @author lsh
 * @date 2018/11/06
 *
 * 创建线程的3种方法
 * 1.继续thread类，重写run方法
 * 2.实现runnable接口
 * 3.callable
 *
 */
public class BasicThreadTest extends Thread {
    @Override
    public void run() {
        System.out.println("线程名称 ： "+getName());
    }


    /**
     * IllegalThreadStateException - 如果线程已经启动
     * */
    @Test
    public void test(){
        BasicThreadTest thread = new BasicThreadTest();
        thread.start();
        new BasicThreadTest().start();
    }

    @Test
    public void test2(){
        for(int i=0;i<5;i++){
            new Thread(new RunTest()).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }

    }
        class RunTest implements Runnable {

            @Override
            public void run() {
                System.out.println("线程名称 ：" + Thread.currentThread().getName());
            }
        }

    /**
     * 线程优先级
     * 优先级10 也不一定 比优先级1 先执行
     */
    @Test
    public void test3() throws Exception {
        Thread thread1 = new Thread(()-> System.out.println("thread1"));
        Thread thread2 = new Thread(()-> System.out.println("thread2"));
        Thread thread3 = new Thread(()-> System.out.println("thread3"));
        thread3.setDaemon(true);
        thread3.setPriority(8);
        thread1.setPriority(1);
        thread2.setPriority(10);


        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(500);

    }


}
