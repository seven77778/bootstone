package com.lsh.demo.overwrite;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by lsh on 2019-04-29.
 *
 * why to override hashcode & equals
 *
 * 不重写，equals 是fasle，重写后 true，但是hashcode不同
 */
public class WhyHashCode {


    @Data
    public static class A{
        private String name ;
        private int age;

        //todo 内部类不能重写 equals hashcode ? 好像只是不提示

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof A){
                A b = (A)obj;
                if(b.age==this.age && b.name.equals(this.name) && b.hashCode()==this.hashCode()){
                    System.out.println("this is override");
                    return true;
                }
            }
            return false;
        }

        /**
         * 所以如果我们对equals方法进行了重写，建议一定要对hashCode方法重写，
         *
         * 以保证相同的对象返回相同的hash值，不同的对象返回不同的hash值。
         * @return
         */
        @Override
        public int hashCode() {
            return this.name.hashCode() ^ this.age;
        }
    }

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        a1.setAge(18);
        a2.setAge(18);
        a1.setName("haha");
        a2.setName("haha");

        System.out.println(a1.equals(a2));
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
        Map<String,A> map = Maps.newHashMap();

        map.put("a1",a1);
        map.put("a2",a2);

        System.out.println(map.get("a1"));

        map.forEach((k,v)-> System.out.println(k+" " +v));

        //hashset 无重复
        HashSet<Object> hashset = new HashSet<>();
        hashset.add("123");
        hashset.add("123");
        hashset.add("1234");
        hashset.forEach(System.out::println);
        System.out.println(hashset.size());

        //使用hashset测试A
        hashset.add(a1);
        hashset.add(a2);
        //不重写hashcode，4个，hashset内部也是map，map put 就要看 hashcode
        System.out.println(hashset.size());




    }


}
