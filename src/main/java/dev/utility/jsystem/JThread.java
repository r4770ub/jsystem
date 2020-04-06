package dev.utility.jsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.swing.JFrame;


public class JThread extends Thread{
	private static final Logger log = Logger.getLogger(JThread.class.getName());

	String cmd; 
	String requester; 
	BufferedReader stdError;
	BufferedReader stdInput;
	JFrame terminalWindow;
	 
	
	public JThread(String cmd) throws IOException
	{
		System.out.println("Process thread created: "+getId() +". Request from " + requester); 
		this.cmd = cmd; 
		this.requester = requester; 
	}

	@Override
	public void run() 
	{
		
	 
		log.info("Process thread: " + getId() + " executing command: " + cmd );
		Runtime rt = Runtime.getRuntime();
		Process proc;
		try {
			proc = rt.exec(cmd);

		BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(proc.getErrorStream()));

			System.out.println("=========================");

			// read the output from the command
			System.out.print("Output of command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null)
			{
			    log.info(s);
			}
			System.out.println(); 
			// read any errors from the attempted command
			System.out.print("Errors from command  (if any):\n");
			while ((s = stdError.readLine()) != null)
			{
			    log.severe(s);
			}
			System.out.println("=========================");
			
		}
		catch(Exception e)
		{
			log.severe("Unable to execute command from java... Exiting"); 
			System.exit(1);
		}
	
	} 


}
