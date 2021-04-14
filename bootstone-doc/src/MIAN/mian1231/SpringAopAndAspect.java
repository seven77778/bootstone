import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 *
 * Spring AOP 和 AspectJ AOP
 *
 * Spring aop 是基于代理的 aop 框架。这意味着, 要实现目标对象的各个方面,
 * 它在运行时来处理
 * 它将创建该对象的代理。使用以下两种方法之一实现:
 *
 * JDK 动态代理 —— Spring AOP 的首选方式。只要目标对象实现甚至一个接口, 就会使用 JDK 动态代理；
 * CGLIB 代理 —— 如果目标对象没有实现接口, 则可以使用 CGLIB 代理。
 *
 * 另一方面, AspectJ 在运行时不做任何事情, 因为类是直接用方面进行编译的。
 * @before @around 等等都是 Aspect的注解
 * Aspect 性能更好
 *
 * 区别：
 * 1 spring是基于代理的，或者是jdk代理，或者是cglib，spring的aop还要xml配置
 * 2 aspect 基于字节码 （这么说比较笼统，因为cglib也是基于字节码的），运行时开销小，
 *  aspect是 编译时期 + 文件加载时期 载入
 *
 */
public class SpringAopAndAspect implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

    }
}
