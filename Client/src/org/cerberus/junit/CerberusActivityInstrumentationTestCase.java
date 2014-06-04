package org.cerberus.junit;

import org.cerberus.scenario.MotionEventControl;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.ViewGroup;

public class CerberusActivityInstrumentationTestCase extends ActivityInstrumentationTestCase2 {

	public CerberusActivityInstrumentationTestCase(Class activityClass) {
		super(activityClass);
	}

	
	public void wait(String key) {
		
		while(MotionEventControl.check(key) == null) {

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			
		}
		
		
	}
	
	
	public View findView(ViewGroup v, int id){

		for(int i = 0 ; i < v.getChildCount() ; i++) {
			if(v.getChildAt(i).getId() == id) {
				return v.getChildAt(i);
			}

			if(v.getChildAt(i) instanceof ViewGroup) {
				View result = findView((ViewGroup) v.getChildAt(i), id);
				if(result!= null) {
					return result;
				}
			}
		}

		return null;

	}
	
}
