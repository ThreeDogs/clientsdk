package testbed.org.cerberus.index;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class DeviceInfoThread extends Thread {

	DeviceManager manager;
	
	public DeviceManager getManager() {
		return manager;
	}

	public void setManager(DeviceManager manager) {
		this.manager = manager;
	}
	
	@Override
	public synchronized void start() {
		System.out.println("----3");
		try {
			runThread();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private void runThread() throws Exception {
		System.out.println("----3");
		ServerSocket serverSocket = new ServerSocket(9001);

		while (true) {

			System.out.println("--");
			// DefaultHttpServerConnection conn = new
			// DefaultHttpServerConnection();

			Socket socket = serverSocket.accept();
			System.out.println("DeviceInfo..accept");
			Scanner scanner = new Scanner(socket.getInputStream());
			String data = null;

			
			while (scanner.hasNext()) {
				data = scanner.nextLine();
				System.out.println(data);
				if (data.indexOf("/favicon.ico") > 0) {
					break;
				}
				break;
			}
			

			
			if (data.indexOf("/favicon.ico") > 0) {
				socket.getOutputStream()
						.write("HTTP/1.1 200 OK\r\n".getBytes());
				socket.getOutputStream().write(
						"Content-Type: text/html\r\n\r\n".getBytes());
				socket.getOutputStream().write("fail".getBytes());

				socket.close();
				continue;
			}

			Gson gson = new Gson();

			socket.getOutputStream().write("HTTP/1.1 200 OK\r\n".getBytes());
			socket.getOutputStream().write(
					"Content-Type: text/json\r\n\r\n".getBytes());
			List<Map> newDeviceList = new ArrayList<Map>();
			for(Map map : DeviceManager.getDeviceList()) {
				Map newMap = new HashMap();
				
				newMap.put("brand", map.get("brand"));
				newMap.put("cpu", map.get("cpu"));
				newMap.put("model", map.get("model"));
				newMap.put("os_version", map.get("os"));
				newMap.put("country", map.get("country"));
				newMap.put("device_key", map.get("deviceName"));
				
				newDeviceList.add(newMap);
			}
			
			socket.getOutputStream().write(gson.toJson(newDeviceList).getBytes());

			
			
			
			socket.close();


		}
	}

	
	
}
