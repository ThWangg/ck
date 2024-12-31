package vku.pntq.inventorymanagement.controller;

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
import java.util.List;
import java.util.ResourceBundle;

public class XoaSpController implements Initializable {

    @FXML
    private TextField donGiaSP_XoaSP;

    @FXML
    private Button huyBoXoaSP;

    @FXML
    private TextField loaiSP_XoaSP;

    @FXML
    private ComboBox<String> maSP_XoaSP;

    @FXML
    private TextField maSP_XoaSP_invisible;

    @FXML
    private TextField soLuongSP_XoaSP;

    @FXML
    private TextField tenSP_XoaSP;

    @FXML
    private TextField trangThaiSP_XoaSP;

    @FXML
    private Button xacNhanXoaSP;

    private SanPhamDAO sanPhamDAO = new SanPhamDAO();

    public void layThongTinMaSp(){
        List<String> danhSachMaSP = sanPhamDAO.layDanhSachMaSanPham();
        maSP_XoaSP.getItems().addAll(danhSachMaSP);
    }

    public void dienThongTinKhiChonMaSP(String maSP) {
        SanPhamDb sanPham = sanPhamDAO.layThongTinSanPham(maSP);
        if (sanPham != null) {
            maSP_XoaSP_invisible.setText(sanPham.getMaSanPham());
            tenSP_XoaSP.setText(sanPham.getTenSanPham());
            loaiSP_XoaSP.setText(sanPham.getLoaiSanPham());
            donGiaSP_XoaSP.setText(String.valueOf(sanPham.getDonGia()));
            soLuongSP_XoaSP.setText(String.valueOf(sanPham.getSoLuong()));
            trangThaiSP_XoaSP.setText(sanPham.getTrangThai());
        }
    }

    public void xacNhanXoaSP(){
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁOS");
        alertXacNhan.setHeaderText(null);
        alertXacNhan.setContentText("Xác nhận xoá thông tin?");
        alertXacNhan.showAndWait();
        if (alertXacNhan.getResult() == ButtonType.OK) {
            try{
                String maSanPham = maSP_XoaSP_invisible.getText();
                String loaiSanPham = loaiSP_XoaSP.getText();
                String tenSanPham = tenSP_XoaSP.getText();
                int soLuong = Integer.parseInt(soLuongSP_XoaSP.getText());
                double donGia = Double.parseDouble(donGiaSP_XoaSP.getText());
                String trangThai = trangThaiSP_XoaSP.getText();

                SanPhamDb sanPham = new SanPhamDb(maSanPham, loaiSanPham, tenSanPham, soLuong, donGia, trangThai);
                if(sanPhamDAO.xoaSanPham(sanPham)){
                    Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
                    alertThanhCong.setTitle("THÔNG BÁO");
                    alertThanhCong.setHeaderText(null);
                    alertThanhCong.setContentText("XOÁ SẢN PHẨM THÀNH CÔNG");
                    alertThanhCong.showAndWait();
                    xacNhanXoaSP.getScene().getWindow().hide();
                }else{
                    Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                    alertLoi.setTitle("LỖI");
                    alertLoi.setContentText("không thể xoá sản phẩm");
                    alertLoi.showAndWait();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void huyBoHuyBoXoaSP(){
        Alert alertHuyBo = new Alert(Alert.AlertType.CONFIRMATION);
        alertHuyBo.setTitle("Huỷ bỏ xoá sản phẩm");
        alertHuyBo.setHeaderText(null);
        alertHuyBo.setContentText("Xác nhận huỷ bỏ?");
        alertHuyBo.showAndWait();
        if (alertHuyBo.getResult() == ButtonType.OK) {
            Stage stage = (Stage) huyBoXoaSP.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        layThongTinMaSp();
        maSP_XoaSP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    dienThongTinKhiChonMaSP(newValue);
                }
            }
        });
    }
}

