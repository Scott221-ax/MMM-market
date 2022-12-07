package model;

import java.sql.SQLException;


public class sqltest {
//    public  SQL mySQL;//连接数据库进行增删改查操作
//
//    {
//        try {
//            mySQL = new SQL();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) throws SQLException {
        SQL mysql = new SQL();
        mysql.insertUserInfo("mzp", "pppp");
    }
}
