/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

import static bank.projekt.RemoveComfirmController.removeChecker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Conrad
 */
public class MainpageController implements Initializable {

    public static boolean isSavingsAccount = false;
    public static boolean isCreditAccount = false;
    public static int withdrawAmount = 0;
    public static double depositamount = 0;

    static double saldo;
    static boolean withdrawn;
    static int index;
    static long pNr;
    static int accountnumber;
    static String removeAction = "";
    static String special = System.getProperty("user.dir") + "\\clients.txt";
    
    @FXML
    private Button SaveToData;
    @FXML
    private Button LoadFromData;
    @FXML
    private Button WriteToFile;
    @FXML
    private Label labelText;
    @FXML
    private Button regButton;
    @FXML
    private Button TransactionsButton;
    @FXML
    private Button addSaveAccButton;
    @FXML
    private Button addCredAccButton;
    @FXML
    private Button removeCustomerButton;
    @FXML
    private Button removeAccountButton;
    @FXML
    private Button changeCustomerButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button depositButton;
    @FXML
    private Button infoButton;
    @FXML
    private Button search;
    @FXML
    private TableView<Customer> table1;
    @FXML
    private TableView<Account> table2;
    @FXML
    private TableColumn<Account, String> accountTypeColumn;
    @FXML
    private TableColumn<Account, Integer> idColumn;
    @FXML
    private TableColumn<Account, Double> balanceColumn;
    @FXML
    private TableColumn<Customer, String> NameColumn;
    @FXML
    private TableColumn<Customer, Long> pnrColumn;
    @FXML
    private TableColumn<Account, Integer> withdrawColumn;
    @FXML
    private TextField nameInputSearch;
    @FXML
    private TextField pnrInputSearch;
    @FXML
    private Button reset;

    //Table data
    public static ObservableList<Customer> data = FXCollections.observableArrayList();
    public static ObservableList<Account> data2 = FXCollections.observableArrayList();

    @FXML
    private void addCustomer(ActionEvent event) throws IOException {
        
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("RegCustomer.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Register new customer");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(regButton.getScene().getWindow());
        stage.showAndWait();
        
        labelText.setText("");
        
    }
    
    @FXML
    private void ListSelected(MouseEvent event) throws IOException{
       
        if (!(table1.getSelectionModel().getSelectedIndex() == -1)) {
            data2.clear();
            for(Account a: table1.getSelectionModel().getSelectedItem().getAllAccounts()){
                data2.add(a);
            }
            
            pNr = table1.getSelectionModel().getSelectedItem().getPnr();
        }
        
        
    }
    @FXML
    private void reset(ActionEvent event) throws IOException{
        BankLogic.resetSearch();
        nameInputSearch.setText("");
        pnrInputSearch.setText("");
        
        //Fix so that Accounts are snapped to closest value after closing Account for Customer
    }
    
    @FXML
    private void TransactionHistory(ActionEvent event) throws IOException{
        //Do something
        if(!(table2.getSelectionModel().getSelectedIndex() == -1) && table2.getSelectionModel().getSelectedItem().getTransactionsList().size() > 0){
            accountnumber = table2.getSelectionModel().getSelectedItem().getAccountNumber();
            String keep = "";
            
            for(Customer e : BankLogic.kunder){
                for(Account acc : e.getAllAccounts()){
                    if(acc.getAccountNumber() == accountnumber){
                        pNr = e.getPnr();
                        if(acc instanceof CreditAccount){
                            keep = "CreditAccount";
                            isCreditAccount = true;
  
                        }
                        else{
                            keep = "SavingsAccount";
                            isSavingsAccount = true;
                        }
                        break;
                    }
                }
            }
            
            Stage stage;
            Parent root;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("Transactions.fxml"));
            stage.setScene(new Scene(root));
            
            
            if(keep.equals("SavingsAccount")){
                stage.setTitle("TransactionHistory for SavingsAccount AccountNr: " + accountnumber);
            }
            else if(keep.equals("CreditAccount")){
                stage.setTitle("TransactionHistory for CreditAccount AccountNr: " + accountnumber);
            }

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(removeCustomerButton.getScene().getWindow());
            stage.showAndWait();
            labelText.setText("");
           
        }
        
