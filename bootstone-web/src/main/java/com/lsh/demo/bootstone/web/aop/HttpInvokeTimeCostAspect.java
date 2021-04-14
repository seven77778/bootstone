package com.lsh.demo.bootstone.web.aop;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import work.utils.CloseHttpUtil;

/**
 * @see CloseHttpUtil#httpGet()
 * target:不入侵httpGet方法，增加调用第三方接口的耗时统计
 * done
 */
@Component
@Aspect
public class HttpInvokeTimeCostAspect {

    //execution 匹配httpGet方法，无参加两个点
    @Pointcut("execution(public * work.utils.CloseHttpUtil.httpGet(..))")
    public void point() {
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint point1)throws Throwable{
        StopWatch  stopwatch = new StopWatch();
        stopwatch.start();
        Object rs = point1.proceed();
        stopwatch.stop();
        BootStoneLog.bootStone.info("----http cost --- " + stopwatch.getTotalTimeMillis());
        return rs;
    }

}
