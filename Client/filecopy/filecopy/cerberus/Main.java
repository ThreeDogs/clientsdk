package filecopy.cerberus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	private static String TOKEN = "|";

	public static void main(String[] args) throws Exception {
		
		String files = args[0];
		String copyFolder = args[1];
		
		String[] fileArr = files.split(TOKEN);
		
		for(String filePath : fileArr) {
			File file = new File(filePath);
			
			fileCopy(filePath, copyFolder + "/" + file.getName());
			
		}
		
		
	}

	public static void fileCopy(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
