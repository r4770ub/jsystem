package dev.utility.jsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dev.utility.jbase.printer.JPrinter;


 public class JCommandPrompt {

	@SuppressWarnings("unused")
	JPrinter p = new JPrinter(JCommandPrompt.class.getPackage() + JCommandPrompt.class.getName()); 
	public static final boolean DEBUG_ON=true ;
	public static final boolean DEBUG_OFF = false; 
	boolean verbose=false;
	private String cmd; 
	private String requester;
	private BufferedReader stdError;
	private BufferedReader stdInput;
	private Process process;
	private Runtime runtimeEnviroment;
	
	 

	protected JCommandPrompt(boolean verbose) {
		this.verbose = verbose;
		cmd = "";

	}

	private void printLog(final String log) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				System.out.println(log);

			}

		});
		thread.start();

	}

	
	protected  String exec(String cmd) throws IOException 
	{
			
		String commandOutput="";
		String stdOut="";
		String stdErr="";
		boolean ERROR_FLAG=false;
			System.out.println(cmd);
			Process p = Runtime.getRuntime().exec(cmd);
		

			stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		
			String s = null;
			while ((s = stdInput.readLine()) != null) 
			{
				stdOut = stdOut +s; 
				if(verbose)
					printLog("\t" + s);

			}
			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) 
			{
				stdErr = stdErr +s; 
				if(verbose)
					printLog("\t" + s);
			
				ERROR_FLAG = true; 
			}

		
			if(ERROR_FLAG)
				return stdErr; 
			else
				return stdOut; 
	}
 

	
	protected void exec(List<String> arguments, boolean hasOutputFile) throws InterruptedException, IOException {
		
		ProcessBuilder processBuilder;
		String outputName = null;
		File combinedFile = null;

		List<String> cmdArray = new ArrayList<String>(); 
		for(String argument :arguments)
			cmdArray.add(argument); 
	
		
		if (hasOutputFile) {
			outputName = arguments.get(arguments.size() - 1);
			arguments.remove(arguments.size() - 1);
			processBuilder = new ProcessBuilder(cmdArray);
			combinedFile = new File(outputName);
			processBuilder.redirectOutput(combinedFile);
			processBuilder.redirectError(combinedFile);

		}
		else
		{
			processBuilder = new ProcessBuilder(cmdArray);

		}	 
		String outputFile =  cmdArray.get(cmdArray.size()-1);
		cmdArray.remove(cmdArray.size()-1);
		String cmd ="";
		for(String name :cmdArray)
			cmd = cmd + " "+name;
		cmd =  cmd +">"+ outputFile;


		
		Process p = processBuilder.start();
		stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

	
		String s = null;
		while ((s = stdInput.readLine()) != null) {

			printLog("\t" + s);

		}
		// read any errors from the attempted command
		while ((s = stdError.readLine()) != null) {

			printLog("\t" + s);
		}

		printLog("");
		printLog("");
		printLog("");
		printLog("");
		
	}

}
