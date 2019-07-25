package basic.fourth;

/**
 * Created by lsh on 2019-07-25.
 * java运算符(算术运算符 + 逻辑运算符)
 *
 */
public class Fourth {

    /**
     * ①算术运算符
     *
     * + - * / 加减乘除 不做赘述
     * % 取余
     * ++ 自增 自己本身加 1
     * -- 自减 自己本身减 1
     *
     *
     * ② 逻辑运算符
     *
     *
     *
     */
    public static void main(String[] args) {

        System.out.println(5 % 2);// 5 除以 2 等于2 余1，所以结果为1
        System.out.println(5 % 12);//结果是5
        System.out.println(5 % 10);//结果是5

        int a = 5;
        int b = 5;
        //比较烦人的 a++ 和 ++a
        System.out.println(a++);//结果是5，because a++ ，加号在后面，就先 取 a的值，然后在让a+1
        System.out.println(a);//结果是6，接上一条，a++过后，a=a+1，所以a=6
        System.out.println(++b);//结果是6，加号在前面，先进行 b=b+1，此时b=6
        System.out.println(b);//结果是6，原因见上


    }

}
