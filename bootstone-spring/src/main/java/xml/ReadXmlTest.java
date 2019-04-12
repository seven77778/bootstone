package xml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lsh on 2019/4/10.
 */
@Component
@RestController
@RequestMapping("/xml")
public class ReadXmlTest {


    /**
     * byName注入
     */
    @Resource(name = "AImpl")
    public  StoneService service;


    @GetMapping("test1")
    public String re(){
        return "OK";
    }

    /**
     * NullPointerException
     * service is null
     *
     * 启动springboot，service不为空
     * 本地test启动为null
     */
    @GetMapping("/test")
    public String test(){
        service.happy();
        return "happy";
    }

    @Test
    public void test2(){
        service.happy();
    }

    /**
     * 通过 ClassPathXmlApplicationContext 方式读取xml
     * 通过 test3 learn ClassPathXmlApplicationContext
     */
    @Test
    public void test3(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");

        XMLStoneService xmlStoneService = classPathXmlApplicationContext.getBean("xmlstoneService2",XMLStoneService.class);
        //Test OK
        xmlStoneService.xmlHappy();
        // todo learn in
        classPathXmlApplicationContext.refresh();

        // nothing
        org.springframework.core.io.Resource resource = classPathXmlApplicationContext.getResource("bean.xml");

        System.out.println(resource);
    }

}
