package Views;


import QUANLY.ADMIN;
import Service.DuNoService;
import Service.NoidieutriService;
import Service.PaymentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class LoginPage extends JFrame {
    public JTextField Password;
    public JTextField UserName;
    private JButton loginButton;
    private JButton resetButton;
    private JPanel Login;
    private JLabel Userlabel;
    private JLabel Passlabel;
    static String role;
    static String CCCD;
    static String PWD;
    static String logintime;

    public void showLoginPage(){
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        //loginPage.setLocationRelativeTo(null);
        //loginPage.pack();
        loginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public LoginPage(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Login);
        this.setLocationRelativeTo(null);
        HomePage homePage = new HomePage();
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserName.setText("");
                Password.setText("");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    String username  = UserName.getText();
                    String password = Password.getText();
                    java.util.Date date=new java.util.Date();

                    DateFormat dateFormat = null;
                    dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    String login = dateFormat.format(date);
                    logintime = login;
                    //System.out.println(date);
                    String sql = "select * from COVID.Users where username = '" + username + "'and password = '"+ password +"'";
                    ResultSet resultSet  = statement.executeQuery(sql);
                    if(resultSet.next()){
                        dispose();
                        if(password.equals("password")){
                            JOptionPane.showMessageDialog(null,"You must change your password");
                            ChangePass changePass = new ChangePass();
                            changePass.ShowChangePass();
                            role = resultSet.getString("role");
                            CCCD = resultSet.getString("username");
                            PWD = resultSet.getString("password");
                            String sql1 = "insert into COVID.loginhistory(login,username) values (?,?)" ;
                            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                            preparedStatement.setString(1,login);
                            preparedStatement.setString(2,username);
                            preparedStatement.execute();
                        }
                        else {
                            role = resultSet.getString("role");
                            CCCD = resultSet.getString("username");
                            PWD = resultSet.getString("password");
                            String sql1 = "insert into COVID.loginhistory(login,username) values (?,?)" ;
                            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                            preparedStatement.setString(1,login);
                            preparedStatement.setString(2,username);
                            preparedStatement.execute();
                            if(new PaymentService().gethanChe(CCCD).equals("Yes")){
                                LocalTime time = LocalTime.now();
                                int timeM = time.getMinute();
                                if(new DuNoService().checKHetHan(CCCD,timeM) == true)
                                    new DuNoService().XuliHetHan(CCCD);
                            }
                            homePage.showHomePage();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"incorrect account");
                        UserName.setText("");
                        Password.setText("");
                    }
                    connection.close();

                }catch (Exception e1){
                    System.out.println(e1.getMessage());
                }

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        loginButton = new JButton(new ImageIcon(new ImageIcon("image\\login.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        loginButton.setPreferredSize(new Dimension(60, 60));
        resetButton = new JButton(new ImageIcon(new ImageIcon("image\\reset.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        resetButton.setPreferredSize(new Dimension(60, 60));
    }
}

