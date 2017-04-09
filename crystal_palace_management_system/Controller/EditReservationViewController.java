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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        if (event.getSource() == editGuest) {

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
                    + "Reservation_Number like '" + reservationNumber + "';");
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
                Start_Date.setValue(LOCAL_DATE(rs.getString("Start_Date")));
                End_Date.setValue(LOCAL_DATE(rs.getString("End_Date")));
                checkIn.setValue(LOCAL_DATE(rs.getString("Check_In")));
                checkOut.setValue(LOCAL_DATE(rs.getString("Check_Out")));
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
