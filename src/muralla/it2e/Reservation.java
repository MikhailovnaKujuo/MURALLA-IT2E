package muralla.it2e;

import java.time.LocalDate;

public class Reservation {
    int id;
    String name;
    String roomType;
    LocalDate checkIn, checkOut;
    String payStatus, status;
    
    public void addReservation(int id, String name, String type, LocalDate in, LocalDate out, String pstats){
        this.id = id;
        this.name = name;
        this.roomType = type;
        this.checkIn = in;
        this.checkOut = out;
        this.payStatus = pstats;
    }
    
    public void viewReservation(){
        this.status = (this.payStatus.equals("Paid")) ? "Confirmed" : "Pending";
        
        System.out.printf("%-12d %-14s %-12s %-17s %-17s %-17s %-15s\n", id, name, roomType, checkIn.toString(), checkOut.toString(), payStatus, status);
        
    }
}
