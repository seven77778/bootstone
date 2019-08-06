package basic.eleven;

/**
 * Created by lsh on 2019-08-05.
 * 今天，来了解一下 sb 吧
 *
 */
public class Eleven {

    public static void main(String[] args) {

        /**
         * key: sb StringBuilder
         * importance : normal
         * usage ： 代替 String，其实跟String基本一样
         */
        String str1 = "123";
        String str2 = "456";
        String str3 = str1 + str2;
        System.out.println("str3是 ： " + str3);//最普通的字符串拼接的方法，就是 + 号

        //但是sb就不用加号，就不
        StringBuilder sb = new StringBuilder();
        sb.append("123");
        sb.append("456");
        sb.append("我是sb");
        System.out.println("sb是 ： " + sb);

        /**
         * 区别来了
         * 1. String的加号和sb的 append，都是拼接字符串，append的效率要高于+号
         * 2. String是一个类，StringBuilder也是一个类，都是代表字符串的类，而且 StringBuilder通过toString方法可以转换成String类
         */
        String newStr = sb.toString();//这样sb就成了String

        //然后是Stringbuffer
        StringBuffer sb2 = new StringBuffer();
        sb2.append("123");
        sb2.append("567");
        System.out.println("大家好，我是StringBuffer " + sb2);
        System.out.println("我也可以变成String " + sb2.toString());

    }

}
