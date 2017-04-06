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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Jason
 */
public class MainController implements Initializable {
    
    @FXML
    private Button login;
    @FXML
    private Button exit;
    @FXML
    private Button register;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitNewEmployee;
    @FXML
    private Button backSubResult;
    @FXML
    private Button returnToMain;
    @FXML
    private Button inventory;
    @FXML
    private Button addInventory;
    @FXML
    private Button viewEmployees;
    @FXML
    private Button viewReservations;
    @FXML
    private Button newReservation;
    @FXML
    private TextField enterUsername, newEmpID, newEmpFirstName, newEmpLastName, 
      newEmpMiddle, newHireYear, newHireMonth, newHireDay, newDeptID, 
      newEmpNickname, newEmpAddress, newEmpPhone, newEmpSSN, newUsername,
      newEmpWage, newEmpSalary ;
    @FXML 
    private DatePicker regDate, DOB, endDate;
    @FXML
    private PasswordField enterPassword, newPassword; 
    @FXML 
    private Button clockOut;
   @FXML
   private Stage stage = null;
   @FXML
   private Parent root = null;
    @FXML
    private InventoryController invCon = new InventoryController();
    @FXML
    private TableView inventoryView, inventoryViewTemp;
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private AnchorPane invAnchor, RegAnchorPane;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{ 
    //getLoginInfo();
     if(event.getSource()==login){
         String username = enterUsername.getText();
         String password = enterPassword.getText();
         boolean check = Crystal_Palace_Management_System.CPMS.loginCheck(username, password);
         if(check == true){
             //get reference to the button's stage         
        stage=(Stage) login.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
         }else{
             System.out.println("Incorrect Credentials! Please Try again.");
             stage=(Stage) login.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/LoginScreen.fxml"));
         }
      }
     else if(event.getSource()==exit){
        //get reference to the button's stage         
        stage=(Stage) exit.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Register.fxml"));
        System.exit(0);
      }
     else if(event.getSource()==register){
        //get reference to the button's stage         
        stage=(Stage) register.getScene().getWindow();
        //load up OTHER FXML document
       root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Register.fxml"));
         
      }
      else if(event.getSource() == submitNewEmployee){
         stage = (Stage) submitNewEmployee.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
     }
       else if(event.getSource() == backSubResult){
         stage = (Stage) backSubResult.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Register.fxml"));
     }
        else if(event.getSource() == returnToMain){
         stage = (Stage) returnToMain.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
     }
         else if(event.getSource() == clockOut){
         stage = (Stage) clockOut.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/LoginScreen.fxml"));
     }
     else{
       stage=(Stage) cancelButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
      }
     //create a new scene with root and set the stage
     
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();    
      
    }
    
    @FXML
    private void handleShowInventory(ActionEvent event) throws IOException{
         if(event.getSource()==inventory){
             //get reference to the button's stage         
        stage=(Stage) inventory.getScene().getWindow();
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
        FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));        
         root= tester.load();
         }
         //create a new scene with root and set the stage
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
      }
    
      @FXML
    private void handleShowEmployees(ActionEvent event) throws IOException{
         if(event.getSource()==viewEmployees){
             //get reference to the button's stage         
        stage=(Stage) viewEmployees.getScene().getWindow();
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
        FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/AllEmployees.fxml"));
         root= tester.load();
         
         }
         //create a new scene with root and set the stage
        
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
      }
    @FXML
    private void handleShowReservations(ActionEvent event) throws IOException{
        if(event.getSource() == viewReservations){
            stage = (Stage) viewReservations.getScene().getWindow();
             FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/ViewReservations.fxml"));
         root= tester.load();
         
         }
         //create a new scene with root and set the stage
        
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
        
    }
    @FXML
    private void handleAddInventory(ActionEvent event) throws IOException{
         if(event.getSource()== addInventory){
             //get reference to the button's stage         
        stage=(Stage) addInventory.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/InventoryInsertItem.fxml"));
        //final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
         
         }
         //create a new scene with root and set the stage
      Scene scene = new Scene(root);    
      stage.setScene(scene);
      stage.show();
      }
    @FXML
    private void handleNewReservation(ActionEvent event) throws IOException{
         if(event.getSource()== newReservation){
             //get reference to the button's stage         
        stage=(Stage) newReservation.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/NewReservation.fxml"));
        //final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
         
         }
         //create a new scene with root and set the stage
      Scene scene = new Scene(root);    
      stage.setScene(scene);
      stage.show();
      }
    public void getLoginInfo(){
      if((enterUsername.getText() != null && !enterUsername.getText().isEmpty())&&
          (enterPassword.getText() != null && !enterPassword.getText().isEmpty())){
          //username = enterUsername.getText();
          //password = enterPassword.getText();
      }else{
          System.exit(0);
      }
//      if((enterPassword.getText() != null && !enterPassword.getText().isEmpty())){
//          password = enterPassword.getText();
//      }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    }    
    
    
}
