package vku.pntq.inventorymanagement.model;

public class SanPhamDb {

    private int STT;
    private String maSanPham;
    private String loaiSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;
    private String trangThai;
    private String maSanPhamCu;
    // màn home
    public SanPhamDb(int STT, String maSanPham, String loaiSanPham, String tenSanPham, int soLuong, double donGia, String trangThai) {
        this.STT = STT;
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }
    //màn sản phẩm
    public SanPhamDb(int STT, String maSanPham, String loaiSanPham, String tenSanPham, int soLuong, double donGia) {
        this.STT = STT;
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    // k có stt;
    public SanPhamDb(String maSanPham, String loaiSanPham, String tenSanPham, int soLuong, double donGia, String trangThai) {
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }
    //sửa sp contrstor)
    public SanPhamDb(String maSanPham, String loaiSanPham, String tenSanPham, int soLuong, double donGia, String trangThai, String maSanPhamCu){
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
        this.maSanPhamCu = maSanPhamCu;
    }
    public int getSTT() {
        return STT;
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
