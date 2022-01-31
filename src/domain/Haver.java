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

@Entity
@Table(name="haver_cab")
public class Haver {

	@Id
	@Column(name="ID",nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	@OneToOne
	@JoinColumn(name="CLIENTE_ID", nullable=false)
	private Cliente cliente;
	@Column(name="VALOR_TOTAL")
	private double valor_total;
	@Column(name="DATA_ALTERACAO")
	private Date data_alteracao;
	@Column(name="STATUS")
	private String status;
	
	public Haver(){
		
	}

	public Haver(Integer id, Cliente cliente, double valor_total,Date data_alteracao, String status) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.valor_total = valor_total;
		this.data_alteracao = data_alteracao;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
