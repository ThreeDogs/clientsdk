package org.cerberus.scenario;

import android.util.Log;


public class MotionCollectionManager {

	private static Integer count = 0;
	private Long lastSleepTime;
	private AbstractMotionStream stream;
	private MotionAddListener addListener;
	private MotionVO lastMotion;
	private boolean callCheck = false;
	
	
	
	public MotionCollectionManager(AbstractMotionStream stream) {
		this.stream = stream;
	}
	
	public void putMotion(MotionVO motionData) {
		
		boolean isSameEditText = false;
		
		
		if(motionData!=null)
			Log.d("cerbeurs_debbugger", "" + motionData.toString() );
		
		if( lastMotion != null && lastMotion.getActivity_class().indexOf("RuntimeMotionInjector")>0 && 
				lastMotion.getView().equals(motionData.getView()) && 
				motionData.getAction_type().equals(lastMotion.getAction_type())  &&
				!(motionData.getActivity_class().indexOf("RuntimeMotionInjector")>0)
				)
		{
			if(!lastMotion.getAction_type().equals("EditText") && !lastMotion.getAction_type().equals("drag")){
				if(!lastMotion.getAction_type().equals("Click")) {
					
				}
					return;
				}
		}
		
		
		if( lastMotion != null && lastMotion.getView().equals(motionData.getView()) && lastMotion.getAction_type().equals("ListItemClick") && lastMotion.getActivity_class().indexOf("cerberus")>0) {
			if(callCheck == false) {
				callCheck = true;
				return;
			} else {
				callCheck = false;
			}
			
		}

		
		if( lastMotion != null && motionData.getAction_type().equals("EditText") && lastMotion.getAction_type().equals("EditText") && lastMotion.getView().equals(motionData.getView()))
			isSameEditText = true;
		
		System.out.println(motionData);
		Log.i("cerberus", motionData.toString());
		if(lastSleepTime == null) {
			lastSleepTime = System.currentTimeMillis();
		} else {
			motionData.setSleep(System.currentTimeMillis() - lastSleepTime);
			lastSleepTime = System.currentTimeMillis();
		}
		if(!isSameEditText)
			motionData.setId(count++);
		Long timestamp = System.currentTimeMillis();
		motionData.setTime_stamp(timestamp);
		
		if(!isSameEditText){
			stream.sendData(motionData);
			if(addListener != null)
	            addListener.addItem(Long.valueOf(timestamp));
		}
		else
			stream.updateData(motionData);
		
		lastMotion = motionData;
		System.out.println(stream);
		callCheck = false;
		
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
	
//	public MotionAddListener getMotionAddListener() {
//		return addListener;
//	}
	
	public static interface MotionAddListener {
		public abstract void addItem(Long id);
	}

	
}
