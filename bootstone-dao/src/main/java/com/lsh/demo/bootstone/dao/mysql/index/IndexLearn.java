package com.lsh.demo.bootstone.dao.mysql.index;

/**
 * 概述：索引可以提高查询速度，但是会影响插入记录的速度。
 * 因为，向有索引的表中插入记录时，数据库系统会按照索引进行排序，这样就降低了插入记录的速度，
 * 插入大量记录时的速度影响会更加明显。
 * 这种情况下，最好的办法是先删除表中的索引，然后插入数据，插入完成后，再创建索引。
 *
 * show index from stu; -- 查看索引
 *
 *
 * create index sourceindex on stu(name); -- 创建索引
 * sourceindex -- 索引名称  stu--表名 name -- 字段名称
 * create index twoindex on stu(name,age); -- 创建组合索引
 *
 * drop index sourceindex on stu; -- 删除索引
 *
 * 查看索引关键词
 * Non_unique	表示该索引是否是唯一索引。若不是唯一索引，则该列的值为 1；若是唯一索引，则该列的值为 0。
 * Seq_in_index 索引位置，单列的为就是1，组合索引，123代表位置
 *
 * EXPLAIN sql;
 *
 * type字段：
 * NULL > system > const > eq_ref > ref > ref_or_null > index_merge >range > index > ALL
 * type为 const，性能非常好，唯一索引，keyname=PRIMARY。且唯一。
 *
 * 索引优化建议：
 * ①禁止在更新十分频繁、区分度不高的属性上建立索引。
 * 更新会变更B+树，更新频繁的字段建立索引会大大降低数据库性能。
 * ②“性别”这种区分度不大的属性，建立索引是没有什么意义的，不能有效过滤数据，性能与全表扫描类似。
 * ③建立组合索引，必须把区分度高的字段放在前面。
 *
 * 案例1：订单表，业务需求，先查 预定的，查不到预定的，就查在住的订单
 * 写两个sql，先查预定- 查不到，再查在住，查两次
 * 或者，一条sql，用in查询出两个状态的所有订单，在java代码中筛选 fixme 哪个好
 *
 */
public class IndexLearn {


}
