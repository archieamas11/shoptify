/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MARITIME 02
 */
public class search {

    public static void searchResult(JTable a, JTextField b) {
        DefaultTableModel model = (DefaultTableModel) a.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        a.setRowSorter(sorter);

        switch (b.getText()) {
            case "Search":
                sorter.setRowFilter(null);
                break;
            case "":
                sorter.setRowFilter(RowFilter.regexFilter(b.getText()));
                break;
            default:
                sorter.setRowFilter(RowFilter.regexFilter(b.getText()));
                break;
        }
    }
}
