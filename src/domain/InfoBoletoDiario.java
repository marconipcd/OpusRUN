package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="info_boleto_diario")
public class InfoBoletoDiario {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="QTD_BOLETOS_BLOQUEADOS")
	private Integer qtd_boletos_bloqueados;
	
	@Column(name="TOTAL_BOLETOS_BLOQUEADOS")
	private double total_boletos_bloqueados;
	
	@Column(name="QTD_BOLETOS_NEGATIVADOS")
	private Integer qtd_boletos_negativados;
	
	@Column(name="TOTAL_BOLETOS_NEGATIVADOS")
	private double total_boletos_negativados;
	
	@Column(name="DATA")
	private Date data;
	
	public InfoBoletoDiario(){
		
	}
	

	public InfoBoletoDiario(Integer id, Integer qtd_boletos_bloqueados,
			double total_boletos_bloqueados, Integer qtd_boletos_negativados,
			double total_boletos_negativados, Date data) {
		super();
		this.id = id;
		this.qtd_boletos_bloqueados = qtd_boletos_bloqueados;
		this.total_boletos_bloqueados = total_boletos_bloqueados;
		this.qtd_boletos_negativados = qtd_boletos_negativados;
		this.total_boletos_negativados = total_boletos_negativados;
		this.data = data;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtd_boletos_bloqueados() {
		return qtd_boletos_bloqueados;
	}

	public void setQtd_boletos_bloqueados(Integer qtd_boletos_bloqueados) {
		this.qtd_boletos_bloqueados = qtd_boletos_bloqueados;
	}

	public double getTotal_boletos_bloqueados() {
		return total_boletos_bloqueados;
	}

	public void setTotal_boletos_bloqueados(double total_boletos_bloqueados) {
		this.total_boletos_bloqueados = total_boletos_bloqueados;
	}

	public Integer getQtd_boletos_negativados() {
		return qtd_boletos_negativados;
	}

	public void setQtd_boletos_negativados(Integer qtd_boletos_negativados) {
		this.qtd_boletos_negativados = qtd_boletos_negativados;
	}

	public double getTotal_boletos_negativados() {
		return total_boletos_negativados;
	}

	public void setTotal_boletos_negativados(double total_boletos_negativados) {
		this.total_boletos_negativados = total_boletos_negativados;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
