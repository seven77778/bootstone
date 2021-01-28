package work.utilscollections;

import org.assertj.core.util.Lists;
import org.junit.Test;

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
        Money money4 = new Money("蚂蚁币", 6,"2");
        list.add(money1);
        list.add(money1);
        list.add(money2);
        list.add(money3);
        list.add(money4);
        //只要price的list<String>,status为1的

        List<Integer> rs = list.stream().filter(x -> "1".equals(x.getStatus())).map(Money::getPrice).distinct().collect(Collectors.toList());
        System.out.println(rs);


    }

}
