package org.cerberus.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.cerberus.config.ServerInfo;
import org.cerberus.crawler.ProfilingCrawler;
import org.cerberus.event.collection.MotionCollector;
import org.cerberus.index.CerberusAPI;
import org.cerberus.profile.battery.BatteryDataList;
import org.cerberus.profile.cpu.CpuDataList;
import org.cerberus.profile.crash.CrashDump;
import org.cerberus.profile.drawtime.FrameDrawTimeDumpList;
import org.cerberus.profile.memory.MemoryDataList;
import org.cerberus.profile.method.MethodTraceDumpList;
import org.cerberus.profile.network.NetworkDataList;
import org.cerberus.scenario.MotionVO;
import org.cerberus.scenario.NetworkMotionStream;
import org.cerberus.scenario.MotionCollectionManager.MotionAddListener;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.robotium.solo.Solo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CerberusActivityInstrumentation extends ActivityInstrumentationTestCase2 {

	private static final String SERVER_IP = ServerInfo.ServerURI;
	private static final Integer SERVER_PORT = 8080;
	
	private static final String LAUNCHER_ACTIVITY_CLASSNAME = "com.example.testandroid.MainActivity";
	private static final String PROTOCAL = "http";
	private static Class<?> launcherActivityClass;
	
	Solo solo;
	
	public CerberusActivityInstrumentation(Class activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}
	
	public CerberusActivityInstrumentation(String pkg, Class activityClass) {
		super(pkg, activityClass);
		// TODO Auto-generated constructor stub
	}

	
	String version;
	Long startDate;
	Integer totalReportId =1;
	static String serial = "1.0"; 
	Integer totalScenarioId = 1;
	
	boolean isCalledSetup = false;
	boolean isCalledTearDown = false;
	
	public void setUp() throws Exception {
		
		
			if(isCalledSetup) {
				Log.e("cerberus_test_runner", "was called... setup");
				return;
			}
			
			isCalledSetup = true;
			
			super.setUp();
			
			  solo = new Solo(getInstrumentation(), getActivity());
			
			CerberusAPI.SERVER_IP = SERVER_IP;
		 	CerberusAPI.SERVER_PORT = SERVER_PORT;
		 	CerberusAPI.LAUNCHER_ACTIVITY_CLASSNAME = LAUNCHER_ACTIVITY_CLASSNAME;
		 	CerberusAPI.PROTOCAL = PROTOCAL;
		 	CerberusAPI.isTestRunning = true;
			
		 	try {
	        	PackageInfo i = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
	        	version = i.versionName;
	        	CerberusAPI.version = i.versionName;
	        } catch(NameNotFoundException e) { }
	        
	        startDate = System.currentTimeMillis();
	        CerberusAPI.startDate = startDate;
	
	        try {
	            Class<?> c = Class.forName("android.os.SystemProperties");
	            Method get = c.getMethod("get", String.class);
	            serial = (String) get.invoke(c, "ro.serialno");
	            CerberusAPI.serial = (String) get.invoke(c, "ro.serialno");
	        } catch (Exception ignored) {
	        	ignored.printStackTrace();
	        }
	        
	//        Socket socket = new Socket(SERVER_IP,SERVER_PORT);
	//        
	//        String content = "make ";
	//        
	//        socket.getOutputStream().write(content.getBytes());
	        
			String url = PROTOCAL + "://" + SERVER_IP + ":" + SERVER_PORT + "/testSvr/ScenarioInfoServlet" ;
			HttpClient client = new DefaultHttpClient();
			ArrayList<NameValuePair> nameValuePairs = 
					new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("deviceName", serial));
	
	
			HttpPost httpPost = new HttpPost(url);
			UrlEncodedFormEntity entityRequest = 
					new UrlEncodedFormEntity(nameValuePairs, "EUC-KR");
			
			httpPost.setEntity(entityRequest);
			
			HttpResponse responsePost = client.execute(httpPost);
			
			String result = "";
			try {
				InputStream in = responsePost.getEntity().getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in, "UTF-8"));
				StringBuilder str = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					str.append(line);
				}
				in.close();
				result = str.toString();
			} catch (Exception ex) {
				result = "Error";
			}
			
			
	//		HttpEntity resEntity = responsePost.getEntity();
	//		String resString = new String( EntityUtils.toString(resEntity).getBytes(),"UTF-8" );
			String resString = result;
			System.out.println(resString); 
			Log.i("cerberusTest", "result - " + resString);
	        totalReportId = Integer.parseInt( resString.split("-")[0] );
	        CerberusAPI.totalReportId = totalReportId;
	        totalScenarioId = Integer.parseInt( resString.split("-")[1] );
	        CerberusAPI.totalScenarioId = totalScenarioId;
	        
	//        Thread.sleep(5000);
	        
	        MotionCollector.getInstance().setMotionAddListener(new MotionAddListener() {
				
	        	private static final String IMAGE_SERVER_URL = "http://" + SERVER_IP + ":8080/testSvr/ImageUploadServlet";
	        	
				@Override
				public void addItem(final Long timeStamp) {
	
					
					String filePath = takeScreenshot(timeStamp.toString());
					final File file = new File(filePath);
					
					new AsyncTask(){
	
						@Override
						protected Object doInBackground(Object... params) {
	
							try{
								HttpClient httpClient = new DefaultHttpClient();
								HttpPost httpPost = new HttpPost(IMAGE_SERVER_URL);
								
								MultipartEntityBuilder meb = MultipartEntityBuilder.create();
								meb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
								meb.addTextBody("timestamp", timeStamp.toString());
								meb.addTextBody("deviceKey", serial);
								meb.addPart("file", new FileBody(file));
								
								httpPost.setEntity(meb.build());
								
								HttpResponse response = httpClient.execute(httpPost);
							}catch(Exception e) {
								Log.e("cerberus", e.getMessage() + "");
								e.printStackTrace();
							}
							
							file.delete();
							
							return null;
						}
	
						
					}.execute();
					
					
					
					
				}
			});
	        
	        ProfilingCrawler.getInstance().start();
	}
	
	@Override
	   public void tearDown() {
		   
		try{
			Log.i("cerberusTest", "tearDown called...");
			Log.i("cerberusTest", "tearDown " + isCalledTearDown);
			
			if(isCalledTearDown) {
				Log.e("cerberus_test_runner", "was called... tearDown");
				return;
			}
			isCalledTearDown = true;
			Log.i("cerberusTest", "tearDown sendCheck" + CerberusAPI.send );
			   if(CerberusAPI.send == true) {
				   return;
			   }
			   CerberusAPI.send = true;
			   ProfilingCrawler.getInstance().stopThread();
			   
			   Gson gson = new Gson();
			   
			   Long finishDate = System.currentTimeMillis();
			   
		        solo.finishOpenedActivities();
		        Log.i("cerberusTest", "tearDown solo finishOpendActivities " );
	//	        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		        
		        Map resultData = new HashMap();
	
		        
		        List cpuList = new ArrayList();
		        
		        for(Object obj : CpuDataList.getInstance()) {
		        	Map map = (Map)obj;
		        	Map newMap = new HashMap();
		        	newMap.put("usage", map.get("usage"));
		        	newMap.put("timestamp", map.get("timestamp"));
		        	cpuList.add(newMap);
		        }
		        resultData.put("cpu_infos_attributes", CpuDataList.getInstance());
		        Log.i("cerberusTest", "tearDown make cpu_infos_attributes " );
		        List memoryList = new ArrayList();
		        for(Object obj : MemoryDataList.getInstance()) {
		        	Map memoryMap = (Map)obj;
		        	Map newMap = new HashMap();
		        	
		        	newMap.put("mem_total", memoryMap.get("mem_total"));
		        	newMap.put("dalvik_heap_alloc", memoryMap.get("dalvik_heap_alloc"));
		        	newMap.put("native_heap_size", memoryMap.get("native_heap_size"));
		        	newMap.put("dalvik_heap_size", memoryMap.get("dalvik_heap_size"));
		        	newMap.put("native_heap_alloc", memoryMap.get("native_heap_alloc"));
		        	newMap.put("mem_alloc", memoryMap.get("mem_alloc"));
		        	newMap.put("client_timestamp", memoryMap.get("client_timestamp"));
		        	memoryList.add(newMap);
		        }
		        resultData.put("memory_infos_attributes", memoryList);
		        Log.i("cerberusTest", "tearDown make memory_infos_attributes " );
		        List motionList = new ArrayList();
		        for(MotionVO motion : ((NetworkMotionStream)MotionCollector.getInstance().getStream()).getMotionList()) {
		        	
		        	Map motionMap = new HashMap();
		        	
		        	motionMap.put("activity_class", motion.getActivity_class());
		        	motionMap.put("param", motion.getParam());
		        	motionMap.put("view", motion.getView());
		        	motionMap.put("sleep", motion.getSleep());
		        	motionMap.put("action_type", motion.getAction_type());
		        	motionMap.put("client_timestamp", motion.getTime_stamp());
		        	
		        	motionList.add(motionMap);
		        }
		        
		        
		        resultData.put("battery_infos_attributes", BatteryDataList.getInstance());
		        Log.i("cerberusTest", "tearDown make battery_infos_attributes " );
		        resultData.put("network_infos_attributes", NetworkDataList.getInstance());
		        Log.i("cerberusTest", "tearDown make network_infos_attributes " );
		        Log.i("cerberusTest", "tearDown MethodTraceDumpList " + MethodTraceDumpList.getInstance() );
		        List<Map> cpuMethodProfilingList = new ArrayList<Map>();
		        for(Map originMethodData : MethodTraceDumpList.getInstance()) {
		        	Map changeMethodData = new HashMap();
		        	changeMethodData.put("tree_key", originMethodData.get("tree_key"));
		        	changeMethodData.put("parent_key", originMethodData.get("parent_key"));
		        	changeMethodData.put("class_name", originMethodData.get("class_name"));
		        	changeMethodData.put("method_name", originMethodData.get("method_name"));
		        	changeMethodData.put("start_timestamp", originMethodData.get("start_timestamp"));
		        	changeMethodData.put("end_timestamp", originMethodData.get("end_timestamp"));
		        	cpuMethodProfilingList.add(changeMethodData);
		        }
		        resultData.put("cpu_methods_attributes", cpuMethodProfilingList);
		        Log.i("cerberusTest", "tearDown make cpu_methods_attributes " );
		        resultData.put("frame_draw_times_attributes", FrameDrawTimeDumpList.getInstance());
		        Log.i("cerberusTest", "tearDown make frame_draw_times_attributes " );
		        resultData.put("motion_event_infos_attributes", motionList);
		        Log.i("cerberusTest", "tearDown make motion_event_infos_attributes " );
		        resultData.put("app_version", version);
	//	        resultData.put("test_datetime", startDate);
		        resultData.put("running_time", finishDate-startDate);
		        resultData.put("device_key", serial);
		        resultData.put("test_code_id", totalScenarioId);
		        resultData.put("total_report_id", totalReportId );
		        
		        
		        if(CrashDump.getInstance().keySet().size()!=0) {
		        	resultData.put("status", -1);
		        	
		        	Map crashDump = CrashDump.getInstance();
		        	crashDump.put("total_report_id", totalReportId);
		        	
		        	resultData.put("crash", crashDump);
		        } else {
		        	resultData.put("status", 1);
		        }
		        
		        
		        // if error then status value is -1
		        
		        
		        Log.i("cerberusTest", "result - " + gson.toJson(resultData));
		        
				String url = PROTOCAL + "://" + SERVER_IP + ":" + SERVER_PORT + "/testSvr/ResultProfilingServlet" ;
				HttpClient client = new DefaultHttpClient();
				ArrayList<NameValuePair> nameValuePairs = 
						new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("data", gson.toJson(resultData)));
	
	
				HttpPost httpPost = new HttpPost(url);
				UrlEncodedFormEntity entityRequest = 
						new UrlEncodedFormEntity(nameValuePairs, "EUC-KR");
				
				httpPost.setEntity(entityRequest);
				
				HttpResponse responsePost = client.execute(httpPost);
				HttpEntity resEntity = responsePost.getEntity();
				System.out.println(EntityUtils.toString(resEntity)); 
				
				System.out.println("finish...");
				Log.i("cerberusTest", "finish Test");
		}catch(Exception e) {
			Log.e("cerberusTest", e.getMessage() + "");
			e.printStackTrace();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	        
	        
	  }
	
	
	 public String takeScreenshot(final String filename) {

		    //hack -to ensure that the current view has been fully loaded

			   String result = "";
			   
			   
//		        View view = solo.getCurrentViews().get(solo.getCurrentViews().size()-1).getRootView();
		        View view = solo.getCurrentActivity().getWindow().getDecorView().getRootView();
		        
		                
		        view.setDrawingCacheEnabled(true);
		        view.buildDrawingCache();
		        Bitmap bitmap = view.getDrawingCache();

		        File directory = new File("/mnt/sdcard/Robotium-Screenshots/");
		        directory.mkdirs();

		        if (bitmap != null) {
		            try {
		                File outputFile = new File(directory, filename + ".jpg");
		                result = outputFile.getAbsolutePath();
		                FileOutputStream ostream = new FileOutputStream(outputFile);
		                bitmap.compress(CompressFormat.JPEG, 100, ostream);
		                ostream.close();
		            } catch (Exception e) {
		            }
		        }
		        
		        return result;
		    }

	 
	 public void afterSetup() {
		 
	 }
	 
	 
	    public View findView(ViewGroup viewgroup, int i)
	    {
	        for(int j = 0; j < viewgroup.getChildCount(); j++)
	        {
	            if(viewgroup.getChildAt(j).getId() == i)
	                return viewgroup.getChildAt(j);
	            if(!(viewgroup.getChildAt(j) instanceof ViewGroup))
	                continue;
	            View view = findView((ViewGroup)viewgroup.getChildAt(j), i);
	            if(view != null)
	                return view;
	        }

	        return null;
	    }
}
