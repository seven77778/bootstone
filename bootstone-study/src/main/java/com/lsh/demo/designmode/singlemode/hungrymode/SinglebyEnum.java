package com.lsh.demo.designmode.singlemode.hungrymode;

/**
 * Created by lsh on 2018/11/13 19:32.
 *
 * @author lsh
 * @date 2018/11/13
 *
 * 枚举实现单例模式
 *
 * if ((clazz.getModifiers() & Modifier.ENUM) != 0)
throw new IllegalArgumentException("Cannot reflectively create enum objects");
 -- 不能被反射获取

 *
 * 1.避免序列化后的多个实例
 * 2.避免了反射带来的多个实例
 *
 * */

public enum SinglebyEnum {
    /**
     * 枚举单例模式
     */
    INSTANCE;
    public String name = "i am enum";


}

class Test{

    public static void main(String[] args) {
        SinglebyEnum s1 = SinglebyEnum.INSTANCE;
        SinglebyEnum s2 = SinglebyEnum.INSTANCE;
        System.out.println(s1 == s2);
        System.out.println(s1.name);

    }
}