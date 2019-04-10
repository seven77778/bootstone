package com.lsh.demo.python;

import org.junit.Test;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by lsh on 2019/4/8.
 * <p>
 * Java调用python的几种方式
 */
public class InvokePythonTest {


    /**
     * JPython调用
     * TypeError: object of type 'NoneType' has no len()
     * 更新pom之后 问题解决
     *
     * <dependency>
     *  <groupId>org.python</groupId>
     *  <artifactId>jython-standalone</artifactId>
     *  <version>2.7.0</version>
     * </dependency>
     */
    @Test
    public void test1() throws Exception {
        Properties props = new Properties();
//        props.put("python.home", "path to the Lib folder");
//        props.put("python.console.encoding", "UTF-8");
//        props.put("python.security.respectJavaAccessibility", "false");
//        props.put("python.import.site", "false");
        PySystemState sys = Py.getSystemState();
        sys.path.add("D:\\Program Files (x86)\\python32\\Lib");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("print('hello')");
        interpreter.exec("import sys");
        interpreter.exec("print sys.path");
    }

    /**
     * 最简洁示例
     */
    @Test
    public void test2(){
        PythonInterpreter interpreter = new PythonInterpreter();
        PySystemState sys = Py.getSystemState();
        //指定python
        sys.path.add("D:\\Program Files (x86)\\python32\\Lib");
        interpreter.exec("print('hello')");
        interpreter.exec("import sys");
        interpreter.exec("print(sys.path)");
    }

    /**
     * JPython execfile 方式执行python脚本
     * 控制台输出也是print中内容
     *
     * 如何传参
     */
    @Test
    public void test3(){
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.execfile("D:\\STUDY_CODE\\Python\\pythondemo\\ReturnTest.py");
        PyFunction func = (PyFunction) pythonInterpreter.get("add",PyFunction.class);
        // nullPoint
        //定义在类下面的方法，nullPoint，直接在脚本中定义的方法 OK
        PyObject pyObject = func.__call__(new PyInteger(998),new PyInteger(1));
        System.out.println(pyObject.toString());

    }


    /**
     * Runtime调用方式
     * java调用python脚本，得到的是print中的数据
     * python:
     * print('return test')
     * java输出 “return test”
     */
    @Test
    public void testReadFromLocal() {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python D:\\STUDY_CODE\\Python\\pythondemo\\ReturnTest.py");
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line ;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开记事本
     * @throws Exception
     */
    @Test
    public void openNote()throws Exception{
        Runtime.getRuntime().exec("notepad.exe");
        Runtime.getRuntime().exec("calc");
    }


    /**
     * python 没有加入环境变量时，手动指定python环境
     * @throws Exception
     */
    @Test
    public void testPath()throws Exception{
        Process proc=
        Runtime.getRuntime().exec("D:\\Program Files (x86)\\python32\\python.exe D:\\STUDY_CODE\\Python\\pythondemo\\ReturnTest.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line ;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }

}
