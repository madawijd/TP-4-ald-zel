
public class BookCopy
{
	private String copyID;
	private String Title;
	private String Author;
	private String Year;
	private Patron patronOutTo;
	private Patron patronsoldTo;
	public Patron getOutTo()
	{
		return patronOutTo;
	}

	public void setOutTo(Patron outTo)
	{
		this.patronOutTo = outTo;
	}
	public void soldTo(Patron soldTo)
	{
		this.patronsoldTo = soldTo;		
	}
	public String getCopyID()
	{
		return copyID;
	}
	public BookCopy(String cid, String title, String author, String year)
	{
		this.copyID = cid;
		this.Title = title;
		this.Author = author;
		this.Year = year;
	}

	public String toString()
	{
		return "Copy w/id= " + this.copyID;
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof BookCopy))
			return false;

		return ((BookCopy) o).getCopyID().equals(this.copyID); // yuck.
	}
//================Main====================================//
	
	public static void main(String[] args)
	{
		

	}
}
