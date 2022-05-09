package Views;

import QUANLY.DSSANPHAM;
import QUANLY.GOINHUYEUPHAM;
import Service.DSSanphamService;
import Service.NhuyeuphamService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class ChiTiet extends  JFrame {
    private JPanel ChiTiet;
    private JTable SPList;
    private JButton quayLạiButton;
    private JLabel TenGoi;
    private JLabel NgayHetHan;
    private JLabel GioiHan;
    private JLabel ThanhTien;
    DefaultTableModel model ;

    public String F_to(String string){
        int index = Integer.parseInt(string.substring(string.length() - 1));
        index++;
        return "F"+index;
    }
    public void showChiTiet(){
        ChiTiet chiTiet = new ChiTiet();
        chiTiet.setVisible(true);
        chiTiet.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        chiTiet.setLocationRelativeTo(null);
//        chiTiet.pack();
    }
    public ChiTiet(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ChiTiet);
//        this.setLocationRelativeTo(null);
//        this.setLocationRelativeTo(null);
        NhuyeuphamService nhuyeuphamService = new NhuyeuphamService();
        GOINHUYEUPHAM goinhuyeupham = nhuyeuphamService.getNhuYeuPham(NhuYeuPhamManagement.Package);
        TenGoi.setText(goinhuyeupham.getTENGOI());
        GioiHan.setText(String.valueOf(goinhuyeupham.getMUCGIOIHAN()));
        NgayHetHan.setText(goinhuyeupham.getNGAYHETHAN());
        ThanhTien.setText(String.valueOf(nhuyeuphamService.tinhThanhTien(TenGoi.getText())) );
        quayLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NhuYeuPhamManagement nhuYeuPhamManagement = new NhuYeuPhamManagement();
                nhuYeuPhamManagement.showNYPManagement();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        String tengoi = NhuYeuPhamManagement.Package;
        DSSanphamService dsSanphamService = new DSSanphamService();
        String header[] ={"Tên Gói" , "Tên Sản phẩm" , "Số lượng"};
        model = new DefaultTableModel(0,3);
        model.setColumnIdentifiers(header);
        SPList = new JTable(model);
        List<DSSANPHAM> dssp;
        dssp = dsSanphamService.getAllDSSanPhamVoiTenGoi(tengoi);
        for (DSSANPHAM dssanpham: dssp){
            System.out.println(dssp);
            model.addRow(new Object[]{NhuYeuPhamManagement.Package, dssanpham.getTenSanPham(), dssanpham.getSoLuong()});
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(SPList.getModel());
        SPList.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        SPList.setRowHeight(50);
        quayLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quayLạiButton.setPreferredSize(new Dimension(60, 60));

    }
}
