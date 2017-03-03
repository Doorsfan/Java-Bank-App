/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

import static bank.projekt.MainpageController.accountnumber;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Micke
 */

public class WriteToFileController implements Initializable {
    
    @FXML
    private Label ErrorText;
    @FXML
    private Button override;
    @FXML
    private Button SelectFile;
    @FXML
    private Button SaveDestination;
    @FXML
    private Button cancelButton;
    @FXML
    private Button TransactionsCustomer;
    @FXML
    private Button TransactionsAllCustomers;
    @FXML
    private Button writeCustomer;
    @FXML
    private Button writeAllCustomers;
    
    @FXML
    private Label labelText;
    
    
    
    
    
    private String input;
    
    Path file2;
    
    private boolean Overwrite = false;
    
    private Desktop desktop = Desktop.getDesktop(); //Needed for the File Chooser part
    
    File file;
    
    
    @FXML
    private void changeOverWrite(ActionEvent event) throws IOException{
        if(!Overwrite){
            override.setText("Overwrite: ON"); //If it's on, the Datastream will overwrite, instead of appending
            Overwrite = true;
        }
        else{
            override.setText("Overwrite: OFF"); //If it's off, the DataStream will append, instead of overwriting
            Overwrite = false;
            
        }
    }
    @FXML
    private void cancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();

    }
    @FXML
    private void SelectFile(ActionEvent event) throws IOException {
         final FileChooser fileChooser = new FileChooser();
       
     
        Stage stage;
        Parent root;
        stage = new Stage();
        
        configureFileChooser(fileChooser);
        file = fileChooser.showOpenDialog(stage);
        if(file != null){
            MainpageController.special = file.getAbsolutePath();
            //Retrieve the Absolute path to see where the User must go for the Directory
            labelText.setText(MainpageController.special);
            MainpageController.special = MainpageController.special.replaceAll("\\\\", "\\\\\\\\");
            //Since \ is a Escpae character for String sequences, we have to add extra ones when handling regex
            
       
             
    }
    }

    private static void configureFileChooser(
	    final FileChooser fileChooser) {
	        fileChooser.setTitle("Choose a Text File");
	        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	        //Sets the initial Directory

	        fileChooser.getExtensionFilters().addAll(
	            new FileChooser.ExtensionFilter("All Files", "*.*"), 
                        new FileChooser.ExtensionFilter("RTF", "*.RTF"), new FileChooser.ExtensionFilter(".DOC", "*.DOC"));
	             
	            //Extensions that allows us to Sort what kind of Files we want to show.
                    //Note, that .txt files include Shortcuts, such as to games etc.

	
	    }
    
   
    
    @FXML
    private void WriteTransactionsForCustomer(ActionEvent event) throws IOException{
        
        String toPrint = "\n";
        boolean pass = true;
        for(String e : BankLogic.getTransactions(MainpageController.pNr,accountnumber)){
            
           toPrint += e + "\n\n";
         
        }
        
        
        if(MainpageController.pNr == 0){ //No customer was selected
            ErrorText.setText("Transactions Error: No Customer was Selected.");
            pass = false;
        }
        if(toPrint.length() == 0 || toPrint.equals("\nThere have been no transactions for this account, yet.\n\n")){ //If there were no transactions for that customer so far, on that account
            ErrorText.setText("No Transactions for Selected Account");
            pass = false;
        }

        if(pass){
        if(file != null){ //A file has been selected, write to that one
            
            
        
       CreateTextFile.openFile(MainpageController.special, file, toPrint, Overwrite);
            ErrorText.setText("Writing succesful.");
        }
        else{
            file = new File(System.getProperty("user.dir") + "\\clients.txt"); //Otherwise, we create a new file claled Clients in the current working directory
            CreateTextFile.openFile(System.getProperty("user.dir") + "\\clients.txt", file, toPrint, Overwrite);
        }
    }
    }
    
    @FXML
    private void WriteTransactionsForCustomers(ActionEvent event) throws IOException{ //Iterates through every Customer, for every acc in the entire bank
        String toPrint = "";
        boolean pass = true;
        boolean pass2 = false;
        long number = 0;
        for(Customer c : BankLogic.kunder){
            for(Account accounts : c.getAllAccounts()){
                for(String e : BankLogic.getTransactions(c.getPnr(),accounts.getAccountNumber())){
                   toPrint += e + "\n\n";
                   if(e.contains("Withdrawal") || e.contains("Deposition")){
                       pass2 = true;
                   }
                }
            }
        }

        if(toPrint.length() == 0 || !pass2){ //If there were no transactions
            ErrorText.setText("No transactions were found to write.");
            pass = false;
        }
        if(pass){
        if(file != null){//A file has been selected, write to that one
       CreateTextFile.openFile(MainpageController.special, file, toPrint, Overwrite); //Write to the file
            ErrorText.setText("Writing succesful.");
        }
        else{
            file = new File(System.getProperty("user.dir") + "\\clients.txt"); //Otherwise, we create a new file claled Clients in the current working directory
            CreateTextFile.openFile(System.getProperty("user.dir") + "\\clients.txt", file, toPrint, Overwrite); //Write to the file
        }
    }
    }
    

    
    @FXML
    private void WriteCustomer(ActionEvent event) throws IOException{
        String toPrint = "";
        boolean pass = true;
        if(MainpageController.pNr == 0){ //No customer was selected
            ErrorText.setText("Writing Error: No valid Customer Chosen.");
            pass = false;
        }
        if(pass){
            for(Customer e : BankLogic.kunder){
                if(e.getPnr() == MainpageController.pNr){
                    String name = e.getName();
                    name = capitalize.capitalize(name);
                    toPrint += "pNr: " + MainpageController.pNr + ", Name and Last Name : " + name;
                    
                    
                }
            }
        if(file != null){//A file has been selected, write to that one
      CreateTextFile.openFile(MainpageController.special, file, toPrint, Overwrite); //Write to the file
            ErrorText.setText("Writing succesful.");
        }
        else{
            file = new File(System.getProperty("user.dir") + "\\clients.txt"); //Otherwise, we create a new file claled Clients in the current working directory
            CreateTextFile.openFile(System.getProperty("user.dir") + "\\clients.txt", file, toPrint, Overwrite); //Write to the file
        }
        }
    }
    
    @FXML
    private void WriteCustomers(ActionEvent event) throws IOException{
        String toPrint = "";
        boolean pass = true;
        
        if(BankLogic.kunder.size() == 0){ //No customer was selected
            ErrorText.setText("There are no registered customers.");
            pass = false;
        }
        if(pass){
            for(Customer e : BankLogic.kunder){
                
                
                    String name = e.getName();
                    name = capitalize.capitalize(name);
                    toPrint += "pNr: " + e.getPnr() + ", Name and Last Name : " + name + "\n";
                    
                
            }
        
        if(file != null){//A file has been selected, write to that one
            
        CreateTextFile.openFile(MainpageController.special, file, toPrint, Overwrite); //Write to the file
        
        ErrorText.setText("Writing succesful.");
        
        }
        else{
            file = new File(System.getProperty("user.dir") + "\\clients.txt"); //Otherwise, we create a new file claled Clients in the current working directory
            CreateTextFile.openFile(System.getProperty("user.dir") + "\\clients.txt", file, toPrint, Overwrite); //Write to the file
        }
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelText.setText(System.getProperty("user.dir") + "\\clients.txt"); //Set value for Label to denote standard directory
    }

}

