package org.cerberus.profile.memory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import android.os.Debug;
import android.util.Log;

public class MemoryDump {

	public static int count = 0;
	
	public static void getMemoryTrace() {

		new Runnable() {

			@Override
			public void run() {

				 Double allocated = new Double(Debug.getNativeHeapAllocatedSize())/new Double((1048576));
			    Double available = new Double(Debug.getNativeHeapSize()/1048576.0);
			    Double free = new Double(Debug.getNativeHeapFreeSize()/1048576.0);

			    DecimalFormat df = new DecimalFormat();
			    df.setMaximumFractionDigits(2);
			    df.setMinimumFractionDigits(2);
			    
			    Map dataMap = new HashMap();
			    
				Runtime runtime = Runtime.getRuntime();
				Process process;
				String res = "-0-";
				System.out.println(android.os.Process.myPid());
				try {
				        String cmd = "/system/bin/cat /proc/meminfo " + android.os.Process.myPid();
//				        String cmd = "/system/bin/cat /proc/meminfo";
				        process = runtime.exec(cmd);
				        InputStream is = process.getInputStream();
				        InputStreamReader isr = new InputStreamReader(is);
				        BufferedReader br = new BufferedReader(isr);
				        String line ;
				        while ((line = br.readLine()) != null) {
//				        	System.out.println(line);
				        	String title = line.split(":")[0];
				        	String value = line.split(":")[1].replaceAll("kB", "").replaceAll(" ", "");
				        	
				        	if(title.replaceAll(" ", "").equals("MemTotal")){
//				        		System.out.println(title + ":" + value);
				        		dataMap.put("mem_total", value);
				        	}else if (title.replaceAll(" ", "").equals("MemFree")) {
				        		dataMap.put("mem_free", value);
				        	}
				        	
				                String segs[] = line.trim().split("[ ]+");
				                if (segs[0].equalsIgnoreCase(Integer.toString( android.os.Process.myPid() ))) {
				                        res = segs[1];
				                        break;
				                }
				        }
				} catch (Exception e) {
				        e.fillInStackTrace();
				        Log.e("Process Manager", "Unable to execute top command");
				}
				

			    Log.d("cerberus", "debug.heap native: allocated " + df.format(allocated) + "MB of " + df.format(available) + "MB (" + df.format(free) + "MB free)");
			    Log.d("cerberus", "debug.memory: allocated: " + df.format(new Double(Runtime.getRuntime().totalMemory()/1048576)) + "MB of " + df.format(new Double(Runtime.getRuntime().maxMemory()/1048576))+ "MB (" + df.format(new Double(Runtime.getRuntime().freeMemory()/1048576)) +"MB free)");
			    
//			    dataMap.put("id"s, count++);
			    dataMap.put("mem_alloc",  Integer.parseInt((String)dataMap.get("mem_total") )  -  Integer.parseInt((String)dataMap.get("mem_free"))  );
			    dataMap.put("native_heap_free", df.format(free));
			    dataMap.put("native_heap_alloc", df.format(allocated));
			    dataMap.put("native_heap_size", df.format(available));
//			    dataMap.put("DalvikHeapFree",  Integer.parseInt((String)dataMap.get("MemFree")) - Integer.parseInt((String)dataMap.get("NativeHeapFree")) );
			    dataMap.put("dalvik_heap_alloc", (Integer)dataMap.get("mem_alloc") - Double.parseDouble((String)dataMap.get("native_heap_alloc")));
			    dataMap.put("dalvik_heap_size", Integer.parseInt((String)dataMap.get("mem_total")) - Double.parseDouble((String)dataMap.get("native_heap_size")));
			    dataMap.put("client_timestamp", System.currentTimeMillis());
				
			    MemoryDataList.getInstance().add(dataMap);
			    
//				System.out.println(res);
			
				
			}
		}.run();
	}

}
