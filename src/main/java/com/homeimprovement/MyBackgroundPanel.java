package com.homeimprovement;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

public class MyBackgroundPanel extends JPanel {

    private static final long serialVersionUID = -4647310868286988256L;
    private final String CLASSNAME = "MyBackgrouundPanel";
    String[] areas = new String[]{"", "ATTIC", "BACKYARD", "BATH1", "BATH2", "BDRM1", "BDRM2", "BDRM3", "BDRM4",
        "FRONTYARD", "GARAGE", "HALLWAY", "KITCHEN", "LIVING ROOM", "ROOF"};
    String[] items = new String[]{"", "ANTENNA", "CHIMNEY", "CLOSET", "DISHWASHER", "DOOR", "DRYER", "FAN",
        "FENCE", "FLOOR", "FRIG", "GUTTERS", "INTERNET", "LIGHTS", "MIRROR",
        "MISC", "OUTLETS", "OVEN", "PLANTS", "SEWAGE LINE", "SHOWER/TUB", "SINK", "TILE",
        "TOILET", "TV", "WALLS", "WASHER", "WINDOWS"};
    private BufferedImage img;
    private BufferedImage scaled;
    MySQLConnect myDatabase;
    private final String BACKGROUND_PIC = "/images/house2024.gif";
    private final String HOMEIMPROVEMENT_DATABASE = "houseexpenses";

    public MyBackgroundPanel(MySQLConnect mySQLDatabase) {
        this.myDatabase = mySQLDatabase;
        setupPanel();
    }

    public static void main(String[] args) {
        /* 
    // TODO Auto-generated method stub
		 String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/home_improvement";
		  String userid = "rbrewer"; 
		  String password = "Great2BeAliveN2022#"; 
		// final String DATABASE_NAME = "home_improvement";

         
        MySQLConnect myDatabase = new MySQLConnect(jdbcUrl, userid, password);
        MyBackgroundPanel backgroundPanel = new MyBackgroundPanel(myDatabase);
        JFrame frame = new JFrame("BackGroundPanel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(backgroundPanel);
        frame.setVisible(true);


         */

    }

