package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import QUANLY.NOIDIEUTRI;
import Service.NoidieutriService;

public class AddSite extends JFrame {
    private JTextField DiaChi;
    private JTextField SucChua;
    private JTextField SoLuong;
    private JButton thêmMớiButton;
    private JButton trởLạiButton;
    private JPanel addSite;

    public void showAddInfo(){
        AddSite addSite = new AddSite();
        addSite.setVisible(true);
        addSite.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        addSite.setLocationRelativeTo(null);
//        addSite.pack();
    }
    public AddSite() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(addSite);
//        this.setLocationRelativeTo(null);
        trởLạiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                siteManagement siteManagement = new siteManagement();
                siteManagement.showSiteManagement();
            }
        });
        thêmMớiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoidieutriService noidieutriService = new NoidieutriService();
                String diachi = DiaChi.getText();
                int succhua = Integer.parseInt(SucChua.getText());
                int soluong = Integer.parseInt(SoLuong.getText());
                noidieutriService.insertNoidieutri(diachi, succhua, soluong);
                dispose();
                siteManagement siteManagement = new siteManagement();
                siteManagement.showSiteManagement();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        thêmMớiButton = new JButton(new ImageIcon(new ImageIcon("image\\create.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        thêmMớiButton.setPreferredSize(new Dimension(60, 60));
        trởLạiButton = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        trởLạiButton.setPreferredSize(new Dimension(60, 60));


    }
}
