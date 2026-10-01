package com.homeimprovement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.text.DecimalFormat;





public class YearlyCostChart extends JFrame {

public YearlyCostChart(int[] years, double[] totalCosts) {
        super("Total Yearly Cost");

        // Build the dataset
        XYSeries series = new XYSeries("Total Cost");
        for (int i = 0; i < years.length; i++) {
            series.add(years[i], totalCosts[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Total Cost Per Year",   // title
                "Year",                  // x-axis label
                "Total Cost ($)",        // y-axis label
                dataset,
                PlotOrientation.VERTICAL,
                true,                    // legend
                true,                    // tooltips
                false                    // urls
        );

        XYPlot plot = chart.getXYPlot();

        // Show years as whole numbers (2024, not 2,024)
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setNumberFormatOverride(new DecimalFormat("0"));
        xAxis.setTickUnit(new NumberTickUnit(1));
        xAxis.setAutoRangeIncludesZero(false);

        // Format y-axis as currency
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setNumberFormatOverride(new DecimalFormat("$#,##0"));

        // Add point markers to the line
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);

        setContentPane(new ChartPanel(chart));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
       
    }

    public static void main(String[] args) {
       
       
        int[] years = {2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030};
        double[] costs = {12000, 13500, 15200, 14800, 17300, 18900, 20500, 22100, 23800, 25600, 27400};

        SwingUtilities.invokeLater(() -> new YearlyCostChart(years, costs).setVisible(true));
        
    }



}
