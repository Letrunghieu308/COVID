package Views;

import Service.SanphamService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSanPham extends JFrame {
    private JTextField TenSanPham;
    private JLabel tênSảnPhẩmLabel;
    private JTextField DonVi;
    private JLabel đơnVịLabel;
    private JTextField DonGia;
    private JLabel đơnGiáLabel;
    private JButton cậpNhậtButton;
    private JButton quayLạiButton;
    private JPanel addSP;
    private JFrame mainFrame;
    private JLabel Info;
    private JButton showFileDialogButton;
    public void showAddInfo(){
        AddSanPham addSP = new AddSanPham();
        addSP.setVisible(true);
        addSP.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        addSP.setLocationRelativeTo(null);
//        addSP.pack();
    }

    public AddSanPham(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(addSP);
//        this.setLocationRelativeTo(null);

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
        quayLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SanPhamManagement spManagement = new SanPhamManagement();
                spManagement.showSanPhamManagement();
            }
        });
        cậpNhậtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SanphamService sanphamService = new SanphamService();
                String tenSP = TenSanPham.getText();
                String anhSP = Info.getText();
                String donvi = DonVi.getText();
                int dongia =Integer.parseInt(DonGia.getText());
                sanphamService.insertSanPham(tenSP, donvi, dongia, anhSP);
                dispose();
                SanPhamManagement spManagement = new SanPhamManagement();
                spManagement.showSanPhamManagement();
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
