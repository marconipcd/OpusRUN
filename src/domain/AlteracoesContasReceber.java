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
@Table(name="alteracoes_conta_Receber")
public class AlteracoesContasReceber {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="TIPO", nullable=false)
	private String tipo;
	
	@OneToOne
	@JoinColumn(name="CONTA_RECEBER_ID", nullable=false)
	private ContasReceber conta_Receber;
	
	@Column(name="OPERADOR_ID", nullable=false)
	private Integer operador;
	
	@OneToOne
	@JoinColumn(name="EMPRESA_ID", nullable=false)
	private Empresa empresa_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ALTERACAO", nullable=false)
	private Date data_alteracao;

	public AlteracoesContasReceber(){
		
	}
	
	public AlteracoesContasReceber(Integer id) {
		super();
		this.id = id;
	}
	
	public AlteracoesContasReceber(Integer id, String tipo, ContasReceber conta_Receber,
			Empresa empresa_id,Integer operador, Date data_alteracao) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.conta_Receber = conta_Receber;
		this.operador = operador;
		this.data_alteracao = data_alteracao;
		this.empresa_id = empresa_id;
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

	public ContasReceber getContasReceber() {
		return conta_Receber;
	}

	public void setconta_Receber(ContasReceber conta_Receber) {
		this.conta_Receber = conta_Receber;
	}

	public Integer getOperador() {
		return operador;
	}

	public void setOperador(Integer operador) {
		this.operador = operador;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public ContasReceber getConta_Receber() {
		return conta_Receber;
	}

	public void setConta_Receber(ContasReceber conta_Receber) {
		this.conta_Receber = conta_Receber;
	}

	public Empresa getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(Empresa empresa_id) {
		this.empresa_id = empresa_id;
	}
	
	
}


