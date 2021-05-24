package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.dubbo.study.DubboThreadPool;
import com.lsh.scm.dubbo.MyDubboService;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see  DubboThreadPool
 */
@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Resource
//    @Lazy
    private MyDubboService myDubboService;

    private AtomicInteger count=new AtomicInteger(0);

    /**
     * dubbo本地直连，报 No provider available from registry 127.0.0.1:2181 for service com.lsh.scm.dubbo.MyDubboService
     * 在zk中 ls /dubbo看，服务是已经注册的 fixme
     *
     * 1.给scm 加注解@EnableDubbo
     * @ImportResource(value = {"classpath:dubbo-provider.xml"})
     * 引入 dubbo-dependencies-zookeeper pom，修改dubbo端口号为20886，启动成功
     *  -- fixed OK
     */

    /**
     * 100 成功，没报错
     */

    @PostMapping("dubbo1")
    public String getDubbo1() throws Exception {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {

                System.out.println("第" + count.getAndIncrement() + "次 的结果：" + myDubboService.getName());

            }).start();
        }
        return "ok";
    }

    /**
     * 20次longtime成功，也没报错 -- mmp,for循环里面调用，都是顺序调用，肯定不报错。。
     * for循环中new Thread啊，验证成功，
     * scm中配置为 dubbo.protocol.threads = 10
     * dubbo.protocol.threadpool=fixed
     * dubbo.protocol.dispatcher=all
     * <p>
     * 20次请求，10次直接报错，10次成功
     */
    @PostMapping("long")
    public String getDubboLong() throws Exception {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println("第" + count.getAndIncrement() + "次 的结果：" + myDubboService.longTimeget());
            }).start();
        }
        return "ok";
    }

    /**
     * 此项测试，修改scm配置为 dubbo.protocol.dispatcher=direct
     *
     */
    @PostMapping("direct")
    public String testDirect() throws Exception {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println("第" + count.getAndIncrement() + "次 的结果：" + myDubboService.getName());
            }).start();
        }
        return "ok";
    }

    /**
     * 1.这次测试有点意思，19次成功的， 但是挨个顺序执行的，进的是IO线程池，一个个的执行
     * 而且最后一个还超时了，provider 超时时间设置的3秒，consumer超时时间设置的10秒
     * -- 感觉时间对不上
     *
     * 修改配置在来测试，provider设置的consumer时间不生效，consumer项目中设置的生效
     * 关于配置 ： dubbo中配置优先级规律：方法级配置优先级高于接口级，consumer的优先级高于provider。
     *
     * 2.dubbo.protocol.iothreads=10
     * 给IO设置10个线程， 依然是顺序执行，why fixme
     */
    @PostMapping("directLong")
    public String testDirectLongTime() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println("第" + count.getAndIncrement() + "次 的结果：" + myDubboService.longTimeget());
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        stopWatch.stop();
        System.out.println("all coss is " + stopWatch.getTotalTimeMillis());
        return "ok";
    }
}
