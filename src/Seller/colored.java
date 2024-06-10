/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Seller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author MARITIME 02
 */
public class colored {

    public static class status extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = (String) value;

            switch (status) {
                case "Available":
                case "Accepted":
                    cellComponent.setForeground(new Color(0, 102, 0));
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Not Available":
                    cellComponent.setForeground(new Color(204, 204, 204));
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Sold out":
                case "Declined":
                    cellComponent.setForeground(Color.RED);
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Pending":
                    cellComponent.setForeground(new Color(255, 153, 0));
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
                    break;
                case "Archived":
                    cellComponent.setForeground(new Color(102, 102, 102));
                    break;
                default:
                    cellComponent.setForeground(table.getForeground());
            }
            ((JLabel) cellComponent).setHorizontalAlignment(SwingConstants.CENTER);

            return cellComponent;
        }
    }
}
