package basic.sixth;

/**
 * Created by lsh on 2019-07-26.
 *
 * 666来了，那来个好玩的类吧，数学类！
 * 本节看看就好~
 *
 *
 */
public class Sixth {

    public static void main(String[] args) {
        //平时定义个数字都是用int ，double，或者long
        int a=0; long b = 123L;
        /**
         * 其实java本身自带了一个Math.java,意思是数学类，实际用途很广泛的，比如
         * 用java开发的计算器，或者建筑上，测绘上，都要用的这个数学类
         *
         * 这个类的用法都是固定的，也不需要特意去记住，看到了认识就行，需要用的的时候去百度一下
         */
        System.out.println("90 度的正弦值：" + Math.sin(Math.PI/2));
        System.out.println("0度的余弦值：" + Math.cos(0));
        System.out.println("60度的正切值：" + Math.tan(Math.PI/3));
        System.out.println("1的反正切值： " + Math.atan(1));
        System.out.println("π/2的角度值：" + Math.toDegrees(Math.PI/2));
        System.out.println(Math.PI);// π 的值，3.1415926，后面记不住啦
        System.out.println(Math.pow(2,4));     ////计算a的b次方


        /**
         * Math  是一个类，计算正弦，平方数 等等 都是这个类中的方法
         * 引申一下，int double 等等也是有对应的 实体类的，称之为 “包装类”，int叫做基本类型，Integer是int的包装类
         * int - Integer
         * long - Long   大小写，类的首字母都是大写的
         * byte - Byte
         * double - Double
         * float - Float
         * short - Short
         *
         * boolean - Boolean
         * char - Character
         *
         * 怎么理解 基本类型 和对应的 包装类
         * 1.其实使用起来是一样的
         * 2.不同的是 包装类比基本类型多了一些功能
         *
         * 有一个大类 叫 Number ，所有的包装类（Integer、Long、Byte、Double、Float、Short）都是抽象类 Number 的子类。
         * （什么是子类 ？？ 后面再聊）
         */

        int h =1;
        Integer hh= 1;
        System.out.println( "int 和 Integer 用法基本一样 " + (h == hh) );
        //下面是Integer 类的一些方法
        System.out.println("Integer 可以表示的最大值 "+Integer.MAX_VALUE);
        System.out.println("Integer 可以表示的最小值 "+Integer.MIN_VALUE);

        System.out.println(Integer.max(4,8));//比较大小，最终结果是较大的值
        System.out.println(Integer.min(4,8));//比较大小，最终结果是较小的值


        /**
         * 下面是一些常用的数学公式，还是Math 类的方法
         *
         *Math.sqrt()//计算平方根
         *Math.cbrt()//计算立方根
         *Math.pow(a, b)//计算a的b次方
         *Math.max( , );//计算最大值
         *Math.min( , );//计算最小值
         */

        System.out.println(Math.sqrt(16));   //4.0
        System.out.println(Math.cbrt(8));    //2.0
        System.out.println(Math.pow(3,2));     //9.0
        System.out.println(Math.max(2.3,4.5));//4.5
        System.out.println(Math.min(2.3,4.5));//2.3

        /**
         * abs求绝对值
         */
        System.out.println(Math.abs(-10.4));    //10.4
        System.out.println(Math.abs(10.1));     //10.1

        /**
         * ceil天花板的意思，就是返回大的值
         */
        System.out.println(Math.ceil(-10.1));   //-10.0
        System.out.println(Math.ceil(10.7));    //11.0
        System.out.println(Math.ceil(-0.7));    //-0.0
        System.out.println(Math.ceil(0.0));     //0.0
        System.out.println(Math.ceil(-0.0));    //-0.0
        System.out.println(Math.ceil(-1.7));    //-1.0

        /**
         * floor地板的意思，就是返回小的值
         */
        System.out.println(Math.floor(-10.1));  //-11.0
        System.out.println(Math.floor(10.7));   //10.0
        System.out.println(Math.floor(-0.7));   //-1.0
        System.out.println(Math.floor(0.0));    //0.0
        System.out.println(Math.floor(-0.0));   //-0.0

        /**
         * random 取得一个大于或者等于0.0小于不等于1.0的随机数
         */
        System.out.println(Math.random());  //小于1大于0的double类型的数
        System.out.println(Math.random()*2);//大于0小于1的double类型的数
        System.out.println(Math.random()*2+1);//大于1小于2的double类型的数

        /**
         * rint 四舍五入，返回double值
         * 注意.5的时候会取偶数    异常的尴尬=。=
         */
        System.out.println(Math.rint(10.1));    //10.0
        System.out.println(Math.rint(10.7));    //11.0
        System.out.println(Math.rint(11.5));    //12.0
        System.out.println(Math.rint(10.5));    //10.0
        System.out.println(Math.rint(10.51));   //11.0
        System.out.println(Math.rint(-10.5));   //-10.0
        System.out.println(Math.rint(-11.5));   //-12.0
        System.out.println(Math.rint(-10.51));  //-11.0
        System.out.println(Math.rint(-10.6));   //-11.0
        System.out.println(Math.rint(-10.2));   //-10.0

        /**
         * round 四舍五入，float时返回int值，double时返回long值
         */
        System.out.println(Math.round(10.1));   //10
        System.out.println(Math.round(10.7));   //11
        System.out.println(Math.round(10.5));   //11
        System.out.println(Math.round(10.51));  //11
        System.out.println(Math.round(-10.5));  //-10
        System.out.println(Math.round(-10.51)); //-11
        System.out.println(Math.round(-10.6));  //-11
        System.out.println(Math.round(-10.2));  //-10
    }
}
