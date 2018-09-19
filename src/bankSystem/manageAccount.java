package bankSystem;

import java.util.*;
import java.io.*;

public class manageAccount 
{
	private accountsInfo[] ob;
	
	public manageAccount() {}
	
	public accountsInfo[] accountInitialization()
	{
		ob = new accountsInfo[20];
		
		int index=0;
		
		try
		{
			File f = new File("clientInfo.txt");
			Scanner input = new Scanner(f);
					
			while(input.hasNextLine())
			{
				ob[index] = new accountsInfo(input.nextLine(),input.nextLine(),
							input.nextLine(),input.nextLine(),input.nextLine(),
							input.nextLine());
				index++;
			}	
		
		}
		
		catch(Exception e){}
				
		return ob;
		
	}
	
	public int TotalAccount()
	{
		ob = new accountsInfo[20];
		
		int index=0;
		
		try
		{
			File f = new File("clientInfo.txt");
			Scanner input = new Scanner(f);
					
			while(input.hasNextLine())
			{
				ob[index] = new accountsInfo(input.nextLine(),input.nextLine(),
							input.nextLine(),input.nextLine(),input.nextLine(),
							input.nextLine());
				index++;
			}	
		
		}
		
		catch(Exception e){}
				
		return index;
	}
}
