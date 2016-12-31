import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Manager {
	private ArrayList<Venue> venues;
	private ArrayList<Record> records;
	private int global = 0;
 	public Manager(){
		venues = new ArrayList<Venue>();
		records = new ArrayList<Record>();
	}
	
	public void addVenueAndRoom(String info){
		Scanner sc = new Scanner(info);
		String VenueName = sc.next();
		String RoomName = sc.next();
		String RoomSize = sc.next();
		
		
		
		if (!VenueCheck(VenueName)){
			int ID = venues.size();
			Venue newVenue = new Venue(VenueName,ID);
			venues.add(newVenue);
		}

		int VenueID = getVenueID(VenueName);
		venues.get(VenueID).addRoom(RoomName,RoomSize);	
		
		
		sc.close();
	}
	
	public void RequestBooking(String info){
		Scanner sc = new Scanner(info);
		int ReservationID = sc.nextInt();
		int StartMonth = monthToNumber(sc.next());
		int StartDate = sc.nextInt();
		Calendar Start = new GregorianCalendar(2016,StartMonth,StartDate);
		int EndMonth = monthToNumber(sc.next());
		int EndDate = sc.nextInt();
		Calendar End = new GregorianCalendar(2016,EndMonth,EndDate);
		int NumOfRoom1 = sc.nextInt();
		String RoomSize1 = sc.next();
		
		
		if (sc.hasNext()){
			int NumOfRoom2 = sc.nextInt();
			String temp = RoomSize1;
			String RoomSize2 = null;
			
			if(RoomSize1.equals("small")){
				RoomSize2 = sc.next();
			} else if (RoomSize1.equals("large")){
				RoomSize1 = sc.next();
				RoomSize2 = temp;
			}
			
			int	availbleVenueID1 = availbleBooking(Start,End,venues,RoomSize1);
			int	availbleVenueID2 = availbleBooking(Start,End,venues,RoomSize2);
			
			if (availbleVenueID1 == availbleVenueID2 && availbleVenueID1 != -1 && availbleVenueID2 != -1){
				int availbleRoomID1 = addBooking(availbleVenueID1,Start,End,venues,RoomSize1,ReservationID,StartMonth,StartDate,EndMonth,EndDate,info);
				int availbleRoomID2 = addBooking(availbleVenueID2,Start,End,venues,RoomSize2,ReservationID,StartMonth,StartDate,EndMonth,EndDate,info);										
				System.out.println("Reservation "+ReservationID+" "+venues.get(availbleVenueID1).getVenueName()+" "+venues.get(availbleVenueID1).FindRoomList(RoomSize1).get(availbleRoomID1).getRoomName()+" "+venues.get(availbleVenueID1).FindRoomList(RoomSize2).get(availbleRoomID2).getRoomName());
				global = 1;
			} else {
				global = 0;
				System.out.println("Request rejected");
			}
							
			
		} else {
			int availbleVenueID = availbleBooking(Start,End,venues,RoomSize1);
			if (availbleVenueID != -1){
				int availbleRoomID = addBooking(availbleVenueID,Start,End,venues,RoomSize1,ReservationID,StartMonth,StartDate,EndMonth,EndDate,info);
				
				System.out.println("Reservation "+ReservationID+" "+venues.get(availbleVenueID).getVenueName()+" "+venues.get(availbleVenueID).FindRoomList(RoomSize1).get(availbleRoomID).getRoomName());
				global = 1;
			} else {
				global = 0;
				System.out.println("Request rejected");
			}
		}
		sc.close();
	}
	
	private int addBooking(int availbleVenueID,Calendar Start,Calendar End,ArrayList<Venue> venues,String RoomSize,int ReservationID,int StartMonth, int StartDate,int EndMonth,int EndDate,String info){
		int availbleRoomID = venues.get(availbleVenueID).FirstAvailbleRoom(Start, End, venues.get(availbleVenueID), RoomSize);
		Booking newBooking = new Booking(StartMonth,EndMonth,StartDate,EndDate, ReservationID);
		venues.get(availbleVenueID).FindRoomList(RoomSize).get(availbleRoomID).BookingList().add(newBooking);
		Collections.sort(venues.get(availbleVenueID).FindRoomList(RoomSize).get(availbleRoomID).BookingList(),Booking.sortbydate);
		Record newRecord = new Record(ReservationID,availbleVenueID,availbleRoomID,RoomSize,Start,End,info);
		records.add(newRecord);	
		return availbleRoomID;
	}
			
	private int availbleBooking (Calendar Start, Calendar End,ArrayList<Venue> venues, String RoomSize){
		int venueID = -1;
		int k = 0;
		while (k < (venues.size()-1) && venues.get(k).FirstAvailbleRoom(Start, End, venues.get(k), RoomSize)== -1){
			k++;
		}
		if (k == (venues.size()-1)){
			if (venues.get(k).FirstAvailbleRoom(Start, End, venues.get(k), RoomSize) == -1){
				k = -1;
			} else {
				venueID = k;
			}
		}
		venueID = k;
		return venueID;
	}
	
	public void ChangeBooking(String info){
		Scanner sc = new Scanner(info);
		int ReservationID = sc.nextInt();
//		System.out.print(ReservationID);
		int RecordID = findNext(ReservationID);
		String BackupInfo = records.get(RecordID).getOriginalInfo();
//		System.out.print(BackupInfo);
		Cancel(info);
				
		int StartMonth = monthToNumber(sc.next());
		int StartDate = sc.nextInt();
		Calendar Start = new GregorianCalendar(2016,StartMonth,StartDate);
		int EndMonth = monthToNumber(sc.next());
		int EndDate = sc.nextInt();
		Calendar End = new GregorianCalendar(2016,EndMonth,EndDate);
		int NumOfRoom1 = sc.nextInt();
		String RoomSize1 = sc.next();
		
		
		if (sc.hasNext()){
			int NumOfRoom2 = sc.nextInt();
			String temp = RoomSize1;
			String RoomSize2 = null;
			
			if(RoomSize1.equals("small")){
				RoomSize2 = sc.next();
			} else if (RoomSize1.equals("large")){
				RoomSize1 = sc.next();
				RoomSize2 = temp;
			}
			
			int	availbleVenueID1 = availbleBooking(Start,End,venues,RoomSize1);
			int	availbleVenueID2 = availbleBooking(Start,End,venues,RoomSize2);
			
			if (availbleVenueID1 == availbleVenueID2 && availbleVenueID1 != -1 && availbleVenueID2 != -1){
				int availbleRoomID1 = addBooking(availbleVenueID1,Start,End,venues,RoomSize1,ReservationID,StartMonth,StartDate,EndMonth,EndDate,info);
				int availbleRoomID2 = addBooking(availbleVenueID2,Start,End,venues,RoomSize2,ReservationID,StartMonth,StartDate,EndMonth,EndDate,info);										
				System.out.println("Change "+ReservationID+" "+venues.get(availbleVenueID1).getVenueName()+" "+venues.get(availbleVenueID1).FindRoomList(RoomSize1).get(availbleRoomID1).getRoomName()+" "+venues.get(availbleVenueID1).FindRoomList(RoomSize2).get(availbleRoomID2).getRoomName());
				global = 1;
			} else {
				global = 0;
				System.out.println("Request rejected");
			}
							
			
		} else {
			int availbleVenueID = availbleBooking(Start,End,venues,RoomSize1);
			if (availbleVenueID != -1){
				int availbleRoomID = addBooking(availbleVenueID,Start,End,venues,RoomSize1,ReservationID,StartMonth,StartDate,EndMonth,EndDate,info);
				
				System.out.println("Change "+ReservationID+" "+venues.get(availbleVenueID).getVenueName()+" "+venues.get(availbleVenueID).FindRoomList(RoomSize1).get(availbleRoomID).getRoomName());
				global = 1;
			} else {
				global = 0;
				System.out.println("Request rejected");
			}
		}
		
		if (global == 0){
			RequestBooking(BackupInfo);
		}
			
		sc.close();
	}
	
	public void CancelBooking(String info){
		Scanner sc = new Scanner(info);
		int ReservationID = sc.nextInt();
		Cancel(info);
		System.out.println("Cancel "+ReservationID);
		sc.close();
	}
	
	public void Cancel(String info){
		Scanner sc = new Scanner(info);
		int ReservationID = sc.nextInt();
		int index = findNext(ReservationID);
		if ((index+1)<records.size() && records.get(index+1).getID() == ReservationID){
			deleteInfo(index,ReservationID);
			deleteInfo(index,ReservationID);
			
		} else {
			deleteInfo(index,ReservationID);
		}
		sc.close();	
	}
	
	private void deleteInfo(int index,int ReservationID){
		 int VenueID = records.get(index).getVenueID();
		 int RoomID = records.get(index).getRoomID();
		 String RoomSize = records.get(index).getRoomSize();
		 int needToCancel = venues.get(VenueID).FindRoomList(RoomSize).get(RoomID).FindBooking(ReservationID);
		 venues.get(VenueID).FindRoomList(RoomSize).get(RoomID).BookingList().remove(needToCancel);
		 records.remove(index);
	//	 System.out.print( venues.get(VenueID).FindRoomList(RoomSize).get(RoomID).BookingList().size());
	}
	
	public void PrintBooking(String info){
		Scanner sc = new Scanner(info);
		String VenueName = sc.next();
		int VenueID = getVenueID(VenueName);
		Venue venueToPrint = venues.get(VenueID);
		int k = 0;
		while (k < venueToPrint.SmallRoomList().size()){
			int j = 0;
			System.out.print(VenueName+" ");
			System.out.print(venueToPrint.SmallRoomList().get(k).getRoomName()+" ");
			while (j < venueToPrint.SmallRoomList().get(k).BookingList().size()){
				String Month = numberToMonth(venueToPrint.SmallRoomList().get(k).BookingList().get(j).getStartDate().get(Calendar.MONTH));
				int Date = venueToPrint.SmallRoomList().get(k).BookingList().get(j).getStartDate().get(Calendar.DATE);
				System.out.print(Month+" ");
				System.out.print(Date+" ");
				System.out.print(venueToPrint.SmallRoomList().get(k).BookingList().get(j).getDays()+" ");
				j++;
			}
			System.out.println();
			k++;
		}
		k = 0;
		while (k < venueToPrint.LargeRoomList().size()){
			int j = 0;
			System.out.print(VenueName+" ");
			System.out.print(venueToPrint.LargeRoomList().get(k).getRoomName()+" ");
			while (j < venueToPrint.LargeRoomList().get(k).BookingList().size()){
				String Month = numberToMonth(venueToPrint.LargeRoomList().get(k).BookingList().get(j).getStartDate().get(Calendar.MONTH));
				int Date = venueToPrint.LargeRoomList().get(k).BookingList().get(j).getStartDate().get(Calendar.DATE);
				System.out.print(Month+" ");
				System.out.print(Date+" ");
				System.out.print(venueToPrint.LargeRoomList().get(k).BookingList().get(j).getDays()+" ");
				j++;
			}
			System.out.println();
			k++;
		}
		sc.close();
	}
			
	private Boolean VenueCheck(String name){
		boolean exists = false;
		int i = 0;
		while (i < venues.size()){
			String curr = venues.get(i).getVenueName();
			if (curr.equals(name)){
				exists = true;
			}
			i++;
		}	
		return exists;
	}
	
	private int getVenueID(String name){
		int ID = -1;
		int i = 0;
		while (i < venues.size()){
			String curr = venues.get(i).getVenueName();
			if (curr.equals(name)){
				ID = i;
			}
			i++;
		}
		return ID;
	}

	private int findNext(int ID){
		int result = -1;
		int k = 0;
		while(k < (records.size())){
			if (records.get(k).getID() == ID){
				result = k;
				break;
			}
			k++;
		}
		return result;
	}
	
	private int monthToNumber(String month){
		int num = 0;
		if(month.equals("Jan")){
			num = 1;
		} else if(month.equals("Feb")){
			num = 2;
		} else if(month.equals("Mar")){
			num = 3;
		} else if(month.equals("Apr")){
			num = 4;
		} else if(month.equals("May")){
			num = 5;
		} else if(month.equals("Jun")){
			num = 6;
		} else if(month.equals("Jul")){
			num = 7;
		} else if(month.equals("Aug")){
			num = 8;
		} else if(month.equals("Sep")){
			num = 9;
		} else if(month.equals("Oct")){
			num = 10;
		} else if(month.equals("Nov")){
			num = 11;
		} else if(month.equals("Dec")){
			num = 12;
		}
		return num;
	}
	
	private String numberToMonth(int number){
		String month = null;
		if(number == 1){
			month = "Jan";
		} else if(number == 2){
			month = "Feb";
		} else if(number == 3){
			month = "Mar";
		} else if(number == 4){
			month = "Apr";
		} else if(number == 5){
			month = "May";
		} else if(number == 6){
			month = "Jun";
		} else if(number == 7){
			month = "Jul";
		} else if(number == 8){
			month = "Aug";
		} else if(number == 9){
			month = "Sep";
		} else if(number == 10){
			month = "Oct";
		} else if(number == 11){
			month = "Nov";
		} else if(number == 12){
			month = "Dec";
		}
		return month;
	}
	
}
