package com.lsh.demo.bootstone.dao.mysql.lock;

/**
 * 哪些操作会引起锁表？
 * 1. mysql 5.6 在 update 和 delete 的时候，where 条件如果不存在索引字段，那么这个事务是否会导致表锁？
 * -- 只有主键和唯一索引才是行锁，普通索引是表锁。
 * -- 当“值重复率”低时，甚至接近主键或者唯一索引的效果，“普通索引”依然是行锁；当“值重复率”高时，MySQL 不会把这个“普通索引”当做索引，即造成了一个没有索引的 SQL，此时引发表锁
 */
public class LockTable {



}
