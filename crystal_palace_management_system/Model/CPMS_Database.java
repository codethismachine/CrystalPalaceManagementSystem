/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Model;

import crystal_palace_management_system.Crystal_Palace_Management_System;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 *
 * @author Jason
 */
public class CPMS_Database {
   private Connection connection; 
  private Statement statement; 
  public String empType;

  
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
  public void employeeType(String emp){
       empType = emp;
      
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
              + "EMP.Password, EMP.Employee_Type FROM EMPLOYEE AS EMP;" );
      while ( rs.next() ) {
         String username = rs.getString("User_ID");
         String password = rs.getString("Password");
            empType = rs.getString("Employee_Type");
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
   
      public boolean insertNewReservation(int Room_Number,
          String Note, String Start_Date, String End_Date, String Check_In, String Check_Out, 
          double Balance, String Cash, int Total_Adults, int Total_Children, String Multi_Reservation_Number
           )
      {
      boolean checkSuccess = false;
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "INSERT INTO VISIT(Room_Number, Note,"
              + "Start_Date, End_Date, Check_In, Check_Out, Balance, Cash, "
              + "Total_Adults, Total_Children, Multi_Reservation_Number "
              + ") VALUES(?,?,?,?,?,?,?,?,?,?,?);";
      
      System.out.println("passed INSERT statement");
      
      PreparedStatement pstmt = connection.prepareStatement(sql);
      //pstmt.setString(1, Reservation_Number);
      pstmt.setInt(1, Room_Number);
      pstmt.setString(2, Note);
      pstmt.setString(3, Start_Date);
      pstmt.setString(4, End_Date);
      pstmt.setString(5, Check_In);
      pstmt.setString(6, Check_Out); 
      pstmt.setDouble(7, Balance);
      pstmt.setString(8, Cash);
      pstmt.setInt(9, Total_Adults);
      pstmt.setInt(10, Total_Children);
      pstmt.setString(11, Multi_Reservation_Number);
      pstmt.executeUpdate();
      
            System.out.println("passed executeUpdate statement");
//      statement.executeUpdate(sql);
      statement.close();
      connection.close();   
      
            System.out.println("Closed connection");
      checkSuccess = true;
      
            System.out.println("checkSuccess = true");
      return checkSuccess;
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
      System.out.println("Enters catch = YES");
       return checkSuccess;
//      System.exit(0);
      } 
      }
       public boolean insertNewGuest(String Phone_Number, String Last_Name,
        String First_Name, String Middle_Name, String Prefix, String Suffix, 
        String Nickname, String Address, String Banned,
        int Visit_Reservation_Number
           )
      {
      boolean checkSuccess = false;
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "INSERT INTO GUEST(Phone_Number, Last_Name, First_Name, "
              + "Middle_Name, Prefix, Suffix, Nickname, Address, Banned,"
              + "Visit_Reservation_Number"
              + ") VALUES(?,?,?,?,?,?,?,?,?,?);";
      
      System.out.println("passed INSERT statement");
      
      PreparedStatement pstmt = connection.prepareStatement(sql);
      //pstmt.setString(1, Reservation_Number);
      pstmt.setString(1, Phone_Number);
      pstmt.setString(2, Last_Name);
      pstmt.setString(3, First_Name);
      pstmt.setString(4, Middle_Name);
      pstmt.setString(5, Prefix);
      pstmt.setString(6, Suffix); 
      pstmt.setString(7, Nickname);
      pstmt.setString(8, Address);
      pstmt.setString(9, Banned);
      pstmt.setInt(10, Visit_Reservation_Number);
      pstmt.executeUpdate();
      
            System.out.println("passed executeUpdate statement");
//      statement.executeUpdate(sql);
      statement.close();
      connection.close();   
      
            System.out.println("Closed connection");
      checkSuccess = true;
      
            System.out.println("checkSuccess = true");
      return checkSuccess;
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
      System.out.println("Enters catch = YES");
       return checkSuccess;
//      System.exit(0);
      } 
      }
       
        public int reservationNumber(){
      int number = -1; 
       try {
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      c.setAutoCommit(false);
      System.out.println("Entered reservation number");

      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT max(Reservation_Number) "
              + "FROM VISIT;" );
       System.out.println("Parsed max reservation number");
      
      while ( rs.next() ) {

          number = rs.getInt("max(Reservation_Number)");
        


      }
      c.commit();
      
      stmt.close();
      c.close();
      return number;
    } catch ( ClassNotFoundException | SQLException e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
       
    }
      return number;
  }
  
