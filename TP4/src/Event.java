
public class Event 
{
	private int EventId;
	private int EmplId;
	private String PatronId;
	private int EventType;
	private String BookID;
	BookCopy[] ListOfEventBooks = new BookCopy[100];
	//Constructor
	public Event(int EvtId, int WorkerID, String PatronID, int EvtType, String processingBookId)
	{
		
		this.EventId = EvtId;
		this.EmplId = WorkerID;
		this.PatronId = PatronID;
		this.EventType = EvtType;	
		this.BookID = processingBookId;
	}
	public void printEvent()
	{
		System.out.println("EventId: " + EventId);
		System.out.println("EventType: " + EventType);
		System.out.println("EmplId: " + EmplId);
		System.out.println("PatronId: " + PatronId);
		System.out.println("BookID: " + BookID);
	}
	//Main
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
