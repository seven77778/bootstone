package work.utils;


import java.sql.*;

/**
 * Created by lsh on 2019-04-24.
 *
 *   可以直接通过javac java执行来测试指定环境数据库连接问题
 *
 *  * note : sqlserver2000
 *  * jdbc:microsoft:sqlserver://xxx:1433;DatabaseName=MYLOCK
 *
 *   1.如果classnotfound
 *   手动copy sqljdbc4 到jdk lib目录
 *
 *   2.no suit for 什么的，去掉 microsoft
 *
 *   3.一定要注意DataBaseName my mg
 */
public class SqlConnectTest {

    public static void main(String[] args) {
        Connection conn;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://xxx:1433;DatabaseName=mylock1232", "sa", "mbt2005");
            String sql = "select roomName from HX_RoomInfor where SimpleName = 'sss'";
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            conn.commit();
            System.out.println(res);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(" DB init error");
        }
    }

}
