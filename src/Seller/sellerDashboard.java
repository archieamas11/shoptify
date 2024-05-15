package Seller;

import accounts.Login;
import static accounts.Login.accountId;
import accounts.UserManager;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.databaseConnector;
import config.search;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import java.time.format.DateTimeFormatter;

public class sellerDashboard extends javax.swing.JFrame {

    int sellerID = UserManager.getLoggedInUserId();

    public sellerDashboard() {
        initComponents();
        displayCurrentDate();
        displayData();
        displayArchive();
        displayAccounts();
        displayPurchase();
        displayTotalSales(sellerID);
        displayTotalProducts(sellerID);
        displayTotalOrders(sellerID);
        displayTotalPendingOrders(sellerID);

        //Buttons
        UXmethods.RoundBorders.setArcStyle(logout, 50);
        UXmethods.RoundBorders.setArcStyle(dashboard, 50);

        UXmethods.RoundBorders.setArcStyle(manage, 50);
        UXmethods.RoundBorders.setArcStyle(accounts, 50);
        UXmethods.RoundBorders.setArcStyle(orders, 50);
        UXmethods.RoundBorders.setArcStyle(archivebtn, 50);
        UXmethods.RoundBorders.setArcStyle(logsButton, 50);

        //Containers
        UXmethods.RoundBorders.setArcStyle(dashboardContainer, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER2, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER3, 15);
    }

