package bank.projekt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Micke
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Repository {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/bank";
   
   static final String USER = "root";
   static final String PASS = "root";
   
   public static int DepositAll(){
       
       

   //  Database credentials
   
   
   Connection conn = null;
   Statement stmt = null;
       
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();
      int amountofTrans = 0;
      int rowCount = 0;
      
      for(Customer e : BankLogic.kunder){
          String sql = "INSERT INTO Customers VALUES(";
          sql += e.getPnr() + ",";
          String[] parts = e.getName().split(" ");
          
          sql +=  ("'" + parts[0] + "','" + parts[1]) + "');";
          try{
              
              stmt.executeUpdate(sql);
              
          }
          catch(Exception d){
              
              sql = "UPDATE Customers SET firstName = '" + parts[0] + "' WHERE pNr = " + e.getPnr() + ";";
              stmt.executeUpdate(sql);
              sql = "UPDATE Customers SET lastName = '" + parts[1] + "' WHERE pNr = " + e.getPnr() + ";";
              stmt.executeUpdate(sql);
        }
          
          
          
          
          for(Account a : e.getAllAccounts()){
              if(a.getAcct_type() != null){
                  try{
                        
                      sql = "INSERT INTO Accounts VALUES(" + a.getAccountNumber() + ", '" + a.getAcct_type() +"'," + e.getPnr() + "," + a.getBalance() + ",'" + a.getInterestRate() + "'," + a.getAmmountOfWithdraws() + ");";
                      stmt.executeUpdate(sql);
                  }
                  catch(Exception b){
                      
                      sql = "UPDATE Accounts SET Balance = " + a.getBalance() + " WHERE Accounts.AccountNumber = " + a.getAccountNumber() + ";";
                      stmt.executeUpdate(sql);
                    }
              }
              else{
                  try{
                    if (a.getBalance() >= 0){
                        sql = "INSERT INTO Accounts VALUES(" + a.getAccountNumber() + ", 'Creditaccount' ," + e.getPnr() + "," + a.getBalance() + ",'0.5'," + a.getAmmountOfWithdraws() + ");";
                    }
                    else{
                        sql = "INSERT INTO Accounts VALUES(" + a.getAccountNumber() + ", 'Creditaccount' ," + e.getPnr() + "," + a.getBalance() + ",'-7'," + a.getAmmountOfWithdraws() + ");";
                    }
                    stmt.executeUpdate(sql);
                  }
                  catch(Exception b){
                      
                      sql = "UPDATE Accounts SET Balance = " + a.getBalance() + " WHERE Accounts.AccountNumber = " + a.getAccountNumber() + ";";
                      if(a.getBalance() < 0){
                          sql = "UPDATE Accounts SET InterestRate = '-7' WHERE Accounts.AccountNumber = " + a.getAccountNumber() + ";";
                      }
                      else{
                          sql = "UPDATE Accounts SET InterestRate = '0.5' WHERE Accounts.AccountNumber = " + a.getAccountNumber() + ";";
                      }
                      stmt.executeUpdate(sql);
                  }
              }
              
 
              
              for(Transactions t : a.getTransactionsList()){
                  amountofTrans += 1;
              }
                  
              
              ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM Transactions;");
              resultSet.next();
              
              rowCount = resultSet.getInt(1);
              
              
              

          }
          
          
          
      }
      
      
      
      if(amountofTrans > rowCount){
          
          stmt.executeUpdate("TRUNCATE TABLE Transactions;");
          for(Customer e : BankLogic.kunder){
              for(Account a : e.getAllAccounts()){
                  for(Transactions t : a.getTransactionsList()){
                      
          String[] parts2 = t.toString().split(" ");
          if(parts2[1].contains("Deposition")){
              parts2[1] = "Deposition";
          }
          else{
              parts2[1] = "Withdrawal";
          }


          //accountNumber, transaction, Amount, DateofWithdrawal

          if(parts2[2] == null){
              String sql2 = "INSERT IGNORE INTO Transactions VALUES(" + a.getAccountNumber() + ", '" 
                      + parts2[1] + "', 'Creditaccount','" + parts2[0] + "');";
              stmt.executeUpdate(sql2);


          }
          else{
              String sql2 = "INSERT IGNORE INTO Transactions VALUES(" + a.getAccountNumber() + ", '" 
                      + parts2[1] + "'," + parts2[3] + ",'" + parts2[0] + "');";

              stmt.executeUpdate(sql2);
          }       
                  }
              }
              
          }      
      }


      stmt.close();
  }
  catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
      return -1;
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
      return -1;
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
          return -1;
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
          
         se.printStackTrace();
         return -1;
      }//end finally try
       
   }
       return 1;
   }
   
   public static int WithdrawAll(){
       
        Connection conn = null;
       Statement stmt = null;

       try{
          //STEP 2: Register JDBC driver
          Class.forName("com.mysql.jdbc.Driver");

          //STEP 3: Open a connection

          conn = DriverManager.getConnection(DB_URL,USER,PASS);
          //STEP 4: Execute a query
          stmt = conn.createStatement();
          int count = 1;
          int length = 0;
          
          ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM Customers;");
          ArrayList<Long> IDs;
          
          if(rs2.next()){
              length = Integer.parseInt(rs2.getString(1));   
          }
          
          String sql = "SELECT * FROM Customers;";
          
          ResultSet rs = stmt.executeQuery(sql);
          IDs = new ArrayList<Long>(length);
          while(rs.next()){
              
              BankLogic.addCustomer((rs.getString(count+1) + " " + rs.getString(count+2)), Long.parseLong(rs.getString(count)));
              IDs.add(Long.parseLong(rs.getString(count)));
              count += 3;
          }
          
          rs.close();
          stmt.close();
          rs2.close();
          
          Statement stmt2 = conn.createStatement();
          
          String sql2 = "SELECT * FROM Accounts;";
          
          ResultSet rs3 = stmt2.executeQuery(sql2);
          
          for(Long e: IDs){
              
              int count4 = 1;
              
              while(rs3.next()){
                  Long compare = Long.parseLong(rs3.getString(3));
                  
                  if(e == compare){
                      String something = rs3.getString(2);
                      
                      if(something.equals("Savingsaccount")){
                          BankLogic.addSavingsAccount(e);
                      }
                      else{
                          BankLogic.addCreditAccount(e);
                      }
                      
                      for(Customer c : BankLogic.kunder){
                         if(c.getPnr() == e){
                             c.getLastAccount().setBalance(Double.parseDouble(rs3.getString(4)));
                             c.getLastAccount().setAmmountOfWithdraws(Integer.parseInt(rs3.getString(6))); //temp value
                             c.getLastAccount().setInterestRate(Double.parseDouble(rs3.getString(5)));
                             break;
                         }
                      }
                  }
                      
                  
             
              }

             }
          
          rs3.close();
          
          String sql3 = "SELECT * FROM Transactions;";
          
          Statement stmt3 = conn.createStatement();
          ResultSet rs4 = stmt3.executeQuery(sql3);
          
          while(rs4.next()){
              for(Long e: IDs){
                  for(Customer c : BankLogic.kunder){
                      if (c.getPnr() == e){
                          for(Account acc : c.getAllAccounts()){
                              if(acc.getAccountNumber() == Integer.parseInt(rs4.getString(1))){
                                  //int accountID, String transactionType, double amount, double balance, String day
                                  Transactions trans = new Transactions(acc.getAccountNumber(), rs4.getString(2) + ": ", Double.parseDouble(rs4.getString(3)), acc.getBalance(), rs4.getString(4));

                                  acc.transactionsList.add(trans);
                              }
                          }
                      }
                  }
              }
              System.out.println(rs4.getString(1) + rs4.getString(2) + rs4.getString(3) + rs4.getString(4));
          }
                 
              
          

          

          

          
          
          
       }
        catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
      return -1;
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
      return -1;
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
          return -1;
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
          
         se.printStackTrace();
         return -1;
      }
          return 1;
       }
}
}
