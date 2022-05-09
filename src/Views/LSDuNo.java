package Views;

import QUANLY.DuNo;
import Service.DuNoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LSDuNo extends  JFrame{
    private JPanel panel1;
    private JTable LsuDU;
    private JButton backButton;
    DefaultTableModel model;
    public LSDuNo(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.showAdminPage();
            }
        });
    }
    public void ShowLSDuNo(){
        LSDuNo lsDuNo = new LSDuNo();
        lsDuNo.setVisible(true);
        lsDuNo.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        DuNoService duNoService = new DuNoService();
        String header[] = {"CCCD" , " Ngày ghi nợ " , " Tên sản phẩm nợ "};
        model= new DefaultTableModel(0,3);
        model.setColumnIdentifiers(header);
        LsuDU = new JTable(model);
        ArrayList<DuNo> list = duNoService.getAllDuNo();
        for(DuNo duNo : list){
            model.addRow(new Object[]{duNo.getCCCD(),duNo.getNgayNo(),duNo.getTenSPNo()});
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(LsuDU.getModel());
        LsuDU.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

        LsuDU.setRowHeight(50);
        backButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        backButton.setPreferredSize(new Dimension(60, 60));

    }
}
