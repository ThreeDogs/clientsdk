package org.cerberus.event.collection;

import org.cerberus.scenario.LogMotionStream;
import org.cerberus.scenario.MotionCollectionManager;

public class MotionCollector {

	private static MotionCollectionManager motionCollectionManager;
	
	private MotionCollector() {
		
		
	}
	
	public static MotionCollectionManager getInstance() {
		if(motionCollectionManager == null)
			motionCollectionManager = new MotionCollectionManager(new LogMotionStream());
		
		return motionCollectionManager;
	}
	
}
