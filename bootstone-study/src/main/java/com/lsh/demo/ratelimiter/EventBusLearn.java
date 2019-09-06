package com.lsh.demo.ratelimiter;

import com.google.common.eventbus.Subscribe;

/**
 * Created by lsh on 2019-09-03.
 *
 * @Subscribe 使用Guava之后, 如果要订阅消息, 就不用再继承指定的接口, 只需要在指定的方法上加上@Subscribe注解即可:
 */
public class EventBusLearn {


    @Subscribe
    void getMessage() {

    }

}
