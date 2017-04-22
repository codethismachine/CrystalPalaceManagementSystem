/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Controller;

import crystal_palace_management_system.Crystal_Palace_Management_System;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jason
 */
public class RegisterController implements Initializable {

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
    private ChoiceBox prefixChoice;
    @FXML
    private ChoiceBox suffixChoice;
    @FXML
    private ChoiceBox jobChoice;
    @FXML
    private ChoiceBox supervisorChoice;
    @FXML
    private ChoiceBox typeChoice;
    @FXML
    private TextField enterUsername;
    @FXML
    private TextField newEmpID;
    @FXML
    private TextField newEmpFirstName;
    @FXML
    private TextField newEmpLastName;
    @FXML
    private TextField newEmpMiddle;
    @FXML
    private TextField newHireYear;
    @FXML
    private TextField newHireMonth;
    @FXML
    private TextField newHireDay;
    @FXML
    private TextField newDeptID;
    @FXML
    private TextField newEmpNickname;
    @FXML
    private TextField newEmpAddress;
    @FXML
    private TextField newEmpPhone;
    @FXML
    private TextField newEmpSSN;
    @FXML
    private TextField newUsername;
    @FXML
    private TextField newEmpSalary;
    @FXML
    private TextField newEmpWage;
    @FXML
    private DatePicker regDate;
    @FXML
    private DatePicker DOB;
    @FXML 
    private DatePicker endDate;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Stage stage = null;
    @FXML
    private Parent root = null;
    @FXML
    private TableView inventoryView;
    @FXML
    private TableView inventoryViewTemp;
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private AnchorPane invAnchor;
    @FXML
    private AnchorPane RegAnchorPane;

    @FXML
    private void handleRegisterNewEmployee(ActionEvent event) throws IOException
    {

        if (event.getSource() == submitNewEmployee) 
        {
            String User_ID = newEmpID.getText();
            String firstName = newEmpFirstName.getText();
            String lastName = newEmpLastName.getText();

            String middleName;
            try 
            {
                middleName = newEmpMiddle.getText();
            } catch (Exception e) 
            {
                middleName = null;
            }
            String Prefix;
            try 
            {
                Prefix = (String) prefixChoice.getValue();
            } catch (Exception e)
            {
                Prefix = null;
            }
            String Suffix;
            try 
            {
                Suffix = (String) suffixChoice.getValue();
            } catch (Exception e) 
            {
                Suffix = null;
            }
            String Nickname;
            try 
            {
                Nickname = newEmpNickname.getText();
            } catch (Exception e)
            {
                Nickname = null;
            }
            String Address = newEmpAddress.getText();
            String JobTitle = (String) jobChoice.getValue();
            String Phone_Number = newEmpPhone.getText();
            String SSN = newEmpSSN.getText();
            LocalDate tempDateOfBirth = DOB.getValue();
            String DateOfBirth = tempDateOfBirth.toString();
            LocalDate tempStart_Date = regDate.getValue();
            String Start_Date = tempStart_Date.toString();
            LocalDate tempEnd_Date = endDate.getValue();
            String End_Date;
            try 
            {
                End_Date = tempEnd_Date.toString();
            } catch (Exception e)
            {
                End_Date = null;
            }
            String Supervisor = null;
            String tempWage = newEmpWage.getText();
            double Wage = Double.parseDouble(tempWage);
            String tempSalary = newEmpSalary.getText();
            double Salary = Double.parseDouble(tempSalary);
            String Employee_Type = (String) typeChoice.getValue();
            String Password = newPassword.getText();

            if (Crystal_Palace_Management_System.CPMS.insertNewEmployee(User_ID,
                    lastName, firstName, middleName, Prefix, Suffix, Nickname, 
                    Password, Address, JobTitle, Phone_Number, SSN,
                    DateOfBirth, Wage, Salary, Employee_Type, Start_Date,
                    End_Date, Supervisor)) 
            {
                System.out.println("Employee added successfully!");
                stage = (Stage) submitNewEmployee.getScene().getWindow();
                //root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
                final FXMLLoader tester = new FXMLLoader(getClass().getResource(""
                        + "/crystal_palace_management_system/"
                        + "View/SubmissionResult.fxml"));
                root = tester.load();
                MainController controller = tester.getController();
                controller.subResultLabel.setText("Employee " + User_ID + " "
                        + "added successfully");
            } else 
            {
                System.out.println("Employee ID already exists!");
                stage = (Stage) submitNewEmployee.getScene().getWindow();
                final FXMLLoader tester = new FXMLLoader(getClass().getResource(""
                        + "/crystal_palace_management_system/View/"
                        + "SubmissionResult.fxml"));
                root = tester.load();
                MainController controller = tester.getController();
                controller.subResultLabel.setText("Employee ID already exists");
            }
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    private void handleCancelAction(ActionEvent event) throws IOException 
    {
        if (event.getSource() == cancelButton) 
        {

            if (Crystal_Palace_Management_System.empType.equals("A")
                    || Crystal_Palace_Management_System.empType.equals("M")) 
            {
                //get reference to the button's stage         
                stage = (Stage) cancelButton.getScene().getWindow();
                //load up OTHER FXML document
                FXMLLoader tester = new FXMLLoader(getClass().getResource(""
                        + "/crystal_palace_management_system/"
                        + "View/EmployeeMainScreen.fxml"));
                //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
                root = tester.load();
            } else if (Crystal_Palace_Management_System.empType.equals("S"))
            {
                stage = (Stage) cancelButton.getScene().getWindow();
                FXMLLoader tester = new FXMLLoader(getClass().getResource(""
                        + "/crystal_palace_management_system/View/"
                        + "EmployeeMainScreen.fxml"));
                root = tester.load();
                MainController controller = tester.getController();
                controller.register.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
            } else 
            {
                stage = (Stage) cancelButton.getScene().getWindow();
                FXMLLoader tester = new FXMLLoader(getClass().getResource(""
                        + "/crystal_palace_management_system/View/"
                        + "EmployeeMainScreen.fxml"));
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
    public void initialize(URL url, ResourceBundle rb) 
    {
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
        typeChoice.getItems().clear();
        typeChoice.setItems(FXCollections.observableArrayList("A", "S", "R",
                "H", "M"));
        //A = admin, S = supervisor, R = receptionist, H = houskeeping
        //M = manager.
        //supervisorChoice.setItems(FXCollections.observableArrayList(null));

    }
}
