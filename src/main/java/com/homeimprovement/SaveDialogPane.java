package com.homeimprovement;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.swing.JFileChooser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaveDialogPane
/*    */ {
/*    */   public static void main(String[] args) {
/* 13 */     JFileChooser chooser = new JFileChooser();
/*    */     
/*    */     try {
/* 16 */       File f = new File((new File(".")).getCanonicalPath());
/* 17 */       chooser.setCurrentDirectory(f);
/* 18 */       chooser.setCurrentDirectory(null);
/* 19 */       chooser.showOpenDialog(null);
/* 20 */       File file1 = chooser.getCurrentDirectory();
/* 21 */     } catch (IOException e) {
/*    */ 
/*    */       
/* 24 */       System.out.println("OOOPS");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\HomeImprovementsNRepairs\homeImprovementsNRepairs.jar!\main_screen\SaveDialogPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */