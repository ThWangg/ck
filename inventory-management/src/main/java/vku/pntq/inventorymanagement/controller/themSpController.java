package vku.pntq.inventorymanagement.controller;

import vku.pntq.inventorymanagement.DAO.NhaCungCapDAO;
import vku.pntq.inventorymanagement.DAO.SanPhamDAO;
import vku.pntq.inventorymanagement.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vku.pntq.inventorymanagement.util.AlertUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ThemSpController implements Initializable{

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

    @FXML ComboBox<String> maNccThemSP;

    @FXML
    private Button xacNhanThemSP;

    private SanPhamDAO sanPhamDAO = new SanPhamDAO();
    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private AlertUtil alertUtil = new AlertUtil();

    public void layThongTinMaNCC(){
        maNccThemSP.getItems().clear();
        List<String> danhSachMaNCC = nhaCungCapDAO.layDanhSachMaNhaCungCap();
        maNccThemSP.getItems().addAll(danhSachMaNCC);
    }

    @FXML
    public void themSanPham() {
        if(nhapMaThemSP.getText().isEmpty() || nhapLoaiThemSP.getText().isEmpty() || nhapTenThemSP.getText().isEmpty() || nhapSoLuongThemSP.getText().isEmpty() || nhapDonGiaThemSP.getText().isEmpty() || maNccThemSP.getValue() == null || maNccThemSP.getValue().isEmpty()){
            alertUtil.alertLoi("LỖI", "Điền đầy đủ thông tin cần thiết");
        }else{
            String maSanPham = nhapMaThemSP.getText();
            String loaiSanPham = nhapLoaiThemSP.getText();
            String tenSanPham = nhapTenThemSP.getText();
            int soLuong = 0;
            int donGia = 0;
            String maNcc = maNccThemSP.getValue();

            try{
                soLuong = Integer.parseInt(nhapSoLuongThemSP.getText());
            }catch(NumberFormatException e){
                alertUtil.alertLoi("LỖI", "Số lượng không hợp lệ");
                return;
            }

            try{
                donGia = Integer.parseInt((nhapDonGiaThemSP.getText()));
            }catch(NumberFormatException e) {
                alertUtil.alertLoi("LỖI", "Đơn giá không hợp lệ");
                return;
            }

            SanPhamDb sanPham = new SanPhamDb(maSanPham, loaiSanPham, tenSanPham, maNcc, soLuong, donGia);
            if(sanPhamDAO.themSanPham(sanPham)){
                alertUtil.alertThongBao("THÔNG BÁO", "Thêm sản phẩm mới thành công");
                nhapMaThemSP.setText("");
                nhapLoaiThemSP.setText("");
                nhapTenThemSP.setText("");
                nhapSoLuongThemSP.setText("");
                nhapDonGiaThemSP.setText("");
            }else{
                alertUtil.alertLoi("LỖI", "Không thể thêm sản phẩm mới");
            }
        }
    }

    public void huyBoThemSP(){
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận huỷ bỏ ?");
        if(xacNhan){
            Stage stage = (Stage) huyBoThemSP.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        layThongTinMaNCC();
    }
}
