package basic.eighth;

import java.util.Scanner;

/**
 * Created by lsh on 2019-07-3022.
 */
public class Game1 {

    /**
     * 猜数字游戏：假使目标数字是147，使用while循环实现控制台的多次输入，才对后终止程序
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请在下一行输入数字");
            int x = scanner.nextInt();
            if(x == 147){
                System.out.println("恭喜你猜对了，答案是147");
                break;
            }
            System.out.println("没猜到，请继续");
        }
    }
}
