package com.lsh.demo.bootstone.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义一个短信服务 starter
 */
@Data
@ConfigurationProperties(prefix = "lshsms")
public class LshSmsProperties {
    /**
     * name
     */
    private String name;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 秘钥
     */
    private String sign;

    /**
     * 网址
     */
    private String url;

    @Override
    public String toString() {
        return "SmsMessage{" +
                "userName='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sign='" + sign + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
