package vku.pntq.inventorymanagement.model;

public class NhapHangDb {
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private int donGia;

    public NhapHangDb(String maSanPham, String tenSanPham, int soLuong, int donGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDonGia() {
        return donGia;
    }
}
