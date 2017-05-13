import java.util.Date;
public class BookCopy
{
	//Atributes of Class  BookCopy
	private String copyID;
	private String Title;
	private String Author;
	private String Year;
	private Patron patronOutTo;
	private Patron patronsoldTo;
	private Date DueDate;
	//=========================================================================================================
	//Name: getOutTo
	//Purpose: gets the patron the copy is checked out to
	//Input: None
	//Output: Patron object the copy is checked out
	//=========================================================================================================
	public Patron getOutTo()
	{
		return patronOutTo;
	}
	//=========================================================================================================
	//Name: setOutTo
	//Purpose: sets the Patron the copy is checking out to
	//Input: Patron
	//Output: None
	//========================================================================================================= 
	public void setOutTo(Patron outTo)
	{
		this.patronOutTo = outTo;
	}
	//=========================================================================================================
	//Name: soldTo
	//Purpose: patron the copy is selling to
	//Input: patorn
	//Output: None
	//=========================================================================================================
	public void soldTo(Patron soldTo)
	{
		this.patronsoldTo = soldTo;		
	}
	//=========================================================================================================
	//Name: getCopyID
	//Purpose: gets a copy ID of Book
	//Input: None
	//Output: CopyID
	//=========================================================================================================
	public String getCopyID()
	{
		return copyID;
	}
	//=========================================================================================================
	//Name: SetDueDate
	//Purpose: set a due date on a Copy
	//Input: Date
	//Output: None
	//=========================================================================================================
	public void SetDueDate(Date CopyDueDate)
	{
		this.DueDate = CopyDueDate;
	}
	//=========================================================================================================
	//Name: GetDueDateTo
	//Purpose: gets a due date of a Copy
	//Input: None
	//Output: None
	//=========================================================================================================
	public Date GetDueDate()
	{
		return this.DueDate;
	}
	//=========================================================================================================
	//Name: BookCopy
	//Purpose: sets CopyID, Title, Author, and Year of a Copy
	//Input: CopyId, title, author, and year of Copy
	//Output: None
	//=========================================================================================================
	public BookCopy(String cid, String title, String author, String year)
	{
		this.copyID = cid;
		this.Title = title;
		this.Author = author;
		this.Year = year;
	}
	//=========================================================================================================
	//Name: toString
	//Purpose: prints out Copy
	//Input: None
	//Output: Cop ID
	//=========================================================================================================
	public String toString()
	{
		return "Copy ID: " + this.copyID;
	}
	//=========================================================================================================
	//Name: equals
	//Purpose: compares two copies
	//Input: book object 
	//Output: true if 2 copies are equal or False if not
	//=========================================================================================================
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof BookCopy))
			return false;

		return ((BookCopy) o).getCopyID().equals(this.copyID); // yuck.
	}
	//=========================================================================================================
	//Name: getOutTo
	//Purpose: gets the patron the copy is checked out to
	//Input: None
	//Output: Patron object the copy is checked out
	//=========================================================================================================
	
	public static void main(String[] args)
	{
		

	}
}
