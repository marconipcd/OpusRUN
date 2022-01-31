package USUARIOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Empresa;
import domain.EmpresaUsuario;
import domain.ModuloUsuario;
import domain.Permissoes;
import domain.SubmoduloUsuario;

public class criarUsuario {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		Integer usuarioBase = 185;
		Integer usuarioNovo = 194;
		
		Query q = em.createQuery("select e from EmpresaUsuario e where e.usuario_id=:usuario_base", EmpresaUsuario.class);
		q.setParameter("usuario_base", usuarioBase);
		List<EmpresaUsuario> listaDeEmpresasPorUsuario = q.getResultList();

		Query q2 = em.createQuery("select e from ModuloUsuario e where e.usuario_id=:usuario_base", ModuloUsuario.class);
		q2.setParameter("usuario_base", usuarioBase);
		List<ModuloUsuario> listaDeModulosPorUsuario = q2.getResultList();
		
		Query q3 = em.createQuery("select e from SubmoduloUsuario e where e.usuario_id=:usuario_base", SubmoduloUsuario.class);
		q3.setParameter("usuario_base", usuarioBase);
		List<SubmoduloUsuario> listaDeSubModulosPorUsuario = q3.getResultList();
		
		Query q4 = em.createQuery("select e from Permissoes e where e.usuario_id=:usuario_base", Permissoes.class);
		q4.setParameter("usuario_base", usuarioBase);
		List<Permissoes> listaDePermissoesPorUsuario = q4.getResultList();
		
		
		em.getTransaction().begin();
			
			for (EmpresaUsuario eu : listaDeEmpresasPorUsuario) {
				
				EmpresaUsuario n_eu = new EmpresaUsuario(null, usuarioNovo, eu.getEmpresa_id());				
				em.persist(n_eu);
			}
			
			for (ModuloUsuario mu : listaDeModulosPorUsuario) {
				ModuloUsuario n_moduloUsuario = new ModuloUsuario(null, mu.getEmpresa_id(), mu.getModulo_id(), usuarioNovo);				
				em.persist(n_moduloUsuario); 
			}
			
			for (SubmoduloUsuario smu : listaDeSubModulosPorUsuario) {
				SubmoduloUsuario n_subModuloUsuario = new SubmoduloUsuario(null, smu.getEmpresa_id(), smu.getSubmodulo_id(), usuarioNovo);				
				em.persist(n_subModuloUsuario); 
			}
			
			for (Permissoes p: listaDePermissoesPorUsuario) {
				Permissoes n_Permissao = new Permissoes(null, p.getSubmodulo_id(), usuarioNovo, p.getEmpresa_id(), p.getCadastrar(), p.getAlterar(), p.getExcluir(), 
						p.getVisualizar(), p.getPermissao());
				
				em.persist(n_Permissao); 
			}
			
		em.getTransaction().commit();
		
		
		
		
		
		
		
	}
	
}
