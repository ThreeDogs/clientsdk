package export.scenario.cerberus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class JavaCodeMaker {

	private static JsonArray scenarioJsonArr = null;
	
	private static StringBuilder sb = new StringBuilder();
	
	
	/**
	 * 
	 * @param args
	 * [0] = mainActivity
	 * [1] = package Name
	 * [2] = scenario key
	 * [3] = file path
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String mainActivity = args[0];
//		String mainActivity = "com.example.test.MainActivity";
		String packageName = args[1];
//		String packageName = "com.example.test";
		String scenarioKey = args[2];
		String filePath = args[3];
		
		
		System.out.println(scenarioKey);
		
		//get scenario data
		String strJsonArr = getScenario(scenarioKey);
		
//		Scanner scanner = new Scanner( new FileInputStream( new File("scenario3.json") ));
//		StringBuilder temp=new StringBuilder();
//		while(scanner.hasNext()){
//			temp.append(scanner.next());
//		}
//		String strJsonArr = temp.toString();

//		System.out.println(strJsonArr);
		
		System.out.println(strJsonArr);
		
		//json parse
		Gson gson = new Gson();
		 JsonReader reader = new JsonReader( new StringReader(strJsonArr));
	        reader.setLenient(true);
	        
	    JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);
	        
	    
	    
		JsonArray scenarioJsonArr = jsonObj.getAsJsonArray("motion_event_infos");
		
		
		sb.append("import " + packageName + ".R;");
		sb.append("\n");
		sb.append("import java.io.*;");
		sb.append("\n");
		sb.append("import java.util.*;");
		sb.append("\n");
		sb.append("import com.robotium.solo.Solo;");
		sb.append("\n");
		sb.append("import android.test.ActivityInstrumentationTestCase2;");
		sb.append("\n");
		sb.append("import android.widget.*;");
		sb.append("\n");
		sb.append("import android.view.*;");
		sb.append("\n");
		sb.append("\n");
		// ------ class name
		sb.append("@SuppressWarnings(\"rawtypes\")\n");
		sb.append("public class CerberusTestRunner extends ActivityInstrumentationTestCase2 {");
		sb.append("\n");
		sb.append("\n");
		// ----- private static line
		sb.append("	private static final String LAUNCHER_ACTIVITY_CLASSNAME = \"" + mainActivity +  "\";");
		sb.append("\n");
		sb.append("	private static Class<?> launcherActivityClass;");
		sb.append("\n");
		sb.append("\n");
		//	-----	static line
		sb.append("	static{");
		sb.append("\n");
		sb.append("		try{");
		sb.append("\n");
		sb.append("			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_CLASSNAME);");
		sb.append("\n");
		sb.append("		}catch(ClassNotFoundException cnfException) {");
		sb.append("\n");
		sb.append("			throw new RuntimeException(cnfException);");
		sb.append("\n");
		sb.append("		}");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		
		sb.append("	Solo solo;");
		sb.append("\n");
		sb.append("\n");
		
		// ------ const line
		sb.append("	@SuppressWarnings(\"unchecked\")");
		sb.append("\n");
		sb.append("	public CerberusTestRunner() {");
		sb.append("\n");
		sb.append("		super( launcherActivityClass );");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		
		sb.append("	public void setUp() throws Exception {");
		sb.append("\n");
		sb.append("\n");
		sb.append("		solo = new Solo(getInstrumentation(), getActivity());");
		sb.append("\n");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		
		
		
		sb.append("	public void testRun() throws Exception {");
		sb.append("\n");
		
		for(int i = 0 ; i < scenarioJsonArr.size(); i++) {
			JsonObject scenarioObjJson = scenarioJsonArr.get(i).getAsJsonObject();
			
			// sleep 
			if(i == 0) {
			 }
			 else if( scenarioObjJson.get("sleep") != null || !scenarioObjJson.get("sleep").getAsString().equals("null")   ) {

//				 sb.append("		Thread.sleep(2000);");
				 sb.append("\n");
				 sb.append("		Thread.sleep(" + (scenarioObjJson.get("sleep").getAsInt() + 2000) + ");");
				 sb.append("\n");
			 }
			sb.append("\n");
			sb.append("		// -------" + (i+1));
			
			if( scenarioObjJson.get("action_type").getAsString().equals("Click") ) {
				sb.append("\n");
				sb.append("\n	{");
				sb.append("			View clickView = solo.getView( R.id." + scenarioObjJson.get("view").getAsString() + ");");
				sb.append("\n");
				sb.append("			if(clickView instanceof Button) {");
				sb.append("\n");
				sb.append("				solo.clickOnView( clickView );");
				sb.append("\n");
				sb.append("			}");
				sb.append("\n");
				sb.append("			else if(clickView instanceof TextView) {");
				sb.append("\n");
				sb.append("				solo.clickOnText(((TextView)clickView).getText().toString());");
				sb.append("\n");
				sb.append("			}");
				sb.append("\n");
				sb.append("			else {");
				sb.append("\n");
				sb.append("				solo.clickOnView( clickView );");
				sb.append("\n");
				sb.append("			}");
				sb.append("\n	}");
				sb.append("\n");
			}
			else if( scenarioObjJson.get("action_type").getAsString().equals("LongClick") ) {
				sb.append("\n");
				sb.append("		solo.clickLongOnView(solo.getView( R.id." + scenarioObjJson.get("view").getAsString() + " ));");
				sb.append("\n");
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("DatePicker")) {
				
				String param = scenarioObjJson.get("param").getAsString();
				sb.append("\n");
				sb.append("		solo.setDatePicker( 0 , " + Integer.parseInt(param.split(",")[0]) + " , " + Integer.parseInt( param.split(",")[1]) + " , " + Integer.parseInt(param.split(",")[2]) + " );");
				sb.append("\n");
				sb.append("		solo.sleep(1500);");
				sb.append("\n");
				sb.append("		solo.clickOnText(\"설정\");");
				sb.append("\n");
				
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("TimePicker")) {
				
				String param = scenarioObjJson.get("param").getAsString();
				
				sb.append("\n");
				sb.append("		solo.setTimePicker( 0 , " + Integer.parseInt(param.split(",")[0]) + " , " + Integer.parseInt( param.split(",")[1]) + " );");
				sb.append("\n");
				sb.append("		solo.clickOnText(\"설정\");");
				sb.append("\n");
				
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("EditText")) {
				
				String param = scenarioObjJson.get("param").getAsString();
				sb.append("\n");
				sb.append("		solo.enterText( (EditText) solo.getCurrentActivity().findViewById(R.id." + scenarioObjJson.get("view").getAsString() + "), \"" + param + "\");");
				sb.append("\n");
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("ListItemClick")) {
				String param = scenarioObjJson.get("param").getAsString();
				int index = Integer.parseInt( param.split(",")[0] );
				
				sb.append("\n");
				sb.append("		AdapterView listView = (AdapterView) solo.getView(listViewId);");
				sb.append("\n");
				sb.append("		View listElement = listView.getChildAt(" + index +"); ");
				sb.append("\n");
				sb.append("		 View btnView = null;");
				sb.append("\n");
				sb.append("		if(listElement instanceof ViewGroup) {");
				sb.append("\n");
				sb.append("			btnView = findView((ViewGroup) listElement, R.id." + scenarioObjJson.get("view").getAsString() + " );");
				sb.append("\n");
				sb.append("\n");
				sb.append("		}");
				sb.append("\n");
				sb.append("		solo.clickOnView(btnView);");
				sb.append("\n");
				sb.append("\n");
				
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("ListClick")) {
				String param = scenarioObjJson.get("param").getAsString();
				int index = Integer.parseInt( param.split(",")[0] );
				sb.append("\n");
				sb.append("		ListView listView = (ListView) solo.getView( R.id." + scenarioObjJson.get("view").getAsString() + ");");
				sb.append("\n");
				sb.append("		View listElement = listView.getChildAt("+index+");");
				sb.append("\n");
				sb.append("		solo.clickOnView(listElement);");
				sb.append("\n");
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("drag")) { 
				
				String param = scenarioObjJson.get("param").getAsString();
				int x1 = Integer.parseInt( param.split(",")[0] );
				int y1 = Integer.parseInt( param.split(",")[1] );
				int x2 = Integer.parseInt( param.split(",")[2] );
				int y2 = Integer.parseInt( param.split(",")[3] );
				sb.append("\n");
				sb.append("		solo.drag(" + x1 + "," + x2 + "," + y1 + "," + y2 + ", 40);");
				sb.append("\n");
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("BackButton")) {
				sb.append("		solo.goBack();");
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("MenuButton")) {
				sb.append("		solo.sendKey(Solo.MENU);");
			}
			else if(scenarioObjJson.get("action_type").getAsString().equals("AlertDialogClick")) {
				String param = scenarioObjJson.get("param").getAsString();
				sb.append("\n"); 
				if(param.equals("-1"))
				 {
					 sb.append("		solo.clickOnView( solo.getView(android.R.id.button1) );");
				 } else if(param.equals("-2")){
					 sb.append("		solo.clickOnView( solo.getView(android.R.id.button2) );");
				 } else if(param.equals("-3")){
					 sb.append("		solo.clickOnView( solo.getView(android.R.id.button3) );");
				 } 
				sb.append("\n");
			}	else if(scenarioObjJson.get("action_type").getAsString().equals("TextClick")) {
				 String param = scenarioObjJson.get("param").getAsString();
				 sb.append("\n");
				 sb.append("		solo.clickOnText(\"" + param + "\");");
				 
				 sb.append("\n");
			 }
			
		}
		
		
		
		
		sb.append("\n");
		sb.append("	}");
		
		sb.append("\n");
		sb.append("\n");
		
		
		//make tearDown method
		sb.append("	@Override");
		sb.append("\n");
		sb.append("	public void tearDown(){");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		
		
		//make function findView
		sb.append("	public View findView(ViewGroup v, int id){");
		sb.append("\n");
		sb.append("\n");
		sb.append("		for(int i = 0 ; i < v.getChildCount() ; i++) {");
		sb.append("\n");
		sb.append("			if(v.getChildAt(i).getId() == id) {");
		sb.append("\n");
		sb.append("				return v.getChildAt(i);");
		sb.append("\n");
		sb.append("			}");
		sb.append("\n");
		sb.append("\n");
		sb.append("			if(v.getChildAt(i) instanceof ViewGroup) {");
		sb.append("\n");
		sb.append("				View result = findView((ViewGroup) v.getChildAt(i), id);");
		sb.append("\n");
		sb.append("				if(result!= null) {");
		sb.append("\n");
		sb.append("					return result;");
		sb.append("\n");
		sb.append("				}");
		sb.append("\n");
		sb.append("			}");
		sb.append("\n");
		sb.append("		}");
		sb.append("\n");
		sb.append("\n");
		sb.append("		return null;");
		sb.append("\n");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("\n");
		
		
		sb.append("}");
//		System.out.println(sb.toString());
		
		FileOutputStream fos = new FileOutputStream(new File(filePath + "/CerberusTestRunner.java"));
		fos.write(sb.toString().getBytes());
		fos.flush();
		fos.close();
	}
	
	private static String getScenario(String scenarioKey) throws Exception {
		String url = "http://localhost:3000/api/v1/test_scenarios/" + scenarioKey +"/motion_event_list" ;
		System.out.println(url);
		HttpClient client = new DefaultHttpClient();
//		ArrayList<NameValuePair> nameValuePairs = 
//				new ArrayList<NameValuePair>();
//		nameValuePairs.add(new BasicNameValuePair("data", gson.toJson(resultData)));
		HttpGet httpGet = new HttpGet(url);
//		UrlEncodedFormEntity entityRequest = 
//				new UrlEncodedFormEntity(nameValuePairs, "EUC-KR");
//		httpGet.setEntity(entityRequest);
		
		HttpResponse responsePost = client.execute(httpGet);
		HttpEntity resEntity = responsePost.getEntity();
//		System.out.println(EntityUtils.toString(resEntity)); 
		String strJsonArr = EntityUtils.toString(resEntity);
		return strJsonArr;
	}
	
}
