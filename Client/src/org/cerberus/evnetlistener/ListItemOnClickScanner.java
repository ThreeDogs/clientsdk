package org.cerberus.evnetlistener;

import org.cerberus.event.collection.MotionCollector;
import org.cerberus.scenario.MotionVO;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListItemOnClickScanner implements OnClickListener {

	private OnClickListener clickListener;
	
	public ListItemOnClickScanner(OnClickListener listener) {
		this.clickListener = listener;
	}

	@Override
	public void onClick(View view) {

		int index = -1;
		
		//view scan
		View parentView = view;
		AdapterView listView = null;
		try{
			while(parentView.getParent()!=null) {
				
				Log.d("cerberusListScanner", parentView.toString());
				
				if(parentView.getParent() instanceof AdapterView) {
					listView = (AdapterView) parentView.getParent();
					int hashCode = parentView.hashCode();
					
					for(int i = 0 ; i < listView.getCount() ; i++) {
						if(listView.getChildAt(i).hashCode() == hashCode) {
							index = i;
							break;
						}
					}
				}
				
				parentView = (View) parentView.getParent();
				
			}
		}catch(Exception e) {
//			e.printStackTrace();
		}
		
		if(index != -1) {
		
			MotionCollector.getInstance().putMotion(
			new MotionVO(
					Long.valueOf(System.currentTimeMillis()), 
					getClass().getName(), 
					"ListItemClick", 
					index+","+ ((View)listView).getId(), 
					view.getResources().getResourceEntryName(view.getId()))
			);
		}
		
		
		clickListener.onClick(view);
		
	}

}
