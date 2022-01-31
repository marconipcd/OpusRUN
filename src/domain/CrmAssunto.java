package domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="crm_assuntos")
@Cacheable(value=false)
public class CrmAssunto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	@Column(name="NOME", nullable=false)
	private String nome;
	@OneToOne
	@JoinColumn(name="SETOR", nullable=true)	
	private Setores setor;
	@Column(name="CONTEUDO", nullable=false)
	private String conteudo;
	@Column(name="MOSTRAR_CENTRAL")
	private boolean mostrar_central;
	
	@Column(name="PERGUNTAS_ATIVAS")
	private String perguntas_ativas;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="PRIORIDADE")
	private String prioridade;
	
	@Column(name="EMPRESA_ID", nullable=false)
	private Integer empresa_id;
	
	public CrmAssunto(Integer id){
		this.id = id;
	}
	
	
	public CrmAssunto(){
		
	}

	public CrmAssunto(Integer id, String nome, String conteudo,
			boolean mostrar_central, Integer empresa_id) {
		super();
		this.id = id;
		this.nome = nome;
		this.conteudo = conteudo;
		this.mostrar_central = mostrar_central;
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

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Integer getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}

	public boolean isMostrar_central() {
		return mostrar_central;
	}

	public void setMostrar_central(boolean mostrar_central) {
		this.mostrar_central = mostrar_central;
	}
	
	public Setores getSetor(){
		return setor;
	}
	public void setSetor(Setores setor){
		this.setor =setor;
	}

	public String getPerguntas_ativas() {
		return perguntas_ativas;
	}

	public void setPerguntas_ativas(String perguntas_ativas) {
		this.perguntas_ativas = perguntas_ativas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	
}

