package org.cerberus.scenario;

public class MotionVO {

	private Integer id;
	private Long time;
	private Long sleep;
	private String activityClass;
	private String type;
	private String param;
	private String view;
	private String reportKey;
	
	public MotionVO(){ 
		
	}
	
	public MotionVO( Long time, String activityClass,
			String type, String param, String view) {
		this.time = time;
		this.activityClass = activityClass;
		this.type = type;
		this.param = param;
		this.view = view;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Long getSleep() {
		return sleep;
	}
	public void setSleep(Long sleep) {
		this.sleep = sleep;
	}
	public String getActivityClass() {
		return activityClass;
	}
	public void setActivityClass(String activityClass) {
		this.activityClass = activityClass;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getReportKey() {
		return reportKey;
	}
	public void setReportKey(String reportKey) {
		this.reportKey = reportKey;
	}

	@Override
	public String toString() {
		return "MotionVO [id=" + id + ", time=" + time + ", sleep=" + sleep
				+ ", activityClass=" + activityClass + ", type=" + type
				+ ", param=" + param + ", view=" + view + ", reportKey="
				+ reportKey + "]";
	}
	
	
	
}
