package QUANLY;

public class BENHNHAN extends USERS {
    private String Hoten;
    private String CCCD;
    private String NamSinh;
    private String DiaChi;
    private String TrangThaiHienTai;
    private String LienQuan;
    private String NoiDangDT;
    private String LSDieuTri;
    private String XacNhan;


    public BENHNHAN() {

    }


    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getTrangThaiHienTai() {
        return TrangThaiHienTai;
    }

    public String getLienQuan() {
        return LienQuan;
    }

    public void setLienQuan(String lienQuan) {
        LienQuan = lienQuan;
    }

    public void setTrangThaiHienTai(String trangThaiHienTai) {
        TrangThaiHienTai = trangThaiHienTai;
    }

    public String getNoiDangDT() {
        return NoiDangDT;
    }

    public void setNoiDangDT(String noiDangDT) {
        NoiDangDT = noiDangDT;
    }

    public String getLSDieuTri() {
        return LSDieuTri;
    }

    public String getXacNhan() {
        return XacNhan;
    }

    public void setXacNhan(String xacNhan) {
        XacNhan = xacNhan;
    }

    public void setLSDieuTri(String LSDieuTri) {
        this.LSDieuTri = LSDieuTri;
    }


    public BENHNHAN( String hoten, String CCCD, String namSinh, String diaChi, String trangThaiHienTai, String lienQuan, String noiDangDT, String LSDieuTri, String xacNhan) {
        Hoten = hoten;
        this.CCCD = CCCD;
        NamSinh = namSinh;
        DiaChi = diaChi;
        TrangThaiHienTai = trangThaiHienTai;
        LienQuan = lienQuan;
        NoiDangDT = noiDangDT;
        this.LSDieuTri = LSDieuTri;
        XacNhan = xacNhan;
    }

    public BENHNHAN(String hoten , String CCD, String namsinh , String diaChi, String trangThaiHienTai, String noiDangDT, String LienQuan , String LSDieuTri){
        Hoten = hoten;
        CCCD  = CCD;
        NamSinh = namsinh ;
        DiaChi = diaChi;
        TrangThaiHienTai = trangThaiHienTai;
        NoiDangDT = noiDangDT;
        this.LienQuan = LienQuan;
        this.LSDieuTri = LSDieuTri;
        this.XacNhan = "No";

    }
    public BENHNHAN(USERS users, String hoten, String namSinh, String diaChi, String trangThaiHienTai, String noiDangDT,String lienQuan ,String LSDieuTri) {
        super(users.getUsername(), users.getPassword(), users.getRole());
        Hoten = hoten;
        this.CCCD = users.getUsername();
        NamSinh = namSinh;
        DiaChi = diaChi;
        TrangThaiHienTai = trangThaiHienTai;
        LienQuan = lienQuan;
        NoiDangDT = noiDangDT;
        this.LSDieuTri = LSDieuTri;
        this.XacNhan="No";
    }
    @Override
    public String toString() {
        return "BENHNHAN{" +
                "Hoten='" + Hoten + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", NamSinh='" + NamSinh + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", TrangThaiHienTai='" + TrangThaiHienTai + '\'' +
                ", NoiDangDT='" + NoiDangDT + '\'' +
                ", LSDieuTri='" + LSDieuTri + '\'' +
                '}';
    }
}
