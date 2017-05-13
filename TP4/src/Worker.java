
public class Worker 
{
	
	int Worker_ID;
	String Name;
	String Address;
	int LogInStatus;
	String UserName;
	String PassWord;	
	//Constructor
	public Worker(int Id, String FullName, String Addr, int LogInStatus, String UserName, String PassWord)
	{
		this.Worker_ID = Id;
		this.Name = FullName;
		this.Address = Addr;
		this.LogInStatus = LogInStatus;
		this.UserName = UserName;
		this.PassWord = PassWord;	
			
	}
	public String getUserName()
	{
		return this.UserName;
	}
	public String getPassword()
	{
		return this.PassWord;
	}
	
	//Main
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
	}

}
