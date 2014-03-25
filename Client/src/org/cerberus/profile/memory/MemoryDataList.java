package org.cerberus.profile.memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryDataList {

	private static List instance;
	
	private MemoryDataList(){
		
	}
	
	public static List getInstance() {
		
		if(instance == null) {
			instance = new ArrayList();
		}
		
		return instance;
	}
	
}
