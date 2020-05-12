package com.lsh.demo.bootstone.web.point;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lsh on 2019-11-08.
 * <p>
 * 1. around 需要有  point.proceed() + 返回值
 * 2. @Pointcut 可以有多个
 *
 * @After
 * 该注解标注的方法在所有的Advice执行完成后执行，无论业务模块是否抛出异常，类似于finally的作用；
 * @Before
 * 是业务逻辑执行前执行，与其对应的是@AfterReturning，而不是@After，@After是所有的切面逻辑执行完之后才会执行，无论是否抛出异常
 */
@Aspect
@Component
public class PointcutTest {

    @Pointcut("execution(* com.lsh.demo.bootstone.web.controller.HelloController.test2(..))")
    public void point() {

    }

    //    切点表达式将会匹配使用public修饰，返回值为任意类型,方法可以有多个参数，但是第一个参数必须是java.lang.String类型的方法
    @Pointcut("execution(public * com.lsh.demo.bootstone.web.controller.HelloController.test2(java.lang.String,..))")
    public void point2() {

    }

    @Pointcut("execution(* com.lsh.demo.bootstone.web.controller.Hello*.t*(..))")
    public void point3() {

    }

    // HelloController 类下所有方法
    @Pointcut("within(com.lsh.demo.bootstone.web.controller.HelloController)")
    public void point4() {

    }

    //未生效
    @Pointcut("within(com.lsh.demo.bootstone.web.controller.HelloController)&& @annotation(MyWithinPoint)")
    public void point5() {

    }

    //OK
    @Pointcut("@annotation(MyWithinPoint)")
    public void point6() {

    }



    /*1、在类上加上该注解，生效，方法上 未生效
        @within 对象级别
        @annotation 方法级别
     */
    @Pointcut("@within(com.lsh.demo.bootstone.web.point.MyWithinPoint)")
    public void point7() {

    }


    //ok
    @Pointcut("target(com.lsh.demo.bootstone.web.controller.HelloController)")
    public void point8(){

    }

    /*
     ************************************************************************************
     */

    @AfterReturning(pointcut = "point2()", returning = "baseResponse")
    public void after(JoinPoint joinPoint, String baseResponse) {

        System.out.println("after" + baseResponse);
    }

    @Before("point8()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("before " + args);
    }

    @After("point2()")
    public void afterMethod(JoinPoint point) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("After：" + methodName + ",参数为：" + args);
    }

    @Around("point7()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("around：" + methodName + ",参数为：" + args);
        return point.proceed();
    }

}
