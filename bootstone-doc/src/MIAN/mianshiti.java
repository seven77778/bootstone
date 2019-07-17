import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lsh
 * <p>
 * 5道
 */

public class mianshiti {

    /**
     * 浮点运算精度
     */
    @Test
    public void test1() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.println(1.0f - 0.9f);
        System.out.println(1.1f - 1.0f);
        System.out.println(0.9f - 0.8f);
        System.out.println(0.8f - 0.7f);//0.100000024
        System.out.println(0.7f - 0.6f);//0.099999964
        System.out.println(15.6f - 15.5f);//0.10000038
        System.out.println(13f - 12f); // 1.0
    }

    /**
     * 包装类不会解决精度问题
     * false
     */
    @Test
    public void test2() {
        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);
        if (a.equals(b)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }


    /**
     * switch匹配null问题
     * first ： default
     * result ： nullPoint
     * <p>
     * 反编译结果： String param = null;
     * String s;
     * switch((s = param).hashCode())
     * null的hashCode，so~
     */
    @Test
    public void test3() {
        String param = null;
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }


    /**
     * 判断两种赋值方式是否一样
     * first ： 一样
     * result ： 完全不同
     * 0.1000000000000000055511151231257827021181583404541015625
     * 0.1
     */
    @Test
    public void test4() {
        BigDecimal a = new BigDecimal(0.1);//double
        System.out.println(a);
        BigDecimal b = new BigDecimal("0.1");
        System.out.println(b);

        //double类型推荐方式
        System.out.println(new BigDecimal(String.valueOf(0.1)));
    }

    /**
     *问题如下：
     *
     * A: lock 是非公平锁 -- 是非公平锁，性能开销较小
     * B: finally 代码块不会抛出异常 --
     * C: tryLock 获取锁失败则直接往下执行 --
     */
    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        try {
            lock.tryLock();
            throw new Exception("");//add -> 进入异常，并且进入finally
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("yichang");
        } finally {
            lock.unlock();
            System.out.println("finally");
        }
    }


}