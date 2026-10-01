package com.homeimprovement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import org.jfree.data.json.impl.JSONArray;
import org.jfree.data.json.impl.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeMainGui {

    private static final long serialVersionUID = -8044048618173986424L;
    private final String CLASSNAME = "HomeMainGui";
    private static String username;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu myMenu;
    private JMenuItem queryWork;
    private JMenuItem logout;
    private JMenuItem download;
    private JMenuItem YearToYearComparison;
    private final String HOMEIMPROVEMENT_DATABASE = "houseexpenses";

    private JMenuItem upload;

    private JMenuItem about;

   // private JMenuItem filePath;

    private JMenuItem reformat;

    private JMenuItem jsonToDatabase;

    private JMenuItem reconnectToDatabase;

    private JMenuItem currentMonth;
    private JMenuItem yearToYearComparison;

    public static Boolean getDatabaseStatus() {
        return databaseStatus;
    }
//Saving this file
    private MySQLConnect mySQLDatabase;
    private static final String inputFile = "cost.csv";
    private final String filename = "mysqlsignonstuff.txt";
    public static final int ROWS = 9;
    public static final int COLS = 12;
    private static boolean maxLimitFlag = false;
    private static Boolean databaseStatus = false;
    private JLabel lblDatabaseStatus;
    private MyBackgroundPanel myBackgroundPanel;

    public HomeMainGui(String username, char[] password) {
        HomeMainGui.username = username;
        initialize();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SQLSignonCredentCorrect();
            if (HomeMainGui.databaseStatus) {
                HomeMainGui.this.lblDatabaseStatus.setText("Database Connected");
                HomeMainGui.this.lblDatabaseStatus.setForeground(Color.green);
            } else {
                HomeMainGui.this.lblDatabaseStatus.setText("Database Unavailable");
                HomeMainGui.this.lblDatabaseStatus.setForeground(Color.red);
            }
        
    }

    class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 3) {
                HomeMainGui.this.reformat.setVisible(false);
            } else if (evt.getClickCount() == 2) {
                HomeMainGui.this.reformat.setVisible(true);
                System.out.println("double-click");
            }
        }
    }

    private void initialize() {
        this.frame = new JFrame();
        this.myBackgroundPanel = new MyBackgroundPanel(this.mySQLDatabase);
        this.frame.setContentPane(this.myBackgroundPanel);

        this.menuBar = new JMenuBar();
        this.menuBar.setLayout(new BorderLayout());
        this.menuBar.setFont(new Font("Segoe UI", 1, 12));
        this.menuBar.setBackground(Color.WHITE);

        this.myMenu = new JMenu("MENU");
        this.myMenu.setMnemonic(0);
        this.myMenu.getAccessibleContext().setAccessibleDescription("Get My Shit");
        this.myMenu.setBackground(new Color(50, 205, 50));
        this.menuBar.add(this.myMenu, "West");

        this.lblDatabaseStatus = new JLabel("NOT CONNECTED");
        this.lblDatabaseStatus.setBounds(156, 16, 159, 15);
        this.lblDatabaseStatus.setFont(new Font("Verdana", 1, 14));

        this.lblDatabaseStatus.setDisplayedMnemonic(65);

        this.menuBar.add(this.lblDatabaseStatus, "East");

        this.download = new JMenuItem("Download From Server");
        this.queryWork = new JMenuItem("Perform Queries");
        this.currentMonth = new JMenuItem("Current Month's Expenses");
        this.upload = new JMenuItem("Copy File Data To Database");
        this.logout = new JMenuItem("Logout");
        this.about = new JMenuItem("About");
        this.reformat = new JMenuItem("Reformat CSV File");
        this.yearToYearComparison = new JMenuItem("Year To Year Comparison"); 
        this.reformat.setVisible(false);

        this.jsonToDatabase = new JMenuItem("Copy JSON Data To Database");
        this.reconnectToDatabase = new JMenuItem("Reconnect To Database");

        this.myMenu.add(this.reformat);
        this.myMenu.add(this.currentMonth);

        this.myMenu.add(this.jsonToDatabase);
        this.myMenu.add(this.queryWork);
        this.myMenu.add(this.upload);
        this.myMenu.add(this.upload);
        this.myMenu.add(this.yearToYearComparison);
        this.myMenu.add(this.reconnectToDatabase);
        this.myMenu.add(this.about);
        this.myMenu.add(this.logout);
        this.jsonToDatabase.setVisible(false);

        this.myMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (HomeMainGui.this.jsonToDatabase.isVisible()) {
                    HomeMainGui.this.jsonToDatabase.setVisible(false);
                } else {
                    HomeMainGui.this.jsonToDatabase.setVisible(true);
                }
            }
        });

        this.jsonToDatabase.addActionListener((ActionEvent e) -> {
            if (!HomeMainGui.getDatabaseStatus()) {
                String message = "Database Not Connected. JSON To Database Operation Not Performed";
                JOptionPane.showMessageDialog(null, message, "Input Error", 0);

                return;
            }

            JFrame frame1 = new JFrame();
            String theMessage = " Do You Want To UpDate The Database with JSON info? If so, all previous info in the database will be refreshed";
            int result = JOptionPane.showConfirmDialog(frame1, theMessage, "alert", 0);
            if (result == 0) {
                if (HomeMainGui.this.populateDatabaseUsingJSONData("homeData.json")) {
                    JOptionPane.showMessageDialog(null, "SUCCESS: JSON Data Downloaded to Database ");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: JSON Data Did Not Downloaded to File ");
                }
            }
        });



        this.yearToYearComparison .addActionListener((ActionEvent e) -> {
           
            if (HomeMainGui.databaseStatus) {  
                 displayYearlyTotalfromDatabase();
                   System.out.println("YearlyCostChart window2 created successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Database Not Connected. Yearly Comparison Not Performed", "Input Error", 0);   
            }
            

           
        });
        this.about.addActionListener((ActionEvent e) -> {
            new About();
        });

        this.currentMonth.addActionListener((ActionEvent e) -> {
            if (HomeMainGui.databaseStatus) {
                getMonthlyExpensesFromDatabase(true);
            } else {
                getMonthlyExpensesFromFile(false);
            }
        });

        this.download.addActionListener((ActionEvent e) -> {
            if (HomeMainGui.databaseStatus) {
                downloadFromServerToFile();
            }
        });

        this.logout.addActionListener((ActionEvent e) -> {
            if (!HomeMainGui.databaseStatus) {
                JOptionPane.showMessageDialog(null,
                        "Database Not Connected. No Data Downlaoded to Google Drive. GoodBye");
                System.exit(0);
            }

            JFrame frame1 = new JFrame();
            String theMessage = " Do You Want To Quit The Application?";

            int result = JOptionPane.showConfirmDialog(frame1, theMessage, "alert", 0);
            if (result == 0) {
                downloadFromServerToFile();
                copyFileToGoogleDrive();
                System.exit(0);
                new Login_Sys();
            }
        });

        this.queryWork.addActionListener((ActionEvent e) -> {
            new QueryingWindow("rbrewer", mySQLDatabase);
        });

        this.upload.addActionListener((ActionEvent e) -> {
            if (!HomeMainGui.getDatabaseStatus()) {
                String message = "No Database Connected. File is not Uploaded to Database";
                JOptionPane.showMessageDialog(null, message, "Input Error", 0);

                return;
            }

            Thread databaseThread = new Thread(() -> {
                if (refreshDatabase()) {
                    JOptionPane.showMessageDialog(null, "MYSQL database updated");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR:::MYSQL database was not updated");
                }
            }, "Database Thread");

            System.out.println(String.valueOf(databaseThread.getName()) + " has statrted");
            databaseThread.start();
        });

        this.reformat.addActionListener((ActionEvent e) -> {
            List<HomeData> list = HomeMainGui.getDataFromFile();
            HomeMainGui.replaceDataInFile(list, "cost.csv");
            JOptionPane.showMessageDialog(null, "Data reformatted in  cost.csv file");
        });

        this.reconnectToDatabase.addActionListener((ActionEvent e) -> {
            HomeMainGui.this.mySQLDatabase = null;
            HomeMainGui.this.SQLSignonCredentCorrect();
            if (HomeMainGui.databaseStatus) {
                HomeMainGui.this.lblDatabaseStatus.setText("Database Connected");
                HomeMainGui.this.lblDatabaseStatus.setForeground(Color.green);
            } else {
                HomeMainGui.this.lblDatabaseStatus.setText("Database Unavailable");
                HomeMainGui.this.lblDatabaseStatus.setForeground(Color.red);
            }
        });

        this.frame.setJMenuBar(this.menuBar);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        displayMarlinExpensesBarChart();
    }

    private void copyFileToGoogleDrive() {
        InputStream in = null;
        OutputStream out = null;
        File source = new File("cost.csv");
        File dest = new File("M:\\My Drive\\Marlin Info\\cost.csv");

        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e1) {

            JOptionPane.showMessageDialog(null, "ERROR: File Copy Not Successful To Google Drive ");
        } finally {

            try {
                in.close();
                if (out == null) {
                    JOptionPane.showMessageDialog(null, "ERROR: Data Not copied to outfile");
                } else {
                    out.close();
                }

            } catch (IOException e2) {

                JOptionPane.showMessageDialog(null, "ERROR: File Copy Not Closed ");
            }
        }
    }

    protected boolean refreshDatabase() {
        if (this.mySQLDatabase.refreshDatabase(getDataFromFile())) {
            return true;
        }
        return false;
    }

    public void downloadFromServerToFile() {
        JFrame frame = new JFrame();
        String theMessage = " Download From Server? File Data Will Be Overwritten. Continue?";
        int result = JOptionPane.showConfirmDialog(frame, theMessage, "alert", 0);
        if (result == 0) {

            String query = "SELECT * FROM houseexpenses";
            this.mySQLDatabase.getQuery(query);
            List<HomeData> myList = this.mySQLDatabase.getList();
            Collections.sort(myList, new SortHomeDataInDescendingOrderByDate());
            if (replaceDataInFile(myList, "cost.csv")) {
                JOptionPane.showMessageDialog(null, "SUCCESS: Data Downloaded to File ");

                if (createJSONFile(myList)) {
                    JOptionPane.showMessageDialog(null, "SUCCESS: JSON File created ");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: Downloading JSON Data ");
                }

            } else {

                JOptionPane.showMessageDialog(null, "ERROR: Downloading Data ");
            }
            this.mySQLDatabase.clearList();
        }
    }

    public boolean createJSONFile(List<HomeData> data) {
        if (data.isEmpty()) {
            return false;
        }
        // Creates a json file with HomeData data
        try (FileWriter file = new FileWriter("homeData.json")) {
            file.write("{ \n");
            String str = " \"homeData\" ";
            file.write(str + ":[ \n");
            for (int i = 0; i < data.size(); i++) {
                ObjectMapper Obj = new ObjectMapper();
                String jsonStr = Obj.writeValueAsString(data.get(i));
                file.write(jsonStr + "\n");
            }
            file.write("] \n" + " }");
        } catch (IOException e) {
            // TODO Auto-generated catch bljock
            e.printStackTrace();
        }
        return true;

    }

    public Boolean populateDatabaseUsingJSONData(String filename) {
        Boolean isDatabaseUpdated = false;
        JSONParser parser = new JSONParser();

        ArrayList<HomeData> databaseEntries = new ArrayList<>();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
            JSONArray homeDataArray = (JSONArray) jsonObject.get("homeData");
            for (int i = 0; i < homeDataArray.size(); i++) {
                JSONObject jsonObjectRow = (JSONObject) homeDataArray.get(i);

                databaseEntries.add(new HomeData(
                        (String) jsonObjectRow.get("date"),
                        (String) jsonObjectRow.get("area"),
                        (String) jsonObjectRow.get("item"),
                        (Double) jsonObjectRow.get("cost"),
                        (String) jsonObjectRow.get("receiptFilename"),
                        (String) jsonObjectRow.get("info"),
                        (Boolean) jsonObjectRow.get("isValue")));
            }
            Collections.sort(databaseEntries, new SortHomeDataInDescendingOrderByDate());
            if (this.mySQLDatabase.refreshDatabase(databaseEntries)) {
                isDatabaseUpdated = true;
            }
        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return isDatabaseUpdated;
    }

    public void getMonthlyExpensesFromDatabase(Boolean showTable) {
        Date date = new Date();
        try {
            double MONTHLY_MAX = 250.0D;

            String result = "SELECT * FROM houseexpenses WHERE  DATE >= '" + getFirstDayOfMonth(date)
                    + "' AND DATE <= '" + getLastDayOfMonth(date) + "'";
            this.mySQLDatabase.getDateRangeResults(result);

            List<HomeData> dateRangeList = this.mySQLDatabase.getList();
            Collections.sort(dateRangeList, new SortHomeDataInDescendingOrderByDate());
            double currentBalance = computeTotalCost(dateRangeList);

            if (showTable) {
                String stitle = "Monthly Query for " + getMonthOfDate() + ". Total Cost = $"
                        + Double.toString(currentBalance);
            }

            if (currentBalance > MONTHLY_MAX && !maxLimitFlag) {
                maxLimitFlag = true;
                playMaxLimitSound(currentBalance, MONTHLY_MAX);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "ERROR: Unable To obtain current months total expense. Check database connection ");
        }

        this.mySQLDatabase.clearList();
    }

    public void getMonthlyExpensesFromFile(Boolean showTable) {
        Date date = new Date();
        List<HomeData> dateRangeList = getDataFromFile(getFirstDayOfMonth(date), getLastDayOfMonth(date));
        Collections.sort(dateRangeList, new SortHomeDataInDescendingOrderByDate());
        double currentBalance = computeTotalCost(dateRangeList);
        double MONTHLY_MAX = 250.0D;

        if (showTable) {
            String stitle = "Monthly Query for " + getMonthOfDate() + ". Total Cost = $"
                    + Double.toString(currentBalance);
        }

        if (currentBalance > MONTHLY_MAX && !maxLimitFlag) {

            maxLimitFlag = true;
            playMaxLimitSound(currentBalance, MONTHLY_MAX);
        }
    }

    public static void playMaxLimitSound(double currentBalance, double maxLimit) {
        try {
            File musicpath = new File("cash_register.wav");
            if (musicpath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                String theMessage = "Your monthly expenditures have exceed your set $"
                        + String.format("%.2f", new Object[]{maxLimit}) + "\n by $"
                        + String.format("%.2f", new Object[]{currentBalance - maxLimit});
                JOptionPane.showMessageDialog(null, theMessage, "ALERT", 0);
            } else {

                String theMessage1 = "Audio File Not Found";
                JOptionPane.showMessageDialog(null, theMessage1, "alert", 0);
            }

        } catch (HeadlessException | IOException | LineUnavailableException | UnsupportedAudioFileException exception) {
        }
    }

    public static String getFirstDayOfMonth(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(5, 1);
        Date dddd = calendar.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        return sdf1.format(dddd);
    }

    public static String getLastDayOfMonth(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(5, calendar.getActualMaximum(5));
        Date dddd = calendar.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        return sdf1.format(dddd);
    }

    public static String getMonthOfDate() {
        LocalDate date = LocalDate.now();
        return date.getMonth().toString();
    }

    private void displayMarlinExpensesBarChart() {
        List<HomeData> myList;
        String query = "SELECT * FROM  home_improvement.houseexpenses";

        if (databaseStatus) {
            this.mySQLDatabase.getQuery(query);
            myList = this.mySQLDatabase.getList();
        } else {
            myList = getDataFromFile();
        }

        double[][] monthlyTotals = new double[9][12];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 12; col++) {
                monthlyTotals[row][col] = 0.0D;
            }
        }

        List<HomeData> list2020 = getListForYear(myList, 2020);
        getMonthlyTotalForTheYear(monthlyTotals, list2020, 0);

        List<HomeData> list2021 = getListForYear(myList, 2021);
        getMonthlyTotalForTheYear(monthlyTotals, list2021, 1);

        List<HomeData> list2022 = getListForYear(myList, 2022);
        getMonthlyTotalForTheYear(monthlyTotals, list2022, 2);

        List<HomeData> list2023 = getListForYear(myList, 2023);
        getMonthlyTotalForTheYear(monthlyTotals, list2023, 3);

        List<HomeData> list2024 = getListForYear(myList, 2024);
        getMonthlyTotalForTheYear(monthlyTotals, list2024, 4);

        List<HomeData> list2025 = getListForYear(myList, 2025);
        getMonthlyTotalForTheYear(monthlyTotals, list2025, 5);

        List<HomeData> list2026 = getListForYear(myList, 2026);
        getMonthlyTotalForTheYear(monthlyTotals, list2026, 6);

        List<HomeData> list2027 = getListForYear(myList, 2027);
        getMonthlyTotalForTheYear(monthlyTotals, list2027, 7);

        List<HomeData> list2028 = getListForYear(myList, 2028);
        getMonthlyTotalForTheYear(monthlyTotals, list2028, 8);

        new MyBarChart("Marlin's Monthly Expenses", monthlyTotals);
       
       if (databaseStatus) {
        this.mySQLDatabase.clearList();
    }
}

