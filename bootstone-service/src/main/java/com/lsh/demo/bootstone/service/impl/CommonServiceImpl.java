package com.lsh.demo.bootstone.service.impl;

import com.lsh.demo.bootstone.service.CommonService;
import com.lsh.demo.bootstone.service.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by lsh on 2019-11-21.
 */
@Component
public class CommonServiceImpl implements CommonService {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 普通方式
     *
     * @param key
     * @return
     */
    @Override
    public String getRestSeconds(String key) {
        System.out.println("redis------");
        if (!redisUtil.exists(key)) {
            redisUtil.setKeyByTime(key, "key过期时间", 20, TimeUnit.SECONDS);
        }
        return redisUtil.getKeyExpire(key, TimeUnit.SECONDS) + "";
    }

    /**
     * lua
     */
}
