import java.util.ArrayList;

public class Room {
	private int RoomID;
	private	String RoomName;
	private ArrayList<Booking> bookings;
	public Room(int RoomID,String RoomName){
		this.RoomID = RoomID;
		this.RoomName = RoomName;	
		bookings = new ArrayList<Booking>();
	}
	
	public String getRoomName(){
		return this.RoomName;
	}

	public int getRoomID(){
		return this.RoomID;
	}

	public ArrayList<Booking> BookingList(){
		return this.bookings;
	}
	
	public int FindBooking(int ReservationID){
		int result = -1;
		int k = 0;
		while (k < bookings.size()){
			if (bookings.get(k).getID() == ReservationID){
				result = k;
			}
			k++;
		}
//		System.out.println("re"+result);
		return result;
	}
}
	