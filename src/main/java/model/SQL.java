package model;

import java.net.MalformedURLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class SQL {
    private final String url = "jdbc:mysql://localhost:3306/market?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
    private final String name = "root";
    private final String password = "0221";
    String sql;
    String table;
    private Connection connection;

    public SQL() throws SQLException {
        table = "tb_items";//默认查询商品表
        System.out.println("SQL INITIALIZE success");
    }

    private static String quote(String str) {
        String s = "'" + str + "'";
        return s;
    }

    //根据情况不同，决定要对哪些表进行操作
    public void setTable(String tablename) {
        this.table = tablename;
    }

    //注册的用户信息插入
    public boolean insertUserInfo(String name, String password) throws SQLException {
        connection = DriverManager.getConnection(url, this.name, this.password);
        Statement statement = connection.createStatement();
        String insertString = convertInsertStmt(name, password);

        boolean ifSucceed = !statement.execute(insertString);

        statement.close();
        connection.close();
        System.out.println(ifSucceed);
        return ifSucceed;
    }

    private String convertInsertStmt(String name, String password) {
        String stmt_1 = "INSERT INTO tb_user(name,password,timeOfRegister) VALUES(";
        //获取当前时间，要插入数据库中
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        java.util.Date now = new Date();
        String time = sdf.format(now);
        //给参数加上引号
        name = quote(name);
        password = quote(password);
        time = quote(time);
        String stmt = stmt_1 + name + "," + password + "," + time + ");";
        System.out.println(stmt);
        return stmt;
    }

    public List query(String sqlQuery) throws SQLException {//将查询语句作为参数传递进来
        connection = DriverManager.getConnection(url, name, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        List list = convertList(resultSet);
        resultSet.close();
        statement.close();
        connection.close();//存疑
        return list;
    }


    public List<Item> itemsInitialize() throws SQLException {
        List<Item> items_list = new ArrayList<>();
        sql = "SELECT * FROM " + table;
        System.out.println(sql);
        List list = query(sql);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            Item item = new Item();
            Map map = (Map) it.next();
            String name = (String) map.get("item_name");
            float price = (float) map.get("item_price");
            String type = (String) map.get("item_type");
            String unit = (String) map.get("item_unit");
            int num = (int) map.get("item_num");
            String itemImgPath = (String) map.get("item_ImgPath");
            item.setName(name);
            item.setPrice(price);
            item.setType(type);
            item.setNum(num);
            item.setImgsrc(itemImgPath);
            item.setUnit(unit);
            items_list.add(item);
        }
        return items_list;
    }


    private List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取列的数量
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap(columnCount);//声明Map
            //String str=null;
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);//将当前行的数据传入
        }
        return list;
    }

    //将购物车写入数据库中
    //TODO 单条插入语句比多条快，改写插入语句
    public boolean addItemsToCartSql(List<Item> itemList) throws MalformedURLException, SQLException {
        Connection connection = DriverManager.getConnection(url, name, password);
        Statement statement = connection.createStatement();

        String str1 = "INSERT INTO tb_tset(item_name,item_price,item_type,item_unit,item_num,item_ImgPath) VALUES";
        String str = str1;
        for (int i = 0; i < itemList.size(); i++) {
            str += convertInsertStmt(itemList.get(i));
            str += ",";
        }
        str = str.substring(0, str.length() - 1) + ";";
        System.out.println(str);
        boolean ifSuccess = statement.execute(str);
        connection.close();
        return ifSuccess;
    }

    //生成插入购物车表的sql语句
    private String convertInsertStmt(Item item) {
        String name = quote(item.getName());
        String price = quote(String.valueOf(item.getPrice()));
        String type = item.getType() == null ? null : quote(item.getType());
        String unit = item.getUnit() == null ? null : quote(item.getUnit());//字段值为空时不需要加单引号
        String num = quote(String.valueOf(item.getChosenNum()));
        String ImgPath = quote(item.getImgPath());
        ImgPath = ImgPath.replaceAll("\\\\", "\\\\\\\\");
        String str = "(" + name + "," + price + "," + type + "," + unit + "," + num + "," + ImgPath + ")";
        return str;
    }
}
