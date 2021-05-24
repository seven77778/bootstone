package com.lsh.demo.annolearn.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 不加@lazy，启动即会加载，只有这个类加 lazy也没用
 * 引用的地方还有加 @lazy才会生效
 *
 * -- 出处和引用处都要加，目前测是这样
 */
@Component
@Lazy
public class MyLazyUtil {

    public MyLazyUtil() {
        System.out.println("*************MyLazyUtil被加载");
    }

    public String getStr(){
        return "333";
    }
}
