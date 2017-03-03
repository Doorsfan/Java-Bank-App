/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ULTRA
 */
public class Transactions {
    
 private int accountID; 
 private String day; 
 private String transactionType; 
 private double amount; 
 private double balance; 
 private String time;
 
 
public Transactions(int accountID, String transactionType, double amount, double balance, String day){

this.accountID = accountID; 
this.amount = amount; 
this.balance = balance;
this.transactionType = transactionType;
this.day = Date();
SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String current_time = time_formatter.format(System.currentTimeMillis());
this.time = current_time;

} 

public static String Date(){
Date currentdate = new Date(); 
SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd"); 
String s = sdf.format(currentdate); 
return s;
}

public String toString(){
    

 if(this.transactionType.equals("Withdrawal: ")){
    return (this.time + "\n" + this.transactionType +  " " + this.amount + " kr.\nPrevious balance was: " + (this.balance+this.amount) + " kr.\nYour New balance is: " + (this.balance) + " kr.");
}
 else{
return(this.time + "\n" + this.transactionType +  " " + this.amount + " kr.\nPrevious balance was: " + (this.balance-this.amount) + " kr.\nYour New balance is: " + (this.balance) + " kr.");
 }
}

    public int getAccountID() {
        return accountID;
    }

    public String getDay() {
        return day;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }



}


    
    

