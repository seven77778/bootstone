package work.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 未验证的
 */
public class DateCut2 {


    /**
     * 分割时间工具类
     * 按照
     * 指定月份周期
     * 将
     * 两日期 拆分区间
     *
     */
      /*  public static String getDateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
       }*/

    /**
     * 返回时间节点
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param cycle     周期
     * @return
     */
    public static List<Date> getDateNode(Date startDate, Date endDate, int cycle) {
        Calendar start = getCalendar(startDate);
        //月份差
        int monthPoor = getMonthPoor(startDate, endDate);
        //分组次数
        int count = monthPoor / cycle;
        //如果不能被整除
        if (monthPoor % cycle != 0) {
            count += 1;
        }
        List<Date> list = new ArrayList<Date>();
        list.add(startDate);
        for (int i = 0; i < count; i++) {
            //递增周期月
            start.add(Calendar.MONTH, +cycle);
            startDate = start.getTime();
            //如果开始日期是在结束日期之后
            boolean flag = startDate.after(endDate);
            if (flag) {
                list.add(endDate);
            } else {
                list.add(startDate);
            }
        }
        return list;
    }

    /**
     * 返回一个日历的实例
     *
     * @param date
     * @return
     */
    private static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 计算日期之间的月份差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private static int getMonthPoor(Date startDate, Date endDate) {
        Calendar startCalendar = getCalendar(startDate);
        Calendar endCalendar = getCalendar(endDate);
        //年份差
        int yearPoor = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        //月份差
        int monthPoor = yearPoor * 12 + (endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH));
        //日差判断TODO
        if (endCalendar.get(Calendar.DAY_OF_MONTH) < endCalendar.get(Calendar.DAY_OF_MONTH)) {
            monthPoor -= 1;
        }
        return monthPoor;
    }

    /**
     * 计算日期之间的天数差
     */
    public static Long getDayPoor(Date startDate, Date endDate) {
        LocalDate startDateLocal = dateToLocalDate(startDate);
        LocalDate endDateLocal = dateToLocalDate(endDate);
        return endDateLocal.toEpochDay() - startDateLocal.toEpochDay();
    }

    /**
     * Date转LocalDate
     *
     * @param date
     * @return
     */
    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


}
