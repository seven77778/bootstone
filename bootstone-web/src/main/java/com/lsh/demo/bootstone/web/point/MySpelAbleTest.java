package com.lsh.demo.bootstone.web.point;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author lsh
 * @date 2022/5/15 9:13
 */
@Aspect
@Component
public class MySpelAbleTest {



    private DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

    private ExpressionParser parser = new SpelExpressionParser();

    @Pointcut("@annotation(MySpElAble)")
    public void point6() {

    }

    @Around(value = "point6() && @annotation(aaa)")
    public Object around6(ProceedingJoinPoint joinPoint, MySpElAble aaa) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("around：" + methodName + ",参数为：" + args);
        System.out.println(aaa.age());

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取方法的形参名称
        String[] params = discoverer.getParameterNames(method);
        //获取方法的实际参数值
        Object[] arguments = joinPoint.getArgs();

        //设置解析spel所需的数据上下文
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], arguments[len]);
        }
        //解析表达式并获取spel的值
        Expression expression = parser.parseExpression(aaa.name());
        Expression expression2 = parser.parseExpression(aaa.age());
//        Expression expression3 = parser.parseExpression("num");
        Object name = expression.getValue(context);
        Object age = expression2.getValue(context);
//        Object num = expression3.getValue(context);

        BootStoneLog.bootStone.info("自定义注解动态传参：name:{},age:{},num:{}",name,age);

        return joinPoint.proceed();
    }
}
