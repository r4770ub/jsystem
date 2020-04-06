package dev.utility.jsystem.scripts;

import java.io.FileNotFoundException;
import java.io.IOException;

import dev.utility.jimage.JImage;

public class JScripts 
{
	
	public String runFindFile (String rootDirectory, String fileName) throws FileNotFoundException
	{
		return JFileScripts.findFileLocation(rootDirectory, fileName);
	}
	
	public void  runRemoveDirectories(String rootDirectory,String[] excludedDirectories) throws IOException
	{
		 JFileScripts.removeDirectories(rootDirectory, excludedDirectories);
	}
	
	public  String runCopyByExtension(String rootDirectory, String fileExtension, String outputDirectory) throws IOException
	{
		return JFileScripts.copyByExtension(rootDirectory, fileExtension, outputDirectory);
		
	}
	
	public static String runExposeData(String steganographyFile, boolean zipStrip, int tarStrip) throws Exception
	{
		return JSteganographyScripts.exposeData(steganographyFile, zipStrip, tarStrip); 
		
	}
	
	public static String runHideData(String inputDirectory,String steganographyFile,JImage coverImage) throws IOException, InterruptedException {
		return JSteganographyScripts.hideData(inputDirectory, steganographyFile, coverImage); 

	}
	
	public static  String runhideData(String inputDirectory,String steganographyFile,String imageCoverPath) throws IOException, InterruptedException {
		
		return JSteganographyScripts.hideData(inputDirectory, steganographyFile, imageCoverPath); 
 
		}
	
	public static String runSerializeJImage(JImage image, String outputFile) throws IOException, ClassNotFoundException 
	{
	 return  JImageScripts.serializeJImage(image, outputFile);
	}
	
	public  static JImage deserializeJImage(String fileLocation) throws ClassNotFoundException, IOException {
		return JImageScripts.deserializeJImage(fileLocation);
	}

	
	

}
