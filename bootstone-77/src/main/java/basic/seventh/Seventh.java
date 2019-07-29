package basic.seventh;

/**
 * Created by lsh on 2019-07-29.
 *
 * Lucky Number 7777777
 *
 * java循环结构
 *
 */
public class Seventh {

    public static void main(String[] args) {

        /**
         * 先运行一下 TryIt ，哈哈哈，是不是停不下来了
         * 右上角，更新代码 箭头 的左边，有一个红色正方形，是停止
         *
         * OJBK，下面步入正题
         *
         * while 循环，java中最常用的循环，看见此关键字，知道是循环就行
         *
         */

        int a = 0;
        while (a < 10){
            System.out.println("每次循环a都加上1，a的值等于 " + a++);
        }

        /**
         * 如上图示，固定写法
         * while (){
         * }
         *
         * while开头，括号中是一个 boolean表达式，为真，就一直执行后面的语句，为假，就不执行
         * TryIt 里面，是写死的 true，所以会一直执行
         * 上面这个例子，先让a = 0，每次执行语句都给a 加1，条件 是a小于10，所以执行个10次，a就大于10了，就停止了
         *
         */


    }

}
