package org.cerberus.scenario;

import android.util.Log;


public class MotionCollectionManager {

	private static Integer count = 0;
	private Long lastSleepTime;
	private AbstractMotionStream stream;
	
	public MotionCollectionManager(AbstractMotionStream stream) {
		this.stream = stream;
	}
	
	public void putMotion(MotionVO motionData) {
		System.out.println(motionData);
		Log.i("cerberus", motionData.toString());
		if(lastSleepTime == null) {
			lastSleepTime = System.currentTimeMillis();
		} else {
			motionData.setSleep(System.currentTimeMillis() - lastSleepTime);
			lastSleepTime = System.currentTimeMillis();
		}
		motionData.setId(count++);
		motionData.setTime_stamp(System.currentTimeMillis());
		stream.sendData(motionData);
		System.out.println(stream);
	}

	public AbstractMotionStream getStream() {
		return stream;
	}

	public void setStream(AbstractMotionStream stream) {
		this.stream = stream;
	}
	

	
}