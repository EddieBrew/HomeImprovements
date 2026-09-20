package com.homeimprovement;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InputStreamImageApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputStream Image Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel();

        // 1. Get the current class loader and open the resource as an InputStream
        // Note: The forward slash "/" looks at the root of your 'src' or resources folder
        try (InputStream is = InputStreamImageApp.class.getResourceAsStream("/images/house2024.gif")) {
            
            if (is == null) {
                throw new IOException("Could not find the image file. Check your resource folder path.");
            }

            // 2. Read the InputStream into a BufferedImage using ImageIO
            BufferedImage img = ImageIO.read(is);

            // 3. Convert the BufferedImage into an ImageIcon and attach to a JLabel
            ImageIcon imageIcon = new ImageIcon(img);
            JLabel label = new JLabel(imageIcon);
            
            panel.add(label);

        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


