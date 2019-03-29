package com.lsh.demo.bootstone.service.producerandconsumer;

/**
 * Created by lsh on 2019/3/18.
 *
 * see http://www.importnew.com/27063.html
 */
public interface Model  {

    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();

}
