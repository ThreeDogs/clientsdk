package manifest_edit.cerberus.manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Main {

	public static void main(String[] args) throws Exception {
//		/Users/nohsunghyun/SoftwareMaestro/cerberus-back/lib/test_apk_generator
//		/Users/nohsunghyun/SoftwareMaestro/cerberus-back/public/uploads/apk/apk/12/AutoScheduleProject/AndroidManifest.xml 
		String fileName = args[0];
		
		System.out.println(fileName);
		
//		int fromIndex = 0;
//		while((fileName.indexOf("/", fromIndex) != -1)) {
//			fromIndex = fileName.indexOf("/", fromIndex);
//			System.out.println(fromIndex);
//		}
//		fileName = fileName.substring(fromIndex);
		
		String filePath = args[1];

		{
			File file = new File(filePath + "/" + fileName
					+ "/AndroidManifest.xml");

			SAXReader reader = new SAXReader();
			Document document = reader.read(file);

			Element root = document.getRootElement();

			Document newDocument = DocumentHelper.createDocument();
			Element newRoot = newDocument.addElement("manifest");

//			xmlns:android="http://schemas.android.com/apk/res/android"
			root.addAttribute("xmlns:android", "http://schemas.android.com/apk/res/android");
			
			List<String> permissionList = new ArrayList();

			System.out.println(root.attributes());

			String packageName = null;

			for (Object attr : root.attributes()) {
				Attribute attribute = (Attribute) attr;
				System.out.println(attribute.getName() + " "
						+ attribute.getValue());

				newRoot.addAttribute(attribute.getName(), attribute.getValue());

				if (attribute.getName().equals("package")) {

					packageName = attribute.getValue();

				}

			}

			
			boolean isInstrumentation = false;
			Element application = null;
			// 루트 엘리먼트의 자식노드 반복
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				// do something

				// System.out.println(element.toString());
				// System.out.println(element.getName());
				if (element.getName().equals("uses-permission")) {
					permissionList.add(element.attributeValue("name"));
				} else if (element.getName().equals("instrumentation")) {
					isInstrumentation = true;
				}

				if (element.getName().equals("application")) {
					application = root.element("application");

					// <service
					// android:name="org.cerberus.service.AlwaysTopButtonService"
					// />
					Element alwaysTopService = application
							.addElement("service");
					alwaysTopService.addAttribute("android:name",
							"org.cerberus.service.AlwaysTopButtonService");

					// <service
					// android:name="edu.umich.PowerTutor.service.UMLoggerService"
					// />
					Element powerTutorLoggerService = application.addElement("service");
					powerTutorLoggerService.addAttribute("android:name", "edu.umich.PowerTutor.service.UMLoggerService");
					
					boolean isUsesLibrary = false;
					for (Iterator j = root.elementIterator("application"); j
							.hasNext();) {
						Element jElement = (Element) j.next();
						if (jElement.getName().equals("uses-library")) {
							isUsesLibrary = true;
						}
					}
					if (!isUsesLibrary) {
						// <uses-library android:name="android.test.runner"/>
						Element usesLibrary = application
								.addElement("uses-library");
						usesLibrary.addAttribute("android:name",
								"android.test.runner");

					}

				} else {
//					element.remove(element.attribute("xmlns:android") );
					newRoot.add((Element) element.clone());
				}

			}

			if (!isInstrumentation) {
				// <instrumentation
				// android:name="android.test.InstrumentationTestRunner"
				// android:targetPackage="com.example.testandroid" />
				Element instrumentation = newRoot.addElement("instrumentation");
				instrumentation.addAttribute("android:name",
						"android.test.InstrumentationTestRunner");
				instrumentation.addAttribute("android:targetPackage",
						packageName);
			}

			System.out.println("permission " + permissionList);

			// <uses-permission
			// android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
			// <uses-permission android:name="android.permission.INTERNET"/>
			// <uses-permission
			// android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

			
//		    <uses-permission android:name="android.permission.INTERNET" />
//		    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
//		    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
//		    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
//		    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
//		    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
			
			//check and append permission
			boolean isSystemAlterWindow = false;
			boolean isInternet = false;
			boolean isWriteExternalStorage = false;
			boolean isReceiveBootCompleted = false;
			boolean isAccessNetworkState = false;
			boolean isReadPhoneState = false;
			boolean isAccessWifiState = false;
			boolean isAccessFineLocation = false;
			
			for (String permission : permissionList) {
				if (permission.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
					isSystemAlterWindow = true;
				}
				else if (permission.equals("android.permission.INTERNET")) {
					isInternet = true;
				}
				else if (permission
						.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
					isWriteExternalStorage = true;
				}
				else if (permission
						.equals("android.permission.ACCESS_FINE_LOCATION")) {
					isAccessFineLocation = true;
				}
				else if (permission
						.equals("android.permission.ACCESS_WIFI_STATE")) {
					isAccessWifiState = true;
				}
				else if (permission
						.equals("android.permission.READ_PHONE_STATE")) {
					isReadPhoneState = true;
				}
				else if (permission
						.equals("android.permission.ACCESS_NETWORK_STATE")) {
					isAccessNetworkState = true;
				}
				else if (permission
						.equals("android.permission.RECEIVE_BOOT_COMPLETED")) {
					isReceiveBootCompleted = true;
				}
			}

			if (!isSystemAlterWindow) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.SYSTEM_ALERT_WINDOW");
			}
			if (!isInternet) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.INTERNET");
			}
			if (!isWriteExternalStorage) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.WRITE_EXTERNAL_STORAGE");
			}
			if (!isAccessFineLocation) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.ACCESS_FINE_LOCATION");
			}
			if (!isAccessWifiState) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.ACCESS_WIFI_STATE");
			}
			if (!isReadPhoneState) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.READ_PHONE_STATE");
			}
			if (!isAccessNetworkState) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.ACCESS_NETWORK_STATE");
			}
			if (!isReceiveBootCompleted) {
				Element nElement = newRoot.addElement("uses-permission");
				nElement.addAttribute("android:name",
						"android.permission.RECEIVE_BOOT_COMPLETED");
			}

			newRoot.add((Element) application.clone());
			XMLWriter writer = new XMLWriter(new FileWriter(file));
			writer.write(newDocument);
			writer.close();

			// Pretty print the document to System.out
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(System.out, format);
			writer.write(document);
			// Compact format to System.out
			format = OutputFormat.createCompactFormat();
			writer = new XMLWriter(System.out, format);
			writer.write(document);

		}
