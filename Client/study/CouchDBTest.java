import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cerberus.event.collection.MotionCollector;
import org.cerberus.scenario.MotionVO;
import org.cerberus.scenario.NetworkMotionStream;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;

public class CouchDBTest {

	public static void main(String[] args) {

		CouchDBTest test = new CouchDBTest();

		test.makeData();
		
	}

	public void makeData() {
		for (int i = 0; i < 10; i++) {
			MotionVO motionData = new MotionVO(System.currentTimeMillis(),
					"TestActivity", "click", "", "R.id.button1");
			MotionCollector.getInstance().putMotion(motionData);
		}
		for (int deviceIndex = 0; deviceIndex < 5; deviceIndex++) {

			List cpuList = new ArrayList();
			for (int i = 0; i < 10; i++) {
				Map map = new HashMap();
//				map.put("detail_report_id", 1);
				map.put("id", i);
				map.put("usage", 3);
				cpuList.add(map);
			}

			List memoryList =  new ArrayList();

			for (int i = 0; i < 10; i++) {
				Map map = new HashMap();
//				map.put("detail_report_id", 1);
				map.put("id", i);
				map.put("mem_total", 0);
//				map.put("mem_free", 0);
				map.put("mem_alloc", 0);
//				map.put("native_heap_free", 0);
				map.put("native_heap_size", 0);
				map.put("native_heap_alloc", 0);
//				map.put("dalvik_heap_free", 0);
				map.put("dalvik_heap_size", 0);
				map.put("dalvik_heap_alloc", 0);
				memoryList.add(map);
			}

			

			Map detailReport = new HashMap();
			detailReport.put("memory_reports", memoryList);
			detailReport.put("cpu_reports", cpuList);
			detailReport.put("motion_reports",
					((NetworkMotionStream) MotionCollector.getInstance()
							.getStream()).getMotionList());
			detailReport.put("detail_report_id", deviceIndex);
			detailReport.put("app_version", "1.0");
			detailReport.put("status", "success");
			detailReport.put("running_time", "1:36");
			detailReport.put("test_scenario_id", "1");
			detailReport.put("device_id", "ocedxxse2134");
			detailReport.put("total_report_id", "1");
			detailReport.put("type", "Report");
			sendData(detailReport);
		}

		
	}

	public void sendData(Map obj) {

		Session s = new Session("172.16.101.163", 5984);
		Database db = s.getDatabase("cerberus");
		
		Document doc = new Document();// db.getDocument( obj.get("detail_report_id").toString() );
		obj.put("detail_report_id", db.getUpdateSeq()+1);
		doc.putAll(obj);
		
		db.saveDocument(doc);
		
	}

}
