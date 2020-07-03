package com.lsh.demo.designmode.observer.demo1;

/**
 * Created by lsh on 2020-07-02.
 * 八进制
 */
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
