package QUANLY;

public class NOIDIEUTRI {
    String DiaChi;
    int SucChua;
    int SoLuong;

    public NOIDIEUTRI(String DiaChi, int SucChua, int SoLuong){
        this.DiaChi = DiaChi;
        this.SoLuong = SoLuong;
        this.SucChua = SucChua;
    }

    public NOIDIEUTRI() {

    }

    public int getSucChua() {
        return SucChua;
    }

    public void setSucChua(int sucChua) {
        SucChua = sucChua;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public boolean KiemTraSucChua( NOIDIEUTRI noidieutri){
        if(noidieutri.SucChua >= noidieutri.SoLuong)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "NOIDIEUTRI{" +
                "DiaChi='" + DiaChi + '\'' +
                ", SucChua=" + SucChua +
                ", SoLuong=" + SoLuong +
                '}';
    }
}
