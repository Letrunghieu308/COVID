package Views;

import CONNECT.JDBC;
import QUANLY.BENHNHAN;
import QUANLY.*;
import QUANLY.NOIDIEUTRI;
import Service.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UpdateInfo extends  JFrame {
    private JTextField hoten;
    private JPanel UpdateIF;
    private JTextField namsinh;
    private JTextField diachi;
    private JTextField trangthaihientai;
    private JTextField noidt;
    private JTextField lienquan;
    private JTextField lscovid;
    private JButton updBtn;
    private JButton backBtn;
    private JComboBox diaChiDTri;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    public void showUpdateInfo(){
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.setVisible(true);
        updateInfo.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        updateInfo.setLocationRelativeTo(null);
//        updateInfo.pack();
    }

    public UpdateInfo (){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(UpdateIF);
//        this.setLocationRelativeTo(null);
        HomePage homePage = new HomePage();


        updBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BenhnhanService benhnhanService = new BenhnhanService();
                String CCCD = HomePage.CCCD;

                try {
                    if(LoginPage.role.equals("mod")){
                        String ho_ten = hoten.getText();
                        String nam_sinh = namsinh.getText();
                        String ttht = trangthaihientai.getText();
                        String dia_chi = comboBox1.getSelectedItem().toString() +" " + comboBox2.getSelectedItem().toString() + " " + comboBox3.getSelectedItem().toString() + " " + diachi.getText();
                        String lquan = lienquan.getText();
                        new BenhnhanService().updateMod(ho_ten,dia_chi,nam_sinh,ttht,lquan,CCCD);

                    }
                    if(LoginPage.role.equals("admin")){

                        String nddt = diaChiDTri.getSelectedItem().toString();
                        new BenhnhanService().upDateAdmin(nddt,CCCD);
                        LocalDateTime datetime1 = LocalDateTime.now();
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formatDateTime = datetime1.format(format);
                        String lsu = formatDateTime + " chuy???n n??i ??i???u tr??? :  " + nddt ;
                        new BenhnhanService().upDateLSu(CCCD, lsu);
                    }

                }
                catch (Exception e2){
                    System.out.println(e2.getMessage());
                }

                //benhnhanService.updateBenhnhan(CCCD,ho_ten,dia_chi,nam_sinh,lquan,ttht,nddt,lscv);
                dispose();
                homePage.showHomePage();
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homePage.showHomePage();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel model;
                if (comboBox1.getSelectedItem().toString() == "TP. H??? Ch?? Minh"){
                    String tp1[] = { "Qu???n 1", "Qu???n 2", "Qu???n 3", "Qu???n 4", "Qu???n 5" };
                    model = new DefaultComboBoxModel(tp1);
                    comboBox2.setModel(model);
                }
                if( comboBox1.getSelectedItem().toString() == "TP. H?? N???i"){
                    String tp1[] = { "Qu???n Ba ????nh","Qu???n ?????ng ??a","Qu???n Hai B?? Tr??ng","Qu???n Ho??n Ki???m","Qu???n B???c T??? Li??m"};
                    model = new DefaultComboBoxModel(tp1);
                    comboBox2.setModel(model);
                }
                if( comboBox1.getSelectedItem().toString() == "TP. C???n Th??"){
                    String tp1[] = {" Qu???n B??nh Th???y"," Qu???n C??i R??ng", "Qu???n Ninh Ki???u" ,"Qu???n ?? M??n" , "Qu???n Th???t N???t"};
                    model = new DefaultComboBoxModel(tp1);
                    comboBox2.setModel(model);
                }
                if( comboBox1.getSelectedItem().toString() == "TP. ???? N???ng"){
                    String tp1[] = { "Qu???n Ng?? H??nh S??n","Qu???n C???m L???","Qu???n Thanh Kh??","Qu???n Li??n Chi???u","Qu???n H???i Ch??u"};
                    model = new DefaultComboBoxModel(tp1);
                    comboBox2.setModel(model);
                }
                if( comboBox1.getSelectedItem().toString() == "TP. H???i Ph??ng"){
                    String tp1[] = { "Qu???n ????? S??n","Qu???n D????ng Kinh","Qu???n H???i An","Qu???n H???ng B??ng","Qu???n Ki???n An"};
                    model = new DefaultComboBoxModel(tp1);
                    comboBox2.setModel(model);
                }
            }
        });

        diaChiDTri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultComboBoxModel model = new DefaultComboBoxModel(new NoidieutriService().getNoiDTri().toArray());
                diaChiDTri.setModel(model);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        updBtn = new JButton(new ImageIcon(new ImageIcon("image\\update.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        updBtn.setPreferredSize(new Dimension(60, 60));
        backBtn = new JButton(new ImageIcon(new ImageIcon("image\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        backBtn.setPreferredSize(new Dimension(60, 60));
    }
}
