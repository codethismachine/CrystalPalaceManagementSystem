/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
/**
 *
 * @author Jason
 */
public class CPMS_Database {
   private Connection connection; 
  private Statement statement; 
  

  
  public void createTableEmployee(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE EMPLOYEE " +
                   "(User_ID      VARCHAR(45) PRIMARY KEY     NOT NULL," +
                   " Last_Name    VARCHAR(45)    NOT NULL, " +
                   " First_Name   VARCHAR(45)    NOT NULL," +
                   " Middle_Name  VARCHAR(45), " +
                   " Prefix       VARCHAR(45), " +
                   " Suffix       VARCHAR(45), " +
                   " Nickname     VARCHAR(45)," +
                   " Password     VARCHAR(45), " +
                   " Address      VARCHAR(45), " + 
                   " JobTitle     VARCHAR(45), " +
                   " Phone_Number VARCHAR(45), " + 
                   " SSN          VARCHAR(9),  " + 
                   " DOB          DATE, " + 
                   " Wage         FLOAT, " +
                   " Salary       FLOAT, " + 
                   " Employee_Type CHAR(1)  NOT NULL,  " + 
                   " Start_Date   Date  NOT NULL, " + 
                   " End_Date     Date, " + 
                   " Supervisor   VARCHAR(45))";
                         
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("Employee table created successfully");
  }
  public void createTableINVENTORY(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE INVENTORY " +
                   "(Item_ID      INT     PRIMARY KEY     NOT NULL," +
                   " Item_Name    VARCHAR(45)    NOT NULL, " +
                   " Price        FLOAT    NOT NULL," +
                   " Quantity     INT, " +
                   " Description  VARCHAR(45), " +
                   " Type         INT, " +
                   " Location     VARCHAR(45))";
                  
                         
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("INVENTORY table created successfully");
  }
  
  public void createTableTIMESHEET(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE TIMESHEET " +
                   "(idTimesheet  VARCHAR(45)  NOT NULL," +
                   " Clock_In     DATETIME     NOT NULL," +
                   " Clock_Out    DATETIME,     "  +    
                   " PRIMARY KEY (idTimesheet, Clock_In)," +
                   " FOREIGN KEY(idTimesheet) REFERENCES EMPLOYEE(User_ID))";
                  
                         
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("TIMESHEET table created successfully");
  }  
  public void createTableVISIT(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE VISIT " +
                   "(Reservation_Number  VARCHAR(45)  NOT NULL, " +
                   " Room_Number     INT, " +
                   " Note    VARCHAR(255), "  +  
                   " Start_Date DATE, " + 
                   " End_Date   DATE, " + 
                   " Check_In   DATETIME, " + 
                   " Check_Out  DATETIME, " + 
                   " Balance    FLOAT, " + 
                   " Cash   CHAR(1), " +
                   " Total_Adults   INT NOT NULL, " +
                   " Total_Children INT, " + 
                   " Multi_Reservation_Number   VARCHAR(45), " + 
                   " PRIMARY KEY (Reservation_Number), " +
                   " FOREIGN KEY(Room_Number) REFERENCES ROOM(Room_Number))";
                  
                         
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("VISIT table created successfully");
  }  
  
  public void createTableGUEST(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE GUEST " +
                   "(Phone_Number   VARCHAR(45)    PRIMARY KEY     NOT NULL," +
                   " Last_Name    VARCHAR(45)    NOT NULL, " +
                   " First_Name   VARCHAR(45)    NOT NULL,"  +
                   " Middle_Name  VARCHAR(45), " +
                   " Prefix       VARCHAR(45), " +
                   " Suffix       VARCHAR(45), " +
                   " Nickname     VARCHAR(45), " +
                   " Address      VARCHAR(45), " + 
                   " Banned       CHAR(1), " +  
                   " Visit_Reservation_Number   VARCHAR(45)        NOT NULL,"  + 
                   " FOREIGN KEY(Visit_Reservation_Number) REFERENCES "  + 
                   " VISIT(Reservation_Number))";
                         
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("GUEST table created successfully");
  }
  
  public void createTableROOM(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE ROOM " +
                   "(Room_Number        INT    PRIMARY KEY    NOT NULL," +
                   " Room_Type          VARCHAR(45)           NOT NULL," +
                   " Number_Beds        INT                   NOT NULL,"  +
                   " Occupancy_Status   INT                   NOT NULL," +
                   " Wheelchair         CHAR(1)               NOT NULL, " +
                   " Pet_Friendly       CHAR(1)               NOT NULL, " +
                   " Smoking            CHAR(1)               NOT NULL)";
                         
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("ROOM table created successfully");
  }
  public void createTableSERVICES(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE SERVICES " +
                   "(Phone_Number      VARCHAR(45)    PRIMARY KEY NOT NULL," +
                   " Provider          VARCHAR(45)                NOT NULL," +
                   " Description       VARCHAR(45))"; 
                         
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("SERVICES table created successfully");
  }
       
       
  
