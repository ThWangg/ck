package vku.pntq.inventorymanagement.controller;

import vku.pntq.inventorymanagement.DAO.NhaCungCapDAO;
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

public class suaNCCController implements Initializable{

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

    // bỏ dữ liệu vô combobox
    public void layThongTinMaNCC(){
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
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁO");
        alertXacNhan.setContentText("xác nhận sửa thông tin?");
        alertXacNhan.showAndWait();
        if (alertXacNhan.getResult() == ButtonType.OK) {
            if (sdtNCC_SuaNCC.getText().isEmpty() || tenNCC_SuaNCC.getText().isEmpty() || diaChiNCC_SuaNCC.getText().isEmpty() || maNCCMoi_SuaNCC.getText().isEmpty()) {
                Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
                alertLoiThieuThongTin.setTitle("LỖI");
                alertLoiThieuThongTin.setHeaderText(null);
                alertLoiThieuThongTin.setContentText("điền đầy đủ thông tin cần thiết");
                alertLoiThieuThongTin.showAndWait();
            } else {
                String maNCCCu = maNCC_SuaNCC.getValue();
                String maNCCMoi = maNCCMoi_SuaNCC.getText();
                String tenNCC = tenNCC_SuaNCC.getText();
                String diaChi = diaChiNCC_SuaNCC.getText();
                int sdt = 0;

                try {
                    sdt = Integer.parseInt(sdtNCC_SuaNCC.getText());
                } catch (NumberFormatException e) {
                    Alert alertSoLuong = new Alert(Alert.AlertType.ERROR);
                    alertSoLuong.setTitle("LỖI");
                    alertSoLuong.setHeaderText(null);
                    alertSoLuong.setContentText("số lượng không hợp lệ");
                    alertSoLuong.showAndWait();
                    return;
                }

                NhaCungCapDb nhaCungCap = new NhaCungCapDb(maNCCMoi, tenNCC, sdt, diaChi, maNCCCu);
                if (nhaCungCapDAO.suaNCC(nhaCungCap)) {
                    Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
                    alertThanhCong.setTitle("THÔNG BÁO");
                    alertThanhCong.setHeaderText(null);
                    alertThanhCong.setContentText("SỬA NHÀ CUNG CẤP THÀNH CÔNG");
                    alertThanhCong.showAndWait();
                    xacNhanSuaNCC.getScene().getWindow().hide();
                } else {
                    Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                    alertLoi.setTitle("LỖI");
                    alertLoi.setContentText("không thể sửa nhà cung cấp");
                    alertLoi.showAndWait();
                }
            }
        }
    }


    public void huyBoSuaNCC(){
        Alert alertHuyBo = new Alert(Alert.AlertType.CONFIRMATION);
        alertHuyBo.setTitle("Huỷ bỏ sửa nhà cung cấp");
        alertHuyBo.setHeaderText(null);
        alertHuyBo.setContentText("Xác nhận huỷ bỏ?");
        alertHuyBo.showAndWait();
        if (alertHuyBo.getResult() == ButtonType.OK) {
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
