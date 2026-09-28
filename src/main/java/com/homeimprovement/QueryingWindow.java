package com.homeimprovement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class QueryingWindow {

    private JFrame qFrame;
    private JDateChooser dateChooserB;
    private JDateChooser dateChooserE;
    private JComboBox<Object> comboBoxArea;
    private JComboBox<Object> comboBoxItem1;
    private JComboBox<Object> comboBoxItem2;
    private JComboBox<Object> comboBoxItem3;
    private JCheckBox chckbxHomeImprovementDates;
    private JCheckBox chckBoxHomeImprovementItems;
    private JTextField textFieldCost;
    private String username;
    private MySQLConnect myDatabase;

    private final String HOMEIMPROVEMENT_DATABASE = "houseexpenses";

    public QueryingWindow(String username, MySQLConnect mySQLDatabase) {
        this.username = username;
        this.myDatabase = mySQLDatabase;
        initialize();
    }

    @SuppressWarnings("Convert2Lambda")
    private void initialize() {
        String[] areas = {"ATTIC", "BACKYARD", "BATH1", "BATH2", "BDRM1", "BDRM2", "BDRM3", "BDRM4",
            "FRONTYARD", "GARAGE", "HALLWAY", "KITCHEN", "LIVING ROOM", "ROOF"};

        String[] items = {"N/A", "ANTENNA", "CHIMNEY", "CLOSET", "DISHWASHER", "DOOR", "DRYER", "FAN",
            "FENCE", "FLOOR", "FRIG", "GUTTERS", "INTERNET", "LIGHTS", "MIRROR",
            "MISC", "OUTLETS", "OVEN", "PLANTS", "SEWAGE LINE", "SHOWER/TUB", "SINK", "TILE",
            "TOILET", "TV", "WALLS", "WASHER", "WINDOWS"};

        this.qFrame = new JFrame();
        this.qFrame.setBounds(100, 100, 600, 500);
        this.qFrame.setDefaultCloseOperation(2);
        this.qFrame.getContentPane().setBackground(Color.YELLOW);
        this.qFrame.getContentPane().setLayout((LayoutManager) null);
        this.qFrame.setTitle("QUERIES FOR REPAIRS/HOME IMPROVEMENTS");
        this.qFrame.setVisible(true);

        JLabel lblQueryPanel = new JLabel("QUERY  PANEL");
        lblQueryPanel.setHorizontalAlignment(0);
        lblQueryPanel.setFont(new Font("Tahoma", 1, 20));
        lblQueryPanel.setBounds(180, 0, 219, 32);
        this.qFrame.getContentPane().add(lblQueryPanel);

        JLabel lblArea = new JLabel("AREA");
        lblArea.setFont(new Font("Tahoma", 0, 11));
        lblArea.setBounds(10, 225, 105, 19);
        this.qFrame.getContentPane().add(lblArea);

        this.comboBoxArea = new JComboBox<Object>();
        this.comboBoxArea.setModel(new DefaultComboBoxModel<>(areas));
        this.comboBoxArea.setBounds(10, 245, 130, 31);
        this.qFrame.getContentPane().add(this.comboBoxArea);

        JLabel lblItem1 = new JLabel("ITEM1");
        lblItem1.setFont(new Font("Tahoma", 0, 11));
        lblItem1.setBounds(241, 225, 105, 19);
        this.qFrame.getContentPane().add(lblItem1);

        this.comboBoxItem1 = new JComboBox<Object>();
        this.comboBoxItem1.setModel(new DefaultComboBoxModel<>(new String[]{"N/A", "ANTENNA", "CHIMNEY", "CLOSET", "DISHWASHER", "DOOR", "DRYER", "FAN", "FENCE", "FLOOR", "FRIG", "GUTTERS", "INTERNET", "LIGHTS", "MIRROR", "MISC", "OUTLETS", "OVEN", "PLANTS", "SEWAGE LINE", "SHOWER/TUB", "SINK", "TILE", "TOILET", "TV", "WALLS", "WASHER", "WINDOWS"}));
        this.comboBoxItem1.setBounds(241, 245, 130, 31);
        this.qFrame.getContentPane().add(this.comboBoxItem1);
        this.comboBoxItem1.setSelectedItem("N/A");

        JLabel lblItem = new JLabel("ITEM2");
        lblItem.setFont(new Font("Tahoma", 0, 11));
        lblItem.setBounds(241, 287, 105, 19);
        this.qFrame.getContentPane().add(lblItem);

        this.comboBoxItem2 = new JComboBox<Object>();
        this.comboBoxItem2.setModel(new DefaultComboBoxModel<>(items));
        this.comboBoxItem2.setBounds(241, 307, 130, 31);
        this.qFrame.getContentPane().add(this.comboBoxItem2);
        this.comboBoxItem2.setSelectedItem("N/A");

        JLabel lblItem_1 = new JLabel("ITEM3");
        lblItem_1.setFont(new Font("Tahoma", 0, 11));
        lblItem_1.setBounds(241, 349, 105, 19);
        this.qFrame.getContentPane().add(lblItem_1);

        this.comboBoxItem3 = new JComboBox<Object>();
        this.comboBoxItem3.setModel(new DefaultComboBoxModel<>(items));
        this.comboBoxItem3.setBounds(241, 369, 130, 31);
        this.qFrame.getContentPane().add(this.comboBoxItem3);
        this.comboBoxItem3.setSelectedItem("N/A");

        JButton btnResultsDateRange = new JButton("RESULTS");
        btnResultsDateRange.setFont(new Font("Tahoma", 1, 11));
        btnResultsDateRange.setBounds(400, 81, 125, 29);
        this.qFrame.getContentPane().add(btnResultsDateRange);

        JLabel lblDateRange = new JLabel("DATE QUERIES");
        lblDateRange.setFont(new Font("Tahoma", 1, 15));
        lblDateRange.setBounds(154, 40, 181, 14);
        this.qFrame.getContentPane().add(lblDateRange);

        JLabel lblBeginningDate = new JLabel("BEGINNING DATE");
        lblBeginningDate.setBounds(10, 65, 143, 14);
        this.qFrame.getContentPane().add(lblBeginningDate);

        JLabel lblEndingDate = new JLabel("ENDING DATE");
        lblEndingDate.setBounds(241, 65, 125, 14);
        this.qFrame.getContentPane().add(lblEndingDate);

        this.dateChooserB = new JDateChooser();
        this.dateChooserB.setDateFormatString("yyyy-MM-dd");
        this.dateChooserB.setBounds(10, 81, 130, 31);
        this.qFrame.getContentPane().add((Component) this.dateChooserB);

        this.dateChooserE = new JDateChooser();
        this.dateChooserE.setDateFormatString("yyyy-MM-dd");
        this.dateChooserE.setBounds(241, 81, 130, 31);
        this.qFrame.getContentPane().add((Component) this.dateChooserE);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setBounds(10, 183, 500, 1);
        this.qFrame.getContentPane().add(horizontalStrut);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 182, 414, 2);
        this.qFrame.getContentPane().add(separator);

        this.chckbxHomeImprovementDates = new JCheckBox("HOME IMPROVEMENTS");
        this.chckbxHomeImprovementDates.setBounds(10, 130, 212, 46);
        this.chckbxHomeImprovementDates.setSelected(false);
        this.qFrame.getContentPane().add(this.chckbxHomeImprovementDates);

        this.chckBoxHomeImprovementItems = new JCheckBox("HOME IMPROVEMENTS");
        this.chckBoxHomeImprovementItems.setBounds(10, 361, 212, 46);
        this.chckBoxHomeImprovementItems.setSelected(false);
        this.qFrame.getContentPane().add(this.chckBoxHomeImprovementItems);

        JButton btnResultsItems = new JButton("RESULTS");
        btnResultsItems.setFont(new Font("Tahoma", 1, 11));
        btnResultsItems.setBounds(400, 307, 125, 29);
        this.qFrame.getContentPane().add(btnResultsItems);

        JLabel lblItemQueries = new JLabel("ITEM QUERIES");
        lblItemQueries.setFont(new Font("Tahoma", 1, 15));
        lblItemQueries.setBounds(154, 195, 149, 14);
        this.qFrame.getContentPane().add(lblItemQueries);

        JLabel lblCost = new JLabel("COST");
        lblCost.setFont(new Font("Tahoma", 0, 11));
        lblCost.setBounds(10, 291, 105, 14);
        this.qFrame.getContentPane().add(lblCost);

        this.textFieldCost = new JTextField();
        this.textFieldCost.setText("100");
        this.textFieldCost.setHorizontalAlignment(0);
        this.textFieldCost.setBounds(10, 307, 130, 30);
        this.qFrame.getContentPane().add(this.textFieldCost);
        this.textFieldCost.setColumns(10);

        this.textFieldCost.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9')
                        && c != '\b'
                        && c != '') {
                    e.consume();
                }
            }
        });

        JLabel lblClickForQuery = new JLabel("Click For Query Results");
        lblClickForQuery.setBounds(400, 340, 150, 14);
        this.qFrame.getContentPane().add(lblClickForQuery);

        JLabel label = new JLabel("Click For Query Results");
        label.setBounds(400, 109, 150, 14);
        this.qFrame.getContentPane().add(label);

        JLabel lblMaximumCostOf = new JLabel("Maximum Cost");
        lblMaximumCostOf.setBounds(10, 340, 105, 14);
        this.qFrame.getContentPane().add(lblMaximumCostOf);

        btnResultsDateRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (HomeMainGui.getDatabaseStatus()) {
                    QueryingWindow.this.getDateRangeFromDatabase();
                    /* 

                    //System.out.println("Test1");
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
                     */
                } else {
                    getDateRangeFromFile();
                    System.out.println("Test2");
                }
            }
        });

        btnResultsItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (HomeMainGui.getDatabaseStatus()) {
                    QueryingWindow.this.doItemsQueryFromDatabase();
                } else {
                    QueryingWindow.this.doItemsQueryFromFile();
                }

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
        createQueryTable(dateRangeList, title);
    }

    private void getDateRangeFromFile() {
        int dateSelect = 1;
        String pattern = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(pattern);

        Date bDate = this.dateChooserB.getDate();
        String dateStringB = formatter.format(bDate);
        Date eDate = this.dateChooserE.getDate();
        String dateStringE = formatter.format(eDate);

        List<HomeData> dateRangeList = getDateRange(HomeMainGui.convertDateStringToInt(dateStringB, dateSelect),
                HomeMainGui.convertDateStringToInt(dateStringE, dateSelect), this.chckbxHomeImprovementDates.isSelected());
        String title = "Date Range Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(dateRangeList));
        createQueryTable(dateRangeList, title);
    }

    private void doItemsQueryFromDatabase() {
        String whocares = "N/A";
        String result = null;
        if (this.comboBoxItem1.getSelectedItem().toString().compareTo(whocares) == 0
                && this.comboBoxItem2.getSelectedItem().toString().compareTo(whocares) == 0
                && this.comboBoxItem3.getSelectedItem().toString().compareTo(whocares) == 0) {

            result = "SELECT * FROM houseexpenses WHERE AREA  = '"
                    + this.comboBoxArea.getSelectedItem().toString() + "';";
        } else {

            result = "SELECT * FROM houseexpenses WHERE ITEMS IN ( '"
                    + this.comboBoxItem1.getSelectedItem().toString() + "' ,'"
                    + this.comboBoxItem2.getSelectedItem().toString() + "' ,'"
                    + this.comboBoxItem3.getSelectedItem().toString() + "' ) AND AREA = '"
                    + this.comboBoxArea.getSelectedItem().toString() + "';";
        }

        System.out.println(result);
        this.myDatabase.getQuery(result);
        List<HomeData> myList = this.myDatabase.getList();
        if (myList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your query returned 0 results");
        } else {
            String title = "Item Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(myList));
            createQueryTable(myList, title);
        }

        this.myDatabase.clearList();
    }

    private void doItemsQueryFromFile() {
        List<HomeData> myList = new ArrayList<>();
        String area = this.comboBoxArea.getSelectedItem().toString();
        String[] items = {this.comboBoxItem1.getSelectedItem().toString(), this.comboBoxItem2.getSelectedItem().toString(), this.comboBoxItem3.getSelectedItem().toString()};

        myList = getQueryItemsList(area, items, this.chckBoxHomeImprovementItems.isSelected());
        if (myList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your query returned 0 results");
        } else {
            String title = "Item Queries. Total Cost = $" + Double.toString(HomeMainGui.computeTotalCost(myList));
            createQueryTable(myList, title);
        }
    }

    public static List<HomeData> getDateRange(int bDate, int eDate, Boolean isSelected) {
        int dateSelect = 1;
        List<HomeData> myList = HomeMainGui.getDataFromFile();

        Iterator<HomeData> itr = myList.iterator();

        while (itr.hasNext()) {
            int date = HomeMainGui.convertDateStringToInt(((HomeData) itr.next()).getDate(), dateSelect);
            if (date < bDate || date > eDate) {
                itr.remove();
            }
        }

        itr = myList.iterator();
        if (isSelected) {
            while (itr.hasNext()) {
                boolean homeImprovementSelected = ((HomeData) itr.next()).getIsValue();

                if (!homeImprovementSelected) {
                    itr.remove();
                }
            }
        }

        return myList;
    }

    private List<HomeData> getQueryItemsList(String area, String[] items, Boolean isSelected) {
        List<HomeData> myList = HomeMainGui.getDataFromFile();
        String notApplicable = "N/A";
        Iterator<HomeData> itr = myList.iterator();

        while (itr.hasNext()) {
            String areaString = ((HomeData) itr.next()).getArea();
            if (!areaString.equals(area)) {
                itr.remove();
            }
        }

        if (items[0].equals(notApplicable) && items[1].equals(notApplicable) && items[2].equals(notApplicable)) {
            getQueryMaxCostLimit(myList, isSelected);
            return myList;
        }

        itr = myList.iterator();

        if (items[0].equals(notApplicable)) {
            while (itr.hasNext()) {
                String item1String = ((HomeData) itr.next()).getItem();
                if (!item1String.equals(items[1]) && !item1String.equals(items[2])) {
                    itr.remove();
                }
            }
        } else if (items[1].equals(notApplicable)) {
            while (itr.hasNext()) {
                String item1String = ((HomeData) itr.next()).getItem();
                if (!item1String.equals(items[0]) && !item1String.equals(items[2])) {
                    itr.remove();
                }
            }
        } else if (items[2].equals(notApplicable)) {
            while (itr.hasNext()) {
                String item1String = ((HomeData) itr.next()).getItem();
                if (!item1String.equals(items[0]) && !item1String.equals(items[1])) {
                    itr.remove();
                }
            }

        } else {

            while (itr.hasNext()) {
                String item1String = ((HomeData) itr.next()).getItem();
                if (!item1String.equals(items[0]) && !item1String.equals(items[1]) && !item1String.equals(items[2])) {
                    itr.remove();
                }
            }
        }

        getQueryMaxCostLimit(myList, isSelected);
        return myList;
    }

    public void getQueryMaxCostLimit(List<HomeData> myList, Boolean isSelected) {
        int cost = Integer.parseInt(this.textFieldCost.getText());

        Iterator<HomeData> itr = myList.iterator();
        //itr = myList.iterator();
        while (itr.hasNext()) {
            int value = (int) Math.round(((HomeData) itr.next()).getCost());
            if (value > cost) {
                itr.remove();
            }
        }

        itr = myList.iterator();
        if (isSelected) {
            while (itr.hasNext()) {
                boolean homeImprovementSelectedItems = ((HomeData) itr.next()).getIsValue();
                if (!homeImprovementSelectedItems) {
                    itr.remove();
                }
            }
        }
    }

    public void createQueryTable(List<HomeData> myList, String title) {
        MyQueryTable myQueryTable = new MyQueryTable(myList);//create Table
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
    }
}


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\query_screen\QueryingWindow.class
 * Java compiler version: 21
 * JD-Core Version:       1.1.3
 */
