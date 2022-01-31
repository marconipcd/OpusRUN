package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class Interface {

	@MkMapping(from="id")
	private String id;
	@MkMapping(from="name")
	private String name;
	@MkMapping(from="type")
	private String type;
	@MkMapping(from="mtu")
	private String mtu;
	@MkMapping(from="l2mtu")
	private String l2mtu;
	@MkMapping(from="dynamic")
	private String dynamic;
	@MkMapping(from="running")
	private String running;
	@MkMapping(from="disabled")
	private String disabled;
	@MkMapping(from="comment")
	private String comment;
	
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMtu() {
		return mtu;
	}
	public void setMtu(String mtu) {
		this.mtu = mtu;
	}
	public String getL2mtu() {
		return l2mtu;
	}
	public void setL2mtu(String l2mtu) {
		this.l2mtu = l2mtu;
	}
	public String getDynamic() {
		return dynamic;
	}
	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}
	public String getRunning() {
		return running;
	}
	public void setRunning(String running) {
		this.running = running;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
