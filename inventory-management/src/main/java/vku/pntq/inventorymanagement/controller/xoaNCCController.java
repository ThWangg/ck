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

public class xoaNCCController implements Initializable{

    @FXML
    private TextField diaChiNCC_XoaNCC;

    @FXML
    private Button huyBoXoaNCC;

    @FXML
    private ComboBox<String> maNCC_XoaNCC;

    @FXML
    private TextField maNCC_XoaNCC_Invinsible;

    @FXML
    private TextField sdtNCC_XoaNCC;

    @FXML
    private TextField tenNCC_XoaNCC;

    @FXML
    private Button xacNhanXoaNCC;

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();


    public void layThongTinMaNCC(){
        List<String> danhSachMaNCC = nhaCungCapDAO.layDanhSachMaNhaCungCap();
        maNCC_XoaNCC.getItems().addAll(danhSachMaNCC);
    }

    public void dienThongTinKhiChonNCC(String maNCC) {
        NhaCungCapDb nhaCungCap = nhaCungCapDAO.layThongTinNhaCungCap(maNCC);
        if (nhaCungCap != null) {
            maNCC_XoaNCC_Invinsible.setText(nhaCungCap.getMaNhaCungCap());
            tenNCC_XoaNCC.setText(nhaCungCap.getTenNhaCungCap());
            sdtNCC_XoaNCC.setText(String.valueOf(nhaCungCap.getSoDienThoai()));
            diaChiNCC_XoaNCC.setText(nhaCungCap.getDiaChi());
        }
    }

    public void xacNhanXoaNCC(){
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁOS");
        alertXacNhan.setHeaderText(null);
        alertXacNhan.setContentText("Xác nhận xoá thông tin?");
        alertXacNhan.showAndWait();
        if (alertXacNhan.getResult() == ButtonType.OK) {
            try {
                String maNCC = maNCC_XoaNCC_Invinsible.getText();
                String tenNC = tenNCC_XoaNCC.getText();
                String diaChi = diaChiNCC_XoaNCC.getText();
                int sdt = Integer.parseInt(sdtNCC_XoaNCC.getText());

                NhaCungCapDb nhaCungCap = new NhaCungCapDb(maNCC, tenNC, sdt, diaChi);
                if (nhaCungCapDAO.xoaNhaCungCap(nhaCungCap)){
                    Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
                    alertThanhCong.setTitle("THÔNG BÁO");
                    alertThanhCong.setHeaderText(null);
                    alertThanhCong.setContentText("XOÁ NHÀ CUNG CẤP THÀNH CÔNG");
                    alertThanhCong.showAndWait();
                    xacNhanXoaNCC.getScene().getWindow().hide();
                }
                else{
                    Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                    alertLoi.setTitle("LỖI");
                    alertLoi.setContentText("không thể xoá nhà cung cấp");
                    alertLoi.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void huyBoHuyBoXoaNCC(){
        Alert alertHuyBo = new Alert(Alert.AlertType.CONFIRMATION);
        alertHuyBo.setTitle("Huỷ bỏ xoá nhà cung cấp");
        alertHuyBo.setHeaderText(null);
        alertHuyBo.setContentText("Xác nhận huỷ bỏ?");
        alertHuyBo.showAndWait();
        if (alertHuyBo.getResult() == ButtonType.OK) {
            Stage stage = (Stage) huyBoXoaNCC.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        layThongTinMaNCC();
        maNCC_XoaNCC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    dienThongTinKhiChonNCC(newValue);
                }
            }
        });
    }
}
