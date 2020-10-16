package biz.limit.anno;


import java.lang.annotation.*;

/**
 * 自定义 的 一个简易的限流工具
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitAnno {

    int num() default 0;

    //redis限流还是单机限流
    String type();

    String name();

    //是否等待 0否 1是
    int waitFor() default 0;
}
