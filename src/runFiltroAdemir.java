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


public class runFiltroAdemir {

	
	public static void main(String[] args){
		
		
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
		
			Query q = em.createQuery("select a from AcessoCliente a where "
					+ "a.base.identificacao LIKE '%FTTH%' AND "
					+ "a.plano.nome NOT LIKE '%ULTRA MAIS%' AND "
					+ "a.status_2 != 'PENDENTE_INSTALACAO' AND "
					+ "a.status_2 != 'ENCERRADO' AND "
					+ "a.plano.nome NOT LIKE '%ESCOLA%' AND "					
					+ "a.contrato.nome NOT LIKE '%DEDICADO%' AND "
					+ "a.contrato.nome NOT LIKE '%GRATIS%'", AcessoCliente.class);
			
			
			
			List<AcessoCliente> contratosEncerrado = q.getResultList();
			
			StringBuilder sb = new StringBuilder();		
			sb.append(new String("Codigo: Nome: Telefone1: Telefone2: Celular1: Celular2: Email: Email Alternativo: Tipo Contrato: Plano: Valor: Concentrador: Regime: Endereco: Bairro:Numero: Cidade: Dias de Inadiplencia: Negativado Conosco:Credito Cliente"));
			sb.append(System.getProperty("line.separator"));

			
			
			File f = new File("D:\\filtroAdemir.csv");
			BufferedWriter br = new BufferedWriter(new FileWriter(f));  
			 					
				if(f.canWrite()){
				
				for (AcessoCliente c : contratosEncerrado) {
					
					sb.append(c.getId()+":");
					sb.append(c.getCliente().getNome_razao()+":");
					sb.append(c.getCliente().getTelefone1()+":");
					sb.append(c.getCliente().getTelefone2()+":");
					sb.append(c.getCliente().getCelular1()+":");
					sb.append(c.getCliente().getCelular2()+":");
					sb.append(c.getCliente().getEmail()+":");
					sb.append(c.getCliente().getEmailAlternativo()+":");
					sb.append(c.getContrato().getNome()+":");
					sb.append(c.getPlano().getNome()+":");
					sb.append(c.getPlano().getValor()+":");
					sb.append(c.getBase().getIdentificacao()+":");
					sb.append(c.getRegime()+":");
					sb.append(c.getCliente().getEndereco_principal().getEndereco()+":");
					sb.append(c.getCliente().getEndereco_principal().getBairro()+":");
					sb.append(c.getCliente().getEndereco_principal().getNumero()+":");
					sb.append(c.getCliente().getEndereco_principal().getCidade()+":");
					
					//---Dias de Inadimplencia
					Query q2 = em.createQuery("select c from ContasReceber c where c.cliente=:cliente and c.status='ABERTO' and c.data_vencimento <:dataHoje order by c.id asc", ContasReceber.class);
					q2.setParameter("cliente", c.getCliente());
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
					q3.setParameter("cliente", c.getCliente());
					
					if(q3.getResultList().size()>0){
						sb.append(q3.getResultList().size()+" boletos:");	
					}else{
						sb.append("NAO:");
					}
					
					//-----Negativado Conosco
					
					
					sb.append(c.getCliente().getCredito_cliente() != null ? c.getCliente().getCredito_cliente() : "NAO INFORMADO"+":");
					
					
					sb.append(System.getProperty("line.separator"));			
					
				}
				
				br.write(sb.toString());  
				br.close();		
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
