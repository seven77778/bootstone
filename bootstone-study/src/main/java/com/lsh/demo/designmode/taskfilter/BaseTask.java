package com.lsh.demo.designmode.taskfilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsh on 2020-05-27.
 */
public abstract class BaseTask<Q extends BaseRq, P extends BaseRs> {
    private List<BaseFilter<Q, P>> filters = new ArrayList();


    protected void beforeExecute(Q request, P response) throws Throwable {
        System.out.println("this is baseTask beforeExecute");
    }

    public void execute(Q request, P response) {
        try {
            this.beforeExecute(request, response);
            boolean saveResult = false;
            int n = this.filters.size();

            for(int i = 0; i < n; ++i) {
//                ((BaseFilter)this.filters.get(i)).setSourceMsg(this.getLinkid());
                saveResult = ((BaseFilter)this.filters.get(i)).execute(request, response);
                if (saveResult) {
                    break;
                }
            }

            this.afterExecute(request, response);
        } catch (Throwable t) {
            response.setCode("1001");
            response.setMsg("system error");
        }

    }

    protected void afterExecute(Q request, P response) throws Throwable {
        System.out.println("this is baseTask afterExecute");
    }


}
