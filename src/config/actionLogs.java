/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ArchieQT
 */
public class actionLogs {

    public static void recordAdminLogs(int admin_id, String action, String details) {

        String sql = "INSERT INTO tbl_adminlogs (admin_id, adminlogs_action, adminlogs_details, adminlogs_timestamp) VALUES (?, ?, ?, NOW())";
        databaseConnector dbc = new databaseConnector();

        try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
            pst.setInt(1, admin_id);
            pst.setString(2, action);
            pst.setString(3, details);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void recordSellerLogs(int seller_id, String action, String details) {

        String sql = "INSERT INTO tbl_sellerlogs (seller_id, sellerlogs_action, sellerlogs_details, sellerlogs_timestamp) VALUES (?, ?, ?, NOW())";
        databaseConnector dbc = new databaseConnector();

        try (PreparedStatement pst = dbc.getConnection().prepareStatement(sql)) {
            pst.setInt(1, seller_id);
            pst.setString(2, action);
            pst.setString(3, details);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayAdminLogs(JTable table, int adminID) {
        try {
            databaseConnector dbc = new databaseConnector();

            String query = "SELECT `adminlogs_id` as `Logs ID`, `admin_id` as `Admin ID`, `adminlogs_action` as `Action`, `adminlogs_details` as `Details`, `adminlogs_timestamp` as `Timestamp` FROM tbl_adminlogs WHERE admin_id = ?";
            PreparedStatement statement = dbc.getConnection().prepareStatement(query);
            statement.setInt(1, adminID);

            ResultSet rs = statement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn column;
            column = table.getColumnModel().getColumn(0);
            column.setPreferredWidth(20);
            column = table.getColumnModel().getColumn(1);
            column.setPreferredWidth(20);
            column = table.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = table.getColumnModel().getColumn(3);
            column.setPreferredWidth(700);
            column = table.getColumnModel().getColumn(4);
            column.setPreferredWidth(100);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);

            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public static void displaySellerLogs(JTable table, int sellerID) {
        try {
            databaseConnector dbc = new databaseConnector();
            String query = "SELECT `seller_id` as `Logs ID`, `seller_id` as `Seller ID`, `sellerlogs_action` as `Action`, `sellerlogs_details` as `Details`, `sellerlogs_timestamp` as `Timestamp` FROM tbl_sellerlogs WHERE seller_id= ?";
            PreparedStatement statement = dbc.getConnection().prepareStatement(query);
            statement.setInt(1, sellerID);
            ResultSet rs = statement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn column;
            column = table.getColumnModel().getColumn(0);
            column.setPreferredWidth(20);
            column = table.getColumnModel().getColumn(1);
            column.setPreferredWidth(20);
            column = table.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = table.getColumnModel().getColumn(3);
            column.setPreferredWidth(700);
            column = table.getColumnModel().getColumn(4);
            column.setPreferredWidth(100);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);

            rs.close();
        } catch (Exception ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
}
