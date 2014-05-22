package org.cerberus.evnetlistener;

import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ListItemOnClickFactory {

	public static ListItemOnClickListener newInstance(OnClickListener l, int i, ViewGroup vg) {
		return new ListItemOnClickListener(l, i, vg);
	}
	
}
