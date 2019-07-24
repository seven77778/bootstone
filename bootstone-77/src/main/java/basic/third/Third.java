package basic.third;

/**
 * Created by lsh on 2019-07-24.
 *
 * java变量和修饰符
 *
 */
public class Third {

    static String all="我是类变量，我在这个类中所有地方都生效";    // 类变量，在方法之外，static修饰

    String str="hello world";  // 实例变量，在方法之外，但是没有加 static

    public void method(){
        int i =0;  // 局部变量，在某个方法中，它的生效范围也只在这个方法中
        System.out.println(str);//str 可以在非static方法中使用
    }

    public static void main(String[] args) {

        System.out.println(all);//类变量，哪里都能用
//        System.out.println(str);//报错，str 虽然是全局的变量，但是不能在static的方法中使用
//        System.out.println(i);//更是报错，因为 i 只是 method 方法中的一个变量

    }

}
