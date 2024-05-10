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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class sellerDashboard extends javax.swing.JFrame {

    public sellerDashboard() {
        initComponents();
        displayData();
        displayArchive();
        displayProducts();
        displayAccounts();

        //Buttons
        UXmethods.RoundBorders.setArcStyle(logout, 50);
        UXmethods.RoundBorders.setArcStyle(dashboard, 50);

        UXmethods.RoundBorders.setArcStyle(manage, 50);
        UXmethods.RoundBorders.setArcStyle(accounts, 50);
        UXmethods.RoundBorders.setArcStyle(orders, 50);
        UXmethods.RoundBorders.setArcStyle(archivebtn, 50);

        //Containers
        UXmethods.RoundBorders.setArcStyle(dashboardContainer, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER, 15);

    }

    private void displayProducts() {
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

    public void displayData() {
        try {
            databaseConnector dbc = new databaseConnector();
            ResultSet rs = dbc.getData("SELECT `product_id` as `Product ID`, `account_id` as `Account ID`, `Product Name`, `Price`, `Stock`, `Status`, `Date Created` FROM products WHERE Status IN ('Available', 'Not Available')");
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
            column = product_table.getColumnModel().getColumn(5); //descriptionm
            column.setPreferredWidth(20);
            column = product_table.getColumnModel().getColumn(6);
            column.setPreferredWidth(20);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            product_table.setDefaultRenderer(Object.class, centerRenderer);

            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayArchive() {
        try {
            databaseConnector dbc = new databaseConnector();
            ResultSet rs = dbc.getData("SELECT * FROM products WHERE Status IN ('archived')");
            archive_table.setModel(DbUtils.resultSetToTableModel(rs));
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        totalSales = new javax.swing.JLabel();
        CONTAINER = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        searchbtn = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        archive = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        stocks = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JEditorPane();
        displayPhoto = new javax.swing.JLabel();
        importt = new javax.swing.JButton();
        image_container = new javax.swing.JTextField();
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

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

        jPanel5.add(dashboardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 70, 680));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 50));

        tabs.setBackground(new java.awt.Color(153, 153, 153));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Great job today! You've done exceptionally well.");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 580, 20));

        helloSeller.setFont(new java.awt.Font("Gloucester MT Extra Condensed", 1, 40)); // NOI18N
        helloSeller.setText("Hi, Seller!");
        jPanel6.add(helloSeller, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/try.png"))); // NOI18N
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, -10, 1120, 270));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Overview");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 158, 226));
        jLabel20.setText("Dashboard");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 40));

        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Monday: 5/10/2024");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 160, -1));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 70));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("₱  19,920");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Sales");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, 20));

        totalSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalsales.png"))); // NOI18N
        jPanel6.add(totalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 280, 80));

        CONTAINER.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(CONTAINER, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 1110, 250));

        tabs.addTab("tab1", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(241, 241, 241));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel17.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 690, 560));

        jPanel8.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 730, 600));

        searchbtn.setBackground(new java.awt.Color(0, 158, 226));
        searchbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 158, 226), 1, true));
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });
        jPanel8.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 60, 40));

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
        jPanel8.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 330, 40));

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
        jPanel8.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 110, 40));

        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });
        jPanel8.add(savebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 140, 40));

        archive.setBackground(new java.awt.Color(255, 102, 102));
        archive.setForeground(new java.awt.Color(255, 255, 255));
        archive.setText("Archive");
        archive.setBorderPainted(false);
        archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveActionPerformed(evt);
            }
        });
        jPanel8.add(archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 580, 130, 50));

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Edit");
        jButton2.setBorderPainted(false);
        jPanel8.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, 130, 50));

        jLabel22.setText("Product ID");
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 350, -1, 30));

        id.setEnabled(false);
        jPanel8.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 350, 160, 30));

        jLabel21.setText("Product Name");
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 130, -1, 30));
        jPanel8.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 130, 120, 30));

        jLabel23.setText("Price");
        jLabel23.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel23CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 170, -1, 30));

        jLabel24.setText("Stocks");
        jLabel24.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel24CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 210, -1, 30));
        jPanel8.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, 120, 30));
        jPanel8.add(stocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 210, 120, 30));

        jLabel27.setText("Status");
        jLabel27.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel27CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 390, -1, 30));

        jLabel26.setText("Description");
        jLabel26.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel26CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 420, -1, 30));

        status.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        jPanel8.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 390, 160, 30));

        jScrollPane2.setViewportView(description);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 460, 260, 100));

        displayPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(displayPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 120, 110));

        importt.setText("Import");
        importt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importtActionPerformed(evt);
            }
        });
        jPanel8.add(importt, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 120, 30));
        jPanel8.add(image_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 120, 110));

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

        jButton1.setBackground(new java.awt.Color(102, 204, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 550, -1, 40));

        jLabel1.setText("add account");
        jPanel9.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        tabs.addTab("tab7", jPanel9);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Edit proructs");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 94, -1, -1));

        tabs.addTab("tab8", jPanel3);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 720));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1180, 720));

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
        tabs.setSelectedIndex(2);
    }//GEN-LAST:event_ordersActionPerformed

    private void jLabel27CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel27CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27CaretPositionChanged

    private void jLabel26CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel26CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel26CaretPositionChanged

    private void jLabel24CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel24CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24CaretPositionChanged
    String fileName;
    String imagePath;
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        tabs.setSelectedIndex(6);
    }//GEN-LAST:event_addActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String productName = name.getText();
            String productPrice = price.getText();
            String productStocks = stocks.getText();
            String productDescription = description.getText();
            String productStatus = (String) status.getSelectedItem();
            String productId = id.getText();

            if (selectedFile != null) {
                fileName = selectedFile.getName();
                imagePath = "src/ProductsImages/" + fileName;

                sql = "UPDATE products SET `Product Name`=?, Price=?, Stock=?, Description=?, ImagePath=?, Status=? WHERE product_id=?";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setString(1, productName);
                    pst.setString(2, productPrice);
                    pst.setString(3, productStocks);
                    pst.setString(4, productDescription);
                    pst.setString(5, imagePath);
                    pst.setString(6, productStatus);
                    pst.setString(7, productId);

                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                        displayData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update data!");
                    }
                }
            } else {
                sql = "UPDATE products SET `Product Name`=?, Price=?, Stock=?, Description=?, Status=? WHERE product_id=?";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setString(1, productName);
                    pst.setString(2, productPrice);
                    pst.setString(3, productStocks);
                    pst.setString(4, productDescription);
                    pst.setString(5, productStatus);
                    pst.setString(6, productId);

                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                        displayData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update data!");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_savebtnActionPerformed

    File selectedFile;

    private void importtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importtActionPerformed
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
                displayPhoto.setIcon(icon);

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
    }//GEN-LAST:event_importtActionPerformed

    private void jLabel23CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel23CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23CaretPositionChanged

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
                ResultSet rs = dbc.getData("SELECT * FROM products WHERE product_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    productID.setText("" + rs.getString("product_id"));
                    productName.setText("" + rs.getString("Product Name"));
                    productQuantity.setText("" + rs.getString("Stock"));
                    productStatus.setText("" + rs.getString("Status"));
                    descript.setText("" + rs.getString("Description"));

                    int height = 70;
                    int width = 70;
                    String getImageFromDatabase = rs.getString("ImagePath");
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
            String sql = "UPDATE products SET `Status`='Available' WHERE `product_id`=?";

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
            int prod_id = Integer.parseInt(id.getText());
            String sql = "UPDATE products SET `Status`='archived' WHERE `product_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, prod_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Product added to archive Successfully!");
                    displayData();
                    displayArchive();
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

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
        int rowIndex = product_table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = product_table.getModel();
            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM products WHERE product_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    id.setText("" + rs.getString("product_id"));
                    name.setText("" + rs.getString("Product Name"));
                    price.setText("" + rs.getString("Price"));
                    stocks.setText(rs.getString("Stock"));
                    description.setText(rs.getString("Description"));
                    status.setSelectedItem(rs.getString("Status"));

                    int height = 120;
                    int width = 110;
                    String getImageFromDatabase = rs.getString("ImagePath");
                    GetImage.displayImage(displayPhoto, getImageFromDatabase, height, width);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UserManager.setLoggedInUserId(accountId);
        int adminID = accountId;
        String valname = name.getText();
        String valprice = price.getText();
        String valstocks = stocks.getText();
        String valdes = description.getText();
        String valstats = (String) status.getSelectedItem();

        if (valname.isEmpty() || valprice.isEmpty() || valstocks.isEmpty() || selectedFile == null || !selectedFile.exists()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields and select an image.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        fileName = selectedFile.getName();
        imagePath = "src/ProductsImages/" + fileName;

        try {
            databaseConnector dbc = new databaseConnector();

            String checkQuery = "SELECT COUNT(*) FROM products WHERE `Product Name` = ?";
            PreparedStatement checkStmt = dbc.getConnection().prepareStatement(checkQuery);
            checkStmt.setString(1, valname);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Product already exist!");
                return;
            }

            String insertQuery = "INSERT INTO `products`(`account_id`, `Product Name`, `Price`, `Stock`, `Description`, `Status`, `ImagePath`, `Date Created`) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE())";
            try (PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setInt(1, adminID);
                insertStmt.setString(2, valname);
                insertStmt.setString(3, valprice);
                insertStmt.setString(4, valstocks);
                insertStmt.setString(5, valdes);
                insertStmt.setString(6, valstats);
                insertStmt.setString(7, imagePath);
                insertStmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Product added successfully!");
            id.setText("");
            name.setText("");
            price.setText("");
            stocks.setText("");
            status.setSelectedIndex(0);
            description.setText("");
            displayPhoto.setIcon(null);
            displayData();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error adding product!" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JTextField ID;
    private javax.swing.JButton accounts;
    private javax.swing.JTable accounts_table;
    private javax.swing.JButton add;
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
    private javax.swing.JButton dashboard;
    private javax.swing.JPanel dashboardContainer;
    private javax.swing.JTextField date;
    private javax.swing.JButton delete;
    private javax.swing.JEditorPane descript;
    private javax.swing.JEditorPane description;
    private javax.swing.JLabel display;
    private javax.swing.JLabel displayPhoto;
    private javax.swing.JButton edit;
    private javax.swing.JTextField email;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel helloSeller;
    private javax.swing.JTextField id;
    private javax.swing.JTextField image_container;
    private javax.swing.JButton importt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField lname;
    private javax.swing.JButton logout;
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
    private javax.swing.JTextField name;
    private javax.swing.JButton orders;
    private javax.swing.JRadioButton other;
    private javax.swing.JTextField price;
    private javax.swing.JLabel productID;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPhoto;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productQuantity;
    private javax.swing.JLabel productStatus;
    private javax.swing.JTable product_table;
    private javax.swing.JButton restore;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JPanel s;
    private javax.swing.JButton savebtn;
    private javax.swing.JTextField search3;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchbtn;
    private javax.swing.JButton searchbtn11;
    private javax.swing.JButton searchbtn12;
    private javax.swing.JButton searchbtn9;
    private javax.swing.JButton selectFile;
    private javax.swing.JComboBox<String> stats;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField stocks;
    private javax.swing.JButton submit;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel totalSales;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
