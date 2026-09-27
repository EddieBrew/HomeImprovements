package com.homeimprovement;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class About {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            About window = new About();
            window.frame.setVisible(true);
        });
    }

    public About() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
        frame.getContentPane().setBackground(Color.PINK);
        frame.getContentPane().setForeground(Color.WHITE);
        frame.setBounds(100, 100, 517, 367);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("About");
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);

        JLabel pageName = new JLabel("About");
        pageName.setFont(new Font("Tahoma", Font.BOLD, 22));
        pageName.setBounds(10, 11, 157, 30);
        frame.getContentPane().add(pageName);

        JLabel title = new JLabel("<html>HomeImprovements: Version 1.0<br>"
                + "@ Oct 2026; Reworked HomeImprovementsNRepairs App for the VSCode platform<br><br>"
                + "Updates From HomeImprovementsNRepairs App:<br>"
                + "1) Reworked and Recompiled using VSCode platform.<br>"
                + "2) Cleaned up the code and improved performance.<br>"
                + "3) No changes were made to the UI layouts.</html>");
        title.setFont(new Font("Tahoma", Font.BOLD, 12));
        title.setBounds(10, 52, 481, 118);
        frame.getContentPane().add(title);

        JLabel description = new JLabel("<html>HomeImprovements tracks maintenance and home improvements"
                + "and repairs at 5917 Marlin Cir. The app stores input information, from users, in a file and a MySQL.<br>"
                + "database. Users can query any room using different parameters and display the results in a table.</html>");
        description.setFont(new Font("Tahoma", Font.PLAIN, 12));
        description.setBounds(10, 214, 459, 79);
        frame.getContentPane().add(description);

        JLabel versionDate = new JLabel("Version Date: October 2026");
        versionDate.setFont(new Font("Tahoma", Font.BOLD, 11));
        versionDate.setBounds(10, 293, 189, 24);
        frame.getContentPane().add(versionDate);
    }
}


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\main_screen\About.class
 * Java compiler version: 21 (52.0)
 *Maven version: 3.8.6
 * JD-Core Version:       1.1.3 
 */
