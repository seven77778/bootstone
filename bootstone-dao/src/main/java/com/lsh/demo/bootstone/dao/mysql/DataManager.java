package com.lsh.demo.bootstone.dao.mysql;

import com.lsh.demo.bootstone.dao.mysql.mapper.DataService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@CacheConfig(cacheNames = "mycaches")
public class DataManager {

    @Resource
    private DataService dataService;

    public Stu getByAnno(Integer id){
        System.out.println("调用数据库");
      return  dataService.getById(id);
    }

}
