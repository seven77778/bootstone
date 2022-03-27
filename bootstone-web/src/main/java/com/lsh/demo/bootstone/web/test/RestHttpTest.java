package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.junit.Test;
import work.utils.HttpAdapter;

import javax.annotation.Resource;

/**
 * @author lsh
 * @date 2022/3/26 17:20
 */
public class RestHttpTest extends BaseTest {

    @Resource
    private HttpAdapter httpAdapter;

    @Test
    public void test(){
        String url = "http://www.baidu.com";
        String res1 =httpAdapter.doGet(url);
//        BootStoneLog.bootStone.info(res1);


        String res2 =httpAdapter.doGetLong(url);


        BootStoneLog.bootStone.info(res2);
    }
}
