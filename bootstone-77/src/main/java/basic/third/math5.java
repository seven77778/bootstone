package basic.third;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class math5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int b = 0;
        do{
            System.out.println("请输入数字：");
            x = sc.nextInt();
            b = b + x;
            x++;
            System.out.println("自然数为" + x);
            System.out.println("b=" + b);
        }while (b < 100);
        sc.close();
        List list = new ArrayList();
    }
}
