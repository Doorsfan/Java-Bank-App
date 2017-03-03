/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

import static bank.projekt.MainpageController.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Micke
 */
public class BankLogic {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    
    public static ArrayList<Customer> kunder = new ArrayList<Customer>();
    
    
    
    public static ArrayList<String> getCustomers(){
        //Returnerar en ArrayList<String> som innehåller en presentation av bankens alla kunder (personnummer och namn)

        ArrayList<String> customer_data = new ArrayList<String>();
        for (int i = 0; i < kunder.size(); i++) {
            customer_data.add(kunder.get(i).getCustomer());
        }
        
        return customer_data;
    }
    public static boolean addCustomer(String name, long pNr){
        //Skapar upp en ny kund med namnet name samt personnumer pNr, kunden skapas endast om det inte 
        //finns någon kund med personnummer pNr. Returnerar true om kund skapades annars returneras false.
        if(kunder.size() > 0){
            for(Customer e : kunder){
                
                if(e.getPnr() == pNr){
                    System.err.println("A person with this Social Security Number already exists!"); //Fixa label med Error
                    return false;
            }
            }
            Customer kund = new Customer(name, pNr);
            data.add(kund);
            kunder.add(kund);
            return true; 
            }
        
        else{
            Customer kund = new Customer(name, pNr);
            data.add(kund);
            kunder.add(kund);
            
        }

        
        return true;
    }
    
    public static void resetSearch(){
        data.clear();
       
        MainpageController.data2.clear();
        for(Customer e: kunder){
            data.add(e);
            
        }
    }
    
