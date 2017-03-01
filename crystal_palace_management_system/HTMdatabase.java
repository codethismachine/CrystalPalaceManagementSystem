/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system;

/**
 *
 * @author Jason
 */
import java.sql.*;
public class HTMdatabase {
  private Connection connection; 
  private Statement statement; 
  
 
  
  
  public void createTable(){
      try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "CREATE TABLE EMPLOYEE " +
                   "(EMPLOYEE_ID INT PRIMARY KEY     NOT NULL," +
                   " FIRST_NAME     VARCHAR    NOT NULL, " +
                   " LAST_NAME      VARCHAR    NOT NULL," +
                   " HIRE_YEAR           INT    NOT NULL, " +
                   " HIRE_MONTH          INT    NOT NULL, " +
                   " HIRE_DAY            INT    NOT NULL, " +
                   " DEPARTMENT_ID  INT        NOT NULL, " +
                   " USERNAME       VARCHAR, " + 
                   " PASSWORD       VARCHAR)";           
      
      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      System.out.println("Employee table created successfully");
  }
  
  public void insertNewEmployee(int empID, String fName, String lName, 
          int year, int month, int day, int deptID, String username, 
          String password){
       try { 
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      statement = connection.createStatement();
      String sql = "INSERT INTO EMPLOYEE(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, "
              + "HIRE_YEAR, HIRE_MONTH, HIRE_DAY, DEPARTMENT_ID, USERNAME, "
              + "PASSWORD) VALUES(?,?,?,?,?,?,?,?,?);";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1, empID);
      pstmt.setString(2, fName);
      pstmt.setString(3, lName);
      pstmt.setInt(4, year);
      pstmt.setInt(5, month);
      pstmt.setInt(6, day);
      pstmt.setInt(7, deptID); 
      pstmt.setString(8, username);
      pstmt.setString(9, password);
      pstmt.executeUpdate();
//      statement.executeUpdate(sql);
      statement.close();
      connection.close();    
      }catch(Exception e){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//      System.exit(0);
      }
      System.out.println("Employee added successfully");
      
     
  }
  public boolean loginCheck(String userName, String Password){
      boolean checker = false; 
       try {
      Class.forName("org.sqlite.JDBC");
      Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      //System.out.println("Opened database successfully");

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
      Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
      Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
