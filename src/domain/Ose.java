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
@Table(name="ose")
@Cacheable(value=false)
public class Ose {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	@Column(name="EMPRESA_ID", nullable=false)
	private Integer empresa_id;
	@OneToOne
	@JoinColumn(name="CLIENTES_ID", nullable=false)
	private Cliente cliente;
	@Column(name="ID_ECF_PRE_VENDA_CABECALHO", nullable=true)
	private Integer id_ecf_pre_venda_cabecalho;
	@Column(name="VENDA_SERVICO_CABECALHO_ID", nullable=true)
	private Integer venda_servico_cabecalho_id;
	@OneToOne
	@JoinColumn(name="SUBGRUPO_ID")
	private SubGrupoOse subgrupo;
	
	@Column(name="GRUPO_ID")
	private Integer grupo;
	@OneToOne
	@JoinColumn(name="TIPO_SUBGRUPO_ID")
	private TipoSubGrupoOse tipo_subgrupo;
	@Column(name="TIPO")
	private String tipo;
	@OneToOne
	@JoinColumn(name="VEICULO_ID")
	private Veiculos veiculo_id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_EX", nullable=true)
	private Date data_ex;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ENCAMINHAMENTO", nullable=true)
	private Date data_encaminhamento;
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ABERTURA", nullable=false) 
	private Date data_abertura;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_FECHAMENTO", nullable=true) 
	private Date data_fechamento;
	@Column(name="TURNO", length=10,nullable=false)
	private String turno;
	@Column(name="BASE", length=50, nullable=true)
	private String base;
	@Column(name="CONCENTRADOR", length=50, nullable=true)
	private String concentrador;
	@Column(name="PLANO", length=50, nullable=true)
	private String plano;
	@Column(name="MATERIAL", length=50)
	private String material;
	@Column(name="OPERADOR_ABERTURA",length=100)
	private String operador_abertura;
	@Column(name="CONTATO", length=200)
	private String contato;
	@Column(name="CIDADE",length=100)
	private String cidade;
	@Column(name="BAIRRO",length=50)
	private String bairro;
	@Column(name="ENDERECO", length=200)
	private String endereco;
	@Column(name="REFERENCIA", length=100)
	private String referencia;
	@Column(name="STATUS_2", length=20, nullable=false) 
	private String status; 
	@Column(name="TIPO_ENCAMINHAMENTO", length=20)
	private String tipo_encaminhamento;
	@Column(name="MOTIVO", length=100)
	private String motivo;
	@Column(name="PROBLEMA",length=200) 
	private String problema; 
	@Column(name="CONCLUSAO",length=200)
	private String conclusao;
	@Column(name="AUSENTE",length=1)
	private String ausente;
	@Column(name="PRIORIDADE", length=10)
	private String prioridade;
	@Column(name="OBS", length=400)
	private String obs;
	@Column(name="NOTA_FISCAL", length=50)
	private String nota_fiscal;
	@Column(name="TIPO_SERVICO",length=100)
	private String tipo_servico;
	@Column(name="TECNICO",length=50)
	private String tecnico;
	@Column(name="OPERADOR", length=50)
	private String operador;
	@Column(name="VALOR")
	private String valor;
	@Column(name="VENCIMENTO")
	private Date vencimento;
	@Column(name="MOTIVO_REAGENDAMENTO")
	private String motivo_reagendamento;
	
	
	@Transient
	private String tempo_total_atendimento;
	
	@Transient
	private String tempo_atendimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CONCLUSAO")
	private Date data_conclusao;
	
	@Column(name="ENDERECO_ID")
	private Integer end;
	
	@Column(name="ACESSO_CLIENTE_ID")
	private Integer contrato;
	
	public Ose(){
		
	}
	
	@Transient
	private String coluna;
	@Transient
	private Date coluna_date;
	@Transient
	private Integer coluna_Inter;
	@Transient
	private Long qtd;
	
	public Ose(String coluna, Long qtd) {		
		this.coluna = coluna;
		this.qtd = qtd;
	}
	
	public Ose(Date coluna_date, Long qtd) {		
		this.coluna_date = coluna_date;
		this.qtd = qtd;
	}

