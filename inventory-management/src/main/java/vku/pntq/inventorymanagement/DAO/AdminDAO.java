package vku.pntq.inventorymanagement.DAO;

import vku.pntq.inventorymanagement.model.AdminDb;
import vku.pntq.inventorymanagement.model.ConnectDatabase;
import vku.pntq.inventorymanagement.model.GuiEmail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.Random;

public class AdminDAO{
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    public boolean ktra(String username, String email, String password) {
        String sql = "SELECT * FROM admin WHERE (username = ? OR email = ?) AND password = ?";
        connect = ConnectDatabase.connectionDb();
        try {
            prepare = connect.prepareStatement(sql);

            if(username != null && !username.isEmpty()){
                prepare.setString(1, username);
            }else{
                prepare.setString(1, email);
            }

            if(email != null && !email.isEmpty()){
                prepare.setString(2, email);
            }else {
                prepare.setString(2, username);
            }
            prepare.setString(3, password);

            result = prepare.executeQuery();
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String taoMaXacNhan() {
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder ma = new StringBuilder();

        for(int i = 0; i < 6; i++){
            int index = random.nextInt(characters.length());
            ma.append(characters.charAt(index));
        }
        return ma.toString();
    }

    public boolean ktraEmail(String email) {
        String sql = "SELECT * FROM admin WHERE email = ?";
        try {
            connect = ConnectDatabase.connectionDb();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, email);

            result = prepare.executeQuery();

            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String maXacNhanDaGui;
    public boolean guiMaResetMatKhau(String email) {
        if (!ktraEmail(email)) {
            return false;
        }

        String maXacNhan = taoMaXacNhan();

        maXacNhanDaGui = maXacNhan;

        GuiEmail guiEmail = new GuiEmail();
        boolean emailSent = guiEmail.sendResetPasswordEmail(email, maXacNhan);

        if (emailSent) {
            return true;
        }else {
            System.out.println("k gui dc ma");
        }
        return false;
    }

    public String getMaXacNhanDaGui(){
        return maXacNhanDaGui;
    }

    public boolean suaMatKhau(AdminDb admin){
        String sql = "UPDATE admin SET password = ? WHERE email = ?";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, admin.getPassword());
            prepare.setString(2, admin.getEmail());
            int result = prepare.executeUpdate();

            if(result > 0){
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
