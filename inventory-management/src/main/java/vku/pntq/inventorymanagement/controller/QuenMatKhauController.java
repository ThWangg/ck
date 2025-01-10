package vku.pntq.inventorymanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vku.pntq.inventorymanagement.DAO.AdminDAO;
import vku.pntq.inventorymanagement.model.AdminDb;
import vku.pntq.inventorymanagement.util.AlertUtil;

public class QuenMatKhauController {

    @FXML
    private TextField emailInput;

    @FXML
    private Label emailLabel;

    @FXML
    private Label maXacNhanLabel;

    @FXML
    private TextField maXacNhans;

    @FXML
    private TextField matKhauMoi;

    @FXML
    private Label matKhauMoiLabel;

    @FXML
    private Button tiepTuc1;

    @FXML
    private Button tiepTuc2;

    @FXML
    private Button xacNhan;

    @FXML
    private TextField xacNhanMatKhau;

    @FXML
    private Label xacNhanMatKhauLabel;


    private AdminDAO adminDAO = new AdminDAO();
    private AlertUtil alertUtil = new AlertUtil();

    @FXML
    public void guiMaXacNhan() {
        String email = emailInput.getText();

        if (email.isEmpty()){
            alertUtil.alertLoi("LỖI", "Điền đầy đủ thông tin");
            return;
        }

        boolean thanhCong = adminDAO.guiMaResetMatKhau(email);
        if(thanhCong){
            alertUtil.alertThongBao("THÔNG BÁO", "Mã đã được gửi về mail");
            emailLabel.setVisible(false);
            emailInput.setVisible(false);
            tiepTuc1.setVisible(false);
            tiepTuc1.setLayoutX(500);
            tiepTuc1.setLayoutX(500);
            maXacNhanLabel.setVisible(true);
            maXacNhans.setVisible(true);
            tiepTuc2.setVisible(true);
            System.out.println("dang nhan nut 1");
        }else{
            alertUtil.alertLoi("LỖI", "Đã có lỗi xảy ra");
        }
    }

    public void thayDoiMatKhau(){
        String maXacNhan = adminDAO.getMaXacNhanDaGui();

        System.out.println("dang nhan nut 2");

        if(maXacNhans.getText().isEmpty()){
            alertUtil.alertLoi("LỖI", "Bạn chưa điền mã xác nhận");
        }
        if(maXacNhans.getText().equals(maXacNhan)){
            maXacNhanLabel.setVisible(false);
            maXacNhans.setVisible(false);
            tiepTuc2.setVisible(false);
            tiepTuc2.setLayoutX(500);
            tiepTuc2.setLayoutX(500);
            matKhauMoi.setVisible(true);
            matKhauMoiLabel.setVisible(true);
            xacNhanMatKhau.setVisible(true);
            xacNhanMatKhauLabel.setVisible(true);
            xacNhan.setVisible(true);
        }else{
            alertUtil.alertLoi("LỖI", "Mã xác nhận không khớp");
        }
    }

    public void xacNhanDoiMatKhau(){
        if(matKhauMoi.getText().isEmpty() || xacNhanMatKhau.getText().isEmpty()){
            alertUtil.alertLoi("LỖI", "Điền đầy đủ thông tin");
            return;
        }
        if(xacNhanMatKhau.getText().equals(matKhauMoi.getText())){
            String password = xacNhanMatKhau.getText();
            String email = emailInput.getText();

            AdminDb admin = new AdminDb(password, email);
            if(adminDAO.suaMatKhau(admin)){
                alertUtil.alertThongBao("THÔNG BÁO", "Mật khẩu đã được thay đổi");
                xacNhan.getScene().getWindow().hide();
            }else{
                System.out.println("pass " + password);
                System.out.println("email " + email);
            }
        }else{
            alertUtil.alertLoi("LỖI", "Mật khẩu không trùng nhau");
        }
    }
}