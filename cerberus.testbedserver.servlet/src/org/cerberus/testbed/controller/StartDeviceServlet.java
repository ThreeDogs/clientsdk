package org.cerberus.testbed.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cerberus.testbed.model.DeviceManager;

public class StartDeviceServlet extends HttpServlet {
       
	private static Logger logger = Logger.getLogger(StartDeviceServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> e = request.getParameterNames();
		
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			System.out.println(key + " " + request.getParameter(key));
		}
		
		String apkFilePath = "";
		String key = "";
		String fileName = "";
		
		final String downloadPath =  getServletContext().getRealPath("/") +  "/apk/" + key + "/" + fileName;
		
//		fileUrlReadAndDownload(apkFilePath, getServletContext().getRealPath("/"), "/apk/" + key + "/" + fileName);
		
		logger.info("servletContextRealPath : " + getServletContext().getRealPath("/"));
		
		//Sleep Time 1sec
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
		}
		
		for (final Map device : DeviceManager.getDeviceList()) {
			
			new Thread( new Runnable() {
				
				@Override
				public void run() {
					String deviceName = (String) device.get("deviceName");
					System.out.println(deviceName + " install");
					DeviceManager.installApk(downloadPath, deviceName);
				}
			}).start();
			
		}
		
		
		for (final Map device : DeviceManager.getDeviceList()) {

			new Thread( new Runnable() {

				@Override
				public void run() {
					String deviceName = (String) device.get("deviceName");
					System.out.println(deviceName + " start");
					DeviceManager.startTest(deviceName);
				}
			}).start();

		}
		
		response.getWriter().write("success".toCharArray());
		response.getWriter().flush();
		
	}

	
	
	public static void fileUrlReadAndDownload(String fileAddress, String localFileName, String downloadDir) {
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
