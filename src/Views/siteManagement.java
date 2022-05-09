package Views;

import QUANLY.*;
import Service.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class siteManagement extends JFrame{
    private JTable NoiDieuTri;
    private JButton thêmBệnhViệnButton;
    private JButton UpdateSite;
    private JPanel siteManagement;
    private JButton AdminBack;
    private JTextField hsptalUpdate;
    private JTextField SchuaField;
    private JScrollPane ScrollPane1;
    private JButton Xoa;
    static String diaChi;
    DefaultTableModel model ;

    public String F_to(String string){
        int index = Integer.parseInt(string.substring(string.length() - 1));
        index++;
        return "F"+index;
    }
    public void showSiteManagement(){
        siteManagement siteManagement = new siteManagement();
        siteManagement.setVisible(true);
        siteManagement.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        siteManagement.setLocationRelativeTo(null);
//        siteManagement.pack();
    }
    public siteManagement(){
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(siteManagement);
//        this.setLocationRelativeTo(null);
//        this.setLocationRelativeTo(null);
        thêmBệnhViệnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddSite addSite = new AddSite();
                addSite.showAddInfo();
            }
        });

        UpdateSite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NoidieutriService().updateSucChua(hsptalUpdate.getText(),Integer.parseInt(SchuaField.getText()));
                siteManagement siteManagement = new siteManagement();
                siteManagement.showSiteManagement();
            }
        });
        AdminBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.showAdminPage();
            }
        });

        NoiDieuTri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) NoiDieuTri.getModel();
                int index  = NoiDieuTri.getSelectedRow();
                hsptalUpdate.setText(NoiDieuTri.getValueAt(index,0).toString());
                diaChi = hsptalUpdate.getText();
            }
        });
        Xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NoidieutriService().deleteNoidieutri(hsptalUpdate.getText());
                dispose();
                Views.siteManagement siteManagement = new siteManagement();
                siteManagement.showSiteManagement();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        NoidieutriService noidieutriService = new NoidieutriService();
        String header[] ={"Địa chỉ" , "Sức chứa" , "Số lương"};
        model = new DefaultTableModel(0,8);
        model.setColumnIdentifiers(header);
        NoiDieuTri = new JTable(model);
        List<NOIDIEUTRI> dsNoiDieuTri;
        dsNoiDieuTri = noidieutriService.getAllNoidieutri();

        for (NOIDIEUTRI noidieutri : dsNoiDieuTri){
            model.addRow(new Object[]{noidieutri.getDiaChi(), noidieutri.getSucChua(), noidieutri.getSoLuong()});
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(NoiDieuTri.getModel());
        NoiDieuTri.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

        NoiDieuTri.setRowHeight(50);
        thêmBệnhViệnButton = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thêmBệnhViệnButton.setPreferredSize(new Dimension(60, 60));
        UpdateSite = new JButton(new ImageIcon(new ImageIcon("image\\update.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        UpdateSite.setPreferredSize(new Dimension(60, 60));
        Xoa = new JButton(new ImageIcon(new ImageIcon("image\\delete.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        Xoa.setPreferredSize(new Dimension(60, 60));
        AdminBack = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        AdminBack.setPreferredSize(new Dimension(60, 60));

    }
}
