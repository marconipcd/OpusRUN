package domain;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="como_nos_conheceu")
public class ComoNosConheceu {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	private Integer empresa_id;
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_cadastro;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_alteracao;
	
	public ComoNosConheceu(){
		
	}

	public ComoNosConheceu(Integer id){
		super();
		this.id = id;
	}
	
	public ComoNosConheceu(Integer id, Integer empresa_id, String nome,
			Date data_cadastro, Date data_alteracao) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.nome = nome;
		this.data_cadastro = data_cadastro;
		this.data_alteracao = data_alteracao;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
	
	
}
