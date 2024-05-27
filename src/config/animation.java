/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author MARITIME 02
 */
public class animation {

    public static void customizeLabel(JLabel label) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Color originalColor = label.getForeground();

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(new Color(0, 158, 226));
                label.setText("<html><u>" + label.getText() + "</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                String labelText = label.getText();
                labelText = labelText.replaceAll("<html><u>", "");
                labelText = labelText.replaceAll("</u></html>", "");
                label.setText(labelText);
                label.setForeground(originalColor);
            }
        });
    }
}
