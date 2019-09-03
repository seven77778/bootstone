package basic.twelve;

import org.junit.Test;

/**
 * Created by lsh on 2019-08-07.
 * <p>
 * 2019年8月7日 今日七夕 跟你在一起真好！
 * <p>
 * 今天我们学习解决异常
 * <p>
 * 如果明天是场意外，你会不会来~预备唱~
 */
public class Twelve {


    /**
     * key : Exception 意外，异常 try catch finally
     * importance : low
     * usage : 虽然不用刻意去记住这种异常，但是异常会经常出现在程序中
     */
    public static void main(String[] args) {

        //运行下面的语句
        int[] arr = {1, 2, 3};
        System.out.println(arr[99]);
        /**
         * 输出结果应该是：
         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 99
         * 	at basic.twelve.Twelve.main(Twelve.java:26)
         *
         * 	1.ArrayIndexOutOfBoundsException
         * 	使用有道翻译一下 ： 数组索引越界异常
         * 	所有的 异常都是 Exception结尾，前面的单词代表它是什么异常
         *
         * 	显而易见，数组越界了，我们的数组只有3个，但是程序中要取 arr[66] ，我们当然没有这么多元素，于是就抛出异常
         *  而且这一类异常，有一个学名 叫做“运行时异常”，就是你这样写，写的时候不会报错，但是 运行以后就报错，
         *
         * (写的时候就报错的，idea编译器会标红告诉你的)
         *
         * 下面请运行 test1，方法前面有个三角符号，点击运行（三角符合变成红色，就是代表上一次运行这个方法失败了，不用关心 三角是绿色还是红色）
         *
         */

        System.out.println("***********************我是分割线******************************");


    }

    @Test
    public void test1() {
        StringBuilder sb = null;//先想想这个sb和昨天的sb有什么区别？
        //运行一下
        sb.append("123");
        System.out.println(sb);

        /**
         * java.lang.NullPointerException
         * 	at basic.twelve.Twelve.test1(Twelve.java:54)
         *
         * 	结果应该是这样的吧
         * 	NullPointer,空指针，java中最广泛，最常见的，杀伤力最强，尤其是新手程序猿最头痛的！
         * 	继续看上面的问题，为什么会抛出nullpoint呢，因为StringBuilder 是一个类
         * 	想使用这个类，就必须有一个对象，java是通过new来产生对象的，所以一般的写法都是
         * 	StringBuilder sb = new StringBuilder（）；
         * 	上面是 sb = null，null就是空，肯定不能使用Stringbuilder的方法了
         *
         * 	那你肯定会想，为什么会有 StringBuilder sb = null 这种写法呢，请看 test2
         */
    }

    @Test
    public void test2() {
        StringBuilder sb = null;//先定义一个sb，但是没初始化，没初始化就给它一个null，这时候sb是si的

        sb = new StringBuilder();//这一步是sb的初始化，也叫做赋值吧，反正就是sb活了

        sb.append(123);//一个正常的sb，可以进行各种操作
        System.out.println(sb);

        /**
         * 运行完是不是发现，正常了
         * 定义一个变量，先让它等于null，然后在后面的某一步在给它赋值 （sb = new xxx）
         * 也是平时一种常见的写法
         */
    }

    @Test
    public void test3() {
        System.out.println(123 / 0);
        System.out.println("~！！！请注意我哦，上面抛出了异常，我就不会执行了");

        /**
         * java.lang.ArithmeticException: / by zero
         *
         * 数学常识类的异常，除数不能为0
         *
         * 总之呢，异常还有很多，但是没必要去记住，碰见了 xxxException，然后根据前面的单词 大概就能知道是什么问题了
         * 再看一眼，上面第二行的sout，它没有执行吧，因为第一行抛出了异常，后面的程序就都不执行了，这样不好吧，程序还是要
         * 继续运行下去
         * 下面就来讲怎么搞定这些异常
         */

    }


    @Test
    public void test4() {
        /**
         * try catch 来了，它就是异常克星！
         */

        try {
            System.out.println(100 / 0);
        } catch (ArithmeticException e) {// 还记得 test3 中抛出了什么异常吧，我们这里就来捕获它
            System.out.println("我捕获了一个异常 ，它是 " + e.toString());//这个e，就异常，把它打印出来看看
        }

        System.out.println("~！！！请注意我哦，我是会执行了，因为上面有 try catch 捕捉了异常，程序会继续进行");
    }


    @Test
    public void test5() {
        /**
         *与test4 唯一的区别在 catch 的括号中，上次是 ArithmeticException，这是是？
         */

        try {
            System.out.println(100 / 0);
        } catch (Exception e) {// 但是我通常这样写，Exception 是所有异常的父类，是它们的爸爸，擒贼先擒王~you know~
            System.out.println("①我捕获了一个异常 ，它是 " + e.toString());//这个e，就异常，把它打印出来看看
        }

        System.out.println("②请注意我哦，我是会执行了，因为上面有 try catch 捕捉了异常，程序会继续进行");
    }

