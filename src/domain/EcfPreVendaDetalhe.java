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
@Table(name="ecf_pre_venda_detalhe")
@Cacheable(value=false)
public class EcfPreVendaDetalhe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true, length=10)
	private Integer id;
	@Column(name="ID_PRODUTO", nullable=false,length=6)
	private Integer produtoId;
	@Column(name="ID_ECF_PRE_VENDA_CABECALHO", nullable=false, length=10)
	private Integer ecfPreVendaCabecalhoId;
	@Column(name="ORDEM", nullable=true,length=11)
	private Integer ordem;
	@Column(name="QUANTIDADE", nullable=true)
	private Float 	quantidade;
	@Column(name="VALOR_UNITARIO", nullable=true)
	private Float 	valorUnitario;
	@Column(name="VALOR_TOTAL", nullable=true)
	private Float 	valorTotal;
	@Column(name="CANCELADO", nullable=true,length=1)
	private String 	cancelado;
	
	@Transient
	@Column(name="PRODUCAO")
	private String 	producao;
	
	
	public EcfPreVendaDetalhe(){
		
	}

	public EcfPreVendaDetalhe(Integer id){
		
		this.id = id;
	}
	
	
	public EcfPreVendaDetalhe(Integer id, Integer produtoId, Integer ecfPreVendaCabecalhoId,
			Integer ordem, Float quantidade,Float valorUnitario, Float valorTotal, String cancelado ){
		
		this.id = id;
		this.produtoId = produtoId;
		this.ecfPreVendaCabecalhoId = ecfPreVendaCabecalhoId;
		this.ordem = ordem;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.cancelado = cancelado;		
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

	public Integer getEcfPreVendaCabecalhoId() {
		return ecfPreVendaCabecalhoId;
	}

	public void setEcfPreVendaCabecalhoId(Integer ecfPreVendaCabecalhoId) {
		this.ecfPreVendaCabecalhoId = ecfPreVendaCabecalhoId;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public String getProducao() {
		return producao;
	}

	public void setProducao(String producao) {
		this.producao = producao;
	}
	
	
	
	
}
