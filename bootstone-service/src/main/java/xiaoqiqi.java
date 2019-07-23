import org.junit.Test;
import java.util.Scanner;


public class xiaoqiqi {

    static String name = "xiaoqiqi";
    static int age = 18;
    static int xiaoaiqiage = 18;
    static int xiaohenghengage = 18;
    static double priceCount = 9.5d;
    static double priceCount2 = 88;

    static char name1 = 'A';
    static char name2 = 'd';
    static char name3 = 'c';

    static String aaa = "A";

    public static void main(String[] args) {
        System.out.println("hello world," + name + "her age is " + age);

        System.out.println("结果为 ： " + add(xiaohenghengage,5));
        System.out.println("结果为 ： " + add(priceCount2,2));
        System.out.println("结果为 ： " + add(priceCount2,2));
        System.out.println("结果为 ： " + add(priceCount2,2));


        System.out.println(1 != 1);

        System.out.println((1==1) || (2==3));

        System.out.println(false || false);
        int a =1;
        int b =1;
        int c = (a++);
        int d = ++b;
        System.out.println(c);
        System.out.println(d);
    }



    public static int add(int a,int b){
        return a+b;
    }

    public static double add(double a,int b){
        return a+b;
    }

}
