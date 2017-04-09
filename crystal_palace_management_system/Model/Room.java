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
public class Room {
    private int RoomNum; 
    private int numberBeds;
    private String roomType;
    private String tvType; 
    private boolean occupancy;
    private boolean beingReserved;
    private boolean wheelchairBathroom;
    private boolean petFriendly;
    private boolean jacuzzi; 
    
    public Room(){
        
    }
    public int getRoomNum(){
        return RoomNum;
    }
    public String getRoomType(){
        return roomType;
    }
    public int getNumberBeds(){
        return numberBeds;
    }
    public String getTVType(){
        return tvType;
    }
    public boolean getAccessibility(){
        return wheelchairBathroom;
    }
    public boolean getPetFriendliness(){
        return petFriendly;
    }
    public boolean getJacuzzi(){
        return jacuzzi; 
    }
    public boolean getBeingReserved(){
        return beingReserved; 
    }
}
