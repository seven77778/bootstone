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
 * 9.TRUNCATE和ROUND 的区别，round 四舍五入，TRUNCATE 直接截取
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
 * 14.因为没有事务导致的脏读  fixme
 * -- A系统房间无效被使用的，条件是订单没了+ 房间还没置为空净，
 * B系统的操作流程是 先给订单弄没，在去给房间置净，有时间卡到时间点上，订单刚改完，房间还没置净，
 * 就查到了脏数据，根本原因还是B系统的一系列操作没有加事务
 * -- 因为A系统是定时任务查询，并且告警，临时解决方案是A查到无效房间，sleep几秒，再去查询刚才查到的房间+酒店，
 * 如果这时状态对 了，就不去告警
 * --不能再去全局查询，因为B没事务，任何时间全局查询，都可能有脏读
 *
 * 15.实战 Using temporary
 *  -- 在主键上增加了DISTINCT，所以导致了临时表
 *  （产生临时表的情况 orderby groupby 子查询 not in exist 等等）
 *
 * / Using join buffer (Block Nested Loop)
 *
 *  -- 用有索引的字段，在left on 后面 on x.id =y.id and x.no = y.no
 *  新增一个and条件后，done
 *
 *  16. 给某个字段前面都拼接上一个字母 A，如果这个字段以A开头，就不操作
 *  -- UPDATE table_room set name = CONCAT('A',name)
 *  where id='123' and LEFT(name,1) != 'A';
 *
 *
 *
 *
 */

public class SqlLearn {


    public static void main(String[] args) {
        String value = "1";
        Integer begin = Integer.valueOf(value);
        System.out.println(begin);
    }
}
