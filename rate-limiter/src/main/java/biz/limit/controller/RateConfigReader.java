package biz.limit.controller;

import biz.limit.RateConfig;
import org.assertj.core.util.Lists;

import java.util.List;

public class RateConfigReader {


    /**
     * memory-qps
     * memory-num
     * redis-num
     * redis-qps
     */
    private static List<RateConfig> configs = Lists.newArrayList(
            new RateConfig(1,"memory-qps","",5,false),
            new RateConfig(2,"memory-num","",3,false),
            new RateConfig(3,"redis-num","",2,false),
            new RateConfig(4,"redis-qps","",7,false),
            new RateConfig(5,"redis-qps","",5,false)
    );

    public static List<RateConfig> getConfig(){
        return configs;
    }

}
