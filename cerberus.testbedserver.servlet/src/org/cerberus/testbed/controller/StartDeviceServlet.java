package org.cerberus.testbed.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cerberus.testbed.model.DeviceManager;
import org.cerberus.testbed.servlet.ScenarioInfoServlet;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class StartDeviceServlet extends HttpServlet {
       
	private static Logger logger = Logger.getLogger(StartDeviceServlet.class);
	
	private static final String SH_PATH = "/Users/nohsunghyun/SoftwareMaestro/profile/";
	private static final String SH_FILE = "test.sh";
	
	
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String data = request.getReader().readLine();
		
		System.out.println(data);
		
		
		
		//get Server Data
//		String data = request.getParameter("data");
//		HashMap map = gson.fromJson(data, HashMap.class);
//
//		String apkFilePath = (String) map.get("apk_url");
//		String totalReportId = (String) map.get("total_report_id");
//		String scenarioJson = (String) map.get("test_scenarios");
//		Map o = new HashMap();
//		o.put("total_report_id", totalReportId);
//		o.put("test_scenarios", scenarioJson);
//		for (final Map device : DeviceManager.getDeviceList()) {
//			
//			if(ScenarioInfoServlet.scenarioMap.get(device.get("deviceName"))==null) {
//				
//				List list = new LinkedList<Map>();
//				list.add(o);
//				ScenarioInfoServlet.scenarioMap.put((String) device.get("deviceName"), list);
//			}
//			else {
//				ScenarioInfoServlet.scenarioMap.get(device.get("deviceName")).add(o);
//			}
//		}
		
		String apkFilePath = "http://localhost:8080/file/newTest.apk";
		
		String key = "1";
		String fileName = "newTest.apk";
		
		final String downloadPath =  getServletContext().getRealPath("/") +  "/apk/" + key + "/" + fileName;
		logger.info("servletContextRealPath : " + getServletContext().getRealPath("/"));
		
		fileUrlReadAndDownload(apkFilePath, getServletContext().getRealPath("/") + "/apk/" + key + "/", fileName);
		
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
					
					System.out.println(deviceName + " uninstall");
					DeviceManager.uninstallApk(deviceName, "com.example.testandroid");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(deviceName + " install");
					DeviceManager.installApk(downloadPath, deviceName);
				}
			}).start();
			
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

	
	
	public static void fileUrlReadAndDownload(String fileAddress, String downloadDir, String localFileName) {
		
		logger.info(fileAddress);
		logger.info(localFileName);
		logger.info(downloadDir);
		
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
			 new File(downloadDir ).mkdirs();
			
			outStream = new BufferedOutputStream(new FileOutputStream(
					downloadDir + "" + localFileName));
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
