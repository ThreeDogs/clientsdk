package org.cerberus.profile.cpu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class CpuDump {

	private static int count = 0;
	public static void getCpuTrace() {
	
		new Runnable() {

			@Override
			public void run() {

				Map data = new HashMap();

//				data.put("timestamp", System.currentTimeMillis());
				
				Runtime runtime = Runtime.getRuntime();
				Process process;
				String res = "-0-";
				System.out.println(android.os.Process.myPid());
				try {
				        String cmd = "top -n 1";
//				        String cmd = "/system/bin/cat /proc/meminfo";
				        process = runtime.exec(cmd);
				        InputStream is = process.getInputStream();
				        InputStreamReader isr = new InputStreamReader(is);
				        BufferedReader br = new BufferedReader(isr);
				        String line ;
				        while ((line = br.readLine()) != null) {
//				        	System.out.println(line);
//				        	String title = line.split(":")[0];
//				        	String value = line.split(":")[1].replaceAll("kB", "").replaceAll(" ", "");
				        	if(line.replaceAll(" ", "").endsWith("com.example.testandroid")){
				        		System.out.println(line);
				        		
				        		while(line.indexOf("  ") > 0) {
				        			line = line.replaceAll("  ", " ");
				        		}
				        		
				        		String usage = line.split(" ")[3];
				        		data.put("id", count++);
				        		data.put("usage", usage);
				        		break;
				        	}
//				                String segs[] = line.trim().split("[ ]+");
//				                if (segs[0].equalsIgnoreCase(Integer.toString( android.os.Process.myPid() ))) {
//				                        res = segs[1];
//				                        break;
//				                }
				        }
				} catch (Exception e) {
				        e.fillInStackTrace();
				        Log.e("Process Manager", "Unable to execute top command");
				}
				System.out.println(res);
			
				CpuDataList.getInstance().add(data);
				
			}
		}.run();
		
	}
}
