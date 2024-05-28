package accounts;

import com.formdev.flatlaf.FlatLightLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Load {

    public static void main(String[] args) {
        try {

            // Set FlatLaf as the look and feel
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Launch the splash screen and main application window
            SwingUtilities.invokeLater(() -> {
                splash_screen splashScreen = new splash_screen();
                splashScreen.setVisible(true);
                frontPage mainPage = new frontPage();
                mainPage.setVisible(false);

                try {
                    // Simulate progress with a Swing Timer
                    javax.swing.Timer timer = new javax.swing.Timer(60, e -> {
                        int progress = splashScreen.jProgressBar2.getValue();
                        progress++;
                        splashScreen.jLabel2.setText(progress + "%");
                        splashScreen.jProgressBar2.setValue(progress);

                        // Once progress reaches 100%, hide the splash screen and show the main window
                        if (progress == 100) {
                            splashScreen.setVisible(false);
                            splashScreen.hide();
                            mainPage.setVisible(true);
                        }
                    });
                    timer.start();
                } catch (Exception e) {
                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, e);
                }
            });

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
