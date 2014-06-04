package org.cerberus.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.support.v4.app.Fragment;

public class ClassCastUtil {

	public static Resources getResource(Object obj){
		if(obj instanceof Activity) {
			
			return ((Activity)obj).getResources();
			
		} else if (obj instanceof Dialog) {
			
			return ((Dialog)obj).getContext().getResources();
			
		} else if( obj instanceof Fragment) {
			
			return ((Fragment)obj).getResources();
		}
		
		return null;
	}
	
}
