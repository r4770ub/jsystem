package dev.utility.jsystem.scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dev.utility.jfile.JFileRoot;
import dev.utility.jfile.JFileTools;
import dev.utility.jsystem.JTerminal;

public class JFileScripts {
	
	public  static  String findFileLocation(String rootDirectory,String fileName) throws FileNotFoundException {
		String location = null;
		JFileRoot fileGrabber = JFileTools.getJFileRoot(rootDirectory);
		List<String> files = fileGrabber.getFiles();

		for (String file : files) {
			if (file.contains(fileName)) {
				File fileLocation = new File(file);
				location = fileLocation.getAbsolutePath();
				break;
			}
		}
		return location;
	}
	
	public static void removeDirectories(String rootDirectory,String[]excludedDirectories) throws IOException {
		File file = new File(rootDirectory);
		JTerminal terminal = new JTerminal(JTerminal.DEBUG_ON);
		JFileRoot fileGrabber = JFileTools.getJFileRoot(rootDirectory);
		fileGrabber.process(); 

		List<String> directories = fileGrabber.getDirectories();
		System.out.println("Number of directories before: " +  directories.size());
		for (String directory : directories) {
			
			for (String exlusion : excludedDirectories) 
			{
				if(directory.contains(exlusion))
				{		
					terminal.run("rm -rf " + directory);
				}		

			}
			
		fileGrabber = JFileTools.getJFileRoot(rootDirectory);
		fileGrabber.process(); 
		directories = fileGrabber.getDirectories();
		System.out.println("Number of directories after: " +  directories.size());

	}
	}
	
	public static  String copyByExtension(String rootDirectory, String fileExtension, String outputDirectory) throws IOException {

		JTerminal terminal = new JTerminal(JTerminal.DEBUG_ON);
		terminal.mkdir(outputDirectory); 
		
		
		
		String location = null;
		JFileRoot fileGrabber = JFileTools.getJFileRoot(rootDirectory);
		List<String> files = fileGrabber.getFiles();

		for (String file : files) {
			if (file.contains(fileExtension)) {
				File fileLocation = new File(file);
				terminal.cp(fileLocation.getAbsolutePath(), outputDirectory + fileLocation.getName());
				break;
			}
		}
		return location;
	}
	
	
	
	

}
