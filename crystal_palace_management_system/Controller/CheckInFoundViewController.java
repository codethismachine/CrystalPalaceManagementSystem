/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Controller;

import crystal_palace_management_system.Controller.CheckInController;
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
public class CheckInFoundViewController implements Initializable {
    
    
    public CheckInController tempCheck = new CheckInController();
   @FXML
  private TableView CheckInTable;
  @FXML
  private Button returnToMain, Edit;
  @FXML
  private TextField ReservationNumberEdit;
  @FXML
  private Stage stage = null;
  @FXML
  private Parent root = null;
 public String Reservation_Number;
 public String Last_Name;
 public String First_Name;
 public String Phone_Number;
 
    private ObservableList<ObservableList> data;
    
    private Connection connection; 
    private Statement statement; 
    //CONNECTION DATABASE
  
    public void checkInViewer(String reservationNumber, String lastName,
            String firstName, String phoneNumber){
          data = FXCollections.observableArrayList();
          try{
              Reservation_Number = reservationNumber;
              System.out.println(Reservation_Number);
              Last_Name = lastName;
              First_Name = firstName;
              Phone_Number = phoneNumber;
              Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
              System.out.println("Opened database successfully");
              String Query = "";
        if (Reservation_Number.length() != 0)
        {
         
            
            Query ="SELECT * FROM VISIT JOIN GUEST WHERE "
                + "Reservation_Number like '" + Reservation_Number + "';";
        }
        else
        {
         Query= "SELECT * FROM VISIT join GUEST on "
                + "Visit_Reservation_Number = Reservation_Number\n" +
                    "   where Visit_Reservation_Number in\n" +
                "(SELECT Visit_Reservation_Number FROM GUEST\n" +
                "WHERE Last_Name like '" + Last_Name + "' "
                + "OR First_Name like '" + First_Name + "' "
                + "OR Phone_Number like '" + Phone_Number + "');";
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
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                CheckInTable.getColumns().addAll(col); 
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
            CheckInTable.setItems(data);
            
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
//        return inventoryView;
      }
     @FXML
    public void handleEdit(ActionEvent event) throws IOException{
            if(event.getSource() == Edit){
                 stage=(Stage) Edit.getScene().getWindow();
         
          String tempReservationNumber = ReservationNumberEdit.getText();
        //load up OTHER FXML document
        //root = FXMLLoader.load(getClass().getResource("/crystal_palace_management_system/View/Inventory.fxml"));
        FXMLLoader tester = new FXMLLoader(getClass().getResource("/crystal_palace_management_system/View/EditReservationView.fxml")); 
        
        
//          test.checkInViewer(reservationNumber, lastName, firstName, phoneNumber);
         root= tester.load();
         EditReservationViewController controller = tester.getController();
         controller.editGuestInfo(tempReservationNumber);
         controller.readAllDataBase();
         
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
