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

public class XoaNCCController implements Initializable{

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
    private AlertUtil alertUtil = new AlertUtil();


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
        boolean xacNhan = alertUtil.alertXacNhan("LỖI", "Xác nhận xoá thông tin ?");
        if(xacNhan){
            try{
                String maNCC = maNCC_XoaNCC_Invinsible.getText();
                String tenNC = tenNCC_XoaNCC.getText();
                String diaChi = diaChiNCC_XoaNCC.getText();
                int sdt = Integer.parseInt(sdtNCC_XoaNCC.getText());
                NhaCungCapDb nhaCungCap = new NhaCungCapDb(maNCC, tenNC, sdt, diaChi);
                if(nhaCungCapDAO.xoaNhaCungCap(nhaCungCap)){
                    alertUtil.alertThongBao("THÔNG BÁO", "Xoá nhà cung cấp thành công");
                    xacNhanXoaNCC.getScene().getWindow().hide();
                }
                else{
                    alertUtil.alertLoi("LỖI", "Không thể xoá nhà cung cấp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void huyBoHuyBoXoaNCC(){
        boolean xacNhan = alertUtil.alertXacNhan("THÔNG BÁO", "Xác nhận huỷ bỏ ?");
        if(xacNhan){
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
