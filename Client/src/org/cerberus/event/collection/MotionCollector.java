package org.cerberus.event.collection;

import org.cerberus.scenario.MotionCollectionManager;
import org.cerberus.scenario.NetworkMotionStream;

public class MotionCollector {

	private static MotionCollectionManager motionCollectionManager;
	
	private MotionCollector() {
		
		
	}
	
	public static MotionCollectionManager getInstance() {
		System.out.println("====");
		if(motionCollectionManager == null)
			motionCollectionManager = new MotionCollectionManager(new NetworkMotionStream());
		
		return motionCollectionManager;
	}
	
}
