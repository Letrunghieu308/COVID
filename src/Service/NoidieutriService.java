package Service;

import CONNECT.JDBC;
import QUANLY.*;
import java.sql.*;
import java.util.ArrayList;

public class NoidieutriService {
    Connection conn;
    public NoidieutriService(){conn = JDBC.connect();}

    public ArrayList<NOIDIEUTRI> getAllNoidieutri(){
        ArrayList<NOIDIEUTRI> listNoidieutri = new ArrayList<>();
        try {
            String query = "SELECT * FROM COVID.Noidieutri";
            Statement stm ;
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String Diachi = rs.getString("DiaChi");
                int SucChua = rs.getInt("SucChua");
                int SoLuong = rs.getInt("SoLuong");
                listNoidieutri.add(new NOIDIEUTRI(Diachi,SucChua, SoLuong));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNoidieutri;
    }
    public NOIDIEUTRI getNoiDieuTriByName(String DiaChi) {
        NOIDIEUTRI noidieutri = new NOIDIEUTRI();
        Connection connection = JDBC.connect();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from covid.noidieutri WHERE DiaChi= '"+ DiaChi +"'");
            Statement stm = conn.createStatement();
            ResultSet rs = pstm.executeQuery();
//            ResultSet rs= stm.executeQuery("select * from covid.noidieutri WHERE DiaChi= '"+ DiaChi +"'");
            while (rs.next()) {
                String Diachi = rs.getString("DiaChi");
                int SucChua = rs.getInt("SucChua");
                int SoLuong = rs.getInt("SoLuong");
                noidieutri.setDiaChi(Diachi);
                noidieutri.setSoLuong(SoLuong);
                noidieutri.setSucChua(SucChua);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return noidieutri;
    }
    public void TangSoLuong(String NoiDieuTri){
        String sql  = "update covid.noidieutri set SoLuong = ? where DiaChi = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,new NoidieutriService().getSoLuongDtri(NoiDieuTri) + 1);
            preparedStatement.setString(2,NoiDieuTri);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void GiamSoLuong(String NoiDieuTri){
        String sql  = "update covid.noidieutri set SoLuong = ? where DiaChi = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,new NoidieutriService().getSoLuongDtri(NoiDieuTri) - 1);
            preparedStatement.setString(2,NoiDieuTri);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public NOIDIEUTRI getNoiDieuTriByDiaChi(String DiaChi) {
        NOIDIEUTRI noidieutri =  new NOIDIEUTRI();
        try {
            Connection connection = JDBC.connect();
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery("select * from covid.noidieutri WHERE DiaChi= \'"+ DiaChi +"'");
            while (rs.next()) {
                String diaChi = rs.getString("DiaChi");
                int SoLuong = rs.getInt("SoLuong");
                int SucChua = rs.getInt("SucChua");

                noidieutri = new NOIDIEUTRI(diaChi, SoLuong,SucChua);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return noidieutri;
    }
    public int getSoLuongDtri(String DiaCHi){
        String sql = "select SoLuong from covid.noidieutri where DiaChi = '"+DiaCHi+"'";
        int SoLuong = 0 ;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                SoLuong = resultSet.getInt("SoLuong");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  SoLuong;
    }
    public int getSucChua(String DiaChi){
        String sql = "select SucChua from covid.noidieutri where DiaChi = '"+DiaChi+"'";
        int SoLuong = 0 ;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                SoLuong = resultSet.getInt("SucChua");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  SoLuong;
    }
    public void insertNoidieutri(String DiaChi, int SucChua,  int SoLuong ){
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("insert into `COVID`.`Noidieutri`(`DiaChi`,`SucChua`,`SoLuong`) value (?,?,?)  ");
            pstm.setString(1,DiaChi);
            pstm.setInt(2, SucChua);
            pstm.setInt(3, SoLuong);
            pstm.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateNoidieutri( String DiaChi, String newDiaChi, int newSucChua,int newSoLuong){
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("update `COVID`.`Noidieutri` set `DiaChi`=?,`SucChua`=?,`SoLuong` =? where `DiaChi`=?");
            pstm.setString(1,newDiaChi);
            pstm.setInt(2, newSucChua);
            pstm.setInt(3, newSoLuong);
            pstm.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void updateSucChua( String DiaChi, int newSucChua){
        try {
            PreparedStatement pstm = conn.prepareStatement("update `COVID`.`Noidieutri` set `SucChua`=? where `DiaChi`=?");
            pstm.setInt(1, newSucChua);
            pstm.setString(2,DiaChi);
            pstm.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateSoLuong( String DiaChi, int newSoLuong){
        PreparedStatement pstm = null;
        Statement stm = null;
        try {
            pstm = conn.prepareStatement("update `COVID`.`Noidieutri` set `SoLuong`=? where `DiaChi`=?");
            pstm.setInt(1, newSoLuong);
            pstm.execute();


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteNoidieutri(String name){
        String query = "DELETE FROM COVID.Noidieutri WHERE DiaChi = ?";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void upDateSLandSC(String dc,int sl,int sc){
        String sql = "update covid.noidieutri set SoLuong = ? , SucChua = ? where DiaChi = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,sl);
            preparedStatement.setInt(2,sc);
            preparedStatement.setString(3,dc);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<String> getNoiDTri(){
        ArrayList<String> list = new ArrayList<>();
        String querry= "select DiaChi from covid.noidieutri";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while(resultSet.next()){
                list.add(resultSet.getString("DiaChi"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public boolean checkFull(String ndt){
        int slg = new NoidieutriService().getSoLuongDtri(ndt);
        int sc = new NoidieutriService().getSucChua(ndt);
        if(slg > sc){
            return  true;
        }
        else return false;
    }
}
