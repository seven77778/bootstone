package com.lsh.demo.bootstone.service;

import com.lsh.demo.bootstone.config.LshSmsProperties;

public class TencentSmsSenderImpl implements SmsSender {

    private LshSmsProperties smsMessage;

    public TencentSmsSenderImpl(LshSmsProperties smsMessage) {
        this.smsMessage = smsMessage;
        System.out.println("腾讯云短信被加载" + smsMessage.toString());
    }
    @Override
    public void send(String sms) {
        System.out.println(smsMessage.toString() + "腾讯云开始发送短信==》短信内容：" + sms);
    }
}
