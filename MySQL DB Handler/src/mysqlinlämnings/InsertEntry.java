/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlinlämnings;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static mysqlinlämnings.DescribePeople.DB_URL;

/**
 *
 * @author Micke
 */
public class InsertEntry {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/test";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void Insert() {
   Connection conn = null;
   Statement stmt = null;
   
   Boolean input = false;
   Scanner system = new Scanner(System.in);
   
   try{
       while(!input){
               System.out.printf("%nWelcome to the Insertion Section.");
               System.out.println("To navigate tables, press 1.");
               
               System.out.println("To insert into a table, please provide a table name and values. (An example: person(55,Tomas,UK,Night,0)");
               System.out.println("WARNING: Straying from the format, will not allow insertion of your value into the database.");
               System.out.println("NOTE:The values must corespond to the respective tables format.");
               System.out.println("NOTE:Any numbers written in the base name, for example 'person5', is Discarded.");
               System.out.println();
               System.out.printf("To exit to the Main Menu, write Q.%n%n");
               
               

               String something = system.next();

               if(something.equals("Q")){
                   System.out.println("Returning to Main Menu.");
                   break;
               }
               if(something.equals("1")){
                   System.out.println("The following tables, are currently available to be viewed: ");
              System.out.println();
              try{
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
                  
                  System.out.println();
                  System.out.println();


                  stmt.close();
                  
                  
                  try{
                      
                      System.out.println("Which one do you wish to see? Please write the number of the table. Q to Quit.");
                      boolean input2 = false;
                      
                      String check = system.next();
                      
                      if(check.equals("Q")){
                          System.out.println("Exiting Choise Menu.");
                          input = true;
                      }
                      
                      while(!input2){
                          try
                          {
                              if((Integer.parseInt(check) >= 1) && ((Integer.parseInt(check)) <= (count-1))){
                                  
                              Class.forName("com.mysql.jdbc.Driver");

                              conn = DriverManager.getConnection(DB_URL,USER,PASS); 
                              stmt = conn.createStatement();
                              switch(Integer.parseInt(check)){
                                  case 1:
                                      sql = "SELECT * FROM cars";
                                      break;
                                  case 2:
                                      sql = "SELECT * FROM hobbies";
                                      break;
                                  case 3:
                                      sql = "SELECT * FROM jobs";
                                      break;
                                  case 4:
                                      sql = "SELECT * FROM person";
                                      break;
                                  case 5:
                                      sql = "SELECT * FROM pets";
                                      break;
                                  case 6:
                                      sql = "SELECT * FROM phonenumber";
                                      break;
                                      
                              }
                              rs = stmt.executeQuery(sql);
                              
                              ResultSetMetaData rsmd = rs.getMetaData();
                              int columnsNumber = rsmd.getColumnCount();
                              try{
                                  rs.getString(1);
                              }
                              catch(Exception e){
                                  System.out.printf("There are no entries to display, for that table.%n%n");
                              }
                              while(rs.next()){
                                  for(int i = 1; i < columnsNumber+1; i++){
                                  if(i == 1){
                                      System.out.println();
                                  }
                                  try{
                                      System.out.printf(rsmd.getColumnName(i) + ":      " +rs.getString(i) + "    %n");
                                  }
                                  catch(Exception e){
                                      System.out.println("Failed in rs Loop");
                                  }
                                  
                                  }
                              }
                              rs.close();
                              stmt.close();
                              break;
                              }
                              else{
                                  throw new Exception();
                              }
                          }
                          
                          catch(Exception IndexOutOfBoundsException){
                              System.out.println("That is not a valid input. Please try again"); 
                              break;   
                          }
                      }   
                  }
                  catch(Exception e){
                      
                      System.out.println("That is not a valid input. Try again.");
                      something = system.next();                 
                  }
              }
              catch(SQLException se){
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
          }
               }
               
               else{
                   String purge = something.trim();
                   String[] clear = purge.split("\\(");
                   String purge1 = clear[0].replaceAll("[^a-zA-Z]", "");
                   purge1 = clear[0].toLowerCase();
                   boolean found = false;
                   
                   try{
                              Class.forName("com.mysql.jdbc.Driver");
                              conn = DriverManager.getConnection(DB_URL,USER,PASS); 
                              stmt = conn.createStatement();
                              
                              
                              
                              String sql = "SHOW TABLES;";
                              
                              String sql2 = "SELECT * FROM " + purge1 + ";";
                              
                              
                              
                              ResultSet test2 = stmt.executeQuery(sql2);

                              
                              ResultSetMetaData rsmd2 = test2.getMetaData();
                              
                              String[] typesOfArguments = new String[rsmd2.getColumnCount()];
                              
                              Statement stmtCount = conn.createStatement();
                              ResultSet resultSetCount = stmtCount.executeQuery("SELECT COUNT(*) FROM " + purge1);
                              
                              resultSetCount.next();
                              int beforeinsertion = resultSetCount.getInt(1);
                              
                              
                              
                              
                              while(test2.next()){
                                  try{
                                      for(int i = 0; i < rsmd2.getColumnCount(); i++){
                                          
                                          typesOfArguments[i] = rsmd2.getColumnTypeName(1+i);
                                      
                                      if(i == rsmd2.getColumnCount()){
                                          break;
                                      }
                                  }
                                  }
                                  catch(Exception e){
                                     break;
                                  }
                                  
                              }
                              int columnCount = rsmd2.getColumnCount();

                              ResultSet rs = stmt.executeQuery(sql);
                              
                              ResultSetMetaData rsmd = rs.getMetaData();
                              
                              boolean character = false;

                              String commando = "INSERT IGNORE INTO ";
                              boolean added = false;
                              while(rs.next()){
                                  if(rs.getString(1).equals(purge1)){
                                      System.out.println("Found destination table: " + purge1 + ".");
                                      if(!added){
                                          commando += purge1 + " VALUES(";
                                          
                                          added = true;
                                      }
                                      found = true;
                                      System.out.println("Checking arguments list...");
                                      
                                      
                                      
                                      purge = something.replaceAll(",", "-");

                                      
                                      String[] temp = new String[1];
                                
                                      temp = purge.split("-");
                                      
                                      
                                      for(int i = 0; i < temp.length ; i++){
                                          if(typesOfArguments[i].equals("INT")){ //accounts for Person table
                                          try{
                                              
                                              temp[i] = temp[i].replaceAll("[^0-9]", "");
                                              int test = Integer.parseInt(temp[i]);
                                              character = false;
                                              
                                              
                                          }
                                          catch(Exception e){
                                              
                                              System.out.println("Expected number: got Text input, at argument " + i);
                                             
                                              break;
                                              }
                                          }
                                          else{
                                              
                                              temp[i] = temp[i].replaceAll("[^a-zA-Z]", "");
                                              if(temp[i].length() == 0){
                                                  System.out.println("Expected letter: Got number input, at argument" + i);
                                                  break;
                                              }
                                              character = true;
                                          }
                                          if(!character){
                                          commando += temp[i];
                                          }
                                          else{
                                              commando += "'" + temp[i] + "'";
                                          }
                                          if(i != temp.length - 1){
                                              commando += ",";
                                          }
                                          
                                      }
                                      if(columnCount == temp.length){
                                          System.out.println("Length of Arguments in Argument list, accepted, Checking Constraint of Unique value..");
                                      }
                                      
                                      
                                      
                                      if(columnCount != temp.length){
                                          System.out.println("The lengths of arguments provided does not match target tables argument list.");
                                          System.out.println("Expected: " + columnCount + " arguments, got: " + temp.length + " arguments.");
                                          for(int i = 0; i < temp.length; i++){
                                              System.out.println(temp[i]);
                                          }
                                          break;
                                      }
                                      Statement stmt3 = conn.createStatement();
                                      commando += ");";
                                      stmt3.executeUpdate(commando);
                                      stmt3.close();
                                      
                                      
                                      
                                      
                                      stmtCount = conn.createStatement();
                                      resultSetCount = stmtCount.executeQuery("SELECT COUNT(*) FROM " + purge1);

                                      resultSetCount.next();
                                      int amountOfEntries = resultSetCount.getInt(1);
                                      
                                      
                                      if(amountOfEntries == beforeinsertion){
                                          System.out.printf("%nERROR: The insertion was not made, due to duplication of Value in assignment value.%n");
                                      }
                                      else if(amountOfEntries > beforeinsertion){
                                      
                                          System.out.printf("%nThe " + commando + " was executed, succesfully.%n");
                                      }
                                      
                                      
                                  }
                                  
                              }
                              
                              if(!found){
                                  System.out.println("There was no table found with the specified name of: " + purge1 + ".");
                              }
                              
                              
                              
                              rs.close();
                              
                              
                              stmt.close();
                              
                              
                              
                              break;

              }
              catch(SQLException se){
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
                  
          }          
               }
              
       }

 
    
}
   catch(Exception e){
       System.out.println("Something went wrong.");
   }
   }
}
