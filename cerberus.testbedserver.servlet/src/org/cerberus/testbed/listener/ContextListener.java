package org.cerberus.testbed.listener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.cerberus.testbed.model.DeviceManager;
import org.cerberus.testbed.servlet.ScenarioInfoServlet;

public class ContextListener implements ServletContextListener {

	private DeviceManager manager ;

    public void contextInitialized(ServletContextEvent arg0) {

		manager = new DeviceManager();
    	manager.start();
    	manager.allAddDevices();
    
    	List list = new LinkedList();
    	
    	Map o = new HashMap();
    	o.put("total_report_id", "5");
    	o.put("test_scenarios", "123123123123");
    	list.add(o);
    	
    	ScenarioInfoServlet.scenarioMap.put("42f6bf1ed4b18f8b", list);
    	
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    
    	manager.interrupt();
    
    }
	
}
