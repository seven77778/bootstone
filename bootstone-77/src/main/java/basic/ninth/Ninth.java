package basic.ninth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsh on 2019-07-31.
 *
 * lalala,今天来看 java集合，一种类似于数组，而又比数组常用的东东
 *
 *
 */
public class Ninth {

    public static void main(String[] args) {
        /**
         * key : list
         * importance : high
         * usage ：java中最常用的，用来存放一组数据，有序，而且可以进行增删改
         *
         */
        //一种最常用的list
        List list = new ArrayList();
        // 定义一个list，然后变量名 . add,可以添加任意类型的数据
        list.add(1);
        list.add("hello77");
        list.add(1.5f);
        System.out.println("list的值为： " + list);
        //还可以改变某个值
        list.set(0,"把第一位的数据改了");
        System.out.println("修改list后 为： " + list);
        //还可以删除指定位置上的数据，序号还是从0开始
        list.remove(0);
        System.out.println("删除第一位以后的list为： " + list);

        //list也可以使用for循环来遍历 list.size()代表list有几个数据，也就是要循环几次
        for (int i=0;i<list.size();i++){
            System.out.println("for循环遍历list ："+ list.get(i));
        }


        /**
         * key : map
         * importance : high
         * usage :java中最常用的 键值对，也是用来存储数据啊什么的，无序，这是和list的区别
         * 格式：map.put(key,value) ,就是给每一个value都起个名字，通过名字找到值
         * put是往map里面塞数据，get是取数据
         *
         */
        Map map = new HashMap();
        map.put("我是String类型的key1","我是String类型的value");
        map.put("key2",1.5f);
        map.put(1,123);
        System.out.println("先看下添加了3个数据的map： " + map);

        System.out.println("map取单个值示例1: "+ map.get("我是String类型的key1"));//取值的时候，key是什么，就一模一样的写
        System.out.println("map取单个值示例2：" + map.get(1));//不是序号哦，而且key是1，value是123的那组数据，map是没有顺序的
        //同样，map也可以删除某个键值对，删掉的是一组数据，key 和 value都删了
        map.remove("key2");
        System.out.println("删除了一组数据后的map ： " + map);


        /**
         * 思考题，map.put("key1","value1");
         * 再来一个 map.put("key1","value2");
         * 两次put的key都是key1，请问这时候取出的对应的值是多少呢，have a try
         */
    }

}
