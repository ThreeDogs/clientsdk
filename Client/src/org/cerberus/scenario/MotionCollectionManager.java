package org.cerberus.scenario;

import android.util.Log;


public class MotionCollectionManager {

	private static Integer count = 0;
	private Long lastSleepTime;
	private AbstractMotionStream stream;
	private MotionAddListener addListener;
	private MotionVO lastMotion;
	
	
	public MotionCollectionManager(AbstractMotionStream stream) {
		this.stream = stream;
	}
	
	public void putMotion(MotionVO motionData) {
		
		boolean isSameEditText = false;
		
		if( lastMotion != null && lastMotion.getActivity_class().indexOf("RuntimeMotionInjector")>0 && lastMotion.getView().equals(motionData.getView()) && !lastMotion.getAction_type().equals("EditText") && motionData.getAction_type().equals(lastMotion.getAction_type()) )
			return;

		if( lastMotion != null && motionData.getAction_type().equals("EditText") && lastMotion.getAction_type().equals("EditText") && lastMotion.getView().equals(motionData.getView()))
			isSameEditText = true;
		
		System.out.println(motionData);
		Log.i("cerberus", motionData.toString());
		if(lastSleepTime == null) {
			lastSleepTime = System.currentTimeMillis();
		} else {
			motionData.setSleep(System.currentTimeMillis() - lastSleepTime + lastSleepTime);
			lastSleepTime = System.currentTimeMillis();
		}
		if(!isSameEditText)
			motionData.setId(count++);
		Long timestamp = System.currentTimeMillis();
		motionData.setTime_stamp(timestamp);
		
		if(!isSameEditText)
			stream.sendData(motionData);
		else
			stream.updateData(motionData);
		lastMotion = motionData;
		System.out.println(stream);
		
		if(addListener != null)
            addListener.addItem(Long.valueOf(timestamp));
	}

	public AbstractMotionStream getStream() {
		return stream;
	}

	public void setStream(AbstractMotionStream stream) {
		this.stream = stream;
	}
	
	public void setMotionAddListener(MotionAddListener listener) {
		this.addListener = listener;
	}
	
	public static interface MotionAddListener {
		public abstract void addItem(Long id);
	}

	
}
