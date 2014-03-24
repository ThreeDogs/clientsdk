package testbed.org.cerberus.index;

import java.io.IOException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;


public class Server {

	public static void main(String[] args) throws IOException, HttpException {
		
		ServerSocket serverSocket = new ServerSocket(9000);

		DeviceManager manager = new DeviceManager();
		manager.start();
		
		while(true) {

			System.out.println("--");
//			DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
			
			Socket socket = serverSocket.accept();

			Scanner scanner = new Scanner(socket.getInputStream());
			String data = null;
			while(scanner.hasNext()){
				data = scanner.nextLine();
				System.out.println(data);
				data =data.split("\\?")[1].replaceAll(" HTTP/1.1", "");
				break;
			}
			
			
			
//			String paramStr = null;
//			conn.bind(socket, new BasicHttpParams());
//			HttpRequest request = conn.receiveRequestHeader();
//			if(request instanceof HttpEntityEnclosingRequest) {
//				conn.receiveRequestEntity((HttpEntityEnclosingRequest)request);
//				HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
//				paramStr = EntityUtils.toString(entity);
//				System.out.println(paramStr);
//			} else {
//				System.out.println("fuck");
//				System.out.println(request );
//				
//			}
			
//			socket.getOutputStream().write("success\n".getBytes());
			socket.getOutputStream().write("HTTP/1.1 200 OK\r\n".getBytes());
			socket.getOutputStream().write("Content-Type: text/html\r\n\r\n".getBytes());
			socket.getOutputStream().write("success".getBytes());

			socket.close();
			
			final Map<String , String> paramMap = new HashMap<String , String>();
			
//
			for(String param : data.split("&")) {
				String key = param.split("=")[0];
				String value = param.split("=")[1];
				paramMap.put(key, value);
			}
			
//			if(paramMap.get("order").equals("install")) {
			
			//Todo
			//Download APK
			//make resultArea to ResultServer
			
			
				for(final Map device : DeviceManager.getDeviceList()) {
					
					
					new Runnable() {
						
						@Override
						public void run() {
							String deviceName = (String) device.get("deviceName");
							DeviceManager.installApk(paramMap.get("apkPath").replaceAll("%2F", "/"), deviceName);
							DeviceManager.startTest(deviceName);		
						}
					}.run();
					
				}
				
//			}
//			conn.close();
			
		
		}
		
	}
	
}
