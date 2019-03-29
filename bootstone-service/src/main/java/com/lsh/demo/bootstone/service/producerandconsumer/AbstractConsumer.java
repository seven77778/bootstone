package com.lsh.demo.bootstone.service.producerandconsumer;

/**
 * Created by lsh on 2019/3/18.
 */
public abstract class AbstractConsumer implements Consumer,Runnable{

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(100);
                consume();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
}
