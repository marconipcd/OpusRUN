import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import util.Real;
import util.StringUtil;
import domain.AcessoCliente;
import domain.Cfop;
import domain.ContasReceber;
import domain.NfeMestre;


public class runNFE {
	
	//6358  - SAPORE
	//6007  - COLEGIO ADVENTISTA
	//8249  - VIANA E MOURA
	//9386  - EMPAC
	//11834 - EMPAC
	//4393  - ACUMULADORES MOURA S A **
	//8890  - VALENCA CONSTRUCOES LTDA 
	//9007  - EVANDRO VALENCA BATISTA E CIA LTDA	
	//10222 - MILLENA COMERCIO VAREJISTA DE MOVEIS E ELETRO EIRELI
	//4937  - A. DE FRANCA RAMOS COUTO ME
	//10435 - SUPRAMAX  **
	//10550 - ACUMULADORES MOURA **
	//11085 - UNIVERSIDADE FEDERAL RURAL
	//11425 - ORDEM DOS ADVOGADOS DO BRASIL SECCAO DE PERNAMBUCO
	//11426 - ORDEM DOS ADVOGADOS DO BRASIL SECCAO DE PERNAMBUCO
	//8892 - SINDICATO DOS TRABALHADORES RURAIS DE B. JARDIM

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
				
		Query qcontratos = em.createQuery("select c from AcessoCliente c where c.id=10550 and  "
				+ "c.emitir_nfe_automatico = 'SIM' and "
				+ "c.status_2 != 'CANCELADO'  and c.cfop_nfe != null", AcessoCliente.class);
		List<AcessoCliente> lista_de_contratos = qcontratos.getResultList();
		
		em.getTransaction().begin();
		
