package com.homeimprovement;


import java.util.Objects;
public class HomeData implements Comparable<HomeData> {
   private String date;
   private String area;
   private String item;
   private Double cost;
   private String receiptFilename;
   private String info;
   private Boolean isValue;
   
   public HomeData(String date, String area, String item, Double cost, String receiptFilename, String info, Boolean isValue) {
     this.date = date;
     this.area = area;
     this.item = item;
     this.cost = cost;
     this.receiptFilename = receiptFilename;
     this.info = info;
     this.isValue = isValue;
   }
 
 
   
   public HomeData(String input) {
     parseIntoVariable(input);
   }
 
   
   private void parseIntoVariable(String input) {
     String COMMA_DELIMITER = ",";
     String[] databaseInput = input.split(COMMA_DELIMITER);
     
     for (int i = 0; i < databaseInput.length; i++) {
       
       switch (i) {
         case 0 -> this.date = databaseInput[i];
         case 1 -> this.area = databaseInput[i].trim();
         case 2 -> this.item = databaseInput[i].trim();
         case 3 -> this.cost = Double.valueOf(databaseInput[i]);
         case 4 -> this.receiptFilename = databaseInput[i].trim();
         case 5 -> this.info = databaseInput[i].trim();
         case 6 -> this.isValue = Boolean.valueOf(databaseInput[i]);
       } 
     } 
   }
   
   public static int convertDateStringToInt(String date) {
     String delimStr = "-";
     
     String[] words = date.split(delimStr);
 
     
     return Integer.parseInt(words[1]) * 100 + Integer.parseInt(words[2]) + 
       Integer.parseInt(words[0]) * 10000;
   }
 
 
 
 
   
@Override
   public String toString() {
     return "HomeData [date=" + this.date + " \n area=" + this.area + " \n item=" + this.item + " \n cost=" + this.cost + "\n receiptFilename=" + 
       this.receiptFilename + "\n info=" + this.info + "\n isValue=" + this.isValue + "]";
   }
   
   public String getDate() {
     return this.date;
   }
   
   public void setDate(String date) {
     this.date = date;
   }
   
   public String getArea() {
     return this.area;
   }
   
   public void setArea(String area) {
     this.area = area;
   }
   
   public String getItem() {
     return this.item;
   }
   
   public void setItem(String item) {
     this.item = item;
   }
   
   public Double getCost() {
     return this.cost;
   }
   
   public void setCost(Double cost) {
     this.cost = cost;
   }
 
   
   public String getReceiptFilename() {
     return this.receiptFilename;
   }
   
   public void setReceiptFilename(String receiptFilename) {
     this.receiptFilename = receiptFilename;
   }
   
   public String getInfo() {
     return this.info;
   }
   
   public void setInfo(String info) {
     this.info = info;
   }
   
   public Boolean getIsValue() {
     return this.isValue;
   }
 
   
@Override
   public int hashCode() {
     return Objects.hash(new Object[] { this.area, this.cost, this.date, this.info, this.isValue, this.item, this.receiptFilename });
   }
 
   
@Override
   public boolean equals(Object obj) {
     if (this == obj)
       return true; 
     if (obj == null)
       return false; 
     if (getClass() != obj.getClass())
       return false; 
     HomeData other = (HomeData)obj;
     return (Objects.equals(this.area, other.area) && Objects.equals(this.cost, other.cost) && Objects.equals(this.date, other.date) && 
       Objects.equals(this.info, other.info) && Objects.equals(this.isValue, other.isValue) && 
       Objects.equals(this.item, other.item) && Objects.equals(this.receiptFilename, other.receiptFilename));
   }
  
@Override
   public int compareTo(HomeData obj) {
     return convertDateStringToInt(this.date) - convertDateStringToInt(obj.date);
   }
 }


/* Location:              C:\Users\rober\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\main_screen\HomeData.class
 * Java compiler version: 21 (JDK 21))
 * JD-Core Version:       1.1.3
 */

