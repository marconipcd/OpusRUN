package domain;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.omg.PortableInterceptor.INACTIVE;

@Entity
@Table(name="permissoes")
public class Permissoes {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Integer id;
	@Column(name="SUBMODULO_ID")
	private Integer submodulo_id;
	@Column(name="USUARIO_ID")
	private Integer usuario_id;
	@Column(name="EMPRESA_ID")
	private Integer empresa_id;
	
	@Column(name="CADASTRAR")
	private String cadastrar;
	@Column(name="ALTERAR")
	private String alterar;
	@Column(name="EXCLUIR")
	private String excluir;
	@Column(name="VISUALIZAR")
	private String visualizar;
	@Column(name="PERMISSAO")
	private String permissao;
	
	public Permissoes(){
		
	}

	public Permissoes(Integer id, Integer submodulo_id, Integer usuario_id,
			Integer empresa_id, String cadastrar, String alterar,
			String excluir, String visualizar, String permissao) {
		super();
		this.id = id;
		this.submodulo_id = submodulo_id;
		this.usuario_id = usuario_id;
		this.empresa_id = empresa_id;
		this.cadastrar = cadastrar;
		this.alterar = alterar;
		this.excluir = excluir;
		this.visualizar = visualizar;
		this.permissao = permissao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubmodulo_id() {
		return submodulo_id;
	}

	public void setSubmodulo_id(Integer submodulo_id) {
		this.submodulo_id = submodulo_id;
	}

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

	public Integer getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}

	public String getCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(String cadastrar) {
		this.cadastrar = cadastrar;
	}

	public String getAlterar() {
		return alterar;
	}

	public void setAlterar(String alterar) {
		this.alterar = alterar;
	}

	public String getExcluir() {
		return excluir;
	}

	public void setExcluir(String excluir) {
		this.excluir = excluir;
	}

	public String getVisualizar() {
		return visualizar;
	}

	public void setVisualizar(String visualizar) {
		this.visualizar = visualizar;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	
	
}
