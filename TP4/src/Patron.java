import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Patron
{
	private String name;
	private String patronID;
	private ArrayList<BookCopy> copiesOut;
	//private <BookCopy> PurchasedCopies;
	//private ArrayList<Hold> HoldsList;
	//private ArrayList<Hold> HoldList;
	static List<Hold> PatronsHoldList = new ArrayList<Hold>();
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
		System.out.println("Placing Hold");
		System.out.println("HoldID: " + h.HoldID);
		System.out.println("PatronID: " + h.PatronID);
		System.out.println("BookId: " + h.BookId);
		System.out.println("HoldType: " + h.HoldType);
		PatronsHoldList.add(h);
	}
	public void removeHold(Hold h)
	{
		System.out.println("Removing Hold");
		System.out.println("HoldID: " + h.HoldID);
		System.out.println("PatronID: " + h.PatronID);
		System.out.println("BookId: " + h.BookId);
		System.out.println("HoldType: " + h.HoldType);
		PatronsHoldList.remove(h);
		
	}
	public void printHoldList()
	{
		for(Hold CurrentHold : PatronsHoldList) 
		{
			System.out.println("-------------------------------------");
			System.out.println("Hold ID: " + CurrentHold.HoldID);
			System.out.println("Book ID: " + CurrentHold.BookId);
			System.out.println("Hold Type: " + CurrentHold.HoldType);
			System.out.println("-------------------------------------");
		}
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
