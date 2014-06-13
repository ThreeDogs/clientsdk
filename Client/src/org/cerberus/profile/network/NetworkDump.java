package org.cerberus.profile.network;

import java.util.HashMap;
import java.util.Map;

import android.net.TrafficStats;
import android.util.Log;

public class NetworkDump {

	private static long lastRx = 0;
	private static long lastTx = 0;
	
	public NetworkDump() {
		lastRx = TrafficStats.getMobileRxBytes();
		lastTx = TrafficStats.getMobileRxBytes();
	}
	
	public static void getNetworkTrace() {
		
		new Runnable() {
			
			@Override
			public void run() {

				long totalRx = TrafficStats.getMobileRxBytes();
				long totalTx = TrafficStats.getMobileTxBytes();
				
				Map map = new HashMap();
				map.put("client_timestamp", System.currentTimeMillis());
				map.put("response_size", (totalRx - lastRx));
				map.put("request_size", (totalTx - lastTx));
				NetworkDataList.getInstance().add(map);
				Log.i("cerberus_network", "Rx " + (totalRx - lastRx) +"byte");
				Log.i("cerberus_network", "Tx " + (totalTx - lastTx) +"byte");
				
				lastRx = totalRx;
				lastTx = totalTx;
				
			}
		}.run();
		
		
	}
	
}
