package Views;

import Service.NhuyeuphamService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNYP extends JFrame {
    private JTextField tenGoi;
    private JTextField gioihanMua;
    private JTextField ngayGioiHan;
    private JButton AddGoi;
    private JButton quayLạiButton;
    private JPanel addNYP;
    private JButton thêmSảnPhẩmButton;
    static String TENGOI;

    public void showAddInfo(){
        AddNYP addNYP = new AddNYP();
        addNYP.setVisible(true);
        addNYP.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        addNYP.setLocationRelativeTo(null);
//        addNYP.pack();
    }

    public AddNYP(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(addNYP);
//        this.setLocationRelativeTo(null);


        AddGoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhuyeuphamService nhuyeuphamService = new NhuyeuphamService();
                String tengoi = tenGoi.getText();
                TENGOI = tenGoi.getText();
                int gioihan = Integer.parseInt(gioihanMua.getText());
                String ngayhethan = ngayGioiHan.getText();
                nhuyeuphamService.insertNhuYeuPham(tengoi,gioihan,ngayhethan);
                dispose();
                NhuYeuPhamManagement nypManagement = new NhuYeuPhamManagement();
                nypManagement.showNYPManagement();

            }
        });
        quayLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NhuYeuPhamManagement nypManagement = new NhuYeuPhamManagement();
                nypManagement.showNYPManagement();

            }
        });
        thêmSảnPhẩmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhuyeuphamService nhuyeuphamService = new NhuyeuphamService();
                String tengoi = tenGoi.getText();
                TENGOI = tenGoi.getText();
                int gioihan = Integer.parseInt(gioihanMua.getText());
                String ngayhethan = ngayGioiHan.getText();
                nhuyeuphamService.insertNhuYeuPham(tengoi,gioihan,ngayhethan);
                dispose();
                AddDSSP addDSSP = new AddDSSP();
                addDSSP.showAddInfo();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        AddGoi = new JButton(new ImageIcon(new ImageIcon("image\\product.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        AddGoi.setPreferredSize(new Dimension(60, 60));
        thêmSảnPhẩmButton = new JButton(new ImageIcon(new ImageIcon("image\\food.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thêmSảnPhẩmButton.setPreferredSize(new Dimension(60, 60));
        quayLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        quayLạiButton.setPreferredSize(new Dimension(60, 60));
    }
}
