/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Controller;

import crystal_palace_management_system.Crystal_Palace_Management_System;
import crystal_palace_management_system.Controller.CheckInFoundViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class CheckInController implements Initializable {
 @FXML
 private TextField checkInLastName;
 @FXML
 private TextField checkInFirst;
 @FXML
 private TextField checkInPhone;
 @FXML
 private TextField checkInReservationNumber;
 @FXML
 private Button submitCheckIn, checkInBack;
 @FXML
 private Stage stage = null;
 @FXML
 private Parent root = null;
 public String reservationNumber;
 public String lastName;
 public String firstName;
 public String phoneNumber;
 @FXML
 private void handleButtonAction(ActionEvent event) throws IOException
 {
       if(event.getSource() == submitCheckIn){
          			  
		 
          stage=(Stage) submitCheckIn.getScene().getWindow();
          int attributeCounter = 0;
          reservationNumber = checkInReservationNumber.getText();
            lastName = checkInLastName.getText();
            firstName = checkInFirst.getText();
            phoneNumber = checkInPhone.getText();
           
           
        
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
        FXMLLoader tester = new FXMLLoader(getClass().getResource(""
            + "/crystal_palace_management_system/View/CheckInFoundView.fxml")); 
        
        
//          test.checkInViewer(reservationNumber, lastName, firstName, phoneNumber);
         root= tester.load();
         CheckInFoundViewController controller = tester.getController();
         controller.checkInViewer(reservationNumber, lastName, firstName,
                 phoneNumber);
         System.out.println(reservationNumber);
       
         }
       
      
         //create a new scene with root and set the stage
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
          }
 @FXML
    private void handleBack(ActionEvent event) throws IOException
    {        

         if(event.getSource()==checkInBack){
              if(Crystal_Palace_Management_System.empType.equals("A") ||
               Crystal_Palace_Management_System.empType.equals("M")){
             //get reference to the button's stage         
        stage=(Stage) checkInBack.getScene().getWindow();
        //load up OTHER FXML document
         FXMLLoader tester = new FXMLLoader(getClass().getResource(""
          + "/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
             root = tester.load();
             }else if(Crystal_Palace_Management_System.empType.equals("S")){
                 stage=(Stage) checkInBack.getScene().getWindow();
                 FXMLLoader tester = new FXMLLoader(getClass().getResource(""
           + "/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
                 root = tester.load();
               MainController controller = tester.getController();
                controller.register.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
             }
             else{
                  stage=(Stage) checkInBack.getScene().getWindow();
                 FXMLLoader tester = new FXMLLoader(getClass().getResource(""
          + "/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
                 root = tester.load();
               MainController controller = tester.getController();
                controller.viewEmployees.setDisable(true);
                controller.register.setDisable(true);
                controller.addInventory.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
                    //viewEmployees.setDisable(true);
             }
             //get reference to the button's stage         
//        stage=(Stage) checkInBack.getScene().getWindow();
//        //load up OTHER FXML document
//        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
//        FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
//         
//       
//         root= tester.load();
         
         }
         //create a new scene with root and set the stage
        
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
      }
          
         

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
}
