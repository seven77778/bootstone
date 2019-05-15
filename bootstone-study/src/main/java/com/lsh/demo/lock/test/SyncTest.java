package com.lsh.demo.lock.test;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/6 13:48.
 *
 * @author lsh
 * @date 2018/11/06
 */
public class SyncTest {

    public synchronized static void add(){
        while (true){
            System.out.println("add");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void delete(){
        while (true){
            System.out.println("delete");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void printHello(){
        System.out.println("hello,i am not synchronized");
    }

    @Test
    public void test() throws Exception {
        SyncTest syncTest = new SyncTest();
        new Thread(()->syncTest.add()).start();
        new Thread(()->syncTest.delete()).start();
        new Thread(()->syncTest.printHello()).start();

        while (Thread.activeCount()>2){
            Thread.yield();
        }
    }

    public static void main(String[] args) {

        new Thread(()->new SyncTest().add()).start();
        new Thread(()->new SyncTest().delete()).start();
        new Thread(()->new SyncTest().printHello()).start();

        while (Thread.activeCount()>2){
            Thread.yield();
        }
    }

}
