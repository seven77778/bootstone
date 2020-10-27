package every;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by lsh on 2019/4/15.
 */
public class BeanFactoryDemo {

    @Test
    public void test(){
        BeanFactory beanFactory = new DefaultListableBeanFactory();

    }

    @Test
    public void instanceSpring(){
        ApplicationContext ac = new FileSystemXmlApplicationContext("applicationContext.xml");
        ac.getBean("userService");
    }

}
