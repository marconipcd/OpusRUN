package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class IpAddress {

	@MkMapping(from="id")
	private String id;
	@MkMapping(from="address")
	private String address;
	@MkMapping(from="comment")
	private String comment;
	@MkMapping(from="interface")
	private String interfaces;
	@MkMapping(from="network")
	private String network;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	
	
	
	
}
