/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Buyer;

import javax.swing.ImageIcon;

/**
 *
 * @author ArchieQT
 */
public class products {

    private ImageIcon imageIcon;
    private String name;
    private int price;

    public products(ImageIcon imageIcon, String name, int price) {
        this.imageIcon = imageIcon;
        this.name = name;
        this.price = price;
    }

    // Getters for imageIcon, name, and price
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
