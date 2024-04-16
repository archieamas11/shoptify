package admin;

import accounts.Login;
import com.formdev.flatlaf.FlatLightLaf;
import config.databaseConnector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class adminDashboard extends javax.swing.JFrame {

    public byte[] imageBytes;
    File selectedFile;
    String path;
    String fileName = null;
    String imgPath = null;
    byte[] person_image = null;
    public String action, reference;

    public adminDashboard() {
        initComponents();
        displayData();
        displayProducts();
        displayAccounts();

        UXmethods.RoundBorders.setArcStyle(logout, 30);
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
            ResultSet rs = dbc.getData("SELECT * FROM products");
            product_table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public ImageIcon ResizeImage(String ImagePath, byte[] pic) {
        ImageIcon MyImage = null;
        if (ImagePath != null) {
            MyImage = new ImageIcon(ImagePath);
        } else {
            MyImage = new ImageIcon(pic);
        }
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(images.getWidth(), images.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public int FileExistenceChecker(String path) {
        File file = new File(path);
        String fileName = file.getName();

        Path filePath = Paths.get("src/images", fileName);
        boolean fileExists = Files.exists(filePath);

        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

    public void imageUpdater(String existingFilePath, String newFilePath) {
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: ");
            }
        } else {
            try {
                Files.copy(selectedFile.toPath(), new File(reference).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error on update!");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        dashboard = new javax.swing.JButton();
        manage = new javax.swing.JButton();
        orders = new javax.swing.JButton();
        graphs = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        total_orders = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        total_products = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        total_sales = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        images = new javax.swing.JLabel();
        image_container = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        importt = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        price = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        stocks = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        editbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JEditorPane();
        jLabel17 = new javax.swing.JLabel();
        search2 = new javax.swing.JTextField();
        searchbtn5 = new javax.swing.JButton();
        searchbtn6 = new javax.swing.JButton();
        searchbtn7 = new javax.swing.JButton();
        searchbtn8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        p_image = new javax.swing.JLabel();
        s = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        accounts_table = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        searchbtn1 = new javax.swing.JButton();
        searchbtn3 = new javax.swing.JButton();
        searchbtn2 = new javax.swing.JButton();
        searchbtn4 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        manage5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        manage6 = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        manage7 = new javax.swing.JLabel();
        myprofile1 = new javax.swing.JLabel();
        manage1 = new javax.swing.JLabel();
        manage8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        manage9 = new javax.swing.JLabel();
        manage10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        select = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        manage11 = new javax.swing.JLabel();
        manage12 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        manage13 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        manage14 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel5.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-administrator-male-100.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 100, -1));

        jLabel1.setText("Admin");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        logout.setBackground(new java.awt.Color(255, 51, 51));
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jPanel3.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 190, 40));

        dashboard.setText("Dashboard");
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        jPanel3.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 190, 40));

        manage.setText("Manage");
        manage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageActionPerformed(evt);
            }
        });
        jPanel3.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 190, 40));

        orders.setText("Orders");
        orders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersActionPerformed(evt);
            }
        });
        jPanel3.add(orders, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 190, 40));

        graphs.setText("Products");
        graphs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphsActionPerformed(evt);
            }
        });
        jPanel3.add(graphs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 190, 40));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 50, 720));

        jButton1.setText("Accounts");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 190, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("ADMINISTRATOR");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 720));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 10));

        tabs.setBackground(new java.awt.Color(153, 153, 153));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Total Orders");
        jPanel16.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-buy-48.png"))); // NOI18N
        jPanel16.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        total_orders.setText("total_orders");
        jPanel16.add(total_orders, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanel6.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 290, 80));

        jPanel13.setBackground(new java.awt.Color(235, 235, 235));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 910, 400));

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-products-48.png"))); // NOI18N
        jPanel12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Total Products");
        jPanel12.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        total_products.setText("total_products");
        jPanel12.add(total_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 30));

        jPanel6.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 290, 80));

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-sales-48.png"))); // NOI18N
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Total Sales");
        jPanel10.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        total_sales.setText("total_sales");
        jPanel10.add(total_sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 290, 80));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel2.setText("Dashboard");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 40));

        tabs.addTab("tab1", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(235, 235, 235));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_table.setAutoCreateRowSorter(true);
        product_table.setBackground(new java.awt.Color(204, 204, 204));
        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(product_table);

        jPanel17.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 890, 280));

        jPanel8.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 930, 320));

        jPanel18.setBackground(new java.awt.Color(235, 235, 235));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("Product Name");
        jPanel18.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));

        images.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(images, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 120, 110));
        jPanel18.add(image_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 120, 110));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Manage Products");
        jPanel18.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jLabel23.setText("Price");
        jLabel23.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel23CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel18.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 30));

        importt.setText("Import");
        importt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importtActionPerformed(evt);
            }
        });
        jPanel18.add(importt, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 120, 30));

        savebtn.setText("Save");
        savebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savebtnMouseClicked(evt);
            }
        });
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });
        jPanel18.add(savebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 140, 30));

        delete.setBackground(new java.awt.Color(255, 51, 51));
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel18.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 140, 30));

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel18.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 140, 30));

        jLabel24.setText("Stocks");
        jLabel24.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel24CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel18.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 30));

        status.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        jPanel18.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 130, 30));
        jPanel18.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 120, 30));

        jLabel26.setText("Description");
        jLabel26.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel26CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel18.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, 30));
        jPanel18.add(stocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 120, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 30, 150));

        jLabel27.setText("Status");
        jLabel27.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jLabel27CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel18.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, 30));
        jPanel18.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 120, 30));

        jLabel22.setText("Product ID");
        jPanel18.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 30));

        id.setEnabled(false);
        jPanel18.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 120, 30));

        editbtn.setText("Edit");
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });
        jPanel18.add(editbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 140, 30));

        jScrollPane2.setViewportView(description);

        jPanel18.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 210, 70));

        jPanel8.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 930, 230));

        jLabel17.setBackground(new java.awt.Color(0, 158, 226));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 158, 226));
        jLabel17.setText("Products");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

        search2.setForeground(new java.awt.Color(102, 102, 102));
        search2.setText("  Search");
        jPanel8.add(search2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 300, 40));

        searchbtn5.setBackground(new java.awt.Color(0, 158, 226));
        searchbtn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchbtn5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 158, 226), 1, true));
        jPanel8.add(searchbtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 60, 40));

        searchbtn6.setBackground(new java.awt.Color(102, 102, 102));
        searchbtn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edi-icon-24.png"))); // NOI18N
        searchbtn6.setBorder(null);
        jPanel8.add(searchbtn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 60, 40));

        searchbtn7.setBackground(new java.awt.Color(122, 183, 147));
        searchbtn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-plus-24.png"))); // NOI18N
        searchbtn7.setBorder(null);
        jPanel8.add(searchbtn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 60, 40));

        searchbtn8.setBackground(new java.awt.Color(255, 51, 51));
        searchbtn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        searchbtn8.setBorder(null);
        jPanel8.add(searchbtn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 60, 40));

        tabs.addTab("tab2", jPanel8);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 51));

        p_image.setText("jLabel5");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(p_image, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p_image, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 330, 210));

        tabs.addTab("tab3", jPanel2);

        s.setBackground(new java.awt.Color(255, 255, 255));
        s.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p1.setBackground(new java.awt.Color(255, 153, 153));
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
        });
        p1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("jLabel5");
        p1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        jPanel15.add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 200, 200));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-edit-24.png"))); // NOI18N
        jPanel15.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 90, 38));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-edit-24.png"))); // NOI18N
        jPanel15.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 90, 38));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Not available");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel15.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 170, 30));

        jScrollPane1.setViewportView(jPanel15);

        s.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 910, 579));

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
        accounts_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accounts_tableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(accounts_table);

        jPanel19.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 890, 530));

        jPanel4.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 930, 570));

        jLabel16.setBackground(new java.awt.Color(0, 158, 226));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 158, 226));
        jLabel16.setText("Buyer's Account");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

        search.setForeground(new java.awt.Color(102, 102, 102));
        search.setText("  Search");
        jPanel4.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 300, 40));

        searchbtn1.setBackground(new java.awt.Color(0, 158, 226));
        searchbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-24.png"))); // NOI18N
        searchbtn1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 158, 226), 1, true));
        jPanel4.add(searchbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 60, 40));

        searchbtn3.setBackground(new java.awt.Color(122, 183, 147));
        searchbtn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-plus-24.png"))); // NOI18N
        searchbtn3.setBorder(null);
        jPanel4.add(searchbtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 60, 40));

        searchbtn2.setBackground(new java.awt.Color(255, 51, 51));
        searchbtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-delete-24.png"))); // NOI18N
        searchbtn2.setBorder(null);
        jPanel4.add(searchbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 60, 40));

        searchbtn4.setBackground(new java.awt.Color(102, 102, 102));
        searchbtn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edi-icon-24.png"))); // NOI18N
        searchbtn4.setBorder(null);
        searchbtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtn4ActionPerformed(evt);
            }
        });
        jPanel4.add(searchbtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 60, 40));

        tabs.addTab("tab5", jPanel4);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage5.setForeground(new java.awt.Color(102, 102, 102));
        manage5.setText("Address");
        jPanel14.add(manage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 30));
        jPanel14.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 120, 40));
        jPanel14.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 120, 40));
        jPanel14.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 250, 40));

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activated", "Inactive", "Pending" }));
        jPanel14.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 240, 40));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buyer", "Seller" }));
        jPanel14.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 240, 40));

        manage9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage9.setForeground(new java.awt.Color(102, 102, 102));
        manage9.setText("Gender");
        jPanel14.add(manage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, 30));

        manage10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage10.setForeground(new java.awt.Color(102, 102, 102));
        manage10.setText("Role");
        jPanel14.add(manage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, 30));

        jButton2.setBackground(new java.awt.Color(0, 158, 226));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 250, 40));
        jPanel14.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 830, 20));

        select.setText("Select Image");
        select.setEnabled(false);
        jPanel14.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 120, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("File size: maximum 1 MB ");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 380, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("File extension: .JPEG, .PNG ");
        jPanel14.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, -1, -1));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 120, 110));

        jTextField4.setEditable(false);
        jPanel14.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 250, 40));

        manage11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage11.setForeground(new java.awt.Color(102, 102, 102));
        manage11.setText("Username");
        jPanel14.add(manage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 30));

        manage12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage12.setForeground(new java.awt.Color(102, 102, 102));
        manage12.setText("Email");
        jPanel14.add(manage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 30));
        jPanel14.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 250, 40));

        manage13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage13.setForeground(new java.awt.Color(102, 102, 102));
        manage13.setText("Buyer ID");
        jPanel14.add(manage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 30));

        jTextField6.setEditable(false);
        jPanel14.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 250, 40));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel14.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 30, 420));
        jPanel14.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 830, 20));

        manage14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        manage14.setForeground(new java.awt.Color(102, 102, 102));
        manage14.setText("Status");
        jPanel14.add(manage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, 30));
        jPanel14.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 250, 40));

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 920, 590));

        tabs.addTab("tab6", jPanel11);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1040, 730));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 1020, 720));

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

    private void graphsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphsActionPerformed
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_graphsActionPerformed

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
        editProduct edit = new editProduct();
    }//GEN-LAST:event_p1MouseClicked

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
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

                    Blob imageBlob = rs.getBlob("image");
                    if (imageBlob != null) {
                        InputStream imageStream = imageBlob.getBinaryStream();
                        BufferedImage bufferedImage = ImageIO.read(imageStream);
                        Image scaledImage = bufferedImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        ImageIcon imageIcon = new ImageIcon(scaledImage);
                        images.setIcon(imageIcon);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_editbtnActionPerformed

    private void jLabel27CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel27CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27CaretPositionChanged

    private void jLabel26CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel26CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel26CaretPositionChanged

    private void jLabel24CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel24CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24CaretPositionChanged

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        String valname = name.getText();
        String valprice = price.getText();
        String valstocks = stocks.getText();
        String valdes = description.getText();

        String valstats = (String) status.getSelectedItem();

        if (valname.isEmpty() || valprice.isEmpty() || valstocks.isEmpty() || selectedFile == null || !selectedFile.exists()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields and select an image.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File selectedFile = new File(path);

        try {
            databaseConnector dbc = new databaseConnector();

            String checkQuery = "SELECT COUNT(*) FROM products WHERE `Product Name` = ?";
            PreparedStatement checkStmt = dbc.getConnection().prepareStatement(checkQuery);
            checkStmt.setString(1, valname);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Product already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (InputStream is = new FileInputStream(selectedFile)) {
                String insertQuery = "INSERT INTO `products`(`Product Name`, Price, Stock, Description, Status, image, Date Created) VALUES (?, ?, ?, ?, ?, ? , CURDATE())";
                PreparedStatement insertStmt = dbc.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, valname);
                insertStmt.setString(2, valprice);
                insertStmt.setString(3, valstocks);
                insertStmt.setString(4, valdes);
                insertStmt.setString(5, valstats);
                insertStmt.setBlob(6, is);

                insertStmt.executeUpdate();

                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);

                    DefaultTableModel model = (DefaultTableModel) product_table.getModel();
                    model.addRow(new Object[]{productId, valname, valprice, valstocks, valdes, valstats});
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to retrieve product ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(null, "Product Added");
            id.setText("");
            name.setText("");
            price.setText("");
            stocks.setText("");
            status.setSelectedIndex(0);
            description.setText("");
            displayData();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading image file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int rowIndex = product_table.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a product first");
        } else {
            TableModel model = product_table.getModel();
            Object value = model.getValueAt(rowIndex, 0);
            String id = value.toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                databaseConnector dbc = new databaseConnector();
                dbc.deleteProduct(Integer.parseInt(id));
                displayData();
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pst;
            String sql;

            if (path != null) {
                File selectedFile = new File(path);

                if (selectedFile.exists()) {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(selectedFile));

                    sql = "UPDATE products SET `Product Name`=?, Price=?, Stock=?, Description=?, image=?, Status=? WHERE product_id=?";
                    pst = dbc.getConnection().prepareStatement(sql);
                    pst.setString(1, name.getText());
                    pst.setString(2, price.getText());
                    pst.setString(3, stocks.getText());
                    pst.setString(4, description.getText());
                    pst.setBinaryStream(5, bis, (int) selectedFile.length());
                    pst.setString(6, (String) status.getSelectedItem());
                    pst.setString(7, id.getText());

                    bis.close();
                } else {
                    JOptionPane.showMessageDialog(null, "File not found at the specified path: " + path);
                    return;
                }
            } else {
                sql = "UPDATE products SET `Product Name`=?, Price=?, Stock=?, Description=?, Status=? WHERE product_id=?";
                pst = dbc.getConnection().prepareStatement(sql);
                pst.setString(1, name.getText());
                pst.setString(2, price.getText());
                pst.setString(3, stocks.getText());
                pst.setString(4, description.getText());
                pst.setString(5, (String) status.getSelectedItem());
                pst.setString(6, id.getText());

            }

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                displayData();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update data!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SQL Error updating data: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void savebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savebtnMouseClicked

    }//GEN-LAST:event_savebtnMouseClicked

    private void importtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importtActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fnwf = new FileNameExtensionFilter("PNG JPG AND JPEG", "png", "jpeg", "jpg");
        fileChooser.addChoosableFileFilter(fnwf);
        int load = fileChooser.showOpenDialog(null);

        if (load == fileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            //reference = "src/images/" + selectedFile.getName();
            path = selectedFile.getAbsolutePath();

            ImageIcon ii = new ImageIcon(path);
            Image img = ii.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            images.setIcon(new ImageIcon(img));
        }
    }//GEN-LAST:event_importtActionPerformed

    private void jLabel23CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel23CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23CaretPositionChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void accounts_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accounts_tableMouseClicked

    }//GEN-LAST:event_accounts_tableMouseClicked

    private void searchbtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtn4ActionPerformed
        tabs.setSelectedIndex(5);
    }//GEN-LAST:event_searchbtn4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JTable accounts_table;
    private javax.swing.JButton add;
    private javax.swing.JButton dashboard;
    private javax.swing.JButton delete;
    private javax.swing.JEditorPane description;
    private javax.swing.JButton editbtn;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton graphs;
    private javax.swing.JTextField id;
    private javax.swing.JTextField image_container;
    private javax.swing.JLabel images;
    private javax.swing.JButton importt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JButton logout;
    private javax.swing.JRadioButton male;
    private javax.swing.JButton manage;
    private javax.swing.JLabel manage1;
    private javax.swing.JLabel manage10;
    private javax.swing.JLabel manage11;
    private javax.swing.JLabel manage12;
    private javax.swing.JLabel manage13;
    private javax.swing.JLabel manage14;
    private javax.swing.JLabel manage5;
    private javax.swing.JLabel manage6;
    private javax.swing.JLabel manage7;
    private javax.swing.JLabel manage8;
    private javax.swing.JLabel manage9;
    private javax.swing.JLabel myprofile1;
    private javax.swing.JTextField name;
    private javax.swing.JButton orders;
    private javax.swing.JRadioButton other;
    private javax.swing.JPanel p1;
    private javax.swing.JLabel p_image;
    private javax.swing.JTextField price;
    private javax.swing.JTable product_table;
    private javax.swing.JPanel s;
    private javax.swing.JButton savebtn;
    private javax.swing.JTextField search;
    private javax.swing.JTextField search2;
    private javax.swing.JButton searchbtn1;
    private javax.swing.JButton searchbtn2;
    private javax.swing.JButton searchbtn3;
    private javax.swing.JButton searchbtn4;
    private javax.swing.JButton searchbtn5;
    private javax.swing.JButton searchbtn6;
    private javax.swing.JButton searchbtn7;
    private javax.swing.JButton searchbtn8;
    private javax.swing.JButton select;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField stocks;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel total_orders;
    private javax.swing.JLabel total_products;
    private javax.swing.JLabel total_sales;
    // End of variables declaration//GEN-END:variables
}
