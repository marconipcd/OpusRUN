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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="crm")
@Cacheable(value=false)
public class Crm {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)	
	private Integer id;
	@Column(name="EMPRESA_ID", nullable=false)
	private Integer empresa_id;
	@OneToOne
	@JoinColumn(name="SETOR_ID", nullable=false) 
	private Setores setor;
	@Column(name="NIVEL") 
	private String nivel;
	@OneToOne
	@JoinColumn(name="clientes_id")
	private Cliente cliente;
	@OneToOne
	@JoinColumn(name="CRM_ASSUNTOS_ID", nullable=false) 
	private CrmAssunto crm_assuntos;
	@OneToOne
	@JoinColumn(name="CRM_FORMAS_CONTATO_ID", nullable=false) 
	private CrmFormasContato crm_formas_contato;
	@Column(name="CONTATO") 
	private String contato;
	@Column(name="ORIGEM") 
	private String origem;
	@Column(name="CONTEUDO") 
	private String conteudo; 
	@Column(name="DATA_AGENDADO")
	@Temporal(TemporalType.DATE)
	private Date data_agendado;
	@Column(name="HORA_AGENDADO")
	@Temporal(TemporalType.TIME)
	private Date hora_agendado;
	@Column(name="DATA_CADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_cadastro;
	@Column(name="DATA_EFETUADO") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_efetuado;
	@Column(name="STATUS") 
	private String status;
	@Column(name="OPERADOR")
	private String operador;

	@Column(name="OPERADOR_TRATAMENTO", nullable=true)
	private String operador_tratamento;
	@Column(name="DATA_INICIO_TRATAMENTO", nullable=true) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_inicio_tratamento;	
	@Column(name="TEMPO_TRATAMENTO", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date tempo_total_tratamento;	
	@Column(name="MOTIVO_REAGENDAMENTO")
	private String motivo_reagendamento;
	
	@OneToOne
	@JoinColumn(name="CONTATO_FEEDBACK") 
	private CrmFormasContato contato_feedback;
	

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ULTIMA_ITERACAO_TRATAMENTO")
	private Date last_iteracao;
	
	@Column(name="CONCLUIDO_PRAZO", nullable=true)
	private boolean concluido_no_prazo;
	@Column(name="REAGENDADO", nullable=true)
	private boolean reagendado;
	
	@OneToOne
	@JoinColumn(name="CONTRATO_ID")
	private AcessoCliente contrato;
	
	@OneToOne
	@JoinColumn(name="ENDERECO_ID")
	private Endereco end;
	
	@Column(name="TIPO_ROTINA")
	private String tipo_rotina;
	
	
	@PrePersist
	private void preInsert()
	{
		data_cadastro = new Date();
	}
	
	
	
	@Transient
	private String coluna;
	@Transient
	private Date coluna_date;
	@Transient
	private Integer coluna_Inter;
	@Transient
	private Long qtd;
	
	public Crm(String coluna, Long qtd) {		
		this.coluna = coluna;
		this.qtd = qtd;
	}
	
	public Crm(Date coluna_date, Long qtd) {		
		this.coluna_date = coluna_date;
		this.qtd = qtd;
	}

	public Crm(Integer coluna_Inter, Long qtd) {		
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

	public Crm(){
		
	}
	
	public Crm(Integer id){
		super();
		this.id = id;
	}

	public Crm(Integer id, Integer empresa_id, Setores setor,
			Cliente cliente, CrmAssunto crm_assuntos,
			CrmFormasContato crm_formas_contato, String contato, String origem,
			String conteudo, Date data_agendado, Date hora_agendado,
			Date data_cadastro, Date data_efetuado, String status,
			String operador, CrmFormasContato contato_feedback) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.setor = setor;
		this.cliente = cliente;
		this.crm_assuntos = crm_assuntos;
		this.crm_formas_contato = crm_formas_contato;
		this.contato = contato;
		this.origem = origem;
		this.conteudo = conteudo;
		this.data_agendado = data_agendado;
		this.hora_agendado = hora_agendado;
		this.data_cadastro = data_cadastro;
		this.data_efetuado = data_efetuado;
		this.status = status;
		this.operador = operador;
		this.contato_feedback = contato_feedback;
	}
	
	public Crm(Integer id, Integer empresa_id, Setores setor,
			Cliente cliente, CrmAssunto crm_assuntos,
			CrmFormasContato crm_formas_contato, String contato, String origem,
			String conteudo, Date data_agendado, Date hora_agendado,
			Date data_cadastro, Date data_efetuado, String status,
			String operador) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.setor = setor;
		this.cliente = cliente;
		this.crm_assuntos = crm_assuntos;
		this.crm_formas_contato = crm_formas_contato;
		this.contato = contato;
		this.origem = origem;
		this.conteudo = conteudo;
		this.data_agendado = data_agendado;
		this.hora_agendado = hora_agendado;
		this.data_cadastro = data_cadastro;
		this.data_efetuado = data_efetuado;
		this.status = status;
		this.operador = operador;
	}
	
	public Crm(Integer id, Integer empresa_id, Setores setor,
			Cliente cliente, CrmAssunto crm_assuntos,
			CrmFormasContato crm_formas_contato, String contato, String origem,
			String conteudo, Date data_agendado, Date hora_agendado,
			Date data_cadastro, Date data_efetuado, String status,
			String operador, String nivel) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.setor = setor;
		this.cliente = cliente;
		this.crm_assuntos = crm_assuntos;
		this.crm_formas_contato = crm_formas_contato;
		this.contato = contato;
		this.origem = origem;
		this.conteudo = conteudo;
		this.data_agendado = data_agendado;
		this.hora_agendado = hora_agendado;
		this.data_cadastro = data_cadastro;
		this.data_efetuado = data_efetuado;
		this.status = status;
		this.operador = operador;
		this.nivel = nivel;
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

	public Setores getSetor() {
		return setor;
	}

	public void setSetor(Setores setor) {
		this.setor = setor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CrmAssunto getCrm_assuntos() {
		return crm_assuntos;
	}

	public void setCrm_assuntos(CrmAssunto crm_assuntos) {
		this.crm_assuntos = crm_assuntos;
	}

	public CrmFormasContato getCrm_formas_contato() {
		return crm_formas_contato;
	}

	public void setCrm_formas_contato(CrmFormasContato crm_formas_contato) {
		this.crm_formas_contato = crm_formas_contato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getData_agendado() {
		return data_agendado;
	}

	public void setData_agendado(Date data_agendado) {
		this.data_agendado = data_agendado;
	}

	public Date getHora_agendado() {
		return hora_agendado;
	}

	public void setHora_agendado(Date hora_agendado) {
		this.hora_agendado = hora_agendado;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_efetuado() {
		return data_efetuado;
	}

	public void setData_efetuado(Date data_efetuado) {
		this.data_efetuado = data_efetuado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getMotivo_reagendamento() {
		return motivo_reagendamento;
	}

	public void setMotivo_reagendamento(String motivo_reagendamento) {
		this.motivo_reagendamento = motivo_reagendamento;
	}

	public String getOperador_tratamento() {
		return operador_tratamento;
	}

	public void setOperador_tratamento(String operador_tratamento) {
		this.operador_tratamento = operador_tratamento;
	}

	public Date getLast_iteracao() {
		return last_iteracao;
	}

	public void setLast_iteracao(Date last_iteracao) {
		this.last_iteracao = last_iteracao;
	}

	public Date getData_inicio_tratamento() {
		return data_inicio_tratamento;
	}

	public void setData_inicio_tratamento(Date data_inicio_tratamento) {
		this.data_inicio_tratamento = data_inicio_tratamento;
	}

	public Date getTempo_total_tratamento() {
		return tempo_total_tratamento;
	}

	public void setTempo_total_tratamento(Date tempo_total_tratamento) {
		this.tempo_total_tratamento = tempo_total_tratamento;
	}

	public boolean isConcluido_no_prazo() {
		return concluido_no_prazo;
	}

	public void setConcluido_no_prazo(boolean concluido_no_prazo) {
		this.concluido_no_prazo = concluido_no_prazo;
	}

	public boolean isReagendado() {
		return reagendado;
	}

	public void setReagendado(boolean reagendado) {
		this.reagendado = reagendado;
	}

	public CrmFormasContato getContato_feedback() {
		return contato_feedback;
	}

	public void setContato_feedback(CrmFormasContato contato_feedback) {
		this.contato_feedback = contato_feedback;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public AcessoCliente getContrato() {
		return contrato;
	}

	public void setContrato(AcessoCliente contrato) {
		this.contrato = contrato;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public String getTipo_rotina() {
		return tipo_rotina;
	}

	public void setTipo_rotina(String tipo_rotina) {
		this.tipo_rotina = tipo_rotina;
	}

	
	
	
	
}

