/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

import accounts.Login;
import accounts.UserManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.actionLogs;
import config.databaseConnector;
import config.flatlaftTable;
import config.isAccountExist;
import config.search;
import config.sorter;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.mindrot.jbcrypt.BCrypt;
import raven.toast.Notifications;

/**
 *
 * @author MARITIME 02
 */
public final class adminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    int admin_id = UserManager.getLoggedInUserId();

    public adminDashboard() {
        initComponents();
        messages0.setFocusable(false);
        messages1.setFocusable(false);
        messages2.setFocusable(false);
        messages3.setFocusable(false);

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        displayAccounts();
        displayMessages();
        displayProducts();
        displayAccountName();
        displayArchiveAccounts();
        display_archive_products();

        actionLogs.displayAdminLogs(actionlogs_table, admin_id);

        flatlaftTable.design(productsContainer, product_table, jScrollPane5); // display wishlist table
        searchBar(product_search_bar);
        searchBar(product_search_bar1);

        flatlaftTable.design(jPanel10, accounts_table, scrollBar); // display wishlist table
        searchBar(searchBar);

        flatlaftTable.design(jPanel21, message4admin_table, jScrollPane12); // display wishlist table
        searchBar(message_search_bar);

        flatlaftTable.design(archiveAccountTableContainer1, archive_products_table, archiveAccountTableContainerScroll1); // display wishlist table
        searchBar(archive_search_bar);

        flatlaftTable.design(jPanel12, archive_accounts_table, archiveAccountTableContainerScroll); // display wishlist table
        searchBar(searchBar1);

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
        UXmethods.RoundBorders.setArcStyle(messages2, 50);

        UXmethods.RoundBorders.setArcStyle(edit, 15);
        UXmethods.RoundBorders.setArcStyle(add2archive, 15);
        UXmethods.RoundBorders.setArcStyle(add, 15);
        UXmethods.RoundBorders.setArcStyle(editAccountSaveBtn, 15);
        UXmethods.RoundBorders.setArcStyle(addAccountsaveBtn, 15);
        UXmethods.RoundBorders.setArcStyle(add2archive, 15);
        UXmethods.RoundBorders.setArcStyle(restore, 15);
        UXmethods.RoundBorders.setArcStyle(delete, 15);

        //Components
        UXmethods.RoundBorders.setArcStyle(searchBar, 15);
        UXmethods.RoundBorders.setArcStyle(accountTableContainer, 30);
        UXmethods.RoundBorders.setArcStyle(dashboardContainer, 15);
        UXmethods.RoundBorders.setArcStyle(editProfileContainer, 30);
        UXmethods.RoundBorders.setArcStyle(addAccountContainer, 30);

        //Dashboard Buttons
        UXmethods.RoundBorders.setArcStyle(logoutBtn, 50);

        //Test
        UXmethods.RoundBorders.setArcStyle(editStatus, 15);
        UXmethods.RoundBorders.setArcStyle(editRole, 15);

        searchBar.setFocusable(false);
    }

    private void searchBar(JTextField search) {
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        search.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new javax.swing.ImageIcon(getClass().getResource("/image/search_icon.png")));
        search.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                //+ "background:#FFFFFF;"
                + "margin:5,20,5,20");
    }

    private void displayMessages() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT "
                    + "m.message_id AS `Message ID`, "
                    + "a.account_id AS `Account ID`, "
                    + "CONCAT(a.first_name, ' ', a.last_name) AS `Full Name`, "
                    + "a.role AS `Role`, "
                    + "m.message_category AS `Category`, "
                    + "m.message_title AS `Title`, "
                    + "m.message_description AS `Description`, "
                    + "m.date_sent AS `Sent`, "
                    + "m.message_status AS `Status` "
                    + "FROM tbl_message4admin m "
                    + "JOIN tbl_accounts a ON a.account_id = m.account_id")) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        message_is_empty.setText("MESSAGE TABLE IS EMPTY!");
                        message4admin_table.setModel(new DefaultTableModel());
                    } else {
                        message_is_empty.setText("");
                        message4admin_table.setModel(DbUtils.resultSetToTableModel(rs));

                        // Center align the text in the table cells
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        for (int i = 0; i < message4admin_table.getColumnCount(); i++) {
                            message4admin_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                        message4admin_table.getColumnModel().getColumn(6).setPreferredWidth(400); // example for the first column
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayProducts() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT "
                    + "p.product_id AS `Product ID`, "
                    + "p.seller_id AS `Seller ID`, "
                    + "CONCAT(a.first_name, ' ', a.last_name) AS `Seller Name`, "
                    + "p.product_name AS `Product Name`, "
                    + "p.product_price AS `Price`, "
                    + "p.product_stock AS `Stock(s)`, "
                    + "p.product_category AS `Category`, "
                    + "p.total_sold AS `Sold`, "
                    + "p.date_created AS `Date Created`, "
                    + "p.product_status AS `Status` "
                    + "FROM tbl_products p "
                    + "JOIN tbl_accounts a ON a.account_id = p.seller_id")) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        product_is_empty.setText("PRODUCT TABLE IS EMPTY!");
                        product_table.setModel(new DefaultTableModel());
                    } else {
                        product_is_empty.setText("");
                        product_table.setModel(DbUtils.resultSetToTableModel(rs));

                        // Center align the text in the table cells
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        for (int i = 0; i < product_table.getColumnCount(); i++) {
                            product_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }

                        // Optionally set preferred column widths
                        // product_table.getColumnModel().getColumn(0).setPreferredWidth(20); // example for the first column
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayAccountName() {
        try {

            databaseConnector dbc = new databaseConnector();
            String query = "SELECT first_name, last_name, account_id, email, `phone_number`, address, role, status, profile_picture FROM tbl_accounts WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, admin_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);
                manageFullName.setText(firstName + " " + lastName);
                manageStatus.setText("" + rs.getString("status"));
                manageEmail.setText("" + rs.getString("email"));
                managePhone.setText("" + rs.getString("phone_number"));
                manageAddress.setText("" + rs.getString("address"));

                int height = 100;
                int width = 100;
                String profilePicture = rs.getString("profile_picture");
                GetImage.displayImage(managePhoto, profilePicture, height, width);

                int sheight = 50;
                int swidth = 50;
                String rofilePicture = rs.getString("profile_picture");
                GetImage.displayImage(messages0, rofilePicture, sheight, swidth);
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
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(
                    "SELECT `account_id` as `ID`, `first_name` as `First Name`, `last_name` as `Last Name`, `username` as `Username`, `address` as `Address`, `phone_number` as `Phone Number`, `role` as `Role`, `date_joined` as `Date Joined`, `status` as `Status` FROM tbl_accounts WHERE status = 'Archived'")) {

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        account_archive_is_empty.setText("ACCOUNT ARCHIVE TABLE IS EMPTY!");
                        archive_accounts_table.setModel(new DefaultTableModel());
                    } else {
                        account_archive_is_empty.setText("");
                        archive_accounts_table.setModel(DbUtils.resultSetToTableModel(rs));

                        // Center align the text in the table cells
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        for (int i = 0; i < archive_accounts_table.getColumnCount(); i++) {
                            archive_accounts_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }

                        // Optionally set preferred column widths
                        // archive_table.getColumnModel().getColumn(0).setPreferredWidth(20); // example for the first column
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayAccounts() {
        try {
            databaseConnector dbc = new databaseConnector();

            String query = "SELECT `account_id` as `Account ID`, `first_name` as `First Name`, `last_name` as `Last Name`, `username` as `Username`, `address` as `Address`, `phone_number` as `Phone Number`, `role` as `Role`, `date_joined` as `Date Joined`, `status` as `Status` FROM tbl_accounts WHERE status IN ('Active', 'Inactive', 'Pending') AND account_id != ?";

            PreparedStatement statement = dbc.getConnection().prepareStatement(query);
            statement.setInt(1, admin_id);
            ResultSet rs = statement.executeQuery();
            accounts_table.setModel(DbUtils.resultSetToTableModel(rs));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            accounts_table.setDefaultRenderer(Object.class, centerRenderer);

            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void display_archive_products() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT "
                    + "p.product_id AS `Product ID`, "
                    + "p.seller_id AS `Seller ID`, "
                    + "CONCAT(a.first_name, ' ', a.last_name) AS `Seller Name`, "
                    + "p.product_name AS `Product Name`, "
                    + "p.product_price AS `Price`, "
                    + "p.product_stock AS `Stock(s)`, "
                    + "p.product_category AS `Category`, "
                    + "p.total_sold AS `Sold`, "
                    + "p.date_created AS `Date Created`, "
                    + "p.product_status AS `Status` "
                    + "FROM tbl_products p "
                    + "JOIN tbl_accounts a ON a.account_id = p.seller_id "
                    + "WHERE p.product_status IN ('Archive')";
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

                if (!rs.isBeforeFirst()) {
                    archive_product_is_empty.setText("ARCHIVE PRODUCTS TABLE IS EMPTY!");
                    archive_products_table.setModel(new DefaultTableModel());
                } else {
                    archive_product_is_empty.setText("");
                    archive_products_table.setModel(DbUtils.resultSetToTableModel(rs));
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    for (int i = 0; i < archive_products_table.getColumnCount(); i++) {
                        archive_products_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
            }
        } catch (SQLException ex) {
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

        jPanel20 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        accountTableContainer = new javax.swing.JPanel();
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
        jPanel10 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        scrollBar = new javax.swing.JScrollPane();
        accounts_table = new javax.swing.JTable();
        searchBar = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        filterContainer4 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        filter_product_table2 = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
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
        actionLogsTableContainerScroll = new javax.swing.JScrollPane();
        actionlogs_table = new javax.swing.JTable();
        product_search_bar1 = new javax.swing.JTextField();
        filterContainer1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        filter_product_table3 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        archiveAccountTableContainer = new javax.swing.JPanel();
        fname1 = new javax.swing.JLabel();
        photo1 = new javax.swing.JLabel();
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
        restore = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        statusIcon1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        account_archive_is_empty = new javax.swing.JLabel();
        archiveAccountTableContainerScroll = new javax.swing.JScrollPane();
        archive_accounts_table = new javax.swing.JTable();
        searchBar1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        productsContainer = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        product_is_empty = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        product_search_bar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        filterContainer = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        filter_product_table = new javax.swing.JComboBox<>();
        product_table_edit_button = new javax.swing.JButton();
        product_table_archive_button = new javax.swing.JButton();
        product_table_delete_button = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        archiveAccountTableContainer1 = new javax.swing.JPanel();
        archive_product_is_empty = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        archiveAccountTableContainerScroll1 = new javax.swing.JScrollPane();
        archive_products_table = new javax.swing.JTable();
        archive_search_bar = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        c11 = new javax.swing.JPanel();
        manage23 = new javax.swing.JLabel();
        productID = new javax.swing.JLabel();
        c12 = new javax.swing.JPanel();
        manage28 = new javax.swing.JLabel();
        productQuantity = new javax.swing.JLabel();
        c13 = new javax.swing.JPanel();
        manage29 = new javax.swing.JLabel();
        productStatus = new javax.swing.JLabel();
        c14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descript = new javax.swing.JEditorPane();
        productPhoto = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        productPrice = new javax.swing.JLabel();
        restore1 = new javax.swing.JButton();
        delete1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        getStatus = new javax.swing.JComboBox<>();
        getCategory = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        edit_product_save_button = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        getName = new javax.swing.JTextField();
        getPrice = new javax.swing.JTextField();
        getStock = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        getDescription = new javax.swing.JEditorPane();
        editNameError = new javax.swing.JLabel();
        editPriceError = new javax.swing.JLabel();
        editStockError = new javax.swing.JLabel();
        desError2 = new javax.swing.JLabel();
        desError3 = new javax.swing.JLabel();
        desError5 = new javax.swing.JLabel();
        desError6 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel23 = new javax.swing.JPanel();
        getPhoto = new javax.swing.JLabel();
        replacebtn = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        desError7 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        message_search_bar = new javax.swing.JTextField();
        filterContainer3 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        filter_product_table1 = new javax.swing.JComboBox<>();
        jSeparator17 = new javax.swing.JSeparator();
        message_is_empty = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        message4admin_table = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        dashboardContainer = new javax.swing.JPanel();
        messages0 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        messages2 = new javax.swing.JToggleButton();
        messages1 = new javax.swing.JToggleButton();
        messages3 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1170, 20));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accountTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        accountTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        fname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fname.setText("FIRST NAME");
        accountTableContainer.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 180, 40));

        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 70x70.png"))); // NOI18N
        accountTableContainer.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 70, 70));
        accountTableContainer.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 270, 20));

        statusIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusIcon.setForeground(new java.awt.Color(153, 255, 153));
        statusIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-connection-activeon-24 (1).png"))); // NOI18N
        statusIcon.setText(".");
        accountTableContainer.add(statusIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 30, 20));

        status.setForeground(new java.awt.Color(102, 102, 102));
        status.setText("Status");
        accountTableContainer.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, 20));

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
        accountTableContainer.add(add2archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 600, 130, 50));

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
        accountTableContainer.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 130, 50));

        c1.setBackground(new java.awt.Color(255, 255, 255));
        c1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage16.setForeground(new java.awt.Color(102, 102, 102));
        manage16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-tag-woman-horizontal-24.png"))); // NOI18N
        c1.add(manage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        id.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        id.setForeground(new java.awt.Color(102, 102, 102));
        id.setText("ID Number");
        c1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 270, 30));

        c2.setBackground(new java.awt.Color(255, 255, 255));
        c2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage17.setForeground(new java.awt.Color(102, 102, 102));
        manage17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-email-24.png"))); // NOI18N
        c2.add(manage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        email.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setText("Email");
        c2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 270, 30));

        c3.setBackground(new java.awt.Color(255, 255, 255));
        c3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage14.setForeground(new java.awt.Color(102, 102, 102));
        manage14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-phone-24.png"))); // NOI18N
        c3.add(manage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        number.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        number.setForeground(new java.awt.Color(102, 102, 102));
        number.setText("Phone Number");
        c3.add(number, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 270, 30));

        c4.setBackground(new java.awt.Color(255, 255, 255));
        c4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage15.setForeground(new java.awt.Color(102, 102, 102));
        manage15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-address-24.png"))); // NOI18N
        c4.add(manage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        address.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        address.setForeground(new java.awt.Color(102, 102, 102));
        address.setText("Location");
        c4.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 270, 30));

        c5.setBackground(new java.awt.Color(255, 255, 255));
        c5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage8.setForeground(new java.awt.Color(102, 102, 102));
        manage8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-24.png"))); // NOI18N
        c5.add(manage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        role.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        role.setForeground(new java.awt.Color(102, 102, 102));
        role.setText("Role");
        c5.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 200, 30));

        accountTableContainer.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 270, 30));

        jPanel7.add(accountTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 310, 670));

        jPanel10.setBackground(new java.awt.Color(241, 241, 241));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel10.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 860, 40));

        scrollBar.setBackground(new java.awt.Color(0, 0, 0));
        scrollBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        accounts_table.setBackground(new java.awt.Color(241, 241, 241));
        accounts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        accounts_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        accounts_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accounts_tableMouseClicked(evt);
            }
        });
        scrollBar.setViewportView(accounts_table);

        jPanel10.add(scrollBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 810, 550));

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
        jPanel10.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 40));

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
        jPanel10.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 160, 40));

        filterContainer4.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setBackground(new java.awt.Color(241, 241, 241));
        jLabel45.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Filter by:");
        filterContainer4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table2.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Categories", "Electronics", "Grocery", "Pet Supplies", "Fashion" }));
        filter_product_table2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filter_product_table2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_product_table2ActionPerformed(evt);
            }
        });
        filterContainer4.add(filter_product_table2, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        jPanel10.add(filterContainer4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 180, 40));

        jLabel47.setBackground(new java.awt.Color(241, 241, 241));
        jLabel47.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Accounts Table");
        jPanel10.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jButton2.setText("View archive");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 130, 40));

        jPanel7.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 810, 670));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1210, 700));

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

        jPanel4.add(editProfileContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1120, 570));

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
        addAccountContainer.add(addAccountsaveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 460, 140, 40));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        addAccountContainer.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 60, 380));

        select.setBackground(new java.awt.Color(241, 241, 241));
        select.setText("Select Image");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        addAccountContainer.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 340, 130, 40));

        displayImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        displayImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/120x120.png"))); // NOI18N
        addAccountContainer.add(displayImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 206, 460, 120));

        optional.setForeground(new java.awt.Color(153, 153, 153));
        optional.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optional.setText("* Optional");
        addAccountContainer.add(optional, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 390, 460, -1));

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
        addAccountContainer.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 460, -1));

        myprofile5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        myprofile5.setText("Add Account");
        addAccountContainer.add(myprofile5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 30));

        manage11.setForeground(new java.awt.Color(102, 102, 102));
        manage11.setText("Create new account profile");
        addAccountContainer.add(manage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));

        jPanel6.add(addAccountContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1120, 570));

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
        editProfileContainer1.add(manageSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, -1, 40));

        myprofile6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        myprofile6.setText("Manage Profile");
        editProfileContainer1.add(myprofile6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 30));

        manage12.setForeground(new java.awt.Color(102, 102, 102));
        manage12.setText("Manage your account profile information");
        editProfileContainer1.add(manage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 30));
        editProfileContainer1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1140, 10));

        manageFullName.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        manageFullName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        manageFullName.setText("Archie Albarico");
        editProfileContainer1.add(manageFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 190, 40));

        manageStatus.setForeground(new java.awt.Color(153, 153, 153));
        manageStatus.setText("Pending");
        editProfileContainer1.add(manageStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, 20));

        jLabel14.setText("Status:");
        editProfileContainer1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, 20));

        managePhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managePhotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managePhotoMouseEntered(evt);
            }
        });
        editProfileContainer1.add(managePhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 100, 100));

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

        editProfileContainer1.add(z2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 320, 30));

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

        editProfileContainer1.add(z3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 320, 30));

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

        editProfileContainer1.add(z4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 320, 30));

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

        editProfileContainer1.add(z5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 320, 30));

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

        editProfileContainer1.add(z6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 320, 30));

        jLabel15.setForeground(new java.awt.Color(0, 158, 226));
        jLabel15.setText("Change");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        editProfileContainer1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, -1, 30));

        changePassword.setForeground(new java.awt.Color(0, 158, 226));
        changePassword.setText("Change");
        changePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePasswordMouseClicked(evt);
            }
        });
        editProfileContainer1.add(changePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 30));

        jLabel20.setForeground(new java.awt.Color(0, 158, 226));
        jLabel20.setText("Change");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        editProfileContainer1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, -1, 30));

        jLabel21.setForeground(new java.awt.Color(0, 158, 226));
        jLabel21.setText("Change");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        editProfileContainer1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, -1, 30));

        actionLogsTableContainerScroll.setBackground(new java.awt.Color(0, 0, 0));
        actionLogsTableContainerScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        actionlogs_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        actionlogs_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionlogs_tableMouseClicked(evt);
            }
        });
        actionLogsTableContainerScroll.setViewportView(actionlogs_table);

        editProfileContainer1.add(actionLogsTableContainerScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 600, 480));

        product_search_bar1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        product_search_bar1.setForeground(new java.awt.Color(140, 140, 140));
        product_search_bar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_search_bar1MouseClicked(evt);
            }
        });
        product_search_bar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                product_search_bar1KeyReleased(evt);
            }
        });
        editProfileContainer1.add(product_search_bar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 180, 40));

        filterContainer1.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setBackground(new java.awt.Color(241, 241, 241));
        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Filter by:");
        filterContainer1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table3.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Categories", "Electronics", "Grocery", "Pet Supplies", "Fashion" }));
        filter_product_table3.setSelectedIndex(0);
        filter_product_table3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filter_product_table3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_product_table3ActionPerformed(evt);
            }
        });
        filterContainer1.add(filter_product_table3, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        editProfileContainer1.add(filterContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 180, 40));
        UXmethods.RoundBorders.setArcStyle(filterContainer1, 10);

        jPanel2.add(editProfileContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 670));

        tabs.addTab("tab4", jPanel2);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archiveAccountTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        archiveAccountTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        fname1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fname1.setText("FIRST NAME");
        archiveAccountTableContainer.add(fname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 180, 40));

        photo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 70x70.png"))); // NOI18N
        archiveAccountTableContainer.add(photo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 70));

        status1.setForeground(new java.awt.Color(102, 102, 102));
        status1.setText("Status");
        archiveAccountTableContainer.add(status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, 20));

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

        archiveAccountTableContainer.add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 270, 30));

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

        archiveAccountTableContainer.add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 270, 30));

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

        archiveAccountTableContainer.add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 270, 30));

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

        archiveAccountTableContainer.add(c9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 270, 30));

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

        archiveAccountTableContainer.add(c10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 270, 30));

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
        archiveAccountTableContainer.add(restore, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 130, 50));

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
        archiveAccountTableContainer.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 600, 130, 50));

        statusIcon1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusIcon1.setForeground(new java.awt.Color(153, 255, 153));
        statusIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-connection-activeon-24 (1).png"))); // NOI18N
        statusIcon1.setText(".");
        archiveAccountTableContainer.add(statusIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 30, 20));

        jPanel8.add(archiveAccountTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 310, 670));

        jPanel12.setBackground(new java.awt.Color(241, 241, 241));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel12.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 820, 40));

        account_archive_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        account_archive_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        account_archive_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(account_archive_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 820, 60));

        archiveAccountTableContainerScroll.setBackground(new java.awt.Color(0, 0, 0));
        archiveAccountTableContainerScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        archive_accounts_table.setBackground(new java.awt.Color(241, 241, 241));
        archive_accounts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        archive_accounts_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        archive_accounts_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archive_accounts_tableMouseClicked(evt);
            }
        });
        archiveAccountTableContainerScroll.setViewportView(archive_accounts_table);

        jPanel12.add(archiveAccountTableContainerScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 820, 550));

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
        jPanel12.add(searchBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 40));

        jLabel43.setBackground(new java.awt.Color(241, 241, 241));
        jLabel43.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Archive AccountsTable");
        jPanel12.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, 30));

        jLabel44.setBackground(new java.awt.Color(241, 241, 241));
        jLabel44.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(204, 204, 204));
        jLabel44.setText("Accounts Table  >");
        jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel44MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jPanel8.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 820, 670));

        tabs.addTab("tab5", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tabs.addTab("tab6", jPanel9);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productsContainer.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        productsContainer.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1140, 20));

        product_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        product_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsContainer.add(product_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 1140, 60));

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        product_table.setAutoCreateRowSorter(true);
        product_table.setBackground(new java.awt.Color(241, 241, 241));
        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        product_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        product_table.setShowHorizontalLines(true);
        product_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(product_table);

        productsContainer.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1140, 560));

        product_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        product_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        product_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_search_barMouseClicked(evt);
            }
        });
        product_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                product_search_barKeyReleased(evt);
            }
        });
        productsContainer.add(product_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 40));

        jLabel18.setBackground(new java.awt.Color(241, 241, 241));
        jLabel18.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Product Table");
        productsContainer.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setBackground(new java.awt.Color(241, 241, 241));
        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Filter by:");
        filterContainer.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Categories", "Electronics", "Grocery", "Pet Supplies", "Fashion" }));
        filter_product_table.setSelectedIndex(0);
        filter_product_table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filter_product_table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_product_tableActionPerformed(evt);
            }
        });
        filterContainer.add(filter_product_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer.add(filterContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 180, 40));
        UXmethods.RoundBorders.setArcStyle(filterContainer, 10);

        product_table_edit_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_edit_button.setForeground(new java.awt.Color(51, 51, 51));
        product_table_edit_button.setText("Edit");
        product_table_edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_edit_buttonActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_edit_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 130, 40));
        UXmethods.RoundBorders.setArcStyle(product_table_edit_button, 10);

        product_table_archive_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_archive_button.setForeground(new java.awt.Color(51, 51, 51));
        product_table_archive_button.setText("Add to archive");
        product_table_archive_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_archive_buttonActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_archive_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 50, 130, 40));
        UXmethods.RoundBorders.setArcStyle(product_table_archive_button, 10);

        product_table_delete_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_delete_button.setText("Delete");
        product_table_delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_delete_buttonActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_delete_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 130, 40));
        UXmethods.RoundBorders.setArcStyle(product_table_delete_button, 10);

        jLabel31.setBackground(new java.awt.Color(241, 241, 241));
        jLabel31.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 204, 204));
        jLabel31.setText("Manage Product  >");
        productsContainer.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel26.setBackground(new java.awt.Color(241, 241, 241));
        jLabel26.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Product Table");
        productsContainer.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, 30));

        jButton1.setText("View archive");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        productsContainer.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 50, 120, 40));
        UXmethods.RoundBorders.setArcStyle(jButton1, 10);

        jPanel11.add(productsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 680));

        tabs.addTab("tab7", jPanel11);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archiveAccountTableContainer1.setBackground(new java.awt.Color(241, 241, 241));
        archiveAccountTableContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archive_product_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        archive_product_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        archive_product_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        archiveAccountTableContainer1.add(archive_product_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 820, 60));
        archiveAccountTableContainer1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 950, 20));

        archiveAccountTableContainerScroll1.setBackground(new java.awt.Color(0, 0, 0));
        archiveAccountTableContainerScroll1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        archive_products_table.setBackground(new java.awt.Color(241, 241, 241));
        archive_products_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        archive_products_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        archive_products_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archive_products_tableMouseClicked(evt);
            }
        });
        archiveAccountTableContainerScroll1.setViewportView(archive_products_table);

        archiveAccountTableContainer1.add(archiveAccountTableContainerScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 820, 560));

        archive_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        archive_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        archive_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archive_search_barMouseClicked(evt);
            }
        });
        archive_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                archive_search_barKeyReleased(evt);
            }
        });
        archiveAccountTableContainer1.add(archive_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 40));

        jLabel41.setBackground(new java.awt.Color(241, 241, 241));
        jLabel41.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("Manage Product  >  Product Table  >");
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        archiveAccountTableContainer1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel42.setBackground(new java.awt.Color(241, 241, 241));
        jLabel42.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Archive Products Table");
        archiveAccountTableContainer1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 30));

        jPanel13.add(archiveAccountTableContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 820, 680));

        jPanel14.setBackground(new java.awt.Color(241, 241, 241));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        c11.setBackground(new java.awt.Color(255, 255, 255));
        c11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage23.setForeground(new java.awt.Color(102, 102, 102));
        manage23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-name-tag-woman-horizontal-24.png"))); // NOI18N
        c11.add(manage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productID.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        productID.setForeground(new java.awt.Color(102, 102, 102));
        c11.add(productID, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 30));

        jPanel14.add(c11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        c12.setBackground(new java.awt.Color(255, 255, 255));
        c12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage28.setForeground(new java.awt.Color(102, 102, 102));
        manage28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-quantity-24.png"))); // NOI18N
        c12.add(manage28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productQuantity.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        productQuantity.setForeground(new java.awt.Color(102, 102, 102));
        c12.add(productQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 30));

        jPanel14.add(c12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        c13.setBackground(new java.awt.Color(255, 255, 255));
        c13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage29.setForeground(new java.awt.Color(102, 102, 102));
        manage29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manage29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-status-24.png"))); // NOI18N
        c13.add(manage29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        productStatus.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        productStatus.setForeground(new java.awt.Color(102, 102, 102));
        c13.add(productStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 30));

        jPanel14.add(c13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        c14.setBackground(new java.awt.Color(255, 255, 255));
        c14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        descript.setBorder(null);
        descript.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(descript);

        c14.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 290));

        jPanel14.add(c14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 270, 310));

        productPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 70x70.png"))); // NOI18N
        jPanel14.add(productPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        productName.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        productName.setForeground(new java.awt.Color(51, 51, 51));
        productName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productName.setText("PRODUCT NAME");
        jPanel14.add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 190, 40));
        jPanel14.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 300, 20));

        productPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        productPrice.setForeground(new java.awt.Color(102, 102, 102));
        productPrice.setText("Status");
        jPanel14.add(productPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, 20));

        restore1.setBackground(new java.awt.Color(122, 183, 147));
        restore1.setForeground(new java.awt.Color(255, 255, 255));
        restore1.setText("Restore");
        restore1.setBorder(null);
        restore1.setBorderPainted(false);
        restore1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restore1ActionPerformed(evt);
            }
        });
        jPanel14.add(restore1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 130, 40));

        delete1.setBackground(new java.awt.Color(255, 102, 102));
        delete1.setForeground(new java.awt.Color(255, 255, 255));
        delete1.setText("Delete");
        delete1.setBorder(null);
        delete1.setBorderPainted(false);
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });
        jPanel14.add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 630, 130, 40));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Description");
        jPanel14.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 310, 680));

        tabs.addTab("tab8", jPanel13);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Stock(s)");
        jPanel17.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        getStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getStatus.setForeground(new java.awt.Color(51, 51, 51));
        getStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        getStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getStatusMouseClicked(evt);
            }
        });
        jPanel17.add(getStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 320, 50));

        getCategory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getCategory.setForeground(new java.awt.Color(51, 51, 51));
        getCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Fashion", "Grocery", "Pet Supplies" }));
        getCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getCategoryMouseClicked(evt);
            }
        });
        jPanel17.add(getCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 320, 50));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Category");
        jPanel17.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        edit_product_save_button.setBackground(new java.awt.Color(0, 158, 226));
        edit_product_save_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_product_save_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_product_save_button.setText("Save");
        edit_product_save_button.setBorderPainted(false);
        edit_product_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_product_save_buttonActionPerformed(evt);
            }
        });
        jPanel17.add(edit_product_save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 270, 40));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Price");
        jPanel17.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Status");
        jPanel17.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Description");
        jPanel17.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Product Name");
        jPanel17.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("General infromation");
        jPanel17.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        jPanel17.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 720, 30));

        getName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getName.setForeground(new java.awt.Color(51, 51, 51));
        getName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getNameMouseClicked(evt);
            }
        });
        jPanel17.add(getName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 660, 50));

        getPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getPriceMouseClicked(evt);
            }
        });
        jPanel17.add(getPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, 50));

        getStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getStockMouseClicked(evt);
            }
        });
        jPanel17.add(getStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 320, 50));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        getDescription.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getDescription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getDescriptionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(getDescription);

        jPanel17.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 660, 120));

        editNameError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editNameError.setForeground(new java.awt.Color(255, 51, 51));
        editNameError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel17.add(editNameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 250, -1));

        editPriceError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editPriceError.setForeground(new java.awt.Color(255, 51, 51));
        editPriceError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel17.add(editPriceError, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 250, -1));

        editStockError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editStockError.setForeground(new java.awt.Color(255, 51, 51));
        editStockError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel17.add(editStockError, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 250, -1));

        desError2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError2.setForeground(new java.awt.Color(255, 51, 51));
        desError2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel17.add(desError2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 260, -1));

        desError3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError3.setForeground(new java.awt.Color(255, 51, 51));
        desError3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel17.add(desError3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 560, -1));

        desError5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError5.setForeground(new java.awt.Color(255, 51, 51));
        desError5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel17.add(desError5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 550, -1));

        desError6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError6.setForeground(new java.awt.Color(255, 51, 51));
        desError6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel17.add(desError6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 260, -1));

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 720, 620));

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel18.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, 30));

        jPanel23.setBackground(new java.awt.Color(204, 204, 204));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel23.add(getPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 270, 175));

        jPanel18.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 390, 195));

        replacebtn.setBackground(new java.awt.Color(0, 158, 226));
        replacebtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        replacebtn.setForeground(new java.awt.Color(255, 255, 255));
        replacebtn.setText("Replace");
        replacebtn.setBorderPainted(false);
        replacebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replacebtnActionPerformed(evt);
            }
        });
        jPanel18.add(replacebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 390, 50));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Product Image");
        jPanel18.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel48.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Edit Product");
        jPanel18.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel49.setBackground(new java.awt.Color(241, 241, 241));
        jLabel49.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(153, 153, 153));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Set the product thumbnail image. Only *.png, *.jpeg, *.jpg files are accepted.");
        jPanel18.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 390, 30));

        desError7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        desError7.setForeground(new java.awt.Color(255, 51, 51));
        desError7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel18.add(desError7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 260, -1));

        jPanel16.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 410, 450));

        jLabel101.setBackground(new java.awt.Color(241, 241, 241));
        jLabel101.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(204, 204, 204));
        jLabel101.setText("Manage Product  >  Product Table  >");
        jLabel101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel101MouseClicked(evt);
            }
        });
        jPanel16.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel103.setBackground(new java.awt.Color(241, 241, 241));
        jLabel103.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(51, 51, 51));
        jLabel103.setText("Edit Product");
        jPanel16.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, 30));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        tabs.addTab("tab9", jPanel15);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(241, 241, 241));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        message_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        message_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        message_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                message_search_barMouseClicked(evt);
            }
        });
        message_search_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                message_search_barActionPerformed(evt);
            }
        });
        message_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                message_search_barKeyReleased(evt);
            }
        });
        jPanel21.add(message_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 170, 40));

        filterContainer3.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel74.setBackground(new java.awt.Color(241, 241, 241));
        jLabel74.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Filter by:");
        filterContainer3.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table1.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Status", "Deactivate account", "Delete product", "Report bug", "Request feature" }));
        filter_product_table1.setSelectedIndex(0);
        filter_product_table1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filter_product_table1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_product_table1ActionPerformed(evt);
            }
        });
        filterContainer3.add(filter_product_table1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 160, 30));

        jPanel21.add(filterContainer3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));
        UXmethods.RoundBorders.setArcStyle(filterContainer3, 10);
        jPanel21.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 700, 20));

        message_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        message_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        message_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel21.add(message_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 700, 60));

        jScrollPane12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        message4admin_table.setAutoCreateRowSorter(true);
        message4admin_table.setBackground(new java.awt.Color(241, 241, 241));
        message4admin_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        message4admin_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        message4admin_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                message4admin_tableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(message4admin_table);

        jPanel21.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 700, 570));

        jLabel46.setBackground(new java.awt.Color(241, 241, 241));
        jLabel46.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Messages Table");
        jPanel21.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 30));

        jLabel50.setBackground(new java.awt.Color(241, 241, 241));
        jLabel50.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(204, 204, 204));
        jLabel50.setText("Messages  >");
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jPanel19.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 700, 670));

        tabs.addTab("tab10", jPanel19);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -20, 1170, 740));

        jPanel20.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 0, 1320, -1));

        dashboardContainer.setBackground(new java.awt.Color(241, 241, 241));
        dashboardContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        messages0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messages0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/2.png"))); // NOI18N
        messages0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messages0MouseClicked(evt);
            }
        });
        dashboardContainer.add(messages0, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, 50));

        logoutBtn.setBackground(new java.awt.Color(255, 102, 102));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout_icons.png"))); // NOI18N
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 50, 50));
        dashboardContainer.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 50, 10));

        messages2.setBackground(new java.awt.Color(204, 204, 204));
        messages2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/manage_icon.png"))); // NOI18N
        messages2.setBorderPainted(false);
        messages2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messages2ActionPerformed(evt);
            }
        });
        dashboardContainer.add(messages2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 50, 50));
        UXmethods.RoundBorders.setArcStyle(messages2, 50);

        messages1.setBackground(new java.awt.Color(204, 204, 204));
        messages1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/message_icon.png"))); // NOI18N
        messages1.setBorderPainted(false);
        messages1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messages1ActionPerformed(evt);
            }
        });
        dashboardContainer.add(messages1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 50, 50));
        UXmethods.RoundBorders.setArcStyle(messages1, 50);

        messages3.setBackground(new java.awt.Color(204, 204, 204));
        messages3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboard_buttons/dashboard_icon.png"))); // NOI18N
        messages3.setBorderPainted(false);
        messages3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messages3ActionPerformed(evt);
            }
        });
        dashboardContainer.add(messages3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 50, 50));
        UXmethods.RoundBorders.setArcStyle(messages3, 50);

        jPanel20.add(dashboardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 740));

        getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        Login logout = new Login();
        logout.setVisible(true);
        this.dispose();

        String action = "Logged out";
        String details = "User " + admin_id + " Successfully logged out!";
        actionLogs.recordAdminLogs(admin_id, action, details);
    }//GEN-LAST:event_logoutBtnActionPerformed
    String fileName;
    String imagePath;
    File selectedFile;
    private ImageIcon pendingIcon;
    private ImageIcon activeIcon;
    private ImageIcon inactiveIcon;
    private ImageIcon archivedIcon;

    private void clearAccountInfoFields(JLabel idField, JLabel fnameField, JLabel emailField, JLabel numberField,
            JLabel addressField, JLabel roleField, JLabel statusField, JLabel statusIcon,
            JLabel photoLabel) {
        idField.setText("");
        fnameField.setText("");
        emailField.setText("");
        numberField.setText("");
        addressField.setText("");
        roleField.setText("");
        statusField.setText("");
        statusIcon.setIcon(null);  // You can set this to a default icon if preferred
        photoLabel.setIcon(null);  // Clear the photo label or set to a default image if preferred
    }

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
                ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    idField.setText("" + rs.getString("account_id"));
                    String firstName = rs.getString("first_name");
                    firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                    fnameField.setText(firstName);
                    emailField.setText("" + rs.getString("email"));
                    numberField.setText("" + rs.getString("phone_number"));
                    addressField.setText("" + rs.getString("address"));
                    roleField.setText("" + rs.getString("role"));
                    String statusValue = rs.getString("status");
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

    private void messages0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messages0MouseClicked
        messages1.setSelected(false);
        messages2.setSelected(false);
        messages3.setSelected(false);

        tabs.setSelectedIndex(3);
        displayAccountName();
        actionLogs.displayAdminLogs(actionlogs_table, admin_id);
    }//GEN-LAST:event_messages0MouseClicked


    private void jLabel101MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseClicked
        emptyValues();
        tabs.setSelectedIndex(6);
    }//GEN-LAST:event_jLabel101MouseClicked

    private void replacebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replacebtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                Image resizedImage = originalImage.getScaledInstance(270, 175, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                photo.setIcon(icon);

                String imageName = selectedFile.getName();
                imagePath = "src/ProductsImages/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                selectedFile = destination;

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }    }//GEN-LAST:event_replacebtnActionPerformed

    private void getDescriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getDescriptionMouseClicked
        getDescription.setFocusable(true);
        getDescription.requestFocusInWindow();
    }//GEN-LAST:event_getDescriptionMouseClicked

    private void getStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getStockMouseClicked
        getStock.setFocusable(true);
        getStock.requestFocusInWindow();
    }//GEN-LAST:event_getStockMouseClicked

    private void getPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getPriceMouseClicked
        getPrice.setFocusable(true);
        getPrice.requestFocusInWindow();
    }//GEN-LAST:event_getPriceMouseClicked

    private void getNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getNameMouseClicked
        getName.setFocusable(true);
        getName.requestFocusInWindow();
    }//GEN-LAST:event_getNameMouseClicked

    private void edit_product_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_product_save_buttonActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String productName = getName.getText();
            String productPrice = getPrice.getText();
            String productStocks = getStock.getText();
            String productDescription = getDescription.getText();
            String productStatus = (String) getStatus.getSelectedItem();
            String productCategory = (String) getCategory.getSelectedItem();

            // Validate input fields
            if (productName.isEmpty() || productPrice.isEmpty() || productStocks.isEmpty() || productDescription.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Please fill in all fields!");
                return;
            }
            if (productName.length() > 21) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Product name must not exceed 21 characters!");
                editNameError.setText("* Product name must not exceed 21 characters.");
                getName.setText("");
                return;
            }

            if (productDescription.length() > 999) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Desccription exceed 900 characters!");
                desError3.setText("* Desccription must not exceed 900 characters!");
                return;
            }

            if (EditgetImageFromDatabase == null) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Please select an image!");
                desError7.setText("* Product image cannot be empty.");
                return;
            }

            // Additional validation for numeric fields
            int price;
            int stocks;
            try {
                price = Integer.parseInt(productPrice);
            } catch (NumberFormatException ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Price must be numeric!");
                return;
            }

            try {
                stocks = Integer.parseInt(productStocks);
            } catch (NumberFormatException ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Stocks must be numeric!");
                return;
            }

            if (price <= 0) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Price must be greater than zero!");

                editPriceError.setText("* Price must be greater than zero.");
                getPrice.setText("");
                return;
            }
            if (stocks <= 0) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Stocks must be greater than zero!");
                editStockError.setText("* Stocks must be greater than zero.");
                getStock.setText("");
                return;
            }

            if (selectedFile != null) {

                sql = "UPDATE tbl_products SET "
                        + "product_name=?, "
                        + "product_price=?, "
                        + "product_stock=?, "
                        + "product_description=?, "
                        + "product_image=?, "
                        + "product_status=?, "
                        + "product_category=? "
                        + "WHERE product_id=?";
            } else {
                sql = "UPDATE tbl_products SET "
                        + "product_name=?, "
                        + "product_price=?, "
                        + "product_stock=?, "
                        + "product_description=?, "
                        + "product_status=?, "
                        + "product_category=? "
                        + "WHERE product_id=?";
            }

            // Prepare the SQL query to count the number of products with the same name but different IDs
            String checkQuery = "SELECT COUNT(*) FROM tbl_products WHERE product_name = ? AND product_id <> ?";
            PreparedStatement checkStmt = dbc.getConnection().prepareStatement(checkQuery);
            checkStmt.setString(1, productName);
            checkStmt.setInt(2, p_id); // Assuming currentProductId holds the ID of the current product
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            // Check if there are any products with the same name but different IDs
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Product already exists!");
                getName.setText("");
                return;
            }

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setString(1, productName);
                pst.setInt(2, price);
                pst.setInt(3, stocks);
                pst.setString(4, productDescription);

                if (selectedFile != null) {
                    pst.setString(5, imagePath);
                    pst.setString(6, productStatus);
                    pst.setString(7, productCategory);
                    pst.setInt(8, p_id);
                } else {
                    pst.setString(5, productStatus);
                    pst.setString(6, productCategory);
                    pst.setInt(7, p_id);
                }

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Product edited successfully");
                    if (selectedFile == null) {
                        String action = "Edit Product";
                        String details = "Admin " + admin_id + " successfully edited product " + p_id + "!";
                        actionLogs.recordAdminLogs(admin_id, action, details);
                    }
                    displayProducts();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update data!");
                }
            }

            emptyValues();
            p_id = 0;
            displayProducts();
            removeErrorsMessage();
            tabs.setSelectedIndex(6);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_edit_product_save_buttonActionPerformed

    private void getCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getCategoryMouseClicked
        getCategory.setFocusable(true);
        getCategory.requestFocusInWindow();
    }//GEN-LAST:event_getCategoryMouseClicked

    private void getStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getStatusMouseClicked
        getStatus.setFocusable(true);
        getStatus.requestFocusInWindow();
    }//GEN-LAST:event_getStatusMouseClicked

    private void delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete1ActionPerformed
        int rowIndex = archive_accounts_table.getSelectedRow(); // Get the selected row index
        if (rowIndex < 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Please select a product first!");
        } else {
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                int pid = (int) archive_accounts_table.getValueAt(rowIndex, 0);
                try {
                    databaseConnector dbc = new databaseConnector();
                    dbc.deleteProduct(pid);
                    displayProducts();
                    display_archive_products();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Product deleted successfully!");
                    // logs
                    String details = "Admin " + admin_id + " successfully deleted the product " + pid + "!";
                    String action = "Delete product";
                    actionLogs.recordAdminLogs(admin_id, action, details);
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_delete1ActionPerformed

    private void restore1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restore1ActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            int pid = Integer.parseInt(productID.getText());
            String sql = "UPDATE tbl_products SET product_status='Available' WHERE product_id = ?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, pid);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    displayProducts();
                    display_archive_products();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Product has been retored Successfully!");
                    String action = "Restore";
                    String details = "Admin " + admin_id + " Successfully restore product " + pid + "!";
                    actionLogs.recordAdminLogs(admin_id, action, details);
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Faild to restore product!");
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_restore1ActionPerformed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        tabs.setSelectedIndex(6);
    }//GEN-LAST:event_jLabel41MouseClicked

    private void archive_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_archive_search_barKeyReleased
        search.searchResult(archive_accounts_table, archive_search_bar);
    }//GEN-LAST:event_archive_search_barKeyReleased

    private void archive_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archive_search_barMouseClicked
        archive_search_bar.setFocusable(true);
        archive_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_archive_search_barMouseClicked

    private void archive_products_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archive_products_tableMouseClicked
        int rowIndex = archive_products_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = archive_products_table.getModel();

            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id =" + model.getValueAt(rowIndex, 0));

                if (rs.next()) {
                    productID.setText("" + rs.getString("product_id"));
                    productPrice.setText("₱  " + rs.getString("product_price"));
                    productName.setText("" + rs.getString("product_name"));
                    productQuantity.setText("" + rs.getString("product_stock"));
                    productStatus.setText("" + rs.getString("product_status"));
                    descript.setText("" + rs.getString("product_description"));
                    int height = 70;
                    int width = 70;
                    String getImageFromDatabase = rs.getString("product_image");
                    GetImage.displayImage(productPhoto, getImageFromDatabase, height, width);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_archive_products_tableMouseClicked

    private void product_table_delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_delete_buttonActionPerformed
        int rowIndex = product_table.getSelectedRow(); // Get the selected row index
        if (rowIndex < 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error: Please select a product to delete!");
        } else {
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                int pid = (int) product_table.getValueAt(rowIndex, 0);

                try {
                    databaseConnector dbc = new databaseConnector();
                    dbc.deleteProduct(pid);
                    displayProducts();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Product has been successfully deleted!");
                    displayProducts();
                    // logs
                    String details = "Admin " + admin_id + " successfully deleted the product " + pid + "!";
                    String action = "Delete product";
                    actionLogs.recordAdminLogs(admin_id, action, details);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_product_table_delete_buttonActionPerformed

    private void product_table_archive_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_archive_buttonActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();

            String sql = "UPDATE tbl_products SET product_status = 'Archive' WHERE product_id = ?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, p_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Product added to archive successfully!");
                    displayProducts();
                    display_archive_products();
                    String action = "Archive";
                    String details = "Admin " + admin_id + " Successfully put product " + p_id + " to archive!";
                    actionLogs.recordAdminLogs(admin_id, action, details);
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "failed to add product to archive!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_product_table_archive_buttonActionPerformed

    private void product_table_edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_edit_buttonActionPerformed
        try {
            int rowIndex = product_table.getSelectedRow();
            if (rowIndex < 0) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Please Select an Item!");
            } else {
                TableModel model = product_table.getModel();
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id = " + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    int height = 270;
                    int width = 175;
                    String getImageFromDatabase = rs.getString("product_image");
                    GetImage.displayImage(getPhoto, getImageFromDatabase, height, width);
                    getName.setText(rs.getString("product_name"));
                    getPrice.setText(rs.getString("product_price"));
                    getStock.setText(rs.getString("product_stock"));
                    getDescription.setText(rs.getString("product_description"));
                    getStatus.setSelectedItem(rs.getString("product_status"));

                    tabs.setSelectedIndex(8);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_product_table_edit_buttonActionPerformed

    private void filter_product_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_product_tableActionPerformed
        sorter.searchResult(product_table, filter_product_table);
    }//GEN-LAST:event_filter_product_tableActionPerformed

    private void product_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_product_search_barKeyReleased
        search.searchResult(product_table, product_search_bar);
    }//GEN-LAST:event_product_search_barKeyReleased

    private void product_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_search_barMouseClicked
        product_search_bar.setFocusable(true);
        product_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_product_search_barMouseClicked
    String EditgetImageFromDatabase;
    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
        int rowIndex = product_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
            return;
        }
        TableModel model = product_table.getModel();

        try {
            databaseConnector dbc = new databaseConnector();

            ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE product_id = " + model.getValueAt(rowIndex, 0));

            if (rs.next()) {
                p_id = rs.getInt("product_id");
                getName.setText("" + rs.getString("product_name"));
                getPrice.setText("" + rs.getString("product_price"));
                getStock.setText("" + rs.getString("product_stock"));
                getCategory.setSelectedItem(rs.getString("product_category"));
                getStatus.setSelectedItem(rs.getString("product_status"));
                getDescription.setText("" + rs.getString("product_description"));
                EditgetImageFromDatabase = rs.getString("product_image");
                int height = 160;
                int width = 160;
                GetImage.displayImage(getPhoto, EditgetImageFromDatabase, height, width);
                sold = rs.getInt("total_sold");
            } else {
                JOptionPane.showMessageDialog(null, "Product details not found!");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void actionlogs_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionlogs_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_actionlogs_tableMouseClicked

    private void jLabel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseClicked
        clearAccountInfoFields(id1, fname1, email1, number1, address1, role1, status1, statusIcon1, photo1);
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel44MouseClicked

    private void searchBar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBar1KeyReleased
        search.searchResult(archive_accounts_table, searchBar1);
    }//GEN-LAST:event_searchBar1KeyReleased

    private void searchBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBar1MouseClicked

    private void searchBar1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBar1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBar1FocusLost

    private void archive_accounts_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archive_accounts_tableMouseClicked
        displaySelectedAccountInfo(archive_accounts_table, id1, fname1, email1, number1, address1, role1, status1, statusIcon1, photo1);
    }//GEN-LAST:event_archive_accounts_tableMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int rowIndex = archive_accounts_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a account first");
        } else {
            TableModel model = archive_accounts_table.getModel();
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
                String details = "User " + admin_id + " Successfully deleted the account " + id1 + "!";
                String action = "Delete Account";
                actionLogs.recordAdminLogs(admin_id, action, details);
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            int restoredAccountID = Integer.parseInt(id1.getText());
            String sql = "UPDATE tbl_accounts SET `status`='Pending' WHERE `account_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, restoredAccountID);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account has been retored Successfully!");
                    displayAccounts();
                    displayArchiveAccounts();
                    String action = "Restore";
                    String details = "User " + admin_id + " Successfully restored account " + restoredAccountID + " from archive!";
                    actionLogs.recordAdminLogs(admin_id, action, details);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to restore account!");
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_restoreActionPerformed

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
            String updateQuery = "UPDATE tbl_accounts SET `phone_number` = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, phone);
            pst.setInt(2, admin_id);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Phone number updated successfully.");
                displayAccountName();
                //logs
                String details = "User " + admin_id + " Successfully changed the phone number!";
                String action = "Change Number";
                actionLogs.recordAdminLogs(admin_id, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update Phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to Phone number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        String newAddress = JOptionPane.showInputDialog(null, "Enter your new address:");

        if (newAddress == null || newAddress.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a new address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Update the address in the database
            String updateQuery = "UPDATE tbl_accounts SET address = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, newAddress);
            pst.setInt(2, admin_id);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Address updated successfully.");
                displayAccountName();
                //logs
                String details = "User " + admin_id + " Successfully changed the address!";
                String action = "Change Address";
                actionLogs.recordAdminLogs(admin_id, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update address.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update address.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void changePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePasswordMouseClicked
        String oldPassword = JOptionPane.showInputDialog(null, "Enter your old password:");

        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your old password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean isExist = isAccountExist.checkPassword(oldPassword, admin_id);

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
            String updateQuery = "UPDATE tbl_accounts SET password = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, hashedNewPassword);
            pst.setInt(2, admin_id);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully.");
                //logs
                String details = "User " + admin_id + " Successfully changed the password!";
                String action = "Change Password";
                actionLogs.recordAdminLogs(admin_id, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_changePasswordMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        String email = JOptionPane.showInputDialog(null, "Enter your new email address:");

        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a new address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Update the address in the database
            String updateQuery = "UPDATE tbl_accounts SET email = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, email);
            pst.setInt(2, admin_id);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Email address updated successfully.");
                displayAccountName();
                //logs
                String details = "User " + admin_id + " Successfully changed the email!";
                String action = "Change Email";
                actionLogs.recordAdminLogs(admin_id, action, details);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update email address.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update address.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void managePhotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managePhotoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_managePhotoMouseEntered

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
                    String sql = "UPDATE tbl_accounts SET `profile_picture`=? WHERE account_id=?";
                    try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                        pst.setString(1, imagePath);
                        pst.setInt(2, admin_id);

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

    private void manageSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageSaveActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String account_id = id.getText();
            String rolee = (String) manageRole.getSelectedItem();

            sql = "UPDATE tbl_accounts SET role=? WHERE account_id=?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setString(1, rolee);
                pst.setString(2, account_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account Updated Successfully!");
                    displayAccounts();
                    displayAccountName();

                    //logs
                    String details = "User " + admin_id + " Successfully changed the role to " + rolee + "!";
                    String action = "Change Role";
                    actionLogs.recordAdminLogs(admin_id, action, details);

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
                sql = "INSERT INTO `tbl_accounts`(`email`, `first_name`, `last_name`, `phone_number`, `username`, `password`, `role`, `date_joined`, `status`, `address`, `profile_picture`) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?, ?)";
                pst = (PreparedStatement) dbc.getConnection().prepareStatement(sql);
                pst.setString(10, imagePath);
            } else {
                String defaultImage = "src/sampleProfiles/default profile 100x100.png";
                sql = "INSERT INTO `tbl_accounts`(`email`, `first_name`, `last_name`, `phone_number`, `username`, `password`, `role`, `date_joined`, `status`, `address`, `profile_picture`) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?, ?)";
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
            String details = "User " + admin_id + " Successfully added an account";
            String action = "Add account";
            actionLogs.recordAdminLogs(admin_id, action, details);
            editRole.setSelectedIndex(0);
            tabs.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addAccountsaveBtnActionPerformed

    private void editAccountSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAccountSaveBtnActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql;
            String account_id = id.getText();
            String statuss = status.getText();
            String roles = role.getText();

            String stats = (String) editStatus.getSelectedItem();
            String rolee = (String) editRole.getSelectedItem();

            sql = "UPDATE tbl_accounts SET status=?, role=? WHERE account_id=?";
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
                        details = "User " + admin_id + " Successfully changed the status and role of " + account_id + " to " + stats + " and " + rolee + "";
                        action = "Change Status & Role";
                    } else if (!stats.equals(statuss)) {
                        details = "User " + admin_id + " Successfully changed the status of " + account_id + " to " + stats + "";
                    } else if (!rolee.equals(roles)) {
                        details = "User " + admin_id + " Successfully changed the role of " + account_id + " to " + rolee + "";
                    }
                    actionLogs.recordAdminLogs(admin_id, action, details);

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

    private void filter_product_table2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_product_table2ActionPerformed
        sorter.searchResult(accounts_table, filter_product_table2);
    }//GEN-LAST:event_filter_product_table2ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        tabs.setSelectedIndex(2);
    }//GEN-LAST:event_addActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        search.searchResult(accounts_table, searchBar);
    }//GEN-LAST:event_searchBarKeyReleased

    private void searchBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBarMouseClicked
        searchBar.setFocusable(true);
        searchBar.requestFocusInWindow();
        if (searchBar.getText().isEmpty() || searchBar.getText().equals("  Search")) {
            searchBar.setText("");
            searchBar.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_searchBarMouseClicked

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().isEmpty()) {
            searchBar.setText("  Search");
            searchBar.setForeground(Color.decode("#8C8C8C"));
        }
    }//GEN-LAST:event_searchBarFocusLost

    private void accounts_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accounts_tableMouseClicked
        displaySelectedAccountInfo(accounts_table, id, fname, email, number, address, role, status, statusIcon, photo);
    }//GEN-LAST:event_accounts_tableMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        try {
            int rowIndex = accounts_table.getSelectedRow();
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(null, "Please Select an Item!");
            } else {
                TableModel model = accounts_table.getModel();
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id = " + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    int height = 100;
                    int width = 100;
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
                    lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);

                    fullname.setText(firstName + " " + lastName);
                    displayStatus.setText(rs.getString("status"));
                    editStatus.setSelectedItem(rs.getString("status"));
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

    private void add2archiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2archiveActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            int account_id = Integer.parseInt(id.getText());
            String sql = "UPDATE tbl_accounts SET `status`='Archived' WHERE `account_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, account_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Account added to archive Successfully!");
                    displayAccounts();
                    displayArchiveAccounts();

                    String action = "Archive";
                    String details = "User " + admin_id + " Successfully put account " + account_id + " to archive!";
                    actionLogs.recordAdminLogs(admin_id, action, details);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add the account to archive!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_add2archiveActionPerformed

    private void message4admin_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_message4admin_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_message4admin_tableMouseClicked

    private void message_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_message_search_barMouseClicked
        message_search_bar.setFocusable(true);
        message_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_message_search_barMouseClicked

    private void message_search_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_message_search_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_message_search_barActionPerformed

    private void message_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_message_search_barKeyReleased
        search.searchResult(message4admin_table, message_search_bar);
    }//GEN-LAST:event_message_search_barKeyReleased

    private void filter_product_table1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_product_table1ActionPerformed
        sorter.searchResult(message4admin_table, filter_product_table1);
    }//GEN-LAST:event_filter_product_table1ActionPerformed

    private void messages2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messages2ActionPerformed
        messages1.setSelected(false);
        messages2.setSelected(true);
        messages3.setSelected(false);
        tabs.setSelectedIndex(6);
    }//GEN-LAST:event_messages2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tabs.setSelectedIndex(7);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void product_search_bar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_search_bar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_product_search_bar1MouseClicked

    private void product_search_bar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_product_search_bar1KeyReleased
        search.searchResult(actionlogs_table, product_search_bar1);
    }//GEN-LAST:event_product_search_bar1KeyReleased

    private void filter_product_table3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_product_table3ActionPerformed
        sorter.searchResult(actionlogs_table, filter_product_table3);
    }//GEN-LAST:event_filter_product_table3ActionPerformed

    private void messages1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messages1ActionPerformed
        messages1.setSelected(true);
        messages2.setSelected(false);
        messages3.setSelected(false);
        tabs.setSelectedIndex(9);
    }//GEN-LAST:event_messages1ActionPerformed

    private void messages3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messages3ActionPerformed
        messages1.setSelected(false);
        messages2.setSelected(false);
        messages3.setSelected(true);
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_messages3ActionPerformed

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel50MouseClicked
    private static int p_id;
    private static int sold = 0;

    private void removeErrorsMessage() {
        //edit
        desError2.setText("");
        desError3.setText("");
        desError5.setText("");
        desError6.setText("");
        desError7.setText("");
    }

    private void emptyValues() {
        getName.setText("");
        getPrice.setText("");
        getStock.setText("");
        getStatus.setSelectedIndex(0);
        getDescription.setText("");
        getPhoto.setIcon(null);
    }

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
    private javax.swing.JLabel account_archive_is_empty;
    private javax.swing.JTable accounts_table;
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
    private javax.swing.JPanel archiveAccountTableContainer1;
    private javax.swing.JScrollPane archiveAccountTableContainerScroll;
    private javax.swing.JScrollPane archiveAccountTableContainerScroll1;
    private javax.swing.JTable archive_accounts_table;
    private javax.swing.JLabel archive_product_is_empty;
    private javax.swing.JTable archive_products_table;
    private javax.swing.JTextField archive_search_bar;
    private javax.swing.JPanel c1;
    private javax.swing.JPanel c10;
    private javax.swing.JPanel c11;
    private javax.swing.JPanel c12;
    private javax.swing.JPanel c13;
    private javax.swing.JPanel c14;
    private javax.swing.JPanel c2;
    private javax.swing.JPanel c3;
    private javax.swing.JPanel c4;
    private javax.swing.JPanel c5;
    private javax.swing.JPanel c6;
    private javax.swing.JPanel c7;
    private javax.swing.JPanel c8;
    private javax.swing.JPanel c9;
    private javax.swing.JLabel changePassword;
    private javax.swing.JPanel dashboardContainer;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete1;
    private javax.swing.JLabel desError2;
    private javax.swing.JLabel desError3;
    private javax.swing.JLabel desError5;
    private javax.swing.JLabel desError6;
    private javax.swing.JLabel desError7;
    private javax.swing.JEditorPane descript;
    private javax.swing.JLabel displayImage;
    private javax.swing.JLabel displayPhoto;
    private javax.swing.JLabel displayStatus;
    private javax.swing.JButton edit;
    private javax.swing.JButton editAccountSaveBtn;
    private javax.swing.JLabel editNameError;
    private javax.swing.JLabel editPriceError;
    private javax.swing.JPanel editProfileContainer;
    private javax.swing.JPanel editProfileContainer1;
    private javax.swing.JComboBox<String> editRole;
    private javax.swing.JComboBox<String> editStatus;
    private javax.swing.JLabel editStockError;
    private javax.swing.JButton edit_product_save_button;
    private javax.swing.JTextField em;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JPanel filterContainer;
    private javax.swing.JPanel filterContainer1;
    private javax.swing.JPanel filterContainer3;
    private javax.swing.JPanel filterContainer4;
    private javax.swing.JComboBox<String> filter_product_table;
    private javax.swing.JComboBox<String> filter_product_table1;
    private javax.swing.JComboBox<String> filter_product_table2;
    private javax.swing.JComboBox<String> filter_product_table3;
    private javax.swing.JTextField first;
    private javax.swing.JLabel fname;
    private javax.swing.JLabel fname1;
    private javax.swing.JLabel fullname;
    private javax.swing.JComboBox<String> getCategory;
    private javax.swing.JEditorPane getDescription;
    private javax.swing.JTextField getName;
    private javax.swing.JLabel getPhoto;
    private javax.swing.JTextField getPrice;
    private javax.swing.JComboBox<String> getStatus;
    private javax.swing.JTextField getStock;
    private javax.swing.JLabel id;
    private javax.swing.JLabel id1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel74;
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
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField last;
    private javax.swing.JButton logoutBtn;
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
    private javax.swing.JLabel manage23;
    private javax.swing.JLabel manage24;
    private javax.swing.JLabel manage25;
    private javax.swing.JLabel manage26;
    private javax.swing.JLabel manage27;
    private javax.swing.JLabel manage28;
    private javax.swing.JLabel manage29;
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
    private javax.swing.JTable message4admin_table;
    private javax.swing.JLabel message_is_empty;
    private javax.swing.JTextField message_search_bar;
    private javax.swing.JLabel messages0;
    private javax.swing.JToggleButton messages1;
    private javax.swing.JToggleButton messages2;
    private javax.swing.JToggleButton messages3;
    private javax.swing.JLabel myprofile4;
    private javax.swing.JLabel myprofile5;
    private javax.swing.JLabel myprofile6;
    private javax.swing.JLabel number;
    private javax.swing.JLabel number1;
    private javax.swing.JLabel optional;
    private javax.swing.JTextField pass;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel photo1;
    private javax.swing.JLabel productID;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPhoto;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productQuantity;
    private javax.swing.JLabel productStatus;
    private javax.swing.JLabel product_is_empty;
    private javax.swing.JTextField product_search_bar;
    private javax.swing.JTextField product_search_bar1;
    private javax.swing.JTable product_table;
    private javax.swing.JButton product_table_archive_button;
    private javax.swing.JButton product_table_delete_button;
    private javax.swing.JButton product_table_edit_button;
    private javax.swing.JPanel productsContainer;
    private javax.swing.JButton replacebtn;
    private javax.swing.JButton restore;
    private javax.swing.JButton restore1;
    private javax.swing.JLabel role;
    private javax.swing.JLabel role1;
    private javax.swing.JComboBox<String> roles;
    private javax.swing.JScrollPane scrollBar;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTextField searchBar1;
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