        else{
            labelText.setText("Please select an Account to show Transactions for.");
        }
        
    }
    @FXML
    private void removeCustomer(ActionEvent event) throws IOException {

        if (!(table1.getSelectionModel().getSelectedIndex() == -1)) {
            removeAction = "Remove Customer";
            Stage stage;
            Parent root;
            pNr = BankLogic.kunder.get(table1.getSelectionModel().getSelectedIndex()).getPnr(); //The Persons Number
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("RemoveComfirm.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Comfirm");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(removeCustomerButton.getScene().getWindow());
            stage.showAndWait();
            

//            }
            removeChecker = false;
            labelText.setText("");
            


        } else {
            labelText.setText("Please choose a customer");

 
        }
    }
    @FXML
    private void addCredAcc(ActionEvent event){
        if (!(table1.getSelectionModel().getSelectedIndex() == -1)){
        CreditAccount test = new CreditAccount();
        
        BankLogic.kunder.get(table1.getSelectionModel().getSelectedIndex()).addAccounts(test);
        
        data2.add(test);
        table2.setItems(data2);
        labelText.setText("");
        }
        else{
            labelText.setText("Please choose a customer");
        }
        
    }
    
    
    

  
    @FXML
    private void addSaveAcc(ActionEvent event) throws IOException{
        if (!(table1.getSelectionModel().getSelectedIndex() == -1)){
           try{
               
           
        
           
            BankLogic.addSavingsAccount(BankLogic.kunder.get(table1.getSelectionModel().getSelectedIndex()).getPnr());
            for(Customer e : BankLogic.kunder){
                
            }
            data2.add(BankLogic.kunder.get(table1.getSelectionModel().getSelectedIndex()).getLastAccount());
            table2.setItems(data2);
            labelText.setText("");
            
           }
           catch(Exception e){
               System.out.println(e + " Error in adding account");
           }
        }
            else {
            labelText.setText("Please choose a customer");

 
        }
       
        
    }
    @FXML
    private void changeCustomer(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("ChangeCustomer.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Modify customer");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(changeCustomerButton.getScene().getWindow());
        stage.showAndWait();
        labelText.setText("");

    }

    @FXML
    private void accountInfo(ActionEvent event) throws IOException {
        try{
            if (!(table2.getSelectionModel().getSelectedIndex() == -1)) {
                saldo = table2.getSelectionModel().getSelectedItem().getBalance();
            Account something = table2.getSelectionModel().getSelectedItem();  
            if (something instanceof SavingsAccount) {
                isSavingsAccount = true;
                
                withdrawn = (something.getAmmountOfWithdraws() > 0);
 
            } 
            else if(something instanceof CreditAccount) {
                isCreditAccount = true;
                
              
            }
            
            Stage stage;
            Parent root;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("AccountInfo.fxml"));
            stage.setScene(new Scene(root));
            
            if(something instanceof CreditAccount){
                accountnumber = ((CreditAccount)something).getAccountNumber();
                stage.setTitle("Credit Account Nr " + accountnumber);
            }
            else{
                accountnumber = ((SavingsAccount)something).getAccountNumber();
                stage.setTitle("Savings Account Nr " + accountnumber);
            }
            labelText.setText("");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(infoButton.getScene().getWindow());
            stage.showAndWait();
            isCreditAccount = false;
            isSavingsAccount = false;
            }
            
            else{
                labelText.setText("Please choose an account.");
            }
        }
            
        catch(Exception e){
            labelText.setText("Please choose an account.");
        }
            
            
            
            
            
