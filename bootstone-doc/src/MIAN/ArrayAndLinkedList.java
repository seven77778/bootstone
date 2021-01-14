import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lsh on 2020-08-05.
 *
 *
 * ArrayList和LinkedList哪个更占空间
 *
 * https://www.cnblogs.com/yeya/p/13430797.html
 *
 * ArrayList
 *
 */
public class ArrayAndLinkedList {


    @Test
    public void test(){
        /**
         * 在源码中，数据默认是从数组的第一个索引开始存储的，当我们添加数据时，
         * ArrayList会把数据填充到上一个索引的后面去，所以，ArrayList的数据都是有序排列的。
         * 而且，由于ArrayList本身是基于数组存储，所以查询的时候只需要根据索引下标就可以找到对应的元素，
         * 查询性能非常的高，这也是我们非常青睐ArrayList的最重要的原因。
         *
         * 插入是复制
         * 插入数据的时候，ArrayList的操作是先把3后面的数组全部复制一遍，然后将这部分数据往后移动一位，
         * 其实就是逐个赋值给后移一位的索引位置，然后3后面就可以空出一个位置，把4放入就完成了插入数据的操作了
         *
         * 删除的时候也是一样，指定index，然后把后面的数据拷贝一份，并且向前移动，这样原来index位置的数据就删除了
         */
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        System.out.println(arrayList.get(0));
    }

    @Test
    public void testLink(){

        /**
         * 和数组一样，LinkedList 也是一种线性数据结构，但它不像数组一样在连续的位置上存储元素，而是通过引用相互链接。
         */

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        System.out.println(linkedList.get(0));



    }




}
