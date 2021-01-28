package spring20200603;

//import sun.security.krb5.Config;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lsh on 2020-06-04.
 * 依赖注入和方法注入
 *
 * 根据官网介绍，依赖注入主要分为两种方式
 * 1、构造函数注入
 * 2、Setter方法注入
 *
 */
public class Second {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new
                // config类主要完成对类的扫描
                AnnotationConfigApplicationContext(Config.class);
        NoXmlBean service = (NoXmlBean) ac.getBean("noxmlbean");
        service.run();

    }

}
