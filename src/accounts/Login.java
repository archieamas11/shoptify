package accounts;

import admin.adminDashboard;
import com.formdev.flatlaf.FlatLightLaf;
import config.databaseConnector;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.mindrot.jbcrypt.BCrypt;
import user.userDashboard;

public class Login extends javax.swing.JFrame {

    private boolean passwordVisible = false;
    private ImageIcon hideIcon;
    private ImageIcon showIcon;
    public static int accountId;

    public Login() {
        initComponents();
        username.setFocusable(false);
        password.setFocusable(false);
        login.setFocusable(false);
        // Round Borders
        // login.setBackground(new Color(0, 158, 226));
        // login.putClientProperty(FlatClientProperties.STYLE, "arc: 30");
    }

    public static boolean loginAccount(String user, String pass, String role) {
        databaseConnector connector = new databaseConnector();

        try {
            String query = "SELECT account_id, password FROM accounts_table WHERE username = ? AND role = ?";
            PreparedStatement statement = connector.getConnection().prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, role);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                accountId = rs.getInt("account_id");
                String hash = rs.getString("password");
                if (BCrypt.checkpw(pass, hash)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;

        hideIcon = new ImageIcon(getClass().getResource("/image/eye_hide.png"));
        showIcon = new ImageIcon(getClass().getResource("/image/eye.png"));

        if (passwordVisible) {
            eye.setIcon(showIcon);
            password.setEchoChar('\u0000');

        } else {
            eye.setIcon(hideIcon);
            password.setEchoChar('\u25cf');
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        password_icon = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        shoptify = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        shoptify_icon = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        username_icon = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        create_account = new javax.swing.JLabel();
        eye = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        password_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-lock-24.png"))); // NOI18N
        jPanel1.add(password_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, 30));

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 290, 10));

        username.setForeground(new java.awt.Color(153, 153, 153));
        username.setText("Username");
        username.setToolTipText("");
        username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        username.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        username.setName(""); // NOI18N
        username.setSelectedTextColor(new java.awt.Color(0, 0, 255));
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
        });
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 240, 30));

        password.setForeground(new java.awt.Color(153, 153, 153));
        password.setText("Password");
        password.setToolTipText("");
        password.setBorder(null);
        password.setEchoChar('\0');
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
        });
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordMouseClicked(evt);
            }
        });
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 210, 30));

        shoptify.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        shoptify.setForeground(new java.awt.Color(0, 158, 226));
        shoptify.setText("Shoptify");
        jPanel1.add(shoptify, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 240, -1, 30));

        welcome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        welcome.setForeground(new java.awt.Color(153, 153, 153));
        welcome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        welcome.setText("Welcome back to ");
        jPanel1.add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 210, 30));

        shoptify_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-buying-94.png"))); // NOI18N
        jPanel1.add(shoptify_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, -1));

        login.setBackground(new java.awt.Color(0, 158, 226));
        login.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login");
        login.setBorder(null);
        login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jPanel1.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 290, 40));

        username_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-male-user-24.png"))); // NOI18N
        jPanel1.add(username_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, -1, 30));

        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, 290, 30));

        create_account.setForeground(new java.awt.Color(102, 102, 102));
        create_account.setText("New User? Click here to Register");
        create_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                create_accountMouseClicked(evt);
            }
        });
        jPanel1.add(create_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 170, 20));

        eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/eye.png"))); // NOI18N
        eye.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eyeMouseClicked(evt);
            }
        });
        jPanel1.add(eye, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 20, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        String user = username.getText();
        String pass = password.getText();

        if (user.equals("Username") || pass.equals("Password")) {
            JOptionPane.showMessageDialog(null, "Please Input both username and password!");
        } else {
            if (loginAccount(user, pass, "Admin")) {
                JOptionPane.showMessageDialog(null, "Admin Login Success!");
                UserManager.setLoggedInUserId(accountId);
                adminDashboard ads = new adminDashboard();
                ads.setVisible(true);
                this.dispose();
            } else if (loginAccount(user, pass, "User")) {
                JOptionPane.showMessageDialog(null, "User Login Success!");
                UserManager.setLoggedInUserId(accountId);
                userDashboard use = new userDashboard();
                use.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed: Account not found or incorrect password!");
            }
        }
    }//GEN-LAST:event_loginActionPerformed

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
        if (username.getText().equals("Username")) {
            username.setText("");
            username.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_usernameFocusGained

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        if (password.getText().equals("Password")) {
            password.setText("");
            password.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_passwordFocusGained

    private void passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseClicked
        password.setFocusable(true);
        password.requestFocusInWindow();
        password.setEchoChar('\u25cf');
        if (username.getText().isEmpty()) {
            username.setText("Username");
            username.setForeground(Color.decode("#999999"));
        }
    }//GEN-LAST:event_passwordMouseClicked

    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked
        username.setFocusable(true);
        username.requestFocusInWindow();
        if (password.getText().isEmpty()) {
            password.setEchoChar('\0');
            password.setText("Password");
            password.setForeground(Color.decode("#999999"));
        }
    }//GEN-LAST:event_usernameMouseClicked

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginActionPerformed(null);
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void create_accountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_create_accountMouseClicked
        createAccount create = new createAccount();
        create.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_create_accountMouseClicked

    private void eyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eyeMouseClicked
        togglePasswordVisibility();
     }//GEN-LAST:event_eyeMouseClicked

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Login().setVisible(true);
                }
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel create_account;
    private javax.swing.JLabel eye;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel password_icon;
    private javax.swing.JLabel shoptify;
    private javax.swing.JLabel shoptify_icon;
    private javax.swing.JTextField username;
    private javax.swing.JLabel username_icon;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
