package Views;

import javax.swing.*;

import CONNECT.JDBC;
import QUANLY.GOINHUYEUPHAM;
import QUANLY.SANPHAM;
import Service.DSSanphamService;
import Service.NhuyeuphamService;
import Service.SanphamService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class NhuYeuPhamManagement extends JFrame {
    private JPanel NYPManagement;
    private JTextField search;
    private JTable showNYP;
    private JButton thêmButton;
    private JButton sửaButton;
    private JButton chiTiếtButton;
    private JButton closeButton;
    private JTextField goiUpdate;
    static String Package;
    DefaultTableModel model ;

    public String F_to(String string){
        int index = Integer.parseInt(string.substring(string.length() - 1));
        index++;
        return "F"+index;
    }

    public void showNYPManagement(){
        NhuYeuPhamManagement nypManagement = new NhuYeuPhamManagement();
        nypManagement.setVisible(true);
        nypManagement.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        nypManagement.setLocationRelativeTo(null);
//        nypManagement.pack();
    }
    public NhuYeuPhamManagement(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(NYPManagement);
//        this.setLocationRelativeTo(null);
//        this.setLocationRelativeTo(null);


        thêmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddNYP addNYP = new AddNYP();
                addNYP.showAddInfo();
            }
        });
        sửaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = JDBC.connect();
                    Statement statement = connection.createStatement();
                    String sql = "select * from COVID.nhuyeupham where TenGoi = '" + goiUpdate.getText() +"'" ;
                    ResultSet resultSet  = statement.executeQuery(sql);
                    if(resultSet.next()){
                        Package = resultSet.getString("TenGoi");
                        dispose();
                        UpdateGoiNPY updateGoiNPY = new UpdateGoiNPY();
                        updateGoiNPY.showUpdate();
                    }

                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        chiTiếtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Package = goiUpdate.getText();
                dispose();
                ChiTiet chiTiet = new ChiTiet();
                chiTiet.showChiTiet();
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
        showNYP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) showNYP.getModel();
                int index  = showNYP.getSelectedRow();
                goiUpdate.setText(showNYP.getValueAt(index,0).toString());
            }
        });

        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultTableModel tableModel = (DefaultTableModel)showNYP.getModel();
                String  searching = search.getText();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
                showNYP.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(searching));
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        NhuyeuphamService nhuyeuphamService = new NhuyeuphamService();
        DSSanphamService dsSanphamService = new DSSanphamService();
        String header[] ={"Tên Gói" , "Giới hạn mua" , "Ngày hết hạn", "Thành tiền"};
        model = new DefaultTableModel(0,4);
        model.setColumnIdentifiers(header);
        showNYP = new JTable(model);
        TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(showNYP.getModel());
        showNYP.setRowSorter(sorter1);

        List<RowSorter.SortKey> sortKeys1 = new ArrayList<>(25);
        sortKeys1.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys1.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        List<GOINHUYEUPHAM> dsNYP;
        dsNYP = nhuyeuphamService.getAllNhuYeuPham();
        JButton buttonCT = new JButton();
        for (GOINHUYEUPHAM goinhuyeupham: dsNYP){
            float thanhtien  = nhuyeuphamService.tinhThanhTien(goinhuyeupham.getTENGOI());

            model.addRow(new Object[]{goinhuyeupham.getTENGOI(), goinhuyeupham.getMUCGIOIHAN(), goinhuyeupham.getNGAYHETHAN(),thanhtien});
        }

        showNYP.setRowHeight(50);
        thêmButton = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thêmButton.setPreferredSize(new Dimension(60, 60));
        sửaButton = new JButton(new ImageIcon(new ImageIcon("image\\edit.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        sửaButton.setPreferredSize(new Dimension(60, 60));
        chiTiếtButton = new JButton(new ImageIcon(new ImageIcon("image\\info.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        chiTiếtButton.setPreferredSize(new Dimension(60, 60));
        closeButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        closeButton.setPreferredSize(new Dimension(60, 60));
    }
}
