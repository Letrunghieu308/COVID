package Views;

import CONNECT.JDBC;
import QUANLY.BENHNHAN;
import Service.BenhnhanService;
import Service.NoidieutriService;
import Service.PaymentService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
public class HomePage extends JFrame{
    private JPanel HomePage;
    private JButton logoutButton;
    private JButton changePasswordButton;
    private JButton AdminPage;
    private JTable ShowInfo;
    private JScrollPane scrollPane1;
    private JTextField search;
    private JButton updatetrangthai;
    private JButton updateDtButton;
    private JButton view;
    private JButton paymentAccountManagementButton;
    private JTextField CCCDUpdate;
    private JButton DeleteBnh;
    private JButton historyButton;
    private JButton xácNhậnKhỏiBệnhButton;
    private JTable KhoiBenhTBle;
    private JButton thốngKêKhỏiBệnhButton;
    private JButton LienQuan;
    private JTable LienQuanTBle;
    private JButton searchBtn;
    private JButton loadButton;
    static String hoTen;
    DefaultTableModel model ;
    DefaultTableModel model1;
    DefaultTableModel model2;
    static String CCCD  ;
    static String lienDoi;

    public String F_to(String string){
        int index = Integer.parseInt(string.substring(string.length() - 1));
        index++;
        return "F"+index;
    }
    public void showHomePage(){
        HomePage homePage = new HomePage();
//        homePage.CheckUpdate();
        homePage.setVisible(true);
//        homePage.setLocationRelativeTo(null);
//        homePage.pack();
        homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public HomePage() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(HomePage);
        this.setLocationRelativeTo(null);

        HomePage.setBounds(50, 50, 500, 200);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    java.util.Date date=new java.util.Date();
                    DateFormat dateFormat = null;
                    dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    String logout = dateFormat.format(date);
                    //String sql1 = "insert into COVID.loginhistory(logout) values (?)" ;
                    //String sql = "select * from COVID.Users where login = '" + username + "'and password = '"+ password +"'";
                    String sql1 = "update covid.loginhistory set logout=?  where login = '" + LoginPage.logintime + "'" ;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                    preparedStatement.setString(1,logout);
                    preparedStatement.execute();
                }catch (Exception e1){
                    System.out.println(e1.getMessage());
                }
                dispose();
                LoginPage login = new LoginPage();
                login.showLoginPage();
//                login.setVisible(true);
//                login.setLocationRelativeTo(null);
//                login.pack();
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChangePass changePass = new ChangePass();
                changePass.ShowChangePass();
            }
        });
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
        updatetrangthai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.role.equals("admin") || LoginPage.role.equals("mod")){
                    int dem = 0;
                    while (dem < 2) {
                        try {
                            Connection connection = JDBC.connect();
                            Statement statement = connection.createStatement();
                            String sql = "select * from COVID.benhnhan where TrangThaiHienTai != 'chuaupdate'" ;
                            ResultSet resultSet  = statement.executeQuery(sql);
                            while (resultSet.next()){
                                String name_f0 = resultSet.getString("LienQuan");
                                String[] arrOfStr = name_f0.split(",", 10);
                                for(String name:arrOfStr){
                                    String sql1=  "update COVID.benhnhan set TrangThaiHienTai= ?  where HoTen = ?";
                                    PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                                    preparedStatement.setString(1,F_to(resultSet.getString("TrangThaiHienTai")));
                                    preparedStatement.setString(2,name);
                                    preparedStatement.execute();
                                    dispose();
                                }
                            }

                        }
                        catch (Exception e1){
                            e1.printStackTrace();
                        }
                        dem++;
                    }
                    HomePage homePage = new HomePage();
                    homePage.showHomePage();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Your role is not avaible");
                }
            }
        });
        AdminPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        if (LoginPage.role.equals("admin") || LoginPage.role.equals("mod")) {
                            dispose();
                            AdminPage adminPage = new AdminPage();
                            adminPage.showAdminPage();

                        } else {
                            JOptionPane.showMessageDialog(null, "Your role is not avaible");
                        }
                    } catch (Exception e2) {
                        e2.getMessage();
                    }
            }
        });
        scrollPane1.addContainerListener(new ContainerAdapter() {
        });

        updateDtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.role.equals("admin") || LoginPage.role.equals("mod")){
                    CCCD = CCCDUpdate.getText();
                    if (CCCD.equals("")) {
                        JOptionPane.showMessageDialog(null,"Bạn chưa chọn người cập nhật");
                        dispose();
                        HomePage homePage = new HomePage();
                        homePage.showHomePage();
                    }
                    else{
                        dispose();
                        UpdateInfo updateInfo = new UpdateInfo();
                        updateInfo.showUpdateInfo();
                    }


                }
            }
        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listTT = new ArrayList<>();
                ArrayList<Integer> listCount = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COVID","root","hieu3082001");
                    Statement statement = connection.createStatement();
                    String sql = "SELECT TrangThaiHienTai, count(TrangThaiHienTai) as thongke\n" +
                            "  FROM COVID.Benhnhan \n" +
                            " GROUP by TrangThaiHienTai" ;
                    ResultSet resultSet  = statement.executeQuery(sql);
                    while(resultSet.next()){
                        listTT.add(resultSet.getString("TrangThaiHienTai"));
                        listCount.add((resultSet.getInt("thongke")));
                    }

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
                JFreeChart chart = ChartFactory.createPieChart("Thống Kê người bệnh",defaultPieDataset,true,true,true);
                PiePlot piePlot = (PiePlot) chart.getPlot();
                ChartFrame frame = new ChartFrame("Thống Kê người bệnh" , chart);
                frame.setVisible(true);
                frame.setSize(450,500);
                //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            }
        });
        paymentAccountManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                  dispose();
                  PaymentAccount paymentAccount = new PaymentAccount();
                  paymentAccount.showPaymentAcc();

            }
        });
        ShowInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) ShowInfo.getModel();
                int index  = ShowInfo.getSelectedRow();
                CCCDUpdate.setText(ShowInfo.getValueAt(index,1).toString());


            }
        });
        DeleteBnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.role.equals("mod")){
                    new BenhnhanService().deleteBenhnhan(CCCDUpdate.getText());
                    new PaymentService().deletePayment(CCCDUpdate.getText());
                    dispose();
                    showHomePage();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Your role is not avalible");
                }
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginHistory page = new LoginHistory();
                page.showLoginHistory();
            }
        });
        xácNhậnKhỏiBệnhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginPage.role.equals("mod")){
                    new BenhnhanService().upDateKhoiBenh(CCCDUpdate.getText());
                    new NoidieutriService().GiamSoLuong(new BenhnhanService().getNoiDTriByCC(CCCDUpdate.getText()));
                    dispose();
                    HomePage homePage = new HomePage();
                    homePage.showHomePage();
                }
                else JOptionPane.showMessageDialog(null,"Bạn không thể thực hiện thao tác này");
            }
        });


        thốngKêKhỏiBệnhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultPieDataset dataset = new DefaultPieDataset();

                dataset.setValue("Bệnh nhân khỏi",new BenhnhanService().getSoluongBnhanKhoi());
                dataset.setValue("Bệnh nhân còn điều trị",new BenhnhanService().getSoluongBnhan());
                JFreeChart barChart = ChartFactory.createPieChart("Biểu đồ thống kê khỏi bệnh",dataset,true ,true,true);
                PiePlot piePlot = (PiePlot) barChart.getPlot();
                ChartFrame frame = new ChartFrame("Thống Kê người bệnh" , barChart);
                frame.setVisible(true);
                frame.setSize(450,500);
            }
        });
        KhoiBenhTBle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) KhoiBenhTBle.getModel();
                int index  = KhoiBenhTBle.getSelectedRow();
                CCCDUpdate.setText(KhoiBenhTBle.getValueAt(index,1).toString());
            }
        });
