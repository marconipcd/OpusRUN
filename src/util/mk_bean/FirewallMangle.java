package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class FirewallMangle {

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
	@MkMapping(from="connection-limit")
	private String connection_limit;
	@MkMapping(from="connection-mark")
	private String connection_mark;
	@MkMapping(from="connection-rate")
	private String connection_rate;
	@MkMapping(from="connection-state")
	private String connection_state;
	@MkMapping(from="connection-type")
	private String connection_type;
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
	@MkMapping(from="limit")
	private String limit;
	@MkMapping(from="log-prefix")
	private String log_prefix;
	@MkMapping(from="new-connection-mark")
	private String new_connection_mark;
	@MkMapping(from="new-dscp")
	private String new_dscp;
	@MkMapping(from="new-mss")
	private String new_mss;
	@MkMapping(from="new-packet-mark")
	private String new_packet_mark;
	@MkMapping(from="new-priority")
	private String new_priority;
	@MkMapping(from="new-routing-mark")
	private String new_routing_mark;
	@MkMapping(from="new-ttl")
	private String new_ttl;
	@MkMapping(from="nth")
	private String nth;
	@MkMapping(from="out-bridge-port")
	private String out_bridge_port;
	@MkMapping(from="out-interface")
	private String out_interface;
	@MkMapping(from="p2p")
	private String p2p;
	@MkMapping(from="packet-mark")
	private String packet_mark;
	@MkMapping(from="packet-size")
	private String packet_size;
	@MkMapping(from="passthrough")
	private String passthrough;
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
	@MkMapping(from="random")
	private String random;
	@MkMapping(from="routing-mark")
	private String routing_mark;
	@MkMapping(from="routing-table")
	private String routing_table;
	@MkMapping(from="sniff-id")
	private String sniff_id;
	@MkMapping(from="sniff-target")
	private String sniff_target;
	@MkMapping(from="sniff-target-port")
	private String sniff_target_port;
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
	@MkMapping(from="tcp-flags")
	private String tcp_flags;
	@MkMapping(from="tcp-mss")
	private String tcp_mss;
	@MkMapping(from="time")
	private String time;
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
	public String getConnection_limit() {
		return connection_limit;
	}
	public void setConnection_limit(String connection_limit) {
		this.connection_limit = connection_limit;
	}
	public String getConnection_mark() {
		return connection_mark;
	}
	public void setConnection_mark(String connection_mark) {
		this.connection_mark = connection_mark;
	}
	public String getConnection_rate() {
		return connection_rate;
	}
	public void setConnection_rate(String connection_rate) {
		this.connection_rate = connection_rate;
	}
	public String getConnection_state() {
		return connection_state;
	}
	public void setConnection_state(String connection_state) {
		this.connection_state = connection_state;
	}
	public String getConnection_type() {
		return connection_type;
	}
	public void setConnection_type(String connection_type) {
		this.connection_type = connection_type;
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
	public String getNew_connection_mark() {
		return new_connection_mark;
	}
	public void setNew_connection_mark(String new_connection_mark) {
		this.new_connection_mark = new_connection_mark;
	}
	public String getNew_dscp() {
		return new_dscp;
	}
	public void setNew_dscp(String new_dscp) {
		this.new_dscp = new_dscp;
	}
	public String getNew_mss() {
		return new_mss;
	}
	public void setNew_mss(String new_mss) {
		this.new_mss = new_mss;
	}
	public String getNew_packet_mark() {
		return new_packet_mark;
	}
	public void setNew_packet_mark(String new_packet_mark) {
		this.new_packet_mark = new_packet_mark;
	}
	public String getNew_priority() {
		return new_priority;
	}
	public void setNew_priority(String new_priority) {
		this.new_priority = new_priority;
	}
	public String getNew_routing_mark() {
		return new_routing_mark;
	}
	public void setNew_routing_mark(String new_routing_mark) {
		this.new_routing_mark = new_routing_mark;
	}
	public String getNew_ttl() {
		return new_ttl;
	}
	public void setNew_ttl(String new_ttl) {
		this.new_ttl = new_ttl;
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
	public String getP2p() {
		return p2p;
	}
	public void setP2p(String p2p) {
		this.p2p = p2p;
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
	public String getPassthrough() {
		return passthrough;
	}
	public void setPassthrough(String passthrough) {
		this.passthrough = passthrough;
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
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
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
	public String getSniff_id() {
		return sniff_id;
	}
	public void setSniff_id(String sniff_id) {
		this.sniff_id = sniff_id;
	}
	public String getSniff_target() {
		return sniff_target;
	}
	public void setSniff_target(String sniff_target) {
		this.sniff_target = sniff_target;
	}
	public String getSniff_target_port() {
		return sniff_target_port;
	}
	public void setSniff_target_port(String sniff_target_port) {
		this.sniff_target_port = sniff_target_port;
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
	public String getTcp_flags() {
		return tcp_flags;
	}
	public void setTcp_flags(String tcp_flags) {
		this.tcp_flags = tcp_flags;
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
