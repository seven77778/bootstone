package basic.seventh;

/**
 * Created by lsh on 2019-07-29.
 *
 * 循环结构进阶用法
 */
public class Seventh2 {

    public static void main(String[] args) {
        /**
         * key：while用法2
         * importance：low
         * while 循环的变异用法，看到认识即可，不需要掌握
         *
         * 用途：1.可能是某个菜鸟程序员 不小心写出来的bug（emmmm），一般很少主动去写while循环，因为很容易成为“死循环”（一直执行，不会停）
         * 2.高频的定时任务，比如像百度这样的公司，需要一直检测自己的网页是不是挂了，可以一直循环访问www.baidu.com
         *
         * 用法：
         * 下面的do{}while(); 也是固定格式，意思是先不管while的条件了，丫的先执行一次再说，如果while后面的条件为true，就一直循环
         * 循环到天荒地老，如果while后面的条件为假，就不继续执行了
         *
         * 最初a=5，所以a>10为假，下面大括号中的语句只会执行一次
         * 把 a 改成一个比10大的数字，你会发现，程序会一直执行
         * Tips ： 程序一直执行的话，被叫做 “死循环”。死循环的时候，电脑干不了其他事情，需要手动点击红色方块才能停止，所以测试完，记得再把a的值改小
         */
        int a= 5;
        do{
            System.out.println("不管下面 while括号后面的条件是真还是假，我都会执行一次！此时a =" + a + ",下面的条件为" +(a>10));
        }while (a > 10);


        /**
         * key：for循环
         * importance ： high
         *
         * 下面来学习一下 if 语句的变异写法，看到认识即可,先自己回忆一下 正常的 if()else{} 用法哈
         * 区别是：if else 是判断真假；for循环 主要是控制循环次数
         * 用途：while不太好控制要循环几次，for循环可以明确的规定 循环几次（一般来说，i小于几，就是循环几次）
         *
         * 固定格式是 for开头，后面跟括号
         * 括号中，分三段，第一段 定义一个变量，一般都是 int i =0，
         * 第二段 ：一个Boolean表达，用来结束循环的，这个布尔为假的时候，循环就停止了
         * 第三段：也是固定写法，i++,意思是每次循环都加上1
         *
         * Tips : 把第二段的 i 改成 10
         */
        for(int i =2; i < 5; i++){ //
            System.out.println("我执行了 " + i +"次，i = " + i);
        }

        /**
         * key： for 循环变异用法
         * importance ： low
         * 记得有一天咱们大概说过数组吧，你还问 数组的变量名称为什么是 arr
         * 数组的英文翻译是 array，缩写为arr（哈哈，我刚查的翻译）
         *
         * 用途：循环一个数组中所有的变量，数组中有多少个数字，就循环多少次
         * 缺点：不能自己控制要循环多少次，只能根据数组中国元素的数量(for 正常用法中，i小于几，就是循环几次)
         */
        int [] array = {1,4,7,8,9};
        for( int x : array){

            System.out.println("数组的元素 的值分别有 " + x);
        }

        /**
         * ********************************
         * key ： break，continue
         * importance ： normal + low
         *
         * 到此处，java基本的循环 写法都了解了，已经覆盖了 99%的场景，下面学习两个和 循环相关的关键字
         * 1.break -- 字面意思 ： 中断，打断，跳出循环
         * 2.continue -- 字面意思：继续，作用是让程序立刻跳转到下一次循环 （这个比较难理解，我好长一段时间都不会用，所以啦，dont worry ）
         */
        /**
         * break 是停止运行，条件符合就停止，比较容易理解
         */
        for(int i =0;i<4;i++){
            if(i == 2){
                System.out.println("i 等于2了，我喜欢小七七，下面是一句break，程序停止");
                break;
            }
            System.out.println("这里是break的用法 i = " + i);
        }

        /**
         * continue 不会停止程序，循环还会继续，但是会跳出当前这一次，重新开始下一次循环，比如下面的例子是
         * 我要循环4次，但是第2次的时候，不要执行
         *
         * 观察 下面的输出，“这里是continue的用法”开头的语句，少了 i=2的结果
         */
        for(int i =0;i<4;i++){
            if(i == 2){
                System.out.println("i 等于2了，下面是一句continue，程序会跳过本次");
                continue;
            }
            System.out.println("这里是continue的用法 i = " + i);
        }


    }

}
