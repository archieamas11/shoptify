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

    public static void searchResult(JTable table, JTextField searchField) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        String searchText = searchField.getText();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(searchText);
        sorter.setRowFilter(rowFilter);
    }
}
