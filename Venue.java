import java.util.ArrayList;
import java.util.Calendar;

public class Venue {
	private String name;
	private int venueid;
	private ArrayList<Room> Small;
	private ArrayList<Room> Large;
	public Venue(String VenueName,int VenueID){
		venueid = VenueID;
		name = VenueName;
		Small = new ArrayList<Room>();
		Large = new ArrayList<Room>();
	}
	
	public String getVenueName (){
		return this.name;
	}
	
	public int getVenueID (){
		return this.venueid;
	}
	
	public ArrayList<Room> SmallRoomList(){
		return this.Small;
	}
	
	public ArrayList<Room> LargeRoomList(){
		return this.Large;
	}
	
	public ArrayList<Room> FindRoomList(String RoomSize){
		ArrayList<Room> result = null;
		if (RoomSize.equals("small")){
			result = this.Small;
		} else if (RoomSize.equals("large")){
			result = this.Large;
		}
		return result;
	}
		
	public void addRoom(String RoomName,String RoomSize){
		if (RoomSize.equals("small")){
			int ID = Small.size();
			Room newroom = new Room(ID,RoomName);
			Small.add(newroom);
		} else if (RoomSize.equals("large")){
			int ID = Large.size();
			Room newroom = new Room(ID,RoomName);
			Large.add(newroom);
		}
		
	}
	
	public int FirstAvailbleRoom(Calendar StartDate, Calendar EndDate,Venue venue,String RoomSize){
		int RoomID = -1;
		
		int k = 0;		
		while (k < (venue.FindRoomList(RoomSize).size()-1) && ConflictCheck(StartDate,EndDate,venue.FindRoomList(RoomSize).get(k).BookingList())){
			k++;
		}

		if (k == (venue.FindRoomList(RoomSize).size()-1)){
			if (ConflictCheck(StartDate,EndDate,venue.FindRoomList(RoomSize).get(k).BookingList())){
				k = -1;
			} else {
				RoomID = k;
			}
		}
		
		if (venue.FindRoomList(RoomSize).size()==0){
			k = -1;
		}
		
		
		RoomID = k;	
		return RoomID;
	}

	public boolean ConflictCheck(Calendar StartDate,Calendar EndDate,ArrayList<Booking> input){
		int k = 0;
		boolean result = false;
		while (k < input.size()){
			if (StartDate.after(input.get(k).getEndDate())){
				result = false;
			} else if (StartDate.before(input.get(k).getStartDate())){
				if (EndDate.before(input.get(k).getStartDate())){
					result = false;
				} else {
					result = true;
					break;
				}	
			} else {
				result = true;
				break;
			}
			k++;
		}
		return result;
	}
	
}
