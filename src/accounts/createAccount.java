package accounts;

import com.formdev.flatlaf.FlatLightLaf;
import config.databaseConnector;
import config.isAccountExist;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author SCC-COLLEGE
 */
public class createAccount extends javax.swing.JFrame {

    public createAccount() {
        initComponents();
        fname.setFocusable(false);
        lname.setFocusable(false);
        email.setFocusable(false);
        number.setFocusable(false);
        address.setFocusable(false);
        username.setFocusable(false);
        password.setFocusable(false);
        role.setFocusable(false);

        UXmethods.RoundBorders.setArcStyle(submit, 15);

        //Container
        UXmethods.RoundBorders.setArcStyle(fnamePanel, 30);
        UXmethods.RoundBorders.setArcStyle(lnamePanel, 30);
        UXmethods.RoundBorders.setArcStyle(c3, 30);
        UXmethods.RoundBorders.setArcStyle(c4, 30);
        UXmethods.RoundBorders.setArcStyle(c5, 30);
        UXmethods.RoundBorders.setArcStyle(c6, 30);
        UXmethods.RoundBorders.setArcStyle(c7, 30);
        UXmethods.RoundBorders.setArcStyle(c8, 30);

    }

    public static boolean checkEmail(String email) {
        databaseConnector dbc = new databaseConnector();

        try {
            String query = "SELECT * FROM accounts_table WHERE email = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean checkUsername(String user) {
        databaseConnector dbc = new databaseConnector();

        try {
            String query = "SELECT * FROM accounts_table WHERE username = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        shoptify_icon = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        fnamePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fname = new javax.swing.JTextField();
        lnamePanel = new javax.swing.JPanel();
        lname = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        c3 = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        c4 = new javax.swing.JPanel();
        number = new javax.swing.JTextField();
        c5 = new javax.swing.JPanel();
        address = new javax.swing.JTextField();
        c6 = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        c7 = new javax.swing.JPanel();
        eye = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        c8 = new javax.swing.JPanel();
        role = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 158, 226));
        jLabel2.setText("SHOPTIFY");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, 20));

        shoptify_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-buying-94.png"))); // NOI18N
        jPanel1.add(shoptify_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Create new account");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 350, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("journey!");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 350, 10));

        login.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        login.setForeground(new java.awt.Color(102, 102, 102));
        login.setText("Already have an account? Login");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        jPanel1.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 690, 190, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Email");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 70, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Phone Number");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 90, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Address");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 80, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Username");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 70, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Password");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 70, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Role");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 70, 20));

        submit.setBackground(new java.awt.Color(0, 158, 226));
        submit.setForeground(new java.awt.Color(255, 255, 255));
        submit.setText("Submit");
        submit.setBorder(null);
        submit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        jPanel1.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 620, 350, 40));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Welcome to Shoptify, please enter your ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 350, 10));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("information to start your amazing shopping");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 350, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 350, 20));

        fnamePanel.setBackground(new java.awt.Color(245, 245, 245));
        fnamePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                fnamePanelComponentMoved(evt);
            }
        });
        fnamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        fnamePanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 10, 40));

        fname.setBackground(new java.awt.Color(245, 245, 245));
        fname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fname.setForeground(new java.awt.Color(91, 90, 95));
        fname.setText("First name");
        fname.setBorder(null);
        fname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fnameFocusGained(evt);
            }
        });
        fname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fnameMouseClicked(evt);
            }
        });
        fnamePanel.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 40));

        jPanel1.add(fnamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 180, 40));

        lnamePanel.setBackground(new java.awt.Color(245, 245, 245));
        lnamePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                lnamePanelComponentMoved(evt);
            }
        });
        lnamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lname.setBackground(new java.awt.Color(245, 245, 245));
        lname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lname.setForeground(new java.awt.Color(91, 90, 95));
        lname.setText("Last name");
        lname.setBorder(null);
        lnamePanel.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 140, 40));

        jPanel1.add(lnamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 180, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton1.setText("jToggleButton1");
        jPanel3.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jToggleButton2.setText("jToggleButton1");
        jPanel3.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jToggleButton3.setText("jToggleButton1");
        jPanel3.add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 370, 720));

        c3.setBackground(new java.awt.Color(245, 245, 245));
        c3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        email.setBackground(new java.awt.Color(245, 245, 245));
        email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        email.setForeground(new java.awt.Color(91, 90, 95));
        email.setText("Email");
        email.setBorder(null);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        c3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel1.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 350, 40));

        c4.setBackground(new java.awt.Color(245, 245, 245));
        c4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        number.setBackground(new java.awt.Color(245, 245, 245));
        number.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        number.setForeground(new java.awt.Color(91, 90, 95));
        number.setText("Phone number");
        number.setBorder(null);
        number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberActionPerformed(evt);
            }
        });
        c4.add(number, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel1.add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 350, 40));

        c5.setBackground(new java.awt.Color(245, 245, 245));
        c5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        address.setBackground(new java.awt.Color(245, 245, 245));
        address.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        address.setForeground(new java.awt.Color(91, 90, 95));
        address.setText("Address");
        address.setBorder(null);
        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });
        c5.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel1.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 350, 40));

        c6.setBackground(new java.awt.Color(245, 245, 245));
        c6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setBackground(new java.awt.Color(245, 245, 245));
        username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username.setForeground(new java.awt.Color(91, 90, 95));
        username.setText("Username");
        username.setBorder(null);
        c6.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 40));

        jPanel1.add(c6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 350, 40));

        c7.setBackground(new java.awt.Color(245, 245, 245));
        c7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/eye.png"))); // NOI18N
        eye.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eyeMouseClicked(evt);
            }
        });
        c7.add(eye, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, 40));

        password.setBackground(new java.awt.Color(245, 245, 245));
        password.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        password.setForeground(new java.awt.Color(91, 90, 95));
        password.setText("Password");
        password.setBorder(null);
        password.setEchoChar('\u0000');
        c7.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 300, 40));

        jPanel1.add(c7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 350, 40));

        jPanel4.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, 40, 40));

        c8.setBackground(new java.awt.Color(245, 245, 245));
        c8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        role.setBackground(new java.awt.Color(245, 245, 245));
        role.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        role.setForeground(new java.awt.Color(91, 90, 95));
        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Seller", "Buyer" }));
        role.setToolTipText("mnvnbv");
        role.setBorder(null);
        role.setName("sdfdsfsd"); // NOI18N
        role.setOpaque(true);
        c8.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 320, 40));

        jPanel1.add(c8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, 350, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        String first_name = fname.getText();
        String last_name = lname.getText();
        String em = email.getText();
        String phone = number.getText();
        String user = username.getText();
        String pass = password.getText();
        String add = address.getText();
        String selectedRole = (String) role.getSelectedItem();

        if (em.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || selectedRole.isEmpty() || user.isEmpty() || pass.isEmpty() || phone.isEmpty() || add.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } //if (pass.length() < 8) {
        //JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        //return;
        //}
        if (phone.length() < 11 || phone.length() > 12 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        databaseConnector dbc = new databaseConnector();
        try {
            if (isAccountExist.checkEmail(em)) {
                JOptionPane.showMessageDialog(null, "Email already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isAccountExist.checkUsername(user)) {
                JOptionPane.showMessageDialog(null, "Username already taken.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
            String defaultImage = "src/sampleProfiles/default profile 100x100.png";

            String sql = "INSERT INTO `accounts_table`(`email`, `fname`, `lname`, `phone number`, `username`,`password`, `role`, `date joined`, `status`, `profile_picture`, `address`) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?, ?)";
            PreparedStatement pst = (PreparedStatement) dbc.getConnection().prepareStatement(sql);
            pst.setString(1, em);
            pst.setString(2, first_name);
            pst.setString(3, last_name);
            pst.setString(4, phone);
            pst.setString(5, user);
            pst.setString(6, hashedPass);
            pst.setString(7, selectedRole);
            pst.setString(8, "Pending");
            pst.setString(9, defaultImage);
            pst.setString(10, add);

            pst.executeUpdate();
            pst.close();

            JOptionPane.showMessageDialog(null, "Account created successfully");

            email.setText("");
            fname.setText("");
            lname.setText("");
            username.setText("");
            number.setText("");
            address.setText("");
            password.setText("");
            role.setSelectedIndex(0);
            Login back = new Login();
            back.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_submitActionPerformed

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        Login back = new Login();
        back.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_loginMouseClicked

    private void fnamePanelComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_fnamePanelComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_fnamePanelComponentMoved

    private void lnamePanelComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_lnamePanelComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lnamePanelComponentMoved

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void eyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eyeMouseClicked
    }//GEN-LAST:event_eyeMouseClicked

    private void numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberActionPerformed

    private void fnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnameFocusGained
        if (fname.getText().equals("First name")) {
            fname.setText("");
            fname.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_fnameFocusGained

    private void fnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnameMouseClicked
        fname.setFocusable(true);
        fname.requestFocusInWindow();
        if (password.getText().isEmpty()) {
            password.setEchoChar('\0');
            password.setText("Password");
            password.setForeground(Color.decode("#999999"));
        } else if (number.getText().isEmpty()) {
            number.setText("Phone number");
        }
    }//GEN-LAST:event_fnameMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new createAccount().setVisible(true);
                }
            });

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(createAccount.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JPanel c3;
    private javax.swing.JPanel c4;
    private javax.swing.JPanel c5;
    private javax.swing.JPanel c6;
    private javax.swing.JPanel c7;
    private javax.swing.JPanel c8;
    private javax.swing.JTextField email;
    private javax.swing.JLabel eye;
    private javax.swing.JTextField fname;
    private javax.swing.JPanel fnamePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JTextField lname;
    private javax.swing.JPanel lnamePanel;
    private javax.swing.JLabel login;
    private javax.swing.JTextField number;
    private javax.swing.JPasswordField password;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JLabel shoptify_icon;
    private javax.swing.JButton submit;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
