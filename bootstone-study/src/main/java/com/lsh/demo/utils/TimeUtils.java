package com.lsh.demo.utils;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lsh on 2019/4/3.
 */
public class TimeUtils {


    /**
     *
     * @param date yyyy/M/d HH:mm:ss
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(String date){
        if(StringUtils.isBlank(date)){
            return null;
        }
        try {
            LocalDateTime time = LocalDateTime.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy/M/d HH:mm:ss"));
            return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }catch (Exception e){
            BootStoneLog.bootStone.error("datetime error" +date,e);
        }
        return null;
    }



    @Test
    public void test1(){
        String date = "2019/04/30 04:39:00";
        System.out.println(formatDate(date));
    }

}
