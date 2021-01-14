package com.lsh.demo.bootstone.dao.mysql.mapper;

public interface CountByCondition {

    /**
     * 统计加条件
     *
     *  count((type = 1) OR NULL) AS count
     *
     *  可以查询出0的数据
     *
     *
     *
     */
}
