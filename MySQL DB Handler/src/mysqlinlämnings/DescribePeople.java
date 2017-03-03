/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlinlämnings;

/**
 *
 * @author Micke
 */


import java.sql.*;
import java.util.Scanner;

public class DescribePeople {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/test";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      
      
      Scanner system = new Scanner(System.in);
      boolean flag = false;
      Scanner next = new Scanner(System.in);
      while(!flag){
          System.out.printf("%n");
          System.out.println("Welcome to the Database Manager!");
          System.out.println("What do you wish to do, today?");
          System.out.println();
          System.out.println("To select a table to view, press: 1");
          System.out.println("To delete an entry from a table, press: 2");
          System.out.println("To update an entry in the a table, press: 3");
          System.out.println("To insert an entry into a table, press: 4");
          System.out.println("To quit the database, press: Q");
          System.out.println();
          String something = system.next();
          
          if(something.equals("Q")){
              
              break;
          }
          
          if (something.equals("1")){
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
                      boolean input = false;
                      
                      String check = next.next();
                      
                      if(check.equals("Q")){
                          System.out.println("Exiting Choise Menu.");
                          input = true;
                      }
                      while(!input){
                          try
                          {
                              if((Integer.parseInt(check) >= 1) && ((Integer.parseInt(check)) <= (count-1))){
                              
                              
                              Class.forName("com.mysql.jdbc.Driver");

                              //STEP 3: Open a connection

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
                                  System.out.println("There are no entries to display, for that table.");
                              }
                 
                          
                              while(rs.next()){
                                  for(int i = 1; i < columnsNumber+1; i++){
                                  if(i == 1){
                                      System.out.println();
                                  }
                                  try{
                                      System.out.printf(rsmd.getColumnName(i) + ": \t" +rs.getString(i) + "\t%n");
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
          else if (something.equals("2")){
              DeleteEntry.delete();
              
          }
          else if (something.equals("3")){
              UpdateEntry.Update();
          }
          else if (something.equals("4")){
              InsertEntry.Insert();
              
          }
          
          else{
              System.out.println("That is not a valid choise. Please try another input, for a choise.");
          }
      }
      
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
   System.out.printf("Exiting Database, Goodbye!%n");
   
}//end main
}



