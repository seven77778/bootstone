package com.lsh.demo.designmode.observer.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lsh on 2020-07-02.
 */
public class MessageEventListener implements EventListener {

    private Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("给用户 {} 发送短信通知(短信)：{}", result.getUid(), result.getMsg());
    }
}
