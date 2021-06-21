package com.lsh.demo.bootstone.dao.mysql.mapper;

import com.lsh.demo.bootstone.dao.mysql.Football;
import com.lsh.demo.bootstone.dao.mysql.SqlLearn;
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

    /**
     * 使用版本号更新stu表
     */
    Integer updateByVersion(@Param("id") Integer id,@Param("grade")String grade,@Param("version")Integer version);

    /**
     * 测试java中String是否能对应mysql的date格式 -- ok
     */
    Integer updateByStringDate(@Param("date")String date);

    /**
     * 测试in 后面能否同事跟多个条件
     * @see SqlLearn 17
     * 查询条件是list，想查 name=aa,id=1 和 name=bb，id=2 的
     * 不能用in，尝试用 or -- binggo！
     *  最终转换结果：SELECT * FROM stu where  (name ='aa' and age=18)  or  (name='bb'and age=1);
     */
    List<Stu> queryByManyIn(@Param("stus")List<Stu> stus);
}
