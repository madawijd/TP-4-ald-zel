
public class Event 
{
	private int EventId;
	private int WorkerId;
	private String PatronId;
	private int EventType;
	BookCopy[] ListOfEventBooks = new BookCopy[100];
	//Constructor
	public Event(int EvtId, int WorkerID, String PatronID, int EvtType)
	{
		
		this.EventId = EvtId;
		this.WorkerId = WorkerID;
		this.PatronId = PatronID;
		this.EventType = EvtType;		
	}
	//Main
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
