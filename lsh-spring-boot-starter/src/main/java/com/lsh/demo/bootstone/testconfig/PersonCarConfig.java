package com.lsh.demo.bootstone.testconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PersonCarConfig {
    @Bean
    public Person person(){
        return new Person(car());
    }

    @Bean
    public Car car(){
        return new Car();
    }
}
