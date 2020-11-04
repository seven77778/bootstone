package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.basic.thread.threadpool.MyBasicThreadFactory;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/pool")
public class ThreadPoolController {

    /**
     * 测试线程池
     */


    /**
     * 奇怪，CORE_POOL_SIZE 是10，但是只有一个线程在进行
     * <p>
     * hehe.. for循环的位置错了。。。
     *
     * 代码正常以后，ExecutorService+runnable都是异步
     *
     */
    @RequestMapping("1")
    public String test() {
        long time = System.currentTimeMillis();
        ExecutorService service = MyBasicThreadFactory.getExecutorService();
        for (int i = 0; i < 5; i++) {
            service.submit(this::printLog);
        }
        BootStoneLog.bootStone.info("all cost - " + (System.currentTimeMillis() - time));
        return "ok";
    }

    @RequestMapping("2")
    public String test2() {
        long time = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 5; i++) {
            service.execute(this::printLog);
        }
        BootStoneLog.bootStone.info("all cost - " + (System.currentTimeMillis() - time));
        return "ok";
    }


    public void printLog() {
        try {
            long num = new Double(Math.random() * 10000).longValue();
            Thread.sleep(num);
            BootStoneLog.bootStone.info("done-" + Thread.currentThread().getName() + "-" + num);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
