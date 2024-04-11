package accounts;

import com.formdev.flatlaf.FlatLightLaf;
import config.databaseConnector;
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
        role = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        login = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        password = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jPanel1.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 350, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("START NOW");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 120, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 158, 226));
        jLabel6.setText("Create new account");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 110, 410, -1));

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
        jPanel1.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, 350, 40));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 640, 720));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Last Name");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, 20));
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 160, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Email");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 70, 20));
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 350, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Username");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 70, 20));
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 350, 40));

        login.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        login.setForeground(new java.awt.Color(102, 102, 102));
        login.setText("Already have an account? Login");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        jPanel1.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 190, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("First Name");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 70, 20));
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 160, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Password");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, 70, 20));
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 350, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        String user = username.getText();
        String pass = password.getText();
        String selectedRole = (String) role.getSelectedItem();

        if (em.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || selectedRole.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //if (pass.length() < 8) {
        //JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        //return;
        //}
        databaseConnector dbc = new databaseConnector();
        try {
            if (checkEmail(em)) {
                JOptionPane.showMessageDialog(null, "Email already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (checkUsername(user)) {
                JOptionPane.showMessageDialog(null, "Username already taken.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());

            String sql = "INSERT INTO `accounts_table`(`email`, `fname`, `lname`, `username`,`password`, `role`, `date`) VALUES (?, ?, ?, ?, ?, ?, CURDATE())";
            PreparedStatement pst = (PreparedStatement) dbc.getConnection().prepareStatement(sql);
            pst.setString(1, em);
            pst.setString(2, first_name);
            pst.setString(3, last_name);
            pst.setString(4, user);
            pst.setString(5, hashedPass);
            pst.setString(6, selectedRole);
            pst.executeUpdate();
            pst.close();

            JOptionPane.showMessageDialog(null, "Account created successfully");

            email.setText("");
            fname.setText("");
            lname.setText("");
            username.setText("");
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
            Logger.getLogger(createAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel login;
    private javax.swing.JTextField password;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JButton submit;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
