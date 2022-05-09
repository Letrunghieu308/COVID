package QUANLY;

public class NoiDTri {
    private String diaChi;
    private int GHan;
    private  int Sluong;

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getGHan() {
        return GHan;
    }

    public NoiDTri(String diaChi, int GHan) {
        this.diaChi = diaChi;
        this.GHan = GHan;
    }

    public NoiDTri() {
    }

    public void setGHan(int GHan) {
        this.GHan = GHan;
    }
}
