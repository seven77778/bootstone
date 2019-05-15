package com.lsh.demo.basic.thread.createthreads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by lsh on 2019-05-14.
 *
 * 创建线程的2种方式，一种是直接继承Thread，另外一种就是实现Runnable接口。
 * 这2种方式都有一个缺陷就是：在执行完任务之后无法获取执行结果
 *
 * Callable用于产生结果，Future用于获取结果
 *
 * call 有返回值，可以抛出异常
 *
 *
 */
public class CallableTest implements Callable<String> { //泛型是call方法return的

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " is run");
        return "run over";
    }

    public static void main(String[] args) {
        CallableTest callableTest = new CallableTest();
        FutureTask<String> task = new FutureTask<String>(callableTest);
        task.run();

        try {
            System.out.println( "result " + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
