import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class VenueHireSystem {

	public static void main(String[] args) {

	      Scanner sc = null;
	      try
	      {
	          sc = new Scanner(new FileReader("test.txt"));  
	      }
	      catch (FileNotFoundException e) {}
/*	      finally
	      {
	          if (sc != null) sc.close();
	      }
*/	     
	      
	      Manager VenueManager = new Manager();
	      
	      String info;
	      String key;
	      
	      while (sc.hasNextLine() && sc.hasNext()){
	    	  key = sc.next();
	    	  info = sc.nextLine();
	    	  if (key.equals("Venue")){
	    		  VenueManager.addVenueAndRoom(info);
	    	  } else if (key.equals("Request")){
	    		  VenueManager.RequestBooking(info);
	    	  } else if (key.equals("Change")){
	    		  VenueManager.ChangeBooking(info);
	    	  } else if (key.equals("Cancel")){
	    		  VenueManager.CancelBooking(info);
	    	  } else if (key.equals("Print")){
	    		  VenueManager.PrintBooking(info);
	    	  }
	      }
	      
	      sc.close();
	}

}
