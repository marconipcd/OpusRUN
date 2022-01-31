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
@Table(name="haver_detalhe")
public class HaverDetalhe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false)
	private Integer id;
	@OneToOne
	@JoinColumn(name="HAVER_CAB_ID", nullable=false)
	private Haver haverCab;
	@Column(name="TIPO")
	private String tipo;
	@Column(name="VALOR")
	private double valor;
	@Column(name="DOC")
	private String doc;
	@Column(name="N_DOC")
	private String n_doc;
	@Column(name="REFERENTE")
	private String referente;	
	@Column(name="DESCRICAO")
	private String descricao;	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_EMISSAO")
	private Date data_emissao;
	@Column(name="OPERADOR")
	private String operador;
	@Column(name="STATUS")
	private String status;
	
	public HaverDetalhe(){
		
	}

	public HaverDetalhe(Integer id, Haver haverCab, String tipo,
			double valor, String doc, String n_doc, String referente,String descricao,
			Date data_emissao, String operador, String status) {
		super();
		this.id = id;
		this.haverCab = haverCab;
		this.tipo = tipo;
		this.valor = valor;
		this.doc = doc;
		this.n_doc = n_doc;
		this.referente = referente;
		this.descricao = descricao;
		this.data_emissao = data_emissao;
		this.operador = operador;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Haver getHaverCab() {
		return haverCab;
	}

	public void setHaverCab(Haver haverCab) {
		this.haverCab = haverCab;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getN_doc() {
		return n_doc;
	}

	public void setN_doc(String n_doc) {
		this.n_doc = n_doc;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}

	public Date getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

