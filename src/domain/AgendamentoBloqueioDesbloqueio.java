package domain;

import java.util.Date;

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
@Table(name="agendamento_bloqueio_desbloqueio")
public class AgendamentoBloqueioDesbloqueio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="CONTRATO_ID")
	private AcessoCliente contrato;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_AGENDADO")
	private Date data_agendado;
	
	@Column(name="USUARIO")	
	private String usuario;
	
	@Column(name="STATUS")
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date data_cadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_EXECUCAO")
	private Date data_execucao;
	
	public AgendamentoBloqueioDesbloqueio(Integer id, AcessoCliente contrato,
			String tipo, Date data_agendado, String usuario, String status,
			Date data_cadastro, Date data_execucao) {
		super();
		this.id = id;
		this.contrato = contrato;
		this.tipo = tipo;
		this.data_agendado = data_agendado;
		this.usuario = usuario;
		this.status = status;
		this.data_cadastro = data_cadastro;
		this.data_execucao = data_execucao;
	}
	
	public AgendamentoBloqueioDesbloqueio(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AcessoCliente getContrato() {
		return contrato;
	}

	public void setContrato(AcessoCliente contrato) {
		this.contrato = contrato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData_agendado() {
		return data_agendado;
	}

	public void setData_agendado(Date data_agendado) {
		this.data_agendado = data_agendado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_execucao() {
		return data_execucao;
	}

	public void setData_execucao(Date data_execucao) {
		this.data_execucao = data_execucao;
	}
	
	
	
	
}
