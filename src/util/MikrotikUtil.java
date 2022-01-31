package util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.legrange.mikrotik.ApiConnection;
import util.m4j.Mikrotik;
import util.mk_bean.WirelessInterface;
import br.com.eits.m4j.bean.MkCommand;
import br.com.eits.m4j.utils.MkParser;


public class MikrotikUtil {

	
	static Mikrotik router;
	
	private static ApiConnection con;
	
	private static void connect(String user, String pass, String host) throws Exception {
		
        con = ApiConnection.connect(host);
        con.login(user, pass);
    }
//
    private static void disconnect() throws Exception {
       // con.disconnect();  // 10498 10193 27230 005186 70000 000797 3 67100000036228
    }
//	
//    public static String getRemoteIpPPOE(String ip, String usuario, String senha, String username){
//		
//    	try{
//	    	connect(usuario, senha, ip);		
//			//List<Map<String, String>> list = con.execute("/ppp/active/print where name="+username);
//			disconnect();	
//			
//			if(list.size() == 1){
//				return ((Map<String, String>) list.get(0)).get("address");
//			}
//			return null;
//    	}catch(Exception e){
//    		////e.printStackTrace();
//    		return null;
//    	}
//	}
//    public static List<Map<String, String>> getUsuariosConectados(String ip, String usuario, String senha){
//		
//    	try{
//	    	connect(usuario, senha, ip);		
//			List<Map<String, String>> list = con.execute("/ppp/active/print");
//			disconnect();	
//			
//			return list;			
//    	}catch(Exception e){
//    		//e.printStackTrace();
//    		return null;
//    	}
//	}
//    
//	
//    public static List<Map<String, String>> getInfoOspfNetwork(String usuario, String senha,String ip, Integer porta){
//		
//		
//		try {
//			StringBuilder sR = new StringBuilder();
//			
//			connect(usuario, senha, ip);		
//			List<Map<String, String>> list = con.execute("/routing/ospf/network/print");			
//			disconnect();		
//			
//			return list;
//		} catch (Exception e) {			
//			//e.printStackTrace();
//			return null;
//		}
//	}
//    public static List<Map<String, String>> getInfoPool(String usuario, String senha,String ip, Integer porta){
//		
//		
//		try {
//			StringBuilder sR = new StringBuilder();
//			
//			connect(usuario, senha, ip);		
//			List<Map<String, String>> list = con.execute("/ip/pool/print");			
//			disconnect();		
//			
//			return list;
//		} catch (Exception e) {			
//			//e.printStackTrace();
//			return null;
//		}
//	}
//	
//	
	public static boolean desconectarCliente(String usuario, String senha,String ip, Integer porta, String username){
	
		boolean ppoe = derrubarConexaoPPPOE(usuario, senha, ip, porta, username);
		boolean hotspot = derrubarConexaoHOTSPOT(usuario, senha, ip, porta, username);
		
		if(!ppoe && !hotspot){
			return false;
		}else{
			return true;
		}
		
	}
//	
	public static boolean derrubarConexaoHOTSPOT(String usuario, String senha,String ip, Integer porta, String username){
		try{
			
			connect(usuario, senha, ip);	
			
			List<Map<String, String>> list = con.execute("/ip/hotspot/active/print where user="+username);
			
			for (Map<String, String> map : list) {				
				String id = map.get(".id");
				con.execute("/ip/hotspot/active/remove numbers="+id);				
			}
			
			disconnect();	
						
			if(list.size() > 0)
			{
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
//	
//	
//		
	public static boolean derrubarConexaoPPPOE(String usuario, String senha,String ip, Integer porta, String username){
		try{
			
			connect(usuario, senha, ip);	
			
			List<Map<String, String>> list = con.execute("/ppp/active/print where name="+username);
			
			for (Map<String, String> map : list) {				
				String id = map.get(".id");
				con.execute("/ppp/active/remove numbers="+id);				
			}
			
			disconnect();		
		
			if(list.size() > 0){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			
			//e.printStackTrace();
			return false;
			
		}
	}
//	
//	public static boolean buscarStatusConexao(String usuario, String senha,String ip, Integer porta, String username){
//		try{
//			
//			connect(usuario, senha, ip);	
//			
//			List<Map<String, String>> list = null;
//			
//			try{
//				list = con.execute("/ppp/active/print where name="+username);
//			}catch(Exception e){
//				System.out.println("Não foi Possivel buscar informações PPPOE");
//			}
//			List<Map<String, String>> list2 = null;
//					
//			try{
//					list2 = con.execute("/ip/hotspot/active/print where user="+username);
//			}catch(Exception e){
//					System.out.println("Não foi Possivel buscar informações HotSpot");
//			}
//			
//			disconnect();	
//					
//			if(list != null && list.size() > 0 || list2 != null && list2.size() > 0)
//			{
//				return true;
//			}else{
//				return false;
//			}
//			
//		}catch(Exception e){
//			
//			//e.printStackTrace();
//			return false;
//			
//		}
//	}
//	
//	public static boolean buscarComunicacao(String usuario, String senha,String ip, Integer porta, String  macAddress){
//		try{
//			
//			connect(usuario, senha, ip);	
//			
//			List<Map<String, String>> list = con.execute("/interface/wireless/registration-table/print where mac-address="+macAddress);
//						
//			disconnect();	
//				
//			if(list.size() > 0 )
//			{
//				return true;
//			}else{
//				return false;
//			}
//			
//		}catch(Exception e){
//			
//			////e.printStackTrace();
//			System.out.println("Não Foi Possivel Localizar Informações de Wireless");
//			return false;			
//		}
//	}
//	
//	public static Map<String, String> buscarRegistationTable(String usuario, String senha,String ip, Integer porta, String  macAddress){
//		try{
//			
//			connect(usuario, senha, ip);	
//			
//			List<Map<String, String>> list = con.execute("/interface/wireless/registration-table/print where mac-address="+macAddress);
//						
//			disconnect();	
//			
//			
//			if(list.size() > 0 )
//			{
//				return list.get(0);
//			}else{
//				return null;
//			}
//			
//		}catch(Exception e){
//			
//			System.out.println("Não Foi Possivel Localizar Informações de Registration Table");
//			return null;
//			
//		}
//	}
//	
//		
//		
//	private static String getComment(String codContrato, String nomeCliente){
//		return "CONTRATO:"+codContrato;		
//	}
//	
//			
//	public static boolean criarMarcacaoFirewallFiltroBloqContext(String usuario, String senha,String ip, Integer porta,String codContrato, String nomeCliente, String palavra){
//		
//		try {			
//			
//			connect(usuario,senha,ip);
//			
//			con.execute("/ip/firewall/layer7-protocol/add name="+palavra+" regexp="+palavra+" comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato);			
//			con.execute("/ip/firewall/mangle/add "
//					+ "action=add-dst-to-address-list "
//					+ "address-list=OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato+" "
//					+ "address-list-timeout=60m "
//					+ "comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato+" "
//					+ "src-address-list=IP_"+nomeCliente+" "
//					+ "layer7-protocol="+palavra+" "
//					+ "protocol=tcp "
//					+ "chain=forward "
//					+ "dst-address=!179.127.32.9 "
//					+ "disabled=no"
//					);
//			
//			List<Map<String, String>> listMangle = con.execute("/ip/firewall/mangle/print where comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato);
//	    	for (Map<String, String> map : listMangle) {
//				con.execute("/ip/firewall/mangle/move numbers="+map.get(".id")+" destination=0");
//			}
//	    	
//	    	con.execute("/ip/firewall/nat/add "
//	    			+ "action=dstnat "
//	    			+ "chain=dstnat "
//	    			+ "src-address-list=IP_"+nomeCliente+" "
//	    			+ "dst-address-list=OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato+" "
//	    			+ "comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato+" "
//	    			+ "protocol=tcp "
//	    			+ "to-addresses=179.127.32.9 "
//	    			+ "to-ports=89 "
//	    			+ "dst-address=!179.127.32.9 "
//	    			+ "disabled=no "
//	    			);
//	    	
//	    	List<Map<String, String>> listNat = con.execute("/ip/firewall/nat/print where comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato);
//	    	
//	    	for (Map<String, String> map : listNat) {
//				con.execute("/ip/firewall/nat/move numbers="+map.get(".id")+" destination=0");
//			}
//			
//	    	disconnect();   
//			
//			return true;
//		} catch (Exception e) {
//
//			//e.printStackTrace();
//			return false;
//
//		}
//			
//	}
//	
//	
//	
//	public static boolean removerMarcacaoFirewallFiltroBloqContext(String usuario, String senha,String ip, Integer porta,String codContrato, String nomeCliente, String palavra){
//		
//		try {
//			connect(usuario, senha, ip);
//			
//			List<Map<String, String>> list0 = con.execute("/ip/firewall/nat/print where comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato);
//						
//			for(Map<String, String> map: list0){
//				con.execute("/ip/firewall/nat/remove numbers="+map.get(".id"));								
//			}
//			
//			List<Map<String, String>> list1 = con.execute("/ip/firewall/mangle/print where comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato);
//			
//			for(Map<String, String> map: list1){
//				con.execute("/ip/firewall/mangle/remove numbers="+map.get(".id"));								
//			}		
//			
//			List<Map<String, String>> list2 = con.execute("/ip/firewall/layer7-protocol/print where comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato);
//		
//			for(Map<String, String> map: list2){
//				con.execute("/ip/firewall/layer7-protocol/remove numbers="+map.get(".id"));								
//			}
//					
//			List<Map<String, String>> list3 = con.execute("/ip/firewall/address-list/print where comment=______________OPUS_AUTOCENSURA::PALAVRA::"+palavra+"::CONTRATO:"+codContrato);
//			
//			for(Map<String, String> map: list3){
//				con.execute("/ip/firewall/address-list/remove numbers="+map.get(".id"));								
//			}
//			
//			disconnect();
//			return true;
//		} catch (Exception e) {
//			//e.printStackTrace();
//			return false;
//		}
//		
//	}
//	
//	public static void removerAccessList(String usuario, String senha,String ip, Integer porta, String nomeCliente, String endereco_mac){
//		try {
//			
//			connect(usuario, senha, ip);		
//			List<Map<String, String>> listWire = con.execute("/interface/wireless/access-list/print where mac-address="+endereco_mac);
//			
//			for(Map<String, String> map:listWire){							
//				con.execute("/interface/wireless/access-list/remove numbers="+map.get(".id"));				
//			}
//			
//			disconnect();
//			
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//	}
//	
//	
//	public static void liberarAccessList(String usuario, String senha,String ip, Integer porta,String codContrato, String nomeCliente, String endereco_mac, String interfaces, String signal){
//		try {
//			connect(usuario,senha,ip);
//			
//			con.execute("/interface/wireless/access-list/add mac-address="+endereco_mac+" interface="+interfaces+" comment="+getComment(codContrato, nomeCliente)+" signal-range="+signal+" disabled=no");			 
//			
//	    	disconnect();    	
//	    	
//		} catch (Exception e) {
//		//	//e.printStackTrace();
//			System.out.println("Nao foi possivel liberar access-list no concentrador");			
//		}
//	}
//	
//	
//	
//	
//	
//	
//	public static List<Map<String, String>> listarInterfaces(String usuario, String senha,String ip, Integer porta){
//		try {
//			connect(usuario,senha,ip);
//			List<Map<String, String>> list = con.execute("/interface/print");			
//			List<Map<String, String>> listNotDynamic = new ArrayList<>();
////			
//			for (Map<String, String> map : list) {
//				if(!map.get("name").toString().substring(0, 6).equals("<pppoe")){ 					
//					listNotDynamic.add(map);
//				}
//			}
//			
//	    	disconnect();    	
//	    
//	    	return listNotDynamic;
//		} catch (Exception e) {
//			System.out.println("ERRO!");
//			return null;
//		}
//	}
//	
//	
//		
//	public static Map<String, String> listarQueuesList(String usuario, String senha,String ip, Integer porta,String user){
//		try {
//			connect(usuario,senha,ip);
//			String u = "<pppoe-"+user+">";
//			
//			
//			List<Map<String, String>> listQueue = con.execute("/queue/simple/print where name='"+u+"'");
//			disconnect();
//			    	
//			if(listQueue.size() > 0){
//				return listQueue.get(0);
//			}else{
//				return null;
//			}			
//		} catch (Exception e) {
//			System.out.println("Não foi possivel pegar Informações de Queue");
//			
//			////e.printStackTrace();
//			return null;
//		}
//	}
//	
//	
//	
//	public static List<WirelessInterface> listarInterfacesWireless(String usuario, String senha,String ip, Integer porta){
//		try {
//			conecta(usuario,senha,ip,porta);
//			MkCommand command = new MkCommand("/interface/print");
//			command.appendWhere("mtu", "1500");
//			
//	    	String result = router.runCommand(command);
//	    	
//	    	desconecta();
//	    	List<WirelessInterface> interfaces = MkParser.asListOfObjects(WirelessInterface.class, result);
//	    	
//	    	return interfaces;
//		} catch (Exception e) {
//			System.out.println("ERRO!");
//			//e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public static void editInterface(String usuario, String senha,String ip, Integer porta,String id, String comment){
//		try {
//			connect(usuario, senha, ip);			
//			con.execute("/interface/set comment="+comment+" numbers="+id);						
//			disconnect();
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//	}
//	
//	public static boolean testconexao(String usuario, String senha,String ip, Integer porta){
//		try {
//			connect(usuario, senha, ip);		
//			disconnect();	
//			return true;
//		} catch (Exception e) {
//			return false;			
//		}
//	}
//	
//	
	private static void conecta(String usuario, String senha,String ip, Integer porta) throws Exception{
		
		router = new Mikrotik();
		
		
		try{
			router.connect(ip, porta, usuario, senha);
		}catch(Exception e){
			//e.printStackTrace();
			router.disconnect();
		}
	}
	private static void desconecta(){
		router.disconnect();
	}

}
