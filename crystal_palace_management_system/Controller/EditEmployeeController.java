/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Controller;

import static crystal_palace_management_system.Controller.EditReservationViewController.LOCAL_DATE;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jason
 */
public class EditEmployeeController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitNewEmployee;
    @FXML
    private Button inventory;
    @FXML
    private Button addInventory;
    @FXML
    private Button viewEmployees;
    @FXML
    private Button editEmployee;
    @FXML
    private ChoiceBox prefixChoice, suffixChoice, jobChoice, supervisorChoice,
    EmptypeChoice;
    @FXML
    private TextField EmpFirstName, EmpLastName, EmpMiddle, EmpNickname,
            EmpAddress, EmpPhone, EmpSSN, EmpWage, EmpSalary;
    @FXML 
    private DatePicker StartDate, DOB, endDate;
    @FXML
    private PasswordField EmpPassword; 
    @FXML
    private Stage stage = null;
    @FXML
    private Parent root = null;
    @FXML
    private TableView inventoryView, inventoryViewTemp;
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private Label empIdLabel, subResultLabel;
    @FXML
    private AnchorPane invAnchor, RegAnchorPane;
    public String employeeUserName;
    private String prefixFromDB, suffixFromDB, superviserDB, empTypeDB;
    
    @FXML
    private void handleEditEmployee(ActionEvent event) throws IOException{
        
      if(event.getSource() == editEmployee){
          String User_ID = empIdLabel.getText();
          String firstName = EmpFirstName.getText();
          String lastName = EmpLastName.getText();
          
          String middleName;
          try{
           middleName = EmpMiddle.getText();
          }catch(Exception e){
              middleName = null;
          }
          String Prefix;
          try{
              Prefix = (String) prefixChoice.getValue();
          }catch(Exception e){
              Prefix = null;
          }
          String Suffix;
          try{
              Suffix = (String) suffixChoice.getValue();
          }catch(Exception e){
              Suffix = null;
          }
          String Nickname;
          try{
              Nickname  = EmpNickname.getText();
          }catch(Exception e){
              Nickname = null;
          }
          String Address = EmpAddress.getText();
          String JobTitle = (String)jobChoice.getValue();
          String Phone_Number = EmpPhone.getText();
          String SSN = EmpSSN.getText();
          LocalDate tempDateOfBirth = DOB.getValue();
          String DateOfBirth = tempDateOfBirth.toString();
          LocalDate tempStart_Date = StartDate.getValue();
          String Start_Date = tempStart_Date.toString();
          LocalDate tempEnd_Date = endDate.getValue();
          String End_Date;
          try{
              End_Date = tempEnd_Date.toString();
          }catch(Exception e){
              End_Date = null;
          }
          String Supervisor = null; 
          String tempWage = EmpWage.getText();
          double Wage;
          try{
              Wage = Double.parseDouble(tempWage); 
          }catch(Exception e){
              Wage = 0; 
          }
          String tempSalary = EmpSalary.getText();
          double Salary;
          try{
               Salary = Double.parseDouble(tempSalary);
          }catch(Exception e){
              Salary = 0;
          }
          
          String Employee_Type = (String) EmptypeChoice.getValue();
          String Password = EmpPassword.getText();
 
        if(Crystal_Palace_Management_System.CPMS.UpdateEmployee(User_ID,
                lastName, firstName, middleName, Prefix, Suffix, Nickname, Password,
                Address, JobTitle, Phone_Number, SSN, DateOfBirth, Wage, Salary, 
                Employee_Type, Start_Date, End_Date, Supervisor)){
              System.out.println("Employee updated successfully!");
              stage = (Stage) editEmployee.getScene().getWindow();
              //root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
              final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              root = tester.load();
                MainController controller = tester.getController();
                controller.subResultLabel.setText("Employee " + User_ID + " updated successfully");
          }else{
              System.out.println("Employee update failed!");
              stage = (Stage) editEmployee.getScene().getWindow();
             final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/SubmissionResult.fxml"));
              root = tester.load();
              MainController controller = tester.getController();
              controller.subResultLabel.setText("Employee update fail");
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
    public void editEmployeeInfo(String username) {
        employeeUserName = username;
    }
    public void readAllDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            String username = employeeUserName;
            System.out.println("username = " + username);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE WHERE "
                    + " User_ID like '" + username + "' ;");
            
            while (rs.next()) {
                EmpPhone.setText(rs.getString("Phone_Number"));
                EmpFirstName.setText(rs.getString("First_Name"));
                EmpLastName.setText(rs.getString("Last_Name"));
//                EmpMiddle.setText(rs.getString("Middle_Name"));
                empIdLabel.setText(username);
                prefixFromDB = rs.getString("Prefix");
                int indexPrefix = prefixChoice.getItems().indexOf(prefixFromDB);
                //System.out.println(indexPrefix);
                prefixChoice.getSelectionModel().select(indexPrefix);
                suffixFromDB = rs.getString("Suffix");
                int indexSuffix = suffixChoice.getItems().indexOf(suffixFromDB);
                suffixChoice.getSelectionModel().select(indexSuffix);
                EmpNickname.setText(rs.getString("Nickname"));
                EmpAddress.setText(rs.getString("Address"));
                EmpSSN.setText(rs.getString("SSN"));
                
                
                //error is below
                try{
                    DOB.setValue(LOCAL_DATE(rs.getString("DOB")));
                }catch(Exception e){
                    DOB.setValue(null);
                }
                try{
                     StartDate.setValue(LOCAL_DATE(rs.getString("Start_Date")));
                }catch(Exception e){
                    StartDate.setValue(null);
                }
                try{
                  endDate.setValue(LOCAL_DATE(rs.getString("End_Date"))); 
                }catch(Exception e){
                    endDate.setValue(null);
                }
                //
                //error is above
                
                superviserDB = rs.getString("Supervisor");
                int indexSupervisor = supervisorChoice.getItems().indexOf(superviserDB);
                supervisorChoice.getSelectionModel().select(indexSupervisor);
                EmpWage.setText(rs.getString("Wage"));
                EmpSalary.setText(rs.getString("Salary"));
                empTypeDB = rs.getString("Employee_Type");
                int indexEmpType = EmptypeChoice.getItems().indexOf(empTypeDB);
                EmptypeChoice.getSelectionModel().select(indexEmpType);
                EmpPassword.setText(rs.getString("Password"));
               
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
    private void handleCancelAction(ActionEvent event) throws IOException{
      if(event.getSource() == cancelButton){
          
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
         //create a new scene with root and set the stage
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();     
     }     
 }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
         prefixChoice.getItems().clear();
         prefixChoice.setItems(FXCollections.observableArrayList("Mr.", 
         "Mrs.", "Ms.", "Dr."));
         suffixChoice.getItems().clear();
         suffixChoice.setItems(FXCollections.observableArrayList("Jr",
         "Sr", "I", "II", "III", "IV", "V"));
         jobChoice.getItems().clear();
         jobChoice.setItems(FXCollections.observableArrayList("Housekeeping",
         "Supervisor", "Receptionist", "Maintenance", "Cook"));
         EmptypeChoice.getItems().clear();
         EmptypeChoice.setItems(FXCollections.observableArrayList("A", "S", "R", 
         "H", "M"));
         //A = admin, S = supervisor, R = receptionist, H = houskeeping
         //M = manager.
         //supervisorChoice.setItems(FXCollections.observableArrayList(null));

}
       
}
