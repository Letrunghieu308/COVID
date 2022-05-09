package QUANLY;

public class DuNo {
    private String CCCD ;
    private String NgayNo;
    private String TenSPNo;

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public DuNo(String CCCD, String ngayNo, String tenSPNo) {
        this.CCCD = CCCD;
        NgayNo = ngayNo;
        TenSPNo = tenSPNo;
    }

    public String getNgayNo() {
        return NgayNo;
    }

    public void setNgayNo(String ngayNo) {
        NgayNo = ngayNo;
    }

    public String getTenSPNo() {
        return TenSPNo;
    }

    public void setTenSPNo(String tenSPNo) {
        TenSPNo = tenSPNo;
    }
}
