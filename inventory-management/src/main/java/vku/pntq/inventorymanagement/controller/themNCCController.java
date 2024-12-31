package vku.pntq.inventorymanagement.controller;

import vku.pntq.inventorymanagement.DAO.NhaCungCapDAO;
import vku.pntq.inventorymanagement.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class themNCCController{

    @FXML
    private TextField diaChiNCC_ThemNCC;

    @FXML
    private Button huyBoThemNCC;

    @FXML
    private TextField maNCC_ThemNCC;

    @FXML
    private TextField sdtNCC_ThemNCC;

    @FXML
    private TextField tenNCC_ThemNCC;

    @FXML
    private Button xacNhanThemNCC;

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();

//    public void themNCCMoi() {
//        String sql = "INSERT INTO nhacungcap (ma_ncc, ten_ncc, sdt, dia_chi) VALUES (?, ?, ?, ?)";
//        Connection connect = database.connectionDb();
//        try {
//            PreparedStatement prepare = connect.prepareStatement(sql);
//            prepare.setString(1, maNCC_ThemNCC.getText());
//            prepare.setString(2, tenNCC_ThemNCC.getText());
//            try {
//                prepare.setInt(3, Integer.parseInt((sdtNCC_ThemNCC.getText())));
//            } catch (NumberFormatException e) {
//                Alert alertLoiSo = new Alert(Alert.AlertType.ERROR);
//                alertLoiSo.setTitle("Lỗi");
//                alertLoiSo.setHeaderText(null);
//                alertLoiSo.setContentText("số điện thoại k chứa kí tự");
//                alertLoiSo.showAndWait();
//            }
//            prepare.setString(4, diaChiNCC_ThemNCC.getText());
//            if(maNCC_ThemNCC.getText().isEmpty() || tenNCC_ThemNCC.getText().isEmpty() || sdtNCC_ThemNCC.getText().isEmpty() || diaChiNCC_ThemNCC.getText().isEmpty()) {
//                Alert alertKhongDuThongTin = new Alert(Alert.AlertType.ERROR);
//                alertKhongDuThongTin.setTitle("Lỗi");
//                alertKhongDuThongTin.setHeaderText(null);
//                alertKhongDuThongTin.setContentText("điền đầy đủ thông tin cần thiết");
//                alertKhongDuThongTin.showAndWait();
//            }else{
//                prepare.executeUpdate();
//                Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
//                alertThanhCong.setTitle("Thêm nhà cung cấp thành công");
//                alertThanhCong.setHeaderText(null);
//                alertThanhCong.setContentText("nhà cung cấp đã được thêm thành công.");
//                alertThanhCong.showAndWait();
//                maNCC_ThemNCC.setText("");
//                tenNCC_ThemNCC.setText("");
//                sdtNCC_ThemNCC.setText("");
//                diaChiNCC_ThemNCC.setText("");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void themNCCMoi(){
        if(maNCC_ThemNCC.getText().isEmpty() || tenNCC_ThemNCC.getText().isEmpty() || diaChiNCC_ThemNCC.getText().isEmpty() || sdtNCC_ThemNCC.getText().isEmpty()){
            Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
            alertLoiThieuThongTin.setTitle("LỖI");
            alertLoiThieuThongTin.setHeaderText(null);
            alertLoiThieuThongTin.setContentText("điền đầy đủ thông tin cần thiết");
            alertLoiThieuThongTin.showAndWait();
        }else{
            String maNCC = maNCC_ThemNCC.getText();
            String tenNCC = tenNCC_ThemNCC.getText();
            String diaChi = diaChiNCC_ThemNCC.getText();
            int sdt = 0;

            try{
                sdt = Integer.parseInt(sdtNCC_ThemNCC.getText());
            }catch(NumberFormatException e){
                Alert alertSoLuong = new Alert(Alert.AlertType.ERROR);
                alertSoLuong.setTitle("LỖI");
                alertSoLuong.setHeaderText(null);
                alertSoLuong.setContentText("số điện thoại không hợp lệ");
                alertSoLuong.showAndWait();
            }

            NhaCungCapDb nhaCungCap = new NhaCungCapDb(maNCC, tenNCC, sdt, diaChi);
            if(nhaCungCapDAO.themNCC(nhaCungCap)){
                Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
                alertThanhCong.setTitle("THÔNG BÁO");
                alertThanhCong.setHeaderText(null);
                alertThanhCong.setContentText("thêm nhà cung mới thành công");
                alertThanhCong.showAndWait();
                maNCC_ThemNCC.setText("");
                tenNCC_ThemNCC.setText("");
                sdtNCC_ThemNCC.setText("");
                diaChiNCC_ThemNCC.setText("");
            }else{
                Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                alertLoi.setTitle("LỖI");
                alertLoi.setHeaderText(null);
                alertLoi.setContentText("không thể thêm nhà cung cấp");
                alertLoi.showAndWait();
            }
        }
    }

    public void huyBoThemNCC(){
        Alert alertHuyBo = new Alert(Alert.AlertType.CONFIRMATION);
        alertHuyBo.setTitle("THÔNG BÁO");
        alertHuyBo.setHeaderText(null);
        alertHuyBo.setContentText("xác nhận huỷ bỏ?");
        alertHuyBo.showAndWait();
        if(alertHuyBo.getResult() == ButtonType.OK){
            Stage stage = (Stage) huyBoThemNCC.getScene().getWindow();
            stage.close();
        }
    }
}
