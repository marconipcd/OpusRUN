import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.joda.time.ReadableInstant;

import domain.Produto;


public class runRelatorioProdutos {

static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public static void main(String[] args) {
		
		try{
		
			FileWriter file = new FileWriter("D:\\Produtos.txt"); 
			PrintWriter gravaArq = new PrintWriter(file);
			
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("select p from Produto p where p.empresaId=1 and p.qtdEstoque > 0 and p.status = 'ATIVO' or p.empresaId=5 and p.qtdEstoque > 0 and p.status = 'ATIVO' order by p.nome asc", Produto.class);
			
			for (Produto p  : (List<Produto>)q.getResultList()) {		
				
				Query q3VendaCabecalho = em.createNativeQuery("SELECT c.ID, c.DATA_PV FROM ecf_pre_venda_cabecalho c, ecf_pre_venda_detalhe d WHERE "
						+ "d.ID_ECF_PRE_VENDA_CABECALHO = c.ID AND "
						+ "c.SITUACAO = 'F' AND "						
						+ "d.ID_PRODUTO =:p "
						+ "ORDER BY c.ID DESC LIMIT 0,1");
				q3VendaCabecalho.setParameter("p", p.getId());
	
				if(q3VendaCabecalho.getResultList().size() == 1){
					Object[] result = (Object[])q3VendaCabecalho.getSingleResult();
					
					Date dataUltimaVenda = (Date)result[1];
					Date dataHoje = new Date();
					Integer dias = Days.daysBetween(new DateTime(dataUltimaVenda), new DateTime(dataHoje)).getDays();
					
					
					//System.out.println();
					gravaArq.printf(p.getEmpresaId().getId().toString()+" | "+p.getId().toString()+" | "+p.getNome()+" | "+p.getCusto_total()+" | "+p.getValorVenda()+" | "+p.getQtdEstoque()+" | "+result[1]+" | "+dias+"%n");
				}	
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
