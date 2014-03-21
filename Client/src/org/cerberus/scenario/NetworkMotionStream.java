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

	private List<MotionVO> motionList = new ArrayList<MotionVO>();
	
	@Override
	public void sendData(MotionVO data) {

		motionList.add(data);
		
	}

	public void sendNetworkData() {
		
		new AsyncTask() {

			@Override
			protected Object doInBackground(Object... arg0) {
				try {
					
					Gson gson = new Gson();
					
					HttpClient client = new DefaultHttpClient();
					
					String uri = "";
					
					HttpPost post = new HttpPost(uri);
					
					List params = new ArrayList();
					
					params.add(new BasicNameValuePair("key", gson.toJson(motionList) ));
					
					UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
					
					post.setEntity(ent);
					
					HttpResponse response = client.execute(post);
					
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
			
		}.execute(null);
		
	}
	
}
