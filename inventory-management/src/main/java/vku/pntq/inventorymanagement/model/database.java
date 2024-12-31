package vku.pntq.inventorymanagement.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectionDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/inventorymanagement", "root", "");
            return connect;
        } catch (Exception e){
            System.out.println("k kết nối đc với db ");
            e.printStackTrace();
        }
        return null;
    }
}
