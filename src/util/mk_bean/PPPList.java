package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class PPPList {

	@MkMapping(from="id")
	private String id;
	@MkMapping(from="name")
	private String name;
	@MkMapping(from="service")
	private String service;
	@MkMapping(from="caller-id")
	private String caller_id;
	@MkMapping(from="address")
	private String address;
	@MkMapping(from="uptime")
	private String uptime;
	@MkMapping(from="encoding")
	private String encoding;
	@MkMapping(from="session-id")
	private String session_id;
	@MkMapping(from="limit-bytes-in")
	private String limit_bytes_in;
	@MkMapping(from="limit-bytes-out")
	private String limit_bytes_out;
	@MkMapping(from="radius")
	private String radius;
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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getCaller_id() {
		return caller_id;
	}
	public void setCaller_id(String caller_id) {
		this.caller_id = caller_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getLimit_bytes_in() {
		return limit_bytes_in;
	}
	public void setLimit_bytes_in(String limit_bytes_in) {
		this.limit_bytes_in = limit_bytes_in;
	}
	public String getLimit_bytes_out() {
		return limit_bytes_out;
	}
	public void setLimit_bytes_out(String limit_bytes_out) {
		this.limit_bytes_out = limit_bytes_out;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	
	
	
}
