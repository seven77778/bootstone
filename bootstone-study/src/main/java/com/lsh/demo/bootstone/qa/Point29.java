package com.lsh.demo.bootstone.qa;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by lsh on 2019/2/12.
 *
 * 29个问题
 *
 *
 *
 *
 */


public class Point29 {

    /**
     * ==：比较的是存放在栈中对象的堆地址，比较两个变量中存储的对象地址是否相同， 即是否是同一个对象
     * 1）比较操作符两端是否是同一对象
     * 2）两边的操作数必须是同一类型才能编译通过 -- String也可以使用==，但是不推荐
     * 3）比较的是地址，如果是基本数据类型，则比较值
     * 但是如果没有重写的话， 则返回的是==的判断结果
     * 另外==的比较效率比equals高
     */
    @Test
    public void test1(){
        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1 == o2);
        System.out.println(Objects.equals(o1, o2));
        System.out.println(o1.equals(o2));
    }

    /**
     *hashcode 和 equals 区别和联系
     */
    @Test
    public void test2(){

    }

    /**
     * -ArrayList内部采用动态数组的方式实现了List的数据结构
     * -更适合于查询操作
     *
     * -解：arraylist是一个可变长数组，插入数据时，先将原始数据复制到一个新的数组（留出了要插入元素的位置），
     * 将要插入的元素赋值即可
     *
     * -LinkedList内部采用了 循环双向链表 数据结构实现List的数据结构
     * -既然是双向链表，那么它的顺序访问会非常高效，而随机访问效率比较低
     * -更适合于增删改操作
     *
     * -linkedlist插入元素，只需要改变 链表中对应两个节点之间的引用关系，
     * 而新数据被封装成一个新的节点，之前的两个节点指向这个新节点即可
     *
     *
     * todo
     */
    @Test
    public void test3(){
        List<Integer> arrList = new ArrayList<>(16);
        LinkedList<Integer> linList = new LinkedList<>();
        linList.addFirst(1);
    }

    /**
     * 转发（forward）和重定向（redirect）
     */
    @Test
    public void test4(){

    }
}
