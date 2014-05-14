package org.cerberus.testbed.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ScenarioInfoServlet extends HttpServlet {
       
	public static Map<String, List> scenarioMap = new HashMap();
	
	private static final Logger logger = Logger.getLogger(ScenarioInfoServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String deviceName = request.getParameter("deviceName");
		
		List scenarioInfo = scenarioMap.get(deviceName);
		
		String totalReportId = (String) ((Map)scenarioInfo.get(0)).get("total_report_id");
		String jsonScenario = (String) ((Map)scenarioInfo.get(0)).get("test_scenarios");
		
		logger.info("totalReportId - " + totalReportId);
		logger.info("jsonScenario - " + jsonScenario);
		
//		scenarioInfo.remove(0);
		
		response.getWriter().write(totalReportId);
		response.getWriter().write("-");
		response.getWriter().write(jsonScenario);
		response.getWriter().flush();  
		
	}
	
}
