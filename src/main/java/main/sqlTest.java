package main;

import model.Item;
import model.SQL;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

public class sqlTest {
    public static void main(String[] args) throws SQLException, MalformedURLException {
        SQL mysql = new SQL();
        List<Item> itemList = mysql.itemsInitialize();
//        for(int i=0;i<itemList.size();i++){
//            Item item=itemList.get(i);
////            System.out.println(item.getName());
////            System.out.println(item.getPrice());
////            System.out.println(item.getType());
//            System.out.println(item.getImgsrc());
//           // File file=new File(item.getImgsrc());


        //  System.out.println(file.toURI().toURL().toString());
        //}

    }
}
