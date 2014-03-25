package org.cerberus.profile.cpu;

import java.util.ArrayList;
import java.util.List;

public class CpuDataList {

	private static List instance;
	
	private CpuDataList() {
		
	}
	
	public static List getInstance() {
		
		if(instance == null) {
			instance = new ArrayList();
		}
		
		return instance;
		
	}
	
}
