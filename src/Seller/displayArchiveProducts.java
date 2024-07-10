/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Seller;

import config.databaseConnector;
import static config.flatlaftTable.resultSetToNonEditableTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MARITIME 02
 */
public class displayArchiveProducts {

    public static void displayArchive(int sellerID, JLabel archive_is_empty, JTable archive_table) {
        try {
            databaseConnector dbc = new databaseConnector();
            PreparedStatement pstmt = dbc.getConnection().prepareStatement("SELECT"
                    + "`product_id` as `Product ID`,"
                    + "`product_name` as `Product Name`,"
                    + "`product_price` as `Price`,"
                    + "`product_stock` as `Stock(s)`,"
                    + "`product_category` as `Category`,"
                    + "`total_sold` as `Sold`,"
                    + "`date_created` as `Date Created`,"
                    + "`product_status` as `Status`"
                    + "FROM tbl_products WHERE product_status IN ('Archive') AND seller_id = ?");
            pstmt.setInt(1, sellerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    archive_is_empty.setText("ARCHIVE TABLE IS EMPTY!");
                    archive_table.setModel(new DefaultTableModel());
                } else {
                    archive_is_empty.setText("");
                    DefaultTableModel tableModel = resultSetToNonEditableTableModel(rs);
                    archive_table.setModel(tableModel);
                    archive_table.getColumnModel().getColumn(7).setCellRenderer(new colored.status());

                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    archive_table.setDefaultRenderer(Object.class, centerRenderer);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
}
