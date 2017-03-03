/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlinlämnings;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Micke
 */
public class DeleteEntry {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/test";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void delete() {
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
          System.out.println("Welcome to the Deletion Section.");
         
          
         

     
              System.out.println("The following tables, are currently available to be Deleted from: ");
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
                      
                      System.out.printf("If you wish to delete all entries in a table, write: DELETE_<table_name> (Example: delete_person).%n");
                      System.out.printf("If you wish to delete a specific entry, write: DELETE_<Table Name>_<Value_of_Primary_Key> (Example: delete_person_66).%n");
                      System.out.printf("If you wish to see all the entries of a specific table, press the associated number.%n");
                      System.out.printf("NOTE: Whenever a Person is removed, ALL of it's associated values, from other tables, gets removed as well.");
                      System.out.printf("%nPress Q to quit to Main Menu.%n");
                      boolean input = false;
                      boolean show = true;
                      
                      String check = next.next();
                      
                      if(check.equals("Q")){
                          System.out.println("Exiting Choise Menu.");
                          input = true;
                      }
                      if((check).toLowerCase().contains("delete")){
                          check = (check).toLowerCase();
                          String check2 = check.replaceAll("\\D","");
                          String check3 = check.replaceAll("[^a-zA-Z_]", "");
                          check3 = check3.replaceAll("_", "");
                          check3 = check3.replaceAll("delete", "");
                          
                          
                          if(check2.length() > 0){
                              //Do delete table_name key_attribute_name value
                              try{
                                  Class.forName("com.mysql.jdbc.Driver");
                                  conn = DriverManager.getConnection(DB_URL,USER,PASS); 
                                  stmt = conn.createStatement();

                                  String sql2 = "DELETE FROM " + check3 + " WHERE ";
                                 


                                  sql = "SHOW TABLES";

                                  
                                  rs = stmt.executeQuery(sql);
                                  
                                  
                                  
                                  while(rs.next()){
                                      if(rs.getString(1).contains(check3)){
                                          if(rs.getString(1).equals("cars")){
                                              sql2 += "BelongsToId IN (";
                                              sql2 += check2 + ");";
                                              
                                              stmt.executeUpdate(sql2);
                                              
                                              rs.close();
                                              stmt.close();
                                              break;
                                             
                                          }
                                          if(rs.getString(1).equals("pets")){
                                              sql2 += "IdOfOwner IN (";
                                              System.out.println("Pets");
                                              sql2 += check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              rs.close();
                                              stmt.close();
                                              break;
                                              
                                          }
                                          if(rs.getString(1).equals("phonenumber")){
                                              sql2 += "NumberId IN (";
                                              sql2 += check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              rs.close();
                                              stmt.close();
                                              break;
                                              
                                          }
                                          if(rs.getString(1).equals("jobs")){
                                              sql2 += "Id IN (";
                                              sql2 += check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              rs.close();
                                              stmt.close();
                                              break;
                                              
                                          }  
                                          if(rs.getString(1).equals("hobbies")){
                                              sql2 += "Id IN (";
                                              sql2 += check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              rs.close();
                                              stmt.close();
                                              break;
                                          }
                                          
                                          if(rs.getString(1).equals("person")){
                                              System.out.println("When a person is deleted, all dependancies in other tables are deleted as well.");
                                              sql2 = "DELETE FROM hobbies WHERE Id IN (" + check2 + ");";
                                              
                                              stmt.executeUpdate(sql2);
                                              
                                              sql2 = "DELETE FROM phonenumber WHERE Id IN (" + check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              
                                              sql2 = "DELETE FROM pets WHERE IdOfOwner IN (" + check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              
                                              sql2 = "DELETE FROM cars WHERE BelongsToId IN (" + check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              
                                              sql2 = "DELETE FROM jobs WHERE Id IN (" + check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              
                                              sql2 = "DELETE FROM person WHERE Id in (" + check2 + ");";
                                              stmt.executeUpdate(sql2);
                                              
                                              
                                              rs.close();
                                              stmt.close();
                                              break;
                                              
                                          }
                                          
       
                                              
                                          }
                                      }
                                    
                              
                                 
                                
                                  rs.close();
                                  System.out.println("The removal of Id " + check2 + " in the " + check3 + " table statement, was successfully executed.");
                                  System.out.println();
                                  System.out.println();


                                  stmt.close();
                                  show = false;
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

                              break;
                          }
                              
 
                              
                          }
                          else if(check2.length() == 0){
                              check = (check).toLowerCase();
                          
                          check3 = check.replaceAll("[^a-zA-Z_]", "");
                          check3 = check3.replaceAll("_", "");
                          check3 = check3.replaceAll("delete", "");

                              //Do delete table_name key_attribute_name value
                              try{
                                  String sql2 = "";
                                  Class.forName("com.mysql.jdbc.Driver");
                                  conn = DriverManager.getConnection(DB_URL,USER,PASS); 
                                  stmt = conn.createStatement();
                                  
                                  if(check3.toLowerCase().equals("person")){
                                      sql2 = "TRUNCATE TABLE Cars;";
                                      stmt.executeUpdate(sql2);
                                      sql2 = "TRUNCATE TABLE Pets;";
                                      stmt.executeUpdate(sql2);
                                      sql2 = "TRUNCATE TABLE Hobbies;";
                                      stmt.executeUpdate(sql2);
                                      sql2 = "TRUNCATE TABLE Jobs;";
                                      stmt.executeUpdate(sql2);
                                      sql2 = "TRUNCATE TABLE Phonenumber;";
                                      stmt.executeUpdate(sql2);
                                      
                                      
                                      sql2 = "UPDATE Person SET Pets = 0 WHERE Id = 255;";
                                      stmt.executeUpdate(sql2);
                                      
                                      sql2 = "UPDATE Person SET Pets = 0 WHERE Id = 1002;";
                                      stmt.executeUpdate(sql2);
                                      
                                      sql2 = "UPDATE Person SET Pets = 0 WHERE Id = 5555;";
                                      stmt.executeUpdate(sql2);
                                      
                                      sql2 = "UPDATE Person SET Pets = 0 WHERE Id = 6666;";
                                      stmt.executeUpdate(sql2);
                                      
                                      
                                      System.out.println("Due to foreign key restraint, the only way to Truncate the Persons table, would be to");
                                      System.out.println("completely drop the other tables. Which is not supported in this version of the Database Manager.");
                                      System.out.println("Exiting to main menu.");
                                      rs.close();
                                      stmt.close();
                                      break;
                                      
                                      
                                  }

                                  
                                  sql2 = "TRUNCATE TABLE " + check3 +";";
                                  
                                 


                                  sql = "SHOW TABLES";

                                  
                                  rs = stmt.executeQuery(sql);
                                  stmt.executeUpdate(sql2);
                                  rs.close();
                                  System.out.println("Truncation of " + check3 + " was successful.");
                                  
                                  stmt.close();
                                              
                                  //Do delete table_name
                                  show = false;
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

                              break;
                          }
                          }
                        
                      }
                      while(!input && show){
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
                          
                              while(rs.next()){
                                  for(int i = 1; i < columnsNumber+1; i++){
                                  if(i == 1){
                                      System.out.println();
                                  }
                                  try{
                                      System.out.printf(rsmd.getColumnName(i) + ":      " +rs.getString(i) + "     %n");
                                  }
                                  catch(Exception e){
                                      System.out.println("Failed in rs Loop");
                                  }
                                  
                                  }
                              }
                              
                              rs.close();
                             


                              stmt.close();
                              String something = system.next();
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
                      String something = system.next();
                      
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
              
              break;
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
   
   
}//end main
    
}
