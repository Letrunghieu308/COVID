package Service;

import CONNECT.JDBC;
import QUANLY.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanphamService {
    Connection conn;
    final JFileChooser fileDialog = new JFileChooser();
    public SanphamService(){conn = JDBC.connect();}

    public ArrayList<SANPHAM> getAllSanPham(){
        ArrayList<SANPHAM> listSanPham = new ArrayList<>();
        String query = "SELECT * FROM COVID.SanPham";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String TenSanPham = rs.getString("TenSanPham");
                String DonVi = rs.getString("DonVi");
                int DonGia = rs.getInt("DonGia");
                String AnhSP = rs.getString("Image");
                listSanPham.add(new SANPHAM(TenSanPham,DonVi,DonGia,AnhSP));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSanPham;
    }
    public SANPHAM getSanPhamByName(String tenSP) {
        SANPHAM SanPham = new SANPHAM();
        Statement stm = null;
        try {
            ResultSet rs= stm.executeQuery("select * from COVID.SanPham WHERE TenSanPham= '"+ tenSP +"'");
            while (rs.next()) {
                String TenSP = rs.getString("TenSanPham");
                String DonVi = rs.getString("DonVi");
                int DonGia = rs.getInt("DonGia");
                String AnhSP = rs.getString("Image");
                SanPham.setTENSANPHAM(TenSP);
                SanPham.setDONVITINH(DonVi);
                SanPham.setDONGIA(DonGia);
                SanPham.setANHSANPHAM(AnhSP);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return SanPham;
    }

    public void insertSanPham(String TenSP, String DonVi,  int DonGia, String AnhSP ){

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("insert into `COVID`.`SanPham`(`TenSanPham`,`DonVi`,`DonGia`, `Image`) value (?,?,?,?)  ");
            pstm.setString(1,TenSP);
            pstm.setString(2, DonVi);
            pstm.setInt(3, DonGia);
            pstm.setString(4, AnhSP);
            pstm.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateSanPham( String TenSP, String newTenSP, String newDonVi,int newDonGia, String newAnhSP){
        PreparedStatement pstm = null;
        PreparedStatement pstm1 =null;
        ArrayList<String> listString = new ArrayList<>();
        try {
            pstm = conn.prepareStatement("update `COVID`.`SanPham` set `TenSanPham`=?,`DonVi`=?,`DonGia` =?,`Image`=? where `TenSanPham`=?");
            pstm.setString(1,newTenSP);
            pstm.setString(2, newDonVi);
            pstm.setInt(3, newDonGia);
            pstm.setString(4,newAnhSP);
            pstm.setString(5,TenSP);

            pstm.execute();
            String sql = "Select TenGoi from covid.nhuyeupham ";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                listString.add(resultSet.getString("TenGoi"));
            }
            for(String tengoi : listString){
                pstm1 = conn.prepareStatement("update COVID."+tengoi+" set `SanPham` =? where `SanPham` = ?");
                pstm1.setString(1,newTenSP);
                pstm1.setString(2,TenSP);
                pstm1.execute();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateSanPham1( String TenSP, String newDonVi,int newDonGia){
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("update `COVID`.`SanPham` set `DonVi`=?,`DonGia` =? where `TenSanPham`=?");
            pstm.setString(1, newDonVi);
            pstm.setInt(2, newDonGia);
            pstm.setString(3, TenSP);
            pstm.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteSanPham(String TenSP){
        String query = "DELETE FROM COVID.SanPham WHERE TenSanPham = ?";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            pstm.setString(1, TenSP);
            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public float getDonGiabyName(String tenSP){
        SANPHAM SanPham = new SANPHAM();

        float dongia = 0;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs= stm.executeQuery("select * from COVID.sanpham WHERE TenSanPham= '"+ tenSP +"'");
            while (rs.next()) {
                dongia = rs.getFloat("DonGia");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return dongia;
    }
}
