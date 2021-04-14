package com.lsh.demo.designmode.observer.demo2;

import com.alibaba.fastjson.JSON;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.junit.Test;

/**
 * Created by lsh on 2020-07-02.
 */
public class ObserverTest {

    @Test
    public void test() {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult result = lotteryService.draw("2765789109876");
        BootStoneLog.bootStone.info("测试结果：{}", JSON.toJSONString(result));
        //2021年4月12日 在加一个email通知 ,体验下来，要加的东西有点多，不是很方便

    }

}
