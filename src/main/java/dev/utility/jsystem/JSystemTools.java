package dev.utility.jsystem;

import java.io.IOException;
import dev.utility.jbase.constants.JConstants;

public class JSystemTools 
{
  
	public static JThread getThread(String command)
	{
		try  
		{
			return new JThread(command);
		} catch (IOException e) 
		{
			JSystemToolException.create(e.getMessage());
		}
		return null;
	} 
	

	 
	public static JTerminal getTerminal(boolean verbose) 
	{ 
		return new JTerminal(verbose);
	} 
	public static JTerminal getTerminal() 
	{ 
		return new JTerminal(true);
	} 
	
	public static JCodecs getCodecs(int type) 
	{
		return new JCodecs(type);
	} 

}


class JSystemToolException 
{
	
	public static void create(String msg)
	{
		String timestamp = JConstants.getAlphaNumericTimeStamp();
		
		System.out.println("JSystemToolException"); 
		System.out.println("Time: " +  timestamp); 
		System.out.println("Message: " +msg); 
		
	}
	
	
	
}
