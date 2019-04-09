package com.lsh.demo.python;

import org.junit.Test;
import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.util.Properties;

/**
 * Created by lsh on 2019/4/8.
 * <p>
 * Java调用python的几种方式
 */
public class InvokePythonTest {


    /**
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
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
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
        interpreter.exec("print('hello')");
    }

}
