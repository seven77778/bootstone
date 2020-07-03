package com.lsh.demo.designmode.observer.demo1;

/**
 * Created by lsh on 2020-07-02.
 * 十六进制
 */
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}