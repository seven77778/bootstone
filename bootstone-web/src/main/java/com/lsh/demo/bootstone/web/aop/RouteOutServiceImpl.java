package com.lsh.demo.bootstone.web.aop;

import com.lsh.demo.bootstone.service.biz.module.out.RouteOutService;
import com.lsh.demo.bootstone.service.biz.vo.RouteBaseRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * 负责路由到各个业务执行类 -- 对外暴露的类
 */
@Service
public class RouteOutServiceImpl implements RouteOutService {

    @Resource
    private RouteApiContext routeApiContext;

    @Override
    public String doExcute(RouteBaseRequest rq) {
        RouteMethodInvoker routeMethodInvoker = routeApiContext.getMethodInvokerMap().get(rq.getMethodName());
        Object result = null;
        if (null != routeMethodInvoker) {
            try {
                result = routeMethodInvoker.getMethod().invoke(routeMethodInvoker.getBean(), rq);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println(e);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        return (String) result;
    }


}
