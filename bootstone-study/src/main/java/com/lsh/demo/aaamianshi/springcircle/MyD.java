package com.lsh.demo.aaamianshi.springcircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 单例模式下，spring会帮你解决
 * @see  AbstractAutowireCapableBeanFactory#createBeanInstance(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
 *
 *
 * 1.什么是循环依赖？
 * 2.什么情况下循环依赖可以被处理？
 * 3.Spring是如何解决的循环依赖？
 *
 * 解答：
 * 2.单例+非构造器注入、
 *
 *
 *
 * 面试官：”Spring是如何解决的循环依赖？“
 *
 * 答：Spring通过三级缓存解决了循环依赖，其中一级缓存为单例池（singletonObjects）,二级缓存为早期曝光对象earlySingletonObjects，三级缓存为早期曝光对象工厂（singletonFactories）。
 *
 * 当A、B两个类发生循环引用时，在A完成实例化后，就使用实例化后的对象去创建一个对象工厂，并添加到三级缓存中，如果A被AOP代理，
 * 那么通过这个工厂获取到的就是A代理后的对象，如果A没有被AOP代理，那么这个工厂获取到的就是A实例化的对象。
 *
 * 当A进行属性注入时，会去创建B，同时B又依赖了A，所以创建B的同时又会去调用getBean(a)来获取需要的依赖，此时的getBean(a)会从缓存中获取：
 *
 * 第一步，先获取到三级缓存中的工厂；
 *
 * 第二步，调用对象工工厂的getObject方法来获取到对应的对象，得到这个对象后将其注入到B中。紧接着B会走完它的生命周期流程，包括初始化、后置处理器等。
 *
 * 当B创建完后，会将B再注入到A中，此时A再完成它的整个生命周期。至此，循环依赖结束！
 *
 * 面试官：”为什么要使用三级缓存呢？二级缓存能解决循环依赖吗？“
 *
 * 答：如果要使用二级缓存解决循环依赖，意味着所有Bean在实例化后就要完成AOP代理，这样违背了Spring设计的原则，
 * Spring在设计之初就是通过AnnotationAwareAspectJAutoProxyCreator这个后置处理器来在Bean生命周期的最后一步来完成AOP代理，
 * 而不是在实例化后就立马进行AOP代理。
 */
@Component
public class MyD {
    public MyD() {
        System.out.println("dddddddddddddddd");
    }

    @Autowired
    private MyC c;
}
