package com.lsh.demo.designmode.observer.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lsh on 2020-07-02.
 */
public class MQEventListener implements EventListener {
    private Logger logger = LoggerFactory.getLogger(MQEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("记录用户 {} 摇号结果(MQ)：{}", result.getUid(), result.getMsg());

    }
}
