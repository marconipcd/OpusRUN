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
@Table(name="alteracoes_contrato")
public class AlterarcoesContrato {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="TIPO", nullable=false)
	private String tipo;
	
	@OneToOne
	@JoinColumn(name="CONTRATO_ID", nullable=false)
	private AcessoCliente contrato;
	
	@OneToOne
	@JoinColumn(name="OPERADOR_ID", nullable=false)
	private Usuario operador;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ALTERACAO", nullable=false)
	private Date data_alteracao;

	public AlterarcoesContrato(){
		
	}
	
	public AlterarcoesContrato(Integer id) {
		super();
		this.id = id;
	}
	
	public AlterarcoesContrato(Integer id, String tipo, AcessoCliente contrato,
			Usuario operador, Date data_alteracao) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.contrato = contrato;
		this.operador = operador;
		this.data_alteracao = data_alteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public AcessoCliente getContrato() {
		return contrato;
	}

	public void setContrato(AcessoCliente contrato) {
		this.contrato = contrato;
	}

	public Usuario getOperador() {
		return operador;
	}

	public void setOperador(Usuario operador) {
		this.operador = operador;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
	
	
}
