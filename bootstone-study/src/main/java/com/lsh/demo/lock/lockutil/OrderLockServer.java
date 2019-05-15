package com.lsh.demo.lock.lockutil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LSH on 2018/11/6.
 *
 * @author LSH
 * @date 2018/11/6
 */
public class OrderLockServer extends AbstractOrderServer implements OrderService {
    static int num =0;

    @Override
    synchronized public String getOrderNo(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHMMSS");
        return format.format(new Date())+(num++);
    }

}
