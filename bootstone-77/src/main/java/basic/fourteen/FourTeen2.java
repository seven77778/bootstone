package basic.fourteen;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lsh on 2019-08-19.
 * <p>
 * 上一个是不是太简单了哈，在第一个的基础是哪个，稍微改造下，再来一个
 * 增加了 注册、注销等功能
 */
public class FourTeen2 {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("liu", "123456");
        map.put("li", "222333");
        map.put("zhang", "password123");//这里依然是数据库的功能

        boolean flag = true;//只要flag是true，任何操作完成都会回到选择操作的页面，只有case4，让flag = false，不再循环，才会退出

        // 所以这里是while循环
        while (flag) {
            System.out.println("                         ");
            System.out.println("-----------------------------77个人管理系统---------------------------------");
            System.out.println("请选择操作 ：");
            System.out.println("\t\t1.注册用户\t\t2.登录");
            System.out.println("\t\t3.删除用户\t\t4.退出");
            System.out.println("-----------------------------——---------------------------------——————————");
            int i = (new Scanner(System.in)).nextInt();

            //根据用户输入的操作来进入不同的代码
            switch (i) {
                case 1:

                    System.out.println("请输入用户名：");
                    Scanner usernameScan = new Scanner(System.in);
                    String username = usernameScan.nextLine();
                    System.out.println("用户名为 ： " + username);

                    //输入密码
                    System.out.println("请输入密码：");
                    Scanner passwordScan = new Scanner(System.in);
                    String password = passwordScan.nextLine();
                    System.out.println("密码为  ： " + password);
                    //先检查用户名是否存在
                    if (null != map.get(username)) {
                        System.out.println("用户名已经存在，请重新注册");
                        break;
                    }
                    map.put(username, password);//这一步就是注册，把用户名+密码放到数据库（map）中，就是注册了
                    System.out.println("您已注册成功 ，用户名是 " + username + ",密码是 " + password);
                    break;
                case 2:

                    System.out.println("请输入用户名：");
                    Scanner usernameScan2 = new Scanner(System.in);//注意哈，变量名称后面都加了2，是和上面的变量区分开
                    String username2 = usernameScan2.nextLine();
                    System.out.println("用户名为 ： " + username2);

                    //输入密码
                    System.out.println("请输入密码：");
                    Scanner passwordScan2 = new Scanner(System.in);
                    String password2 = passwordScan2.nextLine();
                    System.out.println("密码为  ： " + password2);

                    if (map.get(username2) == null) {//map中取不到值，就代表没有这个用户，好理解吧
                        System.out.println("您输入的用户名不存在");
                    } else if (map.get(username2).equals(password2)) {
                        System.out.println("登录成功~~~~~~~~~~~~");
                    } else {
                        System.out.println("登录失败，请检查密码~~~~~~~~~~~~~");
                    }

                    break;
                case 3:
                    //删除用户，也是要用户先登录成功，才能删除自己

                    System.out.println("请输入用户名：");
                    Scanner usernameScan3 = new Scanner(System.in);//注意哈，变量名称后面都加了2，是和上面的变量区分开
                    String username3 = usernameScan3.nextLine();
                    System.out.println("用户名为 ： " + username3);

                    //输入密码
                    System.out.println("请输入密码：");
                    Scanner passwordScan3 = new Scanner(System.in);
                    String password3 = passwordScan3.nextLine();
                    System.out.println("密码为  ： " + password3);

                    if (map.get(username3) == null) {
                        System.out.println("您输入的用户名不存在");
                    } else if (map.get(username3).equals(password3)) {
                        System.out.println("登录成功");
                    } else {
                        System.out.println("登录失败，请检查密码");
                    }
                    System.out.println("是否确认删除，如果是请输入 Y，否请输入 N");
                    String deleteStr = (new Scanner(System.in)).nextLine();
                    if ("Y".equals(deleteStr)) {
                        map.remove(username3);//这一步就是删除，自己理解下，看看能不能想得通
                        System.out.println("你已经成功删除用户名  为 " + username3);
                    }



                    break;
                case 4:
                    flag = false;
                    System.out.println("您已退出");
                    break;
                default:
                    System.out.println("请您输入 1234中的选项，这是默认语句");
            }
        }


    }

}
