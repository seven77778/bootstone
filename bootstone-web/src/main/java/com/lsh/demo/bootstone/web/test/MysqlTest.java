package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.dao.mysql.DataService;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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
 * org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.lsh.demo.bootstone.dao.mysql.DataService.selectAllStu
 *
 * 3.利用好 mybatis插件，因为多了个空格
 * <mapper namespace="com.lsh.demo.bootstone.dao.mysql.DataService">
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
}
