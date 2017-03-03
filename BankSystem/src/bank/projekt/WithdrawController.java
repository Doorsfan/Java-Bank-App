/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

import static bank.projekt.MainpageController.accountnumber;
import static bank.projekt.MainpageController.data2;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Conrad
 */
public class WithdrawController implements Initializable {

    @FXML
    private Button withdrawButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField amountField;

    @FXML
    private void cancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();

    }
       @FXML
    private void withdraw(ActionEvent event) throws IOException {
        try{
            amountField.setText(amountField.getText().replaceAll("[^0-9]", ""));
            if(amountField.getText().length() == 0){
                throw new Exception();
            }
        double amount = Double.parseDouble(amountField.getText());
        BankLogic.withdraw(MainpageController.pNr, accountnumber, amount);
        MainpageController.withdrawAmount = 0;
        Stage stage = (Stage) withdrawButton.getScene().getWindow();
        stage.close();

    }
        catch(Exception e){
            System.err.println("That is not a valid input. Please try again.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
