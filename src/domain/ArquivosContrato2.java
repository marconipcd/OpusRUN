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
@Table(name="arquivos_contrato2")
public class ArquivosContrato2 {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;	
	
	@Column(name="ACESSO_CLIENTE_ID")
	private Integer contrato;
	
	@Column(name="NOME",nullable=true)
	private String nome;
	
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA")
	private Date data;
	
	@Column(name="USUARIO")
	private String usuario;
	
	public ArquivosContrato2(){
		
	}

	public ArquivosContrato2(Integer id, Integer contrato,String nome, Date data) {
		super();
		this.id = id;
		this.contrato = contrato;
		this.nome = nome;		
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContrato() {
		return contrato;
	}

	public void setContrato(Integer contrato) {
		this.contrato = contrato;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
