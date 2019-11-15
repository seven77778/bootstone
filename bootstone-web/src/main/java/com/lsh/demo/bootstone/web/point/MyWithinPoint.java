package com.lsh.demo.bootstone.web.point;

import java.lang.annotation.*;

/**
 * ElementType.TYPE  类上使用必须有
 */

@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyWithinPoint {
}
