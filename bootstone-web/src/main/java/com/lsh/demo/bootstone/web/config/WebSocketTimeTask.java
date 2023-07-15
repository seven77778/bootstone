package com.lsh.demo.bootstone.web.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author lsh
 * @date 2023/7/15 14:48
 */
@Component
@EnableScheduling
public class WebSocketTimeTask {

  @Resource
  private WebSocketSever webSocketSever;

  //3.添加定时任务
  @Scheduled(cron = "*/5 * * * * ?")
  //或直接指定时间间隔，例如：5秒
  //@Scheduled(fixedRate=5000)
  public void configureTasks() {
    System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    webSocketSever.sendMessageByUser(11,"hello");
  }

}
