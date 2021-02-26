package work.utilscollections;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 管道的功能包括：Filter（过滤）、Map(映射)、sort(排序）等
 */

public class ListToMapList {


    /**
     * 在一个list中，按照某种属性来分组
     */
    public static Map<String, List<Money>> listToMapList(List<Money> list) {
        return list.stream().collect(Collectors.groupingBy(Money::getName));
    }

    /**
     * list转map，去重
     * @param list
     * @return
     */
    public static Map<String, Money> listToMap(List<Money> list) {
        //list 转 map,list中的getname有重复值报错
        Map<String, Money> ss =
                list.stream().collect(Collectors.toMap(Money::getName, Function.identity(), (key, value) -> value));
        System.out.println(ss);
        return ss;
    }

    public static Set<String> listToSet(List<Money> list){
        Set<String> set =
                list.stream().map(Money::getName).collect(Collectors.toSet());
        System.out.println(set);
        return set;
    }


    @Test
    public void testSet(){
        List<Money> list = Lists.newArrayList();
        Money money1 = new Money("人民币", 10);
        Money money2 = new Money( "人民币", 99);
        Money money3 = new Money("比特币", 2);
        Money money4 = new Money("蚂蚁币", 6);
        list.add(money1);
        list.add(money1);
        list.add(money2);
        list.add(money3);
        list.add(money4);
        listToSet(list);
    }





    @Test
    public void test() {
        List<Money> list = Lists.newArrayList();
        Money money1 = new Money("人民币", 10);
        Money money2 = new Money("人民币", 99);
        Money money3 = new Money("比特币", 2);
        Money money4 = new Money("蚂蚁币", 6);
        list.add(money1);
        list.add(money1);
        list.add(money2);
        list.add(money3);
        list.add(money4);
        Map<String, List<Money>> res = listToMapList(list);
        System.out.println(res);

        HashSet<Money> set = new HashSet<>(list);
        System.out.println(set);//去重
        System.out.println(listToMap(list));
    }


    /**
     * 搞清楚
     */
    @Test
    public void testFinal() throws Exception{
        List<Money> list = Lists.newArrayList();

        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat2.parse("2020-12-22");
        Date date2 = dateFormat2.parse("2020-12-23");
        Date date3 = dateFormat2.parse("2020-12-24");
        Date date4 = dateFormat2.parse("2020-12-21");

        Money money1 = new Money("人民币", 10,date1);
        Money money2 = new Money("人民币", 99,date2);
        Money money3 = new Money("比特币", 2,date3);
        Money money4 = new Money("蚂蚁币", 6,date4);
        list.add(money1);
        list.add(money1);
        list.add(money2);
        list.add(money3);
        list.add(money4);

        //转map，也就是去重,sorted((x,y)->y.getPrice()-x.getPrice()) 倒序排列
        Map<String, Money> one = list.stream().sorted((x,y)->y.getPrice()-x.getPrice()).collect(Collectors.toMap(Money::getName, Function.identity(), (x, y) -> y));
        System.out.println(one);

        //转map，也就是去重,Comparator.comparing(Money::getDate)) 时间正序
        Map<String, Money> two = list.stream().sorted(Comparator.comparing(Money::getDate)).collect(Collectors.toMap(Money::getName, Function.identity(), (x, y) -> y));
        System.out.println(two);

        //转map，也就是去重,Comparator.comparing(Money::getDate)) 时间倒序，关键字reversed
        Map<String, Money> three = list.stream().sorted(Comparator.comparing(Money::getDate).reversed()).collect(Collectors.toMap(Money::getName, Function.identity(), (x, y) -> y));
        System.out.println(three);
    }
}
