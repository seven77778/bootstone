package com.lsh.demo.designmode.observer.demo2;

import com.lsh.demo.bootstone.service.common.BootStoneLog;

public class EmailListener implements EventListener {
    @Override
    public void doEvent(LotteryResult result) {
        BootStoneLog.bootStone.info("发送email结果"+result.getMsg());

    }
}