private void displayYearlyTotalfromDatabase(){

        List<HomeData> myList;
        String query = "SELECT * FROM  home_improvement.houseexpenses";

        if (databaseStatus) {
            this.mySQLDatabase.getQuery(query);
            myList = this.mySQLDatabase.getList();

        } else {
            myList = getDataFromFile();
        }

        double[] yearlyTotals = new double[9];
        int[] years = new int[9];

        for (int i = 0; i < 9; i++) {
            int year = 2020 + i;
            years[i] = year;

            List<HomeData> listForYear = getListForYear(myList, year);
            //yearlyTotals[i] = computeTotalCost(listForYear);
            yearlyTotals[i] = getCostForYear(listForYear, year);
        }

        new YearlyCostChart(years, yearlyTotals);

        if (databaseStatus) {
            this.mySQLDatabase.clearList();
        }
    }

 private double getCostForYear(List<HomeData> myList, int year) {
        int dateSelect = 3;
        double totalCost = 0.0D;
        Iterator<HomeData> it = myList.iterator();
        while (it.hasNext()) {
            totalCost += ((HomeData) it.next()).getCost();
            }
             return totalCost;
        }
       
    private List<HomeData> getListForYear(List<HomeData> myList, int year) {
        int dateSelect = 3;
        List<HomeData> list = new ArrayList<>();
        Iterator<HomeData> it = myList.iterator();
        while (it.hasNext()) {
            HomeData data = it.next();
            int listYear = convertDateStringToInt(data.getDate(), dateSelect);
            if (listYear == year) {
                list.add(data);
            }
        }
        return list;
    }

    public static void getMonthlyTotalForTheYear(double[][] monthlyTotals, List<HomeData> list, int row) {
        int dateSelect = 2;

        for (int i = 0; i < list.size(); i++) {
            switch (convertDateStringToInt(((HomeData) list.get(i)).getDate(), dateSelect)) {
                case 1:
                    monthlyTotals[row][0] = monthlyTotals[row][0]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 2:
                    monthlyTotals[row][1] = monthlyTotals[row][1]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 3:
                    monthlyTotals[row][2] = monthlyTotals[row][2]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 4:
                    monthlyTotals[row][3] = monthlyTotals[row][3]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 5:
                    monthlyTotals[row][4] = monthlyTotals[row][4]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 6:
                    monthlyTotals[row][5] = monthlyTotals[row][5]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 7:
                    monthlyTotals[row][6] = monthlyTotals[row][6]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 8:
                    monthlyTotals[row][7] = monthlyTotals[row][7]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 9:
                    monthlyTotals[row][8] = monthlyTotals[row][8]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 10:
                    monthlyTotals[row][9] = monthlyTotals[row][9]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 11:
                    monthlyTotals[row][10] = monthlyTotals[row][10]
                            + ((HomeData) list.get(i)).getCost();
                    break;
                case 12:
                    monthlyTotals[row][11] = monthlyTotals[row][11]
                            + ((HomeData) list.get(i)).getCost();
                    break;
            }
        }
    }

    public static int convertDateStringToInt(String date, int dateSelect) {
        String delimStr = "-";

        String[] words = date.split(delimStr);
        int intDate = 0;
        switch (dateSelect) {

            case 1 ->
                intDate = Integer.parseInt(words[1]) * 100 + Integer.parseInt(words[2])
                        + Integer.parseInt(words[0]) * 10000;
            case 2 ->
                intDate = Integer.parseInt(words[1]);
            case 3 ->
                intDate = Integer.parseInt(words[0]);
        }

        return intDate;
    }

    private void SQLSignonCredentCorrect() {

        boolean isFound;
        final String DELIMITER = "%";
        String credentials = getCredentialsFromFile();
        if (credentials == null) {
            JOptionPane.showMessageDialog(null, "MYSQLConnect: Database is Not Connected.");
            databaseStatus = false;
            return;
        }

        String myDatastuff[] = credentials.split(DELIMITER);

        if (myDatastuff.length < 3) {
            JOptionPane.showMessageDialog(null, "MYSQLConnect: Database is Not Connected.");
            databaseStatus = false;
            return;
        }

        this.mySQLDatabase = new MySQLConnect(myDatastuff[0], myDatastuff[1], myDatastuff[2]);
        if (this.mySQLDatabase.isConnected()) {
            JOptionPane.showMessageDialog(null, "MYSQLConnect: Database is Connected.");
            databaseStatus = true;
        } else {
            JOptionPane.showMessageDialog(null, "MYSQLConnect: Database is Not Connected.");
            databaseStatus = false;
        }
    }

    private String getCredentialsFromFile() {

        InputStream inputStream = Login_Sys.class.getClassLoader().getResourceAsStream(filename);
        final int myMagicNumber = 36;
        String allData = null;
        int count = 1;
        BufferedReader bufferedReader = null;

        if (inputStream == null) {
            JOptionPane.showMessageDialog(null, "HomeMainGui: Credentials can not be found.");
            // System.err.println("Configuration file config.txt not found in resources!");
            return null;
        }

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            try {
                String data;
                while ((data = bufferedReader.readLine()) != null) {
                    if (count == myMagicNumber) {
                        allData = data;
                        return allData;
                    }
                    count++;
                }
                if (count < myMagicNumber) { // file does not contain sign-on credential info
                    JOptionPane.showMessageDialog(null, "HomeMainGui: Credentials can not be found.");
                }
            } catch (IOException e) {

                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "HomeMainGui: HomeMainGUI: Can not read  from file ");
            }
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {

                JOptionPane.showMessageDialog(null, "HomeMainGui :Error Closing The File" + e);
                e.printStackTrace();
            }
        }

        return null;
    }

    public static class SortHomeDataInAscendingOrderByDate
            implements Comparator<HomeData> {

        public int compare(HomeData a, HomeData b) {
            int dateSelect = 1;

            return HomeMainGui.convertDateStringToInt(a.getDate(), dateSelect)
                    - HomeMainGui.convertDateStringToInt(b.getDate(), dateSelect);
        }
    }

    public static class SortHomeDataInDescendingOrderByDate
            implements Comparator<HomeData> {

        public int compare(HomeData a, HomeData b) {
            int dateSelect = 1;
            return HomeMainGui.convertDateStringToInt(b.getDate(), dateSelect)
                    - HomeMainGui.convertDateStringToInt(a.getDate(), dateSelect);
        }
    }

    public static List<HomeData> getDataFromFile() {
        BufferedReader fileReader = null;
        String str = "";
        List<HomeData> myList = new ArrayList<>();

        try {
            InputStream resource = HomeMainGui.class.getClassLoader()
                    .getResourceAsStream("resources/cost.csv");
            fileReader = resource == null
                    ? new BufferedReader(new FileReader(inputFile))
                    : new BufferedReader(new InputStreamReader(resource));

            str = fileReader.readLine();
            while ((str = fileReader.readLine()) != null) {
                HomeData data = new HomeData(str);
                myList.add(data);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Cannot find cost.csv file");
            e.printStackTrace();
        } finally {
            try {
                if (fileReader == null) {
                    return null;
                }
                fileReader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error Closing The File" + e);
            }
        }

        if (!myList.isEmpty()) {

            Collections.sort(myList, new SortHomeDataInDescendingOrderByDate());

            return myList;
        }
        return null;
    }

    public static List<HomeData> getDataFromFile(String firstDay, String lastDay) {
        System.out.println();

        BufferedReader fileReader = null;
        String str = "";
        List<HomeData> myList = new ArrayList<>();

        try {
            fileReader = new BufferedReader(new FileReader("cost.csv"));

            str = fileReader.readLine();
            while ((str = fileReader.readLine()) != null) {
                HomeData data = new HomeData(str);

                if (convertDateStringToInt(data.getDate(), 1) >= convertDateStringToInt(firstDay, 1)
                        && convertDateStringToInt(data.getDate(), 1) <= convertDateStringToInt(lastDay, 1)) {
                    myList.add(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Cannot find cost.csv file");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error Closing The File" + e);
            }
        }

        if (!myList.isEmpty()) {

            Collections.sort(myList, new SortHomeDataInDescendingOrderByDate());

            return myList;
        }
        return null;
    }

    public static Boolean placeInFile(HomeData item) {
        Boolean isWrittenToFile = true;
        BufferedWriter bw = null;
        Boolean createFileHeaders = true;
        final String COMMA_DELIMITER = ",";
        final String NEW_LINE_SEPARATOR = "\n";
        try {
            File file = new File("cost.csv");

            if (!file.exists()) {
                file.createNewFile();
                createFileHeaders = false;
            }
            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);

            if (!createFileHeaders) {
                bw.write("DATE");
                bw.write(",");
                bw.write("AREA");
                bw.write(",");
                bw.write("ITEMS");
                bw.write(",");
                bw.write("COST");
                bw.write(",");
                bw.write("RECEIPT FILE NAME");
                bw.write(",");
                bw.write("INFO");
                bw.write(",");
                bw.write("VALUE ADDED");
                bw.write("\n");
            }

            bw.write(item.getDate());
            bw.write(",");
            bw.write(item.getArea());
            bw.write(",");
            bw.write(item.getItem());
            bw.write(",");
            bw.write(item.getCost().toString());
            bw.write(",");
            bw.write(item.getReceiptFilename());
            bw.write(",");
            bw.write(item.getInfo());
            bw.write(",");
            bw.write(item.getIsValue().toString());
            bw.write("\n");

        } catch (IOException ioe) {
            isWrittenToFile = false;
            JOptionPane.showMessageDialog(null, "Error Opening The File ");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error Closing The File ");
            }
        }
        return isWrittenToFile;
    }

    public static Boolean replaceDataInFile(List<HomeData> item, String filename) {
        Boolean isWriteSuccess = false;
        BufferedWriter bw = null;

        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        try {
            File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile();
            }

            if (filename.equalsIgnoreCase("cost.csv")) {
                FileWriter fw = new FileWriter(file, false);
                bw = new BufferedWriter(fw);
                bw.write("DATE");
                bw.write(",");
                bw.write("AREA");
                bw.write(",");
                bw.write("ITEMS");
                bw.write(",");
                bw.write("COST");
                bw.write(",");
                bw.write("RECEIPT FILE NAME");
                bw.write(",");
                bw.write("INFO");
                bw.write(",");
                bw.write("VALUE ADDED");
                bw.write("\n");
            } else {

                FileWriter fw = new FileWriter(file, true);
                bw = new BufferedWriter(fw);
            }

            for (int i = 0; i < item.size(); i++) {
                bw.write(((HomeData) item.get(i)).getDate());
                bw.write(",");
                bw.write(((HomeData) item.get(i)).getArea());
                bw.write(",");
                bw.write(((HomeData) item.get(i)).getItem());
                bw.write(",");
                bw.write(((HomeData) item.get(i)).getCost().toString());
                bw.write(",");
                bw.write(((HomeData) item.get(i)).getReceiptFilename());
                bw.write(",");
                bw.write(((HomeData) item.get(i)).getInfo());
                bw.write(",");
                bw.write(((HomeData) item.get(i)).getIsValue().toString());
                bw.write("\n");
            }
            isWriteSuccess = true;
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,
                    "Data was not written to file. \nVerify the cost.csv file is closed");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error Closing The File " + e);
            }
        }
        return isWriteSuccess;
    }

    public static String reformatDateString(String date) {
        String newDate = null, DELIMITER = "/";
        String[] oldDate = date.split(DELIMITER);
        String month = oldDate[0];
        String day = oldDate[1];

        if (Integer.parseInt(oldDate[0]) < 10) {
            month = "0" + oldDate[0];
        } else {
            month = oldDate[0];
        }
        if (Integer.parseInt(oldDate[1]) < 10) {
            day = "0" + oldDate[1];
        } else {
            day = oldDate[1];
        }

        newDate = String.valueOf(oldDate[2]) + "-" + oldDate[0] + "-" + oldDate[1];
        return newDate;
    }

    public static double computeTotalCost(List<HomeData> list) {
        double count = 0.0D;
        for (HomeData myList : list) {
            count += myList.getCost();
        }

        return Math.round(count * 100.0D) / 100.0D;
    }

    public static void main(String[] args) {
        /*
         * EventQueue.invokeLater(() -> {
         * try {
         * 
         * char[] p = {'l', 'u', 'i', 's', 't', 'a', 'm', '1', '9', '5', '9'};
         * HomeMainGui window = new HomeMainGui("rbrewer", p);
         * window.frame.setVisible(true);
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         * });
         */
    }
}

/*
 * Location:
 * C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\
 * homeImprovementsNRepairs.jar!\main_screen\HomeMainGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version: 1.1.3
 */
