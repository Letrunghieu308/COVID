package QUANLY;

public class LichSuMuaHang {
    private String CCCD ;
    private String NgayThang;
    private String Goimua;

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public LichSuMuaHang(String CCCD, String ngayThang, String goimua) {
        this.CCCD = CCCD;
        NgayThang = ngayThang;
        Goimua = goimua;
    }

    public String getNgayThang() {
        return NgayThang;
    }

    public void setNgayThang(String ngayThang) {
        NgayThang = ngayThang;
    }

    public String getGoimua() {
        return Goimua;
    }

    public void setGoimua(String goimua) {
        Goimua = goimua;
    }
}
