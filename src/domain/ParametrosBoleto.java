package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="parametros_boleto")
public class ParametrosBoleto {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id; 
	
	@Column(name="CLIENTES_ID")
	private Integer cliente_id; 
	
	@Column(name="COBRAR_TAXA_BANCARIA")
	private boolean cobrar_taxa_bancaria;

	
	public ParametrosBoleto() {
	
	}

	
	public ParametrosBoleto(Integer id, Integer cliente_id,
			boolean cobrar_taxa_bancaria) {
		super();
		this.id = id;
		this.cliente_id = cliente_id;
		this.cobrar_taxa_bancaria = cobrar_taxa_bancaria;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}

	public boolean getCobrar_taxa_bancaria() {
		return cobrar_taxa_bancaria;
	}

	public void setCobrar_taxa_bancaria(boolean cobrar_taxa_bancaria) {
		this.cobrar_taxa_bancaria = cobrar_taxa_bancaria;
	} 
	

}
