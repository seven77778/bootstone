package com.lsh.demo.network.serial;

/**
 * Created by lsh on 2019/3/8.
 */

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class SerialPortTest1 implements Runnable, SerialPortEventListener {
    // 检测系统中可用的通讯端口类
    private CommPortIdentifier portId;
    // 枚举类型
    private Enumeration<CommPortIdentifier> portList;

    // RS232串口
    private SerialPort serialPort;

    // 输入输出流
    private InputStream inputStream;
    // 保存串口返回信息
    private String test = "";

    // 单例创建
    private static SerialPortTest1 uniqueInstance = new SerialPortTest1();

    // 初始化串口
    public void init() {
        String comName = "COM3";
        // 获取系统中所有的通讯端口
        portList = CommPortIdentifier.getPortIdentifiers();
        System.out.println("可用串口： " + portList.toString());
        // 循环通讯端口
        while (portList.hasMoreElements()) {
            portId = portList.nextElement();
            // 判断是否是串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 比较串口名称是否是"COM3"
                if (comName.equals(portId.getName())) {
                    System.out.println("找到串口 " + comName);
                    // 打开串口
                    try {
                        // open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
                        serialPort = (SerialPort) portId.open(Object.class.getSimpleName(), 2000);
                        System.out.println("获取串口对象" + comName);
                        //实例化输入流
                        inputStream = serialPort.getInputStream();
                        // 设置串口监听
                        serialPort.addEventListener(this);
                        // 设置串口数据时间有效(可监听)
                        serialPort.notifyOnDataAvailable(true);
                        // 设置串口通讯参数
                        // 波特率，数据位，停止位和校验方式
                        // 波特率9600,无校验
                        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, //
                                SerialPort.STOPBITS_1, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    // 实现接口SerialPortEventListener中的方法 读取从串口中接收的数据
    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI: // 通讯中断
            case SerialPortEvent.OE: // 溢位错误
            case SerialPortEvent.FE: // 帧错误
            case SerialPortEvent.PE: // 奇偶校验错误
            case SerialPortEvent.CD: // 载波检测
            case SerialPortEvent.CTS: // 清除发送
            case SerialPortEvent.DSR: // 数据设备准备好
            case SerialPortEvent.RI: // 响铃侦测
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 输出缓冲区已清空
                System.out.println("return " + event.getEventType());
                break;
            case SerialPortEvent.DATA_AVAILABLE: // 有数据到达
                System.out.println("return " + event.getEventType());
                readComm();
                break;
            default:
                break;
        }
    }

    // 读取串口返回信息
    public void readComm() {
//        byte[] readBuffer = new byte[1024];
        try {
            inputStream = serialPort.getInputStream();

            // 从线路上读取数据流
            int len = 0;
//            while ((len = inputStream.read(readBuffer)) != -1) {
//                System.out.println("test1: " + new String(readBuffer,0,20,"GB2312"));
//                System.out.println("实时反馈：" + new String(readBuffer, 0, len).trim() + new Date());
//                test += new String(readBuffer, 0, len).trim();
//                break;
//            }
//            System.out.println(inputStream.read());
            //closeSerialPort();
            // 缓冲区大小为一个字节
            byte[] bytes = {};
            byte[] readBuffer = new byte[1];
            int bytesNum = inputStream.read(readBuffer);
            while (bytesNum > 0) {
                bytes = ArrayUtils.concat(bytes, readBuffer);
                bytesNum = inputStream.read(readBuffer);
            }
            System.out.println("return :" + new String(bytes, "ISO-8859-1"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            readBuffer=null;
        }
    }

    public void closeSerialPort() {
        uniqueInstance.serialPort.close();
    }

    //向串口发送数据
    public void sendMsg(byte[] bytes) {
        CISAV2Request cisaRequest = new CISAV2Request();
        cisaRequest.setHotelIdentifier("");
        cisaRequest.setClientNum("01");
        cisaRequest.setClientNum("");
        cisaRequest.setEncoderNum("");
        cisaRequest.setFirstName("Li");
        cisaRequest.setLastName("Lei");
        cisaRequest.setRoomNum1("5815");
        try {
//            cisaRequest.setCheckInDate(DateUtils.timeToPattern(makeCardRequest.getStartTime(),"yyyy-MM-dd HH:mm:ss", "yyyyMMdd"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cisaRequest.setRoomNum2("");
        cisaRequest.setRoomNum3("");
        cisaRequest.setRoomNum4("");


        try {
            OutputStream outputStream = serialPort.getOutputStream();
            byte[] cisaBytesRequest = cisaRequest.toBytes();
            outputStream.write(cisaBytesRequest);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        sendMsg(null);
    }
}