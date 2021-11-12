package com.lsh.demo.bootstone.testconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PersonCarTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersonCarConfig.class);
        System.out.println(ctx.getBean("person"));
        System.out.println(ctx.getBean("car"));
        System.out.println(ctx.getBean(Car.class));
        System.out.println(ctx.getBean(PersonCarConfig.class));
        //com.lsh.demo.bootstone.testconfig.PersonCarConfig$$EnhancerBySpringCGLIB$$d369ba50@36c88a32
        //最后一个，是有cglib的增强

    }
    /**
     *
     * 第一种情况，不加@configuration
     * com.lsh.demo.bootstone.testconfig.Car@282003e1
     * com.lsh.demo.bootstone.testconfig.Person@4461c7e3
     * com.lsh.demo.bootstone.testconfig.Car@351d0846
     * 两个car不是一个对象，会重复加载
     *
     *
     * 第二种:
     * com.lsh.demo.bootstone.testconfig.Car@229d10bd
     * com.lsh.demo.bootstone.testconfig.Person@149e0f5d
     * com.lsh.demo.bootstone.testconfig.Car@229d10bd
     *
     * 不会重复加载
     *
     * Spring 容器在启动时，会加载默认的一些PostProcessor，其中就有ConfigurationClassPostProcessor，
     * 这个后置处理程序专门处理带有@Configuration注解的类，这个程序会在bean 定义加载完成后，在bean初始化前进行处理。
     * 主要处理的过程就是使用cglib动态代理增强类，而且是对其中带有@Bean注解的方法进行处理。
     *
     *
     * 总结
     * 那么加了@Configuration和不加有本质上有什么区别的？
     * 当在配置类中一个@Bean 使用方法的方式引用另一个Bean如果不加注解就会重复加载Bean
     * 如果加了@Configuration  则会在这里创建cglib代理，当调用@Bean方法时会先检测容器中是否存在.
     *
     * 链接：https://www.zhihu.com/question/360254920/answer/1661909687

     */
}
