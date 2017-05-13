
public class Hold 
{
	int HoldID;
	String PatronID;
	String HoldType;
	String BookId;
	//Constructor
	public Hold(int holdId, String patronIDHold, String holdType, String bookId)
	{
		
		this.HoldID = holdId;
		System.out.println("HoldID: " + HoldID);
		this.PatronID = patronIDHold;
		System.out.println("PatronID: " + PatronID);
		this.HoldType = holdType;
		System.out.println("HoldType: " + HoldType);
		this.BookId = bookId;	
		System.out.println("BookId: " + BookId);
	}
	//Main
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
