package vku.pntq.inventorymanagement.model;

public class SanPhamDb {
    
    private String maSanPham;
    private String loaiSanPham;
    private String tenSanPham;
    private String ma_ncc;
    private int soLuong;
    private int donGia;
    private String maSanPhamCu;

    //màn sản phẩm
    public SanPhamDb(String maSanPham, String loaiSanPham, String tenSanPham, String ma_ncc, int soLuong, int donGia) {
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.ma_ncc = ma_ncc;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    //sửa sp contrstor)
    public SanPhamDb(String maSanPham, String loaiSanPham, String tenSanPham, String ma_ncc, int soLuong, int donGia, String maSanPhamCu){
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tenSanPham = tenSanPham;
        this.ma_ncc = ma_ncc;
        this.soLuong = soLuong;
        this.donGia = donGia;
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

    public int getDonGia() {
        return donGia;
    }

    public String getMaSanPhamCu() {
        return maSanPhamCu;
    }
}
