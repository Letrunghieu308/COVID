package Views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminPage extends JFrame{
    private JPanel AdminPage;
    private JButton backButton;
    private JButton CreateAc;
    private JButton QlyAcc;
    private JButton lockAccoutManagementButton;
    private JButton siteManagement;
    private JButton quảnLýSảnPhẩmButton;
    private JButton quảnLýNhuYếuButton;
    private JButton lịchSửMuaHàngButton;
    private JButton lsduno;
    private JButton thốngKêNơiĐiềuButton;

    public void showAdminPage(){
        Views.AdminPage adminPage = new AdminPage();
        adminPage.setVisible(true);

//        adminPage.setLocationRelativeTo(null);
//        adminPage.pack();
        adminPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public AdminPage(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(AdminPage);
//        this.setLocationRelativeTo(null);
//        this.setLocationRelativeTo(null);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage();
                homePage.showHomePage();
            }
        });
        CreateAc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    dispose();
                    CreateAcountUser createAcCount = new CreateAcountUser();
                    createAcCount.showCreateUser();

//                createAcCount.setVisible(true);
//                createAcCount.setLocationRelativeTo(null);
//                createAcCount.pack();
            }
        });

//        addInfoCovidButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AddInfo addInfo = new AddInfo("AddInfoPage");
//                addInfo.setVisible(true);
//                addInfo.setLocationRelativeTo(null);
//                addInfo.pack();
//            }
//        });

        QlyAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(LoginPage.role.equals("admin")){
                   dispose();
                   AccountManager accountManager = new AccountManager();
                   accountManager.showAccManager();
               }
               else  JOptionPane.showMessageDialog(null,"Your role is not available");
            }
        });

        lockAccoutManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(LoginPage.role.equals("admin")){
                   dispose();
                   lockMngment lockMngment = new lockMngment();
                   lockMngment.showLock();
               }
               else JOptionPane.showMessageDialog(null,"Your role is not available");
            }
        });

        siteManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.role.equals("admin")){dispose();
                    Views.siteManagement siteManagement = new siteManagement();
                    siteManagement.showSiteManagement();
                }
                else JOptionPane.showMessageDialog(null,"Your role is not available");
            }
        });
        quảnLýSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(LoginPage.role.equals("mod")){
                   dispose();
                   Views.SanPhamManagement spManagement = new SanPhamManagement();
                   spManagement.showSanPhamManagement();
               }
               else JOptionPane.showMessageDialog(null,"Your role is not available");
            }
        });
        quảnLýNhuYếuButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (LoginPage.role.equals("mod")){
                    dispose();
                    NhuYeuPhamManagement nypManagement = new NhuYeuPhamManagement();
                    nypManagement.showNYPManagement();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Your role is not available");
                }

            }
        });
        lịchSửMuaHàngButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.role.equals("mod")){
                    dispose();
                    LSMuaHang lsMuaHang = new LSMuaHang();
                    lsMuaHang.ShowLSMH();
                }
                else JOptionPane.showMessageDialog(null,"Your role is not available");
            }
        });
        lsduno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.role.equals("mod")){
                    dispose();
                    LSDuNo lsDuNo = new LSDuNo();
                    lsDuNo.ShowLSDuNo();
                }
             else JOptionPane.showMessageDialog(null,"Your role is not available");
            }
        });
        thốngKêNơiĐiềuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listTT = new ArrayList<>();
                listTT.add("SucChua");
                listTT.add("SoLuong");
                ArrayList<Integer> listCount = new ArrayList<>();
                int succhua = 0;
                int soluong = 0;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    String sql = "SELECT SucChua,SoLuong " + "  FROM COVID.noidieutri \n" ;
                    ResultSet resultSet  = statement.executeQuery(sql);
                    while(resultSet.next()){
                        succhua = succhua + resultSet.getInt("SucChua");
                        soluong = soluong + resultSet.getInt("SoLuong");
                    }
                    listCount.add(succhua);
                    listCount.add(soluong);
                    System.out.println(listCount);

                }
                catch (Exception exception){
                    exception.getMessage();
                }
                String[] listtrangthai = new String[listTT.size()];
                listtrangthai = listTT.toArray(listtrangthai);
                Integer[] listcount = new Integer[listCount.size()];
                listcount = listCount.toArray(listcount);
                DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
                for(int i = 0 ; i < listtrangthai.length;i ++){
                    defaultPieDataset.setValue(listtrangthai[i] , new Integer(listcount[i]));
                }
                JFreeChart chart = ChartFactory.createPieChart("Thống kê sức chứa",defaultPieDataset,true,true,true);
                PiePlot piePlot = (PiePlot) chart.getPlot();
                ChartFrame frame = new ChartFrame("Thống kê sức chứa" , chart);
                frame.setVisible(true);
                frame.setSize(450,500);
                //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        backButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        backButton.setPreferredSize(new Dimension(60, 60));
        CreateAc = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        CreateAc.setPreferredSize(new Dimension(60, 60));
        QlyAcc = new JButton(new ImageIcon(new ImageIcon("image\\manager.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        QlyAcc.setPreferredSize(new Dimension(60, 60));
        lockAccoutManagementButton = new JButton(new ImageIcon(new ImageIcon("image\\lock.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        lockAccoutManagementButton.setPreferredSize(new Dimension(60, 60));
        siteManagement = new JButton(new ImageIcon(new ImageIcon("image\\ambulance.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        siteManagement.setPreferredSize(new Dimension(60, 60));
        quảnLýSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\food.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quảnLýSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
        quảnLýNhuYếuButton = new JButton(new ImageIcon(new ImageIcon("image\\product.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quảnLýNhuYếuButton.setPreferredSize(new Dimension(60, 60));
        lịchSửMuaHàngButton = new JButton(new ImageIcon(new ImageIcon("image\\clock.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        lịchSửMuaHàngButton.setPreferredSize(new Dimension(60, 60));
        lsduno = new JButton(new ImageIcon(new ImageIcon("image\\clock.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        lsduno.setPreferredSize(new Dimension(60, 60));
        thốngKêNơiĐiềuButton = new JButton(new ImageIcon(new ImageIcon("image\\view.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thốngKêNơiĐiềuButton.setPreferredSize(new Dimension(60, 60));


    }
}
