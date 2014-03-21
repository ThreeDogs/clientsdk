package org.cerberus.scenario;


public class MotionCollectionManager {

	private static Integer count = 0;
	private Long lastSleepTime;
	private AbstractMotionStream stream;
	
	public MotionCollectionManager(AbstractMotionStream stream) {
		this.stream = stream;
	}
	
	public void putMotion(MotionVO motionData) {
		if(lastSleepTime == null) {
			lastSleepTime = System.currentTimeMillis();
		} else {
			motionData.setSleep(System.currentTimeMillis() - lastSleepTime);
			lastSleepTime = System.currentTimeMillis();
		}
		motionData.setId(count++);
		stream.sendData(motionData);
	}

	public AbstractMotionStream getStream() {
		return stream;
	}

	public void setStream(AbstractMotionStream stream) {
		this.stream = stream;
	}
	

	
}
