/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UXmethods;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JComponent;

/**
 *
 * @author MARITIME 02
 */
public class RoundBorders {

    public static void setArcStyle(JComponent component, int arcSize) {
        component.putClientProperty(FlatClientProperties.STYLE, "arc: " + arcSize);
    }
}
