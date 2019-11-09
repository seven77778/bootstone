package com.lsh.demo.basic.timing;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by lsh on 2019-08-29.
 *
 * java定时任务
 *
 */
@Component
@EnableScheduling   // 2.开启定时任务
public class TimeTask {
    private static final Logger log = BootStoneLog.bootStone;


    /**
     * 利用 Thread.sleep 来实现 每隔 2 秒执行一次，也算定时任务
     * 缺点 是 不能指定时间执行
     * @throws Exception
     */
    @Test
    public void test()throws Exception{
        while (true){
            Thread.sleep(2000);
            System.out.println("处理你要做的任务");
            System.out.println(LocalDateTime.now());
        }
    }

    //3.添加定时任务
    @Scheduled(cron = "0 5 * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    public void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }

    /**
     * @Scheduled（cron="定点"）
     * 秒 分 时 日 月 周几
     * http://cron.qqe2.com/
     *
     * second(秒)， minute(分)， hour(时),day of month(日),month(月),day of week(周几)
     * 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
     * 0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
     * 0 0 12 ? * WED 表示每个星期三中午12点
     * "0 0 12 * * ?" 每天中午12点触发
     * "0 15 10 ? * *" 每天上午10:15触发
     * "0 15 10 * * ?" 每天上午10:15触发
     * "0 15 10 * * ? *" 每天上午10:15触发
     * "0 15 10 * * ? 2005" 2005年的每天上午10:15触发
     * "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
     * "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
     * "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
     * "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
     * "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
     * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
     * "0 15 10 15 * ?" 每月15日上午10:15触发
     * "0 15 10 L * ?" 每月最后一日的上午10:15触发
     * "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
     * "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
     * "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
     */
    //3.添加定时任务
    @Scheduled(cron = "0 0/1 * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    public void configureTasks2() {
        System.err.println("执行静态定时任务时间22: " + LocalDateTime.now());
    }


    @Test
    public void  test2(){
        TimeTask timeTask = new TimeTask();
        timeTask.configureTasks();
    }

}
