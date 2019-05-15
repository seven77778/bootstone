package com.lsh.demo.basic.datastructrue.list;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lsh on 2018/11/9 11:33.
 *
 * @author lsh
 * @date 2018/11/09
 */
public class ArraylistTest {

    /**
     * 创建list的5种方式
     */
    @Test
    public void test(){

        List[] lists= new List[5];
        /**
         * guava
         * 最简洁
         */
        lists[0] = Lists.newArrayList(1,"abc");

        /**
         * src 最普通方式
         */
        lists[1] = new ArrayList();
        lists[1].add(1);
        lists[1].add("abc");

        /**
         *匿名内部类
         * 1.匿名内部类 效率上的损失
         * 2.静态内部类持有所在外部类的引用。如果需要将 List 返回给到其他地方使用，可能造成内存泄漏
         *
         * 在Java中内部类的定义与使用一般为成员内部类与匿名内部类，他们的对象都会隐式持有外部类对象的引用，影响外部类对象的回收。
         GC只会回收没有被引用或者根集不可到达的对象（取决于GC算法），内部类在生命周期内始终持有外部类的对象的引用
         造成外部类的对象始终不满足GC的回收条件，反映在内存上就是内存泄露
         */
        lists[2]= new ArrayList(){
            {
                add(1);
                add("abc");
            }
        };

        /**
         * java8 Stream
         */
        lists[3] = Stream.of(1,"abc").collect(Collectors.toList());

        /**
         * Arrays.aslist 固定长度
         * lists[4].add(3) --> UnsupportedOperationException
         * aslist 返回的arraylist是自己的一个静态内部类，没有add方法
         *
         * Arrays.asList 的参数如果是基本类型的数组时，需要留意返回值可能和你预期的不同
         */
        lists[4] = Arrays.asList(1,"abc");

        lists[4].add(3);
        System.out.println(lists[4]);


        for(int i=0;i<5;i++){
            System.out.println(lists[i]);
        }

    }

    /**
     * Arrays.asList 的参数如果是基本类型的数组时，需要留意返回值可能和你预期的不同
     * @return List<int[]> 遍历加泛型。。
     */
    @Test
    public void test2(){
        int[] intarry = new int[]{1,2,3};
        List<int[]> list = Arrays.asList(intarry);

        Integer[] integers = new Integer[]{1,2,3};
        List list1 = Arrays.asList(integers);

        System.out.println(list1);//[1, 2, 3]
        //遍历数组
        int[] a = new int[]{1};
        System.out.println(a[0]);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }

       //List<int[]> 如何遍历
        for(int[] i:list){
            for(int j :i){
                System.out.println("list<int[]>遍历的结果为"+j);
            }
        }
    }

    /**
     * Lists.newArrayList()不允许初始化单独的null
     */
    @Test
    public void test3(){
        //List list = Lists.newArrayList("abc",1);
        int[] intarr={1,2,3};
        List list1 = Lists.newArrayList(intarr,null,3);
        System.out.println(list1);

        System.out.println(Ints.saturatedCast(1));
    }

    /**
     * new ArrayList()初始化过程
     */
    @Test
    public void test4(){
        List list = new ArrayList();
        list.add(1);
    }










}
