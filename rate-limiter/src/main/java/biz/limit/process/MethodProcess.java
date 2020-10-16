package biz.limit.process;


import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.anno.RateLimitAnno;
import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.service.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class MethodProcess  implements InitializingBean {

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private List<RateLimit> limits = Lists.newArrayList();
    private Map<String, RateLimit> limitMap = Maps.newConcurrentMap();


    @Pointcut("@annotation(biz.limit.anno.RateLimitAnno)")
    public void point() {

    }

    @Around("point()&& @annotation(rateLimitAnno)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RateLimitAnno rateLimitAnno) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(proceedingJoinPoint.getArgs());
        System.out.println("around：" + methodName + ",参数为：" + args);

        String name=rateLimitAnno.name();
        String type=rateLimitAnno.type();
        int num= rateLimitAnno.num();
        boolean isWait = rateLimitAnno.waitFor() == 1;

        System.out.println("注解的参数为" + rateLimitAnno.type() + rateLimitAnno.name());

        RateLimit rateLimit = limitMap.get(rateLimitAnno.type());
//        String numLimit = redisUtil.get(rateLimitAnno.name() + rateLimitAnno.type());
        RateConfig config = new RateConfig(type,name,num,isWait);

        //在切面中阻止代码继续进行，也就是限流，直接抛出异常
        rateLimit.limit(config);
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            //释放 todo 测试，先不释放
            rateLimit.releaseLimit(config);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        limits.forEach(x -> {
            limitMap.put(x.getType(), x);
        });
    }
}
