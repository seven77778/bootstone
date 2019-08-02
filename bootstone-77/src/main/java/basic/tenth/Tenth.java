package basic.tenth;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by lsh on 2019-08-02.
 *
 * today，let us learn about Date
 */
public class Tenth {

    public static void main(String[] args) throws Exception{
        /**
         * key : Date
         * importance : normal
         *
         * target：看到认识即可
         *
         * java中的日期类，说实话，比较混乱，也是最开始的设计者没弄好，反正看见Date，LocalDate什么的，都是代表日期
         *
         */

        //缩写是 sout 哦
        System.out.println("比较老版本的日期写法 ： " + new Date());

        System.out.println("新版本日期写法：" + LocalDateTime.now());

        // 打印以后，是不是发现日期 虽然都是当前时间，但是格式不同对吧，也侧面说明了，这里确实设计的不好，但是我们有方法
        System.out.println("输出指定格式日期 ： " + LocalDateTime.of(2019,8,2,12,30,0));
        //新版本日期中，总是带着一个 T ，想办法搞掉
        String date1 = LocalDateTime.of(2019,8,2,12,30,0).toString();//基本上大多数类都有 toString方法，意思是转换成String类型
        System.out.println("使用replace方法替换T 以后的日期格式：" + date1.replaceAll("T"," "));
        /**
         * replace 是 String 自带的方法，String类型可以直接使用
         */
        String str = "ABC";
        System.out.println(" 将str 中的A 替换成777 ： " + str.replace("A","777"));

        /**
         * java自带的计时器，计算经历多少毫秒
         */
        long time1 = System.currentTimeMillis();//得到当前的毫秒数，这个毫秒数是从1970年开始到现在经历了多少毫秒
        System.out.println("现在的毫秒数是 ： " + time1);
        Thread.sleep(40);//这一句的意思是，让当前程序 睡眠 40 毫秒
        long time2 = System.currentTimeMillis();
        System.out.println("从刚才到现在经历了 多少毫秒： " + (time2 -time1));//用途是 在程序的开头，定义一个time1，在结尾定义一个time2，以此来简单的查看自己的代码耗时多久

        /**
         * 思考，试试LocalDateTime 还有其他的什么方法，自己写几个时间看看
         */
    }

}
