package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;

import util.boletos.boleto.Boleto;
import util.boletos.boleto.bancos.BancoDoBrasil;
import util.boletos.boleto.bancos.Sicredi;
import util.boletos.transform.GeradorDeImagemDoCodigoDeBarrasUtil;
import br.com.caelum.stella.boleto.bancos.GeradorDeLinhaDigitavel;



import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BarcodeInter25;

import dao.ContratosAcessoDAO;
import dao.NfeDAO;
import domain.AcessoCliente;
import domain.Cliente;
import domain.ConfigNfe;
import domain.ContasReceber;
import domain.ControleTitulo;
import domain.Empresa;
import domain.NfeMestre;
import domain.PlanoAcesso;

public class ImprimirBoletoFatura {

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PdfWriter writer = null;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	ContasReceber cr;
	
	public ImprimirBoletoFatura(Boleto boleto, Empresa empresa, Cliente cliente, ContasReceber cr) throws Exception {
		
			this.cr = cr;
		
			EntityManager em = emf.createEntityManager();
	
			Query q = em.createQuery("select n from NfeMestre n where n.contas_receber =:boleto", NfeMestre.class);
			q.setParameter("boleto", cr);
			
			if(q.getResultList().size() == 0){
				somenteBoleto(boleto, empresa); 
				System.out.println("BOLETO");
			}else{
				if(q.getResultList().size() == 1){
					NfeMestre nfe = (NfeMestre)q.getSingleResult();
					boletoComNfe(boleto, empresa,nfe);
					System.out.println("NFE");
				}
			}
					
	}
	
//    public void boletoComNfe(ContasReceber boleto,Empresa empresa,NfeMestre nfe){
//		
//		EntityManager em = emf.createEntityManager();
//				
//		AcessoCliente contrato = null;
//		if(CheckNdocUtil.checkNdocAcesso(boleto.getN_doc())){
//			String[] codigos = boleto.getN_doc().split("/");
//			
//			if(codigos[0] != null){
//				try{
//					contrato = ContratosAcessoDAO.find(Integer.parseInt(codigos[0].toString()));
//				}catch(Exception e){
//					
//				}
//			}
//		}		
//		Document doc = new Document(PageSize.A4, 24, 24, 14, 14);	
//		doc.setMargins(12, 12, 12, 12);
//		try{		
//			writer = PdfWriter.getInstance(doc, baos);
//			writer.setStrictImageSequence(true);
//			doc.open();
//			
//			Integer i = 1;
//			
//			StringBuilder str = new StringBuilder();
//			
//					String cad = StringUtil.md52(
//					StringUtil.preencheCom(nfe.getCliente().getDoc_cpf_cnpj(), "0", 14, 1)+
//					StringUtil.preencheCom(nfe.getId().toString(), "0", 9, 1)+
//					StringUtil.preencheCom(Real.formatDbToString(String.valueOf(nfe.getTotal_nota())).replace(",", ""),"0",12,1)+
//					StringUtil.preencheCom(Real.formatDbToString(String.valueOf(nfe.getTotal_nota())).replace(",", ""),"0",12,1)+
//					"000000000000");
//			
//			        String pronto = cad.substring(0,4).toUpperCase()+"."+
//					cad.substring(4, 8).toUpperCase()+"."+
//			        cad.substring(8, 12).toUpperCase()+"."+
//					cad.substring(12, 16).toUpperCase()+"."+
//			        cad.substring(16, 20).toUpperCase()+"."+
//					cad.substring(20, 24).toUpperCase()+"."+
//			        cad.substring(24, 28).toUpperCase()+"."+
//					cad.substring(28, 32).toUpperCase();
//			
//			
//			String theURL = "http://172.17.0.13/boletoNovo/gerarNfe.php?url="+boleto.getN_numero_gerencianet()+"&&hash="+pronto;
//			
//			URL url = new URL(theURL);
//			InputStream is = url.openStream();
//			int ptr = 0;
//			StringBuffer buffer = new StringBuffer();
//			while ((ptr = is.read()) != -1) {
//			    buffer.append((char)ptr);
//			}
//			
//			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();			
//			worker.parseXHtml(writer, doc,new StringReader(buffer.toString()));
//			
//		}catch(Exception e){
//			if(doc != null && doc.isOpen() && doc.getPageNumber() > 0){
//				doc.close(); 
//			}
//			
//			e.printStackTrace();
//		}finally{
//			if(doc != null && doc.isOpen()){
//				doc.close();
//			}
//		}	
//	}
	
	public void boletoComNfe(Boleto boleto,Empresa empresa,NfeMestre nfe){
		
		EntityManager em = emf.createEntityManager();
		
		ConfigNfe valueDefaultNfe = NfeDAO.getDefaultValue();
		
		AcessoCliente contrato = null;
		if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
			String[] codigos = boleto.getNumeroDoDocumento().split("/");
			
			
			if(codigos[0] != null){
				try{
					contrato = ContratosAcessoDAO.find(Integer.parseInt(codigos[0].toString()));
				}catch(Exception e){
					
				}
			}
		}
		
