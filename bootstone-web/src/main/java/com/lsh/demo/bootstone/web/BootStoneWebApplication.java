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
@ComponentScan(basePackages = {"com.lsh.demo.basic","com.lsh.demo.bootstone","com.lsh.demo.spring","xml",
        "com.bootstone.spring.ioc.iocdemo1","com.lsh.demo.bootstone.service","com.lsh.demo.bootstone",
"com.lsh.demo.bootstone.dao.mysql","sentinel","spring20200603","biz.limit",
        "com.lsh.demo.annolearn.lazy","baisc.redission","com.bootstone.spring","com.lsh.demo.bootstone.dubbo.filter"})
@MapperScan("com.lsh.demo.bootstone.dao")
@EnableCaching
@ImportResource(value = {"classpath:dubbo-provider.xml","classpath:dubbo-consumer.xml","classpath:bean.xml"})
public class BootStoneWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStoneWebApplication.class, args);
    }
}
