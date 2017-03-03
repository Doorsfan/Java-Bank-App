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
 * @author Micke
 */
public class TransactionsController implements Initializable {
    @FXML
    private Label acc;
    @FXML
    private Button cancelButton;
    @FXML
    private ListView listView1;
    private final ObservableList
            currentlist=FXCollections.observableArrayList();
    
    @FXML
    private void cancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get values to display in Listview, 
        
 
        
        
        int count = 0;
        
        for(String e : BankLogic.getTransactions(MainpageController.pNr,accountnumber)){
            
            if(count != 0 ){
                currentlist.add(e + "\n");
            }
            
            count += 1;
        }
        
        
        
        listView1.setItems(currentlist);
   
    }
    
}
