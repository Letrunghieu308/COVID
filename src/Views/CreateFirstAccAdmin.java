package Views;

import QUANLY.USERS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.*;

public class CreateFirstAccAdmin extends JFrame{
    private JPanel createFirstAccAdmin;
    private JTextField Id;
    private JTextField Username;
    private JTextField Password;
    private JButton CreateBtn;
    private JButton CloseBtn;
    public void ShowCreateFirstAccAdmin(){

    }

    public CreateFirstAccAdmin(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(createFirstAccAdmin);
//        this.setLocationRelativeTo(null);
        CreateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    int id = Integer.parseInt(Id.getText());
                    String username = Username.getText();
                    String password = Password.getText();
                    String sql = "insert into `COVID`.`Users`(`username`,`password`,`role`) value (?,?,?)" ;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,password);
                    preparedStatement.setString(3,"admin");
                    preparedStatement.execute();
                    dispose();
                    HomePage homePage = new HomePage();
                    homePage.showHomePage();


                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });
        CloseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FirstPage createFirstAccAdmin = new FirstPage();
                createFirstAccAdmin.setVisible(true);
                createFirstAccAdmin.setLocationRelativeTo(null);
                createFirstAccAdmin.pack();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        CreateBtn = new JButton(new ImageIcon(new ImageIcon("image\\OK.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        CreateBtn.setPreferredSize(new Dimension(60, 60));
        CloseBtn = new JButton(new ImageIcon(new ImageIcon("image\\close.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        CloseBtn.setPreferredSize(new Dimension(60, 60));
    }
}