//        LienQuanTBle.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                String []header2 = {"Họ Tên" , "CCCD" , "Năm Sinh" , "Địa CHỉ"};
//                    model2.fireTableDataChanged();
//                    model2 = new DefaultTableModel(0,4);
//                    model2.setColumnIdentifiers(header2);
//                    LienQuanTBle  = new JTable(model2);
//                    TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(LienQuanTBle.getModel());
//                    LienQuanTBle.setRowSorter(sorter2);
//
//                    List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
//                    sortKeys2.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
//                    sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//                    String[] listLQ = new BenhnhanService().getTenLienDoi(CCCDUpdate.getText()).split(",");
//                    ArrayList<BENHNHAN> benhnhans = new ArrayList<>();
//                    for(String  ten : listLQ){
//                        benhnhans.add(new BenhnhanService().getBenhNhanByID(ten));
//                    }
//                    for(BENHNHAN benhnhan : benhnhans){
//                        model2.addRow(new Object[]{benhnhan.getHoten(),benhnhan.getCCCD(),benhnhan.getNamSinh(),benhnhan.getDiaChi()});
//                    }
//                    LienQuanTBle.setRowHeight(50);
//            }
//        });
        LienQuan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lienDoi= CCCDUpdate.getText();
                dispose();
                LienDoi lienDoi = new LienDoi();
                lienDoi.ShowLienDoi();
            }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        String header[] ={"Họ Tên" , " CCCD " , "Năm Sinh","Địa Chỉ", "Trạng Thái Hiện Tại","Nơi Đang Điều Trị","Liên Quan","Lịch Sử Covid"};
        model = new DefaultTableModel(0,8);
        model.setColumnIdentifiers(header);
        ShowInfo = new JTable(model);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(ShowInfo.getModel());
        ShowInfo.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        ArrayList<BENHNHAN> list = new BenhnhanService().getAllBenhnhan();
        for(BENHNHAN benhnhan : list){
            model.addRow(new Object[]{benhnhan.getHoten(), benhnhan.getCCCD(),benhnhan.getNamSinh(),benhnhan.getDiaChi(),benhnhan.getTrangThaiHienTai(),benhnhan.getLienQuan(),benhnhan.getNoiDangDT(),benhnhan.getLSDieuTri()});
        }
        ShowInfo.setRowHeight(50);



        String [] header1 = {"Họ Tên" , " CCCD " , "Năm Sinh","Địa Chỉ", "Trạng Thái Hiện Tại","Nơi Đang Điều Trị","Liên Quan","Lịch Sử Covid"};
        model1 = new DefaultTableModel(0,8);
        model1.setColumnIdentifiers(header1);

        KhoiBenhTBle = new JTable(model1);
        TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(KhoiBenhTBle.getModel());
        KhoiBenhTBle.setRowSorter(sorter1);

        List<RowSorter.SortKey> sortKeys1 = new ArrayList<>(25);
        sortKeys1.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys1.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        ArrayList<BENHNHAN> list1 = new BenhnhanService().getBNKhoiBenh();
        for(BENHNHAN benhnhan : list1){
            model1.addRow(new Object[]{benhnhan.getHoten(), benhnhan.getCCCD(),benhnhan.getNamSinh(),benhnhan.getDiaChi(),benhnhan.getTrangThaiHienTai(),benhnhan.getLienQuan(),benhnhan.getNoiDangDT(),benhnhan.getLSDieuTri()});
        }
        KhoiBenhTBle.setRowHeight(50);

        logoutButton = new JButton(new ImageIcon(new ImageIcon("image\\close.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        logoutButton.setPreferredSize(new Dimension(60, 60));
        AdminPage = new JButton(new ImageIcon(new ImageIcon("image\\go.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        AdminPage.setPreferredSize(new Dimension(60, 60));
        view = new JButton(new ImageIcon(new ImageIcon("image\\view.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        view.setPreferredSize(new Dimension(60, 60));
        thốngKêKhỏiBệnhButton = new JButton(new ImageIcon(new ImageIcon("image\\view.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thốngKêKhỏiBệnhButton.setPreferredSize(new Dimension(60, 60));
        updatetrangthai = new JButton(new ImageIcon(new ImageIcon("image\\update.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        updatetrangthai.setPreferredSize(new Dimension(60, 60));
        updateDtButton = new JButton(new ImageIcon(new ImageIcon("image\\update.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        updateDtButton.setPreferredSize(new Dimension(60, 60));
        changePasswordButton = new JButton(new ImageIcon(new ImageIcon("image\\pass.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        changePasswordButton.setPreferredSize(new Dimension(60, 60));
        paymentAccountManagementButton = new JButton(new ImageIcon(new ImageIcon("image\\payment.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        paymentAccountManagementButton.setPreferredSize(new Dimension(60, 60));
        historyButton = new JButton(new ImageIcon(new ImageIcon("image\\clock.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        historyButton.setPreferredSize(new Dimension(60, 60));
        DeleteBnh = new JButton(new ImageIcon(new ImageIcon("image\\delete.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        DeleteBnh.setPreferredSize(new Dimension(60, 60));
        xácNhậnKhỏiBệnhButton = new JButton(new ImageIcon(new ImageIcon("image\\OK.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        xácNhậnKhỏiBệnhButton.setPreferredSize(new Dimension(60, 60));
        LienQuan = new JButton(new ImageIcon(new ImageIcon("image\\view.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        LienQuan.setPreferredSize(new Dimension(60, 60));
//        AdminPage = new JButton(new ImageIcon(new ImageIcon("image\\next.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
//        AdminPage.setPreferredSize(new Dimension(60, 60));
//        updateDtButton = new JButton(new ImageIcon(new ImageIcon("image\\close.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
//        updateDtButton.setPreferredSize(new Dimension(60, 60));
    }


}
