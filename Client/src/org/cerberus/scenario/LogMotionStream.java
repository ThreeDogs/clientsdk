package org.cerberus.scenario;

public class LogMotionStream implements AbstractMotionStream {

	@Override
	public void sendData(MotionVO data) {

		System.out.println(data.toString());
		
	}

}