  public boolean insertNewEmployee(String User_ID, String Last_Name,
          String First_Name, String Middle_Name, String Prefix,
          String Suffix, String Nickname, String Password,
          String Address, String JobTitle, String Phone_Number,
          String SSN, String DateOfBirth, double Wage, double Salary,
          String Employee_Type, String Start_Date, String End_Date, String Supervisor){
      boolean checkSuccess = false;
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "INSERT INTO EMPLOYEE(User_ID, Last_Name, First_Name, "
              + "Middle_Name, Prefix, Suffix, Nickname, Password, "
              + "Address, JobTitle, Phone_Number, SSN, DOB, Wage,"
              + "Salary, Employee_Type, Start_Date, End_Date, Supervisor" 
              + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, User_ID);
      pstmt.setString(2, Last_Name);
      pstmt.setString(3, First_Name);
      pstmt.setString(4, Middle_Name);
      pstmt.setString(5, Prefix);
      pstmt.setString(6, Suffix);
      pstmt.setString(7, Nickname); 
      pstmt.setString(8, Password);
      pstmt.setString(9, Address);
      pstmt.setString(10, JobTitle);
      pstmt.setString(11, Phone_Number);
      pstmt.setString(12, SSN);
      pstmt.setString(13, DateOfBirth);
      pstmt.setDouble(14, Wage);
      pstmt.setDouble(15, Salary);
      pstmt.setString(16, Employee_Type);
      pstmt.setString(17, Start_Date);
      pstmt.setString(18, End_Date);
      pstmt.setString(19, Supervisor);
      pstmt.executeUpdate();
//      statement.executeUpdate(sql);
      statement.close();
      connection.close();   
      checkSuccess = true;
      return true;
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
       return checkSuccess;
//      System.exit(0);
      }
//      System.out.println("Employee added successfully");
//            return checkSuccess;
     
  }
  public boolean loginCheck(String userName, String Password){
      boolean checker = false; 
       try {
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      c.setAutoCommit(false);
      //System.out.println("Opened database successfully");

      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT EMP.User_ID, "
              + "EMP.Password FROM EMPLOYEE AS EMP;" );
      while ( rs.next() ) {
//         int id = rs.getInt("EMPLOYEE_ID");
//         String  fname = rs.getString("FIRST_NAME");
//         String  lname = rs.getString("LAST_NAME");
//         int hireYear  = rs.getInt("HIRE_YEAR");
//         int  hireMonth = rs.getInt("HIRE_MONTH");
//         int  hireDay = rs.getInt("HIRE_DAY");
//         int departmentID = rs.getInt("DEPARTMENT_ID");
         String username = rs.getString("User_ID");
         String password = rs.getString("Password");
//         System.out.println( "EMPOYEE ID = " + id );
//         System.out.println( "FIRST NAME = " + fname );
//         System.out.println( "LAST NAME = " + lname );
//         System.out.println( "HIRE DATE = " + hireMonth + "/" + hireDay + "/" 
//         + hireYear);
//         System.out.println( "DEPARTEMENT ID = " + departmentID);
//         System.out.println("USERNAME = " + username);
//         System.out.println("PASSWORD = " + password);
//         System.out.println();
         if(username.equals(userName) && password.equals(Password)){
             System.out.println("UserFound");
             checker = true;
             break;
         }
      }
      c.commit();
      
      stmt.close();
      c.close();
      return checker;
    } catch ( ClassNotFoundException | SQLException e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
       
    }
      return checker;
  }
  public void readAllDataBase(){
      try {
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM EMPLOYEE;" );
      while ( rs.next() ) {
         int id = rs.getInt("EMPLOYEE_ID");
         String  fname = rs.getString("FIRST_NAME");
         String  lname = rs.getString("LAST_NAME");
         int hireYear  = rs.getInt("HIRE_YEAR");
         int  hireMonth = rs.getInt("HIRE_MONTH");
         int  hireDay = rs.getInt("HIRE_DAY");
         int departmentID = rs.getInt("DEPARTMENT_ID");
         String username = rs.getString("USERNAME");
         String password = rs.getString("PASSWORD");
         System.out.println( "EMPOYEE ID = " + id );
         System.out.println( "FIRST NAME = " + fname );
         System.out.println( "LAST NAME = " + lname );
         System.out.println( "HIRE DATE = " + hireMonth + "/" + hireDay + "/" 
         + hireYear);
         System.out.println( "DEPARTEMENT ID = " + departmentID);
         System.out.println("USERNAME = " + username);
         System.out.println("PASSWORD = " + password);
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
  }
   public void deleteRecord(int employeeID){
      try {
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      Statement stmt = c.createStatement();
      String sql = "DELETE from EMPLOYEE where EMPLOYEE_ID="
              + employeeID + ";";
      stmt.executeUpdate(sql);
      c.commit();
      System.out.println("Cleared table successfully");
     
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
  }
}
