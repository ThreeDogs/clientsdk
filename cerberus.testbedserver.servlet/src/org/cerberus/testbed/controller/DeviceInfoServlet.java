package org.cerberus.testbed.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cerberus.testbed.model.DeviceManager;

import com.google.gson.Gson;

public class DeviceInfoServlet extends HttpServlet {

	private static final String adbPath = "/Users/nohsunghyun/dev/adt/sdk/platform-tools/adb";

	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		response.getWriter().write( gson.toJson(newDeviceList).toCharArray() );
		response.getWriter().flush();
	}
	
}
