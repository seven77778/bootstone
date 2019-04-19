package com.bootstone.spring.ioc.iocdemo1;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by lsh on 2019/4/18.
 * MoAttack 墨攻攻城，宾临城下，“来者何人” “墨者咯咯咯”
 */
@Service
public class MoAttack {

    public void cityGateAsk(){
        LiuDeHua liuDeHua = new LiuDeHua();
        liuDeHua.responseAsk("在下来攻城");
        //具体演员侵入剧本
        //一个明智的编剧 应该围绕角色创作，而不是具体演员

        //改编后

        //①引入剧本角色 接口 - geli
        GeLi geLi = new LiuDeHua();
        //②通过接口展开剧情
        geLi.responseAsk("在下来攻城");
        //通过引入 导演，使得剧本和具体演员之间解耦
        //对应到软件中，导演就像装配器，安排演员的角色
        //IOC inverse of control 控制 和 反转
        /**
         * 控制 是选择geli演员的权利
         * 反转 是指控制权从剧本脱离 交给导演
         *
         * 结合java来说 即某一接口具体实现的选择控制权从调用类中移除
         * 交给第三方来决定
         */



    }


    /**
     * 通过name注入
     */
    @Resource(name = "xiao")
    private GeLi geLi;


    public void selectGeli(){
        geLi.responseAsk("123");

    }
}
