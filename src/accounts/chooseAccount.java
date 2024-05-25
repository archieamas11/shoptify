/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package accounts;

import com.formdev.flatlaf.FlatLightLaf;
import config.databaseConnector;
import config.isAccountExist;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author MARITIME 02
 */
public class chooseAccount extends javax.swing.JFrame {

    File selectedFile;
    String fileName;
    String imagePath;
    String role = null;

    /**
     * Creates new form NewJFrame
     */
    public chooseAccount() {
        initComponents();
        fname.setFocusable(false);
        lname.setFocusable(false);
        email.setFocusable(false);
        number.setFocusable(false);
        address.setFocusable(false);
        username.setFocusable(false);
        password.setFocusable(false);
        shop_name.setFocusable(false);

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        UXmethods.RoundBorders.setArcStyle(login, 20);
        UXmethods.RoundBorders.setArcStyle(buyer, 30);
        UXmethods.RoundBorders.setArcStyle(seller, 30);
        UXmethods.RoundBorders.setArcStyle(admin, 30);

        UXmethods.RoundBorders.setArcStyle(submit, 15);
        UXmethods.RoundBorders.setArcStyle(importImage, 15);

        UXmethods.RoundBorders.setArcStyle(c1, 15);
        UXmethods.RoundBorders.setArcStyle(c2, 15);
        UXmethods.RoundBorders.setArcStyle(c3, 15);
        UXmethods.RoundBorders.setArcStyle(c4, 15);
        UXmethods.RoundBorders.setArcStyle(c5, 15);
        UXmethods.RoundBorders.setArcStyle(c6, 15);
        UXmethods.RoundBorders.setArcStyle(c7, 15);
        UXmethods.RoundBorders.setArcStyle(c8, 15);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        home = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        buyer = new javax.swing.JButton();
        seller = new javax.swing.JButton();
        admin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        c1 = new javax.swing.JPanel();
        fname = new javax.swing.JTextField();
        c2 = new javax.swing.JPanel();
        lname = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        c3 = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        c4 = new javax.swing.JPanel();
        number = new javax.swing.JTextField();
        c5 = new javax.swing.JPanel();
        address = new javax.swing.JTextField();
        c6 = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        c7 = new javax.swing.JPanel();
        eye = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        importImage = new javax.swing.JButton();
        submit = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        c8 = new javax.swing.JPanel();
        shop_name = new javax.swing.JTextField();
        shopname = new javax.swing.JLabel();
        login1 = new javax.swing.JButton();
        displayPhoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        home.setForeground(new java.awt.Color(0, 158, 226));
        home.setText("SHOPTIFY");
        jPanel4.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 50));

        login.setBackground(new java.awt.Color(245, 245, 245));
        login.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        login.setText("Sign in");
        login.setBorderPainted(false);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jPanel4.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, -1, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 20));

        tabs.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator2.setAlignmentX(0.2F);
        jSeparator2.setAlignmentY(0.2F);
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 30));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Welcome to Shoptify");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 1280, 30));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("What type of account do you want to create?");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1280, 40));

        buyer.setBackground(new java.awt.Color(0, 158, 226));
        buyer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buyer.setForeground(new java.awt.Color(255, 255, 255));
        buyer.setText("Buyer");
        buyer.setBorderPainted(false);
        buyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyerActionPerformed(evt);
            }
        });
        jPanel2.add(buyer, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 500, 50));

        seller.setBackground(new java.awt.Color(0, 158, 226));
        seller.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seller.setForeground(new java.awt.Color(255, 255, 255));
        seller.setText("Seller");
        seller.setBorderPainted(false);
        seller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellerActionPerformed(evt);
            }
        });
        jPanel2.add(seller, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 500, 50));

        admin.setBackground(new java.awt.Color(0, 158, 226));
        admin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        admin.setForeground(new java.awt.Color(255, 255, 255));
        admin.setText("Admin");
        admin.setBorderPainted(false);
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });
        jPanel2.add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 500, 50));

        tabs.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator1.setAlignmentX(0.2F);
        jSeparator1.setAlignmentY(0.2F);
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 10));

        c1.setBackground(new java.awt.Color(245, 245, 245));
        c1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                c1ComponentMoved(evt);
            }
        });
        c1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname.setBackground(new java.awt.Color(245, 245, 245));
        fname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fname.setForeground(new java.awt.Color(153, 153, 153));
        fname.setText("Juan");
        fname.setBorder(null);
        fname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fnameFocusGained(evt);
            }
        });
        fname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fnameMouseClicked(evt);
            }
        });
        c1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 40));

        jPanel3.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 160, 40));

        c2.setBackground(new java.awt.Color(245, 245, 245));
        c2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                c2ComponentMoved(evt);
            }
        });
        c2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lname.setBackground(new java.awt.Color(245, 245, 245));
        lname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lname.setForeground(new java.awt.Color(153, 153, 153));
        lname.setText("Delacruz");
        lname.setBorder(null);
        lname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lnameFocusGained(evt);
            }
        });
        lname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnameMouseClicked(evt);
            }
        });
        c2.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 150, 40));

        jPanel3.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 180, 40));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Create an account");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 350, 30));

        c3.setBackground(new java.awt.Color(245, 245, 245));
        c3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        email.setBackground(new java.awt.Color(245, 245, 245));
        email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        email.setForeground(new java.awt.Color(153, 153, 153));
        email.setText("juandelacruz@gmail.com");
        email.setBorder(null);
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
        });
        email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailMouseClicked(evt);
            }
        });
        c3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel3.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 350, 40));

        c4.setBackground(new java.awt.Color(245, 245, 245));
        c4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        number.setBackground(new java.awt.Color(245, 245, 245));
        number.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        number.setForeground(new java.awt.Color(153, 153, 153));
        number.setText("09123456789");
        number.setBorder(null);
        number.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numberFocusGained(evt);
            }
        });
        number.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numberMouseClicked(evt);
            }
        });
        c4.add(number, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel3.add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 350, 40));

        c5.setBackground(new java.awt.Color(245, 245, 245));
        c5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        address.setBackground(new java.awt.Color(245, 245, 245));
        address.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        address.setForeground(new java.awt.Color(153, 153, 153));
        address.setText("Purok 123, Home town, City");
        address.setBorder(null);
        address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressFocusGained(evt);
            }
        });
        address.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addressMouseClicked(evt);
            }
        });
        c5.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel3.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 350, 40));

        c6.setBackground(new java.awt.Color(245, 245, 245));
        c6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setBackground(new java.awt.Color(245, 245, 245));
        username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username.setForeground(new java.awt.Color(153, 153, 153));
        username.setText("@username");
        username.setBorder(null);
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
        });
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
        });
        c6.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel3.add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 350, 40));

        c7.setBackground(new java.awt.Color(245, 245, 245));
        c7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/eye.png"))); // NOI18N
        eye.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eyeMouseClicked(evt);
            }
        });
        c7.add(eye, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, 40));

        password.setBackground(new java.awt.Color(245, 245, 245));
        password.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        password.setForeground(new java.awt.Color(153, 153, 153));
        password.setText("Password");
        password.setBorder(null);
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
        });
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordMouseClicked(evt);
            }
        });
        c7.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 300, 40));

        jPanel3.add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 350, 40));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Last name");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 80, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Password");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 70, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("First name");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Email");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Phone number");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 120, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Address");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Username");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 70, -1));

        importImage.setBackground(new java.awt.Color(204, 204, 204));
        importImage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        importImage.setForeground(new java.awt.Color(51, 51, 51));
        importImage.setText("*Import Profile Picture");
        importImage.setBorder(null);
        importImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        importImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importImageActionPerformed(evt);
            }
        });
        jPanel3.add(importImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 430, 170, 40));

        submit.setBackground(new java.awt.Color(0, 158, 226));
        submit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        submit.setForeground(new java.awt.Color(255, 255, 255));
        submit.setText("Create account");
        submit.setBorder(null);
        submit.setBorderPainted(false);
        submit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        jPanel3.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 350, 40));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, 40, 450));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("File extension: .JPEG, .PNG ");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 490, 380, -1));

        c8.setBackground(new java.awt.Color(255, 255, 255));
        c8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        shop_name.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shop_name.setForeground(new java.awt.Color(153, 153, 153));
        shop_name.setBorder(null);
        shop_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                shop_nameFocusGained(evt);
            }
        });
        shop_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shop_nameMouseClicked(evt);
            }
        });
        c8.add(shop_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel3.add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 350, 40));

        shopname.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel3.add(shopname, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, -1));

        login1.setBackground(new java.awt.Color(0, 158, 226));
        login1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        login1.setForeground(new java.awt.Color(255, 255, 255));
        login1.setText("Back");
        login1.setBorderPainted(false);
        login1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login1ActionPerformed(evt);
            }
        });
        jPanel3.add(login1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, 40));

        displayPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 170x170.png"))); // NOI18N
        jPanel3.add(displayPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 230, 170, 170));

        tabs.addTab("tab2", jPanel3);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        Login back = new Login();
        back.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_loginActionPerformed

    private void login1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login1ActionPerformed
        tabs.setSelectedIndex(0);
        fname.setText("Juan");
        lname.setText("Delacruz");
        email.setText("juandelacruz@gmail.com");
        number.setText("09123456789");
        address.setText("Purok 123, Home town, City");
        username.setText("@username");
        password.setText("Password");

        shopname.setText("");
        shop_name.setText("");
        shop_name.setBackground(new Color(255, 255, 255));
        c8.setBackground(new Color(255, 255, 255));
        shop_name.setFocusable(false);
        role = null;
        ImageIcon activeIcon = new ImageIcon(getClass().getResource("/sampleProfiles/default profile 170x170.png"));
        displayPhoto.setIcon(activeIcon);
    }//GEN-LAST:event_login1ActionPerformed


    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        String first_name = fname.getText();
        String last_name = lname.getText();
        String em = email.getText();
        String phone = number.getText();
        String user = username.getText();
        String pass = password.getText();
        String add = address.getText();
        String selectedRole = role;
        String shopName = "None";

        if (!shop_name.getText().isEmpty()) {
            shopName = shop_name.getText();
        }

        if (em.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || selectedRole.isEmpty() || user.isEmpty() || pass.isEmpty() || phone.isEmpty() || add.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pass.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (phone.length() < 11 || phone.length() > 12 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        databaseConnector dbc = new databaseConnector();
        try {
            if (isAccountExist.checkEmail(em)) {
                JOptionPane.showMessageDialog(null, "Email already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isAccountExist.checkUsername(user)) {
                JOptionPane.showMessageDialog(null, "Username already taken.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
            PreparedStatement pst;
            String sql;

            if (selectedFile != null) {
                fileName = selectedFile.getName();
                imagePath = "src/sampleProfiles/" + fileName;
            } else {
                String defaultImage = "src/sampleProfiles/default_user_profile.png";
                imagePath = defaultImage;
            }
            sql = "INSERT INTO `tbl_accounts`(`email`, `first_name`, `last_name`, `phone_number`, `username`,`password`, `role`, `status`, `address`, `profile_picture`, `shop_name`, `date_joined`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURDATE())";
            pst = dbc.getConnection().prepareStatement(sql);
            pst.setString(1, em);
            pst.setString(2, first_name);
            pst.setString(3, last_name);
            pst.setString(4, phone);
            pst.setString(5, user);
            pst.setString(6, hashedPass);
            pst.setString(7, selectedRole);
            pst.setString(8, "Pending");
            pst.setString(9, add);
            pst.setString(10, imagePath);
            pst.setString(11, shopName);

            pst.executeUpdate();
            pst.close();

            JOptionPane.showMessageDialog(null, "Account created successfully");

            fname.setText("Juan");
            lname.setText("Delacruz");
            email.setText("juandelacruz@gmail.com");
            number.setText("09123456789");
            address.setText("Purok 123, Home town, City");
            username.setText("@username");
            password.setText("Password");
            shopname.setText("");
            shop_name.setText("");
            shop_name.setBackground(new Color(255, 255, 255));
            c8.setBackground(new Color(255, 255, 255));
            shop_name.setFocusable(false);
            role = null;
            Login back = new Login();
            back.setVisible(true);
            this.dispose();
        } catch (HeadlessException | SQLException e) {
        }
    }//GEN-LAST:event_submitActionPerformed

    private void importImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                Image resizedImage = originalImage.getScaledInstance(170, 170, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                displayPhoto.setIcon(icon);

                String imageName = selectedFile.getName();
                String imagePath = "src/sampleProfiles/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Update the selectedFile to point to the new location
                selectedFile = destination;

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_importImageActionPerformed
    private boolean passwordVisible = false;
    private ImageIcon hideIcon;
    private ImageIcon showIcon;

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;

        hideIcon = new ImageIcon(getClass().getResource("/image/eye.png"));
        showIcon = new ImageIcon(getClass().getResource("/image/eye_hide.png"));

        if (passwordVisible) {
            eye.setIcon(showIcon);
            password.setEchoChar('\u0000');

        } else {
            eye.setIcon(hideIcon);
            password.setEchoChar('\u25cf');
        }
    }

    private void eyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eyeMouseClicked
        togglePasswordVisibility();
    }//GEN-LAST:event_eyeMouseClicked

    private void c2ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_c2ComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_c2ComponentMoved

    private void c1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_c1ComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_c1ComponentMoved

    private void fnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnameMouseClicked
        fname.setFocusable(true);
        fname.requestFocusInWindow();
    }//GEN-LAST:event_fnameMouseClicked

    private void fnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnameFocusGained
        if (fname.getText().equals("Juan")) {
            fname.setText("");
            fname.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_fnameFocusGained

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        tabs.setSelectedIndex(1);
        role = "Admin";

    }//GEN-LAST:event_adminActionPerformed

    private void sellerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellerActionPerformed
        tabs.setSelectedIndex(1);
        shopname.setText("Shop name");
        shop_name.setText("Tindahan");
        shop_name.setBackground(new Color(245, 245, 245));
        c8.setBackground(new Color(245, 245, 245));
        shop_name.setFocusable(true);
        role = "Seller";

    }//GEN-LAST:event_sellerActionPerformed

    private void buyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyerActionPerformed
        tabs.setSelectedIndex(1);
        role = "Buyer";
    }//GEN-LAST:event_buyerActionPerformed

    private void lnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lnameFocusGained
        if (lname.getText().equals("Delacruz")) {
            lname.setText("");
            lname.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_lnameFocusGained

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        if (email.getText().equals("juandelacruz@gmail.com")) {
            email.setText("");
            email.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_emailFocusGained

    private void numberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numberFocusGained
        if (number.getText().equals("09123456789")) {
            number.setText("");
            number.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_numberFocusGained

    private void addressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFocusGained
        if (address.getText().equals("Purok 123, Home town, City")) {
            address.setText("");
            address.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_addressFocusGained

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
        if (username.getText().equals("@username")) {
            username.setText("");
            username.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_usernameFocusGained

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        if (password.getText().equals("Password")) {
            password.setText("");
            password.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_passwordFocusGained

    private static void backToDefault(JPasswordField password) {
        if (password.getText().isEmpty()) {
            password.setEchoChar('\0');
            password.setText("Password");
            password.setForeground(Color.decode("#999999"));
        }
    }


    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked
        username.setFocusable(true);
        username.requestFocusInWindow();
    }//GEN-LAST:event_usernameMouseClicked

    private void passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseClicked
        password.setFocusable(true);
        password.requestFocusInWindow();
        password.setEchoChar('\u25cf');
    }//GEN-LAST:event_passwordMouseClicked

    private void lnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnameMouseClicked
        lname.setFocusable(true);
        lname.requestFocusInWindow();
    }//GEN-LAST:event_lnameMouseClicked

    private void emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailMouseClicked
        email.setFocusable(true);
        email.requestFocusInWindow();
    }//GEN-LAST:event_emailMouseClicked

    private void numberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numberMouseClicked
        number.setFocusable(true);
        number.requestFocusInWindow();
    }//GEN-LAST:event_numberMouseClicked

    private void addressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addressMouseClicked
        address.setFocusable(true);
        address.requestFocusInWindow();
    }//GEN-LAST:event_addressMouseClicked

    private void shop_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shop_nameMouseClicked
        if (role.equals("Seller")) {
            shop_name.setFocusable(true);
            shop_name.requestFocusInWindow();
        }
    }//GEN-LAST:event_shop_nameMouseClicked

    private void shop_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_shop_nameFocusGained
        if (shop_name.getText().equals("Tindahan") || role.equals("Seller")) {
            shop_name.setText("");
            shop_name.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_shop_nameFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new chooseAccount().setVisible(true);
                }
            });

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(frontPage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JButton admin;
    private javax.swing.JButton buyer;
    private javax.swing.JPanel c1;
    private javax.swing.JPanel c2;
    private javax.swing.JPanel c3;
    private javax.swing.JPanel c4;
    private javax.swing.JPanel c5;
    private javax.swing.JPanel c6;
    private javax.swing.JPanel c7;
    private javax.swing.JPanel c8;
    private javax.swing.JLabel displayPhoto;
    private javax.swing.JTextField email;
    private javax.swing.JLabel eye;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel home;
    private javax.swing.JButton importImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField lname;
    private javax.swing.JButton login;
    private javax.swing.JButton login1;
    private javax.swing.JTextField number;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton seller;
    private javax.swing.JTextField shop_name;
    private javax.swing.JLabel shopname;
    private javax.swing.JButton submit;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
