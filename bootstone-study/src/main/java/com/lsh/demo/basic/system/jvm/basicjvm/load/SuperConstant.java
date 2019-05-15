package com.lsh.demo.basic.system.jvm.basicjvm.load;

/**
 * Created by lsh on 2018/10/29.
 *
 * @author lsh
 * @date 2018/10/29
 */
public class SuperConstant {
    static {
        System.out.println("SpuerContant init");
    }

    // TODO: 2018/10/29 mark 
    /**
     * 不加public修饰，superConstant还是要初始化？
     * */
    public static final String value = "SpuerContant";
}
