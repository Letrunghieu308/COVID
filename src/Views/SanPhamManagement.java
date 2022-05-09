package Views;

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
import java.util.ArrayList;
import java.util.List;


public class SanPhamManagement extends JFrame{
    private JPanel SPManagement;
    private JTextField search;
    private JLabel searchLabel;
    private JTable ShowSP;
    private JButton thêmSảnPhẩmButton;
    private JButton quayLạiButton;
    private JButton cậpNhậtSảnPhẩmButton;
    private JButton xóaSảnPhẩmButton;
    private JTextField tenSP;
    DefaultTableModel model ;
    static String nameSP;

    public String F_to(String string){
        int index = Integer.parseInt(string.substring(string.length() - 1));
        index++;
        return "F"+index;
    }

    public void showSanPhamManagement(){
        SanPhamManagement spManagement = new SanPhamManagement();
        spManagement.setVisible(true);
        spManagement.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        spManagement.setLocationRelativeTo(null);
//        spManagement.pack();
    }

    public SanPhamManagement(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(SPManagement);
//        this.setLocationRelativeTo(null);
//        this.setLocationRelativeTo(null);
        thêmSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddSanPham addSanPham = new AddSanPham();
                addSanPham.showAddInfo();
            }
        });

        cậpNhậtSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UpdateSanPham updateSanPham = new UpdateSanPham();
                updateSanPham.showUpdateSP();
            }
        });
        xóaSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//                DeleteSanPham choosedelete = new DeleteSanPham();
//                choosedelete.showChooseDelete();
                new SanphamService().deleteSanPham(tenSP.getText());
                ArrayList<String> list = new NhuyeuphamService().getTenGoiNYP();
                for(String name : list){
                    new DSSanphamService().deleteDSSanPham(name, tenSP.getText());
                }
                dispose();
                SanPhamManagement sanPhamManagement = new SanPhamManagement();
                sanPhamManagement.showSanPhamManagement();
            }
        });

        quayLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.showAdminPage();
            }
        });

        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultTableModel tableModel = (DefaultTableModel)ShowSP.getModel();
                String  searching = search.getText();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
                ShowSP.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(searching));
            }
        });
        ShowSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) ShowSP.getModel();
                int index  = ShowSP.getSelectedRow();
                tenSP.setText(ShowSP.getValueAt(index,0).toString());
                nameSP = tenSP.getText();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        SanphamService sanphamService = new SanphamService();
        String header[] ={"Tên Sản phẩm" , "Đơn vị" , "Đơn giá", "Ảnh sản phẩm"};
        model = new DefaultTableModel(0,4);
        model.setColumnIdentifiers(header);
        ShowSP = new JTable(model);
        TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(ShowSP.getModel());
        ShowSP.setRowSorter(sorter1);

        List<RowSorter.SortKey> sortKeys1 = new ArrayList<>(25);
        sortKeys1.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        List<SANPHAM> dsSanPham;
        dsSanPham = sanphamService.getAllSanPham();
        for (SANPHAM sanpham: dsSanPham){
            model.addRow(new Object[]{sanpham.getTENSANPHAM(), sanpham.getDONVITINH(), sanpham.getDONGIA(), new ImageIcon(new ImageIcon(sanpham.getANHSANPHAM()).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT))});
        }

        ShowSP = new JTable(model) {
            public Class getColumnClass(int column) {
                if(column == 3)
                {
                    return Icon.class;
                }
                else
                    return  Object.class;
            }
        };
        ShowSP.setRowHeight(50);
        cậpNhậtSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\update.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        cậpNhậtSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
        thêmSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thêmSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
        xóaSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\delete.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        xóaSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
        quayLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quayLạiButton.setPreferredSize(new Dimension(60, 60));
    }
}
