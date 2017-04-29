import java.util.ArrayList;

public class Patron
{
	private String name;
	private String patronID;
	private ArrayList<BookCopy> copiesOut;
	//private ArrayList<BookCopy> PurchasedCopies;
	private ArrayList<Hold> HoldList;
	public Patron(String n, String id)
	{
		this.name = n;
		this.patronID = id;
		this.copiesOut = new ArrayList<BookCopy>();
	}
	public String getID()
	{
		return this.patronID;
	}
	public void placeHold(Hold h)
	{
		HoldList.add(h);
	}
	public boolean checkCopyOut(BookCopy c)
	{
		c.setOutTo(this);
		copiesOut.add(c);
		return true;
	}

	public boolean checkCopyIn(BookCopy c)
	{
		c.setOutTo(null);
		if (copiesOut.contains(c))
		{
			copiesOut.remove(c);
			return true;
		}
		else
			return false;
	}
	public boolean purchaseCopy(BookCopy c)
	{
		c.soldTo(this);
		//PurchasedCopies.add(c);
		copiesOut.add(c);
		return true;
	}

	public String toString()
	{
		String toReturn = "Patron w/ name: " + this.name + ", id: " + this.patronID;

		if (this.copiesOut.isEmpty())
		{
			toReturn = toReturn + "\nNo copies checked out.\n";
		}
		else
			for(BookCopy copy : this.copiesOut)
			{
				toReturn = toReturn + "\nCopies checked out:";
				toReturn = toReturn + "\n\t" + copy.toString() + "\n";
			}
		/*
		for(Hold hold : this.HoldList)
		{
			//toReturn = toReturn + "\nCopies checked out:";
			//toReturn = toReturn + "\n\t" + copy.toString() + "\n";
		}
		*/

		return toReturn;
	}

	public static void main(String[] args)
	{
		//Patron p1 = new Patron("James", "007");

		//System.out.println(p1);
	}

}
