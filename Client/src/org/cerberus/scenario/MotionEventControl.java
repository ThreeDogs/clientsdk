package org.cerberus.scenario;

import java.util.HashMap;
import java.util.Map;

import org.cerberus.event.collection.MotionCollector;

public class MotionEventControl {

	public static Map<String, String> controlMap = new HashMap<String, String>();
	
	public static void wait(String key) {
		
		MotionVO motionData = new MotionVO();

		motionData.setAction_type("wait");
		motionData.setParam(key);
		
		
		MotionCollector.getInstance().putMotion(motionData);
		controlMap.put(key, null);
		
	}
	
	public static void resume(String key) {
		
		controlMap.put(key, "");
		
	}

	public static String check(String key) {
		
		if(controlMap.get(key)!= null) {
			controlMap.remove(key);
			return "";
		} else {
			return null;
		}
		
	}
	
}
