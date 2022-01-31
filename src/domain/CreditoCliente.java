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
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="credito_cliente")
@Cacheable(value=false)
public class CreditoCliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true, length=10)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="CLIENTES_ID")
	private Cliente cliente;
	
	@Column(name="LIMITE_CREDITO", nullable=true, unique=false, length=20)
	private String limite_credito;
	
	@Column(name="SALDO", nullable=true, unique=false, length=20)
	private String saldo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ALTERACAO", nullable=true, unique=false)
	private Date data_alteracao;
	
	@PreUpdate
	private void onUpdate(){
		data_alteracao = new Date();
	}
	
	@PrePersist
	private void onInsert(){
		data_alteracao = new Date();
	}
	
	public CreditoCliente(){
		
	}

	public CreditoCliente(Integer id, Cliente cliente, String limite_credito,
			String saldo) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.limite_credito = limite_credito;
		this.saldo = saldo;
		//this.data_alteracao = data_altercao;
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

	public String getLimite_credito() {
		return limite_credito;
	}

	public void setLimite_credito(String limite_credito) {
		this.limite_credito = limite_credito;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
	
	
	
	
	
}
