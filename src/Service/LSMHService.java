package Service;

import CONNECT.JDBC;
import QUANLY.LichSuMuaHang;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LSMHService {
    Connection connection;
    public LSMHService() {
        connection = JDBC.connect();
    }

    public ArrayList<LichSuMuaHang> getAllLS(){
        ArrayList<LichSuMuaHang> lichSuMuaHangs = new ArrayList<>();
        String sql = "select * from covid.lsmuahang";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                lichSuMuaHangs.add( new LichSuMuaHang(resultSet.getString("tenKH"),resultSet.getString("ngaymua"),resultSet.getString("TenSP")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return lichSuMuaHangs;
    }
    boolean checkLSTrong(String tenKH){
        String querry = "select tenKH from covid.lsmuahang where tenKH = '"+tenKH+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while(resultSet.next()){
                if(resultSet.getString("tenKH").equals(tenKH)){
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public String getNgayMua(String hoTen){
        String ngay = null;
        try {
            String sql = "select * from covid.lsmuahang where tenKH ='"+hoTen+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ngay = resultSet.getString("ngaymua");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ngay;
    }
    public String getTenGoi(String hoTen){
        String tenGoi = null;
        try {
            String sql = "select * from covid.lsmuahang where tenKH ='"+hoTen+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                tenGoi = resultSet.getString("TenSP");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tenGoi;
    }
    public void capnhatLS(String hoTen , String ngayMua , String tenSP){
        String sql = "update covid.lsmuahang set ngaymua = ? , tenSP = ? where tenKH = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,getNgayMua(hoTen)+"       "+ ngayMua);
            preparedStatement.setString(2, getTenGoi(hoTen) + "      " + tenSP);
            preparedStatement.setString(3,hoTen);
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void insert(String hoTen , String ngayMua , String tenSP){
        String sql = "insert into covid.lsmuahang (`tenKH`,`ngaymua`,`tenSP`) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,hoTen);
            preparedStatement.setString(2,ngayMua);
            preparedStatement.setString(3,tenSP);
            preparedStatement.execute();

        }catch (Exception e ) {
            e.printStackTrace();
        }
    }
    public void thaoTac(String hoTen,String ngayMua , String tenSP){
        if(checkLSTrong(hoTen) == true){
            insert(hoTen,ngayMua,tenSP);
        }
        else capnhatLS(hoTen,ngayMua,tenSP);
    }
}
