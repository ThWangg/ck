package vku.pntq.inventorymanagement.DAO;

import vku.pntq.inventorymanagement.model.ConnectDatabase;
import vku.pntq.inventorymanagement.model.SanPhamDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SanPhamDAO {
    // table có trạng thái
    public ObservableList<SanPhamDb> layDuLieuBangSP_Home() {
        ObservableList<SanPhamDb> listSPHome = FXCollections.observableArrayList();
        String sql = "SELECT * FROM sanpham";
        try {
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()){
                SanPhamDb sanPhamDb = new SanPhamDb(result.getString("ma_san_pham"), result.getString("loai_san_pham"), result.getString("ten_san_pham"), result.getString("ma_ncc"),result.getInt("so_luong"), result.getDouble("don_gia"), result.getString("trang_thai"));
                listSPHome.add(sanPhamDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSPHome;
    }

    // table k trạng thái
    public ObservableList<SanPhamDb> layDuLieuBangSP_SP() {
        ObservableList<SanPhamDb> listSP = FXCollections.observableArrayList();
        String sql = "SELECT * FROM sanpham";
        try {
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                SanPhamDb sanPhamDb = new SanPhamDb(result.getString("ma_san_pham"), result.getString("loai_san_pham"), result.getString("ten_san_pham"), result.getString("ma_ncc"), result.getInt("so_luong"), result.getDouble("don_gia"));
                listSP.add(sanPhamDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    public void xoaDuLieu() {
        String sql = "DELETE FROM sanpham";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///thêm sp
    public boolean themSanPham(SanPhamDb sanPham) {
        String sql = "INSERT INTO sanpham (ma_san_pham, loai_san_pham, ten_san_pham, ma_ncc, so_luong, don_gia, trang_thai)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connect = ConnectDatabase.connectionDb();
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, sanPham.getMaSanPham());
            prepare.setString(2, sanPham.getLoaiSanPham());
            prepare.setString(3, sanPham.getTenSanPham());
            prepare.setString(4, sanPham.getMa_ncc());
            prepare.setInt(5, sanPham.getSoLuong());
            prepare.setDouble(6, sanPham.getDonGia());
            prepare.setString(7, sanPham.getTrangThai());
            int result = prepare.executeUpdate();
            if(result > 0){
                return true;
            }
            else{
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    ///sửa sp

    //lấy danh sasch mã sp cho combobox
    public List<String> layDanhSachMaSanPham(){
        List<String> danhSachMaSP = new ArrayList<>();
        String sql = "SELECT ma_san_pham FROM sanpham";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet resultSet = prepare.executeQuery();

            while(resultSet.next()){
                danhSachMaSP.add(resultSet.getString("ma_san_pham"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return danhSachMaSP;
    }
    // láy từ mã sp
    public SanPhamDb layThongTinSanPham(String maSanPham) {
        String sql = "SELECT * FROM sanpham WHERE ma_san_pham = ?";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, maSanPham);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return new SanPhamDb(result.getString("ma_san_pham"), result.getString("loai_san_pham"), result.getString("ten_san_pham"), result.getString("ma_ncc"), result.getInt("so_luong"), result.getDouble("don_gia"), result.getString("trang_thai"));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean suaSanPham(SanPhamDb sanPham) {
        String sqlXuatHang = "UPDATE xuathang SET ma_san_pham = ?, ten_san_pham = ?, don_gia = ? WHERE ma_san_pham = ?";
        String sqlSP = "UPDATE sanpham SET ma_san_pham = ?, loai_san_pham = ?, ten_san_pham = ?, ma_ncc = ?, so_luong = ?, don_gia = ?, trang_thai = ? WHERE ma_san_pham = ?";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepareSP = connect.prepareStatement(sqlSP);

            prepareSP.setString(1, sanPham.getMaSanPham());
            prepareSP.setString(2, sanPham.getLoaiSanPham());
            prepareSP.setString(3, sanPham.getTenSanPham());
            prepareSP.setString(4, sanPham.getMa_ncc());
            prepareSP.setInt(5, sanPham.getSoLuong());
            prepareSP.setDouble(6, sanPham.getDonGia());
            prepareSP.setString(7, sanPham.getTrangThai());
            prepareSP.setString(8, sanPham.getMaSanPhamCu());
            prepareSP.executeUpdate();

            try{
                PreparedStatement prepareXuatHang = connect.prepareStatement(sqlXuatHang);
                prepareXuatHang.setString(1, sanPham.getMaSanPham());
                prepareXuatHang.setString(2, sanPham.getTenSanPham());
                prepareXuatHang.setDouble(3, sanPham.getDonGia());
                prepareXuatHang.setString(4, sanPham.getMaSanPhamCu());
                prepareXuatHang.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }


            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    ///  xoá sp

    public boolean xoaSanPham(SanPhamDb sanPham) {
        String sql = "DELETE FROM sanpham WHERE ma_san_pham = ?";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, sanPham.getMaSanPham());

            int result = prepare.executeUpdate();
            if(result > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //lấy danh sách tên sp cho combobox - xuất hàng
    public List<String> layDanhSachTenSanPham(){
        List<String> danhSachTenSP = new ArrayList<>();
        String sql = "SELECT ten_san_pham FROM sanpham";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet resultSet = prepare.executeQuery();

            while(resultSet.next()){
                danhSachTenSP.add(resultSet.getString("ten_san_pham"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return danhSachTenSP;
    }
    // lấy từ tên
    public SanPhamDb layThongTinSanPhamTuTenSP(String tenSanPham) {
        String sql = "SELECT * FROM sanpham WHERE ten_san_pham = ?";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, tenSanPham);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return new SanPhamDb(result.getString("ma_san_pham"), result.getString("loai_san_pham"), result.getString("ten_san_pham"), result.getString("ma_ncc"), result.getInt("so_luong"), result.getDouble("don_gia"), result.getString("trang_thai"));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int laySoLuongSanPham(String maSP) {
        String sql = "SELECT so_luong FROM sanpham WHERE ma_san_pham = ?";
        try{
            Connection connect = ConnectDatabase.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, maSP);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return result.getInt("so_luong");
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

