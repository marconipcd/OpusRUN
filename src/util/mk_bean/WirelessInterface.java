package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class WirelessInterface {

	@MkMapping(from="name")
	private String name;
	
	@MkMapping(from="mtu=1500")
	private String mtu; 
	
	@MkMapping(from="mac-address")
	private String mac_address;
	
	@MkMapping(from="arp") 		
	private String arp;
	
	@MkMapping(from="interface-type") 
	private String interface_type;
	
	@MkMapping(from="mode=ap") 
	private String mode;
	
	@MkMapping(from="frequency") 
	private String frequency;
	
	@MkMapping(from="band") 
	private String band;
	
	@MkMapping(from="channel-width") 
	private String channel_width;
	
	@MkMapping(from="scan-list") 
	private String scan_list;
		      
	@MkMapping(from="wireless-protocol") 
	private String wireless_protocol;
	
	@MkMapping(from="antenna-mode") 
	private String antenna_mode;
	
	@MkMapping(from="wds-mode") 
	private String wds_mode;
	
	@MkMapping(from="wds-default-bridge") 
	private String wds_default;
	
	@MkMapping(from="wds-ignore-ssid=no") 
	private String wds_ignore;
		      
	@MkMapping(from="bridge-mode=enabled") 
	private String bridge_mode;
		      
	@MkMapping(from="default-authentication")		    		  
	private String default_authentication;
		      
	@MkMapping(from="default-forwarding") 
	private String default_forwarding;
	
	@MkMapping(from="default-ap-tx-limit") 
	private String  default_ap_tx_limit;
		      
	@MkMapping(from="default-client-tx-limit") 
	private String default_cliente_tx_limit;
	
	@MkMapping(from="hide-ssid") 
	private String hide_ssid;
	
	@MkMapping(from="security-profile") 
	private String security_profile;
	
	@MkMapping(from="compression")
	private String compression;
	
	@MkMapping(from="ssid")
	private String ssid;
	
	public WirelessInterface(){
		
	}

	public WirelessInterface(String name, String mtu, String mac_address,
			String arp, String interface_type, String mode, String frequency,
			String band, String channel_width, String scan_list,
			String wireless_protocol, String antenna_mode, String wds_mode,
			String wds_default, String wds_ignore, String bridge_mode,
			String default_authentication, String default_forwarding,
			String default_ap_tx_limit, String default_cliente_tx_limit,
			String hide_ssid, String security_profile, String compression) {
		super();
		this.name = name;
		this.mtu = mtu;
		this.mac_address = mac_address;
		this.arp = arp;
		this.interface_type = interface_type;
		this.mode = mode;
		this.frequency = frequency;
		this.band = band;
		this.channel_width = channel_width;
		this.scan_list = scan_list;
		this.wireless_protocol = wireless_protocol;
		this.antenna_mode = antenna_mode;
		this.wds_mode = wds_mode;
		this.wds_default = wds_default;
		this.wds_ignore = wds_ignore;
		this.bridge_mode = bridge_mode;
		this.default_authentication = default_authentication;
		this.default_forwarding = default_forwarding;
		this.default_ap_tx_limit = default_ap_tx_limit;
		this.default_cliente_tx_limit = default_cliente_tx_limit;
		this.hide_ssid = hide_ssid;
		this.security_profile = security_profile;
		this.compression = compression;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMtu() {
		return mtu;
	}

	public void setMtu(String mtu) {
		this.mtu = mtu;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getArp() {
		return arp;
	}

	public void setArp(String arp) {
		this.arp = arp;
	}

	public String getInterface_type() {
		return interface_type;
	}

	public void setInterface_type(String interface_type) {
		this.interface_type = interface_type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getChannel_width() {
		return channel_width;
	}

	public void setChannel_width(String channel_width) {
		this.channel_width = channel_width;
	}

	public String getScan_list() {
		return scan_list;
	}

	public void setScan_list(String scan_list) {
		this.scan_list = scan_list;
	}

	public String getWireless_protocol() {
		return wireless_protocol;
	}

	public void setWireless_protocol(String wireless_protocol) {
		this.wireless_protocol = wireless_protocol;
	}

	public String getAntenna_mode() {
		return antenna_mode;
	}

	public void setAntenna_mode(String antenna_mode) {
		this.antenna_mode = antenna_mode;
	}

	public String getWds_mode() {
		return wds_mode;
	}

	public void setWds_mode(String wds_mode) {
		this.wds_mode = wds_mode;
	}

	public String getWds_default() {
		return wds_default;
	}

	public void setWds_default(String wds_default) {
		this.wds_default = wds_default;
	}

	public String getWds_ignore() {
		return wds_ignore;
	}

	public void setWds_ignore(String wds_ignore) {
		this.wds_ignore = wds_ignore;
	}

	public String getBridge_mode() {
		return bridge_mode;
	}

	public void setBridge_mode(String bridge_mode) {
		this.bridge_mode = bridge_mode;
	}

	public String getDefault_authentication() {
		return default_authentication;
	}

	public void setDefault_authentication(String default_authentication) {
		this.default_authentication = default_authentication;
	}

	public String getDefault_forwarding() {
		return default_forwarding;
	}

	public void setDefault_forwarding(String default_forwarding) {
		this.default_forwarding = default_forwarding;
	}

	public String getDefault_ap_tx_limit() {
		return default_ap_tx_limit;
	}

	public void setDefault_ap_tx_limit(String default_ap_tx_limit) {
		this.default_ap_tx_limit = default_ap_tx_limit;
	}

	public String getDefault_cliente_tx_limit() {
		return default_cliente_tx_limit;
	}

	public void setDefault_cliente_tx_limit(String default_cliente_tx_limit) {
		this.default_cliente_tx_limit = default_cliente_tx_limit;
	}

	public String getHide_ssid() {
		return hide_ssid;
	}

	public void setHide_ssid(String hide_ssid) {
		this.hide_ssid = hide_ssid;
	}

	public String getSecurity_profile() {
		return security_profile;
	}

	public void setSecurity_profile(String security_profile) {
		this.security_profile = security_profile;
	}

	public String getCompression() {
		return compression;
	}

	public void setCompression(String compression) {
		this.compression = compression;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	
}
