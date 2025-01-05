package vku.pntq.inventorymanagement.controller;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import vku.pntq.inventorymanagement.DAO.NhaCungCapDAO;
import vku.pntq.inventorymanagement.DAO.PdfDao;
import vku.pntq.inventorymanagement.DAO.SanPhamDAO;
import vku.pntq.inventorymanagement.DAO.XuatHangDAO;
import vku.pntq.inventorymanagement.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private TextField NCC_timkiem;

    @FXML
    private TextField SP_timKiem;

    @FXML
    private TableView<NhaCungCapDb> bangNCC;

    @FXML
    private TableView<XuatHangDb> bangXuatHang;

    @FXML
    private TableView<SanPhamDb> bangSP_Home;

    @FXML
    private TableView<SanPhamDb> bangSP;

    @FXML
    private TableColumn<NhaCungCapDb, String> cotDiaChi_NCC;

    @FXML
    private TableColumn<SanPhamDb, Double> cotDonGia_Home;

    @FXML
    private TableColumn<SanPhamDb, Double> cotDonGia_XuatHang;

    @FXML
    private TableColumn<SanPhamDb, Double> cotDonGia_SP;

    @FXML
    private TableColumn<SanPhamDb, String> cotLoai_Home;

    @FXML
    private TableColumn<SanPhamDb, String> cotLoai_SP;

    @FXML
    private TableColumn<SanPhamDb, String> cotMaSP_XuatHang;

    @FXML
    private TableColumn<SanPhamDb, String> cotMaSp_Home;

    @FXML
    private TableColumn<NhaCungCapDb, String> cotMa_NCC;

    @FXML
    private TableColumn<SanPhamDb, String> cotMa_SP;

    @FXML
    private TableColumn<NhaCungCapDb, String> cotSDT_NCC;

    @FXML
    private TableColumn<SanPhamDb, String> cotMaNCC_SP;

    @FXML
    private TableColumn<SanPhamDb, String> cotMaNCC_Home;

    @FXML
    private TableColumn<SanPhamDb, Integer> cotSoLuong_Home;

    @FXML
    private TableColumn<SanPhamDb, Integer> cotSoLuong_XuatHang;

    @FXML
    private TableColumn<SanPhamDb, Integer> cotSoLuong_SP;

    @FXML
    private TableColumn<SanPhamDb, String> cotTenSP_XuatHang;

    @FXML
    private TableColumn<SanPhamDb, String> cotTen_Home;

    @FXML
    private TableColumn<NhaCungCapDb, String> cotTen_NCC;

    @FXML
    private TableColumn<SanPhamDb, String> cotTen_SP;

    @FXML
    private TableColumn<SanPhamDb, String> cotTrangThai_Home;

    @FXML
    private Button dangXuatButton;

