//package demo3;
//
//import gnu.io.SerialPortEvent;
//
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.sql.DriverManager;
//import java.util.TooManyListenersException;
//
///**
// * Created by lsh on 2019/3/9.
// */
//public class ReadSerial {
//
//    // 接收函数的定义
//    public void startRead(int time) {
//        try {
//            inputStream = new BufferedInputStream(serialPort.getInputStream());
//        } catch (IOException e) {
//            throw new RuntimeException("获取端口的InputStream出错：" + e.getMessage());
//        }
//        try {
//            serialPort.addEventListener(this);
//        } catch (TooManyListenersException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        serialPort.notifyOnDataAvailable(true);
//        if (time > 0) {
//            this.threadTime = time * 1000;
//            Thread t = new Thread(this);
//            t.start();
//        }
//    }
//
//    // 端口事件监听函数定义
//    public void serialEvent(SerialPortEvent arg0) {
//        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=mytest";
//        String userName = "sa"; // 默认用户名
//        String userPwd = "9406"; // 密码
//        Connection dbConn;
//        switch (arg0.getEventType()) {
//            case SerialPortEvent.BI:
//            case SerialPortEvent.OE:
//            case SerialPortEvent.FE:
//            case SerialPortEvent.PE:
//            case SerialPortEvent.CD:
//            case SerialPortEvent.CTS:
//            case SerialPortEvent.DSR:
//            case SerialPortEvent.RI:
//            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
//                break;
//            case SerialPortEvent.DATA_AVAILABLE:
//                byte[] readBuffer = new byte[1024];
//                try {
//                    while (inputStream.available() > 0) {
//                        inputStream.read(readBuffer);
//                        tempreture += new String(readBuffer).trim();
//                    }
//                    System.out.println(tempreture);
//                } catch (IOException e) {
//                }
//                try {
//                    Class.forName(driverName);
//                    dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//                    if (!dbConn.isClosed()) {
//                        // pw.println("_>Succeed to connecting SQL!" + "
//                        " + "
//                        ");
//                    }
//                    Statement statement1 = dbConn.createStatement();
//                    @SuppressWarnings("unused")
//                    Statement statement2 = dbConn.createStatement();
//                    String sql1 = "INSERT INTO tempreture(tempreture) VALUES('"
//                            + tempreture + "')";
//                    @SuppressWarnings("unused")
//                    int rs1 = statement1.executeUpdate(sql1);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//        }
//    }
//
//    // 计时函数
//    public void run() {
//        try {
//            Thread.sleep(threadTime);
//            serialPort.close();// 关闭接收函数，端口关闭
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
