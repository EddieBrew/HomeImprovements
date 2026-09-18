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
        frame.getContentPane().setLayout(null);

        JLabel pageName = new JLabel("About");
        pageName.setFont(new Font("Tahoma", Font.BOLD, 22));
        pageName.setBounds(10, 11, 157, 30);
        frame.getContentPane().add(pageName);

        JLabel title = new JLabel("<html>HomeImprovementsNRepairs Version 3.5<br>"
                + "@ March 2025; Created with Eclipse<br><br>"
                + "Version 3.5 update:<br>"
                + "1) Created ICON to indicate whether database was connected.<br>"
                + "2) Allow queries using file data when database is not available</html>");
        title.setFont(new Font("Tahoma", Font.BOLD, 12));
        title.setBounds(10, 52, 481, 118);
        frame.getContentPane().add(title);

        JLabel description = new JLabel("<html>HomeImprovementsNRepairs tracks maintenance and home improvement "
                + "repairs at 5917 Marlin Cir. The app stores information in a file and a MySQL database.<br>"
                + "Users can query any room using different parameters and display the results in a table.</html>");
        description.setFont(new Font("Tahoma", Font.PLAIN, 12));
        description.setBounds(10, 214, 459, 79);
        frame.getContentPane().add(description);

        JLabel versionDate = new JLabel("Version Date: March 2025");
        versionDate.setFont(new Font("Tahoma", Font.BOLD, 11));
        versionDate.setBounds(10, 293, 189, 24);
        frame.getContentPane().add(versionDate);
    }
}


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\main_screen\About.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */