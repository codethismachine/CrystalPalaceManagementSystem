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
public class Guest extends Human {
    private int loyaltyPoints; 
    
    public Guest(){
        super();
    }
    public int getLoyaltyPoints(){
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(){
        
    }
}
