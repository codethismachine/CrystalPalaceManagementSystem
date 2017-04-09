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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jason
 */
public class InventoryController implements Initializable {
  @FXML
  private TableView inventoryView;
  @FXML
  private Button showInv, addInventory, returnToMain;
  @FXML
  private Stage stage = null;
  @FXML
  private Parent root = null;
    private ObservableList<ObservableList> data;
    
    private Connection connection; 
    private Statement statement; 
    //CONNECTION DATABASE
    public void buildData(){
          data = FXCollections.observableArrayList();
          try{
              Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
              System.out.println("Opened database successfully");
              statement = connection.createStatement();
         
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from INVENTORY";
            //ResultSet
            ResultSet rs = statement.executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                inventoryView.getColumns().addAll(col); 
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
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            inventoryView.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
//        return inventoryView;
      }
     @FXML
    private void handleShowInventory(ActionEvent event) throws IOException{
         if(event.getSource()==showInv){
             buildData();
      }
    }
    @FXML
    private void handleAddInventory(ActionEvent event) throws IOException{
         if(event.getSource()== addInventory){
             //get reference to the button's stage         
        stage=(Stage) addInventory.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/InventoryInsertItem.fxml"));
        //final FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
         
         }
         //create a new scene with root and set the stage
      Scene scene = new Scene(root);
     
      stage.setScene(scene);
      stage.show();
      }
    @FXML
    private void handleReturnToMain(ActionEvent event) throws IOException{
         if(event.getSource()==returnToMain){
             //get reference to the button's stage         
        stage=(Stage) returnToMain.getScene().getWindow();
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
        FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EmployeeMainScreen.fxml"));
         
       
        
         root= tester.load();
         
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
