package com.lsh.demo.bootstone.service.producerandconsumer.impl;

import com.lsh.demo.bootstone.service.producerandconsumer.*;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lsh on 2019/3/18.
 */
public class BlockQueueModel implements Model {

    private final BlockingDeque<Task> blockingDeque;
    private AtomicInteger taskNo = new AtomicInteger(0);


    public BlockQueueModel(int cap) {
        this.blockingDeque = new LinkedBlockingDeque<>(cap);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }


    private class ProducerImpl extends AbstractProducer implements Runnable, Producer {

        @Override
        public void produce() throws Exception{
            Thread.sleep((long) (Math.random()*1000));
            Task task = new Task(taskNo.getAndIncrement());
            blockingDeque.put(task);
            System.out.println("生产者 " + task.no);

        }
    }

    private class ConsumerImpl extends AbstractConsumer implements Runnable,Consumer {

        @Override
        public void consume() throws Exception{
            // 类似出栈
            Task task = blockingDeque.take();
            Thread.sleep(500 + (long)(Math.random()*500));
            System.out.println("消费者 " + task.no);
        }
    }


    public static void main(String[] args) {
        Model model = new BlockQueueModel(3);
        for(int i=0;i<5;i++){
            new Thread(model.newRunnableConsumer()).start();
        }
        for(int i=0;i<10;i++){
            new Thread(model.newRunnableProducer()).start();
        }
    }

}
