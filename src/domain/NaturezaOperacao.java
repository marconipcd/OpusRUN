package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="natureza_operacao")
public class NaturezaOperacao {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;
	@Column(name="EMPRESA_ID", nullable=false)
	private Integer empresa_id;
	@Column(name="STATUS")
	private String status;
	@Column(name="STATUS_SAIDA")
	private String status_saida;
	@Column(name="DESCRICAO")
	private String descricao;
	@Column(name="APLICACAO")
	private String aplicacao;
	@Column(name="VISUALIZAR")
	private boolean visualizar;	
	@Column(name="EMITIR_ECF")
	private String emitir_ecf;
	@Column(name="tipo")
	private String tipo;
	
	public NaturezaOperacao(){
		
	}

	public NaturezaOperacao(Integer id, Integer empresa_id, String status,
			String descricao, String aplicacao, boolean visualizar,String emitir_ecf) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.status = status;
		this.descricao = descricao;
		this.aplicacao = aplicacao;
		this.visualizar = visualizar;
		this.emitir_ecf = emitir_ecf;
	}
	
	public NaturezaOperacao(Integer id, Integer empresa_id, String status,
			String descricao, String aplicacao, boolean visualizar) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.status = status;
		this.descricao = descricao;
		this.aplicacao = aplicacao;
		this.visualizar = visualizar;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public boolean isVisualizar() {
		return visualizar;
	}

	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmitir_ecf() {
		return emitir_ecf;
	}

	public void setEmitir_ecf(String emitir_ecf) {
		this.emitir_ecf = emitir_ecf;
	}

	public String getStatus_saida() {
		return status_saida;
	}

	public void setStatus_saida(String status_saida) {
		this.status_saida = status_saida;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
