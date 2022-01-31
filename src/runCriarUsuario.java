import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.EmpresaUsuario;
import domain.ModuloUsuario;
import domain.Permissoes;
import domain.SubmoduloUsuario;
import domain.Usuario;


public class runCriarUsuario {

	public static void main(String[] args){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		String usuario_novo = "vinicius";
		String senha_usuario_novo = "Vinicius9980";
		
		String usuario_modelo = "renataandrade";
		
		Usuario u = new Usuario();
		u.setUsername(usuario_novo);
		u.setPassword(util.StringUtil.md5(senha_usuario_novo));
		u.setFuncao("operador");
		
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		
		Query qUserModelo = em.createQuery("select u from Usuario u where u.username LIKE :user", Usuario.class);
		qUserModelo.setParameter("user", usuario_modelo);
		
		Integer cod_usuario_modelo = 135;
		if(qUserModelo.getResultList().size() == 1){
			cod_usuario_modelo = ((Usuario)qUserModelo.getSingleResult()).getId();
		}
		
		
		if(cod_usuario_modelo != 0){
			
			em.getTransaction().begin();
			
			//Empresa Usuario
			Query qEmpresaUsuario = em.createQuery("select eu from EmpresaUsuario eu where eu.usuario_id=:codUsuario", EmpresaUsuario.class);
			qEmpresaUsuario.setParameter("codUsuario", cod_usuario_modelo);
			
			List<EmpresaUsuario> empresasUsuario = qEmpresaUsuario.getResultList();
			for (EmpresaUsuario empresaUsuario : empresasUsuario) {
				
				EmpresaUsuario eu = new EmpresaUsuario();
				eu.setEmpresa_id(empresaUsuario.getEmpresa_id());
				eu.setUsuario_id(u.getId());
				
				em.persist(eu); 
				
			}
			
			//Modulo Usuario
			Query qModuloUsuario = em.createQuery("select mu from ModuloUsuario mu where mu.usuario_id=:codUsuario", ModuloUsuario.class);
			qModuloUsuario.setParameter("codUsuario", cod_usuario_modelo);
			
			List<ModuloUsuario> modulosUsuario = qModuloUsuario.getResultList();
			for (ModuloUsuario moduloUsuario : modulosUsuario) {
				
				ModuloUsuario mu = new ModuloUsuario();
				mu.setModulo_id(moduloUsuario.getModulo_id());
				mu.setEmpresa_id(moduloUsuario.getEmpresa_id());
				mu.setUsuario_id(u.getId());
				
				em.persist(mu); 
			}
			
			//SubModulo Usuario
			Query qSubModuloUsuario = em.createQuery("select su from SubmoduloUsuario su where su.usuario_id=:codUsuario", SubmoduloUsuario.class);
			qSubModuloUsuario.setParameter("codUsuario", cod_usuario_modelo);
			
			List<SubmoduloUsuario> submodulosUsuario = qSubModuloUsuario.getResultList();
			for (SubmoduloUsuario submoduloUsuario : submodulosUsuario) {
				
				SubmoduloUsuario su = new SubmoduloUsuario();
				su.setSubmodulo_id(submoduloUsuario.getSubmodulo_id());
				su.setEmpresa_id(submoduloUsuario.getEmpresa_id());
				su.setUsuario_id(u.getId());
				
				em.persist(su);
			}
			
			
			//Permissoes
			Query qPermissaoUsuario = em.createQuery("select p from Permissoes p where p.usuario_id=:codUsuario", Permissoes.class);
			qPermissaoUsuario.setParameter("codUsuario", cod_usuario_modelo);
			
			List<Permissoes> permissoesUsuario = qPermissaoUsuario.getResultList();
			for (Permissoes permissoes : permissoesUsuario) {
				
				Permissoes p = new Permissoes();
				p.setAlterar(permissoes.getAlterar());
				p.setCadastrar(permissoes.getCadastrar());
				p.setEmpresa_id(permissoes.getEmpresa_id());
				p.setExcluir(permissoes.getExcluir());
				p.setPermissao(permissoes.getPermissao());
				p.setSubmodulo_id(permissoes.getSubmodulo_id());
				p.setVisualizar(permissoes.getVisualizar());
				p.setUsuario_id(u.getId());
				
				em.persist(p);
			}
			
		
			em.getTransaction().commit();
			
			
		}
		
	}
}
