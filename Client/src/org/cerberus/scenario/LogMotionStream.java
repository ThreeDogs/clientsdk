package org.cerberus.scenario;

import android.util.Log;


public class LogMotionStream implements AbstractMotionStream {

	@Override
	public void sendData(MotionVO data) {

		System.out.println(data.toString());

//		Log.i("Log", "Test...");
		
	}

}
