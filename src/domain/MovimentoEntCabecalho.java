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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="movimento_ent_cabecalho")
@Cacheable(value=false)
public class MovimentoEntCabecalho {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true, length=10)
	private Integer id;
	@Column(name="EMPRESA_ID", nullable=false, unique=false, length=10)
	private Integer empresa_id;
	@Column(name="COD_NF", nullable=false, unique=false, length=10)
	private Integer codNf;
	@Column(name="QTD_ITENS", nullable=false, unique=false)
	private Float qtdItens;
	@Column(name="VALOR_TOTAL", nullable=false, unique=false)
	private Float valorTotal;
	
	@Column(name="SITUACAO", nullable=false, unique=false)
	private String situacao;
	@Column(name="USUARIO", nullable=false, unique=false)
	private String usuario;
	
	@Column(name="TIPO", nullable=false, unique=false)
	private String tipo;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="DATA_HORA", nullable=true)
	private Date dataHora;

	
	@Column(name="FORNECEDOR_ID", nullable=false)
	private Integer fornecedor;
	
	@Column(name="TIPO_DOCUMENTO")
	private String tipo_documento; 
	
	@OneToOne
	@JoinColumn(name="NATUREZA_OPERACAO")
	private NaturezaOperacao natureza_operacao; 
	
	@Column(name="OBSERVACAO")
	private String observacao;
	
	public MovimentoEntCabecalho(){
		
	}
	
	@Transient
	private String coluna;
	@Transient
	private Date coluna_date;
	@Transient
	private Integer coluna_Inter;
	@Transient
	private float coluna_Float;
	@Transient
	private Long qtd;
	
	public MovimentoEntCabecalho(String coluna, Long qtd) {		
		this.coluna = coluna;
		this.qtd = qtd;
	}
	
	public MovimentoEntCabecalho(Date coluna_date, Long qtd) {		
		this.coluna_date = coluna_date;
		this.qtd = qtd;
	}

	public MovimentoEntCabecalho(Integer coluna_Inter, Long qtd) {		
		this.coluna_Inter = coluna_Inter;
		this.qtd = qtd;
	}
	
	public MovimentoEntCabecalho(float coluna_Float, Long qtd) {		
		this.coluna_Float = coluna_Float;
		this.qtd = qtd;
	}

	public float getColuna_Float() {
		return coluna_Float;
	}

	public void setColuna_Float(float coluna_Float) {
		this.coluna_Float = coluna_Float;
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

	public Integer getCodNf() {
		return codNf;
	}

	public void setCodNf(Integer codNf) {
		this.codNf = codNf;
	}

	public Float getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(Float qtdItens) {
		this.qtdItens = qtdItens;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Integer getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Integer fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public Date getColuna_date() {
		return coluna_date;
	}

	public void setColuna_date(Date coluna_date) {
		this.coluna_date = coluna_date;
	}

	public Integer getColuna_Inter() {
		return coluna_Inter;
	}

	public void setColuna_Inter(Integer coluna_Inter) {
		this.coluna_Inter = coluna_Inter;
	}

	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public NaturezaOperacao getNatureza_operacao() {
		return natureza_operacao;
	}

	public void setNatureza_operacao(NaturezaOperacao natureza_operacao) {
		this.natureza_operacao = natureza_operacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

	
	


}

