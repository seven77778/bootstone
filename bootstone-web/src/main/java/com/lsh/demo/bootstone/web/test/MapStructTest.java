package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.dao.mapstruct.MapSOrder;
import com.lsh.demo.bootstone.dao.mapstruct.MapSOrderMapper;
import com.lsh.demo.bootstone.dao.mapstruct.MapSOrderQueryParam;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.omg.PortableInterceptor.ObjectReferenceFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootStoneWebApplication.class})// 指定启动类
public class MapStructTest {


    /**
     * 优雅的转换javaBean
     */
    @Test
    public void entity2queryParam() {
        MapSOrder order = new MapSOrder();
        order.setId(12345L);
        order.setOrderSn("orderSn");
        order.setOrderType(0);
        order.setReceiverKeyword("keyword");
        order.setSourceType(1);
        order.setStatus(2);
        order.setTestInteger(1);
        MapSOrderQueryParam orderQueryParam = MapSOrderMapper.INSTANCE.entity2queryParam(order);

        System.out.println(orderQueryParam);
    }
}
