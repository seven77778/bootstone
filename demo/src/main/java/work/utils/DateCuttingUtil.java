package work.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 按天切割时间
 */
public class DateCuttingUtil {

    public static List<String> cutDate(String dateType, String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dBegin = sdf.parse(start);
            Date dEnd = sdf.parse(end);
            return findDates(dateType, dBegin, dEnd);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static List<String> findDates(String dateType, Date dBegin, Date dEnd) throws Exception {
        List<String> listDate = new ArrayList<>();
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        while (calEnd.after(calBegin)) {
            /**
             *
             */
            switch (dateType) {
                case "M":
                    calBegin.add(Calendar.MONTH, 1);
                    break;
                case "D":
                    calBegin.add(Calendar.DAY_OF_YEAR, 1);break;
                default:break;
            }
            if (calEnd.after(calBegin)) {
                listDate.add(new SimpleDateFormat("yyyy-MM-dd").format(calBegin.getTime()));
            } else {
                listDate.add(new SimpleDateFormat("yyyy-MM-dd").format(calEnd.getTime()));
            }
        }
        return listDate;
    }

    public static void main(String[] args) {
        String start = "2016-02-01";
        String end = "2016-03-02";
        List<String> list = DateCuttingUtil.cutDate("D", start, end);
        for (String str :list){
            System.out.println(str);
        }
    }
}
