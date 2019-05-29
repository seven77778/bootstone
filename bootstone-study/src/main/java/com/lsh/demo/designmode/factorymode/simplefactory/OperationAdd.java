package com.lsh.demo.designmode.factorymode.simplefactory;

/**
 * Created by lsh on 2019-05-27.
 */
public class OperationAdd extends Operation {
    @Override
    protected double getResule() {
        return getValue1() + getValue2();
    }


    public static void main(String[] args) {

    }
}

