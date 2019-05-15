package com.lsh.demo.designmode.singlemode;

import lombok.Data;

import java.io.*;

/**
 * Created by lsh on 2018/11/13 19:07.
 *
 * @author lsh
 * @date 2018/11/13
 *
 * 单例模式的序列化问题
 * 序列化前后两个对象并不相等
 * 任何一个readObject方法，不管是显式的还是默认的，它都会返回一个新建的实例，这个新建的实例不同于该类初始化时创建的实例
 * readResolve
 */
@Data
public class SingletonbySerial implements Serializable {
    private static volatile SingletonbySerial singletonbySerial;
    private String content ;

    public static SingletonbySerial getSingletonbySerial(){

        if(singletonbySerial == null){
            synchronized (SingletonbySerial.class){
                if(singletonbySerial == null){
                    singletonbySerial = new SingletonbySerial();
                }
            }
        }
        return singletonbySerial;
    }

    public static void main(String[] args) throws Exception {
        SingletonbySerial singleton = SingletonbySerial.getSingletonbySerial();
        singleton.setContent("hello 序列化");
        //复习序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("single"));
        objectOutputStream.writeObject(singleton);
        objectOutputStream.flush();
        objectOutputStream.close();
        //fan序列化
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("single"));
        SingletonbySerial res = (SingletonbySerial)inputStream.readObject();
        System.out.println(res.getContent());
        System.out.println("是否同一个对象: " + (singleton == res));
    }

}
