package testbed.org.cerberus.index;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
import com.google.gson.Gson;

public class ResultServer {

	public static void main(String[] args) throws IOException {
		System.out.println("----");
		Map<String, Map> list = new HashMap();

		ServerSocket serverSocket = new ServerSocket(7000);

		while (true) {

			Socket socket = serverSocket.accept();
			System.out.println("==");
			Scanner scanner = new Scanner(socket.getInputStream());

			String data = "";
			while (scanner.hasNext()) {
				data += scanner.next() + " ";
			}
			System.out.println(data);
			if (data.startsWith("make")) {

				List<String> deviceList = new ArrayList();

				Integer reportKey = 1;
				Map testObj = new HashMap();

				System.out.println(data);
				
				testObj.put("totalReport", data.split(" ")[3]);
				testObj.put("scenario", data.split(" ")[2]);

				System.out.println(data.split(" ")[1] + " " + testObj);
				
				list.put(data.split(" ")[1], testObj);

				
				
			} else if (data.startsWith("result")) {
				try {
					System.out.println("==--=-=-");

					socket.getOutputStream().write("200".getBytes());

					data = data.replace("result---", "");
					String deviceName = data.substring(0,data.indexOf("---"));
					data = data.replace(deviceName+"---", "");
					System.out.println(data);
					Gson gson = new Gson();
					Map map = gson.fromJson(data, HashMap.class);
					
					System.out.println(map);
//					InputStream in = new ByteArrayInputStream(data.getBytes());
	
//					ObjectInputStream ois = new ObjectInputStream(in);
//					
//					Object obj = ois.readObject();
//					
//					System.out.println(obj);
//					
//					ois.close();
//					in.close();
					
					Session s = new Session("172.16.101.163", 5984);
					Database db = s.getDatabase("cerberus");
					
					Document doc = new Document();// db.getDocument( obj.get("detail_report_id").toString() );
					map.put("detail_report_id", db.getUpdateSeq()+1);
					doc.putAll(map);
					
					db.saveDocument(doc);
					
				} catch (Exception e) {
					e.printStackTrace();
					socket.getOutputStream().write("400".getBytes());
				} finally {
					socket.getOutputStream().flush();
					socket.close();
				}
			} else if (data.startsWith("getScenario")) {
				String deviceName = data.split(" ")[1];
				socket.getOutputStream().write( ((String)list.get(deviceName).get("scenario") + " " + (String)list.get(deviceName).get("totalReport") ).getBytes() );
				socket.getOutputStream().flush();
			}

			
			socket.close();
		}

	}
}
