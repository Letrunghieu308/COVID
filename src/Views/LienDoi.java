package Views;

import QUANLY.BENHNHAN;
import Service.BenhnhanService;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LienDoi extends JFrame{
    private JPanel panel1;
    private JTable LienDoiTble;
    private JButton quayLạiButton;
    DefaultTableModel model2;
    public void ShowLienDoi(){
        LienDoi lienDoi = new LienDoi();
        lienDoi.setVisible(true);
        lienDoi.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public LienDoi(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setLocationRelativeTo(null);
        panel1.setBounds(50,50,500,200);
        quayLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<BENHNHAN> list = new ArrayList<>();
                BENHNHAN benhnhan = new BENHNHAN();
                String[] listbn = new BenhnhanService().getTenLienDoi(HomePage.lienDoi).split(",");
                for(String ten : listbn){
                    benhnhan = new BenhnhanService().getBenhNhanByID(ten);
                    list.add(benhnhan);
                }
                for(BENHNHAN benhnhantemp : list){
                    System.out.println(benhnhantemp);
                }
                System.out.println(HomePage.lienDoi);
                dispose();
                HomePage homePage = new HomePage();
                homePage.showHomePage();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String []header2 = {"Họ Tên" , "CCCD" , "Năm Sinh" , "Địa CHỉ"};
        model2 = new DefaultTableModel(0,4);
        model2.setColumnIdentifiers(header2);
        LienDoiTble  = new JTable(model2);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(LienDoiTble.getModel());
        LienDoiTble.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        ArrayList<BENHNHAN> list = new ArrayList<>();
        String[] listbn = new BenhnhanService().getTenLienDoi(HomePage.lienDoi).split(",");
        for(String ten : listbn){
            list.add( new BenhnhanService().getBenhNhanByID(ten));
        }
        for(BENHNHAN benhnhan : list){

            model2.addRow(new Object[]{benhnhan.getHoten(),benhnhan.getCCCD(),benhnhan.getNamSinh(),benhnhan.getDiaChi()});
        }
        LienDoiTble.setRowHeight(50);

        quayLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quayLạiButton.setPreferredSize(new Dimension(60, 60));

    }
}
