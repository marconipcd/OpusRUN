import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;

import util.DataUtil;
import util.EmailUtil;
import util.ImprimirBoletoFatura;
import util.Real;
import util.boletos.boleto.Banco;
import util.boletos.boleto.Boleto;
import util.boletos.boleto.Emissor;
import util.boletos.boleto.Sacado;
import util.boletos.boleto.bancos.BancoDoBrasil;
import util.boletos.boleto.bancos.Sicredi;
import br.com.caelum.stella.boleto.Datas;
import dao.ContasReceberDAO;
import dao.OseDAO;
import dao.OsiDAO;
import dao.PlanoAcessoDAO;
import domain.Cliente;
import domain.ContaBancaria;
import domain.ContasReceber;
import domain.ControleTitulo;
import domain.Empresa;
import domain.Endereco;
import domain.Ose;
import domain.Osi;
import domain.ParametrosBoleto;
import domain.PlanoAcesso;


public class runLembrete {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public static void main(String[] args) {
									
		List<ContasReceber> boletos = buscarTitulosAbertosVencidosDeAcessoPorContrato();			
					
		if(boletos != null){
			int i = 0;
			for (ContasReceber boleto : boletos) {
				
				//if(i < 1){
					//if(contasReceber2.getCliente().getEmail() != null && !contasReceber2.getCliente().getEmail().equals("") && EmailUtil.validate(contasReceber2.getCliente().getEmail())){
						enviarEmail(boleto);					
						System.out.println(boleto.getCliente().getNome_razao());
						i++;
					//}
				//}else{
				///	break;
			   //  }
			}
		}
			
	}
		
	public static void enviarEmail(ContasReceber boleto){
		
		String pathFull = "";			
		EntityManager em = emf.createEntityManager();			
		Empresa empresa = em.find(Empresa.class, boleto.getEmpresa_id());
		
		try {
			
			String basepath = new File(".").getCanonicalPath();			
			String nomeArquivo = "BOLETO_"+boleto.getId().toString()+".pdf";
						
			InputStream inputStream = new ImprimirBoletoFatura(getBoleto(boleto), empresa, boleto.getCliente(), boleto).getStream();
			OutputStream outputStream = null; 

			try {							
				pathFull = basepath+"/src/boletos/"+nomeArquivo;
				outputStream = new FileOutputStream(new File(pathFull));
		 
				int read = 0;
				byte[] bytes = new byte[1024];
		 
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
		 
				System.out.println("Done!");		 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}		 
				}
			}
			
			Cliente cliente =boleto.getCliente();
			if(cliente.getEmail() != null && !cliente.getEmail().equals("") && EmailUtil.validate(cliente.getEmail())){
				
				boolean check = EmailUtil.Send(boleto, cliente.getNome_razao(),"marconipcd@gmail.com", pathFull);					
				//boolean check = EmailUtil.Send(boleto, cliente.getNome_razao(),cliente.getEmail(), pathFull);
				
				if(check){
					System.out.println("ENVIADO");
				}else{
					System.out.println("NÃO ENVIADO");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}					
	}
	
