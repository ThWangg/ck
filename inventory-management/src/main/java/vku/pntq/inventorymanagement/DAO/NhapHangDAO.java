package vku.pntq.inventorymanagement.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vku.pntq.inventorymanagement.model.ConnectDatabase;
import vku.pntq.inventorymanagement.model.NhapHangDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhapHangDAO {

    public ObservableList<NhapHangDb> layDuLieuBangNhapHang() {
        ObservableList<NhapHangDb> listNhapHang = FXCollections.observableArrayList();
        String sql = "SELECT * FROM nhaphang";
        try {
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                NhapHangDb nhapHangDb = new NhapHangDb(result.getString("ma_san_pham"), result.getString("ten_san_pham"), result.getInt("so_luong"), result.getInt("don_gia"));
                listNhapHang.add(nhapHangDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhapHang;
    }

    public boolean themHangVaoNhapHang(NhapHangDb nhaphang) {
        String sql = "INSERT INTO nhaphang(ma_san_pham, ten_san_pham, so_luong, don_gia) VALUES (?, ?, ?, ?)";
        Connection connect = ConnectDatabase.connectionDb();
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, nhaphang.getMaSanPham());
            prepare.setString(2, nhaphang.getTenSanPham());
            prepare.setInt(3, nhaphang.getSoLuong());
            prepare.setDouble(4, nhaphang.getDonGia());

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

    public int tinhTongTienNhapHang(){
        int tongTien = 0;
        String sql = "SELECT SUM(don_gia * so_luong) AS tong_tien FROM nhaphang";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if(result.next()){
                tongTien = result.getInt("tong_tien");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return tongTien;
    }

    public void xoaDuLieu() {
        String sql = "DELETE FROM nhaphang";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean xoaSanPhamNH(NhapHangDb sanPham) {
        String sql = "DELETE FROM nhaphang WHERE ma_san_pham = ?";
        try {
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, sanPham.getMaSanPham());

            int result = prepare.executeUpdate();
            if(result > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean capNhatSoLuongSanPham(String maSP, int soLuongNhap) {
        String sql = "UPDATE sanpham SET so_luong = so_luong + ? WHERE ma_san_pham = ?";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setInt(1, soLuongNhap);
            prepare.setString(2, maSP);

            int result = prepare.executeUpdate();
            if(result > 0) {
                return true;
            }else{
                return false;
            }
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
