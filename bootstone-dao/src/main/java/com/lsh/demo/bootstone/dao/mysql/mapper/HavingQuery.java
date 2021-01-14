package com.lsh.demo.bootstone.dao.mysql.mapper;

/**
 *
 * having 使用不多，性能不好，在分组之后应用上，很难用到索引
 *
 *       学号        班级
 * 1	20190607001	0607
 * 2	20190607002	0607
 * 3	20190608003	0608
 * 4	20190608004	0608
 * 5	20190609005	0609
 * 6	20190609006	0609
 * 7	20190609007	0609
 *
 *
 */
public interface HavingQuery {



    /**
     * 一、查询班级人数为3的班级 -0609
     *
     *SELECT
     * 	*
     * FROM
     * 	(
     * 		SELECT
     * 			count(*) AS total,
     * 			cno
     * 		FROM
     * 			tbl_student_class ttt
     * 		GROUP BY
     * 			cno
     * 	) aaa
     * WHERE
     * 	aaa.total = 3
     *
     * 	使用having之后
     *
     * 	select count(*),cno from tbl_student_class GROUP BY cno HAVING count(*)>=3
     *
     * WHERE 先过滤出行，然后 GROUP BY 对行进行分组，HAVING 再对组进行过滤，筛选出我们需要的组
     *
     * having的操作对象是组，三种，常数，聚合函数，聚合键，
     * 聚合键也就是 GROUP BY 子句中指定的列名
     *
     * 二、查询主键编号是否连续 -- 没有group by,此时整张表会被聚合为一组
     * idea:一共多少条，用最大id减去最小ID+1，如果连续就相等
     *
     * 1.SELECT '存在缺失的编号' AS gap, (MAX(id) - MIN(id) + 1) as num
     * FROM tbl_student_class
     * HAVING COUNT(*) != MAX(id) - MIN(id) + 1;
     *
     * 等同于
     *
     * 2.SELECT CASE WHEN COUNT(*) = 0 THEN '表为空'
     *     WHEN COUNT(*) != (MAX(id) - MIN(id) + 1) THEN '存在缺失的编号'
     *     ELSE '连续' END AS gap
     * FROM tbl_student_class;
     *
     *
     * 3.where 指定行对应的条件
     *   having 制定组对应的条件
     *
     */






     /*
    DROP TABLE IF EXISTS tbl_student_class;
    CREATE TABLE tbl_student_class (
            id int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    sno varchar(12) NOT NULL COMMENT '学号',
    cno varchar(5) NOT NULL COMMENT '班级号',
    cname varchar(50) NOT NULL COMMENT '班级名',
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生班级表';
    -- ----------------------------
            -- Records of tbl_student_class
    -- ----------------------------
    INSERT INTO tbl_student_class(sno, cno, cname) VALUES ('20190607001', '0607', '影视7班');
    INSERT INTO tbl_student_class(sno, cno, cname) VALUES ('20190607002', '0607', '影视7班');
    INSERT INTO tbl_student_class(sno, cno, cname) VALUES ('20190608003', '0608', '影视8班');
    INSERT INTO tbl_student_class(sno, cno, cname) VALUES ('20190608004', '0608', '影视8班');
    INSERT INTO tbl_student_class(sno, cno, cname) VALUES ('20190609005', '0609', '影视9班');
    INSERT INTO tbl_student_class(sno, cno, cname) VALUES ('20190609006', '0609', '影视9班');
    INSERT INTO tbl_student_class(sno, cno, cname) VALUES ('20190609007', '0609', '影视9班');

    */

}
