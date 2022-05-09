package Views;

import Service.SanphamService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSanPham extends JFrame{
    private JPanel UpdateSanPham;
    private JTextField TenSanPham;
    private JTextField DonVi;
    private JTextField DonGia;
    private JButton cậpNhậtButton;
    private JButton quayLạiButton;
    private JFrame mainFrame;
    private JLabel Info;
    private JButton showFileDialogButton;
    private  JLabel OldName ;
    private JLabel tênSảnPhẩmCũLabel;

    public void showUpdateSP(){


        UpdateSanPham updateSP = new UpdateSanPham();
        updateSP.setVisible(true);
        updateSP.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        updateSP.setLocationRelativeTo(null);
//        updateSP.pack();
    }

    public UpdateSanPham() {
        super();
        OldName.setText(SanPhamManagement.nameSP);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(UpdateSanPham);
//        this.setLocationRelativeTo(null);
        HomePage homePage = new HomePage();

        final JFileChooser fileDialog = new JFileChooser();

        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(mainFrame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();

                    Info.setText(file.getName());
                    //String info = Info.getText();
                } else {
                    Info.setText("Open command cancelled by user.");
                }
            }
        });
        cậpNhậtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sanphamO = SanPhamManagement.nameSP;
                String sanpham = TenSanPham.getText();
                String donvi = DonVi.getText();
                int dongia = Integer.parseInt(DonGia.getText());
                String anhsp = Info.getText();
                SanphamService sanphamService = new SanphamService();
                sanphamService.updateSanPham(sanphamO,sanpham,donvi,dongia,anhsp);
                dispose();
                SanPhamManagement spManagement = new SanPhamManagement();
                spManagement.showSanPhamManagement();
            }
        });
        quayLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(SanPhamManagement.nameSP);
                dispose();
               SanPhamManagement sanPhamManagement = new SanPhamManagement();
               sanPhamManagement.showSanPhamManagement();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        showFileDialogButton = new JButton(new ImageIcon(new ImageIcon("image\\folder.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        showFileDialogButton.setPreferredSize(new Dimension(60, 60));
        cậpNhậtButton = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        cậpNhậtButton.setPreferredSize(new Dimension(60, 60));
        quayLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quayLạiButton.setPreferredSize(new Dimension(60, 60));
    }
}