//		{
//			File file = new File(filePath + "/" + fileName
//					+ "/AndroidManifest.xml");
//			
//			SAXReader reader = new SAXReader();
//			Document document = reader.read(file);
//			
//			Element root = document.getRootElement();
//			
//			Document newDocument = DocumentHelper.createDocument();
//			Element newRoot = newDocument.addElement("manifest");
//			
//			
//			Element application = null;
//			// 루트 엘리먼트의 자식노드 반복
//			for (Iterator i = root.elementIterator(); i.hasNext();) {
//				Element element = (Element) i.next();
//				// do something
//				
//				
//				element.remove(element.attribute("xmlns:android") );
//				
//				newRoot.add((Element) element.clone());
//				
//				
//			}
//			
//			XMLWriter writer = new XMLWriter(new FileWriter(file));
//			writer.write(newDocument);
//			writer.close();
//			
//			// Pretty print the document to System.out
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			writer = new XMLWriter(System.out, format);
//			writer.write(document);
//			// Compact format to System.out
//			format = OutputFormat.createCompactFormat();
//			writer = new XMLWriter(System.out, format);
//			writer.write(document);
//			
//		}
		{
			File file = new File(filePath + "/" + fileName
					+ "/res/values/styles.xml");
			
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			
			Element root = document.getRootElement();
			
			Document newDocument = DocumentHelper.createDocument();
			Element newRoot = newDocument.addElement("resources");
			
			
			newRoot.addElement("style").addAttribute("name", "Widget.AppCompat.Base").addAttribute("parent", "");
			
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				// do something
				
				if (element.getName().equals("style")) {
					if(element.attribute("parent")==null) {
						element.addAttribute("parent", "");
					}
				}
				newRoot.add((Element) element.clone());
				
			}
			

			XMLWriter writer = new XMLWriter(new FileWriter(file));
			writer.write(newDocument);
			writer.close();
			
//			// Pretty print the document to System.out
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			writer = new XMLWriter(System.out, format);
//			writer.write(document);
//			// Compact format to System.out
//			format = OutputFormat.createCompactFormat();
//			writer = new XMLWriter(System.out, format);
//			writer.write(document);
			
			
			
			
			
			
			
			
			
			//-----------------------------
			
			
		}
		scanLayout(filePath + "/" + fileName
				+ "/res/layout/");
		
//		changeImageView("/Users/nohsunghyun/git/clientsdk1/Client/study/activity_task_new_amount.xml");
		
	}
	
	public static void scanLayout(String layoutPath) throws Exception {
		
		File directory = new File(layoutPath);
		
		
		for(String file :directory.list()) {
			changeImageView(layoutPath + "/" + file);
		}
		
	}
	
	public static void changeImageView(String filePath) throws Exception {
		{
			File file = new File(filePath);

			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8") );

			String s = null;
			StringBuilder sb = new StringBuilder();
			
			while((s=br.readLine())!=null) {
				sb.append(s);
			}
			
			br.close();
			
			String r = sb.toString();
			
			System.out.println(r);
			if(!(r.indexOf("<ImageView") >=0)) {
				return;
			}
			
			String result = r.replaceAll("<ImageView ", "<org.cerberus.profile.drawtime.CerberusImageView ");
			
			System.out.println(result);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			outputStreamWriter.write(result);
			outputStreamWriter.flush();
			outputStreamWriter.close();

		}
		
	}
	
}
