/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Ellipse2D;
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

    public static void displayImage(JLabel displayPhoto, String file_path, int height, int width) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(file_path));
            Image scaledImage = bufferedImage.getScaledInstance(height, width, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            if (file_path != null && !file_path.isEmpty()) {
                displayPhoto.setIcon(imageIcon);
            } else {
                JOptionPane.showMessageDialog(null, "No Image found!");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void displayCircularImage(JLabel displayPhoto, String getImageFromDatabase, int height, int width) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(getImageFromDatabase));

            // Create a BufferedImage with TYPE_INT_ARGB to support transparency
            BufferedImage circleImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circleImage.createGraphics();

            // Enable anti-aliasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set background color to transparent
            circleImage = g2.getDeviceConfiguration().createCompatibleImage(height, width, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = circleImage.createGraphics();

            // Create a circle
            Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, height, width);
            g2.setClip(ellipse);

            // Draw the original image onto the circle
            g2.drawImage(bufferedImage, 0, 0, height, width, null);
            g2.dispose();

            // Scale the circle image to fit the label
            Image scaledImage = circleImage.getScaledInstance(height, width, Image.SCALE_SMOOTH);

            // Set the scaled image as the icon for the label
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
