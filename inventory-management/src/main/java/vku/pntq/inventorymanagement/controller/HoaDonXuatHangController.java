package vku.pntq.inventorymanagement.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import vku.pntq.inventorymanagement.DAO.XuatHangDAO;
import vku.pntq.inventorymanagement.model.SanPhamDb;
import vku.pntq.inventorymanagement.model.XuatHangDb;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HoaDonXuatHangController implements Initializable {

    @FXML
    private TableView<XuatHangDb> bangXuatHang;

    @FXML
    private TableColumn<SanPhamDb, Double> cotDonGia_HoaDon;

    @FXML
    private TableColumn<SanPhamDb, String> cotMaSP_HoaDon;

    @FXML
    private TableColumn<SanPhamDb, Integer> cotSoLuong_HoaDon;

    @FXML
    private TableColumn<SanPhamDb, String> cotTenSP_HoaDon;

    @FXML
    private Label gioHoaDon;

    @FXML
    private Label ngayHoaDon;

    @FXML
    private Label hoaDonTongTien;


    private XuatHangDAO xuatHangDAO = new XuatHangDAO();


    public void hienThiBangHoaDon() {
        ObservableList<XuatHangDb> hoaDon = xuatHangDAO.layDuLieuBangXuatHang();
        cotMaSP_HoaDon.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        cotTenSP_HoaDon.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        cotSoLuong_HoaDon.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        cotDonGia_HoaDon.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        bangXuatHang.setItems(hoaDon);
//        if (hoaDon.isEmpty()) {
//            System.out.println("k co du lieu");
//        } else {
//            System.out.println(hoaDon.size());
//        }
    }

    public void tinhTongTienXuatHang(){
        double tongTien = xuatHangDAO.tinhTongTienXuatHang();
        DecimalFormat decimalFormat = new DecimalFormat("0");
        hoaDonTongTien.setText(decimalFormat.format(tongTien));
    }

    public void layThoiGian() {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String gioHienTai = now.format(timeFormatter);
        gioHoaDon.setText(gioHienTai);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String ngayHienTai = now.format(dateFormatter);
        ngayHoaDon.setText(ngayHienTai);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hienThiBangHoaDon();
        tinhTongTienXuatHang();
        layThoiGian();
    }
}
