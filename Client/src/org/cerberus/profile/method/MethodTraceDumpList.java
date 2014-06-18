package org.cerberus.profile.method;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.util.Log;

public class MethodTraceDumpList {

	private static List<Map> instance;
	
	private static Integer sequence = 0;
	
	private MethodTraceDumpList(){
		
	}
	
	public static List<Map> getInstance() {
		
		if(instance == null) {
			instance = new MethodTraceArrayList<Map>();
		}
		
//		Log.i("cerberusTest", instance.toString());
		
		
		return instance;
	}
	
	
	public static Integer nextValue() {
		sequence++;
		return sequence;
	}
	
}

class MethodTraceArrayList<E extends Map> extends CopyOnWriteArrayList<E> {

	@Override
	public boolean add(E e) {
		
		
		
		String parent = (String) e.get("parent");
		Long threadId = (Long) e.get("tId");
		Integer threadLength = (Integer) e.get("tLength");
		
		Integer parentKey = 0;
		if(size()==0){
		} else {
			for(int i = size()-1 ; i >= 1; i--) {
				
				Map dMap = get(i-1);

				if(dMap.get("method_name").equals(parent) && dMap.get("tId").equals(threadId) && (( (Integer)dMap.get("tLength") +1) == threadLength ) ) {
					parentKey =  (Integer) dMap.get("tree_key");
					break;
				}
				
			}
		}
		e.put("parent_key", parentKey);
		
//		e.remove("parent");
		
		Log.i("cerberus_method", e.toString() + "");
		ConcurrentHashMap map = new ConcurrentHashMap(e);
		return super.add((E) map);
	}
	
	
	
}

