package org.cerberus.profile.crash;

import java.util.HashMap;
import java.util.Map;

public class CrashDump {

	private static Map crashList;
	
	public static Map getInstance() {
		
		if(crashList == null) {
			crashList = new HashMap();
		}
		
		return crashList;
	}
	
}
