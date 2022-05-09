package Views;

import QUANLY.DSSANPHAM;
import QUANLY.GOINHUYEUPHAM;
import Service.DSSanphamService;
import Service.NhuyeuphamService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddDSSP extends  JFrame{
    private JTextField TenSP;
    private JTextField SoLuong;
    private JButton thêmSảnPhẩmButton;
    private JButton thoátButton;
    private JPanel addDSSP;
    private JTable SPList;
    //private JButton sửaSảnPhẩmButton;
    DefaultTableModel model ;

    public String F_to(String string){
        int index = Integer.parseInt(string.substring(string.length() - 1));
        index++;
        return "F"+index;
    }

    public void showAddInfo(){
        AddDSSP addSP = new AddDSSP();
        addSP.setVisible(true);
        addSP.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        addSP.setLocationRelativeTo(null);
//        addSP.pack();
    }

    public AddDSSP(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(addDSSP);
//        this.setLocationRelativeTo(null);

        thêmSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        thoátButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NhuYeuPhamManagement nypManagement = new NhuYeuPhamManagement();
                nypManagement.showNYPManagement();
            }
        });
//        sửaSảnPhẩmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//                ChooseUpdate3 chooseUpdate3 = new ChooseUpdate3();
//                chooseUpdate3.showChooseUpdate3();
//            }
//        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String tengoi = AddNYP.TENGOI;
        DSSanphamService dsSanphamService = new DSSanphamService();
        String header[] ={"Tên Gói" , "Tên Sản phẩm" , "Giới hạn"};
        model = new DefaultTableModel(0,8);
        model.setColumnIdentifiers(header);
        SPList = new JTable(model);
        List<DSSANPHAM> dssp;
        dssp = dsSanphamService.getAllDSSanPhamVoiTenGoi(tengoi);
        for (DSSANPHAM dssanpham: dssp){
            System.out.println(dssp);
            model.addRow(new Object[]{NhuYeuPhamManagement.Package, dssanpham.getTenSanPham(), dssanpham.getSoLuong()});
        }
        SPList.setRowHeight(50);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(SPList.getModel());
        SPList.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        thêmSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thêmSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
        thoátButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thoátButton.setPreferredSize(new Dimension(60, 60));
    }
}
