package com.lsh.demo.bootstone.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * @author lsh
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan(basePackages = {"com.lsh.demo","xml","com.bootstone.spring.ioc.iocdemo1",
"sentinel","spring20200603","biz.limit","work.utils","baisc.redission","com.bootstone.spring","arthas"})
@MapperScan("com.lsh.demo.bootstone.dao")
@EnableCaching
@ImportResource(value = {"classpath:dubbo-provider.xml","classpath:dubbo-consumer.xml","classpath:bean.xml"})
public class BootStoneWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStoneWebApplication.class, args);
    }

    /**
     * @see SpringApplication#run(String...) 
     * 1.StopWatch 计时器，记录启动时间，线程不安全
     * 
     * 1·
     */
}
