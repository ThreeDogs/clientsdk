package testbed.org.cerberus.index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DeviceManager extends Thread {

	private static final String adbPath = "/Users/nohsunghyun/dev/adt/sdk/platform-tools/adb";

	private static List<Map> deviceList = new ArrayList();

	public static List<Map> getDeviceList() {
		return deviceList;
	}

	public static void setDeviceList(List<Map> deviceList) {
		DeviceManager.deviceList = deviceList;
	}

	@Override
	public void run() {

		// if (args.length != 0 && args[0] != null) {
		// adbPath = args[0];
		// }

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print(">");
			String command = scanner.nextLine();

			doCommand(command);

		}

	}

	public static void doCommand(final String command) {
		String commandName = command.split(" ")[0];

		if (commandName.equals("add")) {

			String paramDeviceName = command.split(" ")[1];
			List<String> addDeviceList = new ArrayList<String>();
			if (paramDeviceName.equals("All")) {
				try {
					Runtime runtime = Runtime.getRuntime();
					Process process;
					String cmd = adbPath + " devices";
					process = runtime.exec(cmd);
					process.waitFor();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line;
					while ((line = br.readLine()) != null) {

						if (line.startsWith("List of devices attached") || line.length() ==0) {

						} else {
							addDeviceList.add(line.split("	")[0]);
							 System.out.println(line.split("	")[0]);
						}

					}
				} catch (Exception e) {

				}
			} else {
				addDeviceList.add(paramDeviceName);
			}

			System.out.println(addDeviceList);
			
			for (String deviceName : addDeviceList) {

				Map dataMap = new HashMap<String, String>();

				dataMap.put("deviceName", deviceName);

				// deviceList.add(deviceName);

				{
					try {
						Runtime runtime = Runtime.getRuntime();
						Process process;
						String cmd = adbPath + " -s " + deviceName
								+ " shell getprop ro.build.version.release";
						process = runtime.exec(cmd);
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line;
						while ((line = br.readLine()) != null) {
							// System.out.println(line);
							dataMap.put("os", line);
						}
					} catch (Exception e) {

					}
				}
				{
					try {
						Runtime runtime = Runtime.getRuntime();
						Process process;
						String cmd = adbPath + " -s " + deviceName
								+ " shell getprop ro.product.cpu.abi";
						process = runtime.exec(cmd);
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line;
						while ((line = br.readLine()) != null) {
							// System.out.println(line);
							dataMap.put("cpu", line);
						}
					} catch (Exception e) {
						
					}
				}
				{
					try {
						Runtime runtime = Runtime.getRuntime();
						Process process;
						String cmd = adbPath + " -s " + deviceName
								+ " shell getprop ro.product.brand";
						process = runtime.exec(cmd);
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line;
						while ((line = br.readLine()) != null) {
							// System.out.println(line);
							dataMap.put("brand", line);
						}
					} catch (Exception e) {
						
					}
				}
				{
					try {
						Runtime runtime = Runtime.getRuntime();
						Process process;
						String cmd = adbPath + " -s " + deviceName
								+ " shell getprop persist.sys.country";
						process = runtime.exec(cmd);
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line;
						while ((line = br.readLine()) != null) {
							// System.out.println(line);
							dataMap.put("country", line);
						}
					} catch (Exception e) {
						
					}
				}
				{
					try {
						Runtime runtime = Runtime.getRuntime();
						Process process;
						String cmd = adbPath
								+ " -s "
								+ deviceName
								+ " shell cat /system/build.prop | grep \"product\"";
						process = runtime.exec(cmd);
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line;
						while ((line = br.readLine()) != null) {
							// System.out.println(line);

							if (line.startsWith("ro.product.model")) {
								String model = line.replaceAll(
										"ro.product.model=", "");
								dataMap.put("model", model);
							}

						}
					} catch (Exception e) {

					}
				}

				boolean wasIs = false;
				for (Map dataMap2 : deviceList) {
					if (dataMap2.get("deviceName").equals(deviceName)) {
						System.out.println("device added - " + deviceName);
						wasIs = true;

					}
				}

				if (wasIs == false) {
					if (dataMap.get("os") != null) {
						deviceList.add(dataMap);
						System.out.println(dataMap + " add....");
					} else {
						System.out.println("device not found - " + deviceName);
					}
				}
			}
		} else if (commandName.equals("devices")) {
			try {
				Runtime runtime = Runtime.getRuntime();
				Process process;
				String cmd = adbPath + " devices";
				process = runtime.exec(cmd);
				process.waitFor();
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {

					if (line.startsWith("List of devices attached")) {

					} else {
						System.out.println(line.split("	")[0]);
					}

				}
			} catch (Exception e) {

			}
		} else if (commandName.equals("install")) {

			try {
				String apkFile = command.split(" ")[1];
				String deviceName = command.split(" ")[2];

				Runtime runtime = Runtime.getRuntime();
				Process process;
				String cmd = adbPath + " -s " + deviceName + " install -r "
						+ apkFile;
				System.out.println(cmd);
				process = runtime.exec(cmd);
				process.waitFor();
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}

			} catch (Exception e) {

			}
		} else if (commandName.startsWith("startTest")) {
			// ./adb shell am instrument -w -e class
			// org.cerberus.test.CerberusTestRunner#testRun
			// com.example.testandroid/android.test.InstrumentationTestRunner
			new Runnable() {

				@Override
				public void run() {
					System.out.println("--- start test");
					try {
						String deviceName = command.split(" ")[1];

						Runtime runtime = Runtime.getRuntime();
						Process process;
						String cmd = adbPath
								+ " -s "
								+ deviceName
								+ " shell am instrument -w -e class org.cerberus.test.CerberusTestRunner#testRun com.autoschedule.proto/android.test.InstrumentationTestRunner";
						System.out.println(cmd);
						process = runtime.exec(cmd);
						// process.waitFor();
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line;
						while ((line = br.readLine()) != null) {
							System.out.println(line);
						}

					} catch (Exception e) {

					}
				}
			}.run();
			System.out.println("-=-=- finish test");
		}
	}

	public static void installApk(String path, String deviceName) {
		doCommand("install " + path + " " + deviceName);
	}

	public static void startTest(String deviceName) {
		doCommand("startTest " + deviceName);
	}

	public static void allAddDevices() {
		doCommand("add All");
	}
	
	
	
}
