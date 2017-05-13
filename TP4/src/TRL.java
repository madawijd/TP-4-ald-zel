import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
public class TRL 
{	
	static List<Worker> WorkersList = new ArrayList<Worker>();
	static List<BookCopy> LibCopyList = new ArrayList<BookCopy>();
	static List<Patron> PatronsList = new ArrayList<Patron>();
	static List<Event> EventsList = new ArrayList<Event>();	
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private static final DateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	//session Types
	private final static int CHECKOUT_SESSION = 1;
	private final static int CHECKIN_SESSION = 2;
	private final static int PURCHASE_SESSION = 3;
	private final static int PLACE_HOLD = 4;
	private final static int COLLECT_FINE= 5;
	private final static int PRINT_EVENTS= 6;
	private final static int LOG_OUT= 7;
	//LogIn Status
	private final static int LOGED_IN = 1;
	private final static int LOGED_OUT = 1;
	private static final boolean TRUE = true;
	private static int EventCnt = 0;
	private static Date CurrDate;
	private static Date DueDate;
	//Rental Period
	private final static int RENTAL_PERIOD0 = 60;
	//
	public static int AuthocateWorker(String userName, String passWord)
	{
		int WorkerID = 1;
		for(Worker CurrentWorker : WorkersList) 
		{		
			//System.out.println(CurrentWorker.UserName);
			String UserName = CurrentWorker.getUserName();
			String PassWord = CurrentWorker.getPassword();
			if(UserName.equals(userName)) 
			{
				System.out.println(CurrentWorker.UserName);
				if(PassWord.equals(passWord)) 
				{
					System.out.println(CurrentWorker.PassWord);
					WorkerID = CurrentWorker.Worker_ID;
				}
			}
		}		
		//TODO: Check Users credentials
		System.out.println("Authocating Worker.......");
		System.out.println("Worker ID:" + WorkerID);
		return WorkerID;
	}
	public static int logInWorker()
	{		
		int workerId = 1;
		Scanner UserNameScanner = new Scanner(System.in);
		Scanner PasswordScanner = new Scanner(System.in);
		System.out.println("Enter UserName: ");
		String WorkerUserName = UserNameScanner.nextLine();
		System.out.println("Enter PassWord");
		String WorkerPassWord = PasswordScanner.nextLine();	
		workerId = AuthocateWorker(WorkerUserName, WorkerPassWord);		
		System.out.println("Woker Logged In Sucessfully");
		return workerId;
	}
	public static Date GenerateDueDate(Date date, int days)
	{
	     Calendar Calnder = Calendar.getInstance();
	     Calnder.setTime(date);
	     Calnder.add(Calendar.DATE, days); 
	     return Calnder.getTime();
	}
	
	public static String CheckOutCopy(int EvtId, int WorkerID, String PatronID, int EvtType)
	{
		String CopyID = null;
		for(Patron CurrentPatron : PatronsList) 
		{
			System.out.println(CurrentPatron);
			String PatrID = CurrentPatron.getID();
			if(PatrID.equals(PatronID)) 
			{
				CurrentPatron.printHoldList();
				Scanner OutCopyIdScanner = new Scanner(System.in);
				System.out.println("CopyID: ");
				String copyId = OutCopyIdScanner.nextLine();
				System.out.println("copyId: " + copyId);
				Event CurrEvent = new Event(EvtId, WorkerID, PatronID, EvtType,copyId);
				EventsList.add(CurrEvent);
				for(BookCopy CurrentCopy : LibCopyList) 
				{
					CurrDate = new Date();
					CopyID = CurrentCopy.getCopyID();
					if(CopyID.equals(copyId)) 
					{		
						System.out.print("Current Time:");
						System.out.println(DateFormat.format(CurrDate));
						//Generate Due Date								
						//Scanner DaysCntScanner = new Scanner(System.in);
						//System.out.println("Enter Rental Days for Copy:");
						//int RentalDaysCnt = DaysCntScanner.nextInt();
						Date NextDueDate = GenerateDueDate(CurrDate,RENTAL_PERIOD0);
						System.out.print("Copy Due Date:");
						System.out.println(DateFormat.format(NextDueDate));
						//Set Due Date
						CurrentCopy.SetDueDate(NextDueDate);
						//System.out.print("Get Due Date: ");
						System.out.println(DateFormat.format(CurrentCopy.GetDueDate()));
						CurrentPatron.checkCopyOut(CurrentCopy);
						CurrentCopy.setOutTo(CurrentPatron);
						//CurrentCopy.SetDueDate(DueDate);
						//Print Copy
						System.out.println(CurrentPatron);
						System.out.println("----------------------");
						break;							
					}
				}//for
			}//if(PatrID.equals(patronID))
			break;
		}
		return CopyID;
	}
	
