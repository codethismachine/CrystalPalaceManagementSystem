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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import javafx.scene.control.ChoiceBox;
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
public class RoomServiceController implements Initializable {
    @FXML
  private TableView RoomServiceTable;
  @FXML
  private Button returnToMain;
  @FXML
  private Stage stage = null;
  @FXML
  private ChoiceBox selectFood;
  @FXML
  private TextField foodQuantity;
  @FXML
  private Parent root = null;
  public String reservationNumber;
    private ObservableList<ObservableList> data;
    
    private Connection connection; 
    private Statement statement; 
    //CONNECTION DATABASE
    public void buildData(String ReservationNumber){
          data = FXCollections.observableArrayList();
          try{
              RoomServiceTable.getColumns();
              Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
              System.out.println("Opened database successfully");
              statement = connection.createStatement();
         
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from ROOM_SERVICE";
            //ResultSet
            System.out.println("Passed sql room service");
            ResultSet rs = statement.executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                RoomServiceTable.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
             while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
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
            RoomServiceTable.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
//        return inventoryView;
      }
     
    @FXML
    private void handleSubmit(ActionEvent event) throws IOException{
        String food = (String) selectFood.getValue();
        String tempQuantity = foodQuantity.getText();
        int quantity = 0; 
        try{
            quantity = Integer.parseInt(tempQuantity);
        }catch(Exception e){
            quantity = 0;
        }
         HashMap foodPrice = new HashMap();
      
      // Put elements to the map
      foodPrice.put("Fries", new Double(1.99));
      foodPrice.put("Hamburger", new Double(2.99));
      foodPrice.put("Hot Dog", new Double(1.99));
      foodPrice.put("Coca-Cola", new Double(1.49));
      foodPrice.put("Coca-Cola Diet", new Double(1.49));
      foodPrice.put("Sprite", new Double(1.49));
      foodPrice.put("Coors Light", new Double(1.99));
      
       double balance = quantity * ((Double)foodPrice.get(food));
       System.out.println(balance);
       Date time = new Date();
         HashMap foodID = new HashMap();
      
      // Put elements to the map
      foodID.put("Fries", new Integer(11));
      foodID.put("Hamburger", new Integer(12));
      foodID.put("Hot Dog", new Integer(13));
      foodID.put("Coca-Cola", new Integer(14));
      foodID.put("Coca-Cola Diet", new Integer(15));
      foodID.put("Sprite", new Integer(16));
      foodID.put("Coors Light", new Integer(17));
      
      int foodId = (int) foodID.get(food);
         DateFormat timeIn = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         String timeNow = timeIn.format(time);
         Crystal_Palace_Management_System.CPMS.insertNewRoomService(balance, reservationNumber, timeNow, foodId, quantity);
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
    public void editReservatinNumber(String ReservationNumber) {
        reservationNumber = ReservationNumber;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         selectFood.setItems(FXCollections.observableArrayList("Fries", 
         "Hamburger", "Hot Dog", "Coca-Cola", "Coca-Cola Diet", "Sprite",
         "Coors Light"));
        
         
        
    }    
    
}
