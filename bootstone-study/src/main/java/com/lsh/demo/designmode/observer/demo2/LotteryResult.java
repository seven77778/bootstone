package com.lsh.demo.designmode.observer.demo2;

import lombok.Data;

import java.util.Date;

/**
 * Created by lsh on 2020-07-02.
 */
@Data
public class LotteryResult {

    private String uid;
    private String msg;

    public LotteryResult(String uid, String msg) {
        this.uid = uid;
        this.msg = msg;
    }

    public LotteryResult(String uid, String msg, Date date) {
        this.uid = uid;
        this.msg = msg;
        System.out.println(date+"-" + uid);
    }
}
