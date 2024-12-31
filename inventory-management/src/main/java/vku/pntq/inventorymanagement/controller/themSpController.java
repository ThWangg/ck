package vku.pntq.inventorymanagement.controller;

import javafx.event.ActionEvent;
import vku.pntq.inventorymanagement.DAO.SanPhamDAO;
import vku.pntq.inventorymanagement.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class themSpController{

    @FXML
    private Button huyBoThemSP;

    @FXML
    private TextField nhapDonGiaThemSP;

    @FXML
    private TextField nhapLoaiThemSP;

    @FXML
    private TextField nhapMaThemSP;

    @FXML
    private TextField nhapSoLuongThemSP;

    @FXML
    private TextField nhapTenThemSP;

    @FXML
    private TextField nhapTrangThaiThemSP;

    @FXML
    private Button xacNhanThemSP;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private SanPhamDAO sanPhamDAO = new SanPhamDAO();

//    public void themSanPhamMoi(){
//        String sql = "INSERT INTO sanpham (ma_san_pham, loai_san_pham, ten_san_pham, so_luong, don_gia, trang_thai)" + " VALUES (?, ?, ?, ?, ?, ?)";
//        connect = database.connectionDb();
//        try {
//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, nhapMaThemSP.getText());
//            prepare.setString(2, nhapLoaiThemSP.getText());
//            prepare.setString(3, nhapTenThemSP.getText());
//            try{
//                prepare.setInt(4, Integer.parseInt((nhapSoLuongThemSP.getText())));
//            }catch (NumberFormatException e){
//                Alert alertLoiSoLuong = new Alert(Alert.AlertType.ERROR);
//                alertLoiSoLuong.setTitle("Lỗi");
//                alertLoiSoLuong.setHeaderText(null);
//                alertLoiSoLuong.setContentText("Lỗi số lượng");
//                alertLoiSoLuong.showAndWait();
//            }
//            try{
//                prepare.setDouble(5, Double.parseDouble(nhapDonGiaThemSP.getText()));
//            }catch (NumberFormatException e){
//                Alert alertLoiDonGia = new Alert(Alert.AlertType.ERROR);
//                alertLoiDonGia.setTitle("Lỗi");
//                alertLoiDonGia.setHeaderText(null);
//                alertLoiDonGia.setContentText("Lỗi đơn giá");
//                alertLoiDonGia.showAndWait();
//            }
//            prepare.setString(6, nhapTrangThaiThemSP.getText());
//            if (nhapMaThemSP.getText().isEmpty() || nhapLoaiThemSP.getText().isEmpty() || nhapTenThemSP.getText().isEmpty() || nhapSoLuongThemSP.getText().isEmpty() || nhapDonGiaThemSP.getText().isEmpty()) {
//                Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
//                alertLoiThieuThongTin.setTitle("Lỗi");
//                alertLoiThieuThongTin.setHeaderText(null);
//                alertLoiThieuThongTin.setContentText("điền đầy đủ thông tin cần thiết");
//                alertLoiThieuThongTin.showAndWait();
//            }else{
//                prepare.executeUpdate();
//                Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
//                alertThanhCong.setTitle("Thêm sản phẩm thành công");
//                alertThanhCong.setHeaderText(null);
//                alertThanhCong.setContentText("Sản phẩm đã được thêm thành công");
//                alertThanhCong.showAndWait();
//                nhapMaThemSP.setText("");
//                nhapLoaiThemSP.setText("");
//                nhapTenThemSP.setText("");
//                nhapSoLuongThemSP.setText("");
//                nhapDonGiaThemSP.setText("");
//                nhapTrangThaiThemSP.setText("");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }

    @FXML
    public void themSanPham() {
        if(nhapMaThemSP.getText().isEmpty() || nhapLoaiThemSP.getText().isEmpty() || nhapTenThemSP.getText().isEmpty() || nhapSoLuongThemSP.getText().isEmpty() || nhapDonGiaThemSP.getText().isEmpty()) {
            Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
            alertLoiThieuThongTin.setTitle("LỖI");
            alertLoiThieuThongTin.setHeaderText(null);
            alertLoiThieuThongTin.setContentText("điền đầy đủ thông tin cần thiết");
            alertLoiThieuThongTin.showAndWait();
        }else{
            String maSanPham = nhapMaThemSP.getText();
            String loaiSanPham = nhapLoaiThemSP.getText();
            String tenSanPham = nhapTenThemSP.getText();
            int soLuong = 0;
            double donGia = 0;
            String trangThai = nhapTrangThaiThemSP.getText();

            try{
                soLuong = Integer.parseInt(nhapSoLuongThemSP.getText());
            }catch(NumberFormatException e){
                Alert alertSoLuong = new Alert(Alert.AlertType.ERROR);
                alertSoLuong.setTitle("LỖI");
                alertSoLuong.setHeaderText(null);
                alertSoLuong.setContentText("số lượng không hợp lệ");
                alertSoLuong.showAndWait();
                return;
            }

            try{
                donGia = Double.parseDouble(nhapDonGiaThemSP.getText());
            }catch(NumberFormatException e) {
                Alert alertDonGia = new Alert(Alert.AlertType.ERROR);
                alertDonGia.setTitle("LỖI");
                alertDonGia.setHeaderText(null);
                alertDonGia.setContentText("đơn giá không hợp lệ.");
                alertDonGia.showAndWait();
                return;
            }

            SanPhamDb sanPham = new SanPhamDb(maSanPham, loaiSanPham, tenSanPham, soLuong, donGia, trangThai);
            if(sanPhamDAO.themSanPham(sanPham)){
                Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
                alertThanhCong.setTitle("THÔNG BÁO");
                alertThanhCong.setHeaderText(null);
                alertThanhCong.setContentText("thêm sản phẩm thành công");
                alertThanhCong.showAndWait();
                nhapMaThemSP.setText("");
                nhapLoaiThemSP.setText("");
                nhapTenThemSP.setText("");
                nhapSoLuongThemSP.setText("");
                nhapDonGiaThemSP.setText("");
                nhapTrangThaiThemSP.setText("");
            }else{
                Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                alertLoi.setTitle("LỖI");
                alertLoi.setHeaderText(null);
                alertLoi.setContentText("không thể thêm sản phẩm");
                alertLoi.showAndWait();
            }
        }
    }

    public void huyBoThemSP(){
        Alert alertHuyBo = new Alert(Alert.AlertType.CONFIRMATION);
        alertHuyBo.setTitle("huỷ bỏ thêm vào sản phẩm mới");
        alertHuyBo.setHeaderText(null);
        alertHuyBo.setContentText("xác nhận huỷ bỏ?");
        alertHuyBo.showAndWait();
        if (alertHuyBo.getResult() == ButtonType.OK) {
            Stage stage = (Stage) huyBoThemSP.getScene().getWindow();
            stage.close();
        }
    }
}
