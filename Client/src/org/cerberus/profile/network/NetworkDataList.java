package org.cerberus.profile.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NetworkDataList {

	private static List<Map> instance;
	
	private NetworkDataList() {
	}

	public static List<Map> getInstance() {
		
		if(instance == null) {
			instance = new ArrayList<Map>();
		}
		
		return instance;
	}
	
}
