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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="nfe_mestre")
public class NfeMestre {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)	
	private Integer id;
	@OneToOne
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	@OneToOne
	@JoinColumn(name="ACESSO_ID")
	private AcessoCliente contrato;
	@OneToOne
	@JoinColumn(name="CONTAS_RECEBER_ID")
	private ContasReceber contas_receber;
	@OneToOne
	@JoinColumn(name="CFOP_ID")
	private Cfop natOpe;
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_EMISSAO")
	private Date data_emissao;
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PRESTACAO")
	private Date data_prestacao;
	@Column(name="CLASSE_CONSUMO")
	private String classe_consumo;
	@Column(name="FASE_TIPO_UTILIZACAO")
	private String fase_tipo_utilizacao;
	@Column(name="GRUPO_TENSAO")
	private String grupo_tensao;
	@Column(name="CODIGO_IDENTIFICACAO")
	private String codigo_identificacao;
	@Column(name="MODELO")
	private String modelo;
	@Column(name="SERIE")
	private String serie;	
	@Column(name="CODIGO_AUTENTICACAO_DOC_FISCAL")
	private String codigo_autenticacao_doc_fiscal;
	@Column(name="TOTAL_NOTA")
	private double total_nota;
	@Column(name="BC_ICMS")	
	private double bc_icms;
	@Column(name="ICMS")
	private double icms;
	@Column(name="OPERACOES_ISENTAS_N_TRIBUTADAS")
	private double operacoes_isentas_n_tributadas;
	@Column(name="OUTROS_VALORES")
	private double outros_valores;
	@Column(name="DESCONTO")
	private double desconto;
	@Column(name="SITUACAO_DOC")
	private String situacao_doc;
	@Column(name="ANO_MES_REF")
	private String ano_mes_ref;
	@Column(name="REF_ITEM_NF")
	private String ref_item_nf;
	@Column(name="TELEFONE")
	private String telefone;	
	@Column(name="CODIGO_AUT_DIG_REGISTRO")
	private String codigo_aut_dig_registro;	
	@Column(name="EMAIL_ENVIADO")
	private boolean email_enviado;
		
	

	public NfeMestre(){
		
	}
	
	

	public NfeMestre(Integer id, Cliente cliente, AcessoCliente contrato,
			ContasReceber contas_receber, Cfop natOpe, Date data_emissao,
			Date data_prestacao, String classe_consumo,
			String fase_tipo_utilizacao, String grupo_tensao,
			String codigo_identificacao, String modelo, String serie,
			String codigo_autenticacao_doc_fiscal, double total_nota,
			double bc_icms, double icms, double operacoes_isentas_n_tributadas,
			double outros_valores, double desconto, String situacao_doc,
			String ano_mes_ref, String ref_item_nf, String telefone,
			String codigo_aut_dig_registro, boolean email_enviado) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.contrato = contrato;
		this.contas_receber = contas_receber;
		this.natOpe = natOpe;
		this.data_emissao = data_emissao;
		this.data_prestacao = data_prestacao;
		this.classe_consumo = classe_consumo;
		this.fase_tipo_utilizacao = fase_tipo_utilizacao;
		this.grupo_tensao = grupo_tensao;
		this.codigo_identificacao = codigo_identificacao;
		this.modelo = modelo;
		this.serie = serie;
		this.codigo_autenticacao_doc_fiscal = codigo_autenticacao_doc_fiscal;
		this.total_nota = total_nota;
		this.bc_icms = bc_icms;
		this.icms = icms;
		this.operacoes_isentas_n_tributadas = operacoes_isentas_n_tributadas;
		this.outros_valores = outros_valores;
		this.desconto = desconto;
		this.situacao_doc = situacao_doc;
		this.ano_mes_ref = ano_mes_ref;
		this.ref_item_nf = ref_item_nf;
		this.telefone = telefone;
		this.codigo_aut_dig_registro = codigo_aut_dig_registro;
		this.email_enviado = email_enviado;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public AcessoCliente getContrato() {
		return contrato;
	}

	public void setContrato(AcessoCliente contrato) {
		this.contrato = contrato;
	}
	
	

	public ContasReceber getContas_receber() {
		return contas_receber;
	}

	public void setContas_receber(ContasReceber contas_receber) {
		this.contas_receber = contas_receber;
	}

	public Cfop getNatOpe() {
		return natOpe;
	}

	public void setNatOpe(Cfop natOpe) {
		this.natOpe = natOpe;
	}

	public Date getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}

	public Date getData_prestacao() {
		return data_prestacao;
	}

	public void setData_prestacao(Date data_prestacao) {
		this.data_prestacao = data_prestacao;
	}

	public String getClasse_consumo() {
		return classe_consumo;
	}

	public void setClasse_consumo(String classe_consumo) {
		this.classe_consumo = classe_consumo;
	}

	public String getFase_tipo_utilizacao() {
		return fase_tipo_utilizacao;
	}

	public void setFase_tipo_utilizacao(String fase_tipo_utilizacao) {
		this.fase_tipo_utilizacao = fase_tipo_utilizacao;
	}

	public String getGrupo_tensao() {
		return grupo_tensao;
	}

	public void setGrupo_tensao(String grupo_tensao) {
		this.grupo_tensao = grupo_tensao;
	}

	public String getCodigo_identificacao() {
		return codigo_identificacao;
	}

	public void setCodigo_identificacao(String codigo_identificacao) {
		this.codigo_identificacao = codigo_identificacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	public String getCodigo_autenticacao_doc_fiscal() {
		return codigo_autenticacao_doc_fiscal;
	}

	public void setCodigo_autenticacao_doc_fiscal(
			String codigo_autenticacao_doc_fiscal) {
		this.codigo_autenticacao_doc_fiscal = codigo_autenticacao_doc_fiscal;
	}

	public double getTotal_nota() {
		return total_nota;
	}

	public void setTotal_nota(double total_nota) {
		this.total_nota = total_nota;
	}

	public double getBc_icms() {
		return bc_icms;
	}

	public void setBc_icms(double bc_icms) {
		this.bc_icms = bc_icms;
	}

	public double getIcms() {
		return icms;
	}

	public void setIcms(double icms) {
		this.icms = icms;
	}

	public double getOperacoes_isentas_n_tributadas() {
		return operacoes_isentas_n_tributadas;
	}

	public void setOperacoes_isentas_n_tributadas(
			double operacoes_isentas_n_tributadas) {
		this.operacoes_isentas_n_tributadas = operacoes_isentas_n_tributadas;
	}

	public double getOutros_valores() {
		return outros_valores;
	}

	public void setOutros_valores(double outros_valores) {
		this.outros_valores = outros_valores;
	}

	public String getSituacao_doc() {
		return situacao_doc;
	}

	public void setSituacao_doc(String situacao_doc) {
		this.situacao_doc = situacao_doc;
	}

	public String getAno_mes_ref() {
		return ano_mes_ref;
	}

	public void setAno_mes_ref(String ano_mes_ref) {
		this.ano_mes_ref = ano_mes_ref;
	}

	public String getRef_item_nf() {
		return ref_item_nf;
	}

	public void setRef_item_nf(String ref_item_nf) {
		this.ref_item_nf = ref_item_nf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCodigo_aut_dig_registro() {
		return codigo_aut_dig_registro;
	}

	public void setCodigo_aut_dig_registro(String codigo_aut_dig_registro) {
		this.codigo_aut_dig_registro = codigo_aut_dig_registro;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public boolean isEmail_enviado() {
		return email_enviado;
	}

	public void setEmail_enviado(boolean email_enviado) {
		this.email_enviado = email_enviado;
	}
		
}
