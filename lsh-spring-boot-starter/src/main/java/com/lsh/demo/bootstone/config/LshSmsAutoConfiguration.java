package com.lsh.demo.bootstone.config;

import com.lsh.demo.bootstone.service.AliyunSmsSenderImpl;
import com.lsh.demo.bootstone.service.TencentSmsSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(LshSmsProperties.class)
@Configuration
@ConditionalOnProperty(
        prefix = "lsh",
        name = "sms",
        havingValue = "true",
        matchIfMissing = true
)
//prefix为配置文件中的前缀,
//name为配置的名字
//havingValue是与配置的值对比值,当两个值相同返回true,配置类生效.
//使用方如果不配置lsh.sms=true，即无法启动
public class LshSmsAutoConfiguration {

    @Autowired
    private LshSmsProperties lshSmsProperties;

    /**
     *  阿里云发送短信的实现类
     */
    @Bean("aliyunSmsSenderImpl")
    public AliyunSmsSenderImpl aliYunSmsSender(){
        return new AliyunSmsSenderImpl(lshSmsProperties);
    }
    /**
     * 腾讯云发送短信的实现类
     * @return
     */
    @Bean("tencentSmsSenderImpl")
    public TencentSmsSenderImpl tencentSmsSender( ){
        return new TencentSmsSenderImpl(lshSmsProperties);
    }

}
