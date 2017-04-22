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
import java.util.function.UnaryOperator;
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
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jason
 */
public class NewReservationController implements Initializable
{

    @FXML
    private TextField RoomNumber;
    @FXML
    private TextField NoteField;
    @FXML
    private TextField balance;
    @FXML
    private TextField totalAdults;
    @FXML
    private TextField totalChildren;
    @FXML
    private TextField multiReservation;
    @FXML
    private TextField GuestPhone;
    @FXML
    private TextField GuestFirstName;
    @FXML
    private TextField GuestLastName;
    @FXML
    private TextField GuestMiddleName;
    @FXML
    private TextField GuestNickName;
    @FXML
    private TextField GuestAddress;

    @FXML
    private ChoiceBox GuestPrefix;
    @FXML
    private ChoiceBox GuestSuffix;
    @FXML
    private Button submitNewReservation;
    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker Start_Date; 
    @FXML
    private DatePicker End_Date; 
    @FXML
    private DatePicker checkIn;
    @FXML
    private DatePicker checkOut;
    @FXML
    private CheckBox cashCheckBox;
    @FXML
    private Stage stage = null;
    @FXML
    private Parent root = null;

    @FXML
    private void handleNewReservation(ActionEvent event) throws IOException 
    {
        if (event.getSource() == submitNewReservation)
        {
            //Guest Info
            String Phone_Number = GuestPhone.getText();
            String First_Name = GuestFirstName.getText();
            String Last_Name = GuestLastName.getText();
            String Middle_Name = GuestMiddleName.getText();
            String Prefix;
            try 
            {
                Prefix = (String) GuestPrefix.getValue();
            } catch (Exception e) 
            {
                Prefix = null;
            }
            String Suffix;
            try 
            {
                Suffix = (String) GuestSuffix.getValue();
            } catch (Exception e) 
            {
                Suffix = null;
            }
            String Nickname = GuestNickName.getText();
            String Address = GuestAddress.getText();
            String Banned = "T";
            //New Reservation Info
            String tempRoom_Number = RoomNumber.getText();
            int Room_Number;
            try
            {
                Room_Number = Integer.parseInt(tempRoom_Number);
            } catch (Exception e) 
            {
                Room_Number = -1;
            }
            String Note = NoteField.getText();
            LocalDate tempStartDate = Start_Date.getValue();
            String StartDate = null;
            try 
            {
                StartDate = tempStartDate.toString();
            } catch (Exception e) 
            {
                StartDate = null;
            }
            LocalDate tempEndDate = End_Date.getValue();
            String EndDate = null;
            try 
            {
                EndDate = tempEndDate.toString();
            } catch (Exception e)
            {
                EndDate = null;
            }
            LocalDate tempCheckIn = checkIn.getValue();
            String check_In = null;
            try 
            {
                check_In = tempCheckIn.toString();
            } catch (Exception e) 
            {
                check_In = null;
            }
            LocalDate tempCheckOut = checkOut.getValue();
            String check_Out = null;
            try 
            {
                check_Out = tempCheckOut.toString();
            } catch (Exception e) 
            {
                check_Out = null;
            }
            String tempBalance = balance.getText();
            double Balance;
            try 
            {
                Balance = Double.parseDouble(tempBalance);
            } catch (Exception e) 
            {
                Balance = 0.0;
            }
            String cash = "N";
            if (cashCheckBox.isSelected()) 
            {
                cash = "Y";
            }
            String tempAdults = totalAdults.getText();
            int Adults = 0;
            try 
            {
                Adults = Integer.parseInt(tempAdults);
            } catch (Exception e) 
            {
                Adults = 0;
            }
            String tempChildren = totalChildren.getText();
            int Children = 0;
            try 
            {
                Children = Integer.parseInt(tempChildren);
            } catch (Exception e) 
            {
                Children = 0;
            }
            String multi_Reservation = multiReservation.getText();

            if (Crystal_Palace_Management_System.CPMS.insertNewReservation(Room_Number,
                    Note, StartDate, EndDate, check_In, check_Out, Balance,
                    cash, Adults, Children, multi_Reservation
            )) 
            {
                int Visit_Reservation_Number
                        = Crystal_Palace_Management_System.CPMS.reservationNumber();

                Crystal_Palace_Management_System.CPMS.insertNewGuest(
                        Phone_Number, Last_Name, First_Name, Middle_Name, Prefix,
                        Suffix, Nickname, Address, Banned,
                        Visit_Reservation_Number);

                System.out.println("Reservation added successfully!");
                stage = (Stage) submitNewReservation.getScene().getWindow();
                //root = FXMLLoader.load(getClass().getResource("SubmissionResult.fxml"));
                final FXMLLoader tester = new FXMLLoader(getClass().getResource(""
                        + "/crystal_palace_management_system/View/"
                        + "SubmissionResult.fxml"));
                root = tester.load();
                MainController controller = tester.getController();
                controller.subResultLabel.setText("Reservation added "
                        + "successfully");
            } else 
            {
                System.out.println("Reservation already exists!");
                stage = (Stage) submitNewReservation.getScene().getWindow();
                final FXMLLoader tester = new FXMLLoader(getClass().getResource(""
                        + "/crystal_palace_management_system"
                        + "/View/SubmissionResult.fxml"));
                root = tester.load();
                MainController controller = tester.getController();
                controller.subResultLabel.setText("Reservation already exists1");
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
                        + "/crystal_palace_management_system/View/"
                        + "EmployeeMainScreen.fxml"));
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
                        + "/crystal_palace_management_system/"
                        + "View/EmployeeMainScreen.fxml"));
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        GuestPrefix.getItems().clear();
        GuestPrefix.setItems(FXCollections.observableArrayList("Mr.",
                "Mrs.", "Ms.", "Dr."));
        GuestSuffix.getItems().clear();
        GuestSuffix.setItems(FXCollections.observableArrayList("Jr",
                "Sr", "I", "II", "III", "IV", "V"));
    }

}
