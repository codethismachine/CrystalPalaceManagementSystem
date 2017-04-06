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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jason
 */
public class NewReservationController implements Initializable {
@FXML
 private TextField ReservationNumber, RoomNumber, NoteField, balance,
  totalAdults, totalChildren, multiReservation;
 @FXML
 private Button submitNewReservation, cancelButton;
 @FXML
 private DatePicker Start_Date, End_Date, checkIn, checkOut;
 @FXML
 private CheckBox cashCheckBox;
 @FXML
 private Stage stage = null;
 @FXML
 private Parent root = null;
 @FXML
 private void handleNewReservation(ActionEvent event) throws IOException{
       if(event.getSource() == submitNewReservation){
          String Reservation_Number = ReservationNumber.getText();
          
          String tempRoom_Number = RoomNumber.getText();
          int Room_Number;
          try{
              Room_Number = Integer.parseInt(tempRoom_Number);
          }catch(Exception e){
              Room_Number = -1;
          }
          String Note = NoteField.getText();
          LocalDate tempStartDate = Start_Date.getValue();
          String StartDate = tempStartDate.toString();
          LocalDate tempEndDate = End_Date.getValue();
          String EndDate = tempEndDate.toString();
          LocalDate tempCheckIn = checkIn.getValue();
          String check_In = tempCheckIn.toString();
          LocalDate tempCheckOut = checkOut.getValue();
          String check_Out = tempCheckOut.toString();
          String tempBalance = balance.getText();
          double Balance;
          try{
              Balance = Double.parseDouble(tempBalance);
          }catch(Exception e){
              Balance = 0.0;
          }
          String cash = "N";
          if(cashCheckBox.isSelected()){
              cash = "Y";
          }
          String tempAdults = totalAdults.getText();
          int Adults = 0;
          try{
              Adults = Integer.parseInt(tempAdults);
          }catch(Exception e){
              Adults = 0;
          }
          String tempChildren = totalChildren.getText();
          int Children = 0;
          try{
              Children = Integer.parseInt(tempChildren);
          }catch(Exception e){
              Children = 0;
          }
          String multi_Reservation = multiReservation.getText();
          
        if(Crystal_Palace_Management_System.CPMS.insertNewReservation(Reservation_Number, Room_Number,
          Note, StartDate, EndDate, check_In, check_Out, Balance, cash, Adults, Children, multi_Reservation
           )){
              System.out.println("Item added successfully!");
              stage = (Stage) submitNewReservation.getScene().getWindow();
              //root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              tester.getNamespace().put("Test", "Reservation added successfully!");
              root = tester.load();
          }else{
              System.out.println("Item ID already exists!");
              stage = (Stage) submitNewReservation.getScene().getWindow();
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              tester.getNamespace().put("Test", "Reservation ID already exists!");
              root = tester.load();
          }
        
    //create a new scene with root and set the stage
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
 
     }    
         
 }
 @FXML
    private void handleCancelAction(ActionEvent event) throws IOException{
      if(event.getSource() == cancelButton){
         stage = (Stage) cancelButton.getScene().getWindow();
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
     * @param url
     * @param rb
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
