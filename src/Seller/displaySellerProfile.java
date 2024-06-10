/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Seller;

import config.GetImage;
import config.databaseConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author MARITIME 02
 */
public class displaySellerProfile {

    public static void Info(JLabel seller_full_name, JLabel username, JLabel address, JLabel phone, JLabel email, JLabel store, int seller_id, JLabel photo, JLabel seller_rating) {
        try {
            int sum_star;
            int seller_count;
            databaseConnector dbc = new databaseConnector();

            //seller rating
            String query = "SELECT SUM(total_star) as total_star, COUNT(*) as total_rating FROM tbl_rating4seller WHERE seller_id = ?";
            try (PreparedStatement pst = dbc.getConnection().prepareStatement(query)) {
                pst.setInt(1, seller_id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        sum_star = rs.getInt("total_star");
                        seller_count = rs.getInt("total_rating");
                        float rating = (float) sum_star / seller_count;
                        if (sum_star < 1) {
                            seller_rating.setText("0 (0)");
                        } else {
                            seller_rating.setText(String.format("%.1f (%d)", rating, seller_count));
                        }
                    }
                }
            }

            //seller profile
            ResultSet rs = dbc.getData("SELECT * FROM tbl_accounts WHERE account_id =" + seller_id);
            if (rs.next()) {
                String fname;
                String lname;
                fname = rs.getString("first_name");
                lname = rs.getString("last_name");
                fname = Character.toUpperCase(fname.charAt(0)) + fname.substring(1);
                lname = Character.toUpperCase(lname.charAt(0)) + lname.substring(1);
                seller_full_name.setText(fname + " " + lname);
                username.setText("@" + rs.getString("username"));
                address.setText("" + rs.getString("address"));
                phone.setText("" + rs.getString("phone_number"));
                email.setText("" + rs.getString("email"));
                store.setText(rs.getString("shop_name"));
                int height = 120;
                int width = 120;
                String getImageFromDatabase = rs.getString("profile_picture");
                GetImage.displayImage(photo, getImageFromDatabase, height, width);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