		Document doc = new Document(PageSize.A4, 24, 24, 14, 14);	
		doc.setMargins(12, 12, 12, 12);
		try{
		
		
			writer = PdfWriter.getInstance(doc, baos);
			writer.setStrictImageSequence(true);
			doc.open();
			
			Integer i = 1;
			
			StringBuilder str = new StringBuilder();
			
			str.append("<style type='text/css'>");
			str.append(".table1 {");
			str.append("	width: 800px;");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: dashed;");
			str.append("	border-bottom-color: #000;");
			str.append("}");
			
			str.append(".cabecalho {");
			str.append("	font-family: Arial, Helvetica, sans-serif;");
			str.append("	font-size: 14px;");
			str.append("	font-weight: bold;");
			str.append("	text-align: right;");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: solid;");
			str.append("	border-bottom-color: #000;");
			str.append("	vertical-align: bottom;");
			str.append("}");
			
			str.append(".linhas_formatacao {");
			str.append("	font-family: Courier;");
			str.append("	font-size: 8px;");
			str.append("	color: #000;");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: solid;");
			str.append("	border-bottom-color: #000;");
			str.append("	text-align: left;");
			str.append("	vertical-align: bottom;");
			str.append("	border-left-color: #000;");
			str.append("	padding-left: 0px;");
			str.append("}");
			
			str.append(".border-bottom{");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: solid;");
			str.append("	border-bottom-color: #000;");	
			str.append("	font-family: Courier;");
			str.append("	font-size: 8px;");
			str.append("	color: #000;");
			str.append("}");
			
			
			str.append(".border-bottom-dotted{");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: dotted;");
			str.append("	border-bottom-color: #000;");	
			str.append("	font-family: Courier;");
			str.append("	font-size: 8px;");
			str.append("	color: #000;");
			str.append("}");
			
			str.append(".border-bottom-fundo{");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: dotted;");
			str.append("	border-bottom-color: #000;");	
			str.append("	font-family: Courier;");
			str.append("	font-size: 8px;");
			str.append("	color: #000;");
			str.append("	background-color: #CCC;");
			str.append("}");
			
			str.append(".formatacao_valores");
			str.append("{");
			str.append("	font-family: Courier;");
			str.append("	font-size: 8px;");
			str.append("	color: #000;");
			str.append("	padding: 0;");
			str.append("margin: 0;");
			str.append("}");
			
			str.append(".titulo_linhas {");
			str.append("	font-family: Arial, Helvetica, sans-serif;");
			str.append("	font-size: 8px;");
			str.append("	color: #000;");
			str.append("	padding-bottom: 0;");
			str.append("	margin-top: 5px;");
			str.append("	text-align: center;");
			str.append("}");
			
			str.append(".div_vertical {");
			str.append("	background-image: url(imagens/dic_boleto.gif);");
			str.append("}");
			
			str.append(".bg_div {");
			str.append("	background-image: url(imagens/bg_div.gif);");
			str.append("	height: 4px;");
			str.append("}");
			
			str.append(".linhaDigitavel {");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: solid;");
			str.append("	border-bottom-color: #000;");	
			str.append("	font-family: Arial, Helvetica, sans-serif;");
			str.append("	font-size: 14px;");
			str.append("	text-align: right;");
			str.append("	color: #000;");
			str.append("	padding-right: 10px;");
			str.append("}");
			
			
			str.append(".cabecalho_logo{");
			str.append("	vertical-align:top;");
			str.append("	padding-bottom:10px;");
			str.append("	border-bottom-width: 1px;");
			str.append("	border-bottom-style: solid;");
			str.append("	border-bottom-color: #000;");	
			str.append("}");
			
			
			str.append(".aut_mec{");
			str.append("	font-size:8px;");
			str.append("	text-align:right;");
			str.append("	padding-top: 10px;");
			str.append("}");
			
			
			str.append(".table_space{");
			str.append("	margin-top:5px; ");
			str.append("}");
			
			str.append(".desc_topo_fatura{font-family:Arial; font-size:9px;}");
			str.append(".desc_topo_fatura_hash{font-family:Arial; font-size:16px;}");
			
			
			
			str.append(".desc_topo_fatura_top{font-family:Arial; font-size:12px;}");
			str.append(".desc_topo_fatura_info_cliente{font-family:Arial; font-size:10px;}");
			str.append(".desc_topo_fatura_vencimento_valor{font-family:Arial; font-size:14px;}");
			
			str.append(".border-left{");
			str.append("	border-left-width: 1px;");
			str.append("	border-left-style: solid;");
			str.append("	border-left-color: #000;");	
			str.append("	font-family: Courier;");
			str.append("	font-size: 8px;");
			str.append("	color: #000;");
			str.append("	padding-left: 20px;");
			str.append("	padding-top: 10px;");
			str.append("	padding-bottom: 10px;");
			str.append("	margin-right: 30px;");
			str.append("	margin-top: 30px;");
			str.append("	background-color: #e3e2e2;");
			str.append("}");
			
			str.append(".logo_small{");
			str.append("	margin-left:100px;");			
			str.append("}");
			
			
			
			str.append("</style>");
			
			
			
			//str.append("teste");
			
			String base = new File(".").getCanonicalPath();
			
			String logo_url_digital = base + "/src/imagens/logo_digital.png";
			String logo_url_digital_small = base + "/src/imagens/logo_digital_small.png";
			String logo_url_bb = base + "/src/imagens/bb_logo.png";
			String logo_digital_bb = base + "/src/imagens/logo_digital_bb.png";
			
					
					str.append("<table border='0' cellpadding='0' cellspacing='0'  >");
					str.append("<tr>");
						str.append("<td width='760' >");
							str.append("<table >");
									str.append("<tr rowspan='2' >");
										str.append("<td  width='1100'>");
											str.append("<img src='"+logo_url_digital+"' width='187' height='35'  /><br/>");
											str.append("<span class='desc_topo_fatura'>CNPJ: "+StringUtil.format("##.###.###/####-##", empresa.getCnpj())+" - INSCRIÇÃO ESTADUAL: "+empresa.getInscricao_estadual()+"</span><br />");
											str.append("<span class='desc_topo_fatura'>"+empresa.getEndereco()+", "+empresa.getNumero()+" - "+empresa.getBairro()+" - CEP : "+empresa.getCep()+"</span><br />");
											str.append("<span class='desc_topo_fatura'>"+empresa.getSite()+"</span><br /><br />");
											
											str.append("<span class='desc_topo_fatura'>SERVIÇO DE ATENDIMENTO AO CONSUMIDOR (SAC)</span><br />");
											str.append("<span class='desc_topo_fatura'>("+empresa.getDdd_fone1()+") "+empresa.getFone1()+" | "+empresa.get_0800()+"</span><br /><br />");
											
											str.append("<span class='desc_topo_fatura_info_cliente'><strong>"+boleto.getSacado().getNome()+"</strong></span><br />");
											
											if(boleto.getSacado().getCpf().length() == 11){												
												str.append("<span class='desc_topo_fatura_info_cliente'><strong>CPF: "+StringUtil.format("###.###.###-##", boleto.getSacado().getCpf())+"</strong></span><br />");
											}else{
												str.append("<span class='desc_topo_fatura_info_cliente'><strong>CNPJ: "+StringUtil.format("##.###.###/####-##", boleto.getSacado().getCpf())+"</strong></span><br />");
											}
//											str.append("<span class='desc_topo_fatura_info_cliente'>"+nfe.getContrato().getEndereco().getEndereco()+"<br/>"+nfe.getContrato().getEndereco().getBairro()+" - "+nfe.getContrato().getEndereco().getCidade()+" - "+nfe.getContrato().getEndereco().getUf()+"</span>");
											str.append("<span class='desc_topo_fatura_info_cliente'> | CEP: "+boleto.getSacado().getCep()+"</span><br/>");
											str.append("<span class='desc_topo_fatura_info_cliente'>Fone: ("+nfe.getCliente().getDdd_fone1()+") "+nfe.getCliente().getTelefone1()+" </span><br />");
											str.append("<span class='desc_topo_fatura_info_cliente'>E-mail: "+nfe.getCliente().getEmail()+"</span>");
											
										str.append("</td>");
										
										str.append("<td colspan='2' valign='top'>");
											str.append("<span class='desc_topo_fatura_top'><strong>NOTA FISCAL DE SERVIÇO DE COMUNICAÇÃO</strong></span><br /><br />");
											str.append("<span class='desc_topo_fatura'>NOTA FISCAL <strong>Nº: "+StringUtil.format("###.###.###", StringUtil.preencheCom(nfe.getId().toString(), "0", 9, 1))+"</strong></span><br />");
											str.append("<span class='desc_topo_fatura'>MODELO 21</span><br />");
											str.append("<span class='desc_topo_fatura'>CFOP "+nfe.getNatOpe().getId().toString()+" - "+nfe.getNatOpe().getDescricao()+"</span><br /><br />");
											
											
											
											str.append("<span class='desc_topo_fatura'>EMISSÃO: <strong>"+sdf.format(nfe.getData_emissao())+"</strong></span><br />");
											String situacao = nfe.getSituacao_doc().equals("N") ? "ACEITO" : "CANCELADO";
											
											if(situacao.equals("ACEITO")){
												str.append("<span class='desc_topo_fatura'>SITUAÇÃO: <strong>"+situacao+"</strong></span><br />");											
											}else{
												str.append("<span class='desc_topo_fatura' >SITUAÇÃO: <strong style='color:red;'>"+situacao+"</strong></span><br />");
											}
											
											str.append("");		
											
											if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
												str.append("<br/><span class='desc_topo_fatura'>Contrato Termo de Adesão Nº: "+contrato.getId().toString()+"</span>");
												str.append("<br/><span class='desc_topo_fatura'>Mensalidade de Internet - SCM - Plano "+contrato.getPlano().getNome()+"</span>");
												str.append("<br/><span class='desc_topo_fatura_info_cliente'>Período "+getDataMinus30Days(boleto.getDatas().getVencimento())+" a "+getData(boleto.getDatas().getVencimento())+"</span>");
											}		
										
											
											str.append("<br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  class='logo_small'   width='46' height='32' src='"+logo_url_digital_small+"' /><br/>");
											
										str.append("</td>");
									str.append("</tr>");
									
							str.append("</table>");
						str.append("</td>");
					str.append("</tr>");
					
					str.append("<tr>");
					str.append("<td>");
					str.append("<table>");
					
					str.append("<tr>");
						str.append("<td width='300' class='border-bottom' >");	
						
						str.append("</td>");
						
						str.append("<td width='90' class='border-left border-bottom' >");
							str.append("<span class='desc_topo_fatura_vencimento_valor'><strong>VENCIMENTO</strong></span><br/>");
							str.append("<span class='desc_topo_fatura_vencimento_valor'>"+sdf.format(nfe.getData_prestacao())+"</span>");
						str.append("</td>");	
					str.append("</tr>");
					
					str.append("<tr>");
						str.append("<td width='300' align='center'>");
						str.append("<span class='desc_topo_fatura'>RESERVADO AO FISCO</span><br />");
						
						String cad = StringUtil.md52(StringUtil.preencheCom(nfe.getCliente().getDoc_cpf_cnpj(), "0", 14, 1)+StringUtil.preencheCom(nfe.getId().toString(), "0", 9, 1)
								+StringUtil.preencheCom(Real.formatDbToString(String.valueOf(nfe.getTotal_nota())).replace(",", ""),"0",12,1)+
								StringUtil.preencheCom(Real.formatDbToString(String.valueOf(nfe.getTotal_nota())).replace(",", ""),"0",12,1)+"000000000000");
						
						str.append("<span class='desc_topo_fatura_hash'><strong>"+cad.substring(0, 4).toUpperCase()+"."+cad.substring(4, 8).toUpperCase()+"."+cad.substring(8, 12).toUpperCase()+"."+cad.substring(12, 16).toUpperCase()+"."+cad.substring(16, 20).toUpperCase()+"."+cad.substring(20, 24).toUpperCase()+"."+cad.substring(24, 28).toUpperCase()+"."+cad.substring(28, 32).toUpperCase()+"</strong></span><br /><br />");
						str.append("</td>");
						
						str.append("<td width='90'   class='border-left border-bottom' >");
							str.append("<span class='desc_topo_fatura_vencimento_valor'><strong>VALOR R$</strong></span><br/>");
							str.append("<span class='desc_topo_fatura_vencimento_valor'>"+Real.formatDbToString(String.valueOf(nfe.getTotal_nota()))+"</span>");
						str.append("</td>");
					str.append("</tr>");
				
					String valor_final = Real.formatDbToString(String.valueOf((nfe.getTotal_nota() )));
			str.append("</table>");
					str.append("</td>");
					str.append("</tr>");
					