//    @FXML
//    private Button hoaDon_Button;

    @FXML
    private Button homeButton;

    @FXML
    private Label home_DoanhThu;

    @FXML
    private Label home_NhaCungCap;

    @FXML
    private Label home_TongSoHang;

    @FXML
    private AnchorPane manHome;

    @FXML
    private AnchorPane manNCC;

    @FXML
    private AnchorPane manNhapXuat;

    @FXML
    private AnchorPane manSP;

    @FXML
    private Button nhaCungCapButton;

    @FXML
    private Button themHang_Button;

    @FXML
    private Button nhapXuatButton;

    @FXML
    private Button nhapXuatReset_Button;

    @FXML
    private TextField xuatHangSoLuongSP;

    @FXML
    private ComboBox<String> xuatHangTenSP;

    @FXML
    private Label xuatHangTongTien;

    @FXML
    private Button resetNCC_Button;

    @FXML
    private Button reset_Button;

    @FXML
    private Button sanPhamButton;

    @FXML
    private Button suaNCC_Button;

    @FXML
    private Button suaSP_Button;

    @FXML
    private Button themNCC_Button;

    @FXML
    private Button themSP_Button;

    @FXML
    private Button xoaNCC_Button;

    @FXML
    private Button xoaSP_Button;

    @FXML
    private Button xuatHang_Button;

    @FXML
    private Button xoaButton_NhapXuat;

    @FXML
    private TextField MaSP_XuatHang_Invis;

    @FXML
    private TextField DonGia_XuatHang_Invis;

    @FXML
    private ImageView reload_SP;

    @FXML
    private ImageView reload_NCC;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private SanPhamDAO sanPhamDAO = new SanPhamDAO();
    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private XuatHangDAO xuatHangDAO = new XuatHangDAO();

    public void dangXuat() {
        try {
            Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
            alertXacNhan.setTitle("Xác nhận");
            alertXacNhan.setHeaderText(null);
            alertXacNhan.setContentText("xác nhận đăng xuất ?");
            alertXacNhan.showAndWait();
            if (alertXacNhan.getResult() == ButtonType.OK) {
                dangXuatButton.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/LoginDesign.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doiManHienThi(ActionEvent event) {
        if(event.getSource() == homeButton) {
            manHome.setVisible(true);
            manSP.setVisible(false);
            manNCC.setVisible(false);
            manNhapXuat.setVisible(false);
            hienThiBangSP_Home();
        }else if(event.getSource() == sanPhamButton) {
            manHome.setVisible(false);
            manSP.setVisible(true);
            manNCC.setVisible(false);
            manNhapXuat.setVisible(false);
            hienThiBangSP_SP();
        }else if(event.getSource() == nhaCungCapButton) {
            manHome.setVisible(false);
            manSP.setVisible(false);
            manNCC.setVisible(true);
            manNhapXuat.setVisible(false);
            hienThiBangNCC();
        } else if(event.getSource() == nhapXuatButton) {
            manHome.setVisible(false);
            manSP.setVisible(false);
            manNCC.setVisible(false);
            manNhapXuat.setVisible(true);
            hienThiBangXuatHang();
        }
    }

    ///home
    public void hienThiBangSP_Home() {
        ObservableList<SanPhamDb> listSPHome = sanPhamDAO.layDuLieuBangSP_Home();
        bangSP_Home.setItems(listSPHome);
        cotMaSp_Home.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        cotTen_Home.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        cotLoai_Home.setCellValueFactory(new PropertyValueFactory<>("loaiSanPham"));
        cotMaNCC_Home.setCellValueFactory(new PropertyValueFactory<>("ma_ncc"));
        cotSoLuong_Home.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        cotDonGia_Home.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        cotTrangThai_Home.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }

    ///sản phẩm
    public void hienThiBangSP_SP(){
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        ObservableList<SanPhamDb> listSP_SP = sanPhamDAO.layDuLieuBangSP_SP();
        bangSP.setItems(listSP_SP);
        cotMa_SP.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        cotTen_SP.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        cotLoai_SP.setCellValueFactory(new PropertyValueFactory<>("loaiSanPham"));
        cotMaNCC_SP.setCellValueFactory(new PropertyValueFactory<>("ma_ncc"));
        cotSoLuong_SP.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        cotDonGia_SP.setCellValueFactory(new PropertyValueFactory<>("donGia"));
    }

    public void hienThiBangThemSP(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/themSP.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Thêm sản phẩm mới");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hienThiBangSuaSP() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/suaSP.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Sửa sản phâm");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hienThiBangXoaSP() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/xoaSP.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Xoá sản phâm");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void timKiemSP() {
        FilteredList<SanPhamDb> filter = new FilteredList<>(sanPhamDAO.layDuLieuBangSP_SP(), p -> true);
        SP_timKiem.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(sanPham -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (sanPham.getMaSanPham().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (sanPham.getLoaiSanPham().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (sanPham.getTenSanPham().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                try {
                    int soLuongTimKiem = Integer.parseInt(newValue);
                    if (sanPham.getSoLuong() == soLuongTimKiem) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
                try {
                    double donGiaTimKiem = Double.parseDouble(newValue);
                    if (sanPham.getDonGia() == donGiaTimKiem) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
                if (sanPham.getTrangThai().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        bangSP.setItems(filter);
    }

    public void reloadBangSP() {
        hienThiBangSP_SP();
    }

    public void resetBangSP(){
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁO");
        alertXacNhan.setHeaderText(null);
        alertXacNhan.setContentText("Xác nhận xoá tất cả ?");
        alertXacNhan.showAndWait();
        if(alertXacNhan.getResult() == ButtonType.OK){
            Alert alertXacNhanx2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertXacNhanx2.setTitle("XÁC NHẬN LẦN 2");
            alertXacNhanx2.setHeaderText(null);
            alertXacNhanx2.setContentText("bạn có chắc chắn muốn xoá tất cả ?");
            alertXacNhanx2.showAndWait();
            if(alertXacNhanx2.getResult() == ButtonType.OK){
                sanPhamDAO.xoaDuLieu();
                ObservableList<SanPhamDb> listSP = sanPhamDAO.layDuLieuBangSP_SP();
                bangSP.setItems(listSP);
            }
        }
    }

    ///nhà cung cấp
    public void hienThiBangNCC() {
        ObservableList<NhaCungCapDb> listNCC = nhaCungCapDAO.layDuLieuBangNCC();
        bangNCC.setItems(listNCC);
        cotMa_NCC.setCellValueFactory(new PropertyValueFactory<>("maNhaCungCap"));
        cotTen_NCC.setCellValueFactory(new PropertyValueFactory<>("tenNhaCungCap"));
        cotSDT_NCC.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        cotDiaChi_NCC.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    }

    public void hienThiBangThemNCC() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/themNCC.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Thêm nhà cung cấp");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hienThiBangXoaNCC() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/xoaNCC.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Xoá nhà cung cấp");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hienThiBangSuaNCC(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/suaNCC.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Sửa nhà cung cấp");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resetBangNCC(){
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁO");
        alertXacNhan.setHeaderText(null);
        alertXacNhan.setContentText("Xác nhận xoá tất cả ?");
        alertXacNhan.showAndWait();
        if(alertXacNhan.getResult() == ButtonType.OK){
            Alert alertXacNhanx2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertXacNhanx2.setTitle("XÁC NHẬN LẦN 2");
            alertXacNhanx2.setHeaderText(null);
            alertXacNhanx2.setContentText("bạn có chắc chắn muốn xoá tất cả ?");
            alertXacNhanx2.showAndWait();
            if(alertXacNhanx2.getResult() == ButtonType.OK){
                nhaCungCapDAO.xoaDuLieu();
                ObservableList<NhaCungCapDb> listNCC = nhaCungCapDAO.layDuLieuBangNCC();
                bangNCC.setItems(listNCC);
            }
        }
    }

    public void reloadBangNCC() {
        hienThiBangNCC();
    }

    public void timKiemNCC() {
        FilteredList<NhaCungCapDb> filter = new FilteredList<>(nhaCungCapDAO.layDuLieuBangNCC(), p -> true);
        NCC_timkiem.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(nhaCungCap -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (nhaCungCap.getMaNhaCungCap().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (nhaCungCap.getTenNhaCungCap().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (nhaCungCap.getDiaChi().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                try {
                    int sdtTimKiem = Integer.parseInt(newValue);
                    if (nhaCungCap.getSoDienThoai() == sdtTimKiem) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
                return false;
            });
        });
        bangNCC.setItems(filter);
    }

    ///xuất hàng
    public void hienThiBangXuatHang() {
        ObservableList<XuatHangDb> hienThiBangXuatHang = xuatHangDAO.layDuLieuBangXuatHang();
        cotMaSP_XuatHang.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        cotTenSP_XuatHang.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        cotSoLuong_XuatHang.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        cotDonGia_XuatHang.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        bangXuatHang.setItems(hienThiBangXuatHang);
    }

    public void layThongTinTenSP() {
        List<String> danhSachTenSP = sanPhamDAO.layDanhSachTenSanPham();
        xuatHangTenSP.getItems().addAll(danhSachTenSP);
    }

    //invinsible
    public void dienThongTinKhiChonTenSP(String tenSanPham) {
//        System.out.println("ten sp " + tenSanPham);
        SanPhamDb sanPham = sanPhamDAO.layThongTinSanPhamTuTenSP(tenSanPham);
        if(sanPham != null){
            DonGia_XuatHang_Invis.setText(String.valueOf(sanPham.getDonGia()));
            MaSP_XuatHang_Invis.setText(sanPham.getMaSanPham());
        }else{
//            System.out.println("k tim thay" + tenSanPham);
        }
    }
    //
    public void themHangVaoXuatHang(){
        if(xuatHangTenSP.getValue().isEmpty()) {
            Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
            alertLoiThieuThongTin.setTitle("LỖI");
            alertLoiThieuThongTin.setHeaderText(null);
            alertLoiThieuThongTin.setContentText("hãy chọn hàng");
            alertLoiThieuThongTin.showAndWait();
        }
        if(xuatHangSoLuongSP.getText().isEmpty()){
            Alert alertLoiThieuThongTin = new Alert(Alert.AlertType.ERROR);
            alertLoiThieuThongTin.setTitle("LỖI");
            alertLoiThieuThongTin.setHeaderText(null);
            alertLoiThieuThongTin.setContentText("điền đầy đủ thông tin");
            alertLoiThieuThongTin.showAndWait();
        }else{
            String maSP = MaSP_XuatHang_Invis.getText();
            String tenSP = xuatHangTenSP.getValue();
            int soLuong = 0;
            double donGia = Double.parseDouble(DonGia_XuatHang_Invis.getText());

            try{
                soLuong = Integer.parseInt(xuatHangSoLuongSP.getText());
            }catch(NumberFormatException e) {
                Alert alertSoLuong = new Alert(Alert.AlertType.ERROR);
                alertSoLuong.setTitle("LỖI");
                alertSoLuong.setHeaderText(null);
                alertSoLuong.setContentText("số lượng không hợp lệ");
                alertSoLuong.showAndWait();
                System.out.println(xuatHangSoLuongSP);
                return;
            }

            int soLuongTonKho = sanPhamDAO.laySoLuongSanPham(maSP);

            if (soLuong > soLuongTonKho) {
                Alert alertSoLuongKhongHopLe = new Alert(Alert.AlertType.ERROR);
                alertSoLuongKhongHopLe.setTitle("LỖI");
                alertSoLuongKhongHopLe.setHeaderText(null);
                alertSoLuongKhongHopLe.setContentText("số lượng sản phẩm vượt quá số lượng có sẵn");
                alertSoLuongKhongHopLe.showAndWait();
                return;
            }
            XuatHangDb xuatHang = new XuatHangDb(maSP, tenSP, soLuong, donGia);
            if(xuatHangDAO.themHangVaoXuatHang(xuatHang)){
                Alert alertThanhCong = new Alert(Alert.AlertType.INFORMATION);
                alertThanhCong.setTitle("THÔNG BÁO");
                alertThanhCong.setHeaderText(null);
                alertThanhCong.setContentText("thêm sản phẩm thành công");
                alertThanhCong.showAndWait();
                xuatHangSoLuongSP.setText("");
            }else{
                Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                alertLoi.setTitle("LỖI");
                alertLoi.setHeaderText(null);
                alertLoi.setContentText("không thể thêm sản phẩm");
                alertLoi.showAndWait();
            }
        }
    }

    public void tinhTongTienXuatHang(){
        double tongTien = xuatHangDAO.tinhTongTienXuatHang();
        xuatHangTongTien.setText(String.valueOf(tongTien));
    }

    public void xoaHang(){
        XuatHangDb sanPham = bangXuatHang.getSelectionModel().getSelectedItem();
        if(sanPham != null){
            Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
            alertXacNhan.setTitle("THÔNG BÁOS");
            alertXacNhan.setHeaderText(null);
            alertXacNhan.setContentText("Xác nhận xoá thông tin?");
            alertXacNhan.showAndWait();
            if(alertXacNhan.getResult() == ButtonType.OK) {
                if(xuatHangDAO.xoaSanPhamXH(sanPham)){
                    bangXuatHang.getItems().remove(sanPham);
                }else{
                    Alert alertLoi = new Alert(Alert.AlertType.ERROR);
                    alertLoi.setTitle("lỗi");
                    alertLoi.setHeaderText(null);
                    alertLoi.setContentText("không thể xóa sản phẩm");
                    alertLoi.showAndWait();
                }
            }
        }else{
            Alert alertChonSP = new Alert(Alert.AlertType.WARNING);
            alertChonSP.setTitle("THÔNG BÁO");
            alertChonSP.setHeaderText(null);
            alertChonSP.setContentText("Vui lòng chọn sản phẩm để xoá");
            alertChonSP.showAndWait();
        }
    }

    public void resetXuatHang(){
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁO");
        alertXacNhan.setHeaderText(null);
        alertXacNhan.setContentText("Xác nhận xoá tất cả ?");
        alertXacNhan.showAndWait();
        if(alertXacNhan.getResult() == ButtonType.OK){
            xuatHangDAO.xoaDuLieu();
            ObservableList<XuatHangDb> hienThiBangXuatHang = xuatHangDAO.layDuLieuBangXuatHang();
            hienThiBangXuatHang.clear();
            bangXuatHang.setItems(hienThiBangXuatHang);
        }
    }

    private PdfDao pdfDao = new PdfDao();

    public void xuatHang(){
        Alert alertXacNhan = new Alert(Alert.AlertType.CONFIRMATION);
        alertXacNhan.setTitle("THÔNG BÁO");
        alertXacNhan.setHeaderText(null);
        alertXacNhan.setContentText("Hàng đã được xuất đi. Bạn có muốn xem hoá đơn không?");
        alertXacNhan.showAndWait();
        if(alertXacNhan.getResult() == ButtonType.OK) {
            try {

                ObservableList<XuatHangDb> bangXuatHangg = xuatHangDAO.layDuLieuBangXuatHang();

                for(XuatHangDb sanPham: bangXuatHangg){
                    String maSP = sanPham.getMaSanPham();
                    int soLuongXuat = sanPham.getSoLuong();

                    boolean capNhat = xuatHangDAO.capNhatSoLuongSanPham(maSP, soLuongXuat);

                    if(!capNhat){
                        Alert alertThatBai = new Alert(Alert.AlertType.ERROR);
                        alertThatBai.setTitle("Lỗi");
                        alertThatBai.setHeaderText(null);
                        alertThatBai.setContentText("không cập nhật đc số lượng sản phẩm" + maSP);
                        alertThatBai.showAndWait();
                        return;
                    }
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vku/pntq/inventorymanagement/fxml/HoaDon.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                WritableImage writableImage = scene.snapshot(null);

                pdfDao.luuThanhPDF(writableImage, "D:/test/hoadon.pdf");

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông Báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Hoá đơn đã được xuất thành công!");
                successAlert.showAndWait();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/HoaDon.fxml"));
//                Stage stage = new Stage();
//                Scene scene = new Scene(root);
//                stage.setTitle("Sửa nhà cung cấp");
//                stage.setScene(scene);
//                stage.show();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        manHome.setVisible(true);
        manSP.setVisible(false);
        manNCC.setVisible(false);
        manNhapXuat.setVisible(false);
        hienThiBangSP_Home();
        layThongTinTenSP();
        xuatHangTenSP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    dienThongTinKhiChonTenSP(newValue);
                }
            }
        });
    }
}
