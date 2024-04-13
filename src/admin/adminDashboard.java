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

    String path = null;
    File selectedFile = null;

    public adminDashboard() {
        initComponents();
        displayData();
        displayProducts();
    }

    private void displayProducts() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        dashboard = new javax.swing.JButton();
        manage = new javax.swing.JButton();
        orders = new javax.swing.JButton();
        graphs = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
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
        jLabel14 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        images = new javax.swing.JLabel();
        image_container = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("ADMINISTRATOR");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

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

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 720));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 40));

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

        jPanel17.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 870, 280));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Products");
        jPanel17.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 30));

        jPanel8.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 910, 330));

        jPanel18.setBackground(new java.awt.Color(235, 235, 235));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("Product Name");
        jPanel18.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));

        images.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(images, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 120, 110));
        jPanel18.add(image_container, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 120, 110));

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

        jButton16.setText("Import");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 120, 30));

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
        jPanel18.add(savebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 140, 30));

        delete.setBackground(new java.awt.Color(255, 51, 51));
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel18.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 140, 30));

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel18.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 140, 30));

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
        jPanel18.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 130, 30));
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
        jPanel18.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 30, 150));

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
        jPanel18.add(editbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 140, 30));

        jScrollPane2.setViewportView(description);

        jPanel18.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 210, 70));

        jPanel8.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 910, 230));

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

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 30, 1040, 700));

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

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
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
        System.out.println(path);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jLabel23CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel23CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23CaretPositionChanged

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
    private javax.swing.JButton add;
    private javax.swing.JButton dashboard;
    private javax.swing.JButton delete;
    private javax.swing.JEditorPane description;
    private javax.swing.JButton editbtn;
    private javax.swing.JButton graphs;
    private javax.swing.JTextField id;
    private javax.swing.JTextField image_container;
    private javax.swing.JLabel images;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JButton logout;
    private javax.swing.JButton manage;
    private javax.swing.JTextField name;
    private javax.swing.JButton orders;
    private javax.swing.JPanel p1;
    private javax.swing.JLabel p_image;
    private javax.swing.JTextField price;
    private javax.swing.JTable product_table;
    private javax.swing.JPanel s;
    private javax.swing.JButton savebtn;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField stocks;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel total_orders;
    private javax.swing.JLabel total_products;
    private javax.swing.JLabel total_sales;
    // End of variables declaration//GEN-END:variables
}
