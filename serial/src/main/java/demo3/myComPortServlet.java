package demo3;

/**
 * Created by lsh on 2019/3/9.
 */
//添加类包


import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

//创建一个类JavaSerialPort，通过关键词implements实声明自己使用Runnable和SerialPortEventListener这两个接口
public class myComPortServlet  {
    // 定义、声明变量
    private String appName = "Java串口通信";
    private int timeout = 2000;// 定义一个打开端口的最大等待时间
    public static String PortName;
    private CommPortIdentifier commPort;
    private SerialPort serialPort;
    private OutputStream outputStream;
    public static OutputStream out;
    public static String messageString = "点亮单片机上的第**个LED灯！";// 给选定端口发送的字符

    // listPort()方法的定义
    public void listPort() {
        CommPortIdentifier cpid;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();
        //System.out.println("端口信息 ：" + en);
        //System.out.println("端口列表如下：");
        while (en.hasMoreElements()) {
            cpid = (CommPortIdentifier) en.nextElement();
            if (cpid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                //System.out.println(cpid.getName() + ", "
                //+ cpid.getCurrentOwner());
            }
        }
    }

    // selectPort(String portName)方法的定义
    public void selectPort(String portName)throws Exception {
        this.commPort = null;
        CommPortIdentifier cpid;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();
        while (en.hasMoreElements()) {
            cpid = (CommPortIdentifier) en.nextElement();
            if (cpid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (cpid.getName().equals(portName)) {
                    this.commPort = cpid;
                }
            }
        }
        openPort();
    }

    // openPort()方法的定义
    private void openPort() throws Exception{
        try {
            serialPort = (SerialPort) commPort.open(appName, timeout);
        } catch (PortInUseException e) {
        }
        try {
            out = serialPort.getOutputStream();
        } catch (IOException e) {
        }
        try {
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
        }
        try {
            out.write(messageString.getBytes());
        } catch (IOException e) {
        }
    }

    // checkPort()方法的定义
    private void checkPort() {
        if (commPort == null)
            throw new RuntimeException(
                    "端口选择出错请用selectPort(String portName)方法选择正确的端口！");

        if (serialPort == null) {
            throw new RuntimeException("SerialPort 对象无效！");
        }
    }

    // write(String message)方法定义
    public void write(String message) {
        checkPort();

        try {
            outputStream = new BufferedOutputStream(
                    serialPort.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("获取端口的OutputStream出错：" + e.getMessage());
        }

        try {
            outputStream.write(message.getBytes());
            //log("信息发送成功！");
        } catch (IOException e) {
            throw new RuntimeException("向端口发送信息时出错：" + e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }

    // close()方法的定义
    public void close() {
        serialPort.close();
        serialPort = null;
        commPort = null;
    }



    // 类的主方法，Java程序运行首先是从这里开始的
    public static void main(String[] args) throws Exception {
        myComPortServlet sp = new myComPortServlet();
        sp.listPort();
        sp.selectPort("COM1");// 选择COM1口交换数据数据

    }
}