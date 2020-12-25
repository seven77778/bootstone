package com.lsh.demo.bootstone.dao.mysql.mapper;

/**
 *union  会对结果排序和去重复
 * union all 则不会排序也不会去重复 -- 所以union all效率高
 *
 * 1.limit 针对两条语句都有效
 *
 * 2.union前后两条语句的查询列数必须一致
 *
 */
public interface UnionQuery {



}
