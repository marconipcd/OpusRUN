package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class RegistrationTable {
	
	
	@MkMapping(from="id")
	private String id;
	@MkMapping(from="interface")
	private String interfaces;
	@MkMapping(from="radio")
	private String radio;
	@MkMapping(from="mac-address")
	private String mac_address;
	@MkMapping(from="ap")
	private String ap;
	@MkMapping(from="wds")
	private String wds;
	@MkMapping(from="bridge")
	private String bridge;
	@MkMapping(from="rx-rate")
	private String rx_rate;
	@MkMapping(from="tx-rate")
	private String tx_rate;
	@MkMapping(from="packets")
	private String packets;
	@MkMapping(from="bytes")
	private String bytes;
	@MkMapping(from="frames")
	private String frames;
	@MkMapping(from="frame-bytes")
	private String frame_bytes;
	@MkMapping(from="hw-frames")
	private String hw_frames;
	@MkMapping(from="hw-frame-bytes")
	private String hw_frame_bytes;
	@MkMapping(from="tx-frames-timed-out")
	private String tx_frames_timed_out;
	@MkMapping(from="uptime")
	private String uptime;
	@MkMapping(from="last-activity")
	private String last_activity;	
	@MkMapping(from="signal-strength")
	private String signal_strength;
	@MkMapping(from="signal-to-noise")
	private String signal_to_noise;
	@MkMapping(from="signal-strength-ch0")
	private String signal_strength_ch0;
	@MkMapping(from="strength-at-rates")
	private String strength_at_rates;
	@MkMapping(from="tx-signal-strength")
	private String tx_signal_strength;
	@MkMapping(from="tx-ccq")
	private String tx_ccq;
	@MkMapping(from="rx-ccq")
	private String rx_ccq;
	@MkMapping(from="p-throughput")
	private String p_throughput;
	@MkMapping(from="distance")
	private String distance;
	@MkMapping(from="nstreme")
	private String nstreme;
	@MkMapping(from="framing-mode")
	private String framing_mode;
	@MkMapping(from="routeros-version")
	private String routeros_version;
	@MkMapping(from="802.1x-port-enabled")
	private String port_enabled;
	@MkMapping(from="authentication-type")
	private String authentication_type;
	@MkMapping(from="encryption")
	private String encryption;
	@MkMapping(from="group-encryption")
	private String group_encryption;
	@MkMapping(from="management-protection")
	private String management_protection;
	@MkMapping(from="compression")
	private String compression;
	@MkMapping(from="wmm-enabled")
	private String wmm_enabled;
	@MkMapping(from="comment")
	private String comment;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
	}
	public String getWds() {
		return wds;
	}
	public void setWds(String wds) {
		this.wds = wds;
	}
	public String getBridge() {
		return bridge;
	}
	public void setBridge(String bridge) {
		this.bridge = bridge;
	}
	public String getRx_rate() {
		return rx_rate;
	}
	public void setRx_rate(String rx_rate) {
		this.rx_rate = rx_rate;
	}
	public String getTx_rate() {
		return tx_rate;
	}
	public void setTx_rate(String tx_rate) {
		this.tx_rate = tx_rate;
	}
	public String getPackets() {
		return packets;
	}
	public void setPackets(String packets) {
		this.packets = packets;
	}
	public String getBytes() {
		return bytes;
	}
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	public String getFrames() {
		return frames;
	}
	public void setFrames(String frames) {
		this.frames = frames;
	}
	public String getFrame_bytes() {
		return frame_bytes;
	}
	public void setFrame_bytes(String frame_bytes) {
		this.frame_bytes = frame_bytes;
	}
	public String getHw_frames() {
		return hw_frames;
	}
	public void setHw_frames(String hw_frames) {
		this.hw_frames = hw_frames;
	}
	public String getHw_frame_bytes() {
		return hw_frame_bytes;
	}
	public void setHw_frame_bytes(String hw_frame_bytes) {
		this.hw_frame_bytes = hw_frame_bytes;
	}
	public String getTx_frames_timed_out() {
		return tx_frames_timed_out;
	}
	public void setTx_frames_timed_out(String tx_frames_timed_out) {
		this.tx_frames_timed_out = tx_frames_timed_out;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getLast_activity() {
		return last_activity;
	}
	public void setLast_activity(String last_activity) {
		this.last_activity = last_activity;
	}
	public String getSignal_strength() {
		return signal_strength;
	}
	public void setSignal_strength(String signal_strength) {
		this.signal_strength = signal_strength;
	}
	public String getSignal_to_noise() {
		return signal_to_noise;
	}
	public void setSignal_to_noise(String signal_to_noise) {
		this.signal_to_noise = signal_to_noise;
	}
	public String getSignal_strength_ch0() {
		return signal_strength_ch0;
	}
	public void setSignal_strength_ch0(String signal_strength_ch0) {
		this.signal_strength_ch0 = signal_strength_ch0;
	}
	public String getStrength_at_rates() {
		return strength_at_rates;
	}
	public void setStrength_at_rates(String strength_at_rates) {
		this.strength_at_rates = strength_at_rates;
	}
	public String getTx_signal_strength() {
		return tx_signal_strength;
	}
	public void setTx_signal_strength(String tx_signal_strength) {
		this.tx_signal_strength = tx_signal_strength;
	}
	public String getTx_ccq() {
		return tx_ccq;
	}
	public void setTx_ccq(String tx_ccq) {
		this.tx_ccq = tx_ccq;
	}
	public String getRx_ccq() {
		return rx_ccq;
	}
	public void setRx_ccq(String rx_ccq) {
		this.rx_ccq = rx_ccq;
	}
	public String getP_throughput() {
		return p_throughput;
	}
	public void setP_throughput(String p_throughput) {
		this.p_throughput = p_throughput;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getNstreme() {
		return nstreme;
	}
	public void setNstreme(String nstreme) {
		this.nstreme = nstreme;
	}
	public String getFraming_mode() {
		return framing_mode;
	}
	public void setFraming_mode(String framing_mode) {
		this.framing_mode = framing_mode;
	}
	public String getRouteros_version() {
		return routeros_version;
	}
	public void setRouteros_version(String routeros_version) {
		this.routeros_version = routeros_version;
	}
	public String getPort_enabled() {
		return port_enabled;
	}
	public void setPort_enabled(String port_enabled) {
		this.port_enabled = port_enabled;
	}
	public String getAuthentication_type() {
		return authentication_type;
	}
	public void setAuthentication_type(String authentication_type) {
		this.authentication_type = authentication_type;
	}
	public String getEncryption() {
		return encryption;
	}
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}
	public String getGroup_encryption() {
		return group_encryption;
	}
	public void setGroup_encryption(String group_encryption) {
		this.group_encryption = group_encryption;
	}
	public String getManagement_protection() {
		return management_protection;
	}
	public void setManagement_protection(String management_protection) {
		this.management_protection = management_protection;
	}
	public String getCompression() {
		return compression;
	}
	public void setCompression(String compression) {
		this.compression = compression;
	}
	public String getWmm_enabled() {
		return wmm_enabled;
	}
	public void setWmm_enabled(String wmm_enabled) {
		this.wmm_enabled = wmm_enabled;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	

	
}
