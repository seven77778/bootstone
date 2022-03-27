package com.lsh.demo.bootstone.dao.mapstruct;



public class MapSOrder {
    /**
     *订单id
     */
    private Long id;
    /**
     * 订单编号
     */
    private String orderSn;
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
     * 自定义转换
     * 0 - ok
     * 1 - ana
     * 2 - fail
     */
    private Integer testInteger;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
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

    public Integer getTestInteger() {
        return testInteger;
    }

    public void setTestInteger(Integer testInteger) {
        this.testInteger = testInteger;
    }
}
