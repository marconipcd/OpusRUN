package domain;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="enderecos_principais")
@Cacheable(value=false)
public class Endereco {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	@Column(name="FIADORES_ID", nullable=true, unique=false, length=10)
	private Integer fiadores_id;
	@Column(name="CEP",nullable=false, unique=false)
	private String cep;
	@Column(name="ENDERECO",nullable=false, unique=false)
	private String endereco;
	@Column(name="NUMERO", nullable=false, unique=false)
	private String numero;
	@Column(name="CIDADE", nullable=false, unique=false)
	private String cidade;
	@Column(name="BAIRRO", nullable=false, unique=false)
	private String bairro;
	@Column(name="UF", nullable=false, unique=false)
	private String uf;
	@Column(name="PAIS", nullable=false, unique=false)
	private String pais;
	@Column(name="COMPLEMENTO", nullable=true, unique=false)
	private String complemento;
	@Column(name="REFERENCIA", nullable=false, unique=false)
	private String referencia;
	@Column(name="PRINCIPAL", nullable=true)
	private boolean principal;
	@Column(name="STATUS", nullable=true)
	private String status;
	
	
	@OneToOne
	@JoinColumn(name="CLIENTES_ID")
	private Cliente clientes;
	
	
	public Endereco(){
		
	}
	
	public Endereco(Integer id){
		super();
		this.id = id;
	}

	public Endereco(Integer id, Integer fiadores_id, String cep,
			String endereco, String numero, String cidade, String bairro,
			String uf, String pais, String complemento, String referencia,
			Cliente clientes) {
		super();
		this.id = id;
		this.fiadores_id = fiadores_id;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.cidade = cidade;
		this.bairro = bairro;
		this.uf = uf;
		this.pais = pais;
		this.complemento = complemento;
		this.referencia = referencia;
		this.clientes = clientes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getFiadores_id() {
		return fiadores_id;
	}

	public void setFiadores_id(Integer fiadores_id) {
		this.fiadores_id = fiadores_id;
	}

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
   

	
	
}
