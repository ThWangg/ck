package vku.pntq.inventorymanagement.model;

public class XuatHangDb {
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;

    public XuatHangDb(String maSanPham, String tenSanPham, int soLuong, double donGia) {
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

    public double getDonGia() {
        return donGia;
    }
}
