package dev.utility.jsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import dev.utility.jfile.JFileSorter;

public class JTerminal extends JCommandPrompt{

	boolean DELETE = false;
	protected String cmd;
	protected JFileSorter fileParser;
	
	
    public JTerminal()
	{
		super(true); 
	
	}
	public JTerminal(boolean  verbose) 
	{
		super(verbose);
		cmd = "";
	}
	
	public void setVerbose(boolean verbose)
	{
		this.verbose = verbose;
	}

	public void cat(String imgAbsolutePath, String zipAbsolutePath, String outputFileName) throws IOException
	{
	    ProcessBuilder builder = new ProcessBuilder("cat",imgAbsolutePath ,zipAbsolutePath);
        File combinedFile = new File(outputFileName);
        builder.redirectOutput(combinedFile);
        builder.redirectError(combinedFile);
        Process p = builder.start();
	}

	public void zip(String zipDirectory, String zipFile, String sourceDirectory) throws IOException {

		mkdir(zipDirectory);
		cmd = "zip -r " + zipDirectory + zipFile + " " + sourceDirectory;
		run(cmd);
	}

	public void zip(String zipAbsolutePath, String sourceDirectory) throws IOException {
		String zipFile = zipAbsolutePath.substring(zipAbsolutePath.lastIndexOf("/") + 1, zipAbsolutePath.length());
		String zipDirectory = zipAbsolutePath.substring(0, zipAbsolutePath.lastIndexOf("/") + 1);
		zip(zipDirectory, zipFile, sourceDirectory);
	} 

	public void unzip(String zipPath, String tarPath, boolean strip) throws IOException, InterruptedException {

		ProcessBuilder pb;
		List<String> cmdArray = new ArrayList<String>();

		if (strip)

			pb = new ProcessBuilder("unzip", "-j", zipPath, "-d", tarPath);
		else
			pb = new ProcessBuilder("unzip", zipPath, "-d", tarPath);

		pb.redirectErrorStream(true);

		try {
			Process p = pb.start();
			InputStream is = p.getInputStream();
			InputStreamReader r = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(r);
			String line;
			while ((line = in.readLine()) != null) {
				// because of the -qq option, it does actually write out nothing
				System.out.println(line);
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

	}

	public void untar(String tarFilePath, String outputFilePath, int strip) throws IOException {

		mkdir(outputFilePath);

		cmd = "  tar -C " + outputFilePath + " -xvf  " + tarFilePath + " --strip=" + strip;
	    exec(cmd);

	}


	public void tar(String tarAbolutefilePath, String inputFileDirectory) throws IOException {
		
		cmd = "tar -cvf " + tarAbolutefilePath  + " " + inputFileDirectory;
		exec(cmd);
	}

	public void targz(String tarDirectory, String tarFile, String sourceDirectory) throws IOException {
		mkdir(tarDirectory);

		cmd = "tar -cvzf " + tarDirectory + tarFile + " " + sourceDirectory;
		run(cmd);
	}

	public void mv(String sourceDirectory, String destinationDirectory) throws IOException {
		cmd = "mv " + sourceDirectory + " " + destinationDirectory;
		run(cmd);
	}

	public void mkdir(String directory) throws IOException {
		cmd = "mkdir " + directory;
		run(cmd);
	}

	public void rm(String directory) throws IOException {
		File file = new File(directory);
		if (file.isFile())
			cmd = "rm " + directory;
		else
			cmd = "rm -rf " + directory;
	    run(cmd);
	}

	private void copyFile(File sourceFolder, File destinationFolder) throws IOException {
		// Copy the file content from one place to another
		Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("|\t\tFile copied :: " + destinationFolder);
	}

	private void cp(File sourceFolder, File destinationFolder) throws IOException 
	{

		if (sourceFolder.isDirectory()) 
		{
			if (!destinationFolder.exists()) 
			{
				destinationFolder.mkdir();
				System.out.println("|\tDirectory created :: " + destinationFolder);
			}
			String files[] = sourceFolder.list();

			for (String file : files) 
			{
				File srcFile = new File(sourceFolder, file);
				File destFile = new File(destinationFolder, file);
				cp(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
			}
		} else {
			copyFile(sourceFolder, destinationFolder);
		}

	} 

	public void cp(String sourceName, String destName) throws IOException {

		File sourceDirectory = new File(sourceName);
		File destinationDirectory = new File(destName);

			cp(sourceDirectory, destinationDirectory);
		
 
	}
	public void run(String cmd,boolean verbose) throws IOException 
	{
		exec(cmd);
	}

	public void run(String cmd) throws IOException 
	{
		exec(cmd);
	}

	public void run(List<String> cmdArray, boolean HAS_OUTPUTFILE) throws IOException, InterruptedException 
	{
		exec(cmdArray, HAS_OUTPUTFILE);
	}

}
