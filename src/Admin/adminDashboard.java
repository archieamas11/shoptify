/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

import accounts.Login;
import accounts.UserManager;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.databaseConnector;
import config.isAccountExist;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author MARITIME 02
 */
public final class adminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    public adminDashboard() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        displayAccounts();
        displayAccountName();

        //Informations Panel
        UXmethods.RoundBorders.setArcStyle(c1, 15);
        UXmethods.RoundBorders.setArcStyle(c2, 15);
        UXmethods.RoundBorders.setArcStyle(c3, 15);
        UXmethods.RoundBorders.setArcStyle(c4, 15);
        UXmethods.RoundBorders.setArcStyle(c5, 15);

        // Buttons
        UXmethods.RoundBorders.setArcStyle(edit, 15);
        UXmethods.RoundBorders.setArcStyle(delete, 15);
        UXmethods.RoundBorders.setArcStyle(add, 15);
        UXmethods.RoundBorders.setArcStyle(searchIcon, 15);
        UXmethods.RoundBorders.setArcStyle(editAccountSaveBtn, 15);
        UXmethods.RoundBorders.setArcStyle(addAccountsaveBtn, 15);
        UXmethods.RoundBorders.setArcStyle(delete, 15);

        // Components
        UXmethods.RoundBorders.setArcStyle(scrollBar, 15);
        UXmethods.RoundBorders.setArcStyle(searchBar, 15);
        UXmethods.RoundBorders.setArcStyle(accountTableContainer, 30);
        UXmethods.RoundBorders.setArcStyle(dashboardContainer, 15);
        UXmethods.RoundBorders.setArcStyle(editProfileContainer, 30);
        UXmethods.RoundBorders.setArcStyle(addAccountContainer, 30);

        // Dashboard Buttons
        UXmethods.RoundBorders.setArcStyle(dashboard, 50);
        UXmethods.RoundBorders.setArcStyle(logout, 50);

        //Test
        UXmethods.RoundBorders.setArcStyle(editRole, 15);
        UXmethods.RoundBorders.setArcStyle(editStatus, 15);

        searchBar.setFocusable(false);
    }

    public void displayAccountName() {
        try {
            int accountId = UserManager.getLoggedInUserId();

            databaseConnector dbc = new databaseConnector();
            String query = "SELECT fname, account_id, email, `phone number`, address, role, status, profile_picture FROM accounts_table WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, accountId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("fname");
                firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                fname.setText(firstName + "!");
                id.setText("" + rs.getString("account_id"));
                email.setText("" + rs.getString("email"));
                number.setText("" + rs.getString("phone number"));
                address.setText("" + rs.getString("address"));
                role.setText("" + rs.getString("role"));
                String statusValue = rs.getString("status");
                status.setText(statusValue);

                // Set status icon based on status value
                ImageIcon activeIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-activeon-24 (1).png"));
                ImageIcon inactiveIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-inavtiveon-24 (2).png"));
                ImageIcon pendingIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-pendingon-24.png"));

                if (statusValue.equals("Pending")) {
                    statusIcon.setIcon(pendingIcon);
                } else if (statusValue.equals("Active")) {
                    statusIcon.setIcon(activeIcon);
                } else if (statusValue.equals("Inactive")) {
                    statusIcon.setIcon(inactiveIcon);
                }

                // Display profile picture
                int height = 70;
                int width = 70;
                String profilePicture = rs.getString("profile_picture");
                GetImage.displayImage(photo, profilePicture, height, width);
                System.out.println(accountId);

            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayAccounts() {

        try {
            databaseConnector dbc = new databaseConnector();

            ResultSet rs = dbc.getData("SELECT * FROM accounts_table");
            accounts_table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
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
        dashboardContainer = new javax.swing.JPanel();
        dashboard = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        searchIcon = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        tabs = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        accountTableContainer = new javax.swing.JPanel();
        scrollBar = new javax.swing.JScrollPane();
        accounts_table = new javax.swing.JTable();
        fname = new javax.swing.JLabel();
        photo = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        statusIcon = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        c1 = new javax.swing.JPanel();
        manage16 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        c2 = new javax.swing.JPanel();
        manage17 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        c3 = new javax.swing.JPanel();
        manage14 = new javax.swing.JLabel();
        number = new javax.swing.JLabel();
        c4 = new javax.swing.JPanel();
        manage15 = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        c5 = new javax.swing.JPanel();
        manage8 = new javax.swing.JLabel();
        role = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        editProfileContainer = new javax.swing.JPanel();
        manage3 = new javax.swing.JLabel();
        editStatus = new javax.swing.JComboBox<>();
        editAccountSaveBtn = new javax.swing.JButton();
        manage7 = new javax.swing.JLabel();
        editRole = new javax.swing.JComboBox<>();
        myprofile4 = new javax.swing.JLabel();
        manage10 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        fullname = new javax.swing.JLabel();
        displayStatus = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        displayPhoto = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        addAccountContainer = new javax.swing.JPanel();
        first = new javax.swing.JTextField();
        last = new javax.swing.JTextField();
        phoneNumber = new javax.swing.JTextField();
        em = new javax.swing.JTextField();
        user = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        addAccountsaveBtn = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        select = new javax.swing.JButton();
        displayImage = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        roles = new javax.swing.JComboBox<>();
        addresss = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        myprofile5 = new javax.swing.JLabel();
        manage11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardContainer.setBackground(new java.awt.Color(241, 241, 241));
        dashboardContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setBackground(new java.awt.Color(153, 204, 255));
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-dashboard-24.png"))); // NOI18N
        dashboard.setBorderPainted(false);
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        dashboardContainer.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 50, 50));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/2.png"))); // NOI18N
        dashboardContainer.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 70, 50));

        logout.setBackground(new java.awt.Color(255, 102, 102));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-logout-24.png"))); // NOI18N
        logout.setBorderPainted(false);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        dashboardContainer.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 50, 50));
        dashboardContainer.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 50, 10));

        jPanel1.add(dashboardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 70, 670));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 158, 226));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Dashboard");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 130, 30));

        name.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel5.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 50, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Welcome, ");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 50, 30));

        add.setBackground(new java.awt.Color(122, 183, 147));
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add Account");
        add.setBorder(null);
        add.setBorderPainted(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel5.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 40, 180, 50));

        searchIcon.setBackground(new java.awt.Color(204, 204, 204));
        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchIcon.setBorder(null);
        searchIcon.setBorderPainted(false);
        jPanel5.add(searchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 60, 50));

        searchBar.setText("Search");
        searchBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchBarMouseClicked(evt);
            }
        });
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        jPanel5.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 330, 50));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 1210, 120));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accountTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        accountTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollBar.setBackground(new java.awt.Color(0, 0, 0));

        accounts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        accounts_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        accounts_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accounts_tableMouseClicked(evt);
            }
        });
        scrollBar.setViewportView(accounts_table);

        accountTableContainer.add(scrollBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 800, 500));

        fname.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        accountTableContainer.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 180, 40));

        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accountTableContainer.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 70, 70));
        accountTableContainer.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 270, 20));

        statusIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusIcon.setForeground(new java.awt.Color(102, 102, 102));
        statusIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accountTableContainer.add(statusIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 30, 20));

        status.setForeground(new java.awt.Color(102, 102, 102));
        accountTableContainer.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, -1, 20));

        delete.setBackground(new java.awt.Color(255, 102, 102));
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.setBorder(null);
        delete.setBorderPainted(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        accountTableContainer.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 490, 130, 50));

        edit.setBackground(new java.awt.Color(102, 102, 102));
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("Edit");
        edit.setBorder(null);
        edit.setBorderPainted(false);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        accountTableContainer.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, 130, 50));

        c1.setBackground(new java.awt.Color(255, 255, 255));
        c1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage16.setForeground(new java.awt.Color(102, 102, 102));
        manage16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-tag-woman-horizontal-24.png"))); // NOI18N
        c1.add(manage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        id.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        id.setForeground(new java.awt.Color(102, 102, 102));
        c1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 270, 30));

        c2.setBackground(new java.awt.Color(255, 255, 255));
        c2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage17.setForeground(new java.awt.Color(102, 102, 102));
        manage17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-email-24.png"))); // NOI18N
        c2.add(manage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        email.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        c2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 220, 270, 30));

        c3.setBackground(new java.awt.Color(255, 255, 255));
        c3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage14.setForeground(new java.awt.Color(102, 102, 102));
        manage14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-phone-24.png"))); // NOI18N
        c3.add(manage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        number.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        number.setForeground(new java.awt.Color(102, 102, 102));
        c3.add(number, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 260, 270, 30));

        c4.setBackground(new java.awt.Color(255, 255, 255));
        c4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage15.setForeground(new java.awt.Color(102, 102, 102));
        manage15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-address-24.png"))); // NOI18N
        c4.add(manage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        address.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        address.setForeground(new java.awt.Color(102, 102, 102));
        c4.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, 270, 30));

        c5.setBackground(new java.awt.Color(255, 255, 255));
        c5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage8.setForeground(new java.awt.Color(102, 102, 102));
        manage8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-24.png"))); // NOI18N
        c5.add(manage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        role.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        role.setForeground(new java.awt.Color(102, 102, 102));
        c5.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, 270, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Accounts Table");
        accountTableContainer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel7.add(accountTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1150, 570));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 690));

        tabs.addTab("tab1", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editProfileContainer.setBackground(new java.awt.Color(241, 241, 241));
        editProfileContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage3.setForeground(new java.awt.Color(102, 102, 102));
        manage3.setText("Status");
        editProfileContainer.add(manage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, 30));

        editStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "Pending" }));
        editProfileContainer.add(editStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 320, 40));

        editAccountSaveBtn.setBackground(new java.awt.Color(0, 158, 226));
        editAccountSaveBtn.setForeground(new java.awt.Color(255, 255, 255));
        editAccountSaveBtn.setText("Save");
        editAccountSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAccountSaveBtnActionPerformed(evt);
            }
        });
        editProfileContainer.add(editAccountSaveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, -1, 40));

        manage7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage7.setForeground(new java.awt.Color(102, 102, 102));
        manage7.setText("Role");
        editProfileContainer.add(manage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, 30));

        editRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Seller", "Buyer" }));
        editProfileContainer.add(editRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 320, 40));

        myprofile4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        myprofile4.setText("Edit Account");
        editProfileContainer.add(myprofile4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 30));

        manage10.setForeground(new java.awt.Color(102, 102, 102));
        manage10.setText("Manage account profile ");
        editProfileContainer.add(manage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));
        editProfileContainer.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1060, 20));

        fullname.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        fullname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fullname.setText("Archie Albarico");
        editProfileContainer.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 190, 40));

        displayStatus.setForeground(new java.awt.Color(153, 153, 153));
        displayStatus.setText("Pending");
        editProfileContainer.add(displayStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 206, -1, 20));

        jLabel13.setText("Status:");
        editProfileContainer.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 206, -1, 20));
        editProfileContainer.add(displayPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 100, 100));

        jPanel4.add(editProfileContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1150, 570));

        tabs.addTab("tab2", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addAccountContainer.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        first.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.add(first, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 280, 30));

        last.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.add(last, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 280, 30));

        phoneNumber.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.add(phoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 280, 30));

        em.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 280, 30));

        user.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 280, 30));

        pass.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 280, 30));

        addAccountsaveBtn.setBackground(new java.awt.Color(0, 158, 226));
        addAccountsaveBtn.setForeground(new java.awt.Color(255, 255, 255));
        addAccountsaveBtn.setText("Save");
        addAccountsaveBtn.setBorderPainted(false);
        addAccountsaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAccountsaveBtnActionPerformed(evt);
            }
        });
        addAccountContainer.add(addAccountsaveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, -1, 30));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        addAccountContainer.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 60, 380));

        select.setBackground(new java.awt.Color(241, 241, 241));
        select.setText("Select Image");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        addAccountContainer.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 340, 120, 40));

        displayImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default.png"))); // NOI18N
        addAccountContainer.add(displayImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 206, 120, 120));

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("* Optional");
        addAccountContainer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 390, 490, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Phone Number");
        addAccountContainer.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 100, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Role");
        addAccountContainer.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 30, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("First Name");
        addAccountContainer.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 70, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Last Name");
        addAccountContainer.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 70, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Email");
        addAccountContainer.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 40, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Username");
        addAccountContainer.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 70, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Password");
        addAccountContainer.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 70, 30));
        addAccountContainer.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1060, 20));

        roles.setBackground(new java.awt.Color(241, 241, 241));
        roles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Seller", "Buyer" }));
        addAccountContainer.add(roles, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 280, 30));

        addresss.setBackground(new java.awt.Color(241, 241, 241));
        addAccountContainer.add(addresss, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 280, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Address");
        addAccountContainer.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 50, 30));

        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("File extension: .JPEG, .PNG ");
        addAccountContainer.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 490, -1));

        myprofile5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        myprofile5.setText("Add Account");
        addAccountContainer.add(myprofile5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 30));

        manage11.setForeground(new java.awt.Color(102, 102, 102));
        manage11.setText("Create new account profile");
        addAccountContainer.add(manage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));

        jPanel6.add(addAccountContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1150, 570));

        tabs.addTab("tab3", jPanel6);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 1210, 720));

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

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        int rowIndex = accounts_table.getSelectedRow();
        try {
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "Please Select an Item!");
            } else {
                TableModel model = accounts_table.getModel();
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM accounts_table WHERE account_id = " + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    int height = 100;
                    int width = 100;
                    String firstName = rs.getString("fname");
                    String lastName = rs.getString("lname");
                    firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                    lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);

                    fullname.setText(firstName + " " + lastName);
                    displayStatus.setText(rs.getString("Status"));
                    editStatus.setSelectedItem(rs.getString("Status"));
                    editRole.setSelectedItem(rs.getString("role"));
                    String getImageFromDatabase = rs.getString("profile_picture");
                    GetImage.displayImage(displayPhoto, getImageFromDatabase, height, width);
                    tabs.setSelectedIndex(1);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_editActionPerformed

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_dashboardActionPerformed

    private void editAccountSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAccountSaveBtnActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String accountID = id.getText();
            String stats = (String) editStatus.getSelectedItem();
            String rolee = (String) editRole.getSelectedItem();

            sql = "UPDATE accounts_table SET status=?, role=? WHERE account_id=?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setString(1, stats);
                pst.setString(2, rolee);
                pst.setString(3, accountID);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account Updated Successfully!");
                    displayAccounts();
                    tabs.setSelectedIndex(0);
                    status.setText("");
                    fname.setText("");
                    status.setText("");
                    number.setText("");
                    role.setText("");
                    ImageIcon icon = new ImageIcon(getClass().getResource("/sampleProfiles/default.png"));
                    photo.setIcon(icon);
                    editRole.setSelectedItem("Admin");
                    editStatus.setSelectedItem("Active");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update Account!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_editAccountSaveBtnActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        Login logout = new Login();
        logout.setVisible(true);
        this.hide();
    }//GEN-LAST:event_logoutActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int rowIndex = accounts_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a account first");
        } else {
            TableModel model = accounts_table.getModel();
            Object value = model.getValueAt(rowIndex, 0);
            String id = value.toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                databaseConnector dbc = new databaseConnector();
                dbc.deleteAccount(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, "Account deleted successfully!");
                displayAccounts();
            }
        }    }//GEN-LAST:event_deleteActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        tabs.setSelectedIndex(2);
    }//GEN-LAST:event_addActionPerformed
    String fileName;
    String imagePath;
    private void addAccountsaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAccountsaveBtnActionPerformed
        String first_name = first.getText();
        String last_name = last.getText();
        String emails = em.getText();
        String phone = phoneNumber.getText();
        String addre = addresss.getText();
        String username = user.getText();
        String password = pass.getText();
        String selectedRole = (String) roles.getSelectedItem();

        if (emails.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty() || addre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (phone.length() < 11 || phone.length() > 11 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        databaseConnector dbc = new databaseConnector();
        try {
            if (isAccountExist.checkEmail(emails)) {
                JOptionPane.showMessageDialog(null, "Email already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isAccountExist.checkUsername(username)) {
                JOptionPane.showMessageDialog(null, "Username already taken.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());
            String sql;
            PreparedStatement pst;

            if (selectedFile != null) {
                fileName = selectedFile.getName();
                imagePath = "src/sampleProfiles/" + fileName;
                sql = "INSERT INTO `accounts_table`(`email`, `fname`, `lname`, `phone number`, `username`, `password`, `role`, `date joined`, `status`, `address`, `profile_picture`) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?, ?)";
                pst = (PreparedStatement) dbc.getConnection().prepareStatement(sql);
                pst.setString(10, imagePath);
            } else {
                String defaultImage = "src/sampleProfiles/default profile 100x100.png";
                sql = "INSERT INTO `accounts_table`(`email`, `fname`, `lname`, `phone number`, `username`, `password`, `role`, `date joined`, `status`, `address`, `profile_picture`) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?, ?)";
                pst = (PreparedStatement) dbc.getConnection().prepareStatement(sql);
                pst.setString(10, defaultImage);
            }

            pst.setString(1, emails);
            pst.setString(2, first_name);
            pst.setString(3, last_name);
            pst.setString(4, phone);
            pst.setString(5, username);
            pst.setString(6, hashedPass);
            pst.setString(7, selectedRole);
            pst.setString(8, "Pending");
            pst.setString(9, addre);

            pst.executeUpdate();
            pst.close();

            JOptionPane.showMessageDialog(null, "Account created successfully");

            displayAccounts();
            em.setText("");
            first.setText("");
            last.setText("");
            user.setText("");
            role.setText("");
            phoneNumber.setText("");
            pass.setText("");
            addresss.setText("");
            editRole.setSelectedIndex(0);
            tabs.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addAccountsaveBtnActionPerformed
    File selectedFile;
    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                Image resizedImage = originalImage.getScaledInstance(120, 110, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                displayImage.setIcon(icon);

                String imageName = selectedFile.getName();
                String imagePath = "src/sampleProfiles/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                selectedFile = destination;

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_selectActionPerformed

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_searchBarActionPerformed

    private void searchBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBarMouseClicked
        tabs.setSelectedIndex(0);
        searchBar.setFocusable(true);
        searchBar.setText("");
    }//GEN-LAST:event_searchBarMouseClicked

    private ImageIcon pendingIcon;
    private ImageIcon activeIcon;
    private ImageIcon inactiveIcon;


    private void accounts_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accounts_tableMouseClicked
        int rowIndex = accounts_table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = accounts_table.getModel();
            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM accounts_table WHERE account_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    id.setText("" + rs.getString("account_id"));
                    String firstName = rs.getString("fname");
                    firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                    fname.setText(firstName);
                    email.setText("" + rs.getString("email"));
                    number.setText("" + rs.getString("phone number"));
                    address.setText("" + rs.getString("address"));
                    role.setText("" + rs.getString("role"));
                    String statusValue = rs.getString("Status");
                    status.setText(statusValue);
                    activeIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-activeon-24 (1).png"));
                    inactiveIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-inavtiveon-24 (2).png"));
                    pendingIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-pendingon-24.png"));
                    if (statusValue.equals("Pending")) {
                        statusIcon.setIcon(pendingIcon);
                    } else if (statusValue.equals("Active")) {
                        statusIcon.setIcon(activeIcon);
                    } else if (statusValue.equals("Inactive")) {
                        statusIcon.setIcon(inactiveIcon);
                    }
                    int height = 70;
                    int width = 70;
                    String getImageFromDatabase = rs.getString("profile_picture");
                    GetImage.displayImage(photo, getImageFromDatabase, height, width);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_accounts_tableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new adminDashboard().setVisible(true);
                }
            });

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(adminDashboard.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountTableContainer;
    private javax.swing.JTable accounts_table;
    private javax.swing.JButton add;
    private javax.swing.JPanel addAccountContainer;
    private javax.swing.JButton addAccountsaveBtn;
    private javax.swing.JLabel address;
    private javax.swing.JTextField addresss;
    private javax.swing.JPanel c1;
    private javax.swing.JPanel c2;
    private javax.swing.JPanel c3;
    private javax.swing.JPanel c4;
    private javax.swing.JPanel c5;
    private javax.swing.JButton dashboard;
    private javax.swing.JPanel dashboardContainer;
    private javax.swing.JButton delete;
    private javax.swing.JLabel displayImage;
    private javax.swing.JLabel displayPhoto;
    private javax.swing.JLabel displayStatus;
    private javax.swing.JButton edit;
    private javax.swing.JButton editAccountSaveBtn;
    private javax.swing.JPanel editProfileContainer;
    private javax.swing.JComboBox<String> editRole;
    private javax.swing.JComboBox<String> editStatus;
    private javax.swing.JTextField em;
    private javax.swing.JLabel email;
    private javax.swing.JTextField first;
    private javax.swing.JLabel fname;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField last;
    private javax.swing.JButton logout;
    private javax.swing.JLabel manage10;
    private javax.swing.JLabel manage11;
    private javax.swing.JLabel manage14;
    private javax.swing.JLabel manage15;
    private javax.swing.JLabel manage16;
    private javax.swing.JLabel manage17;
    private javax.swing.JLabel manage3;
    private javax.swing.JLabel manage7;
    private javax.swing.JLabel manage8;
    private javax.swing.JLabel myprofile4;
    private javax.swing.JLabel myprofile5;
    private javax.swing.JLabel name;
    private javax.swing.JLabel number;
    private javax.swing.JTextField pass;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel role;
    private javax.swing.JComboBox<String> roles;
    private javax.swing.JScrollPane scrollBar;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchIcon;
    private javax.swing.JButton select;
    private javax.swing.JLabel status;
    private javax.swing.JLabel statusIcon;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
