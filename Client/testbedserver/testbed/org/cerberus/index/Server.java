package testbed.org.cerberus.index;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpException;

public class Server {

	public static void main(String[] args) throws IOException, HttpException, InterruptedException {

		final DeviceManager manager = new DeviceManager();
		manager.start();
		manager.allAddDevices();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				DeviceRunThread deviceRunThread = new DeviceRunThread();
				deviceRunThread.setManager(manager);
				deviceRunThread.start();
						
			}
		}
		).start();
		
		new Runnable() {
			public void run() {
				DeviceInfoThread deviceInfoThread = new DeviceInfoThread();
				deviceInfoThread.setManager(manager);
				deviceInfoThread.start();
	
			}
		}.run();
		

	}

	public static void fileUrlReadAndDownload(String fileAddress,
			String localFileName, String downloadDir) {
		OutputStream outStream = null;
		URLConnection uCon = null;
		InputStream is = null;
		try {
			System.out.println("-------Download Start------");
			URL Url;
			byte[] buf;
			int byteRead;
			int byteWritten = 0;
			Url = new URL(fileAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(
					downloadDir + "\\" + localFileName));
			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[1024];
			while ((byteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, byteRead);
				byteWritten += byteRead;
			}
			System.out.println("Download Successfully.");
			System.out.println("File name : " + localFileName);
			System.out.println("of bytes  : " + byteWritten);
			System.out.println("-------Download End--------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
