package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.dao.mysql.DataManager;
import com.lsh.demo.bootstone.dao.mysql.mapper.DataService;
import com.lsh.demo.bootstone.dao.mysql.Football;
import com.lsh.demo.bootstone.dao.mysql.Stu;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lsh on 2019-11-29.
 *
 * @Repository需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中。
 *
 * @Mapper不需要配置扫描地址，通过xml里面的namespace里面的接口地址，生成了Bean后注入到Service层中。
 *
 *
 * 1.启动失败检查 是否有mapperScan
 * @MapperScan("com.lsh.demo.bootstone.dao")
 *
 * 2.
 * org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.lsh.demo.bootstone.dao.mysql.mapper.DataService.selectAllStu
 *
 * 3.利用好 mybatis插件，因为多了个空格
 * <mapper namespace="com.lsh.demo.bootstone.dao.mysql.mapper.DataService">
 *
 *     4.mysql 连不上，并不会影响项目整体启动
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootStoneWebApplication.class})// 指定启动类
public class MysqlTest {

    @Resource
    private DataService service;

    @Test
    public void test(){
        System.out.println(service.selectAllStu());
    }

    @Test
    public void testGetResult(){
        System.out.println(service.getScoreAndResult());
    }

    @Test
    public void testForeach(){
        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(service.getByForeach(list,"18"));
    }

    @Test
    public void testDynamic(){
        Stu result = service.getDynamicData("");

        System.out.println(result);
    }


    /**
     * 测试时区插入数据库
     *
     * serverTimezone=Asia/Shanghai
     * @JsonFormat(pattern="yyyy-MM-dd",timezone = "Asia/Shanghai")
     */
    @Test
    public void testTimeZone(){
        Football football = new Football();
        football.setName("12");

        Calendar c1 = Calendar.getInstance();

        c1.set(1990,9,1,0,0,0);
        football.setTime(c1.getTime());

        Integer count = service.insertTimeZone(football);
        System.out.println(count);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        if(c2.get(Calendar.MONTH)==c1.get(Calendar.MONTH) &&
                c2.get(Calendar.DAY_OF_MONTH)==c1.get(Calendar.DAY_OF_MONTH)){
            System.out.println(11);
        }
    }


    /**
     * 测试cache注解
     */
    @Resource
    private DataManager dataManager;
    @Test
    public void testCacheAnno(){
        Stu res = dataManager.getByAnno(1);
        System.out.println(res);
    }

}
