package domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="movimento_ent_detalhe")
@Cacheable(value=false)
public class MovimentoEntDetalhe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true, length=10)
	private Integer id;
	@Column(name="PRODUTO_ID", nullable=false,length=6)
	private Integer produtoId;
	@Column(name="MOVIMENTO_ENT_CABECALHO_ID", nullable=false, length=10)
	private Integer movimentoEntCabecalhoId;
	@Column(name="UNIDADE_PRODUTO_ID", nullable=true,length=11)
	private Integer unidadeProdutoId;
	@Column(name="QTD_ANTERIOR", nullable=true)
	private Float 	quantidade_anterior;
	@Column(name="QTD", nullable=true)
	private Float 	quantidade;
	@Column(name="VALOR_CUSTO", nullable=true)
	private Float 	valorCusto;
	@Column(name="VALOR_VENDA", nullable=true)
	private Float 	valorVenda;
	@Column(name="ICMS", nullable=true)
	private Float 	icms;
	@Column(name="IPI", nullable=true)
	private Float 	ipi;
	@Column(name="DARF", nullable=true)
	private Float 	darf;
	@Column(name="GARANTIA", nullable=true)
	private String 	garantia;	
	@Column(name="DIFERENCA_ALIQUOTA")
	private Float diferenca_aliquota;
	
	@Transient
	@Column(name="PRODUCAO")
	private String 	producao;
	
	
	public MovimentoEntDetalhe(){
		
	}

	public MovimentoEntDetalhe(Integer id){
		
		this.id = id;
	}

	public MovimentoEntDetalhe(Integer id, Integer produtoId,
			Integer movimentoEntCabecalhoId, Integer unidadeProdutoId,
			Float quantidade, Float valorCusto, Float valorVenda, Float icms,
			Float ipi, Float darf, String garantia, String producao) {
		super();
		this.id = id;
		this.produtoId = produtoId;
		this.movimentoEntCabecalhoId = movimentoEntCabecalhoId;
		this.unidadeProdutoId = unidadeProdutoId;
		this.quantidade = quantidade;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.icms = icms;
		this.ipi = ipi;
		this.darf = darf;
		this.garantia = garantia;
		this.producao = producao;
	}
	
	public MovimentoEntDetalhe(Integer id, Integer produtoId,
			Integer movimentoEntCabecalhoId, Integer unidadeProdutoId,
			Float quantidade, Float valorCusto, Float valorVenda, Float icms,
			Float ipi, Float darf, String garantia, String producao, Float diferenca_aliquota) {
		super();
		this.id = id;
		this.produtoId = produtoId;
		this.movimentoEntCabecalhoId = movimentoEntCabecalhoId;
		this.unidadeProdutoId = unidadeProdutoId;
		this.quantidade = quantidade;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.icms = icms;
		this.ipi = ipi;
		this.darf = darf;
		this.garantia = garantia;
		this.producao = producao;
		this.diferenca_aliquota = diferenca_aliquota;
	}
	

	public MovimentoEntDetalhe(Integer id, Integer produtoId,
			Integer movimentoEntCabecalhoId, Integer unidadeProdutoId,
			Float quantidade_anterior,Float quantidade,  Float valorCusto, Float valorVenda, Float icms,
			Float ipi, Float darf, String garantia, String producao, Float diferenca_aliquota) {
		super();
		this.id = id;
		this.produtoId = produtoId;
		this.movimentoEntCabecalhoId = movimentoEntCabecalhoId;
		this.unidadeProdutoId = unidadeProdutoId;
		this.quantidade_anterior = quantidade_anterior;
		this.quantidade = quantidade;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.icms = icms;
		this.ipi = ipi;
		this.darf = darf;
		this.garantia = garantia;
		this.producao = producao;
		this.diferenca_aliquota = diferenca_aliquota;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getMovimentoEntCabecalhoId() {
		return movimentoEntCabecalhoId;
	}

	public void setMovimentoEntCabecalhoId(Integer movimentoEntCabecalhoId) {
		this.movimentoEntCabecalhoId = movimentoEntCabecalhoId;
	}

	public Integer getUnidadeProdutoId() {
		return unidadeProdutoId;
	}

	public void setUnidadeProdutoId(Integer unidadeProdutoId) {
		this.unidadeProdutoId = unidadeProdutoId;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
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

	public Float getIcms() {
		return icms;
	}

	public void setIcms(Float icms) {
		this.icms = icms;
	}

	public Float getIpi() {
		return ipi;
	}

	public void setIpi(Float ipi) {
		this.ipi = ipi;
	}

	public Float getDarf() {
		return darf;
	}

	public void setDarf(Float darf) {
		this.darf = darf;
	}

	public String getGarantia() {
		return garantia;
	}
	
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public String getProducao() {
		return producao;
	}

	public void setProducao(String producao) {
		this.producao = producao;
	}

	public Float getDiferenca_aliquota() {
		return diferenca_aliquota;
	}

	public void setDiferenca_aliquota(Float diferenca_aliquota) {
		this.diferenca_aliquota = diferenca_aliquota;
	}

	public Float getQuantidade_anterior() {
		return quantidade_anterior;
	}

	public void setQuantidade_anterior(Float quantidade_anterior) {
		this.quantidade_anterior = quantidade_anterior;
	}
	
	
	
	
	
	
}
