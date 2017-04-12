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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jason
 */
public class TimeSheetViewController implements Initializable {
    @FXML
    private TableView TimeSheetTable;
    @FXML
    private Button ShowTimes, returnToMain, search;
    @FXML
    private Stage stage = null;
    @FXML 
    private DatePicker timeDateSearch;
    @FXML
    private TextField TimeSheetSearch;
    @FXML
    private Parent root = null;
    private ObservableList<ObservableList> data;

    private Connection connection;
    private Statement statement;

    @FXML
    private void handleShowTimes(ActionEvent event) throws IOException {
        if (event.getSource() == ShowTimes) {
            buildData();
        }
    }
    
    @FXML
    private void handleSearch(ActionEvent event) throws IOException, ParseException {
        if (event.getSource() == search) {
            
           LocalDate tempSearchDate = timeDateSearch.getValue();
           java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(tempSearchDate.toString());
          String searchDate = timeDateSearch.toString();
            String userID = TimeSheetSearch.getText();
           DateFormat dateIn = new SimpleDateFormat("yyyy-MM-dd");
           searchDate = dateIn.format(d);
                buildDataBySearch(userID, searchDate);
                System.out.println("search date = " + searchDate);
        }
    }
    public void buildDataBySearch(String UserID, String Date) {
        data = FXCollections.observableArrayList();
        try {
            TimeSheetTable.getColumns().clear();
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
            System.out.println("Opened database successfully");
            statement = connection.createStatement();

            //SQL FOR SELECTING ALL OF CUSTOMER
            
            String Query = "";
            if (UserID.length() != 0){
                Query = "SELECT idTimesheet, Last_Name, First_Name,"
                    + " Nickname, Clock_In_Date, Clock_In_Time, Clock_Out,"
                    + " Time_Worked FROM TIMESHEET join EMPLOYEE "
                    + " where idTimesheet = " + "'" + UserID + "' " 
                    + " AND User_ID = " + "'" + UserID + "' "
                    + " ORDER BY Clock_In_Date asc;";
                    }
            else if (UserID.length() != 0 && Date.length() != 0){
                Query = "SELECT idTimesheet, Last_Name,"
                    + " First_Name, Nickname, Clock_In_Date, Clock_In_Time,"
                    + " Clock_Out, Time_Worked FROM TIMESHEET join EMPLOYEE where "
                    + " idTimesheet = "  + UserID + "'" + " AND User_ID = " + UserID + "'"
                    + " AND Clock_In_Date = '" + Date + "'"
                    + " ORDER BY Clock_In_Date asc;";
                    }
            else if (Date.length() != 0){
                Query = "SELECT idTimesheet, Last_Name,"
                    + " First_Name, Nickname, Clock_In_Date, Clock_In_Time,"
                    + " Clock_Out, Time_Worked FROM TIMESHEET join EMPLOYEE where "
                    + " Clock_In_Date = '" + Date + "'"
                    + " ORDER BY Last_Name asc;";
                    }
    
            //ResultSet
            ResultSet rs = statement.executeQuery(Query);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY * ********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                TimeSheetTable.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList * ******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if (rs.getString(i) != null) {
                        row.add(rs.getString(i));
                    } else {
                        row.add("null");
                    }

                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            TimeSheetTable.setItems(data);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
//        return inventoryView;
    }

    public void buildData() {
        data = FXCollections.observableArrayList();
        try {
            TimeSheetTable.getColumns().clear();
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
            System.out.println("Opened database successfully");
            statement = connection.createStatement();

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT idTimesheet, Last_Name, First_Name, Nickname,"
                    + " Clock_In_Date, Clock_In_Time, Clock_Out,"
                    + " Time_Worked FROM TIMESHEET join EMPLOYEE "
                    + " where User_ID = idTimesheet ORDER BY idTimesheet;";
            //ResultSet
            ResultSet rs = statement.executeQuery(SQL);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY * ********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                TimeSheetTable.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList * ******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if (rs.getString(i) != null) {
                        row.add(rs.getString(i));
                    } else {
                        row.add("null");
                    }

                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            TimeSheetTable.setItems(data);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
//        return inventoryView;
    }

    @FXML
    private void handleReturnToMain(ActionEvent event) throws IOException {
        if (event.getSource() == returnToMain) {
            if (Crystal_Palace_Management_System.empType.equals("A")
                    || Crystal_Palace_Management_System.empType.equals("M")) {
                //get reference to the button's stage         
                stage = (Stage) returnToMain.getScene().getWindow();
                //load up OTHER FXML document
                FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
                //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
                root = tester.load();
            } else if (Crystal_Palace_Management_System.empType.equals("S")) {
                stage = (Stage) returnToMain.getScene().getWindow();
                FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
                root = tester.load();
                MainController controller = tester.getController();
                controller.register.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
            } else {
                stage = (Stage) returnToMain.getScene().getWindow();
                FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
                root = tester.load();
                MainController controller = tester.getController();
                controller.viewEmployees.setDisable(true);
                controller.register.setDisable(true);
                controller.addInventory.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
                //viewEmployees.setDisable(true);
            }

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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
