package dev.utility.jsystem.scripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dev.utility.jimage.JCodecImage;
import dev.utility.jimage.JImage;
import dev.utility.jimage.JImageMetadata;

public class JImageScripts {
	
	public static  String serializeJImage(JImage image, String outputFile) throws IOException, ClassNotFoundException {

		
		image.printMetadata();
		String fileName = outputFile + image.getMetadata().getValue(JImageMetadata.FILE_NAME)
				+ ".IP01";
		System.out.println(fileName);
		image.getMetadata().setFileInformation(fileName);
		FileOutputStream fileOutputStream = new FileOutputStream(
				image.getMetadata().getValue(JImageMetadata.ABSOLUTE_PATH));
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		JCodecImage encrypted  = new JCodecImage(image); 
		objectOutputStream.writeObject(encrypted);
		objectOutputStream.flush();
		objectOutputStream.close();
		return fileName;
		 
	}
	
	public  static JImage deserializeJImage(String fileLocation) throws ClassNotFoundException, IOException {
		 
		FileInputStream fileInputStream = new FileInputStream(fileLocation);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		JCodecImage jCodecImage  = (JCodecImage) objectInputStream.readObject();
		JImage resultImage = jCodecImage.deCodecify();
		objectInputStream.close();
		return resultImage;
	}
	
	

	

}
