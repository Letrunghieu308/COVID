package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangePass extends JFrame {
    private JTextField NewPass;
    private JTextField ReNP;
    private JPanel ChangePass;
    private JButton Back;
    private JButton Ok;
    private JTextField OldPass;
    private JLabel TestPass;
    private  JTextField Id;
    public void ShowChangePass(){
        ChangePass changePass = new ChangePass();
        changePass.setVisible(true);
        changePass.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        changePass.setLocationRelativeTo(null);
//        changePass.pack();
    }

    public ChangePass() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ChangePass);
//        this.setLocationRelativeTo(null);
//        this.setLocationRelativeTo(null);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.PWD.equals("password")){
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.showLoginPage();
                }
                else{
                    dispose();
                    HomePage homePage = new HomePage();
                    homePage.showHomePage();
                }
            }
        });
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    String oldPassword = OldPass.getText();
                    String newPassword = NewPass.getText();
                    String reNPassword = ReNP.getText();
                    String sql = "select * from COVID.Users where username = '" + LoginPage.CCCD +"'";
                    ResultSet resultSet  = statement.executeQuery(sql);
                    if((resultSet.next()) &&(oldPassword.equals(LoginPage.PWD))){
                        String sql1 = "update `COVID`.`Users` set `password`=? where `username` = ?" ;
                        //String sql1 = "update `COVID`.`SanPham` set `TenSanPham`=?,`DonVi`=?,`DonGia` =?,`Image`=? where `TenSanPham`=?";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                        preparedStatement.setString(1,newPassword);
                        preparedStatement.setString(2,LoginPage.CCCD);
                        preparedStatement.execute();
                        dispose();
                        HomePage homePage = new HomePage();
                        homePage.showHomePage();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Information is not true");
                    }

                }
                catch (Exception e2){
                    System.out.println(e2.getMessage());

                }

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Ok = new JButton(new ImageIcon(new ImageIcon("image\\ok.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        Ok.setPreferredSize(new Dimension(60, 60));
        Back = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        Back.setPreferredSize(new Dimension(60, 60));
    }
}
