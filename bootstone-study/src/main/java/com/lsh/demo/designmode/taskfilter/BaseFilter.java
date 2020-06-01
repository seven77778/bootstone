package com.lsh.demo.designmode.taskfilter;

/**
 * Created by lsh on 2020-05-27.
 */
public class BaseFilter<Q, P> {

    public <Q extends BaseRq, P extends BaseRs> boolean execute(Q request, P response) {

        return false;
    }
}