		int i = 1;
		for (AcessoCliente contrato : lista_de_contratos) {

			if(i <= 2623){
				
				System.out.println(contrato.getId());
				SimpleDateFormat sdfAnoMes = new SimpleDateFormat("yyMM");
				ContasReceber boleto = null;
				
				String regexNova = "^"+contrato.getId().toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
				String regexAntiga = "^"+contrato.getId().toString()+"/[0-9]{2}/[0-9]{2}";
				String regexProrata = "^"+contrato.getId().toString()+"/PRORATA";
				
				Query qn = em.createNativeQuery("select * from contas_receber cr where "+				
						"status_2 != 'EXCLUIDO' "+			
					    "and n_doc REGEXP :rNova "+ 
						"and DATE_FORMAT(data_vencimento, '%y%m') =:anoMes and  cr.id=632152 "+
						
						"or "+
						"status_2 != 'EXCLUIDO' "+				
						"and n_doc REGEXP :rAntiga "+
						"and DATE_FORMAT(data_vencimento, '%y%m') =:anoMes and  cr.id=632152 "+
						
						"or "+
						"status_2 != 'EXCLUIDO' "+				
						"and n_doc REGEXP :rProrata "+
						"and DATE_FORMAT(data_vencimento, '%y%m') =:anoMes and  cr.id=632152 "
						
						, ContasReceber.class);
					
				qn.setParameter("rNova", regexNova);
				qn.setParameter("rAntiga", regexAntiga);
				qn.setParameter("rProrata", regexProrata);
				qn.setParameter("anoMes", "2202");
				
				if(qn.getResultList().size()  == 1){
					boleto = (ContasReceber)qn.getSingleResult();					
				}
				
				if(boleto != null && boleto.getStatus().equals("ABERTO")){
					if(contrato.getEmitir_nfe_c_boleto_aberto().equals("SIM") && contrato.getCfop_nfe() != null){
						boolean gerado = false;
						
						Query q = em.createQuery("select nfe From NfeMestre nfe where nfe.contrato =:contrato and nfe.contas_receber=:boleto", NfeMestre.class);
						q.setParameter("contrato", contrato);
						q.setParameter("boleto", boleto);
						
						if(q.getResultList().size() > 0){
							gerado=  true;
						}
						
						if(!gerado){
							
								if(boleto != null && boleto.getValor_titulo() != null && boleto.getBloqueado() == null || !boleto.getBloqueado().equals("S")){
								
										Cfop cfop = new Cfop();
										if(contrato != null && contrato.getCfop_nfe() != null){
											cfop = em.find(Cfop.class, contrato.getCfop_nfe());				
										}
															
										Double valor = Real.formatStringToDBDouble(boleto.getValor_titulo());
										String cod_iden  = StringUtil.md5(contrato.getCliente().getDoc_cpf_cnpj()+"21"+String.valueOf(valor)+"0,00"+"0,00");
										
										double vlr_desconto;
										double vlr_boleto = Real.formatStringToDBDouble(boleto.getValor_titulo());
										double vlr_plano  = Real.formatStringToDBDouble(contrato.getPlano().getValor());
																				
										if(vlr_boleto != vlr_plano){
											vlr_desconto = new Double("0.00");
										}else{
											vlr_desconto = Real.formatStringToDBDouble(contrato.getPlano().getDesconto());
										}
										
										NfeMestre nfeM = new NfeMestre( null,contrato.getCliente(),contrato ,boleto,cfop,new Date(),boleto.getData_vencimento(),	contrato.getContrato().getClasse_consumo(),"4","00",
												contrato.getCliente().getId().toString(),"21","000", cod_iden	, Real.formatStringToDBDouble(boleto.getValor_titulo()), 
												Real.formatStringToDBDouble(boleto.getValor_titulo())-Real.formatStringToDBDouble(contrato.getPlano().getDesconto()),	0,0,	0,	
												vlr_desconto,"N",	sdfAnoMes.format(boleto.getData_vencimento()),"1",contrato.getCliente().getTelefone1(),
												StringUtil.md5(contrato.getCliente().getDoc_cpf_cnpj()+contrato.getCliente().getDoc_rg_insc_estadual()+
												contrato.getCliente().getNome_razao()+"PE"+contrato.getContrato().getClasse_consumo()+"4"+"00"+
												contrato.getCliente().getId().toString()+new Date()+"21"+"000")+cod_iden+valor+0+0+0+0+"N"+sdfAnoMes.format(boleto.getData_vencimento())+"1", false);
																			
										em.persist(nfeM); 	
										
										i++;
										System.out.println("GERADO");
										System.out.println("BOLETO:"+boleto.getId().toString());
								}
						}
					}
				}else{
					boolean gerado = false;
					
					Query q = em.createQuery("select nfe From NfeMestre nfe where nfe.contrato =:contrato and nfe.contas_receber=:boleto", NfeMestre.class);
					q.setParameter("contrato", contrato);
					q.setParameter("boleto", boleto);
					
					if(q.getResultList().size() > 0){
						gerado=  true;
					}
					
					if(!gerado){
						
						Cfop cfop = new Cfop();
						if(contrato != null && contrato.getCfop_nfe() != null){								
							cfop = em.find(Cfop.class, contrato.getCfop_nfe());
						}
						
						if(boleto != null && boleto.getValor_titulo() != null && cfop != null){
							
							if(boleto.getBloqueado() == null || !boleto.getBloqueado().equals("S")){
								Double valor = Real.formatStringToDBDouble(boleto.getValor_titulo());
								String cod_iden  = StringUtil.md5(contrato.getCliente().getDoc_cpf_cnpj()+"21"+String.valueOf(valor)+"0,00"+"0,00");
								
								NfeMestre nfeM = new NfeMestre( null,contrato.getCliente(),contrato ,boleto,cfop,new Date(),boleto.getData_vencimento(),	contrato.getContrato().getClasse_consumo(),"4","00",
									contrato.getCliente().getId().toString(),"21","000", cod_iden	, Real.formatStringToDBDouble(boleto.getValor_titulo()), 
									Real.formatStringToDBDouble(boleto.getValor_titulo())-Real.formatStringToDBDouble(contrato.getPlano().getDesconto()),	0,0,	0,	
									Real.formatStringToDBDouble(contrato.getPlano().getDesconto()),"N",	sdfAnoMes.format(boleto.getData_vencimento()),"1",contrato.getCliente().getTelefone1(),
									StringUtil.md5(contrato.getCliente().getDoc_cpf_cnpj()+contrato.getCliente().getDoc_rg_insc_estadual()+
									contrato.getCliente().getNome_razao()+"PE"+contrato.getContrato().getClasse_consumo()+"4"+"00"+
									contrato.getCliente().getId().toString()+new Date()+"21"+"000")+cod_iden+valor+0+0+0+0+"N"+sdfAnoMes.format(boleto.getData_vencimento())+"1", false);
								
								em.persist(nfeM); 
								
								i++;
								System.out.println("GERADO");
								System.out.println("BOLETO:"+boleto.getId().toString());
							}
						}
					}
				}
			}else{
				break;
				
			}
		}
		System.out.println("Rotina de notas fiscais concluído!");
		em.getTransaction().commit();
		
		}
		
	

}


