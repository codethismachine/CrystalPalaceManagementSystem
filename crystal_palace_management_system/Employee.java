/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system;

import java.util.Date;

/**
 *
 * @author Jason
 */
public class Employee extends Human {
    private String userID;
    private String password; 
    private String address;
    private String jobTitle;
    private String wage;
    private String nickname; 
    
    public Employee(){
        super();
    }
    public String getUserID(){
        return userID;
    }
    public void setUserID(){
        
    }
    public String getPassword(){
        return password; 
    }
    public void setPassword(){
        
    }
    @Override
    public String getPhoneNumber(){
        return super.getPhoneNumber();
    }
    @Override
    public void setPhoneNumber(){
        super.setPhoneNumber();
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public void setJobTitle(){
        
    }
    @Override
    public Date getDateOfBirth(){
        return super.getDateOfBirth();
    }
    @Override
    public void setDateOfBirth(){
        super.setDateOfBirth();
    }
    public String getAddress(){
        return address; 
    }
    public void setAddress(){
        
    }
}
