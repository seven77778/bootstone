package com.lsh.demo.bootstone.dubbo;

/**
 * Created by lsh on 2018/12/8.
 */
public interface RegisterService {

    /**
     * 注册
     * @param name
     * @param age
     */
    String register(String name, Integer age);

}