    private void setupPanel() {
        setLayout((LayoutManager) null);

        // 2. From an InputStream (e.g., resources inside a JAR)
// Load background image using classloader from classpath
        try (InputStream is = MyBackgroundPanel.class.getResourceAsStream(BACKGROUND_PIC)) {
            if (is != null) {
                BufferedImage imageFromStream = ImageIO.read(is);
                setBackground(imageFromStream);
            } else {
                System.err.println("Could not find background image on classpath: house2024.gif");
                JOptionPane.showMessageDialog(this, "Could not find background image on classpath: house2024.gif", "Error", JOptionPane.ERROR_MESSAGE);

                System.exit(0);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Page Load Fault: Error reading background image", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        JLabel lblHomeImprovementRecords = new JLabel("Home Improvement Records");
        lblHomeImprovementRecords.setHorizontalAlignment(0);
        lblHomeImprovementRecords.setBounds(120, 11, 360, 29);
        lblHomeImprovementRecords.setFont(new Font("Tahoma", 1, 24));
        add(lblHomeImprovementRecords);

        JLabel lblDate = new JLabel("DATE");
        lblDate.setBounds(10, 63, 70, 25);
        lblDate.setFont(new Font("Tahoma", 1, 16));
        lblDate.setForeground(Color.RED);
        add(lblDate);

        JLabel lblArea = new JLabel("AREA");
        lblArea.setBounds(150, 63, 70, 25);
        lblArea.setFont(new Font("Tahoma", 1, 16));
        lblArea.setForeground(Color.BLUE);
        add(lblArea);

        JLabel lblItems = new JLabel("ITEMS");
        lblItems.setBounds(287, 63, 70, 25);
        lblItems.setFont(new Font("Tahoma", 1, 16));
        lblItems.setForeground(Color.RED);
        add(lblItems);

        JLabel lblCost = new JLabel("COST");
        lblCost.setBounds(417, 63, 70, 25);
        lblCost.setFont(new Font("Tahoma", 1, 16));
        lblCost.setForeground(Color.BLUE);
        add(lblCost);

        JLabel lblReceipt = new JLabel("RECEIPT");
        lblReceipt.setBounds(507, 65, 80, 21);
        lblReceipt.setFont(new Font("Tahoma", 1, 16));
        lblReceipt.setForeground(Color.RED);
        add(lblReceipt);

        final JDateChooser dateChooserDay = new JDateChooser();
        dateChooserDay.setBounds(10, 88, 105, 31);
        Date date = new Date();
        dateChooserDay.setDate(date);
        dateChooserDay.setDateFormatString("yyyy-MM-dd");
        dateChooserDay.setBackground(Color.RED);
        add((Component) dateChooserDay);

        final JComboBox<Object> comboBoxAREA = new JComboBox<>();
        comboBoxAREA.setBounds(130, 88, 105, 31);
        comboBoxAREA.setModel(new DefaultComboBoxModel<>(this.areas));
        comboBoxAREA.setEditable(false);
        add(comboBoxAREA);

        final JComboBox<Object> comboBoxITEMS = new JComboBox<>();
        comboBoxITEMS.setBounds(260, 88, 105, 31);
        comboBoxITEMS.setModel(new DefaultComboBoxModel<>(this.items));
        comboBoxITEMS.setEditable(false);
        add(comboBoxITEMS);

        JButton btnReceipt = new JButton("ADD RECEIPT");
        btnReceipt.setBounds(490, 88, 133, 31);
        add(btnReceipt);

        final JTextField textFieldCost = new JTextField();
        textFieldCost.setBounds(390, 88, 86, 31);
        textFieldCost.setColumns(10);
        add(textFieldCost);

        final JLabel lblFilename = new JLabel("filename");
        lblFilename.setBounds(507, 118, 105, 25);
        lblFilename.setForeground(Color.YELLOW);
        lblFilename.setFont(new Font("Tahoma", 1, 12));
        add(lblFilename);

        JLabel lblAddInfo = new JLabel("ADDITIONAL INFORMATION");
        lblAddInfo.setBounds(10, 205, 250, 35);
        lblAddInfo.setFont(new Font("Tahoma", 1, 16));
        lblAddInfo.setForeground(Color.BLUE);
        add(lblAddInfo);

        final JCheckBox chckbxValue = new JCheckBox("Value+");
        chckbxValue.setBounds(266, 211, 97, 23);
        chckbxValue.setFont(new Font("Tahoma", 1, 16));
        chckbxValue.setSelected(false);
        chckbxValue.setBackground(Color.WHITE);
        add(chckbxValue);

        final JTextArea textAreaInfo = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        textAreaInfo.setBorder(border);
        textAreaInfo.setOpaque(false);
        textAreaInfo.setWrapStyleWord(true);
        textAreaInfo.setFont(new Font("Arial", 1, 13));
        textAreaInfo.setToolTipText("Type additional Information here");
        textAreaInfo.setBounds(10, 238, 500, 60);
        add(textAreaInfo);

        JButton btnAccept = new JButton("ACCEPT");
        btnAccept.setBounds(261, 309, 89, 31);
        btnAccept.setFont(new Font("Tahoma", 1, 11));
        add(btnAccept);

        final JFrame frame = new JFrame();

        btnReceipt.addActionListener((ActionEvent e) -> {
            File file = null;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(2);
            int option = fileChooser.showOpenDialog(frame);
            if (option == 0) {
                file = fileChooser.getSelectedFile();

                String filepath = file.getPath();
                System.out.println("Before " + filepath);
                String newFilePath = filepath.replaceAll("\\\\", "\\\\\\\\");

                System.out.println("After " + newFilePath);
                lblFilename.setText(newFilePath);
            }
        });

        btnAccept.addActionListener((ActionEvent e) -> {
            Boolean checked;
            if (!HomeMainGui.getDatabaseStatus()) {
                String message = CLASSNAME + ": Database Not Connected. No Data Entered";
                JOptionPane.showMessageDialog(null, message, "Input Error", 0);
                return;
            }
            Date selectedDate = dateChooserDay.getDate();
            String pattern = "yyyy-MM-dd";
            DateFormat formatter = new SimpleDateFormat(pattern);
            String date1 = formatter.format(selectedDate);
            if (chckbxValue.isSelected()) {
                checked = true;
            } else {
                checked = false;
            }
            String query = "INSERT INTO houseexpenses (DATE, AREA, ITEMS, COST, FILENAME, INFO, VALUE)\nVALUES ('" + date1 + "', '" + comboBoxAREA.getSelectedItem().toString() + "' ,'" + comboBoxITEMS.getSelectedItem().toString() + "', " + textFieldCost.getText().toString() + ", '" + lblFilename.getText() + "', '" + textAreaInfo.getText() + "', " + checked + ")";
            try {
                if (MyBackgroundPanel.this.myDatabase.insertData(query)) {
                    JOptionPane.showMessageDialog(null, "Data Sent To Server");
                    comboBoxAREA.setSelectedIndex(0);
                    comboBoxITEMS.setSelectedIndex(0);
                    textFieldCost.setText("");
                    lblFilename.setText("filename");
                    textAreaInfo.setText("");
                } else {
                    String message = "Data was not written to file. \n Check for the following: \n1} Internet Connection is present. \n2) All fields are populated with data. \n3) The cost.csv file is close.";

                    JOptionPane.showMessageDialog(null, message, "Input Error", 0);
                }

            } catch (NumberFormatException e1) {

                String message = "Data was not written to file. \n Check for proper COST field input format (ex 124.55) ";

                JOptionPane.showMessageDialog(null, message, "Input Error", 0);
            } catch (HeadlessException e1) {

                e1.printStackTrace();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return (this.img == null) ? super.getPreferredSize() : new Dimension(this.img.getWidth(), this.img.getHeight());
    }

    public void setBackground(BufferedImage value) {
        if (value != this.img) {
            this.img = value;
            repaint();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (getWidth() > this.img.getWidth() || getHeight() > this.img.getHeight()) {
            this.scaled = getScaledInstanceToFill(this.img, getSize());
        } else {
            this.scaled = this.img;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.scaled != null) {
            int x = (getWidth() - this.scaled.getWidth()) / 2;
            int y = (getHeight() - this.scaled.getHeight()) / 2;
            g.drawImage(this.scaled, x, y, this);
        }
    }

    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {
        double scaleFactor = getScaleFactorToFill(img, size);

        return getScaledInstance(img, scaleFactor);
    }

    public static double getScaleFactorToFill(BufferedImage img, Dimension size) {
        double dScale = 1.0D;

        if (img != null) {

            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);

            dScale = Math.max(dScaleHeight, dScaleWidth);
        }

        return dScale;
    }

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {
        double dScale = iTargetSize / iMasterSize;

        return dScale;
    }

    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {
        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
    }

    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {
        BufferedImage imgScale = img;

        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);
        if (dScaleFactor <= 1.0D) {
            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
        } else {
            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
        }

        return imgScale;
    }

    protected static BufferedImage getScaledDownInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
        int type = (img.getTransparency() == 1)
                ? 1 : 2;

        BufferedImage ret = img;
        if (targetHeight > 0 || targetWidth > 0) {
            int w;
            int h;
            if (higherQuality) {

                w = img.getWidth();
                h = img.getHeight();
            } else {

                w = targetWidth;
                h = targetHeight;
            }

            do {
                if (higherQuality && w > targetWidth) {
                    w /= 2;
                    if (w < targetWidth) {
                        w = targetWidth;
                    }
                }

                if (higherQuality && h > targetHeight) {
                    h /= 2;
                    if (h < targetHeight) {
                        h = targetHeight;
                    }
                }

                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();

                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }

    protected static BufferedImage getScaledUpInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
        int w, h, type = 2;

        BufferedImage ret = img;

        if (higherQuality) {

            w = img.getWidth();
            h = img.getHeight();
        } else {

            w = targetWidth;
            h = targetHeight;
        }

        while (true) {
            if (higherQuality && w < targetWidth) {
                w *= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h < targetHeight) {
                h *= 2;
                if (h > targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
            tmp = null;

            if (w == targetWidth && h == targetHeight) {
                return ret;
            }
        }
    }
}


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\main_screen\MyBackgroundPanel.class
 * Java compiler version: 21
 * JD-Core Version:       1.1.3
 */