	public static String CheckInCopy(int EvtId, int WorkerID, String PatronID, int EvtType)
	{
		String CopyID = null;
		
		for(Patron CurrentPatron : PatronsList) 
		{
			String PatrID = CurrentPatron.getID();
			if(PatrID.equals(PatronID)) 
			{					
			   CurrentPatron.printHoldList();
			   System.out.println(CurrentPatron);  
			   Scanner copyIDScanner = new Scanner(System.in);
			   System.out.println("CopyID: ");
			   String scanedCopyId = copyIDScanner.nextLine();
			   System.out.println("copyId: " + scanedCopyId);
				Event CurrEvent = new Event(EventCnt, WorkerID, PatronID, EvtType,scanedCopyId);
				EventsList.add(CurrEvent);
			   for(BookCopy CurrentCopy : LibCopyList) 
			   {
					String InCopyID = CurrentCopy.getCopyID();
					if(InCopyID.equals(scanedCopyId)) 
					{
						CurrentPatron.checkCopyIn(CurrentCopy);
						System.out.println(CurrentCopy);
						System.out.println("Copy Due Date....");
						System.out.println(DateFormat.format(CurrentCopy.GetDueDate()));
						System.out.println("----------------------");	
						break;
					}
			   }					   
			}
			break;
		}
		
		return CopyID;
	}
	
	
	public static String PurchaseCopy(int EvtId, int WorkerID, String PatronID, int EvtType)
	{
		String CopyID = null;
		for(Patron CurrentPatron : PatronsList) 
		{
			String PatrID = CurrentPatron.getID();
			if(PatrID.equals(PatronID)) 
			{
				CurrentPatron.printHoldList();
				System.out.println(CurrentPatron);  
				Scanner purCopyIDScanner = new Scanner(System.in);
				System.out.println("CopyID: ");
				String copyId = purCopyIDScanner.nextLine();
				System.out.println("copyId: " + copyId);
				Event CurrEvent = new Event(EventCnt, WorkerID, PatronID, EvtType,copyId);
				EventsList.add(CurrEvent);
				for(BookCopy CurrentCopy : LibCopyList) 
				{
					CopyID = CurrentCopy.getCopyID();
					if(CopyID.equals(copyId)) 
					{  
						CurrentPatron.purchaseCopy(CurrentCopy);
						CurrentCopy.soldTo(CurrentPatron);
						//Print Copy
						System.out.println(CurrentPatron);
						System.out.println("----------------------");

					}
				}
		   break;
		}
		break;
		}//outr for loop
	
	return CopyID;
	}
	
	
	
	public static String PlaceHold(int EvtId, int WorkerID, String PatronID, int EvtType)
	{
		for(Patron CurrentPatron : PatronsList) 
		{
			String PatrID = CurrentPatron.getID();
			if(PatrID.equals(PatronID)) 
			{
				CurrentPatron.printHoldList();
				Scanner HoldIDScanner = new Scanner(System.in);
				Scanner HoldTypeScanner = new Scanner(System.in);
				Scanner HoldBookIDScanner = new Scanner(System.in);
								
				System.out.println("Hold ID: ");
				int HoldId = HoldIDScanner.nextInt();
				System.out.println("HoldId: " + HoldId);
			   
				System.out.println("Book ID: ");
				String HoldBookId = HoldBookIDScanner.nextLine();
				System.out.println("Book ID: " + HoldBookId);
				
				System.out.println("Hold Type: ");
				String TypeOfHold = HoldTypeScanner.nextLine();
				System.out.println("Hold Type: " + TypeOfHold);
				Event CurrEvent = new Event(EventCnt, WorkerID, PatronID, EvtType,HoldBookId);
				EventsList.add(CurrEvent);
				Hold NewHold = new Hold(HoldId, PatronID, TypeOfHold, HoldBookId);
				CurrentPatron.placeHold(NewHold);
				CurrentPatron.printHoldList();
			   
			}
			break;
		}	
		return PatronID;
	}
	
