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
@Table(name="controle_titulo")
public class ControleTitulo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false,unique=true, name="ID")
	private Integer id;
	@Column(nullable=false, name="EMPRESA_ID")
	private Integer empresa_id;
	@Column(nullable=false, name="NOME")
	private String nome;
	@OneToOne
	@JoinColumn(name="CONTA_BANCARIA") 
	private ContaBancaria conta_bancaria;
	@Column(nullable=false, name="REGISTRO")
	private String registro;
	
	@OneToOne
	@JoinColumn(name="CONTA_BANCARIA_BKP")
	private ContaBancaria conta_bancaria_bkp;
	
	
	public ControleTitulo(){
		
	}

	public ControleTitulo(Integer id, Integer empresa_id, String nome, ContaBancaria conta_bancaria) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.nome = nome;
		this.conta_bancaria = conta_bancaria;
	}
	
	public ControleTitulo(Integer id, Integer empresa_id, String nome,ContaBancaria conta_bancaria, ContaBancaria conta_bancaria_bkp){
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.nome = nome;
		this.conta_bancaria_bkp = conta_bancaria_bkp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ContaBancaria getConta_bancaria_bkp(){
		return conta_bancaria_bkp;
	}
	public void setConta_bancaria_bkp(ContaBancaria conta_bancaria_bkp){
		this.conta_bancaria_bkp = conta_bancaria_bkp;
	}

	public Integer getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ContaBancaria getConta_bancaria() {
		return conta_bancaria;
	}

	public void setConta_bancaria(ContaBancaria conta_bancaria) {
		this.conta_bancaria = conta_bancaria;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	
	
	
}
