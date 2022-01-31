package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.joda.time.DateTime;

import domain.AcessoCliente;
import domain.AlterarcoesContrato;
import domain.ContasReceber;
import domain.LogAcoes;
import domain.Usuario;

public class AcessoDAO {

	public static boolean renovarContratoAcesso(AcessoCliente contrato){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
				 
		try{
			
			em.getTransaction().begin();
			
				if(contrato.getPlano().getPlano_Renovacao() != null){
					contrato.setPlano(contrato.getPlano().getPlano_Renovacao());
				}
			
				Integer vigencia1 = contrato.getContrato().getVigencia();			
				Date dataVencContratoNova = new Date();
			
				if(!contrato.getContrato().getTipo_contrato().equals("GRATIS")){
					
					List<ContasReceber> resConta = ContasReceberDAO.procurarTodosBoletosDoAcessoPorContrato(contrato.getId());		
					ContasReceber ct = resConta.get(0);
					Date dtVenc = ct.getData_vencimento();
					DateTime dt2 = new DateTime(dtVenc).plusMonths(1);
					DateTime dtVencimentoNovo = new DateTime(dtVenc).plusMonths(vigencia1);
					dtVenc = dt2.toDate();
					
					CredenciaisAcessoDAO.gerarBoletosAcesso(contrato.getCliente().getId(),contrato, contrato.getContrato(), contrato.getPlano(), dtVenc, contrato.getPlano().getValor());
					
					contrato.setData_venc_contrato(dtVencimentoNovo.toDate());
				}else{
					DateTime dtVencimentoNovo = new DateTime(contrato.getData_venc_contrato()).plusMonths(vigencia1);
					contrato.setData_venc_contrato(dtVencimentoNovo.toDate());
				}
				
				contrato.setData_renovacao(new Date());
				contrato.setCarencia("NAO");			
			
				em.merge(contrato);
				
			em.getTransaction().commit();
			
			boolean check = CredenciaisAcessoDAO.DesbloquearContrato(contrato.getId());
			
			LogDAO.add(new LogAcoes(null, "Opus", "Renovou Um Novo Contrato de Acesso"));
			AlteracoesContratoDAO.save(new AlterarcoesContrato(null, "RENOVAÇÃO DE CONTRATO", contrato, new Usuario(100), new Date()));
			if(contrato.getStatus_2().equals("BLOQUEADO") && check){
				AlteracoesContratoDAO.save(new AlterarcoesContrato(null, "CONTRATO DESBLOQUEADO POR RENOVACAO", contrato, new Usuario(100), new Date()));
			}
			
			return true;
		}catch(Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();				
			}
			e.printStackTrace();	
			
			LogDAO.add(new LogAcoes(null, "Opus", "Não Conseguiu Renovar Um Contrato de Acesso, Uma Excessão foi Gerada"));
			return false;
		}
			
	}
}
