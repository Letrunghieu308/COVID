package Views;

import CONNECT.JDBC;
import QUANLY.PAYMENT;
import QUANLY.USERS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class CreateAcountUser extends  JFrame {
    USERS users = new USERS();
    private JTextField userText;
    private JPanel CreateAcountUser;
    private JButton button1;
    private JButton closeButton;
    private JTextField roleField;

    public void showCreateUser(){
        CreateAcountUser createAcountUser = new CreateAcountUser();
        createAcountUser.setVisible(true);
        createAcountUser.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        createAcountUser.setLocationRelativeTo(null);
//        createAcountUser.pack();
    }
    public CreateAcountUser(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(CreateAcountUser);
        //this.setLocationRelativeTo(null);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String rolefiel = roleField.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    String sql = "select * from COVID.Users";
                    ResultSet resultSet = statement.executeQuery(sql);
                    if(resultSet.next()){
                        if(username.equals(resultSet.getString("username"))){
                            JOptionPane.showMessageDialog(null,"Account already exists");
                            userText.setText(" ");
                            roleField.setText(" ");

                        }
                        String sql1 = "insert into `COVID`.`Users`(`username`,`password`,`role`) values (?,?,?)" ;
                        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                        preparedStatement.setString(1,username);
                        preparedStatement.setString(2,"password");
                        if(LoginPage.role.equals("admin")){
                            rolefiel = "mod";
                            preparedStatement.setString(3,rolefiel);
                        }
                        if(LoginPage.role.equals("mod")){
                            rolefiel = "user";
                            preparedStatement.setString(3,rolefiel);
                        }
                        preparedStatement.execute();
                    }

                }catch (Exception e3){
                    e3.getMessage();
                }
                if (rolefiel.equals("user"))
                {
                    users = new USERS(username,"password","user");
                    PAYMENT payment = new PAYMENT(username,0);
                    try {
                        Connection connection = JDBC.connect();
                        String sql = "insert into `COVID`.`benhnhan` (`HoTen`,`CCCD`,`NamSinh`,`DiaChi`,`TrangThaiHienTai`,`NoiDangDieuTri`,`LienQuan`,`LichSuCoVid`) value (?,?,?,?,?,?,?,?)" ;
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1,users.getBenhnhan().getHoten());
                        preparedStatement.setString(2,users.getBenhnhan().getCCCD());
                        preparedStatement.setString(3,users.getBenhnhan().getNamSinh());
                        preparedStatement.setString(4,users.getBenhnhan().getDiaChi());
                        preparedStatement.setString(5,users.getBenhnhan().getTrangThaiHienTai());
                        preparedStatement.setString(6,users.getBenhnhan().getNoiDangDT());
                        preparedStatement.setString(7,users.getBenhnhan().getLienQuan());
                        preparedStatement.setString(8,users.getBenhnhan().getLSDieuTri());
                        preparedStatement.execute();


                    }
                    catch (Exception e1){
                        System.out.println(e1.getMessage());
                    }
                    try {
                        Connection connection = JDBC.connect();
                        String sql = " insert into `COVID`.`payment`(`CCCD`,`SoduTK`) values(?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, payment.getId());
                        preparedStatement.setInt(2,payment.getSoDuTK());
                        preparedStatement.execute();

                    }catch (Exception exception){
                        exception.getMessage();
                    }
                }
                dispose();
                HomePage homePage = new HomePage();
                homePage.showHomePage();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.showAdminPage();
            }
        });

    }
    public void ShowCreateAcountUserPage(){
        CreateAcountUser createAcountUser = new CreateAcountUser();
        createAcountUser.setVisible(true);
        createAcountUser.setLocationRelativeTo(null);
        createAcountUser.pack();
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        button1 = new JButton(new ImageIcon(new ImageIcon("image\\ok.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        button1.setPreferredSize(new Dimension(60, 60));
        closeButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        closeButton.setPreferredSize(new Dimension(60, 60));
    }
}