    public static void createDataBase(){
       
       String DB_URL = "jdbc:mysql://localhost/bank";

       final String USER = "root";
       final String PASS = "root";
       
       Connection conn = null;
       Statement stmt = null;
       try{
      //STEP 2: Register JDBC driver
          Class.forName("com.mysql.jdbc.Driver");

          //STEP 3: Open a connection

          try{
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
          }
          catch(Exception e){
              DB_URL = "jdbc:mysql://localhost";
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql = "CREATE SCHEMA IF NOT EXISTS bank;";
              stmt.executeUpdate(sql);
              stmt.close();
              conn.close();
              
              DB_URL = "jdbc:mysql://localhost/bank";
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              
          }
          
          stmt = conn.createStatement();
          String sql;

          
          
          sql = "CREATE TABLE IF NOT EXISTS Customers(pNr INTEGER(45) NOT NULL UNIQUE, firstName VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, PRIMARY KEY(pNr));";
          stmt.executeUpdate(sql);
          
          sql = "CREATE TABLE IF NOT EXISTS Accounts(AccountNumber INTEGER(45) NOT NULL UNIQUE, AccountType VARCHAR(45), BelongsToId INTEGER(45), Balance INTEGER(45),"
                  + "InterestRate VARCHAR(45), Withdrawals INTEGER(45), FOREIGN KEY(BelongsToId) REFERENCES Customers(pNr));";
          stmt.executeUpdate(sql);
          
          sql = "CREATE TABLE IF NOT EXISTS Transactions(AccountNumber INTEGER(45) NOT NULL, Transaction VARCHAR(45), Amount INTEGER(45), DateOfWithdrawal VARCHAR(45),"
                  + " FOREIGN KEY(AccountNumber) REFERENCES Accounts(AccountNumber));";
          stmt.executeUpdate(sql);
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

    
    public static boolean searchCustomer(String name, long pNr){
        data.clear();
        boolean added = false;
        MainpageController.data2.clear();
        
        if(kunder.size() > 0){
        
        if(name.equals("") && pNr != 0){ //Search on pNr
            for(Customer e : kunder){
                if(e.getPnr() == pNr){
                    data.add(e);
                    if(e.getAllAccounts().size() > 0 && added == false){
                    MainpageController.data2.addAll(e.getAllAccounts());
                    }
                    added = true;
                    
                }
            }
        }
        else if(!name.equals("") && pNr == 0){ //Search on name
            for(Customer e : kunder){
                if(e.getName().toLowerCase().contains(name.toLowerCase())){
                    data.add(e);
                    if(e.getAllAccounts().size() > 0 && added == false){
                    MainpageController.data2.addAll(e.getAllAccounts());
                    }
                    added = true;
                    
                }
            }
        }
        else if(!name.equals("") && pNr != 0){ //Search on name and Pnr
            for(Customer e : kunder){
                if(e.getName().toLowerCase().contains(name.toLowerCase()) || e.getPnr() == pNr){
                    data.add(e);
                    if(e.getAllAccounts().size() > 0 && added == false){
                    MainpageController.data2.addAll(e.getAllAccounts());
                    }
                    added = true;
                }
            }
        }
        }
        if(data.size() == 0){
            return false;
            
        }
        return true;
    }
    public static ArrayList<String> getCustomer(long pNr){
        //Returnerar en List<String> som innehåller informationen om kunden inklusive dennes konton. 
        //Första platsen i listan är förslagsvis reserverad för kundens namn och personnummer sedan följer informationen om kundens konton.
        ArrayList<String> Customers = new ArrayList<String>();
        String toFormat = "";
        if(kunder.size() > 0){
            for(Customer e : kunder){
                
                if(e.pNr == pNr){
                    toFormat += "Customer name: " + e.getName() + ", pNr: " + Long.toString(e.pNr);
//                Customers.add("Customer name: " + e.getName());
//                Customers.add("pNr: " + Long.toString(e.pNr));
                if(e.getAllAccounts().size() > 0){
//                    Customers.add("Accounts:");
                    toFormat += ", Accounts: ";
                    for(Account acc : e.getAllAccounts()){
                        toFormat += acc.getAccount();
                        
                        
                    }
                }
                
                }
            }
        }
        
        Customers.add(toFormat);
        
        return Customers;
        
    }
    public static boolean changeCustomerName(String name, long pNr){
        //Byter namn på kund med personnummer pNr till name, returnerar true om namnet ändrades annars returnerar false (om kunden inte fanns).
        if(kunder.size() > 0){
            for(Customer e : kunder){
                
                if(e.pNr == pNr){
                    e.setName(name);
                    
                    return true;
                    
                }
            }
        }
        return false;
    }
    
    public static ArrayList<String> removeCustomer(long pNr){
        //Tar bort kund med personnummer pNr ur banken, alla kundens eventuella konton tas också bort och resultatet returneras.
        //Listan som returneras ska innehålla information om alla konton som togs bort, saldot som kunden får tillbaka samt vad räntan blev.
           ArrayList<String> intro = new ArrayList<String>();
           
        Customer deletedCustomer = new Customer();
        ArrayList<Account> Safety = new ArrayList<>();
        ArrayList<Customer> duplicate = new ArrayList<>();
        ArrayList<String> removed = new ArrayList<>();
        
        String toReturn = "";
        
        Customer[] test = new Customer[kunder.size()];
        
        Customer temp = new Customer();
        
        int count = 0;
  
        
 

        //To Avoid a Exception that is explicit to Trying to modify a list that we are currently iterating upon
        //We have to conver to Arrays and then handle the values there of
        for(int i = 0; i < test.length ; i++){
            
            test[i] = kunder.get(i);
        }
        
        for(int i = 0; i < test.length; i++){
            if(test[i].getPnr() == pNr){
                 temp = test[i]; //Temp is the Customer
                 break;
            }
            count += 1;
        }
        
        
        
        Account[] test2 = new Account[temp.getAllAccounts().size()]; //test2 holds all the Accounts
        for(int i = 0 ; i < temp.getAllAccounts().size(); i++){
            test2[i] = temp.getAllAccounts().get(i);
            
//            removed.add(temp.getAllAccounts().get(i).toString());
        }
        
        
        
        for(int i = 0 ; i < test2.length ; i ++){
            
            String something = "";
            
            
            int withdrawn = test2[i].getAmmountOfWithdraws();
            
            
            something += withdrawn;
            
            removed.add(test2[i].toString() + " " + closeAccount(pNr, test2[i].getAccountNumber()) + "You have withdrawn: " + something + " times from this account");
            
            

            if(test2[i] instanceof CreditAccount){
                double balance = test2[i].getBalance();
                removed.add((balance < 0) ? "Debt Interest Rate: 7%)" : "Interest Rate: 0.5%)\n");
                
            }
            else{
                removed.add("Interest Rate: 1%)\n");
            }
            
        }
        
        System.out.println();
        System.out.println(removed + " This is the Returned value for Removed Customer");
   
        
        
        
        kunder.remove(temp);
        
        data.remove(temp);

    
        return removed;
    }
    
    public static int addSavingsAccount(long pNr){
        //Skapar ett konto till kund med personnummer pNr, returnerar kontonumret som 
        //det skapade kontot fick alternativt returneras –1 om inget konto skapades.
        for(Customer e : BankLogic.kunder){
            
            if(e.getPnr() == pNr){
                
                e.addAccounts(new SavingsAccount());
                
                return 1;
            }
        }

        return -1;
    }
    
    public static String getAccount(long pNr, int accountId){
        //Returnerar en String som innehåller presentation av kontot med kontonnummer accountId som tillhör kunden pNr 
        //(kontonummer, saldo, kontotyp, räntesats).
           for (Customer client : kunder) {
            if (client.getPnr()== pNr) {
                for (Account account : client.getAllAccounts()) {
                    if (account.getAccountNumber()== accountId) {
                        if(account instanceof SavingsAccount){
                        return account.getAccount();
                        }
                        else{
                            CreditAccount to_return = ((CreditAccount)account);
                            String something = to_return.getAccount();
                            return something;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static boolean deposit(long pNr, int accountId, double amount){
        //Gör en insättning på konto med kontonnummer accountId som tillhör kunden pNr, returnerar true om det gick bra annars false.
         for (Customer person : kunder) {
            if (person.getPnr()== pNr) {
                
                for (Account account : person.getAllAccounts()) {
                    if (account.getAccountNumber()== accountId) {
                        
                        account.deposit(amount);
                        return true;
                    }
                }
            }
 
        }
        return false;
    }
    
    public static boolean withdraw(long pNr, int accountId, double amount){
        //Gör ett uttag på konto med kontonnummer accountId som tillhör kunden pNr, returnerar true om det gick bra annars false.
        boolean check = false;
         for (Customer client : kunder) {
             
            if (client.getPnr()== pNr) {
                
                for (Account account : client.getAllAccounts()) {
                    
                    if (account.getAccountNumber()== accountId) {
                        
                        if(account instanceof CreditAccount){
                            
                           
                                
                               
                                ((CreditAccount)account).withdraw(amount);
                                check = true;
                                break;
                                
                        
                        }
                        if(account instanceof SavingsAccount){
                            
                        
                            ((SavingsAccount)account).withdraw(amount);
                             
                             check=true;
                             break;
                            }
                        }
                    }
                }
            }
        
         
        if(check){
            return true;
        }
        return false;
    }
    
    
    
    public static String closeAccount(long pNr, int accountId){
        //Stänger ett konto med kontonnummer accountId som tillhör kunden pNr, presentation av kontots saldo samt ränta på pengarna ska genereras.
        String SomethingReturn = "";
        Double roundOff;
        for (Customer client : kunder) {
            if (client.getPnr()== pNr)
                for (Account acc : client.getAllAccounts()) {
                    
                    if (acc.getAccountNumber()== accountId) {
                        try{
                        
//                        String data = account.getAccount();
                     //acc.closeCurrentAccount(acc); returns String
//                        client.getAllAccounts().remove(account);
                        if(acc instanceof CreditAccount){
                                    //presentation av kontots saldo samt ränta på pengarna ska genereras.

                                    //number is the Interest rate
                        double number = (acc.getBalance() < 0) ? acc.getBalance()*1.07 : acc.getBalance()*1.005;
                        
                        
                        if(acc.getBalance() < 0){
                        
                        SomethingReturn += "Your interest is: -" + (acc.getBalance() - acc.getBalance()*1.07) + " kr,";

                        }
                        else{
                            roundOff = Math.round(((acc.getBalance()*1.005) - acc.getBalance()) * 100.0) / 100.0;
                            
                            SomethingReturn += "Your interest is: " + roundOff + " kr,";
                        }
                        roundOff = Math.round(number * 100.0) / 100.0;
                        
                        SomethingReturn += " Your total Saldo upon closure is : " + roundOff + " kr. ";
                        
                        
                        
                        
                        
                        client.getAllAccounts().remove(acc);
                        
                        MainpageController.data2.remove(acc);
                        System.out.println(SomethingReturn);

                        return SomethingReturn;

                        }
                        if(acc instanceof SavingsAccount){
                            
                            roundOff = Math.round(((acc.getBalance()*1.01) - acc.getBalance()) * 100.0) / 100.0;
                            SomethingReturn += "Your total interest is: " + ((acc.getBalance()*1.01) - acc.getBalance()) + " kr,";

                            

                            SomethingReturn += " Your total Saldo upon closure, is : " + acc.getBalance()*1.01 + " kr.";


                        }
        
                        
                            client.getAllAccounts().remove(acc);
                        
                        MainpageController.data2.remove(acc);
                        
                       
//                        return "Account close" + data;
                        System.out.println(SomethingReturn);
                        return SomethingReturn;
                        }
                        catch(Exception e){
                            System.out.println(e + " This was the error");
                        }
                    }
                }
        
        
    }
        return "Account does not exist";
    }
    
    public static int addCreditAccount(long pNr){
        //Skapar ett kreditkonto till kund med personnummer pNr och returnerar kontonumret som det skapade 
        //kontot fick (alternativt returneras -1 om inget konto skapades)
         for (int i = 0; i < kunder.size(); i++) {
            if (kunder.get(i).getPnr()== pNr) {
                kunder.get(i).addAccounts(new CreditAccount());
                return kunder.get(i).getLastAccountNr();
            }
        }
        return -1;
    }
    
    public static ArrayList<String> getTransactions(long pNr, int accountId){
        int count = 0;
        ArrayList<String> toReturn = new ArrayList<String>();
         for (Customer client : kunder) {
            if (client.getPnr()== pNr)
                for (Account account : client.getAllAccounts()) {
                    if (account.getAccountNumber()== accountId) {
                        
                        if(account instanceof SavingsAccount){
                            MainpageController.isSavingsAccount = true;
                            
                            toReturn.add("Kontonummer: " + account.getAccountNumber() + " " + account.getAcct_type());
                        }
                        else{
                            
                            MainpageController.isCreditAccount = true;
                            double interest = (account.getBalance() < 0) ? -7 : 0.5;
                            toReturn.add("Kontonummer: " + account.getAccountNumber() + " " + account.getAcct_type());
                        }
                        for(Transactions a : account.getTransactionsList()){
                            if(count > 0){
                                if(account instanceof CreditAccount){
                                double interest = (account.getBalance() < 0) ? -7 : 0.5;
                                toReturn.add("Kontonummer: " + account.getAccountNumber() + " " + account.getAcct_type());
                            }
                            }
                            toReturn.add(a.toString());
                            count += 1;
                        }
                        return toReturn;
                    }
                }
        }
         toReturn.add("There have been no transactions for this account, yet.");
        return toReturn;
    }
     public static void InitilizeList(){
          
      
        for(int i = 0; i < BankLogic.kunder.size();i++){
            MainpageController.data.add(BankLogic.kunder.get(i));
            
        }
           
        }
    
    
    //Programmet ska göras så stabilt som möjligt, även om användaren missförstår instruktionerna ska han/hon inte lyckas krascha programmet i första taget. 
    //Glöm heller inte att hantera om man försöker spara till en fil som är skrivskyddad eller om man försöker öppna en korrupt fil.
    
    //Fix writing method
    
    //När man tar bort ett konto skall räntan beräknas som saldo multiplicerat med ränta/100. Saldo och ränta ska visas på skärmen vid avslutande av kontot.
    //OBS! Enda gången ränta läggs på är alltså när kontot tas bort eftersom årsskiften inte hanteras i denna version av systemet. 
    //Alla transaktioner förutsätts ske under ett och samma kalenderår.
}
