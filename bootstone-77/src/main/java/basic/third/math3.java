package basic.third;
import java.util.Scanner;
public class math3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入消费金额：");
        double money = sc.nextDouble();
        if (money < 200) {
            System.out.println("请支付全款");
        }else if (money >= 200 && money < 600){
            System.out.println("可享受8.5折优惠");
        }else if (money >= 600 && money < 1000) {
            System.out.println("可享受7折优惠");
        }else if (money >= 1000){
            System.out.println("可享受6折优惠");
            }
        }
    }

