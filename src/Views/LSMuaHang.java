package Views;

import QUANLY.LichSuMuaHang;
import Service.LSMHService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LSMuaHang extends JFrame{
    private JPanel lichsu;
    private JButton backButton;
    private JTable ls;
    DefaultTableModel model;

    public LSMuaHang(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(lichsu);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.showAdminPage();
            }
        });
    }
    public void ShowLSMH(){
        LSMuaHang lsMuaHang = new LSMuaHang();
        lsMuaHang.setVisible(true);
        lsMuaHang.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        LSMHService lsmhService = new LSMHService();
        // TODO: place custom component creation code here
        String header[] = {"CCCD", "Ngày mua","Tên gói sản phẩm"};
        model = new DefaultTableModel(0,3);
        model.setColumnIdentifiers(header);
        ls = new JTable(model);
        ArrayList<LichSuMuaHang> lichSuMuaHangs =  lsmhService.getAllLS();
        for(LichSuMuaHang lichSuMuaHang : lichSuMuaHangs){
            model.addRow(new Object[]{lichSuMuaHang.getCCCD(),lichSuMuaHang.getNgayThang(),lichSuMuaHang.getGoimua()});
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(ls.getModel());
        ls.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

        ls.setRowHeight(50);
        backButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        backButton.setPreferredSize(new Dimension(60, 60));

    }
}
