/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal_palace_management_system.Model;

import crystal_palace_management_system.Model.Room;
import java.util.Date;

/**
 *
 * @author Jason
 */
public class Visit extends Room{
    private int reservationNumber;
    private int tempReservationNumber;
    private int roomNum;
    private String note;
    private Date endDate; 
    private Date checkInTime; 
    private Date checkOutTime;
    private double balanceEstimate;
    private boolean cash; 
    
    public Visit(){
        super(); 
    }
    @Override
    public int getRoomNum(){
        return this.roomNum;
    }
    public void setRoomNum(int roomNum){
        this.roomNum = roomNum;
    }
   public int getReservationNumber(){
       return reservationNumber;
   }
   public void setReservationNumber(){
       
   }
   public int getTempReservationNumber(){
       return tempReservationNumber;
   }
   public String getNote(){
       return note;
   }
   public void setNote(String note){
       this.note += note; 
   }
   public Date getEndDate(){
       return endDate;
   }
   public void setEndDate(){
       
   }
   public Date getCheckInTime(){
       return checkInTime;
   }
   public void setCheckInTime(){
       
   }
   public Date getCheckOutTime(){
       return checkOutTime;
   }
   public void setCheckOutTime(){
       
   }
   public double getBalanceEstimate(){
       return balanceEstimate;
   }
   public void setBalanceEstimate(){
       
   }
   public boolean getCash(){
       return cash;
   }
   public void setCash(){
       
   }
}
