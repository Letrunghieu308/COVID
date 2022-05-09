package Views;

import CONNECT.JDBC;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;


@SuppressWarnings("ALL")
public class LoginHistory extends JFrame{
    private JPanel LoginHistory;
    private JButton Back;
//    private JButton changePasswordButton;

    private JTable ShowInfo;
    private JScrollPane scrollPane1;
    private JTextField search;
//    private JButton Refresh;
//    private JButton updateDtButton;
//    private JButton view;
//    private JButton paymentAccountManagementButton;
//    private JButton searchBtn;
//    private JButton loadButton;
    static String hoTen;
    DefaultTableModel model ;

//    public String F_to(String string){
//        int index = Integer.parseInt(string.substring(string.length() - 1));
//        index++;
//        return "F"+index;
//    }
    public void showLoginHistory(){
        LoginHistory Page = new LoginHistory();
        Page.setVisible(true);
        Page.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public LoginHistory() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(LoginHistory);
        this.setLocationRelativeTo(null);

        //HomePage.setBounds(50, 50, 500, 200);


        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultTableModel tableModel = (DefaultTableModel)ShowInfo.getModel();
                String  searching = search.getText();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
                ShowInfo.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(searching));
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage();
                homePage.showHomePage();
//                login.setVisible(true);
//                login.setLocationRelativeTo(null);
//                login.pack();
            }
        });
        scrollPane1.addContainerListener(new ContainerAdapter() {
        });

    }
    private void createUIComponents() {
        //System.out.println(java.time.LocalDate.now());


        // TODO: place custom component creation code here
        String header[] ={"Thời gian đăng nhập" , "Thời gian đăng xuất", "Username"};
        model = new DefaultTableModel(0,2);
        model.setColumnIdentifiers(header);
        ShowInfo = new JTable(model);
        if(LoginPage.role.equals("admin") || LoginPage.role.equals("mod")){
            try {

                Connection connection = JDBC.connect();
                Statement statement = connection.createStatement();
                String sql = "select * from covid.loginhistory";
                ResultSet resultSet  = statement.executeQuery(sql);
                while(resultSet.next()){
                    String login = resultSet.getString("login");
                    String logout = resultSet.getString("logout");
                    String username = resultSet.getString("username");
                    Object [] tbData  = {login,logout,username};
                    model.addRow(tbData);
                }
                connection.close();
            }
            catch (Exception exception){
                exception.getMessage();
            }
        }
        else {
            try {
                Connection connection = JDBC.connect();
                Statement statement = connection.createStatement();
                String sql = "select * from covid.loginhistory where username = '" +  LoginPage.CCCD +"'" ;
                ResultSet resultSet  = statement.executeQuery(sql);
                while(resultSet.next()){
                    String login = resultSet.getString("login");
                    String logout = resultSet.getString("logout");
                    String username = resultSet.getString("username");
                    Object [] tbData  = {login,logout,username};
                    model.addRow(tbData);
                }
                connection.close();
            }
            catch (Exception exception){
                exception.getMessage();
            }
        }

        ShowInfo.setRowHeight(50);
        Back = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        Back.setPreferredSize(new Dimension(60, 60));
//        AdminPage = new JButton(new ImageIcon(new ImageIcon("image\\next.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
//        AdminPage.setPreferredSize(new Dimension(60, 60));
//        updateDtButton = new JButton(new ImageIcon(new ImageIcon("image\\close.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
//        updateDtButton.setPreferredSize(new Dimension(60, 60));
    }


}
