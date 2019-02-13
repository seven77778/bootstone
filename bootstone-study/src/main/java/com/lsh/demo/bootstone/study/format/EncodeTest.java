package com.lsh.demo.bootstone.study.format;

import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by lsh on 2019/1/2.
 * URLEncoder.encode 编码格式
 */
public class EncodeTest {

    @Test
    public void encodeTest() throws Exception{
        String str = "123!@#$飞猪";
        String encodeStr = URLEncoder.encode(str,"utf-8");
        System.out.println(encodeStr);
        String  decodeStr= URLDecoder.decode(encodeStr,"utf-8");
        System.out.println(decodeStr);

    }


}
