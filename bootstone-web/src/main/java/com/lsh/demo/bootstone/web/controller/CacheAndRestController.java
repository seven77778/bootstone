package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lsh
 * @date 2022/3/27 10:56
 */
@RestController
@RequestMapping("/cache")
public class CacheAndRestController {

    @Resource
    private CacheManager caffeineCacheManager;


    @RequestMapping("user")
    @Cacheable(value = "USER", key = "#name", sync = true)
    public String getCache(String name) {
        String res = queryFromDB(name);
        return res;
    }

    @RequestMapping("phone")
    @Cacheable(value = "PHONE", key = "#p0", sync = true)
    public String getCachePhone(String name) {
        String res = queryPhoneFromDB(name);
        return res;
    }

    /**
     * 清理缓存，可以搞成定时任务
     */
    @RequestMapping("clear")
    public void clearCache() {
        Cache cache = caffeineCacheManager.getCache("USER");
        if (null != cache) {
            cache.clear();
            BootStoneLog.bootStone.info("清理缓存success");
        }else {
            BootStoneLog.bootStone.info("清理缓存失败");
        }
    }

    /**
     * 模拟查询数据库
     *
     * @return
     */
    private String queryFromDB(String name) {
        BootStoneLog.bootStone.info("查询数据库 name:{}", name);
        return "查询的姓名为：lsh，2022-03-27 ";
    }

    private String queryPhoneFromDB(String phone) {
        BootStoneLog.bootStone.info("查询数据库 phone，{}", phone);
        return "查询的手机号为 ：123，2022-03-27 ";
    }

}
