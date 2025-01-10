package vku.pntq.inventorymanagement.controller;

import vku.pntq.inventorymanagement.DAO.NhaCungCapDAO;
import vku.pntq.inventorymanagement.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vku.pntq.inventorymanagement.util.AlertUtil;

public class ThemNCCController {

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
    private AlertUtil alertUtil = new AlertUtil();

    public void themNCCMoi(){
        if(maNCC_ThemNCC.getText().isEmpty() || tenNCC_ThemNCC.getText().isEmpty() || diaChiNCC_ThemNCC.getText().isEmpty() || sdtNCC_ThemNCC.getText().isEmpty()){
            alertUtil.alertLoi("LỖI", "Điền đầy đủ thông tin cần thiết");
        }else{
            String maNCC = maNCC_ThemNCC.getText();
            String tenNCC = tenNCC_ThemNCC.getText();
            String diaChi = diaChiNCC_ThemNCC.getText();
            int sdt = 0;

            try{
                sdt = Integer.parseInt(sdtNCC_ThemNCC.getText());
            }catch(NumberFormatException e){
                alertUtil.alertLoi("LỖI", "Số điện thoại không hợp lệ");
            }

            NhaCungCapDb nhaCungCap = new NhaCungCapDb(maNCC, tenNCC, sdt, diaChi);
            if(nhaCungCapDAO.themNCC(nhaCungCap)){
                alertUtil.alertThongBao("THÔNG BÁO", "Thêm nhà cung cấp mới thành công");
                maNCC_ThemNCC.setText("");
                tenNCC_ThemNCC.setText("");
                sdtNCC_ThemNCC.setText("");
                diaChiNCC_ThemNCC.setText("");
            }else{
                alertUtil.alertLoi("LỖI", "Không thể thêm nhà cung cấp mới");
            }
        }
    }

    public void huyBoThemNCC(){
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận huỷ bỏ ?");
        if(xacNhan){
            Stage stage = (Stage) huyBoThemNCC.getScene().getWindow();
            stage.close();
        }
    }
}
