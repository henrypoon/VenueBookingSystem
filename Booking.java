import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Booking {
	private Calendar StartDate;
	private Calendar EndDate;
	private int ReservationID;
	private int days;
	
	public Booking (int StartMonth,int EndMonth,int StartDate,int EndDate,int ReservationID){
		this.StartDate = new GregorianCalendar(2016,StartMonth,StartDate);
		this.EndDate = new GregorianCalendar(2016,EndMonth,EndDate);
		this.ReservationID = ReservationID;
		this.days = daysBetween(this.StartDate,this.EndDate)+1;
	}
	
	public int getID(){
		return ReservationID;
	}
	
	public static int daysBetween(Calendar StartDate, Calendar EndDate) {  
		  Calendar date = (Calendar) StartDate.clone();  
		  int daysBetween = 0;  
		  while (date.before(EndDate)) {  
		    date.add(Calendar.DAY_OF_MONTH, 1);  
		    daysBetween++;  
		  }  
		  return daysBetween;  
	}  
	
	public int getDays(){
		return this.days;
	}
		
	public Calendar getStartDate(){
		return this.StartDate;
	}

	public Calendar getEndDate(){
		return this.EndDate;
	}
	
	public static Comparator<Booking> sortbydate = new Comparator<Booking>(){
		public int compare(Booking b1,Booking b2){
			return b1.getStartDate().compareTo(b2.getStartDate());
		}	
	};
		
	public void printout(){
		System.out.print("Smonth"+this.StartDate.get(Calendar.MONTH)+"\n");
		System.out.print("Sdate"+this.StartDate.get(Calendar.DATE)+"\n");
		System.out.print("EMonth"+this.EndDate.get(Calendar.MONTH)+"\n");
		System.out.print("Edate"+this.EndDate.get(Calendar.DATE)+"\n");
		System.out.print("ID"+this.ReservationID);
	}
	
}
