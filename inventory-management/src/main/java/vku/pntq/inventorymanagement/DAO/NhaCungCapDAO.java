package vku.pntq.inventorymanagement.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vku.pntq.inventorymanagement.model.NhaCungCapDb;
import vku.pntq.inventorymanagement.model.SanPhamDb;
import vku.pntq.inventorymanagement.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {

    public ObservableList<NhaCungCapDb> layDuLieuBangNCC(){
        ObservableList<NhaCungCapDb> listNCC = FXCollections.observableArrayList();
        String sql = "SELECT * FROM nhacungcap";
        try {
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                NhaCungCapDb nhaCungCapDb = new NhaCungCapDb(result.getInt("STT"), result.getString("ma_ncc"), result.getString("ten_ncc"), result.getInt("sdt"), result.getString("dia_chi"));
                listNCC.add(nhaCungCapDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNCC;
    }

    //thêm ncc
    public boolean themNCC(NhaCungCapDb nhaCungCap) {
        String sql = "INSERT INTO nhacungcap (ma_ncc, ten_ncc, sdt, dia_chi)" + " VALUES (?, ?, ?, ?)";
        Connection connect = database.connectionDb();
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, nhaCungCap.getMaNhaCungCap());
            prepare.setString(2, nhaCungCap.getTenNhaCungCap());
            prepare.setInt(3, nhaCungCap.getSoDienThoai());
            prepare.setString(4, nhaCungCap.getDiaChi());
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

    //sưar nhà cung
    public List<String> layDanhSachMaNhaCungCap(){
        List<String> danhSachMaNCC = new ArrayList<>();
        String sql = "SELECT ma_ncc FROM nhacungcap";
        try{
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet resultSet = prepare.executeQuery();

            while(resultSet.next()){
                danhSachMaNCC.add(resultSet.getString("ma_ncc"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return danhSachMaNCC;
    }

    public NhaCungCapDb layThongTinNhaCungCap(String maNhaCungCap) {
        String sql = "SELECT * FROM nhacungcap WHERE ma_ncc = ?";
        try{
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, maNhaCungCap);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                return new NhaCungCapDb(result.getString("ma_ncc"), result.getString("ten_ncc"), result.getInt("sdt"), result.getString("dia_chi"));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean suaNCC(NhaCungCapDb nhaCungCap) {
        String sql = "UPDATE nhacungcap SET ma_ncc = ?, ten_ncc = ?, sdt = ?, dia_chi = ? WHERE ma_ncc = ?";
        try{
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, nhaCungCap.getMaNhaCungCap());
            prepare.setString(2, nhaCungCap.getTenNhaCungCap());
            prepare.setInt(3, nhaCungCap.getSoDienThoai());
            prepare.setString(4, nhaCungCap.getDiaChi());
            prepare.setString(5, nhaCungCap.getMaNhaCungCapCu());

            int result = prepare.executeUpdate();
            if(result > 0) {
                return true;
            }else {
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // xoas ncc

    public boolean xoaNhaCungCap(NhaCungCapDb nhaCungCap) {
        String sql = "DELETE FROM nhacungcap WHERE ma_ncc = ?";
        try{
            Connection connect = database.connectionDb();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, nhaCungCap.getMaNhaCungCap());

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
}