package com.lsh.demo.designmode.observer.demo2;

import java.util.Date;

/**
 * Created by lsh on 2020-07-02.
 */
public class LotteryServiceImpl  extends LotteryService  {
    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    protected LotteryResult doDraw(String uId) {
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 结果
        return new LotteryResult(uId, lottery, new Date());
    }
}
