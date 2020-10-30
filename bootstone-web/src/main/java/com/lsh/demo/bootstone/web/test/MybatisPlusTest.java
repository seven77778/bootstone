package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.dao.mybatisplus.User;
import com.lsh.demo.bootstone.dao.mybatisplus.UserMapper;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootStoneWebApplication.class})
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
