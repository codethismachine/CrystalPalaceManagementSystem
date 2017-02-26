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
import javafx.stage.Stage;

/**
 *
 * @author Jason
 */
public class MainController implements Initializable {
//    
//    @FXML
//    private Label label;
//    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
//    
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
 private void handleButtonAction(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     if(event.getSource()==login){
        //get reference to the button's stage         
        stage=(Stage) login.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("EmployeeMainScreen.fxml"));
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
     else{
       stage=(Stage) cancelButton.getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
      }
     //create a new scene with root and set the stage
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
