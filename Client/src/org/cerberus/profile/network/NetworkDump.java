package org.cerberus.profile.network;

import java.util.HashMap;
import java.util.Map;

import android.net.TrafficStats;
import android.util.Log;

public class NetworkDump {

	private static long lastRx = 0;
	private static long lastTx = 0;
	
	public NetworkDump() {
		lastRx = TrafficStats.getTotalRxBytes();
		lastTx = TrafficStats.getTotalTxBytes();
	}
	
	public static void getNetworkTrace() {
		
		new Runnable() {
			
			@Override
			public void run() {

				long totalRx = TrafficStats.getTotalRxBytes();
				long totalTx = TrafficStats.getTotalTxBytes();
				
				Map map = new HashMap();
				map.put("time_stamp", System.currentTimeMillis());
				map.put("rx", (totalRx - lastRx));
				map.put("tx", (totalTx - lastTx));
				NetworkDataList.getInstance().add(map);
				Log.i("cerberus_network", "Rx " + (totalRx - lastRx) +"byte");
				Log.i("cerberus_network", "Tx " + (totalTx - lastTx) +"byte");
				
				lastRx = totalRx;
				lastTx = totalTx;
				
			}
		}.run();
		
		
	}
	
}
