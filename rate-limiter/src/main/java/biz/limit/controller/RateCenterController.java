package biz.limit.controller;

import biz.limit.RateConfig;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateCenterController  extends AbstractConfiger implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //使用try catch全部包裹，不能影响项目正常启动
        try{
            // after init success
            if (event.getApplicationContext().getParent() != null) {
                return;
            }
            // init config
            List<RateConfig> configs = RateConfigReader.getConfig();



        }catch (Exception e){
            BootStoneLog.bootStone.error("rate-limit init error!!!!!!!!!!!!!!!!!!");
        }



    }

    @Override
    protected void afterAdd() {

    }

    @Override
    protected void afterDelete() {

    }
}
