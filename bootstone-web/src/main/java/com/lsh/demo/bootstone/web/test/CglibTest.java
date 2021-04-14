package com.lsh.demo.bootstone.web.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import work.utilscollections.Student;

import java.lang.reflect.Method;

/**
 * 测试cglib
 * 1.没有无参构造函数报错
 *
 */
public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        Student sample = (Student) enhancer.create();
        sample.eat();
    }
}
