package com.lsh.demo.designmode.singlemode;

/**
 * Created by lsh on 2018/11/9 20:52.
 *
 * @author lsh
 * @date 2018/11/09
 *
 * 单例模式中的双重检查
 *
 * 我们假设有两个线程 a 和 b 调用 getInstance() 方法，假设 a 先走，一路走到 4 这一步，执行  instance = new Singleton() 这句代码。

instance = new Singleton() 这句代码首先会申请一段空间，然后将各个属性初始化为零值(0/null)，执行构造方法中的属性赋值[1]，将这个对象的引用赋值给 instance[2]。在这个过程中，[1] 和 [2] 可能会发生重排序。

此时，线程 b 刚刚进来执行到 1（看上面的代码块），就有可能会看到 instance 不为 null，然后线程 b 也就不会等待监视器锁，而是直接返回 instance。问题是这个 instance 可能还没执行完构造方法（线程 a 此时还在 4 这一步），所以线程 b 拿到的 instance 是不完整的，它里面的属性值可能是初始化的零值(0/false/null)，而不是线程 a 在构造方法中指定的值。
 */
public class SingletonTest {
    private static SingletonTest instance = null;

    private int num;
    private SingletonTest() {
        this.num = 1;
    }

    public static SingletonTest getInstance() {
        if (instance == null) { // 1. 第一次检查
            synchronized (SingletonTest.class) { // 2
                if (instance == null) { // 3. 第二次检查
                    instance = new SingletonTest(); // 4
                }
            }
        }
        return instance;
    }

    /**
     * 依然失败
     * 没有任何规则规定了，释放锁之后的代码不可以在释放锁之前先执行
     * 也就是说，代码中释放锁之后的行为 instance = temp 完全可以被提前到前面的 synchronized 代码块中执行
     * 那么前面说的重排序问题就又出现了
     *
     * 如果所有的属性都是使用 final 修饰的，其实之前介绍的双重检查是可行的，不需要加 volatile
     * @return
     */
    public static SingletonTest getInstance2() {
        if (instance == null) { //
            SingletonTest temp;
            synchronized (SingletonTest.class) { //
                temp = instance;
                if (temp == null) { //
                    synchronized (SingletonTest.class) { // 内嵌一个 synchronized 块
                        temp = new SingletonTest();
                    }
                    instance = temp; //
                }
            }
        }
        return instance;
    }
}
