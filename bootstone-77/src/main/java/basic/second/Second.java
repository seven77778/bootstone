package basic.second;

/**
 * Created by lsh on 2019-07-24.
 *
 * java基本类型
 *
 * 基本类型，一共8种，常用的也就是int，double，boolean
 * String不属于基本数据类型，它是一个类
 * 但String却是平时最常用的表示字符串的方法
 *
 */
public class Second {

    public static void main(String[] args) {

        //整数系列 4个
        //8位 -128 ~ 127
        byte a = 1;
        //16位 -2^15 ~ 2^15 -1
        short b = 2;
        //32位
        int c = 3;
        //64位
        long d = 77L;

        //小数系列 2个,float  和 double区别是，可以支持的小数点多少位不相同，double支持的位数多，一般用double即可
        float e = 1.2f;
        double f = 5.6d;

        //布尔类型,只有 true 或者 false
        boolean g = true;

        //char类型，表示单独的一个字符，不常用
        char h = 'A';
        char p = 'b';

        //一些其他操作

        //可以这样写，三个变量一起定义，都是int
        int d1 = 3, e1 = 4, f1 = 5;

        /***********************************************/

        //String 不属于基本数据类型
        String str = "today is a good day";
        String name = "77";
        String sss = "77 is a lovely girl 就哈哈";

        /**
         * 数组
         */
        int [] arr = new int[]{5,2,8};
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

    }

}
