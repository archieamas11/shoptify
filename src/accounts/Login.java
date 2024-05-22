package accounts;

import Admin.adminDashboard;
import Seller.sellerDashboard;
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
import Buyer.buyerDashboard;
import config.actionLogs;
import config.isAccountExist;
import java.awt.HeadlessException;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;

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
        remember.setFocusable(false);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));

        // Round Borders
        UXmethods.RoundBorders.setArcStyle(usernameContainer, 30);
        UXmethods.RoundBorders.setArcStyle(passwordContainer, 30);
        UXmethods.RoundBorders.setArcStyle(login, 30);
    }

    public static boolean loginAccount(String user, String pass, String role, String stats) {
        databaseConnector connector = new databaseConnector();

        try {
            String query = "SELECT account_id, password FROM tbl_accounts WHERE username = ? AND role = ? AND status = ?";
            PreparedStatement statement = connector.getConnection().prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, role);
            statement.setString(3, stats);

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

        hideIcon = new ImageIcon(getClass().getResource("/image/eye.png"));
        showIcon = new ImageIcon(getClass().getResource("/image/eye_hide.png"));

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
        shoptify = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        shoptify_icon = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        create_account = new javax.swing.JLabel();
        usernameContainer = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        username_icon = new javax.swing.JLabel();
        passwordContainer = new javax.swing.JPanel();
        password = new javax.swing.JPasswordField();
        password_icon = new javax.swing.JLabel();
        eye = new javax.swing.JLabel();
        forgotPasswordBtn = new javax.swing.JLabel();
        remember = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        shoptify.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        shoptify.setForeground(new java.awt.Color(0, 158, 226));
        shoptify.setText("Shoptify");
        jPanel1.add(shoptify, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, -1, 30));

        welcome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        welcome.setForeground(new java.awt.Color(153, 153, 153));
        welcome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        welcome.setText("Welcome back to ");
        jPanel1.add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 210, 30));

        shoptify_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-buying-94.png"))); // NOI18N
        jPanel1.add(shoptify_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, -1, -1));

        login.setBackground(new java.awt.Color(0, 158, 226));
        login.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login");
        login.setBorder(null);
        login.setBorderPainted(false);
        login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jPanel1.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, 290, 40));

        create_account.setForeground(new java.awt.Color(102, 102, 102));
        create_account.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        create_account.setText("New User? Click here to Register");
        create_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                create_accountMouseClicked(evt);
            }
        });
        jPanel1.add(create_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, 290, 20));

        usernameContainer.setBackground(new java.awt.Color(245, 245, 245));
        usernameContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setBackground(new java.awt.Color(245, 245, 245));
        username.setForeground(new java.awt.Color(153, 153, 153));
        username.setText("Username");
        username.setToolTipText("");
        username.setBorder(null);
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
        usernameContainer.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 210, 40));

        username_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-male-user-24.png"))); // NOI18N
        usernameContainer.add(username_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel1.add(usernameContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 290, 40));

        passwordContainer.setBackground(new java.awt.Color(245, 245, 245));
        passwordContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        password.setBackground(new java.awt.Color(245, 245, 245));
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
        passwordContainer.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 210, 40));

        password_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        password_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-lock-24.png"))); // NOI18N
        passwordContainer.add(password_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 40));

        eye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/eye.png"))); // NOI18N
        eye.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eyeMouseClicked(evt);
            }
        });
        passwordContainer.add(eye, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 20, 40));

        jPanel1.add(passwordContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 290, 40));

        forgotPasswordBtn.setForeground(new java.awt.Color(153, 153, 153));
        forgotPasswordBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        forgotPasswordBtn.setText("Forgot Password?");
        forgotPasswordBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotPasswordBtnMouseClicked(evt);
            }
        });
        jPanel1.add(forgotPasswordBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 420, 100, 40));

        remember.setForeground(new java.awt.Color(153, 153, 153));
        remember.setText(" Remember me");
        remember.setBorder(null);
        remember.setContentAreaFilled(false);
        remember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rememberMouseClicked(evt);
            }
        });
        remember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rememberActionPerformed(evt);
            }
        });
        jPanel1.add(remember, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void eyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eyeMouseClicked
        togglePasswordVisibility();
    }//GEN-LAST:event_eyeMouseClicked

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginActionPerformed(null);
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseClicked
        password.setFocusable(true);
        password.requestFocusInWindow();
        password.setEchoChar('\u25cf');
        if (username.getText().isEmpty()) {
            username.setText("Username");
            username.setForeground(Color.decode("#999999"));
        }
    }//GEN-LAST:event_passwordMouseClicked

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        if (password.getText().equals("Password")) {
            password.setText("");
            password.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_passwordFocusGained

    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked
        username.setFocusable(true);
        username.requestFocusInWindow();
        if (password.getText().isEmpty()) {
            password.setEchoChar('\0');
            password.setText("Password");
            password.setForeground(Color.decode("#999999"));
        }
    }//GEN-LAST:event_usernameMouseClicked

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
        if (username.getText().equals("Username")) {
            username.setText("");
            username.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_usernameFocusGained

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        String user = username.getText();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username and password!");
        } else {
            String loginType = checkLogin(user, pass);

            if (loginType != null) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                UserManager.setLoggedInUserId(accountId);

                switch (loginType) {
                    case "Seller":
                        openDashboard(new sellerDashboard());
                        recordSellerLogin();
                        break;
                    case "Buyer":
                        openDashboard(new buyerDashboard());
                        break;
                    case "Admin":
                        openDashboard(new adminDashboard());
                        recordAdminLogin();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed: Account not found or incorrect password!");
                password.setText("");
            }
        }
    }//GEN-LAST:event_loginActionPerformed

    private String checkLogin(String user, String pass) {
        if (loginAccount(user, pass, "Seller", "Active")) {
            return "Seller";
        } else if (loginAccount(user, pass, "Buyer", "Active")) {
            return "Buyer";
        } else if (loginAccount(user, pass, "Admin", "Active")) {
            return "Admin";
        }
        return null;
    }

    private void openDashboard(JFrame dashboard) {
        dashboard.setVisible(true);
        this.dispose();
    }

    private void recordAdminLogin() {
        int adminID = UserManager.getLoggedInUserId();
        String action = "Logged in";
        String details = "Admin " + adminID + " successfully logged in!";
        actionLogs.recordAdminLogs(adminID, action, details);
    }

    private void recordSellerLogin() {
        int sellerID = UserManager.getLoggedInUserId();
        String action = "Logged in";
        String details = "Seller " + sellerID + " successfully logged in!";
        actionLogs.recordSellerLogs(sellerID, action, details);
        System.out.println(sellerID);
    }

    private void create_accountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_create_accountMouseClicked
        chooseAccount reg = new chooseAccount();
        reg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_create_accountMouseClicked

    private void rememberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rememberMouseClicked
        remember.setFocusable(true);
        remember.requestFocusInWindow();
    }//GEN-LAST:event_rememberMouseClicked

    private void rememberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rememberActionPerformed
        boolean isSelected;
        isSelected = remember.isSelected();
        rememberMe.setRememberAccountValue(isSelected);
        System.out.println(isSelected);
    }//GEN-LAST:event_rememberActionPerformed

    private void forgotPasswordBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPasswordBtnMouseClicked
        try {
            databaseConnector dbc = new databaseConnector();
            String userInput = JOptionPane.showInputDialog(null, "Enter your username or email:");

            if (userInput == null) {
                return;
            }

            if (userInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a username or email.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean isExist = isAccountExist.checkUsername(userInput) || isAccountExist.checkEmail(userInput);

            if (!isExist) {
                JOptionPane.showMessageDialog(null, "Username or email does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:");
            if (newPassword == null || newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a new password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (newPassword.length() < 8) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String updateQuery = "UPDATE tbl_accounts SET password = ? WHERE username = ? OR email = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(updateQuery);
            pst.setString(1, hashedNewPassword);
            pst.setString(2, userInput);
            pst.setString(3, userInput);

            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
        }
    }//GEN-LAST:event_forgotPasswordBtnMouseClicked

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
    private javax.swing.JLabel forgotPasswordBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel passwordContainer;
    private javax.swing.JLabel password_icon;
    private javax.swing.JCheckBox remember;
    private javax.swing.JLabel shoptify;
    private javax.swing.JLabel shoptify_icon;
    private javax.swing.JTextField username;
    private javax.swing.JPanel usernameContainer;
    private javax.swing.JLabel username_icon;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
