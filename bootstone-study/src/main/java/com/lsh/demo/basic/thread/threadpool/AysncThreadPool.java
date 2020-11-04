package com.lsh.demo.basic.thread.threadpool;


import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AysncThreadPool {


    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    /**
     * 基本证实ExecutorService + runbale 是异步的
     * test() 中没有sleep的话，test是先执行完的
     */
    @Test
    public void test() {
        long time = System.currentTimeMillis();
        ExecutorService service = MyBasicThreadFactory.getExecutorService();
        service.execute(() -> {
            for (int i = 0; i <= 2; i++) {
                doSomeThing1();
            }
        });
        System.out.println("cost-" + (System.currentTimeMillis() - time));
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }


    /**
     * ExecutorService + callAble + Future 要获取结果，其实就是同步了
     * 只要不调用get(),就不会阻塞
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception{
        long time = System.currentTimeMillis();
        ExecutorService service = MyBasicThreadFactory.getExecutorService();
        //很重要，一定要一起读
        List<Future<String>> futures = Lists.newArrayList();
        for (int i = 0; i <= 2; i++) {
            Future<String> res = service.submit(this::getStr);
            futures.add(res);
        }

        futures.forEach(x->{
            try {
                System.out.println(x.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("cost-" + (System.currentTimeMillis()-time));
    }


    public void doSomeThing1() {
        try {
            Thread.sleep(new Double(Math.random() * 1000).longValue());
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("执行" + Thread.currentThread().getName());
    }

    public String getStr() {
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return Math.random() + "";
    }

}
