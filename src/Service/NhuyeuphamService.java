package Service;

import CONNECT.JDBC;
import QUANLY.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class    NhuyeuphamService  {

    SanphamService sanphamService = new SanphamService();
    DSSanphamService dsSanphamService = new DSSanphamService();
    Connection conn;
    public NhuyeuphamService(){conn = JDBC.connect();}
    public GOINHUYEUPHAM getNhuYeuPham(String TenGoi){
        String query = "SELECT * FROM COVID.NhuYeuPham where `TenGoi` = '"+ TenGoi +"' ";
        GOINHUYEUPHAM goinhuyeuphamn = new GOINHUYEUPHAM();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String tengoi = rs.getString("TenGoi");
                String ngayHetHan = rs.getString("NgayHetHan");
                int MucGioiHan = rs.getInt("GioiHan");
                goinhuyeuphamn.setTENGOI(tengoi);
                goinhuyeuphamn.setNGAYHETHAN(ngayHetHan);
                goinhuyeuphamn.setMUCGIOIHAN(MucGioiHan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goinhuyeuphamn;
    }
    public ArrayList<GOINHUYEUPHAM> getAllNhuYeuPham(){
        ArrayList<GOINHUYEUPHAM> listNhuYeuPham = new ArrayList<>();
        ArrayList<DSSANPHAM> listDSSanPham = null;
        String query = "SELECT * FROM COVID.NhuYeuPham";
        Statement stm ;
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String tengoi = rs.getString("TenGoi");
                String ngayHetHan = rs.getString("NgayHetHan");
                int MucGioiHan = rs.getInt("GioiHan");
                listNhuYeuPham.add(new GOINHUYEUPHAM(tengoi, MucGioiHan, ngayHetHan));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNhuYeuPham;
    }
    public void insertNhuYeuPham(String TenGoi, int GioiHan, String NgayHetHan ){
        try {

            PreparedStatement pstm = conn.prepareStatement("insert into `COVID`.`NhuYeuPham`(`TenGoi`,`GioiHan`,`NgayHetHan`) value (?,?,?)  ");
            pstm.setString(1,TenGoi);
            pstm.setInt(2, GioiHan);
            pstm.setString(3,NgayHetHan);
            pstm.execute();
            String sql = "CREATE TABLE covid."+TenGoi+" (\n" +
                    "  `SanPham` VARCHAR(45) NOT NULL,\n" +
                    "  `SoLuong` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`SanPham`))";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateGoiSP ( String TenGoi, int newGioiHan, String newNgayHetHan){
        try {
            PreparedStatement pstm = conn.prepareStatement("update `COVID`.`NhuYeuPham` set `GioiHan`=?,`NgayHetHan`=? where `TenGoi`=?");
            pstm.setInt(1, newGioiHan);
            pstm.setString(2, newNgayHetHan);
            pstm.setString(3, TenGoi);
            pstm.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteSanPham(String TenGoi){
        String query = "DELETE FROM COVID.NhuYeuPham WHERE TenGoi = ?";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            pstm.setString(1, TenGoi);
            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public float tinhThanhTien(String TenGoi){
        String query = "SELECT * FROM COVID."+TenGoi;
        float thanhtien = 0 ;
        ArrayList<String> listSP = new ArrayList<>();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                listSP.add(rs.getString("SanPham"));
            }
            for(String sp : listSP){
                thanhtien += sanphamService.getDonGiabyName(sp) * dsSanphamService.getSluongByTenandMa(sp,TenGoi);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return thanhtien;
    }
    public int getGioiHanMua(String tenGoi){
        String sql = " select GioiHan from covid.nhuyeupham where TenGoi = '"+tenGoi+"'";
        int slg =0 ;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                slg = resultSet.getInt("GioiHan");
            }
        }catch (Exception e){e.printStackTrace();}
        return slg;
    }
    public void upDateGH(String tenGoi){
        String sql = "update covid.nhuyeupham set GioiHan = ? where TenGoi  = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,new NhuyeuphamService().getGioiHanMua(tenGoi) - 1 );
            preparedStatement.setString(2,tenGoi);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTenGoiNYP(){
        String sql = "select TenGoi from covid.nhuyeupham ";
        ArrayList<String > list = new ArrayList<>();
        try {
            Statement statement= conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(resultSet.getString("TenGoi"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



}
