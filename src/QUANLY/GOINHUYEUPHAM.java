package QUANLY;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GOINHUYEUPHAM {
    public String TENGOI;
    public int MUCGIOIHAN;
    public String NGAYHETHAN;
    public int ThanhTien;


    public GOINHUYEUPHAM (String TENGOI) {
        this.TENGOI = TENGOI;
    }

    public GOINHUYEUPHAM(String TENGOI ,int MUCGIOIHAN, String NGAYHETHAN){
        this.TENGOI = TENGOI;
        this.MUCGIOIHAN = MUCGIOIHAN;
        this.NGAYHETHAN = NGAYHETHAN;
    }

    public GOINHUYEUPHAM(){}

    public String getTENGOI(){return this.TENGOI;}
    public void setTENGOI(String TENGOI){this.TENGOI = TENGOI;}



    public int getMUCGIOIHAN(){return this.MUCGIOIHAN;}
    public void setMUCGIOIHAN(int MUCGIOIHAN){this.MUCGIOIHAN = MUCGIOIHAN;}

    public String getNGAYHETHAN(){return this.NGAYHETHAN;}
    public void setNGAYHETHAN(String NGAYHETHAN){this.NGAYHETHAN = NGAYHETHAN;}

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "GOINHUYEUPHAM{" +
                "TENGOI='" + TENGOI + '\'' +
                ", MUCGIOIHAN=" + MUCGIOIHAN +
                ", NGAYHETHAN='" + NGAYHETHAN + '\'' +
                ", ThanhTien=" + ThanhTien +
                '}';
    }
}
