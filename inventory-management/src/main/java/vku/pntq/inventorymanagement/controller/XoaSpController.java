package vku.pntq.inventorymanagement.controller;

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
    private TextField maNcc_XoaSP;

    @FXML
    private Button xacNhanXoaSP;

    private SanPhamDAO sanPhamDAO = new SanPhamDAO();
    private AlertUtil alertUtil = new AlertUtil();

    public void layThongTinMaSp(){
        maSP_XoaSP.getItems().clear();
        List<String> danhSachMaSP = sanPhamDAO.layDanhSachMaSanPham();
        maSP_XoaSP.getItems().addAll(danhSachMaSP);
    }


    public void dienThongTinKhiChonMaSP(String maSP) {
        SanPhamDb sanPham = sanPhamDAO.layThongTinSanPham(maSP);
        if (sanPham != null) {
            maSP_XoaSP_invisible.setText(sanPham.getMaSanPham());
            tenSP_XoaSP.setText(sanPham.getTenSanPham());
            loaiSP_XoaSP.setText(sanPham.getLoaiSanPham());
            maNcc_XoaSP.setText(sanPham.getMa_ncc());
            donGiaSP_XoaSP.setText(String.valueOf(sanPham.getDonGia()));
            soLuongSP_XoaSP.setText(String.valueOf(sanPham.getSoLuong()));
        }
    }

    public void xacNhanXoaSP(){
        boolean xacNhan = alertUtil.alertXacNhan("LỖI", "Xác nhận xoá thông tin ?");
        if(xacNhan){
            try{
                String maSanPham = maSP_XoaSP_invisible.getText();
                String loaiSanPham = loaiSP_XoaSP.getText();
                String tenSanPham = tenSP_XoaSP.getText();
                String maNCC = maNcc_XoaSP.getText();
                int soLuong = Integer.parseInt(soLuongSP_XoaSP.getText());
                int donGia = Integer.parseInt((donGiaSP_XoaSP.getText()));

                SanPhamDb sanPham = new SanPhamDb(maSanPham, loaiSanPham, tenSanPham, maNCC, soLuong, donGia);
                if(sanPhamDAO.xoaSanPham(sanPham)){
                    alertUtil.alertThongBao("THÔNG BÁO", "Xoá sản phẩm thành công");
                    xacNhanXoaSP.getScene().getWindow().hide();
                }else{
                    alertUtil.alertLoi("LỖI", "Không thể xoá sản phẩm");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void huyBoHuyBoXoaSP(){
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận huỷ bỏ ?");
        if(xacNhan){
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

