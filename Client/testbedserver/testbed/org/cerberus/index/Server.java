package testbed.org.cerberus.index;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpException;

public class Server {

	public static void main(String[] args) throws IOException, HttpException {

		ServerSocket serverSocket = new ServerSocket(9000);

		DeviceManager manager = new DeviceManager();
		manager.start();

		while (true) {

			System.out.println("--");
			// DefaultHttpServerConnection conn = new
			// DefaultHttpServerConnection();

			Socket socket = serverSocket.accept();

			Scanner scanner = new Scanner(socket.getInputStream());
			String data = null;
			while (scanner.hasNext()) {
				data = scanner.nextLine();
				System.out.println(data);
				if (data.indexOf("/favicon.ico") > 0) {
					break;
				}
				data = data.split("\\?")[1].replaceAll(" HTTP/1.1", "");
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

			// String paramStr = null;
			// conn.bind(socket, new BasicHttpParams());
			// HttpRequest request = conn.receiveRequestHeader();
			// if(request instanceof HttpEntityEnclosingRequest) {
			// conn.receiveRequestEntity((HttpEntityEnclosingRequest)request);
			// HttpEntity entity = ((HttpEntityEnclosingRequest)
			// request).getEntity();
			// paramStr = EntityUtils.toString(entity);
			// System.out.println(paramStr);
			// } else {
			// System.out.println("fuck");
			// System.out.println(request );
			//
			// }

			// socket.getOutputStream().write("success\n".getBytes());
			socket.getOutputStream().write("HTTP/1.1 200 OK\r\n".getBytes());
			socket.getOutputStream().write(
					"Content-Type: text/html\r\n\r\n".getBytes());
			socket.getOutputStream().write("success".getBytes());

			socket.close();

			final Map<String, String> paramMap = new HashMap<String, String>();

			// make parameter Map
			for (String param : data.split("&")) {
				String key = param.split("=")[0];
				String value = param.split("=")[1];
				paramMap.put(key, value);
			}

			// if(paramMap.get("order").equals("install")) {

			// Todo
			// Download APK
			// make resultArea to ResultServer

			for (final Map device : DeviceManager.getDeviceList()) {

				//
				Socket resultSocket = new Socket("127.0.0.1", 7000);
				resultSocket.getOutputStream().write(
						("make " + device.get("deviceName") + " " + "1 1")
								.getBytes());
				resultSocket.getOutputStream().flush();
				resultSocket.close();

				new Runnable() {

					@Override
					public void run() {
						String deviceName = (String) device.get("deviceName");
						DeviceManager.installApk(paramMap.get("apkPath")
								.replaceAll("%2F", "/"), deviceName);
					}
				}.run();

			}
			for (final Map device : DeviceManager.getDeviceList()) {

				new Runnable() {

					@Override
					public void run() {
						String deviceName = (String) device.get("deviceName");
						DeviceManager.startTest(deviceName);
					}
				}.run();

			}

			// }
			// conn.close();

		}

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