	public static Boleto getBoleto(ContasReceber cr){
		
		EntityManager em = emf.createEntityManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String controle =  cr.getControle();
		Query qControle = em.createQuery("select c from ControleTitulo c where c.nome=:nome and c.empresa_id =:empresa", ControleTitulo.class);
		qControle.setParameter("nome", controle);
		qControle.setParameter("empresa",cr.getEmpresa_id());
		
		ContaBancaria cb = null;
		
		Integer anoEmissao = Integer.parseInt(cr.getData_emissao().toString().substring(0, 4).toString());
		Integer mesEmissao = Integer.parseInt(cr.getData_emissao().toString().substring(5, 7).toString());
		Integer diaEmissao = Integer.parseInt(cr.getData_emissao().toString().substring(8, 10).toString());
	
		Integer anoVencimento = Integer.parseInt(cr.getData_vencimento().toString().substring(0, 4).toString()); 
		Integer mesVencimento = Integer.parseInt(cr.getData_vencimento().toString().substring(5, 7).toString()); 
		Integer diaVencimento = Integer.parseInt(cr.getData_vencimento().toString().substring(8, 10).toString());
	
		String nossoNumero = "";

		if(cr.getN_numero() != null){
			 nossoNumero = cr.getN_numero();
	        	
	        	if(cr.getControle().equals("ACESSO-PRE") || 
	        			cr.getControle().equals("ACESSO-POS") || 
	        			cr.getControle().equals("ALUGUEL") ||
	        			cr.getControle().equals("ASSISTENCIA")){
	        		
	        		//banco= new BancoDoBrasil();
	        		cb = em.find(ContaBancaria.class, 2);
	        		
	        	}else{
	        		cb = em.find(ContaBancaria.class, 2);
	        	}
	        }
	        
	        if(cr.getN_numero_sicred() != null){
	        	nossoNumero = cr.getN_numero_sicred();
	        	
	        	if(cr.getControle().equals("ACESSO-PRE") || 
	        			cr.getControle().equals("ACESSO-POS") || 
	        			cr.getControle().equals("ALUGUEL") ||
	        			cr.getControle().equals("ASSISTENCIA")){
	        		
	        		//banco= new Sicredi();
	        		cb = em.find(ContaBancaria.class, 5);
	        		
	        	}else{
	        		cb = em.find(ContaBancaria.class, 2);
	        	}
	        }
	        
	        
	        if(cb == null){
	        	cb = em.find(ContaBancaria.class, 2);
	        }
		
		
		String nomeCliente = cr.getCliente().getNome_razao();
		String nDoc =  cr.getN_doc();

	
		Query qPb = em.createQuery("select pb from ParametrosBoleto pb where pb.cliente_id = :codCliente", ParametrosBoleto.class);
		qPb.setParameter("codCliente",cr.getCliente().getId());			
		ParametrosBoleto pb = null;
		boolean cobrarTaxa = true;
		if(qPb.getResultList().size() > 0){
			pb = (ParametrosBoleto) qPb.getSingleResult();
			
			if(!pb.getCobrar_taxa_bancaria()){
				cobrarTaxa = false;
			}
		}
		
		Double vlrBoleto = null; 
		Double valorBoleto = null;
		
		boolean taxBoleto = true;
		
		try{
			//ContasReceber cr = ContasReceberDAO.find(Integer.parseInt(tb.getItem(o).getItemProperty("Cod.").getValue().toString()));									
			String codContrato = cr.getN_doc().split("/")[0].toString();									
			PlanoAcesso oPlanoBoleto = PlanoAcessoDAO.find(cr.getPlano_contrato());
			taxBoleto =oPlanoBoleto != null  &&  oPlanoBoleto.getTaxa_boleto().equals("NAO") ? false : true;		
		}catch(Exception e){
			
		}
		
		if(cobrarTaxa){
			if(!taxBoleto){
				cobrarTaxa = false;
			}
		}
		
		if(cobrarTaxa){
			vlrBoleto = Double.parseDouble(Real.formatStringToDB(cr.getValor_titulo()));
			valorBoleto = vlrBoleto + new Double(cb.getTaxa_boleto());										
		}else{
			valorBoleto = Double.parseDouble(Real.formatStringToDB(cr.getValor_titulo()));										
		}

		//Sacado
		Cliente sacadoCliente = cr.getCliente();
		
		Endereco enderecoSacado = null;

		String[] os = nDoc.split("OS");
		Ose ose =null;	
		Osi osi =null;	
		if(os.length > 1 && controle.equals("SERVICO")){
			ose = OseDAO.find(Integer.parseInt(os[1].split("-")[0]));
		}else if(os.length > 1 && controle.equals("ASSISTENCIA")){
			osi = OsiDAO.find(Integer.parseInt(os[1]));
		}
		
		if(!ContasReceberDAO.allowNdocManual(nDoc)){
		//	String codContrato = nDoc.split("/")[0].toString();
			enderecoSacado = sacadoCliente.getEndereco_principal();
			
		}else if(ose!=null){
			//enderecoSacado = ose.getEnd();
		}else if(osi!=null){
			enderecoSacado = osi.getEnd();										
		}else{
			//Endereco
			Query qEndereco = em.createQuery("select e from Endereco e where e.clientes =:cliente and e.principal =:principal", Endereco.class);
			qEndereco.setParameter("cliente", sacadoCliente);
			qEndereco.setParameter("principal", true);
			
			if(qEndereco.getResultList().size() == 1){
				enderecoSacado = (Endereco)qEndereco.getSingleResult();
			}
		}
		
		if(sacadoCliente != null && enderecoSacado != null){

			Datas datas = Datas.novasDatas()
					.comDocumento(diaEmissao, mesEmissao, anoEmissao)
					.comProcessamento(Calendar.getInstance().get(Calendar.DATE), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR))
					.comVencimento(diaVencimento, mesVencimento, anoVencimento);  

			Emissor emissor= null; 
			
			
			if(cb != null && cb.getId() == 5){
				
				Empresa empresa = em.find(Empresa.class, cr.getEmpresa_id());
			
			//sicred
				emissor = Emissor.novoEmissor()  
			        .comCedente(empresa.getRazao_social())  
			        .comAgencia(Integer.parseInt(cb.getAgencia_banco())).comDigitoAgencia('8')  
			        .comContaCorrente(cb.getCod_beneficiario())  
			        .comNumeroConvenio(cb.getConvenio() != null && !cb.getConvenio().equals("") ? Integer.parseInt(cb.getConvenio()) : 0).comDigitoContaCorrente('8')									           
			        .comCarteira(cb.getCarteira() != null && !cb.getCarteira().equals("") ? Integer.parseInt(cb.getCarteira()) : 0)  
			        .comNossoNumero(nossoNumero)
			        .comPostoBeneficiario(cb.getPosto_beneficiario())
			        .comEndereco(empresa.getEndereco()+", "+empresa.getNumero()+" "+empresa.getBairro()+" - "+empresa.getCidade()+" - "+empresa.getUf());
			
			
			}
			
			
			
			
			
			if(cb != null && cb.getId() == 	2){
			//bb
				Empresa empresa = em.find(Empresa.class, cr.getEmpresa_id());
			emissor = Emissor.novoEmissor()  
		            .comCedente(empresa.getRazao_social())  
		            .comAgencia(Integer.parseInt(cb.getAgencia_banco())).comDigitoAgencia('8')  
		            .comContaCorrente(cb.getN_conta())  
		            .comNumeroConvenio(Integer.parseInt(cb.getConvenio())).comDigitoContaCorrente('8')									           
		            .comCarteira(Integer.parseInt(cb.getCarteira()))  
		            .comNossoNumero(nossoNumero)
		            .comEndereco(empresa.getEndereco()+", "+empresa.getNumero()+" "+empresa.getBairro()+" - "+empresa.getCidade()+" - "+empresa.getUf());  
			}
			
			
			

	        Sacado sacado = Sacado.novoSacado()  
	        		.comNome(nomeCliente)  
	        		.comCpf(sacadoCliente.getDoc_cpf_cnpj())  
	        		.comEndereco(enderecoSacado.getEndereco()+","+enderecoSacado.getNumero())  
	        		.comBairro(enderecoSacado.getBairro())  
	        		.comCep(enderecoSacado.getCep())  
	        		.comCidade(enderecoSacado.getCidade())  
	        		.comUf(enderecoSacado.getUf())  
	        		.comReferencia(enderecoSacado.getReferencia())
	        		.comComplemento(enderecoSacado.getComplemento());

	        
	        Banco banco = null;

	        String n_numero = "";
	        if(cr.getN_numero() != null){
	        	n_numero = cr.getN_numero();
	        	
	        	
	        	if(cr.getControle().equals("ACESSO-PRE") || 
	        			cr.getControle().equals("ACESSO-POS") || 
	        			cr.getControle().equals("SERVICO")|| 
	        			cr.getControle().equals("PRODUTO")||								        			
	        			cr.getControle().equals("ALUGUEL") ||
	        			cr.getControle().equals("ASSISTENCIA")){
	        		
	        		banco= new BancoDoBrasil();
	        		cb = em.find(ContaBancaria.class, 2);
	        		
	        	}
	        }
	        
	        if(cr.getN_numero_sicred() != null){
	        	n_numero = cr.getN_numero_sicred();
	        	
	        	if(cr.getControle().equals("ACESSO-PRE") || 
	        			cr.getControle().equals("ACESSO-POS") || 
	        			cr.getControle().equals("SERVICO") ||								        			
	        			cr.getControle().equals("PRODUTO")||								        			
	        			cr.getControle().equals("ALUGUEL") ||
	        			cr.getControle().equals("ASSISTENCIA")){
	        		
	        		banco= new Sicredi();
	        		cb = em.find(ContaBancaria.class, 5);
	        		
	        	}
	        }
	        
	        
	        if(banco == null){
	        	banco= new BancoDoBrasil();
       		cb = em.find(ContaBancaria.class, 2);
	        }

	        Empresa empresa = em.find(Empresa.class, cr.getEmpresa_id());
	        
			Boleto boleto1 = Boleto.novoBoleto()
					.comCodBoleto(cr.getId())
		            .comBanco(banco)  
		            .comDatas(datas)						              
		            .comEmissor(emissor)  
		            .comSacado(sacado)  
		            .comValorBoleto(valorBoleto)  
		            .comNumeroDoDocumento(nDoc)  
		            .comInstrucoes(cb.getInstrucoes1(), cb.getInstrucoes2(), cb.getInstrucoes3())							            
		            .comDescricoes(cb.getDemonstrativo1(), cb.getDemonstrativo2(), cb.getDemonstrativo3())
		            .comNossoNumero(n_numero)
		            .comContaBancaria(cb) 
		            .comQtd(cr.getQuantidade())
		            .comCnpj(empresa.getCnpj())
		            .comControle(cr.getControle());		
			
			boleto1.setStatus(cr.getStatus()); 
			
			return boleto1;
		}
		
		
		return null;
		
	}
	public static List<ContasReceber> buscarTitulosAbertosVencidosDeAcessoPorContrato(){
		
		EntityManager em = emf.createEntityManager();
		
		String regexProrata = "[0-9]/PRORATA";
		String regexNova = "[0-9]/[0-9]{2}-[0-9]{2}/[0-9]{2}";
		//String regexAntiga = "[0-9]/[0-9]{2}/[0-9]{2}";
		
		Query qn = em.createNativeQuery("select * from contas_receber cr where " +				
				"cr.status_2 ='ABERTO' " +				
				"and cr.n_doc REGEXP :rNova " +
				"and cr.data_vencimento = :dataVencimento "+			
				 "ORDER BY cr.data_vencimento ASC ",
				
				ContasReceber.class);
		
		//qn.setParameter("rNovaProrata", regexProrata);
		qn.setParameter("rNova", regexNova);
		//qn.setParameter("rAntiga", regexAntiga);
		qn.setParameter("dataVencimento", "2021-01-27");
		
//		Query qn = em.createNativeQuery("SELECT * FROM contas_receber cr WHERE "
//				+ "cr.status_2 ='ABERTO' AND "
//				+ "cr.n_doc REGEXP '[0-9]/[0-9]{2}-[0-9]{2}/[0-9]{2}' AND "
//				+ "cr.data_vencimento =:vencimento "
//				+ "ORDER BY cr.data_vencimento ASC",ContasReceber.class);
//		
//		qn.setParameter("vencimento", DataUtil.parseDataUs(new DateTime().plus(10).toDate()));
		
		if(qn.getResultList().size()>0){
			return  qn.getResultList();
		}
		
		return null;
	}
}
