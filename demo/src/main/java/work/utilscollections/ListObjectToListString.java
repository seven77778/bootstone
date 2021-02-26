package work.utilscollections;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import work.utils.DateBootStoneUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将list对象中某个属性重新组装为list，并且过滤某些条件
 * distinct() 可以去重
 *
 * */
public class ListObjectToListString {

    @Test
    public void ObjectListToStringList(){
        List<Money> list = Lists.newArrayList();
        Money money1 = new Money("人民币", 10,"1");
        Money money2 = new Money( "人民币", 99,"1");
        Money money3 = new Money("比特币", 2,"0");
        Money money4 = new Money("", 6,"1");
        list.add(money1);
        list.add(money1);
        list.add(money2);
        list.add(money3);
        list.add(money4);
        //只要price的list<String>,status为1的

        List<Integer> rs = list.stream().filter(x -> "1".equals(x.getStatus())).map(Money::getPrice).distinct().collect(Collectors.toList());
        System.out.println(rs);

        // 获取status为1，而且name不为空的
        List<Integer> rs2 = list.stream().filter(x -> "1".equals(x.getStatus()) && StringUtils.isNotBlank(x.getName())).map(Money::getPrice).distinct().collect(Collectors.toList());
        System.out.println(rs);
    }

    /**
     * list中对象有起止日期，判断哪个日期间隔最长
     */
    @Test
    public void findLongestDate()throws Exception{
        List<Money> list = Arrays.asList(
                new Money("haha","2020-01-01","2020-01-05"),
                new Money("haha","2020-01-01","2020-01-09"),
                new Money("haha","2020-01-01","2020-01-02")
        );

        list.forEach(x->{
            System.out.println(x.getName());
            System.out.println(DateBootStoneUtils.countDays(x.getBegin(),x.getEnd()));
        });

        list.sort((x,y)-> DateBootStoneUtils.countDays(y.getBegin(),y.getEnd()) - DateBootStoneUtils.countDays(x.getBegin(),x.getEnd()));

        System.out.println(list.get(0));
    }


    /**
     * test removeall
     */
    @Test
    public void removeList(){
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        List<Integer> list2 = Lists.newArrayList();
        list2.add(1);
        list.removeAll(list2);
        System.out.println(list);

    }

}
