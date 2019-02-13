package com.lsh.demo.bootstone.domain.object;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by lsh on 2019/2/12.
 * 每个DO都应该重写toString()方法
 */
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -7038238224753401406L;


    /**
     * 子类有效 com.object.BaseDO@8617c268[orderId=<null>,hotelName=<null>]
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
