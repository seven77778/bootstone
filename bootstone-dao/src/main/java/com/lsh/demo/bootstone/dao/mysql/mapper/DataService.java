package com.lsh.demo.bootstone.dao.mysql.mapper;

import com.lsh.demo.bootstone.dao.mysql.Football;
import com.lsh.demo.bootstone.dao.mysql.Stu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lsh on 2019-11-29.
 */

@Repository

public interface DataService {

    List<Stu> selectAllStu();

    List<Stu> getScoreAndResult();

    /**
     * SELECT CAST(class.score AS SIGNED) sql中转换数字
     */
    List<Stu> getByForeach(@Param("ids") List<String> ids,@Param("age")String age);

    Stu getDynamicData(String key);

    Stu getById(Integer id);

    Integer insertTimeZone(Football f);
}
