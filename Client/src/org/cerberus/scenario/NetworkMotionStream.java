package org.cerberus.scenario;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

public class NetworkMotionStream implements AbstractMotionStream {

	private static String testScenarioId = "";
	
	private List<MotionVO> motionList = new ArrayList<MotionVO>();
	
	@Override
	public void sendData(MotionVO data) {
		System.out.println(data + ".add");
		motionList.add(data);
		
	}
	
	

	public List<MotionVO> getMotionList() {
		return motionList;
	}



	public void setMotionList(List<MotionVO> motionList) {
		this.motionList = motionList;
	}



	public void sendNetworkData() {
		
		System.out.println("network Send......." + motionList.toString() );
		
		new AsyncTask() {

			@Override
			protected Object doInBackground(Object... arg0) {
				try {
					
					Gson gson = new Gson();
					
					HttpClient client = new DefaultHttpClient();
					
					String uri = "http://172.16.101.75:3000/api/v1/motion_events";
					
					HttpPost post = new HttpPost(uri);
					
					List params = new ArrayList();
					
					params.add(new BasicNameValuePair("test_scenario_id", testScenarioId ));
					params.add(new BasicNameValuePair("motion_events", gson.toJson(motionList) ));
					
					UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
					
					post.setEntity(ent);
					
					HttpResponse response = client.execute(post);
					
					Log.d("test", "send Data...");
					
					if(response.getEntity() != null) {
						Log.i("test", EntityUtils.toString(response.getEntity()));
					}
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
		}.execute();
	}
	
	
	public void getScenarioId() {

		System.out.println("get Scenario id/....");
		
		new AsyncTask() {

			@Override
			protected Object doInBackground(Object... arg0) {
				try {
					
					Gson gson = new Gson();
					
					HttpClient client = new DefaultHttpClient();
					
					String uri = "http://172.16.101.75:3000/api/v1/test_scenarios";
					
					HttpPost post = new HttpPost(uri);
					
					List params = new ArrayList();
					params.add(new BasicNameValuePair("project_id", "1" ));
					UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
					
					post.setEntity(ent);
					
					HttpResponse response = client.execute(post);
					
					Log.d("test", "send Data...");
					
					if(response.getEntity() != null) {
						String result = EntityUtils.toString(response.getEntity()); 
						Log.i("test", result);
						result = result.split(":")[2].replaceAll(" ", "");
						result = result.substring(0, result.length()-2);
						testScenarioId = result;
						Log.i("test", result);
					}
//					{"response":"test_scenario_id: 2"}

					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
		}.execute();
		
		
		System.out.println("finish get Scenario Id");
	}
	
	
}
