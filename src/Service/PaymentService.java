package Service;
import CONNECT.JDBC;
import QUANLY.PAYMENT;
import Views.LoginPage;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class PaymentService {
    PAYMENT payment = new PAYMENT();
    Connection conn ;
    public  PaymentService(){
        conn = JDBC.connect();
    }
    public void napTienbyID(String id,int money){
        float DangCo = new PaymentService().getSoduTKbyID(id);
        String sql = "update COVID.payment set SoDuTK = ? where CCCD = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,money + (int)DangCo);
            preparedStatement.setString(2,id);
            preparedStatement.execute();
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    public ArrayList<PAYMENT> getAll(){
        ArrayList<PAYMENT> list = new ArrayList<>();
        String querry = " select * from covid.payment";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()){
                list.add(new PAYMENT(resultSet.getString("CCCD") , resultSet.getInt("SoduTK"),resultSet.getInt("SoNo")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public PAYMENT getPaymentbyCC(String CCCD){
        PAYMENT payment = new PAYMENT();
        String querry = " select * from covid.payment where CCCD ='"+CCCD+"'";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()){
               payment= (new PAYMENT(resultSet.getString("CCCD") , resultSet.getInt("SoduTK"),resultSet.getInt("SoNo")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return payment;
    }
    public String gethanChe(String id){
        String sql = "Select HanChe from covid.payment where CCCD = '"+id+"'";
        String hanche ="" ;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                hanche = resultSet.getString("HanChe");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return hanche;
    }
    public void muaHang(float tongtien , String id){
           float Sodu = new PaymentService().getSoduTKbyID(id);
           String sql = "update COVID.payment set SoduTK = ? where CCCD = ?";
           try {
               PreparedStatement preparedStatement = conn.prepareStatement(sql);
               preparedStatement.setInt(1, (int)(Sodu - tongtien));
               preparedStatement.setString(2, id);
               preparedStatement.execute();
           }
           catch (Exception e){
               e.printStackTrace();
           }
    }
    public void lockDuNo(String id){
        String sql = "update covid.payment set HanChe = ? where CCCD = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"Yes");
            preparedStatement.setString(2,id);
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void unlock(String id){
        String sql = "update covid.payment set HanChe = ? where CCCD = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"No");
            preparedStatement.setString(2,id);
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void thanhtoanAdmin(float tongtien ){
        String sql = "update COVID.payment set SoDuTK = ? where CCCD =?";
        float TienHienCo = new PaymentService().getSoduTKbyID("admin");
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,(int) (TienHienCo+tongtien));
            preparedStatement.setString(2,"admin");
            preparedStatement.execute();
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    public float getSoduTKbyID(String id){
        try {
            String sql = "select * from covid.payment where CCCD = '"+ id +"'" ;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                payment = new PAYMENT(resultSet.getString("CCCD"),resultSet.getInt("SoduTK"));
            }
        }catch (Exception exception){
            exception.getMessage();
        }

        return payment.getSoDuTK();
    }
    public void thanhtoanNo(String id){
        float SoDu = new PaymentService().getSoduTKbyID(id);
        float SoNo = new PaymentService().getSoNobyID(id);
        new PaymentService().muaHang(SoNo,id);
        new PaymentService().updateGhiNo(id,0);
        new PaymentService().thanhtoanAdmin(SoNo);
        String[] tengoi = new DuNoService().getTenGoi(id).split(",");
        for(String ten : tengoi){
            new NhuyeuphamService().upDateGH(ten);
        }
        new DuNoService().xoaNo(id);

        new PaymentService().unlock(id);

    }
    public float getSoNobyID(String id){
        try {
            String sql = "select * from covid.payment where CCCD = '"+ id +"'" ;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                payment = new PAYMENT(resultSet.getString("CCCD"),resultSet.getInt("SoduTK") , resultSet.getInt("SoNo"));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return payment.getSoNo();
    }
    public void updateGhiNo(String id , float soTien){
        String sql = "update covid.payment set SoNo = ? where CCCD = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,(int)soTien);
            preparedStatement.setString(2,id);
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
        new PaymentService().lockDuNo(id);

    }
    public void deletePayment(String CCCD){
        String sql = "delete from covid.payment where CCCD= ?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, CCCD);
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
