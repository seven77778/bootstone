package com.lsh.demo.bootstone.dao.mysql;

/**
 * Created by lsh on 2020-06-12.
 *
 * 1. sql日期匹配
 * and  date_format(one.date,'%Y-%m-%d') ='2020-06-11'
 *
 * 2.sql 日期取整
 * SELECT  date(create_time) FROM `order`
 * 等同于上述sql
 *
 * 3.update语句 ，set a=1,b=2，不要用and
 *
 * 4.order by 后面只能跟 $,但是其他地方能用#的就不用$
 *
 * 5.批量插入的sql，如何values后面没有了内容，可能是list为空了
 *
 * 6.生产环境订正数据一定要提前备份数据库！！！ todo and important
 *
 * 7.查询出来两列结果需要对比时，可以用case when
 *
 * 8.已过期的时间条件 查询大于当天   查询大于今天
 * and end_date>=CURDATE() ，查询结束日期大于今天的
 */
public class SqlLearn {


    public static void main(String[] args) {
        String value = "1";
        Integer begin = Integer.valueOf(value);
        System.out.println(begin);
    }
}
