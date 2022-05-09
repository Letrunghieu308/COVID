package Service;

import CONNECT.JDBC;
import QUANLY.*;
import Views.LienDoi;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BenhnhanService {
    NoidieutriService noidieutriService = new NoidieutriService();
    Connection conn;
    public BenhnhanService(){conn = JDBC.connect();}
    public ArrayList<BENHNHAN> getBNKhoiBenh(){
        ArrayList<BENHNHAN> listBenhnhan = new ArrayList<>();
        String query = "SELECT * FROM COVID.Benhnhan where XacNhanKhoiBenh !='No'";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String CCCD = rs.getString("CCCD");
                String tenBN = rs.getString("HoTen");
                String Diachi = rs.getString("DiaChi");
                String Namsinh = rs.getString("NamSinh");
                String Nguoilienquan = rs.getString("LienQuan");
                String Trangthai = rs.getString("TrangThaiHienTai");
                String Diachidieutri = rs.getString("NoiDangDieuTri");
                String Lichsudieutri = rs.getString("LichSuCovid");
                String XacNhan = rs.getString("XacNhanKhoiBenh");

                listBenhnhan.add(new BENHNHAN(tenBN,CCCD,Namsinh, Diachi,Trangthai, Diachidieutri,Nguoilienquan,Lichsudieutri ,XacNhan));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listBenhnhan;
    }
    public ArrayList<BENHNHAN> getAllBenhnhan(){
        ArrayList<BENHNHAN> listBenhnhan = new ArrayList<>();
        String query = "SELECT * FROM COVID.Benhnhan where XacNhanKhoiBenh ='No'";
        Statement stm ;
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String CCCD = rs.getString("CCCD");
                String tenBN = rs.getString("HoTen");
                String Diachi = rs.getString("DiaChi");
                String Namsinh = rs.getString("NamSinh");
                String Nguoilienquan = rs.getString("LienQuan");
                String Trangthai = rs.getString("TrangThaiHienTai");
                String Diachidieutri = rs.getString("NoiDangDieuTri");
                String Lichsudieutri = rs.getString("LichSuCovid");
                String XacNhan = rs.getString("XacNhanKhoiBenh");

                listBenhnhan.add(new BENHNHAN(tenBN,CCCD,Namsinh, Diachi,Trangthai, Diachidieutri,Nguoilienquan,Lichsudieutri ,XacNhan));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listBenhnhan;
    }
    public List<BENHNHAN> getBenhNhanByName(String ten) {
        List<BENHNHAN> Benhnhan = new ArrayList<>();
        Statement stm ;
        try {
            stm = conn.createStatement();
            ResultSet rs= stm.executeQuery("select * from COVID.Benhnhan WHERE TenBN= \'"+ ten +"'");
            while (rs.next()) {
                String CCCD = rs.getString("CCCD");
                String tenBN = rs.getString("HoTen");
                String Diachi = rs.getString("DiaChi");
                String Namsinh = rs.getString("NamSinh");
                String Nguoilienquan = rs.getString("LienQuan");
                String Trangthai = rs.getString("TrangThaiHienTai");
                String Diachidieutri = rs.getString("NoiDangDieuTri");
                String Lichsudieutri = rs.getString("LichSuCovid");

                Benhnhan.add(new BENHNHAN(tenBN,CCCD,Namsinh, Diachi,Trangthai, Diachidieutri,Nguoilienquan,Lichsudieutri));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return Benhnhan;
    }
    public String getNoiDTriByCC(String CCCD){
        String sql = "Select NoiDangDieuTri from covid.benhnhan where CCCD = '"+ CCCD+"'";
        String ndt = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ndt = resultSet.getString("NoiDangDieuTri");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ndt;
    }
    public void updateMod(String hoTen , String DiaChi , String NamSinh , String TrangThaiHienTai , String LienQuan,String CCCD){
        String sql = "update covid.benhnhan set `HoTen` = ? , `DiaChi` = ? , `NamSinh` = ? , `TrangThaiHienTai` = ?,LienQuan = ?   where CCCD=? ";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,hoTen);
            preparedStatement.setString(2,DiaChi);
            preparedStatement.setString(3,NamSinh);
            preparedStatement.setString(4,TrangThaiHienTai);
            preparedStatement.setString(5,LienQuan);
            preparedStatement.setString(6,CCCD);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void upDateAdmin(String NoiDieuTri ,String CCCD){
        String sql = "update covid.benhnhan set NoiDangDieuTri = ? where CCCD = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            if(new NoidieutriService().getSoLuongDtri(NoiDieuTri) < new NoidieutriService().getSucChua(NoiDieuTri)){
                if(!new BenhnhanService().getNoiDTriByCC(CCCD).equals("chuaUpdate")){
                new NoidieutriService().GiamSoLuong(new BenhnhanService().getNoiDTriByCC(CCCD));
                }
                preparedStatement.setString(1,NoiDieuTri);
                preparedStatement.setString(2,CCCD);
                preparedStatement.execute();
                new NoidieutriService().TangSoLuong(NoiDieuTri);


            }else {
                JOptionPane.showMessageDialog(null,"Nơi điều trị đã đầy , vui lòng chọn vị trí khác");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public String getTenLienDoi(String CCCD){
        String lienDoi =null;
        String sql = "select LienQuan from covid.benhnhan where CCCD ='"+CCCD+"'";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                lienDoi = resultSet.getString("LienQuan");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lienDoi;
    }
    public BENHNHAN getBenhNhanByID(String ID) {
      BENHNHAN benhnhan = new BENHNHAN();
      String sql = "select * from covid.benhnhan where HoTen='"+ID+"'";
      try {
          Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);
          while (resultSet.next()){
              benhnhan = new BENHNHAN(resultSet.getString("HoTen"),resultSet.getString("CCCD"),resultSet.getString("NamSinh"),resultSet.getString("DiaChi"),resultSet.getString("TrangThaiHienTai"),resultSet.getString("LienQuan"),resultSet.getString("NoiDangDieuTri"),resultSet.getString("LichSuCoVid"));
          }
      }catch (Exception e ){
          e.printStackTrace();
      }
      return benhnhan;
    }


    public void deleteBenhnhan(String CCCD){
        String query = "DELETE FROM COVID.Benhnhan WHERE CCCD = ?";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            pstm.setString(1, CCCD);
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public int getSoluongBnhanKhoi(){
        int slg = 0;
        String querry = "Select Count(CCCD) from benhnhan where XacNhanKhoiBenh !='No'";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while(resultSet.next()){
                slg = resultSet.getInt("Count(CCCD)");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return slg;
    }
    public int getSoluongBnhan(){
        int slg = 0;
        String querry = "Select Count(CCCD) from benhnhan where XacNhanKhoiBenh ='No'";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while(resultSet.next()){
                slg = resultSet.getInt("Count(CCCD)");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return slg;
    }
    public void upDateLSu(String CCCD,String lsu){
        String sql = "update covid.benhnhan set LichSuCoVid = ? where CCCD = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,lsu);
            preparedStatement.setString(2,CCCD);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getXacNhan(String CCCD){
        String xacnhan = null;
        String sql = "select XacNhanKhoiBenh from covid.benhnhan where CCCD = '"+ CCCD+"'";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                xacnhan = resultSet.getString("XacNhanKhoiBenh");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return xacnhan;
    }
    public void upDateKhoiBenh(String CCCD){
        String sql = "update covid.benhnhan set XacNhanKhoiBenh = ? where CCCD = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"Yes");
            preparedStatement.setString(2,CCCD);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
