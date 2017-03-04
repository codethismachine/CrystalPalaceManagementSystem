/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Model;

/**
 *
 * @author Jason
 */
import java.sql.*;
public class InventoryDatabase {
  private Connection connection; 
  private Statement statement; 
  
  
  public boolean insertNewItem(int itemID, String itemName, int quantity, 
          String description, String location){
      boolean checkSuccess = false;
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "INSERT INTO INVENTORY(ITEM_ID, ITEM_NAME, QUANTITY, "
              + "DESCRIPTION, LOCATION) VALUES(?,?,?,?,?);";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1, itemID);
      pstmt.setString(2, itemName);
      pstmt.setInt(3, quantity);
      pstmt.setString(4, description);
      pstmt.setString(5, location);
      pstmt.executeUpdate();
//      statement.executeUpdate(sql);
      statement.close();
      connection.close();   
      checkSuccess = true;
      return true;
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
       return checkSuccess;
      }

  }
  
}
