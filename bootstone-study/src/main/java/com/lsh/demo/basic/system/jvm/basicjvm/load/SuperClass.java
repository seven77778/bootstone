package com.lsh.demo.basic.system.jvm.basicjvm.load;

/**
 * Created by lsh on 2018/10/29.
 *
 * @author lsh
 * @date 2018/10/29
 */
public class SuperClass {
    static {
        System.out.println("super init");
    }

    static String value = "spuer";
}
