/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikael.rusin.jvsm2.inlämning.pkg2.uppgift.pkg2;

import java.time.LocalDateTime; //Import the LocalDateTime to find out the current time and date etc.
import java.util.Scanner; //Import the scanner for inputs

public class MikaelRusinJVSM2Inlämning2Uppgift2 { //Main class
    public static void main(String[] args) { //Main method for running stuff
        
        System.out.print("What is your name: "); //Ask for their name
        Scanner sc = new Scanner(System.in); //initialize the Scanner
        String name = sc.next(); //Assign the name of whatever the next input is
        Account acc = new Account(name, 200); //Create an object of the Account class that we'll use
        
        acc.showMessage(); //Acess the method of the Acc object to run the Application
        
    } 
}

class Account{
    private double balance; //Bank account balance, we will use this later
    private String name; //This is the name of the person
    
    public Account(String name, double balance){ //A constructor to give the information it needs
        this.balance = balance; //Your balance
        this.name = name; //Your name
        
    }
    public void showMessage(){ //The main function of the App to interact with the object.
        System.out.println(); //Formatting
        System.out.println("Hello " + this.name + ", Welcome to the Account Book!"); //Welcoming message

        Scanner sc = new Scanner(System.in); //Initialize Scanner for input
        
        System.out.println(); //Formatting
        
        boolean flag = false; //A boolean to keep the loop running as long as we need it to
        
        while(flag == false){ //Loop to repeat input and keep the app running until otherwise told
        System.out.println("What do you wish to do?"); //Ask what the purpose is
        System.out.println("Your balance as of (" + LocalDateTime.now() + ") is : " + this.balance); //Display their current balance with each iteraton
        System.out.println(); //formatting
        System.out.println("1. Deposit money!"); //Option 1: Deposit
        System.out.println("2. Withdraw money!"); //option 2 : Withdraw
        System.out.println("3. Exit"); //option 3 : Exit
        System.out.println(); //formatting
        int input = sc.nextInt(); //Ask for a input, so that we know what they want to do
        
        switch(input){ //Handling of the actual input from the user
            
            case 1: //First case, the user wants to deposit stuff
                //We display some information to the user, in regards to their balance and how much they wish to deposit.
                
                System.out.println("How much do you wish to deposit? Your current balance is: " + this.balance);
                System.out.println("(NOTE: You get a 5% interest rate on your deposits!)"); //inform about 5% interest rate on deposits
                depositAmount(sc.nextDouble());
                //We ask the user for a input of how much they wish to deposit, and do a function call to deposit it.
                break;
                
            case 2: //Second case, in case they wish to withdraw
                //Asking how much they wish to withdraw and then feed it to a method that handles the withdrawal
                System.out.println("How much do you wish to withdraw? Your current balance is: " + this.balance);
                withdrawAmount(sc.nextDouble());
                //The method call with how much we wish to withdraw
                break;
                
            case 3: //Third case, they wish to exit.
                
                System.out.println("Goodbye! Have a nice day!"); //Goodbye message
                flag = true; //Cancel the loop
                break; //Break to not go into default
                
            default: //If none of the above options have been chosen, this is the standard we give out
                System.out.println("That is an invalid option."); //information about invalid input
        }
        }
        
        
    }
    
    public void depositAmount(double amount){ //Method to handle the amount to deposit
        
        
        this.balance += (amount*1.05); //5% interest on the deposit amount
        System.out.println("After the deposit, your balance is " + this.balance + "!"); //Tel the user how much they have after depositing
        System.out.println(); //Formatting
        
    }
    
    public void withdrawAmount(double amount){ //method to handle the amount to withdraw
        if(amount <= this.balance){ //Checking to see if the amount is a valid amount compared to remainder
            this.balance -= amount; //Withdraw
            System.out.println("Your balance after the Withdrawal: " + this.balance); //Information about remaining balance
            System.out.println();
            
        }
        else{ //If we don't have enough to withdraw the selected amount
            System.out.println("You cannot withdraw more than you have on your account!"); //Inform about the error
            System.out.println();
        }
    }  
}
