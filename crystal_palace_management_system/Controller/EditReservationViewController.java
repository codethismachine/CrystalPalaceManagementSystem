/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Controller;

import crystal_palace_management_system.Crystal_Palace_Management_System;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jason
 */
public class EditReservationViewController implements Initializable {

    @FXML
    private TextField RoomNumber, NoteField, balance,
            totalAdults, totalChildren, multiReservation, GuestPhone,
            GuestFirstName, GuestLastName, GuestMiddleName, GuestNickName,
            GuestAddress;
    @FXML
    private ChoiceBox GuestPrefix, GuestSuffix;
    private String prefixFromDB, suffixFromDB;
    @FXML
    private Button editGuest, cancelButton;
    @FXML
    private Button Delete;
    @FXML
    private Button RoomService;
    @FXML
    private DatePicker Start_Date, End_Date, checkIn, checkOut;
    @FXML
    private CheckBox cashCheckBox;
    @FXML
    private Stage stage = null;
    @FXML
    private Parent root = null;
    @FXML
    public String reservationNumber;

    public void editGuestInfo(String ReservationNumber) {
        reservationNumber = ReservationNumber;
    }

    @FXML
    private void handleEditReservation(ActionEvent event) throws IOException {
       if(event.getSource() == editGuest){
          //Guest Info
          String Phone_Number = GuestPhone.getText();
          String First_Name = GuestFirstName.getText();
          String Last_Name = GuestLastName.getText();
          String Middle_Name = GuestMiddleName.getText();
          String Prefix;
          try{
              Prefix = (String) GuestPrefix.getValue();
          }catch(Exception e){
              Prefix = null;
          }
          String Suffix;
          try{
              Suffix = (String) GuestSuffix.getValue();
          }catch(Exception e){
              Suffix = null;
          }
          String Nickname = GuestNickName.getText();
          String Address = GuestAddress.getText();
          String Banned = "T";
          //New Reservation Info
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
          String ReservationNumber = reservationNumber;
        if(Crystal_Palace_Management_System.CPMS.UpdateReservation(ReservationNumber,
        Room_Number,Note, StartDate, EndDate, check_In, check_Out, Balance, cash, Adults, Children, multi_Reservation,
        Phone_Number, Last_Name, First_Name, Middle_Name, Prefix, Suffix, Nickname, Address, 
           Banned
           )){ 
              System.out.println("Reservation updated successfully!");
              stage = (Stage) editGuest.getScene().getWindow();
              //root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              root = tester.load();
               MainController controller = tester.getController();
                controller.subResultLabel.setText("Reservation updated successfully");
          }else{
              System.out.println("Update Failed!");
              stage = (Stage) editGuest.getScene().getWindow();
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              root = tester.load();
               MainController controller = tester.getController();
                controller.subResultLabel.setText("Update Failed");
          }
        
    //create a new scene with root and set the stage
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
 
     }    

    }

