package Buyer;

import Seller.sellerDashboard;
import accounts.Login;
import accounts.UserManager;
import accounts.frontPage;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.databaseConnector;
import config.flatlaftTable;
import java.awt.Color;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class buyerDashboard extends javax.swing.JFrame {

    int buyer_id = UserManager.getLoggedInUserId();

    public buyerDashboard() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        displayUserProducts();
        displayCart();
        displayPurchase();

        flatlaftTable.design(cartTableContainer, cart_table, jScrollPane1); // display cart table
        searchBar(orders_search_bar);

        //Panels
        UXmethods.RoundBorders.setArcStyle(container, 30);

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
        UXmethods.RoundBorders.setArcStyle(container, 10);
        UXmethods.RoundBorders.setArcStyle(container, 10);
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
                + "margin:5,20,5,20;"
                + "background:#FFFFFF");
    }

    public void displayCart() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT `cart_id` AS `Cart ID`, `product_id` AS `Product ID`, `product_quantity` AS `Quantity` FROM tbl_cart WHERE buyer_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, buyer_id);

            ResultSet rs = pst.executeQuery();

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

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void displayPurchase() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT `transaction_id` as `Sales ID`, `buyer_id` as `Buyer ID`, `seller_id` as `Seller ID`, `product_id` as `Product ID`, `order_status` as `Order Status`, `date_purchase` as `Date Purchase`  FROM tbl_sales WHERE buyer_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, buyer_id);
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

    private void displayUserProducts() {
        try {

            databaseConnector dbc = new databaseConnector();
            Connection connection = dbc.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT product_image, product_name, product_price FROM tbl_products WHERE product_status = ? AND product_stock > 0");
            statement.setString(1, "Available");

            ResultSet rs = statement.executeQuery();
            JLabel[] nameLabels = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12};
            JLabel[] priceLabels = {price1, price2, price3, price4, price5, price6, price7, price8, price9, price10, price11, price12};
            JLabel[] imageLabels = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};

            int productCounter = 0;
            while (rs.next() && productCounter < imageLabels.length) {

                int height = 210;
                int width = 120;
                String getImageFromDatabase = rs.getString("product_image");
                String productName = rs.getString("product_name");
                int productPrice = rs.getInt("product_price");
                GetImage.displayImage(imageLabels[productCounter], getImageFromDatabase, height, width);
                nameLabels[productCounter].setText(productName);
                priceLabels[productCounter].setText("₱   " + productPrice);
                productCounter++;
            }

            for (int i = productCounter; i < imageLabels.length; i++) {
                removeLabel(imageLabels[i]);
                removeLabel(nameLabels[i]);
                removeLabel(priceLabels[i]);
            }

        } catch (SQLException e) {
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

    private void panelMouseClicked(JPanel panel, JLabel nameLabel, JLabel priceLabel, JLabel imageLabel, int stocks) {
        if (panel != null && imageLabel.getIcon() != null && nameLabel.getText() != null && !nameLabel.getText().isEmpty() && priceLabel.getText() != null && !priceLabel.getText().isEmpty()) {

            ImageIcon icon = (ImageIcon) imageLabel.getIcon();
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(330, 330, Image.SCALE_SMOOTH);
            ImageIcon selectedImage = new ImageIcon(scaledImage);
            product_photo.setIcon(selectedImage);
            product_name.setText(nameLabel.getText());
            product_price.setText(priceLabel.getText());

            try {
                databaseConnector dbc = new databaseConnector();
                Connection connection = dbc.getConnection();

                String getProductIdQuery = "SELECT product_id, seller_id FROM tbl_products WHERE product_name = ?";
                PreparedStatement getProductIdStatement = connection.prepareStatement(getProductIdQuery);
                getProductIdStatement.setString(1, nameLabel.getText());
                ResultSet rs = getProductIdStatement.executeQuery();
                if (rs.next()) {
                    product_id = rs.getInt("product_id");
                    seller_id = rs.getInt("seller_id");
                }
                rs.close(); // Close the first ResultSet
                getProductIdStatement.close(); // Close the first PreparedStatement

                String query = "SELECT "
                        + "p.product_description AS product_description, "
                        + "p.product_stock AS product_stock, "
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

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, product_id);
                rs = statement.executeQuery();
                if (rs.next()) {
                    String shop = rs.getString("product_category");
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
                    stocks = rs.getInt("product_stock");
                    quan = stocks;
                }
                rs.close();
                statement.close();
                connection.close();
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
        p2 = new javax.swing.JPanel();
        name2 = new javax.swing.JLabel();
        price2 = new javax.swing.JLabel();
        image2 = new javax.swing.JLabel();
        p3 = new javax.swing.JPanel();
        price3 = new javax.swing.JLabel();
        name3 = new javax.swing.JLabel();
        image3 = new javax.swing.JLabel();
        p4 = new javax.swing.JPanel();
        image4 = new javax.swing.JLabel();
        name4 = new javax.swing.JLabel();
        price4 = new javax.swing.JLabel();
        p5 = new javax.swing.JPanel();
        price5 = new javax.swing.JLabel();
        image5 = new javax.swing.JLabel();
        name5 = new javax.swing.JLabel();
        p6 = new javax.swing.JPanel();
        image6 = new javax.swing.JLabel();
        price6 = new javax.swing.JLabel();
        name6 = new javax.swing.JLabel();
        p7 = new javax.swing.JPanel();
        name7 = new javax.swing.JLabel();
        image7 = new javax.swing.JLabel();
        price7 = new javax.swing.JLabel();
        p8 = new javax.swing.JPanel();
        image8 = new javax.swing.JLabel();
        name8 = new javax.swing.JLabel();
        price8 = new javax.swing.JLabel();
        p9 = new javax.swing.JPanel();
        image9 = new javax.swing.JLabel();
        name9 = new javax.swing.JLabel();
        price9 = new javax.swing.JLabel();
        p10 = new javax.swing.JPanel();
        price10 = new javax.swing.JLabel();
        image10 = new javax.swing.JLabel();
        name10 = new javax.swing.JLabel();
        p11 = new javax.swing.JPanel();
        name11 = new javax.swing.JLabel();
        image11 = new javax.swing.JLabel();
        price11 = new javax.swing.JLabel();
        p12 = new javax.swing.JPanel();
        image12 = new javax.swing.JLabel();
        price12 = new javax.swing.JLabel();
        name12 = new javax.swing.JLabel();
        next = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
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
        container = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        photo = new javax.swing.JLabel();
        yawa = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        notess = new javax.swing.JEditorPane();
        tamount = new javax.swing.JLabel();
        sc2 = new javax.swing.JLabel();
        product_price3 = new javax.swing.JLabel();
        checkout = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        product_shop_name2 = new javax.swing.JLabel();
        cartTableContainer = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        cart_is_empty = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cart_table = new javax.swing.JTable();
        orders_search_bar = new javax.swing.JTextField();
        deleteCart = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        myprofile = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        edit = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        select = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        manage1 = new javax.swing.JLabel();
        manage2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        manage3 = new javax.swing.JLabel();
        manage4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        manage6 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        myprofile1 = new javax.swing.JLabel();
        manage5 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        manage13 = new javax.swing.JLabel();
        image_view = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        password1 = new javax.swing.JTextField();
        manage14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        logout = new javax.swing.JLabel();
        myprofile2 = new javax.swing.JLabel();
        myprofile3 = new javax.swing.JLabel();
        myprofile4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        purchase_table = new javax.swing.JTable();
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
        jPanel10 = new javax.swing.JPanel();
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

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 20));

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

        name1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name1.setForeground(new java.awt.Color(51, 51, 51));
        p1.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        price1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price1.setForeground(new java.awt.Color(51, 51, 51));
        p1.add(price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));
        p1.add(image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 120));

        jPanel1.add(p1);
        p1.setBounds(190, 50, 210, 180);

        p2.setBackground(new java.awt.Color(255, 255, 255));
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
        });
        p2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name2.setForeground(new java.awt.Color(51, 51, 51));
        p2.add(name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        price2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price2.setForeground(new java.awt.Color(51, 51, 51));
        p2.add(price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));
        p2.add(image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 120));

        jPanel1.add(p2);
        p2.setBounds(410, 50, 210, 180);

        p3.setBackground(new java.awt.Color(255, 255, 255));
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
        });
        p3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        price3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price3.setForeground(new java.awt.Color(51, 51, 51));
        p3.add(price3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

        name3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name3.setForeground(new java.awt.Color(51, 51, 51));
        p3.add(name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));
        p3.add(image3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 120));

        jPanel1.add(p3);
        p3.setBounds(630, 50, 210, 180);

        p4.setBackground(new java.awt.Color(255, 255, 255));
        p4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p4MouseClicked(evt);
            }
        });
        p4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p4.add(image4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        name4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name4.setForeground(new java.awt.Color(51, 51, 51));
        p4.add(name4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        price4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price4.setForeground(new java.awt.Color(51, 51, 51));
        p4.add(price4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

        jPanel1.add(p4);
        p4.setBounds(850, 50, 210, 180);

        p5.setBackground(new java.awt.Color(255, 255, 255));
        p5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p5MouseClicked(evt);
            }
        });
        p5.setLayout(null);

        price5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price5.setForeground(new java.awt.Color(51, 51, 51));
        p5.add(price5);
        price5.setBounds(0, 150, 210, 22);
        p5.add(image5);
        image5.setBounds(0, 0, 210, 123);

        name5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name5.setForeground(new java.awt.Color(51, 51, 51));
        p5.add(name5);
        name5.setBounds(0, 120, 210, 25);

        jPanel1.add(p5);
        p5.setBounds(190, 240, 210, 180);

        p6.setBackground(new java.awt.Color(255, 255, 255));
        p6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p6MouseClicked(evt);
            }
        });
        p6.setLayout(null);
        p6.add(image6);
        image6.setBounds(0, 0, 210, 123);

        price6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price6.setForeground(new java.awt.Color(51, 51, 51));
        p6.add(price6);
        price6.setBounds(0, 150, 210, 22);

        name6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name6.setForeground(new java.awt.Color(51, 51, 51));
        p6.add(name6);
        name6.setBounds(0, 120, 210, 25);

        jPanel1.add(p6);
        p6.setBounds(410, 240, 210, 180);

        p7.setBackground(new java.awt.Color(255, 255, 255));
        p7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p7MouseClicked(evt);
            }
        });
        p7.setLayout(null);

        name7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name7.setForeground(new java.awt.Color(51, 51, 51));
        p7.add(name7);
        name7.setBounds(0, 120, 210, 25);
        p7.add(image7);
        image7.setBounds(0, 0, 210, 123);

        price7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price7.setForeground(new java.awt.Color(51, 51, 51));
        p7.add(price7);
        price7.setBounds(0, 150, 210, 22);

        jPanel1.add(p7);
        p7.setBounds(629, 238, 210, 180);

        p8.setBackground(new java.awt.Color(255, 255, 255));
        p8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p8MouseClicked(evt);
            }
        });
        p8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p8.add(image8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        name8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name8.setForeground(new java.awt.Color(51, 51, 51));
        p8.add(name8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        price8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price8.setForeground(new java.awt.Color(51, 51, 51));
        p8.add(price8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

        jPanel1.add(p8);
        p8.setBounds(850, 240, 210, 180);

        p9.setBackground(new java.awt.Color(255, 255, 255));
        p9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p9MouseClicked(evt);
            }
        });
        p9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p9.add(image9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        name9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name9.setForeground(new java.awt.Color(51, 51, 51));
        p9.add(name9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        price9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price9.setForeground(new java.awt.Color(51, 51, 51));
        p9.add(price9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

        jPanel1.add(p9);
        p9.setBounds(190, 430, 210, 180);

        p10.setBackground(new java.awt.Color(255, 255, 255));
        p10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p10MouseClicked(evt);
            }
        });
        p10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        price10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price10.setForeground(new java.awt.Color(51, 51, 51));
        p10.add(price10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));
        p10.add(image10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        name10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name10.setForeground(new java.awt.Color(51, 51, 51));
        p10.add(name10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        jPanel1.add(p10);
        p10.setBounds(410, 430, 210, 180);

        p11.setBackground(new java.awt.Color(255, 255, 255));
        p11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p11MouseClicked(evt);
            }
        });
        p11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name11.setForeground(new java.awt.Color(51, 51, 51));
        p11.add(name11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));
        p11.add(image11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        price11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price11.setForeground(new java.awt.Color(51, 51, 51));
        p11.add(price11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

        jPanel1.add(p11);
        p11.setBounds(630, 430, 210, 180);

        p12.setBackground(new java.awt.Color(255, 255, 255));
        p12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p12MouseClicked(evt);
            }
        });
        p12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p12.add(image12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        price12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price12.setForeground(new java.awt.Color(51, 51, 51));
        p12.add(price12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

        name12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name12.setForeground(new java.awt.Color(51, 51, 51));
        p12.add(name12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        jPanel1.add(p12);
        p12.setBounds(850, 430, 210, 180);

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/next_right.png"))); // NOI18N
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        jPanel1.add(next);
        next.setBounds(1106, 50, 100, 560);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Show all products");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(0, 620, 1280, 16);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator1.setAlignmentX(0.2F);
        jSeparator1.setAlignmentY(0.2F);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 40, 1280, 10);

        tabs.addTab("tab1", jPanel1);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        jPanel3.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 580));

        tabs.addTab("tab2", jPanel3);

        productInfo.setBackground(new java.awt.Color(255, 255, 255));
        productInfo.setLayout(null);

        product_name.setFont(new java.awt.Font("Roboto", 0, 50)); // NOI18N
        product_name.setForeground(new java.awt.Color(51, 51, 51));
        product_name.setText("Macbook Air");
        productInfo.add(product_name);
        product_name.setBounds(660, 120, 570, 40);

        product_price.setFont(new java.awt.Font("Arial", 1, 50)); // NOI18N
        product_price.setText("₱ 85,999");
        productInfo.add(product_price);
        product_price.setBounds(660, 160, 560, 70);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        product_description.setEditable(false);
        product_description.setBackground(new java.awt.Color(255, 255, 255));
        product_description.setBorder(null);
        product_description.setEditorKit(null);
        product_description.setForeground(new java.awt.Color(51, 51, 51));
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
        s1.add(product_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 250, 250));

        productInfo.add(s1);
        s1.setBounds(140, 120, 470, 370);

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(231, 231, 231));
        jSeparator4.setAlignmentX(0.2F);
        jSeparator4.setAlignmentY(0.2F);
        productInfo.add(jSeparator4);
        jSeparator4.setBounds(0, 40, 1280, 20);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product_sold.png"))); // NOI18N
        productInfo.add(jLabel23);
        jLabel23.setBounds(960, 230, 40, 50);

        product_sold.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        product_sold.setForeground(new java.awt.Color(153, 153, 153));
        product_sold.setText("Sold (12)");
        productInfo.add(product_sold);
        product_sold.setBounds(1010, 230, 110, 50);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product_star.png"))); // NOI18N
        productInfo.add(jLabel24);
        jLabel24.setBounds(650, 230, 40, 50);

        product_rating.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        s2.setBounds(140, 500, 470, 120);

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
        jLabel26.setBounds(790, 230, 40, 50);

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

        product_favorites.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        product_favorites.setForeground(new java.awt.Color(153, 153, 153));
        product_favorites.setText("Favorite (12)");
        productInfo.add(product_favorites);
        product_favorites.setBounds(840, 230, 110, 50);
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
        cartViewContainer.add(quantity_decrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 40, 40));

        quantity_increase.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        quantity_increase.setText("+");
        quantity_increase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_increaseActionPerformed(evt);
            }
        });
        cartViewContainer.add(quantity_increase, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 40, 40));

        txtNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        cartViewContainer.add(txtNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 70, 40));

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Quanity");
        cartViewContainer.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        container.setBackground(new java.awt.Color(204, 204, 204));
        container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("asssssssssssss");
        name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        container.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 320, 30));
        container.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 160, 160));

        cartViewContainer.add(container, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 320, 220));

        yawa.setBackground(new java.awt.Color(204, 204, 204));
        yawa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notess.setBackground(new java.awt.Color(241, 241, 241));
        jScrollPane5.setViewportView(notess);

        yawa.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, 40));

        tamount.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tamount.setForeground(new java.awt.Color(0, 158, 226));
        tamount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tamount.setText("₱  49000");
        yawa.add(tamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 70, 30));

        sc2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sc2.setForeground(new java.awt.Color(102, 102, 102));
        sc2.setText("Total:");
        yawa.add(sc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, 30));

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
        yawa.add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 280, 50));

        cartViewContainer.add(yawa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 320, 160));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shop_icon_1.png"))); // NOI18N
        cartViewContainer.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 30, 40));

        product_shop_name2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_shop_name2.setForeground(new java.awt.Color(51, 51, 51));
        product_shop_name2.setText("Tindahan");
        cartViewContainer.add(product_shop_name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 130, 20));

        jPanel4.add(cartViewContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, 360, 590));

        cartTableContainer.setBackground(new java.awt.Color(241, 241, 241));
        cartTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cartTableContainer.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 860, 20));

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
        cart_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cart_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cart_table);

        cartTableContainer.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 860, 500));

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
        cartTableContainer.add(deleteCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 50, 40));

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
        cartTableContainer.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 120, 40));

        jPanel4.add(cartTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 860, 590));
        jPanel4.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 20));

        tabs.addTab("tab4", jPanel4);

        myprofile.setBackground(new java.awt.Color(245, 245, 245));
        myprofile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        myprofile.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 40, 40));

        edit.setText("Edit Profile");
        myprofile.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel9.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 30, 380));

        select.setText("Select Image");
        jPanel9.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 360, 120, 30));
        jPanel9.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 860, 20));

        manage1.setForeground(new java.awt.Color(102, 102, 102));
        manage1.setText("Manage your account ");
        jPanel9.add(manage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        manage2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage2.setForeground(new java.awt.Color(102, 102, 102));
        manage2.setText("Username");
        jPanel9.add(manage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));
        jPanel9.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 270, 40));

        manage3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage3.setForeground(new java.awt.Color(102, 102, 102));
        manage3.setText("First name");
        jPanel9.add(manage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        manage4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage4.setForeground(new java.awt.Color(102, 102, 102));
        manage4.setText("Address");
        jPanel9.add(manage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 158, 226));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 510, -1, 30));

        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("chielbrc");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, 20));
        jPanel9.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 270, 40));

        manage6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage6.setForeground(new java.awt.Color(102, 102, 102));
        manage6.setText("Last name");
        jPanel9.add(manage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));
        jPanel9.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 270, 40));

        myprofile1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        myprofile1.setText("My Profile");
        jPanel9.add(myprofile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 70, 30));

        manage5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage5.setForeground(new java.awt.Color(102, 102, 102));
        manage5.setText("Phone Number");
        jPanel9.add(manage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, -1, -1));
        jPanel9.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 270, 40));

        manage13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage13.setForeground(new java.awt.Color(102, 102, 102));
        manage13.setText("Password");
        jPanel9.add(manage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, -1));
        jPanel9.add(image_view, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 226, 120, 120));

        jLabel5.setText("Change");
        jPanel9.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, -1, -1));

        jLabel8.setText("Change");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, -1, -1));
        jPanel9.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 270, 40));

        manage14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage14.setForeground(new java.awt.Color(102, 102, 102));
        manage14.setText("Email");
        jPanel9.add(manage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        jLabel10.setText("Change");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, -1));

        password.setText("*******");
        password.setBorder(null);
        jPanel9.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 270, 40));

        myprofile.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 1000, 600));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("username");
        myprofile.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));
        myprofile.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 150, 20));
        myprofile.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 50, 160));

        logout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logout.setText("Logout");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        myprofile.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 80, 20));

        myprofile2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        myprofile2.setText("My Account");
        myprofile.add(myprofile2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 80, 20));

        myprofile3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        myprofile3.setText("Address");
        myprofile3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myprofile3MouseClicked(evt);
            }
        });
        myprofile.add(myprofile3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 80, 20));

        myprofile4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        myprofile4.setText("Logout");
        myprofile.add(myprofile4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 80, 20));

        tabs.addTab("tab5", myprofile);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        purchase_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(purchase_table);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 1080, 470));

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

        jPanel10.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        s3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        category.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        category.setForeground(new java.awt.Color(0, 0, 0));
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
        s3.add(checkout_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 390, 250, 40));

        product_price1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product_price1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        product_price1.setText("Notes");
        s3.add(product_price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 50, 40));

        product_price2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        product_price2.setForeground(new java.awt.Color(0, 158, 226));
        product_price2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        product_price2.setText("₱ 85,999");
        s3.add(product_price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, -1, 40));

        total_quantity.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        total_quantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_quantity.setText("12");
        s3.add(total_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 50, 40));

        jButton5.setText("Cancel");
        jButton5.setBorderPainted(false);
        s3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, -1, 40));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("Total:");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        s3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, -1, 40));

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

        s3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 590, 40));

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

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_nextMouseClicked

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
        panelMouseClicked(p1, name1, price1, image1, quan);
        System.out.println(product_id);
    }//GEN-LAST:event_p1MouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        panelMouseClicked(p3, name3, price3, image3, quan);
     }//GEN-LAST:event_p3MouseClicked

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        panelMouseClicked(p2, name2, price2, image2, quan);
    }//GEN-LAST:event_p2MouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_backMouseClicked

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
            PreparedStatement checkProductStmt = dbc.getConnection().prepareStatement(checkProductQuery);
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
            checkProductStmt.close();
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

    public void displayAccountName() {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT username, first_name, last_name, email, address, profile_picture FROM tbl_accounts WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, buyer_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                jLabel12.setText("" + rs.getString("username"));
                jLabel15.setText("" + rs.getString("username"));
                fname.setText("" + rs.getString("first_name"));
                lname.setText("" + rs.getString("last_name"));
                email.setText("" + rs.getString("email"));
                address.setText("" + rs.getString("address"));
                int heightSmall = 40;
                int widthSmall = 40;
                int height = 120;
                int width = 120;
                String profilePicture = rs.getString("profile_picture");
                GetImage.displayImage(profile, profilePicture, heightSmall, widthSmall);
                GetImage.displayImage(image_view, profilePicture, height, width);
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }


    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        tabs.setSelectedIndex(4);
        displayAccountName();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        panelMouseClicked(p4, name4, price4, image4, quan);
    }//GEN-LAST:event_p4MouseClicked

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
        panelMouseClicked(p5, name5, price5, image5, stock);
    }//GEN-LAST:event_p5MouseClicked

    private void p6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseClicked
        panelMouseClicked(p6, name6, price6, image6, quan);
    }//GEN-LAST:event_p6MouseClicked

    private void p7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MouseClicked
        panelMouseClicked(p7, name7, price7, image7, quan);
    }//GEN-LAST:event_p7MouseClicked

    private void p8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p8MouseClicked
        panelMouseClicked(p8, name8, price8, image8, quan);
    }//GEN-LAST:event_p8MouseClicked

    private void p9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p9MouseClicked
        panelMouseClicked(p9, name9, price9, image9, quan);
    }//GEN-LAST:event_p9MouseClicked

    private void p10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseClicked
        panelMouseClicked(p10, name10, price10, image10, quan);
    }//GEN-LAST:event_p10MouseClicked

    private void p11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p11MouseClicked
        panelMouseClicked(p11, name11, price11, image11, quan);
    }//GEN-LAST:event_p11MouseClicked

    private void p12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p12MouseClicked
        panelMouseClicked(p12, name12, price12, image12, quan);
    }//GEN-LAST:event_p12MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        Login out = new Login();
        out.setVisible(true);
        this.dispose();
        UserManager.logout();
    }//GEN-LAST:event_logoutMouseClicked

    private void myprofile3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myprofile3MouseClicked
        tabs.setSelectedIndex(7);
    }//GEN-LAST:event_myprofile3MouseClicked

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
        String productategory = product_category.getText();

        buyPriceStr = product_price.getText().replaceAll("[^0-9]", "");
        buyPrice = Integer.parseInt(buyPriceStr);
        buyQuantStr = displayQuant.getText();
        buyQuant = Integer.parseInt(buyQuantStr);

        totalPrice = buyPrice * buyQuant;

        total_price.setText("₱ " + totalPrice);
        product_price2.setText("₱ " + totalPrice);
        unit_price.setText("₱ " + productPrice);
        total_quantity.setText(String.format("%d", num));
        product_name2.setText(productName);
        category.setText(productategory);

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
        if (tblQuant > 0) {
            tblQuant--;
            txtNumber.setText(String.valueOf(tblQuant));
            updateTotal();
        }
    }//GEN-LAST:event_quantity_decreaseActionPerformed

    private void updateTotal() {
        total = tblQuant * tblPrice;
        tamount.setText(String.valueOf("₱  " + total));
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
                    + "c.product_id AS `product_id`, "
                    + "p.product_image AS `product_image`, "
                    + "p.product_price AS `product_price`, "
                    + "p.product_name AS `product_name`, "
                    + "c.product_quantity AS `product_quantity` "
                    + "FROM tbl_cart c "
                    + "JOIN tbl_products p ON p.product_id = c.product_id "
                    + "JOIN tbl_accounts a ON a.account_id = c.seller_id "
                    + "WHERE c.cart_id = ?";

            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, cart_id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                stock = rs.getInt("product_stock");
                product_id = rs.getInt("product_id");
                tblPrice = rs.getInt("product_price");
                tblQuant = rs.getInt("product_quantity");
                txtNumber.setText(String.format("%d", tblQuant));

                product_shop_name2.setText(rs.getString("shop_name"));
                name.setText(rs.getString("product_name"));
                int height = 240;
                int width = 200;
                String getImageFromDatabase = rs.getString("product_image");
                GetImage.displayImage(photo, getImageFromDatabase, height, width);
            } else {
                JOptionPane.showMessageDialog(null, "No stock found for product_id: " + product_id);
            }

            rs.close();
            pst.close();
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

    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        String productName = product_name.getText();
        String NOTESS = notess.getText();

        String buyPriceStr = product_price.getText().replaceAll("[^0-9]", "");
        int buyPrice = Integer.parseInt(buyPriceStr);
        String buyQuantStr = txtNumber.getText();
        int buyQuant = Integer.parseInt(buyQuantStr);

        try {
            databaseConnector dbc = new databaseConnector();

            String fetchProductQuery = "SELECT product_stock, product_status FROM tbl_products WHERE product_id = ?";
            PreparedStatement fetchProductStmt = dbc.getConnection().prepareStatement(fetchProductQuery);
            fetchProductStmt.setInt(1, product_id);
            ResultSet fetchRs = fetchProductStmt.executeQuery();
            if (!fetchRs.next()) {
                JOptionPane.showMessageDialog(null, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int currentStock = fetchRs.getInt("product_stock");
            String currentStatus = fetchRs.getString("product_status");

            // Check if the purchase already exists for the given cart ID and product name
            String checkPurchaseQuery = "SELECT * FROM tbl_orders WHERE buyer_id = ? AND product_id = ?";
            PreparedStatement checkPurchaseStmt = dbc.getConnection().prepareStatement(checkPurchaseQuery);
            checkPurchaseStmt.setInt(1, buyer_id);
            checkPurchaseStmt.setInt(2, product_id);
            ResultSet checkRs = checkPurchaseStmt.executeQuery();

            if (checkRs.next()) {
                // If purchase exists, update total_quantity and total_price
                int existingQuant = checkRs.getInt("total_quantity");
                int newQuant = existingQuant + buyQuant;
                int existingTotalPrice = checkRs.getInt("total_price");
                int newTotalPrice = existingTotalPrice + total;

                if (currentStock > stock) {
                    JOptionPane.showMessageDialog(null, "Insufficient stock. Available stock: " + stock);
                } else {
                    String updateQuery = "UPDATE tbl_orders SET total_quantity = ?, total_price = ? WHERE buyer_id = ? AND product_id = ?";
                    PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                    updateStmt.setInt(1, newQuant);
                    updateStmt.setInt(2, newTotalPrice);
                    updateStmt.setInt(3, buyer_id);
                    updateStmt.setInt(4, product_id);
                    updateStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Purchase updated successfully!");
                }
            } else {
                String insertQuery = "INSERT INTO tbl_orders (buyer_id, seller_id, product_id, total_quantity, total_price, notes, order_status, date_purchase) VALUES (?, ?, ?, ?, ?, ?, 'Pending', NOW())";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery);
                insertStmt.setInt(1, buyer_id);
                insertStmt.setInt(2, seller_id);
                insertStmt.setInt(3, product_id);
                insertStmt.setInt(4, buyQuant);
                insertStmt.setInt(5, totalPrice);
                insertStmt.setString(6, NOTESS);

                if (buyQuant > stock) {
                    JOptionPane.showMessageDialog(null, "Insufficient stock. Available stock: " + stock);
                } else {
                    insertStmt.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Purchase added successfully!");
            }

            dbc.deleteCart(cart_id);
            System.out.println(cart_id);
            num = 1;
            displayQuant.setText(String.valueOf(num));
            total = 0;
            tamount.setText(String.valueOf(total));
            displayPurchase();
            displayUserProducts();
            tabs.setSelectedIndex(0);
            name.setText("");
            photo.setIcon(null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_checkoutActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void add_to_wishlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_wishlistActionPerformed
        // TODO add your handling code here:
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
        String productName = product_name.getText();
        String NOTES = notes.getText();

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
    }//GEN-LAST:event_orders_search_barKeyReleased

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
    private javax.swing.JLabel back;
    private javax.swing.JButton buy_now;
    private javax.swing.JPanel cartTableContainer;
    private javax.swing.JPanel cartViewContainer;
    private javax.swing.JLabel cart_is_empty;
    private javax.swing.JTable cart_table;
    private javax.swing.JLabel category;
    private javax.swing.JButton chat_now;
    private javax.swing.JButton checkout;
    private javax.swing.JButton checkout_button;
    private javax.swing.JPanel container;
    private javax.swing.JButton deleteCart;
    private javax.swing.JTextField displayQuant;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel edit1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JTextField fname1;
    private javax.swing.JTextField fname2;
    private javax.swing.JTextField fname3;
    private javax.swing.JTextField fname4;
    private javax.swing.JTextField fname5;
    private javax.swing.JTextField fname6;
    private javax.swing.JLabel home;
    private javax.swing.JLabel image1;
    private javax.swing.JLabel image10;
    private javax.swing.JLabel image11;
    private javax.swing.JLabel image12;
    private javax.swing.JLabel image2;
    private javax.swing.JLabel image3;
    private javax.swing.JLabel image4;
    private javax.swing.JLabel image5;
    private javax.swing.JLabel image6;
    private javax.swing.JLabel image7;
    private javax.swing.JLabel image8;
    private javax.swing.JLabel image9;
    private javax.swing.JLabel image_view;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel logout1;
    private javax.swing.JLabel manage1;
    private javax.swing.JLabel manage10;
    private javax.swing.JLabel manage11;
    private javax.swing.JLabel manage12;
    private javax.swing.JLabel manage13;
    private javax.swing.JLabel manage14;
    private javax.swing.JLabel manage2;
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
    private javax.swing.JLabel myprofile3;
    private javax.swing.JLabel myprofile4;
    private javax.swing.JLabel myprofile5;
    private javax.swing.JLabel myprofile6;
    private javax.swing.JLabel myprofile7;
    private javax.swing.JLabel myprofile8;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel name10;
    private javax.swing.JLabel name11;
    private javax.swing.JLabel name12;
    private javax.swing.JLabel name2;
    private javax.swing.JLabel name3;
    private javax.swing.JLabel name4;
    private javax.swing.JLabel name5;
    private javax.swing.JLabel name6;
    private javax.swing.JLabel name7;
    private javax.swing.JLabel name8;
    private javax.swing.JLabel name9;
    private javax.swing.JLabel next;
    private javax.swing.JEditorPane notes;
    private javax.swing.JEditorPane notess;
    private javax.swing.JTextField orders_search_bar;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p10;
    private javax.swing.JPanel p11;
    private javax.swing.JPanel p12;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p6;
    private javax.swing.JPanel p7;
    private javax.swing.JPanel p8;
    private javax.swing.JPanel p9;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField password1;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel price1;
    private javax.swing.JLabel price10;
    private javax.swing.JLabel price11;
    private javax.swing.JLabel price12;
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
    private javax.swing.JLabel product_name;
    private javax.swing.JLabel product_name2;
    private javax.swing.JLabel product_photo;
    private javax.swing.JLabel product_price;
    private javax.swing.JLabel product_price1;
    private javax.swing.JLabel product_price2;
    private javax.swing.JLabel product_price3;
    private javax.swing.JLabel product_rating;
    private javax.swing.JLabel product_shop_name;
    private javax.swing.JLabel product_shop_name1;
    private javax.swing.JLabel product_shop_name2;
    private javax.swing.JLabel product_sold;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profile1;
    private javax.swing.JPanel profile2;
    private javax.swing.JTable purchase_table;
    private javax.swing.JButton quantity_decrease;
    private javax.swing.JButton quantity_increase;
    private javax.swing.JPanel s1;
    private javax.swing.JPanel s2;
    private javax.swing.JPanel s3;
    private javax.swing.JPanel s4;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel sc2;
    private javax.swing.JButton select;
    private javax.swing.JLabel seller__profile_rating;
    private javax.swing.JLabel seller_joined;
    private javax.swing.JLabel seller_products;
    private javax.swing.JLabel seller_profile;
    private javax.swing.JLabel seller_rating2;
    private javax.swing.JLabel seller_rating5;
    private javax.swing.JLabel seller_rating6;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel tamount;
    private javax.swing.JLabel total_price;
    private javax.swing.JLabel total_quantity;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JLabel unit_price;
    private javax.swing.JButton view_shop;
    private javax.swing.JPanel yawa;
    // End of variables declaration//GEN-END:variables
}
