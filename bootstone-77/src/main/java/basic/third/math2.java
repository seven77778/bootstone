package basic.third;
import ch.qos.logback.core.joran.conditional.ElseAction;

import java.util.Scanner;
public class math2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入奖号：");
        int num = in.nextInt();
//        if (num == 1) {
//            System.out.println("一等奖，奖品是42寸彩电");
//        } else if (num == 2) {
//            System.out.println("二等奖，奖品是光波炉");
//        } else if (num == 3) {
//            System.out.println("三等奖，奖品是加湿器");
//        } else if (num == 4) {
//            System.out.println("四等奖，奖品是16G-U光盘");
//        } else {
//            System.out.println("没有中奖");
//        }


        if (num == 1) {
            System.out.println("1");
        }
        if (num == 2) {
            System.out.println("2");
        }
        if (num == 3) {
            System.out.println("3");
        }
        if (num == 4) {
                System.out.println('4');
        }
        if (num < 1 || num > 4){
        System.out.println("no");}
    }
}