    private void displayCurrentDate() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);

        todaysDate.setText(formattedDate);
    }

    private void displayTotalOrders(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT COUNT(*) as TotalOrders FROM tbl_sales WHERE order_status = 'Accepted' AND seller_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, sellerID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int orders = rs.getInt("TotalOrders");
                totalOrders.setText(String.format("%d", orders));
            } else {
                totalOrders.setText("0");
            }

            rs.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void displayTotalPendingOrders(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT COUNT(*) as TotalPending FROM tbl_sales WHERE order_status = 'Pending' AND seller_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, sellerID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int pending_orders = rs.getInt("TotalPending");
                pendingOrders.setText(String.format("%d", pending_orders));
            } else {
                pendingOrders.setText("0");
            }

            rs.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void displayTotalProducts(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT COUNT(*) as TotalProducts FROM tbl_products WHERE status = 'Available' AND seller_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, sellerID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int total_products = rs.getInt("TotalProducts");
                totalProducts.setText(String.format("%d", total_products));
            } else {
                totalProducts.setText("0");
            }

            rs.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void displayTotalSales(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT SUM(total_price) as TotalSales FROM tbl_sales WHERE order_status = 'Accepted' AND seller_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, sellerID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int sales = rs.getInt("TotalSales");
                totalSales.setText(String.format("₱ %d", sales));
            } else {
                totalSales.setText("₱ 0");
            }

            rs.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
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

    public void displayPurchase() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT `transaction_id` as `Sales ID`, `buyer_id` as `Buyer ID`, `seller_id` as `Seller ID`, `product_id` as `Product ID`, `order_status` as `Order Status`, `date_purchase` as `Date Purchase`  FROM tbl_sales WHERE seller_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, sellerID);
            ResultSet rs = pst.executeQuery();

            purchase_table.setModel(DbUtils.resultSetToTableModel(rs));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            purchase_table.setDefaultRenderer(Object.class, centerRenderer);

            rs.close();
            pst.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void displayData() {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT `product_id` as `Product ID`, `seller_id` as `Seller ID`, `product_name`, `price`, `stock`, `category` as `Category`, `status`, `date_created` FROM tbl_products WHERE status IN ('Available', 'Not Available') AND seller_id = ?");
            pstmt.setInt(1, sellerID);
            ResultSet rs = pstmt.executeQuery();

            product_table.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn column;
            column = product_table.getColumnModel().getColumn(0);
            column.setPreferredWidth(20);
            column = product_table.getColumnModel().getColumn(1);
            column.setPreferredWidth(20);
            column = product_table.getColumnModel().getColumn(2);
            column.setPreferredWidth(200);
            column = product_table.getColumnModel().getColumn(3);
            column.setPreferredWidth(20);
            column = product_table.getColumnModel().getColumn(4);
            column.setPreferredWidth(20);
            column = product_table.getColumnModel().getColumn(5); //description
            column.setPreferredWidth(20);
            column = product_table.getColumnModel().getColumn(6);
            column.setPreferredWidth(20);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            product_table.setDefaultRenderer(Object.class, centerRenderer);

            rs.close();
            pstmt.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayArchive() {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT * FROM tbl_products WHERE status IN ('Archived') AND seller_id = ?");
            pstmt.setInt(1, sellerID);
            ResultSet rs = pstmt.executeQuery();
            archive_table.setModel(DbUtils.resultSetToTableModel(rs));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            archive_table.setDefaultRenderer(Object.class, centerRenderer);
            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        dashboardContainer = new javax.swing.JPanel();
        logout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        dashboard = new javax.swing.JButton();
        manage = new javax.swing.JButton();
        orders = new javax.swing.JButton();
        accounts = new javax.swing.JButton();
        archivebtn = new javax.swing.JButton();
        profile = new javax.swing.JLabel();
        logsButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        helloSeller = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        totalSales = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        containersds = new javax.swing.JLabel();
        CONTAINER = new javax.swing.JPanel();
        CONTAINER1 = new javax.swing.JPanel();
        CONTAINER2 = new javax.swing.JPanel();
        CONTAINER3 = new javax.swing.JPanel();
        CONTAINER5 = new javax.swing.JPanel();
        pendingOrders = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        totalOrders = new javax.swing.JLabel();
        totalProducts = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        totalLoss = new javax.swing.JLabel();
        todaysDate = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        productsContainer = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        editButton = new javax.swing.JButton();
        archive = new javax.swing.JButton();
        salesProfile1 = new javax.swing.JLabel();
        salesFN1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        displayPhoto = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        haha1 = new javax.swing.JLabel();
        stock = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JEditorPane();
        jLabel36 = new javax.swing.JLabel();
        category = new javax.swing.JLabel();
        searchbtn = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        archiveAccountTableContainer = new javax.swing.JPanel();
        archiveAccountTableContainerScroll = new javax.swing.JScrollPane();
        archive_table = new javax.swing.JTable();
        productName = new javax.swing.JLabel();
        productPhoto = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        productPrice = new javax.swing.JLabel();
        c6 = new javax.swing.JPanel();
        manage18 = new javax.swing.JLabel();
        productID = new javax.swing.JLabel();
        c7 = new javax.swing.JPanel();
        manage24 = new javax.swing.JLabel();
        productQuantity = new javax.swing.JLabel();
        c8 = new javax.swing.JPanel();
        manage25 = new javax.swing.JLabel();
        productStatus = new javax.swing.JLabel();
        c9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descript = new javax.swing.JEditorPane();
        jLabel3 = new javax.swing.JLabel();
        restore = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        s = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        purchase_table = new javax.swing.JTable();
        salesProfile = new javax.swing.JLabel();
        salesFN = new javax.swing.JLabel();
        salesAddress = new javax.swing.JLabel();
        accept_order = new javax.swing.JButton();
        decline = new javax.swing.JButton();
        salesPPhoto = new javax.swing.JLabel();
        salesPN = new javax.swing.JLabel();
        salesTotal = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        salesPrice = new javax.swing.JLabel();
        salesQuantity = new javax.swing.JLabel();
        productPrice5 = new javax.swing.JLabel();
        orderTotal = new javax.swing.JLabel();
        searchbtn1 = new javax.swing.JButton();
        searchBar1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        accounts_table = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        searchbtn9 = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        searchbtn11 = new javax.swing.JButton();
        searchbtn12 = new javax.swing.JButton();
        search3 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        manage5 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        manage6 = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        manage7 = new javax.swing.JLabel();
        myprofile1 = new javax.swing.JLabel();
        manage1 = new javax.swing.JLabel();
        manage8 = new javax.swing.JLabel();
        stats = new javax.swing.JComboBox<>();
        role = new javax.swing.JComboBox<>();
        manage9 = new javax.swing.JLabel();
        manage10 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        selectFile = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        manage11 = new javax.swing.JLabel();
        manage12 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        manage13 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        manage14 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        display = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        addSave = new javax.swing.JButton();
        addPhoto = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        addName = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        addPrice = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        addStock = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        addStatus = new javax.swing.JComboBox<>();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        addDescription = new javax.swing.JEditorPane();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        salesProfile3 = new javax.swing.JLabel();
        salesFN3 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        addCategory = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        getStatus = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        getName = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        getPrice = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        getStock = new javax.swing.JTextField();
        editsave = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        getPhoto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        salesProfile2 = new javax.swing.JLabel();
        salesFN2 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        getDescription = new javax.swing.JEditorPane();
        jLabel35 = new javax.swing.JLabel();
        getCategory = new javax.swing.JComboBox<>();
        jPanel28 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel5.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardContainer.setBackground(new java.awt.Color(241, 241, 241));
        dashboardContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setBackground(new java.awt.Color(255, 102, 102));
        logout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-logout-24.png"))); // NOI18N
        logout.setBorderPainted(false);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        dashboardContainer.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 50, 50));
        dashboardContainer.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 50, 20));

        dashboard.setBackground(new java.awt.Color(153, 204, 255));
        dashboard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-dashboard-24.png"))); // NOI18N
        dashboard.setBorderPainted(false);
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        dashboardContainer.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 50, 50));

        manage.setBackground(new java.awt.Color(153, 204, 255));
        manage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        manage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-edit-24_1.png"))); // NOI18N
        manage.setBorderPainted(false);
        manage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageActionPerformed(evt);
            }
        });
        dashboardContainer.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 50, 50));

        orders.setBackground(new java.awt.Color(153, 204, 255));
        orders.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        orders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-buy-24.png"))); // NOI18N
        orders.setBorderPainted(false);
        orders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersActionPerformed(evt);
            }
        });
        dashboardContainer.add(orders, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 50, 50));

        accounts.setBackground(new java.awt.Color(153, 204, 255));
        accounts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        accounts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-user-locked-24.png"))); // NOI18N
        accounts.setBorderPainted(false);
        accounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountsActionPerformed(evt);
            }
        });
        dashboardContainer.add(accounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 50, 50));

        archivebtn.setBackground(new java.awt.Color(153, 204, 255));
        archivebtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        archivebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-archive-24.png"))); // NOI18N
        archivebtn.setBorderPainted(false);
        archivebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivebtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(archivebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 50, 50));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/2.png"))); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        dashboardContainer.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 70, 50));

        logsButton.setBackground(new java.awt.Color(153, 204, 255));
        logsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/activity_logs.png"))); // NOI18N
        logsButton.setBorderPainted(false);
        logsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsButtonActionPerformed(evt);
            }
        });
        dashboardContainer.add(logsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 50, 50));

        jPanel5.add(dashboardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 70, 680));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 10));

        tabs.setBackground(new java.awt.Color(153, 153, 153));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Great job today! You've done exceptionally well.");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 186, 580, -1));

        helloSeller.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 40)); // NOI18N
        helloSeller.setText("Hi, Seller!");
        jPanel6.add(helloSeller, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/try.png"))); // NOI18N
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 1120, 260));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Overview");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 158, 226));
        jLabel20.setText("SHOPTIFY");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 30));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Welcome to shoptify dashboard");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, -1));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 70));

        totalSales.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalSales.setForeground(new java.awt.Color(255, 255, 255));
        totalSales.setText("₱  19,920");
        jPanel6.add(totalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Sales");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, 20));

        containersds.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalsales.png"))); // NOI18N
        jPanel6.add(containersds, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 280, 80));

        CONTAINER.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CONTAINER1.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        CONTAINER.add(CONTAINER1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 420, 250));

        jPanel6.add(CONTAINER, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, 310, 250));

        CONTAINER2.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(CONTAINER2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 380, 250));

        CONTAINER3.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CONTAINER5.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        CONTAINER3.add(CONTAINER5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 420, 250));

        jPanel6.add(CONTAINER3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 380, 250));

        pendingOrders.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        pendingOrders.setText("80");
        jPanel6.add(pendingOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("Pending Orders");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 370, -1, 20));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setText("Total Loss");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, -1, 20));

        totalOrders.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalOrders.setText("80");
        jPanel6.add(totalOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, -1, -1));

        totalProducts.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalProducts.setText("80");
        jPanel6.add(totalProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setText("Total Products");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, -1, 20));

        jLabel37.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel37.setText("Total Orders");
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, 20));

        totalLoss.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalLoss.setText("80");
        jPanel6.add(totalLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, -1, -1));

        todaysDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        todaysDate.setForeground(new java.awt.Color(102, 102, 102));
        todaysDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        todaysDate.setText("Date");
        todaysDate.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(todaysDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 70, -1));

        tabs.addTab("tab1", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productsContainer.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_table.setAutoCreateRowSorter(true);
        product_table.setBackground(new java.awt.Color(204, 204, 204));
        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        product_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(product_table);

        productsContainer.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 750, 560));

        editButton.setBackground(new java.awt.Color(102, 102, 102));
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Edit");
        editButton.setBorderPainted(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        productsContainer.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 530, 130, 50));

        archive.setBackground(new java.awt.Color(255, 102, 102));
        archive.setForeground(new java.awt.Color(255, 255, 255));
        archive.setText("Archive");
        archive.setBorderPainted(false);
        archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveActionPerformed(evt);
            }
        });
        productsContainer.add(archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 530, 130, 50));

        salesProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesProfile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-shop-24.png"))); // NOI18N
        productsContainer.add(salesProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 40, 40));

        salesFN1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        salesFN1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salesFN1.setText("Shop Name");
        productsContainer.add(salesFN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 230, 20));
        productsContainer.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 270, 10));

        displayPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        productsContainer.add(displayPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 120, 110));

        name.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        name.setText("Product Name");
        productsContainer.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, 140, 30));

        price.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        price.setText("Price");
        price.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                priceCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, -1, 20));

        haha1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        haha1.setText("Stocks: ");
        haha1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                haha1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(haha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, -1, 20));

        stock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        stock.setForeground(new java.awt.Color(153, 153, 153));
        stock.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                stockCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, -1, 20));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel29.setText("Status: ");
        jLabel29.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel29CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, -1, 20));

        status.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        status.setForeground(new java.awt.Color(153, 153, 153));
        status.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                statusCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 150, -1, 20));

        jLabel26.setText("Description");
        jLabel26.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel26CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 210, -1, 30));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(null);

        description.setBorder(null);
        jScrollPane2.setViewportView(description);

        jPanel16.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 230));

        productsContainer.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 270, 250));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Category:");
        jLabel36.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel36CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, -1, 20));

        category.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        category.setForeground(new java.awt.Color(153, 153, 153));
        category.setText("Electronics");
        category.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                categoryCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productsContainer.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 170, -1, 20));

        jPanel8.add(productsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 1100, 600));

        searchbtn.setBackground(new java.awt.Color(0, 158, 226));
        searchbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 158, 226), 1, true));
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });
        jPanel8.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 60, 40));

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
        jPanel8.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 330, 40));

        jLabel17.setBackground(new java.awt.Color(0, 158, 226));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 158, 226));
        jLabel17.setText("Products");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

        add.setBackground(new java.awt.Color(122, 183, 147));
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add");
        add.setBorderPainted(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel8.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 110, 40));

        tabs.addTab("tab2", jPanel8);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        archiveAccountTableContainer.add(archiveAccountTableContainerScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 610, 560));

        productName.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        productName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productName.setText("PRODUCT NAME");
        archiveAccountTableContainer.add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 180, 40));

        productPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 70x70.png"))); // NOI18N
        archiveAccountTableContainer.add(productPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 70, 70));
        archiveAccountTableContainer.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 270, 20));

        productPrice.setForeground(new java.awt.Color(102, 102, 102));
        productPrice.setText("Status");
        archiveAccountTableContainer.add(productPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, 30));

        c6.setBackground(new java.awt.Color(255, 255, 255));
        c6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage18.setForeground(new java.awt.Color(102, 102, 102));
        manage18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-tag-woman-horizontal-24.png"))); // NOI18N
        c6.add(manage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productID.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        productID.setForeground(new java.awt.Color(102, 102, 102));
        c6.add(productID, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 270, 30));

        c7.setBackground(new java.awt.Color(255, 255, 255));
        c7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage24.setForeground(new java.awt.Color(102, 102, 102));
        manage24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-quantity-24.png"))); // NOI18N
        c7.add(manage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productQuantity.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        productQuantity.setForeground(new java.awt.Color(102, 102, 102));
        c7.add(productQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 270, 30));

        c8.setBackground(new java.awt.Color(255, 255, 255));
        c8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage25.setForeground(new java.awt.Color(102, 102, 102));
        manage25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-status-24.png"))); // NOI18N
        c8.add(manage25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productStatus.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        productStatus.setForeground(new java.awt.Color(102, 102, 102));
        c8.add(productStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        archiveAccountTableContainer.add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 270, 30));

        c9.setBackground(new java.awt.Color(255, 255, 255));
        c9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        descript.setBorder(null);
        jScrollPane1.setViewportView(descript);

        c9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 120));

        archiveAccountTableContainer.add(c9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 270, 120));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Archive Products Table");
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
        archiveAccountTableContainer.add(restore, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 550, 130, 50));

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
        archiveAccountTableContainer.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 550, 130, 50));

        jPanel2.add(archiveAccountTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 970, 630));

        tabs.addTab("tab3", jPanel2);

        s.setBackground(new java.awt.Color(255, 255, 255));
        s.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(241, 241, 241));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        purchase_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        purchase_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchase_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(purchase_table);

        jPanel12.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 770, 540));

        salesProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/defualt.png"))); // NOI18N
        jPanel12.add(salesProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 40, 40));

        salesFN.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        salesFN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salesFN.setText("FULL NAME");
        jPanel12.add(salesFN, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 30, 210, 20));

        salesAddress.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salesAddress.setForeground(new java.awt.Color(102, 102, 102));
        salesAddress.setText("ADDRESS");
        jPanel12.add(salesAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, -1, 30));

        accept_order.setBackground(new java.awt.Color(122, 183, 147));
        accept_order.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        accept_order.setForeground(new java.awt.Color(255, 255, 255));
        accept_order.setText("Accept");
        accept_order.setBorder(null);
        accept_order.setBorderPainted(false);
        accept_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accept_orderActionPerformed(evt);
            }
        });
        jPanel12.add(accept_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 520, 130, 50));

        decline.setBackground(new java.awt.Color(255, 102, 102));
        decline.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        decline.setForeground(new java.awt.Color(255, 255, 255));
        decline.setText("Decline");
        decline.setBorder(null);
        decline.setBorderPainted(false);
        decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineActionPerformed(evt);
            }
        });
        jPanel12.add(decline, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 520, 130, 50));

        salesPPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(salesPPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 120, 110));

        salesPN.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        salesPN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel12.add(salesPN, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 140, 40));

        salesTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salesTotal.setForeground(new java.awt.Color(0, 158, 226));
        jPanel12.add(salesTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, -1, 20));
        jPanel12.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 270, 10));

        salesPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salesPrice.setForeground(new java.awt.Color(102, 102, 102));
        jPanel12.add(salesPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 120, -1, 30));

        salesQuantity.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salesQuantity.setForeground(new java.awt.Color(102, 102, 102));
        jPanel12.add(salesQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 140, -1, 30));

        productPrice5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        productPrice5.setForeground(new java.awt.Color(102, 102, 102));
        jPanel12.add(productPrice5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 170, -1, 20));

        orderTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel12.add(orderTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 170, -1, 20));

        s.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 1110, 600));

        searchbtn1.setBackground(new java.awt.Color(0, 158, 226));
        searchbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchbtn1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 158, 226), 1, true));
        searchbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtn1ActionPerformed(evt);
            }
        });
        s.add(searchbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 60, 40));

        searchBar1.setForeground(new java.awt.Color(140, 140, 140));
        searchBar1.setText("  Search");
        searchBar1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBar1FocusLost(evt);
            }
        });
        searchBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchBar1MouseClicked(evt);
            }
        });
        searchBar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBar1KeyReleased(evt);
            }
        });
        s.add(searchBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 330, 40));

        jLabel28.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 158, 226));
        jLabel28.setText("SHOPTIFY");
        s.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 200, 30));

        tabs.addTab("tab4", s);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(235, 235, 235));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accounts_table.setAutoCreateRowSorter(true);
        accounts_table.setBackground(new java.awt.Color(204, 204, 204));
        accounts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(accounts_table);

        jPanel19.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 890, 530));

        jPanel4.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 930, 570));

        jLabel16.setBackground(new java.awt.Color(0, 158, 226));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 158, 226));
        jLabel16.setText("Buyer's Account");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

        searchbtn9.setBackground(new java.awt.Color(0, 158, 226));
        searchbtn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchbtn9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 158, 226), 1, true));
        jPanel4.add(searchbtn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 60, 40));

        edit.setBackground(new java.awt.Color(102, 102, 102));
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edi-icon-24.png"))); // NOI18N
        edit.setBorder(null);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel4.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 60, 40));

        searchbtn11.setBackground(new java.awt.Color(122, 183, 147));
        searchbtn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-plus-24.png"))); // NOI18N
        searchbtn11.setBorder(null);
        jPanel4.add(searchbtn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 60, 40));

        searchbtn12.setBackground(new java.awt.Color(255, 51, 51));
        searchbtn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        searchbtn12.setBorder(null);
        jPanel4.add(searchbtn12, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 60, 40));

        search3.setForeground(new java.awt.Color(102, 102, 102));
        search3.setText("  Search");
        jPanel4.add(search3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 300, 40));

        tabs.addTab("tab5", jPanel4);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage5.setForeground(new java.awt.Color(102, 102, 102));
        manage5.setText("Address");
        jPanel14.add(manage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 30));
        jPanel14.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 120, 40));
        jPanel14.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 120, 40));
        jPanel14.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 250, 40));

        manage6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage6.setForeground(new java.awt.Color(102, 102, 102));
        manage6.setText("Date joined");
        jPanel14.add(manage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, -1, 30));

        male.setText(" Male");
        male.setEnabled(false);
        male.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maleMouseClicked(evt);
            }
        });
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });
        jPanel14.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, 20));

        female.setSelected(true);
        female.setText(" Female");
        female.setEnabled(false);
        female.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                femaleMouseClicked(evt);
            }
        });
        jPanel14.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, 20));

        other.setText(" Other");
        other.setToolTipText("");
        other.setEnabled(false);
        other.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherMouseClicked(evt);
            }
        });
        jPanel14.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, 20));

        manage7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage7.setForeground(new java.awt.Color(102, 102, 102));
        manage7.setText("Last Name");
        jPanel14.add(manage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, -1, 30));

        myprofile1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        myprofile1.setText("Buyer's Account");
        jPanel14.add(myprofile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));

        manage1.setForeground(new java.awt.Color(102, 102, 102));
        manage1.setText("Manage buyer's account ");
        jPanel14.add(manage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        manage8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage8.setForeground(new java.awt.Color(102, 102, 102));
        manage8.setText("First Name");
        jPanel14.add(manage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        stats.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activated", "Inactive", "Pending" }));
        jPanel14.add(stats, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 240, 40));

        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jPanel14.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 240, 40));

        manage9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage9.setForeground(new java.awt.Color(102, 102, 102));
        manage9.setText("Gender");
        jPanel14.add(manage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, 30));

        manage10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage10.setForeground(new java.awt.Color(102, 102, 102));
        manage10.setText("Role");
        jPanel14.add(manage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, 30));

        submit.setBackground(new java.awt.Color(0, 158, 226));
        submit.setForeground(new java.awt.Color(255, 255, 255));
        submit.setText("Save");
        submit.setBorderPainted(false);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        jPanel14.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 250, 40));
        jPanel14.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 830, 20));

        selectFile.setText("Select Image");
        jPanel14.add(selectFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 120, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("File size: maximum 1 MB ");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 380, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("File extension: .JPEG, .PNG ");
        jPanel14.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, -1, -1));

        username.setEditable(false);
        jPanel14.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 250, 40));

        manage11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage11.setForeground(new java.awt.Color(102, 102, 102));
        manage11.setText("Username");
        jPanel14.add(manage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 30));

        manage12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage12.setForeground(new java.awt.Color(102, 102, 102));
        manage12.setText("Email");
        jPanel14.add(manage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 30));
        jPanel14.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 250, 40));

        manage13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage13.setForeground(new java.awt.Color(102, 102, 102));
        manage13.setText("Buyer ID");
        jPanel14.add(manage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 30));

        ID.setEditable(false);
        jPanel14.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 250, 40));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel14.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 30, 420));
        jPanel14.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 830, 20));

        manage14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage14.setForeground(new java.awt.Color(102, 102, 102));
        manage14.setText("Status");
        jPanel14.add(manage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, 30));
        jPanel14.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 250, 40));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 120, 110));

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 920, 590));

        tabs.addTab("tab6", jPanel11);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addSave.setBackground(new java.awt.Color(0, 158, 226));
        addSave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addSave.setForeground(new java.awt.Color(255, 255, 255));
        addSave.setText("Save");
        addSave.setBorderPainted(false);
        addSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSaveActionPerformed(evt);
            }
        });
        jPanel9.add(addSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 460, 270, 40));

        addPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Your paragraph text.png"))); // NOI18N
        addPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        addPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPhotoMouseClicked(evt);
            }
        });
        jPanel9.add(addPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 290, 270));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setText("Description");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, -1, -1));

        jPanel18.setBackground(new java.awt.Color(241, 241, 241));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addName.setBackground(new java.awt.Color(241, 241, 241));
        addName.setBorder(null);
        jPanel18.add(addName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        jPanel9.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 280, 40));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setText("Price");
        jPanel9.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, -1, -1));

        jPanel24.setBackground(new java.awt.Color(241, 241, 241));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addPrice.setBackground(new java.awt.Color(241, 241, 241));
        addPrice.setBorder(null);
        jPanel24.add(addPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        jPanel9.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 280, 40));

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setText("Category");
        jPanel9.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 230, -1, -1));

        jPanel25.setBackground(new java.awt.Color(241, 241, 241));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addStock.setBackground(new java.awt.Color(241, 241, 241));
        addStock.setBorder(null);
        jPanel25.add(addStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        jPanel9.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, 280, 40));

        jLabel30.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel30.setText("Stock");
        jPanel9.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, -1, -1));

        jPanel26.setBackground(new java.awt.Color(241, 241, 241));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addStatus.setBackground(new java.awt.Color(241, 241, 241));
        addStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        addStatus.setBorder(null);
        jPanel26.add(addStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        jPanel9.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 280, 40));

        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setBorder(null);

        addDescription.setBackground(new java.awt.Color(241, 241, 241));
        addDescription.setBorder(null);
        jScrollPane8.setViewportView(addDescription);

        jPanel27.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 250, 90));

        jPanel9.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 270, 110));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel31.setText("Add Product");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, 30));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel32.setText("SHOPTIFY");
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        salesProfile3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesProfile3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-shop-24.png"))); // NOI18N
        jPanel9.add(salesProfile3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 40, 40));

        salesFN3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        salesFN3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salesFN3.setText("Shop Name");
        jPanel9.add(salesFN3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 220, 20));
        jPanel9.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 940, 10));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel33.setText("Product Name");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, -1, -1));

        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addCategory.setBackground(new java.awt.Color(241, 241, 241));
        addCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Fashion", "Grocery", "Pet Supplies ", " " }));
        addCategory.setBorder(null);
        jPanel29.add(addCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 40));

        jPanel9.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 270, 40));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel34.setText("Status");
        jPanel9.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, -1, -1));

        tabs.addTab("tab7", jPanel9);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(241, 241, 241));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getStatus.setBackground(new java.awt.Color(241, 241, 241));
        getStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        getStatus.setBorder(null);
        jPanel13.add(getStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 280, 40));

        jPanel17.setBackground(new java.awt.Color(241, 241, 241));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getName.setBackground(new java.awt.Color(241, 241, 241));
        getName.setBorder(null);
        jPanel17.add(getName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        jPanel3.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 280, 40));

        jPanel21.setBackground(new java.awt.Color(241, 241, 241));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getPrice.setBackground(new java.awt.Color(241, 241, 241));
        getPrice.setBorder(null);
        jPanel21.add(getPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 280, 40));

        jPanel22.setBackground(new java.awt.Color(241, 241, 241));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getStock.setBackground(new java.awt.Color(241, 241, 241));
        getStock.setBorder(null);
        jPanel22.add(getStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        jPanel3.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, 280, 40));

        editsave.setBackground(new java.awt.Color(0, 158, 226));
        editsave.setForeground(new java.awt.Color(255, 255, 255));
        editsave.setText("Save");
        editsave.setBorderPainted(false);
        editsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editsaveActionPerformed(evt);
            }
        });
        jPanel3.add(editsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 460, 270, 40));

        jPanel23.setBackground(new java.awt.Color(241, 241, 241));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Click the photo to edit");
        jPanel23.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 290, -1));

        getPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Your paragraph text.png"))); // NOI18N
        getPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        getPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getPhotoMouseClicked(evt);
            }
        });
        jPanel23.add(getPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 270));

        jPanel3.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 290, 270));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Status");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Description");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Price");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Stock");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("SHOPTIFY");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel15.setText("Edit Product");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, 30));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Product Name");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, -1, -1));

        salesProfile2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesProfile2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-shop-24.png"))); // NOI18N
        jPanel3.add(salesProfile2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 40, 40));

        salesFN2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        salesFN2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salesFN2.setText("Shop Name");
        jPanel3.add(salesFN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 220, 20));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 940, 10));

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(null);

        getDescription.setBackground(new java.awt.Color(241, 241, 241));
        getDescription.setBorder(null);
        jScrollPane4.setViewportView(getDescription);

        jPanel15.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 250, 90));

        jPanel3.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 270, 110));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel35.setText("Category");
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 230, -1, -1));

        getCategory.setBackground(new java.awt.Color(241, 241, 241));
        getCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Fashion", "Grocery", "Pet Supplies ", " " }));
        getCategory.setBorder(null);
        jPanel3.add(getCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 270, 40));

        tabs.addTab("tab8", jPanel3);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("activity logs");
        jPanel28.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, -1, -1));

        tabs.addTab("tab9", jPanel28);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 700));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1180, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_dashboardActionPerformed

    private void manageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageActionPerformed
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_manageActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        Login out = new Login();
        out.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void ordersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersActionPerformed
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_ordersActionPerformed

    private void statusCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_statusCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_statusCaretPositionChanged

    private void jLabel26CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel26CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel26CaretPositionChanged

    private void stockCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_stockCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_stockCaretPositionChanged
    String fileName;
    String imagePath;
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        tabs.setSelectedIndex(6);
    }//GEN-LAST:event_addActionPerformed

    File selectedFile;

    private void priceCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_priceCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_priceCaretPositionChanged

    private void accountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountsActionPerformed
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_accountsActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String accountStatus = (String) stats.getSelectedItem();
            String accountRole = (String) role.getSelectedItem();

            String sql = "UPDATE accounts_table SET role=?, status=?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setString(1, accountRole);
                pst.setString(2, accountStatus);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                    tabs.setSelectedIndex(4);
                    displayAccounts();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update data!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_submitActionPerformed

    private void otherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherMouseClicked
        male.setSelected(false);
        female.setSelected(false);
        other.setSelected(true);
        String gender = "Other";
    }//GEN-LAST:event_otherMouseClicked

    private void femaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_femaleMouseClicked
        male.setSelected(false);
        female.setSelected(true);
        other.setSelected(false);
        String gender = "Female";
    }//GEN-LAST:event_femaleMouseClicked

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleActionPerformed

    private void maleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maleMouseClicked
        male.setSelected(true);
        female.setSelected(false);
        other.setSelected(false);
        String gender = "Male";
    }//GEN-LAST:event_maleMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        tabs.setSelectedIndex(5);

        int rowIndex = accounts_table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = accounts_table.getModel();
            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM accounts_table WHERE account_id =" + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    ID.setText("" + rs.getString("account_id"));
                    fname.setText("" + rs.getString("fname"));
                    lname.setText("" + rs.getString("lname"));
                    email.setText("" + rs.getString("email"));
                    address.setText(rs.getString("address"));
                    username.setText(rs.getString("username"));
                    role.setSelectedItem(rs.getString("role"));
                    int height = 120;
                    int width = 110;
                    String getImageFromDatabase = rs.getString("profile_picture");
                    GetImage.displayImage(display, getImageFromDatabase, height, width);
                    date.setText(rs.getString("date joined"));
                    stats.setSelectedItem(rs.getString("status"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        search.searchResult(product_table, searchBar);
    }//GEN-LAST:event_searchBarKeyReleased

    private void searchBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBarMouseClicked
        searchBar.setFocusable(true);
        searchBar.requestFocusInWindow();
        if (searchBar.getText().isEmpty() || searchBar.getText().equals("  Search")) {
            searchBar.setText("");
            searchBar.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_searchBarMouseClicked

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbtnActionPerformed

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().isEmpty()) {
            searchBar.setText("  Search");
            searchBar.setForeground(Color.decode("#8C8C8C"));
        }
    }//GEN-LAST:event_searchBarFocusLost

    private void archive_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archive_tableMouseClicked
        int rowIndex = archive_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = archive_table.getModel();

            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    productID.setText("" + rs.getString("product_id"));
                    productName.setText("" + rs.getString("product_name"));
                    productQuantity.setText("" + rs.getString("stock"));
                    productStatus.setText("" + rs.getString("status"));
                    descript.setText("" + rs.getString("description"));

                    int height = 70;
                    int width = 70;
                    String getImageFromDatabase = rs.getString("image_path");
                    GetImage.displayImage(productPhoto, getImageFromDatabase, height, width);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_archive_tableMouseClicked

    private void restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            int p_id = Integer.parseInt(productID.getText());
            String sql = "UPDATE tbl_products SET `status`='Available' WHERE `product_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, p_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Product has been retored Successfully!");
                    displayData();
                    displayArchive();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to restore Product!");
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_restoreActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int rowIndex = archive_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a product first");
        } else {
            TableModel model = archive_table.getModel();
            Object value = model.getValueAt(rowIndex, 0);
            String id = value.toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                databaseConnector dbc = new databaseConnector();
                dbc.deleteProduct(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, "Product deleted successfully!");
                displayData();
                displayArchive();
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void archiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql = "UPDATE tbl_products SET `Status`='Archived' WHERE `product_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, p_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Product added to archive Successfully!");
                    displayData();
                    displayArchive();
                    displayTotalProducts(sellerID);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add the product to archive!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_archiveActionPerformed

    private void archivebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivebtnActionPerformed
        tabs.setSelectedIndex(2);
    }//GEN-LAST:event_archivebtnActionPerformed

    private static int p_id;

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked

        int rowIndex = product_table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = product_table.getModel();
            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    p_id = rs.getInt("product_id");
                    name.setText("" + rs.getString("product_name"));
                    price.setText("₱  " + rs.getString("price"));
                    stock.setText(rs.getString("stock"));
                    description.setText(rs.getString("description"));
                    status.setText(rs.getString("status"));
                    category.setText(rs.getString("category"));

                    int height = 120;
                    int width = 110;
                    String getImageFromDatabase = rs.getString("image_path");
                    GetImage.displayImage(displayPhoto, getImageFromDatabase, height, width);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void addSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSaveActionPerformed
        String valname = addName.getText();
        String valprice = addPrice.getText();
        String valstocks = addStock.getText();
        String valdes = addDescription.getText();
        String valstats = (String) addStatus.getSelectedItem();
        String valcategory = (String) addCategory.getSelectedItem();

        if (valname.isEmpty() || valprice.isEmpty() || valstocks.isEmpty() || selectedFile == null || !selectedFile.exists()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields and select an image.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        fileName = selectedFile.getName();
        imagePath = "src/ProductsImages/" + fileName;

        try {
            databaseConnector dbc = new databaseConnector();

            String checkQuery = "SELECT COUNT(*) FROM tbl_products WHERE product_name = ?";
            PreparedStatement checkStmt = dbc.getConnection().prepareStatement(checkQuery);
            checkStmt.setString(1, valname);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Product already exist!");
                return;
            }

            String insertQuery = "INSERT INTO tbl_products (`seller_id`, `product_name`, `price`, `stock`, `description`, `status`, `image_path`, `category`, `date_created`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURDATE())";
            try (PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setInt(1, sellerID);
                insertStmt.setString(2, valname);
                insertStmt.setString(3, valprice);
                insertStmt.setString(4, valstocks);
                insertStmt.setString(5, valdes);
                insertStmt.setString(6, valstats);
                insertStmt.setString(7, imagePath);
                insertStmt.setString(7, valcategory);

                insertStmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Product added successfully!");
            addName.setText("");
            addPrice.setText("");
            addStock.setText("");
            addStatus.setSelectedIndex(0);
            addDescription.setText("");
            ImageIcon setPhoto = new ImageIcon(getClass().getResource("/image/Your paragraph text.png"));
            addPhoto.setIcon(setPhoto);
            displayTotalProducts(sellerID);
            displayData();
            tabs.setSelectedIndex(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error adding product!" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_addSaveActionPerformed

    private void searchBar1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBar1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBar1FocusLost

    private void searchBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBar1MouseClicked

    private void searchBar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBar1KeyReleased

    private void searchbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbtn1ActionPerformed

    private void accept_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accept_orderActionPerformed
        newStock = product_stock - total_quantity;
        int rowIndex = purchase_table.getSelectedRow();

        try {
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "Please select a product first");
            } else if (order_status.equals("Accepted")) {
                JOptionPane.showMessageDialog(null, "Product is already accepted!");
            } else {
                databaseConnector dbc = new databaseConnector();
                String sql = "UPDATE tbl_sales SET `order_status`='Accepted' WHERE `transaction_id`=?";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setInt(1, transaction_id);
                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        updateStock(newStock, product_id);
                        if (newStock < 1) {
                            updateStatus("Not Available");
                        }
                        updateProductSold(total_quantity, product_id, true); // Add quantity sold
                        JOptionPane.showMessageDialog(null, "Order has been accepted Successfully!");
                        displayPurchase();
                        newStock = 0;

                        // Clear
                        transaction_id = 0;
                        buyer_id = 0;
                        product_id = 0;
                        salesPN.setText("");
                        salesPrice.setText("");
                        salesTotal.setText("");
                        salesQuantity.setText("");
                        orderTotal.setText("");
                        salesPPhoto.setIcon(null);
                        ImageIcon profilePicture = new ImageIcon(getClass().getResource("/sampleProfiles/defualt.png"));
                        salesProfile.setIcon(profilePicture);
                        displayTotalSales(sellerID);
                        displayTotalPendingOrders(sellerID);
                        displayTotalOrders(sellerID);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_accept_orderActionPerformed

    private void updateProductSold(int quantitySold, int productId, boolean isAccepted) {
        databaseConnector dbc = new databaseConnector();
        String sql = "UPDATE tbl_products SET `sold` = `sold` + ? WHERE `product_id` = ?";
        try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
            pst.setInt(1, isAccepted ? quantitySold : -quantitySold); // Add or subtract based on action
            pst.setInt(2, productId);
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating product sold data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int product_stock;
    private static int total_quantity;
    private static int newStock = 0;

    public static void getStock() {
        try {

            databaseConnector dbc = new databaseConnector();

            String query = "SELECT stock FROM tbl_products WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product_stock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateStock(int newStock, int productId) {
        try {

            databaseConnector dbc = new databaseConnector();

            String updateQuery = "UPDATE tbl_products SET stock = ? WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setInt(1, newStock);
            pst.setInt(2, product_id);
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateStatus(String status) {
        try {
            databaseConnector dbc = new databaseConnector();

            String updateQuery = "UPDATE tbl_products SET status = ? WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, status);
            pst.setInt(2, product_id);
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineActionPerformed
        int rowIndex = purchase_table.getSelectedRow();

        try {
            newStock = total_quantity + product_stock;
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "Please select a product first");
            } else if (order_status.equals("Declined")) {
                JOptionPane.showMessageDialog(null, "Product is already declined!");
            } else {
                databaseConnector dbc = new databaseConnector();
                String sql = "UPDATE tbl_sales SET `order_status`='Declined' WHERE `transaction_id`=?";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setInt(1, transaction_id);
                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        updateStock(newStock, product_id);
                        if (newStock > 0) {
                            updateStatus("Available");
                        }
                        updateProductSold(total_quantity, product_id, false); // Subtract quantity sold
                        JOptionPane.showMessageDialog(null, "Order has been declined Successfully!");
                        displayPurchase();
                        newStock = 0;

                        // Clear
                        transaction_id = 0;
                        buyer_id = 0;
                        product_id = 0;
                        salesPN.setText("");
                        salesPrice.setText("");
                        salesTotal.setText("");
                        salesQuantity.setText("");
                        orderTotal.setText("");
                        salesPPhoto.setIcon(null);
                        ImageIcon defaultProfile = new ImageIcon(getClass().getResource("/sampleProfiles/defualt.png"));
                        salesProfile.setIcon(defaultProfile);
                        displayTotalSales(sellerID);
                        displayTotalPendingOrders(sellerID);
                        displayTotalOrders(sellerID);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_declineActionPerformed

    private static int buyer_id;
    private static int product_id;
    private static int transaction_id;
    private static String order_status;

    public static void displayProductPhoto(JLabel photo) {
        try {

            databaseConnector dbc = new databaseConnector();

            String query = "SELECT image_path FROM tbl_products WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String getImageFromDatabase = rs.getString("image_path");
                int height = 120;
                int width = 110;
                GetImage.displayImage(photo, getImageFromDatabase, height, width);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void displaySalesInfo(JLabel fullname, JLabel profile) {
        try {

            databaseConnector dbc = new databaseConnector();

            String query = "SELECT fname, lname, profile_picture FROM accounts_table WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, buyer_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String getImageFromDatabase = rs.getString("profile_picture");

                firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);

                fullname.setText(firstName + " " + lastName);
                int height = 40;
                int width = 40;
                GetImage.displayImage(profile, getImageFromDatabase, height, width);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void purchase_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchase_tableMouseClicked
        newStock = 0;
        int rowIndex = purchase_table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = purchase_table.getModel();
            try {
                databaseConnector dbc = new databaseConnector();
                String query = "SELECT address FROM accounts_table WHERE account_id = ?";
                PreparedStatement pst = dbc.getConnection().prepareStatement(query);
                pst.setInt(1, accountId);
                ResultSet rs = dbc.getData("SELECT * FROM tbl_sales WHERE transaction_id =" + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    transaction_id = rs.getInt("transaction_id");
                    buyer_id = rs.getInt("buyer_id");
                    product_id = rs.getInt("product_id");
                    salesPN.setText("" + rs.getString("product_name"));
                    salesAddress.setText("" + rs.getString("address"));
                    salesPrice.setText("₱  " + rs.getString("product_price"));
                    salesQuantity.setText("x " + rs.getString("total_quantity"));
                    salesTotal.setText("₱  " + rs.getString("total_price"));
                    orderTotal.setText("Order Total:");
                    displaySalesInfo(salesFN, salesProfile);
                    displayProductPhoto(salesPPhoto);

                    //get the propduct stock for decline
                    getStock();
                    //get the total quantity for decline
                    total_quantity = rs.getInt("total_quantity");
                    order_status = rs.getString("order_status");
                    System.out.println(total_quantity);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_purchase_tableMouseClicked

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_profileMouseClicked

    private void haha1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_haha1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_haha1CaretPositionChanged

    private void jLabel29CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel29CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel29CaretPositionChanged

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        try {
            int rowIndex = product_table.getSelectedRow();
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "Please Select an Item!");
            } else {
                TableModel model = product_table.getModel();
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id = " + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    int height = 290;
                    int width = 270;

                    getName.setText(rs.getString("product_name"));
                    getPrice.setText(rs.getString("price"));
                    getStock.setText(rs.getString("stock"));
                    getDescription.setText(rs.getString("description"));
                    getStatus.setSelectedItem(rs.getString("status"));
                    String getImageFromDatabase = rs.getString("image_path");
                    GetImage.displayImage(getPhoto, getImageFromDatabase, height, width);
                    tabs.setSelectedIndex(7);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_editButtonActionPerformed

    private void editsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editsaveActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String productName = getName.getText();
            String productPrice = getPrice.getText();
            String productStocks = getStock.getText();
            String productDescription = getDescription.getText();
            String productStatus = (String) getStatus.getSelectedItem();
            String productCategory = (String) getCategory.getSelectedItem();

            if (selectedFile != null) {
                fileName = selectedFile.getName();
                imagePath = "src/ProductsImages/" + fileName;

                sql = "UPDATE tbl_products SET product_name=?, price=?, stock=?, description=?, image_path=?, status=?, category=? WHERE product_id=?";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setString(1, productName);
                    pst.setString(2, productPrice);
                    pst.setString(3, productStocks);
                    pst.setString(4, productDescription);
                    pst.setString(5, imagePath);
                    pst.setString(6, productStatus);
                    pst.setString(7, productCategory);
                    pst.setInt(8, p_id);

                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                        displayData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update data!");
                    }
                }
            } else {
                sql = "UPDATE tbl_products SET product_name=?, price=?, stock=?, description=?, status=?, category=? WHERE product_id=?";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setString(1, productName);
                    pst.setString(2, productPrice);
                    pst.setString(3, productStocks);
                    pst.setString(4, productDescription);
                    pst.setString(5, productStatus);
                    pst.setString(6, productCategory);
                    pst.setInt(7, p_id);

                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                        displayData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update data!");
                    }
                }
            }
            getName.setText("");
            getPrice.setText("");
            getStock.setText("");
            getStatus.setSelectedIndex(0);
            getDescription.setText("");
            ImageIcon setPhoto = new ImageIcon(getClass().getResource("/image/Your paragraph text.png"));
            getPhoto.setIcon(setPhoto);
            p_id = 0;
            displayTotalProducts(sellerID);
            displayData();
            tabs.setSelectedIndex(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_editsaveActionPerformed

    private void getPhoto(JLabel photo) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                Image resizedImage = originalImage.getScaledInstance(290, 270, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                photo.setIcon(icon);

                String imageName = selectedFile.getName();
                String imagePath = "src/ProductsImages/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Update the selectedFile to point to the new location
                selectedFile = destination;

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void getPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getPhotoMouseClicked
        getPhoto(getPhoto);
    }//GEN-LAST:event_getPhotoMouseClicked

    private void addPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPhotoMouseClicked
        getPhoto(addPhoto);
    }//GEN-LAST:event_addPhotoMouseClicked

    private void logsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsButtonActionPerformed
        tabs.setSelectedIndex(8);
    }//GEN-LAST:event_logsButtonActionPerformed

    private void jLabel36CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel36CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel36CaretPositionChanged

    private void categoryCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_categoryCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryCaretPositionChanged

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new sellerDashboard().setVisible(true);
                }
            });

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(sellerDashboard.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CONTAINER;
    private javax.swing.JPanel CONTAINER1;
    private javax.swing.JPanel CONTAINER2;
    private javax.swing.JPanel CONTAINER3;
    private javax.swing.JPanel CONTAINER5;
    private javax.swing.JTextField ID;
    private javax.swing.JButton accept_order;
    private javax.swing.JButton accounts;
    private javax.swing.JTable accounts_table;
    private javax.swing.JButton add;
    private javax.swing.JComboBox<String> addCategory;
    private javax.swing.JEditorPane addDescription;
    private javax.swing.JTextField addName;
    private javax.swing.JLabel addPhoto;
    private javax.swing.JTextField addPrice;
    private javax.swing.JButton addSave;
    private javax.swing.JComboBox<String> addStatus;
    private javax.swing.JTextField addStock;
    private javax.swing.JTextField address;
    private javax.swing.JButton archive;
    private javax.swing.JPanel archiveAccountTableContainer;
    private javax.swing.JScrollPane archiveAccountTableContainerScroll;
    private javax.swing.JTable archive_table;
    private javax.swing.JButton archivebtn;
    private javax.swing.JPanel c6;
    private javax.swing.JPanel c7;
    private javax.swing.JPanel c8;
    private javax.swing.JPanel c9;
    private javax.swing.JLabel category;
    private javax.swing.JLabel containersds;
    private javax.swing.JButton dashboard;
    private javax.swing.JPanel dashboardContainer;
    private javax.swing.JTextField date;
    private javax.swing.JButton decline;
    private javax.swing.JButton delete;
    private javax.swing.JEditorPane descript;
    private javax.swing.JEditorPane description;
    private javax.swing.JLabel display;
    private javax.swing.JLabel displayPhoto;
    private javax.swing.JButton edit;
    private javax.swing.JButton editButton;
    private javax.swing.JButton editsave;
    private javax.swing.JTextField email;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField fname;
    private javax.swing.JComboBox<String> getCategory;
    private javax.swing.JEditorPane getDescription;
    private javax.swing.JTextField getName;
    private javax.swing.JLabel getPhoto;
    private javax.swing.JTextField getPrice;
    private javax.swing.JComboBox<String> getStatus;
    private javax.swing.JTextField getStock;
    private javax.swing.JLabel haha1;
    private javax.swing.JLabel helloSeller;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField lname;
    private javax.swing.JButton logout;
    private javax.swing.JButton logsButton;
    private javax.swing.JRadioButton male;
    private javax.swing.JButton manage;
    private javax.swing.JLabel manage1;
    private javax.swing.JLabel manage10;
    private javax.swing.JLabel manage11;
    private javax.swing.JLabel manage12;
    private javax.swing.JLabel manage13;
    private javax.swing.JLabel manage14;
    private javax.swing.JLabel manage18;
    private javax.swing.JLabel manage24;
    private javax.swing.JLabel manage25;
    private javax.swing.JLabel manage5;
    private javax.swing.JLabel manage6;
    private javax.swing.JLabel manage7;
    private javax.swing.JLabel manage8;
    private javax.swing.JLabel manage9;
    private javax.swing.JLabel myprofile1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel orderTotal;
    private javax.swing.JButton orders;
    private javax.swing.JRadioButton other;
    private javax.swing.JLabel pendingOrders;
    private javax.swing.JLabel price;
    private javax.swing.JLabel productID;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPhoto;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productPrice5;
    private javax.swing.JLabel productQuantity;
    private javax.swing.JLabel productStatus;
    private javax.swing.JTable product_table;
    private javax.swing.JPanel productsContainer;
    private javax.swing.JLabel profile;
    private javax.swing.JTable purchase_table;
    private javax.swing.JButton restore;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JPanel s;
    private javax.swing.JLabel salesAddress;
    private javax.swing.JLabel salesFN;
    private javax.swing.JLabel salesFN1;
    private javax.swing.JLabel salesFN2;
    private javax.swing.JLabel salesFN3;
    private javax.swing.JLabel salesPN;
    private javax.swing.JLabel salesPPhoto;
    private javax.swing.JLabel salesPrice;
    private javax.swing.JLabel salesProfile;
    private javax.swing.JLabel salesProfile1;
    private javax.swing.JLabel salesProfile2;
    private javax.swing.JLabel salesProfile3;
    private javax.swing.JLabel salesQuantity;
    private javax.swing.JLabel salesTotal;
    private javax.swing.JTextField search3;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTextField searchBar1;
    private javax.swing.JButton searchbtn;
    private javax.swing.JButton searchbtn1;
    private javax.swing.JButton searchbtn11;
    private javax.swing.JButton searchbtn12;
    private javax.swing.JButton searchbtn9;
    private javax.swing.JButton selectFile;
    private javax.swing.JComboBox<String> stats;
    private javax.swing.JLabel status;
    private javax.swing.JLabel stock;
    private javax.swing.JButton submit;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel todaysDate;
    private javax.swing.JLabel totalLoss;
    private javax.swing.JLabel totalOrders;
    private javax.swing.JLabel totalProducts;
    private javax.swing.JLabel totalSales;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
