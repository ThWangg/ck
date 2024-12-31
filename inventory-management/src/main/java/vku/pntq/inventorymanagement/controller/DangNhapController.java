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

public class DangNhapController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passInput;

    @FXML
    private TextField usernameInput;

    private AdminDAO adminDAO;

    public DangNhapController(){
        adminDAO = new AdminDAO();
    }
    @FXML
    public void loginAdm() {
        String username = usernameInput.getText();
        String password = passInput.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
            alertLoiThieuThongTin.setTitle("lỗi");
            alertLoiThieuThongTin.setHeaderText(null);
            alertLoiThieuThongTin.setContentText("Điền đầy đủ thông tin");
            alertLoiThieuThongTin.showAndWait();
            return;
        }

        if (adminDAO.ktra(username, password)) {
            Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
            alertThanhCong.setTitle("thành công");
            alertThanhCong.setHeaderText(null);
            alertThanhCong.setContentText("ĐĂNG NHẬP THÀNH CÔNG");
            alertThanhCong.showAndWait();
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
            Alert alertSaiThongTin = new Alert(Alert.AlertType.ERROR);
            alertSaiThongTin.setTitle("lỗi");
            alertSaiThongTin.setHeaderText(null);
            alertSaiThongTin.setContentText("Sai tài khoản hoặc mật khẩu");
            alertSaiThongTin.showAndWait();
        }
    }
}
