package basic.eighth;

import java.util.Arrays;

/**
 * Created by lsh on 2019-07-30.
 *
 * 888,这是要发啊~大家好，我是八斗~
 */
public class Eighth {


    public static void main(String[] args) {
        /**
         * key : 数组 []
         * importance : normal
         * function ：存放一组数据的，可以是int，double，char
         * usage：1.int [] arr = {1,2,3,4,5}，有多种写法，关键 看到 []  ，认识到它是数组即可
         * 2.数组是有顺序的，数组的计数从0开始，arr[0] 是1，arr[4] 是5
         *
         * tips: [] 也可以放在arr后面，但是习惯放在arr前面
         *
         */

        int []arr = {1,2,3,4,5};
        int []arr2 = new int[]{1,2,3,4,5};//和上面效果一样
        double []arr3 = {1d,2.2d,5.6d};
        char []arr4 = {'A','C','z'};
        String []arr5 = {"love 77","77 is xinqiu","lalalala","77sb","77小仙女"};

        System.out.println("int数组的长度 " + arr.length);
        System.out.println("数组是有顺序的，String数组的第4位是 " + arr5[4]);

        /**
         * 下面复习 for循环 + 数组
         */
        for(int x :arr){
            System.out.println("循环遍历arr数组 "+x);// 这个操作一般叫做： 遍历数组，遍历，就是拿到这个数组所有的值
        }

        for(String x :arr5){ //注意观察和上面的区别
            System.out.println("循环遍历String数组 "+x);// int数组可以遍历，String也可以遍历
        }

        /**
         * 以下超纲内容
         * key : 二维数组 [][]
         * importance : low
         * function ： 数组中嵌套数组,看到两个 [] ，认识即可
         * usage ： 挺少的，没怎么用过，emmm
         */
        int [][] arrrr = {{1,2},{5,6,7},{8,9}};


        /**
         * 以下内容也超纲
         *
         * key : 数组排序
         * importance ： low
         *
         * 思考：有一组数 ，5，78，9，4，99，1
         * 怎么用最快的速度 把它们按照从小到大排列？
         *
         */

        int []brr = {5,78,9,4,99,1};
        //第一种，java本身提供了一个工具，Arrays.sort()固定用法
        Arrays.sort(brr);
        for(int x : brr){
            System.out.println("Arrays.sort排序过后的顺序 ：" + x);
        }

        // 第二种：冒泡排序,先不写，回头再说吧，emmm
        // 冒泡排序就是重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成


    }


}
