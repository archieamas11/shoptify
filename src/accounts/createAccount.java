package accounts;

import com.formdev.flatlaf.FlatLightLaf;
import config.databaseConnector;
import config.isAccountExist;
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
        UXmethods.RoundBorders.setArcStyle(createAccountContainer, 30);
        UXmethods.RoundBorders.setArcStyle(submit, 15);

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
        createAccountContainer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        password = new javax.swing.JTextField();
        role = new javax.swing.JComboBox<>();
        submit = new javax.swing.JButton();
        number = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createAccountContainer.setBackground(new java.awt.Color(214, 214, 214));
        createAccountContainer.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("START NOW");
        createAccountContainer.add(jLabel2);
        jLabel2.setBounds(60, 20, 120, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 158, 226));
        jLabel6.setText("Create new account");
        createAccountContainer.add(jLabel6);
        jLabel6.setBounds(60, 40, 410, 49);

        login.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        login.setForeground(new java.awt.Color(102, 102, 102));
        login.setText("Already have an account? Login");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        createAccountContainer.add(login);
        login.setBounds(60, 90, 190, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("First Name");
        createAccountContainer.add(jLabel7);
        jLabel7.setBounds(100, 130, 70, 20);
        createAccountContainer.add(fname);
        fname.setBounds(100, 150, 160, 40);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Last Name");
        createAccountContainer.add(jLabel8);
        jLabel8.setBounds(290, 130, 57, 20);
        createAccountContainer.add(lname);
        lname.setBounds(290, 150, 160, 40);
        createAccountContainer.add(email);
        email.setBounds(100, 220, 350, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Email");
        createAccountContainer.add(jLabel9);
        jLabel9.setBounds(100, 200, 70, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Username");
        createAccountContainer.add(jLabel10);
        jLabel10.setBounds(100, 340, 70, 20);
        createAccountContainer.add(username);
        username.setBounds(100, 360, 350, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Phone Number");
        createAccountContainer.add(jLabel11);
        jLabel11.setBounds(100, 270, 90, 20);

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        createAccountContainer.add(password);
        password.setBounds(100, 430, 350, 40);

        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Seller", "Buyer" }));
        createAccountContainer.add(role);
        role.setBounds(100, 490, 350, 40);

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
        createAccountContainer.add(submit);
        submit.setBounds(100, 570, 350, 40);
        createAccountContainer.add(number);
        number.setBounds(100, 290, 350, 40);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Password");
        createAccountContainer.add(jLabel12);
        jLabel12.setBounds(100, 410, 70, 20);

        jPanel1.add(createAccountContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 540, 640));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 370, 720));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        String selectedRole = (String) role.getSelectedItem();

        if (em.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || selectedRole.isEmpty() || user.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
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

            String sql = "INSERT INTO `accounts_table`(`email`, `fname`, `lname`, `phone number`, `username`,`password`, `role`, `date joined`, `status`, `profile_picture`) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?)";
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

            pst.executeUpdate();
            pst.close();

            JOptionPane.showMessageDialog(null, "Account created successfully");

            email.setText("");
            fname.setText("");
            lname.setText("");
            username.setText("");
            number.setText("");
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

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

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
    private javax.swing.JPanel createAccountContainer;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel login;
    private javax.swing.JTextField number;
    private javax.swing.JTextField password;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JButton submit;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
