package com.lsh.demo.bootstone.web.aop;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * 存在map中的，具体执行的方法
 */
@Data
public class RouteMethodInvoker {

    private String methodName;
    private Object bean;
    private Method method;
    private String version;

}
