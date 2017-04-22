/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system;

import crystal_palace_management_system.Model.CPMS_Database;
import crystal_palace_management_system.Model.InventoryDatabase;
import crystal_palace_management_system.Model.EmployeeDatabase;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jason
 */
public class Crystal_Palace_Management_System extends Application 
{
    public static CPMS_Database CPMS = new CPMS_Database();
    public static InventoryDatabase invTable = new InventoryDatabase();
    public static String empType; 
    public static String globalUserName;
    public static String clockInDate;
    public static String clockInHour;
    public static String clockInMinute;
    public static String clockOutHour;
    public static String clockOutMinute;
    public static double clockOutTotalMinutes;
    public static double hoursWorked;
    
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("View/"
                + "LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
         //HTMdatabase test = new HTMdatabase();
         //CPMS_Database CPMS = new CPMS_Database();
        // CPMS.createTableEmployee();
         //CPMS.createTableINVENTORY();
        // CPMS.createTableTIMESHEET();
        // CPMS.createTableVISIT();
        //CPMS.createTableROOM();
          //CPMS.createTableGUEST(); //!!!!!THIS NEEDS TO BE REMADE I FORGOT TO PUT NOT NULL FOR VISIT RESERVATION IN GUEST TABLE
          //CPMS.createTableROOM_SERVICE();
         // CPMS.createTableSERVICES();
        //test.createTable();
//       test.insertTable();
      //test.readAllDataBase();
//       test.deleteRecord(9);
//       test.readAllDataBase();
    }
    public void extendStay(){
        
    }
    
    public void showWakeUpCallList(){
        
    }
    public void logUserIn(){
        
    }
    public void logUserOut(){
        
    }
    public void makeReservation(){
        
    }
    public void checkRoomAvailability(){
        
    }
    public void getCostEstimate(){
        
    }
    public void clockIn(){
        
    }
    public void clockOut(){
        
    }
    public void cancelReservation(){
        
    }
    public void inputRoomServiceOrder(){
        
    }
    public void generateInvoice(){
        
    }
    public void makePayment(){
        
    }
    public void getEmployeeInfo(){
        
    }
    public void getNewGuest(){
        
    }
    public void getGuestInfo(){
        
    }
    public void modifyInventory(){
        
    }
    public void inventoryAlert(){
        
    }
    public void occupancyRate(){
        
    }
    public void checkInsRemaining(){
        
    }
    public void checkOutsRemaining(){
        
    }
    public void missedCheckInAlert(){
        
    }
    
}
