package com.lsh.demo.basic.datastructrue.list;

import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lsh on 2020-05-21.
 *
 * list 转换 map
 *
 * result.stream().collect(Collectors.toMap(FhCikGuestInfo::getPmsOrderId,
 * FhCikGuestInfo::getName,(key1,key2)->key2));
 *
 */
public class ListToMap {


    @Test
    public void testFunction(){
        List<User> list = Lists.newArrayList();

        list.add(new User("abc","12"));
        list.add(new User("bcd","12"));
        Map<String, User> guestInfoMap = list.stream().collect(
                Collectors.toMap(User::getName, Function.identity(),(e1,e2)->e1));
        System.out.println(guestInfoMap);
    }

    @Test
    public void testListToMap(){
        List<User> list = Lists.newArrayList();

        list.add(new User("aaa","12"));
        list.add(new User("aaad","11"));
        list.add(new User("ccc","13"));
        //有重复的name会报错
        Map<String,String> map = list.stream().collect(Collectors.toMap(User::getName,User::getAge));
        System.out.println(map);

        //Function.identity() 还是失败 map重复key
        Map<String, User> guestInfoMap = list.stream().collect(
                Collectors.toMap(User::getName, Function.identity(),(e1,e2)->e1));
        System.out.println(guestInfoMap);
    }

    /**
     * 可以重复，归为一个值
     */
    @Test
    public void testListToMap2  (){
        List<User> list = Lists.newArrayList();

        list.add(new User("aaa","12"));
        list.add(new User("ccc","11"));
        list.add(new User("ccc","13"));
        Map<String,String> map = list.stream().collect(
                Collectors.toMap(User::getName,User::getAge,(e1,e2)->e1));
        System.out.println(map);
    }

    @Data
    public class User{
        private String name;
        private String age;

        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }

}
