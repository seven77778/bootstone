package com.lsh.demo.lock.synchronizetest;

import org.junit.Test;

/**
 * Created by LSH on 2018/11/18.
 *
 * @author LSH
 * @date 2018/11/18
 *
 * 测试synchronized 的两种用途
 * 1.对象锁 2.类锁
 *
 * 1.在静态方法中使用synchronize，传入的是类
 *
 */
public class TwoSyncUseges {


    /**
     * why static中不能使用
     * todo 以下两种创建线程的方式-原理
     */
    public static void main(String[] args) {
        //传入的是类
        new Thread(()->{
            while(true){
                synchronized (TwoSyncUseges.class){

                }
            }
        }).start();

        // 锁对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this){

                }
            }
        }).start();
    }


    @Test
    public void  test(){
        new Thread(()->{
            while(true){
                //基本用法，this即可
                synchronized (this){
                }
            }
        }).start();
    }
}
