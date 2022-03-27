package com.lsh.demo.bootstone.dao.mapstruct;

public class MapSOrderQueryParam {
    /**
     * 订单编号
     */
    private String orderSn123;
    /**
     * 收货人姓名/号码
     */
    private String receiverKeyword;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private Integer status;
    /**
     * 订单类型：0->正常订单；1->秒杀订单
     */
    private Integer orderType;
    /**
     * 订单来源：0->PC订单；1->app订单
     */
    private Integer sourceType;

    /**
     * 不同的字段，通过mapper自定义转换
     */
    private String test;

    /**
     * mapper固定值
     */
    private String constantName;

    public String getOrderSn123() {
        return orderSn123;
    }

    public void setOrderSn123(String orderSn123) {
        this.orderSn123 = orderSn123;
    }

    public String getReceiverKeyword() {
        return receiverKeyword;
    }

    public void setReceiverKeyword(String receiverKeyword) {
        this.receiverKeyword = receiverKeyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getConstantName() {
        return constantName;
    }

    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }
}
