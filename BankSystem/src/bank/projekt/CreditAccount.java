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
public class CreditAccount extends Account{
    private String accountType = "KreditKonto";
    private int kontoNummer = getAccountNumberCounter() - 1;
   
    private double saldo = 1000;
    private double debt_interest = 0.07; //7% debt interest
    private double interest = 0.005; //0.5% positive interest
    private double limit = -5000.0;
    
    
    //Ett kreditkonto ska ha ; saldo, kontoNummer, interest, skuldInterest,  kontoTyp, kreditGräns
    public CreditAccount(){
        
        this.saldo = saldo;
        this.accountType = accountType;
        this.debt_interest = debt_interest;
        this.interest = 0.005;
        this.limit = limit;
        this.kontoNummer = kontoNummer;
        
        
    }
 

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getKontoNummer() {
        return kontoNummer;
    }

    public void setKontoNummer(int kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getDebt_interest() {
        return debt_interest;
    }

    public void setDebt_interest(double debt_interest) {
        this.debt_interest = debt_interest;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
    
    
    @Override
    public boolean withdraw(double value) {
        
        if((super.getBalance() - value) < -5000){
            return false;
        }
        else{
            
            super.setBalance(super.getBalance() - value);
            super.ammountOfWithdraws += 1;
            Transactions trans = new Transactions(getAccountNumber(), "Withdrawal: ", value, super.getBalance(), Transactions.Date());

            transactionsList.add(trans);
//            transactionsList.add("Time: " + date.toString() + "\nWithdrawal: " + value + " kr. New balance: " + super.getBalance() + " kr.");
            return true;
        }

    }
    
    public String getAccount(){
        String build = "CreditAccount(";
        //(kontonummer, saldo, kontotyp, räntesats).
        build += "KontoNummer: " + kontoNummer + ", Saldo: " + balance;
        build += (balance < 0) ? ", 7% Skuldränta" : ", 0.5% Ränta)";
        return build;
    }

    @Override
    public String toString() {
//        String build = "";
//        
//        build += "SavingsAccount(KontoNummer: " + kontonummer + ", ";
//        build+= "Interest Rate: 1%, ";
//        build+= "Interest: " + (balance*interest) + ", ";
//        build += "Total Balance: " + balance;
//        
//        
//        return build;
        String build = "CreditAccount{";
        build += "KontoNummer: " + kontoNummer + ", ";
         return build;
        
      
    }
    
    
    
}
