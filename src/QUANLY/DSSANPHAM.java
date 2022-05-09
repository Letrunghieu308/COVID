package QUANLY;

public class DSSANPHAM {
    String TenSanPham;
    int SoLuong;



    public DSSANPHAM(String SP, int soLuong){
        this.TenSanPham = SP;
        this.SoLuong = soLuong;

    }

    public DSSANPHAM(){}


    public String getTenSanPham() {
        return this.TenSanPham;
    }

    public void setTenSanPham(String sanPham) {
        this.TenSanPham = sanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    @Override
    public String toString() {
        return "DSSANPHAM{" +
                "SanPham=" + TenSanPham +
                ", SoLuong=" + SoLuong +
                '}';
    }
}
