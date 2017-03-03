/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

import static bank.projekt.MainpageController.accountnumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author Conrad
 */
public class AccInfoController implements Initializable {

   private final ObservableList
            currentlist=FXCollections.observableArrayList();
    @FXML
    private Button cancelButton;
    @FXML
    private ListView listView1;
    @FXML
    private Label title;

    @FXML
    private void cancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            
           
            String a;
        if(MainpageController.isCreditAccount){

            a ="Account type: CreditAccount";
            currentlist.add(a);


            if(MainpageController.saldo < 0){
                a = "Debt interest: 7%";
            }


            if(MainpageController.saldo >= 0){
                a = "Interest: 0.5%";
            }
            currentlist.add(a);

            a = "Credit limit: -5000";
            currentlist.add(a);

            a = "Balance: " + MainpageController.saldo;
            currentlist.add(a);
                
                
            
        }
        else if (MainpageController.isSavingsAccount){
          
                    a ="Account type: SavingsAccount";
                    currentlist.add(a);
                    
                    
            
                    if(!MainpageController.withdrawn){
                        a = "Number of free withdrawals left: 1";
                        currentlist.add(a);
                        a = "Withdraw Fee: 0%";
                        currentlist.add(a);
                        
                    }
                    else{
                        a = "Number of free withdrawals left: NONE";
                        currentlist.add(a);
                        a = "Withdraw Fee: 2%";
                        currentlist.add(a);
                    }
                    
              
                    a = "Interest rate: 1%";
                    currentlist.add(a);
                    
                    a = "Balance: " + MainpageController.saldo;
                    currentlist.add(a);
               
                     
                
                
                 
        }
        listView1.setItems(currentlist);
    }
    
    catch(Exception e){
    System.out.println(e);
}
    }
}
