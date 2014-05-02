package org.cerberus.testbed.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.cerberus.testbed.model.DeviceManager;

public class ContextListener implements ServletContextListener {

	private DeviceManager manager ;

    public void contextInitialized(ServletContextEvent arg0) {

		manager = new DeviceManager();
    	manager.start();
    	manager.allAddDevices();
    
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    
    	manager.interrupt();
    
    }
	
}
