package domain;

import java.util.Date;
import java.util.List;

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
@Table(name="ecf_pre_venda_cabecalho")
@Cacheable(value=false)
public class EcfPreVendaCabecalho {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=true, unique=true, length=10)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="N_NF_DEVOLUCAO", nullable=true)
	private EcfPreVendaCabecalho n_nf_devolucao;
	
	@Column(name="EMPRESA_ID", nullable=false, unique=false, length=10)
	private Integer empresa_id;

	@Column(name="FORMAS_PAGAMENTO_ID")
	private Integer formaPagtoID;	
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PV", nullable=true, unique=false)
	private Date data;
	@Temporal(TemporalType.TIME)
	@Column(name="HORA_PV", nullable=true, unique=false, length=8)
	private Date hora_pv;
	@Column(name="SITUACAO", nullable=true, unique=false, length=1)
	private String situacao;
	@Column(name="CCF", nullable=true, unique=false, length=10)
	private Integer ccf;
	@Column(name="TOTAL_DESC", nullable=true, unique=false)
	private Float total_desc;
	@Column(name="TOTAL_ACRES", nullable=true, unique=false)
	private Float total_acres;
	@Column(name="SUB_TOTAL", nullable=true, unique=false)
	private Float subTotal;
	@Column(name="VALOR", nullable=true, unique=false)
	private Float valor;
	@Column(name="VENDEDOR", nullable=true, unique=false, length=100)
	private String vendedor;
	
	//@Column(name="N_PARCELAS", nullable=true, unique=false, length=10)
	//private Integer n_parcelas;
	
	@Column(name="TIPO_VENDA")
	private String tipoVenda;

	@Column(name="TIPO_PRODUTO")
	private Integer tipo_produto;
	
	@Column(name="ENTREGAR", nullable=true)
	private String entregar;
	
	@OneToOne
	@JoinColumn(name="ENDERECO", nullable=true)
	private Endereco end;

	@Column(name="TRANSPORTADORA", nullable=true)
	private Integer transportadora;
	
	@Column(name="COMPRADOR", nullable=true)
	private String comprador;
	
	@Column(name="TIPO", nullable=true)
	private String tipo;

	@Column(name="NATUREZA_OPERACAO", nullable=true)
	private Integer naturezaOperacao;
	
	@OneToOne
	@JoinColumn(name="CLIENTES_ID", nullable=true)
	private Cliente cliente;
	
	@Column(name="OBS", nullable=true)
	private String obs;
	
	@Column(name="N_OS")
	private Integer n_os;
	
	public EcfPreVendaCabecalho(){
		
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
	private List coluna_List;
	@Transient
	private Long qtd;
	
	
	
	
	
	public EcfPreVendaCabecalho(Integer id,
			EcfPreVendaCabecalho n_nf_devolucao, Integer empresa_id,
			Integer formaPagtoID, Date data, Date hora_pv, String situacao,
			Integer ccf, Float total_desc, Float total_acres, Float subTotal,
			Float valor, String vendedor, String tipoVenda,
			Integer tipo_produto, String entregar, Endereco end,
			Integer transportadora, String comprador, String tipo,
			Integer naturezaOperacao, Cliente cliente, String obs,
			Integer n_os, String coluna, Date coluna_date,
			Integer coluna_Inter, float coluna_Float, List coluna_List, Long qtd) {
		super();
		this.id = id;
		this.n_nf_devolucao = n_nf_devolucao;
		this.empresa_id = empresa_id;
		this.formaPagtoID = formaPagtoID;
		this.data = data;
		this.hora_pv = hora_pv;
		this.situacao = situacao;
		this.ccf = ccf;
		this.total_desc = total_desc;
		this.total_acres = total_acres;
		this.subTotal = subTotal;
		this.valor = valor;
		this.vendedor = vendedor;
		this.tipoVenda = tipoVenda;
		this.tipo_produto = tipo_produto;
		this.entregar = entregar;
		this.end = end;
		this.transportadora = transportadora;
		this.comprador = comprador;
		this.tipo = tipo;
		this.naturezaOperacao = naturezaOperacao;
		this.cliente = cliente;
		this.obs = obs;
		this.n_os = n_os;
		this.coluna = coluna;
		this.coluna_date = coluna_date;
		this.coluna_Inter = coluna_Inter;
		this.coluna_Float = coluna_Float;
		this.coluna_List = coluna_List;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora_pv() {
		return hora_pv;
	}

	public void setHora_pv(Date hora_pv) {
		this.hora_pv = hora_pv;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Integer getCcf() {
		return ccf;
	}

	public void setCcf(Integer ccf) {
		this.ccf = ccf;
	}

	public Float getTotal_desc() {
		return total_desc;
	}

	public void setTotal_desc(Float total_desc) {
		this.total_desc = total_desc;
	}

	public Float getTotal_acres() {
		return total_acres;
	}

	public void setTotal_acres(Float total_acres) {
		this.total_acres = total_acres;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getEntregar() {
		return entregar;
	}

	public void setEntregar(String entregar) {
		this.entregar = entregar;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Integer getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Integer transportadora) {
		this.transportadora = transportadora;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(Integer naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
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

	public String getTipoVenda() {
		return tipoVenda;
	}

	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = tipoVenda;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}


	
	public Integer getFormaPagtoID() {
		return formaPagtoID;
	}

	public void setFormaPagtoID(Integer formaPagtoID) {
		this.formaPagtoID = formaPagtoID;
	}

	public List getColuna_List() {
		return coluna_List;
	}

	public void setColuna_List(List coluna_List) {
		this.coluna_List = coluna_List;
	}

	public EcfPreVendaCabecalho getN_nf_devolucao() {
		return n_nf_devolucao;
	}

	public void setN_nf_devolucao(EcfPreVendaCabecalho n_nf_devolucao) {
		this.n_nf_devolucao = n_nf_devolucao;
	}

	public Integer getTipo_produto() {
		return tipo_produto;
	}

	public void setTipo_produto(Integer tipo_produto) {
		this.tipo_produto = tipo_produto;
	}

	public Integer getN_os() {
		return n_os;
	}

	public void setN_os(Integer n_os) {
		this.n_os = n_os;
	}
	
}
