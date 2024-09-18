package muralla.it2e;

import java.time.format.*;
import java.time.LocalDate;
import java.util.*;

public class BookingHandler {
    public void getReservations(){
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        System.out.print("Enter number of Bookings: ");
        int count = scan.nextInt();
        
        Reservation[] books = new Reservation[count];
        
        for(int i = 0; i < count; i++){
            books[i] = new Reservation();
            
            System.out.printf("\nEnter Details of Booking %d:\n", (i + 1));
            
            boolean alreadyExist;
            int id;
            do {
                System.out.print("Booking ID: ");
                id = scan.nextInt();
                alreadyExist = false;
                for (Reservation book : books) {
                    if (book != null && book.id == id) {
                        System.out.println("This ID already exists. Please enter a different ID.");
                        alreadyExist = true;
                        break;
                    }
               }   
            } while (alreadyExist);
            
            scan.nextLine();
            System.out.print("Guest Name: ");
            String name = scan.nextLine();
            
            List<String> types = Arrays.asList("Single", "Double", "Suite");
            String type;
            while (true){
                System.out.print("Room Type (Single, Double, Suite): ");
                type = scan.nextLine();
                
                if(!types.contains(type)){
                    System.out.println("Invalid.");
                }else{
                    break;
                }
            }
            
            LocalDate in = null;
            while (in == null) {
                System.out.print("Check-in Date (yyyy-mm-dd): ");
                String dateInput = scan.nextLine();
                try {
                    in = LocalDate.parse(dateInput, dateFormat);              
                }catch (DateTimeParseException e) {
                    System.out.println("Invalid Date Input.");
                }                
            }
            
            LocalDate out = null;
            while (out == null) {
                System.out.print("Check-out Date (yyyy-mm-dd): ");
                String dateInput = scan.nextLine();
                try {
                    out = LocalDate.parse(dateInput, dateFormat);
                    if (out.isBefore(in)) {
                        System.out.println("Check Out date should not be before Check In.");
                        out = null; 
                    }
                }catch (DateTimeParseException e) {
                    System.out.println("Invalid Date Input.");
                }                
            }
            
            List<String> stats = Arrays.asList("Paid", "Not Paid");
            String pstats;
            while (true){
                System.out.print("Status (Paid, Not Paid): ");
                pstats = scan.nextLine();
                
                if(!stats.contains(pstats)){
                    System.out.println("Invalid.");
                }else{
                    break;
                }
            }
            
            books[i].addReservation(id, name, type, in, out, pstats);       
        }
        
        System.out.println("");
        System.out.printf("%-12s %-14s %-12s %-17s %-17s %-17s %-15s\n", "Booking ID", "Guest Name", "Room Type", "Check-in Date", "Check-out Date", "Payment Status", "Status");
        for(Reservation book : books){
            book.viewReservation();
        }
        
    }
}
