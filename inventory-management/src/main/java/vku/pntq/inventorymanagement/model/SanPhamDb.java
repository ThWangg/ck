package vku.pntq.inventorymanagement.model;

public class SanPhamDb {
    
    private String maSanPham;
    private String loaiSanPham;
    private String tenSanPham;
    private String ma_ncc;
    private int soLuong;
    private double donGia;
    private String trangThai;
    private String maSanPhamCu;
    // màn home
    public SanPhamDb(String maSanPham, String loaiSanPham, String tenSanPham, String ma_ncc, int soLuong, double donGia, String trangThai) {
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.ma_ncc = ma_ncc;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }
    //màn sản phẩm
    public SanPhamDb(String maSanPham, String loaiSanPham, String tenSanPham, String ma_ncc, int soLuong, double donGia) {
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.ma_ncc = ma_ncc;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    //sửa sp contrstor)
    public SanPhamDb(String maSanPham, String loaiSanPham, String tenSanPham, String ma_ncc, int soLuong, double donGia, String trangThai, String maSanPhamCu){
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.ma_ncc = ma_ncc;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
        this.maSanPhamCu = maSanPhamCu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMa_ncc() {
        return ma_ncc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getMaSanPhamCu() {
        return maSanPhamCu;
    }
}
