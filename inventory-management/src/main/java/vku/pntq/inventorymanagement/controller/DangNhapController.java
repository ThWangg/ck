package vku.pntq.inventorymanagement.controller;

import vku.pntq.inventorymanagement.DAO.AdminDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import vku.pntq.inventorymanagement.util.AlertUtil;

public class DangNhapController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passInput;

    @FXML
    private TextField usernameInput;

    private AdminDAO adminDAO;

    private AlertUtil alertUtil = new AlertUtil();

    public DangNhapController(){
        adminDAO = new AdminDAO();
    }
    @FXML
    public void loginAdm() {
        String usernameHoacEmail = usernameInput.getText();
        String password = passInput.getText();
        if (usernameHoacEmail.isEmpty() || password.isEmpty()) {
            Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
            alertLoiThieuThongTin.setTitle("lỗi");
            alertLoiThieuThongTin.setHeaderText(null);
            alertLoiThieuThongTin.setContentText("Điền đầy đủ thông tin");
            alertLoiThieuThongTin.showAndWait();
            return;
        }

        String email = null;
        String username = null;

        if ((usernameHoacEmail.contains(".com") && usernameHoacEmail.contains("@"))){
            email = usernameHoacEmail;
        } else {
            username = usernameHoacEmail;
        }

        if (adminDAO.ktra(username, email, password)) {
            alertUtil.alertThongBao("THÔNG BÁO", "ĐĂNG NHẬP THÀNH CÔNG");
            loginButton.getScene().getWindow().hide();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/dashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch(Exception e) {
                e.printStackTrace();
            }

        }else {
            alertUtil.alertThongBao("LỖI", "SAI TÀI KHOẢN HOẶC MẬT KHẨU");
        }
    }


    @FXML
    public void quenMatKhau() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/QuenMk.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
