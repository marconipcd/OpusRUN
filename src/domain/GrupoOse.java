package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tipos_ose")
public class GrupoOse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="EMPRESA_ID", nullable=false)
	private Integer empresa_id;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
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
	
	@Column(name="PONTOS")
	private String pontos;
	
	@Column(name="META")
	private Integer meta;
	
	@Column(name="LIMITE_DIARIO")
	private Integer limite_diario;
	
	@Column(name="BAIXA_MATERIAL")
	private String baixa_material;
	
	@Column(name="STATUS")
	private String status;
	
		
	public GrupoOse(){
		
	}
	
	public GrupoOse(Integer id) {
		super();
		this.id = id;		
	}

	public GrupoOse(Integer id, Integer empresa_id, String nome, String pontos,
			Integer meta) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.nome = nome;
		this.pontos = pontos;
		this.meta = meta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}

	public Integer getMeta() {
		return meta;
	}

	public void setMeta(Integer meta) {
		this.meta = meta;
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

	public Integer getLimite_diario() {
		return limite_diario;
	}

	public void setLimite_diario(Integer limite_diario) {
		this.limite_diario = limite_diario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBaixa_material() {
		return baixa_material;
	}

	public void setBaixa_material(String baixa_material) {
		this.baixa_material = baixa_material;
	}
	
	
	
}
