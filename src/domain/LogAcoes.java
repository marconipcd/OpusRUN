package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="log_acoes")
public class LogAcoes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="OPERADOR")
	private String operador;
	
	@Column(name="ACAO", columnDefinition="TEXT")
	private String acao;
	
	@Column(name="DATA_CADASTRO")
	private Date data_cadastro;
	
	@PrePersist
	private void onCreate(){
		data_cadastro = new Date();
	}
	
	public LogAcoes(){
		
	}

	public LogAcoes(Integer id, String operador, String acao) {
		super();
		this.id = id;
		this.operador = operador;
		this.acao = acao;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	
}
