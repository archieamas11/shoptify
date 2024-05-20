package Seller;

import accounts.Login;
import static accounts.Login.accountId;
import accounts.UserManager;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.actionLogs;
import config.databaseConnector;
import config.search;
import config.flatlaftTable;
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
        dashboard.setSelected(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30)); //jframe rounded border
        flatlaftTable.design(productsContainer, product_table, jScrollPane5); //seller logs
        flatlaftTable.design(productsContainer1, actionlogs_table, jScrollPane9); //profile
        flatlaftTable.design(productsContainer, product_table, jScrollPane5); // product table
        flatlaftTable.design(messages_container, messages_table, jScrollPane10); // messages table
        flatlaftTable.design(productsContainer2, purchase_table, jScrollPane7); // order table

        actionLogs.displaySellerLogs(actionlogs_table, sellerID); // display seller logs table
        displayCurrentDate(); // display current date in dashboard
        displayData(); //display Products
        displayRatingSumAndCount(); //display seller rating
        displayArchive(); // display archive table
        displayPurchase(); // display orders table
        messages(); // display messages for seller

        //display total sales for seller dashboard
        displayTotalSales(sellerID);
        displayTotalProducts(sellerID);
        displayTotalOrders(sellerID);
        displayTotalPendingOrders(sellerID);
        //

        //ROUNDED CORNERS
        // dashboard & buttons
        UXmethods.RoundBorders.setArcStyle(logout, 50);
        UXmethods.RoundBorders.setArcStyle(dashboard, 50);
        UXmethods.RoundBorders.setArcStyle(manage, 50);
        UXmethods.RoundBorders.setArcStyle(accounts, 50);
        UXmethods.RoundBorders.setArcStyle(orders, 50);
        UXmethods.RoundBorders.setArcStyle(archiveBtn, 50);
        UXmethods.RoundBorders.setArcStyle(logsBtn, 50);
        //Containers
        UXmethods.RoundBorders.setArcStyle(CONTAINER, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER2, 15);
        UXmethods.RoundBorders.setArcStyle(CONTAINER3, 15);
        //

        //profile tab
        UXmethods.RoundBorders.setArcStyle(addContainer4, 20);
        UXmethods.RoundBorders.setArcStyle(messages_container, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer5, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer6, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer7, 20);
        UXmethods.RoundBorders.setArcStyle(deactivate_jpanel, 10);
        UXmethods.RoundBorders.setArcStyle(c1, 10);
        UXmethods.RoundBorders.setArcStyle(c2, 10);
        UXmethods.RoundBorders.setArcStyle(c3, 10);
        UXmethods.RoundBorders.setArcStyle(c4, 10);
        UXmethods.RoundBorders.setArcStyle(c5, 10);
        UXmethods.RoundBorders.setArcStyle(z6, 10);
        c1.setFocusable(false);
        c2.setFocusable(false);
        c3.setFocusable(false);
        c4.setFocusable(false);
        c5.setFocusable(false);
        z6.setFocusable(false);

        UXmethods.RoundBorders.setArcStyle(edit_seller_upload_button, 10);
        UXmethods.RoundBorders.setArcStyle(edit_seller_remove_button, 10);
        UXmethods.RoundBorders.setArcStyle(edit_seller_close_button, 5);
        UXmethods.RoundBorders.setArcStyle(edit_seller_save_button, 5);
        UXmethods.RoundBorders.setArcStyle(activity_search_bar, 10);
        UXmethods.RoundBorders.setArcStyle(filterContainer1, 10);

        UXmethods.RoundBorders.setArcStyle(add1, 10);
        UXmethods.RoundBorders.setArcStyle(add2, 10);
        UXmethods.RoundBorders.setArcStyle(add3, 10);
        UXmethods.RoundBorders.setArcStyle(add4, 10);
        UXmethods.RoundBorders.setArcStyle(add5, 10);

        UXmethods.RoundBorders.setArcStyle(submit, 10);

        //orders table
        UXmethods.RoundBorders.setArcStyle(orders_summary_container, 20);
        UXmethods.RoundBorders.setArcStyle(orders_summary_container1, 20);
        UXmethods.RoundBorders.setArcStyle(orders_summary_container2, 20);
        UXmethods.RoundBorders.setArcStyle(orders_summary_container3, 20);
        UXmethods.RoundBorders.setArcStyle(jPanel21, 10);
        UXmethods.RoundBorders.setArcStyle(decline, 10);
        UXmethods.RoundBorders.setArcStyle(accept_order, 10);
        UXmethods.RoundBorders.setArcStyle(sortContainer1, 10);
        UXmethods.RoundBorders.setArcStyle(jButton2, 10);
        UXmethods.RoundBorders.setArcStyle(filterContainer2, 10);
        UXmethods.RoundBorders.setArcStyle(product_search_bar1, 10);

        //product table
        UXmethods.RoundBorders.setArcStyle(product_search_bar, 10);
        UXmethods.RoundBorders.setArcStyle(filterContainer, 10);
        UXmethods.RoundBorders.setArcStyle(sortContainer, 10);

        UXmethods.RoundBorders.setArcStyle(archive, 10);
        UXmethods.RoundBorders.setArcStyle(editButton, 10);
        UXmethods.RoundBorders.setArcStyle(add, 10);

        UXmethods.RoundBorders.setArcStyle(removetbn, 10);
        UXmethods.RoundBorders.setArcStyle(replacebtn, 10);
        UXmethods.RoundBorders.setArcStyle(editbtn_manage, 10);

        UXmethods.RoundBorders.setArcStyle(jPanel15, 20);
        UXmethods.RoundBorders.setArcStyle(jPanel13, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer, 20);
        UXmethods.RoundBorders.setArcStyle(addContainer1, 20);

        UXmethods.RoundBorders.setArcStyle(jPanel23, 10);

        UXmethods.RoundBorders.setArcStyle(getName, 10);
        UXmethods.RoundBorders.setArcStyle(getCategory, 10);
        UXmethods.RoundBorders.setArcStyle(getPrice, 10);
        UXmethods.RoundBorders.setArcStyle(getStock, 10);
        UXmethods.RoundBorders.setArcStyle(jScrollPane4, 10);
        UXmethods.RoundBorders.setArcStyle(getStatus, 10);

        UXmethods.RoundBorders.setArcStyle(add_category, 10);
        UXmethods.RoundBorders.setArcStyle(addPrice, 10);
        UXmethods.RoundBorders.setArcStyle(addCategory, 10);
        UXmethods.RoundBorders.setArcStyle(jScrollPane8, 10);
        UXmethods.RoundBorders.setArcStyle(addStock, 10);
        UXmethods.RoundBorders.setArcStyle(add_save, 10);
        UXmethods.RoundBorders.setArcStyle(addReplace, 10);
        UXmethods.RoundBorders.setArcStyle(addRemove, 10);
        UXmethods.RoundBorders.setArcStyle(jPanel24, 10);
        //

    }

    private void displayCurrentDate() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);

        todaysDate.setText(formattedDate);
    }

    private void displayRatingSumAndCount() {
        int sum_star = 0;
        int seller_count = 0;

        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT SUM(total_star) as total_star, COUNT(*) as total_rating FROM tbl_rating4seller WHERE seller_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, sellerID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                sum_star = rs.getInt("total_star");
                seller_count = rs.getInt("total_rating");
                float rating = (float) sum_star / seller_count;
                seller_rating.setText(String.format("%.1f (%d)", rating, seller_count));
            }

            rs.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void displayTotalOrders(int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT COUNT(*) as TotalOrders FROM tbl_orders WHERE order_status = 'Accepted' AND seller_id = ?";
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

    private void displayShopName(JLabel shopname) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT shop_name FROM tbl_accounts WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, sellerID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                shopname.setText(rs.getString("shop_name"));
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
            String query = "SELECT COUNT(*) as TotalPending FROM tbl_orders WHERE order_status = 'Pending' AND seller_id = ?";
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
            String query = "SELECT COUNT(*) as TotalProducts FROM tbl_products WHERE product_status = 'Available' AND seller_id = ?";
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
            String query = "SELECT SUM(total_price) as TotalSales FROM tbl_orders WHERE order_status = 'Accepted' AND seller_id = ?";
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

    public void displayPurchase() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT `order_id` as `Sales ID`, `buyer_id` as `Buyer ID`, `seller_id` as `Seller ID`, `product_id` as `Product ID`, `order_status` as `Order Status`, `date_purchase` as `Date Purchase`  FROM tbl_orders WHERE seller_id = ?";
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

    public void messages() {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT * FROM tbl_messages4seller WHERE seller_id = ?");
            pstmt.setInt(1, sellerID);
            ResultSet rs = pstmt.executeQuery();

            messages_table.setModel(DbUtils.resultSetToTableModel(rs));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            messages_table.setDefaultRenderer(Object.class, centerRenderer);

            rs.close();
            pstmt.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayData() {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT `product_id` as `Product ID`, `seller_id` as `Seller ID`, `product_name`, `product_price`, `product_stock`, `product_category` as `Category`, `product_status`, `date_created` FROM tbl_products WHERE status IN ('Available', 'Not Available') AND seller_id = ?");
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
            PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT * FROM tbl_products WHERE product_status IN ('Archived') AND seller_id = ?");
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
        profile = new javax.swing.JLabel();
        dashboard = new javax.swing.JToggleButton();
        manage = new javax.swing.JToggleButton();
        orders = new javax.swing.JToggleButton();
        accounts = new javax.swing.JToggleButton();
        archiveBtn = new javax.swing.JToggleButton();
        logsBtn = new javax.swing.JToggleButton();
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
        jLabel9 = new javax.swing.JLabel();
        totalLoss = new javax.swing.JLabel();
        CONTAINER = new javax.swing.JPanel();
        CONTAINER1 = new javax.swing.JPanel();
        CONTAINER2 = new javax.swing.JPanel();
        CONTAINER3 = new javax.swing.JPanel();
        CONTAINER5 = new javax.swing.JPanel();
        pendingOrders = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalOrders = new javax.swing.JLabel();
        totalProducts = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        todaysDate = new javax.swing.JLabel();
        overviewTotalSales = new javax.swing.JLabel();
        overviewTotalLoss1 = new javax.swing.JLabel();
        overviewTotalLoss2 = new javax.swing.JLabel();
        overviewTotalLoss3 = new javax.swing.JLabel();
        overviewTotalLoss4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        productsContainer = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        product_table_search_icon = new javax.swing.JLabel();
        product_search_bar = new javax.swing.JTextField();
        sortContainer = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        filterContainer = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        add = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        archive = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
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
        productsContainer2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        purchase_table = new javax.swing.JTable();
        product_table_search_icon1 = new javax.swing.JLabel();
        product_search_bar1 = new javax.swing.JTextField();
        sortContainer1 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        filterContainer2 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
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
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        display_photo = new javax.swing.JLabel();
        seller_full_name = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        seller_rating = new javax.swing.JLabel();
        seperator = new javax.swing.JPanel();
        submit = new javax.swing.JButton();
        addContainer4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        seller_address = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        username = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        seller_phone = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        seller_email = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        seller_store = new javax.swing.JLabel();
        bacgkround = new javax.swing.JLabel();
        productsContainer1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        actionlogs_table = new javax.swing.JTable();
        activity_search_icon = new javax.swing.JLabel();
        activity_search_bar = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        filterContainer1 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        add2 = new javax.swing.JButton();
        messages_container = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        messages_table = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        add1 = new javax.swing.JButton();
        add3 = new javax.swing.JButton();
        add4 = new javax.swing.JButton();
        add5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        addContainer1 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel24 = new javax.swing.JPanel();
        addPhoto = new javax.swing.JLabel();
        addReplace = new javax.swing.JButton();
        addRemove = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        addContainer = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        add_category = new javax.swing.JComboBox<>();
        addCategory = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        add_save = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        addName = new javax.swing.JTextField();
        addPrice = new javax.swing.JTextField();
        addStock = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        addDescription = new javax.swing.JEditorPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        getStatus = new javax.swing.JComboBox<>();
        getCategory = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        editbtn_manage = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        getName = new javax.swing.JTextField();
        getPrice = new javax.swing.JTextField();
        getStock = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        getDescription = new javax.swing.JEditorPane();
        jPanel15 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel23 = new javax.swing.JPanel();
        getPhoto = new javax.swing.JLabel();
        replacebtn = new javax.swing.JButton();
        removetbn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        display_photo1 = new javax.swing.JLabel();
        seperator1 = new javax.swing.JPanel();
        addContainer5 = new javax.swing.JPanel();
        seller_address1 = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        seller_phone1 = new javax.swing.JLabel();
        seller_email1 = new javax.swing.JLabel();
        seller_store1 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        addName2 = new javax.swing.JTextField();
        addName3 = new javax.swing.JTextField();
        addName4 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        deactivate_jpanel = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        submit6 = new javax.swing.JButton();
        edit_seller_close_button = new javax.swing.JButton();
        edit_seller_save_button = new javax.swing.JButton();
        addName8 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        c4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        c5 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        z6 = new javax.swing.JTextField();
        bacgkround1 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        addContainer6 = new javax.swing.JPanel();
        seller_address2 = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        seller_phone2 = new javax.swing.JLabel();
        seller_email2 = new javax.swing.JLabel();
        seller_store2 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        edit_seller_remove_button = new javax.swing.JButton();
        edit_seller_upload_button = new javax.swing.JButton();
        addContainer7 = new javax.swing.JPanel();
        seller_address3 = new javax.swing.JLabel();
        username3 = new javax.swing.JLabel();
        seller_phone3 = new javax.swing.JLabel();
        seller_email3 = new javax.swing.JLabel();
        seller_store3 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        addName5 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        addName6 = new javax.swing.JTextField();
        addName7 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        c1 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        c3 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        c2 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        orders_summary_container2 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        orders_summary_container = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        orders_summary_container3 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        orders_summary_container1 = new javax.swing.JPanel();
        decline = new javax.swing.JButton();
        accept_order = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
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
        dashboardContainer.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 50, 50));
        dashboardContainer.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 50, 20));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/2.png"))); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        dashboardContainer.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 50));

        dashboard.setBackground(new java.awt.Color(153, 204, 255));
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-dashboard-24.png"))); // NOI18N
        dashboard.setBorderPainted(false);
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        dashboardContainer.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 50, 50));

        manage.setBackground(new java.awt.Color(153, 204, 255));
        manage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-edit-24.png"))); // NOI18N
        manage.setBorderPainted(false);
        manage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageMouseClicked(evt);
            }
        });
        manage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageActionPerformed(evt);
            }
        });
        dashboardContainer.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 50, 50));

        orders.setBackground(new java.awt.Color(153, 204, 255));
        orders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-buy-24.png"))); // NOI18N
        orders.setBorderPainted(false);
        orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersMouseClicked(evt);
            }
        });
        orders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersActionPerformed(evt);
            }
        });
        dashboardContainer.add(orders, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 50, 50));

        accounts.setBackground(new java.awt.Color(153, 204, 255));
        accounts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-user-locked-24.png"))); // NOI18N
        accounts.setBorderPainted(false);
        accounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountsMouseClicked(evt);
            }
        });
        accounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountsActionPerformed(evt);
            }
        });
        dashboardContainer.add(accounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 50, 50));

        archiveBtn.setBackground(new java.awt.Color(153, 204, 255));
        archiveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-archive-24.png"))); // NOI18N
        archiveBtn.setBorderPainted(false);
        archiveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archiveBtnMouseClicked(evt);
            }
        });
        archiveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(archiveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 50, 50));

        logsBtn.setBackground(new java.awt.Color(153, 204, 255));
        logsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/activity_logs.png"))); // NOI18N
        logsBtn.setBorderPainted(false);
        logsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logsBtnMouseClicked(evt);
            }
        });
        logsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsBtnActionPerformed(evt);
            }
        });
        dashboardContainer.add(logsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 50, 50));

        jPanel5.add(dashboardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 720));

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
        totalSales.setText("₱  0");
        jPanel6.add(totalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Sales");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, 20));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Loss");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, 20));

        totalLoss.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalLoss.setForeground(new java.awt.Color(255, 255, 255));
        totalLoss.setText("₱  0");
        jPanel6.add(totalLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, -1));

        CONTAINER.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CONTAINER1.setBackground(new java.awt.Color(241, 241, 241));
        CONTAINER1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        CONTAINER.add(CONTAINER1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 420, 250));

        jPanel6.add(CONTAINER, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, 310, 260));

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
        pendingOrders.setForeground(new java.awt.Color(255, 255, 255));
        pendingOrders.setText("0");
        jPanel6.add(pendingOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pending Orders");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 370, -1, 20));

        totalOrders.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalOrders.setForeground(new java.awt.Color(255, 255, 255));
        totalOrders.setText("0");
        jPanel6.add(totalOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 330, -1, -1));

        totalProducts.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        totalProducts.setForeground(new java.awt.Color(255, 255, 255));
        totalProducts.setText("0");
        jPanel6.add(totalProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Products");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, -1, 20));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Total Orders");
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 370, -1, 20));

        todaysDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        todaysDate.setForeground(new java.awt.Color(102, 102, 102));
        todaysDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        todaysDate.setText("Date");
        todaysDate.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(todaysDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 70, -1));

        overviewTotalSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalsales_1.png"))); // NOI18N
        jPanel6.add(overviewTotalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 280, 80));

        overviewTotalLoss1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalLoss.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 280, 80));

        overviewTotalLoss2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pendingOrders.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 320, 170, 80));

        overviewTotalLoss3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalProducts.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 170, 80));

        overviewTotalLoss4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/totalOrders.png"))); // NOI18N
        jPanel6.add(overviewTotalLoss4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, 170, 80));

        tabs.addTab("tab1", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productsContainer.setBackground(new java.awt.Color(255, 51, 51));
        productsContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        product_table.setAutoCreateRowSorter(true);
        product_table.setBackground(new java.awt.Color(241, 241, 241));
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

        productsContainer.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 540));

        product_table_search_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search_icon.png"))); // NOI18N
        productsContainer.add(product_table_search_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 40));

        product_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        product_search_bar.setText("           Search");
        product_search_bar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                product_search_barFocusLost(evt);
            }
        });
        product_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_search_barMouseClicked(evt);
            }
        });
        product_search_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_search_barActionPerformed(evt);
            }
        });
        product_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                product_search_barKeyReleased(evt);
            }
        });
        productsContainer.add(product_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        sortContainer.setBackground(new java.awt.Color(255, 255, 255));
        sortContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setBackground(new java.awt.Color(241, 241, 241));
        jLabel36.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Sort by:");
        sortContainer.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Best selling", "Sold", "Rating" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sortContainer.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer.add(sortContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 180, 40));

        jLabel17.setBackground(new java.awt.Color(241, 241, 241));
        jLabel17.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Product Table");
        productsContainer.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setBackground(new java.awt.Color(241, 241, 241));
        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Filter by:");
        filterContainer.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Category", "Status" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filterContainer.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer.add(filterContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        add.setBackground(new java.awt.Color(0, 158, 226));
        add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add product  +");
        add.setBorderPainted(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        productsContainer.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 130, 40));

        editButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editButton.setForeground(new java.awt.Color(51, 51, 51));
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        productsContainer.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 130, 40));

        archive.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        archive.setForeground(new java.awt.Color(51, 51, 51));
        archive.setText("Add to archive");
        archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveActionPerformed(evt);
            }
        });
        productsContainer.add(archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 130, 40));

        jPanel8.add(productsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1150, 620));

        jLabel31.setBackground(new java.awt.Color(241, 241, 241));
        jLabel31.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 204, 204));
        jLabel31.setText("Manage Product  >");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel26.setBackground(new java.awt.Color(241, 241, 241));
        jLabel26.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Product Table");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 30));

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

        productsContainer2.setBackground(new java.awt.Color(255, 51, 51));
        productsContainer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        purchase_table.setAutoCreateRowSorter(true);
        purchase_table.setBackground(new java.awt.Color(241, 241, 241));
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
        jScrollPane7.setViewportView(purchase_table);

        productsContainer2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 540));

        product_table_search_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search_icon.png"))); // NOI18N
        productsContainer2.add(product_table_search_icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 40));

        product_search_bar1.setForeground(new java.awt.Color(140, 140, 140));
        product_search_bar1.setText("           Search");
        product_search_bar1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                product_search_bar1FocusLost(evt);
            }
        });
        product_search_bar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_search_bar1MouseClicked(evt);
            }
        });
        product_search_bar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_search_bar1ActionPerformed(evt);
            }
        });
        product_search_bar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                product_search_bar1KeyReleased(evt);
            }
        });
        productsContainer2.add(product_search_bar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        sortContainer1.setBackground(new java.awt.Color(255, 255, 255));
        sortContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel75.setBackground(new java.awt.Color(241, 241, 241));
        jLabel75.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Sort by:");
        sortContainer1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Pending", "Accept", "Decline", " " }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sortContainer1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer2.add(sortContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 180, 40));

        jLabel79.setBackground(new java.awt.Color(241, 241, 241));
        jLabel79.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jLabel79.setText("Product Table");
        productsContainer2.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer2.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel80.setBackground(new java.awt.Color(241, 241, 241));
        jLabel80.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
        jLabel80.setText("Filter by:");
        filterContainer2.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jComboBox5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Category" }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filterContainer2.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer2.add(filterContainer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        jButton2.setBackground(new java.awt.Color(0, 158, 226));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("View order");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        productsContainer2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 160, 40));

        s.add(productsContainer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1150, 620));

        jLabel81.setBackground(new java.awt.Color(241, 241, 241));
        jLabel81.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(204, 204, 204));
        jLabel81.setText("Manage Orders  >");
        s.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel82.setBackground(new java.awt.Color(241, 241, 241));
        jLabel82.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
        jLabel82.setText("Orders Table");
        s.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 30));

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

        jLabel54.setBackground(new java.awt.Color(241, 241, 241));
        jLabel54.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(204, 204, 204));
        jLabel54.setText("My Profile  >");
        jPanel11.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel55.setBackground(new java.awt.Color(241, 241, 241));
        jLabel55.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("Your activities");
        jPanel11.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, 30));

        jLabel56.setBackground(new java.awt.Color(241, 241, 241));
        jLabel56.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setText("View Profile");
        jPanel11.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, 30));

        display_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        display_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default_seller_profile.png"))); // NOI18N
        jPanel11.add(display_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 150));

        seller_full_name.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        seller_full_name.setForeground(new java.awt.Color(51, 51, 51));
        seller_full_name.setText("Archie Albarico");
        jPanel11.add(seller_full_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 162, 140, 50));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/star_rating.png"))); // NOI18N
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 70, 40));

        seller_rating.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_rating.setForeground(new java.awt.Color(153, 153, 153));
        seller_rating.setText("5.0 (12)");
        jPanel11.add(seller_rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 190, 70, 40));

        seperator.setBackground(new java.awt.Color(241, 241, 241));
        seperator.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submit.setBackground(new java.awt.Color(0, 158, 226));
        submit.setForeground(new java.awt.Color(255, 255, 255));
        submit.setText("Edit profile");
        submit.setBorderPainted(false);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        seperator.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 110, 40));

        jPanel11.add(seperator, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 490, 60));

        addContainer4.setBackground(new java.awt.Color(241, 241, 241));
        addContainer4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/location.png"))); // NOI18N
        addContainer4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        seller_address.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));
        addContainer4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 490, 20));

        username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/phone.png"))); // NOI18N
        addContainer4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 50, 30));

        seller_phone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/email.png"))); // NOI18N
        addContainer4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 50, 30));

        seller_email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/store.png"))); // NOI18N
        addContainer4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 50, 30));

        seller_store.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store.setForeground(new java.awt.Color(153, 153, 153));
        addContainer4.add(seller_store, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jPanel11.add(addContainer4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 490, 180));

        bacgkround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/background.png"))); // NOI18N
        jPanel11.add(bacgkround, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        productsContainer1.setBackground(new java.awt.Color(255, 51, 51));
        productsContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        actionlogs_table.setAutoCreateRowSorter(true);
        actionlogs_table.setBackground(new java.awt.Color(241, 241, 241));
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
        jScrollPane9.setViewportView(actionlogs_table);

        productsContainer1.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1150, 160));

        activity_search_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search_icon.png"))); // NOI18N
        productsContainer1.add(activity_search_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 40));

        activity_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        activity_search_bar.setText("          Search");
        activity_search_bar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                activity_search_barFocusLost(evt);
            }
        });
        activity_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activity_search_barMouseClicked(evt);
            }
        });
        activity_search_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activity_search_barActionPerformed(evt);
            }
        });
        activity_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                activity_search_barKeyReleased(evt);
            }
        });
        productsContainer1.add(activity_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel33.setBackground(new java.awt.Color(241, 241, 241));
        jLabel33.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Product Table");
        productsContainer1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer1.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setBackground(new java.awt.Color(241, 241, 241));
        jLabel34.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Filter by:");
        filterContainer1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Category", "Status" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filterContainer1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer1.add(filterContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        add2.setBackground(new java.awt.Color(0, 158, 226));
        add2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add2.setForeground(new java.awt.Color(255, 255, 255));
        add2.setText("Print");
        add2.setBorderPainted(false);
        add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2ActionPerformed(evt);
            }
        });
        productsContainer1.add(add2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 130, 40));

        jPanel11.add(productsContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 1150, 230));

        messages_container.setBackground(new java.awt.Color(241, 241, 241));
        messages_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        messages_table.setAutoCreateRowSorter(true);
        messages_table.setBackground(new java.awt.Color(241, 241, 241));
        messages_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        messages_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messages_tableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(messages_table);

        messages_container.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 640, 260));

        jLabel40.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Messages");
        messages_container.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, -1));

        add1.setBackground(new java.awt.Color(0, 158, 226));
        add1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add1.setForeground(new java.awt.Color(255, 255, 255));
        add1.setText("Mark as read");
        add1.setBorderPainted(false);
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1ActionPerformed(evt);
            }
        });
        messages_container.add(add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 120, 40));

        add3.setBackground(new java.awt.Color(0, 158, 226));
        add3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add3.setForeground(new java.awt.Color(255, 255, 255));
        add3.setText("Reply");
        add3.setBorderPainted(false);
        add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add3ActionPerformed(evt);
            }
        });
        messages_container.add(add3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 120, 40));

        add4.setBackground(new java.awt.Color(0, 158, 226));
        add4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add4.setForeground(new java.awt.Color(255, 255, 255));
        add4.setText("Delete");
        add4.setBorderPainted(false);
        add4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add4ActionPerformed(evt);
            }
        });
        messages_container.add(add4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 90, 40));

        add5.setBackground(new java.awt.Color(0, 158, 226));
        add5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add5.setForeground(new java.awt.Color(255, 255, 255));
        add5.setText("Archive");
        add5.setBorderPainted(false);
        add5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add5ActionPerformed(evt);
            }
        });
        messages_container.add(add5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 100, 40));

        jPanel11.add(messages_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 640, 340));

        tabs.addTab("tab6", jPanel11);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setBackground(new java.awt.Color(241, 241, 241));
        jLabel45.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(204, 204, 204));
        jLabel45.setText("Manage Product  >");
        jPanel9.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel46.setBackground(new java.awt.Color(241, 241, 241));
        jLabel46.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Add Product");
        jPanel9.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 30));

        addContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        addContainer1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, 30));

        jPanel24.setBackground(new java.awt.Color(255, 204, 204));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel24.add(addPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 290, 200));

        addContainer1.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 390, 200));

        addReplace.setBackground(new java.awt.Color(0, 158, 226));
        addReplace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addReplace.setForeground(new java.awt.Color(255, 255, 255));
        addReplace.setText("Replace");
        addReplace.setBorderPainted(false);
        addReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReplaceActionPerformed(evt);
            }
        });
        addContainer1.add(addReplace, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 190, 40));

        addRemove.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addRemove.setForeground(new java.awt.Color(51, 51, 51));
        addRemove.setText("Remove");
        addRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRemoveActionPerformed(evt);
            }
        });
        addContainer1.add(addRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 190, 40));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Product Image");
        addContainer1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel47.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Add Product");
        addContainer1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel48.setBackground(new java.awt.Color(241, 241, 241));
        jLabel48.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(153, 153, 153));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Set the product thumbnail image. Only *.png, *.jpeg, *.jpg files are accepted.");
        addContainer1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 390, 30));

        jPanel9.add(addContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 410, 470));

        addContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Stock");
        addContainer.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        add_category.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add_category.setForeground(new java.awt.Color(153, 153, 153));
        add_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        add_category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_categoryMouseClicked(evt);
            }
        });
        addContainer.add(add_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 320, 50));

        addCategory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addCategory.setForeground(new java.awt.Color(153, 153, 153));
        addCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Fashion", "Grocery", "Pet Supplies" }));
        addCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCategoryMouseClicked(evt);
            }
        });
        addContainer.add(addCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 320, 50));

        jLabel38.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Category");
        addContainer.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        add_save.setBackground(new java.awt.Color(0, 158, 226));
        add_save.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add_save.setForeground(new java.awt.Color(255, 255, 255));
        add_save.setText("Save");
        add_save.setBorderPainted(false);
        add_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_saveActionPerformed(evt);
            }
        });
        addContainer.add(add_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 270, 40));

        jLabel49.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Price");
        addContainer.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel50.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Status");
        addContainer.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel51.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Description");
        addContainer.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel52.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Product Name");
        addContainer.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel53.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("General infromation");
        addContainer.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        addContainer.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 720, 30));

        addName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName.setForeground(new java.awt.Color(153, 153, 153));
        addName.setText("PlayStation 5");
        addName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNameMouseClicked(evt);
            }
        });
        addContainer.add(addName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 660, 50));

        addPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addPrice.setForeground(new java.awt.Color(153, 153, 153));
        addPrice.setText("₱");
        addPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPriceMouseClicked(evt);
            }
        });
        addContainer.add(addPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, 50));

        addStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addStock.setForeground(new java.awt.Color(153, 153, 153));
        addStock.setText("0");
        addStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addStockMouseClicked(evt);
            }
        });
        addContainer.add(addStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 320, 50));

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));

        addDescription.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addDescription.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane8.setViewportView(addDescription);

        addContainer.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 660, 120));

        jPanel9.add(addContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 720, 620));

        tabs.addTab("tab7", jPanel9);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setBackground(new java.awt.Color(241, 241, 241));
        jLabel41.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("Manage Product  >");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel42.setBackground(new java.awt.Color(241, 241, 241));
        jLabel42.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Edit Product");
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 30));

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Stock");
        jPanel13.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        getStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getStatus.setForeground(new java.awt.Color(51, 51, 51));
        getStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        jPanel13.add(getStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 320, 50));

        getCategory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getCategory.setForeground(new java.awt.Color(51, 51, 51));
        getCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Fashion", "Grocery", "Pet Supplies" }));
        jPanel13.add(getCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 320, 50));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Category");
        jPanel13.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        editbtn_manage.setBackground(new java.awt.Color(0, 158, 226));
        editbtn_manage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editbtn_manage.setForeground(new java.awt.Color(255, 255, 255));
        editbtn_manage.setText("Save");
        editbtn_manage.setBorderPainted(false);
        editbtn_manage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtn_manageActionPerformed(evt);
            }
        });
        jPanel13.add(editbtn_manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 270, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Price");
        jPanel13.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Status");
        jPanel13.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Description");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Product Name");
        jPanel13.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("General infromation");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        jPanel13.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 720, 30));

        getName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getName.setForeground(new java.awt.Color(51, 51, 51));
        jPanel13.add(getName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 660, 50));

        getPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel13.add(getPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, 50));

        getStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel13.add(getStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 320, 50));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        getDescription.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane4.setViewportView(getDescription);

        jPanel13.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 660, 120));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 720, 620));

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel15.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, 30));

        jPanel23.setBackground(new java.awt.Color(255, 204, 204));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(getPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 290, 200));

        jPanel15.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 390, 200));

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
        jPanel15.add(replacebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 190, 40));

        removetbn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        removetbn.setForeground(new java.awt.Color(51, 51, 51));
        removetbn.setText("Remove");
        removetbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removetbnActionPerformed(evt);
            }
        });
        jPanel15.add(removetbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 190, 40));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Product Image");
        jPanel15.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel43.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Edit Product");
        jPanel15.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel44.setBackground(new java.awt.Color(241, 241, 241));
        jLabel44.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(153, 153, 153));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Set the product thumbnail image. Only *.png, *.jpeg, *.jpg files are accepted.");
        jPanel15.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 390, 30));

        jPanel3.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 410, 470));

        tabs.addTab("tab8", jPanel3);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setBackground(new java.awt.Color(241, 241, 241));
        jLabel57.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(204, 204, 204));
        jLabel57.setText("View Profile  >");
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });
        jPanel28.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        display_photo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        display_photo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default_seller_profile.png"))); // NOI18N
        jPanel28.add(display_photo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, 170));

        seperator1.setBackground(new java.awt.Color(241, 241, 241));
        seperator1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel28.add(seperator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 490, 60));

        addContainer5.setBackground(new java.awt.Color(241, 241, 241));
        addContainer5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seller_address1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_address1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        username1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        seller_phone1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_phone1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        seller_email1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        seller_store1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store1.setForeground(new java.awt.Color(153, 153, 153));
        addContainer5.add(seller_store1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel65.setBackground(new java.awt.Color(241, 241, 241));
        jLabel65.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Shop details");
        addContainer5.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));
        addContainer5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 640, 10));

        jLabel58.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setText("Shop name");
        addContainer5.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel59.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("Location");
        addContainer5.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        addName2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName2.setForeground(new java.awt.Color(51, 51, 51));
        addName2.setText("PlayStation 5");
        addName2.setBorder(null);
        addName2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addName2MouseClicked(evt);
            }
        });
        addContainer5.add(addName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 520, 20));

        addName3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName3.setForeground(new java.awt.Color(51, 51, 51));
        addName3.setText("PlayStation 5");
        addName3.setBorder(null);
        addName3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addName3MouseClicked(evt);
            }
        });
        addContainer5.add(addName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 490, 20));

        addName4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName4.setForeground(new java.awt.Color(51, 51, 51));
        addName4.setText("PlayStation 5");
        addName4.setBorder(null);
        addName4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addName4MouseClicked(evt);
            }
        });
        addContainer5.add(addName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 510, 20));

        jLabel74.setForeground(new java.awt.Color(255, 0, 51));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Deactivate Account");
        addContainer5.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 130, 20));

        deactivate_jpanel.setBackground(new java.awt.Color(255, 255, 255));
        deactivate_jpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(153, 153, 153));
        jLabel60.setText("Deactivating your account will remove all your access. This action cannot be undone.");
        deactivate_jpanel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 40));

        jLabel72.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("Deactivate your account?");
        deactivate_jpanel.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 30));

        submit6.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        submit6.setForeground(new java.awt.Color(255, 51, 51));
        submit6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete_button.png"))); // NOI18N
        submit6.setBorder(null);
        submit6.setBorderPainted(false);
        submit6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit6ActionPerformed(evt);
            }
        });
        deactivate_jpanel.add(submit6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 130, 40));

        edit_seller_close_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_seller_close_button.setForeground(new java.awt.Color(51, 51, 51));
        edit_seller_close_button.setText("Close");
        edit_seller_close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_seller_close_buttonActionPerformed(evt);
            }
        });
        deactivate_jpanel.add(edit_seller_close_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 260, 40));

        edit_seller_save_button.setBackground(new java.awt.Color(0, 158, 226));
        edit_seller_save_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_seller_save_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_seller_save_button.setText("Save Changes");
        edit_seller_save_button.setBorderPainted(false);
        edit_seller_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_seller_save_buttonActionPerformed(evt);
            }
        });
        deactivate_jpanel.add(edit_seller_save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 260, 40));

        addContainer5.add(deactivate_jpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 580, 160));

        addName8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName8.setForeground(new java.awt.Color(51, 51, 51));
        addName8.setText("PlayStation 5");
        addName8.setBorder(null);
        addName8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addName8MouseClicked(evt);
            }
        });
        addContainer5.add(addName8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 520, 20));

        jLabel70.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("Username");
        addContainer5.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel71.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Password");
        addContainer5.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shop_icon.png"))); // NOI18N
        addContainer5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 50, -1));
        addContainer5.add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 580, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/location_icon.png"))); // NOI18N
        addContainer5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 50, -1));
        addContainer5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 580, 40));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/username_icon.png"))); // NOI18N
        addContainer5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 50, 40));
        addContainer5.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 580, 40));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/password_icon.png"))); // NOI18N
        addContainer5.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 50, -1));
        addContainer5.add(z6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 580, 40));

        jPanel28.add(addContainer5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 640, 620));

        bacgkround1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/background.png"))); // NOI18N
        jPanel28.add(bacgkround1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 180));

        jLabel63.setBackground(new java.awt.Color(241, 241, 241));
        jLabel63.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setText("Edit Seller Profile");
        jPanel28.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, 30));

        addContainer6.setBackground(new java.awt.Color(241, 241, 241));
        addContainer6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seller_address2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_address2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        username2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        seller_phone2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_phone2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        seller_email2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        seller_store2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store2.setForeground(new java.awt.Color(153, 153, 153));
        addContainer6.add(seller_store2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel61.setBackground(new java.awt.Color(241, 241, 241));
        jLabel61.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Your Photo");
        addContainer6.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        jLabel64.setBackground(new java.awt.Color(241, 241, 241));
        jLabel64.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(153, 153, 153));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("This will displayed to everyone");
        addContainer6.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 190, 30));

        jLabel62.setBackground(new java.awt.Color(241, 241, 241));
        jLabel62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(153, 153, 153));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("File extension: .JPEG, .PNG and JPEG only");
        addContainer6.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 280, 30));

        edit_seller_remove_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_seller_remove_button.setForeground(new java.awt.Color(51, 51, 51));
        edit_seller_remove_button.setText("Remove photo");
        edit_seller_remove_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_seller_remove_buttonActionPerformed(evt);
            }
        });
        addContainer6.add(edit_seller_remove_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, 40));

        edit_seller_upload_button.setBackground(new java.awt.Color(0, 158, 226));
        edit_seller_upload_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        edit_seller_upload_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_seller_upload_button.setText("Upload a photo");
        edit_seller_upload_button.setBorderPainted(false);
        edit_seller_upload_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_seller_upload_buttonActionPerformed(evt);
            }
        });
        addContainer6.add(edit_seller_upload_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 130, 40));

        jPanel28.add(addContainer6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 490, 170));

        addContainer7.setBackground(new java.awt.Color(241, 241, 241));
        addContainer7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seller_address3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_address3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_address3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        username3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(username3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        seller_phone3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_phone3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_phone3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        seller_email3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_email3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        seller_store3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_store3.setForeground(new java.awt.Color(153, 153, 153));
        addContainer7.add(seller_store3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel66.setBackground(new java.awt.Color(241, 241, 241));
        jLabel66.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Personal information");
        addContainer7.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));
        addContainer7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 490, 10));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("Full name");
        addContainer7.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel68.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
        jLabel68.setText("Email address");
        addContainer7.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        addName5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName5.setForeground(new java.awt.Color(51, 51, 51));
        addName5.setText("PlayStation 5");
        addName5.setBorder(null);
        addName5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addName5MouseClicked(evt);
            }
        });
        addContainer7.add(addName5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 380, 20));

        jLabel69.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(51, 51, 51));
        jLabel69.setText("Full name");
        addContainer7.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        addName6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName6.setForeground(new java.awt.Color(51, 51, 51));
        addName6.setText("PlayStation 5");
        addName6.setBorder(null);
        addName6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addName6MouseClicked(evt);
            }
        });
        addContainer7.add(addName6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 380, 20));

        addName7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addName7.setForeground(new java.awt.Color(51, 51, 51));
        addName7.setText("PlayStation 5");
        addName7.setBorder(null);
        addName7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addName7MouseClicked(evt);
            }
        });
        addContainer7.add(addName7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 380, 20));

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile_icon.png"))); // NOI18N
        addContainer7.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 50, 40));
        addContainer7.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 440, 40));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/number_icon.png"))); // NOI18N
        addContainer7.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 50, 40));
        addContainer7.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 440, 40));

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/email_icon.png"))); // NOI18N
        addContainer7.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 50, -1));
        addContainer7.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 440, 40));

        jPanel28.add(addContainer7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 490, 280));

        tabs.addTab("tab9", jPanel28);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orders_summary_container2.setBackground(new java.awt.Color(255, 255, 255));
        orders_summary_container2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel87.setBackground(new java.awt.Color(241, 241, 241));
        jLabel87.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(51, 51, 51));
        jLabel87.setText("Contact information");
        orders_summary_container2.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel12.add(orders_summary_container2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 350, 150));

        orders_summary_container.setBackground(new java.awt.Color(255, 255, 255));
        orders_summary_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        orders_summary_container.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, 140));

        jLabel85.setBackground(new java.awt.Color(241, 241, 241));
        jLabel85.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(51, 51, 51));
        jLabel85.setText("May 1, 2023");
        orders_summary_container.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        jLabel89.setBackground(new java.awt.Color(241, 241, 241));
        jLabel89.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 51));
        jLabel89.setText("#1001111");
        orders_summary_container.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jPanel12.add(orders_summary_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 740, 210));

        orders_summary_container3.setBackground(new java.awt.Color(255, 255, 255));
        orders_summary_container3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setBackground(new java.awt.Color(241, 241, 241));
        jLabel86.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 51, 51));
        jLabel86.setText("Shipping address");
        orders_summary_container3.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel12.add(orders_summary_container3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 350, 150));

        orders_summary_container1.setBackground(new java.awt.Color(255, 255, 255));
        orders_summary_container1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        orders_summary_container1.add(decline, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 130, 50));

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
        orders_summary_container1.add(accept_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 130, 50));

        jLabel88.setBackground(new java.awt.Color(241, 241, 241));
        jLabel88.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 51, 51));
        jLabel88.setText("Order summary");
        orders_summary_container1.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jPanel12.add(orders_summary_container1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 740, 360));

        jPanel16.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1150, 620));

        jLabel83.setBackground(new java.awt.Color(241, 241, 241));
        jLabel83.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(204, 204, 204));
        jLabel83.setText("Orders Table  >");
        jPanel16.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel84.setBackground(new java.awt.Color(241, 241, 241));
        jLabel84.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setText("View Order");
        jPanel16.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, 30));

        tabs.addTab("tab10", jPanel16);

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

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        Login out = new Login();
        out.setVisible(true);
        this.dispose();

        String action = "Logged out";
        String details = "Seller " + sellerID + " Successfully logged out!";
        actionLogs.recordSellerLogs(sellerID, action, details);
    }//GEN-LAST:event_logoutActionPerformed
    String fileName;
    String imagePath;
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        tabs.setSelectedIndex(6);
    }//GEN-LAST:event_addActionPerformed

    File selectedFile;

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            //String accountStatus = (String) stats.getSelectedItem();
            //String accountRole = (String) role.getSelectedItem();

            String sql = "UPDATE tbl_accounts SET role=?, status=?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                //pst.setString(1, accountRole);
                //pst.setString(2, accountStatus);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                    String action = "Edit Profile";
                    String details = "Seller " + sellerID + " Successfully edit its profile!";
                    actionLogs.recordSellerLogs(sellerID, action, details);
                    tabs.setSelectedIndex(4);
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

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        tabs.setSelectedIndex(5);

        int rowIndex = accounts_table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = accounts_table.getModel();
            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id =" + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    // ID.setText("" + rs.getString("account_id"));
                    // lname.setText("" + rs.getString("last_name"));
                    // email.setText("" + rs.getString("email"));
                    // address.setText(rs.getString("address"));
                    //  username.setText(rs.getString("username"));
                    //  role.setSelectedItem(rs.getString("role"));
                    int height = 120;
                    int width = 110;
                    String getImageFromDatabase = rs.getString("profile_picture");
                    // GetImage.displayImage(display, getImageFromDatabase, height, width);
                    //  date.setText(rs.getString("date_joined"));
                    //  stats.setSelectedItem(rs.getString("status"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void product_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_product_search_barKeyReleased
        search.searchResult(product_table, product_search_bar);
    }//GEN-LAST:event_product_search_barKeyReleased

    private void product_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_search_barMouseClicked
        product_search_bar.setFocusable(true);
        product_search_bar.requestFocusInWindow();
        if (product_search_bar.getText().isEmpty() || product_search_bar.getText().equals("  Search")) {
            product_search_bar.setText("");
            product_search_bar.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_product_search_barMouseClicked

    private void product_search_barFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_product_search_barFocusLost
        if (product_search_bar.getText().isEmpty()) {
            product_search_bar.setText("  Search");
            product_search_bar.setForeground(Color.decode("#8C8C8C"));
        }
    }//GEN-LAST:event_product_search_barFocusLost

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
                    productQuantity.setText("" + rs.getString("product_stock"));
                    productStatus.setText("" + rs.getString("product_status"));
                    descript.setText("" + rs.getString("product_description"));

                    int height = 70;
                    int width = 70;
                    String getImageFromDatabase = rs.getString("product_image");
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
            String sql = "UPDATE tbl_products SET product_status='Available' WHERE `product_id`=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, p_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Product has been retored Successfully!");
                    String action = "Restore";
                    String details = "Seller " + sellerID + " Successfully restore product " + p_id + "!";
                    actionLogs.recordSellerLogs(sellerID, action, details);
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
                String action = "Delete";
                String details = "Seller " + sellerID + " Successfully deleted product " + id + "!";
                actionLogs.recordSellerLogs(sellerID, action, details);
                displayData();
                displayArchive();
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void archiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            String sql = "UPDATE tbl_products SET product_status='Archived' WHERE product_id=?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, p_id);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Product added to archive Successfully!");
                    displayData();
                    displayArchive();
                    String action = "Archive";
                    String details = "Seller " + sellerID + " Successfully put product " + p_id + " to archive!";
                    actionLogs.recordSellerLogs(sellerID, action, details);
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

    private static int p_id;


    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked

    }//GEN-LAST:event_product_tableMouseClicked

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
                String sql = "UPDATE tbl_orders SET order_status='Accepted' WHERE order_id=?";
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
                        String action = "Accept Order";
                        String details = "Seller " + sellerID + " Successfully accept order " + transaction_id + "!";
                        actionLogs.recordSellerLogs(sellerID, action, details);
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
        String sql = "UPDATE tbl_products SET `total_sold` = `sold` + ? WHERE `product_id` = ?";
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

            String query = "SELECT product_stock FROM tbl_products WHERE product_id = ?";
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

            String updateQuery = "UPDATE tbl_products SET product_stock = ? WHERE product_id = ?";
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

            String updateQuery = "UPDATE tbl_products SET product_status = ? WHERE product_id = ?";
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
                String sql = "UPDATE tbl_orders SET `order_status`='Declined' WHERE `order_id`=?";
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
                        String action = "Declined Order";
                        String details = "Seller " + sellerID + " declined order " + transaction_id + "!";
                        actionLogs.recordSellerLogs(sellerID, action, details);
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

            String query = "SELECT product_image FROM tbl_products WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String getImageFromDatabase = rs.getString("product_image");
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

            String query = "SELECT first_name, last_name, profile_picture FROM tbl_accounts WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, buyer_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
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

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        tabs.setSelectedIndex(5);

        try {
            databaseConnector dbc = new databaseConnector();
            ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id =" + sellerID);
            if (rs.next()) {
                String fname;
                String lname;
                fname = rs.getString("first_name");
                lname = rs.getString("last_name");
                fname = Character.toUpperCase(fname.charAt(0)) + fname.substring(1);
                lname = Character.toUpperCase(lname.charAt(0)) + lname.substring(1);
                seller_full_name.setText(fname + " " + lname);
                username.setText("@" + rs.getString("username"));
                seller_address.setText("" + rs.getString("address"));
                seller_phone.setText("" + rs.getString("phone_number"));
                seller_email.setText("" + rs.getString("email"));
                seller_store.setText(rs.getString("shop_name"));
                int height = 120;
                int width = 120;
                String getImageFromDatabase = rs.getString("profile_picture");
                GetImage.displayImage(display_photo, getImageFromDatabase, height, width);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_profileMouseClicked

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
                    getPrice.setText(rs.getString("product_price"));
                    getStock.setText(rs.getString("product_stock"));
                    getDescription.setText(rs.getString("description"));
                    getStatus.setSelectedItem(rs.getString("product_status"));
                    String getImageFromDatabase = rs.getString("product_image");
                    GetImage.displayImage(getPhoto, getImageFromDatabase, height, width);
                    tabs.setSelectedIndex(7);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_editButtonActionPerformed

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

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        tabs.setSelectedIndex(0);
        manage.setSelected(false);
        orders.setSelected(false);
        accounts.setSelected(false);
        archiveBtn.setSelected(false);
        logsBtn.setSelected(false);
    }//GEN-LAST:event_dashboardActionPerformed

    private void manageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageActionPerformed
        tabs.setSelectedIndex(1);
        dashboard.setSelected(false);
        orders.setSelected(false);
        accounts.setSelected(false);
        archiveBtn.setSelected(false);
        logsBtn.setSelected(false);
    }//GEN-LAST:event_manageActionPerformed

    private void ordersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersActionPerformed
        tabs.setSelectedIndex(3);
        dashboard.setSelected(false);
        manage.setSelected(false);
        accounts.setSelected(false);
        archiveBtn.setSelected(false);
        logsBtn.setSelected(false);
    }//GEN-LAST:event_ordersActionPerformed

    private void accountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountsActionPerformed
        tabs.setSelectedIndex(4);
        dashboard.setSelected(false);
        manage.setSelected(false);
        orders.setSelected(false);
        archiveBtn.setSelected(false);
        logsBtn.setSelected(false);
    }//GEN-LAST:event_accountsActionPerformed

    private void archiveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveBtnActionPerformed
        tabs.setSelectedIndex(2);
        dashboard.setSelected(false);
        manage.setSelected(false);
        orders.setSelected(false);
        accounts.setSelected(false);
        logsBtn.setSelected(false);
    }//GEN-LAST:event_archiveBtnActionPerformed

    private void logsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsBtnActionPerformed
        actionLogs.displaySellerLogs(actionlogs_table, sellerID);
        tabs.setSelectedIndex(8);
        dashboard.setSelected(false);
        manage.setSelected(false);
        orders.setSelected(false);
        archiveBtn.setSelected(false);
        accounts.setSelected(false);
    }//GEN-LAST:event_logsBtnActionPerformed

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        dashboard.setSelected(true);
    }//GEN-LAST:event_dashboardMouseClicked

    private void manageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageMouseClicked
        manage.setSelected(true);
    }//GEN-LAST:event_manageMouseClicked

    private void ordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseClicked
        orders.setSelected(true);
    }//GEN-LAST:event_ordersMouseClicked

    private void accountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountsMouseClicked
        accounts.setSelected(true);
    }//GEN-LAST:event_accountsMouseClicked

    private void archiveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiveBtnMouseClicked
        archiveBtn.setSelected(true);
    }//GEN-LAST:event_archiveBtnMouseClicked

    private void logsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsBtnMouseClicked
        logsBtn.setSelected(true);
    }//GEN-LAST:event_logsBtnMouseClicked

    private void product_search_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_search_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_product_search_barActionPerformed

    private void editbtn_manageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtn_manageActionPerformed
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

                sql = "UPDATE tbl_products SET product_name=?, product_price=?, product_stock=?, product_description=?, product_image=?, product_status=?, product_category=? WHERE product_id=?";
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
                sql = "UPDATE tbl_products SET product_name=?, product_price=?, product_stock=?, product_description=?, product_status=?, product_category=? WHERE product_id=?";
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
                        String action = "Edit Product";
                        String details = "Seller " + sellerID + " Successfully edit product " + p_id + "!";
                        actionLogs.recordSellerLogs(sellerID, action, details);
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
    }//GEN-LAST:event_editbtn_manageActionPerformed

    private void replacebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replacebtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_replacebtnActionPerformed

    private void removetbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removetbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removetbnActionPerformed

    private void addReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReplaceActionPerformed
        getPhoto(addPhoto);
    }//GEN-LAST:event_addReplaceActionPerformed

    private void addRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRemoveActionPerformed
        getPhoto(getPhoto);
    }//GEN-LAST:event_addRemoveActionPerformed

    private void add_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_saveActionPerformed
        String valname = addName.getText();
        String valprice = addPrice.getText();
        String valstocks = addStock.getText();
        String valdes = addDescription.getText();
        String valstats = (String) add_category.getSelectedItem();
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

            String insertQuery = "INSERT INTO tbl_products (`seller_id`, `product_name`, `product_price`, `product_stock`, `product_description`, `product_status`, `product_image`, `product_category`, `date_created`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURDATE())";
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
            String action = "Add Product";
            String details = "Seller " + sellerID + " Successfully added a new product!";
            actionLogs.recordSellerLogs(sellerID, action, details);
            addName.setText("");
            addPrice.setText("");
            addStock.setText("");
            add_category.setSelectedIndex(0);
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
    }//GEN-LAST:event_add_saveActionPerformed

    private void addNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNameMouseClicked
        addName.setText("");
        addName.setForeground(Color.decode("#333333"));
    }//GEN-LAST:event_addNameMouseClicked

    private void addPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPriceMouseClicked
        addPrice.setText("");
        addPrice.setForeground(Color.decode("#333333"));
    }//GEN-LAST:event_addPriceMouseClicked

    private void add_categoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_categoryMouseClicked
        add_category.setForeground(Color.decode("#333333"));
    }//GEN-LAST:event_add_categoryMouseClicked

    private void addCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryMouseClicked
        addCategory.setForeground(Color.decode("#333333"));
    }//GEN-LAST:event_addCategoryMouseClicked

    private void addStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStockMouseClicked
        addStock.setText("");
        addStock.setForeground(Color.decode("#333333"));
    }//GEN-LAST:event_addStockMouseClicked

    private void actionlogs_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionlogs_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_actionlogs_tableMouseClicked

    private void activity_search_barFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_activity_search_barFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_activity_search_barFocusLost

    private void activity_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activity_search_barMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_activity_search_barMouseClicked

    private void activity_search_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activity_search_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_activity_search_barActionPerformed

    private void activity_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_activity_search_barKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_activity_search_barKeyReleased

    private void add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add1ActionPerformed

    private void messages_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messages_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_messages_tableMouseClicked

    private void add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add2ActionPerformed

    private void add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add3ActionPerformed

    private void add4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add4ActionPerformed

    private void add5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add5ActionPerformed

    private void edit_seller_remove_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_seller_remove_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_seller_remove_buttonActionPerformed

    private void edit_seller_upload_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_seller_upload_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_seller_upload_buttonActionPerformed

    private void addName2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addName2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addName2MouseClicked

    private void addName3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addName3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addName3MouseClicked

    private void addName4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addName4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addName4MouseClicked

    private void addName5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addName5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addName5MouseClicked

    private void addName6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addName6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addName6MouseClicked

    private void addName7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addName7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addName7MouseClicked

    private void addName8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addName8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addName8MouseClicked

    private void submit6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submit6ActionPerformed

    private void edit_seller_close_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_seller_close_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_seller_close_buttonActionPerformed

    private void edit_seller_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_seller_save_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_seller_save_buttonActionPerformed

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        newStock = 0;
        int rowIndex = purchase_table.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = purchase_table.getModel();
            try {
                databaseConnector dbc = new databaseConnector();
                String query = "SELECT address FROM tbl_accounts WHERE account_id = ?";
                PreparedStatement pst = dbc.getConnection().prepareStatement(query);
                pst.setInt(1, accountId);
                ResultSet rs = dbc.getData("SELECT * FROM tbl_orders WHERE order_id =" + model.getValueAt(rowIndex, 0));
                if (rs.next()) {
                    transaction_id = rs.getInt("order_id");
                    buyer_id = rs.getInt("buyer_id");
                    product_id = rs.getInt("product_id");

                    //
                    salesPN.setText("" + rs.getString("product_name"));
                    salesAddress.setText("" + rs.getString("address"));
                    salesPrice.setText("₱  " + rs.getString("product_price"));
                    //

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
        }    }//GEN-LAST:event_jButton2ActionPerformed

    private void purchase_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchase_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_purchase_tableMouseClicked

    private void product_search_bar1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_product_search_bar1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_product_search_bar1FocusLost

    private void product_search_bar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_search_bar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_product_search_bar1MouseClicked

    private void product_search_bar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_search_bar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_product_search_bar1ActionPerformed

    private void product_search_bar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_product_search_bar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_product_search_bar1KeyReleased

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
    private javax.swing.JButton accept_order;
    private javax.swing.JToggleButton accounts;
    private javax.swing.JTable accounts_table;
    private javax.swing.JTable actionlogs_table;
    private javax.swing.JTextField activity_search_bar;
    private javax.swing.JLabel activity_search_icon;
    private javax.swing.JButton add;
    private javax.swing.JButton add1;
    private javax.swing.JButton add2;
    private javax.swing.JButton add3;
    private javax.swing.JButton add4;
    private javax.swing.JButton add5;
    private javax.swing.JComboBox<String> addCategory;
    private javax.swing.JPanel addContainer;
    private javax.swing.JPanel addContainer1;
    private javax.swing.JPanel addContainer4;
    private javax.swing.JPanel addContainer5;
    private javax.swing.JPanel addContainer6;
    private javax.swing.JPanel addContainer7;
    private javax.swing.JEditorPane addDescription;
    private javax.swing.JTextField addName;
    private javax.swing.JTextField addName2;
    private javax.swing.JTextField addName3;
    private javax.swing.JTextField addName4;
    private javax.swing.JTextField addName5;
    private javax.swing.JTextField addName6;
    private javax.swing.JTextField addName7;
    private javax.swing.JTextField addName8;
    private javax.swing.JLabel addPhoto;
    private javax.swing.JTextField addPrice;
    private javax.swing.JButton addRemove;
    private javax.swing.JButton addReplace;
    private javax.swing.JTextField addStock;
    private javax.swing.JComboBox<String> add_category;
    private javax.swing.JButton add_save;
    private javax.swing.JButton archive;
    private javax.swing.JPanel archiveAccountTableContainer;
    private javax.swing.JScrollPane archiveAccountTableContainerScroll;
    private javax.swing.JToggleButton archiveBtn;
    private javax.swing.JTable archive_table;
    private javax.swing.JLabel bacgkround;
    private javax.swing.JLabel bacgkround1;
    private javax.swing.JTextField c1;
    private javax.swing.JTextField c2;
    private javax.swing.JTextField c3;
    private javax.swing.JTextField c4;
    private javax.swing.JTextField c5;
    private javax.swing.JPanel c6;
    private javax.swing.JPanel c7;
    private javax.swing.JPanel c8;
    private javax.swing.JPanel c9;
    private javax.swing.JToggleButton dashboard;
    private javax.swing.JPanel dashboardContainer;
    private javax.swing.JPanel deactivate_jpanel;
    private javax.swing.JButton decline;
    private javax.swing.JButton delete;
    private javax.swing.JEditorPane descript;
    private javax.swing.JLabel display_photo;
    private javax.swing.JLabel display_photo1;
    private javax.swing.JButton edit;
    private javax.swing.JButton editButton;
    private javax.swing.JButton edit_seller_close_button;
    private javax.swing.JButton edit_seller_remove_button;
    private javax.swing.JButton edit_seller_save_button;
    private javax.swing.JButton edit_seller_upload_button;
    private javax.swing.JButton editbtn_manage;
    private javax.swing.JPanel filterContainer;
    private javax.swing.JPanel filterContainer1;
    private javax.swing.JPanel filterContainer2;
    private javax.swing.JComboBox<String> getCategory;
    private javax.swing.JEditorPane getDescription;
    private javax.swing.JTextField getName;
    private javax.swing.JLabel getPhoto;
    private javax.swing.JTextField getPrice;
    private javax.swing.JComboBox<String> getStatus;
    private javax.swing.JTextField getStock;
    private javax.swing.JLabel helloSeller;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
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
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton logout;
    private javax.swing.JToggleButton logsBtn;
    private javax.swing.JToggleButton manage;
    private javax.swing.JLabel manage18;
    private javax.swing.JLabel manage24;
    private javax.swing.JLabel manage25;
    private javax.swing.JPanel messages_container;
    private javax.swing.JTable messages_table;
    private javax.swing.JToggleButton orders;
    private javax.swing.JPanel orders_summary_container;
    private javax.swing.JPanel orders_summary_container1;
    private javax.swing.JPanel orders_summary_container2;
    private javax.swing.JPanel orders_summary_container3;
    private javax.swing.JLabel overviewTotalLoss1;
    private javax.swing.JLabel overviewTotalLoss2;
    private javax.swing.JLabel overviewTotalLoss3;
    private javax.swing.JLabel overviewTotalLoss4;
    private javax.swing.JLabel overviewTotalSales;
    private javax.swing.JLabel pendingOrders;
    private javax.swing.JLabel productID;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPhoto;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productQuantity;
    private javax.swing.JLabel productStatus;
    private javax.swing.JTextField product_search_bar;
    private javax.swing.JTextField product_search_bar1;
    private javax.swing.JTable product_table;
    private javax.swing.JLabel product_table_search_icon;
    private javax.swing.JLabel product_table_search_icon1;
    private javax.swing.JPanel productsContainer;
    private javax.swing.JPanel productsContainer1;
    private javax.swing.JPanel productsContainer2;
    private javax.swing.JLabel profile;
    private javax.swing.JTable purchase_table;
    private javax.swing.JButton removetbn;
    private javax.swing.JButton replacebtn;
    private javax.swing.JButton restore;
    private javax.swing.JPanel s;
    private javax.swing.JTextField search3;
    private javax.swing.JButton searchbtn11;
    private javax.swing.JButton searchbtn12;
    private javax.swing.JButton searchbtn9;
    private javax.swing.JLabel seller_address;
    private javax.swing.JLabel seller_address1;
    private javax.swing.JLabel seller_address2;
    private javax.swing.JLabel seller_address3;
    private javax.swing.JLabel seller_email;
    private javax.swing.JLabel seller_email1;
    private javax.swing.JLabel seller_email2;
    private javax.swing.JLabel seller_email3;
    private javax.swing.JLabel seller_full_name;
    private javax.swing.JLabel seller_phone;
    private javax.swing.JLabel seller_phone1;
    private javax.swing.JLabel seller_phone2;
    private javax.swing.JLabel seller_phone3;
    private javax.swing.JLabel seller_rating;
    private javax.swing.JLabel seller_store;
    private javax.swing.JLabel seller_store1;
    private javax.swing.JLabel seller_store2;
    private javax.swing.JLabel seller_store3;
    private javax.swing.JPanel seperator;
    private javax.swing.JPanel seperator1;
    private javax.swing.JPanel sortContainer;
    private javax.swing.JPanel sortContainer1;
    private javax.swing.JButton submit;
    private javax.swing.JButton submit6;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel todaysDate;
    private javax.swing.JLabel totalLoss;
    private javax.swing.JLabel totalOrders;
    private javax.swing.JLabel totalProducts;
    private javax.swing.JLabel totalSales;
    private javax.swing.JLabel username;
    private javax.swing.JLabel username1;
    private javax.swing.JLabel username2;
    private javax.swing.JLabel username3;
    private javax.swing.JTextField z6;
    // End of variables declaration//GEN-END:variables
}
