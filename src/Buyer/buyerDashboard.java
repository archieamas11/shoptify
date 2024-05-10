package Buyer;

import accounts.Login;
import accounts.UserManager;
import accounts.frontPage;
import com.formdev.flatlaf.FlatLightLaf;
import config.GetImage;
import config.databaseConnector;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class buyerDashboard extends javax.swing.JFrame {

    public buyerDashboard() {
        initComponents();
        displayUserProducts();
        displayCart();
        displayPurchase();

        //Panels
        UXmethods.RoundBorders.setArcStyle(cartTableContainer, 30);
        UXmethods.RoundBorders.setArcStyle(cartViewContainer, 30);
        UXmethods.RoundBorders.setArcStyle(deleteCart, 10);
        UXmethods.RoundBorders.setArcStyle(container, 30);
        UXmethods.RoundBorders.setArcStyle(checkout, 30);
        UXmethods.RoundBorders.setArcStyle(add, 10);
        UXmethods.RoundBorders.setArcStyle(searchbtn1, 10);

        //Animations
        //customizeJPanel(p1);
    }

    //////////////////////////////////////
    //private String selectedGender = "";
    public static int productID;

    //private void getSelectedGender(String gender) {
    //selectedGender = gender;
    //}
    public void displayCart() {

        try {
            int accountId = UserManager.getLoggedInUserId();

            databaseConnector dbc = new databaseConnector();
            String query = "SELECT cart_id, product_id, product_name, product_price, product_quantity, timestamp FROM add2cart WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, accountId);
            ResultSet rs = pst.executeQuery();
            cart_table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void displayPurchase() {

        try {
            int accountId = UserManager.getLoggedInUserId();

            databaseConnector dbc = new databaseConnector();
            String query = "SELECT transaction_id, account_id, product_id, product_name, product_price, total_quantity, total_price, date_purchase FROM purchase WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, accountId);
            ResultSet rs = pst.executeQuery();
            purchase_table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    private void displayUserProducts() {
        try {

            databaseConnector dbc = new databaseConnector();
            Connection connection = dbc.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT `ImagePath`, `Product Name`, `Price` FROM products WHERE Status = ? AND Stock > 0");
            statement.setString(1, "Available");
            ResultSet rs = statement.executeQuery();

            JLabel[] nameLabels = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12};
            JLabel[] priceLabels = {price1, price2, price3, price4, price5, price6, price7, price8, price9, price10, price11, price12};
            JLabel[] imageLabels = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};

            int productCounter = 0;
            while (rs.next() && productCounter < imageLabels.length) {

                int height = 210;
                int width = 120;
                String getImageFromDatabase = rs.getString("ImagePath");

                String productName = rs.getString("Product Name");
                int productPrice = rs.getInt("Price");
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
    private int quan = 1;

    private void panelMouseClicked(JPanel panel, JLabel nameLabel, JLabel priceLabel, JLabel imageLabel, int productID, int stocks) {
        if (panel != null && imageLabel.getIcon() != null && nameLabel.getText() != null && !nameLabel.getText().isEmpty() && priceLabel.getText() != null && !priceLabel.getText().isEmpty()) {

            ImageIcon icon = (ImageIcon) imageLabel.getIcon();
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(330, 330, Image.SCALE_SMOOTH);
            ImageIcon selectedImage = new ImageIcon(scaledImage);
            bigPhoto.setIcon(selectedImage);

            labelname.setText(nameLabel.getText());
            labelprice.setText(priceLabel.getText());

            try {
                databaseConnector dbc = new databaseConnector();
                String query = "SELECT product_id, Description, Stock FROM products WHERE `Product Name` = ?";
                PreparedStatement statement = dbc.getConnection().prepareStatement(query);
                statement.setString(1, nameLabel.getText());

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    productID = rs.getInt("product_id");
                    String description = rs.getString("Description");
                    des.setText(description);
                    stocks = rs.getInt("Stock");
                    quan = stocks;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            productIDs.setProductID(productID);
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
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        productInfo = new javax.swing.JPanel();
        bigPhoto = new javax.swing.JLabel();
        labelname = new javax.swing.JLabel();
        labelprice = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        des = new javax.swing.JEditorPane();
        cart = new javax.swing.JButton();
        buy = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        displayQuant = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cartViewContainer = new javax.swing.JPanel();
        quantity_decrease = new javax.swing.JButton();
        quantity_increase = new javax.swing.JButton();
        txtNumber = new javax.swing.JTextField();
        tamount = new javax.swing.JLabel();
        sc2 = new javax.swing.JLabel();
        checkout = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        container = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        photo = new javax.swing.JLabel();
        cartTableContainer = new javax.swing.JPanel();
        searchbtn1 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cart_table = new javax.swing.JTable();
        deleteCart = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, -1, 40));

        myCart.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        myCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cart.png"))); // NOI18N
        myCart.setText("My Cart");
        myCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myCartMouseClicked(evt);
            }
        });
        jPanel2.add(myCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, -1, 40));

        jLabel3.setFont(getFont());
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/myProfile.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 40, 40));

        home.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        home.setForeground(new java.awt.Color(0, 158, 226));
        home.setText("SHOPTIFY");
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel2.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-home-16.png"))); // NOI18N
        jLabel2.setText(" HOME");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, -1, 40));

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
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(190, 20, 860, 20);

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
        productInfo.add(bigPhoto);
        bigPhoto.setBounds(240, 150, 330, 330);

        labelname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelname.setText("label");
        productInfo.add(labelname);
        labelname.setBounds(620, 140, 240, 40);

        labelprice.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelprice.setText("jLabel4");
        productInfo.add(labelprice);
        labelprice.setBounds(620, 180, 190, 50);

        des.setEditable(false);
        des.setBorder(null);
        des.setEditorKit(null);
        des.setForeground(new java.awt.Color(102, 102, 102));
        des.setFocusable(false);
        des.setMargin(new java.awt.Insets(10, 10, 10, 10));
        des.setName(""); // NOI18N
        des.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(des);

        productInfo.add(jScrollPane2);
        jScrollPane2.setBounds(620, 380, 360, 100);

        cart.setText("Add to Cart");
        cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartActionPerformed(evt);
            }
        });
        productInfo.add(cart);
        cart.setBounds(620, 240, 170, 40);

        buy.setText("Buy it now");
        buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyActionPerformed(evt);
            }
        });
        productInfo.add(buy);
        buy.setBounds(810, 240, 170, 40);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Description");
        productInfo.add(jLabel4);
        jLabel4.setBounds(620, 360, 70, 16);

        jLabel6.setText("Quantity");
        productInfo.add(jLabel6);
        jLabel6.setBounds(620, 280, 60, 30);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        productInfo.add(jButton2);
        jButton2.setBounds(680, 310, 30, 30);

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        productInfo.add(jButton3);
        jButton3.setBounds(620, 310, 30, 30);

        displayQuant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        displayQuant.setText("1");
        productInfo.add(displayQuant);
        displayQuant.setBounds(650, 310, 30, 30);

        tabs.addTab("tab3", productInfo);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cartViewContainer.setToolTipText("");
        cartViewContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        quantity_decrease.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        quantity_decrease.setText("-");
        quantity_decrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_decreaseActionPerformed(evt);
            }
        });
        cartViewContainer.add(quantity_decrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 40, 40));

        quantity_increase.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        quantity_increase.setText("+");
        quantity_increase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_increaseActionPerformed(evt);
            }
        });
        cartViewContainer.add(quantity_increase, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 40, 40));

        txtNumber.setBackground(new java.awt.Color(242, 242, 242));
        txtNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        cartViewContainer.add(txtNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 70, 40));

        tamount.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tamount.setForeground(new java.awt.Color(0, 158, 226));
        tamount.setText("49000");
        cartViewContainer.add(tamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, -1, 40));

        sc2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        sc2.setForeground(new java.awt.Color(102, 102, 102));
        sc2.setText("Total:");
        cartViewContainer.add(sc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, 40));

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
        cartViewContainer.add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 300, 40));

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Quanity");
        cartViewContainer.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        container.setBackground(new java.awt.Color(204, 204, 204));
        container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Samsung Galaxy Oten");
        name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        container.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 250, 50));
        container.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, 200));

        cartViewContainer.add(container, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 280, 260));

        jPanel4.add(cartViewContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 360, 570));

        cartTableContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchbtn1.setBackground(new java.awt.Color(0, 158, 226));
        searchbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchbtn1.setBorder(null);
        searchbtn1.setBorderPainted(false);
        cartTableContainer.add(searchbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 60, 40));
        cartTableContainer.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 250, 40));

        add.setBackground(new java.awt.Color(0, 158, 226));
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add new Items");
        add.setBorder(null);
        add.setBorderPainted(false);
        cartTableContainer.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 120, 40));

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

        cartTableContainer.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 650, 450));

        deleteCart.setBackground(new java.awt.Color(255, 51, 51));
        deleteCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        deleteCart.setBorder(null);
        deleteCart.setBorderPainted(false);
        deleteCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCartActionPerformed(evt);
            }
        });
        cartTableContainer.add(deleteCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 50, 40));

        jPanel4.add(cartTableContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 710, 570));
        jPanel4.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 1100, 20));

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

        jPanel5.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1280, 700));

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
        panelMouseClicked(p1, name1, price1, image1, productID, quan);
    }//GEN-LAST:event_p1MouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        panelMouseClicked(p3, name3, price3, image3, productID, quan);
     }//GEN-LAST:event_p3MouseClicked

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        panelMouseClicked(p2, name2, price2, image2, productID, quan);
    }//GEN-LAST:event_p2MouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_backMouseClicked

    private void cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartActionPerformed
        int accountId = UserManager.getLoggedInUserId();
        int id = productIDs.getProductID();
        String productName = labelname.getText();
        String cartPriceStr = labelprice.getText().replaceAll("[^0-9]", "");
        int cartPrice = Integer.parseInt(cartPriceStr);
        String displayQuantStr = displayQuant.getText();
        int cartQuant = Integer.parseInt(displayQuantStr);

        if (accountId == -1) {
            JOptionPane.showMessageDialog(null, "Please log in to add item to cart");
            return;
        }

        try {
            databaseConnector dbc = new databaseConnector();

            // Check if the product already exists in the cart for the logged-in user
            String checkProductQuery = "SELECT * FROM add2cart WHERE account_id = ? AND product_id = ?";
            PreparedStatement checkProductStmt = dbc.getConnection().prepareStatement(checkProductQuery);
            checkProductStmt.setInt(1, accountId);
            checkProductStmt.setInt(2, id);
            ResultSet checkRs = checkProductStmt.executeQuery();

            if (checkRs.next()) {
                // If the product exists, update the product_quantity
                int existingQuant = checkRs.getInt("product_quantity");
                int newQuant = existingQuant + cartQuant;

                String updateQuery = "UPDATE add2cart SET product_quantity = ? WHERE account_id = ? AND product_id = ?";
                PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                updateStmt.setInt(1, newQuant);
                updateStmt.setInt(2, accountId);
                updateStmt.setInt(3, id);
                updateStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Item added to the cart successfully!");
                displayCart();
                tabs.setSelectedIndex(0);

                return;
            } else {
                // If the product doesn't exist, insert a new record
                String insertQuery = "INSERT INTO add2cart (account_id, product_id, product_name, product_price, product_quantity) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                insertStmt.setInt(1, accountId);
                insertStmt.setInt(2, id);
                insertStmt.setString(3, productName);
                insertStmt.setInt(4, cartPrice);
                insertStmt.setInt(5, cartQuant);

                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Item added to the cart successfully!");
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
    }//GEN-LAST:event_cartActionPerformed

    private void myCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCartMouseClicked
        displayCart();
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_myCartMouseClicked

    public void displayAccountName() {
        try {
            int accountId = UserManager.getLoggedInUserId();

            databaseConnector dbc = new databaseConnector();
            String query = "SELECT username, fname, lname, email, address, profile_picture FROM accounts_table WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, accountId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                jLabel12.setText("" + rs.getString("username"));
                jLabel15.setText("" + rs.getString("username"));
                fname.setText("" + rs.getString("fname"));
                lname.setText("" + rs.getString("lname"));
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
        panelMouseClicked(p4, name4, price4, image4, productID, quan);
    }//GEN-LAST:event_p4MouseClicked

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
        panelMouseClicked(p5, name5, price5, image5, productID, stock);
    }//GEN-LAST:event_p5MouseClicked

    private void p6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p6MouseClicked
        panelMouseClicked(p6, name6, price6, image6, productID, quan);
    }//GEN-LAST:event_p6MouseClicked

    private void p7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p7MouseClicked
        panelMouseClicked(p7, name7, price7, image7, productID, quan);
    }//GEN-LAST:event_p7MouseClicked

    private void p8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p8MouseClicked
        panelMouseClicked(p8, name8, price8, image8, productID, quan);
    }//GEN-LAST:event_p8MouseClicked

    private void p9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p9MouseClicked
        panelMouseClicked(p9, name9, price9, image9, productID, quan);
    }//GEN-LAST:event_p9MouseClicked

    private void p10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p10MouseClicked
        panelMouseClicked(p10, name10, price10, image10, productID, quan);
    }//GEN-LAST:event_p10MouseClicked

    private void p11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p11MouseClicked
        panelMouseClicked(p11, name11, price11, image11, productID, quan);
    }//GEN-LAST:event_p11MouseClicked

    private void p12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p12MouseClicked
        panelMouseClicked(p12, name12, price12, image12, productID, quan);
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

    private void buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyActionPerformed
        int accountId = UserManager.getLoggedInUserId();
        int id = productIDs.getProductID();
        String productName = labelname.getText();
        String buyPriceStr = labelprice.getText().replaceAll("[^0-9]", "");
        int buyPrice = Integer.parseInt(buyPriceStr);
        String buyQuantStr = displayQuant.getText();
        int buyQuant = Integer.parseInt(buyQuantStr);

        int totalPrice = buyPrice * buyQuant;

        try {
            databaseConnector dbc = new databaseConnector();

            // Fetch current stock and status of the product
            String fetchProductQuery = "SELECT stock, status FROM products WHERE product_id = ?";
            PreparedStatement fetchProductStmt = dbc.getConnection().prepareStatement(fetchProductQuery);
            fetchProductStmt.setInt(1, id);
            ResultSet fetchRs = fetchProductStmt.executeQuery();
            if (!fetchRs.next()) {
                JOptionPane.showMessageDialog(null, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int currentStock = fetchRs.getInt("stock");
            String currentStatus = fetchRs.getString("status");

            // Check if the purchase already exists for the given cart ID and product name
            String checkPurchaseQuery = "SELECT * FROM purchase WHERE account_id = ? AND product_id = ?";
            PreparedStatement checkPurchaseStmt = dbc.getConnection().prepareStatement(checkPurchaseQuery);
            checkPurchaseStmt.setInt(1, accountId);
            checkPurchaseStmt.setInt(2, id);
            ResultSet checkRs = checkPurchaseStmt.executeQuery();

            if (checkRs.next()) {
                // If purchase exists, update total_quantity and total_price
                int existingQuant = checkRs.getInt("total_quantity");
                int newQuant = existingQuant + buyQuant;
                int existingTotalPrice = checkRs.getInt("total_price");
                int newTotalPrice = existingTotalPrice + totalPrice;
                String updateQuery = "UPDATE purchase SET total_quantity = ?, total_price = ? WHERE account_id = ? AND product_id = ?";
                PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                updateStmt.setInt(1, newQuant);
                updateStmt.setInt(2, newTotalPrice);
                updateStmt.setInt(3, accountId);
                updateStmt.setInt(4, id);

                updateStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Purchase updated successfully!");
                // Update stock and status if necessary
                updateStockAndStatus(dbc, id, currentStock - buyQuant, currentStatus);
            } else {
                String insertQuery = "INSERT INTO purchase (account_id, product_id, product_name, product_price, total_quantity, total_price, date_purchase) VALUES (?, ?, ?, ?, ?, ?, NOW())";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery);
                insertStmt.setInt(1, accountId);
                insertStmt.setInt(2, id);
                insertStmt.setString(3, productName);
                insertStmt.setInt(4, buyPrice);
                insertStmt.setInt(5, buyQuant);
                insertStmt.setInt(6, totalPrice);

                insertStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Purchase added successfully!");
                // Update stock and status if necessary
                updateStockAndStatus(dbc, id, currentStock - buyQuant, currentStatus);
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
    }//GEN-LAST:event_buyActionPerformed

    private void updateStockAndStatus(databaseConnector dbc, int productId, int newStock, String currentStatus) throws SQLException {
        if (newStock < 1) {
            // Update product status to 'not available' if stock is less than 1
            String updateStatusQuery = "UPDATE products SET Stock = ?, Status = 'Not Available' WHERE product_id = ?";
            PreparedStatement updateStatusStmt = dbc.getConnection().prepareStatement(updateStatusQuery);
            updateStatusStmt.setInt(1, newStock);
            updateStatusStmt.setInt(2, productId);
            updateStatusStmt.executeUpdate();
        } else {
            // Update only the stock if it's more than 0
            String updateStockQuery = "UPDATE products SET Stock = ? WHERE product_id = ?";
            PreparedStatement updateStockStmt = dbc.getConnection().prepareStatement(updateStockQuery);
            updateStockStmt.setInt(1, newStock);
            updateStockStmt.setInt(2, productId);
            updateStockStmt.executeUpdate();
        }
        displayUserProducts();
    }

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
        tamount.setText(String.valueOf(total));
    }
    private int cart_id;
    private void cart_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart_tableMouseClicked
        int rowIndex = cart_table.getSelectedRow();
        TableModel model = cart_table.getModel();

        String tblQuantStr = model.getValueAt(rowIndex, 4).toString();
        String tblName = model.getValueAt(rowIndex, 2).toString();
        String tblPriceStr = model.getValueAt(rowIndex, 3).toString();

        tblPrice = Integer.parseInt(tblPriceStr);

        name.setText(tblName);
        tblQuant = Integer.parseInt(tblQuantStr);
        txtNumber.setText(tblQuantStr);

        updateTotal();

        int product_id = Integer.parseInt(model.getValueAt(rowIndex, 1).toString());
        cart_id = Integer.parseInt(model.getValueAt(rowIndex, 0).toString());

        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT Stock, ImagePath FROM products WHERE product_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                stock = rs.getInt("Stock");
                int height = 240;
                int width = 200;
                String getImageFromDatabase = rs.getString("ImagePath");
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
        int accountId = UserManager.getLoggedInUserId();
        int id = productIDs.getProductID();
        String productName = labelname.getText();
        String buyPriceStr = labelprice.getText().replaceAll("[^0-9]", "");
        int buyPrice = Integer.parseInt(buyPriceStr);
        String buyQuantStr = txtNumber.getText();
        int buyQuant = Integer.parseInt(buyQuantStr);

        try {
            databaseConnector dbc = new databaseConnector();

            String fetchProductQuery = "SELECT stock, status FROM products WHERE product_id = ?";
            PreparedStatement fetchProductStmt = dbc.getConnection().prepareStatement(fetchProductQuery);
            fetchProductStmt.setInt(1, id);
            ResultSet fetchRs = fetchProductStmt.executeQuery();
            if (!fetchRs.next()) {
                JOptionPane.showMessageDialog(null, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int currentStock = fetchRs.getInt("stock");
            String currentStatus = fetchRs.getString("status");

            // Check if the purchase already exists for the given cart ID and product name
            String checkPurchaseQuery = "SELECT * FROM purchase WHERE account_id = ? AND product_id = ?";
            PreparedStatement checkPurchaseStmt = dbc.getConnection().prepareStatement(checkPurchaseQuery);
            checkPurchaseStmt.setInt(1, accountId);
            checkPurchaseStmt.setInt(2, id);
            ResultSet checkRs = checkPurchaseStmt.executeQuery();

            if (checkRs.next()) {
                // If purchase exists, update total_quantity and total_price

                int existingQuant = checkRs.getInt("total_quantity");
                int newQuant = existingQuant + buyQuant;
                int existingTotalPrice = checkRs.getInt("total_price");
                int newTotalPrice = existingTotalPrice + total;

                if (newQuant > stock) {
                    JOptionPane.showMessageDialog(null, "Insufficient stock. Available stock: " + stock);
                } else {
                    String updateQuery = "UPDATE purchase SET total_quantity = ?, total_price = ? WHERE account_id = ? AND product_id = ?";
                    PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                    updateStmt.setInt(1, newQuant);
                    updateStmt.setInt(2, newTotalPrice);
                    updateStmt.setInt(3, accountId);
                    updateStmt.setInt(4, id);
                    updateStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Purchase updated successfully!");
                    updateStockAndStatus(dbc, id, currentStock - buyQuant, currentStatus);
                }
            } else {
                String insertQuery = "INSERT INTO purchase (account_id, product_id, product_name, product_price, total_quantity, total_price, date_purchase) VALUES (?, ?, ?, ?, ?, ?, NOW())";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery);
                insertStmt.setInt(1, accountId);
                insertStmt.setInt(2, id);
                insertStmt.setString(3, productName);
                insertStmt.setInt(4, buyPrice);
                insertStmt.setInt(5, buyQuant);
                insertStmt.setInt(6, total);

                if (buyQuant > stock) {
                    JOptionPane.showMessageDialog(null, "nsufficient stock. Available stock: " + stock);
                } else {
                    insertStmt.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Purchase added successfully!");
                // Update stock and status if necessary
                updateStockAndStatus(dbc, id, currentStock - buyQuant, currentStatus);
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
    private javax.swing.JTextField address;
    private javax.swing.JLabel back;
    private javax.swing.JLabel bigPhoto;
    private javax.swing.JButton buy;
    private javax.swing.JButton cart;
    private javax.swing.JPanel cartTableContainer;
    private javax.swing.JPanel cartViewContainer;
    private javax.swing.JTable cart_table;
    private javax.swing.JButton checkout;
    private javax.swing.JPanel container;
    private javax.swing.JButton deleteCart;
    private javax.swing.JEditorPane des;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labelname;
    private javax.swing.JLabel labelprice;
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
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profile1;
    private javax.swing.JPanel profile2;
    private javax.swing.JTable purchase_table;
    private javax.swing.JButton quantity_decrease;
    private javax.swing.JButton quantity_increase;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel sc2;
    private javax.swing.JTextField search;
    private javax.swing.JButton searchbtn1;
    private javax.swing.JButton select;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel tamount;
    private javax.swing.JTextField txtNumber;
    // End of variables declaration//GEN-END:variables
}
