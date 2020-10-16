package com.lsh.demo.bootstone.dao.sqlserver2008;

import java.sql.*;

/**
 * Created by lsh on 2019/4/18.
 */
public class DBUtils {
    private final static String URL = "jdbc:sqlserver://xx.xx.xx.xx:1433;DatabaseName=mylock1";
    private static final String USER="sa";
    private static final String PASSWORD="x";

    private static Connection conn=null;
    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.获得数据库的连接
            conn=(Connection) DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void main(String[] args) throws Exception{

        Statement stmt = conn.createStatement();

        String sql = "select * from dbo.HX_RoomInfor";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getInt("RoomName"));
        }
    }
}
