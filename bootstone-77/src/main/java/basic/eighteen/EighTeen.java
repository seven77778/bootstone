package basic.eighteen;

import org.junit.Test;

/**
 * Created by lsh on 2019-08-27.
 *
 * 18
 *
 * public 是一个【访问修饰符】，访问权限，比如有一些机密的核心代码，设置为protected，其他包的代码不能访问，因为
 * 开发都是分模块开发的，保不齐其他人 会不会想调通你的代码，所以有一些代码，就会用修饰符来控制一下，当然大部分代码都是public
 *
 * public 是公共的，不同的类、不同的包 都可以访问
 * 不同的包就是 eighteen 和 seventeen 就是不同的包，包存在的意义就是 整理归纳，将相同功能、类似的类 放在一起
 *
 * 其他【访问修饰符】还有 private protect default
 *
 * private ： 私有的，java中访问权限最小的，只允许 本类中访问，（u are my private and me too）
 * protected ： 受保护的，介于public 和 private 之间的一种访问修饰符，被其修饰的类、
 * 　　　　　属性以及方法只能被类本身的方法及子类访问，即使子类在不同的包中也可以访问
 * default ： default就是没有修饰符，不是 修饰符的位置 写 【default】，默认的，同包访问
 *
 * 不需要去背这些，就是以后看到的类 或者 方法 ，并不都是public开头的，也可以说private，protect，知道是控制访问权限的就OK！
 *
 * 复习一下其他的 try catch 捕获异常，让程序继续往下走
 * extends 是继承，implements 是实现，Exception是异常，list是add元素，map是put 一组key和value
 * new int【5】是初始化一个长度为5的数组，if else if 可以有很多个else if
 * switch case 类似 if else，怎么写都行
 * && 逻辑与 || 逻辑或
 *
 *
 */
public class EighTeen {

    //凡是有public出现的地方，都可以替换为private 啊 protected啊，对代码含义影响不大，就是访问权限不同

    public String name;

    private String adress;

    protected String email;

    //一个私有的，无参数，有返回值的方法，必须return 一个String
    private String get(){
        return "";
    }


    /**
     * CPU是中央处理器，一般的电脑都是4核8线程
     * 4核呢就是物理四核，这里的物理是实际存在的，8线程是 逻辑上的，就是一个核当两个核用（可以不用理解）
     *
     * 再来说 CPU，CPU是很专一的，虽然电脑看起来能同时做很多事情，比如一遍听歌，一遍打着LOL，一遍还开着迅雷在下载东西，
     * 有没有想过，CPU是如何运作的呢？
     * 今天，李老师带你走进CPU~
     *
     * CPU哈，是非常专一，同一时刻只能做一件事情，咱们几乎所有的指令（双击网易云，单机鼠标等等）都要传给CPU
     * CPU有个技术叫做 【分时技术】，就是CPU不停的在不同的任务之间切换，切换的速度非常快，一个任务 可能几十微秒 就处理完了
     * 所以咱们无感知，以为CPU同时干了好多事情，其实CPU只能干一件事
     *
     * CPU不停切换的任务，其实在java中叫做 【线程】，还有一个概念叫做 【进程】
     * 这样说，整个qq是一个进程，你用qq和老王聊天，是一个【线程】，和 弟弟聊天，有是一个【线程】，用qq发送图片，又是一个
     * 【线程】，在java里，线程就是一段代码，一个任务
     *
     * 不理解没关系，看下面的代码，方法 test1 就是一个线程，Thread 就是线程
     *
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception{
        System.out.println("当前线程的名称 ： " + Thread.currentThread().getName());
        //那就在看一点好玩的吧
        Long time1 = System.currentTimeMillis();//获取当前时间，毫秒
        Thread.sleep(3000); // 让这个线程 睡眠 3000 毫秒，作用就是 比如你用atm存钱，当atm数钱的时候，程序就会睡眠，等待一会
        Long time2 = System.currentTimeMillis();//获取当前的时间，time2 减去 time1 就是运行这段代码的时间

        System.out.println("耗时为 " + (time2 - time1));
    }

    @Test
    public void test2(){
        //线程还可以修改名字
        Thread.currentThread().setName("我是77的小线程");
        System.out.println(Thread.currentThread().getName());
    }


}
