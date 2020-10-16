package com.lsh.demo.annolearn.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 不加@lazy，启动即会加载
 */
@Component
@Lazy
public class MyLazyUtil {

    public static String ss="MyLazyUtil";

    public MyLazyUtil() {
        System.out.println("*************MyLazyUtil被加载");
    }

    public String getStr(){
        return "333";
    }
}
