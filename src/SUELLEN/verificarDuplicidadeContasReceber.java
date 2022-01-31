package SUELLEN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.ContasReceber;

public class verificarDuplicidadeContasReceber {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args){
		
		EntityManager em = emf.createEntityManager();
		
			
		Query q = em.createQuery("select a from AcessoCliente a where a.status_2 != 'ENCERRADO'", AcessoCliente.class);
		List<AcessoCliente> contratos_ativos = q.getResultList();
		
		List<String> clientesComBoletosDuplicados = new ArrayList<>();
		List<String> boletos_duplicados = new ArrayList<>();
		for (AcessoCliente contrato : contratos_ativos) {
			
			String codAcesso = contrato.getId().toString();
			
			String regexNova = "^"+codAcesso+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
			String regexAntiga = "^"+codAcesso.toString()+"/[0-9]{2}/[0-9]{2}";
			String regexProrata= "PRORATA/"+codAcesso.toString();
						
			Query q1 = em.createNativeQuery("select * from contas_receber cr where "+				
					"cr.status_2 != 'EXCLUIDO' and cr.data_emissao >= '2020-09-01' "+
					"and cr.n_doc REGEXP :rNova  "+
									
					"or "+
					"cr.status_2 != 'EXCLUIDO' and cr.data_emissao >= '2020-09-01'  "+					
					"and cr.n_doc REGEXP :rAntiga "+
										
					"or "+
					"cr.status_2 != 'EXCLUIDO' and cr.data_emissao >= '2020-09-01'  "+					
					"and cr.n_doc REGEXP :rProrata "
					, ContasReceber.class);
					
			
			q1.setParameter("rNova", regexNova);
			q1.setParameter("rAntiga", regexAntiga);
			q1.setParameter("rProrata", regexProrata);
			
			
			List<ContasReceber> boletos = q1.getResultList();
			List<Date> vencimentos = new ArrayList<>();
			for (ContasReceber boleto: boletos) {
				vencimentos.add(boleto.getData_vencimento());
			}
			
			
			for (Date date : vencimentos) {				
				int qtd = Collections.frequency(vencimentos, date);
				if(qtd > 1){
					//System.out.println("Contrato:"+contrato.getId().toString());
					//System.out.println("| Cliente:"+contrato.getCliente().getNome_razao());
					boletos_duplicados.add("Contrato:"+contrato.getId().toString()+"| Cliente:"+contrato.getCliente().getNome_razao());					
				}
			}
		
			System.out.println(contrato.getId().toString());
		}
		
		for (String string : boletos_duplicados) {
			System.out.println(string);
		}
		
		
	}
}
