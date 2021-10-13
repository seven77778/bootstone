package com.lsh.demo.bootstone.service;

import com.lsh.demo.bootstone.config.LshSmsProperties;

public class AliyunSmsSenderImpl implements SmsSender {

    private LshSmsProperties smsMessage;

    public AliyunSmsSenderImpl(LshSmsProperties smsMessage) {
        this.smsMessage=smsMessage;
        System.out.println("阿里云短信被加载" + smsMessage.toString());
    }

    @Override
    public void send(String sms) {
        System.out.println(smsMessage.toString() + "阿里云开始发送短信==》短信内容：" + sms);
    }
}
