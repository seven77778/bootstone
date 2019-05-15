package com.lsh.demo.basic.system.path;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by lsh on 2018/12/22.
 *
 * classpath !!
 */
public class ClassPathTest implements ApplicationContextAware {

    public static void main(String[] args) throws Exception{
        System.out.println(ClassPathTest.class.getClassLoader().getResource("").getPath());
        System.out.println( ResourceUtils.getFile("/wsdl/test.txt"));
    }


    /**
     * ResourceUtils读取当前moudel下resource的文件
     * fileInputStream一次读完
     * 有文件夹 - wsdl/test.txt
     */
    @Test
    public void read2()throws Exception{
        File file=ResourceUtils.getFile("classpath:123.txt");
        File file2 =new File( getClass().getClassLoader().getResource("wsdl/test.txt").getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        byte[] bytes = new byte[fileInputStream.available()];
        byte[] bytes2 = new byte[fileInputStream2.available()];
        fileInputStream.read(bytes);
        fileInputStream2.read(bytes2);
        fileInputStream.close();
        fileInputStream2.close();
        System.out.println(new String(bytes,"utf-8"));
        System.out.println(new String(bytes2,"utf-8"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
