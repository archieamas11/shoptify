/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Buyer;

/**
 *
 * @author MARITIME 02
 */
public class productIDs {

    private static int productID;

    public static int getProductID() {
        return productID;
    }

    public static void setProductID(int id) {
        productID = id;
    }

    public static void logout() {
        productID = -1;
    }
}
