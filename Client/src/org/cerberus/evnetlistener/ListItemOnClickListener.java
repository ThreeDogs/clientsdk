package org.cerberus.evnetlistener;

import org.cerberus.event.collection.MotionCollector;
import org.cerberus.scenario.MotionVO;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ListItemOnClickListener implements OnClickListener{

	private OnClickListener thisListener;
	private int count;
	private ViewGroup parent;
	
	public ListItemOnClickListener(OnClickListener listener, int count, ViewGroup parent) {
		thisListener = listener;
		
		this.count = count;
		this.parent = parent;
		
		
	}
	
	public void onClick(View view) {
		
		Log.i("cerberus log", "==-=-=-=-= ListitemClick" + count + "" + parent.getId());
		
		MotionCollector.getInstance().putMotion(
		new MotionVO(
				Long.valueOf(System.currentTimeMillis()), 
				getClass().getName(), 
				"ListItemClick", 
				count+","+parent.getId(), 
				view.getResources().getResourceEntryName(view.getId()))
		);
		
		thisListener.onClick(view);
		
	}
	

}
