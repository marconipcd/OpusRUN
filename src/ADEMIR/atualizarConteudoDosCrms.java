package ADEMIR;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Crm;
import domain.CrmAssunto;

public class atualizarConteudoDosCrms {

	public static void main(String[] args) {
		
//		StringBuilder sb_reparo = new StringBuilder();
//		
//		sb_reparo.append("Olá, aqui é Hevillyn da empresa DIGITAL,"); 
//		sb_reparo.append("\n\n");
//		sb_reparo.append("Sr. \n"); 
//		sb_reparo.append("Atualmente realizamos um reparo em na Internet em seu endereço. \n");
//		sb_reparo.append("Poderia nos contar como foi a sua experiência conosco? \n");
//		sb_reparo.append("É bem rapidinho! Basta você clicar no link abaixo e você será redirecionado para a pesquisa: \n");
//		sb_reparo.append("\n");
//		sb_reparo.append("https://docs.google.com/forms/d/185dNCFzzVehD1VKbby-9KJSwofSmFOfPB-FBayawVlc/edit?ts=61af5644");
//		sb_reparo.append("\n\n");
//		sb_reparo.append("Alguns benefícios de ser cliente DIGITAL:");
//		sb_reparo.append("\n");
//		sb_reparo.append("- Se indicar um amigo, e ele fechar contrato conosco, o senhor ganha 50% de desconto na próxima mensalidade! Basta acessar nosso site, ir na área do cliente informar seu CPF, e embaixo mostrará 'indique um amigo', o senhor indicando e ele fechando contrato ganhará o desconto!!");
//		sb_reparo.append("\n");
//		sb_reparo.append("- E a Digital ainda tem parcerias com algumas empresas, e o senhor como cliente pode estar ganhando desconto, basta verificar em nosso Instagram quais são elas! ");
//		sb_reparo.append("\n");
//		sb_reparo.append("- As opções de pagamento são várias: você pode pagar direto na DIGITAL Belo Jardim, ou pode pagar direto na nossa central do assinante via Cartão de Crédito ou PIX, ou se quiser pode gerar um boleto. ");		
//		sb_reparo.append("\n\n");	
//		sb_reparo.append("Obrigado por escolher a DIGITAL!");
		
		
		StringBuilder sb_instalacao = new StringBuilder();
			
		sb_instalacao.append("Olá, aqui é Hevillyn da empresa DIGITAL, "); 
		sb_instalacao.append("\n\n");
		sb_instalacao.append("Sr. \n"); 
		sb_instalacao.append("Atualmente realizamos uma Instalação de Internet em seu endereço. \n");
		sb_instalacao.append("Poderia nos contar como foi a sua experiência conosco? \n");
		sb_instalacao.append("É bem rapidinho! Basta você clicar no link abaixo e você será redirecionado para a pesquisa: \n");
		sb_instalacao.append("\n");
		sb_instalacao.append("https://docs.google.com/forms/d/1KCTr7xprvLI5-r_4Lfjq7oTik3aXo_cpCN5rT5rvHaE/edit?ts=61af5630");
		sb_instalacao.append("\n\n");
		sb_instalacao.append("Alguns benefícios de ser cliente DIGITAL:");
		sb_instalacao.append("\n");
		sb_instalacao.append("- Se indicar um amigo, e ele fechar contrato conosco, o senhor ganha 50% de desconto na próxima mensalidade! Basta acessar nosso site, ir na área do cliente informar seu CPF, e embaixo mostrará 'indique um amigo', o senhor indicando e ele fechando contrato ganhará o desconto!!");
		sb_instalacao.append("\n");
		sb_instalacao.append("- E a Digital ainda tem parcerias com algumas empresas, e o senhor como cliente pode estar ganhando desconto, basta verificar em nosso Instagram quais são elas! ");
		sb_instalacao.append("\n");
		sb_instalacao.append("- As opções de pagamento são várias: você pode pagar direto na DIGITAL Belo Jardim, ou pode pagar direto na nossa central do assinante via Cartão de Crédito ou PIX, ou se quiser pode gerar um boleto. ");		
		sb_instalacao.append("\n\n");	
		sb_instalacao.append("Obrigado por escolher a DIGITAL!");
		
	//	System.out.println(sb_instalacao);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select c from Crm c where c.crm_assuntos=:assunto and c.status like 'AGENDADO'", Crm.class);
		q.setParameter("assunto", new CrmAssunto(353));
		List<Crm> crms = q.getResultList();
		
		em.getTransaction().begin();
		for (Crm crm : crms) {
			crm.setConteudo(sb_instalacao.toString()+"\n\n"+crm.getConteudo());
			em.merge(crm);
		}
		em.getTransaction().commit();
		
		System.out.println(q.getResultList().size());
	}

}
