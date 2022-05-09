package QUANLY;

public class SANPHAM {
    public String TENSANPHAM;

    public String DONVITINH;
    public int DONGIA;
    public String ANHSP;

    public SANPHAM(String TENSANPHAM, String DONVITINH, int DONGIA, String ANHSP){
        this.TENSANPHAM = TENSANPHAM;
        this.DONGIA = DONGIA;
        this.DONVITINH = DONVITINH;
        this.ANHSP = ANHSP;
    }

    public SANPHAM(){}

    public String getTENSANPHAM(){return this.TENSANPHAM;}
    public void setTENSANPHAM(String TENSANPHAM){this.TENSANPHAM = TENSANPHAM;}

    public int getDONGIA(){return this.DONGIA;}
    public void setDONGIA(int DONGIA){this.DONGIA = DONGIA;}

    public String getDONVITINH(){return this.DONVITINH;}
    public void setDONVITINH(String DONVITINH){this.DONVITINH = DONVITINH;}

    public String getANHSANPHAM(){return this.ANHSP;}
    public void setANHSANPHAM(String TENSANPHAM){this.ANHSP = TENSANPHAM;}
}
