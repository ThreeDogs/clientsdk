package org.cerberus.scenario;

import java.io.Serializable;

public class MotionVO implements Serializable{

	private Integer seq_id;
	private Long time_stamp;
	private Long sleep;
	private String activity_class;
	private String action_type;
	private String param;
	private String view;
//	private String reportKey;
	
	public MotionVO(){ 
		
	}
	
	public MotionVO( Long time, String activityClass,
			String type, String param, String view) {
		this.time_stamp = time;
		this.activity_class = activityClass;
		this.action_type = type;
		this.param = param;
		this.view = view;
	}
//	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public Long getTime() {
//		return time_stamp;
//	}
//	public void setTime(Long time) {
//		this.time_stamp = time;
//	}
//	public Long getSleep() {
//		return sleep;
//	}
//	public void setSleep(Long sleep) {
//		this.sleep = sleep;
//	}
//	public String getActivityClass() {
//		return activity_class;
//	}
//	public void setActivityClass(String activityClass) {
//		this.activity_class = activityClass;
//	}
//	public String getType() {
//		return action_type;
//	}
//	public void setType(String type) {
//		this.action_type = type;
//	}
//	public String getParam() {
//		return param;
//	}
//	public void setParam(String param) {
//		this.param = param;
//	}
//	public String getView() {
//		return view;
//	}
//	public void setView(String view) {
//		this.view = view;
//	}
//	public String getReportKey() {
//		return reportKey;
//	}
//	public void setReportKey(String reportKey) {
//		this.reportKey = reportKey;
//	}

	@Override
	public String toString() {
		return "MotionVO [id=" + seq_id + ", time=" + time_stamp + ", sleep=" + sleep
				+ ", activityClass=" + activity_class + ", type=" + action_type
				+ ", param=" + param + ", view=" + view + "]";
	}

	public Integer getId() {
		return seq_id;
	}

	public void setId(Integer id) {
		this.seq_id = id;
	}

	public Long getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Long time_stamp) {
		this.time_stamp = time_stamp;
	}

	public Long getSleep() {
		return sleep;
	}

	public void setSleep(Long sleep) {
		this.sleep = sleep;
	}

	public String getActivity_class() {
		return activity_class;
	}

	public void setActivity_class(String activity_class) {
		this.activity_class = activity_class;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	
	
}
