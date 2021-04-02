package com.lsh.demo.bootstone.web.aop;

import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.service.biz.anno.RouteApiName;
import com.lsh.demo.bootstone.service.biz.module.route.RequestBeanRouteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@Component
public class RouteApiContext implements ApplicationContextAware, InitializingBean {


    //为了 afterPropertiesSet 能用到，存这些都是为了map中存一下 反射执行invoke需要的东西
    private ApplicationContext applicationContext;
    private Map<String, RouteMethodInvoker> methodInvokerMap = Maps.newConcurrentMap();


    /**
     * 主要是为了拿到bean
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 配合挺好，在setApplicationContext之后执行，所有的bean都可以拿到
     * 在包含BeanFactory设置了所有bean属性并满足BeanFactoryAware ， ApplicationContextAware等之后调用
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("this is afterPropertiesSet");
        String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, RequestBeanRouteService.class);
        System.out.println(beanNames);
        //拿到实现了RequestBeanRouteService接口的类
        for (String beanName : beanNames) {
            /**
             * @see AutowiredAnnotationBeanPostProcessor#determineCandidateConstructors(java.lang.Class, java.lang.String)
             */
            //determineCandidateConstructors 中有判断contextType是否等于userClass todo 什么时候不一样呢
            Class<?> contextType = applicationContext.getType(beanName);
            Class<?> userClass = ClassUtils.getUserClass(contextType);
            Object bean = applicationContext.getBean(beanName);
            putMethodInMap(userClass,bean);
        }
        System.out.println(methodInvokerMap);
    }

    //组装methodInvokerMap
    public void putMethodInMap(Class<?> userClass,Object bean){
//        Method[] methods = userClass.getMethods();
        Set<Method> methods = MethodIntrospector.selectMethods(userClass,
                (ReflectionUtils.MethodFilter) method -> AnnotatedElementUtils.hasAnnotation(method, RouteApiName.class));
        System.out.println(methods);
        //思考，既然每个目标方法上都有 @RouteApiName 注解，为什么不直接开局扫描所有的注解，拿到目前方法来缓存起来？
        //现在是先拿到所有的 RequestBeanRouteService 的实现类，在这里的方法再拿@RouteApiName 注解
        for (Method m : methods) {
            String methodName = getAnnoFromMethod(m);
            if (StringUtils.isNotBlank(methodName)) {
                RouteMethodInvoker routeMethodInvoker = new RouteMethodInvoker();
                routeMethodInvoker.setMethodName(m.getName());
                routeMethodInvoker.setBean(bean);
                routeMethodInvoker.setMethod(m);
                methodInvokerMap.put(methodName, routeMethodInvoker);
            }
        }
    }


    public String getAnnoFromMethod(Method method) {

        final RouteApiName annotation = AnnotationUtils.findAnnotation(method, RouteApiName.class);
        if (null != annotation) {
            return annotation.name();
        }
        return null;

    }

    public Map<String, RouteMethodInvoker> getMethodInvokerMap() {
        return methodInvokerMap;
    }
}