	public static String CollectFine(int EvtId, int WorkerID, String PatronID, int EvtType)
	{
		Scanner FineScanner = new Scanner(System.in);
		System.out.println("Fine ($Dollar.Cent): ");
		double fineCollected = FineScanner.nextDouble();
		System.out.println("fineCollected: " + fineCollected);
		for(Patron PayingCurrentPatron : PatronsList) 
		{
			String PayingPatrID = PayingCurrentPatron.getID();
			if(PayingPatrID.equals(PatronID)) 
			{
				PayingCurrentPatron.printHoldList();
				Scanner HoldIDScanner = new Scanner(System.in);
				Scanner HoldTypeScanner = new Scanner(System.in);
				Scanner HoldBookIDScanner = new Scanner(System.in);
							
				System.out.println("Hold ID: ");
				int HoldId = HoldIDScanner.nextInt();
				System.out.println("HoldId: " + HoldId);
		   
				System.out.println("Book ID: ");
				String HoldBookId = HoldBookIDScanner.nextLine();
				System.out.println("Book ID: " + HoldBookId);
			
				System.out.println("Hold Type: ");
				String TypeOfHold = HoldTypeScanner.nextLine();
				System.out.println("TypeOfHold: " + TypeOfHold);
			
				Event CurrEvent = new Event(EventCnt, WorkerID, PayingPatrID, EvtType,HoldBookId);
				EventsList.add(CurrEvent);
			
				Hold HoldToRemove = new Hold(HoldId, PayingPatrID, TypeOfHold, HoldBookId);
				PayingCurrentPatron.removeHold(HoldToRemove);
				PayingCurrentPatron.printHoldList();
		   
			}
			break;
		}
		return PatronID;
	}
	