//        } else {
//            labelText.setText("Please choose an account");
//
//        }
        }
        

    

    @FXML
    private void removeAccount(ActionEvent event) throws IOException {
        try{
            removeAction = "Remove Account";
            accountnumber = table2.getSelectionModel().getSelectedItem().getAccountNumber();
        
        pNr = BankLogic.kunder.get(table1.getSelectionModel().getSelectedIndex()).getPnr();
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("RemoveComfirm.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Comfirm");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(removeAccountButton.getScene().getWindow());
        stage.showAndWait();
        
        table2.setItems(data2);
        
        labelText.setText("");
            
        }
        catch(Exception e){
            labelText.setText("Please choose an account");
        }
        
        
        
        
    }

    @FXML
    private void withdraw(ActionEvent event) throws IOException {
        if (!(table2.getSelectionModel().getSelectedIndex() == -1)) {
            pNr = BankLogic.kunder.get(table1.getSelectionModel().getSelectedIndex()).getPnr();
            
            
            accountnumber = table2.getSelectionModel().getSelectedItem().getAccountNumber();
            
            
            Stage stage;
            Parent root;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("Withdraw.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Withdraw");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(withdrawButton.getScene().getWindow());
            stage.showAndWait();
            
            table2.getSelectionModel().getSelectedItem().setBalance(table2.getSelectionModel().getSelectedItem().getBalance());
            table2.getSelectionModel().getSelectedItem().setAmmountOfWithdraws(table2.getSelectionModel().getSelectedItem().getAmmountOfWithdraws());
            
            
     
          
            
            //Set the i-th item
            table2.getItems().set(table2.getSelectionModel().getSelectedIndex(), table2.getSelectionModel().getSelectedItem());
            
            labelText.setText("");
         
            
            

            
            
        } else {
            labelText.setText("Please choose an account");

        }

    }

    @FXML
    private void deposit(ActionEvent event) throws IOException {
        if (!(table2.getSelectionModel().getSelectedIndex() == -1)) {
            pNr = BankLogic.kunder.get(table1.getSelectionModel().getSelectedIndex()).getPnr();
            
            
            accountnumber = table2.getSelectionModel().getSelectedItem().getAccountNumber();
            Stage stage;
            Parent root;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("Deposit.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Deposit");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(depositButton.getScene().getWindow());
            stage.showAndWait();

            

            table2.getSelectionModel().getSelectedItem().setBalance(table2.getSelectionModel().getSelectedItem().getBalance());
     
          
            
            //Set the i-th item
            table2.getItems().set(table2.getSelectionModel().getSelectedIndex(), table2.getSelectionModel().getSelectedItem());
            labelText.setText("");
            
        }
        {
            labelText.setText("Please choose an account");

        }

    }
    
    @FXML
    private void writeToFile(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("WriteToFile.fxml"));
        labelText.setText("");
        stage.setScene(new Scene(root));
        stage.setTitle("Write to a File");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(WriteToFile.getScene().getWindow());
        stage.showAndWait();
        
        
        
    }
    
    @FXML
    public void LoadFromData(ActionEvent event) throws IOException{
        Repository.WithdrawAll();
    }
    
    @FXML
    public void SaveFromData(ActionEvent event) throws IOException{
        
        Repository.DepositAll();
    }

    @FXML
    public void search(ActionEvent event) throws IOException {
        
        boolean error = false;
        boolean error2 = false;
        try{String name = nameInputSearch.getText();
        
        String test2 = nameInputSearch.getText();
        
        String test3 = test2.replaceAll("[^0-9]", "");
        if(test3.length() > 0){
            error2 = true;
        }
        name = name.replaceAll("[^a-zA-Z ]", "");
        nameInputSearch.setText(name);
        
        
        String pNr = pnrInputSearch.getText();
        try{
            if(pNr.length() > 0){
                long test = Long.parseLong(pNr);
            }
        }
        catch(Exception e){
            error = true;
        }
        
        String name2 = pNr.replaceAll("[^0-9]", "");
       
        long parse;
        if(name2.length() > 0 && name.length() > 0){
            pnrInputSearch.setText(name2);
            parse = Long.parseLong(name2);
            if(!BankLogic.searchCustomer(name, parse)){
                labelText.setText("No customer with that pNr and Name was found.");
            }
            else{
                labelText.setText("");
            }
        }
        else if(name2.length() == 0 && (!name.equals(""))){
            parse = 0;
            if(!BankLogic.searchCustomer(name, parse)){
                labelText.setText("No customer with that name was found.");
            }
            else{
                labelText.setText("");
            }
        }
        else if(name2.length() > 0 && (name.equals(""))){
            pnrInputSearch.setText(name2);
            parse = Long.parseLong(name2);
            if(!BankLogic.searchCustomer(name, parse)){
                labelText.setText("No customer with that pNr was found.");
            }
            else{
                labelText.setText("");
            }
        }
        else{
            BankLogic.resetSearch();
            nameInputSearch.setText("");
            pnrInputSearch.setText("");
            if(error && error2){ //Error1 is for Numbers field and Error2 is for the letters field
                labelText.setText("WARNING: Letters in the Numbers Section/Numbers in the Letters field are discarded.");
            }
            else if(error){ 
                labelText.setText("WARNING: Letters in the Numbers Section field are discarded.");
            }
            else if(error2){
                labelText.setText("WARNING: Numbers in the Letters field are discarded.");
            }
            else{
                labelText.setText("");
            }
            
        }
        
        
        }
        catch(Exception e){
            System.out.println(e + " Error in Search");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        NameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        pnrColumn.setCellValueFactory(new PropertyValueFactory<Customer, Long>("pnr"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountType"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("accountNumber"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));
        withdrawColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("ammountOfWithdraws"));
        BankLogic.InitilizeList();
        table1.setItems(data);
        table2.setItems(data2);
        
        
    }

}
