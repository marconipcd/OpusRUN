package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class Queues {
	
	@MkMapping(from="id")
	private String id;
	@MkMapping(from="name")
	private String name;
	@MkMapping(from="target")
	private String target;
	@MkMapping(from="parent")
	private String parent;
	@MkMapping(from="packet-marks")
	private String packet_marks;
	@MkMapping(from="priority")
	private String priority;	
	@MkMapping(from="queue")
	private String queue;
	@MkMapping(from="limit-at")
	private String limit_at;
	@MkMapping(from="max-limit")
	private String max_limit;
	@MkMapping(from="burst-limit")
	private String burst_limit;	
	@MkMapping(from="burst-thresh")
	private String burst_thresh;
	@MkMapping(from="burst-time")
	private String burst_time;	
	@MkMapping(from="invalid")
	private boolean invalid;	
	@MkMapping(from="dynamic")
	private boolean dynamic;
	@MkMapping(from="disabled")
	private boolean disabled;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getPacket_marks() {
		return packet_marks;
	}
	public void setPacket_marks(String packet_marks) {
		this.packet_marks = packet_marks;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getLimit_at() {
		return limit_at;
	}
	public void setLimit_at(String limit_at) {
		this.limit_at = limit_at;
	}
	public String getMax_limit() {
		return max_limit;
	}
	public void setMax_limit(String max_limit) {
		this.max_limit = max_limit;
	}
	public String getBurst_limit() {
		return burst_limit;
	}
	public void setBurst_limit(String burst_limit) {
		this.burst_limit = burst_limit;
	}
	public String getBurst_thresh() {
		return burst_thresh;
	}
	public void setBurst_thresh(String burst_thresh) {
		this.burst_thresh = burst_thresh;
	}
	public String getBurst_time() {
		return burst_time;
	}
	public void setBurst_time(String burst_time) {
		this.burst_time = burst_time;
	}
	public boolean isInvalid() {
		return invalid;
	}
	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}
	public boolean isDynamic() {
		return dynamic;
	}
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


}
