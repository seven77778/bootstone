package work.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lsh on 2020-07-03.
 * <p>
 * 判断是周几
 */
public class SomeDayOfWeekUtils {

    public static String dayForWeek(String pTime) throws Throwable {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = format.parse(pTime);
        Calendar cal = Calendar.getInstance();
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        try {
            cal.setTime(tmpDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

    public static void main(String[] args) throws Throwable {
        String a = dayForWeek("2020-07-03");
        System.out.println(a);
    }

}
