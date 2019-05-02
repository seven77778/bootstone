package work.utils;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Created by lsh on 2019-04-23.
 *
 * 往数据库写time，加'' string
 */
public class DateUtils {


    /**
     * 时间格式化，默认 yyyy-MM-dd HH:MM:ss
     */
    public static String format(String date){
        Objects.requireNonNull(date, "date");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            System.out.println(localDateTime);
            return formatter.format(localDateTime);
        }catch (Exception e){
            BootStoneLog.bootStone.error("format error",e);
            return date;
        }
    }


    /**
     * 时间格式化
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


    /**
     * 获取两个日期之间 相隔的小时数
     * yyyy-MM-dd HH:mm:ss 格式
     *
     * Duration 还可以取 日 时分秒
     */
    public static long countHours(String stratDate,String endDate){

        long result =0;
        try {
            LocalDateTime l1 = LocalDateTime.parse(stratDate.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime l2 = LocalDateTime.parse(endDate.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            java.time.Duration duration = java.time.Duration.between(l1,  l2);
            result = duration.toHours();
        }catch (Exception e){
            BootStoneLog.bootStone.error("diffHours error",e);
        }
        return result;

    }

    /**
     * 减一个小时
     * @return yyyy-MM-dd HH:mm:ss 格式
     */
    public static String minHours(String date){
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(date.trim(), df);
            LocalDateTime res = localDateTime.minusHours(1);
            return df.format(res);
        }catch (Exception e){
            BootStoneLog.bootStone.error("minHours error",e);
            return date;
        }
    }

    public static void main(String[] args) {
        String date = "2109-04-26 15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(localDateTime);
        String res = formatter.format(localDateTime);

        System.out.println(res);
    }


}
