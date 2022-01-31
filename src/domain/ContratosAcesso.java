package domain;


import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="contratos_acesso")
@Cacheable(value=false)
public class ContratosAcesso {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	@Column(name="EMPRESA_ID", nullable=false, unique=false)
	private Integer empresa_id;
	@Column(name="NOME", nullable=false, unique=false, length=200)
	private String nome;
	@Column(name="CLAUSULAS", nullable=true, unique=false, columnDefinition="text")	
	private String clausulas;
	@Column(name="VIGENCIA", nullable=false, unique=false,length=100)
	private Integer vigencia;
	@Column(name="VALOR_ADESAO", nullable=false, unique=false, length=50)
	private String valor_adesao;
//	@Column(name="VALOR_EQUIPAMENTO", nullable=false, unique=false, length=50)
//	private String valor_equipamento;
//	@Column(name="REGIME", nullable=false, unique=false,length=50)
//	private String regime;
	@Column(name="TIPO_CONTRATO", nullable=false, unique=false, length=50)
	private String tipo_contrato;
	@Column(name="BLOQUEIO", nullable=false, unique=false, length=4)
	private Integer bloqueio;
	@Column(name="BLOQUEIO_TOTAL", nullable=false, unique=false, length=4)
	private Integer bloqueio_total;
	@Column(name="CARENCIA", nullable=false, unique=false, length=3)
	private String carencia;
	@Column(name="TITULO", nullable=true, unique=false, columnDefinition="text")
	private String titulo;
//	@Column(name="CABECALHO", nullable=false, unique=false, columnDefinition="text")
//	private String cabecalho;
	@Column(name="VALOR_DESCONTO")
	private String valor_desconto;
	@Column(name="VALOR_EQUIPAMENTO")
	private String valor_equipamento;
	
	@Column(name="STATUS")
	private String status;
	@Column(name="ATO_DE_AUTORIZACAO")
	private String ato_autorizacao;
	@Column(name="TERMO_DE_AUTORIZACAO")
	private String termo_autorizacao;
	@Column(name="REGISTRO_CARTORIO")
	private String reg_cartorio;
	@Column(name="REGISTRO_CARTORIO_DATA")
	private Date reg_cartorio_data;
	@Column(name="REGISTRO_CARTORIO_SCM")
	private String reg_cartorio_scm;
	
	@Column(name="CLASSE_CONSUMO")
	private String classe_consumo;
	
	@Column(name="FRANQUIA")
	private String franquia;
	
	@Column(name="TAXA_TRANSMISSAO_INSTANTANEA")
	private String taxa_instantanea;
	
	@Column(name="TAXA_TRANSMISSAO_MEDIA")
	private String taxa_media;
	
	public ContratosAcesso(){
		
	}
	
	public ContratosAcesso(Integer id){
		this.id = id;
	}

	
	public ContratosAcesso(Integer id, Integer empresa_id, String nome,
			String clausulas, Integer vigencia, String valor_adesao,
			String tipo_contrato, Integer bloqueio,
			String carencia, String titulo,
			String valor_desconto, 
			String status, String ato_autorizacao, String termo_autorizacao, String reg_cartorio, String reg_cartorio_scm) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.nome = nome;
		this.clausulas = clausulas;
		this.vigencia = vigencia;
		this.valor_adesao = valor_adesao;
		//this.regime = regime;
		this.tipo_contrato = tipo_contrato;
		this.bloqueio = bloqueio;
		this.carencia = carencia;
		this.titulo = titulo;
//		this.cabecalho = cabecalho;
		this.valor_desconto = valor_desconto;
		this.status = status;
		this.ato_autorizacao = ato_autorizacao;
		this.termo_autorizacao = termo_autorizacao;
		this.reg_cartorio = reg_cartorio;
		this.reg_cartorio_scm = reg_cartorio_scm;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

//	public String getCabecalho() {
//		return cabecalho;
//	}
//
//	public void setCabecalho(String cabecalho) {
//		this.cabecalho = cabecalho;
//	}

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

	public String getClausulas() {
		return clausulas;
	}

	public void setClausulas(String clausulas) {
		this.clausulas = clausulas;
	}

	public Integer getVigencia() {
		return vigencia;
	}

	public void setVigencia(Integer vigencia) {
		this.vigencia = vigencia;
	}

	public String getValor_adesao() {
		return valor_adesao;
	}

	public void setValor_adesao(String valor_adesao) {
		this.valor_adesao = valor_adesao;
	}

//	public String getValor_equipamento() {
//		return valor_equipamento;
//	}
//
//	public void setValor_equipamento(String valor_equipamento) {
//		this.valor_equipamento = valor_equipamento;
//	}

//	public String getRegime() {
//		return regime;
//	}
//
//	public void setRegime(String regime) {
//		this.regime = regime;
//	}

	public String getTipo_contrato() {
		return tipo_contrato;
	}

	public void setTipo_contrato(String tipo_contrato) {
		this.tipo_contrato = tipo_contrato;
	}

	public Integer getBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(Integer bloqueio) {
		this.bloqueio = bloqueio;
	}
	

	public Integer getBloqueio_total() {
		return bloqueio_total;
	}

	public void setBloqueio_total(Integer bloqueio_total) {
		this.bloqueio_total = bloqueio_total;
	}

	public String getCarencia() {
		return carencia;
	}

	public void setCarencia(String carencia) {
		this.carencia = carencia;
	}

	public String getValor_desconto() {
		return valor_desconto;
	}

	public void setValor_desconto(String valor_desconto) {
		this.valor_desconto = valor_desconto;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAto_autorizacao() {
		return ato_autorizacao;
	}

	public void setAto_autorizacao(String ato_autorizacao) {
		this.ato_autorizacao = ato_autorizacao;
	}

	public String getTermo_autorizacao() {
		return termo_autorizacao;
	}

	public void setTermo_autorizacao(String termo_autorizacao) {
		this.termo_autorizacao = termo_autorizacao;
	}

	public String getReg_cartorio() {
		return reg_cartorio;
	}

	public void setReg_cartorio(String reg_cartorio) {
		this.reg_cartorio = reg_cartorio;
	}

	public String getReg_cartorio_scm() {
		return reg_cartorio_scm;
	}

	public void setReg_cartorio_scm(String reg_cartorio_scm) {
		this.reg_cartorio_scm = reg_cartorio_scm;
	}

	public String getClasse_consumo() {
		return classe_consumo;
	}

	public void setClasse_consumo(String classe_consumo) {
		this.classe_consumo = classe_consumo;
	}

	public Date getReg_cartorio_data() {
		return reg_cartorio_data;
	}

	public void setReg_cartorio_data(Date reg_cartorio_data) {
		this.reg_cartorio_data = reg_cartorio_data;
	}

	public String getFranquia() {
		return franquia;
	}

	public void setFranquia(String franquia) {
		this.franquia = franquia;
	}

	public String getTaxa_instantanea() {
		return taxa_instantanea;
	}

	public void setTaxa_instantanea(String taxa_instantanea) {
		this.taxa_instantanea = taxa_instantanea;
	}

	public String getTaxa_media() {
		return taxa_media;
	}

	public void setTaxa_media(String taxa_media) {
		this.taxa_media = taxa_media;
	}

	public String getValor_equipamento() {
		return valor_equipamento;
	}

	public void setValor_equipamento(String valor_equipamento) {
		this.valor_equipamento = valor_equipamento;
	}

	
}
