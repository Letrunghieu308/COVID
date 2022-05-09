package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class lockMngment extends  JFrame{
    private JPanel Lock;
    private JTextField userLock;
    private JButton lockButton;
    private JButton backButton;
    public void showLock(){
        lockMngment lockMngment = new lockMngment();
        lockMngment.setVisible(true);
        lockMngment.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        lockMngment.setLocationRelativeTo(null);
//        lockMngment.pack();
    }
    public lockMngment(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Lock);
        this.setLocationRelativeTo(null);
        HomePage homePage = new HomePage();
        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userlock = userLock.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    String sql = "select * from COVID.Users where username = '" + userlock  +"'";
                    ResultSet resultSet  = statement.executeQuery(sql);
                    while (resultSet.next()){
                        String sql1 = "update `COVID`.`Users` set `role`=? where username = ?" ;
                        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                        preparedStatement.setString(1,"lock");
                        preparedStatement.setString(2,userlock);
                        preparedStatement.execute();
                        dispose();
                        AdminPage adminPage = new AdminPage();
                        adminPage.showAdminPage();
                    }
                }
                catch (Exception exception){
                    exception.getMessage();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.showAdminPage();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        lockButton = new JButton(new ImageIcon(new ImageIcon("image\\ok.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        lockButton.setPreferredSize(new Dimension(60, 60));
        backButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        backButton.setPreferredSize(new Dimension(60, 60));
    }
}
