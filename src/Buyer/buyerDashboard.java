package Buyer;

import accounts.Login;
import accounts.UserManager;
import accounts.frontPage;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.databaseConnector;
import config.flatlaftTable;
import config.isAccountExist;
import config.search;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.mindrot.jbcrypt.BCrypt;

public final class buyerDashboard extends javax.swing.JFrame {

    int buyer_id = UserManager.getLoggedInUserId();

    public buyerDashboard() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        displayUserProducts();
        displayCart();
        displayPurchase();
        display_wishlist();
        displayProducts();

        flatlaftTable.design(cartTableContainer, cart_table, jScrollPane1); // display cart table
        searchBar(orders_search_bar);

        flatlaftTable.design(cartTableContainer1, purchase_table, jScrollPane6); // display orders table
        searchBar(purchase_search_bar);

        flatlaftTable.design(productsContainer, product_table, jScrollPane7); // display product table
        searchBar(product_search_bar);

        //Panels
        UXmethods.RoundBorders.setArcStyle(s1, 20);
        UXmethods.RoundBorders.setArcStyle(s2, 20);
        UXmethods.RoundBorders.setArcStyle(add_to_cart, 10);
        UXmethods.RoundBorders.setArcStyle(buy_now, 10);
        UXmethods.RoundBorders.setArcStyle(chat_now, 10);
        UXmethods.RoundBorders.setArcStyle(view_shop, 10);
        UXmethods.RoundBorders.setArcStyle(add_to_wishlist, 10);

        //cart
        UXmethods.RoundBorders.setArcStyle(cartViewContainer, 20);
        UXmethods.RoundBorders.setArcStyle(yawa, 20);
        UXmethods.RoundBorders.setArcStyle(checkout, 10);
        UXmethods.RoundBorders.setArcStyle(jScrollPane5, 10);
        UXmethods.RoundBorders.setArcStyle(deleteCart, 10);
        UXmethods.RoundBorders.setArcStyle(add, 10);
        //

