package com.lsh.demo.bootstone.web.common.request;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpUafImplConfig {

    @Bean
    public HttpUafImpl getBean(){
        return new HttpUafImpl();
    }
}
