package vku.pntq.inventorymanagement.controller;

import vku.pntq.inventorymanagement.DAO.NhaCungCapDAO;
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

public class SuaNCCController implements Initializable{

    @FXML
    private TextField diaChiNCC_SuaNCC;

    @FXML
    private Button huyBoSuaNCC;

    @FXML
    private TextField maNCCMoi_SuaNCC;

    @FXML
    private ComboBox<String> maNCC_SuaNCC;

    @FXML
    private TextField sdtNCC_SuaNCC;

    @FXML
    private TextField tenNCC_SuaNCC;

    @FXML
    private Button xacNhanSuaNCC;

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private AlertUtil alertUtil = new AlertUtil();

    // bỏ dữ liệu vô combobox
    public void layThongTinMaNCC(){
        maNCC_SuaNCC.getItems().clear();
        List<String> danhSachMaNCC = nhaCungCapDAO.layDanhSachMaNhaCungCap();
        maNCC_SuaNCC.getItems().addAll(danhSachMaNCC);
    }

    public void dienThongTinKhiChonNCC(String maNCC) {
        NhaCungCapDb nhaCungCap = nhaCungCapDAO.layThongTinNhaCungCap(maNCC);
        if (nhaCungCap != null) {
            maNCCMoi_SuaNCC.setText(nhaCungCap.getMaNhaCungCap());
            tenNCC_SuaNCC.setText(nhaCungCap.getTenNhaCungCap());
            sdtNCC_SuaNCC.setText(String.valueOf(nhaCungCap.getSoDienThoai()));
            diaChiNCC_SuaNCC.setText(nhaCungCap.getDiaChi());
        }
    }

    public void xacNhanSuaNCC() {
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận sửa thông tin ?");
        if(xacNhan){
            if(sdtNCC_SuaNCC.getText().isEmpty() || tenNCC_SuaNCC.getText().isEmpty() || diaChiNCC_SuaNCC.getText().isEmpty() || maNCCMoi_SuaNCC.getText().isEmpty()) {
                alertUtil.alertLoi("LỖI", "Điền đầy đủ thông tin cần thiết");
            }else {
                String maNCCCu = maNCC_SuaNCC.getValue();
                String maNCCMoi = maNCCMoi_SuaNCC.getText();
                String tenNCC = tenNCC_SuaNCC.getText();
                String diaChi = diaChiNCC_SuaNCC.getText();
                int sdt = 0;

                try{
                    sdt = Integer.parseInt(sdtNCC_SuaNCC.getText());
                }catch(NumberFormatException e) {
                    alertUtil.alertLoi("LỖI", "Lỗi số lượng");
                    return;
                }

                NhaCungCapDb nhaCungCap = new NhaCungCapDb(maNCCMoi, tenNCC, sdt, diaChi, maNCCCu);
                if(nhaCungCapDAO.suaNCC(nhaCungCap)) {
                    alertUtil.alertThongBao("THÔNG BÁO", "Sửa nhà cung cấp thành công");
                    xacNhanSuaNCC.getScene().getWindow().hide();
                }else {
                    alertUtil.alertLoi("LỖI", "Không thể sửa thông tin nhà cung cấp");
                }
            }
        }
    }


    public void huyBoSuaNCC(){
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận huỷ bỏ ?");
        if(xacNhan){
            Stage stage = (Stage) huyBoSuaNCC.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        layThongTinMaNCC();
        maNCC_SuaNCC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    dienThongTinKhiChonNCC(newValue);
                }
            }
        });
    }
}