        //Animations
        //customizeJPanel(p1);
    }

    //////////////////////////////////////
    //private String selectedGender = "";
    //panel clicked
    //private void getSelectedGender(String gender) {
    //selectedGender = gender;
    //}
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

    String profilePicture;

    public void displayAccountName() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT * FROM tbl_accounts WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, buyer_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                jLabel12.setText("" + rs.getString("username"));
                username.setText("" + rs.getString("username"));
                fname.setText("" + rs.getString("first_name"));
                lname.setText("" + rs.getString("last_name"));
                email.setText("" + rs.getString("email"));
                edit_phone_number.setText("" + rs.getString("phone_number"));
                address.setText("" + rs.getString("address"));
                int height = 60;
                int width = 60;
                profilePicture = rs.getString("profile_picture");
                GetImage.displayImage(jLabel6, profilePicture, height, width);
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayCart() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT "
                    + "c.cart_id AS `Cart ID`, "
                    + "p.product_name AS `Product Name`, "
                    + "p.product_category AS `Category`, "
                    + "p.product_price AS `Unit Price`, "
                    + "c.product_quantity AS `Quantity` "
                    + "FROM tbl_cart c "
                    + "JOIN tbl_products p ON p.product_id = c.product_id "
                    + "WHERE c.buyer_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, buyer_id);

                try (ResultSet rs = pst.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        cart_is_empty.setText("CART TABLE IS EMPTY!");
                        cart_table.setModel(new DefaultTableModel());
                    } else {
                        cart_is_empty.setText("");
                        cart_table.setModel(DbUtils.resultSetToTableModel(rs));
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        cart_table.setDefaultRenderer(Object.class, centerRenderer);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void displayPurchase() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT "
                    + "o.order_id AS `Order ID`, "
                    + "a.shop_name AS `Store`, "
                    + "p.product_name AS `Product Name`, "
                    + "p.product_category AS `Category`, "
                    + "p.product_price AS `Unit Price`, "
                    + "o.total_quantity AS `Quantity`, "
                    + "o.total_price AS `Total Price`, "
                    + "o.order_status AS `Status` "
                    + "FROM tbl_orders o "
                    + "JOIN tbl_products p ON p.product_id = o.product_id "
                    + "JOIN tbl_accounts a ON a.account_id = o.seller_id "
                    + "WHERE o.buyer_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, buyer_id);

                try (ResultSet rs = pst.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        purchase_is_empty.setText("CART TABLE IS EMPTY!");
                        purchase_table.setModel(new DefaultTableModel());
                    } else {
                        purchase_is_empty.setText("");
                        purchase_table.setModel(DbUtils.resultSetToTableModel(rs));
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        purchase_table.setDefaultRenderer(Object.class, centerRenderer);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void displayProducts() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (Connection conn = dbc.getConnection(); PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT "
                    + "a.shop_name AS `Seller`, "
                    + "p.product_id AS `Product ID`, "
                    + "p.product_name AS `Product Name`, "
                    + "p.product_price AS `Price`, "
                    + "p.product_stock AS `Stock(s)`, "
                    + "p.product_category AS `Category` "
                    + "FROM tbl_products p "
                    + "JOIN tbl_accounts a ON a.account_id = p.seller_id "
                    + "WHERE product_status IN ('Available') AND product_stock > 0")) {

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        product_is_empty.setText("PRODUCT TABLE IS EMPTY!");
                        product_table.setModel(new DefaultTableModel());
                    } else {
                        product_is_empty.setText("");
                        product_table.setModel(DbUtils.resultSetToTableModel(rs));

                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        for (int i = 0; i < product_table.getColumnCount(); i++) {
                            product_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void display_wishlist() {
        try {
            databaseConnector dbc = new databaseConnector();
            try (Connection conn = dbc.getConnection(); PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT "
                    + "w.wishlist_id AS `Wishlist #`, "
                    + "a.shop_name AS `Seller`, "
                    + "p.product_name AS `Product Name`, "
                    + "p.product_price AS `Price`, "
                    + "p.product_stock AS `Stock(s)`, "
                    + "p.product_category AS `Category` "
                    + "FROM tbl_wishlist w "
                    + "JOIN tbl_accounts a ON a.account_id = w.seller_id "
                    + "JOIN tbl_products p ON p.product_id = w.product_id "
                    + "WHERE p.product_status IN ('Available') AND p.product_stock > 0")) {

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        wishlist_is_empty.setText("PRODUCT TABLE IS EMPTY!");
                        wishlist_table.setModel(new DefaultTableModel());
                    } else {
                        wishlist_is_empty.setText("");
                        wishlist_table.setModel(DbUtils.resultSetToTableModel(rs));

                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        for (int i = 0; i < wishlist_table.getColumnCount(); i++) {
                            wishlist_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void displayUserProducts() {
        try {

            databaseConnector dbc = new databaseConnector();
            Connection connection = dbc.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT product_image, product_name, product_price FROM tbl_products WHERE product_status = ? AND product_stock > 0");
            statement.setString(1, "Available");

            ResultSet rs = statement.executeQuery();
            JLabel[] nameLabels = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12, name13, name14, name15};
            JLabel[] priceLabels = {price1, price2, price3, price4, price5, price6, price7, price8, price9, price10, price11, price12, price13, price14, price15};
            JLabel[] imageLabels = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15};

            int productCounter = 0;
            while (rs.next() && productCounter < imageLabels.length) {

                int height = 180;
                int width = 130;
                String getImageFromDatabase = rs.getString("product_image");
                String productName = rs.getString("product_name");
                int productPrice = rs.getInt("product_price");
                GetImage.displayImage(imageLabels[productCounter], getImageFromDatabase, height, width);
                nameLabels[productCounter].setText(productName);
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                priceLabels[productCounter].setText("₱ " + numberFormat.format(productPrice));
                productCounter++;
            }

            if (productCounter == 0) {
                home_is_empty.setText("NO PRODUCTS ARE AVAILABLE!");
            } else {
                home_is_empty.setText("");
            }

            for (int i = productCounter; i < imageLabels.length; i++) {
                removeLabel(imageLabels[i]);
                removeLabel(nameLabels[i]);
                removeLabel(priceLabels[i]);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeLabel(JLabel label) {
        if (label != null) {
            label.setIcon(null);
            label.setText(null);
            label.setVisible(false);
        }
    }

    //Quantity of the selected product
    int quan = 1;
    int product_id = 0;
    int seller_id = 0;
    String shop;
    String product_image;

    private void panelMouseClicked(JPanel panel, JLabel nameLabel, JLabel priceLabel, JLabel imageLabel) {
        if (panel != null && imageLabel.getIcon() != null && nameLabel.getText() != null && !nameLabel.getText().isEmpty() && priceLabel.getText() != null && !priceLabel.getText().isEmpty()) {

            //product_photo.setIcon(selectedImage);
            product_name.setText(nameLabel.getText());
            product_price.setText(priceLabel.getText());

            try {
                databaseConnector dbc = new databaseConnector();
                try (Connection connection = dbc.getConnection()) {
                    String getProductIdQuery = "SELECT product_id, seller_id FROM tbl_products WHERE product_name = ?";
                    ResultSet rs;
                    try (PreparedStatement getProductIdStatement = connection.prepareStatement(getProductIdQuery)) {
                        getProductIdStatement.setString(1, nameLabel.getText());
                        rs = getProductIdStatement.executeQuery();
                        if (rs.next()) {
                            product_id = rs.getInt("product_id");
                            seller_id = rs.getInt("seller_id");
                        }
                        rs.close(); // Close the first ResultSet
                        // Close the first PreparedStatement
                    }

                    String query = "SELECT "
                            + "p.product_description AS product_description, "
                            + "p.product_stock AS product_stock, "
                            + "p.product_image AS product_image, "
                            + "p.total_sold AS total_sold, "
                            + "p.product_category AS product_category, "
                            + "COUNT(p.product_id) AS total_products, "
                            + "w.total_favorites AS total_favorites, "
                            + "SUM(pr.total_star) AS total_star, "
                            + "COUNT(pr.product_id) AS total_rating, "
                            + "SUM(sr.total_star) AS seller_star, "
                            + "COUNT(sr.seller_id) AS seller_rating, "
                            + "a.profile_picture AS seller_profile, "
                            + "a.shop_name AS shop_name, "
                            + "a.date_joined AS date_joined "
                            + "FROM tbl_products p "
                            + "LEFT JOIN tbl_wishlist w ON w.product_id = p.product_id "
                            + "LEFT JOIN tbl_rating4products pr ON pr.product_id = p.product_id "
                            + "LEFT JOIN tbl_rating4seller sr ON sr.seller_id = p.seller_id "
                            + "LEFT JOIN tbl_accounts a ON a.account_id = p.seller_id "
                            + "WHERE p.product_status = 'Available' AND p.product_id = ? "
                            + "GROUP BY p.product_id, w.total_favorites, a.profile_picture";

                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setInt(1, product_id);
                        rs = statement.executeQuery();
                        if (rs.next()) {
                            product_image = rs.getString("product_image");
                            shop = rs.getString("product_category");
                            String productname = nameLabel.getText();
                            String coloredText = "<html><span style='color: rgb(153, 153, 153);'>" + shop + "</span>  > <span style='color: rgb(38, 38, 38);'>  " + productname + "</span></html>";
                            product_category.setText(coloredText);
                            product_shop_name.setText(rs.getString("shop_name"));
                            seller_joined.setText(rs.getString("date_joined"));
                            int favorites = rs.getInt("total_favorites");
                            if (favorites < 1) {
                                product_favorites.setText("Favorites (0)");
                            } else {
                                product_favorites.setText("Favorites (" + favorites + ")");
                            }
                            product_sold.setText(rs.getString("total_sold"));
                            int total_products = rs.getInt("total_products");
                            seller_products.setText(String.format("%d", total_products));

                            int height = 80;
                            int width = 80;
                            String getImageFromDatabase = rs.getString("seller_profile");
                            GetImage.displayImage(seller_profile, getImageFromDatabase, height, width);

                            int sheight = 360;
                            int swidth = 260;
                            String sgetImageFromDatabase = rs.getString("product_image");
                            GetImage.displayImage(product_photo, sgetImageFromDatabase, sheight, swidth);

                            int sum_star = rs.getInt("total_star");
                            int seller_sum_star = rs.getInt("seller_star");
                            int seller_count = rs.getInt("total_rating");
                            int seller_seller_count = rs.getInt("seller_rating");
                            float seller_rating = seller_seller_count > 0 ? (float) seller_sum_star / seller_seller_count : 0;
                            float rating = seller_count > 0 ? (float) sum_star / seller_count : 0;
                            if (sum_star < 1) {
                                product_rating.setText("0 (0)");
                            } else {
                                product_rating.setText(String.format("%.1f (%d)", rating, seller_count));
                            }
                            if (seller_sum_star < 1) {
                                seller__profile_rating.setText("0 (0)");
                            } else {
                                seller__profile_rating.setText(String.format("%.1f (%d)", seller_rating, seller_seller_count));
                            }

                            product_description.setText(rs.getString("product_description"));
                            quan = rs.getInt("product_stock");
                        }
                        rs.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tabs.setSelectedIndex(2);
        } else {
            System.out.println("Panel is empty");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        myCart = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        name1 = new javax.swing.JLabel();
        price1 = new javax.swing.JLabel();
        image1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        home_is_empty = new javax.swing.JLabel();
        p2 = new javax.swing.JPanel();
        name2 = new javax.swing.JLabel();
        price2 = new javax.swing.JLabel();
        image2 = new javax.swing.JLabel();
        p3 = new javax.swing.JPanel();
        name3 = new javax.swing.JLabel();
        price3 = new javax.swing.JLabel();
        image3 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        name4 = new javax.swing.JLabel();
        price4 = new javax.swing.JLabel();
        image4 = new javax.swing.JLabel();
        p5 = new javax.swing.JPanel();
        name5 = new javax.swing.JLabel();
        price5 = new javax.swing.JLabel();
        image5 = new javax.swing.JLabel();
        p6 = new javax.swing.JPanel();
        name6 = new javax.swing.JLabel();
        price6 = new javax.swing.JLabel();
        image6 = new javax.swing.JLabel();
        p7 = new javax.swing.JPanel();
        name7 = new javax.swing.JLabel();
        price7 = new javax.swing.JLabel();
        image7 = new javax.swing.JLabel();
        p8 = new javax.swing.JPanel();
        name8 = new javax.swing.JLabel();
        price8 = new javax.swing.JLabel();
        image8 = new javax.swing.JLabel();
        p9 = new javax.swing.JPanel();
        name9 = new javax.swing.JLabel();
        price9 = new javax.swing.JLabel();
        image9 = new javax.swing.JLabel();
        p10 = new javax.swing.JPanel();
        name10 = new javax.swing.JLabel();
        price10 = new javax.swing.JLabel();
        image10 = new javax.swing.JLabel();
        p11 = new javax.swing.JPanel();
        name11 = new javax.swing.JLabel();
        price11 = new javax.swing.JLabel();
        image11 = new javax.swing.JLabel();
        p12 = new javax.swing.JPanel();
        name12 = new javax.swing.JLabel();
        price12 = new javax.swing.JLabel();
        image12 = new javax.swing.JLabel();
        p13 = new javax.swing.JPanel();
        name13 = new javax.swing.JLabel();
        price13 = new javax.swing.JLabel();
        image13 = new javax.swing.JLabel();
        p14 = new javax.swing.JPanel();
        name14 = new javax.swing.JLabel();
        price14 = new javax.swing.JLabel();
        image14 = new javax.swing.JLabel();
        p15 = new javax.swing.JPanel();
        name15 = new javax.swing.JLabel();
        price15 = new javax.swing.JLabel();
        image15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        productsContainer = new javax.swing.JPanel();
        product_is_empty = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        product_search_bar = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        filterContainer1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        filter_product_table1 = new javax.swing.JComboBox<>();
        product_table_add_button1 = new javax.swing.JButton();
        productInfo = new javax.swing.JPanel();
        product_name = new javax.swing.JLabel();
        product_price = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        product_description = new javax.swing.JEditorPane();
        add_to_cart = new javax.swing.JButton();
        buy_now = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        displayQuant = new javax.swing.JTextField();
        s1 = new javax.swing.JPanel();
        product_photo = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        product_sold = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        product_rating = new javax.swing.JLabel();
        s2 = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        view_shop = new javax.swing.JButton();
        chat_now = new javax.swing.JButton();
        product_shop_name = new javax.swing.JLabel();
        seller_rating2 = new javax.swing.JLabel();
        seller_joined = new javax.swing.JLabel();
        seller_rating5 = new javax.swing.JLabel();
        seller_rating6 = new javax.swing.JLabel();
        seller__profile_rating = new javax.swing.JLabel();
        seller_products = new javax.swing.JLabel();
        seller_profile = new javax.swing.JLabel();
        add_to_wishlist = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        product_category = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        add_to_wishlist1 = new javax.swing.JButton();
        product_favorites = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        cartViewContainer = new javax.swing.JPanel();
        quantity_decrease = new javax.swing.JButton();
        quantity_increase = new javax.swing.JButton();
        txtNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        product_shop_name2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        payment = new javax.swing.JToggleButton();
        photo = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        cartTableContainer = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        cart_is_empty = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cart_table = new javax.swing.JTable();
        orders_search_bar = new javax.swing.JTextField();
        deleteCart = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        yawa = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        notess = new javax.swing.JEditorPane();
        tamount = new javax.swing.JLabel();
        sc2 = new javax.swing.JLabel();
        product_price3 = new javax.swing.JLabel();
        checkout = new javax.swing.JButton();
        error = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        myprofile = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        select_image = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        manage1 = new javax.swing.JLabel();
        manage2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        manage3 = new javax.swing.JLabel();
        manage4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        manage6 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        myprofile1 = new javax.swing.JLabel();
        manage5 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        manage13 = new javax.swing.JLabel();
        edit_phone_number = new javax.swing.JTextField();
        manage14 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        edit_profile_save_button = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        buyer_photo_profile = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jSeparator15 = new javax.swing.JSeparator();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cartTableContainer1 = new javax.swing.JPanel();
        jSeparator17 = new javax.swing.JSeparator();
        purchase_is_empty = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        purchase_table = new javax.swing.JTable();
        purchase_search_bar = new javax.swing.JTextField();
        filterContainer = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        filter_product_table = new javax.swing.JComboBox<>();
        product_table_add_button = new javax.swing.JButton();
        product_table_archive_button = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        profile2 = new javax.swing.JPanel();
        profile1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        edit1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        myprofile6 = new javax.swing.JLabel();
        logout1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        manage8 = new javax.swing.JLabel();
        myprofile7 = new javax.swing.JLabel();
        manage7 = new javax.swing.JLabel();
        fname1 = new javax.swing.JTextField();
        manage10 = new javax.swing.JLabel();
        fname2 = new javax.swing.JTextField();
        fname3 = new javax.swing.JTextField();
        myprofile5 = new javax.swing.JLabel();
        fname4 = new javax.swing.JTextField();
        fname5 = new javax.swing.JTextField();
        fname6 = new javax.swing.JTextField();
        savebtn = new javax.swing.JButton();
        manage9 = new javax.swing.JLabel();
        manage11 = new javax.swing.JLabel();
        manage12 = new javax.swing.JLabel();
        myprofile8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        s3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        product_shop_name1 = new javax.swing.JLabel();
        category = new javax.swing.JLabel();
        checkout_button = new javax.swing.JButton();
        product_price1 = new javax.swing.JLabel();
        product_price2 = new javax.swing.JLabel();
        total_quantity = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        total_price = new javax.swing.JLabel();
        unit_price = new javax.swing.JLabel();
        product_name2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        notes = new javax.swing.JEditorPane();
        checkout_photo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jToggleButton8 = new javax.swing.JToggleButton();
        jSeparator18 = new javax.swing.JSeparator();
        s4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        product_category1 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        status_address = new javax.swing.JLabel();
        order_status = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        status_total = new javax.swing.JLabel();
        status_quantity = new javax.swing.JLabel();
        status_name = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jPanel18 = new javax.swing.JPanel();
        jToggleButton14 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jSeparator21 = new javax.swing.JSeparator();
        select_image1 = new javax.swing.JButton();
        jSeparator22 = new javax.swing.JSeparator();
        manage15 = new javax.swing.JLabel();
        manage16 = new javax.swing.JLabel();
        email1 = new javax.swing.JTextField();
        manage17 = new javax.swing.JLabel();
        manage18 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        fname7 = new javax.swing.JTextField();
        manage19 = new javax.swing.JLabel();
        lname1 = new javax.swing.JTextField();
        myprofile2 = new javax.swing.JLabel();
        manage20 = new javax.swing.JLabel();
        address1 = new javax.swing.JTextField();
        manage21 = new javax.swing.JLabel();
        edit_phone_number1 = new javax.swing.JTextField();
        manage22 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        edit_profile_save_button1 = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        buyer_photo_profile1 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        productsContainer1 = new javax.swing.JPanel();
        wishlist_is_empty = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        wishlist_table = new javax.swing.JTable();
        wishlist_search_bar = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        filterContainer2 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        filter_product_table2 = new javax.swing.JComboBox<>();
        product_table_add_button2 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/purchase.png"))); // NOI18N
        jLabel1.setText("My Purchases");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, -1, 40));

        myCart.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        myCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cart.png"))); // NOI18N
        myCart.setText("My Cart");
        myCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myCartMouseClicked(evt);
            }
        });
        jPanel2.add(myCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 20, -1, 40));

        jLabel3.setFont(getFont());
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/myProfile.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 20, 40, 40));

        home.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        home.setForeground(new java.awt.Color(0, 158, 226));
        home.setText("SHOPTIFY");
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel2.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-home-16.png"))); // NOI18N
        jLabel2.setText(" HOME");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, -1, 40));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 10));

        tabs.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        p1.setBackground(new java.awt.Color(255, 255, 255));
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
        });
        p1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name1.setForeground(new java.awt.Color(51, 51, 51));
        p1.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price1.setForeground(new java.awt.Color(153, 153, 153));
        p1.add(price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p1.add(image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p1);
        p1.setBounds(150, 60, 180, 180);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Show more");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11);
        jLabel11.setBounds(950, 650, 180, 20);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator1.setAlignmentX(0.2F);
        jSeparator1.setAlignmentY(0.2F);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 40, 1280, 10);

        home_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        home_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        home_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(home_is_empty);
        home_is_empty.setBounds(0, 300, 1280, 30);

        p2.setBackground(new java.awt.Color(255, 255, 255));
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
        });
        p2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name2.setForeground(new java.awt.Color(51, 51, 51));
        p2.add(name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price2.setForeground(new java.awt.Color(153, 153, 153));
        p2.add(price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p2.add(image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p2);
        p2.setBounds(350, 60, 180, 180);

        p3.setBackground(new java.awt.Color(255, 255, 255));
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
        });
        p3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name3.setForeground(new java.awt.Color(51, 51, 51));
        p3.add(name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price3.setForeground(new java.awt.Color(153, 153, 153));
        p3.add(price3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p3.add(image3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p3);
        p3.setBounds(550, 60, 180, 180);

        p4.setBackground(new java.awt.Color(255, 255, 255));
        p4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p4MouseClicked(evt);
            }
        });
        p4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name4.setForeground(new java.awt.Color(51, 51, 51));
        p4.add(name4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price4.setForeground(new java.awt.Color(153, 153, 153));
        p4.add(price4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p4.add(image4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p4);
        p4.setBounds(750, 60, 180, 180);

        p5.setBackground(new java.awt.Color(255, 255, 255));
        p5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p5MouseClicked(evt);
            }
        });
        p5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name5.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name5.setForeground(new java.awt.Color(51, 51, 51));
        p5.add(name5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price5.setForeground(new java.awt.Color(153, 153, 153));
        p5.add(price5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p5.add(image5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p5);
        p5.setBounds(950, 60, 180, 180);

        p6.setBackground(new java.awt.Color(255, 255, 255));
        p6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p6MouseClicked(evt);
            }
        });
        p6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name6.setForeground(new java.awt.Color(51, 51, 51));
        p6.add(name6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price6.setForeground(new java.awt.Color(153, 153, 153));
        p6.add(price6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p6.add(image6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p6);
        p6.setBounds(150, 260, 180, 180);

        p7.setBackground(new java.awt.Color(255, 255, 255));
        p7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p7MouseClicked(evt);
            }
        });
        p7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name7.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name7.setForeground(new java.awt.Color(51, 51, 51));
        p7.add(name7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price7.setForeground(new java.awt.Color(153, 153, 153));
        p7.add(price7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p7.add(image7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p7);
        p7.setBounds(350, 260, 180, 180);

        p8.setBackground(new java.awt.Color(255, 255, 255));
        p8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p8MouseClicked(evt);
            }
        });
        p8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name8.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name8.setForeground(new java.awt.Color(51, 51, 51));
        p8.add(name8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price8.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price8.setForeground(new java.awt.Color(153, 153, 153));
        p8.add(price8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p8.add(image8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p8);
        p8.setBounds(550, 260, 180, 180);

        p9.setBackground(new java.awt.Color(255, 255, 255));
        p9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p9MouseClicked(evt);
            }
        });
        p9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name9.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name9.setForeground(new java.awt.Color(51, 51, 51));
        p9.add(name9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price9.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price9.setForeground(new java.awt.Color(153, 153, 153));
        p9.add(price9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p9.add(image9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p9);
        p9.setBounds(750, 260, 180, 180);

        p10.setBackground(new java.awt.Color(255, 255, 255));
        p10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p10MouseClicked(evt);
            }
        });
        p10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name10.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name10.setForeground(new java.awt.Color(51, 51, 51));
        p10.add(name10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price10.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price10.setForeground(new java.awt.Color(153, 153, 153));
        p10.add(price10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p10.add(image10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p10);
        p10.setBounds(950, 260, 180, 180);

        p11.setBackground(new java.awt.Color(255, 255, 255));
        p11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p11MouseClicked(evt);
            }
        });
        p11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name11.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name11.setForeground(new java.awt.Color(51, 51, 51));
        p11.add(name11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price11.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price11.setForeground(new java.awt.Color(153, 153, 153));
        p11.add(price11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p11.add(image11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p11);
        p11.setBounds(150, 460, 180, 180);

        p12.setBackground(new java.awt.Color(255, 255, 255));
        p12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p12MouseClicked(evt);
            }
        });
        p12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name12.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name12.setForeground(new java.awt.Color(51, 51, 51));
        p12.add(name12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price12.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price12.setForeground(new java.awt.Color(153, 153, 153));
        p12.add(price12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p12.add(image12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p12);
        p12.setBounds(350, 460, 180, 180);

        p13.setBackground(new java.awt.Color(255, 255, 255));
        p13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p13MouseClicked(evt);
            }
        });
        p13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name13.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name13.setForeground(new java.awt.Color(51, 51, 51));
        p13.add(name13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price13.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price13.setForeground(new java.awt.Color(153, 153, 153));
        p13.add(price13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p13.add(image13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p13);
        p13.setBounds(550, 460, 180, 180);

        p14.setBackground(new java.awt.Color(255, 255, 255));
        p14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p14MouseClicked(evt);
            }
        });
        p14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name14.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name14.setForeground(new java.awt.Color(51, 51, 51));
        p14.add(name14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price14.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price14.setForeground(new java.awt.Color(153, 153, 153));
        p14.add(price14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p14.add(image14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p14);
        p14.setBounds(750, 460, 180, 180);

        p15.setBackground(new java.awt.Color(255, 255, 255));
        p15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p15MouseClicked(evt);
            }
        });
        p15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name15.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        name15.setForeground(new java.awt.Color(51, 51, 51));
        p15.add(name15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, 25));

        price15.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        price15.setForeground(new java.awt.Color(153, 153, 153));
        p15.add(price15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 30));
        p15.add(image15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 130));

        jPanel1.add(p15);
        p15.setBounds(950, 460, 180, 180);

        tabs.addTab("tab1", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productsContainer.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        product_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsContainer.add(product_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 1240, 60));
        productsContainer.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1240, 10));

        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        product_table.setAutoCreateRowSorter(true);
        product_table.setBackground(new java.awt.Color(241, 241, 241));
        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        product_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        product_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(product_table);

        productsContainer.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1240, 540));

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
        productsContainer.add(product_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel30.setBackground(new java.awt.Color(241, 241, 241));
        jLabel30.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Product Table");
        productsContainer.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer1.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setBackground(new java.awt.Color(241, 241, 241));
        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Filter by:");
        filterContainer1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table1.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Grocery", "Pet Supplies", "Fashion" }));
        filter_product_table1.setSelectedIndex(-1);
        filter_product_table1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filterContainer1.add(filter_product_table1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer.add(filterContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        product_table_add_button1.setBackground(new java.awt.Color(0, 158, 226));
        product_table_add_button1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_add_button1.setForeground(new java.awt.Color(255, 255, 255));
        product_table_add_button1.setText("View product");
        product_table_add_button1.setBorderPainted(false);
        product_table_add_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_add_button1ActionPerformed(evt);
            }
        });
        productsContainer.add(product_table_add_button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 130, 40));

        jPanel3.add(productsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1240, 620));

        tabs.addTab("tab2", jPanel3);

        productInfo.setBackground(new java.awt.Color(255, 255, 255));
        productInfo.setLayout(null);

        product_name.setFont(new java.awt.Font("Segoe UI Black", 1, 45)); // NOI18N
        product_name.setForeground(new java.awt.Color(51, 51, 51));
        product_name.setText("Macbook Airsssssssssss");
        productInfo.add(product_name);
        product_name.setBounds(660, 120, 570, 40);

        product_price.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        product_price.setForeground(new java.awt.Color(102, 102, 102));
        product_price.setText("₱ 85,999");
        productInfo.add(product_price);
        product_price.setBounds(660, 160, 520, 70);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        product_description.setEditable(false);
        product_description.setBackground(new java.awt.Color(255, 255, 255));
        product_description.setBorder(null);
        product_description.setEditorKit(null);
        product_description.setForeground(new java.awt.Color(102, 102, 102));
        product_description.setFocusable(false);
        product_description.setMargin(new java.awt.Insets(10, 10, 10, 10));
        product_description.setName(""); // NOI18N
        product_description.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(product_description);

        productInfo.add(jScrollPane2);
        jScrollPane2.setBounds(670, 370, 540, 180);

        add_to_cart.setBackground(new java.awt.Color(241, 241, 241));
        add_to_cart.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add_to_cart.setText("Add to Cart");
        add_to_cart.setBorderPainted(false);
        add_to_cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_cartActionPerformed(evt);
            }
        });
        productInfo.add(add_to_cart);
        add_to_cart.setBounds(740, 570, 230, 50);

        buy_now.setBackground(new java.awt.Color(0, 158, 226));
        buy_now.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        buy_now.setForeground(new java.awt.Color(255, 255, 255));
        buy_now.setText("Buy it now");
        buy_now.setBorderPainted(false);
        buy_now.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buy_nowActionPerformed(evt);
            }
        });
        productInfo.add(buy_now);
        buy_now.setBounds(980, 570, 230, 50);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel4.setText("Description");
        productInfo.add(jLabel4);
        jLabel4.setBounds(670, 340, 110, 30);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        productInfo.add(jButton2);
        jButton2.setBounds(760, 280, 60, 50);

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        productInfo.add(jButton3);
        jButton3.setBounds(660, 280, 50, 50);

        displayQuant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        displayQuant.setText("1");
        displayQuant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayQuantActionPerformed(evt);
            }
        });
        productInfo.add(displayQuant);
        displayQuant.setBounds(710, 280, 50, 50);

        s1.setBackground(new java.awt.Color(241, 241, 241));
        s1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        s1.add(product_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 360, 260));

        productInfo.add(s1);
        s1.setBounds(140, 120, 470, 360);

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator4.setAlignmentX(0.2F);
        jSeparator4.setAlignmentY(0.2F);
        productInfo.add(jSeparator4);
        jSeparator4.setBounds(0, 40, 1280, 20);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product_sold.png"))); // NOI18N
        productInfo.add(jLabel23);
        jLabel23.setBounds(900, 230, 40, 50);

        product_sold.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        product_sold.setForeground(new java.awt.Color(153, 153, 153));
        product_sold.setText("Sold (12)");
        productInfo.add(product_sold);
        product_sold.setBounds(950, 230, 110, 50);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product_star.png"))); // NOI18N
        productInfo.add(jLabel24);
        jLabel24.setBounds(650, 230, 40, 50);

        product_rating.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        product_rating.setForeground(new java.awt.Color(153, 153, 153));
        product_rating.setText("5.0 (12)");
        productInfo.add(product_rating);
        product_rating.setBounds(700, 230, 70, 50);

        s2.setBackground(new java.awt.Color(241, 241, 241));
        s2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        s2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 20, 80));

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shop_icon_1.png"))); // NOI18N
        s2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 30, 40));

        view_shop.setBackground(new java.awt.Color(0, 158, 226));
        view_shop.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        view_shop.setForeground(new java.awt.Color(255, 255, 255));
        view_shop.setText("View Shop");
        view_shop.setBorderPainted(false);
        s2.add(view_shop, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 100, 40));

        chat_now.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        chat_now.setText("Chat now");
        chat_now.setBorderPainted(false);
        s2.add(chat_now, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 80, 40));

        product_shop_name.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_shop_name.setForeground(new java.awt.Color(51, 51, 51));
        product_shop_name.setText("Tindahan");
        s2.add(product_shop_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 130, 20));

        seller_rating2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_rating2.setForeground(new java.awt.Color(153, 153, 153));
        seller_rating2.setText("Joined");
        s2.add(seller_rating2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 60, 20));

        seller_joined.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        seller_joined.setForeground(new java.awt.Color(0, 158, 226));
        seller_joined.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        seller_joined.setText("6-18-2024");
        s2.add(seller_joined, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 80, 20));

        seller_rating5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_rating5.setForeground(new java.awt.Color(153, 153, 153));
        seller_rating5.setText("Products");
        s2.add(seller_rating5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 60, 20));

        seller_rating6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_rating6.setForeground(new java.awt.Color(153, 153, 153));
        seller_rating6.setText("Ratings");
        s2.add(seller_rating6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 60, 20));

        seller__profile_rating.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller__profile_rating.setForeground(new java.awt.Color(0, 158, 226));
        seller__profile_rating.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        seller__profile_rating.setText("5.0 (12)");
        s2.add(seller__profile_rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 50, 20));

        seller_products.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seller_products.setForeground(new java.awt.Color(0, 158, 226));
        seller_products.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        seller_products.setText("9");
        s2.add(seller_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 40, 20));
        s2.add(seller_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 80));

        productInfo.add(s2);
        s2.setBounds(140, 490, 470, 130);

        add_to_wishlist.setBackground(new java.awt.Color(241, 241, 241));
        add_to_wishlist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product_heart.png"))); // NOI18N
        add_to_wishlist.setBorderPainted(false);
        add_to_wishlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_wishlistActionPerformed(evt);
            }
        });
        productInfo.add(add_to_wishlist);
        add_to_wishlist.setBounds(660, 570, 70, 50);

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("Home  >");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        productInfo.add(jLabel13);
        jLabel13.setBounds(140, 80, 80, 30);

        product_category.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        product_category.setForeground(new java.awt.Color(153, 153, 153));
        productInfo.add(product_category);
        product_category.setBounds(220, 80, 380, 30);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product_heart.png"))); // NOI18N
        productInfo.add(jLabel26);
        jLabel26.setBounds(760, 230, 40, 50);

        add_to_wishlist1.setBackground(new java.awt.Color(241, 241, 241));
        add_to_wishlist1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product_heart.png"))); // NOI18N
        add_to_wishlist1.setBorderPainted(false);
        add_to_wishlist1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_wishlist1ActionPerformed(evt);
            }
        });
        productInfo.add(add_to_wishlist1);
        add_to_wishlist1.setBounds(660, 570, 70, 50);

        product_favorites.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        product_favorites.setForeground(new java.awt.Color(153, 153, 153));
        product_favorites.setText("Favorite (12)");
        productInfo.add(product_favorites);
        product_favorites.setBounds(810, 230, 110, 50);
        productInfo.add(jSeparator8);
        jSeparator8.setBounds(660, 550, 550, 20);

        tabs.addTab("tab3", productInfo);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cartViewContainer.setBackground(new java.awt.Color(241, 241, 241));
        cartViewContainer.setToolTipText("");
        cartViewContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        quantity_decrease.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        quantity_decrease.setText("-");
        quantity_decrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_decreaseActionPerformed(evt);
            }
        });
        cartViewContainer.add(quantity_decrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 40, 40));

        quantity_increase.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        quantity_increase.setText("+");
        quantity_increase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_increaseActionPerformed(evt);
            }
        });
        cartViewContainer.add(quantity_increase, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 40, 40));

        txtNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        cartViewContainer.add(txtNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 70, 40));

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Payment method");
        cartViewContainer.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, -1));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shop_icon_1.png"))); // NOI18N
        cartViewContainer.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 40));

        product_shop_name2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_shop_name2.setForeground(new java.awt.Color(51, 51, 51));
        product_shop_name2.setText("Tindahan");
        cartViewContainer.add(product_shop_name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 130, 20));

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Quanity");
        cartViewContainer.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        payment.setText("Cash on delivery");
        payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentActionPerformed(evt);
            }
        });
        cartViewContainer.add(payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 150, 40));
        cartViewContainer.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 270, 175));

        name.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cartViewContainer.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 270, 30));

        jPanel4.add(cartViewContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 100, 350, 360));

        cartTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        cartTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cartTableContainer.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 880, 20));

        cart_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        cart_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        cart_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartTableContainer.add(cart_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 290, 860, 60));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        cart_table.setBackground(new java.awt.Color(241, 241, 241));
        cart_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        cart_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        cart_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cart_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cart_table);

        cartTableContainer.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 880, 480));

        orders_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        orders_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        orders_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orders_search_barMouseClicked(evt);
            }
        });
        orders_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                orders_search_barKeyReleased(evt);
            }
        });
        cartTableContainer.add(orders_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        deleteCart.setBackground(new java.awt.Color(255, 51, 51));
        deleteCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        deleteCart.setBorder(null);
        deleteCart.setBorderPainted(false);
        deleteCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCartActionPerformed(evt);
            }
        });
        cartTableContainer.add(deleteCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 50, 40));

        add.setBackground(new java.awt.Color(0, 158, 226));
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add new Items");
        add.setBorder(null);
        add.setBorderPainted(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        cartTableContainer.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 120, 40));

        jPanel4.add(cartTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 880, 560));
        jPanel4.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 20));

        yawa.setBackground(new java.awt.Color(241, 241, 241));
        yawa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notess.setBackground(new java.awt.Color(241, 241, 241));
        jScrollPane5.setViewportView(notess);

        yawa.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 310, 50));

        tamount.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tamount.setForeground(new java.awt.Color(0, 158, 226));
        tamount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tamount.setText("₱  49000");
        yawa.add(tamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 70, 30));

        sc2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sc2.setForeground(new java.awt.Color(102, 102, 102));
        sc2.setText("Total:");
        yawa.add(sc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, 30));

        product_price3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_price3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        product_price3.setText("Notes");
        yawa.add(product_price3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 30));

        checkout.setBackground(new java.awt.Color(0, 158, 226));
        checkout.setForeground(new java.awt.Color(255, 255, 255));
        checkout.setText("Checkout");
        checkout.setBorder(null);
        checkout.setBorderPainted(false);
        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });
        yawa.add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 310, 50));

        error.setForeground(new java.awt.Color(255, 102, 102));
        yawa.add(error, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 320, -1));

        jPanel4.add(yawa, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 470, 350, 190));

        jLabel40.setBackground(new java.awt.Color(241, 241, 241));
        jLabel40.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(204, 204, 204));
        jLabel40.setText("Home  >");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 30));

        jLabel41.setBackground(new java.awt.Color(241, 241, 241));
        jLabel41.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("My Cart");
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, 30));

        tabs.addTab("tab4", jPanel4);

        myprofile.setBackground(new java.awt.Color(255, 255, 255));
        myprofile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit.setText("Profile");
        myprofile.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, 30));

        jPanel9.setBackground(new java.awt.Color(241, 241, 241));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel9.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 30, 380));

        select_image.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        select_image.setText("Select Image");
        select_image.setBorderPainted(false);
        select_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_imageActionPerformed(evt);
            }
        });
        jPanel9.add(select_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 350, 130, 40));
        jPanel9.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 940, 20));

        manage1.setForeground(new java.awt.Color(102, 102, 102));
        manage1.setText("Manage your account ");
        jPanel9.add(manage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        manage2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage2.setForeground(new java.awt.Color(102, 102, 102));
        manage2.setText("Username");
        jPanel9.add(manage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));
        jPanel9.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 270, 40));

        manage3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage3.setForeground(new java.awt.Color(102, 102, 102));
        manage3.setText("First name");
        jPanel9.add(manage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        manage4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage4.setForeground(new java.awt.Color(102, 102, 102));
        manage4.setText("Address");
        jPanel9.add(manage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, 40));

        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("chielbrc");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 20));
        jPanel9.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 270, 40));

        manage6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage6.setForeground(new java.awt.Color(102, 102, 102));
        manage6.setText("Last name");
        jPanel9.add(manage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));
        jPanel9.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 270, 40));

        myprofile1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        myprofile1.setText("My Profile");
        jPanel9.add(myprofile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 70, 30));

        manage5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage5.setForeground(new java.awt.Color(102, 102, 102));
        manage5.setText("Phone Number");
        jPanel9.add(manage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));
        jPanel9.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 270, 40));

        manage13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage13.setForeground(new java.awt.Color(102, 102, 102));
        manage13.setText("Password");
        jPanel9.add(manage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, 40));
        jPanel9.add(edit_phone_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 270, 40));

        manage14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage14.setForeground(new java.awt.Color(102, 102, 102));
        manage14.setText("Email");
        jPanel9.add(manage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        password.setText("         Password");
        jPanel9.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 270, 40));

        edit_profile_save_button.setBackground(new java.awt.Color(0, 158, 226));
        edit_profile_save_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_profile_save_button.setForeground(new java.awt.Color(255, 255, 255));
        edit_profile_save_button.setText("Save");
        edit_profile_save_button.setBorderPainted(false);
        edit_profile_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_profile_save_buttonActionPerformed(evt);
            }
        });
        jPanel9.add(edit_profile_save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 270, 40));

        jLabel71.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 158, 226));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel71.setText("Change");
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buyer_photo_profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buyer_photo_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 100x100.png"))); // NOI18N
        jPanel12.add(buyer_photo_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 130));

        jPanel9.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, 130, 130));

        jLabel72.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 158, 226));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText("Change");
        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, -1, -1));

        myprofile.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1000, 590));

        username.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        username.setText("username");
        myprofile.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 120, 30));
        myprofile.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 200, 20));
        myprofile.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 50, 160));

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator14.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator14.setAlignmentX(0.2F);
        jSeparator14.setAlignmentY(0.2F);
        myprofile.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 20));

        jToggleButton1.setBackground(new java.awt.Color(241, 241, 241));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/buyer_logout.png"))); // NOI18N
        jToggleButton1.setText("   Logout");
        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        myprofile.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 200, 40));

        jToggleButton2.setBackground(new java.awt.Color(241, 241, 241));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/buyer_buy.png"))); // NOI18N
        jToggleButton2.setText("My purchases");
        jToggleButton2.setBorder(null);
        jToggleButton2.setBorderPainted(false);
        jToggleButton2.setIconTextGap(10);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        myprofile.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 40));

        jToggleButton3.setBackground(new java.awt.Color(241, 241, 241));
        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/buyer_icon.png"))); // NOI18N
        jToggleButton3.setText("  My Account");
        jToggleButton3.setBorder(null);
        jToggleButton3.setBorderPainted(false);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        myprofile.add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 40));
        myprofile.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 200, 20));

        jToggleButton5.setBackground(new java.awt.Color(241, 241, 241));
        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/buyer_cart.png"))); // NOI18N
        jToggleButton5.setText("My cart       ");
        jToggleButton5.setBorder(null);
        jToggleButton5.setBorderPainted(false);
        jToggleButton5.setIconTextGap(10);
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        myprofile.add(jToggleButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 40));

        jToggleButton6.setBackground(new java.awt.Color(241, 241, 241));
        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/buyer_call_admin.png"))); // NOI18N
        jToggleButton6.setText("Contact admin");
        jToggleButton6.setBorder(null);
        jToggleButton6.setBorderPainted(false);
        myprofile.add(jToggleButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 200, 40));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myprofile.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 70));

        tabs.addTab("tab5", myprofile);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cartTableContainer1.setBackground(new java.awt.Color(241, 241, 241));
        cartTableContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cartTableContainer1.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1240, 20));

        purchase_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        purchase_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        purchase_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartTableContainer1.add(purchase_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 290, 1240, 60));

        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        purchase_table.setBackground(new java.awt.Color(241, 241, 241));
        purchase_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        purchase_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        purchase_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchase_tableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(purchase_table);

        cartTableContainer1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1240, 500));

        purchase_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        purchase_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        purchase_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchase_search_barMouseClicked(evt);
            }
        });
        purchase_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                purchase_search_barKeyReleased(evt);
            }
        });
        cartTableContainer1.add(purchase_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        filterContainer.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setBackground(new java.awt.Color(241, 241, 241));
        jLabel42.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Filter by:");
        filterContainer.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Accepted", "Declined" }));
        filter_product_table.setSelectedIndex(-1);
        filter_product_table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filterContainer.add(filter_product_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        cartTableContainer1.add(filterContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        product_table_add_button.setBackground(new java.awt.Color(0, 158, 226));
        product_table_add_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_add_button.setForeground(new java.awt.Color(255, 255, 255));
        product_table_add_button.setText("View order");
        product_table_add_button.setBorderPainted(false);
        product_table_add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_add_buttonActionPerformed(evt);
            }
        });
        cartTableContainer1.add(product_table_add_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 130, 40));

        product_table_archive_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_archive_button.setForeground(new java.awt.Color(51, 51, 51));
        product_table_archive_button.setText("Delete");
        product_table_archive_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_archive_buttonActionPerformed(evt);
            }
        });
        cartTableContainer1.add(product_table_archive_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 130, 40));

        jPanel7.add(cartTableContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1240, 590));

        jLabel43.setBackground(new java.awt.Color(241, 241, 241));
        jLabel43.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(204, 204, 204));
        jLabel43.setText("Home  >");
        jPanel7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jLabel44.setBackground(new java.awt.Color(241, 241, 241));
        jLabel44.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("My Orders");
        jPanel7.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, 30));

        tabs.addTab("tab6", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("customer info");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 205, -1, -1));

        tabs.addTab("tab7", jPanel8);

        profile2.setBackground(new java.awt.Color(245, 245, 245));
        profile2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile1.setText("jLabel12");
        profile2.add(profile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 40, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("username");
        profile2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        edit1.setText("Edit Profile");
        profile2.add(edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));
        profile2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 150, 20));

        myprofile6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        myprofile6.setText("Address");
        myprofile6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myprofile6MouseClicked(evt);
            }
        });
        profile2.add(myprofile6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 80, 20));

        logout1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logout1.setText("Logout");
        logout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logout1MouseClicked(evt);
            }
        });
        profile2.add(logout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 80, 20));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel11.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 860, 20));

        manage8.setForeground(new java.awt.Color(102, 102, 102));
        manage8.setText("Manage your address");
        jPanel11.add(manage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        myprofile7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        myprofile7.setText("My Address");
        jPanel11.add(myprofile7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 30));

        manage7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage7.setForeground(new java.awt.Color(102, 102, 102));
        manage7.setText("Municipality");
        jPanel11.add(manage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));
        jPanel11.add(fname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 270, 40));

        manage10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage10.setForeground(new java.awt.Color(102, 102, 102));
        manage10.setText("City");
        jPanel11.add(manage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));
        jPanel11.add(fname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 270, 40));
        jPanel11.add(fname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 270, 40));

        myprofile5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        myprofile5.setForeground(new java.awt.Color(102, 102, 102));
        myprofile5.setText("My Account");
        myprofile5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myprofile5MouseClicked(evt);
            }
        });
        jPanel11.add(myprofile5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 80, -1));
        jPanel11.add(fname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 270, 40));
        jPanel11.add(fname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 270, 40));
        jPanel11.add(fname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 270, 40));

        savebtn.setBackground(new java.awt.Color(0, 158, 226));
        savebtn.setForeground(new java.awt.Color(255, 255, 255));
        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });
        jPanel11.add(savebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, -1, 30));

        manage9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage9.setForeground(new java.awt.Color(102, 102, 102));
        manage9.setText("Municipality");
        jPanel11.add(manage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        manage11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage11.setForeground(new java.awt.Color(102, 102, 102));
        manage11.setText("Municipality");
        jPanel11.add(manage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        manage12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage12.setForeground(new java.awt.Color(102, 102, 102));
        manage12.setText("Municipality");
        jPanel11.add(manage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        profile2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 1000, 600));

        myprofile8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        myprofile8.setText("My Account");
        myprofile8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myprofile8MouseClicked(evt);
            }
        });
        profile2.add(myprofile8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 80, 20));

        tabs.addTab("tab8", profile2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s3.setBackground(new java.awt.Color(241, 241, 241));
        s3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shop_icon_1.png"))); // NOI18N
        s3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 30, 40));

        product_shop_name1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_shop_name1.setForeground(new java.awt.Color(51, 51, 51));
        product_shop_name1.setText("Tindahan");
        s3.add(product_shop_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 130, 40));

        category.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        category.setText("Electronics");
        s3.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, 40));

        checkout_button.setBackground(new java.awt.Color(0, 158, 226));
        checkout_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        checkout_button.setForeground(new java.awt.Color(255, 255, 255));
        checkout_button.setText("Checkout");
        checkout_button.setBorderPainted(false);
        checkout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkout_buttonActionPerformed(evt);
            }
        });
        s3.add(checkout_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 360, 250, 70));

        product_price1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_price1.setForeground(new java.awt.Color(102, 102, 102));
        product_price1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        product_price1.setText("Notes");
        s3.add(product_price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 50, 30));

        product_price2.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        product_price2.setForeground(new java.awt.Color(0, 158, 226));
        product_price2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        product_price2.setText("₱ 85,999");
        s3.add(product_price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 390, -1, 40));

        total_quantity.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        total_quantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_quantity.setText("12");
        s3.add(total_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 50, 40));

        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setText("Cancel");
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        s3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, -1, 40));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("Total");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        s3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 360, -1, 30));

        total_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        total_price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_price.setText("₱ 85,999");
        s3.add(total_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 110, -1, 40));

        unit_price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        unit_price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unit_price.setText("₱ 85,999");
        s3.add(unit_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 50, 40));

        product_name2.setFont(new java.awt.Font("Roboto", 0, 50)); // NOI18N
        product_name2.setForeground(new java.awt.Color(51, 51, 51));
        product_name2.setText("Macbook Air");
        s3.add(product_name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, 70));

        jScrollPane3.setViewportView(notes);

        s3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 250, 70));
        s3.add(checkout_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 110, 110));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Payment method");
        s3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, -1, 30));

        jToggleButton8.setText("Cash on delivery");
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });
        s3.add(jToggleButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 160, 70));
        s3.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 1070, 30));

        jPanel6.add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 1120, 450));

        s4.setBackground(new java.awt.Color(241, 241, 241));
        s4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("Action");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        s4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 0, -1, 50));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setText("Category");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        s4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, 50));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("Quantity");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        s4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, -1, 50));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 153, 153));
        jLabel21.setText("Total Price");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        s4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, -1, 50));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 153, 153));
        jLabel22.setText("Product");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        s4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 50));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setText("Unit Price");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        s4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, -1, 50));

        jPanel6.add(s4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 1120, 50));

        product_category1.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        product_category1.setForeground(new java.awt.Color(153, 153, 153));
        product_category1.setText("asdsssssssssssssssssssss");
        jPanel6.add(product_category1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 240, 20));

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator12.setAlignmentX(0.2F);
        jSeparator12.setAlignmentY(0.2F);
        jPanel6.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 20));

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("Home  >");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, 20));

        tabs.addTab("tab9", jPanel6);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(241, 241, 241));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("possible for you.");
        jPanel13.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        status_address.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        status_address.setForeground(new java.awt.Color(153, 153, 153));
        status_address.setText("Tunghaan, Minglanilla, Cebu");
        jPanel13.add(status_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        order_status.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        order_status.setText("Order confirmed!");
        jPanel13.add(order_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 410, -1));

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setText("Price");
        jPanel14.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, 40));

        jLabel33.setText("Product");
        jPanel14.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 410, 40));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel15.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 110));

        jPanel13.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 110));

        jButton1.setText("View invoice");
        jPanel13.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 200, 40));

        jLabel34.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel34.setText("Delivery address");
        jPanel13.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 410, -1));

        status_total.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        status_total.setForeground(new java.awt.Color(0, 158, 226));
        status_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        status_total.setText("₱ 85,999");
        jPanel13.add(status_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 140, -1));

        status_quantity.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status_quantity.setText("Quantity: 1");
        jPanel13.add(status_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 180, -1));

        status_name.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        status_name.setText("Macbook Airsssssssssss");
        jPanel13.add(status_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 230, -1));
        jPanel13.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 410, 30));

        jLabel38.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel38.setText("Order summary");
        jPanel13.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 410, -1));

        jLabel39.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(153, 153, 153));
        jLabel39.setText("We've received your order and are working hard to process it as quickly as");
        jPanel13.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jPanel10.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 470, 550));

        jPanel17.setBackground(new java.awt.Color(241, 241, 241));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton4.setText("jToggleButton4");
        jPanel17.add(jToggleButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 50, 40));

        jToggleButton7.setBackground(new java.awt.Color(241, 241, 241));
        jToggleButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/star_outline.png"))); // NOI18N
        jToggleButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToggleButton7.setBorderPainted(false);
        jToggleButton7.setFocusPainted(false);
        jToggleButton7.setInheritsPopupMenu(true);
        jToggleButton7.setOpaque(true);
        jToggleButton7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/image/star_filled.png"))); // NOI18N
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });
        jPanel17.add(jToggleButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 40, 40));

        jToggleButton9.setText("jToggleButton4");
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });
        jPanel17.add(jToggleButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 50, 40));

        jToggleButton10.setText("jToggleButton4");
        jPanel17.add(jToggleButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 50, 40));

        jToggleButton11.setText("jToggleButton4");
        jPanel17.add(jToggleButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 50, 40));

        jPanel10.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 370, 120));

        jPanel18.setBackground(new java.awt.Color(241, 241, 241));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton14.setText("jToggleButton4");
        jPanel18.add(jToggleButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 50, 40));

        jToggleButton13.setText("jToggleButton4");
        jPanel18.add(jToggleButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 50, 40));

        jToggleButton16.setText("jToggleButton4");
        jPanel18.add(jToggleButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 50, 40));

        jToggleButton12.setText("jToggleButton4");
        jPanel18.add(jToggleButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, 40));

        jToggleButton15.setText("jToggleButton4");
        jPanel18.add(jToggleButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 50, 40));

        jPanel10.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, 370, 120));

        tabs.addTab("tab10", jPanel10);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(241, 241, 241));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel20.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 30, 380));

        select_image1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        select_image1.setText("Select Image");
        select_image1.setBorderPainted(false);
        select_image1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_image1ActionPerformed(evt);
            }
        });
        jPanel20.add(select_image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 350, 130, 40));
        jPanel20.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 940, 20));

        manage15.setForeground(new java.awt.Color(102, 102, 102));
        manage15.setText("Manage your account ");
        jPanel20.add(manage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        manage16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage16.setForeground(new java.awt.Color(102, 102, 102));
        manage16.setText("Username");
        jPanel20.add(manage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));
        jPanel20.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 270, 40));

        manage17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage17.setForeground(new java.awt.Color(102, 102, 102));
        manage17.setText("First name");
        jPanel20.add(manage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        manage18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage18.setForeground(new java.awt.Color(102, 102, 102));
        manage18.setText("Address");
        jPanel20.add(manage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, 40));

        jLabel45.setForeground(new java.awt.Color(153, 153, 153));
        jLabel45.setText("chielbrc");
        jPanel20.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 20));
        jPanel20.add(fname7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 270, 40));

        manage19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage19.setForeground(new java.awt.Color(102, 102, 102));
        manage19.setText("Last name");
        jPanel20.add(manage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));
        jPanel20.add(lname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 270, 40));

        myprofile2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        myprofile2.setText("My Profile");
        jPanel20.add(myprofile2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 70, 30));

        manage20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage20.setForeground(new java.awt.Color(102, 102, 102));
        manage20.setText("Phone Number");
        jPanel20.add(manage20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));
        jPanel20.add(address1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 270, 40));

        manage21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage21.setForeground(new java.awt.Color(102, 102, 102));
        manage21.setText("Password");
        jPanel20.add(manage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, 40));
        jPanel20.add(edit_phone_number1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 270, 40));

        manage22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage22.setForeground(new java.awt.Color(102, 102, 102));
        manage22.setText("Email");
        jPanel20.add(manage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        password1.setText("         Password");
        jPanel20.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 270, 40));

        edit_profile_save_button1.setBackground(new java.awt.Color(0, 158, 226));
        edit_profile_save_button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_profile_save_button1.setForeground(new java.awt.Color(255, 255, 255));
        edit_profile_save_button1.setText("Save");
        edit_profile_save_button1.setBorderPainted(false);
        edit_profile_save_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_profile_save_button1ActionPerformed(evt);
            }
        });
        jPanel20.add(edit_profile_save_button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 270, 40));

        jLabel73.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 158, 226));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText("Change");
        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });
        jPanel20.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buyer_photo_profile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buyer_photo_profile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sampleProfiles/default profile 100x100.png"))); // NOI18N
        jPanel21.add(buyer_photo_profile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 130));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, 130, 130));

        jLabel74.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 158, 226));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel74.setText("Change");
        jLabel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel74MouseClicked(evt);
            }
        });
        jPanel20.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, -1, -1));

        jPanel16.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1000, 590));

        tabs.addTab("tab11", jPanel16);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productsContainer1.setBackground(new java.awt.Color(241, 241, 241));
        productsContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        wishlist_is_empty.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        wishlist_is_empty.setForeground(new java.awt.Color(51, 51, 51));
        wishlist_is_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsContainer1.add(wishlist_is_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 1240, 60));
        productsContainer1.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1240, 10));

        jScrollPane8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        wishlist_table.setAutoCreateRowSorter(true);
        wishlist_table.setBackground(new java.awt.Color(241, 241, 241));
        wishlist_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        wishlist_table.setSelectionBackground(new java.awt.Color(204, 229, 255));
        wishlist_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wishlist_tableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(wishlist_table);

        productsContainer1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1240, 490));

        wishlist_search_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        wishlist_search_bar.setForeground(new java.awt.Color(140, 140, 140));
        wishlist_search_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wishlist_search_barMouseClicked(evt);
            }
        });
        wishlist_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                wishlist_search_barKeyReleased(evt);
            }
        });
        productsContainer1.add(wishlist_search_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        jLabel35.setBackground(new java.awt.Color(241, 241, 241));
        jLabel35.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Product Table");
        productsContainer1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        filterContainer2.setBackground(new java.awt.Color(255, 255, 255));
        filterContainer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setBackground(new java.awt.Color(241, 241, 241));
        jLabel36.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Filter by:");
        filterContainer2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        filter_product_table2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filter_product_table2.setForeground(new java.awt.Color(153, 153, 153));
        filter_product_table2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronics", "Grocery", "Pet Supplies", "Fashion" }));
        filter_product_table2.setSelectedIndex(-1);
        filter_product_table2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        filterContainer2.add(filter_product_table2, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 110, 30));

        productsContainer1.add(filterContainer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 180, 40));

        product_table_add_button2.setBackground(new java.awt.Color(0, 158, 226));
        product_table_add_button2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_table_add_button2.setForeground(new java.awt.Color(255, 255, 255));
        product_table_add_button2.setText("View product");
        product_table_add_button2.setBorderPainted(false);
        product_table_add_button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_table_add_button2ActionPerformed(evt);
            }
        });
        productsContainer1.add(product_table_add_button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 130, 40));

        jPanel19.add(productsContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1240, 570));

        jLabel37.setFont(new java.awt.Font("Segoe UI Black", 0, 30)); // NOI18N
        jLabel37.setText("My Favorties");
        jPanel19.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 60));

        tabs.addTab("tab12", jPanel19);

        jPanel5.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
        panelMouseClicked(p1, name1, price1, image1);
    }//GEN-LAST:event_p1MouseClicked

    private void add_to_cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_cartActionPerformed
        String productName = product_name.getText();
        String cartPriceStr = product_price.getText().replaceAll("[^0-9]", "");
        int cartPrice = Integer.parseInt(cartPriceStr);
        String displayQuantStr = displayQuant.getText();
        int cartQuant = Integer.parseInt(displayQuantStr);

        try {
            databaseConnector dbc = new databaseConnector();

            // Check if the product already exists in the cart for the logged-in user
            String checkProductQuery = "SELECT * FROM tbl_cart WHERE buyer_id = ? AND product_id = ?";
            try (PreparedStatement checkProductStmt = dbc.getConnection().prepareStatement(checkProductQuery)) {
                checkProductStmt.setInt(1, buyer_id);
                checkProductStmt.setInt(2, product_id);
                ResultSet checkRs = checkProductStmt.executeQuery();

                if (checkRs.next()) {
                    // If the product exists, update the product_quantity
                    int existingQuant = checkRs.getInt("product_quantity");
                    int newQuant = existingQuant + cartQuant;

                    String updateQuery = "UPDATE tbl_cart SET product_quantity = ? WHERE buyer_id = ? AND product_id = ?";
                    PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                    updateStmt.setInt(1, newQuant);
                    updateStmt.setInt(2, buyer_id);
                    updateStmt.setInt(3, product_id);
                    updateStmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Item added to the cart successfully!");
                    displayCart();
                    tabs.setSelectedIndex(0);
                } else {
                    // If the product doesn't exist, insert a new record
                    String insertQuery = "INSERT INTO tbl_cart (buyer_id, product_id, product_quantity, seller_id, date_added) VALUES (?, ?, ?, ?, NOW())";
                    PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                    insertStmt.setInt(1, buyer_id);
                    insertStmt.setInt(2, product_id);
                    insertStmt.setInt(3, cartQuant);
                    insertStmt.setInt(4, seller_id);

                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Item added to the cart successfully!");
                }

                checkRs.close();
            }
            num = 1;
            displayQuant.setText(String.valueOf(num));
            displayCart();
            tabs.setSelectedIndex(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_add_to_cartActionPerformed

    private void myCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCartMouseClicked
        displayCart();
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_myCartMouseClicked


    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        tabs.setSelectedIndex(4);
        displayAccountName();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void myprofile6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myprofile6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_myprofile6MouseClicked

    private void logout1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout1MouseClicked
        Login out = new Login();
        out.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logout1MouseClicked

    private void myprofile5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myprofile5MouseClicked
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_myprofile5MouseClicked

    private void myprofile8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myprofile8MouseClicked
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_myprofile8MouseClicked

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_savebtnActionPerformed

    String buyPriceStr;
    int buyPrice;
    String buyQuantStr;
    int buyQuant;
    private void buy_nowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buy_nowActionPerformed
        String productName = product_name.getText();
        String productPrice = product_price.getText();
        buyPriceStr = product_price.getText().replaceAll("[^0-9]", "");
        buyPrice = Integer.parseInt(buyPriceStr);
        buyQuantStr = displayQuant.getText();
        buyQuant = Integer.parseInt(buyQuantStr);
        totalPrice = buyPrice * buyQuant;

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        total_price.setText(String.valueOf("₱  " + numberFormat.format(totalPrice)));
        product_price2.setText(String.valueOf("₱  " + numberFormat.format(totalPrice)));
        unit_price.setText(productPrice);
        total_quantity.setText(String.format("%d", num));
        product_name2.setText(productName);
        category.setText(shop);
        int height = 110;
        int width = 110;
        GetImage.displayImage(checkout_photo, product_image, height, width);
        tabs.setSelectedIndex(8);
    }//GEN-LAST:event_buy_nowActionPerformed


    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        tabs.setSelectedIndex(0);
        displayUserProducts();
    }//GEN-LAST:event_homeMouseClicked

    private int tblQuant = 0;
    private int tblPrice;
    private int total = 0;
    private int stock;

    private void quantity_increaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantity_increaseActionPerformed
        if (tblQuant < stock) {
            tblQuant++;
            txtNumber.setText(String.valueOf(tblQuant));
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient stock. Available stock: " + stock);
        }
    }//GEN-LAST:event_quantity_increaseActionPerformed

    private void quantity_decreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantity_decreaseActionPerformed
        if (tblQuant > 1) {
            tblQuant--;
            txtNumber.setText(String.valueOf(tblQuant));
            updateTotal();
        }
    }//GEN-LAST:event_quantity_decreaseActionPerformed

    private void updateTotal() {
        total = tblQuant * tblPrice;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        tamount.setText(String.valueOf("₱  " + numberFormat.format(total)));
    }
    private int cart_id;
    private void cart_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart_tableMouseClicked
        int rowIndex = cart_table.getSelectedRow();
        TableModel model = cart_table.getModel();

        updateTotal();

        cart_id = Integer.parseInt(model.getValueAt(rowIndex, 0).toString());

        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT "
                    + "a.shop_name AS `shop_name`, "
                    + "p.product_stock AS `product_stock`, "
                    + "p.product_id AS `product_id`, "
                    + "c.product_id AS `product_id`, "
                    + "p.product_image AS `product_image`, "
                    + "p.product_price AS `product_price`, "
                    + "p.product_name AS `product_name`, "
                    + "c.product_quantity AS `product_quantity` "
                    + "FROM tbl_cart c "
                    + "JOIN tbl_products p ON p.product_id = c.product_id "
                    + "JOIN tbl_accounts a ON a.account_id = c.seller_id "
                    + "WHERE c.cart_id = ?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, cart_id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        stock = rs.getInt("product_stock");
                        product_id = rs.getInt("product_id");
                        tblPrice = rs.getInt("product_price");
                        tblQuant = rs.getInt("product_quantity");
                        txtNumber.setText(String.format("%d", tblQuant));
                        tamount.setText("₱ " + rs.getString("product_price"));
                        product_shop_name2.setText(rs.getString("shop_name"));
                        name.setText(rs.getString("product_name"));
                        int height = 270;
                        int width = 175;
                        String getImageFromDatabase = rs.getString("product_image");
                        GetImage.displayImage(photo, getImageFromDatabase, height, width);
                    } else {
                        JOptionPane.showMessageDialog(null, "No stock found for product_id: " + product_id);
                    }
                }
            }
            dbc.getConnection().close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cart_tableMouseClicked

    private void deleteCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCartActionPerformed
        int rowIndex = cart_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a product first");
        } else {
            TableModel model = cart_table.getModel();
            Object value = model.getValueAt(rowIndex, 0);
            String id = value.toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                databaseConnector dbc = new databaseConnector();
                dbc.deleteCart(Integer.parseInt(id));
                num = 1;
                displayQuant.setText(String.valueOf(num));
                total = 0;
                tamount.setText(String.valueOf(total));
                name.setText("");
                photo.setIcon(null);
                displayCart();
            }
        }
    }//GEN-LAST:event_deleteCartActionPerformed
    private int num = 1;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (num < quan) {
            num++;
            displayQuant.setText(String.valueOf(num));
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient stock. Available stock: " + quan);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (num > 1) {
            num--;
            displayQuant.setText(String.valueOf(num));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    int new_count;
    int new_total_price;

    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        try {
            String NOTESS = notess.getText();
            databaseConnector dbc = new databaseConnector();
            String cod = payment_method;
            String fetchProductQuery = "SELECT product_stock FROM tbl_products WHERE product_id = ?";
            PreparedStatement fetchProductStmt = dbc.getConnection().prepareStatement(fetchProductQuery);
            fetchProductStmt.setInt(1, product_id);
            ResultSet fetchRs = fetchProductStmt.executeQuery();

            if (!fetchRs.next()) {
                JOptionPane.showMessageDialog(null, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int currentStock = fetchRs.getInt("product_stock");

            if (tblQuant > currentStock) {
                JOptionPane.showMessageDialog(null, "Insufficient stock. Available stock: " + currentStock);
                return;
            }

            if (cod.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a payment method!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (NOTESS.length() > 40) {
                error.setText("* Notes exceed maximum character limit!");
                notess.setText("");
                return;
            }

            // Check if the purchase already exists
            String checkPurchaseQuery = "SELECT * FROM tbl_orders WHERE buyer_id = ? AND product_id = ?";
            PreparedStatement checkPurchaseStmt = dbc.getConnection().prepareStatement(checkPurchaseQuery);
            checkPurchaseStmt.setInt(1, buyer_id);
            checkPurchaseStmt.setInt(2, product_id);
            ResultSet checkRs = checkPurchaseStmt.executeQuery();

            if (checkRs.next()) {
                // Purchase exists
                String orderStatus = checkRs.getString("order_status");
                if (orderStatus.equals("Pending")) {
                    // If order status is "Pending", update total_quantity and total_price
                    int existingQuant = checkRs.getInt("total_quantity");
                    int newCount = existingQuant + tblQuant;
                    int existingTotalPrice = checkRs.getInt("total_price");
                    int newTotalPrice = existingTotalPrice + (tblQuant * tblPrice);

                    // Update purchase
                    String updateQuery = "UPDATE tbl_orders SET total_quantity = ?, total_price = ? WHERE buyer_id = ? AND product_id = ?";
                    PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                    updateStmt.setInt(1, newCount);
                    updateStmt.setInt(2, newTotalPrice);
                    updateStmt.setInt(3, buyer_id);
                    updateStmt.setInt(4, product_id);
                    updateStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Purchase updated successfully!");
                } else {
                    // If order status is not "Pending", insert a new record
                    String insertQuery = "INSERT INTO tbl_orders (buyer_id, seller_id, product_id, total_quantity, total_price, notes, order_status, date_purchase, payment_method) VALUES (?, ?, ?, ?, ?, ?, 'Pending', NOW(), ?)";
                    PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery);
                    insertStmt.setInt(1, buyer_id);
                    insertStmt.setInt(2, seller_id);
                    insertStmt.setInt(3, product_id);
                    insertStmt.setInt(4, tblQuant);
                    insertStmt.setInt(5, tblQuant * tblPrice);
                    insertStmt.setString(6, NOTESS);
                    insertStmt.setString(7, payment_method);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "New purchase added successfully!");
                }
            } else {
                // If no matching order exists, insert a new record
                String insertQuery = "INSERT INTO tbl_orders (buyer_id, seller_id, product_id, total_quantity, total_price, notes, order_status, date_purchase, payment_method) VALUES (?, ?, ?, ?, ?, ?, 'Pending', NOW(), ?)";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery);
                insertStmt.setInt(1, buyer_id);
                insertStmt.setInt(2, seller_id);
                insertStmt.setInt(3, product_id);
                insertStmt.setInt(4, tblQuant);
                insertStmt.setInt(5, tblQuant * tblPrice);
                insertStmt.setString(6, NOTESS);
                insertStmt.setString(7, payment_method);
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "New purchase added successfully!");
            }

            // Perform other UI updates
            dbc.deleteCart(cart_id);
            num = 1;
            error.setText("");
            displayQuant.setText(String.valueOf(num));
            total = 0;
            tamount.setText(String.valueOf(total));
            displayPurchase();
            displayUserProducts();
            tabs.setSelectedIndex(0);
            name.setText("");
            photo.setIcon(null);
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error processing purchase: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_checkoutActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void add_to_wishlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_wishlistActionPerformed
        databaseConnector dbc = new databaseConnector();
        try {
            // Assuming buyer_id, seller_id, and product_id are already defined and have valid values

            // Check if the product is already in the wishlist for the buyer
            String checkPurchaseQuery = "SELECT * FROM tbl_wishlist WHERE buyer_id = ? AND product_id = ?";
            PreparedStatement checkPurchaseStmt = dbc.getConnection().prepareStatement(checkPurchaseQuery);
            checkPurchaseStmt.setInt(1, buyer_id);
            checkPurchaseStmt.setInt(2, product_id);

            ResultSet checkRs = checkPurchaseStmt.executeQuery();

            if (checkRs.next()) {
                // The product is already in the wishlist, display a message
                JOptionPane.showMessageDialog(null, "You have already added this product to your wishlist.");
            } else {
                // The product is not in the wishlist, so insert a new row with total_favorites set to 1
                String sql = "INSERT INTO `tbl_wishlist`(`buyer_id`, `seller_id`, `product_id`, `total_favorites`, `date_added`) VALUES (?, ?, ?, 1, NOW())";
                try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                    pst.setInt(1, buyer_id);
                    pst.setInt(2, seller_id);
                    pst.setInt(3, product_id);
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Product added to wishlist successfully.");
                display_wishlist();
            }

            tabs.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_add_to_wishlistActionPerformed

    private void add_to_wishlist1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_wishlist1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_to_wishlist1ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void displayQuantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayQuantActionPerformed

    }//GEN-LAST:event_displayQuantActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    int totalPrice;
    private void checkout_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkout_buttonActionPerformed
        String NOTES = notes.getText();
        String cod = payment_method;
        if (cod.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a payment method!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            databaseConnector dbc = new databaseConnector();

            // Check if the purchase already exists for the given cart ID and product name
            String checkPurchaseQuery = "SELECT * FROM tbl_orders WHERE buyer_id = ? AND product_id = ? AND order_status = 'Pending'";
            PreparedStatement checkPurchaseStmt = dbc.getConnection().prepareStatement(checkPurchaseQuery);
            checkPurchaseStmt.setInt(1, buyer_id);
            checkPurchaseStmt.setInt(2, product_id);

            ResultSet checkRs = checkPurchaseStmt.executeQuery();

            if (checkRs.next()) {
                // If purchase exists, update total_quantity and total_price
                int existingQuant = checkRs.getInt("total_quantity");
                int newQuant = existingQuant + buyQuant;
                int existingTotalPrice = checkRs.getInt("total_price");
                int newTotalPrice = existingTotalPrice + totalPrice;
                String updateQuery = "UPDATE tbl_orders SET total_quantity = ?, total_price = ? WHERE buyer_id = ? AND product_id = ?";
                PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                updateStmt.setInt(1, newQuant);
                updateStmt.setInt(2, newTotalPrice);
                updateStmt.setInt(3, buyer_id);
                updateStmt.setInt(4, product_id);

                updateStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Purchase updated successfully!");
                // Update stock and status if necessary
            } else {
                String insertQuery = "INSERT INTO tbl_orders (buyer_id, seller_id, product_id, total_quantity, total_price, notes, order_status, date_purchase) VALUES (?, ?, ?, ?, ?, ?, 'Pending', NOW())";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery);
                insertStmt.setInt(1, buyer_id);
                insertStmt.setInt(2, seller_id);
                insertStmt.setInt(3, product_id);
                insertStmt.setInt(4, buyQuant);
                insertStmt.setInt(5, totalPrice);
                insertStmt.setString(6, NOTES);

                insertStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Purchase added successfully!");
            }
            num = 1;
            displayQuant.setText(String.valueOf(num));
            displayPurchase();
            displayUserProducts();
            tabs.setSelectedIndex(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_checkout_buttonActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_addActionPerformed

    private void orders_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orders_search_barMouseClicked
        orders_search_bar.setFocusable(true);
        orders_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_orders_search_barMouseClicked

    private void orders_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orders_search_barKeyReleased
        search.searchResult(cart_table, orders_search_bar);
    }//GEN-LAST:event_orders_search_barKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_jButton5ActionPerformed
    File selectedFile;
    String imagePath;
    private void select_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_imageActionPerformed
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
                buyer_photo_profile.setIcon(icon);

                String imageName = selectedFile.getName();
                imagePath = "src/buyer_profile_pictures/" + imageName;
                File destination = new File(imagePath);
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                selectedFile = destination;

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_select_imageActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        Login out = new Login();
        out.setVisible(true);
        this.dispose();
        UserManager.logout();
    }//GEN-LAST:event_jToggleButton1ActionPerformed
    String fileName;

    private void edit_profile_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_profile_save_buttonActionPerformed
        String first_name = fname.getText();
        String last_name = lname.getText();
        String phone = edit_phone_number.getText();
        String addre = address.getText();

        if (first_name.isEmpty() || last_name.isEmpty() || phone.isEmpty() || addre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (phone.length() < 11 || phone.length() > 11 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedFile != null) {
            fileName = selectedFile.getName();
            imagePath = "src/buyer_profile_pioctures/" + fileName;
        } else {
            imagePath = profilePicture;
        }

        String sql;
        PreparedStatement pst;

        try {
            databaseConnector dbc = new databaseConnector();

            sql = "UPDATE tbl_accounts SET first_name=?, last_name=?, address=?, phone_number=?, profile_picture=? WHERE account_id=?";
            pst = dbc.getConnection().prepareStatement(sql);
            pst.setString(1, first_name);
            pst.setString(2, last_name);
            pst.setString(3, addre);
            pst.setString(4, phone);
            pst.setString(5, imagePath);
            pst.setInt(6, buyer_id);

            int rowsUpdated = pst.executeUpdate();
            pst.close();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
                displayAccountName();
                tabs.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update Account!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error adding product!" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_edit_profile_save_buttonActionPerformed

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked
        String oldPassword = JOptionPane.showInputDialog(null, "Enter your old password:");

        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your old password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isExist = isAccountExist.checkPassword(oldPassword, buyer_id);

        if (!isExist) {
            JOptionPane.showMessageDialog(null, "Your old password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a new password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newPassword.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            // Update the password in the database
            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String updateQuery = "UPDATE tbl_accounts SET password = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            int rowsAffected;
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery)) {
                pst.setString(1, hashedNewPassword);
                pst.setInt(2, buyer_id);
                rowsAffected = pst.executeUpdate();
            }

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
        }
    }//GEN-LAST:event_jLabel71MouseClicked

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        displayAccountName();
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
        String eemail = JOptionPane.showInputDialog(null, "Enter your new email address:");

        if (eemail == null || eemail.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isAccountExist.checkEmail(eemail)) {
            JOptionPane.showMessageDialog(null, "Email already registered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // Update the address in the database
            String updateQuery = "UPDATE tbl_accounts SET email = ? WHERE account_id = ?";
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, eemail);
            pst.setInt(2, buyer_id);
            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Email address updated successfully.");
                displayAccountName();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update email address.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update address.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel72MouseClicked
    int orderId;
    private void purchase_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchase_tableMouseClicked

    }//GEN-LAST:event_purchase_tableMouseClicked

    private void purchase_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchase_search_barMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_purchase_search_barMouseClicked

    private void purchase_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_purchase_search_barKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_purchase_search_barKeyReleased

    String payment_method = "";
    private void paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentActionPerformed
        payment_method = "Cash on delivery";
    }//GEN-LAST:event_paymentActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        tabs.setSelectedIndex(7);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void product_table_add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_add_buttonActionPerformed
        int rowIndex = purchase_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
        } else {
            TableModel model = purchase_table.getModel();
            orderId = (int) model.getValueAt(rowIndex, 0);

            try {
                databaseConnector dbc = new databaseConnector();
                ResultSet rs = dbc.getData("SELECT "
                        + "o.order_id AS order_id, "
                        + "p.product_name AS product_name, "
                        + "p.product_image AS product_image, "
                        + "o.total_quantity AS total_quantity, "
                        + "o.total_price AS total_price, "
                        + "o.order_status AS order_status, "
                        + "a.address AS address "
                        + "FROM tbl_orders o "
                        + "JOIN tbl_accounts a ON a.account_id = o.buyer_id "
                        + "JOIN tbl_products p ON p.product_id = o.product_id "
                        + "WHERE o.order_id = " + orderId);
                if (rs.next()) {
                    order_status.setText(rs.getString("order_status"));
                    status_name.setText(rs.getString("product_name"));
                    status_quantity.setText("Quantity: " + rs.getInt("total_quantity"));
                    int totals = rs.getInt("total_price");

                    NumberFormat numberFormat = NumberFormat.getNumberInstance();
                    status_total.setText(String.valueOf("₱  " + numberFormat.format(totals)));
                    status_address.setText("" + rs.getString("address"));
                    int height = 160;
                    int width = 110;
                    String getImageFromDatabase = rs.getString("product_image");
                    GetImage.displayImage(jLabel15, getImageFromDatabase, height, width);
                    tabs.setSelectedIndex(9);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_product_table_add_buttonActionPerformed

    private void product_table_archive_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_archive_buttonActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();

            String sql = "UPDATE tbl_orders SET order_status = 'Deleted' WHERE order_id = ?";

            try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
                pst.setInt(1, orderId);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Order deleted Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to deleted the Order to archive!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_product_table_archive_buttonActionPerformed

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        panelMouseClicked(p2, name2, price2, image2);
    }//GEN-LAST:event_p2MouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        panelMouseClicked(p3, name3, price3, image3);
    }//GEN-LAST:event_p3MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        panelMouseClicked(p4, name4, price4, image4);
    }//GEN-LAST:event_p4MouseClicked

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
        panelMouseClicked(p5, name5, price5, image5);
    }//GEN-LAST:event_p5MouseClicked

    private void p6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseClicked
        panelMouseClicked(p6, name6, price6, image6);
    }//GEN-LAST:event_p6MouseClicked

    private void p7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MouseClicked
        panelMouseClicked(p7, name7, price7, image7);
    }//GEN-LAST:event_p7MouseClicked

    private void p8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p8MouseClicked
        panelMouseClicked(p8, name8, price8, image8);
    }//GEN-LAST:event_p8MouseClicked

    private void p9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p9MouseClicked
        panelMouseClicked(p9, name9, price9, image9);
    }//GEN-LAST:event_p9MouseClicked

    private void p10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseClicked
        panelMouseClicked(p10, name10, price10, image10);
    }//GEN-LAST:event_p10MouseClicked

    private void p11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p11MouseClicked
        panelMouseClicked(p11, name11, price11, image11);

    }//GEN-LAST:event_p11MouseClicked

    private void p12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p12MouseClicked
        panelMouseClicked(p12, name12, price12, image12);
    }//GEN-LAST:event_p12MouseClicked

    private void p13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p13MouseClicked
        panelMouseClicked(p13, name13, price13, image13);
    }//GEN-LAST:event_p13MouseClicked

    private void p14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p14MouseClicked
        panelMouseClicked(p14, name14, price14, image14);
    }//GEN-LAST:event_p14MouseClicked

    private void p15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p15MouseClicked
        panelMouseClicked(p15, name15, price15, image15);
    }//GEN-LAST:event_p15MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked

    }//GEN-LAST:event_product_tableMouseClicked

    private void product_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_search_barMouseClicked
        product_search_bar.setFocusable(true);
        product_search_bar.requestFocusInWindow();
    }//GEN-LAST:event_product_search_barMouseClicked

    private void product_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_product_search_barKeyReleased
        search.searchResult(product_table, product_search_bar);
    }//GEN-LAST:event_product_search_barKeyReleased

    private void product_table_add_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_add_button1ActionPerformed
        int rowIndex = product_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
            return;
        }
        TableModel model = product_table.getModel();

        try {
            databaseConnector dbc = new databaseConnector();

            ResultSet rs = dbc.getData("SELECT "
                    + "p.product_description AS product_description, "
                    + "p.product_stock AS product_stock, "
                    + "p.product_image AS product_image, "
                    + "p.total_sold AS total_sold, "
                    + "p.product_name AS product_name, "
                    + "p.product_category AS product_category, "
                    + "COUNT(p.product_id) AS total_products, "
                    + "w.total_favorites AS total_favorites, "
                    + "SUM(pr.total_star) AS total_star, "
                    + "COUNT(pr.product_id) AS total_rating, "
                    + "SUM(sr.total_star) AS seller_star, "
                    + "COUNT(sr.seller_id) AS seller_rating, "
                    + "a.profile_picture AS seller_profile, "
                    + "a.shop_name AS shop_name, "
                    + "a.date_joined AS date_joined "
                    + "FROM tbl_products p "
                    + "LEFT JOIN tbl_wishlist w ON w.product_id = p.product_id "
                    + "LEFT JOIN tbl_rating4products pr ON pr.product_id = p.product_id "
                    + "LEFT JOIN tbl_rating4seller sr ON sr.seller_id = p.seller_id "
                    + "LEFT JOIN tbl_accounts a ON a.account_id = p.seller_id "
                    + "WHERE p.product_status = 'Available' AND p.product_id = " + model.getValueAt(rowIndex, 1)
                    + " GROUP BY p.product_id, w.total_favorites, a.profile_picture");
            if (rs.next()) {
                shop = rs.getString("product_category");
                String pname = rs.getString("product_name");

                product_name.setText(pname);
                String coloredText = "<html><span style='color: rgb(153, 153, 153);'>" + shop + "</span>  > <span style='color: rgb(38, 38, 38);'>  " + pname + "</span></html>";
                product_category.setText(coloredText);
                product_shop_name.setText(rs.getString("shop_name"));
                seller_joined.setText(rs.getString("date_joined"));
                int favorites = rs.getInt("total_favorites");
                if (favorites < 1) {
                    product_favorites.setText("Favorites (0)");
                } else {
                    product_favorites.setText("Favorites (" + favorites + ")");
                }
                product_sold.setText(rs.getString("total_sold"));
                int total_products = rs.getInt("total_products");
                seller_products.setText(String.format("%d", total_products));

                int height = 80;
                int width = 80;
                String getImageFromDatabase = rs.getString("seller_profile");
                GetImage.displayImage(seller_profile, getImageFromDatabase, height, width);

                int sheight = 360;
                int swidth = 260;
                String sgetImageFromDatabase = rs.getString("product_image");
                GetImage.displayImage(product_photo, sgetImageFromDatabase, sheight, swidth);

                int sum_star = rs.getInt("total_star");
                int seller_sum_star = rs.getInt("seller_star");
                int seller_count = rs.getInt("total_rating");
                int seller_seller_count = rs.getInt("seller_rating");
                float seller_rating = seller_seller_count > 0 ? (float) seller_sum_star / seller_seller_count : 0;
                float rating = seller_count > 0 ? (float) sum_star / seller_count : 0;
                if (sum_star < 1) {
                    product_rating.setText("0 (0)");
                } else {
                    product_rating.setText(String.format("%.1f (%d)", rating, seller_count));
                }
                if (seller_sum_star < 1) {
                    seller__profile_rating.setText("0 (0)");
                } else {
                    seller__profile_rating.setText(String.format("%.1f (%d)", seller_rating, seller_seller_count));
                }

                product_description.setText(rs.getString("product_description"));
                quan = rs.getInt("product_stock");
                tabs.setSelectedIndex(2);

            } else {
                JOptionPane.showMessageDialog(null, "Product details not found!");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
        }
    }//GEN-LAST:event_product_table_add_button1ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        jToggleButton7.setBackground(new Color(241, 241, 241));
        jToggleButton9.setSelected(true);
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed

    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void wishlist_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishlist_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_wishlist_tableMouseClicked

    private void wishlist_search_barMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishlist_search_barMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_wishlist_search_barMouseClicked

    private void wishlist_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wishlist_search_barKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_wishlist_search_barKeyReleased

    private void product_table_add_button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_table_add_button2ActionPerformed
        int rowIndex = wishlist_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please Select an Item!");
            return;
        }
        TableModel model = wishlist_table.getModel();

        try {
            databaseConnector dbc = new databaseConnector();

            ResultSet rs = dbc.getData("SELECT "
                    + "p.product_description AS product_description, "
                    + "p.product_stock AS product_stock, "
                    + "p.product_image AS product_image, "
                    + "p.total_sold AS total_sold, "
                    + "p.product_name AS product_name, "
                    + "p.product_category AS product_category, "
                    + "COUNT(p.product_id) AS total_products, "
                    + "w.total_favorites AS total_favorites, "
                    + "SUM(pr.total_star) AS total_star, "
                    + "COUNT(pr.product_id) AS total_rating, "
                    + "SUM(sr.total_star) AS seller_star, "
                    + "COUNT(sr.seller_id) AS seller_rating, "
                    + "a.profile_picture AS seller_profile, "
                    + "a.shop_name AS shop_name, "
                    + "a.date_joined AS date_joined "
                    + "FROM tbl_wishlist w "
                    + "LEFT JOIN tbl_products p ON p.product_id = w.product_id "
                    + "LEFT JOIN tbl_rating4products pr ON pr.product_id = w.product_id "
                    + "LEFT JOIN tbl_rating4seller sr ON sr.seller_id = w.seller_id "
                    + "LEFT JOIN tbl_accounts a ON a.account_id = w.seller_id "
                    + "WHERE p.product_status = 'Available' AND w.wishlist_id = " + model.getValueAt(rowIndex, 0)
                    + " GROUP BY p.product_id, w.total_favorites, a.profile_picture");
            if (rs.next()) {
                shop = rs.getString("product_category");
                String pname = rs.getString("product_name");

                product_name.setText(pname);
                String coloredText = "<html><span style='color: rgb(153, 153, 153);'>" + shop + "</span>  > <span style='color: rgb(38, 38, 38);'>  " + pname + "</span></html>";
                product_category.setText(coloredText);
                product_shop_name.setText(rs.getString("shop_name"));
                seller_joined.setText(rs.getString("date_joined"));
                int favorites = rs.getInt("total_favorites");
                if (favorites < 1) {
                    product_favorites.setText("Favorites (0)");
                } else {
                    product_favorites.setText("Favorites (" + favorites + ")");
                }
                product_sold.setText(rs.getString("total_sold"));
                int total_products = rs.getInt("total_products");
                seller_products.setText(String.format("%d", total_products));

                int height = 80;
                int width = 80;
                String getImageFromDatabase = rs.getString("seller_profile");
                GetImage.displayImage(seller_profile, getImageFromDatabase, height, width);

                int sheight = 360;
                int swidth = 260;
                String sgetImageFromDatabase = rs.getString("product_image");
                GetImage.displayImage(product_photo, sgetImageFromDatabase, sheight, swidth);

                int sum_star = rs.getInt("total_star");
                int seller_sum_star = rs.getInt("seller_star");
                int seller_count = rs.getInt("total_rating");
                int seller_seller_count = rs.getInt("seller_rating");
                float seller_rating = seller_seller_count > 0 ? (float) seller_sum_star / seller_seller_count : 0;
                float rating = seller_count > 0 ? (float) sum_star / seller_count : 0;
                if (sum_star < 1) {
                    product_rating.setText("0 (0)");
                } else {
                    product_rating.setText(String.format("%.1f (%d)", rating, seller_count));
                }
                if (seller_sum_star < 1) {
                    seller__profile_rating.setText("0 (0)");
                } else {
                    seller__profile_rating.setText(String.format("%.1f (%d)", seller_rating, seller_seller_count));
                }

                product_description.setText(rs.getString("product_description"));
                quan = rs.getInt("product_stock");
                tabs.setSelectedIndex(2);

            } else {
                JOptionPane.showMessageDialog(null, "Product details not found!");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
        }
    }//GEN-LAST:event_product_table_add_button2ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        payment_method = "Cash on delivery";
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void select_image1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_image1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_image1ActionPerformed

    private void edit_profile_save_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_profile_save_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_profile_save_button1ActionPerformed

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel73MouseClicked

    private void jLabel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel74MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel74MouseClicked

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new buyerDashboard().setVisible(true);
                }
            });

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(frontPage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton add_to_cart;
    private javax.swing.JButton add_to_wishlist;
    private javax.swing.JButton add_to_wishlist1;
    private javax.swing.JTextField address;
    private javax.swing.JTextField address1;
    private javax.swing.JButton buy_now;
    private javax.swing.JLabel buyer_photo_profile;
    private javax.swing.JLabel buyer_photo_profile1;
    private javax.swing.JPanel cartTableContainer;
    private javax.swing.JPanel cartTableContainer1;
    private javax.swing.JPanel cartViewContainer;
    private javax.swing.JLabel cart_is_empty;
    private javax.swing.JTable cart_table;
    private javax.swing.JLabel category;
    private javax.swing.JButton chat_now;
    private javax.swing.JButton checkout;
    private javax.swing.JButton checkout_button;
    private javax.swing.JLabel checkout_photo;
    private javax.swing.JButton deleteCart;
    private javax.swing.JTextField displayQuant;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel edit1;
    private javax.swing.JTextField edit_phone_number;
    private javax.swing.JTextField edit_phone_number1;
    private javax.swing.JButton edit_profile_save_button;
    private javax.swing.JButton edit_profile_save_button1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email1;
    private javax.swing.JLabel error;
    private javax.swing.JPanel filterContainer;
    private javax.swing.JPanel filterContainer1;
    private javax.swing.JPanel filterContainer2;
    private javax.swing.JComboBox<String> filter_product_table;
    private javax.swing.JComboBox<String> filter_product_table1;
    private javax.swing.JComboBox<String> filter_product_table2;
    private javax.swing.JTextField fname;
    private javax.swing.JTextField fname1;
    private javax.swing.JTextField fname2;
    private javax.swing.JTextField fname3;
    private javax.swing.JTextField fname4;
    private javax.swing.JTextField fname5;
    private javax.swing.JTextField fname6;
    private javax.swing.JTextField fname7;
    private javax.swing.JLabel home;
    private javax.swing.JLabel home_is_empty;
    private javax.swing.JLabel image1;
    private javax.swing.JLabel image10;
    private javax.swing.JLabel image11;
    private javax.swing.JLabel image12;
    private javax.swing.JLabel image13;
    private javax.swing.JLabel image14;
    private javax.swing.JLabel image15;
    private javax.swing.JLabel image2;
    private javax.swing.JLabel image3;
    private javax.swing.JLabel image4;
    private javax.swing.JLabel image5;
    private javax.swing.JLabel image6;
    private javax.swing.JLabel image7;
    private javax.swing.JLabel image8;
    private javax.swing.JLabel image9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField lname1;
    private javax.swing.JLabel logout1;
    private javax.swing.JLabel manage1;
    private javax.swing.JLabel manage10;
    private javax.swing.JLabel manage11;
    private javax.swing.JLabel manage12;
    private javax.swing.JLabel manage13;
    private javax.swing.JLabel manage14;
    private javax.swing.JLabel manage15;
    private javax.swing.JLabel manage16;
    private javax.swing.JLabel manage17;
    private javax.swing.JLabel manage18;
    private javax.swing.JLabel manage19;
    private javax.swing.JLabel manage2;
    private javax.swing.JLabel manage20;
    private javax.swing.JLabel manage21;
    private javax.swing.JLabel manage22;
    private javax.swing.JLabel manage3;
    private javax.swing.JLabel manage4;
    private javax.swing.JLabel manage5;
    private javax.swing.JLabel manage6;
    private javax.swing.JLabel manage7;
    private javax.swing.JLabel manage8;
    private javax.swing.JLabel manage9;
    private javax.swing.JLabel myCart;
    private javax.swing.JPanel myprofile;
    private javax.swing.JLabel myprofile1;
    private javax.swing.JLabel myprofile2;
    private javax.swing.JLabel myprofile5;
    private javax.swing.JLabel myprofile6;
    private javax.swing.JLabel myprofile7;
    private javax.swing.JLabel myprofile8;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel name10;
    private javax.swing.JLabel name11;
    private javax.swing.JLabel name12;
    private javax.swing.JLabel name13;
    private javax.swing.JLabel name14;
    private javax.swing.JLabel name15;
    private javax.swing.JLabel name2;
    private javax.swing.JLabel name3;
    private javax.swing.JLabel name4;
    private javax.swing.JLabel name5;
    private javax.swing.JLabel name6;
    private javax.swing.JLabel name7;
    private javax.swing.JLabel name8;
    private javax.swing.JLabel name9;
    private javax.swing.JEditorPane notes;
    private javax.swing.JEditorPane notess;
    private javax.swing.JLabel order_status;
    private javax.swing.JTextField orders_search_bar;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p10;
    private javax.swing.JPanel p11;
    private javax.swing.JPanel p12;
    private javax.swing.JPanel p13;
    private javax.swing.JPanel p14;
    private javax.swing.JPanel p15;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p6;
    private javax.swing.JPanel p7;
    private javax.swing.JPanel p8;
    private javax.swing.JPanel p9;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField password1;
    private javax.swing.JToggleButton payment;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel price1;
    private javax.swing.JLabel price10;
    private javax.swing.JLabel price11;
    private javax.swing.JLabel price12;
    private javax.swing.JLabel price13;
    private javax.swing.JLabel price14;
    private javax.swing.JLabel price15;
    private javax.swing.JLabel price2;
    private javax.swing.JLabel price3;
    private javax.swing.JLabel price4;
    private javax.swing.JLabel price5;
    private javax.swing.JLabel price6;
    private javax.swing.JLabel price7;
    private javax.swing.JLabel price8;
    private javax.swing.JLabel price9;
    private javax.swing.JPanel productInfo;
    private javax.swing.JLabel product_category;
    private javax.swing.JLabel product_category1;
    private javax.swing.JEditorPane product_description;
    private javax.swing.JLabel product_favorites;
    private javax.swing.JLabel product_is_empty;
    private javax.swing.JLabel product_name;
    private javax.swing.JLabel product_name2;
    private javax.swing.JLabel product_photo;
    private javax.swing.JLabel product_price;
    private javax.swing.JLabel product_price1;
    private javax.swing.JLabel product_price2;
    private javax.swing.JLabel product_price3;
    private javax.swing.JLabel product_rating;
    private javax.swing.JTextField product_search_bar;
    private javax.swing.JLabel product_shop_name;
    private javax.swing.JLabel product_shop_name1;
    private javax.swing.JLabel product_shop_name2;
    private javax.swing.JLabel product_sold;
    private javax.swing.JTable product_table;
    private javax.swing.JButton product_table_add_button;
    private javax.swing.JButton product_table_add_button1;
    private javax.swing.JButton product_table_add_button2;
    private javax.swing.JButton product_table_archive_button;
    private javax.swing.JPanel productsContainer;
    private javax.swing.JPanel productsContainer1;
    private javax.swing.JLabel profile1;
    private javax.swing.JPanel profile2;
    private javax.swing.JLabel purchase_is_empty;
    private javax.swing.JTextField purchase_search_bar;
    private javax.swing.JTable purchase_table;
    private javax.swing.JButton quantity_decrease;
    private javax.swing.JButton quantity_increase;
    private javax.swing.JPanel s1;
    private javax.swing.JPanel s2;
    private javax.swing.JPanel s3;
    private javax.swing.JPanel s4;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel sc2;
    private javax.swing.JButton select_image;
    private javax.swing.JButton select_image1;
    private javax.swing.JLabel seller__profile_rating;
    private javax.swing.JLabel seller_joined;
    private javax.swing.JLabel seller_products;
    private javax.swing.JLabel seller_profile;
    private javax.swing.JLabel seller_rating2;
    private javax.swing.JLabel seller_rating5;
    private javax.swing.JLabel seller_rating6;
    private javax.swing.JLabel status_address;
    private javax.swing.JLabel status_name;
    private javax.swing.JLabel status_quantity;
    private javax.swing.JLabel status_total;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel tamount;
    private javax.swing.JLabel total_price;
    private javax.swing.JLabel total_quantity;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JLabel unit_price;
    private javax.swing.JLabel username;
    private javax.swing.JButton view_shop;
    private javax.swing.JLabel wishlist_is_empty;
    private javax.swing.JTextField wishlist_search_bar;
    private javax.swing.JTable wishlist_table;
    private javax.swing.JPanel yawa;
    // End of variables declaration//GEN-END:variables
}
