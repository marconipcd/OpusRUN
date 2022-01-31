package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="subgrupo_ose")
public class SubGrupoOse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="GRUPO_OSE_ID", nullable=false)
	private GrupoOse grupo;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@Column(name="PRIORIDADE", nullable=false)
	private Integer prioridade;
	
	@Column(name="GERAR_CRM", nullable=true)
	private String gerar_crm;
	
	@OneToOne
	@JoinColumn(name="SETOR", nullable=true)	
	private Setores setor;
	@OneToOne
	@JoinColumn(name="CRM_ASSUNTO", nullable=true)
	private CrmAssunto crm_assunto;
	@OneToOne
	@JoinColumn(name="CRM_FORMA_CONTATO", nullable=true)
	private CrmFormasContato crm_forma_contato;
	
	
	@Column(name="DATA_CADASTRO", nullable=false)
	private Date data_cadastro;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="LIMITE")
	private boolean limite;
	

	public SubGrupoOse(){
		
	}

	public SubGrupoOse(Integer id) {
		super();
		this.id = id;
		
	}

	public SubGrupoOse(Integer id, GrupoOse grupo, String nome,
			Date data_cadastro) {
		super();
		this.id = id;
		this.grupo = grupo;
		this.nome = nome;
		
		
		this.data_cadastro = data_cadastro;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	


	public GrupoOse getGrupo() {
		return grupo;
	}


	public void setGrupo(GrupoOse grupo) {
		this.grupo = grupo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	

	public Date getData_cadastro() {
		return data_cadastro;
	}


	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	
	public String getGerar_crm(){
		return gerar_crm;
	}
	public void setGerar_crm(String s){
		this.gerar_crm = s;
	}
	
	public Setores getSetor(){
		return setor;
	}
	public void setSetor(Setores setor){
		this.setor =setor;
	}
	
	public CrmAssunto getCrm_assunto(){
		return crm_assunto;
	}
	public void setCrm_assunto(CrmAssunto crm_assunto){
		this.crm_assunto = crm_assunto;
	}
	
	public CrmFormasContato getCrm_forma_contato(){
		return crm_forma_contato;
	}
	public void setCrm_forma_contato(CrmFormasContato crm_forma_contato){
		this.crm_forma_contato = crm_forma_contato;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isLimite() {
		return limite;
	}

	public void setLimite(boolean limite) {
		this.limite = limite;
	}

	
	
	
	
}
