package Views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.*;

public class FirstPage extends JFrame {
    private JPanel firstPage;
    private JButton createAdminAcc;
    private JButton CloseBtn;
    private JLabel title;
    private JLabel bg;

    public void showFirstPage(){
        FirstPage firstPage = new FirstPage();
        firstPage.setVisible(true);
        //firstPage.setLocationRelativeTo(null);
        //firstPage.pack();
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //firstPage.setLocation(dim.width/2-firstPage.getSize().width/2, dim.height/2-firstPage.getSize().height/2);
        //firstPage.setSize(1920, 1080);
        firstPage.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //title.setFont(new Font("NewellsHand", Font.PLAIN, 100));
    }
    public FirstPage(){
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH)
        this.setContentPane(firstPage);
        //this.setLocationRelativeTo(null);
        //createAdminAcc = new JButton("Click Button");
//
        createAdminAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    String sql = "select * from COVID.Users";
                    ResultSet resultSet = statement.executeQuery(sql);
                    int count = 0 ;
                    while (resultSet.next()){
                        count ++ ;
                    }
                    if(count == 0 ){
                        dispose();
                        CreateFirstAccAdmin createFirstAccAdmin = new CreateFirstAccAdmin("CreateFirstAccAction");
                        createFirstAccAdmin.setVisible(true);
                        createFirstAccAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
//                        createFirstAccAdmin.setLocationRelativeTo(null);
//                        createFirstAccAdmin.pack();
                    }
                    else{

                        dispose();
                        LoginPage loginPage = new LoginPage();
                        loginPage.showLoginPage();
                    }


                }catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });
        CloseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    private void createUIComponents() {
        //createAdminAcc = new JButton(new ImageIcon("D:\\Java\\COVIDPRJ - Copy\\image\\next.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);

        createAdminAcc = new JButton(new ImageIcon(new ImageIcon("image\\next.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        createAdminAcc.setPreferredSize(new Dimension(60, 60));
        title = new JLabel(new ImageIcon(new ImageIcon("image\\bg.jpg").getImage().getScaledInstance(1515, 450, Image.SCALE_DEFAULT)));
//        title.setPreferredSize(new Dimension(60, 60));
        CloseBtn = new JButton(new ImageIcon(new ImageIcon("image\\close.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        CloseBtn.setPreferredSize(new Dimension(60, 60));
    }
}
