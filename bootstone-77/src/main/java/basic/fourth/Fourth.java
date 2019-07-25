package basic.fourth;

/**
 * Created by lsh on 2019-07-25.
 * java运算符(算术运算符 + 关系运算符 + 逻辑运算符)
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
     * ② 关系运算符
     * == 判断左右两边的数字是否相等
     * != 不等于
     * > >= < <= 见字知意
     *
     *
     * ③ 逻辑运算符
     * && 和，and，如果&& 两边都是true，结果为true
     * || 或，or，如果两边有一个是true，结果就是true，两边都是false
     * ! 取反，true 是真，!true 就是假
     *
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

        //下面是逻辑运算符
        System.out.println("**********下面是逻辑运算符 ***************");
        System.out.println(!true);
        System.out.println(true && true);
        System.out.println(false && false);
        System.out.println(false && true);
        System.out.println(( 1>0) && (3>2));//怎么写都ojbk，hahaha

        //还有一些常用的
        int c = 5;

        System.out.println();

    }

}
