package com.homeimprovement;
 import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
import java.io.InputStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;

/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.Border;

/*     */ import com.toedter.calendar.JDateChooser;



public class MyBackgroundPanel extends JPanel {
    
private static final long serialVersionUID = -4647310868286988256L;
			private final String CLASSNAME = "MyBackgrouundPanel";
/*  54 */   String[] areas = new String[] { "", "ATTIC", "BACKYARD", "BATH1", "BATH2", "BDRM1", "BDRM2", "BDRM3", "BDRM4", 
/*  55 */       "FRONTYARD", "GARAGE", "HALLWAY", "KITCHEN", "LIVING ROOM", "ROOF" };
/*  56 */   String[] items = new String[] { "", "ANTENNA", "CHIMNEY", "CLOSET", "DISHWASHER", "DOOR", "DRYER", "FAN", 
/*  57 */       "FENCE", "FLOOR", "FRIG", "GUTTERS", "INTERNET", "LIGHTS", "MIRROR", 
/*  58 */       "MISC", "OUTLETS", "OVEN", "PLANTS", "SEWAGE LINE", "SHOWER/TUB", "SINK", "TILE", 
/*  59 */       "TOILET", "TV", "WALLS", "WASHER", "WINDOWS" };
/*     */   private BufferedImage img;
/*     */   private BufferedImage scaled;
/*     */   MySQLConnect myDatabase;
/*  63 */   private final String BACKGROUND_PIC = "resources/images/house2024.gif";
/*  64 */   private final String HOMEIMPROVEMENT_DATABASE = "houseexpenses";
/*     */   
/*     */   public MyBackgroundPanel(MySQLConnect mySQLDatabase) {
/*  67 */     this.myDatabase = mySQLDatabase;
/*  68 */     setupPanel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupPanel() {
/*  87 */     setLayout((LayoutManager)null);
/*     */     

	// 2. From an InputStream (e.g., resources inside a JAR)
// Load background image using classloader from classpath
    try (InputStream is = MyBackgroundPanel.class.getResourceAsStream("/resources/house2024.gif")) {
        if (is != null) {
            BufferedImage imageFromStream = ImageIO.read(is);
            setBackground(imageFromStream);
        } else {
            System.err.println("Could not find background image on classpath: /resources/house2024.gif");
            JOptionPane.showMessageDialog(this, "Page Load Fault: Can not find background image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (IOException e) {
        //e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Page Load Fault: Error reading background image", "Error", JOptionPane.ERROR_MESSAGE);
    }
	
/*     */     
/*  96 */     JLabel lblHomeImprovementRecords = new JLabel("Home Improvement Records");
/*  97 */     lblHomeImprovementRecords.setHorizontalAlignment(0);
/*  98 */     lblHomeImprovementRecords.setBounds(120, 11, 360, 29);
/*  99 */     lblHomeImprovementRecords.setFont(new Font("Tahoma", 1, 24));
/* 100 */     add(lblHomeImprovementRecords);
/*     */     
/* 102 */     JLabel lblDate = new JLabel("DATE");
/* 103 */     lblDate.setBounds(10, 63, 70, 25);
/* 104 */     lblDate.setFont(new Font("Tahoma", 1, 16));
/* 105 */     lblDate.setForeground(Color.RED);
/* 106 */     add(lblDate);
/*     */     
/* 108 */     JLabel lblArea = new JLabel("AREA");
/* 109 */     lblArea.setBounds(150, 63, 70, 25);
/* 110 */     lblArea.setFont(new Font("Tahoma", 1, 16));
/* 111 */     lblArea.setForeground(Color.BLUE);
/* 112 */     add(lblArea);
/*     */     
/* 114 */     JLabel lblItems = new JLabel("ITEMS");
/* 115 */     lblItems.setBounds(287, 63, 70, 25);
/* 116 */     lblItems.setFont(new Font("Tahoma", 1, 16));
/* 117 */     lblItems.setForeground(Color.RED);
/* 118 */     add(lblItems);
/*     */     
/* 120 */     JLabel lblCost = new JLabel("COST");
/* 121 */     lblCost.setBounds(417, 63, 70, 25);
/* 122 */     lblCost.setFont(new Font("Tahoma", 1, 16));
/* 123 */     lblCost.setForeground(Color.BLUE);
/* 124 */     add(lblCost);
/*     */     
/* 126 */     JLabel lblReceipt = new JLabel("RECEIPT");
/* 127 */     lblReceipt.setBounds(507, 65, 80, 21);
/* 128 */     lblReceipt.setFont(new Font("Tahoma", 1, 16));
/* 129 */     lblReceipt.setForeground(Color.RED);
/* 130 */     add(lblReceipt);
/*     */     
/* 132 */     final JDateChooser dateChooserDay = new JDateChooser();
/* 133 */     dateChooserDay.setBounds(10, 88, 105, 31);
/* 134 */     Date date = new Date();
/* 135 */     dateChooserDay.setDate(date);
/* 136 */     dateChooserDay.setDateFormatString("yyyy-MM-dd");
/* 137 */     dateChooserDay.setBackground(Color.RED);
/* 138 */     add((Component)dateChooserDay);
/*     */     
/* 140 */     final JComboBox<Object> comboBoxAREA = new JComboBox<>();
/* 141 */     comboBoxAREA.setBounds(130, 88, 105, 31);
/* 142 */     comboBoxAREA.setModel(new DefaultComboBoxModel<>(this.areas));
/* 143 */     comboBoxAREA.setEditable(false);
/* 144 */     add(comboBoxAREA);
/*     */     
/* 146 */     final JComboBox<Object> comboBoxITEMS = new JComboBox<>();
/* 147 */     comboBoxITEMS.setBounds(260, 88, 105, 31);
/* 148 */     comboBoxITEMS.setModel(new DefaultComboBoxModel<>(this.items));
/* 149 */     comboBoxITEMS.setEditable(false);
/* 150 */     add(comboBoxITEMS);
/*     */     
/* 152 */     JButton btnReceipt = new JButton("ADD RECEIPT");
/* 153 */     btnReceipt.setBounds(490, 88, 133, 31);
/* 154 */     add(btnReceipt);
/*     */     
/* 156 */     final JTextField textFieldCost = new JTextField();
/* 157 */     textFieldCost.setBounds(390, 88, 86, 31);
/* 158 */     textFieldCost.setColumns(10);
/* 159 */     add(textFieldCost);
/*     */     
/* 161 */     final JLabel lblFilename = new JLabel("filename");
/* 162 */     lblFilename.setBounds(507, 118, 105, 25);
/* 163 */     lblFilename.setForeground(Color.YELLOW);
/* 164 */     lblFilename.setFont(new Font("Tahoma", 1, 12));
/* 165 */     add(lblFilename);
/*     */     
/* 167 */     JLabel lblAddInfo = new JLabel("ADDITIONAL INFORMATION");
/* 168 */     lblAddInfo.setBounds(10, 205, 250, 35);
/* 169 */     lblAddInfo.setFont(new Font("Tahoma", 1, 16));
/* 170 */     lblAddInfo.setForeground(Color.BLUE);
/* 171 */     add(lblAddInfo);
/*     */     
/* 173 */     final JCheckBox chckbxValue = new JCheckBox("Value+");
/* 174 */     chckbxValue.setBounds(266, 211, 97, 23);
/* 175 */     chckbxValue.setFont(new Font("Tahoma", 1, 16));
/* 176 */     chckbxValue.setSelected(false);
/* 177 */     chckbxValue.setBackground(Color.WHITE);
/* 178 */     add(chckbxValue);
/*     */     
/* 180 */     final JTextArea textAreaInfo = new JTextArea();
/* 181 */     Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
/* 182 */     textAreaInfo.setBorder(border);
/* 183 */     textAreaInfo.setOpaque(false);
/* 184 */     textAreaInfo.setWrapStyleWord(true);
/* 185 */     textAreaInfo.setFont(new Font("Arial", 1, 13));
/* 186 */     textAreaInfo.setToolTipText("Type additional Information here");
/* 187 */     textAreaInfo.setBounds(10, 238, 500, 60);
/* 188 */     add(textAreaInfo);
/*     */     
/* 190 */     JButton btnAccept = new JButton("ACCEPT");
/* 191 */     btnAccept.setBounds(261, 309, 89, 31);
/* 192 */     btnAccept.setFont(new Font("Tahoma", 1, 11));
/* 193 */     add(btnAccept);
/*     */ 
/*     */     
/* 196 */     final JFrame frame = new JFrame();
/*     */     
/* 198 */     btnReceipt.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 201 */             File file = null;
/* 202 */             JFileChooser fileChooser = new JFileChooser();
/* 203 */             fileChooser.setFileSelectionMode(2);
/* 204 */             int option = fileChooser.showOpenDialog(frame);
/* 205 */             if (option == 0) {
/* 206 */               file = fileChooser.getSelectedFile();
/*     */ 
/*     */               
/* 209 */               String filepath = file.getPath();
/* 210 */               System.out.println("Before " + filepath);
/* 211 */               String newFilePath = filepath.replaceAll("\\\\", "\\\\\\\\");
/*     */ 
/*     */ 
/*     */               
/* 215 */               System.out.println("After " + newFilePath);
/* 216 */               lblFilename.setText(newFilePath);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 221 */     btnAccept.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*     */             Boolean checked;
/* 225 */             if (!HomeMainGui.getDatabaseStatus()) {
/* 226 */               String message = CLASSNAME + ": Database Not Connected. No Data Entered";
/* 227 */               JOptionPane.showMessageDialog(null, message, "Input Error", 0);
						return;
/*     */             } 
/*     */ 
/*     */             
/* 234 */             Date selectedDate = dateChooserDay.getDate();
/* 235 */             String pattern = "yyyy-MM-dd";
/* 236 */             DateFormat formatter = new SimpleDateFormat(pattern);
/* 237 */             String date = formatter.format(selectedDate);
/*     */             
/* 239 */             if (chckbxValue.isSelected()) {
/* 240 */               checked = Boolean.valueOf(true);
/*     */             } else {
/* 242 */               checked = Boolean.valueOf(false);
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 247 */             String query = "INSERT INTO houseexpenses (DATE, AREA, ITEMS, COST, FILENAME, INFO, VALUE)\nVALUES ('" + 
/*     */               
/* 249 */               date + "', '" + comboBoxAREA.getSelectedItem().toString() + "' ,'" + 
/* 250 */               comboBoxITEMS.getSelectedItem().toString() + "', " + 
/* 251 */               textFieldCost.getText().toString() + ", '" + lblFilename.getText() + "', '" + 
/* 252 */               textAreaInfo.getText() + "', " + checked + ")";
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 257 */               if (MyBackgroundPanel.this.myDatabase.insertData(query).booleanValue()) {
/* 258 */                 JOptionPane.showMessageDialog(null, "Data Sent To Server");
/* 259 */                 comboBoxAREA.setSelectedIndex(0);
/* 260 */                 comboBoxITEMS.setSelectedIndex(0);
/* 261 */                 textFieldCost.setText("");
/* 262 */                 lblFilename.setText("filename");
/* 263 */                 textAreaInfo.setText("");
/*     */               } else {
/* 265 */                 String message = "Data was not written to file. \n Check for the following: \n1} Internet Connection is present. \n2) All fields are populated with data. \n3) The cost.csv file is close.";
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 270 */                 JOptionPane.showMessageDialog(null, message, "Input Error", 0);
/*     */               }
/*     */             
/* 273 */             } catch (NumberFormatException e1) {
/*     */ 
/*     */               
/* 276 */               String message = "Data was not written to file. \n Check for proper COST field input format (ex 124.55) ";
/*     */               
/* 278 */               JOptionPane.showMessageDialog(null, message, "Input Error", 0);
/*     */             }
/* 280 */             catch (HeadlessException e1) {
/*     */               
/* 282 */               e1.printStackTrace();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }

/*     */   public Dimension getPreferredSize() {
/* 338 */     return (this.img == null) ? super.getPreferredSize() : new Dimension(this.img.getWidth(), this.img.getHeight());
/*     */   }
/*     */   
/*     */   public void setBackground(BufferedImage value) {
/* 342 */     if (value != this.img) {
/* 343 */       this.img = value;
/* 344 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 350 */     super.invalidate();
/* 351 */     if (getWidth() > this.img.getWidth() || getHeight() > this.img.getHeight()) {
/* 352 */       this.scaled = getScaledInstanceToFill(this.img, getSize());
/*     */     } else {
/* 354 */       this.scaled = this.img;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 360 */     super.paintComponent(g);
/* 361 */     if (this.scaled != null) {
/* 362 */       int x = (getWidth() - this.scaled.getWidth()) / 2;
/* 363 */       int y = (getHeight() - this.scaled.getHeight()) / 2;
/* 364 */       g.drawImage(this.scaled, x, y, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {
/* 370 */     double scaleFactor = getScaleFactorToFill(img, size);
/*     */     
/* 372 */     return getScaledInstance(img, scaleFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getScaleFactorToFill(BufferedImage img, Dimension size) {
/* 378 */     double dScale = 1.0D;
/*     */     
/* 380 */     if (img != null) {
/*     */       
/* 382 */       int imageWidth = img.getWidth();
/* 383 */       int imageHeight = img.getHeight();
/*     */       
/* 385 */       double dScaleWidth = getScaleFactor(imageWidth, size.width);
/* 386 */       double dScaleHeight = getScaleFactor(imageHeight, size.height);
/*     */       
/* 388 */       dScale = Math.max(dScaleHeight, dScaleWidth);
/*     */     } 
/*     */ 
/*     */     
/* 392 */     return dScale;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getScaleFactor(int iMasterSize, int iTargetSize) {
/* 398 */     double dScale = iTargetSize / iMasterSize;
/*     */     
/* 400 */     return dScale;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {
/* 405 */     return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {
/* 413 */     BufferedImage imgScale = img;
/*     */     
/* 415 */     int iImageWidth = (int)Math.round(img.getWidth() * dScaleFactor);
/* 416 */     int iImageHeight = (int)Math.round(img.getHeight() * dScaleFactor);
/* 417 */     if (dScaleFactor <= 1.0D) {
/* 418 */       imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
/*     */     } else {
/* 420 */       imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
/*     */     } 
/*     */     
/* 423 */     return imgScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static BufferedImage getScaledDownInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
/* 432 */     int type = (img.getTransparency() == 1) ? 
/* 433 */       1 : 2;
/*     */     
/* 435 */     BufferedImage ret = img;
/* 436 */     if (targetHeight > 0 || targetWidth > 0) {
/*     */       int w; int h;
/* 438 */       if (higherQuality) {
/*     */ 
/*     */ 
/*     */         
/* 442 */         w = img.getWidth();
/* 443 */         h = img.getHeight();
/*     */       }
/*     */       else {
/*     */         
/* 447 */         w = targetWidth;
/* 448 */         h = targetHeight;
/*     */       } 
/*     */       
/*     */       do {
/* 452 */         if (higherQuality && w > targetWidth) {
/* 453 */           w /= 2;
/* 454 */           if (w < targetWidth) {
/* 455 */             w = targetWidth;
/*     */           }
/*     */         } 
/*     */         
/* 459 */         if (higherQuality && h > targetHeight) {
/* 460 */           h /= 2;
/* 461 */           if (h < targetHeight) {
/* 462 */             h = targetHeight;
/*     */           }
/*     */         } 
/*     */         
/* 466 */         BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
/* 467 */         Graphics2D g2 = tmp.createGraphics();
/* 468 */         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
/* 469 */         g2.drawImage(ret, 0, 0, w, h, null);
/* 470 */         g2.dispose();
/*     */         
/* 472 */         ret = tmp;
/* 473 */       } while (w != targetWidth || h != targetHeight);
/*     */     } else {
/* 475 */       ret = new BufferedImage(1, 1, type);
/*     */     } 
/* 477 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static BufferedImage getScaledUpInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
/* 486 */     int w, h, type = 2;
/*     */     
/* 488 */     BufferedImage ret = img;
/*     */     
/* 490 */     if (higherQuality) {
/*     */ 
/*     */ 
/*     */       
/* 494 */       w = img.getWidth();
/* 495 */       h = img.getHeight();
/*     */     }
/*     */     else {
/*     */       
/* 499 */       w = targetWidth;
/* 500 */       h = targetHeight;
/*     */     } 
/*     */     
/*     */     while (true) {
/* 504 */       if (higherQuality && w < targetWidth) {
/* 505 */         w *= 2;
/* 506 */         if (w > targetWidth) {
/* 507 */           w = targetWidth;
/*     */         }
/*     */       } 
/*     */       
/* 511 */       if (higherQuality && h < targetHeight) {
/* 512 */         h *= 2;
/* 513 */         if (h > targetHeight) {
/* 514 */           h = targetHeight;
/*     */         }
/*     */       } 
/*     */       
/* 518 */       BufferedImage tmp = new BufferedImage(w, h, type);
/* 519 */       Graphics2D g2 = tmp.createGraphics();
/* 520 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
/* 521 */       g2.drawImage(ret, 0, 0, w, h, null);
/* 522 */       g2.dispose();
/*     */       
/* 524 */       ret = tmp;
/* 525 */       tmp = null;
/*     */       
/* 527 */       if (w == targetWidth && h == targetHeight)
/* 528 */         return ret; 
/*     */     } 
/*     */   }
}


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\main_screen\MyBackgroundPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */

