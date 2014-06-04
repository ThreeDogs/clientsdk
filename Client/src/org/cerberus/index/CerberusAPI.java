package org.cerberus.index;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.cerberus.crawler.ProfilingCrawler;
import org.cerberus.event.collection.MotionCollector;
import org.cerberus.profile.cpu.CpuDataList;
import org.cerberus.profile.crash.CrashDump;
import org.cerberus.profile.memory.MemoryDataList;
import org.cerberus.scenario.MotionVO;
import org.cerberus.scenario.NetworkMotionStream;
import org.cerberus.service.AlwaysTopButtonService;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class CerberusAPI {

    private static final int STATUS_START = 1;
    private static final int STATUS_NONE = 0;
    private static int STATUS = 0;
    public static final int STATUS_RUNNING = 1;
    public static final int STATUS_FINISH = 0;
    public static Integer status_ = Integer.valueOf(0);
    private Context c;
    public static String apiKey;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    public static int index = 0x1000;
	public boolean isRecording;
	public static boolean isTestRunning = false; 

	public static String version;
	public static Long startDate;
	public static Integer totalReportId =1;
	public static String serial = "1.0"; 
	public static Integer totalScenarioId = 1;
	public static boolean send = false;
	
	public static  String SERVER_IP = "172.16.101.79";
	public static  Integer SERVER_PORT = 8080;
	
	public static  String LAUNCHER_ACTIVITY_CLASSNAME = "com.example.testandroid.MainActivity";
	public static  String PROTOCAL = "http";
	
	
	public CerberusAPI(Context c, String apiKey) {
		this.c = c;
		this.apiKey = apiKey;
		isRecording = true;
	}
	public CerberusAPI(Context c, String apiKey, boolean isRecording) {
		this.c = c;
		this.apiKey = apiKey;
		this.isRecording = isRecording;
	}
	
	public void start() {
		
		System.out.println("------------------------");
		
		mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e)
            {
                Log.e("cerberusTest", (new StringBuilder("error \n")).append(getStackTrace(e)).toString());
                
                if(e instanceof OutOfMemoryError) {
                	Log.e("cerberusTest", "OutOfMemory Errorr........");
                }
                
                if(e instanceof Error) {
                	Log.e("cerberusTest", "Crash.....");
                }
                
                StackTraceElement[] stackTraceElements = e.getStackTrace();
                
                Map throwableMap = new HashMap();
                
//                
//                throwableMap.put("error_name", stackTraceElements[0].getClassName());
//                throwableMap.put("error_line", stackTraceElements[0].getLineNumber());
//                throwableMap.put("description", e.getLocalizedMessage());
                
                CrashDump.getInstance().put("error_name", stackTraceElements[0].getClassName());
                CrashDump.getInstance().put("error_line", stackTraceElements[0].getLineNumber());
                CrashDump.getInstance().put("description", e.getLocalizedMessage());
                
                Log.i("cerberusTest", isTestRunning + "");
                Log.i("cerberusTest", CerberusAPI.send + "");
                
                if(isTestRunning && !CerberusAPI.send) {
                	Log.i("cerberusTest", "send cerberusApi...");
            	 CerberusAPI.send = true;
            	 
          		   ProfilingCrawler.getInstance().stopThread();
          		   
          		   final Gson gson = new Gson();
          		   
          		   Long finishDate = System.currentTimeMillis();
          		   
          	        final Map resultData = new HashMap();

          	        
          	        List cpuList = new ArrayList();
          	        
          	        for(Object obj : CpuDataList.getInstance()) {
          	        	Map map = (Map)obj;
          	        	Map newMap = new HashMap();
          	        	newMap.put("usage", map.get("usage"));
          	        	newMap.put("timestamp", map.get("timestamp"));
          	        	cpuList.add(newMap);
          	        }
          	        resultData.put("cpu_infos_attributes", CpuDataList.getInstance());
          	        
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
          	        
          	        resultData.put("motion_event_infos_attributes", motionList);
          	        resultData.put("app_version", version);
          	        resultData.put("test_datetime", startDate);
          	        resultData.put("running_time", finishDate-startDate);
          	        resultData.put("device_key", serial);
          	        resultData.put("test_scenario_id", totalScenarioId);
          	        resultData.put("total_report_id", totalReportId );
          	        
          	        
          	        if(CrashDump.getInstance().keySet().size()!=0) {
          	        	resultData.put("status", -1);
          	        	
          	        	Map crashDump = CrashDump.getInstance();
          	        	crashDump.put("total_report_id", totalReportId);
          	        	
          	        	resultData.put("crash", crashDump);
          	        } else {
          	        	resultData.put("status", 1);
          	        }
          	        
          	      Log.i("cerberusTest", resultData.toString());
          	        // if error then status value is -1
          	        
          	      new Runnable() {
					public void run() {
						try{
							 
						 	Log.i("cerberusTest", "sendNetwork...");
						 
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
	          	        
	          	        }catch(Exception errorNetwork) {
	          	        	
	          	        }
					}
				}.run();
          	      
          	        
          	       
                	
                	
                	
                	
                	
                }
                
                mUncaughtExceptionHandler.uncaughtException(t, e);
            }

            private String getStackTrace(Throwable e)
            {
                Writer result = new StringWriter();
                PrintWriter printWriter = new PrintWriter(result);
                for(Throwable cause = e; cause != null; cause = cause.getCause())
                    cause.printStackTrace(printWriter);

                String stackTraceAsString = result.toString();
                printWriter.close();
                return stackTraceAsString;
            }

        });
		
		if (isRecording) {
			c.startService(new Intent( c , AlwaysTopButtonService.class));
		}
	}
	
//	public void a(){
//		ProfilingCrawler.getInstance().start();
//	}
}
