package ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    private static final int DURATION = 3000; // Duration in milliseconds (3 seconds)

    public SplashScreen() {
        // Load the splash image
        JLabel splashLabel = new JLabel(new ImageIcon("./data/splash_image.jpeg"));
        add(splashLabel, BorderLayout.CENTER);

        // Set the splash screen size to match the image size
        setSize(splashLabel.getPreferredSize());

        // Center the splash screen on the screen
        setLocationRelativeTo(null);
    }

    // Display the splash screen for the specified duration
    public void showSplash() {
        setVisible(true);
        try {
            Thread.sleep(DURATION); // Wait for the splash screen duration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false); // Hide the splash screen after the duration
    }
}
