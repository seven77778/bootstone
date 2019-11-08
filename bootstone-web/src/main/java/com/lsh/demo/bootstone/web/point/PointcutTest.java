package com.lsh.demo.bootstone.web.point;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lsh on 2019-11-08.
 */
@Aspect
@Component
public class PointcutTest {

    @Pointcut("execution(* com.lsh.demo.bootstone.web.controller.HelloController.test2(..))")
    public void pointCut() {
        System.out.println("aaaa");
    }


    @AfterReturning(pointcut = "pointCut()", returning = "baseResponse")
    public void after(JoinPoint joinPoint, String baseResponse) {

        System.out.println("after" + baseResponse);
    }

    @Before( "com.lsh.demo.bootstone.web.controller.HelloController.test2()")
    public  void before(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("before");
    }

    @After(("execution(* com.lsh.demo.bootstone.web.controller.HelloController.test2(..))"))
    public void afterMethod(JoinPoint point){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用后连接点方法为：" + methodName + ",参数为：" + args);
    }

}
