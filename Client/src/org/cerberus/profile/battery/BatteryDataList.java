package org.cerberus.profile.battery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Use PowerTutor
public class BatteryDataList {

	private static List<Map> instance;
	
	
	
	public static List<Map> getInstance() {
		
		if(instance == null) {
			instance = new ArrayList<Map>();
		}
		
		return instance;
	}
	
}
