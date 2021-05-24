package com.lsh.demo.bootstone.dao.mysql.lock;

import org.springframework.stereotype.Component;
@Component
public class OptimisticLock {
    /**
     * mysql乐观锁，使用版本号
     * 修改表，增加version字段
     *
     * 每次只能一个更新成功，不适合高并发
     *
     * 还有一种削减库存的方法，sql中判断库存减1是否大于0 -- 还不知道具体使用场景
     *
     *
     *
     */

}
