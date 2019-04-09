package com.lsh.demo.python;

import org.junit.Test;
import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.util.Properties;

/**
 * Created by lsh on 2019/4/8.
 *
 * Java调用python的几种方式
 */
public class InvokePythonTest {


    @Test
    public void test1()throws Exception{
        Properties props = new Properties();
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        PySystemState sys = Py.getSystemState();
        sys.path.add("D:\\newsoftware\\phthon27_64\\Lib");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import clr;");
        interpreter.exec("import sys;");
//        interpreter.exec("print sys.path");
    }

}
