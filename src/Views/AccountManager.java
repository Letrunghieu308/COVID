package Views;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager extends JFrame {
    private JPanel AccManager;
    private JTable showinfo;
    private JButton backButton;
    DefaultTableModel model;
    public void showAccManager(){
        AccountManager accountManager = new AccountManager();
        accountManager.setVisible(true);
        accountManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        accountManager.setLocationRelativeTo(null);
//        accountManager.pack();
    }
    public AccountManager(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(AccManager);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.showAdminPage();
                //homePage.showHomePage();
            }
        });
//        this.setLocationRelativeTo(null);
//        this.setLocationRelativeTo(null);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        String header[] = {"Tên Tài Khoản" , "Mật Khẩu" , "Vai Trò"};
        model = new DefaultTableModel(0,3);
        model.setColumnIdentifiers(header);
        showinfo = new JTable(model);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
            Statement statement = connection.createStatement();
            String sql = "select * from COVID.Users where role ='mod' or role = 'lock'";
            ResultSet resultSet = statement.executeQuery(sql);
            String revert = "";
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println(resultSet.getString("password").length());
                for(int i = 0 ; i < password.length() ; i ++ ) {
                    revert = revert + "*";
                }
                String role = resultSet.getString("role");
                Object[] tbData = {username,revert,role };
                model.addRow(tbData);

                revert = "";
            }
        }catch (Exception exception){
            exception.getMessage();
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(showinfo.getModel());
        showinfo.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        showinfo.setRowHeight(50);
        backButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        backButton.setPreferredSize(new Dimension(60, 60));
    }
}
