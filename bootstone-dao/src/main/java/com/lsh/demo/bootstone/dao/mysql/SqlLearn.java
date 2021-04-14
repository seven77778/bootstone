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
 *
 * 9.TRUNCATE和ROUND 的区别，round 四舍五入，TRUNCATE 直接截取】
 *
 * 10.查询百分比，前后相除的时候，一般都有sum，count，这样才是数字
 * SELECT round(sum(CASE when type = '0' then 1 ELSE 0 END)/
 * count(*) *100  ,2)  from stu where  status ='优秀' ;
 * *100是计算出来的是小数，算成百分比
 *
 * 11.拼接 CONCAT
 * SELECT CONCAT(round(sum(CASE when id=1 then age ELSE 0 end )/ sum(age) *100  ,2),'%')  from stu
 *
 * 12.当年第一天
 * concat(year(now()),'-01-01')
 *
 * 13.round(hotel_id, 2) 一个round会影响到最终结果的小数点位数
 *
 */

public class SqlLearn {


    public static void main(String[] args) {
        String value = "1";
        Integer begin = Integer.valueOf(value);
        System.out.println(begin);
    }
}
