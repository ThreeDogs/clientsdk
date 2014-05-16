package testbed.org.cerberus.index;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DeviceRunThread extends Thread {

	DeviceManager manager;
	
	
	
	public DeviceManager getManager() {
		return manager;
	}

	public void setManager(DeviceManager manager) {
		this.manager = manager;
	}

	@Override
	public synchronized void start() {
		System.out.println("----2");
		try {
			runThread();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void runThread() throws Exception {
		System.out.println("----2");
		ServerSocket serverSocket = new ServerSocket(9000);

		while (true) {

			System.out.println("--");
			// DefaultHttpServerConnection conn = new
			// DefaultHttpServerConnection();

			Socket socket = serverSocket.accept();

			Scanner scanner = new Scanner(socket.getInputStream());
			String data = null;

			
			//apkinfosend
			
//			while (scanner.hasNext()) {
//				data = scanner.nextLine();
//				System.out.println(data);
//				if (data.indexOf("/favicon.ico") > 0) {
//					break;
//				}
////				System.out.println(data);
////				data = data.split("\\?")[1].replaceAll(" HTTP/1.1", "");
////				break;
//				
//				
////				if(data.startsWith("Content-Length: ")) {
////					System.out.println("--ContentLength " + data);
////					Integer contentLength = new Integer( data.replaceAll("Content-Length: ", "") );
////					System.out.println(contentLength);
////
////					
////					StringBuilder sb = new StringBuilder();
////					
////					int b = 0;
////					
////					
////					BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
////					byte[] buffer = new byte[contentLength];
////					
////					System.out.println(buffer.length);
////					int result;
////					int count =0;
////					while(scanner.hasNext()) {
////
////						String scn = scanner.next();
////						
////						sb.append(scn);
////						
////						System.out.println(scn);
////						
////					}
////					System.out.println("count = " + count);
////					data = sb.toString();
////					break;
//////					System.out.println(scanner.nextByte(contentLength-1));
////				}
//			}
			

			System.out.println("---");
//			System.out.println(data);
//			System.out.println("---");
			
//			if (data.indexOf("/favicon.ico") > 0) {
//				socket.getOutputStream()
//						.write("HTTP/1.1 200 OK\r\n".getBytes());
//				socket.getOutputStream().write(
//						"Content-Type: text/html\r\n\r\n".getBytes());
//				socket.getOutputStream().write("fail".getBytes());
//
//				socket.close();
//				continue;
//			}

			socket.getOutputStream().write("HTTP/1.1 200 OK\r\n".getBytes());
			socket.getOutputStream().write(
					"Content-Type: text/html\r\n\r\n".getBytes());
			socket.getOutputStream().write("success".getBytes());

			socket.close();

			final Map<String, String> paramMap = new HashMap<String, String>();

			// make parameter Map
//			for (String param : data.split(",")) {
//				String key = param.split(":")[0];
//				String value = param.split(":")[1];
//				paramMap.put(key, value);
//			}

			
			//paramMap {"test_scenario_ids"=[1]}, "total_report_id"=12, {"apk_url"="/uploads/apk/apk/4/TestAndroid.apk"}
//			System.out.println("paramMap " + paramMap.toString());
			
			// if(paramMap.get("order").equals("install")) {

			// Todo
			// Download APK
			// make resultArea to ResultServer

//			List<Thread> threadPool = new ArrayList<Thread>();
//			
//			for (final Map device : DeviceManager.getDeviceList()) {
//
//				Socket resultSocket = new Socket("127.0.0.1", 7000);
//				resultSocket.getOutputStream().write(
//						("make " + device.get("deviceName") + " " + "1 1")
//								.getBytes());
//				resultSocket.getOutputStream().flush();
//				resultSocket.close();
//
//				
//				Runnable r = new Runnable() {
//
//					@Override
//					public void run() {
//						String deviceName = (String) device.get("deviceName");
//						DeviceManager.installApk(paramMap.get("apkPath")
//								.replaceAll("%2F", "/"), deviceName);
//						System.out.println(device.get("model") + " finish");
//					}
//				};
//
//				Thread t = new Thread(r);
//				t.start();
//				System.out.println("t" + t);
//				threadPool.add(t);
//			}
//			
//			for(Thread joinThread : threadPool) {
//				joinThread.join();
//			}
//			
			Thread.sleep(10000);
			for (final Map device : DeviceManager.getDeviceList()) {

				new Thread( new Runnable() {

					@Override
					public void run() {
						String deviceName = (String) device.get("deviceName");
						System.out.println(deviceName + " start");
						DeviceManager.startTest(deviceName);
					}
				}
				).start();

			}
			// }
			// conn.close();

		}
	}

	
	
	
}
