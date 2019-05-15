package com.lsh.demo.lock.lockutil;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractOrderServer implements OrderService{

    static int num;

    /**
     * 类锁 只有一个
     * 对象锁 可以有多个
     * */
    public static synchronized String getOrderNo2(){
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMDDHHMMSS");
        return format.format(new Date())+(num++);
    }

    @Override
    public String getOrderNo() {
        return getOrderNo2();
    }
}
