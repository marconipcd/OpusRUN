package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class AddressList {

	@MkMapping(from="id")
	private String id;
	@MkMapping(from="list")
	private String list;
	@MkMapping(from="address")
	private String address;
	@MkMapping(from="dynamic")
	private String dynamic;
	@MkMapping(from="disabled")
	private String disabled;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDynamic() {
		return dynamic;
	}
	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
	

}
