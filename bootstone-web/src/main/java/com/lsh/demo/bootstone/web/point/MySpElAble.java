package com.lsh.demo.bootstone.web.point;

import java.lang.annotation.*;

/**
 * @author lsh
 * @date 2022/5/15 9:12
 *
 * 测试动态传参
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MySpElAble {

    String name();

    String age();

    int num() default 0;

}
