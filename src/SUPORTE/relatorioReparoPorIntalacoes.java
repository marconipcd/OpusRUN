package SUPORTE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;

import domain.Ose;

public class relatorioReparoPorIntalacoes {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		//Selecionar as instalações
		Query q = em.createQuery("select o from Ose o where o.grupo = 2", Ose.class);
		List<Ose> instalacoes = q.getResultList();
		
		List<Ose> instalacoes_com_reparo = new ArrayList<>();
		for (Ose i : instalacoes) {
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								
				Query q1 = em.createQuery("select o from Ose o where o.contrato =:c and o.grupo=9 and o.data_abertura<=:da", Ose.class);
				q1.setParameter("c", i.getContrato());
				q1.setParameter("da", new DateTime(sdf.parse(sdf.format(i.getData_abertura()))).plusDays(120).toDate());
				
				if(q1.getResultList().size() > 0){
					for (Ose ose : (List<Ose>)q1.getResultList()) {					
						instalacoes_com_reparo.add(ose);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		for (Ose ose : instalacoes_com_reparo) {
			System.out.println(ose.getId()+" | "+ose.getData_abertura()+" | "+ose.getContrato().toString()+" | "+ose.getSubgrupo().getNome()+" | "+ose.getTipo_subgrupo().getNome());
		}
	}
}
