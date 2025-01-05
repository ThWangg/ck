package vku.pntq.inventorymanagement.model;

public class NhaCungCapDb {
    private String maNhaCungCap;
    private String tenNhaCungCap;
    private int soDienThoai;
    private String diaChi;
    private String maNhaCungCapCu;

    public NhaCungCapDb(String maNhaCungCap, String tenNhaCungCap, int soDienThoai, String diaChi) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }
    // sửa ncc
    public NhaCungCapDb(String maNhaCungCap, String tenNhaCungCap, int soDienThoai, String diaChi, String maNhaCungCapCu) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.maNhaCungCapCu = maNhaCungCapCu;
    }

    public String getMaNhaCungCap(){
        return maNhaCungCap;
    }

    public String getTenNhaCungCap(){
        return tenNhaCungCap;
    }

    public int getSoDienThoai(){
        return soDienThoai;
    }

    public String getDiaChi(){
        return diaChi;
    }

    public String getMaNhaCungCapCu(){
        return maNhaCungCapCu;
    }
}
