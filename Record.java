import java.util.Calendar;

public class Record {
	private int ReservationID;
	private int VenueID;
	private int RoomID;
	private String RoomSize;
	private Calendar StartDate;
	private Calendar EndDate;
	private String OriginalInfo;
	
	public Record(int ID,int VID,int RID,String RS,Calendar SD,Calendar ED,String OI){
		this.ReservationID = ID;
		this.VenueID = VID;
		this.RoomID = RID;
		this.RoomSize = RS;
		this.StartDate = SD;
		this.EndDate = ED;
		this.OriginalInfo = OI;
//		printout();
	}
	
	public int getID(){
		return this.ReservationID;
	}
	
	public int getVenueID(){
		return this.VenueID;
	}
	
	public int getRoomID(){
		return this.RoomID;
	}
	
	public String getRoomSize(){
		return this.RoomSize;
	}
	
	public String getOriginalInfo(){
		return this.OriginalInfo;
	}
	
	public void printout(){
		System.out.print("OI"+this.OriginalInfo+"\n");
		System.out.print("ID"+this.ReservationID+"\n");
		System.out.print("VID:"+this.VenueID+"\n");
		System.out.print("RID:"+this.RoomID+"\n");
		System.out.print("SDmonth"+this.StartDate.get(Calendar.MONTH)+"\n");
		System.out.print("SDdate"+this.StartDate.get(Calendar.DATE)+"\n");
		System.out.print("EDmonth"+this.EndDate.get(Calendar.MONTH)+"\n");
		System.out.print("EDdate"+this.EndDate.get(Calendar.DATE)+"\n");
	}
	
}
