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

public class UpdateGoiNPY extends JFrame {
    private JTextField GioiHan;
    private JTextField NgayHetHan;
    private JButton cậpNhậtButton;
    private JButton cậpNhậtSảnPhẩmButton;
    private JButton trởLạiButton;
    private JPanel UpdatePackage;
    private JTable SPList;
    DefaultTableModel model ;


    public void showUpdate(){
        UpdateGoiNPY updateNYP = new UpdateGoiNPY();
        updateNYP.setVisible(true);
        updateNYP.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        updateNYP.setLocationRelativeTo(null);
//        updateNYP.pack();
    }

    public UpdateGoiNPY(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(UpdatePackage);
//        this.setLocationRelativeTo(null);

        cậpNhậtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String TenGoi = NhuYeuPhamManagement.Package;
                System.out.println(TenGoi);
                int gioihan = Integer.parseInt(GioiHan.getText());
                String ngayhethan = NgayHetHan.getText();
                GOINHUYEUPHAM temp = new GOINHUYEUPHAM(TenGoi,gioihan,ngayhethan);
                System.out.println(temp);
                NhuyeuphamService nhuyeuphamService = new NhuyeuphamService();
                nhuyeuphamService.updateGoiSP(TenGoi,gioihan, ngayhethan);
                dispose();
                NhuYeuPhamManagement nhuYeuPhamManagement = new NhuYeuPhamManagement();
                nhuYeuPhamManagement.showNYPManagement();
            }
        });
        trởLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NhuYeuPhamManagement nhuYeuPhamManagement = new NhuYeuPhamManagement();
                nhuYeuPhamManagement.showNYPManagement();
            }
        });
        cậpNhậtSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UpdateDSSP updateDSSP = new UpdateDSSP();
                updateDSSP.showUpdate();
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
        SPList.setRowHeight(50);
        cậpNhậtButton = new JButton(new ImageIcon(new ImageIcon("image\\update.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        cậpNhậtButton.setPreferredSize(new Dimension(60, 60));
        cậpNhậtSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\food.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        cậpNhậtSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
        trởLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        trởLạiButton.setPreferredSize(new Dimension(60, 60));
    }
}
