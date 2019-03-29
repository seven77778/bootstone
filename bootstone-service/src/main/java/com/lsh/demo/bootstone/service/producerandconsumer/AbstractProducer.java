package com.lsh.demo.bootstone.service.producerandconsumer;

/**
 * Created by lsh on 2019/3/18.
 */
public abstract class AbstractProducer implements Producer,Runnable {

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(100);
                produce();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
}
