package Views;

import CONNECT.JDBC;
import QUANLY.DSSANPHAM;
import QUANLY.SANPHAM;
import Service.DSSanphamService;
import Service.SanphamService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class UpdateDSSP extends JFrame {
    private JTextField tenSP;
    private JTextField Soluong;
    private JTable ProductList;
    private JButton cậpNhậtButton;
    private JButton quayLạiButton;
    private JTextField Newname;
    private JPanel UpdateProduct;
    private JTable showSP;
    private JButton addSP;
    private JButton thêmSảnPhẩmButton;
    private JButton xóaSảnPhẩmButton;
    DefaultTableModel model;
    DefaultTableModel model1;

    public void showUpdate(){
        UpdateDSSP updateDSSP = new UpdateDSSP();
        updateDSSP.setVisible(true);
        updateDSSP.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        updateDSSP.setLocationRelativeTo(null);
//        updateDSSP.pack();
    }

    public UpdateDSSP(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(UpdateProduct);
//        this.setLocationRelativeTo(null);


        cậpNhậtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tengoi = NhuYeuPhamManagement.Package;
                String TenSP = tenSP.getText();
                int nsoluong = Integer.parseInt(Soluong.getText());
                DSSanphamService dsSanphamService = new DSSanphamService();
                try {
                    Connection conn = JDBC.connect();
                    PreparedStatement pstm = conn.prepareStatement("update COVID.? set  SoLuong  = ? where SanPham = ? ");
                    pstm.setString(1,tengoi);
                    pstm.setInt(2, nsoluong);
                    pstm.setString(3,TenSP);
                    pstm.execute();

                }catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
                dispose();
                UpdateDSSP updateDSSP = new UpdateDSSP();
                updateDSSP.showUpdate();
            }
        });
        quayLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UpdateGoiNPY updateGoiNPY = new UpdateGoiNPY();
                updateGoiNPY.showUpdate();

            }
        });

        showSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) showSP.getModel();
                int index  = showSP.getSelectedRow();
                tenSP.setText(showSP.getValueAt(index,0).toString());
            }
        });
        addSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DSSanphamService sanphamService = new DSSanphamService();
                sanphamService.updateSoLuongSanPham(NhuYeuPhamManagement.Package ,tenSP.getText(),Integer.parseInt(Soluong.getText()) );
                dispose();
                UpdateDSSP updateDSSP = new UpdateDSSP();
                updateDSSP.showUpdate();
            }
        });
        xóaSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DSSanphamService sanphamService = new DSSanphamService();
                sanphamService.deleteDSSanPham(NhuYeuPhamManagement.Package,tenSP.getText());
                dispose();
                UpdateDSSP updateDSSP = new UpdateDSSP();
                updateDSSP.showUpdate();
            }
        });
        ProductList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) ProductList.getModel();
                int index  = ProductList.getSelectedRow();
                tenSP.setText(ProductList.getValueAt(index,1).toString());
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
        ProductList = new JTable(model);
        List<DSSANPHAM> dssp;
        dssp = dsSanphamService.getAllDSSanPhamVoiTenGoi(tengoi);
        for (DSSANPHAM dssanpham: dssp){
            System.out.println(dssp);
            model.addRow(new Object[]{NhuYeuPhamManagement.Package, dssanpham.getTenSanPham(), dssanpham.getSoLuong()});
        }
        ProductList.setRowHeight(50);
        SanphamService sanphamService = new SanphamService();
        String header1[] ={"Tên Sản phẩm" , "Đơn vị" , "Đơn giá", "Ảnh sản phẩm","Số lượng" };
        int slg= 0 ;
        model1 = new DefaultTableModel(0,12);
        model1.setColumnIdentifiers(header1);
        showSP = new JTable(model1);
        List<SANPHAM> dsSanPham;
        dsSanPham = sanphamService.getAllSanPham();
        for (SANPHAM sanpham: dsSanPham){
            model1.addRow(new Object[]{sanpham.getTENSANPHAM(), sanpham.getDONVITINH(), sanpham.getDONGIA(), new ImageIcon(new ImageIcon(sanpham.getANHSANPHAM()).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)),});
        }

        showSP = new JTable(model1) {
            public Class getColumnClass(int column) {
               if(column!= 3){
                   return Object.class;
               }
               else  return Icon.class;
            }
        };

        int num = 0;
        showSP.setRowHeight(50);
        addSP = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        addSP.setPreferredSize(new Dimension(60, 60));
        cậpNhậtButton = new JButton(new ImageIcon(new ImageIcon("image\\update.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        cậpNhậtButton.setPreferredSize(new Dimension(60, 60));
        quayLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quayLạiButton.setPreferredSize(new Dimension(60, 60));
        xóaSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\delete.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        xóaSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
    }

}


