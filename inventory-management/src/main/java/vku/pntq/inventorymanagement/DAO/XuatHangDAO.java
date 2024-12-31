package vku.pntq.inventorymanagement.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vku.pntq.inventorymanagement.model.NhaCungCapDb;
import vku.pntq.inventorymanagement.model.XuatHangDb;
import vku.pntq.inventorymanagement.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class XuatHangDAO {

    public ObservableList<XuatHangDb> layDuLieuBangXuatHang() {
        ObservableList<XuatHangDb> listXuatHang = FXCollections.observableArrayList();
        String sql = "SELECT * FROM xuathang";
        try {
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                XuatHangDb xuatHangDb = new XuatHangDb(result.getString("ma_san_pham"), result.getString("ten_san_pham"), result.getInt("so_luong"), result.getDouble("don_gia"));
                listXuatHang.add(xuatHangDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listXuatHang;
    }

    public boolean themHangVaoXuatHang(XuatHangDb xuatHang) {
        String sql = "INSERT INTO xuathang(ma_san_pham, ten_san_pham, so_luong, don_gia) VALUES (?, ?, ?, ?)";
        Connection connect = database.connectionDb();
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, xuatHang.getMaSanPham());
            prepare.setString(2, xuatHang.getTenSanPham());
            prepare.setInt(3, xuatHang.getSoLuong());
            prepare.setDouble(4, xuatHang.getDonGia());

            int result = prepare.executeUpdate();
            if(result > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public double tinhTongTienXuatHang(){
        double tongTien = 0.0;
        String sql = "SELECT SUM(don_gia * so_luong) AS tong_tien FROM xuathang";
        try{
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if(result.next()){
                tongTien = result.getDouble("tong_tien");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return tongTien;
    }

    public void xoaDuLieu() {
        String sql = "DELETE FROM xuathang";
        try{
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
