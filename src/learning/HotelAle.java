package learning;

import java.util.HashMap;
import java.util.Map;


public class HotelAle {


	    private Map<Integer, Map<Integer, Room>> floors;

	    public HotelAle() {
	        floors = new HashMap<Integer, Map<Integer, Room>>();

	        // Add three floors to the hotel
	        for (int i = 1; i <= 3; i++) {
	            Map<Integer, Room> rooms = new HashMap<Integer, Room>();

	            // Add four rooms to each floor
	            for (int j = 1; j <= 4; j++) {
	                rooms.put(j, new Room(2, "Queen", "2022-04-20", "2022-04-22"));
	            }

	            floors.put(i, rooms);
	        }
	    }

	    // Method to get a specific room
	    public Room getRoom(int floor, int roomNumber) {
	        Map<Integer, Room> rooms = floors.get(floor);
	        return rooms.get(roomNumber);
	    }

	    // Method to book a specific room
	    public void bookRoom(int floor, int roomNumber, String checkIn, String checkOut) {
	        Map<Integer, Room> rooms = floors.get(floor);
	        Room room = rooms.get(roomNumber);
	        room.setNextBooking(checkIn, checkOut);
	    }

	    // Room class
	    public static class Room {
	        private int numBeds;
	        private String bedSize;
	        private String lastCleaned;
	        private String nextBookingCheckIn;
	        private String nextBookingCheckOut;

	        public Room(int numBeds, String bedSize, String lastCleaned, String nextBookingCheckOut) {
	            this.numBeds = numBeds;
	            this.bedSize = bedSize;
	            this.lastCleaned = lastCleaned;
	            this.nextBookingCheckIn = null;
	            this.nextBookingCheckOut = nextBookingCheckOut;
	        }

	        public int getNumBeds() {
	            return numBeds;
	        }

	        public void setNumBeds(int numBeds) {
	            this.numBeds = numBeds;
	        }

	        public String getBedSize() {
	            return bedSize;
	        }

	        public void setBedSize(String bedSize) {
	            this.bedSize = bedSize;
	        }

	        public String getLastCleaned() {
	            return lastCleaned;
	        }

	        public void setLastCleaned(String lastCleaned) {
	            this.lastCleaned = lastCleaned;
	        }

	        public String getNextBookingCheckIn() {
	            return nextBookingCheckIn;
	        }

	        public String getNextBookingCheckOut() {
	            return nextBookingCheckOut;
	        }

	        public void setNextBooking(String checkIn, String checkOut) {
	            this.nextBookingCheckIn = checkIn;
	            this.nextBookingCheckOut = checkOut;
	        }
	    }

	    // Main method to test the hotel class
	    public static void main(String[] args) {
	        HotelAle hotel = new HotelAle();
	        System.out.println("Number of beds in room 1 on floor 1:::" + hotel.getRoom(1, 1).getNumBeds());
	        System.out.println("Bed size in room 1 on floor 1:::" + hotel.getRoom(1, 1).getBedSize());
	        System.out.println("Last cleaned date for room 1 on floor 1:::" + hotel.getRoom(1, 1).getLastCleaned());
	        System.out.println("Next booking check-in date for room 1 on floor 1:::" + hotel.getRoom(1, 1).getNextBookingCheckIn());
	        System.out.println("Next booking check-out date for room 1 on floor 1:::" + hotel.getRoom(1, 1).getNextBookingCheckOut());
	        System.out.println("Booking room 2 on floor 2 from 2022-04-23 to 2022-04-25...:::");
	        hotel.bookRoom(2, 2, "2022-04-23", "2022-04-25");
	        System.out.println("Next booking check-in date for room 2 on floor 2:::" + hotel.getRoom(2, 2).getNextBookingCheckIn());
	        System.out.println("Next booking check-out date for room 2 on floor 2:::" + hotel.getRoom(2, 2).getNextBookingCheckOut());
	    }
	}

