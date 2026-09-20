package com.homeimprovement;


import java.util.Objects;
public class HomeData implements Comparable<HomeData> {
/*     */   private String date;
/*     */   private String area;
/*     */   private String item;
/*     */   private Double cost;
/*     */   private String receiptFilename;
/*     */   private String info;
/*     */   private Boolean isValue;
/*     */   
/*     */   public HomeData(String date, String area, String item, Double cost, String receiptFilename, String info, Boolean isValue) {
/*  23 */     this.date = date;
/*  24 */     this.area = area;
/*  25 */     this.item = item;
/*  26 */     this.cost = cost;
/*  27 */     this.receiptFilename = receiptFilename;
/*  28 */     this.info = info;
/*  29 */     this.isValue = isValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HomeData(String input) {
/*  35 */     parseIntoVariable(input);
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseIntoVariable(String input) {
/*  40 */     String COMMA_DELIMITER = ",";
/*  41 */     String[] databaseInput = input.split(COMMA_DELIMITER);
/*     */     
/*  43 */     for (int i = 0; i < databaseInput.length; i++) {
/*     */       
/*  45 */       switch (i) {
/*     */         case 0:
/*  47 */           this.date = databaseInput[i];
/*     */           break;
/*     */         case 1:
/*  50 */           this.area = databaseInput[i].trim();
/*     */           break;
/*     */         case 2:
/*  53 */           this.item = databaseInput[i].trim();
/*     */           break;
/*     */         case 3:
/*  56 */           this.cost = Double.valueOf(Double.parseDouble(databaseInput[i]));
/*     */           break;
/*     */         case 4:
/*  59 */           this.receiptFilename = databaseInput[i].trim();
/*     */           break;
/*     */         case 5:
/*  62 */           this.info = databaseInput[i].trim();
/*     */           break;
/*     */         case 6:
/*  65 */           this.isValue = Boolean.valueOf(Boolean.parseBoolean(databaseInput[i]));
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertDateStringToInt(String date) {
/*  75 */     String delimStr = "-";
/*     */     
/*  77 */     String[] words = date.split(delimStr);
/*     */ 
/*     */     
/*  80 */     return Integer.parseInt(words[1]) * 100 + Integer.parseInt(words[2]) + 
/*  81 */       Integer.parseInt(words[0]) * 10000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */@Override
   public String toString() {
/*  89 */     return "HomeData [date=" + this.date + " \n area=" + this.area + " \n item=" + this.item + " \n cost=" + this.cost + "\n receiptFilename=" + 
/*  90 */       this.receiptFilename + "\n info=" + this.info + "\n isValue=" + this.isValue + "]";
/*     */   }
/*     */   
/*     */   public String getDate() {
/*  94 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(String date) {
/*  98 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getArea() {
/* 102 */     return this.area;
/*     */   }
/*     */   
/*     */   public void setArea(String area) {
/* 106 */     this.area = area;
/*     */   }
/*     */   
/*     */   public String getItem() {
/* 110 */     return this.item;
/*     */   }
/*     */   
/*     */   public void setItem(String item) {
/* 114 */     this.item = item;
/*     */   }
/*     */   
/*     */   public Double getCost() {
/* 118 */     return this.cost;
/*     */   }
/*     */   
/*     */   public void setCost(Double cost) {
/* 122 */     this.cost = cost;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReceiptFilename() {
/* 127 */     return this.receiptFilename;
/*     */   }
/*     */   
/*     */   public void setReceiptFilename(String receiptFilename) {
/* 131 */     this.receiptFilename = receiptFilename;
/*     */   }
/*     */   
/*     */   public String getInfo() {
/* 135 */     return this.info;
/*     */   }
/*     */   
/*     */   public void setInfo(String info) {
/* 139 */     this.info = info;
/*     */   }
/*     */   
/*     */   public Boolean getIsValue() {
/* 143 */     return this.isValue;
/*     */   }
/*     */ 
/*     */   
/*     */@Override
   public int hashCode() {
/* 148 */     return Objects.hash(new Object[] { this.area, this.cost, this.date, this.info, this.isValue, this.item, this.receiptFilename });
/*     */   }
/*     */ 
/*     */   
/*     */@Override
   public boolean equals(Object obj) {
/* 153 */     if (this == obj)
/* 154 */       return true; 
/* 155 */     if (obj == null)
/* 156 */       return false; 
/* 157 */     if (getClass() != obj.getClass())
/* 158 */       return false; 
/* 159 */     HomeData other = (HomeData)obj;
/* 160 */     return (Objects.equals(this.area, other.area) && Objects.equals(this.cost, other.cost) && Objects.equals(this.date, other.date) && 
/* 161 */       Objects.equals(this.info, other.info) && Objects.equals(this.isValue, other.isValue) && 
/* 162 */       Objects.equals(this.item, other.item) && Objects.equals(this.receiptFilename, other.receiptFilename));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */@Override
   public int compareTo(HomeData obj) {
/* 170 */     return convertDateStringToInt(this.date) - convertDateStringToInt(obj.date);
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\main_screen\HomeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */

