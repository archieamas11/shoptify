/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

import accounts.Login;
import accounts.UserManager;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.adminlogs;
import config.databaseConnector;
import config.isAccountExist;
import config.search;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
    int accountID = UserManager.getLoggedInUserId();

    public adminDashboard() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        displayAccounts();
        displayAccountName();
        displayArchiveAccounts();
        adminlogs.displayAdminLogs(actionlogs_table);

        //Informations Panel
        UXmethods.RoundBorders.setArcStyle(c1, 15);
        UXmethods.RoundBorders.setArcStyle(c2, 15);
        UXmethods.RoundBorders.setArcStyle(c3, 15);
        UXmethods.RoundBorders.setArcStyle(c4, 15);
        UXmethods.RoundBorders.setArcStyle(c5, 15);
        UXmethods.RoundBorders.setArcStyle(c6, 15);
        UXmethods.RoundBorders.setArcStyle(c7, 15);
        UXmethods.RoundBorders.setArcStyle(c8, 15);
        UXmethods.RoundBorders.setArcStyle(c9, 15);
        UXmethods.RoundBorders.setArcStyle(c10, 15);
        UXmethods.RoundBorders.setArcStyle(z2, 15);
        UXmethods.RoundBorders.setArcStyle(z3, 15);
        UXmethods.RoundBorders.setArcStyle(z4, 15);
        UXmethods.RoundBorders.setArcStyle(z5, 15);
        UXmethods.RoundBorders.setArcStyle(z6, 15);

        //Buttons
        UXmethods.RoundBorders.setArcStyle(edit, 15);
        UXmethods.RoundBorders.setArcStyle(add2archive, 15);
        UXmethods.RoundBorders.setArcStyle(add, 15);
        UXmethods.RoundBorders.setArcStyle(searchIcon, 15);
        UXmethods.RoundBorders.setArcStyle(editAccountSaveBtn, 15);
        UXmethods.RoundBorders.setArcStyle(addAccountsaveBtn, 15);
        UXmethods.RoundBorders.setArcStyle(add2archive, 15);
        UXmethods.RoundBorders.setArcStyle(restore, 15);
        UXmethods.RoundBorders.setArcStyle(delete, 15);

        //Components
        UXmethods.RoundBorders.setArcStyle(scrollBar, 15);
        UXmethods.RoundBorders.setArcStyle(archiveAccountTableContainerScroll, 15);
        UXmethods.RoundBorders.setArcStyle(searchBar, 15);
        UXmethods.RoundBorders.setArcStyle(accountTableContainer, 30);
        UXmethods.RoundBorders.setArcStyle(archiveAccountTableContainer, 30);
        UXmethods.RoundBorders.setArcStyle(dashboardContainer, 15);
        UXmethods.RoundBorders.setArcStyle(editProfileContainer, 30);
        UXmethods.RoundBorders.setArcStyle(addAccountContainer, 30);
        UXmethods.RoundBorders.setArcStyle(actionLogsTableContainer, 15);
        UXmethods.RoundBorders.setArcStyle(actionLogsTableContainerScroll, 15);

        //Dashboard Buttons
        UXmethods.RoundBorders.setArcStyle(dashboardBtn, 50);
        UXmethods.RoundBorders.setArcStyle(logoutBtn, 50);
        UXmethods.RoundBorders.setArcStyle(archiveBtn, 50);
        UXmethods.RoundBorders.setArcStyle(logsBtn, 50);

        //Test
        UXmethods.RoundBorders.setArcStyle(editStatus, 15);
        UXmethods.RoundBorders.setArcStyle(editRole, 15);

        searchBar.setFocusable(false);
    }

    public void displayAccountName() {
        try {

            databaseConnector dbc = new databaseConnector();
            String query = "SELECT fname, lname, account_id, email, `phone number`, address, role, status, profile_picture FROM accounts_table WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, accountID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);

                //Profile
                manageFullName.setText(firstName + " " + lastName);
                manageStatus.setText("" + rs.getString("status"));
                manageEmail.setText("" + rs.getString("email"));
                managePhone.setText("" + rs.getString("phone number"));
                manageAddress.setText("" + rs.getString("address"));

                //Dashboard
                name.setText(firstName);
                id.setText("" + rs.getString("account_id"));
                email.setText("" + rs.getString("email"));
                number.setText("" + rs.getString("phone number"));
                address.setText("" + rs.getString("address"));
                role.setText("" + rs.getString("role"));
                String statusValue = rs.getString("status");
                status.setText(statusValue);

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

                int height = 100;
                int width = 100;
                String profilePicture = rs.getString("profile_picture");
                GetImage.displayImage(managePhoto, profilePicture, height, width);
                int heights = 70;
                int widths = 70;
                GetImage.displayImage(photo, profilePicture, heights, widths);
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void displayArchiveAccounts() {

        try {
            databaseConnector dbc = new databaseConnector();

            ResultSet rs = dbc.getData("SELECT `account_id` as `Account ID`, `fname` as `First Name`, `lname` as `Last Name`, `username` as `Username`, `address` as `Address`, `phone number` as `Phone Number`, `role` as `Role`, `date joined` as `Date Joined`, `status` as `Status` FROM accounts_table WHERE status = 'archived'");
            archive_table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayAccounts() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT `account_id` as `Account ID`, `fname` as `First Name`, `lname` as `Last Name`, `username` as `Username`, `address` as `Address`, `phone number` as `Phone Number`, `role` as `Role`, `date joined` as `Date Joined`, `status` as `Status` FROM accounts_table WHERE status IN ('Active', 'Inactive', 'Pending') AND account_id != ?";
            PreparedStatement statement = dbc.getConnection().prepareStatement(query);
            statement.setInt(1, accountID);
            ResultSet rs = statement.executeQuery();
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
        dashboardBtn = new javax.swing.JButton();
        profile = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        archiveBtn = new javax.swing.JButton();
        logsBtn = new javax.swing.JButton();
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
        add2archive = new javax.swing.JButton();
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
        optional = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        editProfileContainer1 = new javax.swing.JPanel();
        manageSave = new javax.swing.JButton();
        myprofile6 = new javax.swing.JLabel();
        manage12 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        manageFullName = new javax.swing.JLabel();
        manageStatus = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        managePhoto = new javax.swing.JLabel();
        z2 = new javax.swing.JPanel();
        manage19 = new javax.swing.JLabel();
        manageEmail = new javax.swing.JLabel();
        z3 = new javax.swing.JPanel();
        manage20 = new javax.swing.JLabel();
        managePhone = new javax.swing.JLabel();
        z4 = new javax.swing.JPanel();
        manage21 = new javax.swing.JLabel();
        manageAddress = new javax.swing.JLabel();
        z5 = new javax.swing.JPanel();
        manageRole = new javax.swing.JComboBox<>();
        manage9 = new javax.swing.JLabel();
        z6 = new javax.swing.JPanel();
        manage22 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        changePassword = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        archiveAccountTableContainer = new javax.swing.JPanel();
        archiveAccountTableContainerScroll = new javax.swing.JScrollPane();
        archive_table = new javax.swing.JTable();
        fname1 = new javax.swing.JLabel();
        photo1 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        statusIcon1 = new javax.swing.JLabel();
        status1 = new javax.swing.JLabel();
        c6 = new javax.swing.JPanel();
        manage18 = new javax.swing.JLabel();
        id1 = new javax.swing.JLabel();
        c7 = new javax.swing.JPanel();
        manage24 = new javax.swing.JLabel();
        email1 = new javax.swing.JLabel();
        c8 = new javax.swing.JPanel();
        manage25 = new javax.swing.JLabel();
        number1 = new javax.swing.JLabel();
        c9 = new javax.swing.JPanel();
        manage26 = new javax.swing.JLabel();
        address1 = new javax.swing.JLabel();
        c10 = new javax.swing.JPanel();
        manage27 = new javax.swing.JLabel();
        role1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        restore = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        actionLogsTableContainer = new javax.swing.JPanel();
        actionLogsTableContainerScroll = new javax.swing.JScrollPane();
        actionlogs_table = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardContainer.setBackground(new java.awt.Color(241, 241, 241));
        dashboardContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardBtn.setBackground(new java.awt.Color(153, 204, 255));
        dashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-dashboard-24.png"))); // NOI18N
        dashboardBtn.setBorderPainted(false);
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(dashboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 50, 50));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/2.png"))); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        dashboardContainer.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 70, 50));

        logoutBtn.setBackground(new java.awt.Color(255, 102, 102));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-logout-24.png"))); // NOI18N
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 50, 50));
        dashboardContainer.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 50, 10));

        archiveBtn.setBackground(new java.awt.Color(153, 204, 255));
        archiveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-archive-24.png"))); // NOI18N
        archiveBtn.setBorderPainted(false);
        archiveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(archiveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 50, 50));

        logsBtn.setBackground(new java.awt.Color(153, 204, 255));
        logsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-paste-special-32_1.png"))); // NOI18N
        logsBtn.setBorderPainted(false);
        logsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(logsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 50, 50));

        jPanel1.add(dashboardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 70, 670));

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
        jPanel5.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 240, 50));

        searchIcon.setBackground(new java.awt.Color(0, 158, 226));
        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchIcon.setBorder(null);
        searchIcon.setBorderPainted(false);
        searchIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIconActionPerformed(evt);
            }
        });
        jPanel5.add(searchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 60, 50));

        searchBar.setForeground(new java.awt.Color(140, 140, 140));
        searchBar.setText("  Search");
        searchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBarFocusLost(evt);
            }
        });
        searchBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchBarMouseClicked(evt);
            }
        });
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBarKeyReleased(evt);
            }
        });
        jPanel5.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 500, 50));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 1210, 120));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

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

        accountTableContainer.add(scrollBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 810, 500));

        fname.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fname.setText("FIRSTNAME");
        accountTableContainer.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 180, 40));

        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 70x70.png"))); // NOI18N
        accountTableContainer.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 70, 70));
        accountTableContainer.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 270, 20));

        statusIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusIcon.setForeground(new java.awt.Color(102, 102, 102));
        statusIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accountTableContainer.add(statusIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 30, 20));

        status.setForeground(new java.awt.Color(102, 102, 102));
        accountTableContainer.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, -1, 20));

        add2archive.setBackground(new java.awt.Color(255, 102, 102));
        add2archive.setForeground(new java.awt.Color(255, 255, 255));
        add2archive.setText("Add to Archive");
        add2archive.setBorder(null);
        add2archive.setBorderPainted(false);
        add2archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2archiveActionPerformed(evt);
            }
        });
        accountTableContainer.add(add2archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 490, 130, 50));

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

        id.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
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

        email.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
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

        number.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
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

        address.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
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

        role.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        role.setForeground(new java.awt.Color(102, 102, 102));
        c5.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, 270, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Accounts Table");
        accountTableContainer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

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
        editAccountSaveBtn.setBorderPainted(false);
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
        addAccountContainer.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 130, 40));

        displayImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        displayImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/120x120.png"))); // NOI18N
        addAccountContainer.add(displayImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 206, 490, 120));

        optional.setForeground(new java.awt.Color(153, 153, 153));
        optional.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optional.setText("* Optional");
        addAccountContainer.add(optional, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 390, 490, -1));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editProfileContainer1.setBackground(new java.awt.Color(241, 241, 241));
        editProfileContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manageSave.setBackground(new java.awt.Color(0, 158, 226));
        manageSave.setForeground(new java.awt.Color(255, 255, 255));
        manageSave.setText("Save");
        manageSave.setBorderPainted(false);
        manageSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageSaveActionPerformed(evt);
            }
        });
        editProfileContainer1.add(manageSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 470, -1, 40));

        myprofile6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        myprofile6.setText("Manage Profile");
        editProfileContainer1.add(myprofile6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, 30));

        manage12.setForeground(new java.awt.Color(102, 102, 102));
        manage12.setText("Manage your account profile information");
        editProfileContainer1.add(manage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));
        editProfileContainer1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1060, 20));

        manageFullName.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        manageFullName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        manageFullName.setText("Archie Albarico");
        editProfileContainer1.add(manageFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 190, 40));

        manageStatus.setForeground(new java.awt.Color(153, 153, 153));
        manageStatus.setText("Pending");
        editProfileContainer1.add(manageStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, -1, 20));

        jLabel14.setText("Status:");
        editProfileContainer1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, 20));

        managePhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managePhotoMouseClicked(evt);
            }
        });
        editProfileContainer1.add(managePhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 100, 100));

        z2.setBackground(new java.awt.Color(255, 255, 255));
        z2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage19.setForeground(new java.awt.Color(102, 102, 102));
        manage19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-email-24.png"))); // NOI18N
        z2.add(manage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        manageEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        manageEmail.setText("Email");
        z2.add(manageEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 230, 30));

        editProfileContainer1.add(z2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 320, 30));

        z3.setBackground(new java.awt.Color(255, 255, 255));
        z3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage20.setForeground(new java.awt.Color(102, 102, 102));
        manage20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-phone-24.png"))); // NOI18N
        z3.add(manage20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        managePhone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        managePhone.setText("Phone");
        z3.add(managePhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 230, 30));

        editProfileContainer1.add(z3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 320, 30));

        z4.setBackground(new java.awt.Color(255, 255, 255));
        z4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage21.setForeground(new java.awt.Color(102, 102, 102));
        manage21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-address-24.png"))); // NOI18N
        z4.add(manage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        manageAddress.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        manageAddress.setText("Address");
        z4.add(manageAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 230, 30));

        editProfileContainer1.add(z4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 320, 30));

        z5.setBackground(new java.awt.Color(255, 255, 255));
        z5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manageRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        manageRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Seller", "Buyer" }));
        manageRole.setBorder(null);
        z5.add(manageRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 260, 30));

        manage9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage9.setForeground(new java.awt.Color(102, 102, 102));
        manage9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-24.png"))); // NOI18N
        z5.add(manage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        editProfileContainer1.add(z5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 320, 30));

        z6.setBackground(new java.awt.Color(255, 255, 255));
        z6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage22.setForeground(new java.awt.Color(102, 102, 102));
        manage22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-password-24.png"))); // NOI18N
        z6.add(manage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Password");
        z6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 30));

        editProfileContainer1.add(z6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 320, 30));

        jLabel15.setForeground(new java.awt.Color(0, 158, 226));
        jLabel15.setText("Change");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        editProfileContainer1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 260, -1, 30));

        changePassword.setForeground(new java.awt.Color(0, 158, 226));
        changePassword.setText("Change");
        changePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePasswordMouseClicked(evt);
            }
        });
        editProfileContainer1.add(changePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, -1, 30));

        jLabel20.setForeground(new java.awt.Color(0, 158, 226));
        jLabel20.setText("Change");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        editProfileContainer1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, -1, 30));

        jLabel21.setForeground(new java.awt.Color(0, 158, 226));
        jLabel21.setText("Change");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        editProfileContainer1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, -1, 30));

        jPanel2.add(editProfileContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1150, 570));

        tabs.addTab("tab4", jPanel2);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archiveAccountTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        archiveAccountTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archiveAccountTableContainerScroll.setBackground(new java.awt.Color(0, 0, 0));

        archive_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        archive_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        archive_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archive_tableMouseClicked(evt);
            }
        });
        archiveAccountTableContainerScroll.setViewportView(archive_table);

        archiveAccountTableContainer.add(archiveAccountTableContainerScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 810, 500));

        fname1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        fname1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fname1.setText("FIRST NAME");
        archiveAccountTableContainer.add(fname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 180, 40));

        photo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 70x70.png"))); // NOI18N
        archiveAccountTableContainer.add(photo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 70, 70));
        archiveAccountTableContainer.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 270, 20));

        statusIcon1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusIcon1.setForeground(new java.awt.Color(102, 102, 102));
        statusIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-connection-activeon-24 (1).png"))); // NOI18N
        archiveAccountTableContainer.add(statusIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 30, 20));

        status1.setForeground(new java.awt.Color(102, 102, 102));
        status1.setText("Status");
        archiveAccountTableContainer.add(status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, -1, 20));

        c6.setBackground(new java.awt.Color(255, 255, 255));
        c6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage18.setForeground(new java.awt.Color(102, 102, 102));
        manage18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-tag-woman-horizontal-24.png"))); // NOI18N
        c6.add(manage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        id1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        id1.setForeground(new java.awt.Color(102, 102, 102));
        c6.add(id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 270, 30));

        c7.setBackground(new java.awt.Color(255, 255, 255));
        c7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage24.setForeground(new java.awt.Color(102, 102, 102));
        manage24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-email-24.png"))); // NOI18N
        c7.add(manage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        email1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        email1.setForeground(new java.awt.Color(102, 102, 102));
        c7.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 220, 270, 30));

        c8.setBackground(new java.awt.Color(255, 255, 255));
        c8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage25.setForeground(new java.awt.Color(102, 102, 102));
        manage25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-phone-24.png"))); // NOI18N
        c8.add(manage25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        number1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        number1.setForeground(new java.awt.Color(102, 102, 102));
        c8.add(number1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 260, 270, 30));

        c9.setBackground(new java.awt.Color(255, 255, 255));
        c9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage26.setForeground(new java.awt.Color(102, 102, 102));
        manage26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-address-24.png"))); // NOI18N
        c9.add(manage26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        address1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        address1.setForeground(new java.awt.Color(102, 102, 102));
        c9.add(address1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, 270, 30));

        c10.setBackground(new java.awt.Color(255, 255, 255));
        c10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage27.setForeground(new java.awt.Color(102, 102, 102));
        manage27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-24.png"))); // NOI18N
        c10.add(manage27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        role1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        role1.setForeground(new java.awt.Color(102, 102, 102));
        c10.add(role1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c10, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, 270, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Archive Accounts Table");
        archiveAccountTableContainer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        restore.setBackground(new java.awt.Color(122, 183, 147));
        restore.setForeground(new java.awt.Color(255, 255, 255));
        restore.setText("Restore");
        restore.setBorder(null);
        restore.setBorderPainted(false);
        restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreActionPerformed(evt);
            }
        });
        archiveAccountTableContainer.add(restore, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, 130, 50));

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
        archiveAccountTableContainer.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 490, 130, 50));

        jPanel8.add(archiveAccountTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1150, 570));

        tabs.addTab("tab5", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        actionLogsTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        actionLogsTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        actionLogsTableContainerScroll.setBackground(new java.awt.Color(0, 0, 0));

        actionlogs_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        actionlogs_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        actionlogs_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionlogs_tableMouseClicked(evt);
            }
        });
        actionLogsTableContainerScroll.setViewportView(actionlogs_table);

        actionLogsTableContainer.add(actionLogsTableContainerScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1090, 500));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Action Logs");
        actionLogsTableContainer.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        jPanel9.add(actionLogsTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1150, 570));

        tabs.addTab("tab6", jPanel9);

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
        try {
            int rowIndex = accounts_table.getSelectedRow();
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

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void editAccountSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAccountSaveBtnActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String account_id = id.getText();
            String statuss = status.getText();
            String roles = role.getText();

            String stats = (String) editStatus.getSelectedItem();
            String rolee = (String) editRole.getSelectedItem();

            sql = "UPDATE accounts_table SET status=?, role=? WHERE account_id=?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setString(1, stats);
                pst.setString(2, rolee);
                pst.setString(3, account_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account Updated Successfully!");
                    displayAccounts();
                    displayAccountName();
                    //logs
                    String details = null;
                    String action = "Change Status";
                    if (!stats.equals(statuss) && !rolee.equals(roles)) {
                        details = "User " + accountID + " Successfully changed the status and role of " + account_id + " to " + stats + " and " + rolee + "";
                        action = "Change Status & Role";
                    } else if (!stats.equals(statuss)) {
                        details = "User " + accountID + " Successfully changed the status of " + account_id + " to " + stats + "";
                    } else if (!rolee.equals(roles)) {
                        details = "User " + accountID + " Successfully changed the role of " + account_id + " to " + rolee + "";
                    }
                    adminlogs.recordLogs(accountID, action, details);

                    tabs.setSelectedIndex(0);
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

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        Login logout = new Login();
        logout.setVisible(true);
        this.dispose();

        String action = "Logged out";
        String details = "User " + accountID + " Successfully logged out!";
        adminlogs.recordLogs(accountID, action, details);
    }//GEN-LAST:event_logoutBtnActionPerformed

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

            //logs
            String details = "User " + accountID + " Successfully added an account";
            String action = "Add account";
            adminlogs.recordLogs(accountID, action, details);
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

    private ImageIcon pendingIcon;
    private ImageIcon activeIcon;
    private ImageIcon inactiveIcon;
    private ImageIcon archivedIcon;

    private void displaySelectedAccountInfo(JTable table, JLabel idField, JLabel fnameField, JLabel emailField,
            JLabel numberField, JLabel addressField, JLabel roleField,
            JLabel statusField, JLabel statusIcon, JLabel photoLabel) {
        int rowIndex = table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = table.getModel();

            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM accounts_table WHERE account_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    idField.setText("" + rs.getString("account_id"));
                    String firstName = rs.getString("fname");
                    firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                    fnameField.setText(firstName);
                    emailField.setText("" + rs.getString("email"));
                    numberField.setText("" + rs.getString("phone number"));
                    addressField.setText("" + rs.getString("address"));
                    roleField.setText("" + rs.getString("role"));
                    String statusValue = rs.getString("Status");
                    statusField.setText(statusValue);

                    ImageIcon activeIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-activeon-24 (1).png"));
                    ImageIcon inactiveIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-inavtiveon-24 (2).png"));
                    ImageIcon archivedIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-inavtiveon-24 (2).png"));
                    ImageIcon pendingIcon = new ImageIcon(getClass().getResource("/image/icons8-connection-pendingon-24.png"));

                    if (statusValue.equals("Pending")) {
                        statusIcon.setIcon(pendingIcon);
                    } else if (statusValue.equals("Active")) {
                        statusIcon.setIcon(activeIcon);
                    } else if (statusValue.equals("Inactive")) {
                        statusIcon.setIcon(inactiveIcon);
                    } else {
                        statusIcon.setIcon(archivedIcon);
                    }

                    int height = 70;
                    int width = 70;
                    String getImageFromDatabase = rs.getString("profile_picture");
                    GetImage.displayImage(photoLabel, getImageFromDatabase, height, width);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }

    private void accounts_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accounts_tableMouseClicked
        displaySelectedAccountInfo(accounts_table, id, fname, email, number, address, role, status, statusIcon, photo);
    }//GEN-LAST:event_accounts_tableMouseClicked

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        tabs.setSelectedIndex(3);
        displayAccountName();
    }//GEN-LAST:event_profileMouseClicked

    private void manageSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageSaveActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String account_id = id.getText();
            String rolee = (String) manageRole.getSelectedItem();

            sql = "UPDATE accounts_table SET role=? WHERE account_id=?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setString(1, rolee);
                pst.setString(2, account_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account Updated Successfully!");
                    displayAccounts();
                    displayAccountName();

                    //logs
                    String details = "User " + accountID + " Successfully changed the role to " + rolee + "!";
                    String action = "Change Role";
                    adminlogs.recordLogs(accountID, action, details);

                    tabs.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update Account!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_manageSaveActionPerformed

    private void changePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePasswordMouseClicked
        String oldPassword = JOptionPane.showInputDialog(null, "Enter your old password:");

        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your old password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean isExist = isAccountExist.checkPassword(oldPassword, accountID);

            if (!isExist) {
                JOptionPane.showMessageDialog(null, "Your old password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:");
            if (newPassword == null || newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a new password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the password in the database
            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String updateQuery = "UPDATE accounts_table SET password = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, hashedNewPassword);
            pst.setInt(2, accountID);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully.");
                //logs
                String details = "User " + accountID + " Successfully changed the password!";
                String action = "Change Password";
                adminlogs.recordLogs(accountID, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_changePasswordMouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        String newAddress = JOptionPane.showInputDialog(null, "Enter your new address:");

        if (newAddress == null || newAddress.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a new address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Update the address in the database
            String updateQuery = "UPDATE accounts_table SET address = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, newAddress);
            pst.setInt(2, accountID);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Address updated successfully.");
                displayAccountName();
                //logs
                String details = "User " + accountID + " Successfully changed the address!";
                String action = "Change Address";
                adminlogs.recordLogs(accountID, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update address.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update address.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        String email = JOptionPane.showInputDialog(null, "Enter your new email address:");

        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a new address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Update the address in the database
            String updateQuery = "UPDATE accounts_table SET email = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, email);
            pst.setInt(2, accountID);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Email address updated successfully.");
                displayAccountName();
                //logs
                String details = "User " + accountID + " Successfully changed the email!";
                String action = "Change Email";
                adminlogs.recordLogs(accountID, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update email address.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update address.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        String phone = JOptionPane.showInputDialog(null, "Enter your new Phone number:");

        if (phone.length() < 11 || phone.length() > 12 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (phone == null || phone.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Update the address in the database
            String updateQuery = "UPDATE accounts_table SET `phone number` = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, phone);
            pst.setInt(2, accountID);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Phone number updated successfully.");
                displayAccountName();
                //logs
                String details = "User " + accountID + " Successfully changed the phone number!";
                String action = "Change Number";
                adminlogs.recordLogs(accountID, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update Phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to Phone number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void managePhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managePhotoMouseClicked
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
                managePhoto.setIcon(icon);

                String imageName = selectedFile.getName();
                String imagePath = "src/sampleProfiles/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                selectedFile = destination;

                if (selectedFile != null) {
                    fileName = selectedFile.getName();
                    imagePath = "src/sampleProfiles/" + fileName;

                    // Initialize database connection
                    databaseConnector dbc = new databaseConnector();
                    String sql = "UPDATE accounts_table SET `profile_picture`=? WHERE account_id=?";
                    try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                        pst.setString(1, imagePath);
                        pst.setInt(2, accountID);

                        int rowsUpdated = pst.executeUpdate();

                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null, "Profile picture updated successfully!");
                            displayAccountName();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to update profile picture!");
                        }
                    }
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_managePhotoMouseClicked


    private void searchIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIconActionPerformed
        search.searchResult(accounts_table, searchBar);
    }//GEN-LAST:event_searchIconActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        search.searchResult(accounts_table, searchBar);
        search.searchResult(actionlogs_table, searchBar);
    }//GEN-LAST:event_searchBarKeyReleased

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().isEmpty()) {
            searchBar.setText("  Search");
            searchBar.setForeground(Color.decode("#8C8C8C"));
        }
    }//GEN-LAST:event_searchBarFocusLost

    private void searchBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBarMouseClicked
        searchBar.setFocusable(true);
        searchBar.requestFocusInWindow();
        if (searchBar.getText().isEmpty() || searchBar.getText().equals("  Search")) {
            searchBar.setText("");
            searchBar.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_searchBarMouseClicked

    private void archiveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveBtnActionPerformed
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_archiveBtnActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int rowIndex = archive_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a account first");
        } else {
            TableModel model = archive_table.getModel();
            Object value = model.getValueAt(rowIndex, 0);
            String id = value.toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                databaseConnector dbc = new databaseConnector();
                dbc.deleteAccount(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, "Account deleted successfully!");
                displayAccounts();
                displayArchiveAccounts();
                //logs
                String details = "User " + accountID + " Successfully deleted the account " + id1 + "!";
                String action = "Delete Account";
                adminlogs.recordLogs(accountID, action, details);
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            int restoredAccountID = Integer.parseInt(id1.getText());
            String sql = "UPDATE accounts_table SET `status`='Pending' WHERE `account_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, restoredAccountID);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account has been retored Successfully!");
                    displayAccounts();
                    displayArchiveAccounts();
                    String action = "Restore";
                    String details = "User " + accountID + " Successfully restored account " + restoredAccountID + " from archive!";
                    adminlogs.recordLogs(accountID, action, details);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to restore account!");
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_restoreActionPerformed

    private void add2archiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2archiveActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            int account_id = Integer.parseInt(id.getText());
            String sql = "UPDATE accounts_table SET `Status`='archived' WHERE `account_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, account_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account added to archive Successfully!");
                    displayAccounts();
                    displayArchiveAccounts();

                    String action = "Archive";
                    String details = "User " + accountID + " Successfully put account " + account_id + " to archive!";
                    adminlogs.recordLogs(accountID, action, details);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add the account to archive!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_add2archiveActionPerformed

    private void archive_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archive_tableMouseClicked
        displaySelectedAccountInfo(archive_table, id1, fname1, email1, number1, address1, role1, status1, statusIcon1, photo1);
    }//GEN-LAST:event_archive_tableMouseClicked

    private void logsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsBtnActionPerformed
        tabs.setSelectedIndex(5);
        adminlogs.displayAdminLogs(actionlogs_table);
    }//GEN-LAST:event_logsBtnActionPerformed

    private void actionlogs_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionlogs_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_actionlogs_tableMouseClicked

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
    private javax.swing.JPanel actionLogsTableContainer;
    private javax.swing.JScrollPane actionLogsTableContainerScroll;
    private javax.swing.JTable actionlogs_table;
    private javax.swing.JButton add;
    private javax.swing.JButton add2archive;
    private javax.swing.JPanel addAccountContainer;
    private javax.swing.JButton addAccountsaveBtn;
    private javax.swing.JLabel address;
    private javax.swing.JLabel address1;
    private javax.swing.JTextField addresss;
    private javax.swing.JPanel archiveAccountTableContainer;
    private javax.swing.JScrollPane archiveAccountTableContainerScroll;
    private javax.swing.JButton archiveBtn;
    private javax.swing.JTable archive_table;
    private javax.swing.JPanel c1;
    private javax.swing.JPanel c10;
    private javax.swing.JPanel c2;
    private javax.swing.JPanel c3;
    private javax.swing.JPanel c4;
    private javax.swing.JPanel c5;
    private javax.swing.JPanel c6;
    private javax.swing.JPanel c7;
    private javax.swing.JPanel c8;
    private javax.swing.JPanel c9;
    private javax.swing.JLabel changePassword;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JPanel dashboardContainer;
    private javax.swing.JButton delete;
    private javax.swing.JLabel displayImage;
    private javax.swing.JLabel displayPhoto;
    private javax.swing.JLabel displayStatus;
    private javax.swing.JButton edit;
    private javax.swing.JButton editAccountSaveBtn;
    private javax.swing.JPanel editProfileContainer;
    private javax.swing.JPanel editProfileContainer1;
    private javax.swing.JComboBox<String> editRole;
    private javax.swing.JComboBox<String> editStatus;
    private javax.swing.JTextField em;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JTextField first;
    private javax.swing.JLabel fname;
    private javax.swing.JLabel fname1;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel id;
    private javax.swing.JLabel id1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField last;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton logsBtn;
    private javax.swing.JLabel manage10;
    private javax.swing.JLabel manage11;
    private javax.swing.JLabel manage12;
    private javax.swing.JLabel manage14;
    private javax.swing.JLabel manage15;
    private javax.swing.JLabel manage16;
    private javax.swing.JLabel manage17;
    private javax.swing.JLabel manage18;
    private javax.swing.JLabel manage19;
    private javax.swing.JLabel manage20;
    private javax.swing.JLabel manage21;
    private javax.swing.JLabel manage22;
    private javax.swing.JLabel manage24;
    private javax.swing.JLabel manage25;
    private javax.swing.JLabel manage26;
    private javax.swing.JLabel manage27;
    private javax.swing.JLabel manage3;
    private javax.swing.JLabel manage7;
    private javax.swing.JLabel manage8;
    private javax.swing.JLabel manage9;
    private javax.swing.JLabel manageAddress;
    private javax.swing.JLabel manageEmail;
    private javax.swing.JLabel manageFullName;
    private javax.swing.JLabel managePhone;
    private javax.swing.JLabel managePhoto;
    private javax.swing.JComboBox<String> manageRole;
    private javax.swing.JButton manageSave;
    private javax.swing.JLabel manageStatus;
    private javax.swing.JLabel myprofile4;
    private javax.swing.JLabel myprofile5;
    private javax.swing.JLabel myprofile6;
    private javax.swing.JLabel name;
    private javax.swing.JLabel number;
    private javax.swing.JLabel number1;
    private javax.swing.JLabel optional;
    private javax.swing.JTextField pass;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel photo1;
    private javax.swing.JLabel profile;
    private javax.swing.JButton restore;
    private javax.swing.JLabel role;
    private javax.swing.JLabel role1;
    private javax.swing.JComboBox<String> roles;
    private javax.swing.JScrollPane scrollBar;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchIcon;
    private javax.swing.JButton select;
    private javax.swing.JLabel status;
    private javax.swing.JLabel status1;
    private javax.swing.JLabel statusIcon;
    private javax.swing.JLabel statusIcon1;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField user;
    private javax.swing.JPanel z2;
    private javax.swing.JPanel z3;
    private javax.swing.JPanel z4;
    private javax.swing.JPanel z5;
    private javax.swing.JPanel z6;
    // End of variables declaration//GEN-END:variables
}
