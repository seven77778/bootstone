package basic.third;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class math6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int b = 0;
        System.out.println("请输入数字：");
        x = b = sc.nextInt();
        int count = 0;//计数器
        List list = new ArrayList();//暂时存储

        do {
            count++;
            int x2 = ++x;
            b = b + x2;

            list.add("第 " + count + "次的自然数为 "+ x2);

        } while (b < 100);
        System.out.println("自然数为" + x + ",此时的总和为 ： " + b+",过程为 "+list);

        sc.close();


        //固定用法，正则表达式
        String line = "This order was placed for QT! OK?";
        String pattern = "(\\d+)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        Matcher mac = r.matcher(line);

        System.out.println(mac.find());

    }
}
