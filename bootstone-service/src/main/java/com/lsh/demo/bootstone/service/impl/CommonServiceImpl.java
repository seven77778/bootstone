package com.lsh.demo.bootstone.service.impl;

import com.lsh.demo.bootstone.service.CommonService;
import com.lsh.demo.bootstone.service.util.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        try{
            if (!redisUtil.exists(key)) {
                redisUtil.setKeyByTime(key, "key过期时间", 20, TimeUnit.SECONDS);
            }
            return redisUtil.getKeyExpire(key, TimeUnit.SECONDS) + "";
        }catch (Exception e){
            return "sdsadsadasda";
        }

    }


    /**
     * 事务回滚的2种方式
     * @param key
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public String invokeSql(String key) {
        return null;
    }

    /**
     * 2. 加了 @Transactional 会自动去连接数据库，连不上也会抛出异常
     */
    @Override
//    @Transactional
    public String invokeSql2(String key) {
        throw new RuntimeException("手动抛出异常");
    }
}
