package com.lsh.demo.basic.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 *  jdk动态代理缺陷，适用于接口，普通类可以使用cglib，原理是通过继承，所以不能用于final类，final，private方法
 *
 * JDK自从1.3版本开始，就引入了动态代理
 * JDK的动态代理用起来非常简单，但是它有一个限制，就是使用动态代理的对象必须实现一个或多个接口
 * 如果是没有实现接口的类可以使用CGLIB
 *
 * CGLIB包的底层是通过使用一个小而快的字节码处理框架ASM，来转换字节码并生成新的类
 * 不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉
 *
 * cglib常用的地方
 * 1. @Transactional 注解，判断方法是否有事务的时候，这个注解只要public方法
 * 2.Spring的做法，基于接口的用proxy,否则就用cglib
 *
 *
 *
 */




/**
 * 总结 cglib ~= around
 * cglib的Enhancer说起来神奇，用起来一页纸不到就讲完了。
 *     它的原理就是用Enhancer生成一个原有类的子类，并且设置好callback到proxy，
 *     则原有类的每个方法调用都会转为调用实现了MethodInterceptor接口的proxy的intercept() 函数：
 * public Object intercept(Object o,Method method,Object[] args,MethodProxy proxy)
 *
 *  在intercept()函数里，你可以在执行Object result=proxy.invokeSuper(o,args);
 *  来执行原有函数，在执行前后加入自己的东西，改变它的参数值，也可以瞒天过海，完全干别的。说白了，就是AOP中的around advice。
 */
public class CglibTest {
    private Hello hello = new Hello();
    private ProxyFactory factory = new ProxyFactory();
    private Hello helloProxy = (Hello)factory.createProxy(hello);
    /**
     *抛出异常失败
     * */
    @Test
    public void test() throws Exception {
        System.out.println(helloProxy.say(true));
    }

    @Test
    public void test2() throws Exception {
        System.out.println(helloProxy.say(false));
    }


    /**
     * 修改方法的返回值
     * */
    @Test
    public void testChangeValue() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback((FixedValue) () -> "fixed-value");

        Hello proxy = (Hello)enhancer.create();
        System.out.println("result " + proxy.say(false));
    }

    /**
     * hello say方法并没有执行
     * */
    @Test
    public void test3() throws Exception {
        Hello hello2 = (Hello)factory.createProxybyChange(hello);
        System.out.println( hello2.say(false));
    }

    /**
     * MethodInterceptor
     * */
    @Test
    public void test4(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello2.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello cglib!";
            }else {
                return "else";
            }
        });
        Hello2 ss = (Hello2)enhancer.create();
        System.out.println(ss.getStr());

    }

    /**
     * getStr : class com.basic.reflection.cglib.Hello2
     wait : class java.lang.Object
     wait : class java.lang.Object
     wait : class java.lang.Object
     equals : class java.lang.Object
     toString : class java.lang.Object
     hashCode : class java.lang.Object
     getClass : class java.lang.Object
     notify : class java.lang.Object
     notifyAll : class java.lang.Object
     * */
    @Test
    public void  test5(){
        Class clz = Hello2.class;
        for(Method method : clz.getMethods()){
            System.out.println(method.getName() + " : "+ method.getDeclaringClass());
        }
    }

}