	public Ose(Integer coluna_Inter, Long qtd) {		
		this.coluna_Inter = coluna_Inter;
		this.qtd = qtd;
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

	
	public Ose(Integer id){
		this.id = id;
	}

	public Ose(Integer id, Integer empresa_id, Cliente cliente,
			Integer id_ecf_pre_venda_cabecalho,
			Integer venda_servico_cabecalho_id, SubGrupoOse subgrupo,
			Integer grupo, TipoSubGrupoOse tipo_subgrupo, String tipo,
			Veiculos veiculo_id, Date data_ex, Date data_encaminhamento,
			Date data_abertura, Date data_fechamento, String turno,
			String base, String material, String operador_abertura,
			String contato,String cidade, String bairro, String endereco, String referencia,
			String status, String tipo_encaminhamento, String motivo,
			String problema, String conclusao, String ausente,
			String prioridade, String obs, String nota_fiscal,
			String tipo_servico, String tecnico, String operador) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.cliente = cliente;
		this.id_ecf_pre_venda_cabecalho = id_ecf_pre_venda_cabecalho;
		this.venda_servico_cabecalho_id = venda_servico_cabecalho_id;
		this.subgrupo = subgrupo;
		this.grupo = grupo;
		this.tipo_subgrupo = tipo_subgrupo;
		this.tipo = tipo;
		this.veiculo_id = veiculo_id;
		this.data_ex = data_ex;
		this.data_encaminhamento = data_encaminhamento;
		this.data_abertura = data_abertura;
		this.data_fechamento = data_fechamento;
		this.turno = turno;
		this.base = base;
		this.material = material;
		this.operador_abertura = operador_abertura;
		this.contato = contato;
		this.cidade = cidade;
		this.bairro = bairro;
		this.endereco = endereco;
		this.referencia = referencia;
		this.status = status;
		this.tipo_encaminhamento = tipo_encaminhamento;
		this.motivo = motivo;
		this.problema = problema;
		this.conclusao = conclusao;
		this.ausente = ausente;
		this.prioridade = prioridade;
		this.obs = obs;
		this.nota_fiscal = nota_fiscal;
		this.tipo_servico = tipo_servico;
		this.tecnico = tecnico;
		this.operador = operador;
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

	public Integer getId_ecf_pre_venda_cabecalho() {
		return id_ecf_pre_venda_cabecalho;
	}

	public void setId_ecf_pre_venda_cabecalho(Integer id_ecf_pre_venda_cabecalho) {
		this.id_ecf_pre_venda_cabecalho = id_ecf_pre_venda_cabecalho;
	}

	public Integer getVenda_servico_cabecalho_id() {
		return venda_servico_cabecalho_id;
	}

	public void setVenda_servico_cabecalho_id(Integer venda_servico_cabecalho_id) {
		this.venda_servico_cabecalho_id = venda_servico_cabecalho_id;
	}

	public SubGrupoOse getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(SubGrupoOse subgrupo) {
		this.subgrupo = subgrupo;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public TipoSubGrupoOse getTipo_subgrupo() {
		return tipo_subgrupo;
	}

	public void setTipo_subgrupo(TipoSubGrupoOse tipo_subgrupo) {
		this.tipo_subgrupo = tipo_subgrupo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Veiculos getVeiculo_id() {
		return veiculo_id;
	}

	public void setVeiculo_id(Veiculos veiculo_id) {
		this.veiculo_id = veiculo_id;
	}

	public Date getData_ex() {
		return data_ex;
	}

	public void setData_ex(Date data_ex) {
		this.data_ex = data_ex;
	}

	public Date getData_encaminhamento() {
		return data_encaminhamento;
	}

	public void setData_encaminhamento(Date data_encaminhamento) {
		this.data_encaminhamento = data_encaminhamento;
	}

	public Date getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}

	public Date getData_fechamento() {
		return data_fechamento;
	}

	public void setData_fechamento(Date data_fechamento) {
		this.data_fechamento = data_fechamento;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getOperador_abertura() {
		return operador_abertura;
	}

	public void setOperador_abertura(String operador_abertura) {
		this.operador_abertura = operador_abertura;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo_encaminhamento() {
		return tipo_encaminhamento;
	}

	public void setTipo_encaminhamento(String tipo_encaminhamento) {
		this.tipo_encaminhamento = tipo_encaminhamento;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public String getAusente() {
		return ausente;
	}

	public void setAusente(String ausente) {
		this.ausente = ausente;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getNota_fiscal() {
		return nota_fiscal;
	}

	public void setNota_fiscal(String nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}

	public String getTipo_servico() {
		return tipo_servico;
	}

	public void setTipo_servico(String tipo_servico) {
		this.tipo_servico = tipo_servico;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getContrato() {
		return contrato;
	}

	public void setContrato(Integer contrato) {
		this.contrato = contrato;
	}

	public String getConcentrador() {
		return concentrador;
	}

	public void setConcentrador(String concentrador) {
		this.concentrador = concentrador;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getMotivo_reagendamento() {
		return motivo_reagendamento;
	}

	public void setMotivo_reagendamento(String motivo_reagendamento) {
		this.motivo_reagendamento = motivo_reagendamento;
	}

	public Date getData_conclusao() {
		return data_conclusao;
	}

	public void setData_conclusao(Date data_conclusao) {
		this.data_conclusao = data_conclusao;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public String getTempo_total_atendimento() {
		if(getData_abertura() != null && getData_conclusao() != null){
			
			Date data1 = getData_abertura();
			Date data2 = getData_conclusao();

			long segundos = (data2.getTime() - data1.getTime()) / 1000;
			int semanas = (int)Math.floor(segundos / 604800);
			segundos -= semanas * 604800;
			int dias = (int)Math.floor(segundos / 86400);
			segundos -= dias * 86400;
			int horas = (int)Math.floor(segundos / 3600);
			segundos -= horas * 3600;
			int minutos = (int)Math.floor(segundos / 60);
			segundos -= minutos * 60;

			
			return dias + "d, " +horas + "h, " + minutos+"min";
		}
		
		return "";
	}

	public void setTempo_total_atendimento(String tempo_atendimento) {
		this.tempo_total_atendimento = tempo_atendimento;
	}

	public String getTempo_atendimento() {
		if(getData_encaminhamento() != null){
			
			Date data1 = getData_encaminhamento();
			
			Date data2 = new Date();
			if(getStatus().equals("CONCLUIDO") && getData_conclusao() != null){
				data2 = getData_conclusao();
			}

			long segundos = (data2.getTime() - data1.getTime()) / 1000;
			int semanas = (int)Math.floor(segundos / 604800);
			segundos -= semanas * 604800;
			int dias = (int)Math.floor(segundos / 86400);
			segundos -= dias * 86400;
			int horas = (int)Math.floor(segundos / 3600);
			segundos -= horas * 3600;
			int minutos = (int)Math.floor(segundos / 60);
			segundos -= minutos * 60;

			
			return dias + "d, " +horas + "h, " + minutos+"min";
		}
		
	
		
		return "";
	}

	public void setTempo_atendimento(String tempo_atendimento) {
		this.tempo_atendimento = tempo_atendimento;
	}



	
	
}
