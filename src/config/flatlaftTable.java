/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Cursor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ArchieQT
 */
public class flatlaftTable {

    public static void design(JPanel panel, JTable table, JScrollPane scroll) {
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:#F1F1F1;");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:40;"
                //+ "hoverBackground:null;"
                //+ "pressedBackground:null;"
                + "separatorColor:#F1F1F1;"
                + "font:bold;"
                + "background:#F1F1F1;");

        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "showVerticalLines:true;"
                + "selectionBackground:#99CCFF;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");

        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
        scroll.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static DefaultTableModel resultSetToNonEditableTableModel(ResultSet rs) throws SQLException {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells non-editable
            }
        };

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Add columns to table model
        for (int i = 1; i <= columnCount; i++) {
            tableModel.addColumn(metaData.getColumnLabel(i));
        }

        // Add rows to table model
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            tableModel.addRow(row);
        }

        return tableModel;
    }
}
