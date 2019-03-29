package com.lsh.demo.network.serial;

import com.lsh.demo.serial.SerialPortParameterFailure;
import gnu.io.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

/**
 * Created by lsh on 2019/3/8.
 */
public class SerialTest {

    public static void main(String[] args) {
        System.out.println(getAvailableSerialPorts());
    }

    /**
     * @Description:获取所有可用的串口集合
     */
    @SuppressWarnings("unchecked")
    public static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) portList.nextElement();
            if (com.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(com.getName());
                try {
                    // open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
                    /*
                     * open方法打开通讯端口，获得一个CommPort对象，它使程序独占端口。
                     * 如果端口正被其他应用程序占用，将使用CommPortOwnershipListener事件机制
                     * 传递一个PORT_OWNERSHIP_REQUESTED事件。
                     * 每个端口都关联一个InputStream和一个OutputStream,如果端口是用
                     * open方法打开的，那么任何的getInputStream都将返回相同的数据流对象，除非 有close被调用。
                     */
                    CommPort thePort = com.open(Object.class.getSimpleName(), 5000000);
//                    thePort.close();
                    h.add(com);
                } catch (PortInUseException e) {
                    // 不可用串口
                    System.out.println("Port, " + com.getName() + ", is in use.");
                } catch (Exception e) {
                    System.err.println("Failed to open port " + com.getName());
                    e.printStackTrace();
                }
            }

        }
        return h;
    }

    /**
     * 查找所有可用端口
     *
     * @return 可用端口名称列表
     */
    public static final ArrayList<String> findPort() {
        //获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<>();
        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }

    /**
     * 打开串口
     *
     * @param portName 端口名称
     * @param baudrate 波特率
     * @return 串口对象
     * @throws SerialPortParameterFailure 设置串口参数失败
     * @throws NotASerialPort             端口指向设备不是串口类型
     * @throws NoSuchPort                 没有该端口对应的串口设备
     */
    public static final SerialPort openPort(String portName, int baudrate) throws Exception {

        try {
            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);
            //判断是不是串口
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                try {
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
                    throw new SerialPortParameterFailure();
                }
                //System.out.println("Open " + portName + " sucessfully !");
                return serialPort;
            } else {
                //不是串口
                throw new NotASerialPort();
            }
        } catch (Exception e1) {
            System.out.println("error");
            return null;
        }
    }

}
