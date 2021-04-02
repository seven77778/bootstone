package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootStoneWebApplication.class})// 指定启动类
public class BaseTest {
}
