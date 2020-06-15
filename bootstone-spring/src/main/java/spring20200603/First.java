package spring20200603;

import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lsh on 2020-06-03.
 *
 * spring 官方文档
 * https://docs.spring.io/spring/docs/5.3.0-SNAPSHOT/spring-framework-reference/core.html#spring-core
 *
 * 学习地址：
 * https://blog.csdn.net/qq_41907991/article/details/103589868
 *
 * 同时写 application.xml 和 application.yml 报错 文档根元素 "beans" 必须匹配 DOCTYPE 根 "null"
 *
 * */
public class First {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cc =
                // 这里我们通过xml配置实例化一个容器
                new ClassPathXmlApplicationContext("bean.xml");
        First first = (First) cc.getBean("aaa123");
        /**
         * @see AbstractAutowireCapableBeanFactory#createBeanInstance
         * createBeanInstance 中bean name 有 stoneService xmlstoneService 等等，都是在 bean.xml中配置的
         *
         * NoXmlBean 在First.java的debug肿么没有被加载到，只是bean.xml，启动整个项目应该可以
         * 启动bootstone，noxmlbean加载，其实bean.xml没有被加载到
         * @ImportResource("classpath:bean.xml") 搞定，注解先加载，xml后加载
         *
         * why:只能加载public的类？
         *
         * supplier 可以创建对象
         *
         * Spring官网上指明了，在Spring中实例化一个对象有三种方式：
         *
         * 1、构造函数
         * 2、实例工厂方法
         * 3、静态工厂方法
         *
         * 对象实例化，只是得到一个对象，还不是一个完全的Spring中的Bean，我们实例化后的这个对象还没有完成依赖注入，没有走完一系列的声明周期
         *
         */
    }


}
