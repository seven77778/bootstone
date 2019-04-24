package work.sqlserver;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by lsh on 2019-04-23.
 *
 * sqlserver最简单的方式
 *
 * 每次请求都重新打开一个连接，使用完关闭
 * 适合场景 调用量较小
 *
 * static 代码块加载也可，但是不方便从配置中取数据
 *
 * 现在都是用spring 来管理，都忘了 connection 的原始操作 todo
 *
 * note : sqlserver2000
 * jdbc:microsoft:sqlserver://xxx:1433;DatabaseName=MYLOCK
 *
 *
 *
 */
public class SqlServerDemo1 {


    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("lockLocalLog");

    public static Connection getConnection(String url, String user, String pw) {
        Connection conn = null;
        try {
            //1.加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.获得数据库的连接
            conn = DriverManager.getConnection(url, user, pw);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            LOGGER.error(" DB init error");
        }
        return conn;
    }

    public static boolean addDb(String roomNo, String CardId, String startDate, String endDate, String ruleBuff, String floor, String build, String room, String url, String user, String pw) {
        Connection conn = getConnection(url, user, pw);

        String sqlRoomInfor = "update Room set CurCards=1,StateID=1 where RoomName=" + roomNo;
        String sqlGuest = "INSERT INTO Guest (CheckInType,CardID,BuildingID ,FloorID,RoomID,InnerID,IdentityID,StartDate,EndDate,Cancel,Recycle," +
                "Losted,Price,COState,Operator,InputDate,COOperator,CODateTime,RuleValue,RuleBuff)VALUES (1," + CardId + "," + build + "," + floor + "," + room +
                ",255,1," + "'" + startDate + "'" + "," + "'" + endDate + "'" + "," + "1,1,0,0,0,'system'," + "'" + startDate + "'" + ",'system'" + "," + "'" + startDate + "'" + ",0246," + ruleBuff + ")";

        LOGGER.info("RoomInfor= " + sqlRoomInfor + " Guest= " + sqlGuest);
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlRoomInfor);
            stmt.execute(sqlGuest);
            conn.commit();
            return true;
        } catch (Exception e) {
            LOGGER.error(" addGuestDetails Error", e);
            try {
                conn.rollback();
            } catch (Exception e1) {
                LOGGER.error(" RollBack Error ", e1);
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
