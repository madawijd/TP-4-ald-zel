import java.util.*;
import java.util.Scanner;
public class TRL 
{	
	static List<Worker> WorkersList = new ArrayList<Worker>();
	static List<BookCopy> LibCopyList = new ArrayList<BookCopy>();
	static List<Patron> PatronsList = new ArrayList<Patron>();
	//session Types
	private final static int CHECKOUT_SESSION = 1;
	private final static int CHECKIN_SESSION = 2;
	private final static int PURCHASE_SESSION = 3;
	//LogIn Status
	private final static int LOGED_IN = 1;
	private final static int LOGED_OUT = 1;
	private static final boolean TRUE = true;
	private static int EventCnt = 0;
	public static int AuthocateWorker(String userName, String passWord)
	{
		int WorkerID = 1;
		//TODO: Check Users credentials
		return WorkerID;
	}
	public static int logInWorker()
	{		
		int workerId = 1;
		Scanner UserNameScanner = new Scanner(System.in);
		Scanner PasswordScanner = new Scanner(System.in);
		System.out.println("UserName: ");
		String WorkerUserName = UserNameScanner.nextLine();
		System.out.println("PassWord");
		String WorkerPassWord = PasswordScanner.nextLine();	
		workerId = AuthocateWorker(WorkerUserName, WorkerPassWord);		
		System.out.println("Woker Logged In Sucessfully");
		return workerId;
	}
	//Main
	public static void main(String[] args) 
	{
		int CurrCopyIndex = 0;
		int CurrPatronIndex = 0;
		int CurrWorkerIndex = 0;
		int WorkerID;


		//BookCopy[] ListOfBookCopies= new BookCopy[100];
		//Patron[] ListOfPatrons = new Patron[100]
		//Worker[] ListOfWorkers= new Worker[100];
		
		//Create Worker
		System.out.println("--------CREATING WORKER--------------");
		Worker Sandy_Thomas = new Worker(001, "Sandy Thomas", "111 Main Street Saint Paul MN 55111", 1, "Sandy", "123");
		Worker Bahata_Mamo = new Worker(001, "Bahata Mamo", "222 2nd Street Saint Paul MN 55111", 1, "Lekimu", "321");
		WorkersList.add(Sandy_Thomas);
		WorkersList.add(Bahata_Mamo);
		//Create 2 Patrons
		System.out.println("--------CREATING PATRONS--------------");
				
		Patron p1 = new Patron("TesfaBirhan", "004");
		PatronsList.add(p1);
		Patron p2 = new Patron("James", "007");
		PatronsList.add(p2);
		System.out.println(p1);
		System.out.println(p1);
		//Create 2 Copies1
		System.out.println("--------CREATING COPIES--------------");
		BookCopy c1 = new BookCopy("0047", "Software Engineering 101", "Andrew", "1997");
		System.out.println(c1);
		LibCopyList.add(c1);
		BookCopy c2 = new BookCopy("0048", "Software Engineering 1012", "Betsi", "1999");
		LibCopyList.add(c2);
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
				Event CurrEvent = new Event(EventCnt, WorkerID, patronID, EventType); 
				for(Patron CurrentPatron : PatronsList) 
				{
					System.out.println(CurrentPatron);
					String PatrID = CurrentPatron.getID();
					if(PatrID.equals(patronID)) 
					{
						Scanner OutCopyIdScanner = new Scanner(System.in);
						System.out.println("CopyID: ");
						String copyId = OutCopyIdScanner.nextLine();
						System.out.println("copyId: " + copyId);
						for(BookCopy CurrentCopy : LibCopyList) 
						{
							String CopyID = CurrentCopy.getCopyID();
							if(CopyID.equals(copyId)) 
							{								
								CurrentPatron.checkCopyOut(CurrentCopy);
								CurrentCopy.setOutTo(CurrentPatron);
								//Print Copy
								System.out.println(CurrentPatron);
								System.out.println("----------------------");
							
								break;							
							}
						 }//for
					}//if(PatrID.equals(patronID))
					break;
				}
			}//if(EventType == CHECKOUT_SESSION)	
			if(EventType == CHECKIN_SESSION)
			{
				System.out.println("Checking In copy from patron");  
				System.out.println("PatornID: ");
				String patronID = PatronInfoScanner.nextLine();
				System.out.println("patronID: " + patronID);
				Event CurrEvent = new Event(EventCnt, WorkerID, patronID, EventType); 
				for(Patron CurrentPatron : PatronsList) 
				{
					String PatrID = CurrentPatron.getID();
					if(PatrID.equals(patronID)) 
					{
					   System.out.println(CurrentPatron);  
					   Scanner copyIDScanner = new Scanner(System.in);
					   System.out.println("CopyID: ");
					   String scanedCopyId = copyIDScanner.nextLine();
					   System.out.println("copyId: " + scanedCopyId);
					   for(BookCopy CurrentCopy : LibCopyList) 
					   {
							String InCopyID = CurrentCopy.getCopyID();
							if(InCopyID.equals(scanedCopyId)) 
							{
								CurrentPatron.checkCopyIn(CurrentCopy);
								System.out.println(CurrentCopy);
								System.out.println("----------------------");	
								break;
							}
					   }					   
					}
					break;
				}	
			}//if(EventType == CHECKIN_SESSION)
			if(EventType == PURCHASE_SESSION)
			{
				System.out.println("Selling copy to patron");  
				System.out.println("PatornID: ");
				String patronID = PatronInfoScanner.nextLine();
				System.out.println("patronID: " + patronID);
				Event CurrEvent = new Event(EventCnt, WorkerID, patronID, EventType); 
				for(Patron CurrentPatron : PatronsList) 
				{
					String PatrID = CurrentPatron.getID();
					if(PatrID.equals(patronID)) 
					{
					   System.out.println(CurrentPatron);  
					   Scanner purCopyIDScanner = new Scanner(System.in);
					   System.out.println("CopyID: ");
					   String copyId = purCopyIDScanner.nextLine();
					   System.out.println("copyId: " + copyId);
					   for(BookCopy CurrentCopy : LibCopyList) 
					   {
							String CopyID = CurrentCopy.getCopyID();
							if(CopyID.equals(copyId)) 
							{  
							    CurrentPatron.purchaseCopy(c1);
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
				
			}//if(EventType == PURCHASE_SESSION)	  
		}//if(Worker_ID)
	}//ends main
}//ends Class TRL
