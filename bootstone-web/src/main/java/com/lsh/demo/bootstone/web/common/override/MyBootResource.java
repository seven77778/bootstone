package com.lsh.demo.bootstone.web.common.override;

import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

/**
 * org.springframework.core.env.AbstractEnvironment protected void customizePropertySources(MutablePropertySources propertySources)
 * 自定义在调用getProperty(String)和相关方法期间此Environment要搜索的PropertySource对象集。
 * 鼓励重写此方法的子类使用MutablePropertySources.addLast(PropertySource)添加属性源，
 * 以使其他子类可以调用具有可预测结果的super.customizePropertySources() 。
 *
 * 例如：
 * 	   public class Level1Environment extends AbstractEnvironment {
 *                      @Override
 *           protected void customizePropertySources(MutablePropertySources propertySources) {
 * 	           super.customizePropertySources(propertySources); // no-op from base class
 * 	           propertySources.addLast(new PropertySourceA(...));
 * 	           propertySources.addLast(new PropertySourceB(...));
 *                  }
 * 	   }
 *
 * 	   public class Level2Environment extends Level1En            {
 *                      @Override
 * 	       protected void customizePropertySources(MutablePropertySources propertySources) {
 * 	           super.customizePropertySources(propertySources); // add all from superclass
 * 	           propertySources.addLast(new PropertySourceC(...));
 * 	           propertySources.addLast(new PropertySourceD       );
 * 	       }
 * 	   }
 *
 * 在这种安排下，将按照源A，B，C，D的顺序解析属性。 也就是说，属性源“ A”优先于属性源“ D”。
 * 如果Level2Environment子类希望为属性源C和D提供比A和B更高的优先级，则可以在之后而不是在添加其自身之前简单地调用super.customizePropertySources ：
 * 	   public class Level2Environment extends           vironment {
 *           @Override
 * 	       protected void customizePropertySources(MutablePropertySources propertySources) {
 * 	           propertySources.addLast(new PropertySourceC(...));
 * 	           propertySources.addLast(new PropertySourceD(...));
 * 	           super.customizePropertySources(propertySources); // add            sup       ss
 * 	       }
 * 	   }
 *
 * 现在，根据需要，搜索顺序为C，D，A，B。
 * 除了这些建议之外，子类还可以使用任何add&#42; ， remove或replace MutablePropertySources公开的方法，以创建所需属性源的准确排列。
 * 基本实现未注册任何属性源。
 * 请注意，任何ConfigurableEnvironment客户端都可以通过getPropertySources()访问器
 * （通常在ApplicationContextInitializer#getPropertySources()进一步定制属性源。 例如：
 * 	   ConfigurableEnvironment env = new StandardEnvironment();
 * 	   env.getPropertySources().addLast(new PropertySourceX(...));
 *
 * 有关实例变量访问的警告
 * 在子类中具有默认的初始值声明实例变量不应该从这个方法中访问。 由于Java对象创建生命周期的限制，当AbstractEnvironment()构造函数调用此回调时，
 * 尚未分配任何初始值，这可能导致NullPointerException或其他问题。 如果需要访问实例变量的默认值，请将此方法保留为空操作，
 * 并直接在子类构造函数中执行属性源操作和实例变量访问。 注意，给实例变量赋值是没有问题的。 它只是试图读取必须避免的默认值。
 *
 * 也可以看看：
 * MutablePropertySources ， PropertySourcesPropertyResolver ， org.springframework.context.ApplicationContextInitializer
 *   Maven: org.springframework:spring-core:5.2.10.RELEASE
 *
 *
 * @see StandardEnvironment#customizePropertySources(MutablePropertySources)  加载系统配置
 *
 * @see ConfigFileApplicationListener#addPropertySources(org.springframework.core.env.ConfigurableEnvironment, org.springframework.core.io.ResourceLoader)
 *  分叉，一个初始化，一个listener
 *
 * @see ConfigFileApplicationListener.Loader#addLoadedPropertySources()
 * yml中的配置终于现身
 *
 */

public class MyBootResource {
}
