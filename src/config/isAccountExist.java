/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ArchieQT
 */
public class isAccountExist {

    public static boolean checkEmail(String email) {
        databaseConnector dbc = new databaseConnector();

        try {
            String query = "SELECT * FROM tbl_accounts WHERE email = ?";
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
            String query = "SELECT * FROM tbl_accounts WHERE username = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean checkPassword(String password, int id) {

        databaseConnector dbc = new databaseConnector();

        try {
            String query = "SELECT * FROM tbl_accounts WHERE account_id = ?";
            PreparedStatement pst = dbc.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String hash = rs.getString("password");
                if (BCrypt.checkpw(password, hash)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }
}
