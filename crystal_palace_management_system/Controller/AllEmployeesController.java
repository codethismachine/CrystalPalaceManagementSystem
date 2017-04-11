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
public class AllEmployeesController implements Initializable {
  @FXML
  private TableView EmployeeView;
  @FXML
 private TextField EmployeeLastName, EmployeeFirstName, EmployeeNickname,
         EmployeeUsername, employeeUsername;
  @FXML
  private Button showEmployees, returnToMain, showAll, Edit;
  @FXML
  private Stage stage = null;
  @FXML
  private Parent root = null;
  
    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> dataAll;
    private Connection connection; 
    private Statement statement; 
    //CONNECTION DATABASE
    public void buildData(){
          data = FXCollections.observableArrayList();
          try{
             EmployeeView.getColumns().clear();
            //SQL FOR SELECTING ALL OF CUSTOMER
          
              String UserId = EmployeeUsername.getText();
              String Last_Name = EmployeeLastName.getText();
              String First_Name = EmployeeFirstName.getText();
              String Nickname = EmployeeNickname.getText();
              Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
              System.out.println("Opened database successfully");
              String Query = "";
        if (UserId.length() != 0)
        {   
            Query ="SELECT User_Id, Last_Name, First_Name, Nickname, Phone_Number "
                    + "FROM EMPLOYEE WHERE "
                + "User_Id like '" + UserId + "';";
            System.out.println("Passed search by username");
        }
        else
        {
         Query= "SELECT  User_Id, Last_Name, First_Name, Nickname, "
                 + "Phone_Number FROM Employee " +
                "WHERE Last_Name like '" + Last_Name + "' "
                + "OR First_Name like '" + First_Name + "' "
                + "OR Phone_Number like '" + Nickname + "';";
         System.out.println("Passed search by up to 3 attributes");
        }
              
              
              statement = connection.createStatement();
         
            //SQL FOR SELECTING ALL OF CUSTOMER
          
            //ResultSet
            ResultSet rs = statement.executeQuery(Query);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param){                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                
                EmployeeView.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    if(rs.getString(i) != null ){
                         row.add(rs.getString(i));
                    }else{
                        row.add("null");
                    }
                   
                  
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            EmployeeView.setItems(data);
           
          }catch(Exception e){
              System.out.println("Error on Building Data");             
          }
//        return inventoryView;
      }
     @FXML
    private void handleShowEmployees(ActionEvent event) throws IOException{
         if(event.getSource()==showEmployees){
              
             buildData();
      }
    }
    
    @FXML
    private void handleShowAllEmployees(ActionEvent event) throws IOException{
         if(event.getSource()==showAll){
              
             buildDataAll();
      }
    }
    @FXML
    private void handleReturnToMain(ActionEvent event) throws IOException{
         if(event.getSource()==returnToMain){
         if(Crystal_Palace_Management_System.empType.equals("A") ||
               Crystal_Palace_Management_System.empType.equals("M")){
             //get reference to the button's stage         
        stage=(Stage) returnToMain.getScene().getWindow();
        //load up OTHER FXML document
         FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
             root = tester.load();
             }else if(Crystal_Palace_Management_System.empType.equals("S")){
                 stage=(Stage) returnToMain.getScene().getWindow();
                 FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml")); 
                 root = tester.load();
               MainController controller = tester.getController();
                controller.register.setDisable(true);
                System.out.println(Crystal_Palace_Management_System.empType);
             }
             else{
                  stage=(Stage) returnToMain.getScene().getWindow();
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
    public void buildDataAll(){
          dataAll = FXCollections.observableArrayList();
          try{
              EmployeeView.getColumns().clear();
              Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
              System.out.println("Opened database successfully");
              String Query = "SELECT User_Id, Last_Name, First_Name, Nickname, Phone_Number "
                    + "FROM EMPLOYEE";
        
              
              statement = connection.createStatement();
         
            //SQL FOR SELECTING ALL OF CUSTOMER
          
            //ResultSet
            ResultSet rs = statement.executeQuery(Query);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param){                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                EmployeeView.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    if(rs.getString(i) != null ){
                         row.add(rs.getString(i));
                    }else{
                        row.add("null");
                    }
                   
                  
                }
                System.out.println("Row [1] added "+row );
                dataAll.add(row);

            }

            //FINALLY ADDED TO TableView
            EmployeeView.setItems(dataAll);
          }catch(Exception e){
              System.out.println("Error on Building Data");             
          }
//        return inventoryView;
      }
    @FXML
    public void handleEdit(ActionEvent event) throws IOException{
            if(event.getSource() == Edit){
                 stage=(Stage) Edit.getScene().getWindow();
         
          String tempUsername = employeeUsername.getText();
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
        FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EditEmployee.fxml")); 
        
        
//          test.checkInViewer(reservationNumber, lastName, firstName, phoneNumber);
         root= tester.load();
         EditEmployeeController controller = tester.getController();
         controller.editEmployeeInfo(tempUsername);
         controller.readAllDataBase();
         
            }
               //create a new scene with root and set the stage
      Scene scene = new Scene(root);  
      stage.setScene(scene);
      stage.show();
             
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
