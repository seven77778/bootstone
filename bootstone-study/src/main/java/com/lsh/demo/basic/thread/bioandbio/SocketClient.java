package com.lsh.demo.basic.thread.bioandbio;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lsh on 2019-05-09.
 *
 * 模拟20个客户端并发请求，服务端则使用单线程
 *
 */
public class SocketClient {

    public static void main(String[] args) throws InterruptedException {
        Integer clientNumber = 20;
        CountDownLatch countDownLatch = new CountDownLatch(clientNumber);

        // 分别启动20个客户端
        for (int index = 0; index < clientNumber; index++, countDownLatch.countDown()) {
            SocketClientRequestThread client = new SocketClientRequestThread(countDownLatch, index);
            new Thread(client).start();
        }

        synchronized (SocketClient.class) {
            SocketClient.class.wait();
        }
    }



}
