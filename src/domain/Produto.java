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
@Table(name="produto")
@Cacheable(value=false)
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false , unique=false)
	private Integer id;
	@OneToOne
	@JoinColumn(name="EMPRESA_ID", nullable=false)
	private Empresa empresaId;
	@Column(name="FORNECEDOR_ID", nullable=false)
	private Integer fornecedorId;
	@Column(name="GRUPO_ID", nullable=false)
	private Integer grupoId;
	@Column(name="MARCAS_ID", nullable=true)
	private Integer marcasId;
	@Column(name="ID_UNIDADE_PRODUTO")
	private Integer unidadeProduto;
	@Column(name="GTIN", nullable=true)
	private String 	gTin;
	@Column(name="NOME", nullable=true)
	private String 	nome;	
	@Column(name="VALOR_CUSTO", nullable=true)
	private Float 	valorCusto;
	@Column(name="VALOR_VENDA", nullable=true)
	private Float 	valorVenda;
	@Column(name="LUCRO", nullable=true)
	private Float 	lucro;
	@Column(name="GARANTIA", nullable=true)
	private String	garantia;
	@Column(name="QTD_ESTOQUE", nullable=true)
	private Float 	qtdEstoque;	
	@Column(name="QTD_ESTOQUE_DEPOSITO", nullable=true)
	private Float 	qtdEstoqueDeposito;	
	@Column(name="ESTOQUE_MIN", nullable=true)
	private Float 	estoqueMin;
	@Column(name="IAT", nullable=true)
	private String	iat;
	@Column(name="IPPT", nullable=true)
	private String	ippt;
	@Column(name="NCM", nullable=true)
	private String	ncm;	
	@Column(name="CEST", nullable=true)
	private String	cest;	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="DATA_ESTOQUE", nullable=true)
	private Date dataEstoque;	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="DATA_ALTERACAO", nullable=true)
	private Date dataAlteracao;
	@Column(name="REFERENCIA", nullable=true)
	private String 	referencia;
	@Column(name="TIPO_ITEM", nullable=true)
	private Integer tipo_item;				
	@Column(name="PESO", nullable=true)
	private Double 	peso;	
	@Column(name="ULTIMA_COMPRA", nullable=true)
	private String 	ultima_compra;	
	
	@Column(name="CST_ORIGEM", nullable=true)
	private Integer cst_origem;	
	
	@Column(name="CST_FORMA_TRIBUTO", nullable=true)
	private Integer	cst_forma_tributo;	
	
	@Column(name="SIMPLES_NACIONAL", nullable=true)
	private Integer	simples_nacional;
	
	@Column(name="ECF_CUP_FISCAL", nullable=true)
	private Integer	ecf_cup_filcal;	
	@Column(name="OUTRAS_TB_DESC", nullable=true)
	private String	outras_tb_desc;
	@Column(name="AVAL_CLIENTE", nullable=true)
	private String	aval_cliente;	
	@Column(name="TAXA_IPI", nullable=true)
	private double 	taxaIpi;
	@Column(name="FRETE", nullable=false)
	private Float 	frete;	
	@Column(name="MARGEM_LUCRO", nullable=false)
	private Float 	margemLucro;
	@Column(name="FRACIONAR", nullable=true)
	private Integer fracionar;	
	@Column(name="UTILIZAR_SERIAIS", nullable=true)
	private String	utilizaSeriais;
	@Column(name="STATUS", nullable=true)
	private String	status;	
	@Column(name="DIFERENCA_ALIQUOTA", nullable=true)	
	private Float diferenca_aliquota;
	@Column(name="PERMITIR_DESCONTO", nullable=true)	
	private Float permitir_desconto;	
	@Column(name="CUSTO_TOTAL", nullable=true)	
	private Float custo_total;
	@Column(name="TAXA_ICMS", nullable=true)	
	private Float taxaIcms;
	@OneToOne
	@JoinColumn(name="CFOP")
	private Cfop cfop;	
	@Column(name="AJUSTADO")
	private String ajustado;
	
	@Transient
	private String coluna;
	@Transient
	private Date coluna_date;
	@Transient
	private Long qtd;
	@Transient
	private Float coluna_float;
	@Transient
	private Integer coluna_Integer;
	
	
	@Column(name="NOME_PRODUTO_LOJA")
	private String nome_produto_loja;
	@Column(name="PRECO_PROMOCIONAL_LOJA")
	private Float preco_promocional;
	@Column(name="PRODUTO_ATIVADO_LOJA")
	private String produto_ativado;
	@Column(name="PRODUTO_DESTAQUE_LOJA")
	private String produto_destaque;
	@Column(name="URL_VIDEO_YOUTUBE_LOJA")
	private String url_video_youtube;
	@Column(name="DESC_PRODUTO_LOJA")
	private String desc_produto;
	@Column(name="COD_PRO_LOJA")
	private String cod_pro_loja;
	@Column(name="SIC_LOJA")
	private boolean sic_loja;
	
	
	@Column(name="PONTO_DE_PEDIDO", nullable=true)
	private String ponto_de_pedido;
	
	
	public Produto() {
		super();
	}
	
	
		
	public Produto(Integer id, Empresa empresaId, Integer fornecedorId,
			Integer grupoId, Integer marcasId, Integer unidadeProduto,
			String gTin, String nome, Float valorCusto, Float valorVenda,
			Float lucro, String garantia, Float qtdEstoque,
			Float qtdEstoqueDeposito, Float estoqueMin, String iat,
			String ippt, String ncm, String cest, Date dataEstoque,
			Date dataAlteracao, String referencia, Integer tipo_item,
			Double peso, String ultima_compra, Integer cst_origem,
			Integer cst_forma_tributo, Integer simples_nacional,
			Integer ecf_cup_filcal, String outras_tb_desc, String aval_cliente,
			double taxaIpi, Float frete, Float margemLucro, Integer fracionar,
			String utilizaSeriais, String status, Float diferenca_aliquota,
			Float permitir_desconto, Float custo_total, Float taxaIcms,
			Cfop cfop, String ajustado, String coluna, Date coluna_date,
			Long qtd, Float coluna_float, Integer coluna_Integer,
			String nome_produto_loja, Float preco_promocional,
			String produto_ativado, String produto_destaque,
			String url_video_youtube, String desc_produto, String cod_pro_loja,
			boolean sic_loja, String ponto_de_pedido) {
		super();
		this.id = id;
		this.empresaId = empresaId;
		this.fornecedorId = fornecedorId;
		this.grupoId = grupoId;
		this.marcasId = marcasId;
		this.unidadeProduto = unidadeProduto;
		this.gTin = gTin;
		this.nome = nome;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.lucro = lucro;
		this.garantia = garantia;
		this.qtdEstoque = qtdEstoque;
		this.qtdEstoqueDeposito = qtdEstoqueDeposito;
		this.estoqueMin = estoqueMin;
		this.iat = iat;
		this.ippt = ippt;
		this.ncm = ncm;
		this.cest = cest;
		this.dataEstoque = dataEstoque;
		this.dataAlteracao = dataAlteracao;
		this.referencia = referencia;
		this.tipo_item = tipo_item;
		this.peso = peso;
		this.ultima_compra = ultima_compra;
		this.cst_origem = cst_origem;
		this.cst_forma_tributo = cst_forma_tributo;
		this.simples_nacional = simples_nacional;
		this.ecf_cup_filcal = ecf_cup_filcal;
		this.outras_tb_desc = outras_tb_desc;
		this.aval_cliente = aval_cliente;
		this.taxaIpi = taxaIpi;
		this.frete = frete;
		this.margemLucro = margemLucro;
		this.fracionar = fracionar;
		this.utilizaSeriais = utilizaSeriais;
		this.status = status;
		this.diferenca_aliquota = diferenca_aliquota;
		this.permitir_desconto = permitir_desconto;
		this.custo_total = custo_total;
		this.taxaIcms = taxaIcms;
		this.cfop = cfop;
		this.ajustado = ajustado;
		this.coluna = coluna;
		this.coluna_date = coluna_date;
		this.qtd = qtd;
		this.coluna_float = coluna_float;
		this.coluna_Integer = coluna_Integer;
		this.nome_produto_loja = nome_produto_loja;
		this.preco_promocional = preco_promocional;
		this.produto_ativado = produto_ativado;
		this.produto_destaque = produto_destaque;
		this.url_video_youtube = url_video_youtube;
		this.desc_produto = desc_produto;
		this.cod_pro_loja = cod_pro_loja;
		this.sic_loja = sic_loja;
		this.ponto_de_pedido = ponto_de_pedido;
	}



	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}
	
	public Date getColuna_date() {
		return coluna_date;
	}
	public void setColuna_date(Date coluna_date) {
		this.coluna_date = coluna_date;
	}
	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	
	public Float getColuna_float() {
		return coluna_float;
	}

	public void setColuna_float(Float coluna_float) {
		this.coluna_float = coluna_float;
	}
    
	public Integer getColuna_Integer() {
		return coluna_Integer;
	}

	public void setColuna_Integer(Integer coluna_Integer) {
		this.coluna_Integer = coluna_Integer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Empresa empresaId) {
		this.empresaId = empresaId;
	}

	public Integer getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public Integer getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	public Integer getMarcasId() {
		return marcasId;
	}

	public void setMarcasId(Integer marcasId) {
		this.marcasId = marcasId;
	}

	public Integer getUnidadeProduto() {
		return unidadeProduto;
	}

	public void setUnidadeProduto(Integer unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public String getgTin() {
		return gTin;
	}

	public void setgTin(String gTin) {
		this.gTin = gTin;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public Float getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(Float valorCusto) {
		this.valorCusto = valorCusto;
	}

	public Float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Float valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Float getLucro() {
		return lucro;
	}

	public void setLucro(Float lucro) {
		this.lucro = lucro;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public Float getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Float qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	

	public Float getEstoqueMin() {
		return estoqueMin;
	}

	public void setEstoqueMin(Float estoqueMin) {
		this.estoqueMin = estoqueMin;
	}



	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getIppt() {
		return ippt;
	}

	public void setIppt(String ippt) {
		this.ippt = ippt;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}


	public Date getDataEstoque() {
		return dataEstoque;
	}

	public void setDataEstoque(Date dataEstoque) {
		this.dataEstoque = dataEstoque;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getTipo_item() {
		return tipo_item;
	}

	public void setTipo_item(Integer tipo_item) {
		this.tipo_item = tipo_item;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getUltima_compra() {
		return ultima_compra;
	}

	public void setUltima_compra(String ultima_compra) {
		this.ultima_compra = ultima_compra;
	}

	public Integer getCst_forma_tributo() {
		return cst_forma_tributo;
	}

	public void setCst_forma_tributo(Integer cst_forma_tributo) {
		this.cst_forma_tributo = cst_forma_tributo;
	}

	public Integer getSimples_nacional() {
		return simples_nacional;
	}

	public void setSimples_nacional(Integer simples_nacional) {
		this.simples_nacional = simples_nacional;
	}

	public Integer getEcf_cup_filcal() {
		return ecf_cup_filcal;
	}

	public void setEcf_cup_filcal(Integer ecf_cup_filcal) {
		this.ecf_cup_filcal = ecf_cup_filcal;
	}

	public String getOutras_tb_desc() {
		return outras_tb_desc;
	}

	public void setOutras_tb_desc(String outras_tb_desc) {
		this.outras_tb_desc = outras_tb_desc;
	}

	public String getAval_cliente() {
		return aval_cliente;
	}

	public void setAval_cliente(String aval_cliente) {
		this.aval_cliente = aval_cliente;
	}



	public double getTaxaIpi() {
		return taxaIpi;
	}

	public void setTaxaIpi(double taxaIpi) {
		this.taxaIpi = taxaIpi;
	}

	public Float getFrete() {
		return frete;
	}

	public void setFrete(Float frete) {
		this.frete = frete;
	}

	public Float getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(Float margemLucro) {
		this.margemLucro = margemLucro;
	}

	public Integer getFracionar() {
		return fracionar;
	}

	public void setFracionar(Integer fracionar) {
		this.fracionar = fracionar;
	}

	public String getUtilizaSeriais() {
		return utilizaSeriais;
	}

	public void setUtilizaSeriais(String utilizaSeriais) {
		this.utilizaSeriais = utilizaSeriais;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCst_origem() {
		return cst_origem;
	}

	public void setCst_origem(Integer cst_origem) {
		this.cst_origem = cst_origem;
	}

	public Float getDiferenca_aliquota() {
		return diferenca_aliquota;
	}

	public void setDiferenca_aliquota(Float diferenca_aliquota) {
		this.diferenca_aliquota = diferenca_aliquota;
	}

	public Float getPermitir_desconto() {
		return permitir_desconto;
	}

	public void setPermitir_desconto(Float permitir_desconto) {
		this.permitir_desconto = permitir_desconto;
	}

	public Float getCusto_total() {
		return custo_total;
	}

	public void setCusto_total(Float custo_total) {
		this.custo_total = custo_total;
	}

	public Float getTaxaIcms() {
		return taxaIcms;
	}

	public void setTaxaIcms(Float taxaIcms) {
		this.taxaIcms = taxaIcms;
	}

	public String getPonto_de_pedido() {
		if(getQtdEstoque() <= getEstoqueMin()){
			this.ponto_de_pedido = "SIM";
		}else{
			this.ponto_de_pedido = "NAO";
		}
		
		return ponto_de_pedido;
	}

	public void setPonto_de_pedido(String ponto_de_pedido) {
		if(getQtdEstoque() <= getEstoqueMin()){
			this.ponto_de_pedido = "SIM";
		}else{
			this.ponto_de_pedido = "NAO";
		}
	}

	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public String getNome_produto_loja() {
		return nome_produto_loja;
	}

	public void setNome_produto_loja(String nome_produto_loja) {
		this.nome_produto_loja = nome_produto_loja;
	}

	public Float getPreco_promocional() {
		return preco_promocional;
	}

	public void setPreco_promocional(Float preco_promocional) {
		this.preco_promocional = preco_promocional;
	}

	public String getProduto_ativado() {
		return produto_ativado;
	}

	public void setProduto_ativado(String produto_ativado) {
		this.produto_ativado = produto_ativado;
	}

	public String getProduto_destaque() {
		return produto_destaque;
	}

	public void setProduto_destaque(String produto_destaque) {
		this.produto_destaque = produto_destaque;
	}

	public String getUrl_video_youtube() {
		return url_video_youtube;
	}

	public void setUrl_video_youtube(String url_video_youtube) {
		this.url_video_youtube = url_video_youtube;
	}

	public String getDesc_produto() {
		return desc_produto;
	}

	public void setDesc_produto(String desc_produto) {
		this.desc_produto = desc_produto;
	}

	public boolean isSic_loja() {
		return sic_loja;
	}

	public void setSic_loja(boolean sic_loja) {
		this.sic_loja = sic_loja;
	}

	public String getCod_pro_loja() {
		return cod_pro_loja;
	}

	public void setCod_pro_loja(String cod_pro_loja) {
		this.cod_pro_loja = cod_pro_loja;
	}

	public Float getQtdEstoqueDeposito() {
		return qtdEstoqueDeposito;
	}

	public void setQtdEstoqueDeposito(Float qtdEstoqueDeposito) {
		this.qtdEstoqueDeposito = qtdEstoqueDeposito;
	}

	public String getAjustado() {
		return ajustado;
	}

	public void setAjustado(String ajustado) {
		this.ajustado = ajustado;
	}

	

	
	
	
}
