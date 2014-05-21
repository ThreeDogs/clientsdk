package manifest_edit.cerberus.manifest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Main {

	public static void main(String[] args) throws DocumentException,
			IOException {
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

			boolean isSystemAlterWindow = false;
			boolean isInternet = false;
			boolean isWriteExternalStorage = false;
			for (String permission : permissionList) {
				if (permission.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
					isSystemAlterWindow = true;
				}
				if (permission.equals("android.permission.INTERNET")) {
					isInternet = true;
				}
				if (permission
						.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
					isWriteExternalStorage = true;
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
			
			// Pretty print the document to System.out
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(System.out, format);
			writer.write(document);
			// Compact format to System.out
			format = OutputFormat.createCompactFormat();
			writer = new XMLWriter(System.out, format);
			writer.write(document);
			
		}
	}

}
