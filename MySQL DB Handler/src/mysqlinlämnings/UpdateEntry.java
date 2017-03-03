/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlinlämnings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Micke
 */
public class UpdateEntry {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/test";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void Update() {
   Connection conn = null;
   Statement stmt = null;
   Statement stmt2 = null;
 
      
      Scanner system = new Scanner(System.in);
      boolean flag = false;
      Scanner next = new Scanner(System.in);
      
      
      
      while(!flag){
          System.out.println("Welcome to the Update Section.");
          System.out.printf("If you wish to browse available tables, press: 1.%n%n");


          System.out.println("To update a table, please write the following format:");
          System.out.printf("<table_name>_<Identifying_Attribute>_<Assignment_Attribute>_<new value>_<Operator>_<Comparing Value>%n%n");
          System.out.printf("An example: person_id_name_Mikael_=_1991%n");
          System.out.printf("Will give: UPDATE person SET name='Mikael' WHERE id = (1991)'%n%n");
          
          System.out.println("Another example: person_name_name_Mikael_LIKE_S%");
          System.out.println("Will give: UPDATE person SET name='Mikael' WHERE name LIKE ('S%');");
          System.out.println();
          System.out.println("If you wish to return to the Main Menu, press Q.");
          System.out.println();
              String input2 = system.next();
              if(input2.equals("Q")){
                  System.out.println("Exiting to Main Menu.");
                  break;
              }
              
              if(!input2.equals("1")){
                  try{
                  conn = DriverManager.getConnection(DB_URL,USER,PASS); 
                  stmt = conn.createStatement();
                  stmt2 = conn.createStatement();
                  Statement stmt3 = conn.createStatement();
                  
                  
                  
                  String sql;
                  boolean found = false;
                  boolean found2 = false;
                  boolean found3 = false;
                  boolean found4 = false;
                  
                  boolean pass = false;
                  System.out.println("Attempting to Update..");
                  
                  sql = "SHOW TABLES";
                  String statement = "";
                  ResultSet rs = stmt.executeQuery(sql);
                  
                  
                  String[] clear = input2.split("_");
                  String input3 = clear[0];
                  String input4 = clear[2];
                  String input5 = clear[3];
                  String input6 = clear[1];
                  String input7 = clear[4].toUpperCase();
                  input7 = input7.replaceAll("[^a-zA-Z=]", "");
                  if(input7.equals("LIKE") || input7.equals("IN") || input7.equals("IS") || input7.equals("=")){
                      pass = true;
                  }
                  if(!pass){
                      System.out.println("ERROR: Only LIKE/IN/IS/= Operators are allowed in the operators field.");
                      throw new Exception();
                  }
                  if(input7.length() == 0){
                      throw new NumberFormatException();
                  }
                  
                  
                  
                  
                  String input8 = clear[5];
                  
                  
                  
                  for(int i = 0; i < clear.length; i++){
                      if(clear[i].length() == 0 || clear[i].equals(" ")){
                          System.out.println("ERROR: One of the fields were filed as empty. Stopping operations.");
                          throw new Exception();
                      }
                  }
                  while(rs.next()){
                      if(rs.getString(1).equals(input3)){
                          
                          
                          
                          System.out.println("Found table: " + input3 + "..");
                          
                          String testStatement = "SELECT * FROM " + input3 + ";";
                          
                          ResultSet rs3 = stmt3.executeQuery(testStatement);
                          
                          ResultSetMetaData rsmd2 = rs3.getMetaData();
                          int length = rsmd2.getColumnCount();
                          
                          
                          int count = 1;
                          
                          for(int i = 1; i < length; i++){
                              String check = (rsmd2.getColumnName(i)).toLowerCase();
                              if(input6.equals(check)){
                                  System.out.println("Found identifying attribute:" + input6 + "..");
                                  found2 = true;
                                  count = i;
                              }
                              if(input4.equals(check)){
                                  System.out.println("Found assignment attribute:" + input4 + "..");
                                  found3 = true;
                              }
                              
                              
                          }
                          if(!found2){
                              System.out.println("Error in Second Argument: Identifying attribute not found.");
                              throw new Exception();
                          }
                          if(!found3){
                              System.out.println("Error in Third Argument: Assignment attribute not found.");
                              throw new Exception();
                          }
                          
                          //input8 is the input value
                          while(rs3.next()){
                              if(!input8.contains("%")){
                              if(rs3.getString(1).equals(input8)){
                                  System.out.println("A matching value of " + input8 + " was found..");
                                  found4 = true;
                              }
                              }
                              if(input8.contains("%")){
                                  
                                  
                                  
                                  if(input8.charAt(0) == '%' && input8.charAt(input8.length()-1) == '%'){
                                      for(int i = 1; i < length; i++){
                                          
                                          if(rs3.getString(i).contains(input8.substring(1, input8.length()-1)) && count == i){
                                              System.out.println("Found a match for " + input8 + " , Compared to: " + rs3.getString(i));
                                              found4 = true;
                                          }
                                      }
                                      
                                  }
                                  if(input8.charAt(0) == '%' && input8.charAt(input8.length()-1) != '%'){
                                     
                                      for(int i = 1; i < length; i++){
                                          
                                          
                                          if(rs3.getString(i).length() >= input8.length()-1){
                                              if(rs3.getString(i).substring(rs3.getString(i).length()-((input8.length() - 1))).equals(input8.substring(1,input8.length())) && count == i){
                                                  System.out.println("Found a match for " + input8 + ", Compared to: " + rs3.getString(i));
                                                  found4 = true;
                                              }
                                              
                                          
                                      }
                                  }
                                  }
                                  if(input8.charAt(0) != '%' && input8.charAt(input8.length()-1) == '%'){
                                      for(int i = 1; i < length; i++){
                                          
                                          
                                          if(rs3.getString(i).length() >= input8.length()-1){
                                              
                                              if(rs3.getString(i).substring(0,((input8.length() - 1))).equals(input8.substring(0,input8.length()-1)) && count == i){
                                                  System.out.println("Found a match for " + input8 + ", Compared to: " + rs3.getString(i));
                                                  found4 = true;
                                              }
                                              
                                          
                                      }
                                  }
                                  }
                              }
                          }
                          if(!found4){
                              System.out.println("A matching value of " + input8 + " was not found to update in the table: " + input3 + ".");
                              throw new Exception();
                          }
                          System.out.println();
                          found = true;
                          try{
                              int test = Integer.parseInt(input5);
                          }
                          catch(Exception e){
                              input5 = "'" + input5 + "'";
                          }
                          try{
                              int test = Integer.parseInt(input8);
                          }
                          catch(Exception e){
                              input8 = "'" + input8 + "'";
                          }
                          statement += "UPDATE " + input3 + " SET " + input4 +"=" + input5 + " WHERE " + input6 + " " + input7 + " (" + input8 + ");";
                          System.out.println("Attempting the following statement: " + statement);
                          System.out.println();
                          
                          stmt2.executeUpdate(statement);
                          System.out.println("The " + statement + " , was successfull.");
                          System.out.println();
                      }
                  
                   }
                  
                  if(!found){
                      System.out.println("No table with the name '" + input3 +"' was found.");
                      System.out.println();
                  }
                  
                  rs.close();
                  
                  stmt.close();
                  stmt2.close();
                  }
                  catch(SQLException se){
                      //Handle errors for JDBC
                      se.printStackTrace();
                   }catch(Exception e){
                      //Handle errors for Class.forName
                      if(e instanceof ArrayIndexOutOfBoundsException){
                          
                          System.out.println("FAILED: Too few arguments. Please provide at least 6 arguments, seperated by _'s.");
                          
                      }
                      else if(e instanceof NumberFormatException){
                          System.out.println("FAILED: You may only provide IN/LIKE/= in the operators field.");
                      }
                      
                      System.out.println();
                     
                   }finally{
                      //finally block used to close resources
                      try{
                         if(stmt!=null)
                            stmt.close();
                      }catch(SQLException se2){
                      }// nothing we can do
                      try{
                         if(conn!=null)
                            conn.close();
                      }catch(SQLException se){
                         se.printStackTrace();
                      }//end finally try
                   }//end try
                  
              }
              
              if(input2.equals("1")){
          
              System.out.println();
              try{
                  System.out.println("The following tables, are currently available to be Updated from: ");
                  System.out.println();
                  Class.forName("com.mysql.jdbc.Driver");

                  //STEP 3: Open a connection

                  conn = DriverManager.getConnection(DB_URL,USER,PASS); 
                  stmt = conn.createStatement();
                  String sql;
                  
                  sql = "SHOW TABLES";
                  ResultSet rs = stmt.executeQuery(sql);
                  int count = 1;
                  while(rs.next()){

                      System.out.print(count + ":" +rs.getString(1) + " ");
                      count += 1;
                      }
                  
                  rs.close();
                  
                  
                  ResultSet rs2 = stmt.executeQuery(sql);
                  System.out.println();
                  System.out.println();
                  System.out.println("If you wish to inspect one of the tables, write the associated value with the table.");
                  System.out.printf("If you wish to return to updating, write anything else.%n%n");
                  input2 = system.next();
                  System.out.println();
                  
                  try{
                      int test = Integer.parseInt(input2);
                      if(test <= (count - 1) && test > 0){
                          int count2 = 1;
                          while(rs2.next()){
                              if(count2 == test){
                                  String found = "SELECT * FROM " + rs2.getString(1) + ";";
                                  stmt.executeQuery(found);
                                  ResultSet rs3 = stmt.executeQuery(found);
                                  
                                  ResultSetMetaData rsmd = rs3.getMetaData();
                                  int columnsNumber = rsmd.getColumnCount();
                                  while(rs3.next()){
                                      for(int i = 1; i < columnsNumber+1; i++){
                                          
                                          System.out.printf(rsmd.getColumnName(i) + "    ");
                                          if(rs3.getString(i).indexOf('%') >= 0){
                                              String special = rs3.getString(i).replaceAll("%", "Percent");
                                              System.out.println(special);
                                          }
                                          else{
                                              System.out.printf(rs3.getString(i) + "\t\t");
                                          }
                                          if((i % columnsNumber) == 0){
                                              System.out.println();
                                          }
//                                          System.out.print(rs3.getString(i) + " ");
                                          
                                      }
                                  }
                                  
                                  
                                  break;
                              }
                              count2 += 1;
                          }
                          System.out.println();
                          
                          
                         
                            
                      }
                      else{
                          System.out.printf("The specified number is out of range, in contrast to table numbers.");
                      }
                      
                  }
                  catch(Exception e){
                      
                      System.out.println("You did not write a valid number. Exiting to Update menu.");
                      System.out.println();
                  }
                  


                  stmt.close();
                  
      

   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
              }
   
   }
   }
}
