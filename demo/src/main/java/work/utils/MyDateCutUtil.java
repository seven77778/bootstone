package work.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.dao.vo.PriceDate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 已验证
 */
@Component
public class MyDateCutUtil {

    /**
     * 日期的拆分和组装
     */
    public static List<PriceDate> splitAndPackage(List<PriceDate> allReadyDates, List<PriceDate> priceDates) {

        //最终结果
        List<PriceDate> resultList = Lists.newArrayList();

        //使用map，防止日期重复
        Map<String, PriceDate> dateMap = Maps.newConcurrentMap();
        for (PriceDate priceDate : allReadyDates) {
            List<PriceDate> findDates = findDates(priceDate);
            findDates.forEach(x -> {
                dateMap.put(x.getBeginDate() + "-" + x.getEndDate(), x);
            });
        }

        //传入的价格日期
        for (PriceDate pd : priceDates) {
            PriceDate priceDate = new PriceDate();
            priceDate.setBeginDate(pd.getBeginDate());
            priceDate.setEndDate(pd.getEndDate());
            priceDate.setPrice(pd.getPrice());
            List<PriceDate> findDates = findDates(priceDate);
            findDates.forEach(x -> {
                dateMap.put(x.getBeginDate() + "-" + x.getEndDate(), x);
            });
        }

        //将map转为list，没有重复的日期，这个是一天一天的价格
        List<PriceDate> mapToList = new ArrayList<>(dateMap.values());
        //排序
        listSort(mapToList);
        //按照价格分组
        Map<Integer, List<PriceDate>> priceGroupMap = mapToList.stream().collect(Collectors.groupingBy(PriceDate::getPrice));
        for (Map.Entry<Integer, List<PriceDate>> entry : priceGroupMap.entrySet()) {
            Integer k = entry.getKey();
            List<PriceDate> v = entry.getValue();
            Object[] dateArr = v.stream().map(PriceDate::getBeginDate).toArray();
            Date beginDate = (Date) dateArr[0];
            Date endDate = null;
            for (int i = 0; i < dateArr.length; i++) {
                long ms = -1;
                if (i > 0) {
                    Date d1 = (Date) dateArr[i];
                    Date d2 = (Date) dateArr[i - 1];
                    ms = d1.getTime() - d2.getTime();
                }
                if (ms > 1 * 24 * 60 * 60 * 1000) {
                    PriceDate sing = new PriceDate();
                    sing.setPrice(k);
                    sing.setBeginDate(beginDate);
                    sing.setEndDate(null != endDate ? endDate : beginDate);
                    resultList.add(sing);
                    beginDate = (Date) dateArr[i];
                    endDate = null;
                } else if (ms == 1 * 24 * 60 * 60 * 1000) {
                    endDate = (Date) dateArr[i];
                }
            }
            PriceDate out = new PriceDate();
            out.setPrice(k);
            out.setBeginDate(beginDate);
            out.setEndDate(null != endDate ? endDate : beginDate);
            resultList.add(out);
        }

        System.out.println(resultList);
        return resultList;

    }


    public static List<PriceDate> findDates(PriceDate rate) {
        List<PriceDate> priceDates = Lists.newArrayList();
        //第一天的日期加上
        PriceDate firstRate = new PriceDate();
        firstRate.setPrice(rate.getPrice());
        firstRate.setBeginDate(rate.getBeginDate());
        firstRate.setEndDate(rate.getBeginDate());
        priceDates.add(firstRate);
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(rate.getBeginDate());
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(rate.getEndDate());
        while (calEnd.after(calBegin)) {
            PriceDate priceDate = new PriceDate();
            calBegin.add(Calendar.DAY_OF_YEAR, 1);
            if (calEnd.after(calBegin)) {
                priceDate.setBeginDate(calBegin.getTime());
                priceDate.setEndDate(calBegin.getTime());
            } else {
                priceDate.setBeginDate(calEnd.getTime());
                priceDate.setEndDate(calEnd.getTime());
            }
            priceDate.setPrice(rate.getPrice());
            priceDates.add(priceDate);
        }
        return priceDates;
    }


    /**
     * 重新排序方法
     *
     * @param list
     */
    private static void listSort(List<PriceDate> list) {
        list.sort((o1, o2) -> {
            if (o1.getBeginDate().after(o2.getBeginDate())) {
                return 1;
            } else {
                return -1;
            }
        });
    }


    public static void main(String[] args) throws Exception {
        List<PriceDate> allReadyDates = Lists.newArrayList();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date start1 = dateFormat.parse("2020-12-01");
        Date end1 = dateFormat.parse("2020-12-05");

        Date start2 = dateFormat.parse("2020-12-10");
        Date end2 = dateFormat.parse("2020-12-15");


        PriceDate priceDate1 = new PriceDate();
        priceDate1.setPrice(66);
        priceDate1.setBeginDate(start1);
        priceDate1.setEndDate(end1);
        allReadyDates.add(priceDate1);

        PriceDate priceDate2 = new PriceDate();
        priceDate2.setPrice(88);
        priceDate2.setBeginDate(start2);
        priceDate2.setEndDate(end2);
        allReadyDates.add(priceDate2);


        List<PriceDate> priceDates = Lists.newArrayList();
        Date start3 = dateFormat.parse("2020-12-02");
        Date end3 = dateFormat.parse("2020-12-06");

        Date start4 = dateFormat.parse("2020-12-11");
        Date end4 = dateFormat.parse("2020-12-16");

        PriceDate priceDate3 = new PriceDate();
        priceDate3.setPrice(999);
        priceDate3.setBeginDate(start3);
        priceDate3.setEndDate(end3);
        priceDates.add(priceDate3);

        PriceDate priceDate4 = new PriceDate();
        priceDate4.setPrice(999);
        priceDate4.setBeginDate(start4);
        priceDate4.setEndDate(end4);
        priceDates.add(priceDate4);

        List<PriceDate> list = splitAndPackage(allReadyDates, priceDates);
        System.out.println(list);
    }

}
