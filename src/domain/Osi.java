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

@Entity
@Table(name="osi")
@Cacheable(value=false)
public class Osi {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true) 
	private Integer id;
	@Column(name="EMPRESA_ID", nullable=false)
	private Integer empresa_id;
	@OneToOne
	@JoinColumn(name="CLIENTES_ID", nullable=false)
	private Cliente cliente;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ENTRADA", nullable=false)
	private Date data_entrada; 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_AGENDAMENTO", nullable=false)
	private Date data_agendamento;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ECAMINHAMENTO", nullable=true)
	private Date data_encaminhamento;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CONCLUSAO", nullable=true)
	private Date data_conclusao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ENTREGA", nullable=true)
	private Date data_entrega;
	@Column(name="CONTATO", length=200, nullable=false)
	private String contato;
	@Column(name="EQUIPAMENTO", length=200, nullable=false)
	private String equipamento;
	@Column(name="ACESSORIOS", length=200, nullable=true)
	private String acessorios;
	@Column(name="OBSERVACAO", length=200, nullable=true)
	private String observacao;
	@Column(name="DIAS_EM_MANUTENCAO", length=10, nullable=false)
	private String dias_em_manutencao;
	@Column(name="OPERADOR", length=50, nullable=false)
	private String operador;
	@Column(name="TECNICO", length=50, nullable=true) 
	private String tecnico; 
	@Column(name="PROBLEMA", length=200, nullable=true)
	private String problema;
	@Column(name="CONCLUSAO", length=300, nullable=true)
	private String conclusao;
	@Column(name="PECAS_SUBS", length=300, nullable=true)
	private String pecas_subs;
	@Column(name="VALOR", length=50, nullable=true)
	private String valor;
	@Column(name="NF_GARANTIA", length=100, nullable=true)
	private String nf_garantia;
	@Column(name="STATUS_2", length=20, nullable=false)
	private String status;
	@Column(name="ENTREGAR", length=20, nullable=false)
	private String entregar;
	
	@OneToOne
	@JoinColumn(name="ENDERECO_ID")
	private Endereco end;
	
	public Osi(){
		
	}
	
	public Osi(Integer id){
		this.id = id;
	}

	public Osi(Integer id, Integer empresa_id, Cliente cliente,
			Date data_entrada, Date data_agendamento, Date data_encaminhamento,
			Date data_conclusao, String contato, String equipamento,
			String acessorios, String observacao, String dias_em_manutencao,
			String operador, String tecnico, String problema, String conclusao,
			String valor, String nf_garantia, String status) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.cliente = cliente;
		this.data_entrada = data_entrada;
		this.data_agendamento = data_agendamento;
		this.data_encaminhamento = data_encaminhamento;
		this.data_conclusao = data_conclusao;
		this.contato = contato;
		this.equipamento = equipamento;
		this.acessorios = acessorios;
		this.observacao = observacao;
		this.dias_em_manutencao = dias_em_manutencao;
		this.operador = operador;
		this.tecnico = tecnico;
		this.problema = problema;
		this.conclusao = conclusao;
		this.valor = valor;
		this.nf_garantia = nf_garantia;
		this.status = status;
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

	public Date getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}

	public Date getData_agendamento() {
		return data_agendamento;
	}

	public void setData_agendamento(Date data_agendamento) {
		this.data_agendamento = data_agendamento;
	}

	public Date getData_encaminhamento() {
		return data_encaminhamento;
	}

	public void setData_encaminhamento(Date data_encaminhamento) {
		this.data_encaminhamento = data_encaminhamento;
	}

	public Date getData_conclusao() {
		return data_conclusao;
	}

	public void setData_conclusao(Date data_conclusao) {
		this.data_conclusao = data_conclusao;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(String acessorios) {
		this.acessorios = acessorios;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDias_em_manutencao() {
		return dias_em_manutencao;
	}

	public void setDias_em_manutencao(String dias_em_manutencao) {
		this.dias_em_manutencao = dias_em_manutencao;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNf_garantia() {
		return nf_garantia;
	}

	public void setNf_garantia(String nf_garantia) {
		this.nf_garantia = nf_garantia;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Date getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}

	public String getPecas_subs() {
		return pecas_subs;
	}

	public void setPecas_subs(String pecas_subs) {
		this.pecas_subs = pecas_subs;
	}

	public String getEntregar() {
		return entregar;
	}

	public void setEntregar(String entregar) {
		this.entregar = entregar;
	}
	
	
}
