package domain;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="setores")
@Cacheable(value=false)
public class Setores {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true, length=10)
	private Integer id;
	@Column(name="EMPRESA_ID")
	private Integer empresa_id;
	@Column(name="NOME", nullable=true, unique=false, length=100)
	private String nome;
	@Column(name="DATA_CADASTRO", nullable=true, unique=false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date data_cadastro;
	@Column(name="DATA_ALTERACAO", nullable=true, unique=false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date data_alteracao;
	
	@OneToOne
	@JoinColumn(name="SETOR_ROOT_ID", nullable=true)
	private Setores setor_root;
	
	
	public Setores(){
		
	}
		
	
	public Date getData_alteracao() {
		return data_alteracao;
	}
	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
	
	
	@PrePersist
    protected void onCreate(){
		data_alteracao = data_cadastro = new Date();
    }	
	@PreUpdate
	protected void onUpdate(){
		data_alteracao = new Date();
	}
	
	

	
	
	public Setores(Integer id, Integer empresa_id, String nome,
			Date data_cadastro, Date data_alteracao, Setores setor_child) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.nome = nome;
		this.data_cadastro = data_cadastro;
		this.data_alteracao = data_alteracao;
		this.setor_root = setor_child;
	}


	public Setores getSetor_root() {
		return setor_root;
	}


	public void setSetor_root(Setores setor_root) {
		this.setor_root = setor_root;
	}


	public Setores(Integer id){
		super();
		this.id = id;
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
	
	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

//	public List<Setores> getSetor() {
//		return setor;
//	}
//
//	public void setSetor(List<Setores> setor) {
//		this.setor = setor;
//	}



	
	
	

	
	
	
	
	
}