					str.append("<tr><td><br/><br/>");
					str.append("<table>");					
					str.append("<tr >");
						str.append("<td class='border-bottom'  width='25'>");
							str.append("<span class='desc_topo_fatura_info_cliente'><strong>SERVIÇO</strong></span>");
						str.append("</td>");
						str.append("<td class='border-bottom'  width='1'>");
						str.append("<span class='desc_topo_fatura_info_cliente'><strong>CFOP</strong></span>");
						str.append("</td>");
						str.append("<td class='border-bottom'  width='10'>");
						str.append("<span class='desc_topo_fatura_info_cliente'><strong>NATUREZA DA OPERAÇÃO</strong></span>");
						str.append("</td>");
						str.append("<td  class='border-bottom' width='1' align='right'>");
						str.append("<span class='desc_topo_fatura_info_cliente'><strong>BC ICMS</strong></span>");
						str.append("</td>");
						str.append("<td class='border-bottom' width='1' align='right'>");
						str.append("<span class='desc_topo_fatura_info_cliente'><strong>ALÍQ ICMS</strong></span>");
						str.append("</td>");
						str.append("<td class='border-bottom' width='1' align='right'>");
						str.append("<span class='desc_topo_fatura_info_cliente'><strong>TOTAL R$</strong></span>");
						str.append("</td>");
					str.append("</tr>");
					
					str.append("<tr height='80'  style='background-color:#e3e2e2;'>");
					str.append("<td class='border-bottom' valign='top'>");
						str.append("<span class='desc_topo_fatura'>Prestação de serviço de comunicação multimídia</span>");
					str.append("</td>");
					str.append("<td class='border-bottom' valign='top'>");
						str.append("<span class='desc_topo_fatura'>"+nfe.getNatOpe().getId().toString()+"</span>");
					str.append("</td>");
					str.append("<td class='border-bottom' valign='top'>");
						str.append("<span class='desc_topo_fatura'>"+nfe.getNatOpe().getDescricao()+"</span>");
					str.append("</td>");
					str.append("<td class='border-bottom' valign='top' align='right'>");
						str.append("<span class='desc_topo_fatura'>0,00</span>");
					str.append("</td>");
					str.append("<td class='border-bottom' valign='top' align='right'>");
						str.append("<span class='desc_topo_fatura'>0,00</span>");
					str.append("</td>");
					str.append("<td class='border-bottom' valign='top' align='right'>");
						str.append("<span class='desc_topo_fatura'>"+Real.formatDbToString(String.valueOf(nfe.getTotal_nota()))+"</span>");
					str.append("</td>");
					str.append("</tr>");
					
					str.append("</table>");
					str.append("</td></tr>");
					
					str.append("<tr><td>");
					str.append("<table>");					
						
						str.append("<tr >");
						str.append("<td class='border-bottom' width='50' align='right'>");						
						str.append("</td >");
						str.append("<td width='1'  class='border-bottom' align='right'>");
							str.append("<span class='desc_topo_fatura'><strong>TOTAL R$</strong></span>");
						str.append("</td >");
						str.append("<td width='1' class='border-bottom' align='right'>");
						
						  
							str.append("<span class='desc_topo_fatura'><strong>"+valor_final+"</strong></span>");
						str.append("</td >");
						str.append("</tr>");					
					str.append("</table>");
					str.append("</td></tr>");
					
					
					str.append("<tr><td><table>");
					
					str.append("<tr >");
						str.append("<td  width='1' align='right'>");
							str.append("<span class='desc_topo_fatura_info_cliente'><strong>BC ICMS</strong></span>");
						str.append("</td>");
						str.append("<td width='20' align='right'>");
							str.append("<span class='desc_topo_fatura_info_cliente'><strong>VALOR DO ICMS</strong></span>");
						str.append("</td>");
						str.append("<td width='35' align='right'>");
//							str.append("<span class='desc_topo_fatura_info_cliente'><strong>VALOR APROXIMADO DOS TRIBUTOS (FONTE IBPT)</strong></span>");
						str.append("</td>");						
					str.append("</tr>");					
					
					str.append("</table></td></tr>");
					str.append("<tr><td><table>");
					
					str.append("<tr >");
						str.append("<td class='border-bottom'  width='1' align='right'>");
							str.append("<span class='desc_topo_fatura_info_cliente'>0,00</span>");
						str.append("</td>");
						str.append("<td class='border-bottom'  width='20' align='right'>");
							str.append("<span class='desc_topo_fatura_info_cliente'>0,00</span>");
						str.append("</td>");
						str.append("<td  class='border-bottom' width='35' align='right'>");
						
						double percentual = valueDefaultNfe.getValor_aprox_trib() / 100.0;					
						double valor_tributos =  (percentual * Real.formatStringToDBDouble(valor_final));
						
//							str.append("<span class='desc_topo_fatura_info_cliente'>"+Real.formatDbToString(String.valueOf(valor_tributos))+" ("+Real.formatDbToString(String.valueOf(valueDefaultNfe.getValor_aprox_trib()))+" %)</span>");
						str.append("</td>");						
					str.append("</tr>");					
					
					str.append("</table></td></tr>");
					
					
					str.append("<tr><td><table>");
					
					str.append("<tr >");
						str.append("<td   width='1'  valign='top'>");
							str.append("<span class='desc_topo_fatura_info_cliente'><strong>OBS</strong></span>");
						str.append("</td>");							
					str.append("</tr>");					
					
					str.append("</table></td></tr>");
					
					str.append("<tr><td><table>");
					
					str.append("<tr >");
						str.append("<td class='border-bottom'  width='1' height='45' valign='top'>");
							str.append("<span class='desc_topo_fatura_info_cliente'>"+valueDefaultNfe.getObservacao()+"</span>");														
						str.append("</td>");							
					str.append("</tr>");					
					
					str.append("</table></td></tr>");
					
					str.append("</table>");
					

						XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
						//convert to PDF
						worker.parseXHtml(writer, doc,new StringReader(str.toString()));
					//}
			
			
			
		}catch(Exception e){
			if(doc != null && doc.isOpen() && doc.getPageNumber() > 0){
				doc.close(); 
			}
			
			e.printStackTrace();
		}finally{
			if(doc != null && doc.isOpen()){
				doc.close();
			}
		}	
	
		
	}
			
		
	public void somenteBoleto(Boleto boleto,Empresa empresa){
		
		EntityManager em = emf.createEntityManager();
		
		ConfigNfe valueDefaultNfe = NfeDAO.getDefaultValue();	
		
		Document doc = new Document(PageSize.A4, 24, 24, 14, 14);	
		doc.setMargins(12, 12, 12, 12);
		try{
			
			
		
					writer = PdfWriter.getInstance(doc, baos);
					writer.setStrictImageSequence(true);
					doc.open();
			
					Integer i = 1;
			
					
			
					StringBuilder str = new StringBuilder();
					if(boleto.getStatus().equals("ABERTO")){
						
						if(boleto.getBanco().getClass() == BancoDoBrasil.class){
							
							
							str.append("<style type='text/css'>");
							str.append(".table1 {");
							str.append("	width: 800px;");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: dashed;");
							str.append("	border-bottom-color: #000;");
							str.append("}");
							
							str.append(".cabecalho {");
							str.append("	font-family: Arial, Helvetica, sans-serif;");
							str.append("	font-size: 14px;");
							str.append("	font-weight: bold;");
							str.append("	text-align: right;");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: solid;");
							str.append("	border-bottom-color: #000;");
							str.append("	vertical-align: bottom;");
							str.append("}");
							
							str.append(".linhas_formatacao {");
							str.append("	font-family: Courier;");
							str.append("	font-size: 8px;");
							str.append("	color: #000;");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: solid;");
							str.append("	border-bottom-color: #000;");
							str.append("	text-align: left;");
							str.append("	vertical-align: bottom;");
							str.append("	border-left-color: #000;");
							str.append("	padding-left: 0px;");
							str.append("}");
							
							str.append(".border-bottom{");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: solid;");
							str.append("	border-bottom-color: #000;");	
							str.append("	font-family: Courier;");
							str.append("	font-size: 8px;");
							str.append("	color: #000;");
							str.append("}");
							
							
							str.append(".border-bottom-dotted{");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: dotted;");
							str.append("	border-bottom-color: #000;");	
							str.append("	font-family: Courier;");
							str.append("	font-size: 8px;");
							str.append("	color: #000;");
							str.append("}");
							
							str.append(".border-bottom-fundo{");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: dotted;");
							str.append("	border-bottom-color: #000;");	
							str.append("	font-family: Courier;");
							str.append("	font-size: 8px;");
							str.append("	color: #000;");
							str.append("	background-color: #CCC;");
							str.append("}");
							
							str.append(".formatacao_valores");
							str.append("{");
							str.append("	font-family: Courier;");
							str.append("	font-size: 8px;");
							str.append("	color: #000;");
							str.append("	padding: 0;");
							str.append("margin: 0;");
							str.append("}");
							
							str.append(".titulo_linhas {");
							str.append("	font-family: Arial, Helvetica, sans-serif;");
							str.append("	font-size: 8px;");
							str.append("	color: #000;");
							str.append("	padding-bottom: 0;");
							str.append("	margin-top: 5px;");
							str.append("	text-align: center;");
							str.append("}");
							
							str.append(".div_vertical {");
							str.append("	background-image: url(imagens/dic_boleto.gif);");
							str.append("}");
							
							str.append(".bg_div {");
							str.append("	background-image: url(imagens/bg_div.gif);");
							str.append("	height: 4px;");
							str.append("}");
							
							str.append(".linhaDigitavel {");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: solid;");
							str.append("	border-bottom-color: #000;");	
							str.append("	font-family: Arial, Helvetica, sans-serif;");
							str.append("	font-size: 14px;");
							str.append("	text-align: right;");
							str.append("	color: #000;");
							str.append("	padding-right: 10px;");
							str.append("}");
							
							
							str.append(".cabecalho_logo{");
							str.append("	vertical-align:top;");
							str.append("	padding-bottom:10px;");
							str.append("	border-bottom-width: 1px;");
							str.append("	border-bottom-style: solid;");
							str.append("	border-bottom-color: #000;");	
							str.append("}");
							
							
							str.append(".aut_mec{");
							str.append("	font-size:8px;");
							str.append("	text-align:right;");
							str.append("	padding-top: 10px;");
							str.append("}");
							
							
							str.append(".table_space{");
							str.append("	margin-top:35px; ");
							str.append("}");
							
							
							str.append("</style>");
					
							if(i % 2 == 0){ 
														
								str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1 border-bottom table_space' >");
							}else{	
								if(i != 1){
									doc.newPage();
								}
								
								
								str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1 border-bottom'>");
							}
							
							i++;
							
							//Linha1, Logotipo, 001-9, Recibo Sacado e Recibo Entrega
							
							String base = new File(".").getCanonicalPath();
							//GeradorDeLinhaDigitavel linhaDigitavelGenerator = new GeradorDeLinhaDigitavel();
							//String codigoDeBarras = boleto.getBanco().geraCodigoDeBarrasPara(boleto);
							
							String logo_url = base + "/src/imagens/bb_logo.png";
							String logo_digital_bb = base + "/src/imagens/logo_digital_bb.png";
											
						
							str.append("	<tr>");
							str.append("		<td>");
							str.append("			<table width='94%' border='0' cellpadding='0' cellspacing='0'>");
							str.append("				<tr>");
							str.append("					<td width='80' class='cabecalho_logo' height='40'>");
							str.append("						<img src='"+logo_url+"' width='155' height='20' />");
							str.append("					</td>");
							
							str.append("					<td width='70' class='cabecalho' >");
							str.append("						|001-9|");
							str.append("					</td>");
							
							AcessoCliente contrato = null;
							if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
								String[] codigos = boleto.getNumeroDoDocumento().split("/");
								
								
								if(codigos[0] != null){
									try{
										contrato = ContratosAcessoDAO.find(Integer.parseInt(codigos[0].toString()));
									}catch(Exception e){
										
									}
								}
							}
							
							boolean imprimir_cod_barras = false;
							
							ControleTitulo controle = null;
							Query q = em.createQuery("select c from ControleTitulo c where c.nome =:nome and c.empresa_id =:empresa", ControleTitulo.class); 
							q.setParameter("nome", boleto.getControle());
							q.setParameter("empresa", empresa.getId());
							if(q.getResultList().size() == 1){
								controle = (ControleTitulo)q.getSingleResult();
								
								if(controle != null && controle.getRegistro() != null && controle.getRegistro().equals("SIM")){
									imprimir_cod_barras = true;
									
									if(contrato != null && contrato.getEmitir_nfe_automatico() != null &&  contrato.getEmitir_nfe_automatico().equals("SIM") && contrato.getEmitir_nfe_c_boleto_aberto() != null && contrato.getEmitir_nfe_c_boleto_aberto().equals("SIM")){
										imprimir_cod_barras = true;
									}else{
										
										if(contrato != null){
											imprimir_cod_barras = false;
										}else{
											imprimir_cod_barras = true;
										}
									}
								}
							}
										
							str.append("					<td width='220' class='cabecalho' >");
							str.append("						<span class='titulo_linhas'>Recibo de Pagador</span><br />");
							
							if(imprimir_cod_barras){
						//		str.append(	"						<span style='font-size:10px;'>"+ linhaDigitavelGenerator.geraLinhaDigitavelPara(codigoDeBarras) +"</span>");
							}
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='140' class='cabecalho' >");
							str.append("						Recibo Entrega");
							str.append("					</td>");
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							str.append("</table>");
							
							
							str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
							str.append("	<tr>");
							str.append("		<td>");
							str.append("			<table width='97%' border='0' cellpadding='0' cellspacing='0'>");
							str.append("				<tr>");
						

							str.append("					<td class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Nome do Pagador/CPF/CNPJ/Endereço</span><br />");			
							str.append(							boleto.getSacado().getNome()+" "+boleto.getSacado().getCpf()+" <br/> "+boleto.getSacado().getEndereco()+" "+boleto.getSacado().getBairro() );
							str.append("						<br/><span class='titulo_linhas'>Sacador/Avalista</span>");			
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='60' class='border-bottom' height='20' >");
							str.append("						<span class='titulo_linhas'>Vencimento</span><br />");			
							str.append(							formatDate(boleto.getDatas().getVencimento()));
							str.append("					</td>");
							
							str.append("					<td width='2%' class='border-bottom' height='20'>");
							str.append("						<img src='imagens/div_txt.gif' width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='60' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>(=) Valor Documento</span><br />");			
							str.append(							Real.formatDbToString(boleto.getValorBoleto().toString()));
							str.append("					</td>");
							
							str.append("					<td width='2%' class='border-bottom' height='20'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='60' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Nº Documento</span><br />");	
							str.append(							boleto.getNumeroDoDocumento());
							str.append("					</td>");
							
							
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							str.append("</table>");
							
							
							str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
							str.append("	<tr>");
							str.append("		<td>");
							str.append("			<table width='97%' border='0' cellpadding='0' cellspacing='0'>");
							str.append("				<tr>");
						

							str.append("					<td width='100' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Nosso Número</span><br />");
							str.append(							boleto.getBanco().getNossoNumeroDoEmissorFormatado(boleto.getEmissor()));
							str.append("					</td>");
							
							str.append("					<td width='2%' class='border-bottom' height='20'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='85' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Nº Documento</span><br />");	
							str.append(							boleto.getNumeroDoDocumento());
							str.append("					</td>");
							
							str.append("					<td width='2%' class='border-bottom' height='20'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='80' class='border-bottom' height='20' >");
							str.append("						<span class='titulo_linhas'>Data de Vencimento</span><br />");			
							str.append(							formatDate(boleto.getDatas().getVencimento()));
							str.append("					</td>");
							
							str.append("					<td width='2%' class='border-bottom' height='20'>");
							str.append("						<img src='imagens/div_txt.gif' width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='100' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Valor do Documento</span><br />");			
							str.append(							Real.formatDbToString(boleto.getValorBoleto().toString()));
							str.append("					</td>");
							
							str.append("					<td width='2%' class='border-bottom' height='20'>");
							str.append("						<img src='imagens/div_txt.gif' width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='75' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>(=) Valor Pago</span><br />");			
							str.append("_ ");
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							
							str.append("					<td width='2%' class='border-bottom' height='20'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='167' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Pagador</span><br />");			
							str.append(							boleto.getSacado().getNome().length() > 36 ? boleto.getSacado().getNome().substring(0,36) : boleto.getSacado().getNome());
							str.append("					</td>");			
							
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							str.append("</table>");
							
							str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
							str.append("	<tr>");
							str.append("		<td>");
							str.append("			<table width='97%' border='0' cellpadding='0' cellspacing='0'>");
							str.append("				<tr>");
						
							str.append("					<td class='border-bottom-dotted'>");
							str.append("						<span class='titulo_linhas'>Nome do Beneficiário/CPF/CNPJ/Endereço</span><br />");			
							str.append("						"+boleto.getEmissor().getCedente()+" "+boleto.getCnpj()+" "+empresa.getEndereco()+"  "+empresa.getBairro()+" "+empresa.getNumero()+" - "+empresa.getCidade()+ "<br/>");
							str.append("						"+boleto.getAgenciaECodigoCedente());
							str.append("						<br/><span class='titulo_linhas'>Agência/Código do Beneficiário</span>");			
							str.append("					</td>");		
							
							str.append("					<td width='100' class='border-bottom' >");
							str.append("						<span class='titulo_linhas'>Autênticação Mecânica</span><br /><br /><br /><br />");			
							
							str.append("					</td>");		
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							

							str.append("					<td width='130' class='border-bottom' >");
							str.append("						<span class='titulo_linhas'>Assinatura do Recebedor</span><br /><br /><br /><br />");			
							str.append("					</td>");
							
							str.append("					<td width='80' class='border-bottom' >");
							str.append("						<span class='titulo_linhas'>Data de Entrega</span><br /><br />");			
							str.append("						____/____/___");
							str.append("					</td>");
						
							
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							str.append("</table>");
							
							
												
							//Parte Boleto: TOPO LINHA DIGITAVEL
							str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
							str.append("	<tr>");
							str.append("		<td colspan='3'>");
							str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0'>");
							str.append("				<tr>");
							str.append("					<td class='cabecalho_logo' height='40' width='50'>");
							str.append("						<img src='"+logo_url+"'  width='155' height='20' />");
							str.append("					</td>");
							
							str.append("					<td  class='border-bottom' height='20' width='20'>");
							str.append("						<span class='cabecalho'>|001-9|</span>");
							str.append("					</td>");
				        
							str.append("					<td class='linhaDigitavel' width='100'>");
							
							
							
							if(imprimir_cod_barras){
						//		str.append(						linhaDigitavelGenerator.geraLinhaDigitavelPara(codigoDeBarras));
							}
							str.append("					</td>");
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							
							//Linha: Local Pagto., Uso Banco, Vencimentoborder-bottom
							str.append("	<tr>");    
							str.append("		<td colspan='3'>");
							str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0'>");
							str.append("				<tr>");
							
							str.append("					<td width='529' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Local de Pagamento</span><br />");
							str.append("   						Pagável em qualquer banco até o vencimento.");
							str.append("					</td>");
														        
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='194' class='border-bottom-fundo'>");
							str.append("						<span class='titulo_linhas'>Data de Vencimento</span><br />");
							str.append(							"<strong>"+formatDate(boleto.getDatas().getVencimento())+"</strong>");
							str.append("					</td>");
							
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							
							//Linha: Cedente, Agência/Código Cedente
							str.append("	<tr>");
							str.append("		<td colspan='3'>");
							str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
							str.append("				<tr>");
							
							str.append("					<td width='529' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Nome do Beneficiário</span><br />");
							str.append("						"+boleto.getEmissor().getCedente()+" "+boleto.getCnpj()+"<br/>");
							str.append("					</td>");
							
							str.append("					<td width='1%' >");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
				        
							str.append("					<td width='194' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Agência / Código do Beneficiário</span><br />");
							str.append("						"+boleto.getEmissor().getAgenciaFormatado()+"<br/>");
							str.append("					</td>");
							
							str.append("				</tr>");			
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							
							
							
							//Linha Endereço cedente, nosso numero
							str.append("	<tr>");
							str.append("		<td colspan='3'>");
							str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
							str.append("				<tr>");
							
							str.append("					<td width='120' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Data do Documento</span><br />");
							str.append(							formatDate(boleto.getDatas().getDocumento()));
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='120' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Nr. Documento</span><br />");
							str.append(							boleto.getNumeroDoDocumento());
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='100' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Espécie DOC</span><br />");
							
							
							if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
								str.append("						DS");
							}else{
								str.append("						DM");
							}
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='80' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Aceite.</span><br />");
							str.append("						N");
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='75' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Data Processamento</span><br />");
							str.append(							formatDate(boleto.getDatas().getDocumento()));
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
														
							str.append("					<td width='194' class='border-bottom' height='20' >");
							str.append("						<span class='titulo_linhas'>Nosso Número</span><br />");
							str.append(							boleto.getBanco().getNossoNumeroDoEmissorFormatado(boleto.getEmissor()));
							str.append("					</td>");
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							
							//Linha Data Doc. Ndoc, Especie, Aceite, Data Proc. Carteira, Moeda, Quantidade, Valor doc
							str.append("	<tr>");
							str.append("		<td colspan='3'>");
							str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
							str.append("				<tr>");
							
							str.append("					<td width='120' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Uso do banco</span><br />");					
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='120' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Carteira</span><br />");
							str.append(							boleto.getBanco().getCarteiraDoEmissorFormatado(boleto.getEmissor()));
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='100' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Espécie</span><br />");
							
							
							if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
								str.append("						DS");
							}else{
								str.append("						DM");
							}
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							
							str.append("					<td width='80' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>Quantidade</span><br />");
							str.append(						boleto.getQtd() != null ? boleto.getQtd() : "");
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='75' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>xValor</span><br />");
							str.append(						"");
							str.append("					</td>");
							
							str.append("					<td width='1%'>");
							str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
							str.append("					</td>");
							
							str.append("					<td width='191' class='border-bottom-fundo'>");
							str.append("						<span class='titulo_linhas'>(=)Valor do Documento</span><br />");
							str.append("						<strong> "+Real.formatDbToString(boleto.getValorBoleto().toString())+"</strong>");
							str.append("					</td>");
							str.append("				</tr>");
							str.append("			</table>");
							str.append("		</td>");
							str.append("	</tr>");
							
							str.append("	<tr>");
							
							str.append("		<td width='400' border='0' valign='top' >");
							str.append("			<span class='titulo_linhas'>Informações de Responsabilidade do Beneficiário</span><br />");
							str.append("<br />");
							
							
							
							PlanoAcesso p = em.find(PlanoAcesso.class, cr.getPlano_contrato());
							if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
								str.append("Serviço de Internet - SCM - "+p.getNome()+" <br/> Período "+getDataMinus30Days(boleto.getDatas().getVencimento())+" a "+getData(boleto.getDatas().getVencimento())+"<br />");
								String[] codigos = boleto.getNumeroDoDocumento().split("/");
								str.append("Contrato Termo Adesao Nº: "+codigos[0]+"<br/>");												
							}
							
							for (String s : boleto.getInstrucoes()) {
								str.append(s);
							}					
							
