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
public class displayProducts {

    public static void products(int seller_id, JLabel product_is_empty, JTable product_table) {
        try {
            databaseConnector dbc = new databaseConnector();
            try (PreparedStatement pstmt = dbc.getConnection().prepareStatement(""
                    + "SELECT `product_id` as `#`,"
                    + "`product_name` as `Product Name`,"
                    + "`product_price` as `Price`,"
                    + "`product_stock` as `Stock(s)`,"
                    + "`product_category` as `Category`,"
                    + "`total_sold` as `Sold`,"
                    + "`date_created` as `Date Created`,"
                    + "`product_status` as `Status`"
                    + " FROM tbl_products "
                    + "WHERE product_status IN ('Available', 'Not Available', 'Sold out')"
                    + "AND seller_id = ?")) {
                pstmt.setInt(1, seller_id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        product_is_empty.setText("PRODUCT TABLE IS EMPTY!");
                        product_table.setModel(new DefaultTableModel());
                    } else {
                        product_is_empty.setText("");
                        DefaultTableModel tableModel = resultSetToNonEditableTableModel(rs);
                        product_table.setModel(tableModel);
                        product_table.getColumnModel().getColumn(7).setCellRenderer(new colored.status());

                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                        product_table.setDefaultRenderer(Object.class, centerRenderer);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
}
