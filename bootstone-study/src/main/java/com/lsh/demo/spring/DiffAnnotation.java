package com.lsh.demo.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lsh on 2019/2/12.
 * 注解
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
public class DiffAnnotation {

   @Value("${value1}")
    private  String test1;

   @Test
    public void test(){
        System.out.println(test1);
    }



}
