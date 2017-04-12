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
  
  
  public boolean insertNewItem(int Item_ID, String Item_Name, double Price, 
          int Quantity, String Description, int Type, String Location){
      boolean checkSuccess = false;
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "INSERT INTO INVENTORY(ITEM_ID, ITEM_NAME, QUANTITY, "
              + "PRICE, DESCRIPTION, TYPE, LOCATION) VALUES(?,?,?,?,?,?,?);";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1, Item_ID);
      pstmt.setString(2, Item_Name);
      pstmt.setDouble(3, Price);
      pstmt.setInt(4, Quantity);
      pstmt.setString(5, Description);
      pstmt.setInt(6, Type);
      pstmt.setString(7, Location);
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
