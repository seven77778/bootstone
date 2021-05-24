package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.dao.mysql.mapper.DataService;
import com.lsh.demo.bootstone.service.CommonService;
import com.lsh.demo.bootstone.web.common.request.HttpRateImpl;
import com.lsh.demo.bootstone.web.common.request.HttpRateImplConfig;
import com.lsh.demo.bootstone.web.common.request.SaveStu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lsh on 2019-11-21.
 */
@RestController
@RequestMapping("/service")
public class ServiceController {


    @Resource
    private CommonService commonService;

    @Resource
    private HttpRateImplConfig httpRateImplConfig;

    @Resource
    private HttpRateImpl httpRate;

    /**
     * httpUaf 也可以，是因为@bean引入过了
     */
    @RequestMapping("/testbean")
    public String testBean(){
        HttpRateImpl rs = httpRateImplConfig.getBean();
        return rs.getName() +"--" + httpRate.getName();
    }

    /**
     * 测试 不加 @compent 引入bean 直接报错
     */
    @RequestMapping("/testnobean")
    public String testNoBean(){
        return  httpRate.getName();
    }
    /**
     * 404不全是url路径不对，内部报错，寻error页无果
     * @return
     */
    @GetMapping("404")
    public String getDynamicData(){
        BeanUtils.copyProperties(null,null);
        return null;
    }

    /**
     * 前端用get方式调用post，会报405
     * @return
     */
    @PostMapping("404post")
    public String getDynamicDatap(){
        BeanUtils.copyProperties(null,null);
        return null;
    }

    @GetMapping("/test")
    public Map<String, Object> test(String key){
        Map<String, Object> map = new HashMap();
          map.put("data", "TestController getTest()");
         return map;
    }

    @GetMapping("/rest")
    public String getRestSeconds(String key){
        System.out.println("收到前端的key " + key);
        if(StringUtils.isBlank(key)){
            key = "default_key";
        }
        return commonService.getRestSeconds(key);
    }

    @RequestMapping("/runtime")
    public String getRunTime(){
        return commonService.invokeSql2("");
    }


    private SaveStu saveStu;

    @PostMapping("savedata")
    public String saveData(@RequestBody SaveStu stus){
        if(null!=stus){
            saveStu = stus;
            return "success";

        }else {
            return "failed";
        }
    }

    /**
     * 405 - 前端用get来请求post接口
     * @return
     */
    @PostMapping("getdata")
    public SaveStu getData(){
        return saveStu;
    }


    /**
     * 测试@cacheAble注解
     * 使用要点：要配置cacheNames,yml文件要配置cache-names=: mycaches
     */
    @Resource
    private DataService dataService;

    @RequestMapping("cache1")
    @Cacheable(cacheNames = "mycaches")
    public String getByAnno(){
        System.out.println("调用数据库");
        return dataService.getById(1).getName();
    }


}
