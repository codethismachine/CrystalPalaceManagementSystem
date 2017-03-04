/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Controller;

import crystal_palace_management_system.Crystal_Palace_Management_System;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jason
 */
public class InventoryInsertItemController implements Initializable {
 @FXML
 private TextField newItemID, newItemName, newItemQuantity, newItemDescription,
     newItemLocation;
 @FXML
 private Button submitNewItem, cancelButton;
 @FXML
 private Stage stage = null;
 @FXML
 private Parent root = null;
    
 @FXML
 private void handleButtonAction(ActionEvent event) throws IOException{
       if(event.getSource() == submitNewItem){
          String tempID = newItemID.getText();
          int itemID = Integer.parseInt(tempID);
          String itemName = newItemName.getText();
          String tempQuant = newItemQuantity.getText();
          int quantity = Integer.parseInt(tempQuant);
          String description = newItemDescription.getText();
          String location = newItemLocation.getText();
        if(Crystal_Palace_Management_System.invTable.insertNewItem(itemID, itemName, quantity, description, location)){
              System.out.println("Item added successfully!");
              stage = (Stage) submitNewItem.getScene().getWindow();
              //root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              tester.getNamespace().put("Test", "Item added successfully!");
              root = tester.load();
          }else{
              System.out.println("Item ID already exists!");
              stage = (Stage) submitNewItem.getScene().getWindow();
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              tester.getNamespace().put("Test", "Item ID already exists!");
              root = tester.load();
          }
    //create a new scene with root and set the stage
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
     }else if(event.getSource() == cancelButton){
         stage = (Stage) submitNewItem.getScene().getWindow();
         final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
         root = tester.load();
         //create a new scene with root and set the stage
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();     
     }     
 }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
