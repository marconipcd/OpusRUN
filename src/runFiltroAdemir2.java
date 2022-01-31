import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.Days;

import domain.AcessoCliente;
import domain.Cliente;
import domain.ContasReceber;
import domain.GrupoOse;
import domain.Ose;


public class runFiltroAdemir2 {

	
public static void main(String[] args){
		
		
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
		
			Query q = em.createQuery("select a from AcessoCliente a where "				
					+ "a.status_2 = 'ENCERRADO' "
					//+ "a.cliente.nome_razao not like '%teste%' and  "
					//+ "a.cliente.credito_cliente not like '%INADIMPL%'"					
					, AcessoCliente.class);

			
			
			List<AcessoCliente> contratosEncerrados = q.getResultList();
			
			StringBuilder sb = new StringBuilder();		
			//sb.append(new String("Codigo: Nome: Telefone1: Telefone2: Celular1: Celular2: Email: Email Alternativo: Endereco: Bairro:Numero: Cidade: Dias de Inadiplencia: Negativado Conosco:Credito Cliente"));
			sb.append(new String("Codigo: Nome: Telefone1: Telefone2: Celular1: Celular2: Email: Email Alternativo: Tipo Contrato: Plano: Valor: Concentrador: Regime: Endereco: Bairro:Numero: Cidade: Dias de Inadiplencia: Negativado Conosco:Credito Cliente:Cod Contrato: Motivo Cancelamento"));
			sb.append(System.getProperty("line.separator"));

			
			
			File f = new File("D:\\filtroAdemir4.csv");
			BufferedWriter br = new BufferedWriter(new FileWriter(f));  
			 					
				if(f.canWrite()){
				
				for (AcessoCliente a : contratosEncerrados) {
					
					
					

					
					Query q4 = em.createQuery("select a from AcessoCliente a where a.cliente=:c and a.status_2!='ENCERRADO'", AcessoCliente.class);
					q4.setParameter("c", a.getCliente());
					
					if(q4.getResultList().size()==0){
						
						//-----OS DE CANCELAMENTO						
						GrupoOse grupo = em.find(GrupoOse.class, 4);
						Query q5 = em.createQuery("select o from Ose o where "
								+ "o.grupo =:grupo AND "
								+ "o.contrato =:contrato  "
								//+ "o.tipo_subgrupo.nome not like 'INADIMPLENTE' AND "
								//+ "o.tipo_subgrupo.nome not like 'EMPRESA DESATIVADA' AND "
								//+ "o.tipo_subgrupo.nome not like 'MUDOU DE CIDADE' AND "
								//+ "o.tipo_subgrupo.nome not like 'SEM VIABILIDADE' "
								, Ose.class);						
						q5.setParameter("grupo", grupo);
						q5.setParameter("contrato", a);
						
						if(q5.getResultList().size() == 1){
							
						
							
							
						
					
						sb.append(a.getCliente().getId()+":");
						sb.append(a.getCliente().getNome_razao()+":");
						sb.append(a.getCliente().getTelefone1()+":");
						sb.append(a.getCliente().getTelefone2()+":");
						sb.append(a.getCliente().getCelular1()+":");
						sb.append(a.getCliente().getCelular2()+":");
						sb.append(a.getCliente().getEmail()+":");
						sb.append(a.getCliente().getEmailAlternativo()+":");
						sb.append(a.getContrato().getNome()+":");
						sb.append(a.getPlano().getNome()+":");
						sb.append(a.getPlano().getValor()+":");
						sb.append(a.getBase() != null  && a.getBase().getIdentificacao() != null ? a.getBase().getIdentificacao()+":" : "NAO ENCONTRADO"+":");
						sb.append(a.getRegime()+":");
						sb.append(a.getCliente().getEndereco_principal() != null && a.getCliente().getEndereco_principal().getEndereco() != null ? a.getCliente().getEndereco_principal().getEndereco()+":" : "" +":");
						sb.append(a.getCliente().getEndereco_principal() != null && a.getCliente().getEndereco_principal().getBairro() != null ? a.getCliente().getEndereco_principal().getBairro()+":" : "" +":");
						sb.append(a.getCliente().getEndereco_principal() != null && a.getCliente().getEndereco_principal().getNumero() != null ? a.getCliente().getEndereco_principal().getNumero()+":" : "" +":");
						sb.append(a.getCliente().getEndereco_principal() != null && a.getCliente().getEndereco_principal().getCidade() != null ? a.getCliente().getEndereco_principal().getCidade()+":" : "" +":");
						
						
						//---Dias de Inadimplencia
						Query q2 = em.createQuery("select c from ContasReceber c where c.cliente=:cliente and c.status='ABERTO' and c.data_vencimento <:dataHoje order by c.id asc", ContasReceber.class);
						q2.setParameter("cliente", a.getCliente());
						q2.setParameter("dataHoje", new Date());
											
						if(q2.getResultList().size()>0){
							ContasReceber boletoAtrasado = (ContasReceber )q2.getResultList().toArray()[0];
							Date dt1 =  boletoAtrasado.getData_vencimento();
							Date dt2 = new Date();
							
							Integer qtdDiasAtrasado = Days.daysBetween(new DateTime(dt1), new DateTime(dt2)).getDays();
							
							sb.append(qtdDiasAtrasado.toString()+":");	
						}else{
							sb.append("0:");
						}
						//---Dias de Inadimplencia
						
						
						
						//---Negativado Conosco
						Query q3 = em.createQuery("select c from ContasReceber c where c.cliente=:cliente and c.status LIKE'%NEGATIVADO%'",ContasReceber.class);
						q3.setParameter("cliente", a.getCliente());
						
						if(q3.getResultList().size()>0){
							sb.append(q3.getResultList().size()+" boletos:");	
						}else{
							sb.append("NAO:");
						}
						
						//-----Negativado Conosco
						
						
						sb.append(a.getCliente().getCredito_cliente() != null ? a.getCliente().getCredito_cliente()+":" : "NAO INFORMADO"+":");
						
						
						sb.append(a.getId()+":");
						
						
						
						
						if(q5.getResultList().size()>0){
							
							if(q5.getResultList().size() == 1){
							
								Ose ordemServico = (Ose)q5.getSingleResult();
								
								sb.append(ordemServico.getTipo_subgrupo().getNome()+":" );
							}else{
								sb.append("MAIS DE UMA OSE DE CANCELAMENTO"+":");		
								
							}
							
							
						}else{							
							sb.append("OS NAO ENCONTRADA"+":");
						}
						
						
						
						
						sb.append(System.getProperty("line.separator"));	
					
						
						}
						
						
					}
					
				}
				
				br.write(sb.toString());  
				br.close();		
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
