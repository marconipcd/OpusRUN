package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class FirewallNat {

	@MkMapping(from="id")
	private String id;	
	@MkMapping(from="action")
	private String action;
	@MkMapping(from="address-list")
	private String address_list;
	@MkMapping(from="address-list-timeout")
	private String address_list_timeout;
	@MkMapping(from="comment")
	private String comment;
	@MkMapping(from="connection-bytes")
	private String connection_bytes;
	@MkMapping(from="connection-mark")
	private String connection_mark;
	@MkMapping(from="connection-type")
	private String connection_type;
	@MkMapping(from="connection-limit")
	private String connection_limit;
	@MkMapping(from="connection-rate")
	private String connection_rate;
	@MkMapping(from="content")
	private String content;
	@MkMapping(from="copy-from")
	private String copy_from;
	@MkMapping(from="disabled")
	private String disabled;
	@MkMapping(from="dscp")
	private String dscp;
	@MkMapping(from="dst-address")
	private String dst_address;
	@MkMapping(from="dst-address-list")
	private String dst_address_list;
	@MkMapping(from="dst-address-type")
	private String dst_address_type;
	@MkMapping(from="dst-limit")
	private String dst_limit;
	@MkMapping(from="dst-port")
	private String dst_port;
	@MkMapping(from="fragment")
	private String fragment;
	@MkMapping(from="hotspot")
	private String hotspot;
	@MkMapping(from="icmp-options")
	private String icmp_options;
	@MkMapping(from="in-bridge-port")
	private String in_bridge_port;
	@MkMapping(from="in-interface")
	private String in_interface;
	@MkMapping(from="ingress-priority")
	private String ingress_priority;
	@MkMapping(from="ipv4-options")
	private String ipv4_options;
	@MkMapping(from="jump-target")
	private String jump_target;
	@MkMapping(from="layer7-protocol")
	private String layer7_protocol;
	@MkMapping(from="limit")
	private String limit;
	@MkMapping(from="log-prefix")
	private String log_prefix;
	@MkMapping(from="nth")
	private String nth;
	@MkMapping(from="out-bridge-port")
	private String out_bridge_port;
	@MkMapping(from="out-interface")
	private String out_interface;
	@MkMapping(from="packet-mark")
	private String packet_mark;
	@MkMapping(from="packet-size")
	private String packet_size;
	@MkMapping(from="per-connection-classifier")
	private String per_connection_classifier;
	@MkMapping(from="place-before")
	private String place_before;
	@MkMapping(from="port")
	private String port;
	@MkMapping(from="priority")
	private String priority;
	@MkMapping(from="protocol")
	private String protocol;
	@MkMapping(from="psd")
	private String psd;
	@MkMapping(from="randon")
	private String randon;
	@MkMapping(from="routing-mark")
	private String routing_mark;
	@MkMapping(from="routing-table")
	private String routing_table;
	@MkMapping(from="same-not-by-dst")
	private String same_not_by_dst;
	@MkMapping(from="src-address")
	private String src_address;
	@MkMapping(from="src-address-list")
	private String src_address_list;
	@MkMapping(from="src-address-type")
	private String src_address_type;
	@MkMapping(from="src-mac-address")
	private String src_mac_address;
	@MkMapping(from="src-port")
	private String src_port;
	@MkMapping(from="tcp-mss")
	private String tcp_mss;
	@MkMapping(from="time")
	private String time;
	@MkMapping(from="to-address")
	private String to_addresses;
	@MkMapping(from="to-ports")
	private String to_ports;
	@MkMapping(from="ttl")
	private String ttl;
	@MkMapping(from="chain")
	private String chain;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAddress_list() {
		return address_list;
	}
	public void setAddress_list(String address_list) {
		this.address_list = address_list;
	}
	public String getAddress_list_timeout() {
		return address_list_timeout;
	}
	public void setAddress_list_timeout(String address_list_timeout) {
		this.address_list_timeout = address_list_timeout;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getConnection_bytes() {
		return connection_bytes;
	}
	public void setConnection_bytes(String connection_bytes) {
		this.connection_bytes = connection_bytes;
	}
	public String getConnection_mark() {
		return connection_mark;
	}
	public void setConnection_mark(String connection_mark) {
		this.connection_mark = connection_mark;
	}
	public String getConnection_type() {
		return connection_type;
	}
	public void setConnection_type(String connection_type) {
		this.connection_type = connection_type;
	}
	public String getConnection_limit() {
		return connection_limit;
	}
	public void setConnection_limit(String connection_limit) {
		this.connection_limit = connection_limit;
	}
	public String getConnection_rate() {
		return connection_rate;
	}
	public void setConnection_rate(String connection_rate) {
		this.connection_rate = connection_rate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCopy_from() {
		return copy_from;
	}
	public void setCopy_from(String copy_from) {
		this.copy_from = copy_from;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getDscp() {
		return dscp;
	}
	public void setDscp(String dscp) {
		this.dscp = dscp;
	}
	public String getDst_address() {
		return dst_address;
	}
	public void setDst_address(String dst_address) {
		this.dst_address = dst_address;
	}
	public String getDst_address_list() {
		return dst_address_list;
	}
	public void setDst_address_list(String dst_address_list) {
		this.dst_address_list = dst_address_list;
	}
	public String getDst_address_type() {
		return dst_address_type;
	}
	public void setDst_address_type(String dst_address_type) {
		this.dst_address_type = dst_address_type;
	}
	public String getDst_limit() {
		return dst_limit;
	}
	public void setDst_limit(String dst_limit) {
		this.dst_limit = dst_limit;
	}
	public String getDst_port() {
		return dst_port;
	}
	public void setDst_port(String dst_port) {
		this.dst_port = dst_port;
	}
	public String getFragment() {
		return fragment;
	}
	public void setFragment(String fragment) {
		this.fragment = fragment;
	}
	public String getHotspot() {
		return hotspot;
	}
	public void setHotspot(String hotspot) {
		this.hotspot = hotspot;
	}
	public String getIcmp_options() {
		return icmp_options;
	}
	public void setIcmp_options(String icmp_options) {
		this.icmp_options = icmp_options;
	}
	public String getIn_bridge_port() {
		return in_bridge_port;
	}
	public void setIn_bridge_port(String in_bridge_port) {
		this.in_bridge_port = in_bridge_port;
	}
	public String getIn_interface() {
		return in_interface;
	}
	public void setIn_interface(String in_interface) {
		this.in_interface = in_interface;
	}
	public String getIngress_priority() {
		return ingress_priority;
	}
	public void setIngress_priority(String ingress_priority) {
		this.ingress_priority = ingress_priority;
	}
	public String getIpv4_options() {
		return ipv4_options;
	}
	public void setIpv4_options(String ipv4_options) {
		this.ipv4_options = ipv4_options;
	}
	public String getJump_target() {
		return jump_target;
	}
	public void setJump_target(String jump_target) {
		this.jump_target = jump_target;
	}
	public String getLayer7_protocol() {
		return layer7_protocol;
	}
	public void setLayer7_protocol(String layer7_protocol) {
		this.layer7_protocol = layer7_protocol;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getLog_prefix() {
		return log_prefix;
	}
	public void setLog_prefix(String log_prefix) {
		this.log_prefix = log_prefix;
	}
	public String getNth() {
		return nth;
	}
	public void setNth(String nth) {
		this.nth = nth;
	}
	public String getOut_bridge_port() {
		return out_bridge_port;
	}
	public void setOut_bridge_port(String out_bridge_port) {
		this.out_bridge_port = out_bridge_port;
	}
	public String getOut_interface() {
		return out_interface;
	}
	public void setOut_interface(String out_interface) {
		this.out_interface = out_interface;
	}
	public String getPacket_mark() {
		return packet_mark;
	}
	public void setPacket_mark(String packet_mark) {
		this.packet_mark = packet_mark;
	}
	public String getPacket_size() {
		return packet_size;
	}
	public void setPacket_size(String packet_size) {
		this.packet_size = packet_size;
	}
	public String getPer_connection_classifier() {
		return per_connection_classifier;
	}
	public void setPer_connection_classifier(String per_connection_classifier) {
		this.per_connection_classifier = per_connection_classifier;
	}
	public String getPlace_before() {
		return place_before;
	}
	public void setPlace_before(String place_before) {
		this.place_before = place_before;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getRandon() {
		return randon;
	}
	public void setRandon(String randon) {
		this.randon = randon;
	}
	public String getRouting_mark() {
		return routing_mark;
	}
	public void setRouting_mark(String routing_mark) {
		this.routing_mark = routing_mark;
	}
	public String getRouting_table() {
		return routing_table;
	}
	public void setRouting_table(String routing_table) {
		this.routing_table = routing_table;
	}
	public String getSame_not_by_dst() {
		return same_not_by_dst;
	}
	public void setSame_not_by_dst(String same_not_by_dst) {
		this.same_not_by_dst = same_not_by_dst;
	}
	public String getSrc_address() {
		return src_address;
	}
	public void setSrc_address(String src_address) {
		this.src_address = src_address;
	}
	public String getSrc_address_list() {
		return src_address_list;
	}
	public void setSrc_address_list(String src_address_list) {
		this.src_address_list = src_address_list;
	}
	public String getSrc_address_type() {
		return src_address_type;
	}
	public void setSrc_address_type(String src_address_type) {
		this.src_address_type = src_address_type;
	}
	public String getSrc_mac_address() {
		return src_mac_address;
	}
	public void setSrc_mac_address(String src_mac_address) {
		this.src_mac_address = src_mac_address;
	}
	public String getSrc_port() {
		return src_port;
	}
	public void setSrc_port(String src_port) {
		this.src_port = src_port;
	}
	public String getTcp_mss() {
		return tcp_mss;
	}
	public void setTcp_mss(String tcp_mss) {
		this.tcp_mss = tcp_mss;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTo_addresses() {
		return to_addresses;
	}
	public void setTo_addresses(String to_addresses) {
		this.to_addresses = to_addresses;
	}
	public String getTo_ports() {
		return to_ports;
	}
	public void setTo_ports(String to_ports) {
		this.to_ports = to_ports;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public String getChain() {
		return chain;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
