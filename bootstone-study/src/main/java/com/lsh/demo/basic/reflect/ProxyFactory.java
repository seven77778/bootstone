package com.lsh.demo.basic.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lsh on 2018/10/31.
 *
 * @author lsh
 * @date 2018/10/31
 *
 * invokeSuper是退出当前interceptor的处理，进入下一个callback处理
 * invoke则会继续回调该方法，如果传递给invoke的obj参数出错容易造成递归调用
 */
public class ProxyFactory implements MethodInterceptor {

    private Object object;

    public Object createProxy(Object target){
        this.object = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());//设置代理目标
        enhancer.setCallback(this);//回调
        //enhancer.setClassLoader(this.object.getClass().getClassLoader());//the same effect
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }

    public Object createProxybyChange(Object target){
        this.object = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());//设置代理目标
        enhancer.setCallback(new FixedValue() {
                                 @Override
                                 public Object loadObject() throws Exception {
                                     return "createProxybyChange value";
                                 }
                             });
            //enhancer.setClassLoader(this.object.getClass().getClassLoader());//the same effect
            enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try{
            before();
            result = methodProxy.invokeSuper(o,objects);
            after();//此处的before和after相当于AspectJ的around
        }catch (Exception e){
            System.out.println("ex "+e.getMessage());
        }
        return result;
    }

    public void before(){
        System.out.println("this is before");
    }

    public void after(){
        System.out.println("this is after");
    }
}
