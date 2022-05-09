package Service;
import CONNECT.JDBC;
import QUANLY.*;

import java.sql.*;
import java.util.ArrayList;

public class DSSanphamService {
    SanphamService sanphamService = new SanphamService();
    Connection conn;
    public DSSanphamService(){conn = JDBC.connect();}


    public ArrayList<DSSANPHAM> getAllDSSanPhamVoiTenGoi(String TenGoi){
        ArrayList<DSSANPHAM> listDSSanPham = new ArrayList<>();
        String query = "SELECT * FROM COVID."+TenGoi;
        Statement stm ;
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String TenSanPham = rs.getString("SanPham");
                int SoLuong = rs.getInt("SoLuong");
                listDSSanPham.add(new DSSANPHAM(TenSanPham,SoLuong));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDSSanPham;
    }
    boolean checkSPTrong(String tengoi , String sp){

        try {
            String querry = "select SoLuong from COVID."+tengoi+" where SanPham ='"+sp+"'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(querry);
            while (rs.next()){
                if(!(rs.getString("SoLuong")).equals("")){
                    return true;
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public DSSANPHAM getDSSanPham(){
        DSSANPHAM dssanpham = new DSSANPHAM();
        String query = "SELECT * FROM COVID.DSSanPham";
        Statement stm ;
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String GoiSP = rs.getString("TenGoi");
                String TenSanPham = rs.getString("SanPham");
                int SoLuong = rs.getInt("SoLuong");
                dssanpham.setTenSanPham(TenSanPham);
                dssanpham.setSoLuong(SoLuong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dssanpham;
    }
    public void insertDSSP(String GoiSP, String TenSP, int SoLuong ){
        try {
            PreparedStatement pstm = conn.prepareStatement("insert into covid."+GoiSP+"(`SanPham`,`SoLuong`) value (?,?) ");
            pstm.setString(1,TenSP);
            pstm.setInt(2, SoLuong);
            pstm.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateSoLuongSanPham( String GoiSP, String TenSP,  int newSoLuong){

        if(checkSPTrong(GoiSP,TenSP) == true){
            try {
                PreparedStatement pstm = conn.prepareStatement("update `COVID`."+GoiSP+" set `SoLuong`=? where `SanPham`=?");
                pstm.setInt(1,  newSoLuong);
                pstm.setString(2, TenSP);
                pstm.execute();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else {
            insertDSSP(GoiSP,TenSP, newSoLuong);
        }
    }

    public void deleteDSSanPham( String GoiSP, String TenSP ){
        String query = "delete from COVID."+GoiSP+" where SanPham = ?";

        try {
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, TenSP);
            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getSluongByTenandMa(String tenSP,String maSP){
        SANPHAM SanPham = new SANPHAM();

        int sl = 0;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs= stm.executeQuery("select * from COVID."+ maSP +" WHERE SanPham= '"+ tenSP +"'" );
            while (rs.next()) {
                sl = rs.getInt("SoLuong");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return sl;
    }
    public void checkHetHan(String id ){

    }

}
