package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class FirewallFilter {

	@MkMapping(from="id")
	private String id;
	@MkMapping(from="chain")
	private String chain;
	@MkMapping(from="action")
	private String action;
	@MkMapping(from="protocol")
	private String protocol;
	@MkMapping(from="src-address-list")
	private String src_address_list;
	@MkMapping(from="content")
	private String content;
	@MkMapping(from="invalid")
	private String invalid;
	@MkMapping(from="dynamic")
	private String dynamic;
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
	public String getChain() {
		return chain;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getSrc_address_list() {
		return src_address_list;
	}
	public void setSrc_address_list(String src_address_list) {
		this.src_address_list = src_address_list;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