//							str.append("<br />");
							
							for (String s : boleto.getDescricoes()) {
								str.append(s);
							}
							
							str.append("<br/>FAVOR PAGAR O BOLETO, NAO DEPOSITAR.<br/>");
							str.append("<br />");
							str.append("Nome do Pagador/CPF/CNPJ/Endereço<br />");
							str.append("<strong>"+boleto.getSacado().getNome()+" "+boleto.getSacado().getCpf()+"</strong><br />");
							str.append(boleto.getSacado().getEndereco()+" - "+boleto.getSacado().getBairro()+" - "+boleto.getSacado().getComplemento()+"<br />");
							String ref = boleto.getSacado().getRefencia() != null  && boleto.getSacado().getRefencia().length() > 50 ? boleto.getSacado().getRefencia().substring(0, 50) : boleto.getSacado().getRefencia();
							str.append(boleto.getSacado().getCep()+" - "+boleto.getSacado().getCidade()+"-"+boleto.getSacado().getUf() + " ("+ref+")");					
							
							str.append("		</td>");
							
							
							str.append("		<td width='150' border='0' valign='top' haling='center'>");
							str.append("			<br /><br />");
							
							String basepath = new File(".").getCanonicalPath();
							String nomeImg = basepath + "/src/imagens/uploads/cod_"+boleto.getCodBoleto().toString()+".png";
							
							
							
							Image imgCodNNumero = geraImagemDoCodigoDeBarrasParaNNumero(boleto.getCodBoleto().toString(), 30f);									
							File f = new File(nomeImg);
							
							
							try{						
								BufferedImage bi = toBufferedImage(imgCodNNumero, BufferedImage.TYPE_INT_ARGB);
								ImageIO.write(bi, "png", f);
							}catch(Exception e){
								e.printStackTrace();
							}
							
												
						
							str.append("<img src='"+nomeImg+"' /><br/>"+boleto.getCodBoleto().toString());
										
							
							
							str.append("		</td>");
								
							str.append("		<td  valign='top' border='0'>");
							
							str.append("			<table width='84%'  border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
											
							str.append("				<tr>");
							
							str.append("					<td width='191' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>(-) Desconto / Abatimento</span><br />");
							str.append("						&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
						
							
							str.append("				<tr>");
							
							str.append("					<td width='191' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>(+) Juros / Multa</span><br />");
			
							
							str.append("						&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
							
											
							
							str.append("				<tr>");
							
							str.append("					<td width='191' class='border-bottom' height='20'>");
							str.append("						<span class='titulo_linhas'>(=) Valor Cobrado</span><br />");
							str.append("						&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
							str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
							
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
		str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("					</td>");
							
							str.append("				</tr>");
							
							

							
							
							str.append("				<tr>");
							
							str.append("					<td style='text-align:right; ' width='191' height:'100' >");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("			&nbsp;");
							str.append("						<img style='margin-top:30px;' src='"+logo_digital_bb+"' width='180' />");
							str.append("					</td>");
							
							str.append("				</tr>");
							
										
							
							str.append("			</table>");		
							
							str.append("		</td>");
							
							str.append("	</tr>");				
							
							str.append("</table>");
							
							str.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
							str.append("	<tr>");
							str.append("		<td class='border-bottom'>");
							str.append("			<br/>");
							str.append("			&nbsp;");
							str.append("		</td>");
							str.append("	</tr>");				
							str.append("</table>");
							
							
							str.append("<table border='0' cellpadding='0' cellspacing='0' width='70%'>");
							str.append("	<tr>");
							str.append("		<td class='aut_mec'>");
							str.append("			<br/>");
							str.append("			Autênticação Mecanica");
							str.append("		</td>");
							str.append("	</tr>");				
							str.append("</table>");
												
							//get the XMLWorkerHelper Instance		
							XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
							//convert to PDF
							worker.parseXHtml(writer, doc,new StringReader(str.toString()));
										
								
							
								//Image imagemDoCodigoDeBarras = GeradorDeImagemDoCodigoDeBarrasUtil.geraImagemDoCodigoDeBarrasPara(
								//		boleto.getBanco().geraCodigoDeBarrasPara(boleto), 55.00f);		
							
								//com.itextpdf.text.Image barCode = com.itextpdf.text.Image.getInstance(toBufferedImage(imagemDoCodigoDeBarras, BufferedImage.TYPE_INT_ARGB), null);						
								///barCode.scaleToFit(300f,100f);
								//barCode.setSpacingAfter(500.0f);
								//barCode.setSpacingBefore(100.0f);
								
								//IMPRIMIR CÓDIGO DE BARRAS SOMENTE PARA CONTRATOS QUE EMITEM NOTA FISCAL
								//if(imprimir_cod_barras){
									
								//	doc.add(barCode);
								//}
								
								
								//worker.parseXHtml(writer, doc, new StringReader("<br/>."));
								DottedLineSeparator line = new DottedLineSeparator();						
								line.setPercentage(97.0f);
								line.setOffset(-20.0f);
								
								
								if(i % 2 == 0){ 
									doc.add(line);
								}
								
								
							
							
						}
					
					
					
						if(boleto.getBanco().getClass() == Sicredi.class){
							
						
					
						
						
						
						str.append("<style type='text/css'>");
						str.append(".table1 {");
						str.append("	width: 800px;");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: dashed;");
						str.append("	border-bottom-color: #000;");
						str.append("}");
						
						str.append(".cabecalho {");
						str.append("	font-family: Arial, Helvetica, sans-serif;");
						str.append("	font-size: 14px;");
						str.append("	font-weight: bold;");
						str.append("	text-align: right;");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: solid;");
						str.append("	border-bottom-color: #000;");
						str.append("	vertical-align: bottom;");
						str.append("}");
						
						str.append(".linhas_formatacao {");
						str.append("	font-family: Courier;");
						str.append("	font-size: 8px;");
						str.append("	color: #000;");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: solid;");
						str.append("	border-bottom-color: #000;");
						str.append("	text-align: left;");
						str.append("	vertical-align: bottom;");
						str.append("	border-left-color: #000;");
						str.append("	padding-left: 0px;");
						str.append("}");
						
						str.append(".border-bottom{");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: solid;");
						str.append("	border-bottom-color: #000;");	
						str.append("	font-family: Courier;");
						str.append("	font-size: 8px;");
						str.append("	color: #000;");
						str.append("}");
						
						
						str.append(".border-bottom-dotted{");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: dotted;");
						str.append("	border-bottom-color: #000;");	
						str.append("	font-family: Courier;");
						str.append("	font-size: 8px;");
						str.append("	color: #000;");
						str.append("}");
						
						str.append(".border-bottom-fundo{");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: dotted;");
						str.append("	border-bottom-color: #000;");	
						str.append("	font-family: Courier;");
						str.append("	font-size: 8px;");
						str.append("	color: #000;");
						str.append("	background-color: #CCC;");
						str.append("}");
						
						str.append(".formatacao_valores");
						str.append("{");
						str.append("	font-family: Courier;");
						str.append("	font-size: 8px;");
						str.append("	color: #000;");
						str.append("	padding: 0;");
						str.append("margin: 0;");
						str.append("}");
						
						str.append(".titulo_linhas {");
						str.append("	font-family: Arial, Helvetica, sans-serif;");
						str.append("	font-size: 8px;");
						str.append("	color: #000;");
						str.append("	padding-bottom: 0;");
						str.append("	margin-top: 5px;");
						str.append("	text-align: center;");
						str.append("}");
						
						str.append(".div_vertical {");
						str.append("	background-image: url(imagens/dic_boleto.gif);");
						str.append("}");
						
						str.append(".bg_div {");
						str.append("	background-image: url(imagens/bg_div.gif);");
						str.append("	height: 4px;");
						str.append("}");
						
						str.append(".linhaDigitavel {");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: solid;");
						str.append("	border-bottom-color: #000;");	
						str.append("	font-family: Arial, Helvetica, sans-serif;");
						str.append("	font-size: 14px;");
						str.append("	text-align: right;");
						str.append("	color: #000;");
						str.append("	padding-right: 10px;");
						str.append("}");
						
						
						str.append(".cabecalho_logo{");
						str.append("	vertical-align:top;");
						str.append("	padding-bottom:10px;");
						str.append("	border-bottom-width: 1px;");
						str.append("	border-bottom-style: solid;");
						str.append("	border-bottom-color: #000;");	
						str.append("}");
						
						
						str.append(".aut_mec{");
						str.append("	font-size:8px;");
						str.append("	text-align:right;");
						str.append("	padding-top: 10px;");
						str.append("}");
						
						
						str.append(".table_space{");
						str.append("	margin-top:35px; ");
						str.append("}");
						
						
						str.append("</style>");
//				
						if(i % 2 == 0){ 
													
							str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1 border-bottom table_space' >");
						}else{	
							if(i != 1){
								doc.newPage();
							}
							
							
							str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1 border-bottom'>");
						}
						
						i++;
//						
//						//Linha1, Logotipo, 001-9, Recibo Sacado e Recibo Entrega
						
						String base = new File(".").getCanonicalPath();
						GeradorDeLinhaDigitavel linhaDigitavelGenerator = new GeradorDeLinhaDigitavel();
						StringBuilder codigoDeBarras = new StringBuilder(boleto.getBanco().geraCodigoDeBarrasPara(boleto)); 
						
						codigoDeBarras.insert(5, '.');
						codigoDeBarras.insert(11, "  ");
						codigoDeBarras.insert(18, '.');
						codigoDeBarras.insert(25, "  ");
						codigoDeBarras.insert(32, '.');
						codigoDeBarras.insert(39, "  ");
						codigoDeBarras.insert(42, "  ");
											
						String logo_digital_bb = base + "/src/imagens/logo_digital_bb.png";
						String logo_url = "";
						if(boleto.getContaBancaria().getCod_banco().equals("001")){					
							logo_url = base + "/src/imagens/bb_logo.png";
						}
						
						if(boleto.getContaBancaria().getCod_banco().equals("748")){					
							logo_url = base + "/src/imagens/sicredi_logo.png";
						}
										
					
						str.append("	<tr>");
						str.append("		<td>");
						str.append("			<table width='94%' border='0' cellpadding='0' cellspacing='0'>");
						str.append("				<tr>");
						str.append("					<td width='80' class='cabecalho_logo' height='40'>");
						str.append("						<img src='"+logo_url+"' width='155' height='30' />");
						str.append("					</td>");
						
						str.append("					<td width='70' class='cabecalho' >");
						if(boleto.getContaBancaria().getCod_banco().equals("001")){
							str.append("						|"+boleto.getContaBancaria().getCod_banco()+"-9|");
						}
//						
						if(boleto.getContaBancaria().getCod_banco().equals("748")){
							str.append("						|"+boleto.getContaBancaria().getCod_banco()+"-X|");
						}
						
						
						str.append("					</td>");
						
						AcessoCliente contrato = null;
						if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
							String[] codigos = boleto.getNumeroDoDocumento().split("/");
							
							
							if(codigos[0] != null){
								try{
									contrato = ContratosAcessoDAO.find(Integer.parseInt(codigos[0].toString()));
								}catch(Exception e){
									
								}
							}
						}
						
						boolean imprimir_cod_barras = false;
						
						ControleTitulo controle = null;
						Query q = em.createQuery("select c from ControleTitulo c where c.nome =:nome and c.empresa_id =:empresa", ControleTitulo.class); 
						q.setParameter("nome", boleto.getControle());
						q.setParameter("empresa", empresa.getId());
						if(q.getResultList().size() == 1){
							controle = (ControleTitulo)q.getSingleResult();
							
							if(controle != null && controle.getRegistro() != null && controle.getRegistro().equals("SIM")){
								imprimir_cod_barras = true;
								
								if(contrato != null && contrato.getEmitir_nfe_automatico() != null &&  contrato.getEmitir_nfe_automatico().equals("SIM") && contrato.getEmitir_nfe_c_boleto_aberto() != null && contrato.getEmitir_nfe_c_boleto_aberto().equals("SIM")){
									imprimir_cod_barras = true;
								}else{
									
									if(contrato != null){
										imprimir_cod_barras = false;
									}else{
										imprimir_cod_barras = true;
									}
								}
							}
						}
//									
						str.append("					<td width='220' class='cabecalho' >");
						str.append("						<span class='titulo_linhas'>Recibo de Pagador</span><br />");
						
						if(imprimir_cod_barras){
							str.append(	"						<span style='font-size:10px;'>"+ codigoDeBarras.toString() +"</span>");
						}
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='140' class='cabecalho' >");
						str.append("						Recibo Entrega");
						str.append("					</td>");
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						str.append("</table>");
						
//						
						str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
						str.append("	<tr>");
						str.append("		<td>");
						str.append("			<table width='97%' border='0' cellpadding='0' cellspacing='0'>");
						str.append("				<tr>");
					

						str.append("					<td class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Nome do Pagador/CPF/CNPJ/Endereço</span><br />");			
						str.append(							boleto.getSacado().getNome()+" "+boleto.getSacado().getCpf()+" <br/> "+boleto.getSacado().getEndereco()+" "+boleto.getSacado().getBairro() );
						str.append("						<br/><span class='titulo_linhas'>Sacador/Avalista</span>");			
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='60' class='border-bottom' height='20' >");
						str.append("						<span class='titulo_linhas'>Vencimento</span><br />");			
						str.append(							formatDate(boleto.getDatas().getVencimento()));
						str.append("					</td>");
//						
						str.append("					<td width='2%' class='border-bottom' height='20'>");
						str.append("						<img src='imagens/div_txt.gif' width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='60' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>(=) Valor Documento</span><br />");			
						str.append(							Real.formatDbToString(boleto.getValorBoleto().toString()));
						str.append("					</td>");
						
						str.append("					<td width='2%' class='border-bottom' height='20'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='60' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Nº Documento</span><br />");	
						str.append(							boleto.getNumeroDoDocumento());
						str.append("					</td>");
						
						
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						str.append("</table>");
						
						
						str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
						str.append("	<tr>");
						str.append("		<td>");
						str.append("			<table width='97%' border='0' cellpadding='0' cellspacing='0'>");
						str.append("				<tr>");
					

						str.append("					<td width='100' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Nosso Número</span><br />");
						str.append(							boleto.getNossoNumero());
						str.append("					</td>");
						
						str.append("					<td width='2%' class='border-bottom' height='20'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='85' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Nº Documento</span><br />");	
						str.append(							boleto.getNumeroDoDocumento());
						str.append("					</td>");
						
						str.append("					<td width='2%' class='border-bottom' height='20'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='80' class='border-bottom' height='20' >");
						str.append("						<span class='titulo_linhas'>Vencimento</span><br />");			
						str.append(							formatDate(boleto.getDatas().getVencimento()));
						str.append("					</td>");
						
						str.append("					<td width='2%' class='border-bottom' height='20'>");
						str.append("						<img src='imagens/div_txt.gif' width='6' height='18' />");
						str.append("					</td>");
//						
						str.append("					<td width='100' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Valor do Documento</span><br />");			
						str.append(							Real.formatDbToString(boleto.getValorBoleto().toString()));
						str.append("					</td>");
						
						str.append("					<td width='2%' class='border-bottom' height='20'>");
						str.append("						<img src='imagens/div_txt.gif' width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='75' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>(=) Valor Pago</span><br />");			
						str.append("_ ");
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						
						str.append("					<td width='2%' class='border-bottom' height='20'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='167' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Pagador</span><br />");			
						str.append(							boleto.getSacado().getNome().length() > 36 ? boleto.getSacado().getNome().substring(0,36) : boleto.getSacado().getNome());
						str.append("					</td>");			
						
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						str.append("</table>");
						
						str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
						str.append("	<tr>");
						str.append("		<td>");
						str.append("			<table width='97%' border='0' cellpadding='0' cellspacing='0'>");
						str.append("				<tr>");
					
						str.append("					<td class='border-bottom-dotted'>");
						str.append("						<span class='titulo_linhas'>Nome do Beneficiário/CPF/CNPJ/Endereço</span><br />");			
						str.append("						"+boleto.getEmissor().getCedente()+" "+boleto.getCnpj()+" "+empresa.getEndereco()+"  "+empresa.getBairro()+" "+empresa.getNumero()+" - "+empresa.getCidade()+ "<br/>");
						str.append("						"+boleto.getAgenciaECodigoCedente());
						str.append("						<br/><span class='titulo_linhas'>Agência/Código do Beneficiário</span>");			
						str.append("					</td>");		
						
						str.append("					<td width='100' class='border-bottom' >");
						str.append("						<span class='titulo_linhas'>Autênticação Mecânica</span><br /><br /><br /><br />");			
						
						str.append("					</td>");		
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						

						str.append("					<td width='130' class='border-bottom' >");
						str.append("						<span class='titulo_linhas'>Assinatura do Recebedor</span><br /><br /><br /><br />");			
						str.append("					</td>");
						
						str.append("					<td width='80' class='border-bottom' >");
						str.append("						<span class='titulo_linhas'>Data de Entrega</span><br /><br />");			
						str.append("						____/____/___");
						str.append("					</td>");
					
						
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						str.append("</table>");
						
						
											
						//Parte Boleto: TOPO LINHA DIGITAVEL
						str.append("<table border='0' cellpadding='0' cellspacing='0' class='table1' class='border-bottom'>");
						str.append("	<tr>");
						str.append("		<td colspan='3'>");
						str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0'>");
						str.append("				<tr>");
						str.append("					<td class='cabecalho_logo' height='40' width='50'>");
						str.append("						<img src='"+logo_url+"'  width='155' height='30' />");
						str.append("					</td>");
						
						str.append("					<td  class='border-bottom' height='20' width='20'>");
						str.append("						<span class='cabecalho'>|748-X|</span>");
						str.append("					</td>");
			        
						str.append("					<td class='linhaDigitavel' width='100'>");
						
						
						
						if(imprimir_cod_barras){
							str.append(						codigoDeBarras.toString());
						}
						str.append("					</td>");
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						
						//Linha: Local Pagto., Uso Banco, Vencimentoborder-bottom
						str.append("	<tr>");    
						str.append("		<td colspan='3'>");
						str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0'>");
						str.append("				<tr>");
						
						str.append("					<td width='529' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Local de Pagamento</span><br />");
						str.append("   						Pagável em qualquer banco até o vencimento.");
						str.append("					</td>");
													        
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='194' class='border-bottom-fundo'>");
						str.append("						<span class='titulo_linhas'>Vencimento</span><br />");
						str.append(							"<strong>"+formatDate(boleto.getDatas().getVencimento())+"</strong>");
						str.append("					</td>");
						
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						
						//Linha: Cedente, Agência/Código Cedente
						str.append("	<tr>");
						str.append("		<td colspan='3'>");
						str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
						str.append("				<tr>");
						
						str.append("					<td width='529' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Beneficiário</span><br />");
						str.append("						"+boleto.getEmissor().getCedente()+" "+boleto.getCnpj()+"<br/>");
						str.append("					</td>");
						
						str.append("					<td width='1%' >");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
			        
						str.append("					<td width='194' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Agência / Código do Beneficiário</span><br />");
						str.append("						"+boleto.getEmissor().getAgenciaFormatado()+"."+boleto.getEmissor().getPostoBeneficiario()+"."+boleto.getEmissor().getContaCorrente()+"<br/>");
						str.append("					</td>");
						
						str.append("				</tr>");			
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						
						
						
						//Linha Endereço cedente, nosso numero
						str.append("	<tr>");
						str.append("		<td colspan='3'>");
						str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
						str.append("				<tr>");
						
						str.append("					<td width='120' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Data do Documento</span><br />");
						str.append(							formatDate(boleto.getDatas().getDocumento()));
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='120' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Número do Documento</span><br />");
						str.append(							boleto.getNumeroDoDocumento());
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='100' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Espécie Documento</span><br />");
						
						
						if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
							str.append("						DS");
						}else{
							str.append("						DM");
						}
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='80' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Aceite</span><br />");
						str.append("						NÃO");
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='75' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Data Processamento</span><br />");
						str.append(							formatDate(boleto.getDatas().getDocumento()));
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
													
						str.append("					<td width='194' class='border-bottom' height='20' >");
						str.append("						<span class='titulo_linhas'>Nosso Número</span><br />");
						str.append(							boleto.getNossoNumero());
						str.append("					</td>");
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						
						//Linha Data Doc. Ndoc, Especie, Aceite, Data Proc. Carteira, Moeda, Quantidade, Valor doc
						str.append("	<tr>");
						str.append("		<td colspan='3'>");
						str.append("			<table width='95%' border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
						str.append("				<tr>");
						
						str.append("					<td width='120' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Uso do banco</span><br />");					
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='120' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Carteira</span><br />");
						str.append(							"1");
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='100' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Espécie</span><br />");
						
						
						str.append("						REAL");
						
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						
						str.append("					<td width='80' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Quantidade</span><br />");
						str.append(						boleto.getQtd() != null ? boleto.getQtd() : "");
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='75' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>Valor</span><br />");
						str.append(						"");
						str.append("					</td>");
						
						str.append("					<td width='1%'>");
						str.append("						<img src='imagens/div_txt.gif'  width='6' height='18' />");
						str.append("					</td>");
						
						str.append("					<td width='191' class='border-bottom-fundo'>");
						str.append("						<span class='titulo_linhas'>(=)Valor do Documento</span><br />");
						str.append("						<strong> "+Real.formatDbToString(boleto.getValorBoleto().toString())+"</strong>");
						str.append("					</td>");
						str.append("				</tr>");
						str.append("			</table>");
						str.append("		</td>");
						str.append("	</tr>");
						
						str.append("	<tr>");
						
						str.append("		<td width='400' border='0' valign='top' >");
						str.append("			<span class='titulo_linhas'>Instruções (Todas as informações deste bloqueto são de exclusiva responsabilidade do beneficiário)</span><br />");
						str.append("<br />");
						
						
						
						if(CheckNdocUtil.checkNdocAcesso(boleto.getNumeroDoDocumento())){
							str.append("Mensalidade de Serviço de Comunicação Multimidia-SCM <br/> Período "+getDataMinus30Days(boleto.getDatas().getVencimento())+" a "+getData(boleto.getDatas().getVencimento())+"<br />");
							String[] codigos = boleto.getNumeroDoDocumento().split("/");
							str.append("Contrato Termo Adesao Nº: "+codigos[0]+"<br/>");												
						}
						
						for (String s : boleto.getInstrucoes()) {
							str.append(s);
						}					
						
//						str.append("<br />");
						
						for (String s : boleto.getDescricoes()) {
							str.append(s);
						}
						
						str.append("<br/>FAVOR PAGAR O BOLETO, NAO DEPOSITAR.<br/>");
						str.append("<br />");
						str.append("Nome do Pagador/CPF/CNPJ/Endereço<br />");
						str.append("<strong>"+boleto.getSacado().getNome()+" "+boleto.getSacado().getCpf()+"</strong><br />");
						str.append(boleto.getSacado().getEndereco()+" - "+boleto.getSacado().getBairro()+" - "+boleto.getSacado().getComplemento()+"<br />");
						String ref = boleto.getSacado().getRefencia() != null  && boleto.getSacado().getRefencia().length() > 50 ? boleto.getSacado().getRefencia().substring(0, 50) : boleto.getSacado().getRefencia();
						str.append(boleto.getSacado().getCep()+" - "+boleto.getSacado().getCidade()+"-"+boleto.getSacado().getUf() + " ("+ref+")");					
						
						str.append("		</td>");
						
						
						str.append("		<td width='150' border='0' valign='top' haling='center'>");
						str.append("			<br /><br />");
						
						String basepath = new File(".").getCanonicalPath();
						String nomeImg = basepath + "/src/imagens/uploads/cod_"+boleto.getCodBoleto().toString()+".png";
						
						
						
						Image imgCodNNumero = geraImagemDoCodigoDeBarrasParaNNumero(boleto.getCodBoleto().toString(), 30f);										
						File f = new File(nomeImg);
						
						
						try{						
							BufferedImage bi = toBufferedImage(imgCodNNumero, BufferedImage.TYPE_INT_ARGB);
							ImageIO.write(bi, "png", f);
						}catch(Exception e){
							e.printStackTrace();
						}
						
											
					
						str.append("<img src='"+nomeImg+"' /><br/>"+boleto.getCodBoleto().toString());
									
						
						
						str.append("		</td>");
							
						str.append("		<td  valign='top' border='0'>");
						
						str.append("			<table width='84%'  border='0' cellpadding='0' cellspacing='0' class='linhas_formatacao'>");
										
						str.append("				<tr>");
						
						str.append("					<td width='191' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>(-) Desconto / Abatimento</span><br />");
						str.append("						&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
					
						
						str.append("				<tr>");
						
						str.append("					<td width='191' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>(+) Juros / Multa</span><br />");
		
						
						str.append("						&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
						
										
						
						str.append("				<tr>");
						
						str.append("					<td width='191' class='border-bottom' height='20'>");
						str.append("						<span class='titulo_linhas'>(=) Valor Cobrado</span><br />");
						str.append("						&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
						str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
						
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
	str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("					</td>");
						
						str.append("				</tr>");
						
						

						
						
						str.append("				<tr>");
						
						str.append("					<td style='text-align:right; ' width='191' height:'100' >");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("			&nbsp;");
						str.append("						<img style='margin-top:30px;' src='"+logo_digital_bb+"' width='180' />");
						str.append("					</td>");
						
						str.append("				</tr>");
						
									
						
						str.append("			</table>");		
						
						str.append("		</td>");
						
						str.append("	</tr>");				
						
						str.append("</table>");
						
						str.append("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
						str.append("	<tr>");
						str.append("		<td class='border-bottom'>");
						str.append("			<br/>");
						str.append("			&nbsp;");
						str.append("		</td>");
						str.append("	</tr>");				
						str.append("</table>");
						
						
						str.append("<table border='0' cellpadding='0' cellspacing='0' width='70%'>");
						str.append("	<tr>");
						str.append("		<td class='aut_mec'>");
						str.append("			<br/>");
						str.append("			Autênticação Mecanica");
						str.append("		</td>");
						str.append("	</tr>");				
						str.append("</table>");
											
//						//get the XMLWorkerHelper Instance		
						XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
						//convert to PDF
						worker.parseXHtml(writer, doc,new StringReader(str.toString()));
									
							
						
							Image imagemDoCodigoDeBarras = GeradorDeImagemDoCodigoDeBarrasUtil.geraImagemDoCodigoDeBarrasPara(
									boleto.getBanco().geraCodigoDeBarras44(boleto), 55.00f);		
						
							com.itextpdf.text.Image barCode = com.itextpdf.text.Image.getInstance(toBufferedImage(imagemDoCodigoDeBarras, BufferedImage.TYPE_INT_ARGB), null);						
							barCode.scaleToFit(300f,100f);
							barCode.setSpacingAfter(500.0f);
							barCode.setSpacingBefore(100.0f);
							
							//IMPRIMIR CÓDIGO DE BARRAS SOMENTE PARA CONTRATOS QUE EMITEM NOTA FISCAL
							if(imprimir_cod_barras){
								
								doc.add(barCode);
							}
							
							
							//worker.parseXHtml(writer, doc, new StringReader("<br/>."));
							DottedLineSeparator line = new DottedLineSeparator();						
							line.setPercentage(97.0f);
							line.setOffset(-20.0f);
							
							
							if(i % 2 == 0){ 
								doc.add(line);
							}
							
							
						
							
													
							
						}
						
					}else{
						XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
						//convert to PDF
						worker.parseXHtml(writer, doc,new StringReader(str.toString()));
					}
					
					
		}catch(Exception e){
			if(doc != null && doc.isOpen() && doc.getPageNumber() > 0){
				doc.close(); 
			}
			
			e.printStackTrace();
		}finally{
			if(doc != null && doc.isOpen()){
				doc.close();
			}
		}	
	}

	public static Image geraImagemDoCodigoDeBarrasParaNNumero(String nNumero, float altura) {
		BarcodeInter25 bar = new BarcodeInter25();
		bar.setCode(nNumero);
		bar.setExtended(false);
		
		bar.setTextAlignment(Element.ALIGN_LEFT);
		bar.setBarHeight(altura);
		bar.setFont(null);
		bar.setX(0.73f);
		bar.setN(3);
		bar.setAltText(nNumero);
		
		return bar.createAwtImage(Color.BLACK, Color.WHITE);
	}
	
	private BufferedImage toBufferedImage(Image image, int type) {
		return generateBufferedImageFor(image, type);
	}
	
	private  BufferedImage generateBufferedImageFor(Image image, int type) {
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		BufferedImage result = new BufferedImage(w, h, type);
		Graphics2D g = result.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return result;
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	
	private String formatDate(Calendar data) {
		return sdf.format(data.getTime());
	}
	
	private String getDataMinus30Days(Calendar data) {

		DateTime d = new DateTime(data);
		return sdf.format(d.minusMonths(1).toDateTime().minusDays(1).toDate());
	}
	
	private String getData(Calendar data) {

		DateTime d = new DateTime(data);
		return sdf.format(d.toDate());
	}

	
	public InputStream getStream() {
		return new ByteArrayInputStream(baos.toByteArray());
	}
	
}
