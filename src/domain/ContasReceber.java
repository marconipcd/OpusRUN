package domain;


import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="contas_receber")
@Cacheable(value=false)
public class ContasReceber {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID",nullable=false, unique=true)
	private Integer id;
	@Column(name="N_DOC",nullable=true, unique=false)
	private String n_doc;
	@Column(name="N_NUMERO",nullable=true, unique=false)
	private String n_numero;
	@Column(name="N_NUMERO_SICRED", nullable=true)
	private String n_numero_sicred;
	@Column(name="N_NUMERO_SICRED_ANTIGO", nullable=true)
	private String n_numero_sicred_antigo;
	@Column(name="N_NUMERO_GERENCIANET")
	private String n_numero_gerencianet;
	@Column(name="TRANSACAO_GERENCIANET")
	private String transacao_gerencianet;
	@Column(name="VALOR_TITULO",nullable=true, unique=false)
	private String valor_titulo;
	@Column(name="VALOR_PAGAMENTO",nullable=true, unique=false)
	private String valor_pagamento;
	@Column(name="VALOR_LANCAMENTO",nullable=true, unique=false)
	private String valor_lancamento;
	@Column(name="VALOR_TARIFA",nullable=true, unique=false)
	private String valor_tarifa;
	@Column(name="DATA_EMISSAO",nullable=true, unique=false)
	@Temporal(TemporalType.DATE)
	private Date  data_emissao;
	@Column(name="DATA_VENCIMENTO",nullable=true, unique=false)
	@Temporal(TemporalType.DATE)
	private Date data_vencimento;
	@Column(name="DATA_PAGAMENTO",nullable=true, unique=false)
	@Temporal(TemporalType.DATE)
	private Date data_pagamento;
	@Column(name="DATA_BAIXA",nullable=true, unique=false)
	@Temporal(TemporalType.DATE)
	private Date data_baixa;
	@Column(name="DATA_EXCLUSAO",nullable=true, unique=false)
	@Temporal(TemporalType.DATE)
	private Date data_exclusao;
	@Column(name="FORMA_PGTO",nullable=true, unique=false)
	private String forma_pgto;
	@Column(name="TIPO_BAIXA",nullable=true, unique=false)
	private String tipo_baixa;
	@Column(name="CONTROLE",nullable=true, unique=false)
	private String controle;
	@Column(name="CENTRO_CUSTO",nullable=true, unique=false)
	private String centro_custo;
	@Column(name="STATUS_2",nullable=true, unique=false)
	private String status;
	@Column(name="DESBLOQUEAR",nullable=true, unique=false)
	private String desbloquear;
	@Column(name="BLOQUEAR",nullable=true, unique=false)
	private String bloquear;
	@Column(name="DESBLOQUEADO",nullable=true, unique=false)
	private String desbloqueado;
	@Column(name="BLOQUEADO",nullable=true, unique=false)
	private String bloqueado;
	@Column(name="TIPO_TITULO",nullable=true, unique=false)
	private String tipo_titulo;
	
	@Column(name="OPERADOR",nullable=true, unique=false)
	private String operador;

	@Column(name="VALOR_RECEBIDO")
	private Double valor_recebido;
	@Column(name="VALOR_TROCO")
	private Double valor_troco;
	
	@Column(name="QTD")
	private String quantidade;
	
	@Column(name="PLANO_CONTRATO")
	private Integer plano_contrato;
	
	@Column(name="REMESSA_ENVIADA")
	private String remessa_enviada;
	
	@Column(name="COMANDO_REMESSA")
	private String comando_remessa;
	
	@Column(name="REMESSA_RECEBIDA_BANCO")
	private String remessa_recebida_banco;
	
