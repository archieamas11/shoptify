package user;

import accounts.Login;
import accounts.UserManager;
import accounts.frontPage;
import com.formdev.flatlaf.FlatLightLaf;
import config.databaseConnector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class userDashboard extends javax.swing.JFrame {

    public userDashboard() {
        initComponents();
        displayUserProducts();
        displayCart();
    }
    private String selectedGender = "";

    private void getSelectedGender(String gender) {
        selectedGender = gender;
    }

    public void displayCart() {
        try {
            databaseConnector dbc = new databaseConnector();

            String username = UserManager.getLoggedInUser();

            // Select only the rows where the username matches the currently logged-in user
            String selectQuery = "SELECT * FROM add2cart WHERE username = ?";
            PreparedStatement selectStmt = dbc.getConnection().prepareStatement(selectQuery);
            selectStmt.setString(1, username);
            ResultSet rs = selectStmt.executeQuery();

            // Create a DefaultTableModel to hold the data for the JTable
            DefaultTableModel model = new DefaultTableModel();
            // Add columns to the model
            model.addColumn("Product Name");
            model.addColumn("Price");
            model.addColumn("Quantity");

            // Populate the model with data from the ResultSet
            while (rs.next()) {
                String productName = rs.getString("product_name");
                int price = rs.getInt("product_price");
                int quantity = rs.getInt("product_quantity");

                // Add a row to the model
                model.addRow(new Object[]{productName, price, quantity});
            }

            // Set the model to your JTable
            cart_table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayUserProducts() {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement statement = dbc.getConnection().prepareStatement("SELECT `image`, `Product Name`, `Price` FROM products WHERE Status = ?");
            statement.setString(1, "Available");
            ResultSet rs = statement.executeQuery();

            int productCounter = 1;
            JLabel[] nameLabels = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12};
            JLabel[] priceLabels = {price1, price2, price3, price4, price5, price6, price7, price8, price9, price10, price11, price12};
            JLabel[] imageLabels = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};
            while (rs.next() && productCounter <= 12) {
                Blob imageBlob = rs.getBlob("image");
                InputStream imageStream = imageBlob.getBinaryStream();
                try {
                    BufferedImage bufferedImage = ImageIO.read(imageStream);
                    Image scaledImage = bufferedImage.getScaledInstance(210, 200, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(scaledImage);
                    String productName = rs.getString("Product Name");
                    int productPrice = rs.getInt("Price");

                    imageLabels[productCounter - 1].setIcon(imageIcon);
                    nameLabels[productCounter - 1].setText(productName);
                    priceLabels[productCounter - 1].setText("₱   " + productPrice);

                    productCounter++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void panelMouseClicked(JPanel panel, JLabel nameLabel, JLabel priceLabel, JLabel imageLabel) {
        if (panel != null && imageLabel.getIcon() != null && nameLabel.getText() != null && !nameLabel.getText().isEmpty() && priceLabel.getText() != null && !priceLabel.getText().isEmpty()) {
            ImageIcon icon = (ImageIcon) imageLabel.getIcon();
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(330, 330, Image.SCALE_SMOOTH);
            ImageIcon selectedImage = new ImageIcon(scaledImage);
            atay.setIcon(selectedImage);
            labelname.setText(nameLabel.getText());
            labelprice.setText(priceLabel.getText());

            try {
                databaseConnector dbc = new databaseConnector();
                PreparedStatement statement = dbc.getConnection().prepareStatement("SELECT `Description` FROM products WHERE `Product Name` = ?");
                statement.setString(1, nameLabel.getText());
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    String description = rs.getString("Description");
                    des.setText(description);
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
        home = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        myCart = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        image1 = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();
        price1 = new javax.swing.JLabel();
        p2 = new javax.swing.JPanel();
        image2 = new javax.swing.JLabel();
        name2 = new javax.swing.JLabel();
        price2 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        productInfo = new javax.swing.JPanel();
        atay = new javax.swing.JLabel();
        labelname = new javax.swing.JLabel();
        labelprice = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        des = new javax.swing.JEditorPane();
        cart = new javax.swing.JButton();
        buy = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        quantity = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cart_table = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        myprofile = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        edit = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        manage = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        image_view = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        manage1 = new javax.swing.JLabel();
        manage2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        manage3 = new javax.swing.JLabel();
        manage4 = new javax.swing.JLabel();
        other = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        manage5 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox<>();
        day = new javax.swing.JComboBox<>();
        month = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        manage6 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        myprofile1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        logout = new javax.swing.JLabel();
        myprofile2 = new javax.swing.JLabel();
        myprofile3 = new javax.swing.JLabel();
        myprofile4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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

        home.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        home.setForeground(new java.awt.Color(0, 158, 226));
        home.setText("SHOPTIFY");
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel2.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 50));

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

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 40));

        tabs.setBackground(new java.awt.Color(51, 255, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        p1.setBackground(new java.awt.Color(255, 255, 255));
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
        });
        p1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p1.add(image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        name1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name1.setForeground(new java.awt.Color(51, 51, 51));
        p1.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        price1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price1.setForeground(new java.awt.Color(51, 51, 51));
        p1.add(price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

        jPanel1.add(p1);
        p1.setBounds(190, 50, 210, 180);

        p2.setBackground(new java.awt.Color(255, 255, 255));
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
        });
        p2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p2.add(image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 123));

        name2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name2.setForeground(new java.awt.Color(51, 51, 51));
        p2.add(name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 25));

        price2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        price2.setForeground(new java.awt.Color(51, 51, 51));
        p2.add(price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 22));

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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 580));

        tabs.addTab("tab2", jPanel3);

        productInfo.setBackground(new java.awt.Color(255, 255, 255));
        productInfo.setLayout(null);
        productInfo.add(atay);
        atay.setBounds(240, 150, 330, 330);

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
        jScrollPane2.setBounds(620, 340, 360, 140);

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

        quantity.setFocusCycleRoot(true);
        quantity.setName(""); // NOI18N
        quantity.setValue(1);
        productInfo.add(quantity);
        quantity.setBounds(620, 310, 50, 22);

        tabs.addTab("tab3", productInfo);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cart_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(cart_table);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 1080, 470));
        jPanel4.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 300, 40));

        jButton2.setBackground(new java.awt.Color(0, 158, 226));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add New Items");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 60, 130, 40));

        searchbtn.setText("Search");
        jPanel4.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 110, 40));

        tabs.addTab("tab4", jPanel4);

        myprofile.setBackground(new java.awt.Color(245, 245, 245));
        myprofile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        myprofile.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 40, 40));

        edit.setText("Edit Profile");
        myprofile.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage.setForeground(new java.awt.Color(102, 102, 102));
        manage.setText("Date of birth");
        jPanel9.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, 20));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel9.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 30, 380));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(image_view, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(image_view, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 120, 110));

        select.setText("Select Image");
        jPanel9.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 360, 120, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("File extension: .JPEG, .PNG ");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("File size: maximum 1 MB ");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 400, -1, -1));
        jPanel9.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 860, 20));

        manage1.setForeground(new java.awt.Color(102, 102, 102));
        manage1.setText("Manage your account ");
        jPanel9.add(manage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        manage2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage2.setForeground(new java.awt.Color(102, 102, 102));
        manage2.setText("Username");
        jPanel9.add(manage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));
        jPanel9.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 270, 40));

        manage3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage3.setForeground(new java.awt.Color(102, 102, 102));
        manage3.setText("First name");
        jPanel9.add(manage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        manage4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage4.setForeground(new java.awt.Color(102, 102, 102));
        manage4.setText("Email");
        jPanel9.add(manage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, -1));

        other.setText(" Other");
        other.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherMouseClicked(evt);
            }
        });
        jPanel9.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, -1, 20));

        male.setText(" Male");
        male.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maleMouseClicked(evt);
            }
        });
        jPanel9.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, -1, 20));

        female.setText(" Female");
        female.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                femaleMouseClicked(evt);
            }
        });
        jPanel9.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, -1, 20));

        manage5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage5.setForeground(new java.awt.Color(102, 102, 102));
        manage5.setText("Gender");
        jPanel9.add(manage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, 20));

        year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013" }));
        jPanel9.add(year, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, -1, 30));

        day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jPanel9.add(day, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, -1, 30));

        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jPanel9.add(month, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, -1, 30));

        jButton1.setBackground(new java.awt.Color(0, 158, 226));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, -1, 30));

        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("chielbrc");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, 20));
        jPanel9.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 270, 40));

        manage6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage6.setForeground(new java.awt.Color(102, 102, 102));
        manage6.setText("Last name");
        jPanel9.add(manage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));
        jPanel9.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 270, 40));

        myprofile1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        myprofile1.setText("My Profile");
        jPanel9.add(myprofile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 70, 30));

        myprofile.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 1000, 600));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("username");
        myprofile.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));
        myprofile.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 150, 20));
        myprofile.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, -1, -1));
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("My Purchase Table");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

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

        jPanel5.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1280, 690));

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

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_homeMouseClicked

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_nextMouseClicked

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
        panelMouseClicked(p1, name1, price1, image1);
    }//GEN-LAST:event_p1MouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        panelMouseClicked(p3, name3, price3, image3);
     }//GEN-LAST:event_p3MouseClicked

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        panelMouseClicked(p2, name2, price2, image2);
    }//GEN-LAST:event_p2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartActionPerformed
        String username = UserManager.getLoggedInUser();
        String productName = labelname.getText();
        String cartPriceStr = labelprice.getText().replaceAll("[^0-9]", "");
        int cartPrice = Integer.parseInt(cartPriceStr);
        int cartQuant = (int) quantity.getValue();

        if (username == null) {
            JOptionPane.showMessageDialog(null, "Please log in to add item to cart");
            return;
        }

        try {
            databaseConnector dbc = new databaseConnector();

            // Check if the username already has an existing record in the add2cart table
            String selectQuery = "SELECT * FROM add2cart WHERE username = ?";
            PreparedStatement selectStmt = dbc.getConnection().prepareStatement(selectQuery);
            selectStmt.setString(1, username);
            ResultSet rs = selectStmt.executeQuery();

            boolean userExists = rs.next(); // This will be true if the user exists

            // If the user exists, check if the same product_name already exists in the add2cart table for that username
            if (userExists) {
                String checkProductQuery = "SELECT * FROM add2cart WHERE username = ? AND product_name = ?";
                PreparedStatement checkProductStmt = dbc.getConnection().prepareStatement(checkProductQuery);
                checkProductStmt.setString(1, username);
                checkProductStmt.setString(2, productName);
                ResultSet checkRs = checkProductStmt.executeQuery();

                if (checkRs.next()) {
                    // If the product_name exists for username, only update the product_quantity
                    int existingQuant = checkRs.getInt("product_quantity");
                    int newQuant = existingQuant + cartQuant;

                    String updateQuery = "UPDATE add2cart SET product_quantity = ? WHERE username = ? AND product_name = ?";
                    PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                    updateStmt.setInt(1, newQuant);
                    updateStmt.setString(2, username);
                    updateStmt.setString(3, productName);
                    updateStmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Item added to the cart successfully!");
                    tabs.setSelectedIndex(0);
                    quantity.setValue(1);
                    return; // Exit the method since we've already updated the quantity
                }

                // If another username performs the action, create a new cart_id
                String insertQuery = "INSERT INTO add2cart (username, product_name, product_price, product_quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, username);
                insertStmt.setString(2, productName);
                insertStmt.setInt(3, cartPrice);
                insertStmt.setInt(4, cartQuant);

                insertStmt.executeUpdate();

            } else {
                // If the user doesn't exist, create a new cart_id
                String insertQuery = "INSERT INTO add2cart (username, product_name, product_price, product_quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, username);
                insertStmt.setString(2, productName);
                insertStmt.setInt(3, cartPrice);
                insertStmt.setInt(4, cartQuant);

                insertStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Item added to the cart successfully!");

            tabs.setSelectedIndex(0);
            quantity.setValue(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cartActionPerformed

    private void myCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCartMouseClicked
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_myCartMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel1MouseClicked

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
        // TODO add your handling code here:
    }//GEN-LAST:event_myprofile8MouseClicked

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_savebtnActionPerformed

    private void buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();

            // Retrieve the username from the accounts_table
            String getUsernameQuery = "SELECT username FROM accounts_table WHERE username";
            PreparedStatement getUsernameStmt = dbc.getConnection().prepareStatement(getUsernameQuery);
            ResultSet usernameRs = getUsernameStmt.executeQuery();
            String username;
            if (usernameRs.next()) {
                username = usernameRs.getString("username"); // Retrieve the username from the result set
            } else {
                // Handle the case where username is not found in accounts_table
                JOptionPane.showMessageDialog(null, "Username not found in accounts table!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method or handle the error appropriately
            }

            String product_name = labelname.getText();
            String priceStr = labelprice.getText().replaceAll("[^0-9]", ""); // Remove any non-numeric characters
            int unit_price = Integer.parseInt(priceStr);

            int quantity_bought = (int) quantity.getValue();
            int total_price = unit_price * quantity_bought;

            // Check if a purchase with the same username and product already exists
            String checkQuery = "SELECT * FROM purchase WHERE username = ? AND product_name = ?";
            PreparedStatement checkStmt = dbc.getConnection().prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            checkStmt.setString(2, product_name);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                // Update the quantity and total price if the purchase exists
                String updateQuery = "UPDATE purchase SET quantity = quantity + ?, total_price = total_price + ? WHERE username = ? AND product_name = ?";
                PreparedStatement updateStmt = dbc.getConnection().prepareStatement(updateQuery);
                updateStmt.setInt(1, quantity_bought);
                updateStmt.setInt(2, total_price);
                updateStmt.setString(3, username);
                updateStmt.setString(4, product_name);
                updateStmt.executeUpdate();
            } else {
                // Insert a new record if the purchase does not exist
                String insertQuery = "INSERT INTO purchase(product_name, unit_price, quantity, total_price, timestamp, username) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?)";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery);
                insertStmt.setString(1, product_name);
                insertStmt.setInt(2, unit_price);
                insertStmt.setInt(3, quantity_bought);
                insertStmt.setInt(4, total_price);
                insertStmt.setString(5, username);
                insertStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Item bought successfully!");

            tabs.setSelectedIndex(5);
            //displayPurchase();
            quantity.setValue(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_buyActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void maleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maleMouseClicked
        male.setSelected(true);
        female.setSelected(false);
        other.setSelected(false);
        String gender = "Male";
        getSelectedGender(gender);
    }//GEN-LAST:event_maleMouseClicked

    private void femaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_femaleMouseClicked
        male.setSelected(false);
        female.setSelected(true);
        other.setSelected(false);
        String gender = "Female";
        getSelectedGender(gender);
    }//GEN-LAST:event_femaleMouseClicked

    private void otherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherMouseClicked
        male.setSelected(false);
        female.setSelected(false);
        other.setSelected(true);
        String gender = "Other";
        getSelectedGender(gender);
    }//GEN-LAST:event_otherMouseClicked

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new userDashboard().setVisible(true);
                }
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(frontPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel atay;
    private javax.swing.JButton buy;
    private javax.swing.JButton cart;
    private javax.swing.JTable cart_table;
    private javax.swing.JComboBox<String> day;
    private javax.swing.JEditorPane des;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel edit1;
    private javax.swing.JTextField email;
    private javax.swing.JRadioButton female;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labelname;
    private javax.swing.JLabel labelprice;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel logout1;
    private javax.swing.JRadioButton male;
    private javax.swing.JLabel manage;
    private javax.swing.JLabel manage1;
    private javax.swing.JLabel manage10;
    private javax.swing.JLabel manage11;
    private javax.swing.JLabel manage12;
    private javax.swing.JLabel manage2;
    private javax.swing.JLabel manage3;
    private javax.swing.JLabel manage4;
    private javax.swing.JLabel manage5;
    private javax.swing.JLabel manage6;
    private javax.swing.JLabel manage7;
    private javax.swing.JLabel manage8;
    private javax.swing.JLabel manage9;
    private javax.swing.JComboBox<String> month;
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
    private javax.swing.JRadioButton other;
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
    private javax.swing.JSpinner quantity;
    private javax.swing.JButton savebtn;
    private javax.swing.JTextField search;
    private javax.swing.JButton searchbtn;
    private javax.swing.JButton select;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
