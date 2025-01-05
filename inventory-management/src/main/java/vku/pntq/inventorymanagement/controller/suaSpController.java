package vku.pntq.inventorymanagement.controller;

import vku.pntq.inventorymanagement.DAO.NhaCungCapDAO;
import vku.pntq.inventorymanagement.DAO.SanPhamDAO;
import vku.pntq.inventorymanagement.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ResourceBundle;

public class suaSpController implements Initializable{

    @FXML
    private Button huyBoSuaSP;

    @FXML
    private ComboBox<String> maSP_SuaSP;

    @FXML
    private ComboBox<String> maNCC_SuaSP;

    @FXML
    private TextField nhapDonGiaSP_SuaSP;

    @FXML
    private TextField nhapLoaiSP_SuaSP;

    @FXML
    private TextField nhapMaSPMoi_SuaSP;

    @FXML
    private TextField nhapSoLuongSP_SuaSP;

    @FXML
    private TextField nhapTenSP_SuaSP;

    @FXML
    private TextField nhapTrangThaiSP_SuaSP;

    @FXML
    private Button xacNhanSuaSP;

    private SanPhamDAO sanPhamDAO = new SanPhamDAO();

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();


    public void huyBoSuaSP() {
        Alert alertHuyBo = new Alert(Alert.AlertType.CONFIRMATION);
        alertHuyBo.setTitle("Huỷ bỏ sửa sản phẩm");
        alertHuyBo.setHeaderText(null);
        alertHuyBo.setContentText("Xác nhận huỷ bỏ?");
        alertHuyBo.showAndWait();
        if (alertHuyBo.getResult() == ButtonType.OK) {
            Stage stage = (Stage) huyBoSuaSP.getScene().getWindow();
            stage.close();
        }
    }

    // bỏ dữ liệu vô combobox
    public void layThongTinMaSp(){
        List<String> danhSachMaSP = sanPhamDAO.layDanhSachMaSanPham();
        maSP_SuaSP.getItems().addAll(danhSachMaSP);
    }
    public void layThongTinMaNCC(){
        List<String> danhSachMaNCC = nhaCungCapDAO.layDanhSachMaNhaCungCap();
        maNCC_SuaSP.getItems().addAll(danhSachMaNCC);
    }

    public void dienThongTinKhiChonMaSP(String maSP) {
        SanPhamDb sanPham = sanPhamDAO.layThongTinSanPham(maSP);
        if (sanPham != null) {
            nhapMaSPMoi_SuaSP.setText(sanPham.getMaSanPham());
            nhapTenSP_SuaSP.setText(sanPham.getTenSanPham());
            nhapLoaiSP_SuaSP.setText(sanPham.getLoaiSanPham());
            maNCC_SuaSP.setValue(sanPham.getMa_ncc());
            nhapDonGiaSP_SuaSP.setText(String.valueOf(sanPham.getDonGia()));
            nhapSoLuongSP_SuaSP.setText(String.valueOf(sanPham.getSoLuong()));
            nhapTrangThaiSP_SuaSP.setText(sanPham.getTrangThai());
        }
    }

    public void xacNhanSuaSP() {
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁO");
        alertXacNhan.setContentText("xác nhận sửa thông tin?");
        alertXacNhan.showAndWait();
        if (alertXacNhan.getResult() == ButtonType.OK){
            if(nhapMaSPMoi_SuaSP.getText().isEmpty() || nhapLoaiSP_SuaSP.getText().isEmpty() || nhapTenSP_SuaSP.getText().isEmpty() || nhapSoLuongSP_SuaSP.getText().isEmpty() || nhapDonGiaSP_SuaSP.getText().isEmpty()) {
                Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
                alertLoiThieuThongTin.setTitle("LỖI");
                alertLoiThieuThongTin.setHeaderText(null);
                alertLoiThieuThongTin.setContentText("điền đầy đủ thông tin cần thiết");
                alertLoiThieuThongTin.showAndWait();
            }else{
                String maSanPhamCu = maSP_SuaSP.getValue();
                String maSanPhamMoi = nhapMaSPMoi_SuaSP.getText();
                String loaiSanPham = nhapLoaiSP_SuaSP.getText();
                String tenSanPham = nhapTenSP_SuaSP.getText();
                String maNCC = maNCC_SuaSP.getValue();
                int soLuong = 0;
                double donGia = 0;
                try {
                    soLuong = Integer.parseInt(nhapSoLuongSP_SuaSP.getText());
                } catch (NumberFormatException e) {
                    Alert alertSoLuong = new Alert(Alert.AlertType.ERROR);
                    alertSoLuong.setTitle("LỖI");
                    alertSoLuong.setHeaderText(null);
                    alertSoLuong.setContentText("số lượng không hợp lệ");
                    alertSoLuong.showAndWait();
                    return;
                }

                try {
                    donGia = Double.parseDouble(nhapDonGiaSP_SuaSP.getText());
                }catch(NumberFormatException e) {
                    Alert alertDonGia = new Alert(Alert.AlertType.ERROR);
                    alertDonGia.setTitle("LỖI");
                    alertDonGia.setHeaderText(null);
                    alertDonGia.setContentText("đơn giá không hợp lệ.");
                    alertDonGia.showAndWait();
                    return;
                }

                String trangThai = nhapTrangThaiSP_SuaSP.getText();

                SanPhamDb sanPham = new SanPhamDb(maSanPhamMoi, loaiSanPham, tenSanPham, maNCC, soLuong, donGia, trangThai, maSanPhamCu);
                if (sanPhamDAO.suaSanPham(sanPham)){
                    Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
                    alertThanhCong.setTitle("THÔNG BÁO");
                    alertThanhCong.setHeaderText(null);
                    alertThanhCong.setContentText("SỬA SẢN PHẨM THÀNH CÔNG");
                    alertThanhCong.showAndWait();
                    xacNhanSuaSP.getScene().getWindow().hide();
                }else{
                    Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                    alertLoi.setTitle("LỖI");
                    alertLoi.setContentText("không thể sửa sản phẩm");
                    alertLoi.showAndWait();
//                    System.out.println("ma " +maSanPhamMoi);
//                    System.out.println("loai " + loaiSanPham);
//                    System.out.println("ten " +tenSanPham);
//                    System.out.println("ncc " + maNCC);
//                    System.out.println("sl " + soLuong);
//                    System.out.println("dg " + donGia);
//                    System.out.println("tt " + trangThai);
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        layThongTinMaSp();
        layThongTinMaNCC();
        maSP_SuaSP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    dienThongTinKhiChonMaSP(newValue);
                }
            }
        });
    }
}