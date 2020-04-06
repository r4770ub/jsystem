package dev.utility.jsystem.scripts;

import java.io.File;
import java.io.IOException;

import dev.utility.jimage.JImage;
import dev.utility.jsystem.JTerminal;

public class JSteganographyScripts {
	
	
public static  String hideData(String inputDirectory,String steganographyFile,String imageCoverPath) throws IOException, InterruptedException {
		
		if(steganographyFile.endsWith("/"))
		{
			steganographyFile.substring(0, steganographyFile.length()-2);
		}
		
		JImage image = new JImage(imageCoverPath);
		return hideData(inputDirectory, steganographyFile,image);
		

	}



	//input the inputdirectory and the name of output file you want. it will be created in the parent of the 
	//input directory. the cover image selects which image from resources. 
	public static String hideData(String inputDirectory,String steganographyFile,JImage coverImage) throws IOException, InterruptedException {
		
		JTerminal terminal = new JTerminal(JTerminal.DEBUG_ON);
		File inputFile = new File(inputDirectory); 
		String parentPath = inputFile.getParent()+"/"; 

		
		String coverPath = parentPath + "coverImage.jpg";
		coverImage.writeJImageAsJPG(coverPath);
		String absoluteTar = parentPath+ steganographyFile + ".tar";
		String absoluteZip = parentPath+  steganographyFile + ".zip";
		String absoluteJpg =  parentPath+ steganographyFile + ".jpg";
		
		System.out.println("Steganography Creator Called");
		System.out.println("Input Directory being steganified: "+ inputDirectory);
		System.out.println("Input Cover Image  fileb eing used: "+ coverPath); 

		System.out.println("Temp tar filebeing used: "+ absoluteTar); 
		System.out.println("Temp zip filebeing used: "+ absoluteZip); 
		System.out.println("Output file being created : "+ absoluteJpg); 


		coverImage.writeJImageAsJPG(coverPath);

	
		terminal.tar(absoluteTar, inputDirectory);
		terminal.zip(absoluteZip, absoluteTar);
		terminal.cat(coverPath, absoluteZip, absoluteJpg);

		terminal.rm(absoluteTar);
		terminal.rm(absoluteZip);
		terminal.rm(coverPath);
		return absoluteJpg;

	}
	
	public static String exposeData(String steganographyFile, boolean zipStrip, int tarStrip) throws Exception {
		JTerminal terminal= new JTerminal(JTerminal.DEBUG_ON);
		File file = new File(steganographyFile); 
		
		String parentPath = file.getParent()+"/";
		String fileName = file.getName();
	 	fileName = fileName.substring(0, fileName.lastIndexOf('.'));
	
	
		
		String jpgPath =parentPath +fileName+"cp.jpg";
		String zipPath = parentPath +fileName +".zip";
		String tarPath = parentPath +fileName+".tar";
		
		String outputFilePath = parentPath +fileName; 
		terminal.mkdir(outputFilePath); 
		terminal.cp(steganographyFile, jpgPath);
		terminal.mv(jpgPath, zipPath);
		terminal.unzip(zipPath, parentPath,zipStrip);
		terminal.untar(tarPath,outputFilePath,tarStrip);
		terminal.rm(jpgPath);
		terminal.rm(zipPath);
		terminal.rm(tarPath);
		return outputFilePath; 
	}



}