    static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public void readAllDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VISIT JOIN GUEST WHERE "
                    + "Reservation_Number = " + reservationNumber + " AND "
                    + "Visit_Reservation_Number = " + reservationNumber + " ;");
            while (rs.next()) {
                GuestPhone.setText(rs.getString("Phone_Number"));
                GuestFirstName.setText(rs.getString("First_Name"));
                GuestLastName.setText(rs.getString("Last_Name"));
                GuestMiddleName.setText(rs.getString("Middle_Name"));
//         GuestPrefix.setValue(rs.getString("Prefix"));
                prefixFromDB = rs.getString("Prefix");
                int indexPrefix = GuestPrefix.getItems().indexOf(prefixFromDB);
                //System.out.println(indexPrefix);
                GuestPrefix.getSelectionModel().select(indexPrefix);
                suffixFromDB = rs.getString("Suffix");
                int indexSuffix = GuestSuffix.getItems().indexOf(suffixFromDB);
                GuestSuffix.getSelectionModel().select(indexSuffix);
                GuestNickName.setText(rs.getString("Nickname"));
                GuestAddress.setText(rs.getString("Address"));
                RoomNumber.setText(rs.getString("Room_Number"));
                NoteField.setText(rs.getString("Note"));
                try{
                     Start_Date.setValue(LOCAL_DATE(rs.getString("Start_Date")));
                }catch(Exception e){
                    Start_Date.setValue(null);
                }
                 try{
                     End_Date.setValue(LOCAL_DATE(rs.getString("End_Date")));
                }catch(Exception e){
                    End_Date.setValue(null);
                }
                 try{
                        checkIn.setValue(LOCAL_DATE(rs.getString("Check_In")));
                }catch(Exception e){
                    System.out.println("Check In Date is: " + checkOut );
                    checkIn.setValue(null);

                }
                  try{
                    System.out.println("Check In Date is: " + checkIn );
                        checkOut.setValue(LOCAL_DATE(rs.getString("Check_Out")));
                }catch(Exception e){
                    checkOut.setValue(null);
                }
                 
                
                balance.setText(rs.getString("Balance"));
                String cashDB = rs.getString("Cash");
                boolean cashCheck = false;
                if (cashDB.equals("Y")) {
                    cashCheck = true;
                }
                cashCheckBox.setSelected(cashCheck);
                totalAdults.setText(rs.getString("Total_Adults"));
                totalChildren.setText(rs.getString("Total_Children"));
                multiReservation.setText(rs.getString("Multi_Reservation_Number"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    @FXML
    private void handleDelete(ActionEvent event) throws IOException {
        if(event.getSource() == Delete){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Cancellation");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to cancel the reservation?");

        Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        Crystal_Palace_Management_System.CPMS.RemoveReservation(reservationNumber);
        stage = (Stage) Delete.getScene().getWindow();
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              root = tester.load();
               MainController controller = tester.getController();
                controller.subResultLabel.setText("Reservatadmion Cancelled.");
        
      }else {
            return;
        }
          Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
    }
  }
      @FXML
    private void handleRoomService(ActionEvent event) throws IOException {
        if(event.getSource() == RoomService){
                 stage=(Stage) RoomService.getScene().getWindow();
         
          
          System.out.println("reservation number from edit reservation " + reservationNumber);
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
        FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/RoomService.fxml")); 
        
        
//          test.checkInViewer(reservationNumber, lastName, firstName, phoneNumber);
         root= tester.load();
         RoomServiceController controller = tester.getController();
         controller.editReservatinNumber(reservationNumber);
         controller.buildData(reservationNumber);
         
            }
               //create a new scene with root and set the stage
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
             
    }
    @FXML
    private void handleCancelAction(ActionEvent event) throws IOException {
        if (event.getSource() == cancelButton) {
           if(Crystal_Palace_Management_System.empType.equals("A") ||
               Crystal_Palace_Management_System.empType.equals("M")){
             //get reference to the button's stage         
        stage=(Stage) cancelButton.getScene().getWindow();
        //load up OTHER FXML document
         FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
             root = tester.load();
             }else if(Crystal_Palace_Management_System.empType.equals("S")){
                 stage=(Stage) cancelButton.getScene().getWindow();
                 FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
                 root = tester.load();
               MainController controller = tester.getController();
                controller.register.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
             }
             else{
                  stage=(Stage) cancelButton.getScene().getWindow();
                 FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
                 root = tester.load();
               MainController controller = tester.getController();
                controller.viewEmployees.setDisable(true);
                controller.register.setDisable(true);
                controller.addInventory.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
                    //viewEmployees.setDisable(true);
             }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GuestPrefix.setItems(FXCollections.observableArrayList("Mr.",
                "Mrs.", "Ms.", "Dr."));
        GuestSuffix.setItems(FXCollections.observableArrayList("Jr",
                "Sr", "I", "II", "III", "IV", "V"));
        // TODO
    }

}
