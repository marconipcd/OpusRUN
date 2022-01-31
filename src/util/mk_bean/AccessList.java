package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class AccessList {

	@MkMapping(from="id")
	private String id;
	@MkMapping(from="mac-address")
	private String mac_address;
	@MkMapping(from="interface")
	private String interface_allow;
	@MkMapping(from="signal-range")
	private String signal_range;
	@MkMapping(from="authentication")
	private String authentication;
	@MkMapping(from="forwarding")
	private String forwarding;
	@MkMapping(from="ap-tx-limit")
	private String ap_tx_limit;
	@MkMapping(from="client-tx-limit")
	private String client_tx_limit;
	@MkMapping(from="private-algo")
	private String private_algo;
	@MkMapping(from="private-key")
	private String private_key;
	@MkMapping(from="private-pre-shared-key")	
	private String private_pre_shared_key;
	@MkMapping(from="management-protection-key")
	private String management_protection_key;
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
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public String getInterface_allow() {
		return interface_allow;
	}
	public void setInterface_allow(String interface_allow) {
		this.interface_allow = interface_allow;
	}
	public String getSignal_range() {
		return signal_range;
	}
	public void setSignal_range(String signal_range) {
		this.signal_range = signal_range;
	}
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public String getForwarding() {
		return forwarding;
	}
	public void setForwarding(String forwarding) {
		this.forwarding = forwarding;
	}
	public String getAp_tx_limit() {
		return ap_tx_limit;
	}
	public void setAp_tx_limit(String ap_tx_limit) {
		this.ap_tx_limit = ap_tx_limit;
	}
	public String getClient_tx_limit() {
		return client_tx_limit;
	}
	public void setClient_tx_limit(String client_tx_limit) {
		this.client_tx_limit = client_tx_limit;
	}
	public String getPrivate_algo() {
		return private_algo;
	}
	public void setPrivate_algo(String private_algo) {
		this.private_algo = private_algo;
	}
	public String getPrivate_key() {
		return private_key;
	}
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}
	public String getPrivate_pre_shared_key() {
		return private_pre_shared_key;
	}
	public void setPrivate_pre_shared_key(String private_pre_shared_key) {
		this.private_pre_shared_key = private_pre_shared_key;
	}
	public String getManagement_protection_key() {
		return management_protection_key;
	}
	public void setManagement_protection_key(String management_protection_key) {
		this.management_protection_key = management_protection_key;
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
