package com.lsh.demo.bootstone.dao.mysql.mapper;

public interface ComplexQuery2 {

    /**
     * 通过order表，统计出卖的最好的房型,每个hotel一共卖了多少
     *
     *
     * 1001	type123
     * 1001	type123
     * 1001	type123
     * 1001	type124
     * 1002	type123
     * 1002	type124
     * 1002	type124
     * 1002	type124
     * 1003	type125
     * 1003	type127
     * 1003	type127
     *
     * 1001	范县大酒店	3	4	type123	大床房
     * 1002	张庄大酒店	3	4	type124	双床房
     * 1003	李庄大酒店	2	3	type127	总统套房
     *
     */
    void selectSellBestRoomType();


}
