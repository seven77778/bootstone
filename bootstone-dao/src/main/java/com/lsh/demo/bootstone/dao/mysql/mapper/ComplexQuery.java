package com.lsh.demo.bootstone.dao.mysql.mapper;

/**
 * mark some complex interfaces
 */
public interface ComplexQuery {


    /**
     * 以一张表为基准表，如果join的多张表都是一个id多条数据
     * 在left join 的时候，（）在括号中单独查询中想要的数据即可
     *
     * &gt;=   大于
     *
     * &lt;=   小于
     *
     */
    void selectBy1();

}
