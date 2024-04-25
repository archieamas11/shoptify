/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author MARITIME 02
 */
public class GetImage {

    public static void displayImage(JLabel displayPhoto, String getImageFromDatabase, int height, int width) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(getImageFromDatabase));
            Image scaledImage = bufferedImage.getScaledInstance(height, width, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            if (getImageFromDatabase != null && !getImageFromDatabase.isEmpty()) {
                displayPhoto.setIcon(imageIcon);
            } else {
                JOptionPane.showMessageDialog(null, "No Image found!");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