    @Test
    public void test6() {
        /**
         *与test4 唯一的区别在 catch 的括号中
         */

        try {
            System.out.println(100 / 0);
        } catch (Exception e) {// 但是我通常这样写，Exception 是所以异常的父类，是它们的爸爸，擒贼先擒王，逮到巴巴就行
            System.out.println("①我捕获了一个异常 ，它是 " + e.toString());//这个e，就异常，把它打印出来看看
        } finally {
            System.out.println("②七七你好，我叫 finally代码块，我的作用就是，不管你们上面怎么折腾，我是一定会运行的");
        }

        System.out.println("③请注意我哦，我是会执行了，因为上面有 try catch 捕捉了异常，程序会继续进行");
    }

    @Test
    public void test7() {
        /*
        请直接运行，看输出
         */
        int[] array = {0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1,
                0, 0, 1, 1, 4, 5, 2, 3, 4, 1, 0, 1,

                0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0};

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < array.length; i++) {
            if (i % 7 == 0)
                sb.append("  \n");
            if (array[i] == 0)
                sb.append("   ");
            else if (array[i] == 4)
                sb.append("  ");
            else if (array[i] == 5)
                sb.append(" I ");
            else if (array[i] == 2)
                sb.append("Lvoe ");
            else if (array[i] == 3)
                sb.append("77");

            else
                sb.append("  " + "**");

        }
        System.out.println(sb);


        for (int i = 0, k = 0; i < 14; i++) {// 打印行

            // 上部分 上分为 四个部分

            if (i < 3) {

                for (int j = 0; j < 5 - 2 * i; j++) {// 1、空心

                    System.out.print(" ");

                }

                if (i == 2) {// 2、*

                    for (int j = 0; j < 6 + 4 * i - 1; j++) {

                        System.out.print("*");

                    }

                    for (int j = 0; j < 7 - 4 * i + 2; j++) {// 3、空心

                        System.out.print(" ");

                    }

                    for (int j = 0; j < 6 + 4 * i - 1; j++) {// 4、*

                        System.out.print("*");

                    }

                } else {

                    for (int j = 0; j < 6 + 4 * i; j++) {// 2、*

                        System.out.print("*");

                    }

                    for (int j = 0; j < 7 - 4 * i; j++) {// 3、空心

                        System.out.print(" ");

                    }

                    for (int j = 0; j < 6 + 4 * i; j++) {// 4、*

                        System.out.print("*");

                    }

                }

            } else if (i < 6) {// 中间

                for (int j = 0; j < 29; j++) {

                    System.out.print("*");

                }

            } else {// 下部分 6

                if (i == 13) {

                    for (int j = 0; j < 2 * (i - 6); j++) {// 打印空格

                        System.out.print(" ");

                    }

                    System.out.print("*");

                } else {

                    for (int j = 0; j < 2 * (i - 6) + 1; j++) {// 打印空格

                        System.out.print(" ");

                    }

                    for (int j = 1; j < 28 - 4 * k; j++) {

                        System.out.print("*");

                    }

                    k++;

                }

            }

            System.out.println();// 换行


        }
    }

    @Test
    public void test77() {
        String wordsString = "刘会婷我爱你！";
        char[] wordsArray = wordsString.toCharArray();//将要表白的语句转为字符数组
        int tempWord;//用于临时存放每个字符的ASCII码
        int tempWordLength = 0;
        String tempWords = "";//用于临时存放每个字符的二进制表示形式
        String codeWords = "";//编码之后的表白语句
        for (int i = 0; i < wordsArray.length; i++) {
            tempWord = wordsArray[i];//获取当前字符的ASCII码
            tempWords = Integer.toBinaryString(tempWord);
            tempWordLength = tempWords.length();
            if (tempWordLength < 8) {
                //为美观起见，每个字符的二进制表示形式均限制为8位
                for (int j = 0; j < 8 - tempWordLength; j++) {
                    tempWords = "0" + tempWords;
                }
            }
            codeWords = codeWords + tempWords + " ";
            tempWords = "";//将当前二进制字符归空
        }
        System.out.println(codeWords);
    }

    @Test
    public void test777(){
        System.out.println(BinstrToStr("111001000110001 111010100011111 110110100111011 111001000110001 00110111 00110111"));
    }

    private String BinstrToStr(String binStr) {
        String[] tempStr = StrToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    // 将初始二进制字符串转换成字符串数组，以空格相隔
    private String[] StrToStrArray(String str) {
        return str.split(" ");
    }

    // 将二进制字符串转换为char
    private char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    // 将二进制字符串转换成int数组
    private int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

}
