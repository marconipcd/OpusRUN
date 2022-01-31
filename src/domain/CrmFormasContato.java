package domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="crm_formas_contato")
@Cacheable(value=false)
public class CrmFormasContato {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	@Column(name="NOME", nullable=false)
	private String nome;
	@Column(name="EMPRESA_ID", nullable=false)
	private Integer empresa_id;
	
	public CrmFormasContato() {
	
	}
	public CrmFormasContato(Integer id){
		this.id = id;
	}
	
	public CrmFormasContato(Integer id, String nome, Integer empresa_id) {
		super();
		this.id = id;
		this.nome = nome;
		this.empresa_id = empresa_id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getEmpresa_id() {
		return empresa_id;
	}
	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}
	
	
	
}

