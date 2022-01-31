package domain;

import java.util.Date;

import javax.persistence.Basic;
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
import javax.persistence.TemporalType;

@Entity
@Table(name="fornecedores")
@Cacheable(value=false)
public class Fornecedor {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable=false, unique=true, name="ID")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="EMPRESA_ID")
	private Empresa empresa;
	
	@Column(nullable=false, unique=false, length=200, name="RAZAO_SOCIAL")
	private String razao_social;
	@Column(nullable=false, unique=false, length=200, name="FANTASIA")
	private String fantasia;
	@Column(nullable=false, unique=false, length=45, name="CNPJ")
	private String cnpj;
	@Column(nullable=false, unique=false, length=45, name="INSC_ESTADUAL")
	private String insc_estadual;
	@Column(nullable=false, unique=false, length=100, name="ENDERECO")
	private String endereco;
	@Column(nullable=false, unique=false, length=20, name="NUMERO")
	private String numero;
	@Column(nullable=true, unique=false, length=45, name="BAIRRO")
	private String bairro;
	@Column(nullable=false, unique=false, length=45, name="CIDADE")
	private String cidade;
	@Column(nullable=false, unique=false, length=2, name="UF")
	private String uf;
	@Column(nullable=false, unique=false, length=8, name="PAIS")
	private String pais;
	@Column(nullable=false, unique=false, length=50, name="CEP")
	private String cep;
	@Column(nullable=false, unique=false, length=2, name="DDD_FONE1")
	private String ddd_fone1;	
	
	@Column(nullable=false, unique=false, length=10, name="FONE1")
	private String fone1;
	@Column(nullable=true, unique=false, length=10, name="DDD_FONE2")
	private String ddd_fone2;
	@Column(nullable=true, unique=false, length=10,name="FONE2")
	private String fone2;
	@Column(nullable=true, unique=false, length=10, name="DDD_FONE3")
	private String ddd_fone3;
	@Column(nullable=true, unique=false, length=10, name="FONE3")
	private String fone3;
	@Column(nullable=true, unique=false, length=10, name="DDD_FONE4")
	private String ddd_fone4;
	@Column(nullable=true, unique=false, length=10, name="FONE4")
	private String fone4;
	@Column(nullable=true, unique=false, length=12, name="FONE0800")
	private String fone0800;
	@Column(nullable=true, unique=false, length=10, name="FAX")
	private String fax;
	
	@Column(nullable=true, unique=false, length=100, name="DTPVENDAS")
	private String dtpvendas;
	@Column(nullable=true, unique=false, length=100, name="DTPVENDAS2")
	private String dtpvendas2;
	@Column(nullable=true, unique=false, length=100, name="CONTATO_DPT1")
	private String contato_dpt1;
	@Column(nullable=true, unique=false, length=100, name="CONTATO_DPT2")
	private String contato_dpt2;
	@Column(nullable=true, unique=false, length=100, name="DDD_TELEFONE_PRINCIPAL_DPT1")
	private String ddd_tel_principal_dpt1;
	@Column(nullable=true, unique=false, length=100, name="DDD_TELEFONE_PRINCIPAL_DPT2")
	private String ddd_tel_principal_dpt2;
	@Column(nullable=true, unique=false, length=100, name="TELEFONE_PRINCIPAL_DPT1")
	private String tel_principal_dpt1;
	@Column(nullable=true, unique=false, length=100, name="TELEFONE_PRINCIPAL_DPT2")
	private String tel_principal_dpt2;
	@Column(nullable=true, unique=false, length=100, name="DDD_TELEFONE_ALTERNATIVO_DPT1")
	private String ddd_tel_alternativo_dpt1;
	@Column(nullable=true, unique=false, length=100, name="DDD_TELEFONE_ALTERNATIVO_DPT2")
	private String ddd_tel_alternativo_dpt2;
	@Column(nullable=true, unique=false, length=100, name="TELEFONE_ALTERNATIVO_DPT1")
	private String tel_alternativo_dpt1;
	@Column(nullable=true, unique=false, length=100, name="TELEFONE_ALTERNATIVO_DPT2")
	private String tel_alternativo_dpt2;
	@Column(nullable=true, unique=false, length=100, name="EMAIL_DPT1")
	private String email_dpt1;
	@Column(nullable=true, unique=false, length=100, name="EMAIL_DPT2")
	private String email_dpt2;
	@Column(nullable=true, unique=false, length=100, name="SKYPE_DPT1")
	private String skype_dpt1;
	@Column(nullable=true, unique=false, length=100, name="SKYPE_DPT2")
	private String skype_dpt2;
	
	
	
	@Column(nullable=true, unique=false, length=45, name="EMAIL")
	private String email;
	@Column(nullable=true, unique=false, length=45, name="HOME_PAGE")
	private String home_page;
	@Column(nullable=true, unique=false, length=100, name="CONTATO_REPRESENTANTE")
	private String contato_representante;
	@Column(nullable=true, unique=false, length=200, name="REPRESENTANTE")
	private String representante;
	@Column(nullable=false, unique=false, length=2, name="DDD_FONE1_REPRESENTANTE")
	private String ddd_fone1_representante;
	@Column(nullable=true, unique=false, length=10, name="FONE1_REPRESENTANTE")
	private String fone1_representante;
	@Column(nullable=false, unique=false, length=2, name="DDD_FONE2_REPRESENTANTE")
	private String ddd_fone2_representante;
	@Column(nullable=true, unique=false, length=10, name="FONE2_REPRESENTANTE")
	private String fone2_representante;
	@Column(nullable=false, unique=false, length=2, name="DDD_FONE3_REPRESENTANTE")
	private String ddd_fone3_representante;
	@Column(nullable=true, unique=false, length=10, name="FONE3_REPRESENTANTE")
	private String fone3_representante;
	@Column(nullable=false, unique=false, length=2, name="DDD_FONE4_REPRESENTANTE")
	private String ddd_fone4_representante;
	@Column(nullable=true, unique=false, length=10, name="FONE4_REPRESENTANTE")
	private String fone4_representante;
	@Column(nullable=true, unique=false, length=10, name="FAX_REPRESENTANTE")
	private String fax_representante;
	@Column(nullable=true, unique=false, length=50, name="MSN_REPRESENTANTE")
	private String msn_representante;
	@Column(nullable=true, unique=false, length=50, name="EMAIL_REPRESENTANTE")
	private String email_representante;
	@Column(nullable=true, unique=false, length=10, name="CEL_REPRESENTANTE")
	private String cel_representante;
	@Column(name="CNAE_ID")
	private Integer cnae_fiscal;	
	@Basic(optional=true)
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date data_cadastro;	
	@Basic(optional=true)
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="DATA_ALTERACAO")
	private Date data_alteracao;	
	@Column(nullable=true, unique=false, length=100, name="COMPLEMENTO")
	private String complemento;
	@Column(nullable=true, unique=false, length=100, name="PONTO_REFERENCIA")
	private String ponto_referencia;	
	@Column(nullable=true, unique=false, length=50, name="MSN")
	private String msn;
	@Column(name="SITE_REPRESENTANTE", nullable=true, unique=false, length=200)
	private String site_representante;
	@Column(nullable=true, unique=false, length=100, name="TIPO_DE_FRETE")
	private String tipo_de_frete;	
	@Column(nullable=true, unique=false, name="TRANSPORTADORA_PADRAO_ID")
	private Integer transportadora_padrao_id;	
	@Column(nullable=true, unique=false, length=100, name="RAMO_DE_ATIVIDADE")
	private String ramo_de_atividade;
	@Column(nullable=true, unique=false, length=300, name="PRINCIPAIS_PRODUTOS")
	private String principais_produtos;
	@Column(nullable=true, unique=false, length=300, name="OBSERVACAO")
	private String observacao;
	@Column(nullable=true, unique=false, length=300, name="OBSERVACAO_REPRESENTANTE")
	private String observacao_representante;
	@Basic(optional=true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PRIMEIRA_COMPRA")
	private Date primeira_compra;
	@Basic(optional=true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ULTIMA_COMPRA")
	private Date ultima_compra;
	@Column(nullable=true, unique=false, name="LIMITE_DE_CREDITO")
	private String limite_de_credito;
	@Column(nullable=true, unique=false, length=100, name="STATUS")
	private String status;	
	@Column(name="fornecedor")
	private boolean fornecedor;
	@Column(name="transportadora")
	private boolean transportadora;
	
	public boolean isFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(boolean fornecedor) {
		this.fornecedor = fornecedor;
	}
	public boolean isTrasnportadora() {
		return transportadora;
	}
	public void setTrasnportadora(boolean trasnportadora) {
		this.transportadora = trasnportadora;
	}
	@PrePersist
    protected void onCreate(){
		data_alteracao = data_cadastro = new Date();
    }	
	@PreUpdate
	protected void onUpdate(){
		data_alteracao = new Date();
	}
	
	public Fornecedor(Integer id){
		super();
		this.id = id;
	}

	public Fornecedor(){
		
	}
	
	public Fornecedor(Integer id, Empresa empresa, String razao_social,
			String fantasia, String cnpj, String insc_estadual,
			String endereco, String numero, String bairro, String cidade,
			String uf, String pais, String cep, String ddd_fone1, String fone1,
			String ddd_fone2, String fone2, String ddd_fone3, String fone3,
			String ddd_fone4, String fone4, String fone0800, String fax,
			String dtpvendas, String email, String home_page,
			String contato_representante, String representante,
			String ddd_fone1_representante, String fone1_representante,
			String ddd_fone2_representante, String fone2_representante,
			String ddd_fone3_representante, String fone3_representante,
			String ddd_fone4_representante, String fone4_representante,
			String fax_representante, String msn_representante,
			String email_representante, String cel_representante,
			Date data_cadastro, Date data_alteracao, String complemento,
			String ponto_referencia, String msn, String site_representante,
			String tipo_de_frete, Integer transportadora_padrao_id,
			String ramo_de_atividade, String principais_produtos,
			String observacao, String observacao_representante,
			Date primeira_compra, Date ultima_compra, String limite_de_credito,
			String status) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.razao_social = razao_social;
		this.fantasia = fantasia;
		this.cnpj = cnpj;
		this.insc_estadual = insc_estadual;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.pais = pais;
		this.cep = cep;
		this.ddd_fone1 = ddd_fone1;
		this.fone1 = fone1;
		this.ddd_fone2 = ddd_fone2;
		this.fone2 = fone2;
		this.ddd_fone3 = ddd_fone3;
		this.fone3 = fone3;
		this.ddd_fone4 = ddd_fone4;
		this.fone4 = fone4;
		this.fone0800 = fone0800;
		this.fax = fax;
		this.dtpvendas = dtpvendas;
		this.email = email;
		this.home_page = home_page;
		this.contato_representante = contato_representante;
		this.representante = representante;
		this.ddd_fone1_representante = ddd_fone1_representante;
		this.fone1_representante = fone1_representante;
		this.ddd_fone2_representante = ddd_fone2_representante;
		this.fone2_representante = fone2_representante;
		this.ddd_fone3_representante = ddd_fone3_representante;
		this.fone3_representante = fone3_representante;
		this.ddd_fone4_representante = ddd_fone4_representante;
		this.fone4_representante = fone4_representante;
		this.fax_representante = fax_representante;
		this.msn_representante = msn_representante;
		this.email_representante = email_representante;
		this.cel_representante = cel_representante;
		this.data_cadastro = data_cadastro;
		this.data_alteracao = data_alteracao;
		this.complemento = complemento;
		this.ponto_referencia = ponto_referencia;
		this.msn = msn;
		this.site_representante = site_representante;
		this.tipo_de_frete = tipo_de_frete;
		this.transportadora_padrao_id = transportadora_padrao_id;
		this.ramo_de_atividade = ramo_de_atividade;
		this.principais_produtos = principais_produtos;
		this.observacao = observacao;
		this.observacao_representante = observacao_representante;
		this.primeira_compra = primeira_compra;
		this.ultima_compra = ultima_compra;
		this.limite_de_credito = limite_de_credito;
		this.status = status;
	}
	public Fornecedor(Integer id, Empresa empresa, String razao_social,
			String fantasia, String cnpj, String insc_estadual,
			String endereco, String numero, String bairro, String cidade,
			String uf, String pais, String cep, String ddd_fone1, String fone1,
			String ddd_fone2, String fone2, String ddd_fone3, String fone3,
			String ddd_fone4, String fone4, String fone0800, String fax,
			String dtpvendas, String email, String home_page,
			String contato_representante, String representante,
			String ddd_fone1_representante, String fone1_representante,
			String ddd_fone2_representante, String fone2_representante,
			String ddd_fone3_representante, String fone3_representante,
			String ddd_fone4_representante, String fone4_representante,
			String fax_representante, String msn_representante,
			String email_representante, String cel_representante,
			Date data_cadastro, Date data_alteracao, String complemento,
			String ponto_referencia, String msn, String site_representante,
			String tipo_de_frete, Integer transportadora_padrao_id,
			String ramo_de_atividade, String principais_produtos,
			String observacao, String observacao_representante,
			Date primeira_compra, Date ultima_compra, String limite_de_credito,
			String status, boolean fornecedor) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.razao_social = razao_social;
		this.fantasia = fantasia;
		this.cnpj = cnpj;
		this.insc_estadual = insc_estadual;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.pais = pais;
		this.cep = cep;
		this.ddd_fone1 = ddd_fone1;
		this.fone1 = fone1;
		this.ddd_fone2 = ddd_fone2;
		this.fone2 = fone2;
		this.ddd_fone3 = ddd_fone3;
		this.fone3 = fone3;
		this.ddd_fone4 = ddd_fone4;
		this.fone4 = fone4;
		this.fone0800 = fone0800;
		this.fax = fax;
		this.dtpvendas = dtpvendas;
		this.email = email;
		this.home_page = home_page;
		this.contato_representante = contato_representante;
		this.representante = representante;
		this.ddd_fone1_representante = ddd_fone1_representante;
		this.fone1_representante = fone1_representante;
		this.ddd_fone2_representante = ddd_fone2_representante;
		this.fone2_representante = fone2_representante;
		this.ddd_fone3_representante = ddd_fone3_representante;
		this.fone3_representante = fone3_representante;
		this.ddd_fone4_representante = ddd_fone4_representante;
		this.fone4_representante = fone4_representante;
		this.fax_representante = fax_representante;
		this.msn_representante = msn_representante;
		this.email_representante = email_representante;
		this.cel_representante = cel_representante;
		this.data_cadastro = data_cadastro;
		this.data_alteracao = data_alteracao;
		this.complemento = complemento;
		this.ponto_referencia = ponto_referencia;
		this.msn = msn;
		this.site_representante = site_representante;
		this.tipo_de_frete = tipo_de_frete;
		this.transportadora_padrao_id = transportadora_padrao_id;
		this.ramo_de_atividade = ramo_de_atividade;
		this.principais_produtos = principais_produtos;
		this.observacao = observacao;
		this.observacao_representante = observacao_representante;
		this.primeira_compra = primeira_compra;
		this.ultima_compra = ultima_compra;
		this.limite_de_credito = limite_de_credito;
		this.status = status;
		this.fornecedor = fornecedor;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDtpvendas() {
		return dtpvendas;
	}

	public void setDtpvendas(String dtpvendas) {
		this.dtpvendas = dtpvendas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHome_page() {
		return home_page;
	}

	public void setHome_page(String home_page) {
		this.home_page = home_page;
	}

	public String getContato_representante() {
		return contato_representante;
	}

	public void setContato_representante(String contato_representante) {
		this.contato_representante = contato_representante;
	}

	public String getFone1_representante() {
		return fone1_representante;
	}

	public void setFone1_representante(String fone1_representante) {
		this.fone1_representante = fone1_representante;
	}

	public String getFone2_representante() {
		return fone2_representante;
	}

	public void setFone2_representante(String fone2_representante) {
		this.fone2_representante = fone2_representante;
	}

	public String getFone3_representante() {
		return fone3_representante;
	}

	public void setFone3_representante(String fone3_representante) {
		this.fone3_representante = fone3_representante;
	}

	public String getFone4_representante() {
		return fone4_representante;
	}

	public void setFone4_representante(String fone4_representante) {
		this.fone4_representante = fone4_representante;
	}

	public String getFax_representante() {
		return fax_representante;
	}

	public void setFax_representante(String fax_representante) {
		this.fax_representante = fax_representante;
	}

	public String getMsn_representante() {
		return msn_representante;
	}

	public void setMsn_representante(String msn_representante) {
		this.msn_representante = msn_representante;
	}

	public String getCel_representante() {
		return cel_representante;
	}

	public void setCel_representante(String cel_representante) {
		this.cel_representante = cel_representante;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPonto_referencia() {
		return ponto_referencia;
	}

	public void setPonto_referencia(String ponto_referencia) {
		this.ponto_referencia = ponto_referencia;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getFone3() {
		return fone3;
	}

	public void setFone3(String fone3) {
		this.fone3 = fone3;
	}

	public String getFone4() {
		return fone4;
	}

	public void setFone4(String fone4) {
		this.fone4 = fone4;
	}

	public String getFone0800() {
		return fone0800;
	}

	public void setFone0800(String fone0800) {
		this.fone0800 = fone0800;
	}

	public String getTipo_de_frete() {
		return tipo_de_frete;
	}

	public void setTipo_de_frete(String tipo_de_frete) {
		this.tipo_de_frete = tipo_de_frete;
	}
	
	public Integer getTransportadora_padrao_id() {
		return transportadora_padrao_id;
	}
	
	public void setTransportadora_padrao_id(Integer transportadora_padrao_id) {
		this.transportadora_padrao_id = transportadora_padrao_id;
	}

	public String getRamo_de_atividade() {
		return ramo_de_atividade;
	}

	public void setRamo_de_atividade(String ramo_de_atividade) {
		this.ramo_de_atividade = ramo_de_atividade;
	}

	public String getPrincipais_produtos() {
		return principais_produtos;
	}

	public void setPrincipais_produtos(String principais_produtos) {
		this.principais_produtos = principais_produtos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getPrimeira_compra() {
		return primeira_compra;
	}

	public void setPrimeira_compra(Date primeira_compra) {
		this.primeira_compra = primeira_compra;
	}

	public Date getUltima_compra() {
		return ultima_compra;
	}

	public void setUltima_compra(Date ultima_compra) {
		this.ultima_compra = ultima_compra;
	}

	public String getLimite_de_credito() {
		return limite_de_credito;
	}

	public void setLimite_de_credito(String limite_de_credito) {
		this.limite_de_credito = limite_de_credito;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public Date getData_alteracao() {
		return data_alteracao;
	}
	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
	public String getInsc_estadual() {
		return insc_estadual;
	}
	public void setInsc_estadual(String insc_estadual) {
		this.insc_estadual = insc_estadual;
	}
	public String getEmail_representante() {
		return email_representante;
	}
	public void setEmail_representante(String email_representante) {
		this.email_representante = email_representante;
	}
	public String getObservacao_representante() {
		return observacao_representante;
	}
	public void setObservacao_representante(String observacao_representante) {
		this.observacao_representante = observacao_representante;
	}
	
	public String getDdd_fone1() {
		return ddd_fone1;
	}
	public void setDdd_fone1(String ddd_fone1) {
		this.ddd_fone1 = ddd_fone1;
	}
	public String getDdd_fone2() {
		return ddd_fone2;
	}
	public void setDdd_fone2(String ddd_fone2) {
		this.ddd_fone2 = ddd_fone2;
	}
	public String getDdd_fone3() {
		return ddd_fone3;
	}
	public void setDdd_fone3(String ddd_fone3) {
		this.ddd_fone3 = ddd_fone3;
	}
	public String getDdd_fone4() {
		return ddd_fone4;
	}
	public void setDdd_fone4(String ddd_fone4) {
		this.ddd_fone4 = ddd_fone4;
	}
	public String getDdd_fone1_representante() {
		return ddd_fone1_representante;
	}
	public void setDdd_fone1_representante(String ddd_fone1_representante) {
		this.ddd_fone1_representante = ddd_fone1_representante;
	}
	public String getDdd_fone2_representante() {
		return ddd_fone2_representante;
	}
	public void setDdd_fone2_representante(String ddd_fone2_representante) {
		this.ddd_fone2_representante = ddd_fone2_representante;
	}
	public String getDdd_fone3_representante() {
		return ddd_fone3_representante;
	}
	public void setDdd_fone3_representante(String ddd_fone3_representante) {
		this.ddd_fone3_representante = ddd_fone3_representante;
	}
	public String getDdd_fone4_representante() {
		return ddd_fone4_representante;
	}
	public void setDdd_fone4_representante(String ddd_fone4_representante) {
		this.ddd_fone4_representante = ddd_fone4_representante;
	}
	public String getSite_representante() {
		return site_representante;
	}
	public void setSite_representante(String site_representante) {
		this.site_representante = site_representante;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getDtpvendas2() {
		return dtpvendas2;
	}
	public void setDtpvendas2(String dtpvendas2) {
		this.dtpvendas2 = dtpvendas2;
	}
	public String getContato_dpt1() {
		return contato_dpt1;
	}
	public void setContato_dpt1(String contato_dpt1) {
		this.contato_dpt1 = contato_dpt1;
	}
	public String getContato_dpt2() {
		return contato_dpt2;
	}
	public void setContato_dpt2(String contato_dpt2) {
		this.contato_dpt2 = contato_dpt2;
	}
	public String getDdd_tel_principal_dpt1() {
		return ddd_tel_principal_dpt1;
	}
	public void setDdd_tel_principal_dpt1(String ddd_tel_principal_dpt1) {
		this.ddd_tel_principal_dpt1 = ddd_tel_principal_dpt1;
	}
	public String getDdd_tel_principal_dpt2() {
		return ddd_tel_principal_dpt2;
	}
	public void setDdd_tel_principal_dpt2(String ddd_tel_principal_dpt2) {
		this.ddd_tel_principal_dpt2 = ddd_tel_principal_dpt2;
	}
	public String getTel_principal_dpt1() {
		return tel_principal_dpt1;
	}
	public void setTel_principal_dpt1(String tel_principal_dpt1) {
		this.tel_principal_dpt1 = tel_principal_dpt1;
	}
	public String getTel_principal_dpt2() {
		return tel_principal_dpt2;
	}
	public void setTel_principal_dpt2(String tel_principal_dpt2) {
		this.tel_principal_dpt2 = tel_principal_dpt2;
	}
	public String getDdd_tel_alternativo_dpt1() {
		return ddd_tel_alternativo_dpt1;
	}
	public void setDdd_tel_alternativo_dpt1(String ddd_tel_alternativo_dpt1) {
		this.ddd_tel_alternativo_dpt1 = ddd_tel_alternativo_dpt1;
	}
	public String getDdd_tel_alternativo_dpt2() {
		return ddd_tel_alternativo_dpt2;
	}
	public void setDdd_tel_alternativo_dpt2(String ddd_tel_alternativo_dpt2) {
		this.ddd_tel_alternativo_dpt2 = ddd_tel_alternativo_dpt2;
	}
	public String getTel_alternativo_dpt1() {
		return tel_alternativo_dpt1;
	}
	public void setTel_alternativo_dpt1(String tel_alternativo_dpt1) {
		this.tel_alternativo_dpt1 = tel_alternativo_dpt1;
	}
	public String getTel_alternativo_dpt2() {
		return tel_alternativo_dpt2;
	}
	public void setTel_alternativo_dpt2(String tel_alternativo_dpt2) {
		this.tel_alternativo_dpt2 = tel_alternativo_dpt2;
	}
	public String getEmail_dpt1() {
		return email_dpt1;
	}
	public void setEmail_dpt1(String email_dpt1) {
		this.email_dpt1 = email_dpt1;
	}
	public String getEmail_dpt2() {
		return email_dpt2;
	}
	public void setEmail_dpt2(String email_dpt2) {
		this.email_dpt2 = email_dpt2;
	}
	public String getSkype_dpt1() {
		return skype_dpt1;
	}
	public void setSkype_dpt1(String skype_dpt1) {
		this.skype_dpt1 = skype_dpt1;
	}
	public String getSkype_dpt2() {
		return skype_dpt2;
	}
	public void setSkype_dpt2(String skype_dpt2) {
		this.skype_dpt2 = skype_dpt2;
	}
	public Integer getCnae_fiscal() {
		return cnae_fiscal;
	}
	public void setCnae_fiscal(Integer cnae_fiscal) {
		this.cnae_fiscal = cnae_fiscal;
	}
	public boolean isTransportadora() {
		return transportadora;
	}
	public void setTransportadora(boolean transportadora) {
		this.transportadora = transportadora;
	}

	
	
	
}
