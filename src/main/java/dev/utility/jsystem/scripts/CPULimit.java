package dev.utility.jsystem.scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dev.utility.jfile.JFileRoot;
import dev.utility.jfile.JFileTools;
import dev.utility.jsystem.JTerminal;

public class CPULimit {
	
	
	

	public void cpulimit(String programName, int maxPercentCPU) throws IOException, InterruptedException
	{
		JTerminal terminal= new JTerminal(JTerminal.DEBUG_ON);
		Integer actualPercentCPU = maxPercentCPU * Runtime.getRuntime().availableProcessors();
		List<String> cmdArray = new ArrayList<String>();
		cmdArray.add("cpulimit");
		cmdArray.add("-e");
		cmdArray.add(programName);
		cmdArray.add("-1");
		cmdArray.add(actualPercentCPU.toString());
		terminal.run(cmdArray,false);
		
	}

}
