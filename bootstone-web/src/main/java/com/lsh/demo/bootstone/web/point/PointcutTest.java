package com.lsh.demo.bootstone.web.point;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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


//    @AfterReturning(value = "execution(* com.lsh.demo.bootstone.web.controller.HelloController.test2(..))", returning = "baseResponse")
//    public void after(JoinPoint joinPoint, String baseResponse) {
//
//        System.out.println("after" + baseResponse);
//    }

    @Before("execution(* com.lsh.demo.bootstone.web.controller.HelloController.test2(..))")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("before");
    }

    @After(("execution(* com.lsh.demo.bootstone.web.controller.HelloController.test2(..))"))
    public void afterMethod(JoinPoint point) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("After：" + methodName + ",参数为：" + args);
    }

    @Around(("execution(* com.lsh.demo.bootstone.web.controller.HelloController.test2(..))"))
    public void around(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        point.proceed();

        System.out.println("around：" + methodName + ",参数为：" + args);
    }

}
