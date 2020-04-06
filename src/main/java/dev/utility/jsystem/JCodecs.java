package dev.utility.jsystem;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import dev.utility.jbase.JHelper;
import dev.utility.jbase.constants.JConstants;
import dev.utility.jfile.JFileSorter;
import dev.utility.jfile.JFileTools;
import dev.utility.jimage.JImage;

public class JCodecs {

	public static final int PO1_CODEC = 0;
	public static final int VO1_CODEC = 1;
	private int option;

	JCodecs(int option_type) {
		this.option = option_type;
	} 

	public static JImage p01Decoder(byte[] encryptedByted) throws IOException {
		byte[] imageBytes = Base64.getDecoder().decode(encryptedByted);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
		BufferedImage bImage = ImageIO.read(bis);
		return new JImage(bImage);
	}

	public static byte[] p01Encoder(JImage jImage) throws IOException {
		BufferedImage bImage = jImage.getBufferedImage();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", bos);
		byte[] image_data = bos.toByteArray();
		byte[] encryptedBytes = Base64.getEncoder().encode(image_data);
		return encryptedBytes;

	}
 

	public static void encryptP01Files() throws IOException  {
		JFileSorter fileSorter = JFileTools.getFileSorter(JConstants.INPUT_DESKTOP_DIRECTORY, JFileSorter.SORT_FILES_ALPHANUMERIC);
		String[]  fileNames = fileSorter.getFileNames();
		
		
		for (String fileName : fileNames) 
		{
			System.out.println("Encrypting.... " +  fileName); 
			JImage image = new JImage(fileName); 
			String newName = JHelper.getOnlyFileName(fileName) + ".P01";
			byte[] p01Image  = p01Encoder(image); 
			FileOutputStream fos = new FileOutputStream(JConstants.OUTPUT_DESKTOP_DIRECTORY + newName);
			  fos.write(p01Image);
			  fos.close();
			  System.out.println("Encrypted to..... " + newName);

		}
	}
	
	public static void decryptP01Files() throws IOException {
		JFileSorter fileSorter = JFileTools.getFileSorter(JConstants.INPUT_DESKTOP_DIRECTORY, JFileSorter.SORT_FILES_ALPHANUMERIC);
		String[]  fileNames = fileSorter.getFileNames();
		
		for (String fileName : fileNames) 
		{
		
			
//			String newName = JString.getOnlyFileName(fileName) + ".P01";
//			byte[] p01Image  = p01Encoder(image); 
//			FileOutputStream fos = new FileOutputStream(JConstants.OUTPUT_DESKTOP_DIRECTORY + newName);
//			  fos.write(p01Image);
//			  fos.close();
//			  System.out.println("Encrypted to..... " + newName);

		}
	}

}
