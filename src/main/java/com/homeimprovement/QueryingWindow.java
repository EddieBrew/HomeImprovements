package com.homeimprovement;

import java.awt.BorderLayout;
 /*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;

/*     */ import javax.swing.Box;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JTextField;

/*     */ import com.toedter.calendar.JDateChooser;

/*     */
 /*     */ public class QueryingWindow /*     */ {

    /*     */ private JFrame qFrame;
    /*     */    private JDateChooser dateChooserB;
    /*     */    private JDateChooser dateChooserE;
    /*     */    private JComboBox<Object> comboBoxArea;
    /*     */    private JComboBox<Object> comboBoxItem1;
    /*     */    private JComboBox<Object> comboBoxItem2;
    /*     */    private JComboBox<Object> comboBoxItem3;
    /*     */    private JCheckBox chckbxHomeImprovementDates;
    /*     */    private JCheckBox chckBoxHomeImprovementItems;
    /*     */    private JTextField textFieldCost;
    /*     */    private String username;
    /*     */    private MySQLConnect myDatabase;  
                 

    /*  48 */    private final String HOMEIMPROVEMENT_DATABASE = "houseexpenses";

    /*     */ public QueryingWindow(String username, MySQLConnect mySQLDatabase) {
        /*  68 */ this.username = username;
        /*  69 */ this.myDatabase = mySQLDatabase;
        /*  70 */ initialize();
        /*     */    }

    /*     */@SuppressWarnings("Convert2Lambda")
 private void initialize() {
        /*  79 */ String[] areas = {"ATTIC", "BACKYARD", "BATH1", "BATH2", "BDRM1", "BDRM2", "BDRM3", "BDRM4",
            /*  80 */ "FRONTYARD", "GARAGE", "HALLWAY", "KITCHEN", "LIVING ROOM", "ROOF"};
        /*     */
 /*  82 */ String[] items = {"N/A", "ANTENNA", "CHIMNEY", "CLOSET", "DISHWASHER", "DOOR", "DRYER", "FAN",
            /*  83 */ "FENCE", "FLOOR", "FRIG", "GUTTERS", "INTERNET", "LIGHTS", "MIRROR",
            /*  84 */ "MISC", "OUTLETS", "OVEN", "PLANTS", "SEWAGE LINE", "SHOWER/TUB", "SINK", "TILE",
            /*  85 */ "TOILET", "TV", "WALLS", "WASHER", "WINDOWS"};
        /*     */
 /*  87 */ this.qFrame = new JFrame();
        /*  88 */ this.qFrame.setBounds(100, 100, 600, 500);
        /*  89 */ this.qFrame.setDefaultCloseOperation(2);
        /*  90 */ this.qFrame.getContentPane().setBackground(Color.YELLOW);
        /*  91 */ this.qFrame.getContentPane().setLayout((LayoutManager) null);
        /*  92 */ this.qFrame.setTitle("QUERIES FOR REPAIRS/HOME IMPROVEMENTS");
        /*  93 */ this.qFrame.setVisible(true);
        /*     */
 /*  95 */ JLabel lblQueryPanel = new JLabel("QUERY  PANEL");
        /*  96 */ lblQueryPanel.setHorizontalAlignment(0);
        /*  97 */ lblQueryPanel.setFont(new Font("Tahoma", 1, 20));
        /*  98 */ lblQueryPanel.setBounds(180, 0, 219, 32);
        /*  99 */ this.qFrame.getContentPane().add(lblQueryPanel);
        /*     */
 /* 101 */ JLabel lblArea = new JLabel("AREA");
        /* 102 */ lblArea.setFont(new Font("Tahoma", 0, 11));
        /* 103 */ lblArea.setBounds(10, 225, 105, 19);
        /* 104 */ this.qFrame.getContentPane().add(lblArea);
        /*     */
 /* 106 */ this.comboBoxArea = new JComboBox<Object>();
        /* 107 */ this.comboBoxArea.setModel(new DefaultComboBoxModel<>(areas));
        /* 108 */ this.comboBoxArea.setBounds(10, 245, 130, 31);
        /* 109 */ this.qFrame.getContentPane().add(this.comboBoxArea);
        /*     */
 /* 111 */ JLabel lblItem1 = new JLabel("ITEM1");
        /* 112 */ lblItem1.setFont(new Font("Tahoma", 0, 11));
        /* 113 */ lblItem1.setBounds(241, 225, 105, 19);
        /* 114 */ this.qFrame.getContentPane().add(lblItem1);
        /*     */
 /*     */
 /* 117 */ this.comboBoxItem1 = new JComboBox<Object>();
        /* 118 */ this.comboBoxItem1.setModel(new DefaultComboBoxModel<>(new String[]{"N/A", "ANTENNA", "CHIMNEY", "CLOSET", "DISHWASHER", "DOOR", "DRYER", "FAN", "FENCE", "FLOOR", "FRIG", "GUTTERS", "INTERNET", "LIGHTS", "MIRROR", "MISC", "OUTLETS", "OVEN", "PLANTS", "SEWAGE LINE", "SHOWER/TUB", "SINK", "TILE", "TOILET", "TV", "WALLS", "WASHER", "WINDOWS"}));
        /* 119 */ this.comboBoxItem1.setBounds(241, 245, 130, 31);
        /* 120 */ this.qFrame.getContentPane().add(this.comboBoxItem1);
        /* 121 */ this.comboBoxItem1.setSelectedItem("N/A");
        /*     */
 /* 123 */ JLabel lblItem = new JLabel("ITEM2");
        /* 124 */ lblItem.setFont(new Font("Tahoma", 0, 11));
        /* 125 */ lblItem.setBounds(241, 287, 105, 19);
        /* 126 */ this.qFrame.getContentPane().add(lblItem);
        /*     */
 /* 128 */ this.comboBoxItem2 = new JComboBox<Object>();
        /* 129 */ this.comboBoxItem2.setModel(new DefaultComboBoxModel<>(items));
        /* 130 */ this.comboBoxItem2.setBounds(241, 307, 130, 31);
        /* 131 */ this.qFrame.getContentPane().add(this.comboBoxItem2);
        /* 132 */ this.comboBoxItem2.setSelectedItem("N/A");
        /*     */
 /* 134 */ JLabel lblItem_1 = new JLabel("ITEM3");
        /* 135 */ lblItem_1.setFont(new Font("Tahoma", 0, 11));
        /* 136 */ lblItem_1.setBounds(241, 349, 105, 19);
        /* 137 */ this.qFrame.getContentPane().add(lblItem_1);
        /*     */
 /* 139 */ this.comboBoxItem3 = new JComboBox<Object>();
        /* 140 */ this.comboBoxItem3.setModel(new DefaultComboBoxModel<>(items));
        /* 141 */ this.comboBoxItem3.setBounds(241, 369, 130, 31);
        /* 142 */ this.qFrame.getContentPane().add(this.comboBoxItem3);
        /* 143 */ this.comboBoxItem3.setSelectedItem("N/A");
        /*     */
 /* 145 */ JButton btnResultsDateRange = new JButton("RESULTS");
        /* 146 */ btnResultsDateRange.setFont(new Font("Tahoma", 1, 11));
        /* 147 */ btnResultsDateRange.setBounds(400, 81, 125, 29);
        /* 148 */ this.qFrame.getContentPane().add(btnResultsDateRange);
        /*     */
 /* 150 */ JLabel lblDateRange = new JLabel("DATE QUERIES");
        /* 151 */ lblDateRange.setFont(new Font("Tahoma", 1, 15));
        /* 152 */ lblDateRange.setBounds(154, 40, 181, 14);
        /* 153 */ this.qFrame.getContentPane().add(lblDateRange);
        /*     */
 /* 155 */ JLabel lblBeginningDate = new JLabel("BEGINNING DATE");
        /* 156 */ lblBeginningDate.setBounds(10, 65, 143, 14);
        /* 157 */ this.qFrame.getContentPane().add(lblBeginningDate);
        /*     */
 /* 159 */ JLabel lblEndingDate = new JLabel("ENDING DATE");
        /* 160 */ lblEndingDate.setBounds(241, 65, 125, 14);
        /* 161 */ this.qFrame.getContentPane().add(lblEndingDate);
        /*     */
 /* 163 */ this.dateChooserB = new JDateChooser();
        /* 164 */ this.dateChooserB.setDateFormatString("yyyy-MM-dd");
        /* 165 */ this.dateChooserB.setBounds(10, 81, 130, 31);
        /* 166 */ this.qFrame.getContentPane().add((Component) this.dateChooserB);
        /*     */
 /* 168 */ this.dateChooserE = new JDateChooser();
        /* 169 */ this.dateChooserE.setDateFormatString("yyyy-MM-dd");
        /* 170 */ this.dateChooserE.setBounds(241, 81, 130, 31);
        /* 171 */ this.qFrame.getContentPane().add((Component) this.dateChooserE);
        /*     */
 /* 173 */ Component horizontalStrut = Box.createHorizontalStrut(20);
        /* 174 */ horizontalStrut.setBounds(10, 183, 500, 1);
        /* 175 */ this.qFrame.getContentPane().add(horizontalStrut);
        /*     */
 /* 177 */ JSeparator separator = new JSeparator();
        /* 178 */ separator.setBounds(10, 182, 414, 2);
        /* 179 */ this.qFrame.getContentPane().add(separator);
        /*     */
 /* 181 */ this.chckbxHomeImprovementDates = new JCheckBox("HOME IMPROVEMENTS");
        /* 182 */ this.chckbxHomeImprovementDates.setBounds(10, 130, 212, 46);
        /* 183 */ this.chckbxHomeImprovementDates.setSelected(false);
        /* 184 */ this.qFrame.getContentPane().add(this.chckbxHomeImprovementDates);
        /*     */
 /* 186 */ this.chckBoxHomeImprovementItems = new JCheckBox("HOME IMPROVEMENTS");
        /* 187 */ this.chckBoxHomeImprovementItems.setBounds(10, 361, 212, 46);
        /* 188 */ this.chckBoxHomeImprovementItems.setSelected(false);
        /* 189 */ this.qFrame.getContentPane().add(this.chckBoxHomeImprovementItems);
        /*     */
 /* 191 */ JButton btnResultsItems = new JButton("RESULTS");
        /* 192 */ btnResultsItems.setFont(new Font("Tahoma", 1, 11));
        /* 193 */ btnResultsItems.setBounds(400, 307, 125, 29);
        /* 194 */ this.qFrame.getContentPane().add(btnResultsItems);
        /*     */
 /* 196 */ JLabel lblItemQueries = new JLabel("ITEM QUERIES");
        /* 197 */ lblItemQueries.setFont(new Font("Tahoma", 1, 15));
        /* 198 */ lblItemQueries.setBounds(154, 195, 149, 14);
        /* 199 */ this.qFrame.getContentPane().add(lblItemQueries);
        /*     */
 /* 201 */ JLabel lblCost = new JLabel("COST");
        /* 202 */ lblCost.setFont(new Font("Tahoma", 0, 11));
        /* 203 */ lblCost.setBounds(10, 291, 105, 14);
        /* 204 */ this.qFrame.getContentPane().add(lblCost);
        /*     */
 /* 206 */ this.textFieldCost = new JTextField();
        /* 207 */ this.textFieldCost.setText("100");
        /* 208 */ this.textFieldCost.setHorizontalAlignment(0);
        /* 209 */ this.textFieldCost.setBounds(10, 307, 130, 30);
        /* 210 */ this.qFrame.getContentPane().add(this.textFieldCost);
        /* 211 */ this.textFieldCost.setColumns(10);
        /*     */
 /*     */
 /*     */
 /* 215 */ this.textFieldCost.addKeyListener(new KeyAdapter() {
            /*     */ public void keyTyped(KeyEvent e) {
                /* 217 */ char c = e.getKeyChar();
                /* 218 */ if ((c < '0' || c > '9')
                        && /* 219 */ c != '\b'
                        && /* 220 */ c != '') {
                    /* 221 */ e.consume();
                    /*     */                }
                /*     */            }
            /*     */        });
        /*     */
 /*     */
 /* 227 */ JLabel lblClickForQuery = new JLabel("Click For Query Results");
        /* 228 */ lblClickForQuery.setBounds(400, 340, 150, 14);
        /* 229 */ this.qFrame.getContentPane().add(lblClickForQuery);
        /*     */
 /* 231 */ JLabel label = new JLabel("Click For Query Results");
        /* 232 */ label.setBounds(400, 109, 150, 14);
        /* 233 */ this.qFrame.getContentPane().add(label);
        /*     */
 /* 235 */ JLabel lblMaximumCostOf = new JLabel("Maximum Cost");
        /* 236 */ lblMaximumCostOf.setBounds(10, 340, 105, 14);
        /* 237 */ this.qFrame.getContentPane().add(lblMaximumCostOf);
        /*     */
 /*     */
        btnResultsDateRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (HomeMainGui.getDatabaseStatus()) {
                    QueryingWindow.this.getDateRangeFromDatabase();
                    System.out.println("Test1");
                    List<HomeData> dateRangeList = QueryingWindow.this.myDatabase.getList();
                    Collections.sort(dateRangeList, (Comparator<? super HomeData>) new HomeMainGui.SortHomeDataInDescendingOrderByDate());
                    String title = "Date Range Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(dateRangeList));
                    MyQueryTable myQueryTable = new MyQueryTable(dateRangeList);//create Table
                    JScrollPane mScrollPane = new JScrollPane(myQueryTable.getTable());//place table in scroll pane
                    JPanel panel = new JPanel();//panel object
                    panel.setLayout(new BorderLayout());
                    panel.add(mScrollPane, BorderLayout.CENTER);
                    //place table in frame
                    JFrame myFrame = new JFrame();
                    myFrame.setResizable(true);
                    myFrame.setTitle(title);
                    myFrame.getContentPane().setLayout(new BorderLayout());
                    myFrame.setBounds(100, 400, 800, 400);
                    //myFrame.setSize(800,400);
                    myFrame.getContentPane().add(panel);
                    myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    myFrame.setVisible(true);
                    QueryingWindow.this.myDatabase.clearList();
                } else {
                    System.out.println("Test2");
                }
            }
        });
       

         btnResultsItems.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 /* 255 */ if (HomeMainGui.getDatabaseStatus()) {
                     /* 256 */ QueryingWindow.this.doItemsQueryFromDatabase();
                     /*     */                 } else {
                     /* 258 */ QueryingWindow.this.doItemsQueryFromFile();
                     /*     */                 }
                
             }
         });
     }


     private void getDateRangeFromDatabase() {
         String pattern = "yyyy-MM-dd";
         DateFormat formatter = new SimpleDateFormat(pattern);

         Date bDate = this.dateChooserB.getDate();
         String dateStringB = formatter.format(bDate);
         Date eDate = this.dateChooserE.getDate();
         String dateStringE = formatter.format(eDate);

         String result = "SELECT * FROM houseexpenses WHERE  DATE >= '" + dateStringB + "' AND DATE <= '" + dateStringE + "'";
         System.out.println(result);
         this.myDatabase.getDateRangeResults(result);

         List<HomeData> dateRangeList = this.myDatabase.getList();
         System.out.println("dataRangeList = " + dateRangeList.size());
         Collections.sort(dateRangeList, (Comparator<? super HomeData>) new HomeMainGui.SortHomeDataInDescendingOrderByDate());

         String title = "Date Range Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(dateRangeList));
        MyQueryTable  myQueryTable = new MyQueryTable(dateRangeList);//create Table
        JScrollPane mScrollPane = new JScrollPane(myQueryTable.getTable());//place table in scroll pane
        JPanel panel = new JPanel();//panel object
        panel.setLayout(new BorderLayout());
        panel.add(mScrollPane, BorderLayout.CENTER);

        //place table in frame
        JFrame myFrame = new JFrame();
        myFrame.setResizable(true);
        myFrame.setTitle(title);
        myFrame.getContentPane().setLayout(new BorderLayout());
        myFrame.setBounds(100, 400, 800, 400);
        //myFrame.setSize(800,400);
        myFrame.getContentPane().add(panel);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.setVisible(true);
         this.myDatabase.clearList();
     
     }

  private void getDateRangeFromFile() {
       /* 311 */ int dateSelect = 1;
        /* 312 */ String pattern = "yyyy-MM-dd";
        /* 313 */ DateFormat formatter = new SimpleDateFormat(pattern);
        /*     */
 /* 315 */ Date bDate = this.dateChooserB.getDate();
        /* 316 */ String dateStringB = formatter.format(bDate);
        /* 317 */ Date eDate = this.dateChooserE.getDate();
        /* 318 */ String dateStringE = formatter.format(eDate);
        /*     */
 /* 320 */ List<HomeData> dateRangeList = getDateRange(HomeMainGui.convertDateStringToInt(dateStringB, dateSelect),
                /* 321 */ HomeMainGui.convertDateStringToInt(dateStringE, dateSelect), Boolean.valueOf(this.chckbxHomeImprovementDates.isSelected()));
        /* 322 */ String title = "Date Range Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(dateRangeList));
 }

    /*     */ private void doItemsQueryFromDatabase() {
        /* 335 */ String whocares = "N/A";
        /* 336 */ String result = null;
        /* 337 */ if (this.comboBoxItem1.getSelectedItem().toString().compareTo(whocares) == 0
                && /* 338 */ this.comboBoxItem2.getSelectedItem().toString().compareTo(whocares) == 0
                && /* 339 */ this.comboBoxItem3.getSelectedItem().toString().compareTo(whocares) == 0) {
            /*     */
 /* 341 */ result = "SELECT * FROM houseexpenses WHERE AREA  = '"
                    + /* 342 */ this.comboBoxArea.getSelectedItem().toString() + "';";
            /*     */        } else {
            /*     */
 /* 345 */ result = "SELECT * FROM houseexpenses WHERE ITEMS IN ( '"
                    + /* 346 */ this.comboBoxItem1.getSelectedItem().toString() + "' ,'"
                    + /* 347 */ this.comboBoxItem2.getSelectedItem().toString() + "' ,'"
                    + /* 348 */ this.comboBoxItem3.getSelectedItem().toString() + "' ) AND AREA = '"
                    + /* 349 */ this.comboBoxArea.getSelectedItem().toString() + "';";
            /*     */        }
        /*     */
 /*     */
 /* 353 */ System.out.println(result);
        /* 354 */ this.myDatabase.getQuery(result);
        /* 355 */ List<HomeData> myList = this.myDatabase.getList();
        /* 356 */ if (myList.isEmpty()) {
            /* 357 */ JOptionPane.showMessageDialog(null, "Your query returned 0 results");
            /*     */        } else {
            /* 359 */ String title = "Item Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(myList));
            /*     */        }
        /*     */
 /*     */
 /* 363 */ this.myDatabase.clearList();
 }

    /*     */ private void doItemsQueryFromFile() {
        /* 375 */ List<HomeData> myList = new ArrayList<>();
        /* 376 */ String area = this.comboBoxArea.getSelectedItem().toString();
        /* 377 */ String[] items = {this.comboBoxItem1.getSelectedItem().toString(), this.comboBoxItem2.getSelectedItem().toString(), this.comboBoxItem3.getSelectedItem().toString()};
        /*     */
 /* 379 */ myList = getQueryItemsList(area, items, Boolean.valueOf(this.chckBoxHomeImprovementItems.isSelected()));
        /* 380 */ if (myList.isEmpty()) {
            /* 381 */ JOptionPane.showMessageDialog(null, "Your query returned 0 results");
            /*     */        } else {
            /* 383 */ String title = "Item Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(myList));
            /*     */        }
        /*     */    }

    /*     */ public static List<HomeData> getDateRange(int bDate, int eDate, Boolean isSelected) {
        /* 417 */ int dateSelect = 1;
        /* 418 */ List<HomeData> myList = HomeMainGui.getDataFromFile();
        /*     */
 /* 420 */ Iterator<HomeData> itr = myList.iterator();
        /*     */
 /* 422 */ while (itr.hasNext()) {
            int date = HomeMainGui.convertDateStringToInt(((HomeData) itr.next()).getDate(), dateSelect);
            /* 423 */ if (date < bDate || date > eDate) {
                /* 424 */ itr.remove();
                /*     */            }
        }
        /*     */
 /* 427 */ itr = myList.iterator();
        /* 428 */ if (isSelected) /*     */ {
            /* 430 */ while (itr.hasNext()) {
                /* 431 */ boolean homeImprovementSelected = ((HomeData) itr.next()).getIsValue();
                /*     */
 /* 433 */ if (!homeImprovementSelected) {
                    /* 434 */ itr.remove();
                    /*     */                }
                /*     */            }
            /*     */        }
        /*     */
 /* 439 */ return myList;
        /*     */    }

 /*     */ private List<HomeData> getQueryItemsList(String area, String[] items, Boolean isSelected) {
        /* 457 */ List<HomeData> myList = HomeMainGui.getDataFromFile();
        /* 458 */ String notApplicable = "N/A";
        /* 459 */ Iterator<HomeData> itr = myList.iterator();
        /*     */
 /* 461 */ while (itr.hasNext()) {
            /* 462 */ String areaString = ((HomeData) itr.next()).getArea();
            /* 463 */ if (!areaString.equals(area)) {
                /* 464 */ itr.remove();
                /*     */            }
            /*     */        }
        /*     */
 /* 468 */ if (items[0].equals(notApplicable) && items[1].equals(notApplicable) && items[2].equals(notApplicable)) {
            /* 469 */ getQueryMaxCostLimit(myList, isSelected);
            /* 470 */ return myList;
            /*     */        }
        /*     */
 /*     */
 /* 474 */ itr = myList.iterator();
        /*     */
 /* 476 */ if (items[0].equals(notApplicable)) {
            /* 477 */ while (itr.hasNext()) {
                /* 478 */ String item1String = ((HomeData) itr.next()).getItem();
                /* 479 */ if (!item1String.equals(items[1]) && !item1String.equals(items[2])) {
                    /* 480 */ itr.remove();
                    /*     */                }
                /*     */            }
            /* 483 */        } else if (items[1].equals(notApplicable)) {
            /* 484 */ while (itr.hasNext()) {
                /* 485 */ String item1String = ((HomeData) itr.next()).getItem();
                /* 486 */ if (!item1String.equals(items[0]) && !item1String.equals(items[2])) {
                    /* 487 */ itr.remove();
                    /*     */                }
                /*     */            }
            /* 490 */        } else if (items[2].equals(notApplicable)) {
            /* 491 */ while (itr.hasNext()) {
                /* 492 */ String item1String = ((HomeData) itr.next()).getItem();
                /* 493 */ if (!item1String.equals(items[0]) && !item1String.equals(items[1])) {
                    /* 494 */ itr.remove();
                    /*     */                }
                /*     */            }
            /*     */
 /*     */        } else {
            /*     */
 /* 500 */ while (itr.hasNext()) {
                /* 501 */ String item1String = ((HomeData) itr.next()).getItem();
                /* 502 */ if (!item1String.equals(items[0]) && !item1String.equals(items[1]) && !item1String.equals(items[2])) {
                    /* 503 */ itr.remove();
                    /*     */                }
                /*     */            }
            /*     */        }
        /*     */
 /* 508 */ getQueryMaxCostLimit(myList, isSelected);
        /* 509 */ return myList;
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */ public void getQueryMaxCostLimit(List<HomeData> myList, Boolean isSelected) {
        /* 515 */ int cost = Integer.parseInt(this.textFieldCost.getText());
        /*     */
 /* 517 */ Iterator<HomeData> itr = myList.iterator();
        /* 518 */ itr = myList.iterator();
        /* 519 */ while (itr.hasNext()) {
            /* 520 */ int value = (int) Math.round(((HomeData) itr.next()).getCost().doubleValue());
            /* 521 */ if (value > cost) {
                /* 522 */ itr.remove();
                /*     */            }
            /*     */        }
        /*     */
 /*     */
 /* 527 */ itr = myList.iterator();
        /* 528 */ if (isSelected) /* 529 */ {
            while (itr.hasNext()) {
                /* 530 */ boolean homeImprovementSelectedItems = ((HomeData) itr.next()).getIsValue().booleanValue();
                /* 531 */ if (!homeImprovementSelectedItems) /* 532 */ {
                    itr.remove();
                }
                /*     */            }
        }
        /*     */    }
    /*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\query_screen\QueryingWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
