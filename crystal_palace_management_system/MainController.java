/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Jason
 */
public class MainController implements Initializable {
//    protected String username; 
//    protected String password;
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
    private TextField enterUsername, newEmpID, newEmpFirstName, newEmpLastName, 
      newHireYear, newHireMonth, newHireDay, newDeptID, newUsername;
    @FXML
    private PasswordField enterPassword, newPassword; 
    @FXML 
    private Button clockOut;
    
    @FXML
 private void handleButtonAction(ActionEvent event) throws IOException{
     Stage stage = null; 
     Parent root = null;
    //getLoginInfo();
     if(event.getSource()==login){
         String username = enterUsername.getText();
         String password = enterPassword.getText();
         boolean check = Crystal_Palace_Management_System.test.loginCheck(username, password);
         if(check == true){
             //get reference to the button's stage         
        stage=(Stage) login.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("EmployeeMainScreen.fxml"));
         }else{
             System.out.println("Incorrect Credentials! Please Try again.");
             stage=(Stage) login.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
         }
      }
     else if(event.getSource()==exit){
        //get reference to the button's stage         
        stage=(Stage) exit.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        System.exit(0);
      }
     else if(event.getSource()==register){
        //get reference to the button's stage         
        stage=(Stage) register.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
      }
      else if(event.getSource() == submitNewEmployee){
         stage = (Stage) submitNewEmployee.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
     }
       else if(event.getSource() == backSubResult){
         stage = (Stage) backSubResult.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
     }
        else if(event.getSource() == returnToMain){
         stage = (Stage) returnToMain.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EmployeeMainScreen.fxml"));
     }
         else if(event.getSource() == clockOut){
         stage = (Stage) clockOut.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
     }
     else{
       stage=(Stage) cancelButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
      }
     //create a new scene with root and set the stage
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    @FXML
    private void handleRegisterNewEmployee(ActionEvent event) throws IOException{
        Stage stage = null; 
        Parent root = null;
      if(event.getSource() == submitNewEmployee){
          String tempID = newEmpID.getText();
          int empID = Integer.parseInt(tempID);
          String fName = newEmpFirstName.getText();
          String lName = newEmpLastName.getText();
          int year = Integer.parseInt(newHireYear.getText());
          int month = Integer.parseInt(newHireMonth.getText());
          int day = Integer.parseInt(newHireDay.getText());
          int deptID = Integer.parseInt(newDeptID.getText());
          String username = newUsername.getText();
          String password = newPassword.getText();
          Crystal_Palace_Management_System.test.insertNewEmployee(empID, fName, lName, year, month, day, deptID, username, password);
        stage = (Stage) submitNewEmployee.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
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
