/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jason
 */
public class Crystal_Palace_Management_System extends Application {
    public static HTMdatabase test = new HTMdatabase();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
         //HTMdatabase test = new HTMdatabase();
        //test.createTable();
//       test.insertTable();
      test.readAllDataBase();
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
