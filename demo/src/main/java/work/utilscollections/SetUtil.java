package work.utilscollections;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.List;
import java.util.Set;

/**
 * 1.利用set对list去重
 */
public class SetUtil {

    /**
     * 利用set去重，money name相同的，也保留下来了
     * @param list
     * @return
     */
    public static List<Money> setRepetList(List<Money> list) {
        Set<Money> set = Sets.newSet();
        set.addAll( list );
        List<Money> moneyList = Lists.newArrayList();
        moneyList.addAll( set );
        return moneyList;
    }


    /**
     * 利用set去重，money name相同的，也保留下来了
     * @param list
     * @return
     */
    public static List<Money> setRepetListForEach(List<Money> list) {
        Set<Money> set = Sets.newSet();
        list.forEach( x->{
            set.add( x );
        } );
        List<Money> moneyList = Lists.newArrayList();
        moneyList.addAll( set );
        return moneyList;
    }


    @Test
    public void testSet() {
        List<Money> list = Lists.newArrayList();
        Money money1 = new Money( "人民币", 10 );
        Money money2 = new Money( "人民币", 99 );
        Money money3 = new Money( "比特币", 2 );
        Money money4 = new Money( "蚂蚁币", 6 );
        Money money5 = new Money( "蚂蚁币", 6 );
        list.add( money1 );
        list.add( money1 );
        list.add( money2 );
        list.add( money3 );
        list.add( money4 );
        list.add( money5 );
        List<Money> rs = setRepetList( list );
        List<Money> rs2 = setRepetListForEach( list );
        System.out.println(  rs );
        System.out.println(  rs2 );
    }

}
