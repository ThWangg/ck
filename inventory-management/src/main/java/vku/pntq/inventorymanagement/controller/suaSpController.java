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
import vku.pntq.inventorymanagement.util.AlertUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SuaSpController implements Initializable{

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
    private Button xacNhanSuaSP;

    private SanPhamDAO sanPhamDAO = new SanPhamDAO();

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private AlertUtil alertUtil = new AlertUtil();


    public void huyBoSuaSP() {
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận huỷ bỏ ?");
        if(xacNhan){
            Stage stage = (Stage) huyBoSuaSP.getScene().getWindow();
            stage.close();
        }
    }

    // bỏ dữ liệu vô combobox
    public void layThongTinMaSp(){
        maSP_SuaSP.getItems().clear();
        List<String> danhSachMaSP = sanPhamDAO.layDanhSachMaSanPham();
        maSP_SuaSP.getItems().addAll(danhSachMaSP);
    }
    public void layThongTinMaNCC(){
        List<String> danhSachMaNCC = nhaCungCapDAO.layDanhSachMaNhaCungCap();
        maNCC_SuaSP.getItems().addAll(danhSachMaNCC);
    }

    public void dienThongTinKhiChonMaSP(String maSP) {
        SanPhamDb sanPham = sanPhamDAO.layThongTinSanPham(maSP);
        if(sanPham != null) {
            nhapMaSPMoi_SuaSP.setText(sanPham.getMaSanPham());
            nhapTenSP_SuaSP.setText(sanPham.getTenSanPham());
            nhapLoaiSP_SuaSP.setText(sanPham.getLoaiSanPham());
            maNCC_SuaSP.setValue(sanPham.getMa_ncc());
            nhapDonGiaSP_SuaSP.setText(String.valueOf(sanPham.getDonGia()));
            nhapSoLuongSP_SuaSP.setText(String.valueOf(sanPham.getSoLuong()));
        }
    }

    public void xacNhanSuaSP() {
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận sửa thông tin ?");
        if(xacNhan){
            if(nhapMaSPMoi_SuaSP.getText().isEmpty() || nhapLoaiSP_SuaSP.getText().isEmpty() || nhapTenSP_SuaSP.getText().isEmpty() || nhapSoLuongSP_SuaSP.getText().isEmpty() || nhapDonGiaSP_SuaSP.getText().isEmpty()) {
                alertUtil.alertLoi("LỖI", "Điền đầy đủ thông tin cần thiết");
            }else{
                String maSanPhamCu = maSP_SuaSP.getValue();
                String maSanPhamMoi = nhapMaSPMoi_SuaSP.getText();
                String loaiSanPham = nhapLoaiSP_SuaSP.getText();
                String tenSanPham = nhapTenSP_SuaSP.getText();
                String maNCC = maNCC_SuaSP.getValue();
                int soLuong = 0;
                int donGia = 0;
                try {
                    soLuong = Integer.parseInt(nhapSoLuongSP_SuaSP.getText());
                }catch(NumberFormatException e) {
                    alertUtil.alertLoi("LỖI", "Số lượng không hợp lệ");
                    return;
                }

                try {
                    donGia = Integer.parseInt(nhapDonGiaSP_SuaSP.getText());
                }catch(NumberFormatException e) {
                    alertUtil.alertLoi("LỖI", "Đơn giá không hợp lệ");
                    return;
                }

                SanPhamDb sanPham = new SanPhamDb(maSanPhamMoi, loaiSanPham, tenSanPham, maNCC, soLuong, donGia, maSanPhamCu);
                if(sanPhamDAO.suaSanPham(sanPham)){
                    alertUtil.alertThongBao("THÔNG BÁO", "Sửa sản phẩm thành công");
                    xacNhanSuaSP.getScene().getWindow().hide();
                }else{
                    alertUtil.alertLoi("LỖI", "Không thể sửa sản phẩm");
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