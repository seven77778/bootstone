package com.lsh.demo.basic.lambda;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * Created by lsh on 2019/1/30.
 * <p>
 * lambda 筛选集合数据
 */
public class LambdaTest {
    List<Integer> list = new ArrayList<Integer>() {
        {
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }
    };

    List<Integer> newList = Lists.newArrayList(1, 2, 3, 4, 5, 6);

    @Test
    public void test1() {

        /*
         * 找到第一个大于1的数据
         */
        Optional<Integer> list1 = list.stream().filter(x -> x > 1).findFirst();
        list1.ifPresent(System.out::print);

        System.out.println();
        /*
         * 将大于1 的数据重新组合
         */
        List<Integer> list2 = list.stream().filter(x -> x > 1).collect(Collectors.toList());
        list2.forEach(System.out::print);

    }

    /**
     *
     */
    @Test
    public void test2() {
        long num = list.stream().filter(Objects::nonNull).count();
        System.out.println(num);
        nonNull(list);

        System.out.println(123);

        /*
        使用lambda 进行list中元素的更改 todo
         */
//        list.stream().allMatch().collect(Collectors.toList());
    }

    public void replace(int x) {
        x = x + 1000;
    }


    //******************

    /**
     *  filter 筛选list的数据
     */
    private List<Integer> mylist = Lists.newArrayList(1,2,3,4,5);

    @Test
    public void test4(){
        System.out.println(mylist);
        List<Integer> list2 = mylist.stream().filter(a -> a > 2).collect(Collectors.toList());
        System.out.println(list2);
    }

    @Test
    public void  test5(){



    }


    public static void main(String[] args) {
        String ss = "015019aaa      ";
        String regEx="\\d";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ss);
        System.out.println( m.replaceAll("").trim());

    }

}
