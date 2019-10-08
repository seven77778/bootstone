package work.utils;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    /**
     * 加一天
     * @return yyyy-MM-dd HH:mm:ss 格式
     */
    public static String addDay(String date){
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(date.trim(), df);
            LocalDateTime res = localDateTime.plusDays(1);
            return df.format(res);
        }catch (Exception e){
            BootStoneLog.bootStone.error("minHours error",e);
            return date;
        }
    }


    /**
     * 计算时间差
     */
    public void getResult(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println("计算两个时间的差：");
        LocalDateTime end = LocalDateTime.of(2019,9,18,19,55,55);
        Duration duration = Duration.between(now,end);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
        System.out.println(now);
        System.out.println(end);
        System.out.println(hours);
    }

    public static void main(String[] args) {
       String date = "2019-09-08 12:00:00";
        System.out.println(addDay(date));

    }

    /**
     * date转换localdatetime
     * 计算12小时时间差
     * @param begin
     * @param end
     * @return
     */
    public static boolean in12Hours(Date begin,Date end){

        LocalDateTime localDateTimeBegin = LocalDateTime.ofInstant(begin.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTimeEnd = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
        Duration duration = Duration.between(localDateTimeBegin,localDateTimeEnd);
        if(duration.toHours()<12){
            return true;
        }
        return false;
    }




}