public void checkInViewer(String Reservation_Number, String 
            Last_Name, String First_Name, String Phone_Number){
       
       try {
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      c.setAutoCommit(false);
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
                + "OR FirstName like '" + First_Name + "' "
                + "OR Phone_Number like '" + Phone_Number + "');";
        }
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(Query);
      
    // System.out.println("Entered reservation number");

      /*Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT max(Reservation_Number) "
              + "FROM VISIT;" );
       System.out.println("Parsed max reservation number");
      
      while ( rs.next() ) {

          number = rs.getInt("max(Reservation_Number)");*/
        


      
      c.commit();
      
      stmt.close();
      c.close();
      
    } catch ( ClassNotFoundException | SQLException e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
       
    }
      
  }
 public void insertClockIn(String username, String clockInDate, String 
         clockInTime){
     
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");
      statement = connection.createStatement();
      String sql = "INSERT INTO TIMESHEET(idTimesheet, Clock_In_Date, Clock_In_Time"
              + ") VALUES(?,?,?);";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, username);
      pstmt.setString(2, clockInDate);
      pstmt.setString(3, clockInTime);
      pstmt.executeUpdate();
//      statement.executeUpdate(sql);
      statement.close();
      connection.close();   
   
      
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());

      }

     
  }
 public void insertClockOut(String username, String clockOutDate, String 
         clockOutTime){

       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");
      statement = connection.createStatement();
      double clockInMinutes = clockInTimeGet(username, clockOutDate);
      System.out.println("clock in minutes = " + clockInMinutes);
     double totalHours = (Crystal_Palace_Management_System.clockOutTotalMinutes - 
            clockInMinutes) / 60;
      NumberFormat formatter = new DecimalFormat("#00.00");
      String totalOutput = formatter.format(totalHours);
      System.out.println("total output = " + totalOutput);
      //String totalHours = "3";
      System.out.println("clock out time " + clockOutTime);
      System.out.println("clcok out date" +clockOutDate);
      
      String sql = "UPDATE TIMESHEET SET Clock_Out = " + "'"+clockOutTime+"'" + ","   
            + " Time_Worked = " + "'"+totalOutput +"'"
            + " WHERE idTimesheet = " +"'"+ username+"'" + " AND Clock_In_Date = "
            + "'"+clockOutDate + "'" + ";";
 
      PreparedStatement pstmt = connection.prepareStatement(sql);
//      //pstmt.setString(1, username);
//     // pstmt.setString(2, clockInDate);
//     // pstmt.setString(3, clockInTime);
      pstmt.execute();
//      statement.executeUpdate(sql);
    
      statement.close();
      connection.close();   
   
      
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());

      }

     
  }
  public double clockInTimeGet(String username, String clockInDate){
      double total = 0.0;
       try {
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      c.setAutoCommit(false);
      //System.out.println("Opened database successfully");

      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT Clock_In_Time, idTimesheet FROM TIMESHEET WHERE"
              + " idTimesheet = " + "'"+ username +"'" + "AND Clock_In_Date = " + "'" + clockInDate + "'"+ ";");
      while ( rs.next() ) {
      
         String tempClockIn = rs.getString("Clock_In_Time");
         System.out.println("entered clock in time get ");
         String hour = tempClockIn.substring(0,2);
         String minutes = tempClockIn.substring(3,5);
         System.out.println("HoursParsed= " + hour + " MinutesParsed = " + minutes);
         total = (Double.parseDouble(hour) * 60) + Double.parseDouble(minutes);
         //return total;
        
      
      }
      rs.close();
      c.commit();
      
      stmt.close();
      c.close();
   
    } catch ( ClassNotFoundException | SQLException e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
       
    }
     return total;
  }
  public boolean UpdateEmployee(String User_ID, String Last_Name,
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
      String sql = "UPDATE EMPLOYEE SET Last_Name = " + "'"+Last_Name+"'" + ","   
            + " First_Name = " + "'"+First_Name +"' ,"
              + " Middle_Name = " + "'"+Middle_Name +"' ,"
              + " Prefix = " + "'"+Prefix +"' ,"
              + " Suffix = " + "'"+Suffix +"' ,"
              + " Nickname = " + "'"+Nickname +"' ,"
              + " Password = " + "'"+Password +"' ,"
              + " Address = " + "'"+Address +"' ,"
              + " JobTitle = " + "'"+JobTitle +"' ,"
              + " Phone_Number = " + "'"+Phone_Number +"' ,"
              + " SSN = " + "'"+SSN +"' ,"
              + " DOB = " + "'"+DateOfBirth +"' ,"
              + " Wage = " + "'"+Wage +"' ,"
              + " Salary = " + "'"+Salary +"' ,"
              + " Employee_Type = " + "'"+Employee_Type +"' ,"
              + " Start_Date = " + "'"+Start_Date +"' ,"
              + " End_Date = " + "'"+End_Date +"' ,"
              + " Supervisor = " + "'"+Supervisor +"' "
            + " WHERE User_ID = " +"'"+ User_ID+"'" + ";";
//      statement.executeUpdate(sql);
        PreparedStatement pstmt = connection.prepareStatement(sql);
//      //pstmt.setString(1, username);
//     // pstmt.setString(2, clockInDate);
//     // pstmt.setString(3, clockInTime);
      pstmt.execute();
      statement.close();
      connection.close();   
      checkSuccess = true;
      return true;
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
       return checkSuccess;
//      System.exit(0);
      }
  }
  public boolean UpdateReservation(String ReservationNumber,int Room_Number,
          String Note, String Start_Date, String End_Date, String Check_In, String Check_Out, 
          double Balance, String Cash, int Total_Adults, int Total_Children, String Multi_Reservation_Number,
          String Phone_Number, String Last_Name,
        String First_Name, String Middle_Name, String Prefix, String Suffix, 
        String Nickname, String Address, String Banned)
      {
      boolean checkSuccess = false;
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:CPMS.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
       String sql = "UPDATE VISIT SET Room_Number = " + "'"+Room_Number+"'" + ","   
            + " Note = " + "'"+Note +"' ,"
              + " Start_Date = " + "'"+Start_Date +"' ,"
              + " End_Date = " + "'"+End_Date +"' ,"
              + " Check_In = " + "'"+Check_In +"' ,"
              + " Check_Out = " + "'"+Check_Out+"' ,"
              + " Balance = " + "'"+Balance +"' ,"
              + " Cash = " + "'"+Cash +"' ,"
              + " Total_Adults = " + "'"+Total_Adults +"' ,"
              + " Total_Children = " + "'"+Total_Children +"' ,"
              + " Multi_Reservation_Number = " + "'"+Multi_Reservation_Number +"' "
            + " WHERE Reservation_Number = " +"'"+ ReservationNumber+"'" + ";";
      
       String sql2 = "UPDATE GUEST SET Last_Name = " + "'"+Last_Name +"' ,"
              + " First_Name = " + "'"+First_Name +"' ,"
              + " Middle_Name = " + "'"+Middle_Name +"' ,"
              + " Prefix = " + "'"+Prefix +"' ,"
              + " Suffix = " + "'"+Suffix+"' ,"
              + " Nickname = " + "'"+Nickname +"' ,"
              + " Address = " + "'"+Address +"' ,"
              + " Banned = " + "'"+Banned +"' "
            + " WHERE Visit_Reservation_Number = " +"'"+ ReservationNumber+"'" + ";";
      
      System.out.println("passed INSERT statement");
      
      PreparedStatement pstmt = connection.prepareStatement(sql);     
      pstmt.executeUpdate();
      PreparedStatement pstmt2 = connection.prepareStatement(sql2);     
      pstmt2.executeUpdate();
            System.out.println("passed executeUpdate statement");
//      statement.executeUpdate(sql);
      statement.close();
      connection.close();   
      
            System.out.println("Closed connection");
      checkSuccess = true;
      
            System.out.println("checkSuccess = true");
      return checkSuccess;
      }catch(ClassNotFoundException | SQLException e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
      System.out.println("Enters catch = YES");
       return checkSuccess;
//      System.exit(0);
      } 
      }
}