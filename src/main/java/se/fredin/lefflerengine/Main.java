package se.fredin.lefflerengine;

import se.fredin.lefflerengine.screen.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        var window = new JFrame("Leffler Engine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        var gameScreen = new GamePanel(16, 3, 16, 12, Color.BLACK);
        window.add(gameScreen);

        // Causes window to be sized to fit preferred size of panel
        window.pack();

        window.setLocationRelativeTo(null); // Center the frame
        window.setVisible(true);

        gameScreen.startGameThread();
    }

}
