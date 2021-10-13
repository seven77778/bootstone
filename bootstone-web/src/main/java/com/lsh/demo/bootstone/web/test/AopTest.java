package com.lsh.demo.bootstone.web.test;

import org.junit.Test;
import work.utils.CloseHttpUtil;

import javax.annotation.Resource;
import java.io.IOException;

public class AopTest extends BaseTest {
    @Resource
    private CloseHttpUtil httpUtil;

    @Test
    public void testHttpCostAndAop()throws IOException {
        httpUtil.httpGet();
    }
}
