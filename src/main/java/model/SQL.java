package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class SQL {
    private final String url = "jdbc:mysql://localhost:3306/market?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
    private final String name = "root";
    private final String password = "0221";
    private Connection connection;

    public SQL() throws SQLException {
        connection = DriverManager.getConnection(url, name, password);
        System.out.println("success");
    }

    private static List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map rowData = new HashMap(columnCount);//声明Map
            //String str=null;
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), (String) rs.getObject(i));//获取键名及值
                //str= rs.getString(i);
            }
            //list.add(str);
            list.add(rowData);
            //System.out.println(list.toString());
        }
        return list;
    }

    public void test() {
        System.out.println("lll");
    }

    public boolean insertUserInfo(String name, String password) throws SQLException {
        Statement statement = connection.createStatement();
        String insertString = convertInsertStmt(name, password);
        System.out.println(insertString);
//        try {
//
//            statement.execute(insertString);
//        } catch (SQLException e) {
//            System.out.println(e);
//            return false;
//        }
        return true;
    }

    private String convertInsertStmt(String name, String password) {
        String stmt_1 = "insert into tb_user (name,password,timeOfRegister) values (";
        //获取当前时间，要插入数据库中
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        java.util.Date now = new Date();
        String time = sdf.format(now);
        //给参数加上引号
        name = quote(name);
        password = quote(password);
        time = quote(time);
        String stmt = new StringBuilder().append(stmt_1).append(name).append(",").append(password).append(",").append(time).append(");").toString();
        return stmt;
    }

    private String quote(String str) {
        String s = "'" + str + "'";
        return s;
    }

    public List query(String sqlQuery) throws SQLException {//将查询语句作为参数传递进来
        Statement statement = connection.createStatement();
        //4.执行命令窗口里的语句
        //String sqlQuery = new StringBuilder().append("select password from tb_admin where name = ").append("'").append(username).append("'").toString();

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        List list = convertList(resultSet);
        //String str = "error";
        //List list=new ArrayList();
//        if(resultSet.next())
//            str =resultSet.getString(1);
        resultSet.close();
        statement.close();
        connection.close();
        return list;
    }
}
