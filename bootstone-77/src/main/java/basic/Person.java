package basic;

/**
 * Created by lsh on 2019-07-23.
 *
 * 一个类，代表人类
 *
 *  public class xxx（类的名字） 后面跟一个中括号{}
 *  这是所有java的类型的统一格式
 *
 *  类是java最小的单位，所有的代码都在类中，什么是类，类是对一类事物的描述，是抽象的。
 *  比如定义一个 Person 类
 *
 */
public class Person {


    /**
     * 该人类的各种属性，包括姓名，性别，年龄 等等
     *
     * 这就是在方法外面定义的，本类中，所有方法里面都有效
     */

    String name = "lsh";
    String sex = "男";
    int age = 18;
    String address = "范县";
    String email = "xxx@163.com";
    boolean shifouxihuanchishi = false;

    /**
     * 这是一个不带参数的方法
     *
     * 1.java类中的方法，public 是固定的开头，意思是 一个公共的方法，其他的类中也可以使用该方法
     * 其他的开头还有private，意思是私有的，其他的类不能使用，暂时无需了解
     * 2.方法又可以理解为技能，也就是这个类会干的事情
     * 3.需要执行的语句都在方法中，比如system.out.print这种
     * 4.方法中也可以有定义变量，int a = 0，但是方法中定义的变量，只在本方法中有效
     * 5.在方法外面定义的变量，在这个类中，所有方法中都有效。
     *
     */
    public void eat(){
        System.out.println("会吃饭");
    }


    /**
     * 不带参数的方法
     */
    public void walk(){
        System.out.println("会走路");
    }

    /**
     * 带参数的方法
     *
     * 拉屎的方法
     * 想要拉屎，就要先吃点什么
     *
     * 注意括号中，第一次有了内容，上面的方法都没有
     * 括号中的内容，被称为参数，也就是执行 / 调用 这个方法，需要传入的 内容
     * 想想add(a,b)，调用加法，就需要传两个 int 对吧
     *
     * 参数可以是像 int 这种的基本类型，也可以是一个类，比如下面的Food，是一个食物类
     *
     * @param food 是食物，x 是要吃几个馒头
     */
    public void shit(int x,Food food){

        food.beEaten();//食物的方法，被吃掉了
        System.out.println("我叫" +name + " 我今年"+age+"岁，"+"我吃了 " + x +" 个馒头，吃饱了，去拉屎了");
    }


    /**
     * 熟悉的main方法来了，每个类，都只能有一个main方法，其他的方法可以有无数个
     * main方法是直接可以运行的方法，点击左边的绿色三角
     * 也只有main方法可以直接运行，所以经常作为新手入门的方法
     * @param args
     */
    public static void main(String[] args) {

      //下面来模拟 人 吃 食物，然后拉屎的过程

        //第一步，创造对象，这个 p 就是Person的一个实例，代表一个普通的人吧
        Person p  = new Person();
        //第二步，创造食物对象，f 代表具体的食物
        Food f = new Food();
        //人吃5个馒头
        p.shit(5,f);

        System.out.println("完事了，睡觉去");



    }

}
