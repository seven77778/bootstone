package com.lsh.demo.bootstone.dao.mysql;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchDataService {

    /**
     * 批量更新语句
     *
     * trim prefix 后面跟的是sql字段 begin_date
     *
     */
    int batchUpdate(List<Stu> stus);


    /**
     * 批量查询
     */

    List<Stu> batchSelect(@Param("ids") List<Integer> ids);

    /**
     *https://blog.csdn.net/qq_22771739/article/details/84668620
     *
     * ON DUPLICATE KEY UPDATE作用
     * 语句的作用，当insert已经存在的记录时，执行Update
     * 单条执行时 返回 1代表是插入，2 是更新
     */
    int duplicateInsert(@Param("name")String name,@Param("id")Integer id);

    /**
     * 批量使用ON DUPLICATE KEY UPDATE
     * INSERT INTO stu (NAME, id)
     * VALUES
     * 	('121212', 12),('1111',1) ON DUPLICATE KEY UPDATE id =VALUES(id),name=VALUES(name);
     */
    int batchDuplicateInsert(List<Stu> stus);


    /**
     * 查询出来的两列结果，进行对比是否一致，可以使用case when
     */
    List<Stu> selectNotSame();


}
