/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accounts;

/**
 *
 * @author MARITIME 02
 */
public class rememberMe {

    private static boolean rememberAccount;

    public static boolean getRememberAccountValue() {
        return rememberAccount;
    }

    public static void setRememberAccountValue(boolean isSelected) {
        rememberAccount = isSelected;
    }
}
