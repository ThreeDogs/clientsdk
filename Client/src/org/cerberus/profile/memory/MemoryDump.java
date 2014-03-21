package org.cerberus.profile.memory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class MemoryDump {

	public static void getMemoryTrace() {

		new Runnable() {

			@Override
			public void run() {


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
				        	System.out.println(title + ":" + value);
				        	
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
				System.out.println(res);
			
				
			}
		}.run();
	}

}