	@Column(name="empresa_id",nullable=true, unique=false)
	private Integer empresa_id;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="id", name="CLIENTES_ID")
	private Cliente cliente;

	@Column(name="QTD_DIAS_PRO_RATA")
	private Integer qtdDiasProRata;
	
	@Transient
	private String coluna;
	@Transient
	private Date coluna_date;
	@Transient
	private Integer coluna_Inter;
	@Transient
	private Long qtd;
	
	public ContasReceber(String coluna, Long qtd) {		
		this.coluna = coluna;
		this.qtd = qtd;
	}
	
	public ContasReceber(Date coluna_date, Long qtd) {		
		this.coluna_date = coluna_date;
		this.qtd = qtd;
	}

	public ContasReceber(Integer coluna_Inter, Long qtd) {		
		this.coluna_Inter = coluna_Inter;
		this.qtd = qtd;
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	
	public Long getQtd() {
		return qtd;
	}




	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}




	public ContasReceber(){
		
	}
	
	public ContasReceber(Integer id){
		this.id = id;
	}
	
	
	public ContasReceber(Integer id, String n_doc, String n_numero,
			String valor_titulo, String valor_pagamento, Date data_emissao,
			Date data_vencimento, Date data_pagamento, Date data_baixa,
			Date data_exclusao, String forma_pgto, String tipo_baixa,
			String controle, String centro_custo, String status,
			String desbloquear, String bloquear, String desbloqueado,
			String bloqueado, String tipo_titulo, Double valor_recebido,
			Double valor_troco, Integer empresa_id, Cliente cliente) {
		super();
		this.id = id;
		this.n_doc = n_doc;
		this.n_numero = n_numero;
		this.valor_titulo = valor_titulo;
		this.valor_pagamento = valor_pagamento;
		this.data_emissao = data_emissao;
		this.data_vencimento = data_vencimento;
		this.data_pagamento = data_pagamento;
		this.data_baixa = data_baixa;
		this.data_exclusao = data_exclusao;
		this.forma_pgto = forma_pgto;
		this.tipo_baixa = tipo_baixa;
		this.controle = controle;
		this.centro_custo = centro_custo;
		this.status = status;
		this.desbloquear = desbloquear;
		this.bloquear = bloquear;
		this.desbloqueado = desbloqueado;
		this.bloqueado = bloqueado;
		this.tipo_titulo = tipo_titulo;
		this.valor_recebido = valor_recebido;
		this.valor_troco = valor_troco;
		this.empresa_id = empresa_id;
		this.cliente = cliente;
	}

	public ContasReceber(Cliente cliente, String ndocumento,String valorDoc,Date dataEmissao,Date dataVenc,String status,String tipo){
		this.cliente = cliente;
		this.n_doc = ndocumento;
		this.valor_titulo = valorDoc;
		this.status = status;
		this.tipo_titulo  = tipo;
		this.data_emissao = dataEmissao;
		this.data_vencimento = dataVenc;
	}
	
	public ContasReceber(Cliente cliente, String ndocumento,String valorDoc,Date dataEmissao,Date dataVenc,String status,String tipo, String controle){
		this.cliente = cliente;
		this.n_doc = ndocumento;
		this.valor_titulo = valorDoc;
		this.status = status;
		this.tipo_titulo  = tipo;
		this.data_emissao = dataEmissao;
		this.data_vencimento = dataVenc;
		this.controle = controle;
	}
	public ContasReceber(Cliente cliente, String ndocumento,String valorDoc,Date dataEmissao,Date dataVenc,String status,String tipo, String controle,Integer plano_contrato){
		this.cliente = cliente;
		this.n_doc = ndocumento;
		this.valor_titulo = valorDoc;
		this.status = status;
		this.tipo_titulo  = tipo;
		this.data_emissao = dataEmissao;
		this.data_vencimento = dataVenc;
		this.controle = controle;
		this.plano_contrato = plano_contrato;
	}
	public ContasReceber(Cliente cliente, String ndocumento,String valorDoc,Date dataEmissao,Date dataVenc,String status,String tipo, String controle,String qtd){
		this.cliente = cliente;
		this.n_doc = ndocumento;
		this.valor_titulo = valorDoc;
		this.status = status;
		this.tipo_titulo  = tipo;
		this.data_emissao = dataEmissao;
		this.data_vencimento = dataVenc;
		this.controle = controle;
		this.quantidade = qtd;
	}
	
	public ContasReceber(Integer id, String n_doc, String n_numero,
			String valor_titulo, String valor_pagamento, Date data_emissao,
			Date data_vencimento, Date data_pagamento, Date data_baixa,
			Date data_exclusao, String forma_pgto, String tipo_baixa,
			String controle, String centro_custo, String status,
			String desbloquear, String bloquear, String desbloqueado,
			String bloqueado, String tipo_titulo) {
		super();
		this.id = id;
		this.n_doc = n_doc;
		this.n_numero = n_numero;
		this.valor_titulo = valor_titulo;
		this.valor_pagamento = valor_pagamento;
		this.data_emissao = data_emissao;
		this.data_vencimento = data_vencimento;
		this.data_pagamento = data_pagamento;
		this.data_baixa = data_baixa;
		this.data_exclusao = data_exclusao;
		this.forma_pgto = forma_pgto;
		this.tipo_baixa = tipo_baixa;
		this.controle = controle;
		this.centro_custo = centro_custo;
		this.status = status;
		this.desbloquear = desbloquear;
		this.bloquear = bloquear;
		this.desbloqueado = desbloqueado;
		this.bloqueado = bloqueado;
		this.tipo_titulo = tipo_titulo;
	}
	
	public ContasReceber(Integer id, String n_doc, String n_numero,
			String valor_titulo, String valor_pagamento, Date data_emissao,
			Date data_vencimento, Date data_pagamento, Date data_baixa,
			Date data_exclusao, String forma_pgto, String tipo_baixa,
			String controle, String centro_custo, String status,
			String desbloquear, String bloquear, String desbloqueado,
			String bloqueado, String tipo_titulo,Cliente cliente,Integer empresa_id) {
		super();
		this.id = id;
		this.n_doc = n_doc;
		this.n_numero = n_numero;
		this.valor_titulo = valor_titulo;
		this.valor_pagamento = valor_pagamento;
		this.data_emissao = data_emissao;
		this.data_vencimento = data_vencimento;
		this.data_pagamento = data_pagamento;
		this.data_baixa = data_baixa;
		this.data_exclusao = data_exclusao;
		this.forma_pgto = forma_pgto;
		this.tipo_baixa = tipo_baixa;
		this.controle = controle;
		this.centro_custo = centro_custo;
		this.status = status;
		this.desbloquear = desbloquear;
		this.bloquear = bloquear;
		this.desbloqueado = desbloqueado;
		this.bloqueado = bloqueado;
		this.tipo_titulo = tipo_titulo;
		this.cliente = cliente;
		this.empresa_id = empresa_id;
		
	}
	
	public ContasReceber(Integer id, String n_doc, String n_numero,
			String valor_titulo, String valor_pagamento, Date data_emissao,
			Date data_vencimento, Date data_pagamento, Date data_baixa,
			Date data_exclusao, String forma_pgto, String tipo_baixa,
			String controle, String centro_custo, String status,
			String desbloquear, String bloquear, String desbloqueado,
			String bloqueado, String tipo_titulo,Cliente cliente,Integer empresa_id, Integer qtdDiasProRata) {
		super();
		this.id = id;
		this.n_doc = n_doc;
		this.n_numero = n_numero;
		this.valor_titulo = valor_titulo;
		this.valor_pagamento = valor_pagamento;
		this.data_emissao = data_emissao;
		this.data_vencimento = data_vencimento;
		this.data_pagamento = data_pagamento;
		this.data_baixa = data_baixa;
		this.data_exclusao = data_exclusao;
		this.forma_pgto = forma_pgto;
		this.tipo_baixa = tipo_baixa;
		this.controle = controle;
		this.centro_custo = centro_custo;
		this.status = status;
		this.desbloquear = desbloquear;
		this.bloquear = bloquear;
		this.desbloqueado = desbloqueado;
		this.bloqueado = bloqueado;
		this.tipo_titulo = tipo_titulo;
		this.cliente = cliente;
		this.empresa_id = empresa_id;
		this.qtdDiasProRata = qtdDiasProRata;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getN_doc() {
		return n_doc;
	}

	public void setN_doc(String n_doc) {
		this.n_doc = n_doc;
	}

	public String getN_numero() {
		return n_numero;
	}

	public void setN_numero(String n_numero) {
		this.n_numero = n_numero;
	}

	public String getValor_titulo() {
		return valor_titulo;
	}

	public void setValor_titulo(String valor_titulo) {
		this.valor_titulo = valor_titulo;
	}

	public String getValor_pagamento() {
		return valor_pagamento;
	}

	public void setValor_pagamento(String valor_pagamento) {
		this.valor_pagamento = valor_pagamento;
	}

	public Date getData_emissao() {
		return data_emissao;
	}
	
	public String getN_numero_sicred(){
		return n_numero_sicred;
	}
	
	public void setN_numero_sicred(String n_numero_sicred){
		this.n_numero_sicred = n_numero_sicred;
	}

	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Date getData_baixa() {
		return data_baixa;
	}

	public void setData_baixa(Date data_baixa) {
		this.data_baixa = data_baixa;
	}

	public Date getData_exclusao() {
		return data_exclusao;
	}

	public void setData_exclusao(Date data_exclusao) {
		this.data_exclusao = data_exclusao;
	}

	public String getForma_pgto() {
		return forma_pgto;
	}

	public void setForma_pgto(String forma_pgto) {
		this.forma_pgto = forma_pgto;
	}

	public String getTipo_baixa() {
		return tipo_baixa;
	}

	public void setTipo_baixa(String tipo_baixa) {
		this.tipo_baixa = tipo_baixa;
	}

	public String getControle() {
		return controle;
	}

	public void setControle(String controle) {
		this.controle = controle;
	}

	public String getCentro_custo() {
		return centro_custo;
	}

	public void setCentro_custo(String centro_custo) {
		this.centro_custo = centro_custo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesbloquear() {
		return desbloquear;
	}

	public void setDesbloquear(String desbloquear) {
		this.desbloquear = desbloquear;
	}

	public String getBloquear() {
		return bloquear;
	}

	public void setBloquear(String bloquear) {
		this.bloquear = bloquear;
	}

	public String getDesbloqueado() {
		return desbloqueado;
	}

	public void setDesbloqueado(String desbloqueado) {
		this.desbloqueado = desbloqueado;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getTipo_titulo() {
		return tipo_titulo;
	}

	public void setTipo_titulo(String tipo_titulo) {
		this.tipo_titulo = tipo_titulo;
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

	public Double getValor_recebido() {
		return valor_recebido;
	}

	public void setValor_recebido(Double valor_recebido) {
		this.valor_recebido = valor_recebido;
	}

	public Double getValor_troco() {
		return valor_troco;
	}

	public void setValor_troco(Double valor_troco) {
		this.valor_troco = valor_troco;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
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

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPlano_contrato() {
		return plano_contrato;
	}

	public void setPlano_contrato(Integer plano_contrato) {
		this.plano_contrato = plano_contrato;
	}
		
	public String getRemessaEnviada(){
		return remessa_enviada;
	}
	public void setRemessaEnviada(String remessa_enviada){
		this.remessa_enviada = remessa_enviada;
	}
	
	public String getComandoRemessa(){
		return comando_remessa;
	}
	public void setComandoRemessa(String comando_remessa){
		this.comando_remessa = comando_remessa;
	}

	public String getRemessa_recebida_banco() {
		return remessa_recebida_banco;
	}

	public void setRemessa_recebida_banco(String remessa_recebida_banco) {
		this.remessa_recebida_banco = remessa_recebida_banco;
	}

	public String getValor_lancamento() {
		return valor_lancamento;
	}

	public void setValor_lancamento(String valor_lancamento) {
		this.valor_lancamento = valor_lancamento;
	}

	public String getValor_tarifa() {
		return valor_tarifa;
	}

	public void setValor_tarifa(String valor_tarifa) {
		this.valor_tarifa = valor_tarifa;
	}

	public Integer getQtdDiasProRata() {
		return qtdDiasProRata;
	}

	public void setQtdDiasProRata(Integer qtdDiasProRata) {
		this.qtdDiasProRata = qtdDiasProRata;
	}

	public String getN_numero_sicred_antigo() {
		return n_numero_sicred_antigo;
	}

	public void setN_numero_sicred_antigo(String n_numero_sicred_antigo) {
		this.n_numero_sicred_antigo = n_numero_sicred_antigo;
	}

	public String getN_numero_gerencianet() {
		return n_numero_gerencianet;
	}

	public void setN_numero_gerencianet(String n_numero_gerencianet) {
		this.n_numero_gerencianet = n_numero_gerencianet;
	}

	public String getTransacao_gerencianet() {
		return transacao_gerencianet;
	}

	public void setTransacao_gerencianet(String transacao_gerencianet) {
		this.transacao_gerencianet = transacao_gerencianet;
	}

	public String getRemessa_enviada() {
		return remessa_enviada;
	}

	public void setRemessa_enviada(String remessa_enviada) {
		this.remessa_enviada = remessa_enviada;
	}

	public String getComando_remessa() {
		return comando_remessa;
	}

	public void setComando_remessa(String comando_remessa) {
		this.comando_remessa = comando_remessa;
	}
	
	
}
