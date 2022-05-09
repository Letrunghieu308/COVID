package QUANLY;

public class PAYMENT {
    private String id;
    private int soDuTK;
    private int soNo;
    private String hanche;

    public int getSoNo() {
        return soNo;
    }

    public void setSoNo(int soNo) {
        this.soNo = soNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSoDuTK() {
        return soDuTK;
    }

    public PAYMENT(String id, int soDuTK) {
        this.id = id;
        this.soDuTK = soDuTK;
        this.soNo = 0;
        this.hanche = "No";
    }

    public PAYMENT(String id, int soDuTK, int soNo) {
        this.id = id;
        this.soDuTK = soDuTK;
        this.soNo = soNo;
    }

    public PAYMENT() {
        this.soDuTK = 0;
    }

    public void setSoDuTK(int soDuTK) {
        this.soDuTK = soDuTK;
    }
}
