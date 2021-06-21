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
 * 或者，一条sql，用in查询出两个状态的所有订单，在java代码中筛选 fixme 哪个好 查一次好
 *
 */

/* sql1：
EXPLAIN SELECT * FROM room
LEFT JOIN  rtype ON room.roomcode = rtype.code
WHERE room.hotelid = '123';
sql2：
EXPLAIN SELECT * FROM room
LEFT JOIN  rtype ON room.hotelid = rtype.hotelid and room.roomcode = rtype.code
WHERE room.hotelid = '123';
sql3：
EXPLAIN SELECT * FROM room,  rtype
WHERE room.hoteild = '123' and room.hoteilid = rtype.hoteilid and room.roomcode=rtype.code ;
 */
/**
   id select_type table type  possible_keys key key_len ref rows filtered Extra  partitions(都空)
 * 一：
 * 1	SIMPLE	room	ref	    PRIMARY	PRIMARY	50	  const	    28	100
 * 1	SIMPLE	rtype	ALL		null	null	                663	100	  Using where; Using join buffer (Block Nested Loop)
 *
 * 四: 发现少了条件，排除
 * 1	SIMPLE	room	ref	    PRIMARY	PRIMARY	50	 const	    28	100
 * 1	SIMPLE	rtype	ref	    PRIMARY	PRIMARY	50	 hotelId    2	100
 *
 * 二：
 * 1	SIMPLE	room	ref	    PRIMARY	PRIMARY	50	 const	            28	100
 * 1	SIMPLE	rtype	eq_ref	PRIMARY	PRIMARY	148	 hotelId,roomCode	1	100
 * 三：不用left on
 * 1	SIMPLE	rtype	ref	   PRIMARY	PRIMARY	50	const	4	100
 * 1	SIMPLE	room	ref	   PRIMARY	PRIMARY	50	const	28	10	Using where
 *
 * 哪个好？
 * 1.key_len越短越好 -- 表示索引中使用的字节数
 * 2.type -- system > const > eq_ref > ref > range > index > all
 * 3.第一条之所以有Using join buffer (Block Nested Loop)，因为 room.roomcode 没索引
 * 4.看 2和3，一个是ref，一个eq_ref，一个extra无，一个是 using where ？？
 *
 * 5.索引失效实例 room表
 * explain room=1001，扫描结果为all ，原因是 hotelid+room 共同组成了PRIMARY索引
 * 单独用room查询，作为组合索引的第二个，不生效
 *
 */
public class IndexLearn {


}
