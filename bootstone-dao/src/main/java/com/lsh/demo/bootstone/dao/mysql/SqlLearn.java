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
 */
public class SqlLearn {

}
