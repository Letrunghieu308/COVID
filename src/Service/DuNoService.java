package Service;

import CONNECT.JDBC;
import QUANLY.DuNo;
import Views.HomePage;
import Views.LoginPage;
import Views.PaymentAccount;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

public class DuNoService {
    Connection connection;
    public DuNoService(){
        connection = JDBC.connect();
    }
    public ArrayList<DuNo> getAllDuNo(){
        ArrayList<DuNo> list = new ArrayList<>();
        String sql = " Select * from covid.thanhtoanduno";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                list.add(new DuNo(resultSet.getString("CCCD") , resultSet.getString("NgayGhiNo"),resultSet.getString("TenSanPham")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  list;
    }
    public boolean checkDuNoTrong(String CCCD){
        String querry = "select CCCD from covid.thanhtoanduno where CCCD = '"+CCCD+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while(resultSet.next()){
                if(resultSet.getString("CCCD").equals(CCCD)){
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public String getNgayGhiNo(String hoTen){
        String ngay = null;
        try {
            String sql = "select * from covid.thanhtoanduno where CCCD ='"+hoTen+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ngay = resultSet.getString("NgayGhiNo");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return ngay;
    }
    public String getTenGoi(String hoTen){
        String tenGoi = null;
        try {
            String sql = "select * from covid.thanhtoanduno where CCCD ='"+hoTen+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                tenGoi = resultSet.getString("TenSanPham");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tenGoi;
    }
    public void insert(String hoTen , String NgayGhiNo , String tenSP){
        String sql = "insert into covid.thanhtoanduno (`CCCD`,`NgayGhiNo`,`TenSanPham`) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,hoTen);
            preparedStatement.setString(2,NgayGhiNo);
            preparedStatement.setString(3,tenSP);
            preparedStatement.execute();

        }catch (Exception e ) {
            e.printStackTrace();
        }
    }
    public void thaoTac(String hoTen,String NgayGhiNo , String tenSP){
        if(checkDuNoTrong(hoTen) == true){
            insert(hoTen,NgayGhiNo,tenSP);
        }
        else JOptionPane.showMessageDialog(null,"Bạn chỉ được phép  nợ 1 lần ");
    }
    public int getNgayHetHan(String id){
        String date = new DuNoService().getNgayGhiNo(id);
        String[] list = date.split(" ");
        LocalTime time = LocalTime.parse(list[1]);
        int ngayhethan = time.getMinute() + 2;
        return ngayhethan;
    }

    public void xoaNo(String id){
        String sql = "delete from `covid`.`thanhtoanduno` where (`CCCD` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public boolean checKHetHan(String id , int time){
        if(new DuNoService().getNgayHetHan(id) <= time){
            return true;
        }
        else
            return false;
    }

    public void XuliHetHan(String id){
           if(new PaymentService().getSoduTKbyID(id) >= new PaymentService().getSoNobyID(id)){
               new PaymentService().thanhtoanNo(id);
           }
           else{
               JOptionPane.showMessageDialog(null,"Vui lòng nạp thêm tiền để thanh toán nợ ");
               new HomePage().dispose();
               PaymentAccount paymentAccount = new PaymentAccount();
               paymentAccount.showPaymentAcc();
           }
    }
}

