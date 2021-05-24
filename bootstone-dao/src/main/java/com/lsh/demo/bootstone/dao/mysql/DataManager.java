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

    public Stu getByAnno(Integer id) {
        System.out.println("调用数据库");
        return dataService.getById(id);
    }

    /**
     * 版本号更新，每次更新一个,用更新grade模拟购买商品，更新成功就是购买成功
     */
    public void buyGood(Integer id, String grade) {
        Stu stu = dataService.getById(id);
        int i = dataService.updateByVersion(id, grade, stu.getVersion());
        if (i != 1) {
            System.out.println("更新失败");
        } else {
            System.out.println("更新成功");
        }
    }
}
