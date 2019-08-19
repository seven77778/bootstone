package basic.fourteen;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lsh on 2019-08-19.
 *
 * 今天写了一个 模拟登录的 小程序，通过用户输入的用户名 和 密码 来验证  密码是否正确
 * map用来充当数据库的角色
 * 数据库就是一个专门存放数据的地方，咱们玩LOL，登录的时候验证的用户名密码，也都是在lol 的数据库中
 *
 *
 */
public class FourTeen {


    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();//尖括号中的<String,String> 被称为 泛型，这样写，map的key 和 value都只能是String了
        map.put("liu","123456");
        map.put("li","222333");
        map.put("zhang","password123");
        //map存放了三个用户的用户名，liu li zhang，和他们分别对应的密码，这就是相当于数据库了
        /**
         * 关于这个map，map是一个key对应一个value，key不能重复，value可以重复
         * 所以map特别适合来存放 用户名 - 密码，
         * ①用户输入的用户名，先根据这个用户名去map里面寻找，有没有这个key，如果没有，就是未注册用户
         * ②如果有这个key，说明用户存在，再根据用户传入的密码，去map里面看看密码能不能对的上
         */

        //还记得吧，从控制台输入数据，输入用户名
        System.out.println("请输入用户名：");
        Scanner usernameScan = new Scanner(System.in);
        String username = usernameScan.nextLine();
        System.out.println("用户名为 ： " + username);

        //输入密码
        System.out.println("请输入密码：");
        Scanner passwordScan = new Scanner(System.in);
        String password = passwordScan.nextLine();
        System.out.println("密码为  ： " + password);

        if(map.get(username) == null){
            System.out.println("您输入的用户名不存在");
        }else if (map.get(username).equals(password)){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败，请检查密码");
        }


    }

}
