package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="swith")
public class Swith {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="EMPRESA_ID")
	private Integer empresa_id;
	
	@Column(name="IDENTIFICACAO")
	private String identificacao;
	
	@OneToOne
	@JoinColumn(name="CONCENTRADOR_ID")
	private Concentrador concentrador;
	
	@Column(name="MODELO")
	private String modelo;
	
	@Column(name="ENDERECO")
	private String endereco;
	
	@Column(name="NUMERO")
	private String numero;
	
	@Column(name="REFERENCIA")
	private String referencia;
	
	@Column(name="OLT")
	private String olt;
	
	@Column(name="PON")
	private String pon;
	
	@Column(name="SINAL_DB")
	private String sinal_db;
	
	public Swith(){
		
	}

	public Swith(Integer id, Integer empresa_id, String identificacao,
			Concentrador concentrador, String modelo, String endereco,
			String numero, String referencia) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.identificacao = identificacao;
		this.concentrador = concentrador;
		this.modelo = modelo;
		this.endereco = endereco;
		this.numero = numero;
		this.referencia = referencia;
	}
	public Swith(Integer id, Integer empresa_id, String identificacao,
			Concentrador concentrador, String modelo, String endereco,
			String numero, String referencia, String pon) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.identificacao = identificacao;
		this.concentrador = concentrador;
		this.modelo = modelo;
		this.endereco = endereco;
		this.numero = numero;
		this.referencia = referencia;
		this.pon = pon;
	}

	public Integer getId() {
		return id;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}



	public Integer getEmpresa_id() {
		return empresa_id;
	}



	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}







	public Concentrador getConcentrador() {
		return concentrador;
	}







	public void setConcentrador(Concentrador concentrador) {
		this.concentrador = concentrador;
	}

	public String getPon() {
		return pon;
	}

	public void setPon(String pon) {
		this.pon = pon;
	}

	public String getOlt() {
		return olt;
	}

	public void setOlt(String olt) {
		this.olt = olt;
	}

	public String getSinal_db() {
		return sinal_db;
	}

	public void setSinal_db(String sinal_db) {
		this.sinal_db = sinal_db;
	}
	
	
	
	
	
}