	public static void printEventLog()
	{
	
		for(Event CurrentEvent : EventsList) 
		{
			System.out.println("-------------------------------------");
			//System.out.println("Hold ID: " + CurrentEvent.toString());
			//System.out.println("Book ID: " + CurrentHold.BookId);
			//System.out.println("Hold Type: " + CurrentHold.HoldType);
			CurrentEvent.printEvent();
			System.out.println("-------------------------------------");
		}
	}	
	//Main
	public static void main(String[] args) 
	{
		int CurrCopyIndex = 0;
		int CurrPatronIndex = 0;
		int CurrWorkerIndex = 0;
		int WorkerID;
		String DueDate;
		//BookCopy[] ListOfBookCopies= new BookCopy[100];
		//Patron[] ListOfPatrons = new Patron[100]
		//Worker[] ListOfWorkers= new Worker[100];
		
		//----Create 2 Workers---
		System.out.println("--------CREATING WORKER--------------");
		Worker Zelalem_Cherenet = new Worker(001, "Zelalem Cherenet", "111 Main Street Saint Paul MN 55111", 1, "Sandy", "123");
		Worker Madawi_Aldamadi = new Worker(001, "Madawi Aldamadi", "222 2nd Street Saint Paul MN 55111", 1, "Lekimu", "321");
		//Add Workers to TRL Workers list
		WorkersList.add(Zelalem_Cherenet);
		WorkersList.add(Madawi_Aldamadi);
		//Create 2 Patrons
		System.out.println("--------CREATING PATRONS--------------");
		Patron p1 = new Patron("TesfaBirhan", "004");
		Patron p2 = new Patron("James", "007");
		//Add Patrons to TRL Patron list
		PatronsList.add(p1);
		PatronsList.add(p2);
		System.out.println(p1);
		System.out.println(p1);
		//Create 2 Copies
		System.out.println("--------CREATING COPIES--------------");
		BookCopy c1 = new BookCopy("0047", "Software Engineering 101", "Andrew", "1997");
		BookCopy c2 = new BookCopy("0048", "Software Engineering 1012", "Betsi", "1999");
		//Add Copies to TRL Copies list
		LibCopyList.add(c1);
		LibCopyList.add(c2);
		System.out.println("----------TRL COPIES------------");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println("----------------------");
		//Worker LogIN`
		System.out.println("--------WORKER LOGIN--------------");
		WorkerID = logInWorker();
		System.out.println("====================================");
		boolean LoggedIn = TRUE;
		while(LoggedIn)
		{
			EventCnt++;
			//Event Type
			Scanner EventTypeScanner = new Scanner(System.in);
			Scanner PatronInfoScanner = new Scanner(System.in);
			System.out.println("EventType: ");
			System.out.println("Enter 1 for CHECKOUT_SESSION");
			System.out.println("Enter 2 for CHECKIN_SESSION");
			System.out.println("Enter 3 for PURCHASE_SESSION");
			System.out.println("Enter 4 for PLACING_HOLD");
			System.out.println("Enter 5 for COLLECT_FINE");
			System.out.println("Enter 6 to PRINT_EVENTS LOG");
			System.out.println("Enter 7 to LogOut");
			System.out.println("----------------------------");
			int EventType = EventTypeScanner.nextInt();
			System.out.println("EventType: " + EventType);
			//if(CurrentPatron.getString.getList.contains("patronID"))
			if(EventType == CHECKOUT_SESSION)
			{	
				System.out.println("Checking out copy to patron");  
				System.out.println("PatornID: ");
				String patronID = PatronInfoScanner.nextLine();
				System.out.println("patronID: " + patronID);
				CheckOutCopy(EventCnt, WorkerID, patronID, CHECKOUT_SESSION);

			}//if(EventType == CHECKOUT_SESSION)	
			if(EventType == CHECKIN_SESSION)
			{
				System.out.println("Checking In copy from patron");  
				System.out.println("PatornID: ");
				String patronID = PatronInfoScanner.nextLine();
				System.out.println("patronID: " + patronID);
				CheckInCopy(EventCnt, WorkerID, patronID, CHECKIN_SESSION);
	
			}//if(EventType == CHECKIN_SESSION)
			if(EventType == PURCHASE_SESSION)
			{
				System.out.println("Selling copy to patron");  
				System.out.println("PatornID: ");
				String patronID = PatronInfoScanner.nextLine();
				System.out.println("patronID: " + patronID);
				PurchaseCopy(EventCnt, WorkerID, patronID, PURCHASE_SESSION);
				
			}//if(EventType == PURCHASE_SESSION)	
			
			// -------- UNDER CONSTRUCTION ------------------//
			if(EventType == PLACE_HOLD)
			{
				System.out.println("Placing Hold on Patron");  
				System.out.println("PatornID: ");
				Scanner PlaceHoldScanner = new Scanner(System.in);
				String patronIDHold = PlaceHoldScanner.nextLine();
				System.out.println("patronID: " + patronIDHold);
				PlaceHold(EventCnt, WorkerID, patronIDHold, PURCHASE_SESSION);
			}
			if(EventType == COLLECT_FINE)
			{
				Scanner FinePatronIDScanner = new Scanner(System.in);
				System.out.println("Collecting Fine From Patron and Removing file");  
				System.out.println("PatornID: ");
				String FinePatronID = FinePatronIDScanner.nextLine();
				System.out.println("FinePatronID: " + FinePatronID);
				
				CollectFine(EventCnt, WorkerID, FinePatronID, PURCHASE_SESSION);				

			}
			if(EventType == PRINT_EVENTS)
			{
				printEventLog();
			}
			if(EventType == LOG_OUT)
			{
				LoggedIn = false;
				return;
			}			
		}//if(Worker_ID)
	}//ends main
}//ends Class TRL
