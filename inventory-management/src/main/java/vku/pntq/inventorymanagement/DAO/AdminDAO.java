package vku.pntq.inventorymanagement.DAO;

import vku.pntq.inventorymanagement.model.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO{
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    public boolean ktra(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        connect = database.connectionDb();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);
            result = prepare.executeQuery();
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
