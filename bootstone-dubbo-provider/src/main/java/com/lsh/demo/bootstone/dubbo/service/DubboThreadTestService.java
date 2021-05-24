package com.lsh.demo.bootstone.dubbo.service;

/**
 * 测试dubbo线程池
 */
public interface DubboThreadTestService {

    String simpleGet();

    String longTimeGet();

    String get();
}
