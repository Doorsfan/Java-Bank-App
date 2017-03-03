/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

/**
 *
 * @author Micke
 */
public class SavingsAccount extends Account{
    
     private boolean  firstWithdrawal=false;
     
    private boolean withdrawn = false;
    private double  interest = 1.01;
    private String accountType = "Savingsaccount";
    private int kontonummer = getAccountNumberCounter() - 1;
     //Ett sparkonto ska ha ; saldo, interest,kontoNummer, kontoTyp, withdrawn
    
        public SavingsAccount(String accountType, double balance, int kontonummer) { 
            
  
           super (accountType, balance);
           
           
            firstWithdrawal=false;
            
            
            
//            transactionsList.add("Savings account named: " + accountType + " was created: " + date.toString() + " Balance: " + balance);
            

      
        setAcct_type("Savingsaccount");
        

    }
     public SavingsAccount(){

        super();
        setAcct_type("Savingsaccount");
        

          }
     
     
        public void closeCurrentAccount() {

       

        }
        public boolean withdraw(double value) {
            
            if(balance >= value){
              
              if(firstWithdrawal==false){
                  super.ammountOfWithdraws += 1;
                  balance-= value;
                balance = Math.round(balance * 100.0) / 100.0;
               
                try{
                    Transactions trans = new Transactions(getAccountNumber(), "Withdrawal: ", value, getBalance(), Transactions.Date());

                    transactionsList.add(trans);
                   
                }
                catch(Exception e){
                    System.out.println(e + " Error in Savings Account");
                }
                firstWithdrawal=true;
                return true;
              }else{
                if (balance>=1.02*value){
                  balance-=1.02*value;
                  balance = Math.round(balance * 100.0) / 100.0;
                
                  Transactions trans = new Transactions(getAccountNumber(), "Withdrawal: ", value*1.02, getBalance(), Transactions.Date());

            transactionsList.add(trans);
               
                super.ammountOfWithdraws += 1;
                return true;
                } else{
                  System.out.println("Not enough balance amount. Deposit some value.\n"); //Change to a Label
                }
              }
              
              
            }else {
              System.out.println("Not enough balance amount. Deposit some value.\n"); //Change to a Label
              return false;
            }
            return false;
          }


    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

   

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

  

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public boolean getWithdrawn(){
        return this.withdrawn;
    }

    public int getKontoNummer() {
        return kontonummer;
    }
    
    public double getSaldo() {
        return balance;
    }

    public void setSaldo(double saldo) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        //Listan som returneras ska innehålla information om alla konton som togs bort, saldot som kunden får tillbaka samt vad räntan blev.
        String build = "";
        
        build += "SavingsAccount(KontoNummer: " + kontonummer + ", ";
        
        return build;
    }
    
}
